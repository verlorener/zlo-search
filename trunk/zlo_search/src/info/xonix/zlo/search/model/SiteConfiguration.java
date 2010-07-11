package info.xonix.zlo.search.model;

import info.xonix.zlo.search.config.Config;
import info.xonix.zlo.search.utils.TimeUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;


/**
 * Author: Vovan
 * Date: 28.12.2007
 * Time: 2:45:21
 */
abstract class SiteConfiguration /*extends DbAccessor*/ {

    private String markEndMsg1;
    private String markEndMsg2;
    private String msgNotExistOrWrong;
    private String withoutTopic;

    // regexes
    private String msgRegReStr;
    private String msgUnregReStr;

    private Pattern msgRegRe;
    private Pattern msgUnregRe;

    private String linkIndexReStr;

    private Pattern linkIndexRe;

    private ArrayList<Integer> msgReGroupsOrder = null;

    private String siteUrl;
    private String siteSmilesPath;
    private String siteDescription;

    private String readQuery;

    private String msgDatePattern;

    // index
    private boolean performIndexing;
    private String indexDirDouble;

    private int indexerIndexPerTime;
    private int indexerIndexPeriod;
    private int indexerReconnectPeriod;
    private int indexerLimitPerSecond;

    // db daemon
    private int dbScanPerTime;
    private int dbScanPeriod;
    private int dbReconnectPeriod;

    private Integer siteNumber;

    private String name;


    public SiteConfiguration(String name) {
        Properties p = new Properties();

        try {
            for (String propFile : Config.getProp(Config.SITE_CONFIG_PREFIX + name).split(";")) {
                Config.loadProperties(p, "info/xonix/zlo/search/config/" + propFile);
            }
        } catch (NullPointerException e) {
            System.err.println("Can't locate: " + Config.SITE_CONFIG_PREFIX + name);
            throw e;
        }

        this.name = name;

        markEndMsg1 = p.getProperty("str.mark.end.1");
        markEndMsg2 = p.getProperty("str.mark.end.2");

        msgNotExistOrWrong = p.getProperty("str.msg.not.exists");
        withoutTopic = p.getProperty("str.without.topic");

        msgRegReStr = p.getProperty("regex.msg.reg");
        msgUnregReStr = p.getProperty("regex.msg.unreg");

        msgRegRe = msgRegReStr == null ? null : Pattern.compile(msgRegReStr, Pattern.DOTALL);
        msgUnregRe = msgUnregReStr == null ? null : Pattern.compile(msgUnregReStr, Pattern.DOTALL);

        String msgReGroups = p.getProperty("regex.msg.groups");
        if (msgReGroups != null) {
            msgReGroupsOrder = new ArrayList<Integer>();
            for (String s : StringUtils.split(msgReGroups, ',')) {
                msgReGroupsOrder.add(Integer.parseInt(s));
            }
        }

        linkIndexReStr = p.getProperty("regex.link.index");

        linkIndexRe = Pattern.compile(linkIndexReStr);

        siteUrl = p.getProperty("site.url");
        siteSmilesPath = p.getProperty("site.smiles.path");
        siteDescription = p.getProperty("site.description");

        readQuery = p.getProperty("site.read.query");

        // db -----
//        initDb(p);

        // indexer-----
        performIndexing = Config.TRUE.equals(p.getProperty("indexer.perform.indexing"));
        indexDirDouble = Config.getProp("indexer.dir.double") + "/index_" + name;

        indexerIndexPerTime = Integer.parseInt(p.getProperty("indexer.daemon.index.per.time"));
        indexerIndexPeriod = TimeUtils.parseToMilliSeconds(p.getProperty("indexer.daemon.period.to.index"));
        indexerReconnectPeriod = TimeUtils.parseToMilliSeconds(p.getProperty("indexer.daemon.period.to.reconnect"));
        indexerLimitPerSecond = Integer.parseInt(p.getProperty("indexer.limit.per.second"));

        // db daemon-----
        dbScanPerTime = Integer.parseInt(p.getProperty("db.daemon.scan.per.time"));
        dbScanPeriod = TimeUtils.parseToMilliSeconds(p.getProperty("db.daemon.period.to.scan"));
        dbReconnectPeriod = TimeUtils.parseToMilliSeconds(p.getProperty("db.daemon.period.to.reconnect"));

        siteNumber = Integer.parseInt(p.getProperty("site.number"));

        msgDatePattern = p.getProperty("str.date.pattern");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SiteConfiguration)) {
            return false;
        }

        return /*StringUtils.equals(getName(), ((SiteConfiguration) obj).getName()) &&*/
                StringUtils.equals(name, ((SiteConfiguration) obj).name);
    }

    // getters & setters
    // setters commented-out - object is immutable!

    public String getMarkEndMsg1() {
        return markEndMsg1;
    }

/*    public void setMarkEndMsg1(String markEndMsg1) {
        this.markEndMsg1 = markEndMsg1;
    }*/

    public String getMarkEndMsg2() {
        return markEndMsg2;
    }

/*    public void setMarkEndMsg2(String markEndMsg2) {
        this.markEndMsg2 = markEndMsg2;
    }*/

    public String getMsgNotExistOrWrong() {
        return msgNotExistOrWrong;
    }

/*    public void setMsgNotExistOrWrong(String msgNotExistOrWrong) {
        this.msgNotExistOrWrong = msgNotExistOrWrong;
    }*/

    public String getWithoutTopic() {
        return withoutTopic;
    }

/*    public void setWithoutTopic(String withoutTopic) {
        this.withoutTopic = withoutTopic;
    }*/

    public String getMsgRegReStr() {
        return msgRegReStr;
    }

/*    public void setMsgRegReStr(String msgRegReStr) {
        this.msgRegReStr = msgRegReStr;
    }*/

    public String getMsgUnregReStr() {
        return msgUnregReStr;
    }

    public Pattern getMsgRegRe() {
        return msgRegRe;
    }

    public Pattern getMsgUnregRe() {
        return msgUnregRe;
    }

    /*    public void setMsgUnregReStr(String msgUnregReStr) {
        this.msgUnregReStr = msgUnregReStr;
    }*/

    public String getLinkIndexReStr() {
        return linkIndexReStr;
    }

    public Pattern getLinkIndexRe() {
        return linkIndexRe;
    }
/*    public void setLinkIndexRegex(String linkIndexReStr) {
        this.linkIndexReStr = linkIndexReStr;
    }*/

    public ArrayList<Integer> getMsgReGroupsOrder() {
        return msgReGroupsOrder;
    }

/*    public void setMsgReGroupsOrder(ArrayList<Integer> msgReGroupsOrder) {
        this.msgReGroupsOrder = msgReGroupsOrder;
    }*/

    public String getSiteUrl() {
        return siteUrl;
    }

/*    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }*/

    public String getSiteSmilesPath() {
        return siteSmilesPath;
    }

/*    public void setSiteSmilesPath(String siteSmilesPath) {
        this.siteSmilesPath = siteSmilesPath;
    }*/

    public String getSiteDescription() {
        return siteDescription;
    }

/*    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }*/

    public String getReadQuery() {
        return readQuery;
    }

/*    public void setReadQuery(String readQuery) {
        this.readQuery = readQuery;
    }*/

    public String getMsgDatePattern() {
        return msgDatePattern;
    }

    public boolean isPerformIndexing() {
        return performIndexing;
    }

/*    public void setPerformIndexing(boolean performIndexing) {
        this.performIndexing = performIndexing;
    }*/

    public String getIndexDirDouble() {
        return indexDirDouble;
    }

/*    public void setIndexDirDouble(String indexDirDouble) {
        this.indexDirDouble = indexDirDouble;
    }*/

    public int getIndexerIndexPerTime() {
        return indexerIndexPerTime;
    }

/*    public void setIndexerIndexPerTime(int indexerIndexPerTime) {
        this.indexerIndexPerTime = indexerIndexPerTime;
    }*/

    public int getIndexerIndexPeriod() {
        return indexerIndexPeriod;
    }

/*    public void setIndexerIndexPeriod(int indexerIndexPeriod) {
        this.indexerIndexPeriod = indexerIndexPeriod;
    }*/

    public int getIndexerReconnectPeriod() {
        return indexerReconnectPeriod;
    }

/*    public void setIndexerReconnectPeriod(int indexerReconnectPeriod) {
        this.indexerReconnectPeriod = indexerReconnectPeriod;
    }*/

    public int getIndexerLimitPerSecond() {
        return indexerLimitPerSecond;
    }

/*    public void setIndexerLimitPerSecond(int indexerLimitPerSecond) {
        this.indexerLimitPerSecond = indexerLimitPerSecond;
    }*/

    public int getDbScanPerTime() {
        return dbScanPerTime;
    }

/*    public void setDbScanPerTime(int dbScanPerTime) {
        this.dbScanPerTime = dbScanPerTime;
    }*/

    public int getDbScanPeriod() {
        return dbScanPeriod;
    }

/*    public void setDbScanPeriod(int dbScanPeriod) {
        this.dbScanPeriod = dbScanPeriod;
    }*/

    public int getDbReconnectPeriod() {
        return dbReconnectPeriod;
    }

/*    public void setDbReconnectPeriod(int dbReconnectPeriod) {
        this.dbReconnectPeriod = dbReconnectPeriod;
    }*/

    public Integer getSiteNumber() {
        return siteNumber;
    }

/*    public void setSiteNumber(Integer siteNumber) {
        this.siteNumber = siteNumber;
    }*/

    public String getName() {
        return name;
    }

/*    public void setName(String name) {
        this.name = name;
    }*/
}
