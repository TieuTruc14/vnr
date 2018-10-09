/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : db_vnr_voc

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2014-10-23 15:22:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_station
-- ----------------------------
DROP TABLE IF EXISTS `tbl_station`;
CREATE TABLE `tbl_station` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `feature` bit(1) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_station
-- ----------------------------
INSERT INTO `tbl_station` VALUES ('1', 'Hà Nội', '', '0');
INSERT INTO `tbl_station` VALUES ('2', 'Giáp Bát', '', '5180');
INSERT INTO `tbl_station` VALUES ('3', 'Văn Điển', '\0', '8930');
INSERT INTO `tbl_station` VALUES ('4', 'Thường Tín', '\0', '17400');
INSERT INTO `tbl_station` VALUES ('5', 'Chợ Tía', '\0', '25500');
INSERT INTO `tbl_station` VALUES ('6', 'Phú Xuyên', '\0', '33340');
INSERT INTO `tbl_station` VALUES ('7', 'Đồng Văn', '\0', '44670');
INSERT INTO `tbl_station` VALUES ('8', 'Phủ Lý', '', '55860');
INSERT INTO `tbl_station` VALUES ('9', 'Bình Lục', '\0', '66540');
INSERT INTO `tbl_station` VALUES ('10', 'Cầu Họ', '\0', '72910');
INSERT INTO `tbl_station` VALUES ('11', 'Đặng Xá', '\0', '81000');
INSERT INTO `tbl_station` VALUES ('12', 'Nam Định', '', '86760');
INSERT INTO `tbl_station` VALUES ('13', 'Trình Xuyên', '\0', '93315');
INSERT INTO `tbl_station` VALUES ('14', 'Núi Gôi', '\0', '100800');
INSERT INTO `tbl_station` VALUES ('15', 'Cát Đằng', '\0', '107620');
INSERT INTO `tbl_station` VALUES ('16', 'Ninh Bình', '', '114620');
INSERT INTO `tbl_station` VALUES ('17', 'Cầu Yên', '\0', '120350');
INSERT INTO `tbl_station` VALUES ('18', 'Gềnh', '\0', '125040');
INSERT INTO `tbl_station` VALUES ('19', 'Đồng Giao', '\0', '133740');
INSERT INTO `tbl_station` VALUES ('20', 'Bỉm Sơn', '', '141500');
INSERT INTO `tbl_station` VALUES ('21', 'Đò Lèn', '\0', '152300');
INSERT INTO `tbl_station` VALUES ('22', 'Nghĩa Trang', '\0', '161000');
INSERT INTO `tbl_station` VALUES ('23', 'Thanh Hóa', '', '175230');
