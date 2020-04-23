/*
 Navicat Premium Data Transfer
 Date: 23/04/2020 16:25:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_articles_content
-- ----------------------------
DROP TABLE IF EXISTS `blog_articles_content`;
CREATE TABLE `blog_articles_content` (
                                         `aid` int(10) NOT NULL,
                                         `description` varchar(255) DEFAULT NULL,
                                         `path` varchar(255) DEFAULT NULL,
                                         PRIMARY KEY (`aid`),
                                         CONSTRAINT `fk_profile_content` FOREIGN KEY (`aid`) REFERENCES `blog_articles_profile` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog_articles_profile
-- ----------------------------
DROP TABLE IF EXISTS `blog_articles_profile`;
CREATE TABLE `blog_articles_profile` (
                                         `aid` int(10) NOT NULL AUTO_INCREMENT,
                                         `time` timestamp(6) NULL DEFAULT NULL,
                                         `title` varchar(100) DEFAULT NULL,
                                         `author` varchar(32) DEFAULT NULL,
                                         `views` int(11) DEFAULT NULL,
                                         `shown` varchar(2) DEFAULT NULL,
                                         PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog_general
-- ----------------------------
DROP TABLE IF EXISTS `blog_general`;
CREATE TABLE `blog_general` (
                                `title` varchar(50) NOT NULL,
                                `name` varchar(50) DEFAULT NULL,
                                `description` varchar(255) DEFAULT NULL,
                                `htmlDescription` varchar(255) DEFAULT NULL,
                                `htmlCopyright` varchar(255) DEFAULT NULL,
                                `beian` varchar(50) DEFAULT NULL,
                                PRIMARY KEY (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_general
-- ----------------------------
BEGIN;
INSERT INTO `blog_general` VALUES ('Techape', 'Techape\'s Blog', '猿来如此！', 'techape个人技术博客', 'Copyright © 2019-2020 techape www.techape.fun All Rights Reserved.', '鲁ICP备19038578号-1');
COMMIT;

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
                             `uid` varchar(10) NOT NULL,
                             `username` varchar(32) NOT NULL,
                             `passwd` varchar(32) NOT NULL,
                             `admin` varchar(2) NOT NULL,
                             PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
BEGIN;
INSERT INTO `blog_user` VALUES ('admin', 'techape', 'techape', 'Y');
COMMIT;

-- ----------------------------
-- Table structure for blog_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `blog_user_profile`;
CREATE TABLE `blog_user_profile` (
                                     `uid` varchar(10) NOT NULL,
                                     `selfdes` varchar(255) DEFAULT NULL,
                                     `email` varchar(255) DEFAULT NULL,
                                     `github` varchar(255) DEFAULT NULL,
                                     `csdn` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`uid`),
                                     CONSTRAINT `fk_user_profile_user` FOREIGN KEY (`uid`) REFERENCES `blog_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_user_profile
-- ----------------------------
BEGIN;
INSERT INTO `blog_user_profile` VALUES ('admin', '\"行百里者半九十\"', '1354839386@qq.com', 'https://github.com/dzzhyk', 'https://dzzhyk.blog.csdn.net');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
