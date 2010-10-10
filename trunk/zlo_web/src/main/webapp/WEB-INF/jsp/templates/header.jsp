<%@ page import="info.xonix.zlo.web.utils.RequestUtils" %>
<%--
  User: Vovan
  Date: 25.04.2008
  Time: 18:01:15
--%>
<%@ include file="/WEB-INF/jsp/import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>

<c:set var="activeScreen"><tiles:getAsString name="activeScreen"/></c:set>
<jsp:useBean id="siteRoot" class="java.lang.String" scope="request"/>

<c:set var="site">
    <c:choose>
        <c:when test="${not empty param['site']}">${param['site']}</c:when>
        <c:otherwise>${cookie['site'].value}</c:otherwise>
    </c:choose>
</c:set>

<div id="header" align="center">
    <ul>
        <li><a href="search"
               <c:if test="${activeScreen == 'search'}">class="activeLink"</c:if>>�����</a></li>
        <li><a href="stats.jsp?site=${site}"
               <c:if test="${activeScreen == 'stats'}">class="activeLink"</c:if>>����������</a></li>
        <li><a href="nickhost.jsp?site=${site}"
               <c:if test="${activeScreen == 'nickhost'}">class="activeLink"</c:if>>����/�����</a></li>
        <li><a href="history.jsp"
               <c:if test="${activeScreen == 'history'}">class="activeLink"</c:if>>�������</a></li>
        <li><a href="forums.jsp"
               <c:if test="${activeScreen == 'forums'}">class="activeLink"</c:if>>������</a></li>
        <li><a href="faq.jsp"
               <c:if test="${activeScreen == 'faq'}">class="activeLink"</c:if>>FAQ</a></li>
        <li><a href="about.jsp"
               <c:if test="${activeScreen == 'about'}">class="activeLink"</c:if>>About</a></li>

        <c:if test="<%= RequestUtils.isLocalIp(request) %>">
            <span class="adminLinks">
                <%--admin menu items--%>
                <li><a href="detectspam.jsp"
                       <c:if test="${activeScreen == 'detectspam'}">class="activeLink"</c:if>>Spam</a></li>
                <li><a href="admin.jsp"
                       <c:if test="${activeScreen == 'admin'}">class="activeLink"</c:if>>Admin</a></li>
            </span>
        </c:if>
    </ul>
</div>