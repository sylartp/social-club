/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : sams

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-29 13:49:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'create');
INSERT INTO `permission` VALUES ('2', 'delete');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'vip');

-- ----------------------------
-- Table structure for `role_permission_rel`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL,
  `permission_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------
INSERT INTO `role_permission_rel` VALUES ('1', '1', '1');
INSERT INTO `role_permission_rel` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `user_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `role_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_rel
-- ----------------------------
INSERT INTO `user_role_rel` VALUES ('1', '1', '1');
INSERT INTO `user_role_rel` VALUES ('2', '1', '2');
