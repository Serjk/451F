<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtr HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtr">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="shortcut icon" href="<c:url value="/resources/img/flame.ico"/>">
    <link href="<c:url value="/resources/style/css/site.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/TextSelect.js"/>"></script>
    <title>Профиль пользователя</title>
</head>

<body>
<div id="header">
    <div style="float: left;">
        <a class="header_logo" href="/">
            <div style="display: table-cell;"><img src="/resources/img/flame.ico" width="50px" height="50px"/></div>
            <div style="display: table-cell; vertical-align: middle; padding-left: 10px"><p>Главная</p></div>
        </a>
    </div>

    <div></div>
    <div style="float: right;">
        <c:if test="${!empty loginUser}">
            <div style="display: table-cell;">
                <a href="<c:url value="/logout" />" class="header_button"><spring:message code="label.login.logout"/></a>
            </div>
        </c:if>
    </div>
</div>

<div id="page">
    <c:if test="${!empty loginUser}">
        <div id="block_menu">
            <a href="<c:url value="/user/report/find"/>" class="block_menu_button"> <spring:message code="label.title.find" /> </a>
            <a href="<c:url value="/user/news"/>" class="block_menu_button"> <spring:message code="label.title.news"/></a>
            <a href="<c:url value="/user/report/archive"/>" class="block_menu_button"><spring:message code="label.title.arcive"/> </a>
            <c:if test="${loginUser.role=='ROLE_OFFICIAL'}">
                <a href="<c:url value="/admin/user"/>" class="block_menu_button"><spring:message code="label.title.manageUser"/></a>
                <a href="<c:url value="/admin/wage"/>" class="block_menu_button">Ставки</a>
                <a href="<c:url value="/admin/bank"/>" class="block_menu_button">Бюджет</a>
                <a href="<c:url value="/admin/payment"/>" class="block_menu_button">Расходы</a>
            </c:if>
            <a href="<c:url value="/user/profile"/>" class="block_menu_button">Профиль</a>

        </div>
    </c:if>

    <div id="content">
        <div class="block" style="text-align: left;">
            <p class="page_title">Профиль пользователя </p>
            <table style="width: auto" cellpadding="0" cellspacing="0">
                <tr>
                    <td>Имя</td>
                    <td>${loginUser.firstName}</td>
                </tr>
                <tr>
                    <td>Фамилия</td>
                    <td>${loginUser.lastName}</td>
                </tr>

                <tr>
                    <td>Логин</td>
                    <td>${loginUser.lastName}</td>
                </tr>

                <tr>
                    <td>Адрес</td>
                    <td>${loginUser.address}</td>
                </tr>

                <tr>
                    <td>Роль</td>
                    <td>${role.ruName}</td>
                </tr>
            </table>

        </div>
    </div>
</div>

<div id="footer">
    <div>
        <p><spring:message code="label.footer"/></p>
    </div>
</div>

</body>

<script>

    $(document).ready(function () {
        SetSize();
    });


    $(window).resize(function () {
        SetSize();
    });

    function SetSize() {
        var content = ($("#content").height() + $("#header").height() + $("#footer").height() + $("#block_menu").height());
        var body = $('body').height();
        if (content > body) {
            $("#footer").css({"position": "relative"});
            var pageHeight = $("#content").height() + $("#block_menu").height();
            $("#page").height(pageHeight);
        } else {
            $("#footer").css({"position": "absolute"});
            var pageHeight = $("#footer").position().top - $("#header").height();
            $("#page").height(pageHeight);
        }
    }

</script>
</html>