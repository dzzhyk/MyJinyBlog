$(function () {
    // 禁止拖动a标签
    $("body").on("mousedown", "a", function (e) {
        e.preventDefault();
    });
    // 禁止拖动img
    $('img').on('mousedown',function (e) {
        e.preventDefault()
    })
    $.get("header.html",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
    $.get("sidebar.html",function (data) {
        $("#sidebar").html(data);
    });

    // 如果没有favicon.ico加载默认favicon
    $.ajax({
        url: "favicon.ico",
        error: function(){
            $("#favicon").prop("href","favicon_d.ico");
        }
    })
});
