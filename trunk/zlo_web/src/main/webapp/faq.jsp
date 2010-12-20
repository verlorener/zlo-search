<%@ include file="WEB-INF/jsp/import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>

<link rel="stylesheet" type="text/css" href="main.css"/>

<title>FAQ</title>

<tiles:insertDefinition name="header.faq"/>

<div class="content">
    <h3 class="attention">News</h3>
    <ul>
        <li>�������� <a href="ws.jsp">���-������ ������</a>;</li>
        <li>�������� ����� �� <a href="http://forum.dolgopa.org/">http://forum.dolgopa.org/</a>;</li>
        <li>�������� ����� �� <a href="http://x.zlowiki.ru/">http://x.zlowiki.ru/</a>;</li>
        <li>�������� ����� �� �������: <a href="http://takeoff.mipt.ru/cgi-bin/board/index.cgi?index">����� ������
            ������ ����</a>, <a href="http://zlo.rt.mipt.ru/dev/">Programming Board</a>;
        </li>
        <li>��������� ���������������� RSS. ������ ����� ����������� �� ��������� �� ����������� �������, ��������,
            ����� <a
                    href="search?rss&st=adv&text=%F1%E8%F1%FC*+%F2%E8%F2%FC*+boobs+tits+%F1%E8%F1%E8+%F6%FB%F6%EA%E8&topic=-1&inTitle=on&inBody=on&hasImg=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">�����
                <img src="feed-icon-14x14.png" alt="�������� �����"/></a>;
        </li>
        <li>�������� ����� �� ������ <a href="http://velo.mipt.ru/cgi/forum/index.cgi">velo.mipt.ru</a>;</li>
        <li>������ ������ ����� ������ � ����� ������, ��������
            <a href="search?st=all&text=http%3A%2F%2Fbash.org.ru%2Fbest.php&topic=-1&inTitle=on&inBody=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                http://bash.org.ru/best.php</a>;
        </li>
        <li>���� �������� ����� ������ �� ���������� <a
                href="search?st=all&text=&topic=-1&inTitle=on&inBody=on&hasImg=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">�
            ����������</a>,
            � ������ �� ���������� <a
                    href="search?st=all&text=&topic=-1&inTitle=on&inBody=on&hasUrl=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">��
                ��������</a>;
        </li>
    </ul>

    <h3>��� ������?</h3>
    ��������� ����� ��� ������ ������:
    <ul>
        <li><a href="#adv">�� ����� �������</a>;</li>
        <li><a href="#adv">���� �� � ����� �� ����</a>;</li>
        <li><a href="#exct">������ �����</a>;</li>
    </ul>
    ����� ����, �������� ���������� ���������� ������ ��:
    <ul>
        <li>���� ��������;</li>
        <li>����� ������������;</li>
        <li>����� ������������;</li>
        <li>���������� ���;</li>
        <li>�������������� ���������: 1) ������ �� ������������������ ������������� 2) ������ ����� ��������� ��
            �������� 3) ������ ����� ��������� � ����������;
        </li>
    </ul>
    �����, �������� ����� ������ �� ����� ������������ � �����.

    <h4 id="exct">������ �����</h4>
    ��� ������ � ������ <i>"������ �����"</i> ������ ��������� � ������ ���������� ������� ����� ������ �� �������
    ���������� ����.

    <h4 id="adv">����������� �����</h4>
    ������ <i>"�� ����� �������"</i> � <i>"���� �� � ����� �� ����"</i> ������������� ����������� ������������ ������ �
    ���������� ���� ���, ��� � ������ ������ ����� ������� ������ ��������
    �������� AND, � �� ������ OR. ��� ����������� ������ ��������� ������� ����� ����� ���:
    <table border=1>
        <tr>
            <th>������</th>
            <th>����������</th>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%F7%E5%F0%ED%FB%E9+%E1%E5%EB%FB%E9+%EA%F0%E0%F1%ED%FB%E9&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=Search">
                    ������ ����� �������</a>
                ���
                <a href="search?st=all&text=%F7%E5%F0%ED%FB%E9+OR+%E1%E5%EB%FB%E9+OR+%EA%F0%E0%F1%ED%FB%E9&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                    ������ OR ����� OR �������
                </a>
            </td>
            <td>������ "���". ����� ������� ��� ���������, ���������� ���� �� ���� �� ����.</td>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%EF%F0%E5%E2%E5%E4+-%EC%E5%E4%E2%E5%E4&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=Search">
                    ������ -������</a>
                ���
                <a href="search?st=all&text=%EF%F0%E5%E2%E5%E4+NOT+%EC%E5%E4%E2%E5%E4&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                    ������ NOT ������
                </a>
            </td>
            <td>������ "��". ����� ������� ��� ���������, ���������� "������" �� �� ���������� "������". ������ "-"
                ����� ������������ "!".
            </td>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%2B%EF%F0%E5%E2%E5%E4+%2B%EC%E5%E4%E2%E5%E4&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=Search">
                    +������ +������</a>
                ���
                <a href="search?st=all&text=%EF%F0%E5%E2%E5%E4+AND+%EC%E5%E4%E2%E5%E4&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                    ������ AND ������
                </a>
            </td>
            <td>������ "�". ����� ������� ��� ���������, ���������� ������������ ��� �����. ������������� ������ ������
                "�� ����� �������".
            </td>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%22java+html%22%7E2&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                    "java html"~2</a></td>
            <td>����� ������� ���������, ��� ����� "java" � "html" ������� �� ����� ��� �� 2 ����� ���� �� �����.</td>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%22%F3+%EB%F3%EA%EE%EC%EE%F0%FC%FF+%E4%F3%E1+%E7%E5%EB%E5%ED%FB%E9%22&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=Search">
                    "� ��������� ��� �������"</a></td>
            <td>����� ������� ���������, ���������� ������ �����. ������������� ������ ������ "������ �����".</td>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%F2%3F%E7&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=Search">
                    �?�</a></td>
            <td>"?" ������������� ����� ������������ �����. ����� ������ ����� "���", "���". <i>�� �������� � ������
                ������ �����!</i></td>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%F1%F2%EE*&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=Search">
                    ���*</a></td>
            <td>"*" ������������� ������������� ����� ����, ������� 0. ����� ������ ��� �����, ������������ �� "���".
                <i>�� �������� � ������ ������ �����!</i></td>
        </tr>
        <tr>
            <td>
                <a href="search?st=adv&text=%2B%28%E1%E5%EB%FB%E9+%F7%E5%F0%ED%FB%E9%29+%2B%F6%E2%E5%F2&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=Search">
                    +(����� ������) +����</a>
                ���
                <a href="search?st=all&text=%28%E1%E5%EB%FB%E9+OR+%F7%E5%F0%ED%FB%E9%29+%F6%E2%E5%F2&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                    (����� OR ������) ����
                </a>
            </td>
            <td>����� ������������ ������. ����� ������� ��������� �� ������ "����" � � ����� �� ���� "�����" � "������"
                ��� � ����� �����.
            </td>
        </tr>
    </table>

    ��� ������� ��������:
    <ul>
        <li>
            <a href="search?st=adv&text=%2B%28%EF%EE%F1%EE%E2%E5%F2%F3%E9%F2%E5+%EF%EE%E4%F1%EA%E0%E6%E8%F2%E5%29+%2B%28%F4%E8%EB%FC%EC+%EA%E8%ED%EE%29&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                +(����������� ����������) +(����� ����)
            </a>
        </li>
        <li>
            <a href="search?st=adv&text=%2B%F2%E5%EB%E5%F4%EE%ED+%2B%F2%E0%EA%F1%E8&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                +������� +�����
            </a>
        </li>
        <li>
            <a href="search?st=adv&text=%2B%E7%E5%F0%EA%E0%EB%EE+%2Bnod32&topic=-1&inTitle=on&nick=&host=&site=0&pageSize=0&submitBtn=%C8%F1%EA%E0%F2%FC%21">
                +������� +nod32
            </a>
        </li>
    </ul>

    <h4>�������� �����</h4>
    �� ��������� ����������� ��������� ���� ��� ������ ��� ����, ����� �������� ������ �����������. ������������ �
    ���������� ������� �������� ����� ��� ������� ��������� � ��� ����
    ����� ��� ���������� �����.
</div>

<tiles:insertDefinition name="ga"/>