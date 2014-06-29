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

                <div class="inputs-block">
                    <input type="text" id="j_login" placeholder="Логин"/>
                    <input type="password" id="j_password" placeholder="Пароль"/>
                    <div>
                        <input type="submit" value="Войти" onclick="loginUser()"/>
                    </div>
                    <p id="login_error"> </p>
                </div>
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

                <div class="inputs-block">
                    <input class="reg_input" id="input_firstName" type="name" placeholder="Имя">
                    <input class="reg_input" id="input_lastName" type="name" placeholder="Фамилия">
                    <input class="reg_input" id="input_address" type="name" placeholder="Адрес">
                    <input class="reg_input" id="input_login" type="name" placeholder="Логин">
                    <input class="reg_input" id="input_password1" type="password" placeholder="Пароль">
                    <input class="reg_input" id="input_password2" type="password" placeholder="Повторите пароль">
                    <div>
                        <a href="#" class="transition_button" onclick="addUser()">Зарегистрироваться</a>
                    </div>
                    <div id="reg_result" ></div>
                </div>

            </div>
        </div>
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
        <p><spring:message code="label.footer"/></p>
    </div>
</div>

</body>

<script>

    function loginUser() {
        var login = $("#j_login").val();
        var password = $("#j_password").val();

        var dataIn = "j_username=" + login + "&j_password=" + password;

        console.log(dataIn);
        if (login == "" || password == "") {
            $("#login_error").text("Заполните поля")
        }

        else {
            $.ajax({
                url: "j_spring_security_check",  // указываем URL и

                async: false,
                type: "POST",
                data: dataIn,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("X-Ajax-call", "true");
                },
                success: function(result) {
                    if(result=="UserDetailsService returned null, which is an interface contract violation"){
                        $("#login_error").text("Пользователь не найден в системе")
                    }

                    else if(result=="Bad credentials"){
                        $("#login_error").text("Неверный пароль")
                    }
                    else(location.reload());
                }
            });
        }

    }

    function addUser() {

        var firstName = $("#input_firstName").val();
        var lastName = $("#input_lastName").val();
        var login = $("#input_login").val();
        var address = $("#input_address").val();
        var password1 = $("#input_password1").val();
        var password2 = $("#input_password2").val();

        var dataIn = "firstName=" + firstName + "&lastName=" + lastName +
                "&login=" + login + "&address=" + address +
                "&password1=" + password1 + "&password2=" + password2;

        console.log(firstName);
        if (firstName == "" || lastName == "" || login == "" ||
                address == "" || password1 == "" || password2 == "") {
            $("#reg_result").text("Заполните все поля")
        }
        else {

            $.ajax({
                url: "/rest/admin/user/add",  // указываем URL и
                dataType: "json",
                async: false,
                type: "POST",
                data: dataIn,
                success: function (data, textStatus) { // вешаем свой обработчик на функцию success
                    if (!$.isEmptyObject(data)) {
                        console.log(data);
                        $("#reg_result").text(data.message);
                        if (data.errorCode == "rest.createuser.success") {
                            $(".reg_input").val("");
                        }
                    }
                }
            });
        }
    }
</script>
</html>