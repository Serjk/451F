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
                <a href="<c:url value="/user/profile"/>" class="block_menu_button">Профиль</a>
            </div>
        </c:if>

        <div id="content" style="text-align: left;">
            <div class="block">
                    <c:if test="${loginUser.role=='ROLE_OFFICIAL'}">
                        <div class="warning">
                            <p3>Действия:</p>
                             <a href="#" class="transition_button" onclick="setFormValue()">Создание новости</a>
                             <a href="#" class="transition_button" onclick="getNewsList(1)">Все новости</a>
                             <a href="#" class="transition_button" onclick="getNewsList(2)">Новости за день</a>
                        </div>
                    </c:if>

                    <c:if test="${loginUser.role!='ROLE_OFFICIAL'}">
                        <div class="warning">
                             <p3>Быстрый фильтр:</p>
                             <a href="#" class="transition_button" onclick="getNewsList(1)">Все новости</a>
                             <a href="#" class="transition_button" onclick="getNewsList(2)">Новости за день</a>
                        </div>
                    </c:if>

                     <p id ="res_hed" >Результат запроса:</p>
                    <table id ="filter_result"  style="font-size:16px;" class="paginated">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Заголовок</th>
                            <th>Текст</th>
                            <th>Дата</th>
                            <th>Действия</th>
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


    <div id="blackblock"></div>

    <div id="news_denun" style="text-align:left">
        <input type="hidden" id="myDiv" name="id" value="">
        <p>Заголовок новости</p>
        <input type="text" id="title" value="">
        <p>Краткое описание новости  </p>
        <input type="text" id="summary" value="">
        <p>Текст новости </p>
        <div>
            <textarea type="text" id="description" value=""cols="40" rows="5"></textarea>
        </div>
        <input type="submit" value="Добавить новость" onClick ="addNews()"/>
        <input type="button" value="Отмена" id="cancel"/>
        <p id="error_p"></p>
    </div>

</body>

<script>

     function setFormValue(){
        $("#blackblock").show();
        $("#news_denun").show();
     }

    $(cancel).click(function () {
         $("#blackblock").hide();
         $("#news_denun").hide();
      });


    $("#blackblock").click(function () {
        $("#blackblock").hide();
        $("#news_denun").hide();
        location.reload();
    });

    function addNews(){
        var title =  $('#title').val();
        var summary = $('#summary').val();
        var text = $('#description').val();

        if($.isEmptyObject(title)||$.isEmptyObject(title)||$.isEmptyObject(title)){
           $('#error_p').text("Пожалуйста, заполните все поля");
        }
        else{
            var dataIn = "title="+title+"&summary="+summary+"&description="+text;
            console.log(dataIn);
            $.ajax({
                url: "/user/rest/news/new",  // указываем URL и
                dataType: "json",
                async: false,
                type: "POST",
                data: dataIn,
                success: function (data, textStatus) { // вешаем свой обработчик на функцию success
                   if (!$.isEmptyObject(data)) {
                       console.log(data);
                         $("#blackblock").hide();
                         $("#news_denun").hide();
                         location.reload();
                   }
                }
            });
        }
    }

    $(document).ready(function () {
        $("#blackblock").hide();
        $("#news_denun").hide();
         getNewsList();
    })

    $(window).resize(function () {
        SetSize();
    });

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

    function getNewsList(id){
        var url;

        if (id==1 || id == null){
                    url = "/user/rest/news/all/";
                    $('#res_hed').text("Общий архив новостей:");
                }
                else if(id==2){
                    url = "/user/rest/news/last";
                    $('#res_hed').text("Новости за день:");
                }

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
                        var date = new Date(val.date);
                        var curr_date = date.getDate();
                        var curr_month = date.getMonth() + 1; //Months are zero based
                        var curr_year = date.getFullYear();

                        $("#filter_result").find('tbody')
                            .append($('<tr>')
                                .append(
                                $('<td id=idCell'+val.id+'>').text(val.id),
                                $('<td>').text(val.title),
                                $('<td>').text(val.summary),
                                $('<td>').text(curr_date + "-" + curr_month + "-" + curr_year),
                                $('<td>').append($('<a>').text("Подробно...").attr('href',"/user/news/find/"+val.id))
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