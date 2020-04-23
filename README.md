[TOC]

# MyJinyBlog

MyJinyBlog is a personal blog platform based on JavaWeb technology. The entire project is completely open source, so you can download and research it as you like. This project is also one of my practical projects to learn JavaEE technology, so if I have a better way of implementation, I will keep updating this project.

Are you looking for [Chinese_Version](README_CN.md) ?



## Why MyJinyBlog?

I have also used some very famous blog platforms, such as WordPress, Hexo and other blog frameworks. 
It is true that they are all excellent works, but my personal server configuration is not high, running these blog frameworks is very difficult, and the response time is very long when completing some settings. I like blogging, but I do n’t like being lost in the ocean of plug-ins and skins, which not only consumes my precious energy, but also makes me feel very uncomfortable. After learning the knowledge of JavaWeb, I was excited to realize that I now have the ability to complete a static page blog, which is different from the mainstream dynamic page blog. 

The static page blog has a faster access speed, which makes me very happy. In addition, the process of completing the entire blog project is a test in itself, and I look forward to it with enthusiasm.



## Feature

- Have basic blog functions, you can create, edit and display blog posts
- Has a sidebar that can display personal information, archived information, and related links
- Have a simple management background, where you can modify your blog information or manage articles
- When writing an article, you can publish or set the article as a draft
- Can upload custom avatar and favicon.ico icon of website



## Start

Because the technology currently used by MyJinyBlog is relatively old (usable level), the steps to use MyJinyBlog are currently complicated.

However, Configuring this project is still very simple:

1. Make sure you have a server that already has a **JRE** and has **Tomcat** installed.

2. git clone this project to your computer.

3. Open the project and load the required files in the **pom.xml** file.

4. Use the **db.sql** file under **main / resources** in the project to create the **database** required by this project.

    - <u>The default user name of the project is techape, and the password is also "techape"</u>.
    - Some personal information about this blog (temporarily) is my github profile, you can change it yourself.

5. Configure your database related content in the **druid.properties** file under **main / resources**

    ```properties
    driverClassName=com.mysql.jdbc.Driver
    url=jdbc:mysql://[your database here]?useUnicode=true&characterEncoding=UTF-8
    username=
    password=
    initialSize=5
    maxActive=10
    maxWait=3000
    ```

6. Configure your redis related content in the **jedis.properties** file under **main / resources**

    ```properties
    host=localhost
    port=6379
    maxTotal=50
    maxIdle=10
    ```

7. Use **maven** instruction to package the project and get war file

    ```
    mvn clean
    mvn package
    ```

8. Place the war file in the webapps directory of the Tomcat server, and then start Tomcat.

9. View this project under your server path.

10. Of course you can do more on this simple project, I will omit it here.



**This project currently has an online demo:**

[MyJinyBlog Online](https://www.techape.fun/MyJinyBlog)



## More Details

Since I just started learning about JavaEE and have not yet touched the Spring framework, this project was developed using Servlet as the back-end body.

Some content currently used:

1. HTML + CSS + JavaScript
2. jQuery
3. ajax
4. servlet
5. Mysql

I admit that the project still has very, very many shortcomings. I am still a student and I am constantly improving this project. If you are interested in this project, you can fork and clone this project, which is fully licensed. If you encounter some problems in the process of research, you can submit your issue on the issues page, I will definitely try my best to solve it! !

Of course, if you like this project, you can watch or star, thank you for your support!



## Contact

If you have more suggestions, my contact information is as follows:
1354839386@qq.com

