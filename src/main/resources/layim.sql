/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost
 Source Database       : layim

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : utf-8

 Date: 02/24/2018 21:12:34 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tbl_project_config`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_project_config`;
CREATE TABLE `tbl_project_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `value` varchar(300) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `isValid` bit(1) NOT NULL,
  `modifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_project_config`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_project_config` VALUES ('1', '上传文件的访问域名', 'http://resource.webyefei.com/', 'upload_resource_host', b'1', '2018-02-09 11:45:57'), ('2', '用户登录信息有效期限（天）', '30', 'access_token_expired_date', b'1', '2018-02-10 15:22:19'), ('3', '是否只允许一个设备同时登录', '1', 'only_allow_one_client_login', b'1', '2018-02-10 17:05:25');
COMMIT;

-- ----------------------------
--  Table structure for `tbl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(40) NOT NULL,
  `nickName` varchar(100) NOT NULL DEFAULT '',
  `signature` varchar(255) NOT NULL DEFAULT '',
  `avatar` text NOT NULL,
  `password` varchar(255) NOT NULL,
  `registerTime` datetime NOT NULL,
  `isDeleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `ck_userID_userName_nickName` (`userID`,`userName`,`nickName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_user`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user` VALUES ('1', 'yefei', '王不留行', '无敌', 'https://avatars2.githubusercontent.com/u/14289678?s=460&v=4', 'e10adc3949ba59abbe56e057f20f883e', '2018-02-14 18:23:11', null), ('2', 'yefei1', '月亮', '无双', 'http://qlogo3.store.qq.com/qzone/461184610/461184610/100?1493390878', '698d51a19d8a121ce581499d7b701668', '2018-02-14 22:21:45', null), ('3', 'yefei2', '太阳', '盖世', 'http://tva1.sinaimg.cn/crop.0.0.180.180.180/83d685c5jw1e8qgp5bmzyj2050050aa8.jpg', '202cb962ac59075b964b07152d234b70', '2018-02-22 17:10:55', null);
COMMIT;

-- ----------------------------
--  Table structure for `tbl_user_apply_friend`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_apply_friend`;
CREATE TABLE `tbl_user_apply_friend` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `applyUserID` int(11) NOT NULL,
  `toUserID` int(11) NOT NULL,
  `userGroupID` int(11) NOT NULL DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0 已申请； 1 已推送给用户； 2 已同意 ； 3 已拒绝 ；4 已忽略',
  `createTime` datetime NOT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_user_apply_friend`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user_apply_friend` VALUES ('1', '3', '2', '3', null, '3', '2018-02-22 17:14:01', '2018-02-24 20:18:15'), ('2', '3', '2', '3', null, '4', '2018-02-22 17:14:48', '2018-02-24 20:24:53'), ('3', '3', '2', '3', null, '3', '2018-02-22 17:14:49', '2018-02-24 20:35:52'), ('4', '3', '2', '3', null, '4', '2018-02-22 17:15:06', '2018-02-24 21:02:09'), ('5', '3', '2', '3', null, '4', '2018-02-22 17:15:07', '2018-02-24 21:02:43'), ('6', '3', '2', '3', null, '4', '2018-02-22 17:15:07', '2018-02-24 21:08:04'), ('7', '3', '2', '3', null, '3', '2018-02-22 17:15:08', '2018-02-24 21:08:29');
COMMIT;

-- ----------------------------
--  Table structure for `tbl_user_friend_item`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_friend_item`;
CREATE TABLE `tbl_user_friend_item` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `groupID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  KEY `groupID` (`groupID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_user_friend_item`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user_friend_item` VALUES ('1', '1', '2'), ('2', '2', '1'), ('10', '3', '2'), ('11', '2', '3');
COMMIT;

-- ----------------------------
--  Table structure for `tbl_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_group`;
CREATE TABLE `tbl_user_group` (
  `groupID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `groupName` varchar(100) NOT NULL,
  `isDeleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`groupID`),
  KEY `userID` (`userID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_user_group`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user_group` VALUES ('1', '1', '我的基友', null), ('2', '2', '我的基友', null), ('3', '3', '我的基友', null);
COMMIT;

-- ----------------------------
--  Table structure for `tbl_user_group_default`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_group_default`;
CREATE TABLE `tbl_user_group_default` (
  `groupName` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_user_group_default`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user_group_default` VALUES ('我的基友');
COMMIT;

-- ----------------------------
--  Table structure for `tbl_user_login`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_login`;
CREATE TABLE `tbl_user_login` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `accessToken` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `expiredTime` datetime NOT NULL,
  `isValid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  UNIQUE KEY `uk_accessToken` (`accessToken`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_user_login`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user_login` VALUES ('1', '1', 'ce0bf5dd-4254-4cec-aa07-e661224b4323', '2018-02-10 11:41:59', '2018-02-23 23:16:55', '2018-02-11 11:41:47', b'0'), ('2', '1', 'aa4bcefb-2db7-42f3-ad03-22f7d1fe1c3c', '2018-02-14 17:45:57', '2018-02-23 23:16:55', '2018-03-16 17:45:57', b'0'), ('3', '1', '1b9625a5-5eed-4c84-9bb3-e15b2bb76556', '2018-02-14 18:19:24', '2018-02-23 23:16:55', '2018-03-16 18:19:24', b'0'), ('4', '1', '209d7f99-c809-4137-a8cb-f9809b773924', '2018-02-14 18:23:11', '2018-02-23 23:16:55', '2018-03-16 18:23:11', b'0'), ('5', '1', '331ad9a3-7f0a-47f6-989b-8e48807379ac', '2018-02-14 19:33:17', '2018-02-23 23:16:55', '2018-03-16 19:33:17', b'0'), ('6', '1', 'c4a3eeaa-4072-4fd7-b42a-643d51179592', '2018-02-14 19:35:21', '2018-02-23 23:16:55', '2018-03-16 19:35:21', b'0'), ('7', '2', '47eb3d50-b940-46bb-9f2c-a893f5d0bd5e', '2018-02-14 22:21:45', '2018-02-24 09:50:45', '2018-03-16 22:21:45', b'0'), ('8', '1', '3767cf95-bbf3-4139-ac82-a33f0b208bee', '2018-02-15 21:06:02', '2018-02-23 23:16:55', '2018-03-17 21:06:02', b'0'), ('9', '2', 'b89bcce0-03fc-4404-8da4-968741aa1e06', '2018-02-15 21:28:57', '2018-02-24 09:50:45', '2018-03-17 21:28:57', b'0'), ('10', '1', '6a1babc4-be3d-4bb7-b084-6c1952dc1e48', '2018-02-16 01:34:51', '2018-02-23 23:16:55', '2018-03-18 01:34:51', b'0'), ('11', '1', 'ad4b981b-824b-4fe6-b9f0-304d09562627', '2018-02-16 01:41:44', '2018-02-23 23:16:55', '2018-03-18 01:41:44', b'0'), ('12', '1', '30e612fc-1248-4b5e-bd91-53f00d4be0b3', '2018-02-16 01:44:38', '2018-02-23 23:16:55', '2018-03-18 01:44:38', b'0'), ('13', '1', '65fbe564-8c9e-475a-8cf8-5ff8905063a8', '2018-02-16 02:28:28', '2018-02-23 23:16:55', '2018-03-18 02:28:28', b'0'), ('14', '2', '9c0e748d-3606-45ba-9134-59cf9172e08b', '2018-02-16 08:12:47', '2018-02-24 09:50:45', '2018-03-18 08:12:47', b'0'), ('15', '1', 'e61db031-ef97-43fd-a1e8-030fda623e72', '2018-02-16 08:13:08', '2018-02-23 23:16:55', '2018-03-18 08:13:08', b'0'), ('16', '2', '908dc5a3-e91e-4dab-b3a9-f9df47515a43', '2018-02-16 08:21:55', '2018-02-24 09:50:45', '2018-03-18 08:21:55', b'0'), ('17', '2', 'e46ff4d0-d3a3-47f6-bbfa-4652aeb72817', '2018-02-22 10:34:29', '2018-02-24 09:50:45', '2018-03-24 10:34:29', b'0'), ('18', '1', 'c440a84f-9b57-4cbe-82db-2a26163346c0', '2018-02-22 11:13:00', '2018-02-23 23:16:55', '2018-03-24 11:13:00', b'0'), ('19', '3', '6f7254ec-3d83-4f76-92d9-f760b9585bae', '2018-02-22 17:10:55', '2018-02-24 10:31:35', '2018-03-24 17:10:55', b'0'), ('20', '3', 'ab024362-2f40-445b-89be-2931cbc4b0cf', '2018-02-22 23:14:30', '2018-02-24 10:31:35', '2018-03-24 23:14:30', b'0'), ('21', '3', '751857ad-0b5b-437d-94b1-7c71fbc9ec18', '2018-02-23 09:43:26', '2018-02-24 10:31:35', '2018-03-25 09:43:26', b'0'), ('22', '1', '1354ad79-c58e-439c-a879-6a68edb3a772', '2018-02-23 10:33:28', '2018-02-23 23:16:55', '2018-03-25 10:33:28', b'0'), ('23', '3', 'eb851489-c254-4bae-bf48-50c9f8c42f56', '2018-02-23 10:48:43', '2018-02-24 10:31:35', '2018-03-25 10:48:43', b'0'), ('24', '1', '529c5f96-0c17-4753-baa1-4dbc015f16bd', '2018-02-23 10:55:38', '2018-02-23 23:16:55', '2018-03-25 10:55:38', b'0'), ('25', '1', '7ec201cb-89d2-4be1-b81f-d3e18152b9cc', '2018-02-23 13:56:44', '2018-02-23 23:16:55', '2018-03-25 13:56:44', b'0'), ('26', '1', '8e5226dd-6781-4aa1-a9bc-0e8868087f26', '2018-02-23 13:58:11', '2018-02-23 23:16:55', '2018-03-25 13:58:11', b'0'), ('27', '1', 'ce2a2139-f27c-4ec6-a8df-a186f63b1c3b', '2018-02-23 13:58:47', '2018-02-23 23:16:55', '2018-03-25 13:58:47', b'0'), ('28', '3', '6c0dad15-b3df-4190-bfba-8c551e63657e', '2018-02-23 14:00:20', '2018-02-24 10:31:35', '2018-03-25 14:00:20', b'0'), ('29', '3', 'e31a6483-9f65-40a5-b64a-f9c70314024f', '2018-02-23 15:43:55', '2018-02-24 10:31:35', '2018-03-25 15:43:55', b'0'), ('30', '1', '55443d46-436a-47e0-84cf-6b9e469328a9', '2018-02-23 16:49:35', '2018-02-23 23:16:55', '2018-03-25 16:49:35', b'0'), ('31', '1', 'ed32890d-6d8a-4be8-8127-5e58fbdb07ab', '2018-02-23 23:16:55', '2018-02-23 23:16:55', '2018-03-25 23:16:55', b'1'), ('32', '2', '8fd76a2f-b725-459b-88f4-dd8f57507d15', '2018-02-24 00:01:07', '2018-02-24 09:50:45', '2018-03-26 00:01:07', b'0'), ('33', '3', '1dcef909-88fc-4208-b2d4-90b03fabf617', '2018-02-24 09:48:13', '2018-02-24 10:31:35', '2018-03-26 09:48:13', b'0'), ('34', '2', '7ce60886-a00e-41b1-8614-1d7db437651f', '2018-02-24 09:50:45', '2018-02-24 09:50:45', '2018-03-26 09:50:45', b'1'), ('35', '3', '66bd518d-1cc4-4e8d-84e2-faa7d1ce3927', '2018-02-24 10:31:35', '2018-02-24 10:31:35', '2018-03-26 10:31:35', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `tbl_user_msg`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_msg`;
CREATE TABLE `tbl_user_msg` (
  `msgID` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserID` int(11) NOT NULL,
  `toUserID` int(11) NOT NULL,
  `msgContent` text NOT NULL,
  `msgType` int(11) NOT NULL COMMENT '消息类型   1：文本    2 文件   3 语音',
  `resourcePath` varchar(255) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `readTime` datetime DEFAULT NULL,
  `isRead` tinyint(3) NOT NULL DEFAULT '0' COMMENT '消息状态  0 已收到发送请求  1已发送给目标用户  2目标用户已收到',
  PRIMARY KEY (`msgID`,`msgType`),
  KEY `fromUserID` (`fromUserID`,`toUserID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `tbl_user_msg`
-- ----------------------------
BEGIN;
INSERT INTO `tbl_user_msg` VALUES ('1', '1', '2', '1111', '1', null, '2018-02-15 20:00:22', null, '1'), ('2', '1', '2', 'kkkk', '1', null, '2018-02-15 20:00:41', null, '1'), ('3', '2', '1', '呜呜呜呜', '1', null, '2018-02-15 23:56:51', null, '1'), ('4', '2', '1', 'eee', '1', null, '2018-02-16 00:00:00', null, '1'), ('5', '1', '2', 'sdajfsdf', '1', null, '2018-02-16 01:35:29', null, '1'), ('6', '2', '1', '77777', '1', null, '2018-02-16 01:37:59', null, '1'), ('7', '1', '2', '88888', '1', null, '2018-02-16 01:38:08', null, '1'), ('8', '2', '1', '000', '1', null, '2018-02-16 01:42:09', null, '1'), ('9', '2', '1', '999', '1', null, '2018-02-16 01:42:33', null, '1'), ('10', '2', '1', '8888', '1', null, '2018-02-16 01:43:23', null, '1'), ('11', '2', '1', '77', '1', null, '2018-02-16 01:44:11', null, '1'), ('12', '1', '2', 'gg', '1', null, '2018-02-16 01:51:40', null, '1'), ('13', '1', '2', '--', '1', null, '2018-02-16 01:52:27', null, '1'), ('14', '1', '2', 'kk', '1', null, '2018-02-16 01:52:40', null, '1'), ('15', '2', '1', 'hh', '1', null, '2018-02-16 02:28:40', null, '1'), ('16', '1', '2', '我', '1', null, '2018-02-16 02:28:58', null, '1'), ('17', '2', '1', 'jjj', '1', null, '2018-02-16 02:29:40', null, '1'), ('18', '2', '1', 'kk', '1', null, '2018-02-16 02:33:24', null, '1'), ('19', '1', '2', '去吧', '1', null, '2018-02-16 08:13:20', null, '1'), ('20', '2', '1', 'wath', '1', null, '2018-02-16 08:13:35', null, '1'), ('21', '1', '2', '滦县', '1', null, '2018-02-16 08:13:53', null, '1'), ('22', '1', '2', '今天', '1', null, '2018-02-16 08:23:15', null, '1'), ('23', '2', '1', '明天', '1', null, '2018-02-16 08:23:35', null, '1'), ('24', '1', '2', '111', '1', null, '2018-02-22 11:13:13', null, '1'), ('25', '2', '1', '333', '1', null, '2018-02-22 11:13:21', null, '1'), ('26', '1', '2', '0000', '1', null, '2018-02-22 11:14:03', null, '1'), ('27', '2', '1', '111', '1', null, '2018-02-22 11:42:06', null, '1'), ('28', '1', '2', 'face[哈欠] ', '1', null, '2018-02-23 16:54:20', null, '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
