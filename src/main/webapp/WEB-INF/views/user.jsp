<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.title.manageUser" /></title>
</head>

<body>
    <a href="<c:url value="/user/index" />">
        <spring:message code="label.title.index" />
    </a>
  
    <h2><spring:message code="label.title.manageUser" /></h2>

<form:form method="post" action="/admin/user/add" commandName="user">

	<table>
		<tr>
			<td>
				<spring:message code="label.user.firstname" />
			</td>
			<td><form:input path="firstName" /></td>
		</tr>
		<tr>
			<td>
				<spring:message code="label.user.lastname" />
			</td>
			<td><form:input path="lastName" /></td>
		</tr>
		<tr>
			<td>
				<spring:message code="label.user.login" />
			</td>
			<td><form:input path="login" /></td>
		</tr>
		<tr>
			<td>
				<spring:message code="label.user.address" />
			</td>
			<td><form:input path="address" /></td>
		</tr>
		<tr>
            <td>
                <spring:message code="label.user.password" />
            </td>
            <td><form:input type="password" path="password" /></td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.user.role" />
            </td>
            <td>
                 <form:select path="role">
                     <form:options items="${roles}" />;
                 </form:select>
             </td>
        </tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.user.adduser"/>" /></td>
		</tr>
	</table>
</form:form>

<c:if test="${!empty listUser}">
    <h3><spring:message code="label.user.users" /></h3>
	<table class="data">
		<tr>
			<th><spring:message code="label.user.firstname" /></th>
			<th><spring:message code="label.user.login" /></th>
			<th><spring:message code="label.user.role" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${listUser}" var="user">
			<tr>
				<td>${user.lastName}, ${user.firstName}</td>
				<td>${user.login}</td>
				<td>${user.role}</td>
				<td><a href="/admin/user/delete/${user.id}"><spring:message code="label.user.delete" /></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>