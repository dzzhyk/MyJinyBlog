
CREATE TABLE `blog_user` (
`username` varchar(32) ,
`passwd` varchar(32) ,
`selfdes` varchar(255) ,
`email` varchar(50) ,
`github` varchar(70) ,
`csdn` varchar(70) ,
PRIMARY KEY (`username`, `passwd`) 
)
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `blog_general` (
`title` varchar(50) ,
`name` varchar(50) ,
`description` varchar(255) ,
`html_description` varchar(255) ,
`html_copyright` varchar(255) ,
`beian` varchar(50) ,
PRIMARY KEY (`title`) 
)
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `blog_articles_profile` (
`id` int(11) ,
`time` timestamp(6),
`title` varchar(100),
`author` varchar(32) ,
`views` int(11),
PRIMARY KEY (`id`) 
);

CREATE TABLE `blog_articles_content` (
`id` int(11) ,
`description` varchar(255) ,
`content` varchar(255),
PRIMARY KEY (`id`) 
);


ALTER TABLE `blog_articles_profile` ADD CONSTRAINT `fk_blog_articles_blog_user` FOREIGN KEY (`author`) REFERENCES `blog_user` (`username`);
ALTER TABLE `blog_articles_content` ADD CONSTRAINT `fk_blog_articles_content` FOREIGN KEY (`id`) REFERENCES `blog_articles_profile` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

