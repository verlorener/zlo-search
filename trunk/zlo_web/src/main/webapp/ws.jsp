<%@ include file="WEB-INF/jsp/import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>

<link rel="stylesheet" type="text/css" href="main.css"/>

<title>Web Service ������</title>

<tiles:insertDefinition name="header.ws"/>

<div class="content">
    <h3>Web Service ������</h3>

    ���������� ������������� <a href="/ws/">���-������</a> (<a href="ws/search?wsdl">wsdl</a>),
    ����������� �� ���������� <a href="http://cxf.apache.org/">Apache CXF</a>.

    ��� ������ �� <a href="http://www.python.org/">python</a> ������������� ���������� ��� WS
    <a href="https://fedorahosted.org/suds/">suds</a>
    (<a href="http://zlo.rt.mipt.ru/?read=6939422">������ �������������</a>).

    ���-����� search ��������� ��������� ������, ��������������� �������
    <a href="http://lucene.apache.org/java/3_0_2/queryparsersyntax.html">Lucene query parser</a>'a, ��� ����
    ������������ ����:
    <ul>
        <li>title - string</li>
        <li>topicCode - int</li>
        <li>body - string</li>
        <li>nick - string</li>
        <li>reg - boolean (0 or 1)</li>
        <li>host - string</li>
        <li>date - msg date (yyyyMMddHHmm)</li>
        <li>url - boolean (0 or 1)</li>
        <li>img - boolean (0 or 1)</li>
    </ul>
</div>

<tiles:insertDefinition name="ga"/>