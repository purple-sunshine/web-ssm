/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : db_ssm

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-18 19:04:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `code` varchar(50) DEFAULT NULL COMMENT '用户角色编码',
  `name` varchar(50) DEFAULT NULL COMMENT '用户角色名称',
  `id` varchar(36) NOT NULL COMMENT '用户id',
  `create_by` varchar(50) DEFAULT NULL COMMENT '该角色创建人',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '角色创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '角色更新人',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '角色更新时间',
  `status` int(1) DEFAULT '0' COMMENT '删除状态（0：未删除  1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
