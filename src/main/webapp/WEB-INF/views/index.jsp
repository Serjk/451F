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
        <title><spring:message code="label.title.find" /></title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/flame.ico"/>">
        <link href="<c:url value="/resources/style/css/site.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/js/TextSelect.js" />"></script>
        <script src="<c:url value="/resources/js/DivHider.js" />"></script>
    </head>

<body>
<div id="header">
    <div style="float: left;">
        <a class="header_logo" href="/">
            <div style="display: table-cell;"><img src="/resources/img/flame.ico" width="50px" height="50px"/></div>
            <div style="display: table-cell; vertical-align: middle; padding-left: 10px"><p>Главная</p></div>
        </a>
    </div>
    <div style="float: right;">
        <div style="display: table-cell;">
            <div class="header_button" id="login_button">
                <p>Вход</p>
            </div>
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
        <div style="display: table-cell;">
            <a href="<c:url value="/logout" />" class="header_button"><spring:message code="label.login.logout" /> </a>
        </div>
        <div style="display: table-cell;">
            <div class="header_button" id="reg_button">
                <p>Регистрация</p>
            </div>
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
<div id="page">

    <div id="block_menu">
        <a href="<c:url value="/user/report/all" />" class="block_menu_button"><spring:message code="label.title.reports" /></a>
        <a href="<c:url value="/user/report/find" />" class="block_menu_button"> <spring:message code="label.title.find" /> </a>
        <a href="<c:url value="/user/report/my" />" class="block_menu_button"><spring:message code="label.title.myreports"/> </a>
        <a href="<c:url value="/user/report/tome" />" class="block_menu_button"><spring:message code="label.title.reportstome"/> </a>
        <a href="<c:url value="/admin/user"  />" class="block_menu_button"><spring:message code="label.title.manageUser"/></a>
        <a href="<c:url value="/"/>" class="block_menu_button"/> <spring:message code="label.title.news"/></a>
    </div>
    <div id="content">
        <div class="block">
            <table border="0px" cellspacing="10" style="width: 100%; margin-top: 20px" >
                <tr>
                    <td style="vertical-align: bottom; background: url('/resources/img/book_left.jpg'); background-size: cover;">
                    </td>
                    <td style="width: 1%; vertical-align: bottom; ">
                        <div style="font-family: of;">
                            <p> Официальный государственный</p>
                            <p> интернет сервис </p>
                            <p>по контролю запрещенного</p>
                            <p>книгооборота в городе N.</p>
                        </div>
                        <div style="margin-top: 50px;">
                            <img src="/resources//img/book.jpg"/>
                        </div>
                    </td>
                    <td style="vertical-align: bottom; background: url('/resources/img/book_left.jpg'); background-size: cover;">
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div id="footer">
    <div>
        <p>Компания разработчиков "Самые умные" 2013-2014 гг Version 0.1</p>
    </div>
</div>
</body>
</html>