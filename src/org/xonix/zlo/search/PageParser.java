package org.xonix.zlo.search;

import org.xonix.zlo.search.model.ZloMessage;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Author: gubarkov
 * Date: 30.05.2007
 * Time: 20:17:07
 */
public class PageParser {
    private static Pattern MSG_UNREG_RE = Pattern.compile(
        "<DIV ALIGN=CENTER><BIG>(.*?)</BIG>&nbsp;&nbsp;<BIG>(.*?)</BIG>" +
        "<BR>��������� ���� �������:\\s*<b>(.*?)</b><SMALL>\\s*\\(unreg\\)</SMALL>\\s*<small>" +
        "\\((.*?)\\)</small><BR>����:\\s*(.*?)</DIV><BR><br\\s*/><div class=\"body\">(.*?)</div>"
    );

    private static Pattern MSG_REG_RE = Pattern.compile(
        "<DIV ALIGN=CENTER><BIG>(.*?)</BIG>&nbsp;&nbsp;<BIG>(.*?)</BIG>" +
        "<BR>��������� ���� �������:\\s*<a href=\"\\?uinfo=.*?\" class=\"nn\" onclick=\"popup\\('uinfo', '.*?', 700, 600\\); return false;\" title=\"���������� � ������������\" target=\"_blank\">(.*?)</a>\\s*" +
        "<small>\\((.*?)\\)</small><BR>����:\\s*(.*?)</DIV><BR><br\\s*/><div class=\"body\">(.*?)</div>"      
    );


    public static ZloMessage parseMessage(String msg) {
        ZloMessage zloMessage = new ZloMessage();

        Matcher m = MSG_UNREG_RE.matcher(msg);
        if (m.find()) {
            zloMessage.setReg(false);
        } else {
            m = MSG_REG_RE.matcher(msg);
            if (!m.find())
                return null;
            zloMessage.setReg(true);
        }

        zloMessage.setTopic(prepareTopic(m.group(1)));
        zloMessage.setTitle(m.group(2));
        zloMessage.setNick(m.group(3));
        zloMessage.setHost(m.group(4));
        zloMessage.setDate(prepareDate(m.group(5)));
        zloMessage.setBody(prepareBody(m.group(6)));

        return zloMessage;
    }

    public static ZloMessage parseMessage(String msg, int urlNum) {
        ZloMessage zm = parseMessage(msg);
        if (zm == null)
            return null;

        zm.setNum(urlNum);
        return zm;
    }

    private static String prepareTopic(String topic) {
        topic = topic.substring(1, topic.length()-1);
        return "��� ����".equals(topic) ? "" : topic;
    }

    private static String prepareBody(String body) {
        return body.replaceAll("<P>", "\n")
                .replaceAll("<br>", "\n")
                .replaceAll("<BR>", "\n")
                .replaceAll("<IMG BORDER=0 SRC=\".*?\" ALT=\"(.*?)\">", "$1") // ��������
                .trim();
    }

    private static Date prepareDate(String s) {
        final String[] RUS_MONTHS = {"������", "�������", "����", "������", "���", "����", "����",
                                    "������", "��������", "�������", "������", "�������"};
        DateFormat df = new SimpleDateFormat("M d hh:mm:ss yyyy");
        s = s.split(",")[1].trim();
        for (int i=0; i<RUS_MONTHS.length; i++) {
            s = s.replaceFirst(RUS_MONTHS[i], Integer.toString(i+1));
        }
        Date d = null;
        try {
            d = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static void main(String[] args) throws IOException {
//        String s = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
//                "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\" /><link rel=\"shortcut icon\" href=\"/favicon.ico\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"/main.css\" /><meta http-equiv=\"Page-Exit\" content=\"progid:DXImageTransform.Microsoft.Fade(Duration=0.2)\" /><title>�����-����-���� : ����� ���������� �� ����� ������ �����</title></head><body>\n" +
//                "<script language=\"JavaScript\" type=\"text/javascript\">function popup(action, value, w, h){wnd=window.open(\"?\"+action+\"=\"+value,\"popup\",\"resizable=no,menubars=no,scrollbars=yes,width=\"+w+\",height=\"+h); }</script><div class=\"menu\"><A HREF=\"#1\">������� � �������</A><A HREF=\"#Reply\">��������</A><A HREF=\"?index#1\" style=\"color:red;\">�� ������� ��������</A><a HREF=\"http://boards.alexzam.ru\">�����</A><A HREF=\"?register=form\">�����������</A><A HREF=\"?login=form\">����</A><A HREF=\"?rules\">�������</A></div><BR><DIV ALIGN=CENTER><BIG>[��� ����]</BIG>&nbsp;&nbsp;<BIG>����� ���������� �� ����� ������ �����</BIG><BR>��������� ���� �������: <b>Bbsadmin</b><SMALL> (unreg)</SMALL> <small>(ignition.3ka.mipt.ru)</small><BR>����: �������, ������ 7 14:16:27 2001</DIV><BR><br /><div class=\"body\"><P>���� ��������� ����� �� ����� � ������, ������� ��, ��� ����� �������� �� ���������� �����..<P>IE: C:\\WINDOWS\\Cookies NN:<P>C:\\Program Files\\Netscape\\Users\\{user}\\cookies.txt</div><P></P><BR><CENTER><BIG>��������� � ���� ������</BIG></CENTER><DIV class=w><span id=m1><A NAME";
        //System.out.println(parseMessage(s));

        for (int i=3764000; i<3764113; i++) {
            System.out.println(parseMessage(PageRetriever.getPageContentByNumber(i), i));
        }
        //System.out.println(prepareDate("�������, ��� 31 21:52:27 2007"));
    }
}