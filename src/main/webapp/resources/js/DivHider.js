/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 29.03.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

$(".header_button").disableSelection();
$(".header_logo").disableSelection();
$(".block_menu_button").disableSelection();

$(document).ready(function () {
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

$("#blackblock").hide();
$("#confirm_denun").hide();
$("#confirm_denun2").hide();
$("#assign_denun").hide();

 $("#blackblock").click(function () {
    $("#blackblock").hide();
    $("#confirm_denun").hide();
    $("#confirm_denun2").hide();
    });

$("#cancel").click(function () {
    $("#blackblock").hide();
    $("#confirm_denun").hide();
    });
$("#ok").click(function () {
    $("#confirm_denun").hide();
    $("#confirm_denun2").show();
    });
$("#thanks").click(function () {
    $("#blackblock").hide();
    $("#confirm_denun").hide();
    $("#confirm_denun2").hide();
    });
});
$(window).resize(function () {
    SetSize();
    });
function SetSize() {
    var content = ($("#content").height() + $("#header").height() + $("#footer").height());
    var body = $('body').height();
    if (content > body) {
    $("#footer").css({"position": "relative"});
var pageHeight = $("#content").height();
$("#page").height(pageHeight);
} else {
    $("#footer").css({"position": "absolute"});
var pageHeight = $("#footer").position().top - $("#header").height();
$("#page").height(pageHeight);
}
}