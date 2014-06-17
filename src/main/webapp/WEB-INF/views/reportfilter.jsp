<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="shortcut icon" href="<c:url value="/resources/img/flame.ico"/>">
    <link href="<c:url value="/resources/style/css/site.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/TextSelect.js" />"></script>
    <title><spring:message code="label.title.detals"/> ${report.id}</title>
</head>

<body>

<div id="header">
    <div style="float: left;">
        <a class="header_logo" href="index.html">
            <div style="display: table-cell;"><img src="/resources/img/flame.ico" width="50px" height="50px"/></div>
            <div style="display: table-cell; vertical-align: middle; padding-left: 10px"><p>Главная</p></div>
        </a>
    </div>

    <div></div>
    <div style="float: right;">
        <div style="display: table-cell;">
            <c:if test="${empty loginUser}">
                <div class="header_button" id="login_button">
                    <p><spring:message code="label.header.login"/></p>
                </div>
            </c:if>
            <div class="login-form">
                <form action="<c:url value='j_spring_security_check' />" method='POST'>
                    <div class="inputs-block">
                        <input type="text" name="j_username" placeholder="Email"/>
                        <input type="password" name="j_password" placeholder="Пароль"/>
                        <div>
                            <input type="submit" value="Войти" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <c:if test="${!empty loginUser}">
            <div style="display: table-cell;">
                <a href="<c:url value="/logout" />" class="header_button"><spring:message code="label.login.logout"/></a>
            </div>
        </c:if>
        <div style="display: table-cell;">
            <c:if test="${empty loginUser}">
                <div class="header_button" id="reg_button">
                    <p><spring:message code="label.header.register"/></p>
                </div>
            </c:if>
            <div class="reg-form">
                <form>
                    <div class="inputs-block">
                        <input class="login_input" name="name" type="name" placeholder="Имя">
                        <input class="login_input" name="email" type="email" placeholder="Email">
                        <input class="login_input" name="password" type="password" placeholder="Пароль">
                        <input class="login_input" name="password_check" type="password" placeholder="Повторите пароль">

                        <div>
                            <input class="login_submit" type="submit" value="Зарегистрироваться">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


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

<div id="footer">
    <div>
        <p><spring:message code="label.footer"/></p>
    </div>
</div>

</body>
<script>
    $(document).ready(function () {
        $("#blackblock").hide();
        $("#assign_police_denun").hide();
        $("#assign_fireman_denun").hide();
        $("#validate_workflow_denun").hide();
        $("#step_denun").hide();

        SetSize();
        $(".login-form").hide();
        $("#login_button").click(function () {
            $(".login-form").toggle();
            $(".reg-form").hide();
        });
        $(".reg-form").hide();
        $("#reg_button").click(function () {
            $(".reg-form").toggle();
            $(".login-form").hide();
        });
    })


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