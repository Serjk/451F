<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="shortcut icon" href="<c:url value="/resources/img/flame.ico"/>">
    <link href="<c:url value="/resources/style/css/site.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/TextSelect.js"/>"></script>
    <title>Ставки</title>
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

    <div id="content" style="text-align: left;">
        <div class="block">
            <p id ="res_hed" >Расходы:</p>
            <table   style="font-size:16px;" class="paginated">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Пользователь</th>
                    <th>Тип ставки</th>
                    <th>Дата</th>
                    <th>Время</th>
                    <th>Списано средств</th>
                </tr>
                </thead>
                <tbody class="tbody">

                    <c:if test="${!empty paymentList}">
                        <c:forEach items="${paymentList}" var="payment">
                            <tr>
                                <th>
                                        ${payment.id}
                                </th>
                                <c:if test="${!empty userList}">
                                    <c:forEach items="${userList}" var="user">
                                        <c:if test="${user.id==payment.userId}">
                                            <th>
                                                  ${user.firstName} ${user.lastName}
                                            </th>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${!empty wageList}">
                                    <c:forEach items="${wageList}" var="wage">
                                        <c:if test="${wage.id==payment.wageId}">
                                            <th>
                                                    ${wage.type}
                                            </th>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <th>
                                    <fmt:formatDate value="${payment.date}" pattern="yyyy-MM-dd"/>
                                </th>
                                <th>
                                    <fmt:formatDate value="${payment.date}" pattern="HH:mm:ss"/>
                                </th>
                                <th>
                                    ${payment.count}
                                </th>
                            </tr>
                        </c:forEach>
                    </c:if>

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

    <div id="blackblock"></div>

    <div id="bank_denun" style="text-align:left">
        <p>Изменение тарифа ставки</p>
        <input type="hidden" id="wage" value="">
        <p>Значение </p>
        <input id="value" type="number" value="">
        <div>
            <input type="submit" value="Изменить" onClick ="editWage()"/>
            <input type="button" value="Отмена" id="cancel"/>
        </div>
        <p id="error_p"></p>
    </div>

</body>

<script>

    function setFormValue(id){
        $("#blackblock").show();
        $("#bank_denun").show();
        $("#wage").val(id);
    }

    $(cancel).click(function () {
        $("#blackblock").hide();
        $("#bank_denun").hide();
    });

    $("#blackblock").click(function () {
        $("#blackblock").hide();
        $("#bank_denun").hide();
        location.reload();
    });

    $(document).ready(function () {
        $("#blackblock").hide();
        $("#bank_denun").hide();
        setPagination();
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

    function setPagination(){
        $('table.paginated').each(function() {
            var currentPage = 0;
            var numPerPage = 10;
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
</script>

</html>