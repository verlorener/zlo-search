<%@ page import="org.xonix.zlo.search.db.DbAccessor, org.xonix.zlo.search.dao.Site, java.util.TreeMap" %>
<%@ include file="import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<link rel="stylesheet" type="text/css" href="main.css" />
<title>Request history</title>
<h3 align="center">Request history</h3>

<sql:setDataSource dataSource="<%= DbAccessor.getInstance("search_log").getDataSource() %>" />
 <sql:query var="res">
    SELECT * FROM request_log
    order by req_date DESC
    LIMIT 1000;
</sql:query>

<% int i=0; %>
<div align="center">
    <display:table name="${res.rows}" id="row" htmlId="resultTable">
        <display:column title="�" headerClass="head">
            <a href="search?<c:out value="${row.req_query_str}"/>" class="search">
                <%= ++i %>
            </a>
        </display:column>
        <display:column property="req_text" title="�����" headerClass="head" />
        <display:column property="req_nick" title="��� ������" headerClass="head" />
        <display:column property="req_host" title="���� ������" headerClass="head" />
        <display:column title="����" headerClass="head">
            <% Site site = Site.getSite((Integer)((TreeMap)row).get("site")); %>
            <a href="http://<%= site.getSITE_URL() %>">
                <%= site.getSiteName() %></a>
        </display:column>
        <display:column property="req_date" title="����" headerClass="head" />
        <display:column property="user_agent" title="�������" class="small" headerClass="head" />
    </display:table>
</div>