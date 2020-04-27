<!--引入showdown.js-->
document.write("<script type='text/javascript' src='js/showdown.min.js'></script>");

$(function(){
    // 改变小白三角的位置
    var id = window.location.pathname.split('/')[1].slice(0, length-5);
    if (id !== "index"){
        $('#'+id).addClass("active");
    }
})


// 获取url中的参数
function getURLParameter(name) {
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}


// 休眠函数
function sleep(d){
    for(var t = Date.now();Date.now() - t <= d;);
}

// 全局变量记录当前已经加载的文章数量
var CURRENT_ARTICLE_NUMBER = 0;

// 全局变量记录当前页面是否已经全部加载完
var ALREADY_COMPLETE = false;

// 在当前已经加载的文章数量基础上，动态刷新一定数量的文章列表
function loadListProfile(count) {
    // 返回数据1：{flag:true, data:JloggArticleProfile, errorMsg:"1"}
    // 返回数据2：{flag:false, data:null, errorMsg:"0"}
    // 异步获取articles_profile
    if (ALREADY_COMPLETE===true){
        return;
    }else {
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
                    ALREADY_COMPLETE = true;
                }
            },
            error:function(info){
                // 展示404页面 - 待定
            }
        })
    }
}


// 在当前已经加载文章数量的基础上，继续加载一定数量属于特定Archive的文章
function loadListProfileByArchive(year, month, count) {
    if (ALREADY_COMPLETE===true){
        return;
    }else {
        $.ajax({
            url:"servletFlushArticleProfileByArchive",
            dataType:"json",
            data:{  "currentNumber":CURRENT_ARTICLE_NUMBER,
                "count":count,
                "year":year,
                "month":month},
            success:function(info){
                if (info.data != null){
                    $("#sk-three-bounce").show();
                    $("#already-bottom").hide();
                    generateList(info);
                    CURRENT_ARTICLE_NUMBER += count;
                }else {
                    $("#sk-three-bounce").hide();
                    $("#already-bottom").show();
                    ALREADY_COMPLETE = true;
                }
            },
            error:function(info){
                // 展示404页面 - 待定
            }
        })
    }
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

var COULD_SUBMIT = true;

// 检查目标长度
function checkLength(bar){
    if (DataLength($(bar).val()) > 50){
        $(bar).addClass("title-overflow");
        COULD_SUBMIT = false;
    }else {
        $(bar).removeClass("title-overflow");
        COULD_SUBMIT = true;
    }
}

function submitText(shown) {
    if (COULD_SUBMIT){
        if (confirm("确定要提交吗？")){
            var formData = new FormData();
            formData.append("title", $("#write-title").val());
            formData.append("text", $("#text").val());
            formData.append("shown", shown);

            var aid = getURLParameter("aid");
            if (aid!=null){
                formData.append("aid",aid);
            }

            $.ajax({
                url:"servletAddNewArticle",
                method:"POST",
                data:formData,
                dataType:"json",
                processData:false,
                contentType: false,
                success:function (info) {
                    alert(info.errorMsg);
                },
                error:function () {
                    alert("提交失败！");
                }
            })
        }
    }else {
        alert("无法提交，请检查！");
    }
}

//textarea支持tab缩进
function tab(e){
    if (event.keyCode === 9) {
        event.preventDefault();
        var indent = '    ';
        var start = e.selectionStart;
        var end = e.selectionEnd;
        var selected = window.getSelection().toString();
        selected = indent + selected.replace(/\n/g, '\n' + indent);
        e.value = e.value.substring(0, start) + selected + e.value.substring(end);
        this.setSelectionRange(start + indent.length, start
            + selected.length);
        event.returnValue = false;
    }
}

// 预览函数
function convert(){
    var converter = new showdown.Converter();
    text = $('.input-area').val();
    var html = converter.makeHtml(text);
    $('.preview-body').html(html);
}


// 刷新details页面的文章题目日期时间信息
function flushDetailProfile(info) {
    $("#article-title").text(info.data.title);
    var year = info.data.time.year;
    var month = info.data.time.monthValue;
    var day = info.data.time.dayOfMonth;
    var timeh = "<p id=\"article-time\" class=\"text-center\">"+year+"年"+month+"月"+day+"日</p>";
    $("#article-time").html(timeh);
}

// 根据文章id获取Profile，参数是操作函数
function flushArticleProfile(func) {
    var aid = getURLParameter("aid");
    if (aid!=null){
        $.ajax({
            url:"servletFlushArticleProfileByAid",
            method: "POST",
            data:{"aid":aid},
            success:function (info) {
                if (info.flag === true){
                    func(info);
                }
            }
        })
    }
}


// 将题目添加到title-area
function addTitleToWriteTitle(info) {
    $("#write-title").val(info.data.title);
}

// 将文本添加到textarea
function addTextToTextarea(data) {
    $("#text").val(data);
    convert();
}

// 将details中的文本转换为html
function convertTextToHtml(data) {
    // var result = decodeURIComponent(data);
    var converter = new showdown.Converter();
    var html = converter.makeHtml(data);
    $('#article-body').html(html);
}


// 如果有参数传递，加载文章文字内容
function loadContent(func) {
    var aid = getURLParameter("aid");
    if (aid != null){
        $.ajax({
            url:"servletFlushArticleContent",
            method:"POST",
            data:{"aid": aid},
            success:function (info) {
                if (info.flag===true){
                    // 获取文章成功
                    // 根据info中的path信息找到文件
                    var tempPath = info.data.path + "/" + info.data.aid;
                    $.ajax({
                        url:tempPath,
                        dataType:"text",
                        success:function(data){
                            func(data); // 调用参数函数
                        },
                        error:function () {
                            // 404
                            // alert("加载文章失败！");
                        }
                    })
                }else {
                    // 404
                    // alert("加载文章失败！");
                }
            },
            error:function(){
                // alert("加载文章失败!");
            }
        })
    }
}

// 响应式小屏幕适配
function transformForPhone() {
    var $sitelogo = $("#sitelogo");
    var $manage = $("#manage");
    if ($(window).width() <= 770){
        // 隐藏站点logo
        $sitelogo.hide();
        // 隐藏管理登录
        $manage.hide();
        // archive页面隐藏前置信息
    }else {
        $sitelogo.show();
        $manage.show();
    }
}

$(window).resize(transformForPhone);

// 监听滚动条
function addScrollEvent(func1, func2, func3, func4){
    $(window).scroll(function (){
        var scroH = $(document).scrollTop();  //滚动高度
        var viewH = $(window).height();  //可见高度
        var contentH = $(document).height();  //内容高度
        if(scroH > 300){  //距离顶部大于300px时
            func1();
        }else {
            func2();
        }
        if (contentH - (scroH + viewH) <= 30){  //距离底部高度小于30px
            func3();
        }
        if (contentH === (scroH + viewH)) {  //滚动条滑到底部啦
            func4();
        }
    });
}

function addOneView() {
    var aid = getURLParameter("aid");
    $.ajax({
        url:"servletAddOneView",
        dataType:"json",
        method:"POST",
        data:{"aid":aid},
        success:function () {

        },
        error:function () {

        }
    })
}