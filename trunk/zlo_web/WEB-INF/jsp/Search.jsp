<%--
  User: gubarkov
  Date: 14.08.2007
  Time: 16:46:12
--%>
<%@ include file="import.jsp" %>
<%@ page contentType="text/html; charset=windows-1251" %>

<jsp:useBean id="backendBean" class="info.xonix.zlo.web.BackendBean" scope="request" />
<jsp:setProperty name="backendBean" property="*" /> <%-- all from request properties --%>

<jsp:useBean id="hl" class="info.xonix.zlo.search.FoundTextHighlighter" scope="request" />
<jsp:setProperty name="hl" property="hlClass" value="hl0" />
<jsp:setProperty name="hl" property="highlightWords" value="${requestScope['hw']}"/>

<html>
    <head>
        <title><fmt:message key="page.title" /></title>
        <link rel="stylesheet" type="text/css" href="main.css" />
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>
        <tiles:insertDefinition name="header.search" />

        <div id="searchform">
            <table width="100%">
                <tr><td height="1"></td></tr>
                <tr>
                    <td width="33%"></td>
                    <td>
                        <form action="search" method="get" id="searchFrm">
                            <input type="radio" name="st" id="st1" value="all" <c:if test="${param['st'] == 'all'}">checked="checked"</c:if> /><label for="st1"><fmt:message key="label.search.all" /></label>
                            <input type="radio" name="st" id="st2" value="exct" <c:if test="${param['st'] == 'exct'}">checked="checked"</c:if> /><label for="st2"><fmt:message key="label.search.exact.phrase" /></label>
                            <input type="radio" name="st" id="st3" value="adv" <c:if test="${param['st'] == 'adv'}">checked="checked"</c:if> /><label for="st3"><fmt:message key="label.search.advanced" /></label>
                            <br/>
                            <fmt:message key="label.text" /> <input type="text" name="text" <c:if test="${not empty param['text']}">value="<c:out value="${param['text']}" />" </c:if>style="width:450px;" />
                            <fmt:message key="label.topic" /> <jsp:getProperty name="backendBean" property="topicSelector" />
                            <br/>
                            <fmt:message key="label.search" />
                            <input type="checkbox" name="inTitle" id="inTitle" <c:if test="${not empty param['inTitle']}">checked="checked"</c:if>/> <label for="inTitle"><fmt:message key="label.search.in.title" /></label>
                            <input type="checkbox" name="inBody" id="inBody" <c:if test="${not empty param['inBody']}">checked="checked"</c:if>/> <label for="inBody"><fmt:message key="label.search.in.body" /></label>
                            <br/>
                            <fmt:message key="label.search.messages" />
                            <input type="checkbox" name="reg" id="reg" <c:if test="${not empty param['reg']}">checked="checked"</c:if>/> <label for="reg"><fmt:message key="label.search.in.reg" /></label>
                            <input type="checkbox" name="hasUrl" id="hasUrl" <c:if test="${not empty param['hasUrl']}">checked="checked"</c:if>/> <label for="hasUrl"><fmt:message key="label.search.in.has.url" /></label>
                            <input type="checkbox" name="hasImg" id="hasImg" <c:if test="${not empty param['hasImg']}">checked="checked"</c:if>/> <label for="hasImg"><fmt:message key="label.search.in.has.img" /></label>
                            <br/>
                            <fmt:message key="label.nick" /> <input type="text" name="nick" <c:if test="${not empty param['nick']}">value="<c:out value="${param['nick']}" />"</c:if>style="width:200px;" />
                            <fmt:message key="label.host" /> <input type="text" name="host" <c:if test="${not empty param['host']}">value="<c:out value="${param['host']}" />"</c:if>style="width:200px;" />
                            <br/>
                            <input type="checkbox" name="dates" id="dates" onclick="changedDatesSelector();" <c:if test="${not empty param['dates']}">checked="checked"</c:if>/> <label for="dates"><fmt:message key="label.dates" /></label>
                            <fmt:message key="label.from.date" /> <input type="text" name="fd" id="fd" value="<c:out value="${requestScope['fd']}" />" />
                            <fmt:message key="label.to.date" /> <input type="text" name="td" id="td" value="<c:out value="${requestScope['td']}" />" />
                            <br/>
                            <fmt:message key="label.site" /> <jsp:getProperty name="backendBean" property="siteSelector" />
                            <script type="text/javascript">
                                document.getElementsByName("text")[0].focus();
                                document.getElementsByName("site")[0].onchange = function() {
                                    document.getElementsByName("topic")[0].selectedIndex = 0;
                                    document.getElementById("searchFrm").submit();
                                }
                            </script>
                            <fmt:message key="label.per.page" /> <jsp:getProperty name="backendBean" property="pageSizeSelector" />
                            <br/>
                            <input type="submit" name="submitBtn" value="<fmt:message key="button.search"/>"/>
                        </form>
                    </td>
                    <td></td>
                </tr>
            </table>
        </div>
    <c:if test="${requestScope['debug'] == true}">
        <div id="debug">
            <pre>
                Query:          ${requestScope['searchResult'].query}
                isNewSearch:    ${requestScope['searchResult'].newSearch}
            </pre>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${empty requestScope['error']}">
            <c:if test="${not empty requestScope['lastMsgs']}">
                <div class="content">
                    <table cellspacing="5">
                        <tr><td><fmt:message key="label.last.saved.msg" /></td><td>${requestScope['lastMsgs'][0]}</td><td>(<fmt:formatDate value="${requestScope['lastMsgs_dates'][0]}" pattern="dd/MM/yyyy HH:mm" />)</td></tr>
                        <tr><td><fmt:message key="label.last.indexed.msg" /></td><td>${requestScope['lastMsgs'][1]}</td><td>(<fmt:formatDate value="${requestScope['lastMsgs_dates'][1]}" pattern="dd/MM/yyyy HH:mm" />)</td></tr>
                    </table>
                </div>
            </c:if>
            <c:if test="${not empty requestScope['searchResult']}">
                <div class="searchResOuter">
                <display:table name="requestScope.searchResult.paginatedList" id="msg" htmlId="resultTable"
                               decorator="info.xonix.zlo.web.decorators.SearchResultLineDecorator" requestURI="search"
                                class="searchRes">
                    <c:set var="site" value="${msg.site}" />

                    <display:setProperty name="basic.msg.empty_list"><span class="pagebanner">���������, ��������������� ��������� ��������� ������ �� �������. </span></display:setProperty>
                    <display:setProperty name="paging.banner.one_item_found"><span class="pagebanner">������� ���� ���������. </span></display:setProperty>
                    <display:setProperty name="paging.banner.all_items_found"><span class="pagebanner">������� ���������: {0}, �������� ���. </span></display:setProperty>
                    <display:setProperty name="paging.banner.some_items_found"><span class="pagebanner">������� ���������: {0}, �������� � {2} �� {3}. </span></display:setProperty>
                    <display:setProperty name="paging.banner.group_size" value="15" />
                    <display:setProperty name="paging.banner.onepage" value="" />
                    <display:setProperty name="paging.banner.placement" value="both" />
                    <display:setProperty name="paging.banner.full">
                        <span class="pagelinks">[<a href="{1}">����</a>/<a href="{2}">����</a>] {0} [<a href="{3}">����</a>/<a href="{4}">�������</a>]</span>
                    </display:setProperty>
                    <display:setProperty name="paging.banner.first">
                        <span class="pagelinks">[����/����] {0} [<a href="{3}">����</a>/<a href="{4}">�������</a>]</span>
                    </display:setProperty>
                    <display:setProperty name="paging.banner.last">
                        <span class="pagelinks">[<a href="{1}">����</a>/<a href="{2}">����</a>] {0} [����/�������]</span>
                    </display:setProperty>

                    <display:column title="�"
                                    class="small" headerClass="head"
                                    style="text-align:center;width:1%;">${msg.hitId + 1}</display:column>
                    <display:column title="����" headerClass="head" style="width:67%">
                        <a href="http://${site.SITE_URL}${site.READ_QUERY}${msg.num}">
                            <c:if test="${not empty msg.topic and msg.topic != '��� ����'}">[${msg.topic}]</c:if>
                            <jsp:setProperty name="hl" property="text" value="${msg.title}" />
                            <c:out value="${hl.highlightedText}" escapeXml="false" /></a>
                        <small>
                            <c:if test="${empty msg.body}">(-)</c:if>
                            <c:if test="${msg.hasUrl}">(url)</c:if>
                            <c:if test="${msg.hasImg}">(pic)</c:if>
                        </small>
                        <a class="search" href="msg?site=${msg.site.num}&num=${msg.num}<c:if test="${not empty hl.wordsStr}">&hw=${hl.wordsStr}</c:if>"><fmt:message key="link.saved.msg" /></a>
                    </display:column>
                    <display:column title="���" headerClass="head">
                        <tiles:insertDefinition name="nick">
                            <tiles:putAttribute name="reg" value="${msg.reg}" />
                            <tiles:putAttribute name="nick" value="${msg.nick}" />
                            <tiles:putAttribute name="site" value="${msg.site}" />
                        </tiles:insertDefinition>
                    </display:column>
                    <display:column title="����" class="small" headerClass="head">
                        <tiles:insertDefinition name="host">
                            <tiles:putAttribute name="host" value="${msg.host}" />
                            <tiles:putAttribute name="site" value="${msg.site}" />
                        </tiles:insertDefinition>
                    </display:column>
                    <display:column title="����" property="date"
                                    class="small nowrap" headerClass="head" />
                </display:table>
                </div>
            </c:if>
        </c:when>
        <c:otherwise>
            <div class="error">
                <c:out value="${requestScope['error']}" escapeXml="false" />
            </div>
        </c:otherwise>
    </c:choose>
    </body>
    <script type="text/javascript">
        changedDatesSelector();
    </script>
    <tiles:insertDefinition name="ga" />
</html>
