package info.xonix.zlo.search.logic.forum_adapters;

import info.xonix.zlo.search.model.Message;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gubarkov
 * Date: 19.02.12
 * Time: 2:14
 */
public abstract class ForumAdapterAbstract implements ForumAdapter {
    private final static Logger log = Logger.getLogger(ForumAdapterAbstract.class);

    private int retryCount = 3;

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * @param from (including)
     * @param to   (excluding)
     * @return list of received messages
     * @throws ForumAccessException if can't get msg from site
     */
    public List<Message> getMessages(long from, long to) throws ForumAccessException {
        if (to < from) {
            throw new IllegalArgumentException("to (" + to + ") < from (" + from + ")");
        }

        List<Message> messages = new ArrayList<Message>((int) (to - from));

        for (long messageId = from; messageId < to; messageId++) {
            messages.add(getMessageWithRetrying(messageId));
        }

        return messages;
    }

    public Message getMessageWithRetrying(long messageId) throws ForumAccessException {
        for (int i = 0; i < retryCount; i++) {
            try {
                return getMessage(messageId);
            } catch (ForumAccessException e) {
                if (i == (retryCount - 1)) { // last iteration
                    throw e;
                }

                log.warn("Problem getting message #" + messageId + ". Retrying...");
            }
        }

        throw new IllegalStateException("We should not get here...");
    }
}