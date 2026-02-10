/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_upms_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 10/02/2026 15:28:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '市',
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区',
  `detail_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人电话',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `receiver_lat` decimal(10, 6) NOT NULL COMMENT '收货地址纬度',
  `receiver_lng` decimal(10, 6) NOT NULL COMMENT '收货地址经度',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_delete_time`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '北京市', '北京市', '海淀区', '中关村大街1号', '13800138001', '张三', '2026-01-17 02:24:01', 39.980000, 116.310000, '2026-01-17 02:24:01', NULL);
INSERT INTO `address` VALUES (2, '北京市', '北京市', '朝阳区', '建国路88号', '13800138002', '李四', '2026-01-17 02:24:01', 39.920000, 116.460000, '2026-01-17 02:24:01', NULL);
INSERT INTO `address` VALUES (3, '上海市', '上海市', '浦东新区', '世纪大道100号', '13800138003', '王五', '2026-01-17 02:24:01', 31.230000, 121.470000, '2026-01-17 02:24:01', NULL);
INSERT INTO `address` VALUES (4, '上海市', '上海市', '黄浦区', '南京东路200号', '13800138004', '赵六', '2026-01-17 02:24:01', 31.230000, 121.480000, '2026-01-17 02:24:01', NULL);
INSERT INTO `address` VALUES (5, '广东省', '深圳市', '南山区', '科技园南区', '13800138005', '钱七', '2026-01-17 02:24:01', 22.540000, 113.950000, '2026-01-17 02:24:01', NULL);

-- ----------------------------
-- Table structure for app_user_address
-- ----------------------------
DROP TABLE IF EXISTS `app_user_address`;
CREATE TABLE `app_user_address`  (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `address_id` bigint NOT NULL COMMENT '地址id',
  PRIMARY KEY (`user_id`, `address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of app_user_address
-- ----------------------------
INSERT INTO `app_user_address` VALUES (2001, 1);
INSERT INTO `app_user_address` VALUES (2001, 2);
INSERT INTO `app_user_address` VALUES (2002, 2);
INSERT INTO `app_user_address` VALUES (2002, 3);
INSERT INTO `app_user_address` VALUES (2003, 1);
INSERT INTO `app_user_address` VALUES (2004, 3);
INSERT INTO `app_user_address` VALUES (2004, 4);
INSERT INTO `app_user_address` VALUES (2005, 4);

-- ----------------------------
-- Table structure for audit_record
-- ----------------------------
DROP TABLE IF EXISTS `audit_record`;
CREATE TABLE `audit_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `audit_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核编号',
  `biz_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核项:\r\nMERCHANT_SETTLE-商家入驻\r\nWITHDRAW-提现\r\nGOODS-商品上架\r\nPARTNER_APPLY-合伙人\r\nSTAFF_APPLY-服务人员\r\nRIDER_APPLY-骑手',
  `biz_id` bigint UNSIGNED NOT NULL COMMENT '业务实体ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '审核状态:0-待审核 1-审核通过 2-审核拒绝',
  `auditor_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审核人ID',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_audit_no`(`audit_no` ASC) USING BTREE,
  INDEX `idx_applicant`(`biz_id` ASC, `status` ASC) USING BTREE,
  INDEX `idx_delete`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2015710533714206722 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '审核记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of audit_record
-- ----------------------------
INSERT INTO `audit_record` VALUES (1001, 'AUD202501170001', 'RIDER_APPLY', 3001, '申请成为骑手', 1, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (1002, 'AUD202501170002', 'RIDER_APPLY', 3002, '申请成为骑手', 1, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (1003, 'AUD202501170003', 'RIDER_APPLY', 3003, '申请成为骑手', 0, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (2001, 'AUD202501170004', 'STAFF_APPLY', 5001, '申请成为合伙人', 1, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (2002, 'AUD202501170005', 'STAFF_APPLY', 5002, '申请成为合伙人', 1, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (2003, 'AUD202501170006', 'STAFF_APPLY', 5003, '申请成为合伙人', 0, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (3001, 'AUD202501170007', 'MERCHANT_SETTLE', 4001, '商家入驻申请', 1, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (3002, 'AUD202501170008', 'MERCHANT_SETTLE', 4002, '商家入驻申请', 1, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (3003, 'AUD202501170009', 'MERCHANT_SETTLE', 4003, '商家入驻申请', 0, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (4001, 'AUD202501170010', 'STAFF_APPLY', 2001, '申请成为服务人员', 1, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (4002, 'AUD202501170011', 'STAFF_APPLY', 2002, '申请成为服务人员', 2, 1003, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (4003, 'AUD202501170012', 'STAFF_APPLY', 2003, '申请成为服务人员', 0, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `audit_record` VALUES (2015710533714206721, 'AUD1769417815924_633', 'ACTIVITY_PUBLISH', 1001, '活动申请', 1, NULL, '2026-01-26 16:56:56', '2026-01-27 11:12:56', NULL);

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_type` tinyint UNSIGNED NOT NULL COMMENT '1-系统后台 2-普通用户/服务者 3-商家 4-骑手 5-合伙人',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `last_login_at` datetime NULL DEFAULT NULL COMMENT '最后登陆时间',
  `last_login_ip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_delete_time`(`delete_at` ASC) USING BTREE,
  INDEX `idx_user_type`(`user_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9005 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES (1001, 'admin', '超级管理员', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, '/files/campus-default/default/2026/02/05/0abf36c3aeaf4af0abb4a3105110b203.png', '13800138001', 'admin@campus.com', 1, '2026-01-17 02:21:48', '2026-02-05 12:31:29', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (1002, 'sysadmin', '系统管理员', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/sysadmin.jpg', '13800138002', 'sysadmin@campus.com', 1, '2026-01-17 02:21:48', '2026-01-17 02:38:32', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (1003, 'auditor', '审核员', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/auditor.jpg', '13800138003', 'auditor@campus.com', 1, '2026-01-17 02:21:48', '2026-01-17 02:38:36', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (1004, 'operator', '运营人员', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/operator.jpg', '13800138004', 'operator@campus.com', 1, '2026-01-17 02:21:48', '2026-01-17 02:38:39', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (1005, 'testuser', '测试用户', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 0, 'https://example.com/avatar/testuser.jpg', '13800138005', 'testuser@campus.com', 1, '2026-01-17 02:21:48', '2026-01-17 02:38:41', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (2001, 'student1', '学生张小明', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/student1.jpg', '13900139001', 'student1@campus.com', 2, '2026-01-17 02:21:48', '2026-01-17 02:38:43', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (2002, 'student2', '学生李小红', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/student2.jpg', '13900139002', 'student2@campus.com', 2, '2026-01-17 02:21:48', '2026-01-17 02:38:45', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (2003, 'student3', '学生王小刚', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 0, 'https://example.com/avatar/student3.jpg', '13900139003', 'student3@campus.com', 2, '2026-01-17 02:21:48', '2026-01-17 02:38:47', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (2004, 'student4', '学生赵小美', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/student4.jpg', '13900139004', 'student4@campus.com', 2, '2026-01-17 02:21:48', '2026-01-17 02:38:50', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (2005, 'student5', '学生陈小龙', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/student5.jpg', '13900139005', 'student5@campus.com', 2, '2026-01-17 02:21:48', '2026-01-17 02:38:51', NULL, '2026-01-17 02:21:48', '127.0.0.1');
INSERT INTO `base_user` VALUES (2006, 'student6', '学生刘小花', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/student6.jpg', '13900139006', 'student6@campus.com', 2, '2026-02-08 08:00:00', '2026-02-08 08:00:00', NULL, '2026-02-08 08:00:00', '127.0.0.1');
INSERT INTO `base_user` VALUES (2007, 'student7', '学生陈小明', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/student7.jpg', '13900139007', 'student7@campus.com', 2, '2026-02-08 08:00:00', '2026-02-08 08:00:00', NULL, '2026-02-08 08:00:00', '127.0.0.1');
INSERT INTO `base_user` VALUES (2008, 'student8', '学生周小丽', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/student8.jpg', '13900139008', 'student8@campus.com', 2, '2026-02-08 08:00:00', '2026-02-08 08:00:00', NULL, '2026-02-08 08:00:00', '127.0.0.1');
INSERT INTO `base_user` VALUES (3001, 'rider1', '骑手刘大伟', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/rider1.jpg', '13700137001', 'rider1@campus.com', 4, '2026-01-17 02:23:06', '2026-01-17 02:38:54', NULL, '2026-01-17 02:23:06', '127.0.0.1');
INSERT INTO `base_user` VALUES (3002, 'rider2', '骑手孙小芳', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/rider2.jpg', '13700137002', 'rider2@campus.com', 4, '2026-01-17 02:23:06', '2026-01-17 02:38:56', NULL, '2026-01-17 02:23:06', '127.0.0.1');
INSERT INTO `base_user` VALUES (3003, 'rider3', '骑手周小军', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 0, 'https://example.com/avatar/rider3.jpg', '13700137003', 'rider3@campus.com', 4, '2026-01-17 02:23:06', '2026-01-17 02:38:58', NULL, '2026-01-17 02:23:06', '127.0.0.1');
INSERT INTO `base_user` VALUES (4001, 'mch1', '校园便利店', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/mch1.jpg', '13600136001', 'mch1@campus.com', 3, '2026-01-17 02:24:01', '2026-01-17 02:39:00', NULL, '2026-01-17 02:24:01', '127.0.0.1');
INSERT INTO `base_user` VALUES (4002, 'mch2', '美味餐厅', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/mch2.jpg', '13600136002', 'mch2@campus.com', 3, '2026-01-17 02:24:01', '2026-01-17 02:39:03', NULL, '2026-01-17 02:24:01', '127.0.0.1');
INSERT INTO `base_user` VALUES (4003, 'mch3', '水果超市', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 0, 'https://example.com/avatar/mch3.jpg', '13600136003', 'mch3@campus.com', 3, '2026-01-17 02:24:01', '2026-01-17 02:39:05', NULL, '2026-01-17 02:24:01', '127.0.0.1');
INSERT INTO `base_user` VALUES (5001, 'partner1', '合伙人吴大明', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/partner1.jpg', '13500135001', 'partner1@campus.com', 3, '2026-01-17 02:24:01', '2026-01-17 02:39:07', NULL, '2026-01-17 02:24:01', '127.0.0.1');
INSERT INTO `base_user` VALUES (5002, 'partner2', '合伙人郑小红', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/partner2.jpg', '13500135002', 'partner2@campus.com', 3, '2026-01-17 02:24:01', '2026-01-17 02:39:09', NULL, '2026-01-17 02:24:01', '127.0.0.1');
INSERT INTO `base_user` VALUES (5003, 'partner3', '合伙人王小华', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 0, 'https://example.com/avatar/partner3.jpg', '13500135003', 'partner3@campus.com', 3, '2026-01-17 02:24:01', '2026-01-17 02:39:12', NULL, '2026-01-17 02:24:01', '127.0.0.1');
INSERT INTO `base_user` VALUES (9001, 'blocked_user', '被拉黑用户', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 0, 'https://example.com/avatar/blocked.jpg', '13900139999', 'blocked@campus.com', 2, '2026-01-17 02:24:02', '2026-01-17 02:39:15', NULL, '2026-01-17 02:24:02', '127.0.0.1');
INSERT INTO `base_user` VALUES (9002, 'pending_rider', '待审核骑手', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/pending_rider.jpg', '13700137999', 'pending_rider@campus.com', 4, '2026-01-17 02:24:02', '2026-01-17 02:39:18', NULL, '2026-01-17 02:24:02', '127.0.0.1');
INSERT INTO `base_user` VALUES (9003, 'pending_mch', '待审核商家', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/pending_mch.jpg', '13600136999', 'pending_mch@campus.com', 3, '2026-01-17 02:24:02', '2026-01-17 02:39:20', NULL, '2026-01-17 02:24:02', '127.0.0.1');
INSERT INTO `base_user` VALUES (9004, 'pending_partner', '待审核合伙人', '$2a$10$D3wBqrrBC5Fsylxj7vr2CexvFD6NN.MH7efGyYwrQg1nywQDHyvvq', 1, 'https://example.com/avatar/pending_partner.jpg', '13500135999', 'pending_partner@campus.com', 3, '2026-01-17 02:24:02', '2026-01-17 02:39:23', NULL, '2026-01-17 02:24:02', '127.0.0.1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色标识',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-停用,1-启用',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_name`(`role_name` ASC) USING BTREE,
  UNIQUE INDEX `uk_role_code`(`role_code` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 'super_admin', 1, 1, '2026-01-17 02:21:29', '2026-01-17 02:21:29', NULL);
INSERT INTO `role` VALUES (2, '系统管理员', 'admin', 2, 1, '2026-01-17 02:21:29', '2026-01-17 02:21:29', NULL);
INSERT INTO `role` VALUES (3, '审核员', 'auditor', 3, 1, '2026-01-17 02:21:29', '2026-01-17 02:21:29', NULL);
INSERT INTO `role` VALUES (4, '运营人员', 'operator', 4, 1, '2026-01-17 02:21:29', '2026-01-17 02:21:29', NULL);
INSERT INTO `role` VALUES (5, '测试角色', 'test_role', 5, 0, '2026-01-17 02:21:29', '2026-01-17 02:21:29', NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (1, 5);
INSERT INTO `role_menu` VALUES (1, 6);
INSERT INTO `role_menu` VALUES (1, 7);
INSERT INTO `role_menu` VALUES (1, 11);
INSERT INTO `role_menu` VALUES (1, 12);
INSERT INTO `role_menu` VALUES (1, 13);
INSERT INTO `role_menu` VALUES (1, 14);
INSERT INTO `role_menu` VALUES (1, 15);
INSERT INTO `role_menu` VALUES (1, 21);
INSERT INTO `role_menu` VALUES (1, 22);
INSERT INTO `role_menu` VALUES (1, 23);
INSERT INTO `role_menu` VALUES (1, 24);
INSERT INTO `role_menu` VALUES (1, 31);
INSERT INTO `role_menu` VALUES (1, 32);
INSERT INTO `role_menu` VALUES (1, 33);
INSERT INTO `role_menu` VALUES (1, 34);
INSERT INTO `role_menu` VALUES (1, 41);
INSERT INTO `role_menu` VALUES (1, 42);
INSERT INTO `role_menu` VALUES (1, 43);
INSERT INTO `role_menu` VALUES (1, 44);
INSERT INTO `role_menu` VALUES (1, 51);
INSERT INTO `role_menu` VALUES (1, 52);
INSERT INTO `role_menu` VALUES (1, 53);
INSERT INTO `role_menu` VALUES (1, 54);
INSERT INTO `role_menu` VALUES (1, 61);
INSERT INTO `role_menu` VALUES (1, 62);
INSERT INTO `role_menu` VALUES (1, 63);
INSERT INTO `role_menu` VALUES (1, 64);
INSERT INTO `role_menu` VALUES (1, 71);
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 3);
INSERT INTO `role_menu` VALUES (2, 4);
INSERT INTO `role_menu` VALUES (2, 5);
INSERT INTO `role_menu` VALUES (2, 11);
INSERT INTO `role_menu` VALUES (2, 12);
INSERT INTO `role_menu` VALUES (2, 13);
INSERT INTO `role_menu` VALUES (2, 14);
INSERT INTO `role_menu` VALUES (2, 15);
INSERT INTO `role_menu` VALUES (2, 21);
INSERT INTO `role_menu` VALUES (2, 22);
INSERT INTO `role_menu` VALUES (2, 23);
INSERT INTO `role_menu` VALUES (2, 31);
INSERT INTO `role_menu` VALUES (2, 32);
INSERT INTO `role_menu` VALUES (2, 33);
INSERT INTO `role_menu` VALUES (2, 41);
INSERT INTO `role_menu` VALUES (2, 42);
INSERT INTO `role_menu` VALUES (2, 43);
INSERT INTO `role_menu` VALUES (2, 51);
INSERT INTO `role_menu` VALUES (2, 52);
INSERT INTO `role_menu` VALUES (2, 53);
INSERT INTO `role_menu` VALUES (3, 1);
INSERT INTO `role_menu` VALUES (3, 6);
INSERT INTO `role_menu` VALUES (3, 11);
INSERT INTO `role_menu` VALUES (3, 12);
INSERT INTO `role_menu` VALUES (3, 13);
INSERT INTO `role_menu` VALUES (3, 14);
INSERT INTO `role_menu` VALUES (3, 15);
INSERT INTO `role_menu` VALUES (3, 61);
INSERT INTO `role_menu` VALUES (3, 62);
INSERT INTO `role_menu` VALUES (3, 63);
INSERT INTO `role_menu` VALUES (3, 64);
INSERT INTO `role_menu` VALUES (4, 1);
INSERT INTO `role_menu` VALUES (4, 2);
INSERT INTO `role_menu` VALUES (4, 5);
INSERT INTO `role_menu` VALUES (4, 11);
INSERT INTO `role_menu` VALUES (4, 12);
INSERT INTO `role_menu` VALUES (4, 13);
INSERT INTO `role_menu` VALUES (4, 14);
INSERT INTO `role_menu` VALUES (4, 15);
INSERT INTO `role_menu` VALUES (4, 21);
INSERT INTO `role_menu` VALUES (4, 51);

-- ----------------------------
-- Table structure for school_partner
-- ----------------------------
DROP TABLE IF EXISTS `school_partner`;
CREATE TABLE `school_partner`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `org_id` bigint UNSIGNED NOT NULL COMMENT '组织ID（学校ID或商家ID）',
  `partner_id` bigint UNSIGNED NOT NULL COMMENT '合伙人ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_org_partner`(`org_id` ASC, `partner_id` ASC) USING BTREE COMMENT '组织-合伙人唯一索引',
  INDEX `idx_org`(`org_id` ASC) USING BTREE COMMENT '组织查询索引',
  INDEX `idx_partner`(`partner_id` ASC) USING BTREE COMMENT '合伙人查询索引',
  INDEX `idx_created`(`created_at` ASC) USING BTREE COMMENT '创建时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '组织-合伙人关联表（支持多对多）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school_partner
-- ----------------------------
INSERT INTO `school_partner` VALUES (1, 1001, 5001, '2026-01-17 02:24:01', '2026-01-17 02:24:01');
INSERT INTO `school_partner` VALUES (2, 1002, 5001, '2026-01-17 02:24:01', '2026-01-17 02:24:01');
INSERT INTO `school_partner` VALUES (3, 1003, 5002, '2026-01-17 02:24:01', '2026-01-17 02:24:01');
INSERT INTO `school_partner` VALUES (4, 1004, 5002, '2026-01-17 02:24:01', '2026-01-17 02:24:01');
INSERT INTO `school_partner` VALUES (5, 1005, 5003, '2026-01-17 02:24:01', '2026-01-17 02:24:01');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `dict_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典键',
  `dict_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用,1-启用',
  `level` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '层级 1-一级 2-二级',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 504 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, NULL, '审核状态字典', 'AUDIT_STATUS', '审核状态', 1, 1, 1, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (2, NULL, '用户状态字典', 'USER_STATUS', '用户状态', 2, 1, 1, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (3, NULL, '菜单类型字典', 'MENU_TYPE', '菜单类型', 3, 1, 1, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (4, NULL, '审核业务类型字典', 'BIZ_TYPE', '业务类型', 4, 1, 1, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (5, NULL, '性别字典', 'GENDER', '性别', 5, 1, 1, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (101, 1, '审核状态-待审核', 'PENDING', '待审核', 1, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (102, 1, '审核状态-审核通过', 'APPROVED', '审核通过', 2, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (103, 1, '审核状态-审核拒绝', 'REJECTED', '审核拒绝', 3, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (201, 2, '用户状态-正常', 'NORMAL', '正常', 1, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (202, 2, '用户状态-拉黑', 'BLOCKED', '拉黑', 2, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (301, 3, '菜单类型-目录', 'DIRECTORY', '目录', 1, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (302, 3, '菜单类型-菜单', 'MENU', '菜单', 2, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (303, 3, '菜单类型-按钮', 'BUTTON', '按钮', 3, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (401, 4, '业务类型-商家入驻', 'MERCHANT_SETTLE', '商家入驻', 1, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (402, 4, '业务类型-提现', 'WITHDRAW', '提现', 2, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (403, 4, '业务类型-商品上架', 'GOODS', '商品上架', 3, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (404, 4, '业务类型-服务人员申请', 'STAFF_APPLY', '服务人员', 4, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (405, 4, '业务类型-骑手申请', 'RIDER_APPLY', '骑手', 5, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (501, 5, '性别-未知', 'UNKNOWN', '未知', 1, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (502, 5, '性别-男', 'MALE', '男', 2, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);
INSERT INTO `sys_dict` VALUES (503, 5, '性别-女', 'FEMALE', '女', 3, 1, 2, '2026-01-17 02:20:48', '2026-01-17 02:20:48', NULL);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint NOT NULL COMMENT '编号',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `bucket_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件存储桶名称',
  `original` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, 'avatar_admin.jpg', 'campus-avatar', 'admin.jpg', 'image/jpeg', 102400, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `sys_file` VALUES (2, 'license_mch1.jpg', 'campus-license', 'mch1_license.jpg', 'image/jpeg', 204800, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `sys_file` VALUES (3, 'logo_mch1.jpg', 'campus-logo', 'mch1_logo.jpg', 'image/jpeg', 153600, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `sys_file` VALUES (4, 'qrcode_partner1.jpg', 'campus-qrcode', 'partner1_qrcode.jpg', 'image/jpeg', 51200, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `sys_file` VALUES (5, 'document_contract.pdf', 'campus-doc', 'contract.pdf', 'application/pdf', 512000, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '父菜单ID(0为根菜单)',
  `menu_type` tinyint NOT NULL COMMENT '类型:0-目录,1-菜单,2-按钮',
  `menu_icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限标识',
  `is_frame` tinyint NOT NULL DEFAULT 1 COMMENT '0:是外链 1:站内路由',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问路径',
  `redirect` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '重定向路径',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-隐藏,1-显示',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 0, NULL, '系统设置', 0, NULL, 1, '/system', '', 1, '2026-02-05 12:02:08', '2026-02-06 11:47:26', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, 1, NULL, '系统用户', 0, NULL, 1, '/system/user', NULL, 1, '2026-02-06 08:20:04', '2026-02-06 08:50:25', NULL);

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作用域',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回调地址',
  `access_token_validity` int NULL DEFAULT NULL COMMENT '访问令牌有效期（秒）',
  `refresh_token_validity` int NULL DEFAULT NULL COMMENT '刷新令牌有效期（秒）',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_client_id`(`client_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '终端信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES (1, 'campus', 'campus', 'server,read,write', 'password,refresh_token', NULL, 43200, 2592000, '2026-01-16 02:46:01', '2026-01-16 02:46:01', NULL);
INSERT INTO `sys_oauth_client_details` VALUES (2, 'app', 'app', 'all', 'password,refresh_token', 'http://127.0.0.1:8080/callback', 7200, 2592000, '2026-01-17 02:21:48', '2026-01-17 02:36:22', NULL);
INSERT INTO `sys_oauth_client_details` VALUES (3, 'miniapp', 'miniapp', 'all', 'password,refresh_token', 'http://127.0.0.1:8080/callback', 7200, 2592000, '2026-01-17 02:21:48', '2026-01-17 02:28:34', NULL);

-- ----------------------------
-- Table structure for sys_school
-- ----------------------------
DROP TABLE IF EXISTS `sys_school`;
CREATE TABLE `sys_school`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '学校ID',
  `school_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '校名',
  `address_id` bigint NULL DEFAULT NULL COMMENT '学校地址id',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:1启用 0禁用',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC, `id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10025 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学校表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_school
-- ----------------------------
INSERT INTO `sys_school` VALUES (1001, '111', 1, 0, '2026-01-17 02:24:01', '2026-01-17 15:41:26', '2026-01-17 07:41:28');
INSERT INTO `sys_school` VALUES (1002, '清华大学', 2, 1, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `sys_school` VALUES (1003, '复旦大学', 3, 1, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `sys_school` VALUES (1004, '上海交通大学', 4, 1, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `sys_school` VALUES (1005, '深圳大学', 5, 0, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid` ASC, `branch_id` ASC) USING BTREE,
  INDEX `ix_log_created`(`log_created` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_app
-- ----------------------------
DROP TABLE IF EXISTS `user_app`;
CREATE TABLE `user_app`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `base_user_id` bigint UNSIGNED NULL DEFAULT NULL,
  `gender` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未知 1-男 2-女',
  `openid` char(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stu_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计收益',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '学校id',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联审核表id（审核服务人员资格）',
  `real_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_user_id`(`base_user_id` ASC) USING BTREE,
  UNIQUE INDEX `idx_openid`(`openid` ASC) USING BTREE,
  INDEX `idx_delete_time`(`delete_time` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1002 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_app
-- ----------------------------
INSERT INTO `user_app` VALUES (101, 2001, 1, 'o1234567890abcdef1234567890ab', '2021001', 100.00, 500.00, 1001, NULL, '张小明', '110101200001011234', '2026-01-17 02:23:06', '2026-01-17 02:23:06', NULL);
INSERT INTO `user_app` VALUES (102, 2002, 2, 'o1234567890abcdef1234567890ac', '2021002', 200.50, 800.00, 1001, NULL, '李小红', '110101200001021235', '2026-01-17 02:23:06', '2026-01-17 02:23:06', NULL);
INSERT INTO `user_app` VALUES (103, 2003, 1, 'o1234567890abcdef1234567890ad', '2021003', 50.00, 300.00, 1001, NULL, '王小刚', '110101200001031236', '2026-01-17 02:23:06', '2026-01-17 02:23:06', NULL);
INSERT INTO `user_app` VALUES (104, 2004, 2, 'o1234567890abcdef1234567890ae', '2021004', 150.00, 600.00, 1002, NULL, '赵小美', '110101200001041237', '2026-01-17 02:23:06', '2026-01-17 02:23:06', NULL);
INSERT INTO `user_app` VALUES (901, 9001, 1, 'o1234567890abcdef1234567890zz', '2099999', 0.00, 0.00, 1001, NULL, '被拉黑用户', '110101200001019999', '2026-01-17 02:24:02', '2026-01-17 02:24:02', NULL);
INSERT INTO `user_app` VALUES (1001, 2005, 1, 'o1234567890abcdef1234567890af', '2021005', 80.00, 400.00, 1002, NULL, '陈小龙', '110101200001051238', '2026-01-17 02:23:06', '2026-01-27 06:50:37', NULL);

-- ----------------------------
-- Table structure for user_mch
-- ----------------------------
DROP TABLE IF EXISTS `user_mch`;
CREATE TABLE `user_mch`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `base_user_id` bigint UNSIGNED NOT NULL,
  `mch_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商户名',
  `address_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联表地址id',
  `logo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商户logo',
  `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `business_license_urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营业执照路径，以\",\"分割',
  `partner_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '合伙人id',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `minimum_order_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最低起送金额(默认0)',
  `card_number` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '银行卡号',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审查表id（审查商户资格）',
  `business_start_time` time NULL DEFAULT NULL COMMENT '开始营业时间',
  `business_end_time` time NULL DEFAULT NULL COMMENT '结束营业时间',
  `is_open` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否营业 0-休息 1-营业（默认休息）',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '累计收益',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_user_id`(`base_user_id` ASC) USING BTREE,
  INDEX `idx_delete_time`(`delete_time` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE,
  INDEX `idx_is_open`(`is_open` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 902 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_mch
-- ----------------------------
INSERT INTO `user_mch` VALUES (301, 4001, '校园便利店', 1, 'https://example.com/logo/mch1.jpg', '张老板', 'https://example.com/license/mch1.jpg', 5001, '110101198001011234', 10.00, '6222021234567890126', 2001, '08:00:00', '22:00:00', 1, NULL, 0.00, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `user_mch` VALUES (302, 4002, '美味餐厅', 2, 'https://example.com/logo/mch2.jpg', '李大厨', 'https://example.com/license/mch2.jpg', 5001, '110101198002021235', 20.00, '6222021234567890127', 2002, '10:00:00', '21:00:00', 1, NULL, 0.00, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `user_mch` VALUES (303, 4003, '水果超市', 3, 'https://example.com/logo/mch3.jpg', '王经理', 'https://example.com/license/mch3.jpg', 5002, '110101198003031236', 15.00, '6222021234567890128', 2003, '09:00:00', '20:00:00', 0, NULL, 0.00, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `user_mch` VALUES (901, 9003, '待审核商家', 5, 'https://example.com/logo/pending_mch.jpg', '待审核联系人', 'https://example.com/license/pending_mch.jpg', 5001, '110101198001019999', 10.00, '6222021234567890998', 3003, '08:00:00', '22:00:00', 0, NULL, 0.00, '2026-01-17 02:24:02', '2026-01-17 02:24:02', NULL);

-- ----------------------------
-- Table structure for user_partner
-- ----------------------------
DROP TABLE IF EXISTS `user_partner`;
CREATE TABLE `user_partner`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '合伙人ID',
  `base_user_id` bigint NOT NULL,
  `partner_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '合伙人姓名',
  `invite_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推广码(邀请合伙人)',
  `invite_code_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推广码相对路径',
  `card_number` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行卡号',
  `commission_rate` decimal(5, 2) NOT NULL DEFAULT 0.00 COMMENT '分佣比例(%)',
  `parent_id` bigint UNSIGNED NULL DEFAULT 0 COMMENT '上级合伙人ID(0代表无上级)',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '关联的审核表id',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总收益',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`invite_code` ASC) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC, `id` ASC) USING BTREE,
  CONSTRAINT `chk_partner_rate` CHECK ((`commission_rate` >= 0) and (`commission_rate` <= 100))
) ENGINE = InnoDB AUTO_INCREMENT = 20012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '合伙人表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_partner
-- ----------------------------
INSERT INTO `user_partner` VALUES (401, 5001, '吴大明', 'INVITE001', 'https://example.com/qrcode/partner1.jpg', '6222021234567890129', 5.00, 0, 3001, NULL, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `user_partner` VALUES (402, 5002, '郑小红', 'INVITE002', 'https://example.com/qrcode/partner2.jpg', '6222021234567890130', 6.00, 401, 3002, NULL, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `user_partner` VALUES (403, 5003, '王小华', 'INVITE003', 'https://example.com/qrcode/partner3.jpg', '6222021234567890131', 4.50, 401, 3003, NULL, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL);
INSERT INTO `user_partner` VALUES (901, 9004, '待审核合伙人', 'INVITE999', 'https://example.com/qrcode/pending_partner.jpg', '6222021234567890997', 5.00, 0, 2003, NULL, NULL, '2026-01-17 02:24:02', '2026-01-17 02:24:02', NULL);

-- ----------------------------
-- Table structure for user_rider
-- ----------------------------
DROP TABLE IF EXISTS `user_rider`;
CREATE TABLE `user_rider`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `base_user_id` bigint UNSIGNED NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `emergency_contact_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '紧急联系人姓名',
  `emergency_contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '紧急联系人电话',
  `real_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `id_card_front` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证正面url',
  `id_card_back` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证反面url',
  `openid` char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联表审核表id（审核棋手资质）',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计收益',
  `address_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联表地址id',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  `card_number` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '银行卡号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_user_id`(`base_user_id` ASC) USING BTREE,
  UNIQUE INDEX `idx_openid`(`openid` ASC) USING BTREE,
  INDEX `idx_delete_time`(`delete_at` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 902 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_rider
-- ----------------------------
INSERT INTO `user_rider` VALUES (201, 3001, NULL, NULL, NULL, '刘大伟', '110101199001011234', NULL, NULL, NULL, 1001, NULL, NULL, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL, '6222021234567890123');
INSERT INTO `user_rider` VALUES (202, 3002, NULL, NULL, NULL, '孙小芳', '110101199002021235', NULL, NULL, NULL, 1002, NULL, NULL, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL, '6222021234567890124');
INSERT INTO `user_rider` VALUES (203, 3003, NULL, NULL, NULL, '周小军', '110101199003031236', NULL, NULL, NULL, 1003, NULL, NULL, NULL, '2026-01-17 02:24:01', '2026-01-17 02:24:01', NULL, '6222021234567890125');
INSERT INTO `user_rider` VALUES (901, 9002, NULL, NULL, NULL, '待审核骑手', '110101199901019999', NULL, NULL, NULL, 1003, NULL, NULL, NULL, '2026-01-17 02:24:02', '2026-01-17 02:24:02', NULL, '6222021234567890999');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `uk_user_role`(`user_id` ASC, `role_id` ASC) USING BTREE,
  INDEX `idx_role`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1001, 1);
INSERT INTO `user_role` VALUES (1002, 2);
INSERT INTO `user_role` VALUES (1003, 3);
INSERT INTO `user_role` VALUES (1004, 4);
INSERT INTO `user_role` VALUES (1005, 5);

-- ----------------------------
-- Table structure for user_sys
-- ----------------------------
DROP TABLE IF EXISTS `user_sys`;
CREATE TABLE `user_sys`  (
  `id` bigint NOT NULL,
  `base_user_id` bigint NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_user_id`(`base_user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_sys
-- ----------------------------
INSERT INTO `user_sys` VALUES (1, 1001, '张三', 1);
INSERT INTO `user_sys` VALUES (2, 1002, '李四', 1);
INSERT INTO `user_sys` VALUES (3, 1003, '王五', 2);
INSERT INTO `user_sys` VALUES (4, 1004, '赵六', 1);
INSERT INTO `user_sys` VALUES (5, 1005, '测试员', 0);

SET FOREIGN_KEY_CHECKS = 1;
