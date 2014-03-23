<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title.filter" /></title>
</head>

<body>
<a href="<c:url value="/user/index" />">
    <spring:message code="label.title.index" />
</a>

<h2><spring:message code="label.title.filter" /></h2>

<c:if test="${!empty listReport}">
    <table class="data">
        <tr>
            <th><spring:message code="label.reportfilter.id" /></th>
            <th><spring:message code="label.reportfilter.summ" /></th>
            <th><spring:message code="label.reportfilter.suspect" /></th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${listReport}" var="report">
            <tr>
                <td>${report.id}</td>
                <td>${report.summary}</td>
                <td>${report.suspectId}</td>
                <td><a href="/user/report/find/${report.id}"><spring:message code="label.reportfilter.detal" /></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>