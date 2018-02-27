/*
Navicat MySQL Data Transfer

Source Server         : myaliyun
Source Server Version : 50636
Source Host           : 119.23.254.180:3306
Source Database       : admin

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-09-13 10:27:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource`;
CREATE TABLE `admin_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(600) DEFAULT NULL COMMENT '资源标题',
  `url` varchar(600) DEFAULT NULL COMMENT '资源url',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_resource
-- ----------------------------
INSERT INTO `admin_resource` VALUES ('27', '咨询管理', 'zixunadd', '0');
INSERT INTO `admin_resource` VALUES ('28', '添加咨询', 'addzixun', '27');
INSERT INTO `admin_resource` VALUES ('29', '删除咨询', 'del', '27');
INSERT INTO `admin_resource` VALUES ('30', '搜索咨询', 'search', '27');
INSERT INTO `admin_resource` VALUES ('31', '图片管理', 'tianjia', '0');
INSERT INTO `admin_resource` VALUES ('32', '删除图片', 'delpic', '31');
INSERT INTO `admin_resource` VALUES ('33', '咨询顾问', '1212', '28');
INSERT INTO `admin_resource` VALUES ('34', '图片列表', '/picture/picturelist', '31');
INSERT INTO `admin_resource` VALUES ('35', '管理员管理', '管理员管理', '0');
INSERT INTO `admin_resource` VALUES ('36', '角色管理', '/resource/adminrole', '35');
INSERT INTO `admin_resource` VALUES ('37', '权限管理', '/resource/adminpermission', '35');
INSERT INTO `admin_resource` VALUES ('38', '管理员列表', '/user/adminlist', '35');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名字',
  `role_desc` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '122112', '1212');
INSERT INTO `admin_role` VALUES ('6', '图片管理员', '图片管理员');
INSERT INTO `admin_role` VALUES ('13', '产品管理', '产品管理');
INSERT INTO `admin_role` VALUES ('15', '销售经理', '销售经理1');
INSERT INTO `admin_role` VALUES ('16', '121212', '');
INSERT INTO `admin_role` VALUES ('17', '咨212', '');
INSERT INTO `admin_role` VALUES ('18', '121212', '');
INSERT INTO `admin_role` VALUES ('20', '超级管理员', '拥有所有权限');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(255) DEFAULT NULL COMMENT '用户电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `pic` varchar(255) DEFAULT NULL COMMENT '用户图像',
  `sex` smallint(6) DEFAULT NULL COMMENT '用户性别 1,男 2 女',
  `login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `user_email` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '账号状态 1可用  0不可用',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=384 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', '18767180386', '2017-07-16 18:00:47', '1', '123456', 'ic_launcher 211 (1).png', '1', '2017-09-13 10:25:19', null, '0:0:0:0:0:0:0:1', '12121212@qq.com', '1');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('5', '6', '27');
INSERT INTO `role_resource` VALUES ('6', '6', '28');
INSERT INTO `role_resource` VALUES ('7', '6', '29');
INSERT INTO `role_resource` VALUES ('8', '6', '30');
INSERT INTO `role_resource` VALUES ('9', '6', '31');
INSERT INTO `role_resource` VALUES ('10', '6', '32');
INSERT INTO `role_resource` VALUES ('45', '13', '27');
INSERT INTO `role_resource` VALUES ('46', '13', '28');
INSERT INTO `role_resource` VALUES ('47', '13', '29');
INSERT INTO `role_resource` VALUES ('48', '13', '30');
INSERT INTO `role_resource` VALUES ('49', '13', '31');
INSERT INTO `role_resource` VALUES ('50', '13', '32');
INSERT INTO `role_resource` VALUES ('53', '15', '27');
INSERT INTO `role_resource` VALUES ('54', '15', '28');
INSERT INTO `role_resource` VALUES ('55', '15', '29');
INSERT INTO `role_resource` VALUES ('56', '15', '30');
INSERT INTO `role_resource` VALUES ('57', '15', '31');
INSERT INTO `role_resource` VALUES ('58', '15', '32');
INSERT INTO `role_resource` VALUES ('75', '20', '27');
INSERT INTO `role_resource` VALUES ('76', '20', '28');
INSERT INTO `role_resource` VALUES ('77', '20', '33');
INSERT INTO `role_resource` VALUES ('78', '20', '29');
INSERT INTO `role_resource` VALUES ('79', '20', '30');
INSERT INTO `role_resource` VALUES ('80', '20', '31');
INSERT INTO `role_resource` VALUES ('81', '20', '32');
INSERT INTO `role_resource` VALUES ('82', '20', '34');
INSERT INTO `role_resource` VALUES ('83', '20', '35');
INSERT INTO `role_resource` VALUES ('84', '20', '36');
INSERT INTO `role_resource` VALUES ('85', '20', '37');
INSERT INTO `role_resource` VALUES ('86', '20', '38');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '368', '1');
INSERT INTO `user_role` VALUES ('2', '369', '2');
INSERT INTO `user_role` VALUES ('3', '370', '19');
INSERT INTO `user_role` VALUES ('4', '370', '18');
INSERT INTO `user_role` VALUES ('5', '370', '17');
INSERT INTO `user_role` VALUES ('6', '370', '16');
INSERT INTO `user_role` VALUES ('7', '370', '15');
INSERT INTO `user_role` VALUES ('8', '370', '13');
INSERT INTO `user_role` VALUES ('9', '370', '10');
INSERT INTO `user_role` VALUES ('10', '370', '9');
INSERT INTO `user_role` VALUES ('11', '370', '8');
INSERT INTO `user_role` VALUES ('12', '370', '7');
INSERT INTO `user_role` VALUES ('13', '370', '6');
INSERT INTO `user_role` VALUES ('14', '370', '1');
INSERT INTO `user_role` VALUES ('15', '371', '19');
INSERT INTO `user_role` VALUES ('16', '371', '18');
INSERT INTO `user_role` VALUES ('17', '371', '17');
INSERT INTO `user_role` VALUES ('18', '371', '16');
INSERT INTO `user_role` VALUES ('19', '371', '15');
INSERT INTO `user_role` VALUES ('20', '371', '13');
INSERT INTO `user_role` VALUES ('21', '371', '10');
INSERT INTO `user_role` VALUES ('22', '371', '9');
INSERT INTO `user_role` VALUES ('23', '371', '8');
INSERT INTO `user_role` VALUES ('24', '371', '7');
INSERT INTO `user_role` VALUES ('25', '371', '6');
INSERT INTO `user_role` VALUES ('26', '371', '1');
INSERT INTO `user_role` VALUES ('27', '372', '18');
INSERT INTO `user_role` VALUES ('28', '372', '7');
INSERT INTO `user_role` VALUES ('29', '373', '18');
INSERT INTO `user_role` VALUES ('30', '373', '17');
INSERT INTO `user_role` VALUES ('31', '373', '7');
INSERT INTO `user_role` VALUES ('32', '373', '6');
INSERT INTO `user_role` VALUES ('33', '374', '18');
INSERT INTO `user_role` VALUES ('34', '374', '7');
INSERT INTO `user_role` VALUES ('35', '375', '18');
INSERT INTO `user_role` VALUES ('36', '375', '7');
INSERT INTO `user_role` VALUES ('37', '376', '17');
INSERT INTO `user_role` VALUES ('38', '376', '6');
INSERT INTO `user_role` VALUES ('39', '377', '18');
INSERT INTO `user_role` VALUES ('40', '378', '17');
INSERT INTO `user_role` VALUES ('41', '378', '6');
INSERT INTO `user_role` VALUES ('54', '379', '19');
INSERT INTO `user_role` VALUES ('55', '379', '16');
INSERT INTO `user_role` VALUES ('56', '379', '15');
INSERT INTO `user_role` VALUES ('57', '379', '13');
INSERT INTO `user_role` VALUES ('58', '379', '10');
INSERT INTO `user_role` VALUES ('59', '379', '9');
INSERT INTO `user_role` VALUES ('60', '379', '8');
INSERT INTO `user_role` VALUES ('61', '379', '1');
INSERT INTO `user_role` VALUES ('62', '381', '19');
INSERT INTO `user_role` VALUES ('63', '381', '18');
INSERT INTO `user_role` VALUES ('64', '381', '17');
INSERT INTO `user_role` VALUES ('65', '381', '16');
INSERT INTO `user_role` VALUES ('66', '381', '15');
INSERT INTO `user_role` VALUES ('67', '381', '13');
INSERT INTO `user_role` VALUES ('68', '381', '10');
INSERT INTO `user_role` VALUES ('69', '381', '9');
INSERT INTO `user_role` VALUES ('70', '381', '8');
INSERT INTO `user_role` VALUES ('71', '381', '7');
INSERT INTO `user_role` VALUES ('72', '381', '6');
INSERT INTO `user_role` VALUES ('73', '381', '1');
INSERT INTO `user_role` VALUES ('86', '382', '19');
INSERT INTO `user_role` VALUES ('87', '382', '18');
INSERT INTO `user_role` VALUES ('88', '382', '17');
INSERT INTO `user_role` VALUES ('89', '382', '16');
INSERT INTO `user_role` VALUES ('90', '382', '15');
INSERT INTO `user_role` VALUES ('91', '382', '13');
INSERT INTO `user_role` VALUES ('92', '382', '10');
INSERT INTO `user_role` VALUES ('93', '382', '9');
INSERT INTO `user_role` VALUES ('94', '382', '8');
INSERT INTO `user_role` VALUES ('95', '382', '7');
INSERT INTO `user_role` VALUES ('96', '382', '6');
INSERT INTO `user_role` VALUES ('97', '382', '1');
INSERT INTO `user_role` VALUES ('110', '383', '19');
INSERT INTO `user_role` VALUES ('111', '383', '18');
INSERT INTO `user_role` VALUES ('112', '383', '17');
INSERT INTO `user_role` VALUES ('113', '383', '16');
INSERT INTO `user_role` VALUES ('114', '383', '15');
INSERT INTO `user_role` VALUES ('115', '383', '13');
INSERT INTO `user_role` VALUES ('116', '383', '10');
INSERT INTO `user_role` VALUES ('117', '383', '9');
INSERT INTO `user_role` VALUES ('118', '383', '8');
INSERT INTO `user_role` VALUES ('119', '383', '7');
INSERT INTO `user_role` VALUES ('120', '383', '6');
INSERT INTO `user_role` VALUES ('121', '383', '1');
INSERT INTO `user_role` VALUES ('122', '10', '19');
INSERT INTO `user_role` VALUES ('123', '10', '18');
INSERT INTO `user_role` VALUES ('124', '10', '17');
INSERT INTO `user_role` VALUES ('125', '10', '16');
INSERT INTO `user_role` VALUES ('126', '10', '15');
INSERT INTO `user_role` VALUES ('127', '10', '13');
INSERT INTO `user_role` VALUES ('128', '10', '10');
INSERT INTO `user_role` VALUES ('129', '10', '9');
INSERT INTO `user_role` VALUES ('130', '10', '8');
INSERT INTO `user_role` VALUES ('131', '10', '7');
INSERT INTO `user_role` VALUES ('132', '10', '6');
INSERT INTO `user_role` VALUES ('133', '10', '1');
INSERT INTO `user_role` VALUES ('138', '1', '20');
INSERT INTO `user_role` VALUES ('139', '1', '6');

-- ----------------------------
-- Procedure structure for createChildLst
-- ----------------------------
DROP PROCEDURE IF EXISTS `createChildLst`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `createChildLst`(IN `rootId` int,IN `nDepth` int)
BEGIN
	#Routine body goes here...
  DECLARE done INT DEFAULT 0;
  DECLARE  b INT;
  DECLARE  cur1 CURSOR FOR SELECT id FROM  admin_resource WHERE parent_id=rootId;
 -- DECLARE  CONTINUE HANDLER FOR FOUND SET done=1;
  INSERT INTO tmpLst VALUES (null,rootId,nDepth);
  OPEN  cur1;
  FETCH cur1 INTO b;
  WHILE done=0 DO

    CALL createChildLst(b,nDepth+1);
     FETCH cur1 INTO b;
   END WHILE; 
CLOSE cur1;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getChildList
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildList`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `getChildList`(`rootId` int) RETURNS varchar(1000) CHARSET utf8
BEGIN
	#Routine body goes here...
   DECLARE sTemp VARCHAR(1000);
   DECLARE sTempChd VARCHAR(1000);
   SET sTemp='$';
   SET sTempChd=CAST(rootId as CHAR);

  WHILE sTempChd is NOT NULL DO
      SET sTemp=CONCAT(sTemp,',',sTempChd);
      SELECT GROUP_CONCAT(id) INTO sTempChd FROM admin_resource WHERE  FIND_IN_SET(parent_id,sTempChd)>0;

  END WHILE;

	RETURN sTemp;
END
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;
