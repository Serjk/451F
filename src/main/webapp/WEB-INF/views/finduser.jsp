<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
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

<div id="page">
    <c:if test="${!empty loginUser}">
        <div id="block_menu">
            <a href="<c:url value="/user/report/find"/>" class="block_menu_button"> <spring:message code="label.title.find" /> </a>
            <a href="<c:url value="/user/news/"/>" class="block_menu_button"> <spring:message code="label.title.news"/></a>
            <a href="<c:url value="/user/report/archive"/>" class="block_menu_button"><spring:message code="label.title.arcive"/> </a>
            <c:if test="${loginUser.role=='ROLE_ADMIN'}">
                <a href="<c:url value="/admin/user"/>" class="block_menu_button"><spring:message code="label.title.manageUser"/></a>
            </c:if>
            <a href="<c:url value="/user/profile"/>" class="block_menu_button">Профиль</a>
        </div>
    </c:if>

    <div id="content">
        <div class="block" style="text-align: left;">
            <p class="page_title">Новый донос</p>
            <hr/>

            <form action="/user/report/new" method="post">
                <p> <spring:message code="label.find.firstname"/><input type="text" name="firstName" value="">
                    <spring:message code="label.find.lastname" /> <input type="text" name="lastName" value="">
                    <input type="submit" value="<spring:message code="label.find.find"/>"/>
                </p>
            </form>
            <div style="border-top: 1px dashed black; width: 95%; height: 0px; margin: 10px;"></div>

            <c:if test="${!empty listSuspectUser}">
                <p><spring:message code="label.find.users" /></p>
                <table border="1px" style="width: 700px" cellpadding="0" cellspacing="0">
                    <tr>
                        <th><spring:message code="label.find.number" /></th>
                        <th><spring:message code="label.find.firstname" /></th>
                        <th><spring:message code="label.find.lastname" /></th>
                        <th><spring:message code="label.find.address" /></th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:forEach items="${listSuspectUser}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.address}</td>
                            <td style="width: 1%"><div class="denun"  onClick="setFormValue('${user.id}','${user.firstName}','${user.lastName}','${user.address}')"></div></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

        </div>
    </div>

    <div id="footer">
        <div>
            <p>Компания разработчиков "Самые умные" 2013-2014 гг Version 0.1</p>
        </div>
    </div>

    <div id="blackblock"></div>
    <div id="confirm_denun">
        <p id="lastFirst"></p>
        <p>проживающего по адресу</p>
        <div id="address"></div>
        <form action="/user/report/add" method="post">
            <input type="hidden" id="myDiv" name="id" value="">
            <p><spring:message code="label.report.summary"/> <input type="text" name="summary" value=""> </p>
            <p><spring:message code="label.report.description"/> <textarea type="text" name="description" value=""cols="40" rows="5"></textarea> </p>
            <input type="submit" value="Отправить донос" />
            <input type="button" value="Отмена" id="cancel"/>
        </form>
    </div>

    <div id="confirm_denun2">
        <p id="thanks">Спасибо!<br> Ваша заявка будет рассмотрена.</p>
    </div>
</div>
</body>
<script>

    function setFormValue(id,first,last,addres){
        $("#blackblock").show();
        $("#confirm_denun").show();

        $("#myDiv").val(id);
        $("#lastFirst").text("Донос на гражданина"+" "+first+" "+last);
        $("#address").text(addres);
    }

</script>
</html>