import info.xonix.zlo.search.FoundTextHighlighter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: Vovan
 * Date: 24.04.11
 * Time: 16:45
 */
@RunWith(Parameterized.class)
public class FoundTextHighlighterTests {
    private static FoundTextHighlighter foundTextHighlighter = new FoundTextHighlighter();

    private String input;
    private String[] words;
    private String expected;

    public FoundTextHighlighterTests(String input, String[] words, String expected) {
        this.input = input;
        this.words = words;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> generateParams() {
        List<Object[]> params = new LinkedList<Object[]>();

        params.add(new Object[]{
                "���� ������� ���������� �� ���������� ����� ������ �� ������� ����",
                new String[]{"�*��"},
                "���� ������� <span class=\"hl\">����������</span> �� ���������� ����� ������ �� ������� ����"});

        return params;
    }

    @Test
    public void test() {
        assertThat(highlight(input, words), equalTo(expected));
    }

    private String highlight(String input, String[] words) {
        foundTextHighlighter.setText(input);
        foundTextHighlighter.setHighlightWords(words);
        return foundTextHighlighter.getHighlightedText();
    }
}
