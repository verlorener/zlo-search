import junit.framework.TestCase;
import org.junit.Test;
import org.xonix.zlo.search.utils.TimeUtils;
import org.xonix.zlo.search.utils.HtmlUtils;
import org.xonix.zlo.search.model.ZloMessage;
import org.xonix.zlo.search.DBManager;
import org.xonix.zlo.search.DBException;

/**
 * Author: gubarkov
 * Date: 03.10.2007
 * Time: 22:56:24
 */
public class Tests1 extends TestCase {
    @Test
    public void testTimeUtils() {
        assertEquals(125000, TimeUtils.parseToMilliSeconds("2m5s"));
        assertEquals(120000, TimeUtils.parseToMilliSeconds("2m"));
        assertEquals(5000, TimeUtils.parseToMilliSeconds("5s"));
    }

    @Test
    public void testHtmlUtils() {
        // local:
        assertEquals(false, HtmlUtils.hasImg("<IMG BORDER=0 SRC=\"pic/bigsmile.gif\" ALT=\":))\">"));
        assertEquals(false, HtmlUtils.hasImg("<IMG SRC=\"pic/bigsmile.gif\">"));
        // http:
        assertEquals(true, HtmlUtils.hasImg("<IMG BORDER=0 SRC=\"http://www.ru/pic/bigsmile.gif\" ALT=\":))\">"));
        assertEquals(true, HtmlUtils.hasImg("<IMG BORDER=0 SRC=\'http://www.ru/pic/bigsmile.gif\' ALT=\":))\">"));
        assertEquals(true, HtmlUtils.hasImg("<IMG BORDER=0 SRC=http://www.ru/pic/bigsmile.gif ALT=\":))\">"));
        assertEquals(true, HtmlUtils.hasImg("<IMG BORDER=0 SRC=http://www.ru/pic/bigsmile.gif>"));
        assertEquals(true, HtmlUtils.hasImg("<IMG SRC=http://www.ru/pic/bigsmile.gif>"));

        String s = "<img src=\"http://rs.foto.radikal.ru/0709/b6/d54aa729b48e.jpg\"><br><br>��������� ����� ������ �� ������������ �����.<br><br>�� ��������<br><br><blockquote class=\"quote\"><span class=\"inquote\">[q]</span><b>������:</b><br>� ��� �� ����������� ������������ �������?<br><br>������ ������ (26���), ������������ ������ ���������� ������-����������� ����� ����:<br><br>���� ��������� (21 ���) � ��������, ��� ����.<br><br>������ ������ (36���) � �������, ��������� ���, ������ ����������� ��� � �����.<br><br>�������� ���� (21���) � ������ ����� 2-������, ������������.<br><br>�������� ������ (19���) � ������ ����� 1-������.<br><br>������ ������� (19���) � ������ ������ �� ����� � ����������� ���, ������� �������, ������ ����� 2-������.<br><br>���������� ����������, ������� ����, ������� �������� �������������, ��������� ������ ������ �����, ������� ������ ������ ��������, �������� ������� ������.<br><br>� � ����� ����� �� �������������? ����� ������ ��� ��������� � �������?<br><br>� ������������ � ��������������, ����������� �����. ���� � ��������� ������, ���� � ������� �������, ������������, ����� � ������� �������, ������������ � ������, ������ �������, � ������� ������� ������ � ���� � ����.<br><br>� ��� ������ ������� ����: ������������, ��, ��������?<br><br>������ ������� ���� ��������������.<br><br>� ��� ��� ������ � ���� �����?<br><br>� ���� ������ ��������������, ���� � ����� � ��������� ������������ �������.<br><br>� ��� ��� ��� ���� ���������: ��������� ��������� ��� ���� �����?<br><br>���� ������ RaHoWa ���� ����� ����� ��� ������� � �������� �� ���. � � 1996 ���� ������ ������, ��� � ���������� ������ ��������, ������������, ���� � 2001 ���� ����� ��������� �� ���� � �������� � �� ������ ����.<br><br>� ��� ������ ����� ���� ��� ������, �������� � ������?<br><br>������ (������), ��������� � �������������� ������ ��� �������, ������ � ���������� ��� ��� �������, ����� �� ������ ���������, �� ������ ���� ����, ����� ���� �� ���� ������� � �������� �� ������� ������. ����� ����� �������� � ���� (���������)� �� ����� ������ ��������, ����� ��� ���������� ������������ ��������� � ����� � ������ � �������, ���� ��� ���� �� ���� � ������ ������� ����������. �����-������ (�������) ����� � �������, ��������� ������� ������, ����� ��� ������� � 15 ������� � ���������, �� ����� �����, ����� ���� ��� �������� � ������ ������ � ������ ��� ��������� ����� � ����������.<br><br>� � ����� ����� ��� ���������� �����?<br><br>�������� � ����� ������������, ����������� � �����������. ������ ���������� ����������� ��� ����� ��-�� ���������������� �������� ���� ������ ���������� � ���! ��� �� ���� ��������, ��� ����� ���� ����� ������������� ������� ������������� ��������� � �������� ������� � ������, �������� �� ���������� ��� � �.�.<br><br>������ �� ��������� ������������ �����������. 16 �������� ������� ����� ���������. � ����� � ����� ������ ��������� � �� �� ������, � ������ ���� �� ��������� �� ������ (����������), ������ ���������� � ������ ���. �����������, ��������� ����������� ����� ��������� � ��������. ������� �� ����� ���������� ������.<br><br>� �����, �� � �����, ��� �������� ����� 15 ������� � �� ������� ������������ �����, ����� ������ ����� �������� �������, ������ � ������, ������, ����� �������, �������� ������ �� �. ���������� ������ ��������. ��� �� ��������� �������� ����������� �� ������ �� ���������� �������� � ����� ���-����� �������, ����� ���������� ���� � ��������, ������ ����������� ����� � 2004-2005 �����, ������� �������� � ����������� � �� ������ ������, �������� ��������� � ������ ����������. ��� ����� ����� ����� ���� ������� �� �������� �� ������� ������ � �� ����� ���� ������ � ���, �� ���������� ������ � ����������� ���������, ���������� ������ � ������ �� ����� ������� �� ������ �������� ������� �������.<br><br>�� ���� �������� ������� �����, � � ��������� ����� ������� ����� ����� �������� � �������.<br><br>�� �������! ����� ����! ����� ������! ���� �����!<br><br>������ ������, 55-��.<br><br><br>������� �������� ���������� �������<br>���� ������������� ������� ������ �������<br><br><br><br><br><br>����� ���� ������ �������<br><br>� 1997 ���� ����� � ������� �������, ������ ��������� tatu � ����������� ���� �� ������ �����. �� ��� ������ ���� ������� ����� ������.<br><br>� 1998 ���� � ������ ����������� ��������� �� ����� �������.<br><br>� 1998 ��������� � �������� ������� Streethools.<br><br>� 1999 ���� ������� ������� ������ �� �����������.<br><br>� 1999 ���� � ������� ������������� ������� 88� ��������� � ��������� �� ����������� � �.�, ������� �������. ����� �� ������ � �����, �������, ����, ��������.<br><br>� 2000 ���� ��������� � ���������� ������������ ��������38 ��������� �� ���������. �������� ������ ������������ � �������� ������ � 222 ��. ������ �� 3 ����. ����� �� �������� �� �������� ����.<br><br>� 2001 ���� ��� ��������������� �������� ������� ������ ����� ������-���������� ���������� � ����. ��� ������ ����������� � �������� ��������� � ������������� ��������� � �������, ��������, �� �������� �������.<br><br>������� � ������ ������ ������� ������� ��������� ������������ �����:<br><br>2003 ���, ���� � ����� ���������� �������.<br><br>2003 ���, ������ � ����� ��������� ������� �������.<br><br>2004 ��� � ����� ������������ ���������.<br><br>2005 ��� � ����� ������������ ��������� ������� ����.<br><br>2006 ��� � ����� ������, ������ ���������� �������, ���������� ��������� � ������ �� ���������������� �����, ����� ������� ���������, ����� ������ ������ �����, ����� ���-����� ��������.<br><br>21 ������� 2006 ���� � ����� �� ������������ �����.<br><br>����� ������ ������ ������� ����� ������������:<br><br>� 2007 ���� ����� ��� ������������ ����� � ��������, ������� �������� ����� ��������� � � 2006 ����.<span class=\"inquote\">[/q]</span></blockquote><br><br><blockquote class=\"quote\"><span class=\"inquote\">[q]</span><b>������:</b><br> ����� �������� �������������<br>���� ������ ������� ��������<br><br>����� �������� ����� ����� ������� �� ������ � ������������ � ������ ���� ������������ ����� ������������������ ������� � �������������� ������������� ������� ���������� � ������� ����������. � ����� ������� ����������� ��� ���������� ����������� (����������, ���������, ���������, ���������������) ������ ������� �������� � ������������ ���, ��� �� �������� ������������� ������� ������� �� �������� �� ������ �������� � �������� ��������� �������. ��� ������ ��� ����� �� �������� ������ ������ ������� ���������. ����� ������� �������, ��������� �������������� ����������� ����������� ������� ��������������� ��������, ������������� �������� ��� �������� ����� ������. ��� ����� �� ����������� �� ������� ����, �� ������� �����������. ���������� �� ������� �������� �������� ���� ��� �� ����������, �.�. ���������� ��������� ���� ������������ ���������������.<br><br>����������� ������ �� ������ � ������������ ��������� �������� �� ������ ������ � ���������������� �������� �������� ����������������� ������������ ����. ����� ������������� � ������ �� ����������.<br><br>�������� ����� ����������� ������������� ����� ���������, ��������� � ��������� ���������� ���� �� ����������������� � �� ������ ��������� ������������� ������. ������ ��� ���� ����, ������, ������ ��������.<br><br>� ������ ������� ������� ������� �������� ���������������� �������� �����, ��������� ������� ������������ ������� � ������� ���� ��� ����� ������ (� ������ ������ �� ������� ������ �� �����������, ���������� ������� �������� �����������, ��������), � ��� ��� ����������.<br><br>��������� ��� � ��� ��������� ������ ������ ������ � ����������� ������ ���������� ���� ���, ������������ ����������, � ����� �� ������ ������� ��� �������� �������� ������� ��, �� ���� ���������� �������� ����������� ���� ���������������� �����������. ������ ��� �������� ����������� ��������� � ������� �������, ������ ������ ��������� �������������� ��� � ��� ���� �����. ������ ������������ ���, ��� ������� ������ �� �����, ���� ���� �� ������ � ��� �������� ����������� � ������������ � ������� �� ������ � ������������.<br><br>� ������� � 2002 ���� ����� ���������� ���������� � ���, ��� �� ������� ������������������ ����������� ��� ������� ���������� �������� ������� ����� ���������� ����������, ����������� ������������. ��� ������ �������� � ��� ����������� ������� �������� �����, ����������������, �������� ���������� ������� �����. ������� � ��� ���� ������������ ��� ��� ��� (����� �� ������������ �� ��� ����� �������).<br><br>�� ������ �� ������� ���������� �������� �������� �� ������ ������������� ������, � ���������� ������� ������ ���������� ������ �������� �� ���. �� ��� ������ ���� �� �����, ��� ��������� �� ���������� ��������, ���� ������ ��������. ��, � ���������, ��-�� ����������� ��� �������� ���������� ������������� �������������� ��������� (��������) ������ �� ������. ��� �������� ����� ������ ������ ��������� ���-��� ����������� ���������� ������, � ����������� � � ������� ����� ������. �� ������������� ���� ��������, ��� ��� �������� ��� �� ���������� �������, ��� � � ������� �������� ������ �� �������� ������������ ����������.<br><br>����� 2002 ���� ������� ������� ������ � ������� ����� (������ ������-����������� �����) ����� ��� �������������� ������ �� �.����. ������ ���� �������� ����������� ���������� �����������. ������� ���� ��� ���� ������������� �������� ��������������� ����������� � ���������� ����. � ������ ���������� ���� 10-16 ���, ��������� ������������� � ������ � ��������������� �����. ������ ������������ �� ������ ���� �� ������ ����� (��� ������). ����� ������ �������� �� �����. ���� ��������, ��� �������� ����� ������� ���������� ������ ���� �� ����, � �� ������������ � ������� �������. ����� ������, ��� ��������� ��� ����� ����� ������ � ������� ���������� �������� �������, ��� ���� ���� ������� ������� ���� � ������ �� ����� ������ �� ������� � ����� �������������. ������ ����� ������: ������� ����, ��������� ����� � ��� ��� ������ ���� ��������� ����������. � ������� ����������� �������, �������� ������, �� ��� ������ ������, ��������� ���� �� ������, ���������� ����� ����� ��������� �� 12-������� ������� ����������� �����. ������ ��������� ������� ��������� ������������ ��������, ��� ����� ���� ����, � ����� ������������ ���������� � ������� ������ �������, ��������� �������� ����� ������ ������ ���, � �.� . �� �� ���� ����� ������������ (����� ���� ��� ���� �������, ���������� �� �������� ������), ������ ���������� ������� � ����������. ������ ���� ������������ ���� (4 ����� ����� � ������), �� ���� �� �����, ��� ���������. ������ ����������� ������ ������������ ������� ���������� ���� ������, ������� ������� �� �� �������, �� ��� �� ������ ���� � ������ � ������, ��� � ������������ ���� ���� ���, � ������, ������ �� ������� ������� �������� ���� � �������. ������� ������, ������� �������� ������ ��� ������������ ������ ���� � ������ �����, �����������, �����, ��� �� ��� � ����������, �� ���� ����� �����, ����� ������� ������� ������� ������� ����� ����������� ���� ������ � ����� ���� � ��� ����, ��� ����� ��������� �� ������ ���������� ������ ���. ��� ��������� ��� � �����-�� ������� ���. ����������� ������ ��� ���������, ��� �� ����� �� ������ ������� �����. ��� ������� ����� ���������� � �� ���� �� ���� ������, � ��� �����-�� ������ ������, 4 ������� � � ������ ���������, ������� ������ � ����������. ���� ���������� ������������� �� ����� ��� ������, �� ������� ��� ����� ��� ���������� ������, ������� ������� ��� ���� �� ����� � ������ ������������, � �������� �� ���� ��������� ������� �� ���� �� ��������, �� ���� � ������������ ������� ������ �� ����������, � ���� �� ����� ������� � ������� ������� ��� ������.<br><br>� ����� ���� �������� �������, ��� ������� ������ ����, ��� ������ ������ ����� ����� ��������� ������� �������. ��� ������������ � ��������� �������, ����������, ��� �� ������� ���� ��������. ����� 20 ����� �� ������ ������ ���������� ���������� ���������� ������ � ����� ���������. ������ ��������� ����������� ������� ������ ���������� �����������, �� ��������� ������� �� �������� ������� �����. ��� ������������ ������� �������. � ����� � ���, ��� ����� ������ �� �������������� ������ ���������� � �� ��������, � �� ��� �� ������� ��� ������������ ������ ��������� � �� ������ � ��������� ���� ��������� ������ ����������� �������, ��� ������ ���� ������� �� ������� ������ ������. ����, ����������� � ���������� (4 ������� �������), ���� �� ������� ���������� ��������. � ������ � ����������� � ���� � �������� �� ������ � �� �����. ������� ������� ������� ��� ���������� ��������� ������� �� ����������� ��������.<br><br>���� ������ ��� ������� �� 4 ������ 2002 ����, ����� � ������ � ��������� ����������� ����������� ������ ����� ������ � ������� ���. �� ������ � ���� ��� ��� ������ ���� ��������� ���������. ������ �������� ���������� ��� �� �������� ��� � ��������, � ���� �� ��������� �������� �����:<br><br>1. ���������� � ��������� ������������ ������ �� ������, ����� ���������� ������������ �� ������ � ������� �� ���� ������� ����������.<br><br>2. ����������, � ������ ��������� ��� �� ����� ��������� ��������� ������, �� ������ ���������������.<br><br>3. ���������� ������� ����� �������� ���� ��������������� � ������������ � �������� ������������� ������� �������� ���.<br><br>4. � ������ ���������� ���������� ������ ������� ���, ���� �������� ���� � ����������� �������� ����������� � ���������� ��������, � �������� �������� ����� ������������ ��������� ����� ����������, ��������� ������ �� �����������<br><br>��� ��� ����� � 2002 ���� �������� �������� ������������� ������� �������� ��� ��������� �� �������������� �������� ������������� �������� ���� ����������.<br><br><a href=\"http://www.nordrus.org/news/detail.php?ID=1677\" style=\"text-decoration: underline;\" target=\"_blank\">http://www.nordrus.org/news/detail.php?ID=1677</a><span class=\"inquote\">[/q]</span></blockquote><br><br>PS �������� � ������ ������������� ���� ���� �� ���� ��������� ��-�� ����, ��� ������� ��� ����������� ������ ������, ����� ��������.<br><br>����� ������!";
        assertEquals(false, HtmlUtils.hasUrl("234"));
        assertEquals(true, HtmlUtils.hasImg(s));
        assertEquals(true, HtmlUtils.hasUrl(s));        
    }

    @Test
    public void testGetMessages() {
        try {
            for (ZloMessage m : DBManager.getMessages(new int[] {1,2,3,100,2000,1000050}, -1)) {
                System.out.println(m);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
