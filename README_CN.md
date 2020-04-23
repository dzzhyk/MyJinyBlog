[TOC]

# MyJinyBlog

MyJinyBlog是一个基于JavaWeb技术的个人博客平台，整个项目是完全开源的，所以你可以任意下载并且研究使用。
这个项目同时也是我学习JavaEE技术的实践项目之一，所以如果我有更好的实现方式，会不断更新这个项目。



## 项目的前世今生：

​		在完成这个项目之前我就一直在写博客了，我也使用过一些非常著名的博客平台，比如WordPress、Hexo等博客框架。诚然他们都是非常优秀的作品，但是我的个人服务器配置不高，运行这些博客框架非常吃力，而且完成一些设置的时候响应时间非常长。我喜欢写博客，可我不喜欢迷失在各种插件、皮肤的海洋里面，这不仅耗费了我宝贵的精力，还让我感到非常不适。在学习了JavaWeb的知识时候，我激动地意识到我现在具备了自己完成一个静态页面博客的能力，区别于主流的动态页面博客，

​		静态页面博客的访问速度更快，这让我非常开心。除此之外，完成整个博客项目的过程本身就是一场考验，我期待并且热情期盼着。



## 特点

- 拥有基本的博客功能，可以创建、编辑、展示博客文章
- 拥有一个侧边栏，可以展示个人信息、归档信息、相关连接
- 拥有一个简单的管理后台，在这里可以修改你的博客信息、或者管理文章
- 写文章的时候可以发布或者将文章设置为草稿
- 可以上传自定义头像和网站的favicon.ico图标



## 开始

因为MyJinyBlog目前使用的技术比较旧了（能用的水平），因此想要使用MyJinyBlog的步骤目前比较复杂，我使用maven来进行项目管理，在学习新技术之后我会不断更新这个项目。

配置这个项目仍然非常简单：

1. 确保你有一个已经具有**JRE**运行环境并且安装好**Tomcat**的服务器

2. git clone 这个项目到你的电脑上

3. 打开这个项目，并且加载**pom.xml**文件中所需求的文件

4. 使用项目中**main/resources**下的**db.sql**文件创建项目需要的数据库

    - <u>项目默认的用户名是techape，密码也是"techape"</u>
    - 有关博客的一些个性信息（暂时）是我的github信息，你可以自己更改

5. 在**main/resources**下的**druid.properties**文件中配置你的数据库相关内容

    ```properties
    driverClassName=com.mysql.jdbc.Driver
    url=jdbc:mysql://[your database here]?useUnicode=true&characterEncoding=UTF-8
    username=
    password=
    initialSize=5
    maxActive=10
    maxWait=3000
    ```

6. 在main/resources下的**jedis.properties**文件中配置你的redis相关内容

    ```properties
    host=localhost
    port=6379
    maxTotal=50
    maxIdle=10
    ```

7. 使用**maven**指令将项目进行打包，得到war包文件

    ```shell
    mvn clean
    mvn package
    ```

8. 将war包文件放置到Tomcat服务器的webapps目录下，然后启动Tomcat

9. 在你的服务器启动路径下查看项目

10. 当然你还可以在这个简单的项目上做的更多，在这里我就省略了。



这个项目目前有一个在线效果演示，这是我自己搭建的网站：

[MyJinyBlog Online](https://www.techape.fun/MyJinyBlog)



## 关于项目

由于本人刚开始学习JavaEE的相关知识，尚未接触Spring框架，因此这个项目是使用Servlet作为后端主体进行开发的。

目前使用到的一些内容：

1. 前端三剑客（HTML+CSS+JavaScript）
2. jQuery
3. ajax
4. servlet
5. Mysql


我承认该项目仍然有非常非常非常多的不足，我还是一名学生，正在不断改进这个项目。
如果你对这个项目感兴趣，可以fork、clone这个项目，这是完全许可的。
如果你在研究的过程中遇到了一些问题，可以在issues页面提交你的问题，我一定会尽全力解决！！

当然如果你喜欢这个项目，可以watch或者star，感谢你的支持！



## 联系我

如果你有更多建议，我的联系方式如下：

[1354839386@qq.com](mailto:1354839386@qq.com)

