<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtr HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtr">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title.detals" /></title>
</head>

<body>
<a href="<c:url value="/user/index" />">
    <spring:message code="label.title.index" />
</a>

<h2><spring:message code="label.title.detals" /></h2>

    <table class="data">
        <tr>
            <td><spring:message code="label.reportdetals.id"/></td>
            <td>${report.id}</td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.summ"/><td>
            <td>${report.summary}</td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.description"/></td>
            <td>${report.description}</td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.suspectId"/></td>
            <td>${report.suspectId}</td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.reporterId"/></td>
            <td>${report.reporterId}</td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.data"/></td>
            <td>${report.date}</td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.time"/></td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.assignee"/></td>
            <td>${report.assignee}</td>
        </tr>
        <tr>
            <td><spring:message code="label.reportdetals.step"/></td>
            <td>${report.step}</td>
        </tr>
        <tr>
            &nbsp;
            <td><a href="/"><spring:message code="label.user.delete" /></a></td>
        </tr>
    </table>
</body>
</html>