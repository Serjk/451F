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
                    <c:if test="${!empty transitions}">
                        <tr>
                        <c:forEach items="${transitions}" var="transition">
                            <a href="#" class="transition_button" onclick="validateWorkFlowTransition(${report.id},${transition.stepOut})" >${transition.name}</a></td>  &nbsp;
                        </c:forEach>
                        </tr>
                    </c:if>
                    <tr>
                        <td><spring:message code="label.reportdetals.id"/></td>
                        <td>${report.id}</td>
                    </tr>
                    <tr>
                        <td>Ошибки</td>
                        <td></td>
                    </tr>
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
                    <tr>
                        <td><a href="/"><spring:message code="label.user.delete" /></a></td>

                    </tr>
                </table>
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
</body>

<script>
    $(document).ready(function () {
        $("#blackblock").hide();
        $("#assign_police_denun").hide();
        $("#assign_fireman_denun").hide();
        $("#validate_workflow_denun").hide();
        $("#step_denun").hide();
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
</script>
</html>