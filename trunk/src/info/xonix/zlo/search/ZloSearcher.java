package info.xonix.zlo.search;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import info.xonix.zlo.search.config.Config;
import info.xonix.zlo.search.dao.Site;
import info.xonix.zlo.search.model.ZloMessage;
import info.xonix.zlo.search.site.SiteSource;
import info.xonix.zlo.search.utils.TimeUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Author: gubarkov
 * Date: 01.06.2007
 * Time: 2:24:05
 */
public class ZloSearcher extends SiteSource {
    private static final Logger logger = Logger.getLogger(ZloSearcher.class);

    public static final int PERIOD_RECREATE_INDEXER = TimeUtils.parseToMilliSeconds(Config.getProp("searcher.period.recreate.indexer"));

    private static long lastCreateTime = -1;
    private static boolean isReopening = false;
    private static IndexReader indexReader;

    public ZloSearcher(Site site) {
        super(site);
    }

    public static IndexReader getIndexReader() {
        if (indexReader == null) {
            try {
                indexReader = IndexReader.open(Config.INDEX_DIR);
            } catch (IOException e) {
                logger.error("Error while creating index reader: " + e);
            }
        } else {
            try {
                if (needToRecreateReader()) {
                    synchronized (ZloSearcher.class) {
                        if (needToRecreateReader()) {
                            startReopeningThread();
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("IndexReader error", e);
            }
        }
        return indexReader;
    }

    private static boolean needToRecreateReader() throws IOException {
        return !indexReader.isCurrent()
                && System.currentTimeMillis() - lastCreateTime > PERIOD_RECREATE_INDEXER
                && !isReopening;
    }

    private static void startReopeningThread() {
        Thread t = new Thread(new Runnable() {

            public void run() {
                isReopening = true;

                logger.info("Start recreating indexReader in separate thread...");
                IndexReader _indexReader = null,
                        oldIndexReader;

                try {
                    _indexReader = IndexReader.open(Config.INDEX_DIR);
                } catch (IOException e) {
                    logger.error("Error while recreating index reader: " + e);
                }

                // search to form memory caches
                searchIndexReader(_indexReader, " +nick:��������", null);
                oldIndexReader = indexReader;

                indexReader = _indexReader;
                lastCreateTime = System.currentTimeMillis();

                clean(oldIndexReader);
                logger.info("Successfuly recreated.");

                isReopening = false;
            }
        });

        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    public static void clean(IndexReader ir) {
        try {
            if (ir != null)
                ir.close();
        } catch (IOException e) {
            logger.error("Error while closing index reader: " + e.getClass());
        }
    }

    public static void clean() {
        clean(indexReader);
        indexReader = null;
    }

    public static class ParseException extends RuntimeException {
        private String query;

        public ParseException(String query, Throwable cause) {
            super(cause);
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }

    public SearchResult search(int topicCode,
                               String text,
                               boolean inTitle,
                               boolean inBody,
                               boolean inReg,
                               boolean inHasUrl,
                               boolean inHasImg,
                               String nick,
                               String host,
                               Date fromDate,
                               Date toDate) {

        return search(ZloMessage.formQueryString(text, inTitle, inBody, topicCode, nick, host, fromDate, toDate, inReg, inHasUrl, inHasImg));
    }

    public SearchResult search(SearchRequest searchRequest) {
        return search(
                searchRequest.getTopicCode(),
                searchRequest.getText(),
                searchRequest.isInTitle(),
                searchRequest.isInBody(),
                searchRequest.isInReg(),
                searchRequest.isInHasUrl(),
                searchRequest.isInHasImg(),
                searchRequest.getNick(),
                searchRequest.getHost(),
                searchRequest.getFromDate(),
                searchRequest.getToDate()
        );
    }

    public SearchResult search(int topicCode,
                               String text,
                               boolean inTitle,
                               boolean inBody,
                               boolean inReg,
                               boolean inHasUrl,
                               boolean inHasImg,
                               String nick,
                               String host) {
        return search(topicCode, text, inTitle, inBody,
                inReg, inHasUrl, inHasImg, nick, host, null, null);
    }

    public static Sort getDateSort() {
        // sort causes slow first search & lot memory used!
        return Config.SEARCH_PERFORM_SORT
                ? new Sort(new SortField(ZloMessage.FIELDS.DATE, SortField.STRING, true))
                : null;
    }

    private SearchResult search(String queryStr) {
        if (!Config.USE_DOUBLE_INDEX) {
            return searchIndexReader(null, queryStr, null);
        } else {
            return searchDoubleIndex(queryStr, null);
        }
    }

    private SearchResult searchDoubleIndex(String queryStr, Sort sort) {
        if (sort == null)
            sort = getDateSort();

        SearchResult result = new SearchResult();
        try {
            Query query = setQuery(result, queryStr);
            DoubleIndexSearcher dis = getDoubleIndexSearcher();
            result.setHits(dis.search(query, sort));
        } catch (org.apache.lucene.queryParser.ParseException e) {
            throw new ParseException(queryStr, e);
        } catch (IOException e) {
            logger.error(e);
        }
        return result;
    }

    private DoubleIndexSearcher doubleIndexSearcher;

    public DoubleIndexSearcher getDoubleIndexSearcher() {
        if (doubleIndexSearcher == null) {
            doubleIndexSearcher = new DoubleIndexSearcher(getSite(), getDateSort());
        }
        return doubleIndexSearcher;
    }

    public static SearchResult searchIndexReader(IndexReader indexReader, String queryStr, Sort sort) {
        if (sort == null)
            sort = getDateSort();

        if (indexReader == null)
            indexReader = getIndexReader();

        SearchResult result = new SearchResult();
        IndexSearcher searcher = null;
        try {
            searcher = new IndexSearcher(indexReader);
            result.setSearcher(searcher);
            Query query = setQuery(result, queryStr);
            Hits hits = searcher.search(query, sort);
            result.setHits(hits);
        } catch (IOException e) {
            logger.error(e);
        } catch (org.apache.lucene.queryParser.ParseException e) {
            throw new ParseException(queryStr, e);
        } finally {
            try {
                if (searcher != null)
                    searcher.close();
            } catch (IOException e) {
                logger.warn("Error while closing searcher: " + e);
            }
        }
        return result;
    }

    private static Query setQuery(SearchResult result, String queryStr) throws org.apache.lucene.queryParser.ParseException {
        Analyzer analyzer = ZloMessage.constructAnalyzer();
        QueryParser parser = new QueryParser(ZloMessage.FIELDS.BODY, analyzer);
        Query query = parser.parse(queryStr);

        logger.info("queryStr: " + queryStr);
        logger.info("query   : " + query);

        result.setAnalyzer(analyzer);
        result.setQueryParser(parser);
        result.setQuery(query);
        return query;
    }

    public ZloMessage searchMsgByNum(int urlNum) {
        try {
            return search("+num:" + ZloMessage.URL_NUM_FORMAT.format(urlNum)).iterator().next().getMessage(); // returns 1 result
        } catch (NoSuchElementException e) { // 0 results found => msg with such num not indexed
            return null;
        }
    }

    public SearchResult searchInNumRange(int urlFrom, int urlTo) {
        return search(MessageFormat.format(" +num:[{0} TO {1}]", urlFrom, urlTo));
    }

    public int getLastIndexedNumber() {
        String searchStr = MessageFormat.format(" +num:[{0} TO {1}]",
                ZloMessage.URL_NUM_FORMAT.format(0),
                ZloMessage.URL_NUM_FORMAT.format(999999999));
        try {
            DoubleHits hits = ZloSearcher.searchIndexReader(
                    Config.USE_DOUBLE_INDEX ? getDoubleIndexSearcher().getSmallReader() : null,
                    searchStr,
                    new Sort(new SortField(ZloMessage.FIELDS.URL_NUM, SortField.STRING, true))).getHits();
            return hits.length() > 0
                    ? Integer.parseInt(hits.doc(0).get(ZloMessage.FIELDS.URL_NUM))
                    : -1;
        } catch (IOException e) {
            logger.error(e);
            return -1;
        }
    }
}