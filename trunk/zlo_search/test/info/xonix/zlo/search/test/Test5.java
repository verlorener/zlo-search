package info.xonix.zlo.search.test;

import info.xonix.zlo.search.config.Config;
import info.xonix.zlo.search.domainobj.Site;
import info.xonix.zlo.search.doubleindex.DoubleIndexSearcher;
import info.xonix.zlo.search.spring.AppSpringContext;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Sort;

import java.io.IOException;
import java.io.StringReader;

/**
 * Author: Vovan
 * Date: 04.05.2008
 * Time: 0:24:44
 */
public class Test5 {
    private static final Config config = AppSpringContext.get(Config.class);

    public static void main(String[] args) {
        m4();
    }

/*    public static void m3() {
        // test index
        Site zlo = Site.forName("zlo");
        IndexerLogicImpl zi = new IndexerLogicImpl(zlo);

        zi.setAnalyzer(new RussianWithNumbersAndSpecialStopWordsAnalyzer(new String[0])); // nos stopWords=empty index with all words
        zi.setIndexPerTime(10000);
        zi.setIndexDir(new File("D:\\TEST\\JAVA\\ZloSearcher\\indexes\\__test\\no_stop_words"));

        try {
            zi.indexRange(0, zlo.getDbManager().getLastMessageNumber());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }*/

    public static void m2() {
        String s = "� ��� ����� �����?";

        Analyzer analyzer = config.getMessageAnalyzer();
//        Analyzer analyzer = new RussianWithNumbersAndSpecialStopWordsAnalyzer(new String[0]);

        showTokens(s, analyzer);

    }

    private static void showTokens(String s, Analyzer analyzer) {
        Token t;
        TokenStream ts = analyzer.tokenStream("body", new StringReader(s));
        try {
            while ((t = ts.next()) != null) {
                System.out.println(t.termText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void m1() {
        Analyzer analyzer = config.getMessageAnalyzer();
        QueryParser qp = new QueryParser("body", analyzer);
        try {
            System.out.println(qp.parse("nick:\"\\\\/\\\\/0\\\\/\\\\/KA\""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void m4() {
//        ZloSearcher zs = new ZloSearcher(Site.forName("zlo"));
//        System.out.println(zs.search(-1, "����", true, true, false, false, false, null, null).getHits().length());
        Site site = Site.forName("zlo");
        DoubleIndexSearcher dis = new DoubleIndexSearcher(site, null);
        IndexSearcher is = new IndexSearcher(dis.getBigReader());
        try {
            Hits hits = is.search(new QueryParser("body", config.getMessageAnalyzer()).parse("body:���� title:����"), Sort.INDEXORDER);

            for (int i = 1; i < 10; i++) {
                System.out.println(hits.doc(hits.length() - i).get("num"));
            }

            System.out.println(hits.length());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
