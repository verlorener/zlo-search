<%@ page import="org.xonix.zlo.search.config.HtmlStrings" %>
<%--
  User: gubarkov
  Date: 14.08.2007
  Time: 16:46:12
--%>
<%@ include file="import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>

<jsp:useBean id="backendBean" class="org.xonix.zlo.web.BackendBean" scope="session" />
<jsp:setProperty name="backendBean" property="*" /> <%-- all from request properties --%>

<jsp:useBean id="siteRoot" class="java.lang.String" scope="session" />

<html>
    <head>
        <title><%= HtmlStrings.PAGE_TITLE %></title>
        <link rel="stylesheet" type="text/css" href="main.css" />
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>
        <div id="searchform">
            <table width="100%">
                <tr><td height="20px"></td></tr>
                <tr>
                    <td width="33%"></td>
                    <td>
                        <form action="search" method="get">
                            <%--<%= HtmlStrings.LABEL_TITLE %> <input type="text" name="title" <c:if test="${not empty param['title']}">value="<c:out value="${param['title']}" />" </c:if>style="width:450px;" />--%>
                            <%--<%= HtmlStrings.LABEL_TOPIC %> <jsp:getProperty name="backendBean" property="topicSelector" />
                            <br/>--%>
                            <%= HtmlStrings.LABEL_TEXT %> <input type="text" name="body" <c:if test="${not empty param['body']}">value="<c:out value="${param['body']}" />" </c:if>style="width:450px;" />
                            <%= HtmlStrings.LABEL_TOPIC %> <jsp:getProperty name="backendBean" property="topicSelector" />
                            <br/>
                            <%= HtmlStrings.LABLE_SEARCH %>
                            <input type="checkbox" name="inTitle" id="inTitle" <c:if test="${not empty param['inTitle']}">checked="checked"</c:if>/> <label for="inTitle"><%= HtmlStrings.LABLE_IN_TITLE %></label>
                            <input type="checkbox" name="inBody" id="inBody" <c:if test="${not empty param['inBody']}">checked="checked"</c:if>/> <label for="inBody"><%= HtmlStrings.LABLE_IN_BODY %></label>
                            <br/>
                            <%= HtmlStrings.LABEL_NICK %> <input type="text" name="nick" <c:if test="${not empty param['nick']}">value="<c:out value="${param['nick']}" />" </c:if>style="width:200px;" />
                            <%= HtmlStrings.LABEL_HOST %> <input type="text" name="host" <c:if test="${not empty param['host']}">value="<c:out value="${param['host']}" />" </c:if>style="width:200px;" />
                            <br/>
                            <input type="checkbox" name="dates" id="dates" onchange="changedDatesSelector();" <c:if test="${not empty param['dates']}">checked="checked"</c:if>/> <label for="dates"><%= HtmlStrings.LABEL_DATES %></label>
                            <%= HtmlStrings.LABEL_FROM_DATE %> <input type="text" name="fd" id="fd" value="${sessionScope['fd']}" />
                            <%= HtmlStrings.LABEL_TO_DATE %> <input type="text" name="td" id="td" value="${sessionScope['td']}" />
                            <br/>
                            <%= HtmlStrings.LABEL_SITE %> <jsp:getProperty name="backendBean" property="siteSelector" />
                            <%= HtmlStrings.LABEL_PER_PAGE %> <jsp:getProperty name="backendBean" property="pageSizeSelector" />
                            <br/>
                            <input type="submit" name="submit" value="Search"/>
                        </form>
                    </td>
                    <td></td>
                </tr>
            </table>
        </div>
    <c:if test="${requestScope['debug'] == true}">
        <br/>
        <div id="debug">
            <pre>
                Query: <c:out value="${sessionScope['searchResult'].query}" />
            </pre>
        </div>
        <br/>
    </c:if>

    <c:choose>
        <c:when test="${empty requestScope['error']}">
            <c:if test="${not empty sessionScope['searchResult']}">
                <display:table name="sessionScope.searchResult.paginatedList" id="msg" htmlId="resultTable"
                               decorator="org.xonix.zlo.web.decorators.SearchResultLineDecorator" requestURI="search">
                    <display:setProperty name="basic.msg.empty_list"><span class="pagebanner">���������, ��������������� ��������� ��������� ������ �� �������. </span></display:setProperty>
                    <display:setProperty name="paging.banner.one_item_found"><span class="pagebanner">������� ���� ���������. </span></display:setProperty>
                    <display:setProperty name="paging.banner.all_items_found"><span class="pagebanner">������� ���������: {0}, �������� ���. </span></display:setProperty>
                    <display:setProperty name="paging.banner.some_items_found"><span class="pagebanner">������� ���������: {0}, �������� � {2} �� {3}. </span></display:setProperty>
                    <display:setProperty name="paging.banner.group_size">15</display:setProperty>
                    <display:setProperty name="paging.banner.full">
                        <span class="pagelinks">[<a href="{1}">����</a>/<a href="{2}">����</a>] {0} [<a href="{3}">����</a>/<a href="{4}">�������</a>]</span>
                    </display:setProperty>
                    <display:setProperty name="paging.banner.first">
                        <span class="pagelinks">[����/����] {0} [<a href="{3}">����</a>/<a href="{4}">�������</a>]</span>
                    </display:setProperty>
                    <display:setProperty name="paging.banner.last">
                        <span class="pagelinks">[<a href="{1}">����</a>/<a href="{2}">����</a>] {0} [����/�������]</span>
                    </display:setProperty>

                    <display:column title="<%= HtmlStrings.HEADER_NUM.toString() %>"><c:out value="${msg.hitId + 1}" /></display:column>
                    <display:column title="<%= HtmlStrings.HEADER_TITLE.toString() %>">
                        <a href="http://<c:out value="${siteRoot}" />/?read=<c:out value="${msg.num}" />">
                            <c:if test="${not empty msg.topic and msg.topic != '��� ����'}">
                                [<c:out value="${msg.topic}" />]
                            </c:if>
                            <c:out value="${msg.title}" escapeXml="false" /></a>
                        <small>
                            <c:if test="${empty msg.body}">(-)</c:if>
                            <c:if test="${msg.hasUrl}">(url)</c:if>
                            <c:if test="${msg.hasImg}">(pic)</c:if>
                        </small>
                        <a class="search" href="msg?num=<c:out value="${msg.num}" />"><%= HtmlStrings.LINK_SAVED_MSG %></a>
                    </display:column>
                    <display:column title="<%= HtmlStrings.HEADER_NICK.toString() %>">
                        <span class="nick">
                            <c:choose>
                                <c:when test="${not msg.reg}">
                                    <c:out value="${msg.nick}" />
                                </c:when>
                                <c:otherwise>
                                    <a href="http://<c:out value="${siteRoot}" />/?uinfo=<c:out value="${msg.nick}" />"><c:out value="${msg.nick}" /></a>
                                </c:otherwise>
                            </c:choose>
                        </span>
                        <a class="search" href="search?nick=<c:out value="${msg.nick}" />">?</a>
                    </display:column>
                    <display:column title="<%= HtmlStrings.HEADER_HOST.toString() %>">
                        <c:out value="${msg.host}" />
                        <a class="search" href="search?host=<c:out value="${msg.host}" />">?</a>
                    </display:column>
                    <display:column title="<%= HtmlStrings.HEADER_DATE.toString() %>" property="date" />
                </display:table>
            </c:if>
        </c:when>
        <c:otherwise>
            <div class="error">
                <c:out value="${requestScope['error']}" />
            </div>
        </c:otherwise>
    </c:choose>
    </body>
    <script type="text/javascript">
        changedDatesSelector();
    </script>
</html>
