<%--
  User: gubarkov
  Date: 30.08.2007
  Time: 21:10:26
--%>
<%@ include file="../import.jsp"%>
<%@ page contentType="text/html; charset=windows-1251" %>

<c:out value="${requestScope['aaa']}" />

<c:out value="${'������ ���'}" />  <br />
<c:out value="${'��� ����' == '��� ����'}" />

<c:set var="a" value="${'��� ����'}" />
<c:out value="${a}   ${a == '��� ����'}   ${a != '��� ����'}" /> 