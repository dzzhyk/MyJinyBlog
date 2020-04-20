$(function(){
    // 改变小白三角的位置
    var id = window.location.pathname.split('/')[1].slice(0, length-5);
    if (id !== "index"){
        $('#'+id).addClass("active");
    }
})

// 休眠函数
function sleep(d){
    for(var t = Date.now();Date.now() - t <= d;);
}

// 全局变量记录当前已经加载的文章数量
var CURRENT_ARTICLE_NUMBER = 0;

// 在当前已经加载的文章数量基础上，动态刷新一定数量的文章列表
function loadListProfile(count) {
    // 返回数据1：{flag:true, data:JloggArticleProfile, errorMsg:"1"}
    // 返回数据2：{flag:false, data:null, errorMsg:"0"}
    // 异步获取articles_profile
    $.ajax({
        url:"servletFlushArticleProfileByLimit",
        dataType:"json",
        data:{  "currentNumber":CURRENT_ARTICLE_NUMBER,     // 当前文章数量
            "count":count},                 // 加载文章的数量
        success:function(info){
            if (info.data != null){
                // 显示加载条
                $("#sk-three-bounce").show();
                $("#already-bottom").hide();
                // 一旦成功，根据article_profile中的aid异步获取description
                // 同时动态生成列表
                // 返回数据1:{flag:true, data:JloggArticleContent, errorMsg:"1"}
                // 返回数据2：{flag:false, data:null, errorMsg:"0"}
                generateList(info);
                // 更新CURRENT_ARTICLE_NUMBER
                CURRENT_ARTICLE_NUMBER += count;
            }else {
                // 如果失败，info.data==null，隐藏加载条
                $("#sk-three-bounce").hide();
                $("#already-bottom").show();
            }
        },
        error:function(info){
            // 展示404页面 - 待定
        }
    })
}

// 根据Profile信息加载列表，info.data是每个article的profile信息
function generateList(info) {
    var start = "<!-- START blog-post-->\n";
    var end = "\n<!-- END blog-post -->\n";
    $.each(info.data, function(index, item){       // 遍历info.data
        var aid = item.aid;
        var title = item.title;
        var year = item.time.year;
        var month = item.time.monthValue;
        var day = item.time.dayOfMonth;
        var author = item.author;
        var views = item.views;

        $.ajax({
            url:"servletFlushArticleContent",
            dataType: "json",
            data:{"aid":this.aid},
            success:function(contentInfo){
                // href="details.html?aid=?"
                // 获取成功，生成div
                var str =
                    start
                    + "<div class=\"blog-post-back\">\n" +
                    "      <div class=\"blog-post\">\n" +
                    "            <h3 class=\"blog-post-title\"><a href=\"details.html?aid="+aid+"\">"+title+"</a></h3>\n" +
                    "            <div class=\"blog-post-meta\">\n" +
                    "                  "+year+"年"+month+"月"+day+"日 | 作者: "+author+" | 浏览: "+views+" |\n" +
                    "                  <a href=\"details.html?aid="+aid+"\">阅读全文...</a>\n" +
                    "            </div>\n" +
                    "            <div class=\"blog-post-entry\">\n" +
                    "                  <p>\n&emsp;&emsp;"+contentInfo.data.description +"</p>\n" +
                    "            </div>\n" +
                    "      </div>\n" +
                    "</div>"
                    + end;
                $("#article-list").append(str);
            },
            error:function () {
            }
        })
    })
}