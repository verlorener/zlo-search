package org.xonix.zlo.web.servlets.test;

import org.xonix.zlo.search.config.Config;
import org.xonix.zlo.web.FoundTextHighlighter;

import java.util.regex.Pattern;

/**
 * Author: Vovan
 * Date: 14.12.2007
 * Time: 19:35:01
 */
public class WebTest1 {
    public static void main(String[] args) {
        new Config();
        m1();
    }

    public static void m1() {
        FoundTextHighlighter fh = new FoundTextHighlighter();
        fh.setHighlightWords(new String[] {"vkontakte"});
        fh.setText("<a href=\"http://a.vkontakte.ru/club886777\" style=\"text-decoration: underline;\" target=\"_blank\">��� ��� ����vkontakte����� vkontakte �_�</a>");
        System.out.println(fh.getHighlightedText());
        fh.setText("������ ������������) ���������<br><img class=\"imgtag\" src=\"http://cs41.vkontakte.ru/u823713/a_584103a.jpg\"><br><br>����� �����<br><img class=\"imgtag\" src=\"http://cs66.vkontakte.ru/u1888933/a_fce39de.jpg\">");
        System.out.println(fh.getHighlightedText());
//        System.out.println("aAbaA".replaceAll("(?i)aa", "1"));
//        System.out.println("�����".replaceAll("(?iu)��", "1"));
//        System.out.println("���������".toLowerCase());
//        System.out.println(Pattern.compile("��", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher("��").find());
    }
}
