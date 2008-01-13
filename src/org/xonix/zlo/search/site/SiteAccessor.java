package org.xonix.zlo.search.site;

import org.xonix.zlo.search.config.Config;
import org.xonix.zlo.search.model.ZloMessage;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


/**
 * Author: Vovan
 * Date: 28.12.2007
 * Time: 2:45:21
 */
public class SiteAccessor {

    public static final String SITE_CONFIG_PREFIX = "site.config.";

    public String MARK_END_MSG_1;
    public String MARK_END_MSG_2;
    public String MSG_NOT_EXIST_OR_WRONG;
    public String WITHOUT_TOPIC;

    // regexes
    public String MSG_REG_RE_STR;
    public String MSG_UNREG_RE_STR;
    public String LINK_INDEX_REGEX;

    public String SITE_URL;
    public String READ_QUERY;

    public String JNDI_DS_NAME;

    private String DB_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    public boolean DB_VIA_CONTAINER;

    private String siteName;


    public SiteAccessor(String siteName) {
        Properties p = Config.loadProperties("org/xonix/zlo/search/config/" + Config.getProp(SITE_CONFIG_PREFIX + siteName));

        setSiteName(siteName);

        MARK_END_MSG_1 =        p.getProperty("str.mark.end.1");
        MARK_END_MSG_2 =        p.getProperty("str.mark.end.2");

        MSG_NOT_EXIST_OR_WRONG = p.getProperty("str.msg.not.exists");
        WITHOUT_TOPIC =         p.getProperty("str.without.topic");

        MSG_REG_RE_STR =        p.getProperty("regex.msg.reg");
        MSG_UNREG_RE_STR =      p.getProperty("regex.msg.unreg");

        LINK_INDEX_REGEX =      p.getProperty("regex.link.index");

        SITE_URL =              p.getProperty("site.url");
        READ_QUERY =            p.getProperty("site.read.query");
        // db -----
        JNDI_DS_NAME =          p.getProperty("db.jndi.ds.name");

        DB_DRIVER =             p.getProperty("db.driver");
        DB_URL =                p.getProperty("db.url");
        DB_USER =               p.getProperty("db.user");
        DB_PASSWORD =           p.getProperty("db.password");

        DB_VIA_CONTAINER = Config.TRUE.equals(p.getProperty("db.use.container.pull"));
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public ZloMessage getMessage(int num) throws IOException {
        return getParser().parseMessage(getRetriever().getPageContentByNumber(num), num);
    }

    private PageRetriever retreiver;
    private PageRetriever getRetriever() {
        if (retreiver == null) {
            retreiver = new PageRetriever(this);
        }
        return retreiver;
    }

    private PageParser parser;
    private PageParser getParser() {
        if (parser == null) {
            parser = new PageParser(this);
        }
        return parser;
    }

    public int getLastRootMessageNumber() throws IOException {
        return getRetriever().getLastRootMessageNumber(); 
    }

    private DriverManagerDataSource ds;
    public DataSource getDataSource() {
        if (ds == null) {
            ds = new DriverManagerDataSource();
            ds.setDriverClassName(DB_DRIVER);
            ds.setUrl(DB_URL);
            ds.setUsername(DB_USER);
            ds.setPassword(DB_PASSWORD);
        }
        return ds;
    }
}
