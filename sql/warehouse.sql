/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : warehouse

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-04-15 13:25:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `GoodsId` int(10) NOT NULL AUTO_INCREMENT,
  `GoodsNum` varchar(50) NOT NULL,
  `GoodsName` varchar(50) NOT NULL,
  `BuyDate` date NOT NULL,
  `StartUseDate` date NOT NULL,
  `EndUseDate` date NOT NULL,
  `Useyear` int(11) NOT NULL,
  `GoodsType` varchar(50) NOT NULL,
  PRIMARY KEY (`GoodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '2', '3', '2017-04-11', '2017-04-12', '2018-10-31', '10', '233');
INSERT INTO `goods` VALUES ('2', '2', '2', '2017-04-03', '2017-04-21', '2017-04-03', '1', '2');
INSERT INTO `goods` VALUES ('3', '1', '1', '2017-04-12', '2017-04-20', '2017-04-18', '1', '1');
INSERT INTO `goods` VALUES ('7', '3', '金条制造机', '2017-04-09', '2017-04-09', '2099-04-09', '100', '机械');
INSERT INTO `goods` VALUES ('9', '1', '1', '2017-04-12', '2017-04-12', '2017-04-12', '1', '1');
INSERT INTO `goods` VALUES ('10', '1', '1', '2017-04-14', '2017-04-14', '2017-04-14', '1', '1');

-- ----------------------------
-- Table structure for `maintainers`
-- ----------------------------
DROP TABLE IF EXISTS `maintainers`;
CREATE TABLE `maintainers` (
  `MaintainerId` int(10) NOT NULL AUTO_INCREMENT,
  `MaintainerName` varchar(50) NOT NULL,
  `MaintainerJob` varchar(50) NOT NULL,
  `MaintainerCompany` varchar(50) NOT NULL,
  `MaintainerPhone` varchar(50) NOT NULL,
  PRIMARY KEY (`MaintainerId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintainers
-- ----------------------------
INSERT INTO `maintainers` VALUES ('7', '汤姆猫', '猫', '猫和老鼠', '1234567');
INSERT INTO `maintainers` VALUES ('8', '1', '1', '1', '1');
INSERT INTO `maintainers` VALUES ('9', '111', '111', '111', '111');

-- ----------------------------
-- Table structure for `maintain_goods`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_goods`;
CREATE TABLE `maintain_goods` (
  `MaintainerId` int(10) NOT NULL,
  `GoodId` int(10) NOT NULL,
  `StartMaintainDate` date NOT NULL,
  `EndMaintainDate` date NOT NULL,
  PRIMARY KEY (`MaintainerId`,`GoodId`),
  KEY `GoodId` (`GoodId`),
  CONSTRAINT `GoodId` FOREIGN KEY (`GoodId`) REFERENCES `goods` (`GoodsId`) ON DELETE CASCADE,
  CONSTRAINT `MaintainerId` FOREIGN KEY (`MaintainerId`) REFERENCES `maintainers` (`MaintainerId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_goods
-- ----------------------------
INSERT INTO `maintain_goods` VALUES ('7', '1', '2017-03-14', '2017-06-08');
INSERT INTO `maintain_goods` VALUES ('7', '7', '2017-04-21', '2017-07-13');
INSERT INTO `maintain_goods` VALUES ('8', '10', '2017-01-01', '2017-04-08');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `UserId` int(10) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `UserPassword` varchar(50) NOT NULL,
  `UserType` varchar(50) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', '1', 'user');
INSERT INTO `users` VALUES ('2', 'a', 'a', 'admin');
INSERT INTO `users` VALUES ('3', '2', '2', 'user');
INSERT INTO `users` VALUES ('5', '张三', '11', 'user');
INSERT INTO `users` VALUES ('6', 'admin', 'admin', 'admin');
