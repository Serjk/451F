<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/1html; charset=utf8">
    <title><spring:message code="label.report.title" /></title>
</head>
<body>

<h2><spring:message code="label.report.title" /></h2>

<form:form method="post" action="/user/report/add" commandName="report">
    <table>
        <tr>
            <td>
                <spring:message code="label.report.summary" />
            </td>
            <td><form:input path="summary" /></td>
        </tr>

        <tr>
            <td>
                <spring:message code="label.report.description" />
            </td>
            <td><form:input path="description" /></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"
                                value="<spring:message code="label.report.toUser"/>" /></td>
        </tr>
    </table>

</form:form>

</body>
</html>