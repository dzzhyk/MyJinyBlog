$(function () {
    // 禁止拖动a标签
    $("body").on("mousedown", "a", function (e) {
        e.preventDefault();
    });
    $.get("header.html",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
    $.get("sidebar.html",function (data) {
        $("#sidebar").html(data);
    });
    $.get("article-list.html",function (data) {
        $("#article-list").html(data);
    });
    $.get("article.html",function (data) {
        $("#article").html(data);
    });
});
