<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.findTitle" /></title>
</head>
<body>

<h2><spring:message code="label.findTitle" /></h2>

<form action="/user/report/new" method="post">
    <table>

        <tr>
            <td>
                <spring:message code="label.firstname" />
            </td>
            <td><input type="text" name="firstName" value=""></td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.lastname" />
            </td>
            <td><input type="text" name="lastName" value=""></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="<spring:message code="label.find"/>"/></td>
        </tr>
    </table>
</form>


<c:if test="${!empty listSuspectUser}">
    <h3><spring:message code="label.users" /></h3>
    <table class="data">
        <tr>
            <th><spring:message code="label.firstname" /></th>
            <th><spring:message code="label.address" /></th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${listSuspectUser}" var="user">
            <tr>
                <td>${user.lastName}, ${user.firstName}</td>
                <td>${user.address}</td>
                <td><a href="/user/report/add/${user.id}"><spring:message code="label.report.to" /></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>