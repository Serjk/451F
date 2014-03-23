<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <title><spring:message code="label.title.index" /></title>
    </head>

<body>
        <h2><spring:message code="label.title.index" /></h2>

        <a href="<c:url value="/logout" />">
            <spring:message code="label.login.logout" />
        </a>
        <br>
        <a href="<c:url value="/login.jsp" />">
            <spring:message code="label.login.submit" />
        </a>
        <br>
        <a href="<c:url value="/user/report/find" />">
            <spring:message code="label.title.find" />
        </a>
        <br>
        <a href="<c:url value="/user/reports/my" />">
            <spring:message code="label.title.myreports" />
        </a>
        <br>
        <a href="<c:url value="/user/report/tome" />">
            <spring:message code="label.title.reportstome" />
        </a>
        <br>
        <a href="<c:url value="/user/report/all" />">
            <spring:message code="label.title.reports" />
        </a>
        <br>
        <a href="<c:url value="/user/report/fireman/all" />">
            <spring:message code="label.title.fireman.all" />
        </a>
        <br>
        <a href="<c:url value="/user/report/fireman/assignedtome" />">
            <spring:message code="label.title.fireman.assignee" />
        </a>
        <br>
        <a href="<c:url value="/user/report/police/all" />">
            <spring:message code="label.title.policeman.all" />
        </a>
        <br>
        <a href="<c:url value="/user/report/police/assignedtome" />">
            <spring:message code="label.title.policeman.assignee" />
        </a>
        <br>
        <a href="<c:url value="/admin/user" />">
            <spring:message code="label.title.manageUser" />
        </a>
        <br>
        <a href="<c:url value="/user/report/news" />">
            <spring:message code="label.title.news" />
        </a>
    </body>
</html>