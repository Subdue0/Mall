/*
Navicat MySQL Data Transfer

Source Server         : SSM
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : myshop

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-07-27 18:13:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Create Database and select Database
-- ----------------------------
CREATE DATABASE myshop;
USE myshop;

-- ----------------------------
-- Table structure for `customers`
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `cust_id` int(10) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cust_password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cust_status` bit(1) DEFAULT NULL,
  `cust_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cust_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`cust_id`) USING BTREE,
  KEY `cust_name` (`cust_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=975 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('97', '范冰冰', 'MTIz', '', null, null);
INSERT INTO `customers` VALUES ('212', '周杰伦', 'MQ==', '', null, null);
INSERT INTO `customers` VALUES ('583', '王思聪', 'MTIz', '', null, null);

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `good_id` int(10) NOT NULL,
  `good_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `good_price` double(20,1) DEFAULT NULL,
  `type_id` int(10) DEFAULT NULL,
  `good_detail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `good_icon_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `good_image_detail_url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pic_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`good_id`) USING BTREE,
  KEY `fk_type_id` (`type_id`) USING BTREE,
  CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `types` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('101', 'Intel i3 9100 2核2线程', '999.9', '1', '有货', 'i31.jpg', 'i31.jpg,i32.jpg,i33.jpg', 'http://localhost:8080/myshop/images/i31.jpg');
INSERT INTO `goods` VALUES ('102', 'Intel i5 8400 4核6线程', '1299.0', '1', '有货', 'i51.jpg', 'i51.jpg,i52.jpg,i53.jpg', 'http://localhost:8080/myshop/images/i51.jpg');
INSERT INTO `goods` VALUES ('103', 'Intel i5 8500 6核6线程', '1399.0', '1', '有货', 'i51.jpg', 'i51.jpg,i52.jpg,i53.jpg', 'http://localhost:8080/myshop/images/i51.jpg');
INSERT INTO `goods` VALUES ('104', 'Intel i5 8600 6核6线程', '1499.0', '1', '有货', 'i51.jpg', 'i51.jpg,i52.jpg,i53.jpg', 'http://localhost:8080/myshop/images/i51.jpg');
INSERT INTO `goods` VALUES ('105', 'Intel i5 9400 6核6线程', '1299.0', '1', '有货', 'i51.jpg', 'i51.jpg,i52.jpg,i53.jpg', 'http://localhost:8080/myshop/images/i51.jpg');
INSERT INTO `goods` VALUES ('106', 'Intel i7 7700 4核8线程', '1799.0', '1', '有货', 'i71.jpg', 'i71.jpg,i72.jpg,i73.jpg', 'http://localhost:8080/myshop/images/i71.jpg');
INSERT INTO `goods` VALUES ('107', 'Intel i7 8700 8核8线程', '2499.0', '1', '无货', 'i71.jpg', 'i71.jpg,i72.jpg,i73.jpg', 'http://localhost:8080/myshop/images/i71.jpg');
INSERT INTO `goods` VALUES ('108', 'Intel i7 9700 8核8线程', '3199.0', '1', '无货', 'i71.jpg', 'i71.jpg,i72.jpg,i73.jpg', 'http://localhost:8080/myshop/images/i71.jpg');
INSERT INTO `goods` VALUES ('109', 'Intel i9 9900 12核24线程', '3999.0', '1', '有货', 'i91.jpg', 'i91.jpg,i92.jpg,i93.jpg', 'http://localhost:8080/myshop/images/i91.jpg');
INSERT INTO `goods` VALUES ('201', 'GeForce GTX 1050', '799.0', '2', '有货', 'itx1.jpg', 'itx1.jpg,itx2.jpg,itx3.jpg', 'http://localhost:8080/myshop/images/itx1.jpg');
INSERT INTO `goods` VALUES ('202', 'GeForce GTX 1060', '1299.0', '2', '有货', 'asus2.jpg', 'asus2.jpg,asus1.jpg,asus3.jpg,asus4.jpg', 'http://localhost:8080/myshop/images/asus2.jpg');
INSERT INTO `goods` VALUES ('203', 'GeForce GTX 1070', '2399.0', '2', '无货', 'color2.jpg', 'color2.jpg,color1.jpg,color3.jpg,color4.jpg', 'http://localhost:8080/myshop/images/color2.jpg');
INSERT INTO `goods` VALUES ('204', 'GeForce GTX 1080', '3399.0', '2', '有货', 'giga2.jpg', 'giga2.jpg,giga1.jpg,giga3.jpg,giga4.jpg', 'http://localhost:8080/myshop/images/giga2.jpg');
INSERT INTO `goods` VALUES ('205', 'GeForce GTX 2060', '2799.0', '2', '有货', 'maxsun2.jpg', 'maxsun2.jpg,maxsun1.jpg,maxsun3.jpg,maxsun4.jpg', 'http://localhost:8080/myshop/images/maxsun2.jpg');
INSERT INTO `goods` VALUES ('206', 'GeForce GTX 2070', '3299.0', '2', '有货', 'maxsun6.jpg', 'maxsun6.jpg,maxsun5.jpg,maxsun7.jpg,maxsun8.jpg,', 'http://localhost:8080/myshop/images/maxsun6.jpg');
INSERT INTO `goods` VALUES ('207', 'GeForce GTX 2080', '3999.0', '2', '有货', 'rtx1.jpg', 'rtx1.jpg,rtx2.jpg,rtx3.jpg', 'http://localhost:8080/myshop/images/rtx1.jpg');
INSERT INTO `goods` VALUES ('208', 'GeForce GTX 1050ti', '899.0', '2', '无货', 'gtx1.jpg', 'gtx1.jpg,gtx2.jpg,gtx3.jpg', 'http://localhost:8080/myshop/images/gtx1.jpg');
INSERT INTO `goods` VALUES ('209', 'GeForce GTX 1080ti', '3699.0', '2', '有货', 'maxsun10.jpg', 'maxsun9.jpg,maxsun10.jpg,maxsun11.jpg', 'http://localhost:8080/myshop/images/maxsun10.jpg');
INSERT INTO `goods` VALUES ('210', 'GeForce GTX 1650', '1299.0', '2', '有货', 'gtx1.jpg', 'gtx1.jpg,gtx2.jpg,gtx3.jpg', 'http://localhost:8080/myshop/images/gtx1.jpg');
INSERT INTO `goods` VALUES ('301', 'Kingston 8G  DDR4 2666MHz', '299.0', '3', '有货', 'ram1.jpg', 'ram1.jpg,ram2.jpg,ram3.jpg', 'http://localhost:8080/myshop/images/ram1.jpg');
INSERT INTO `goods` VALUES ('302', 'Kingston 16G  DDR4 2666MHz', '499.0', '3', '有货', 'ram1.jpg', 'ram1.jpg,ram2.jpg,ram3.jpg', 'http://localhost:8080/myshop/images/ram1.jpg');
INSERT INTO `goods` VALUES ('303', 'Kingston 4G DDR4 2400MHz', '199.0', '3', '有货', 'ram1.jpg', 'ram1.jpg,ram2.jpg,ram3.jpg', 'http://localhost:8080/myshop/images/ram1.jpg');
INSERT INTO `goods` VALUES ('304', 'Gskill 4G DDR4 2400MHz', '169.0', '3', '有货', 'ram2.jpg', 'ram1.jpg,ram2.jpg,ram3.jpg', 'http://localhost:8080/myshop/images/ram1.jpg');
INSERT INTO `goods` VALUES ('305', 'Gskill 8G DDR4 2666MHz', '259.0', '3', '有货', 'ram2.jpg', 'ram1.jpg,ram2.jpg,ram3.jpg', 'http://localhost:8080/myshop/images/ram1.jpg');
INSERT INTO `goods` VALUES ('306', 'Gskill 16 DDR4 2666MHz', '459.0', '3', '有货', 'ram2.jpg', 'ram1.jpg,ram2.jpg,ram3.jpg', 'http://localhost:8080/myshop/images/ram1.jpg');
INSERT INTO `goods` VALUES ('401', 'Samsung 120GB 固态硬盘', '199.0', '4', '有货', 'samsun2.jpg', 'samsun2.jpg,samsun1.jpg,samsun3.jpg,samsun4.jpg,', 'http://localhost:8080/myshop/images/samsun2.jpg');
INSERT INTO `goods` VALUES ('402', 'Samsung 240GB 固态硬盘', '499.0', '4', '有货', 'samsun2.jpg', 'samsun2.jpg,samsun1.jpg,samsun3.jpg,samsun4.jpg,', 'http://localhost:8080/myshop/images/samsun2.jpg');
INSERT INTO `goods` VALUES ('403', 'Samsung 480GB 固态硬盘', '799.0', '4', '有货', 'samsun2.jpg', 'samsun2.jpg,samsun1.jpg,samsun3.jpg,samsun4.jpg,', 'http://localhost:8080/myshop/images/samsun2.jpg');
INSERT INTO `goods` VALUES ('405', 'WD 240GB SATA硬盘', '279.0', '4', '有货', 'sata1.jpg', 'sata1.jpg,sata2.jpg,sata3.jpg', 'http://localhost:8080/myshop/images/sata1.jpg');
INSERT INTO `goods` VALUES ('406', 'Seagate 2TB 机械硬盘', '399.0', '4', '有货', 'seagate1.jpg', 'seagate1.jpg,seagate2.jpg,seagate3.jpg,seagate4.jpg', 'http://localhost:8080/myshop/images/seagate1.jpg');
INSERT INTO `goods` VALUES ('407', 'WD 1TB 机械硬盘', '289.0', '4', '有货', 'wd1.jpg', 'wd1.jpg,wd2.jpg,wd3.jpg,wd4.jpg', 'http://localhost:8080/myshop/images/wd1.jpg');

-- ----------------------------
-- Table structure for `og`
-- ----------------------------
DROP TABLE IF EXISTS `og`;
CREATE TABLE `og` (
  `order_id` int(10) NOT NULL,
  `good_id` int(10) NOT NULL,
  `good_num` int(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`good_id`) USING BTREE,
  CONSTRAINT `fk_og_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of og
-- ----------------------------
INSERT INTO `og` VALUES ('1775', '204', '155');
INSERT INTO `og` VALUES ('1775', '207', '200');
INSERT INTO `og` VALUES ('1775', '209', '200');
INSERT INTO `og` VALUES ('1775', '305', '200');
INSERT INTO `og` VALUES ('1987', '104', '100');
INSERT INTO `og` VALUES ('1987', '203', '100');
INSERT INTO `og` VALUES ('1987', '403', '100');
INSERT INTO `og` VALUES ('5807', '102', '1');
INSERT INTO `og` VALUES ('5807', '106', '1');
INSERT INTO `og` VALUES ('5807', '209', '1');
INSERT INTO `og` VALUES ('8607', '207', '50');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `cust_id` int(10) DEFAULT NULL,
  `order_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pay_mode` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `order_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `order_remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `order_price` double DEFAULT NULL,
  `rec_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `rec_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `rec_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `logistics_progress` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `logistics_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  KEY `cust_id` (`cust_id`) USING BTREE,
  KEY `rec_name` (`rec_name`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`cust_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9399 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1775', '583', '未发货', '微信', '2020-06-29 15:13:06', null, '2118245', '范冰冰', '158888', '东莞', null, null);
INSERT INTO `orders` VALUES ('1987', '97', '未发货', '支付宝', '2020-07-27 14:16:51', null, '469700', '范冰冰', '1895656213', '东莞', null, null);
INSERT INTO `orders` VALUES ('5807', '212', '未发货', '微信', '2020-07-15 18:44:26', null, '6797', '王思聪', '158888888', '东莞', null, null);
INSERT INTO `orders` VALUES ('8607', '97', '未发货', '支付宝', '2020-07-27 14:17:55', null, '199950', '李城', '1895656213', '东莞', null, null);

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(32) NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `user_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'abc', '123');
INSERT INTO `sys_user` VALUES ('2', 'def', '123');

-- ----------------------------
-- Table structure for `types`
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `type_id` int(10) NOT NULL,
  `type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `type_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('1', '处理器', 'http://localhost:8090/myshop/images/cpu.jpg');
INSERT INTO `types` VALUES ('2', '显卡', 'http://localhost:8090/myshop/images/gpu.jpg');
INSERT INTO `types` VALUES ('3', '内存', 'http://localhost:8090/myshop/images/ram.jpg');
INSERT INTO `types` VALUES ('4', '硬盘', 'http://localhost:8090/myshop/images/disk.jpg');
