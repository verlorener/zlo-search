<%--
  User: Vovan
  Date: 01.06.2008
  Time: 1:26:28
--%>
<%@ include file="WEB-INF/jsp/import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<link rel="stylesheet" type="text/css" href="main.css" />

<title>������������� ������</title>

<tiles:insertDefinition name="header.forums" />

<div align="center" class="content">
    <h3>������������� ������</h3>

    <c:set var="sites" value="<%= Site.getSites() %>" />

    <display:table id="site" htmlId="resultTable" name="${sites}">
        <display:column title="������">
            <c:set var="url" value="http://${site.SITE_URL}/" />
            <a href="${url}">${url}</a>
        </display:column>
        <display:column title="��������" property="SITE_DESCRIPTION" />
        <display:column title="���������" property="dbManager.lastIndexedNumber" />

        <display:column title="�������">
            <a href="search?site=${site.num}" class="search">(�����)</a>
            <a href="stats.jsp?site=${site.num}" class="search">(����������)</a>
            <a href="nickhost.jsp?site=${site.num}" class="search">(����/�����)</a>
        </display:column>

    </display:table>
</div>
