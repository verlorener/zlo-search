package info.xonix.zlo.web.ws;

import info.xonix.zlo.search.dao.MessagesDao;
import info.xonix.zlo.search.domainobj.Site;
import info.xonix.zlo.search.logic.AppLogic;
import info.xonix.zlo.search.logic.SearchException;
import info.xonix.zlo.search.logic.SearchLogic;
import info.xonix.zlo.search.logic.SiteLogic;
import info.xonix.zlo.web.ws.dto.Message;
import info.xonix.zlo.web.ws.dto.MessageShallow;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Vovan
 * Date: 12.12.10
 * Time: 18:53
 */
@WebService
public class BoardSearchService {
    private static final Logger log = Logger.getLogger(BoardSearchService.class);

    @Autowired
    private AppLogic appLogic;

    @Autowired
    private SiteLogic siteLogic;

    @Autowired
    private SearchLogic searchLogic;

    @Autowired
    private MessagesDao messagesDao;

    @Nonnull
    private Site site(int siteId) throws ServiceException {
        final Site site = siteLogic.getSite(siteId);
        if (site == null) {
            throw new ServiceException("Wrong siteId: " + siteId);
        }
        return site;
    }

    private Message fromMessageModel(info.xonix.zlo.search.model.Message messageModel) {
        return new Message(
                messageModel.getNum(),
                messageModel.getNick(),
                messageModel.getHost(),
                messageModel.isReg(),
                messageModel.getTopic(),
                messageModel.getTitle(),
                messageModel.getBody(),
                messageModel.getDate(),
                messageModel.isHasUrl(),
                messageModel.isHasImg());
    }

    private MessageShallow fromMessageModelShallow(info.xonix.zlo.search.model.MessageShallow message) {
        return new MessageShallow(
                message.getNum(),
                message.getNick(),
                message.getHost(),
                message.isReg(),
                message.getDate(),
                message.getTopic(),
                message.getTitle());
    }

    @WebMethod
    public int getLastSavedMsgNumber(@WebParam(name = "siteId") int siteId) throws ServiceException {
        log.info("getLastSavedMsgNumber(" + siteId + ")");

        return appLogic.getLastSavedMessageNumber(site(siteId));
    }

    @WebMethod
    public int getLastIndexedMsgNumber(@WebParam(name = "siteId") int siteId) throws ServiceException {
        log.info("getLastIndexedMsgNumber(" + siteId + ")");

        return appLogic.getLastIndexedNumber(site(siteId));
    }

    @WebMethod
    public Message getMessage(
            @WebParam(name = "siteId") int siteId,
            @WebParam(name = "msgId") int msgId) throws ServiceException {
        log.info("getMessage(" + siteId + ", " + msgId + ")");

        return fromMessageModel(appLogic.getMessageByNumber(site(siteId), msgId));
    }

    @WebMethod
    public List<Message> search(
            @WebParam(name = "siteId") int siteId,
            @WebParam(name = "searchString") String searchString,
            @WebParam(name = "skip") int skip,
            @WebParam(name = "limit") int limit) throws ServiceException {
        log.info("search(" + siteId + ", \"" + searchString + "\", " + skip + ", " + limit + ")");

        final Site site = site(siteId);

        final int[] resultIds = search(site, searchString, skip, limit);

        final List<info.xonix.zlo.search.model.Message> messages = messagesDao.getMessages(site, resultIds);

        List<Message> resultMessages = new ArrayList<Message>(messages.size());

        for (info.xonix.zlo.search.model.Message message : messages) {
            resultMessages.add(fromMessageModel(message));
        }

        return resultMessages;
    }

    @WebMethod
    public List<MessageShallow> searchShallow(
            @WebParam(name = "siteId") int siteId,
            @WebParam(name = "searchString") String searchString,
            @WebParam(name = "skip") int skip,
            @WebParam(name = "limit") int limit) throws ServiceException {
        log.info("searchShallow(" + siteId + ", \"" + searchString + "\", " + skip + ", " + limit + ")");

        final Site site = site(siteId);

        final int[] resultIds = search(site, searchString, skip, limit);

        final List<info.xonix.zlo.search.model.MessageShallow> messages = messagesDao.getShallowMessages(site, resultIds);

        List<MessageShallow> resultMessages = new ArrayList<MessageShallow>(messages.size());

        for (info.xonix.zlo.search.model.MessageShallow message : messages) {
            resultMessages.add(fromMessageModelShallow(message));
        }

        return resultMessages;
    }

    private int[] search(Site site, String searchString, int skip, int limit) throws ServiceException {
        final int[] resultIds;

        try {
            resultIds = searchLogic.search(site, searchString, skip, limit);
        } catch (SearchException e) {
            throw new ServiceException("Search error:" + e, e);
        }

        return resultIds;
    }
}