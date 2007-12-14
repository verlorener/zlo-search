package org.xonix.zlo.web;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Author: Vovan
 * Date: 14.12.2007
 * Time: 16:49:08
 */
public class FoundTextHighlighter {

    private static final Logger logger = Logger.getLogger(FoundTextHighlighter.class);

    private String[] highlightWords;
    private String text;

    public void setHighlightWords(String[] highlightWords) {
        this.highlightWords = highlightWords;
    }

    public String getWordsStr() {
        return StringUtils.join(highlightWords, ",");
    }

    public void setWordsStr(String wordsStr) {
        if (StringUtils.isNotEmpty(wordsStr)) {
            highlightWords = wordsStr.split(",");
        } else {
            highlightWords = new String[0];
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHighlightedText() {
        String txt = text;
        for (String w : highlightWords) {
            txt = txt.replaceAll("(?iu)(" + w + ".*?)\\b", "<span class=\"hl\">$1</span>");
        }
        return txt;
    }
}
