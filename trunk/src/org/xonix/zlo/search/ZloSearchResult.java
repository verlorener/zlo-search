package org.xonix.zlo.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.xonix.zlo.search.model.ZloMessageAccessor;
import org.xonix.zlo.search.model.ZloMessageLazy;
import org.xonix.zlo.web.ZloPaginatedList;
import org.displaytag.pagination.PaginatedList;

import java.util.Date;
import java.util.Iterator;

/**
 * Author: gubarkov
 * Date: 31.08.2007
 * Time: 16:59:08
 */
public class ZloSearchResult implements Iterable {

    private Hits hits;
    private IndexSearcher searcher;
    private Analyzer analyzer;
    private QueryParser parser;
    private Query query;

    // search is performed for these criteria:
    private String topicCode;
    private String text;
    private boolean inTitle;
    private boolean inBody;
    private String nick;
    private String host;
    private Date fromDate;
    private Date toDate;

    private PaginatedList paginatedList;

    private boolean newSearch = true; // by default after created

    public ZloSearchResult() {
    }

    public Hits getHits() {
        return hits;
    }

    public void setHits(Hits hits) {
        this.hits = hits;
    }

    public void setSearcher(IndexSearcher searcher) {
        this.searcher = searcher;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void setQueryParser(QueryParser parser) {
        this.parser = parser;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public IndexSearcher getSearcher() {
        return searcher;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public QueryParser getParser() {
        return parser;
    }

    public Query getQuery() {
        return query;
    }

    private class MsgsIterator implements Iterator<ZloMessageAccessor> {
        Iterator hitsIterator = hits.iterator();

        public boolean hasNext() {
            return hitsIterator.hasNext();
        }

        public ZloMessageAccessor next() {
//            return ZloMessage.fromHit((Hit) hitsIterator.next());
            return new ZloMessageLazy((Hit) hitsIterator.next());
        }

        public void remove() {
        }
    }

    public Iterator<ZloMessageAccessor> iterator() {
        return new MsgsIterator();
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isInTitle() {
        return inTitle;
    }

    public void setInTitle(boolean inTitle) {
        this.inTitle = inTitle;
    }

    public boolean isInBody() {
        return inBody;
    }

    public void setInBody(boolean inBody) {
        this.inBody = inBody;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public boolean isTheSameSearch(SearchRequest searchRequest) {
        return searchRequest.isTheSameSearch(topicCode, text, inTitle, inBody, nick, host, fromDate, toDate);
    }

    public boolean isNotTheSameSearch(SearchRequest searchRequest) {
        return !isTheSameSearch(searchRequest);
    }

    public PaginatedList getPaginatedList() {
        if (paginatedList == null)
            paginatedList = ZloPaginatedList.fromZloSearchResult(this);
        return paginatedList;
    }

    public boolean isNewSearch() {
        return newSearch;
    }

    public void setNewSearch(boolean newSearch) {
        this.newSearch = newSearch;
    }
}