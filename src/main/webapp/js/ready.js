$(function(){
    // 改变小白三角的位置
    var id = window.location.pathname.split('/')[1].slice(0, length-5);
    if (id !== "index"){
        $('#'+id).addClass("active");
    }
})

