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
        fh.setHighlightWords(new String[] {"����"});
        fh.setText("������ ����, ������ �-��...");
        System.out.println(fh.getHighlightedText());
//        System.out.println("aAbaA".replaceAll("(?i)aa", "1"));
//        System.out.println("�����".replaceAll("(?iu)��", "1"));
//        System.out.println("���������".toLowerCase());
//        System.out.println(Pattern.compile("��", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher("��").find());
    }
}
