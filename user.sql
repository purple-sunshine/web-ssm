/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : db_ssm

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-18 19:04:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL COMMENT '用户主键',
  `login_name` varchar(50) NOT NULL COMMENT '登录账号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码（岗位编码）',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `phone` varchar(30) DEFAULT NULL COMMENT '注册手机号',
  `department_code` varchar(30) DEFAULT NULL COMMENT '所属部门编码',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别（男 or 女）',
  `note` varchar(255) DEFAULT NULL,
  `department_name` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称（岗位名称）',
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '删除状态（0：未删除  1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', null, null, '123456', null, null, null, null, null, null, null, null, null, null, '0');
