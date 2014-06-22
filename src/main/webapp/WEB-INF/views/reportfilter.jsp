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
    <link href="<c:url value="/resources/style/css/jquery-ui-1.9.2.custom.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/i18n/jquery-ui-i18n.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/>"></script>
    <script src="<c:url value="/resources/js/TextSelect.js" />"></script>
    <title><spring:message code="label.title.filter"/></title>
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
            <a href="<c:url value="/"/>" class="block_menu_button"> <spring:message code="label.title.news"/></a>
            <a href="<c:url value="/user/report/archive"/>" class="block_menu_button"><spring:message code="label.title.arcive"/> </a>
            <c:if test="${loginUser.role=='ROLE_ADMIN'}">
                <a href="<c:url value="/admin/user"/>" class="block_menu_button"><spring:message code="label.title.manageUser"/></a>
                <a href="<c:url value="/admin/workflow"/>" class="block_menu_button"><spring:message code="label.title.manageWorkFlow"/></a>
            </c:if>
        </div>
        </c:if>

<div id="content">
    <div class="block" style="text-align: left;">
        <div class="error">Не найдено ни одного запроса</div>
        <div class="warning">


            <c:if test="${loginUser.role=='ROLE_USER'}">
            <div>
                Быстрый запрос:
                <a href="#" class="transition_button" onclick="getSimpleReportList(3)">Созданные мной</a> &nbsp;
            </div>
            </c:if>

            <c:if test="${loginUser.role!='ROLE_USER'}">
            <div>
                Быстрый запрос:
                <a href="#" class="transition_button" onclick="getSimpleReportList(1)">Общий архив доносов</a>
                <a href="#" class="transition_button" onclick="getSimpleReportList(2)">Назначенные на меня</a> &nbsp;
                <a href="#" class="transition_button" onclick="getSimpleReportList(3)">Созданные мной</a> &nbsp;
                <a href="#" class="transition_button" onclick="getSimpleReportList(4)">Без исполнителя</a>
                <a href="#" class="transition_button" onclick="getSimpleReportList(5)">На обработке полиции</a> &nbsp;
                <a href="#" class="transition_button" onclick="getSimpleReportList(6)">На обработке пожарных</a>


            </div>

            <div>
                Выборка по статусу:
                <c:if test="${!empty listStep}">
                    <select id = "step_select">
                        <c:forEach items="${listStep}" var="step">
                            <option value=${step.id}>${step.stepName}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <a href="#" class="transition_button" onclick="getSimpleReportList(8)">Выбрать</a> &nbsp;
            </div>

            <div>
                Выборка по дате. Начало: <input id = "datepicker1" class="datepicker" type="text" />
                Окончание: <input id = "datepicker2" class="datepicker" type="text" /> &nbsp;
                <a href="#" class="transition_button"onclick="getSimpleReportList(7)">Выбрать</a>
            </div>
                </c:if>



        </div>
            <p id ="res_hed" >Результат запроса:</p>
            <table id ="filter_result" class="paginated">
                <thead>
                    <tr>
                        <th><spring:message code="label.reportfilter.id" /></th>
                        <th><spring:message code="label.reportfilter.summ" /></th>
                        <th><spring:message code="label.reportfilter.suspect" /></th>
                        <th><spring:message code="label.reportfilter.policeman" /></th>
                        <th><spring:message code="label.reportfilter.fireman" /></th>
                        <th><spring:message code="label.reportfilter.step" /></th>
                        <th>Дата доноса</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody class="tbody">
                </tbody>
            </table>
            <div class="pager"></div>
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
        getSimpleReportList();

        $(function(){
            $.datepicker.setDefaults(
                    $.extend($.datepicker.regional["ru"])
            );


            $(".datepicker").datepicker();
        });

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

    function setPagination(){
        $('table.paginated').each(function() {
            var currentPage = 0;
            var numPerPage = 5;
            var $table = $(this);
            $table.bind('repaginate', function() {
                $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
            });
            $table.trigger('repaginate');
            var numRows = $table.find('tbody tr').length;
            var numPages = Math.ceil(numRows / numPerPage);
            $('.pager').empty();
            var $pager = $('<div class="pager"></div>');

            for (var page = 0; page < numPages; page++) {
                $('<span class="page-number"></span>').text(page + 1).bind('click', {
                    newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                }).appendTo($pager).addClass('clickable');
            }
            $pager.insertAfter($table).find('span.page-number:first').addClass('active');
        });
    }

    function getSimpleReportList(id){
        var url;

        if (id==1){
            url = "/user/rest/report/archive";
            $('#res_hed').text("Общий архив доносов:");
        }
        else if(id==2){
            url = "/user/rest/report/assignee/currentuser ";
            $('#res_hed').text("Назначенные на меня:");
        }
        else if (id==3){
            url = "/user/rest/report/my";
            $('#res_hed').text("Созданные мной:");
        }
        else if (id==4){
            url = "/user/rest/report/assignee/empty";
            $('#res_hed').text("Без исполнителя:");
        }
        else if (id==5){
            url = "/user/rest/report/assignee/policeman";
            $('#res_hed').text("На обработке полиции:");
        }
        else if (id==6){
            url = "/user/rest/report/assignee/fireman";
            $('#res_hed').text("На обработке пожарных:");
        }

        else if (id==7){

            if (!$('#datepicker1').datepicker("getDate") ||
                    !$('#datepicker2').datepicker("getDate")){
                alert("Введите дату начала и дату конца");
            }
            else {
                var start = $('#datepicker1').datepicker("getDate").getTime();
                var end   = $('#datepicker2').datepicker("getDate").getTime();
                console.log( start +" "+ end);
                url = "/user/rest/report/date/"+start +"/"+end ;
                $('#res_hed').text("Выборка по дате:");
            }
        }
        else if (id==8){
            var stepid = $('#step_select option:selected').val();
            console.log(stepid);
            url = "/user/rest/report/step/" + stepid;
            $('#res_hed').text("Выборка по шагу:");
        }
        else{
            url = "/user/rest/report/my";
            $('#res_hed').text("Созданные мной:");
        }

        $.ajax({
            url: url,  // указываем URL и
            dataType : "json",
            async : false,
            success: function (data, textStatus) { // вешаем свой обработчик на функцию success

                if (!$.isEmptyObject(data)){
                    $('#filter_result').show();
                    $('#res_hed').show();
                    $('.error').hide();
                    $('.pager').show();
                    $("#filter_result").find('tbody').empty();
                    $.each(data, function(i, val) {    // обрабатываем полученные данные

                        var date = new Date(val.date);
                        var curr_date = date.getDate();
                        var curr_month = date.getMonth() + 1; //Months are zero based
                        var curr_year = date.getFullYear();

                        $("#filter_result").find('tbody')
                            .append($('<tr>')
                                .append(
                                    $('<td>').text(val.id),
                                    $('<td>').text(val.summary),
                                    $('<td>').text(getUserName(val.suspectDisplayName)),
                                    $('<td>').text(getUserName(val.policemanDisplayName)),
                                    $('<td>').text(getUserName(val.firemanDisplayName)),
                                    $('<td>').text(val.stepName),
                                    $('<td>').text(curr_date + "-" + curr_month + "-" + curr_year),
                                    $('<td>')
                                            .append(
                                                $('<a>').text("Подробно...").attr('href',"/user/report/find/"+val.id)
                                            )
                                )
                            );
                    });
                    setPagination();
                }
                else {
                    $('#filter_result').hide();
                    $('#res_hed').hide();
                    $('.error').show();
                    $('.pager').hide();
                }
            }
        });

        SetSize();
    }

    function getUserName(user){
        if(user)
            return user
        else
            return "Не установлен"
    }

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