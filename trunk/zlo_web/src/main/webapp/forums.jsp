<%@ page import="info.xonix.zlo.search.domainobj.Site" %>
<%@ page import="info.xonix.zlo.search.logic.AppLogic" %>
<%@ page import="info.xonix.zlo.search.logic.SiteLogic" %>
<%--
  User: Vovan
  Date: 01.06.2008
  Time: 1:26:28
--%>
<%@ include file="WEB-INF/jsp/import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<link rel="stylesheet" type="text/css" href="main.css"/>

<%!
    SiteLogic siteLogic = AppSpringContext.get(SiteLogic.class);
    AppLogic appLogic = AppSpringContext.get(AppLogic.class);
%>

<title>������������� ������</title>

<tiles:insertDefinition name="header.forums"/>

<div align="center" class="content">
    <h3>������������� ������</h3>

    <c:set var="sites" value="<%= siteLogic.getSites() %>"/>

    <display:table id="site" htmlId="resultTable" name="${sites}">
        <display:column title="������">
            <c:set var="siteUrl" value="${site.siteUrl}"/>
            <c:if test="${
                not f:endsWith(siteUrl, '.cgi') and
                not f:endsWith(siteUrl, '.exe')
            }">
                <c:set var="siteUrl" value="${siteUrl}/"/>
            </c:if>
            <c:set var="url" value="http://${siteUrl}"/>
            <a href="${url}">${url}</a>
        </display:column>
        <display:column title="��������">
            <c:choose>
                <c:when test="${not site.performIndexing}">
                    <strike><c:out value="${site.siteDescription}"/></strike>
                </c:when>
                <c:otherwise><c:out value="${site.siteDescription}"/></c:otherwise>
            </c:choose>
        </display:column>
        <display:column title="���������">
            <%= appLogic.getLastIndexedNumber((Site) site) %>
        </display:column>

        <display:column title="�������">
            <a href="search?site=${site.siteNumber}" class="search">(�����)</a>
            <a href="stats.jsp?site=${site.siteNumber}" class="search">(����������)</a>
            <a href="nickhost.jsp?site=${site.siteNumber}" class="search">(����/�����)</a>
        </display:column>

    </display:table>
</div>

<tiles:insertDefinition name="ga"/>
