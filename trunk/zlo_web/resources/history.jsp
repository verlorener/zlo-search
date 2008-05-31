<%@ include file="WEB-INF/jsp/import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<link rel="stylesheet" type="text/css" href="main.css" />

<c:set var="localIps"><fmt:message key="history.localIps" /></c:set>
<c:set var="isLocalIp" value="<%= RequestUtils.isLocalIp(request, (String) pageContext.getAttribute("localIps")) %>" />
<c:set var="showAll" value="${ param['all'] != null and isLocalIp }" />

<c:choose>
    <c:when test="${isLocalIp and not empty param['n']}">
        <c:set var="numberToShow" value="${param['n']}" />
    </c:when>
    <c:otherwise>
        <c:set var="numberToShow"><fmt:message key="history.numberToShow" /></c:set>        
    </c:otherwise>
</c:choose>

<sql:setDataSource dataSource="<%= DbAccessor.getInstance("search_log").getDataSource() %>" />

<c:choose>
    <c:when test="${showAll}">
        <sql:query var="res">
            SELECT * FROM request_log
            order by req_date DESC
            LIMIT ?;
            <sql:param value="${xonix:int(numberToShow)}" />
        </sql:query>
        <sql:query var="totalNum">
            SELECT count(*) AS count FROM request_log
        </sql:query>
    </c:when>
    <c:otherwise>
        <sql:query var="res">
            SELECT * FROM request_log
            WHERE host not in ${xonix:mysqlRange(localIps)}
            order by req_date DESC
            LIMIT ?;
            <sql:param value="${xonix:int(numberToShow)}" />
        </sql:query>
        <sql:query var="totalNum">
            SELECT count(*) AS count FROM request_log
            WHERE host not in ${xonix:mysqlRange(localIps)}
        </sql:query>
    </c:otherwise>
</c:choose>

<title>������� ��������</title>

<tiles:insertDefinition name="header.history" />

<div align="center" class="content">
    <h3>������� ��������
            <c:if test="${isLocalIp}">
                <c:choose>
                    <c:when test="${!showAll}"><a href="history.jsp?all" class="search">(��������)</a></c:when>
                    <c:otherwise><a href="history.jsp" class="search">(������)</a></c:otherwise>    
                </c:choose>
            </c:if>
    </h3>
    <small>(����� ��������:${totalNum.rows[0].count}, �������� ���������: ${numberToShow})</small>

    <display:table name="${res.rows}" id="row" htmlId="resultTable" decorator="info.xonix.zlo.web.decorators.HistoryTableDecorator">
        <display:column title="�" headerClass="head">
            <a href="search?<c:out value="${row.req_query_str}"/>" class="search">${row_rowNum}</a>
        </display:column>
        <display:column property="searchText" title="�����" headerClass="head" />
        <display:column property="searchNick" title="��� ������" headerClass="head" />
        <display:column property="searchHost" title="���� ������" headerClass="head" />
        <display:column title="����" headerClass="head">
            <% Site site = Site.getSite((Integer)((TreeMap)row).get("site")); %>
            <a href="http://<%= site.getSITE_URL() %>">
                <%= site.getName() %></a>
        </display:column>
        <display:column property="reqDate" title="����" headerClass="head" class="small" />
        <c:if test="${showAll}">
            <display:column property="host" title="����" class="small" headerClass="head" />
            <display:column property="user_agent" title="User-Agent" class="small" headerClass="head" />
        </c:if>
        <display:column property="userAgentSmall" title="�������" class="small center" headerClass="head" />
    </display:table>
</div>

<tiles:insertDefinition name="ga" />