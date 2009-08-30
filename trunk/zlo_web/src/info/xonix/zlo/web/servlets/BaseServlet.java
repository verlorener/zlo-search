package info.xonix.zlo.web.servlets;

import info.xonix.zlo.search.dao.Site;
import info.xonix.zlo.web.CookieUtils;
import info.xonix.zlo.web.servlets.helpful.ForwardingRequest;
import info.xonix.zlo.web.servlets.helpful.ForwardingServlet;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: Vovan
 * Date: 21.12.2007
 * Time: 16:32:17
 */
public class BaseServlet extends ForwardingServlet {
    public static final String REQ_SITE_ROOT = "siteRoot";
    public static final String QS_SITE = "site";

    protected void setSiteInReq(ForwardingRequest request, HttpServletResponse response) {
        String siteInCookie;
        Site site;

        String siteNumStr = request.getParameter(QS_SITE);

        if (StringUtils.isNotEmpty(siteNumStr)) {
            site = getSiteOrDefault(siteNumStr);
            CookieUtils.rememberInCookie(response, QS_SITE, site.getNum().toString());
        } else if (StringUtils.isNotEmpty(siteInCookie = CookieUtils.recallFromCookie(request, QS_SITE))) {
            site = getSiteOrDefault(siteInCookie);
        } else {
            site = getSiteOrDefault("0");
        }

        request.setParameter(QS_SITE, site.getNum().toString());
        request.setAttribute(QS_SITE, site);
        request.setAttribute(REQ_SITE_ROOT, site.getSITE_URL());
    }

    private Site getSiteOrDefault(String siteNumStr) {
        Site defaultSite = Site.getSites().get(0);
        Site site;
        try {
            site = Site.getSite(Integer.parseInt(siteNumStr));
            if (site == null) {
                site = defaultSite;
            }
        } catch (Exception e) {
            site = defaultSite;
        }
        return site;
    }

    public static Site getSite(HttpServletRequest req) {
        // todo: tmp
        String sn = req.getParameter(QS_SITE);
        int siteId;
        if (StringUtils.isNotEmpty(sn)) {
            siteId = Integer.parseInt(sn);
        } else {
            siteId = 0;
        }

        return Site.getSite(siteId);
    }
}
