<%@ page contentType="text/html; charset=windows-1251" %>

<h3>XML Forum Protocol</h3>

<jsp:useBean id="backendBean" class="info.xonix.zlo.web.BackendBean" scope="request" />

<style type="text/css">
    .form { background: #eee; width: 300px; }
</style>

<form action="xmlfp.jsp" method="get" class="form">
    ����: <jsp:getProperty name="backendBean" property="siteSelector" /><br />
    ����� ���������: <input type="text" value="${param['num']}" name="num" /><br />

    <input type="submit" value="�������� XML" />
</form>

<form action="xmlfp.jsp" method="get" class="form">
    <input type="hidden" name="lastMessageNumber" value="true" />
    ����: <jsp:getProperty name="backendBean" property="siteSelector" /><br />
    <input type="submit" value="�������� ����� ���������� ���������" />
</form>

<h3>XML Schemas</h3>
<ul>
    <li><a href="xsd/message.xsd">message.xsd</a></li>
    <li><a href="xsd/author.xsd">author.xsd</a></li>
    <li><a href="xsd/lastMessageNumber.xsd">lastMessageNumber.xsd</a></li>
</ul>