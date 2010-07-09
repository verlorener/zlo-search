package info.xonix.zlo.web.decorators;

import info.xonix.zlo.search.config.DateFormats;
import info.xonix.zlo.web.utils.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;
import org.springframework.web.util.HtmlUtils;

import java.util.TreeMap;

/**
 * Author: Vovan
 * Date: 30.03.2008
 * Time: 16:56:20
 */
public class HistoryTableDecorator extends TableDecorator {

    public static final int MAX_LEN = 40;

    private String shortenString(String s, int len) {
        if (s.length() > len) {
            s = StringUtils.substring(s, 0, len - 3) + "...";
        }

        return s;
    }

    private String shortenString(Object s) {
        if (s == null)
            return "";
        return shortenString(s.toString(), MAX_LEN);
    }


    public String getSearchText() {
        return HtmlUtils.htmlEscape(shortenString(((TreeMap) getCurrentRowObject()).get("req_text")));
    }

    public String getSearchNick() {
        return HtmlUtils.htmlEscape(shortenString(((TreeMap) getCurrentRowObject()).get("req_nick")));
    }

    public String getSearchHost() {
        return HtmlUtils.htmlEscape(shortenString(((TreeMap) getCurrentRowObject()).get("req_host")));
    }

    public String getReqDate() {
        return DateFormats.ddMMyyyyy_HHmm.format(((TreeMap) getCurrentRowObject()).get("req_date"));
    }

    public String getUserAgentSmall() {
        return RequestUtils.getUserAgentSmall((String) ((TreeMap) getCurrentRowObject()).get("user_agent"));
    }
}
