<%@ include file="WEB-INF/jsp/import.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" type="text/css" href="css/main.css"/>

<c:set var="title">Web Service поиска</c:set>
<title>${title}</title>

<tiles:insertDefinition name="header.developer"/>

<div class="content">
    <h3>${title}</h3>

    Бордопоиск предоставляет <a href="ws/">веб-сервис</a> (<a href="ws/search?wsdl">wsdl</a>),
    выполненный на технологии <a href="http://cxf.apache.org/">Apache CXF</a>.

    Для работы из <a href="http://www.python.org/">python</a> рекомеднуется библиотека для WS
    <a href="https://fedorahosted.org/suds/">suds</a>
    (<a href="http://zlo.rt.mipt.ru/?read=6939422">пример использования</a>).

    Веб-метод search принимает поисковую строку, соответствующую
    <a href="http://lucene.apache.org/core/3_6_0/queryparsersyntax.html">формату Lucene query parser</a>'a, при этом
    распознаются поля:
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