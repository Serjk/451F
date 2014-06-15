<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.workflow.manageWotkflow" /></title>
</head>

<body>
<a href="<c:url value="/user/index" />">
    <spring:message code="label.title.index" />
</a>


<h2><spring:message code="label.title.manageStep" /></h2>

<form:form method="post" action="/admin/workflow/step/new" commandName="step">

    <table>
        <tr>
            <td>
                <spring:message code="label.step.name" />
            </td>
            <td><form:input path="stepName" /></td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.step.summary" />
            </td>
            <td><form:input path="stepSummary" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="<spring:message code="label.step.add"/>" /></td>
        </tr>
    </table>
</form:form>

<c:if test="${!empty listStep}">
    <h3><spring:message code="label.step.steps" /></h3>
    <table class="data">
        <tr>
            <th><spring:message code="label.step.id" /></th>
            <th><spring:message code="label.step.name" /></th>
            <th><spring:message code="label.step.summary" /></th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${listStep}" var="step">
            <tr>
                <td>${step.id}</td>
                <td>${step.stepName}</td>
                <td>${step.stepSummary}</td>
                <td><a href="/admin/step/delete/${step.id}"><spring:message code="label.step.remove" /></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h2><spring:message code="label.title.manageTransition" /></h2>

<form:form method="post" action="/admin/workflow/transition/new" commandName="transition">

    <table>
        <tr>
            <td>
                <spring:message code="label.transition.name" />
            </td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.transition.stepIn" />
            </td>
            <td><form:input path="stepIn" /></td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.transition.stepOut" />
            </td>
            <td><form:input path="stepOut" /></td>
        </tr>
        <tr>
            <td>
                <spring:message code="label.transition.permission" />
            </td>
            <td>
                <form:select path="permission">
                    <form:options items="${permission}" />;
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="<spring:message code="label.transition.addtransition"/>" /></td>
        </tr>
    </table>
</form:form>

<c:if test="${!empty listTransition}">
    <h3><spring:message code="label.transition.transition" /></h3>
    <table class="data">
        <tr>
            <th><spring:message code="label.transition.id" /></th>
            <th><spring:message code="label.transition.name" /></th>
            <th><spring:message code="label.transition.stepIn" /></th>
            <th><spring:message code="label.transition.stepOut" /></th>
            <th><spring:message code="label.transition.permission" /></th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${listTransition}" var="transition">
            <tr>
                <td>${transition.id}</td>
                <td>${transition.name}</td>
                <td>${transition.stepIn}</td>
                <td>${transition.stepOut}</td>
                <td>${transition.permission}</td>
                <td><a href="/admin/workflow/transition/delete/${transition.id}"><spring:message code="label.user.delete" /></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>