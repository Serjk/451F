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
    <script src="<c:url value="/resources/js/TextSelect.js"/>"></script>
    <title><spring:message code="label.title.detals"/> ${report.id}</title>
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
                </c:if>
            </div>
        </c:if>

        <div id="content" style="text-align: left;">
            <div class="block">

                <h3><spring:message code="label.user.users" /></h3>

                    <table id ="filter_result"  style="font-size:16px;" class="paginated">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th><spring:message code="label.user.firstname" /></th>
                            <th><spring:message code="label.user.login" /></th>
                            <th><spring:message code="label.user.role" /></th>
                            <th>Действия</th>
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
        getSimpleUserList();

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

    function getSimpleUserList(id){
        var url = "/user/rest/user/all";

        optionValues0 = {"1": {id: "ROLE_USER", value: "Гражданин"},
                         "2": {id: "ROLE_POLICE", value: "Полицейский"},
                         "3": {id: "ROLE_FIREMAN", value: "Пожарный"},
                         "4": {id: "ROLE_OFFICIAL", value: "Чиновник"}};

        $.ajax({
            url: url,  // указываем URL и
            dataType : "json",
            async : false,
            success: function (data, textStatus) { // вешаем свой обработчик на функцию success

                if (!$.isEmptyObject(data)){

                    $('#filter_result').show();
                    $('.pager').show();
                    $("#filter_result").find('tbody').empty();

                    $.each(data, function(i, val) {    // обрабатываем полученные данные
                        console.log(val);
                        var id = "mySelect"+val.id
                        $("#filter_result").find('tbody')
                            .append($('<tr>')
                                .append(
                                $('<td id=idCell'+val.id+'>').text(val.id),
                                $('<td>').text(val.firstName +" "+ val.lastName),
                                $('<td>').text(val.login),
                                $('<td>').append($('<select>').attr("id",id)),
                                $('<td>')
                                    .append(
                                    $('<a>').text("Удалить пользователя...").attr('href',"/user/report/find/"+val.id)
                                )
                            )
                        );
                        $.each(optionValues0, function(order, object) {
                        key = object.id;
                        value = object.value;
                        $('#'+id).append($('<option>', { value : key }).text(value)); }),
                        $('#'+id).val(val.role).change(),
                        $('#'+id).change(function() {
                            var role =  $('#mySelect'+val.id).val();
                            var id = $('#idCell'+val.id).text();
                            var dataIn = "userId="+id+"&roleId="+role;
                            console.log(dataIn);
                            $.ajax({
                                url: "/rest/admin/user/updaterole",  // указываем URL и
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
                        });
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