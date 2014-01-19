<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
        <title><spring:message code="label.indexTitle" /></title>
    </head>

<body>
        <h2><spring:message code="label.indexTitle" /></h2>

        <a href="<c:url value="/logout" />">
            <spring:message code="label.logout" />
        </a>
        <br>
        <a href="<c:url value="/login.jsp" />">
            <spring:message code="label.login" />
        </a>
        <br>
        <a href="<c:url value="/user/report/find" />">
            <spring:message code="label.report.toUser" />
        </a>
        <br>
        <a href="<c:url value="/user/myreports" />">
            <spring:message code="label.user.myreports" />
        </a>
        <br>
        <a href="<c:url value="/user/reportstome" />">
            <spring:message code="label.user.reportstome" />
        </a>
        <br>
        <a href="<c:url value="/user/news" />">
            <spring:message code="label.user.news" />
        </a>
        <br>
        <a href="<c:url value="/admin/user" />">
            <spring:message code="label.adminUser" />
        </a>
        <br>
        <a href="<c:url value="/admin/user" />">
            <spring:message code="label.adminUser" />
        </a>
    </body>
</html>