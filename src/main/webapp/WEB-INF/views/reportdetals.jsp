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
                        <p>Вход</p>
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
                        <p>Регистрация</p>
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
        <div id="block_menu">
            <a href="<c:url value="/user/report/all" />" class="block_menu_button"><spring:message code="label.title.reports" /></a>
            <a href="<c:url value="/user/report/find" />" class="block_menu_button"> <spring:message code="label.title.find" /> </a>
            <a href="<c:url value="/user/report/my" />" class="block_menu_button"><spring:message code="label.title.myreports"/> </a>
            <a href="<c:url value="/user/report/tome" />" class="block_menu_button"><spring:message code="label.title.reportstome"/> </a>
            <a href="<c:url value="/admin/user"  />" class="block_menu_button"><spring:message code="label.title.manageUser"/></a>
            <a href="<c:url value="/"/>" class="block_menu_button"> <spring:message code="label.title.news"/></a>
        </div>

        <div id="content">
            <div class="block" style="text-align: left;">
                <p class="page_title">Подробности доноса №${report.id}</p>

                <div id="buttons_menu">
                    <c:if test="${!empty transitions}">
                        <tr>
                            <c:forEach items="${transitions}" var="transition">
                                <a href="#" class="transition_button" onclick="validateWorkFlowTransition(${report.id},${transition.stepOut})" >${transition.name}</a></td>  &nbsp;
                            </c:forEach>
                        </tr>
                    </c:if>
                </div>

                <table table border="1px" style="width: auto" cellpadding="0" cellspacing="0">

                    <tr>
                        <td><spring:message code="label.reportdetals.summ"/></td>
                        <td>${report.summary}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.description"/></td>
                        <td>${report.description}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.suspectId"/></td>
                        <td>  ${suspect.firstName} ${suspect.lastName} </td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.address"/></td>
                        <td>${suspect.address}"</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.reporterId"/></td>
                        <td>${reporter.firstName} ${reporter.lastName}</td>
                    </tr>

                    <tr>
                        <td><spring:message code="label.reportdetals.data"/></td>
                        <td><fmt:formatDate value="${report.date}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.time"/></td>
                        <td><fmt:formatDate value="${report.date}" pattern="HH:mm:ss"/></td>
                    </tr>
                    <c:if test="${loginUser.role =='ROLE_POLICE'}">
                        <tr>
                            <td><spring:message code="label.reportdetals.policeman"/></td>
                            <c:if test="${!empty policeman}">
                                <td><a href="#" onClick="assignReportToPoliceman()">${policeman.firstName} ${policeman.lastName}</a></td>
                            </c:if>
                            <c:if test="${empty policeman}">
                                <td><a href="#" onClick="assignReportToPoliceman()">Назначить полицейского</a></td>
                            </c:if>
                        </tr>
                    </c:if>
                    <c:if test="${loginUser.role =='ROLE_FIREMAN'}">
                        <tr>
                            <td><spring:message code="label.reportdetals.fireman"/></td>
                            <c:if test="${!empty fireman}">
                                <td><a href="#" onClick="assignReportToFireman()">${fireman.firstName} ${fireman.lastName}</a></td>
                            </c:if>
                            <c:if test="${empty fireman}">
                                <td><a href="#" onClick="assignReportToFireman()">Назначить пожарного</a></td>
                            </c:if>
                        </tr>
                    </c:if>

                    <tr>
                        <td><spring:message code="label.reportdetals.step"/></td>
                        <td>${step.stepName}</td>
                    </tr>
                </table>
                <div id="buttons_menu">
                    <a href="/" class="transition_button"><spring:message code="label.user.delete" /></a>
                </div>
            </div>
        </div>


        <div id="blackblock"></div>

        <div id="assign_police_denun">
            <p>Список полицейских:</p>
            <div class="box">
                <c:if test="${!empty policemanAssigners}">
                    <c:forEach items="${policemanAssigners}" var="policemanAssignee">
                        <div>
                            <a href="/user/report/assignee/policeman/${report.id}/${policemanAssignee.id}">${policemanAssignee.firstName} ${policemanAssignee.firstName}</a>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div id="assign_fireman_denun">
            <p>Список пожарных:</p>
            <div class="box">
                <c:if test="${!empty firemanAssigners}">
                    <c:forEach items="${firemanAssigners}" var="firemanAssignee">
                        <div>
                            <a href="/user/report/assignee/fireman/${report.id}/${firemanAssignee.id}">${firemanAssignee.firstName} ${firemanAssignee.firstName}</a>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div id="validate_workflow_denun">
            <p>В ходе работы возникла следующая ошибка:</p>
            <div id="errorbox"></div>
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

    $("#blackblock").click(function () {
        $("#blackblock").hide();
        $("#assign_police_denun").hide();
        $("#assign_fireman_denun").hide();
        $("#validate_workflow_denun").hide();
        $("#step_denun").hide();
    });

    function assignReportToPoliceman(){
        $("#blackblock").show();
        $("#assign_police_denun").show();
    }

    function assignReportToFireman(){
        $("#blackblock").show();
        $("#assign_fireman_denun").show();
    }
    function validateWorkFlowTransition(reportId, transitionStepOut){
        var errorCodeArray = ["label.workflow.validation.firemanid.empty","label.workflow.validation.policeman.empty"];
        $('#errorbox').empty();
        $.ajax({
            url: "/user/report/step/"+reportId+"/"+transitionStepOut,             // указываем URL и
            ataType : "json",                      // тип загружаемых данных
            success: function (data, textStatus) { // вешаем свой обработчик на функцию success
                console.log(data.errorCode);
                console.log(data.message);
                if($.inArray(data.errorCode, errorCodeArray)!=-1){
                    console.log(data.errorCode);
                    console.log("нашли вхождение");
                    $("<p>").text(data.message).appendTo('#errorbox');
                    $("#blackblock").show();
                    $("#validate_workflow_denun").show();
                }
                else if (data.errorCode=="label.workflow.validation.ok"){
                    console.log("Нет ошибок");
                    location.reload();
                }
            }
        });
    }

    $(".header_button").disableSelection();
    $(".header_logo").disableSelection();
    $(".block_menu_button").disableSelection();


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