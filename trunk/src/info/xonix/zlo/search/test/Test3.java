package info.xonix.zlo.search.test;

import info.xonix.zlo.search.site.PageParser;
import info.xonix.zlo.search.dao.Site;
import info.xonix.zlo.search.model.ZloMessage;

/**
 * Author: Vovan
 * Date: 22.03.2008
 * Time: 22:05:23
 */
public class Test3 {
    public static void main(String[] args) {
        String msg;
        msg = "</CENTER>\n" +
                "<BR>\n" +
                "\n" +
                "<DIV align = center>\n" +
                "<BIG>[��� ����] STELS ��� ����. ���������� ������ ��������.</BIG><BR>\n" +
                "��������� ���� �������: <B>bull</B> <SMALL>(unreg)</SMALL> <SMALL>(ppp85-140-32-253.pppoe.mtu-net.ru)</SMALL><BR>\n" +
                "����: 22/03/2008  2:00</DIV>\n" +
                "\n" +
                "<BR>\n" +
                "<BR>\n" +
                "<CENTER><BIG>��������� � ���� ������</BIG></CENTER>";

        /*msg = "</TABLE>\n" +
                "</CENTER>\n" +
                "<BR>\n" +
                "\n" +
                "<DIV align = center>\n" +
                "<BIG>[��� ����] � ���������� ������� � ��������������� ����� ��� ����������.</BIG><BR>\n" +
                "��������� ���� �������: <B>subebitor</B> <SMALL>(unreg)</SMALL> <SMALL>(172.16.1.51)</SMALL><BR>\n" +
                "����: 08/01/2008  18:07</DIV>\n" +
                "\n" +
                "<BR>\n" +
                "\n" +
                "<DIV class = body>\n" +
                "� � ��� ������? � ���, ��� �� ������ ��������� ��� ������ ������ ������, ������� � ������������? (������������������, ����������, ��� ������ ���� �������).\n" +
                "</DIV>\n" +
                "\n" +
                "<BR>\n" +
                "<CENTER><BIG>��������� � ���� ������</BIG></CENTER>";*/
        msg = "/CENTER>\n" +
                "<BR>\n" +
                "\n" +
                "<DIV align = center>\n" +
                "<BIG>[��� ����] welcome ;)</BIG><BR>\n" +
                "��������� ���� �������: <A class = profile href = \"index.cgi?uinfo=Mnemonic\" target = _blank><B>Mnemonic</B></A> <SMALL>(mnemonic.rt.mipt.ru)</SMALL><BR>\n" +
                "����: 04/06/2003  11:13</DIV>\n" +
                "<BR>\n" +
                "<BR>\n" +
                "<CENTER><BIG>��������� � ���� ������</BIG></CENTER>\n" +
                "\n" +
                "<DIV class = ots>";

        Site site = Site.forName("velo");
        site.setDB_VIA_CONTAINER(false);
        PageParser p = new PageParser(site);
        ZloMessage m = p.parseMessage(msg, 123);
        System.out.println(m);
    }
}
