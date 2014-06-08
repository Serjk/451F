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
    <title><spring:message code="label.title.find" /></title>
    <link rel="shortcut icon" href="<c:url value="/resources/img/flame.ico"/>">
    <link href="<c:url value="/resources/style/css/site.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
    <title><spring:message code="label.title.detals" /></title>

</head>

<body>
    <div id="page">
        <a href="<c:url value="/user/index" />">
            <spring:message code="label.title.index" />
        </a>
        <h2><spring:message code="label.title.detals" /></h2>
        <div id="content">
            <div class="block" style="text-align: left;">
                <table class="data">
                    <tr>
                        <td><spring:message code="label.reportdetals.id"/></td>
                        <td>${report.id}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.summ"/><td>
                        <td>${report.summary}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.description"/></td>
                        <td>${report.description}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.suspectId"/></td>
                        <td>${report.suspectId}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.reporterId"/></td>
                        <td>${report.reporterId}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.data"/></td>
                        <td><fmt:formatDate value="${report.date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.time"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.assignee"/></td>
                        <td>${report.assignee}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.reportdetals.step"/></td>
                        <td>${report.stepId}</td>
                    </tr>
                    <tr>
                        &nbsp;
                        <td><a href="/"><spring:message code="label.user.delete" /></a></td>

                        <td><div><a href="#" onClick="setFormValue(${report.id})">Назначить исполнителя</a></div></td>

                        <td><div><a href="#" onClick="setStepFormValue(${report.id})">Выбрать шаг</a></div></td>
                    </tr>
                </table>
            </div>
        </div>

        <div id="blackblock"></div>

        <div id="assign_denun">
            <p>Список исполнителей:</p>
            <div class="box"></div>
        </div>

        <div id="step_denun">
            <p>Список шагов:</p>
            <div class="stepbox"></div>
        </div>
    </div>
</body>
<script>

    $(document).ready(function () {
        $("#blackblock").hide();
        $("#assign_denun").hide();
        $("#step_denun").hide();
    })

    $("#blackblock").click(function () {
        $("#blackblock").hide();
        $("#assign_denun").hide();
        $("#step_denun").hide();
    });

    function setFormValue(reportId){

        $('.box').empty();

        $.ajax({
            url: '/user/report/users',             // указываем URL и
            dataType : "json",                     // тип загружаемых данных
            success: function (data, textStatus) { // вешаем свой обработчик на функцию success
                $.each(data, function(i, val) {    // обрабатываем полученные данные
                    console.log(val.firstName);
                    $('<div class=' + 'newbox'+val.id+'>').appendTo('.box');
                    $('<a href='+'/user/report/assignee/'+reportId+'/'+val.id+'>').text(val.firstName+" "+val.lastName).appendTo('.newbox'+val.id);
                });
            }
        });

        $("#blackblock").show();
        $("#assign_denun").show();
    }


    function setStepFormValue(reportId){

        $('.stepbox').empty();

        $.ajax({
            url: '/user/report/steps',             // указываем URL и
            dataType : "json",                     // тип загружаемых данных
            success: function (data, textStatus) { // вешаем свой обработчик на функцию success
                $.each(data, function(i, val) {    // обрабатываем полученные данные
                    console.log(val.stepName);
                    $('<div class=' + 'newstepbox'+val.id+'>').appendTo('.stepbox');
                    $('<a href='+'/user/report/step/'+reportId+'/'+val.id+'>').text(val.stepName).appendTo('.newstepbox'+val.id);
                });
            }
        });

        $("#blackblock").show();
        $("#step_denun").show();
    }
</script>
</html>