/*
Navicat MySQL Data Transfer

Source Server         : 10.10.9.58[localhost]
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : zkhy_website1

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-09-11 14:36:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zk_sysuser
-- ----------------------------
DROP TABLE IF EXISTS `zk_sysuser`;
CREATE TABLE `zk_sysuser` (
  `sys_user_id` varchar(50) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zk_sysuser
-- ----------------------------
INSERT INTO `zk_sysuser` VALUES ('ff22ee990a904556a3301a5fe1f44774', 'sysadmin', 'E10ADC3949BA59ABBE56E057F20F883E', '0', '2019-07-24 13:37:24', '2019-07-24 18:00:54');
