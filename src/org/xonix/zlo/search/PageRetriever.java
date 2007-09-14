package org.xonix.zlo.search;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.xonix.zlo.search.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Author: gubarkov
 * Date: 30.05.2007
 * Time: 17:36:44
 */
public class PageRetriever {
    private static HttpClient HTTP_CLIENT = new HttpClient(new MultiThreadedHttpConnectionManager());

    public static String getPageContentByNumber(int num) throws IOException{
        GetMethod getMethod = formGetMethod("http://" + Config.INDEXING_URL + Config.READ_QUERY + num);

        List<String> stringGroups = new ArrayList<String>();
        InputStream is = null;

        try {
            HTTP_CLIENT.executeMethod(getMethod);

            // ����������� ������ �� "<BIG>��������� � ���� ������</BIG>"
            is = getMethod.getResponseBodyAsStream();
            stringGroups.add("");

            int currSize;
            do {
                byte[] buff = new byte[Config.BUFFER];
                int lenRead = is.read(buff);

                if (lenRead == -1)
                    break;

                stringGroups.add(new String(buff, 0, lenRead, Config.CHARSET_NAME));
                currSize = stringGroups.size();
            } while(
                (stringGroups.get(currSize - 2) + stringGroups.get(currSize - 1)).indexOf(Config.END_MSG_MARK) == -1
                );
        } finally {
            if (is != null)
                is.close();
            getMethod.releaseConnection(); // http://jakarta.apache.org/httpcomponents/httpclient-3.x/threading.html
        }
        StringBuffer sb = new StringBuffer(stringGroups.size());
        for (String s : stringGroups) {
            sb.append(s);
        }
        return sb.toString();
    }

    /* load page until first root-message found
    *  returns last number of root-message or -1 if not found
     */
    public static int getLastRootMessageNumber() throws IOException {
        GetMethod getMethod = formGetMethod("http://" + Config.INDEXING_URL);

        InputStream is = null;
        Matcher m = null;

        try {
            HTTP_CLIENT.executeMethod(getMethod);
            // ����������� ������ �� ����� � PageParser.INDEX_UNREG_RE"
            is = getMethod.getResponseBodyAsStream();
            List<String> stringGroups = new ArrayList<String>();
            stringGroups.add("");

            int currSize;
            do {
                byte[] buff = new byte[Config.BUFFER];
                int lenRead = is.read(buff);
                if (lenRead == -1)
                    break;
                stringGroups.add(new String(buff, 0, lenRead, Config.CHARSET_NAME));
                currSize = stringGroups.size();
                m = PageParser.INDEX_UNREG_RE.matcher(stringGroups.get(currSize - 2) + stringGroups.get(currSize - 1));
            } while(!m.find());
        } finally {
            if (is != null)
                is.close();
            getMethod.releaseConnection();
        }

        return m != null ? Integer.parseInt(m.group(1)) : -1;
    }

    private static GetMethod formGetMethod(String uri) {
        GetMethod getMethod = new GetMethod(uri);
        getMethod.addRequestHeader("Host", Config.INDEXING_URL);
        getMethod.addRequestHeader("User-Agent", Config.USER_AGENT);
        return getMethod;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getPageContentByNumber(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}