package org.xonix.zlo.search.utils;

import java.util.regex.Pattern;

/**
 * Author: Vovan
 * Date: 22.09.2007
 * Time: 22:31:40
 */
public class HtmlUtils {
    private static final String [] BOTH_TAGS = {"a", "b", "i", "p", "blockquote", "span", "pre"};
    private static final String [] SINGLE_TAGS = {"br", "hr", "img"};
    private final static String SPACE = " ";
    private final static String NEW_LINE = "\n";

    private static final Pattern IMG = Pattern.compile("<img\\s+.*?src=(\"?|\'?)http://.*(\\1).+?>", Pattern.CASE_INSENSITIVE);
    private static final Pattern URL = Pattern.compile("<a\\s.+?>", Pattern.CASE_INSENSITIVE);

    public static String cleanHtml(String s) {
        final String POSIBLE_SPACE_AND_ATTRIBS = "(\\s*?|\\s+.*?)";
        for (String tag : BOTH_TAGS) {
            s = s.replaceAll("(?i)<" + tag + POSIBLE_SPACE_AND_ATTRIBS + ">", SPACE)
                    .replaceAll("(?i)</" + tag + ">", SPACE);
        }

        for (String tag : SINGLE_TAGS) {
            s = s.replaceAll("(?i)<" + tag + POSIBLE_SPACE_AND_ATTRIBS + ">", NEW_LINE)
                    .replaceAll("(?i)<" + tag + POSIBLE_SPACE_AND_ATTRIBS + "/>", NEW_LINE);
        }

        return s;
    }

    public static boolean hasUrl(String s) {
        return URL.matcher(s).find();
    }

    public static boolean hasImg(String s) {
        return IMG.matcher(s).find();
    }

    public static String cleanBoardSpecific(String s) {
        s = cleanHtml(s);
        s = s.replaceAll("\\[q\\]", " ").replaceAll("\\[/q\\]", " ");
        return s;
    }

    public static void main(String[] args) {
//        String s = "<img src=\"http://rs.foto.radikal.ru/0709/b6/d54aa729b48e.jpg\"><br><br>��������� ����� ������ �� ������������ �����.<br><br>�� ��������<br><br><blockquote class=\"quote\"><span class=\"inquote\">[q]</span><b>������:</b><br>� ��� �� ����������� ������������ �������?<br><br>������ ������ (26���), ������������ ������ ���������� ������-����������� ����� ����:<br><br>���� ��������� (21 ���) � ��������, ��� ����.<br><br>������ ������ (36���) � �������, ��������� ���, ������ ����������� ��� � �����.<br><br>�������� ���� (21���) � ������ ����� 2-������, ������������.<br><br>�������� ������ (19���) � ������ ����� 1-������.<br><br>������ ������� (19���) � ������ ������ �� ����� � ����������� ���, ������� �������, ������ ����� 2-������.<br><br>���������� ����������, ������� ����, ������� �������� �������������, ��������� ������ ������ �����, ������� ������ ������ ��������, �������� ������� ������.<br><br>� � ����� ����� �� �������������? ����� ������ ��� ��������� � �������?<br><br>� ������������ � ��������������, ����������� �����. ���� � ��������� ������, ���� � ������� �������, ������������, ����� � ������� �������, ������������ � ������, ������ �������, � ������� ������� ������ � ���� � ����.<br><br>� ��� ������ ������� ����: ������������, ��, ��������?<br><br>������ ������� ���� ��������������.<br><br>� ��� ��� ������ � ���� �����?<br><br>� ���� ������ ��������������, ���� � ����� � ��������� ������������ �������.<br><br>� ��� ��� ��� ���� ���������: ��������� ��������� ��� ���� �����?<br><br>���� ������ RaHoWa ���� ����� ����� ��� ������� � �������� �� ���. � � 1996 ���� ������ ������, ��� � ���������� ������ ��������, ������������, ���� � 2001 ���� ����� ��������� �� ���� � �������� � �� ������ ����.<br><br>� ��� ������ ����� ���� ��� ������, �������� � ������?<br><br>������ (������), ��������� � �������������� ������ ��� �������, ������ � ���������� ��� ��� �������, ����� �� ������ ���������, �� ������ ���� ����, ����� ���� �� ���� ������� � �������� �� ������� ������. ����� ����� �������� � ���� (���������)� �� ����� ������ ��������, ����� ��� ���������� ������������ ��������� � ����� � ������ � �������, ���� ��� ���� �� ���� � ������ ������� ����������. �����-������ (�������) ����� � �������, ��������� ������� ������, ����� ��� ������� � 15 ������� � ���������, �� ����� �����, ����� ���� ��� �������� � ������ ������ � ������ ��� ��������� ����� � ����������.<br><br>� � ����� ����� ��� ���������� �����?<br><br>�������� � ����� ������������, ����������� � �����������. ������ ���������� ����������� ��� ����� ��-�� ���������������� �������� ���� ������ ���������� � ���! ��� �� ���� ��������, ��� ����� ���� ����� ������������� ������� ������������� ��������� � �������� ������� � ������, �������� �� ���������� ��� � �.�.<br><br>������ �� ��������� ������������ �����������. 16 �������� ������� ����� ���������. � ����� � ����� ������ ��������� � �� �� ������, � ������ ���� �� ��������� �� ������ (����������), ������ ���������� � ������ ���. �����������, ��������� ����������� ����� ��������� � ��������. ������� �� ����� ���������� ������.<br><br>� �����, �� � �����, ��� �������� ����� 15 ������� � �� ������� ������������ �����, ����� ������ ����� �������� �������, ������ � ������, ������, ����� �������, �������� ������ �� �. ���������� ������ ��������. ��� �� ��������� �������� ����������� �� ������ �� ���������� �������� � ����� ���-����� �������, ����� ���������� ���� � ��������, ������ ����������� ����� � 2004-2005 �����, ������� �������� � ����������� � �� ������ ������, �������� ��������� � ������ ����������. ��� ����� ����� ����� ���� ������� �� �������� �� ������� ������ � �� ����� ���� ������ � ���, �� ���������� ������ � ����������� ���������, ���������� ������ � ������ �� ����� ������� �� ������ �������� ������� �������.<br><br>�� ���� �������� ������� �����, � � ��������� ����� ������� ����� ����� �������� � �������.<br><br>�� �������! ����� ����! ����� ������! ���� �����!<br><br>������ ������, 55-��.<br><br><br>������� �������� ���������� �������<br>���� ������������� ������� ������ �������<br><br><br><br><br><br>����� ���� ������ �������<br><br>� 1997 ���� ����� � ������� �������, ������ ��������� tatu � ����������� ���� �� ������ �����. �� ��� ������ ���� ������� ����� ������.<br><br>� 1998 ���� � ������ ����������� ��������� �� ����� �������.<br><br>� 1998 ��������� � �������� ������� Streethools.<br><br>� 1999 ���� ������� ������� ������ �� �����������.<br><br>� 1999 ���� � ������� ������������� ������� 88� ��������� � ��������� �� ����������� � �.�, ������� �������. ����� �� ������ � �����, �������, ����, ��������.<br><br>� 2000 ���� ��������� � ���������� ������������ ��������38 ��������� �� ���������. �������� ������ ������������ � �������� ������ � 222 ��. ������ �� 3 ����. ����� �� �������� �� �������� ����.<br><br>� 2001 ���� ��� ��������������� �������� ������� ������ ����� ������-���������� ���������� � ����. ��� ������ ����������� � �������� ��������� � ������������� ��������� � �������, ��������, �� �������� �������.<br><br>������� � ������ ������ ������� ������� ��������� ������������ �����:<br><br>2003 ���, ���� � ����� ���������� �������.<br><br>2003 ���, ������ � ����� ��������� ������� �������.<br><br>2004 ��� � ����� ������������ ���������.<br><br>2005 ��� � ����� ������������ ��������� ������� ����.<br><br>2006 ��� � ����� ������, ������ ���������� �������, ���������� ��������� � ������ �� ���������������� �����, ����� ������� ���������, ����� ������ ������ �����, ����� ���-����� ��������.<br><br>21 ������� 2006 ���� � ����� �� ������������ �����.<br><br>����� ������ ������ ������� ����� ������������:<br><br>� 2007 ���� ����� ��� ������������ ����� � ��������, ������� �������� ����� ��������� � � 2006 ����.<span class=\"inquote\">[/q]</span></blockquote><br><br><blockquote class=\"quote\"><span class=\"inquote\">[q]</span><b>������:</b><br> ����� �������� �������������<br>���� ������ ������� ��������<br><br>����� �������� ����� ����� ������� �� ������ � ������������ � ������ ���� ������������ ����� ������������������ ������� � �������������� ������������� ������� ���������� � ������� ����������. � ����� ������� ����������� ��� ���������� ����������� (����������, ���������, ���������, ���������������) ������ ������� �������� � ������������ ���, ��� �� �������� ������������� ������� ������� �� �������� �� ������ �������� � �������� ��������� �������. ��� ������ ��� ����� �� �������� ������ ������ ������� ���������. ����� ������� �������, ��������� �������������� ����������� ����������� ������� ��������������� ��������, ������������� �������� ��� �������� ����� ������. ��� ����� �� ����������� �� ������� ����, �� ������� �����������. ���������� �� ������� �������� �������� ���� ��� �� ����������, �.�. ���������� ��������� ���� ������������ ���������������.<br><br>����������� ������ �� ������ � ������������ ��������� �������� �� ������ ������ � ���������������� �������� �������� ����������������� ������������ ����. ����� ������������� � ������ �� ����������.<br><br>�������� ����� ����������� ������������� ����� ���������, ��������� � ��������� ���������� ���� �� ����������������� � �� ������ ��������� ������������� ������. ������ ��� ���� ����, ������, ������ ��������.<br><br>� ������ ������� ������� ������� �������� ���������������� �������� �����, ��������� ������� ������������ ������� � ������� ���� ��� ����� ������ (� ������ ������ �� ������� ������ �� �����������, ���������� ������� �������� �����������, ��������), � ��� ��� ����������.<br><br>��������� ��� � ��� ��������� ������ ������ ������ � ����������� ������ ���������� ���� ���, ������������ ����������, � ����� �� ������ ������� ��� �������� �������� ������� ��, �� ���� ���������� �������� ����������� ���� ���������������� �����������. ������ ��� �������� ����������� ��������� � ������� �������, ������ ������ ��������� �������������� ��� � ��� ���� �����. ������ ������������ ���, ��� ������� ������ �� �����, ���� ���� �� ������ � ��� �������� ����������� � ������������ � ������� �� ������ � ������������.<br><br>� ������� � 2002 ���� ����� ���������� ���������� � ���, ��� �� ������� ������������������ ����������� ��� ������� ���������� �������� ������� ����� ���������� ����������, ����������� ������������. ��� ������ �������� � ��� ����������� ������� �������� �����, ����������������, �������� ���������� ������� �����. ������� � ��� ���� ������������ ��� ��� ��� (����� �� ������������ �� ��� ����� �������).<br><br>�� ������ �� ������� ���������� �������� �������� �� ������ ������������� ������, � ���������� ������� ������ ���������� ������ �������� �� ���. �� ��� ������ ���� �� �����, ��� ��������� �� ���������� ��������, ���� ������ ��������. ��, � ���������, ��-�� ����������� ��� �������� ���������� ������������� �������������� ��������� (��������) ������ �� ������. ��� �������� ����� ������ ������ ��������� ���-��� ����������� ���������� ������, � ����������� � � ������� ����� ������. �� ������������� ���� ��������, ��� ��� �������� ��� �� ���������� �������, ��� � � ������� �������� ������ �� �������� ������������ ����������.<br><br>����� 2002 ���� ������� ������� ������ � ������� ����� (������ ������-����������� �����) ����� ��� �������������� ������ �� �.����. ������ ���� �������� ����������� ���������� �����������. ������� ���� ��� ���� ������������� �������� ��������������� ����������� � ���������� ����. � ������ ���������� ���� 10-16 ���, ��������� ������������� � ������ � ��������������� �����. ������ ������������ �� ������ ���� �� ������ ����� (��� ������). ����� ������ �������� �� �����. ���� ��������, ��� �������� ����� ������� ���������� ������ ���� �� ����, � �� ������������ � ������� �������. ����� ������, ��� ��������� ��� ����� ����� ������ � ������� ���������� �������� �������, ��� ���� ���� ������� ������� ���� � ������ �� ����� ������ �� ������� � ����� �������������. ������ ����� ������: ������� ����, ��������� ����� � ��� ��� ������ ���� ��������� ����������. � ������� ����������� �������, �������� ������, �� ��� ������ ������, ��������� ���� �� ������, ���������� ����� ����� ��������� �� 12-������� ������� ����������� �����. ������ ��������� ������� ��������� ������������ ��������, ��� ����� ���� ����, � ����� ������������ ���������� � ������� ������ �������, ��������� �������� ����� ������ ������ ���, � �.� . �� �� ���� ����� ������������ (����� ���� ��� ���� �������, ���������� �� �������� ������), ������ ���������� ������� � ����������. ������ ���� ������������ ���� (4 ����� ����� � ������), �� ���� �� �����, ��� ���������. ������ ����������� ������ ������������ ������� ���������� ���� ������, ������� ������� �� �� �������, �� ��� �� ������ ���� � ������ � ������, ��� � ������������ ���� ���� ���, � ������, ������ �� ������� ������� �������� ���� � �������. ������� ������, ������� �������� ������ ��� ������������ ������ ���� � ������ �����, �����������, �����, ��� �� ��� � ����������, �� ���� ����� �����, ����� ������� ������� ������� ������� ����� ����������� ���� ������ � ����� ���� � ��� ����, ��� ����� ��������� �� ������ ���������� ������ ���. ��� ��������� ��� � �����-�� ������� ���. ����������� ������ ��� ���������, ��� �� ����� �� ������ ������� �����. ��� ������� ����� ���������� � �� ���� �� ���� ������, � ��� �����-�� ������ ������, 4 ������� � � ������ ���������, ������� ������ � ����������. ���� ���������� ������������� �� ����� ��� ������, �� ������� ��� ����� ��� ���������� ������, ������� ������� ��� ���� �� ����� � ������ ������������, � �������� �� ���� ��������� ������� �� ���� �� ��������, �� ���� � ������������ ������� ������ �� ����������, � ���� �� ����� ������� � ������� ������� ��� ������.<br><br>� ����� ���� �������� �������, ��� ������� ������ ����, ��� ������ ������ ����� ����� ��������� ������� �������. ��� ������������ � ��������� �������, ����������, ��� �� ������� ���� ��������. ����� 20 ����� �� ������ ������ ���������� ���������� ���������� ������ � ����� ���������. ������ ��������� ����������� ������� ������ ���������� �����������, �� ��������� ������� �� �������� ������� �����. ��� ������������ ������� �������. � ����� � ���, ��� ����� ������ �� �������������� ������ ���������� � �� ��������, � �� ��� �� ������� ��� ������������ ������ ��������� � �� ������ � ��������� ���� ��������� ������ ����������� �������, ��� ������ ���� ������� �� ������� ������ ������. ����, ����������� � ���������� (4 ������� �������), ���� �� ������� ���������� ��������. � ������ � ����������� � ���� � �������� �� ������ � �� �����. ������� ������� ������� ��� ���������� ��������� ������� �� ����������� ��������.<br><br>���� ������ ��� ������� �� 4 ������ 2002 ����, ����� � ������ � ��������� ����������� ����������� ������ ����� ������ � ������� ���. �� ������ � ���� ��� ��� ������ ���� ��������� ���������. ������ �������� ���������� ��� �� �������� ��� � ��������, � ���� �� ��������� �������� �����:<br><br>1. ���������� � ��������� ������������ ������ �� ������, ����� ���������� ������������ �� ������ � ������� �� ���� ������� ����������.<br><br>2. ����������, � ������ ��������� ��� �� ����� ��������� ��������� ������, �� ������ ���������������.<br><br>3. ���������� ������� ����� �������� ���� ��������������� � ������������ � �������� ������������� ������� �������� ���.<br><br>4. � ������ ���������� ���������� ������ ������� ���, ���� �������� ���� � ����������� �������� ����������� � ���������� ��������, � �������� �������� ����� ������������ ��������� ����� ����������, ��������� ������ �� �����������<br><br>��� ��� ����� � 2002 ���� �������� �������� ������������� ������� �������� ��� ��������� �� �������������� �������� ������������� �������� ���� ����������.<br><br><a href=\"http://www.nordrus.org/news/detail.php?ID=1677\" style=\"text-decoration: underline;\" target=\"_blank\">http://www.nordrus.org/news/detail.php?ID=1677</a><span class=\"inquote\">[/q]</span></blockquote><br><br>PS �������� � ������ ������������� ���� ���� �� ���� ��������� ��-�� ����, ��� ������� ��� ����������� ������ ������, ����� ��������.<br><br>����� ������!";
//        System.out.println(hasUrl("234"));
//        System.out.println(hasImg(s));
//        System.out.println(cleanBoardSpecific(s));
        System.out.println(hasImg("<IMG BORDER=0 SRC=\"pic/bigsmile.gif\" ALT=\":))\">"));
        System.out.println(hasImg("<IMG SRC=\"pic/bigsmile.gif\">"));
        System.out.println(hasImg("<IMG BORDER=0 SRC=\"http://www.ru/pic/bigsmile.gif\" ALT=\":))\">"));
        System.out.println(hasImg("<IMG BORDER=0 SRC=\'http://www.ru/pic/bigsmile.gif\' ALT=\":))\">"));
        System.out.println(hasImg("<IMG BORDER=0 SRC=http://www.ru/pic/bigsmile.gif ALT=\":))\">"));
        System.out.println(hasImg("<IMG BORDER=0 SRC=http://www.ru/pic/bigsmile.gif>"));
        System.out.println(hasImg("<IMG SRC=http://www.ru/pic/bigsmile.gif>"));
        System.exit(0);
    }
}