/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_errand_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 10/02/2026 15:26:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限字符',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用 1-启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_name`(`role_name` ASC) USING BTREE,
  UNIQUE INDEX `uk_role_code`(`role_code` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_role_code_status`(`role_code` ASC, `status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of app_role
-- ----------------------------
INSERT INTO `app_role` VALUES (1, '普通用户', 'NORMAL', 1, 1, '2025-12-09 19:50:07', '2025-12-09 19:50:07');
INSERT INTO `app_role` VALUES (2, '服务人员', 'SERVICE_STAFF', 2, 1, '2025-12-09 19:50:07', '2025-12-09 19:50:07');

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码(BCrypt加密,默认手机号后6位)',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学号',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像URL',
  `commission_total` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '分佣收益(累计金额)',
  `balance` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '账户余额(元)',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属学校ID',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '关联审批表id(用户身份/角色审批)',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-已禁用 1-已启用',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `open_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户的openId',
  `gender` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone` ASC) USING BTREE,
  UNIQUE INDEX `uk_openid`(`open_id` ASC) USING BTREE,
  INDEX `idx_school`(`school_id` ASC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC, `id` ASC) USING BTREE,
  INDEX `uk_code`(`code` ASC) USING BTREE,
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE,
  CONSTRAINT `chk_balance` CHECK (`balance` >= 0),
  CONSTRAINT `chk_commission` CHECK (`commission_total` >= 0)
) ENGINE = InnoDB AUTO_INCREMENT = 4018 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'C端用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES (1, 'user', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', 'test', '13801000001', '/uploads/2025-12-15/af43ed47cb6b4824a9dfc20804a4e94f.png', 0.00, 100.00, 1, 1072, 1, '2026-01-31 11:44:45', '0:0:0:0:0:0:0:1', '2025-12-09 20:57:55', '2026-01-31 03:39:13', NULL, NULL, '男', '张三', 'aaa');
INSERT INTO `app_user` VALUES (2, 'zhangsan', '$2a$10$N9qo8uLOickgx2ZMRZoMyeAJZv6f6c6HfH2q3q5vJ7T8K9l0m1n2o3', '20210001', '13800000001', 'https://example.com/avatar1.jpg', 100.50, 500.00, 1, 101, 0, '2024-01-15 10:30:00', '192.168.1.101', '2023-09-01 08:00:00', '2025-12-12 09:54:15', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (3, 'lisi', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '20210002', '13800000002', 'https://example.com/avatar2.jpg', 350.00, 1100.50, 1, 102, 1, '2024-01-14 15:20:00', '192.168.1.102', '2023-09-02 09:10:00', '2026-01-26 11:16:32', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (4, 'wangwu', '$2a$10$BcDeFgHiJkLmNoPqRsTuVwXyZ0123456789ABCDEFGHIJKLMNOPQ', '20210003', '13800000003', 'https://example.com/avatar3.jpg', 80.00, 300.25, 2, 103, 1, '2024-01-13 14:45:00', '192.168.1.103', '2023-09-03 10:20:00', '2025-12-12 09:54:25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (5, 'zhaoliu', '$2a$10$CdEfGhIjKlMnOpQrStUvWxYz0123456789ABCDEFGHIJKLMNOPQR', '20210004', '13800000004', 'https://example.com/avatar4.jpg', 150.75, 800.00, 2, 104, 1, '2024-01-12 11:30:00', '192.168.1.104', '2023-09-04 11:30:00', '2025-12-12 09:54:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (6, 'qianqi', '$2a$10$DeFgHiJkLmNoPqRsTuVwXyZ0123456789ABCDEFGHIJKLMNOPQRS', '20210005', '13800000005', 'https://example.com/avatar5.jpg', 300.00, 1500.00, 3, 105, 1, '2024-01-11 16:40:00', '192.168.1.105', '2023-09-05 12:40:00', '2025-12-12 09:54:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (7, 'sunba', '$2a$10$EfGhIjKlMnOpQrStUvWxYz0123456789ABCDEFGHIJKLMNOPQRST', '20210006', '13800000006', 'https://example.com/avatar6.jpg', 45.50, 200.00, 3, 106, 0, '2024-01-10 09:15:00', '192.168.1.106', '2023-09-06 13:50:00', '2025-12-12 09:54:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (8, 'zhoujiu', '$2a$10$FgHiJkLmNoPqRsTuVwXyZ0123456789ABCDEFGHIJKLMNOPQRSTU', '20210007', '13800000007', 'https://example.com/avatar7.jpg', 180.25, 950.75, 4, 107, 0, '2024-01-09 13:25:00', '192.168.1.107', '2023-09-07 14:00:00', '2025-12-12 09:55:06', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (9, 'wushi', '$2a$10$GhIjKlMnOpQrStUvWxYz0123456789ABCDEFGHIJKLMNOPQRSTUV', '20210008', '13800000008', 'https://example.com/avatar8.jpg', 220.00, 1100.00, 4, 108, 1, '2024-01-08 17:50:00', '192.168.1.108', '2023-09-08 15:10:00', '2025-12-12 09:55:15', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (10, 'zhengyi', '$2a$10$HiJkLmNoPqRsTuVwXyZ0123456789ABCDEFGHIJKLMNOPQRSTUVWX', '20210009', '13800000009', 'https://example.com/avatar9.jpg', 75.80, 450.30, 5, 109, 1, '2024-01-07 08:45:00', '192.168.1.109', '2023-09-09 16:20:00', '2025-12-12 09:55:24', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (11, 'fengshier', '$2a$10$IjKlMnOpQrStUvWxYz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ', '20210010', '13800000010', 'https://example.com/avatar10.jpg', 500.25, 2500.00, 5, 110, 1, '2024-01-06 20:10:00', '192.168.1.110', '2023-09-10 17:30:00', '2025-12-12 09:55:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (12, 'xu', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '21212121', '13822222222', 'https://example.com/avatar10.jpg', 0.00, 0.00, 5, 111, 1, '2026-01-09 11:36:19', '0:0:0:0:0:0:0:1', '2025-12-15 09:38:39', '2025-12-22 20:13:22', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (4006, '测试昵称', '$2a$10$oiRHRKNukInkn7hXghYdtuCcp.0iNhjHmIUtApXIvdHjOMppa4gVu', NULL, NULL, 'http://101.43.233.201:8002/uploads/2026-01-31/b323f7d2b0cc44c69faa07734ed28e67.jpg', 0.00, 0.00, 10019, 1074, 1, '2026-02-01 22:36:00', '127.0.0.1', '2025-12-19 17:48:58', '2026-01-31 15:44:15', NULL, 'oOYcH7Z05snsWaY1VGsLG03d-8sg', '男', NULL, '测试昵称22');
INSERT INTO `app_user` VALUES (4007, 'wx_oR05k1_x_ee67d1', '$2a$10$dEUBxC8B/Ki2KRA.sDIvN.3JiX7NWcBnsM5nTcu6d3.h0hI5MIkzO', NULL, NULL, NULL, 0.00, 0.00, NULL, NULL, 1, '2026-01-08 14:18:57', '0:0:0:0:0:0:0:1', '2025-12-19 20:24:22', '2026-01-05 09:27:55', NULL, 'oR05k1_xrvGXxIAScNGd1XLd9ml8', '女', NULL, NULL);
INSERT INTO `app_user` VALUES (4008, 'wx_oOYcH7cu_bb5a73', '$2a$10$gpIUXIcW5fPYSzN.TCOsY.EHmIfF402ZWz9PnrlBZ5y/Clr9m8B4m', NULL, NULL, 'http://172.16.8.163:8094/uploads/2026-01-27/173b4f5ed11a47e299b5f023422f8449.jpeg', 0.00, 0.00, 10022, 1083, 1, '2026-02-01 21:53:55', '127.0.0.1', '2025-12-19 20:58:45', '2026-01-31 03:37:22', NULL, 'oOYcH7cu5fIihx3Il4TQOASNXpKE', '男', NULL, 'aaa');
INSERT INTO `app_user` VALUES (4009, '塔斯汀', '$2a$10$oRUuFmJj6d6XW8BH00TofOiPfWIqExuUB96bkIZuEz4B8D729zSdy', NULL, NULL, 'http://172.16.8.163:8094/uploads/2026-01-17/4407938386a745eca06900d6d0ab3eaa.jpeg', 0.00, 0.02, 10022, 1076, 1, '2026-02-01 22:46:49', '127.0.0.1', '2025-12-27 14:26:24', '2026-02-01 21:54:46', NULL, 'oOYcH7dHzekxZm_DmJ-vNWvqRTcM', NULL, '张三', NULL);
INSERT INTO `app_user` VALUES (4010, 'wx_oOYcH7X4_60c934', '$2a$10$rkOJ7AvCtz9l0KTWdAr0O.LjdR/4sAP5SZTL7ve8Y28tLT1eG2ME.', NULL, NULL, 'http://10.2.24.6:8002/uploads/2026-01-31/d193f30b94ea408cbae163bcb00ab127.jpg', 0.00, 0.00, 10009, NULL, 1, '2026-01-31 10:56:01', '127.0.0.1', '2026-01-26 17:49:16', '2026-01-31 10:47:13', NULL, 'oOYcH7X4GPUqOCni5qa6bO3dSJJQ', '男', NULL, 'jpa');
INSERT INTO `app_user` VALUES (4011, 'wx_oOYcH7XK_97d79a', '$2a$10$G5uKVpPsS50wom2q2pYkQOI/GeTuh6scfrPXPonB.RYcKkRM48tL.', NULL, NULL, NULL, 0.00, 0.00, 10009, 1082, 1, '2026-01-31 11:34:36', '127.0.0.1', '2026-01-26 17:57:59', '2026-01-31 10:34:12', NULL, 'oOYcH7XKQcDSJXJX3Ll5lJmic4Jw', NULL, NULL, NULL);
INSERT INTO `app_user` VALUES (4012, '伊丽莎白鼠', '$2a$10$2svbtlu1spwawgvZ20oNreg6Aaa1f5/JIiTHy5qjZgQ5ATSx3R1fe', NULL, '15987189785', '/uploads/2026-01-31/wx_avatar_7487b08a8d1a4fc5b775d5285e2e999a.jpg', 0.00, 0.00, 10022, NULL, 1, '2026-01-31 11:06:59', '127.0.0.1', '2026-01-27 14:55:38', '2026-01-31 11:06:59', NULL, 'oOYcH7X9OrC4WGM7TN4YBACPRe7E', '男', NULL, NULL);
INSERT INTO `app_user` VALUES (4013, '微信用户', '$2a$10$084ehqOEsWNsk8uAfxtLmOPfQoZNo3qRAMIyiWC9/nrykH4Ifq.Nm', NULL, '15512982628', 'http://tmp/xN64EKB9rhJQce147a69381c67a70a1378cff41c1b7f.png', 0.00, 0.00, 10018, 1088, 1, '2026-01-29 16:32:48', '172.16.11.89', '2026-01-27 14:59:38', '2026-01-30 12:01:54', NULL, 'oOYcH7VGxj_Q3AGCJoJQ04EKW7YM', '男', NULL, '贺');
INSERT INTO `app_user` VALUES (4014, '晚星叙', '$2a$10$RJ.JArdV6w/ITclpstZeoONBwAbA0aRwFIUmY/xLg/ACe/GCaYr5.', NULL, '16631253930', '/uploads/2026-02-02/d3441f81ef7047d28ec981533973c5c2.jpg', 0.00, 0.00, 10022, 1087, 1, '2026-01-31 10:29:28', '127.0.0.1', '2026-01-27 15:25:12', '2026-02-02 09:03:03', NULL, 'oOYcH7YLYnnsofuICjl2oZneyY0U', '男', NULL, '晚星叙');
INSERT INTO `app_user` VALUES (4015, 'wx_oOYcH7YH_17049e', '$2a$10$lgOLyy9nuU2kDHb9ktUcjermqsadg4YpJC1F2DpL3A.hnulhXXQt.', NULL, '15003246516', NULL, 0.00, 0.00, 10018, 1086, 1, '2026-01-27 16:37:42', '172.16.9.253', '2026-01-27 15:39:04', '2026-01-31 10:43:10', NULL, 'oOYcH7YH4KDIQuyNAnKK-txIh0oY', '女', NULL, '大白鼠二号');
INSERT INTO `app_user` VALUES (4016, 'wx_oOYcH7SP_955076', '$2a$10$Mtqx9iNV8WLOgIiRmPmNEOhWrwzgKzt4Pz9Y2iPXnaw8i5Crh9zzG', NULL, '19931722823', NULL, 0.00, 0.00, 10005, 1085, 1, '2026-01-27 16:00:22', '172.16.8.135', '2026-01-27 15:56:50', '2026-01-30 10:59:28', NULL, 'oOYcH7SPpdamGNsWQCSqXyhVYg_U', '男', NULL, '月亮不心动');
INSERT INTO `app_user` VALUES (4017, 'wx_oOYcH7Wp_52e617', '$2a$10$BdFUXTczflxUVAQWZputvut8eWDK0aoqIpfohkXBMw/55ILURC9SK', NULL, '15944702580', '/uploads/2026-01-30/1aa6d12a56144b90976820c2e2084692.jpeg', 0.00, 0.00, 10019, 1084, 1, '2026-02-02 09:08:49', '127.0.0.1', '2026-01-27 15:59:15', '2026-01-31 10:33:11', NULL, 'oOYcH7WpPbv6_P-QMkVABtxbWACs', '男', NULL, 'Passerby.');

-- ----------------------------
-- Table structure for app_user_address
-- ----------------------------
DROP TABLE IF EXISTS `app_user_address`;
CREATE TABLE `app_user_address`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '地址ID，自增主键',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID，关联用户表',
  `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系人手机号',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '详细地址（如：某某社区1号楼）',
  `door_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '门牌号',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认地址：1-是，0-否',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除：1-已删除，0-未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_is_default`(`is_default` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户地址表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of app_user_address
-- ----------------------------
INSERT INTO `app_user_address` VALUES (1, 1, '张三', '13800138000', '某某社区2号楼', '101室', 1, '2025-12-12 09:28:46', '2025-12-16 16:57:22', 1);
INSERT INTO `app_user_address` VALUES (2, 1, '张三', '13800138000', '某某社区1号楼', '101室', 0, '2025-12-16 16:54:22', '2026-01-02 19:43:16', 0);
INSERT INTO `app_user_address` VALUES (3, 4006, '测试1', '18111111111', '长沙师范二号楼', '2666', 0, '2025-12-24 10:28:02', '2025-12-24 11:47:48', 1);
INSERT INTO `app_user_address` VALUES (4, 4006, '往往', '16111111111', '沧州师范学院2号楼', '2777', 0, '2025-12-24 10:29:00', '2026-01-26 11:14:57', 0);
INSERT INTO `app_user_address` VALUES (5, 4006, '悄悄', '15111111111', '沧州师范6号楼', '1333', 0, '2025-12-24 10:30:45', '2025-12-27 11:16:14', 0);
INSERT INTO `app_user_address` VALUES (6, 4006, '忍忍', '19333111111', '测试小区2号楼', '2628', 0, '2025-12-24 10:38:53', '2026-01-20 02:24:25', 0);
INSERT INTO `app_user_address` VALUES (7, 4006, '冲冲冲', '16111111111', '测试小区七号楼', '2483', 1, '2025-12-24 10:43:16', '2025-12-24 11:47:55', 1);
INSERT INTO `app_user_address` VALUES (8, 4009, 'cs', '13908764657', '沧州师范学院', '2638', 1, '2025-12-29 12:05:08', '2026-01-17 15:03:40', 1);
INSERT INTO `app_user_address` VALUES (9, 1, '小明2', '13004387618', '河北石家庄裕华', '丽水清远', 1, '2026-01-02 14:58:53', '2026-01-02 19:51:44', 0);
INSERT INTO `app_user_address` VALUES (10, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:37', '2026-01-17 15:03:58', 1);
INSERT INTO `app_user_address` VALUES (11, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:39', '2026-01-17 15:04:07', 1);
INSERT INTO `app_user_address` VALUES (12, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:39', '2026-01-17 15:03:53', 1);
INSERT INTO `app_user_address` VALUES (13, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:39', '2026-01-17 15:03:50', 1);
INSERT INTO `app_user_address` VALUES (14, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:39', '2026-01-17 15:04:02', 1);
INSERT INTO `app_user_address` VALUES (15, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:40', '2026-01-17 15:03:56', 1);
INSERT INTO `app_user_address` VALUES (16, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:40', '2026-01-17 15:04:05', 1);
INSERT INTO `app_user_address` VALUES (17, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 0, '2026-01-17 09:07:40', '2026-01-17 15:04:12', 1);
INSERT INTO `app_user_address` VALUES (18, 4009, '张三', '13908736458', '大同大学8号楼101', NULL, 1, '2026-01-17 09:07:42', '2026-01-17 16:11:17', 1);
INSERT INTO `app_user_address` VALUES (19, 4009, '张三', '13800138000', '某某社区1号楼', '101室', 0, '2026-01-17 15:23:46', '2026-01-17 16:11:14', 1);
INSERT INTO `app_user_address` VALUES (20, 4009, '老吴', '13902484744', '河北师范大学', '2001号11楼', -1, '2026-01-17 16:02:41', '2026-01-19 09:55:50', 1);
INSERT INTO `app_user_address` VALUES (21, 4009, '张三', '13902937845', '师范大学', '100示', -1, '2026-01-17 16:05:43', '2026-01-17 16:06:14', 1);
INSERT INTO `app_user_address` VALUES (22, 4009, '王五二', '15193846743', '张家口职业技术学院', '10号楼601室', 0, '2026-01-17 16:07:33', '2026-01-26 10:26:24', 0);
INSERT INTO `app_user_address` VALUES (23, 4009, '张武', '13904945785', '张家口职业技术学院', '2号楼602', 1, '2026-01-17 16:08:46', '2026-01-17 16:11:21', 1);
INSERT INTO `app_user_address` VALUES (24, 4009, '安其拉', '13904856343', '张家口职业技术学院', '3号楼601室', 0, '2026-01-17 16:12:07', '2026-01-26 10:26:28', 0);
INSERT INTO `app_user_address` VALUES (25, 4009, '312', '13904595743', '123', '231', -1, '2026-01-17 16:12:52', '2026-01-17 16:19:14', 1);
INSERT INTO `app_user_address` VALUES (26, 4009, '1111', '13904945785', '111', '222', -1, '2026-01-17 16:19:31', '2026-01-17 16:19:59', 1);
INSERT INTO `app_user_address` VALUES (27, 4009, '132', '13904595734', '123', '12312', -1, '2026-01-17 16:21:20', '2026-01-17 16:25:56', 1);
INSERT INTO `app_user_address` VALUES (28, 4009, '111', '15193948743', '11111', '111', -1, '2026-01-17 16:25:17', '2026-01-17 16:25:53', 1);
INSERT INTO `app_user_address` VALUES (29, 4009, '123', '13948978888', '123', '12312', -1, '2026-01-17 16:25:47', '2026-01-17 16:25:51', 1);
INSERT INTO `app_user_address` VALUES (30, 4009, '老孙', '13904856778', '河北师范大学', '2号楼0489', -1, '2026-01-19 09:15:15', '2026-01-19 09:15:23', 1);
INSERT INTO `app_user_address` VALUES (31, 4009, '21', '13990485677', '123', '1222', -1, '2026-01-19 09:16:50', '2026-01-19 09:51:23', 1);
INSERT INTO `app_user_address` VALUES (32, 4009, '章鱼哥', '13804759443', '沧州交通', '17号楼9304', 0, '2026-01-20 10:07:00', '2026-01-26 09:27:21', 1);
INSERT INTO `app_user_address` VALUES (33, 4006, '测试1', '18131212121', '沧州师范学校', '2620', 0, '2026-01-21 15:30:49', '2026-01-29 19:45:22', 0);
INSERT INTO `app_user_address` VALUES (34, 4006, '测试22', '18111111111', '河北师范大学', '2111', 0, '2026-01-21 16:01:32', '2026-01-21 13:06:13', 0);
INSERT INTO `app_user_address` VALUES (35, 4006, '诺', '18111111111', '江苏省南京市江宁区盛江花苑', '2111', 1, '2026-01-21 21:06:10', '2026-01-29 19:45:22', 0);
INSERT INTO `app_user_address` VALUES (36, 4009, '2312312', '13903494723', '2133231', '2312', -1, '2026-01-24 14:45:33', '2026-01-26 09:18:29', 1);
INSERT INTO `app_user_address` VALUES (37, 4009, '2314123', '13904897533', '213124', '2143241', -1, '2026-01-24 14:45:55', '2026-01-26 09:18:26', 1);
INSERT INTO `app_user_address` VALUES (38, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', -1, '2026-01-26 09:19:23', '2026-01-26 09:19:38', 1);
INSERT INTO `app_user_address` VALUES (39, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', -1, '2026-01-26 09:19:23', '2026-01-26 09:19:43', 1);
INSERT INTO `app_user_address` VALUES (40, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', -1, '2026-01-26 09:19:23', '2026-01-26 09:19:45', 1);
INSERT INTO `app_user_address` VALUES (41, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', -1, '2026-01-26 09:19:23', '2026-01-26 09:19:48', 1);
INSERT INTO `app_user_address` VALUES (42, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', 0, '2026-01-26 09:19:23', '2026-01-26 10:26:50', 0);
INSERT INTO `app_user_address` VALUES (43, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', -1, '2026-01-26 09:19:23', '2026-01-26 09:19:53', 1);
INSERT INTO `app_user_address` VALUES (44, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', -1, '2026-01-26 09:19:24', '2026-01-26 09:19:33', 1);
INSERT INTO `app_user_address` VALUES (45, 4009, '韩宝', '13904857743', '清华大学', '2号楼8901', -1, '2026-01-26 09:19:24', '2026-01-26 09:19:36', 1);
INSERT INTO `app_user_address` VALUES (46, 4009, '章武', '13609487474', '清华大学', '10号楼1001', -1, '2026-01-26 09:20:31', '2026-01-26 09:27:17', 1);
INSERT INTO `app_user_address` VALUES (47, 4009, '张三', '13603489478', '滨海新区', '幸福路100111号', 1, '2026-01-26 09:28:02', '2026-01-26 15:58:42', 0);
INSERT INTO `app_user_address` VALUES (48, 4009, '老王', '13904856754', '滨海新区', '2号楼109', 0, '2026-01-26 09:28:47', '2026-01-26 10:26:35', 0);
INSERT INTO `app_user_address` VALUES (49, 4009, '老陈', '13903489474', '大城县一中', '2号楼801', 0, '2026-01-26 10:21:34', '2026-01-26 10:26:34', 0);
INSERT INTO `app_user_address` VALUES (50, 4009, '老楚', '13904895755', '大城县一中', '10号楼101', 0, '2026-01-26 10:23:20', '2026-01-26 10:26:32', 0);
INSERT INTO `app_user_address` VALUES (51, 4010, '南迪', '15175837597', '西门', '101', 1, '2026-01-26 17:54:19', '2026-01-26 17:54:19', 0);
INSERT INTO `app_user_address` VALUES (52, 4010, '南迪', '15175837597', '西门', '102', -1, '2026-01-26 17:54:41', '2026-01-26 17:54:41', 0);
INSERT INTO `app_user_address` VALUES (53, 4009, '张三', '13800138000', '某某社区1号楼', NULL, 0, '2026-01-26 19:03:14', '2026-01-26 19:03:14', 0);
INSERT INTO `app_user_address` VALUES (54, 4015, '明先', '15004359685', '河北省石家庄市桥西区', '丽水沁园', 1, '2026-01-29 15:02:50', '2026-01-29 18:42:18', 0);
INSERT INTO `app_user_address` VALUES (55, 4015, 'bear', '16604593485', '河北省保定市容城县', '阳光花园', 0, '2026-01-29 16:20:26', '2026-01-29 16:20:26', 0);
INSERT INTO `app_user_address` VALUES (56, 4015, '超111', '18839483472', '河北省邢台市襄都区', '小区111', 0, '2026-01-29 16:22:03', '2026-01-29 16:40:58', 1);
INSERT INTO `app_user_address` VALUES (57, 4016, '邢福林', '19931722123', '辽宁省鞍山市铁西区', '111', 1, '2026-01-29 17:02:30', '2026-01-29 18:03:22', 0);
INSERT INTO `app_user_address` VALUES (58, 4016, '刘亚超', '19931722823', '河北省邢台市临城县', '101', 0, '2026-01-29 17:30:23', '2026-01-29 18:47:51', 1);
INSERT INTO `app_user_address` VALUES (59, 4013, '小贺', '15512982628', '河北省秦皇岛市北戴河区', '803', 0, '2026-01-29 17:31:51', '2026-01-29 17:48:23', 0);
INSERT INTO `app_user_address` VALUES (60, 4013, '小王', '19322575585', '河北省唐山市路南区', '222', 0, '2026-01-29 17:48:23', '2026-01-30 09:30:11', 1);
INSERT INTO `app_user_address` VALUES (61, 4013, '小王', '14755849659', '北京市北京市东城区', '81', 0, '2026-01-29 17:50:03', '2026-01-30 09:30:44', 0);
INSERT INTO `app_user_address` VALUES (62, 4013, '11', '15512982628', '北京市北京市东城区', '11', 1, '2026-01-29 17:50:46', '2026-01-29 18:00:40', 1);
INSERT INTO `app_user_address` VALUES (63, 4016, '王建雄', '19931722345', '河北省邢台市柏乡县', '201', 0, '2026-01-29 18:52:32', '2026-01-29 18:52:55', 1);
INSERT INTO `app_user_address` VALUES (64, 4013, '小李', '15512982628', '北京市北京市东城区', '11', 1, '2026-01-29 19:02:49', '2026-01-29 19:02:53', 1);
INSERT INTO `app_user_address` VALUES (65, 4014, '小飞贺', '15511281662', '河北省衡水市安平县', '邓庄村衡水第一杆', 1, '2026-01-30 09:04:51', '2026-01-30 09:26:20', 0);
INSERT INTO `app_user_address` VALUES (66, 4014, '小王', '16631253930', '河北省石家庄市桥西区', '河北师大科技楼C座', 0, '2026-01-30 09:26:58', '2026-01-30 09:27:33', 0);
INSERT INTO `app_user_address` VALUES (67, 4013, '王爽1', '15297692112', '河北省石家庄市裕华区', '432', 0, '2026-01-30 09:30:44', '2026-01-30 09:31:05', 0);
INSERT INTO `app_user_address` VALUES (68, 4014, '测试王', '15511281662', '北京市北京市东城区', '测试', 0, '2026-01-30 09:34:31', '2026-01-30 09:34:40', 1);
INSERT INTO `app_user_address` VALUES (69, 4014, '111111111', '15511281661', '北京市北京市西城区', '1111111', 0, '2026-01-30 09:43:06', '2026-01-30 09:43:16', 1);

-- ----------------------------
-- Table structure for app_user_role
-- ----------------------------
DROP TABLE IF EXISTS `app_user_role`;
CREATE TABLE `app_user_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_role`(`user_id` ASC, `role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '应用用户角色中间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of app_user_role
-- ----------------------------
INSERT INTO `app_user_role` VALUES (4, 4, 2);
INSERT INTO `app_user_role` VALUES (3, 5, 2);
INSERT INTO `app_user_role` VALUES (5, 6, 2);
INSERT INTO `app_user_role` VALUES (6, 7, 2);
INSERT INTO `app_user_role` VALUES (2, 4004, 1);
INSERT INTO `app_user_role` VALUES (9, 4006, 1);
INSERT INTO `app_user_role` VALUES (10, 4006, 2);
INSERT INTO `app_user_role` VALUES (7, 4009, 1);
INSERT INTO `app_user_role` VALUES (8, 4009, 2);
INSERT INTO `app_user_role` VALUES (11, 4010, 1);
INSERT INTO `app_user_role` VALUES (12, 4011, 1);
INSERT INTO `app_user_role` VALUES (13, 4011, 2);
INSERT INTO `app_user_role` VALUES (14, 4012, 1);
INSERT INTO `app_user_role` VALUES (15, 4013, 1);
INSERT INTO `app_user_role` VALUES (24, 4013, 2);
INSERT INTO `app_user_role` VALUES (16, 4014, 1);
INSERT INTO `app_user_role` VALUES (23, 4014, 2);
INSERT INTO `app_user_role` VALUES (17, 4015, 1);
INSERT INTO `app_user_role` VALUES (22, 4015, 2);
INSERT INTO `app_user_role` VALUES (18, 4016, 1);
INSERT INTO `app_user_role` VALUES (21, 4016, 2);
INSERT INTO `app_user_role` VALUES (19, 4017, 1);
INSERT INTO `app_user_role` VALUES (20, 4017, 2);

-- ----------------------------
-- Table structure for app_user_school
-- ----------------------------
DROP TABLE IF EXISTS `app_user_school`;
CREATE TABLE `app_user_school`  (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `school_id` bigint NOT NULL COMMENT '学校id',
  PRIMARY KEY (`user_id`, `school_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-学校（社区）关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app_user_school
-- ----------------------------
INSERT INTO `app_user_school` VALUES (1, 1);
INSERT INTO `app_user_school` VALUES (1, 2);
INSERT INTO `app_user_school` VALUES (1, 3);
INSERT INTO `app_user_school` VALUES (4006, 10004);
INSERT INTO `app_user_school` VALUES (4006, 10005);
INSERT INTO `app_user_school` VALUES (4006, 10008);
INSERT INTO `app_user_school` VALUES (4008, 10005);
INSERT INTO `app_user_school` VALUES (4009, 10004);
INSERT INTO `app_user_school` VALUES (4009, 10005);
INSERT INTO `app_user_school` VALUES (4009, 10010);
INSERT INTO `app_user_school` VALUES (4009, 10021);
INSERT INTO `app_user_school` VALUES (4011, 10004);

-- ----------------------------
-- Table structure for app_user_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `app_user_withdrawal`;
CREATE TABLE `app_user_withdrawal`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '提现ID',
  `withdrawal_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现单号',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `amount` decimal(15, 2) NOT NULL COMMENT '提现金额(元)',
  `actual_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '实际到账金额(元)',
  `withdraw_type` tinyint NOT NULL COMMENT '提现方式:1-微信 2-支付宝 3-银行卡',
  `withdraw_account` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现账号',
  `withdraw_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现人姓名',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '关联的审核记录ID',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '打款状态:0-未打款 1-已打款 2-打款失败',
  `pay_operator_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '打款操作人ID',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '打款时间',
  `pay_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打款备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `balanceAfter` decimal(10, 2) NULL DEFAULT NULL COMMENT '提现后余额',
  `balanceBefore` decimal(10, 2) NULL DEFAULT NULL COMMENT '提现前余额',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `statistics_day` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_no`(`withdrawal_no` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_status`(`pay_status` ASC) USING BTREE,
  INDEX `idx_time`(`created_at` ASC) USING BTREE,
  INDEX `fk_withdrawal_payer`(`pay_operator_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE,
  INDEX `idx_withdraw_type`(`withdraw_type` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户提现记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of app_user_withdrawal
-- ----------------------------
INSERT INTO `app_user_withdrawal` VALUES (7, 'WD202312120001', 1, 100.00, 99.00, 1, 'wechat_openid_123', '张三', 1031, 1, 1, '2025-12-12 09:28:57', '微信提现成功', '2025-12-12 09:28:57', '2025-12-12 11:28:14', NULL, NULL, NULL, NULL);
INSERT INTO `app_user_withdrawal` VALUES (8, 'WD202312120002', 2, 100.00, 99.00, 1, 'wechat_openid_123', '张三', 1036, 0, NULL, '2025-12-12 11:54:42', '', '2025-12-12 11:50:37', '2025-12-12 12:29:13', NULL, NULL, NULL, NULL);
INSERT INTO `app_user_withdrawal` VALUES (9, 'WD202312120003', 3, 100.00, 100.00, 1, 'wechat_openid_123', '张三', 1037, 0, NULL, '2025-12-12 11:57:52', '', '2025-12-12 11:57:31', '2025-12-12 12:29:22', NULL, NULL, NULL, NULL);
INSERT INTO `app_user_withdrawal` VALUES (19, 'WD202601261430455607', 4009, 0.01, NULL, 1, 'oOYcH7dHzekxZm_DmJ-vNWvqRTcM', '张三', 1081, 0, NULL, NULL, NULL, '2026-01-26 14:30:46', '2026-01-26 14:30:46', NULL, NULL, '提现到微信', NULL);
INSERT INTO `app_user_withdrawal` VALUES (20, 'WD202601282032400809', 4009, 0.01, NULL, 1, 'oOYcH7dHzekxZm_DmJ-vNWvqRTcM', '张三', 1089, 0, NULL, NULL, NULL, '2026-01-28 20:32:41', '2026-01-28 20:32:41', NULL, NULL, '提现到微信', '2026-01-28');

-- ----------------------------
-- Table structure for audit_record
-- ----------------------------
DROP TABLE IF EXISTS `audit_record`;
CREATE TABLE `audit_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `audit_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核编号',
  `subject_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '申请主体类型:MERCHANT-商家,SCHOOL-学校,USER-用户',
  `subject_id` bigint NOT NULL COMMENT '申请主体ID',
  `subject_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申请主体名称，例如商家或学校',
  `biz_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '业务类型:SETTLE_IN-入驻,WITHDRAW-提现,GOODS-商品上架,ACTIVITY-活动,STAFF_APPLY服务人员申请,RIDER-APPLY骑手申请',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '申请内容/说明',
  `audit_status` tinyint NOT NULL DEFAULT 0 COMMENT '审核状态:0-待审核 1-审核通过 2-审核拒绝',
  `auditor_id` bigint NULL DEFAULT NULL COMMENT '审核人ID',
  `auditor_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核人姓名',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `audit_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核意见',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_audit_no`(`audit_no` ASC) USING BTREE,
  INDEX `idx_subject`(`subject_type` ASC, `subject_id` ASC) USING BTREE,
  INDEX `idx_biz_status`(`biz_type` ASC, `audit_status` ASC) USING BTREE,
  INDEX `idx_auditor`(`auditor_id` ASC, `audit_status` ASC) USING BTREE,
  INDEX `idx_audit_status`(`audit_status` ASC) USING BTREE,
  INDEX `idx_biz_type`(`biz_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1124 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '审核记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of audit_record
-- ----------------------------
INSERT INTO `audit_record` VALUES (102, 'STAFF_APPLY_202312230001', 'USER', 4001, '张三', 'STAFF_APPLY', '服务人员申请', '申请成为校园跑腿服务人员,愿意为同学提供优质服务', 1, 1, 'admin', '2023-12-23 10:00:00', '审核通过,符合服务人员要求', '2023-12-23 09:00:00', '2023-12-23 10:00:00', NULL);
INSERT INTO `audit_record` VALUES (103, 'STAFF_APPLY_202312230002', 'USER', 4002, '李四', 'STAFF_APPLY', '服务人员申请', '申请成为校园跑腿服务人员,有丰富的校园配送经验', 1, 1, 'admin', '2023-12-23 11:00:00', '审核通过,经验丰富', '2023-12-23 09:30:00', '2023-12-23 11:00:00', NULL);
INSERT INTO `audit_record` VALUES (104, 'STAFF_APPLY_202312230003', 'USER', 4003, '王五', 'STAFF_APPLY', '服务人员申请', '申请成为校园跑腿服务人员,熟悉校园环境,能够快速送达', 1, 1, 'admin', '2023-12-23 14:00:00', '审核通过,熟悉校园', '2023-12-23 13:00:00', '2023-12-23 14:00:00', NULL);
INSERT INTO `audit_record` VALUES (105, 'USERxxxxxx', 'USER', 6, 'qianqi', 'STAFF_APPLY', '服务人员审核', '申请成为服务人员', 2, 1, 'admin', '2025-12-23 19:29:56', NULL, '2025-12-15 20:01:37', '2025-12-15 20:02:25', NULL);
INSERT INTO `audit_record` VALUES (106, 'USERxxxxxx2', 'USER', 7, 'sunba', 'STAFF_APPLY', '服务人员审核', '申请成为服务人员', 2, 1, 'admin', '2025-12-23 19:25:44', NULL, '2025-12-15 20:03:06', '2025-12-23 15:57:26', NULL);
INSERT INTO `audit_record` VALUES (201, 'SETTLE_IN_202312020001', 'MERCHANT', 1, '校园美食城', 'SETTLE_IN', '商家入驻申请-校园美食城', '申请入驻校园跑腿平台，主营各类美食外卖，拥有食品经营许可证，店铺位于校园内食堂二楼', 1, 1, 'admin', '2023-12-03 10:00:00', '资质齐全，审核通过', '2023-12-02 14:00:00', '2023-12-03 10:00:00', NULL);
INSERT INTO `audit_record` VALUES (202, 'SETTLE_IN_202312020002', 'MERCHANT', 2, '快乐奶茶店', 'SETTLE_IN', '商家入驻申请-快乐奶茶店', '申请入驻校园跑腿平台，主营各类奶茶饮品，拥有营业执照和食品经营许可证', 1, 1, 'admin', '2023-12-03 11:00:00', '资质合格，审核通过', '2023-12-02 15:00:00', '2023-12-03 11:00:00', NULL);
INSERT INTO `audit_record` VALUES (203, 'SETTLE_IN_202312020003', 'MERCHANT', 3, '书香文具店', 'SETTLE_IN', '商家入驻申请-书香文具店', '申请入驻校园跑腿平台，主营文具、图书等学习用品，已有3年经营经验', 1, 1, 'admin', '2023-12-03 14:00:00', '经营范围明确，审核通过', '2023-12-02 16:00:00', '2023-12-03 14:00:00', NULL);
INSERT INTO `audit_record` VALUES (204, 'SETTLE_IN_202312020004', 'MERCHANT', 4, '便利超市', 'SETTLE_IN', '商家入驻申请-便利超市', '申请入驻校园跑腿平台，主营日用品、零食饮料等，营业执照齐全', 1, 1, 'admin', '2023-12-03 15:00:00', '证照齐全，审核通过', '2023-12-02 17:00:00', '2023-12-03 15:00:00', NULL);
INSERT INTO `audit_record` VALUES (1012, 'PARTNER_17651632374268944', 'PARTNER', 20004, '张三', 'SETTLE_IN', '合伙人入驻申请', '合伙人「张三」申请入驻，手机号：13800138001', 0, 1, 'admin', '2025-12-08 19:49:23', NULL, '2025-12-08 11:07:17', '2025-12-23 15:57:24', NULL);
INSERT INTO `audit_record` VALUES (1013, 'PARTNER_17651645718358289', 'PARTNER', 20005, '张三', 'SETTLE_IN', '合伙人入驻申请', '合伙人「张三」申请入驻，手机号：13800138002', 0, 1, 'admin', '2025-12-09 09:58:07', NULL, '2025-12-08 11:29:31', '2025-12-23 15:57:22', NULL);
INSERT INTO `audit_record` VALUES (1014, 'PARTNER_17651649735183852', 'PARTNER', 20006, '张有', 'SETTLE_IN', '合伙人入驻申请', '合伙人「张有」申请入驻，手机号：13800138003', 1, 2, 'root', '2026-01-07 15:03:43', NULL, '2025-12-08 11:36:13', '2025-12-08 11:36:13', NULL);
INSERT INTO `audit_record` VALUES (1015, 'PARTNER_17651653088226928', 'PARTNER', 20007, '章鱼哥', 'SETTLE_IN', '合伙人入驻申请', '合伙人「章鱼哥」申请入驻，手机号：13800138004', 0, 1, 'admin', '2025-12-11 17:55:28', NULL, '2025-12-08 11:41:48', '2025-12-23 15:57:18', NULL);
INSERT INTO `audit_record` VALUES (1016, 'PARTNER_17651654888355040', 'PARTNER', 20008, '安其拉', 'SETTLE_IN', '合伙人入驻申请', '合伙人「安其拉」申请入驻，手机号：13801138000', 1, 2, 'root', '2026-01-07 15:04:59', NULL, '2025-12-08 11:44:48', '2025-12-23 15:57:17', NULL);
INSERT INTO `audit_record` VALUES (1017, 'PARTNER_17652442053699335', 'PARTNER', 20009, '刺客五六七', 'SETTLE_IN', '合伙人入驻申请', '合伙人「刺客五六七」申请入驻，手机号：13211111116', 0, 1, 'admin', '2025-12-09 09:38:30', NULL, '2025-12-09 09:36:45', '2025-12-23 15:57:15', NULL);
INSERT INTO `audit_record` VALUES (1019, 'MERCHANT_SETTLE_17654361801702633', 'MERCHANT', 15, '测试添加', 'SETTLE_IN', '商家入驻申请', '商家「测试添加」申请入驻，联系方式：17111111111，所在地区：河北省沧州市运河区', 0, 1, 'admin', '2025-12-11 20:53:45', '', '2025-12-11 14:56:20', '2025-12-23 15:57:12', NULL);
INSERT INTO `audit_record` VALUES (1020, 'PRODUCT_17655037170756915', 'PRODUCT', 6, '包子', 'GOODS', '商品审核', '商品「包子」申请上架审核', 0, 1, 'admin', '2025-12-12 09:41:57', NULL, '2025-12-12 09:41:57', '2025-12-23 15:57:10', NULL);
INSERT INTO `audit_record` VALUES (1021, 'PRODUCT_17655037278396593', 'PRODUCT', 5, '黄焖鸡米饭', 'GOODS', '商品审核', '商品「黄焖鸡米饭」申请上架审核', 2, 1, 'admin', '2025-12-29 09:30:50', NULL, '2025-12-12 09:42:07', '2025-12-23 15:57:09', NULL);
INSERT INTO `audit_record` VALUES (1022, 'PRODUCT_17655037313685173', 'PRODUCT', 4, '麦当劳', 'GOODS', '商品审核', '商品「麦当劳」申请上架审核', 1, 1, 'admin', '2025-12-29 09:29:54', NULL, '2025-12-12 09:42:11', '2025-12-23 15:57:07', NULL);
INSERT INTO `audit_record` VALUES (1023, 'PRODUCT_17655037340636524', 'PRODUCT', 3, 'kfc', 'GOODS', '商品审核', '商品「kfc」申请上架审核', 0, 1, 'admin', '2025-12-12 09:42:14', NULL, '2025-12-12 09:42:14', '2025-12-23 15:57:06', NULL);
INSERT INTO `audit_record` VALUES (1024, 'AU202312120001', 'MERCHANT', 1001, '张三美食店', 'SETTLE_IN', '商家入驻申请', '申请入驻校园外卖平台，主营快餐小吃', 0, NULL, NULL, NULL, NULL, '2025-12-12 09:50:23', '2025-12-12 09:50:23', NULL);
INSERT INTO `audit_record` VALUES (1025, 'AU202312120002', 'RIDER', 2001, '李四骑手', 'SETTLE_IN', '骑手入驻申请', '申请成为校园配送骑手，有电动车', 1, 1, 'admin', '2026-01-06 19:22:52', '资料齐全，审核通过', '2023-12-12 09:00:00', '2026-01-06 19:22:52', NULL);
INSERT INTO `audit_record` VALUES (1026, 'AU202312120003', 'RIDER', 2002, '赵六骑手', 'WITHDRAWAL', '提现申请审核', '申请提现100元，提现到支付宝', 0, NULL, NULL, NULL, NULL, '2025-12-12 09:50:23', '2025-12-12 09:50:23', NULL);
INSERT INTO `audit_record` VALUES (1027, 'AU202312120004', 'MERCHANT', 1002, '校园超市', 'WITHDRAWAL', '商家提现审核', '申请提现500元，提现到银行卡', 0, 2, '财务小李', '2023-12-12 14:20:00', '金额正确，同意打款', '2023-12-12 13:00:00', '2025-12-23 15:57:00', NULL);
INSERT INTO `audit_record` VALUES (1028, 'AU202312120005', 'MERCHANT', 1003, '校园奶茶店', 'SETTLE_IN', '奶茶店入驻申请', '申请入驻校园外卖平台', 0, 1, '管理员王五', '2023-12-12 16:45:00', '', '2023-12-12 15:30:00', '2025-12-23 15:56:58', NULL);
INSERT INTO `audit_record` VALUES (1031, 'AU202312120006', 'PARTNER', 20010, '张三', 'WITHDRAWAL', '提现申请审核', '申请提现', 0, 1, '财务小李', '2025-12-12 11:56:40', NULL, '2025-12-12 11:25:44', '2025-12-23 15:56:57', NULL);
INSERT INTO `audit_record` VALUES (1032, 'AU202312120007', 'PARTNER', 20011, '张三', 'WITHDRAWAL', '提现审请审核', '申请提现', 0, NULL, '', '2025-12-12 11:56:40', NULL, '2025-12-12 11:53:55', '2025-12-12 12:15:14', NULL);
INSERT INTO `audit_record` VALUES (1035, 'AU202312120008', 'PAARTNER', 20012, '张三', 'WITHDRAWAL', '提现申请审核', '申请提现', 0, NULL, '', '2025-12-12 11:56:40', NULL, '2025-12-12 11:56:06', '2025-12-12 12:15:34', NULL);
INSERT INTO `audit_record` VALUES (1036, 'AU202312120009', 'PAARTNER', 20013, '张三', 'WITHDRAWAL', '提现申请审核', '申请提现', 0, NULL, NULL, NULL, NULL, '2025-12-12 12:28:02', '2025-12-12 12:28:02', NULL);
INSERT INTO `audit_record` VALUES (1037, 'AU202312120010', 'PAARTNER', 20014, '张三', 'WITHDRAWAL', '提现申请审核', '申请提现', 0, 1, 'admin', '2025-12-12 12:30:13', NULL, '2025-12-12 12:29:03', '2025-12-23 15:56:56', NULL);
INSERT INTO `audit_record` VALUES (1038, 'POST21212121_14', 'PAARTNER', 12, '徐四', 'POST', '发布帖子审核', '申请发布帖子', 0, NULL, NULL, NULL, NULL, '2025-12-15 11:07:34', '2025-12-15 11:12:25', NULL);
INSERT INTO `audit_record` VALUES (1039, 'POST21212121_16', 'PAARTNER', 12, '徐四', 'POST', '发布帖子审核', '申请发布帖子', 0, NULL, NULL, NULL, NULL, '2025-12-15 11:11:34', '2025-12-15 11:13:11', NULL);
INSERT INTO `audit_record` VALUES (1040, 'ACTIVITY_1765847912201', 'ACTIVITY', 17, '未知学校', 'ACTIVITY', '活动审核 - 旅游', '活动内容: 玩', 0, 1, 'admin', '2025-12-16 09:19:47', NULL, '2025-12-16 09:18:32', '2025-12-23 15:56:54', NULL);
INSERT INTO `audit_record` VALUES (1041, 'PARTNER_17658479743012761', 'PARTNER', 20010, '啦啦啦啦啦啦啦', 'SETTLE_IN', '合伙人入驻申请', '合伙人「啦啦啦啦啦啦啦」申请入驻，手机号：13903234567', 1, 1, 'admin', '2025-12-23 16:13:45', NULL, '2025-12-16 09:19:34', '2025-12-16 09:19:34', NULL);
INSERT INTO `audit_record` VALUES (1042, 'ACTIVITY_1765848071120', 'ACTIVITY', 18, '未知学校', 'ACTIVITY', '活动审核 - 旅游111', '活动内容: 玩', 0, 1, 'admin', '2025-12-16 09:21:22', NULL, '2025-12-16 09:21:11', '2025-12-23 15:56:52', NULL);
INSERT INTO `audit_record` VALUES (1043, 'PARTNER_17658485781938154', 'PARTNER', 20011, '周二', 'SETTLE_IN', '合伙人入驻申请', '合伙人「周二」申请入驻，手机号：13613132345', 1, 1, 'admin', '2025-12-23 17:22:01', NULL, '2025-12-16 09:29:38', '2025-12-16 09:29:38', NULL);
INSERT INTO `audit_record` VALUES (1044, 'ACTIVITY_1765857819978', 'ACTIVITY', 19, '未知学校', 'ACTIVITY', '活动审核 - 周二', '活动内容: <p>吃冰激凌</p>', 0, 1, 'admin', '2025-12-16 12:03:45', NULL, '2025-12-16 12:03:40', '2025-12-23 15:56:50', NULL);
INSERT INTO `audit_record` VALUES (1055, 'mch_test', 'test', 1005, '麻辣香锅', 'SETTLE_IN', '商家入驻申请', 'testtesttest', 0, NULL, NULL, NULL, NULL, '2025-12-23 16:16:50', '2025-12-23 16:16:50', NULL);
INSERT INTO `audit_record` VALUES (1060, 'AU202312120011', 'RIDER', 2006, '王五骑手', 'SETTLE_IN', '骑手入驻申请', '申请成为校园配送骑手，有车', 2, 1, 'admin', '2026-01-06 19:31:26', '资料不齐全，审核未通过', '2026-01-06 19:29:20', '2026-01-06 19:31:26', NULL);
INSERT INTO `audit_record` VALUES (1061, 'ACTIVITY_1767957495861', 'ACTIVITY', 20, '未知学校', 'ACTIVITY', '活动审核 - 活动标题', '活动内容: <p>活动内容</p>', 1, 2, 'root', '2026-01-13 15:09:45', NULL, '2026-01-09 19:18:16', '2026-01-09 19:18:16', NULL);
INSERT INTO `audit_record` VALUES (1062, 'ACTIVITY_1768288139701', 'ACTIVITY', 21, '未知学校', 'ACTIVITY', '活动审核 - 测试活动标题', '活动内容: <p>活动内容</p>', 1, 2, 'root', '2026-01-13 15:09:41', NULL, '2026-01-13 15:09:00', '2026-01-13 15:09:00', NULL);
INSERT INTO `audit_record` VALUES (1063, 'ACTIVITY_1768363980855', 'ACTIVITY', 22, NULL, 'ACTIVITY', '活动审核 - 春游', '活动内容: <p>去公园玩老鹰抓小鸡</p>', 1, 2, 'root', '2026-01-14 12:13:14', NULL, '2026-01-14 12:13:01', '2026-01-14 12:13:01', NULL);
INSERT INTO `audit_record` VALUES (1064, 'PRODUCT_17685274979359134', 'PRODUCT', 9, '商品', 'GOODS', '商品审核', '商品「商品」申请上架审核', 1, 2, 'root', '2026-01-16 09:38:18', NULL, '2026-01-16 09:38:17', '2026-01-16 09:38:18', NULL);
INSERT INTO `audit_record` VALUES (1065, 'PRODUCT_17685274979795228', 'PRODUCT', 9, '商品', 'GOODS', '商品审核', '商品「商品」申请上架审核', 1, 2, 'root', '2026-01-16 09:38:18', NULL, '2026-01-16 09:38:17', '2026-01-16 09:38:18', NULL);
INSERT INTO `audit_record` VALUES (1066, '', '', 0, NULL, '', '', NULL, 0, NULL, NULL, NULL, NULL, '2025-12-12 11:20:34', '2025-12-12 11:20:51', NULL);
INSERT INTO `audit_record` VALUES (1072, 'SP17690450082409c1761', 'USER', 1, 'user', 'STAFF_APPLY', '服务者申请', '我有丰富的服务经验，希望能为社区提供帮助', 0, NULL, NULL, NULL, NULL, '2026-01-22 09:23:28', '2026-01-22 09:23:28', NULL);
INSERT INTO `audit_record` VALUES (1073, 'SP176904597415882142f', 'USER', 4009, '塔斯汀', 'STAFF_APPLY', '服务者申请', '我要买帕拉梅拉', 1, 2, 'root', '2026-01-22 12:01:26', NULL, '2026-01-22 09:39:34', '2026-01-22 09:39:34', NULL);
INSERT INTO `audit_record` VALUES (1074, 'SP1769051814916141ae5', 'USER', 4006, '测试昵称', 'STAFF_APPLY', '服务者申请', '我要买帕拉梅拉', 1, 2, 'root', '2026-01-22 19:56:24', NULL, '2026-01-22 11:16:55', '2026-01-22 11:16:55', NULL);
INSERT INTO `audit_record` VALUES (1075, 'SP176905454167720756f', 'USER', 4009, '塔斯汀', 'STAFF_APPLY', '服务者申请', '热爱服务', 1, 2, 'root', '2026-01-22 12:02:39', NULL, '2026-01-22 12:02:22', '2026-01-22 12:02:22', NULL);
INSERT INTO `audit_record` VALUES (1076, 'SP17690548116515cda9d', 'USER', 4009, '塔斯汀', 'STAFF_APPLY', '服务者申请', '321', 1, 2, 'root', '2026-01-22 12:07:05', NULL, '2026-01-22 12:06:52', '2026-01-22 12:06:52', NULL);
INSERT INTO `audit_record` VALUES (1077, 'PRODUCT_17690815222083985', 'PRODUCT', 2, '可口可乐', 'GOODS', '商品审核', '商品「可口可乐」申请上架审核', 1, 2, 'root', '2026-01-22 19:32:02', NULL, '2026-01-22 11:32:03', '2026-01-22 11:32:03', NULL);
INSERT INTO `audit_record` VALUES (1078, 'AUD1769225129192b2c0f7', 'USER', 1, 'user', 'ACTIVITY', '校园歌手大赛', '报名参加！', 0, NULL, NULL, NULL, NULL, '2026-01-24 11:25:29', '2026-01-24 11:25:29', NULL);
INSERT INTO `audit_record` VALUES (1079, 'AUD1769226583590dd4d3a', 'USER', 1, 'user', 'ACTIVITY', '校园歌手大赛', '报名参加！', 1, 1, 'admin', '2026-01-24 11:51:55', '活动内容合规，审核通过', '2026-01-24 11:49:44', '2026-01-24 11:49:44', NULL);
INSERT INTO `audit_record` VALUES (1080, 'AUD1769240256065ffd8ab', 'USER', 4006, '测试昵称', 'ACTIVITY', '种树', '123', 1, 2, 'root', '2026-01-24 15:37:45', NULL, '2026-01-24 15:37:36', '2026-01-24 15:37:36', NULL);
INSERT INTO `audit_record` VALUES (1081, 'AUD17694090456483b3d1e', 'USER', 4009, '塔斯汀', 'WITHDRAW', '用户提现申请', '提现金额：0.01元，提现方式：微信', 0, NULL, NULL, NULL, NULL, '2026-01-26 14:30:46', '2026-01-26 14:30:46', NULL);
INSERT INTO `audit_record` VALUES (1082, 'SP1769421518829649c0a', 'USER', 4011, 'wx_oOYcH7XK_97d79a', 'STAFF_APPLY', '服务者申请', '赚钱', 1, 2, 'root', '2026-01-26 17:59:52', NULL, '2026-01-26 17:58:39', '2026-01-26 17:58:39', NULL);
INSERT INTO `audit_record` VALUES (1083, 'SP1769518495498bb6236', 'USER', 4008, 'wx_oOYcH7cu_bb5a73', 'STAFF_APPLY', '服务者申请', '零零七六零', 0, NULL, NULL, NULL, NULL, '2026-01-27 20:54:56', '2026-01-27 20:54:56', NULL);
INSERT INTO `audit_record` VALUES (1084, 'SERVICE_STAFF_1769518560068', 'USER', 4017, 'wx_oOYcH7Wp_52e617', 'SERVICE_STAFF_APPLY', '服务人员申请', NULL, 1, 2, 'root', '2026-01-27 20:56:00', NULL, '2026-01-27 20:56:00', '2026-01-27 20:56:00', NULL);
INSERT INTO `audit_record` VALUES (1085, 'SERVICE_STAFF_1769518612429', 'USER', 4016, 'wx_oOYcH7SP_955076', 'SERVICE_STAFF_APPLY', '服务人员申请', NULL, 1, 2, 'root', '2026-01-27 20:56:52', NULL, '2026-01-27 20:56:52', '2026-01-27 20:56:52', NULL);
INSERT INTO `audit_record` VALUES (1086, 'SERVICE_STAFF_1769518671678', 'USER', 4015, 'wx_oOYcH7YH_17049e', 'SERVICE_STAFF_APPLY', '服务人员申请', NULL, 1, 2, 'root', '2026-01-27 20:57:52', NULL, '2026-01-27 20:57:52', '2026-01-27 20:57:52', NULL);
INSERT INTO `audit_record` VALUES (1087, 'SERVICE_STAFF_1769518676474', 'USER', 4014, 'wx_oOYcH7YL_5908ef', 'SERVICE_STAFF_APPLY', '服务人员申请', NULL, 1, 2, 'root', '2026-01-27 20:57:57', NULL, '2026-01-27 20:57:56', '2026-01-27 20:57:56', NULL);
INSERT INTO `audit_record` VALUES (1088, 'SERVICE_STAFF_1769518684889', 'USER', 4013, '微信用户', 'SERVICE_STAFF_APPLY', '服务人员申请', NULL, 1, 2, 'root', '2026-01-27 20:58:05', NULL, '2026-01-27 20:58:05', '2026-01-27 20:58:05', NULL);
INSERT INTO `audit_record` VALUES (1089, 'AUD17696035608256a16c3', 'USER', 4009, '塔斯汀', 'WITHDRAW', '用户提现申请', '提现金额：0.01元，提现方式：微信', 0, NULL, NULL, NULL, NULL, '2026-01-28 20:32:41', '2026-01-28 20:32:41', NULL);
INSERT INTO `audit_record` VALUES (1090, 'AUD1769737529305abebca', 'USER', 1, 'user', 'ACTIVITY', '校园歌手大赛', '一年一度的校园歌手大赛即将开始，欢迎同学们踊跃报名参加！', 0, NULL, NULL, NULL, NULL, '2026-01-30 09:45:29', '2026-01-30 09:45:29', NULL);
INSERT INTO `audit_record` VALUES (1091, 'AUD1769741969276fb7ea7', 'USER', 4017, 'wx_oOYcH7Wp_52e617', 'ACTIVITY', '来来', '好想来', 0, NULL, NULL, NULL, NULL, '2026-01-30 10:59:29', '2026-01-30 10:59:29', NULL);
INSERT INTO `audit_record` VALUES (1092, 'AUD1769826036445af089f', 'USER', 4010, 'wx_oOYcH7X4_60c934', 'ACTIVITY', '露营', '露营', 1, 2, 'root', '2026-01-31 10:23:11', NULL, '2026-01-31 10:20:36', '2026-01-31 10:20:36', NULL);
INSERT INTO `audit_record` VALUES (1093, 'AUD17698263752160da86c', 'USER', 4010, 'wx_oOYcH7X4_60c934', 'ACTIVITY', '露营', '露营123', 1, 2, 'root', '2026-01-31 10:26:28', NULL, '2026-01-31 10:26:15', '2026-01-31 10:26:15', NULL);
INSERT INTO `audit_record` VALUES (1094, 'AUD17698265222261de4b6', 'USER', 4010, 'wx_oOYcH7X4_60c934', 'ACTIVITY', '露营', '露营234', 1, 2, 'root', '2026-01-31 10:28:49', NULL, '2026-01-31 10:28:42', '2026-01-31 10:28:42', NULL);
INSERT INTO `audit_record` VALUES (1095, 'AUD17698288220637c855a', 'USER', 4016, 'wx_oOYcH7SP_955076', 'ACTIVITY', '参观保尔', '看人家敲代码', 0, NULL, NULL, NULL, NULL, '2026-01-31 11:07:02', '2026-01-31 11:07:02', NULL);
INSERT INTO `audit_record` VALUES (1096, 'AUD176983036804809d545', 'USER', 1, 'user', 'ACTIVITY', '校园歌手大赛', '一年一度的校园歌手大赛即将开始，欢迎同学们踊跃报名参加！', 0, NULL, NULL, NULL, NULL, '2026-01-31 11:32:48', '2026-01-31 11:32:48', NULL);
INSERT INTO `audit_record` VALUES (1097, 'AUD17698303924733be09b', 'USER', 1, 'user', 'ACTIVITY', '123', '123', 0, NULL, NULL, NULL, NULL, '2026-01-31 11:33:12', '2026-01-31 11:33:12', NULL);
INSERT INTO `audit_record` VALUES (1098, 'AUD17698328143625d4bfb', 'USER', 4012, '伊丽莎白鼠', 'ACTIVITY', '羽毛球', '羽毛球·', 0, NULL, NULL, NULL, NULL, '2026-01-31 12:13:34', '2026-01-31 12:13:34', NULL);
INSERT INTO `audit_record` VALUES (1099, 'AUD1769833351568a5155a', 'USER', 4014, '晚星叙', 'ACTIVITY', '测试活动', '今天吃什么', 0, NULL, NULL, NULL, NULL, '2026-01-31 12:22:32', '2026-01-31 12:22:32', NULL);
INSERT INTO `audit_record` VALUES (1100, 'AUD1769837274852e1386b', 'USER', 4016, 'wx_oOYcH7SP_955076', 'ACTIVITY', '测试图片', '测试图片月', 0, NULL, NULL, NULL, NULL, '2026-01-31 13:27:55', '2026-01-31 13:27:55', NULL);
INSERT INTO `audit_record` VALUES (1101, 'AUD-2018937573870800896', 'MERCHANT', 1043, '美羊羊', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"美羊羊\",\"contact\":\"15003246517\",\"partnerId\":2,\"payoutAccount\":\"15003246516\",\"email\":\"32244857@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/c666faaa6d1a45ddaddbdf850d457301.png\",\"businessScopeCode\":[410,411]}', 1, 1, 'admin', '2026-02-04 14:42:49', '', '2026-02-04 14:40:02', '2026-02-04 14:40:02', NULL);
INSERT INTO `audit_record` VALUES (1102, 'AUD-2018997644122263552', 'MERCHANT', 1046, 'tbl', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"tbl\",\"contact\":\"15003246516\",\"partnerId\":1,\"payoutAccount\":\"15003246516\",\"email\":\"3224487@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/eaa3b78d798b4f149734298119b213ac.png\",\"businessScopeCode\":[411,417,423]}', 0, NULL, NULL, NULL, NULL, '2026-02-04 18:38:44', '2026-02-04 18:38:44', NULL);
INSERT INTO `audit_record` VALUES (1104, 'AUD-2019029529053499392', 'MERCHANT', 1068, '伊丽莎白鼠的店铺', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"伊丽莎白鼠的店铺\",\"contact\":\"15987189785\",\"partnerId\":2,\"payoutAccount\":\"123\",\"email\":\"123654@qq.com\",\"businessLicenseType\":0,\"businessLicenseURLs\":\"/uploads/2026-02-04/b3f7ae0f7cbd439fb2d0b6d5d5fdf0c9.png\",\"businessScopeCode\":[431,432]}', 0, NULL, NULL, NULL, NULL, '2026-02-04 20:45:26', '2026-02-04 20:45:26', NULL);
INSERT INTO `audit_record` VALUES (1105, 'AUD-2019030498344570880', 'MERCHANT', 1072, 'xfl', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"xfl\",\"contact\":\"18003246511\",\"partnerId\":1,\"payoutAccount\":\"123\",\"email\":\"123456987@qq.com\",\"businessLicenseType\":0,\"businessLicenseURLs\":\"/uploads/2026-02-04/649a19f055e54bf3b4bb18e18d62f68d.jpeg\",\"businessScopeCode\":[407,408]}', 0, NULL, NULL, NULL, NULL, '2026-02-04 20:49:17', '2026-02-04 20:49:17', NULL);
INSERT INTO `audit_record` VALUES (1106, 'AUD-2019031323825541120', 'MERCHANT', 1075, 'xxffll', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"xxffll\",\"contact\":\"15987189784\",\"partnerId\":20005,\"payoutAccount\":\"123456\",\"email\":\"19813020@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/cf535e9461874674996bcbd5ba57032a.jpeg\",\"businessScopeCode\":[427,428]}', 0, NULL, NULL, NULL, NULL, '2026-02-04 20:52:34', '2026-02-04 20:52:34', NULL);
INSERT INTO `audit_record` VALUES (1107, 'AUD-2019032517402824704', 'MERCHANT', 1079, 'xxffll', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"xxffll\",\"contact\":\"15987189784\",\"partnerId\":20005,\"payoutAccount\":\"123456\",\"email\":\"198130201@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/cf535e9461874674996bcbd5ba57032a.jpeg\",\"businessScopeCode\":[427,428]}', 0, NULL, NULL, NULL, NULL, '2026-02-04 20:57:19', '2026-02-04 20:57:19', NULL);
INSERT INTO `audit_record` VALUES (1108, 'AUD-2019032825365401600', 'MERCHANT', 1082, 'tbltbl', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"tbltbl\",\"contact\":\"15987189780\",\"partnerId\":20007,\"payoutAccount\":\"12345678\",\"email\":\"1234569002@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/327762b313bd473d9513175d58056503.png\",\"businessScopeCode\":[423,417,411]}', 0, NULL, NULL, NULL, NULL, '2026-02-04 20:58:32', '2026-02-04 20:58:32', NULL);
INSERT INTO `audit_record` VALUES (1109, 'AUD-2019032837436608512', 'MERCHANT', 1083, '小吃', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"小吃\",\"contact\":\"17003246516\",\"partnerId\":1,\"payoutAccount\":\"17003246516\",\"email\":\"12345611@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/a64c13eeb9dc4b8b97d7ec97906125a6.png\",\"businessScopeCode\":[410]}', 0, NULL, NULL, NULL, NULL, '2026-02-04 20:58:35', '2026-02-04 20:58:35', NULL);
INSERT INTO `audit_record` VALUES (1110, 'AUD-2019216291746942976', 'MERCHANT', 1085, '美食街', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"美食街\",\"contact\":\"15003246526\",\"partnerId\":2,\"payoutAccount\":\"15003246526\",\"email\":\"15003246526@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-05/a206b291ecfe40b0a6c8198964008375.png\",\"businessScopeCode\":[411,412,410]}', 1, 1, 'admin', '2026-02-05 09:17:24', '', '2026-02-05 09:07:34', '2026-02-05 09:07:34', NULL);
INSERT INTO `audit_record` VALUES (1111, 'AUD-2019216652620664832', 'MERCHANT', 1086, '伊丽莎白鼠', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"伊丽莎白鼠\",\"contact\":\"15987189785\",\"partnerId\":1,\"payoutAccount\":\"1234567891234567890\",\"email\":\"1981302052@163.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-05/b840abbef7c54bff9bc8c42877bc353e.jpeg\",\"businessScopeCode\":[431,432]}', 1, 1, 'admin', '2026-02-05 09:53:33', '', '2026-02-05 09:09:00', '2026-02-05 09:09:00', NULL);
INSERT INTO `audit_record` VALUES (1113, 'AUD-2019220942856785920', 'MERCHANT', 1090, 'tbltbl123', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"tbltbl123\",\"contact\":\"15003246543\",\"partnerId\":1,\"payoutAccount\":\"1500324654312345678\",\"email\":\"15003246543@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-05/2d5ec638625943bf971d8fd068dbeecb.png\",\"businessScopeCode\":[411,412,413]}', 0, NULL, NULL, NULL, NULL, '2026-02-05 09:26:03', '2026-02-05 09:26:03', NULL);
INSERT INTO `audit_record` VALUES (1114, 'AUD-2019223104571707392', 'MERCHANT', 1093, '小吃', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"小吃\",\"contact\":\"17003245616\",\"partnerId\":1,\"payoutAccount\":\"11111111111\",\"email\":\"1232326@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/a64c13eeb9dc4b8b97d7ec97906125a6.png\",\"businessScopeCode\":[410]}', 0, NULL, NULL, NULL, NULL, '2026-02-05 09:34:38', '2026-02-05 09:34:38', NULL);
INSERT INTO `audit_record` VALUES (1115, 'AUD-2019224621093621760', 'MERCHANT', 1096, '小吃', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"小吃\",\"contact\":\"17003245616\",\"partnerId\":1,\"payoutAccount\":\"11111111111\",\"email\":\"1232326@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/a64c13eeb9dc4b8b97d7ec97906125a6.png\",\"businessScopeCode\":[410]}', 0, NULL, NULL, NULL, NULL, '2026-02-05 09:40:40', '2026-02-05 09:40:40', NULL);
INSERT INTO `audit_record` VALUES (1116, 'AUD-2019224880809119744', 'MERCHANT', 1098, '小吃', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"小吃\",\"contact\":\"17003245616\",\"partnerId\":1,\"payoutAccount\":\"11111111111\",\"email\":\"1232326@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/a64c13eeb9dc4b8b97d7ec97906125a6.png\",\"businessScopeCode\":[410]}', 0, NULL, NULL, NULL, NULL, '2026-02-05 09:41:42', '2026-02-05 09:41:42', NULL);
INSERT INTO `audit_record` VALUES (1117, 'AUD-2019224996160868352', 'MERCHANT', 1099, '小吃', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"小吃\",\"contact\":\"11111111111\",\"partnerId\":1,\"payoutAccount\":\"11111111111\",\"email\":\"1232326@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-04/a64c13eeb9dc4b8b97d7ec97906125a6.png\",\"businessScopeCode\":[410]}', 0, NULL, NULL, NULL, NULL, '2026-02-05 09:42:09', '2026-02-05 09:42:09', NULL);
INSERT INTO `audit_record` VALUES (1118, 'AUD-2019253743446528000', 'MERCHANT', 1101, '盖浇饭', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"盖浇饭\",\"contact\":\"15003246551\",\"partnerId\":1,\"payoutAccount\":\"1500324655112345678\",\"email\":\"15003246551@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-05/88629040b51f47089558030cd39f9776.png\",\"businessScopeCode\":[411,412,413,417]}', 1, 1, 'admin', '2026-02-05 11:43:42', '', '2026-02-05 11:36:23', '2026-02-05 11:36:23', NULL);
INSERT INTO `audit_record` VALUES (1119, 'AUD-2019329934736101376', 'MERCHANT', 1103, '伊丽莎白鼠', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"伊丽莎白鼠\",\"contact\":\"15987189785\",\"partnerId\":1,\"payoutAccount\":\"1234567891234567890\",\"email\":\"741852963@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-05/c5af481b5876432ba201dca877bf359d.jpeg\",\"businessScopeCode\":[410,411,412]}', 0, NULL, NULL, NULL, NULL, '2026-02-05 16:39:08', '2026-02-05 16:39:08', NULL);
INSERT INTO `audit_record` VALUES (1120, 'AUD-2019332335719288832', 'MERCHANT', 1105, '伊丽莎白鼠11', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"伊丽莎白鼠11\",\"contact\":\"13245678911\",\"partnerId\":1,\"payoutAccount\":\"1234567891234567890\",\"email\":\"13245678900@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-05/5a069a575bab4f3490c82149ed721684.jpeg\",\"businessScopeCode\":[404,405]}', 1, 1, 'admin', '2026-02-05 16:57:15', '', '2026-02-05 16:48:41', '2026-02-05 16:48:41', NULL);
INSERT INTO `audit_record` VALUES (1121, 'AUD-2019734062830325760', 'MERCHANT', 1107, '柏林饭店', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"柏林饭店\",\"contact\":\"15987185555\",\"partnerId\":20004,\"payoutAccount\":\"1234567891234567890\",\"email\":\"15987185555@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-06/dd117c5c101641f3844c7bac38ec8e8b.png\",\"businessScopeCode\":[410,411]}', 0, NULL, NULL, NULL, NULL, '2026-02-06 19:25:00', '2026-02-06 19:25:00', NULL);
INSERT INTO `audit_record` VALUES (1122, 'AUD-2019734734380339200', 'MERCHANT', 1109, '柏林二号', 'SETTLE_IN', '商家入驻申请', '{\"merchantName\":\"柏林二号\",\"contact\":\"15987185553\",\"partnerId\":20005,\"payoutAccount\":\"1234567891234567890\",\"email\":\"15987185553@qq.com\",\"businessLicenseType\":2,\"businessLicenseURLs\":\"/uploads/2026-02-06/ecb5e1cc572e4e41bb1df3695cabc0e8.png\",\"businessScopeCode\":[410,411]}', 1, 1, 'admin', '2026-02-06 19:28:56', '', '2026-02-06 19:27:40', '2026-02-06 19:27:40', NULL);
INSERT INTO `audit_record` VALUES (1123, 'AUD-201973473438034', 'RIDER', 2003, '15230752833', 'SETTLE_IN', '骑手入驻申请', '123', 0, NULL, NULL, NULL, NULL, '2026-02-10 01:51:53', '2026-02-10 02:31:52', NULL);

-- ----------------------------
-- Table structure for browsing_history
-- ----------------------------
DROP TABLE IF EXISTS `browsing_history`;
CREATE TABLE `browsing_history`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '浏览记录ID，自增主键',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID，关联用户表',
  `content_type` tinyint NOT NULL COMMENT '内容类型：1-论坛帖子，2-外卖商家',
  `content_id` bigint UNSIGNED NOT NULL COMMENT '内容ID（论坛帖子ID或商家ID）',
  `created_date` date NOT NULL COMMENT '创建日期，用于分区',
  PRIMARY KEY (`id`, `created_date`) USING BTREE,
  INDEX `idx_content`(`content_type` ASC, `content_id` ASC) USING BTREE,
  INDEX `idx_created_date`(`created_date` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户浏览记录表' ROW_FORMAT = DYNAMIC PARTITION BY RANGE (to_days(`created_date`))
PARTITIONS 4
(PARTITION `p_202512` VALUES LESS THAN (739982) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 ,
PARTITION `p_202601` VALUES LESS THAN (740013) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 ,
PARTITION `p_202602` VALUES LESS THAN (740041) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 ,
PARTITION `p_max` VALUES LESS THAN (MAXVALUE) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 )
;

-- ----------------------------
-- Records of browsing_history
-- ----------------------------
INSERT INTO `browsing_history` VALUES (1, 1, 1, 2, '2025-12-12');
INSERT INTO `browsing_history` VALUES (2, 2, 1, 3, '2025-12-15');
INSERT INTO `browsing_history` VALUES (4, 1, 2, 10, '2025-12-15');
INSERT INTO `browsing_history` VALUES (5, 1, 2, 11, '2025-12-15');
INSERT INTO `browsing_history` VALUES (6, 1, 2, 12, '2025-12-15');
INSERT INTO `browsing_history` VALUES (7, 1, 2, 1002, '2025-12-17');
INSERT INTO `browsing_history` VALUES (8, 1, 2, 1002, '2025-12-18');
INSERT INTO `browsing_history` VALUES (9, 4006, 1, 53, '2026-01-21');
INSERT INTO `browsing_history` VALUES (10, 4006, 1, 53, '2026-01-22');
INSERT INTO `browsing_history` VALUES (11, 4006, 1, 41, '2026-01-22');
INSERT INTO `browsing_history` VALUES (12, 4006, 1, 54, '2026-01-22');
INSERT INTO `browsing_history` VALUES (13, 4009, 1, 54, '2026-01-22');
INSERT INTO `browsing_history` VALUES (14, 4006, 1, 56, '2026-01-22');
INSERT INTO `browsing_history` VALUES (15, 4006, 1, 56, '2026-01-23');
INSERT INTO `browsing_history` VALUES (16, 4006, 1, 41, '2026-01-23');
INSERT INTO `browsing_history` VALUES (17, 4006, 1, 55, '2026-01-23');
INSERT INTO `browsing_history` VALUES (18, 4006, 2, 1, '2026-01-26');
INSERT INTO `browsing_history` VALUES (19, 4006, 1, 55, '2026-01-26');
INSERT INTO `browsing_history` VALUES (20, 4006, 1, 41, '2026-01-26');
INSERT INTO `browsing_history` VALUES (21, 4006, 1, 56, '2026-01-26');
INSERT INTO `browsing_history` VALUES (22, 4006, 1, 57, '2026-01-26');
INSERT INTO `browsing_history` VALUES (23, 4011, 2, 1, '2026-01-26');
INSERT INTO `browsing_history` VALUES (24, 4011, 2, 4, '2026-01-26');
INSERT INTO `browsing_history` VALUES (25, 4011, 2, 2, '2026-01-26');
INSERT INTO `browsing_history` VALUES (26, 4006, 2, 1, '2026-01-27');
INSERT INTO `browsing_history` VALUES (27, 4006, 2, 1, '2026-01-28');
INSERT INTO `browsing_history` VALUES (28, 4013, 1, 58, '2026-01-28');
INSERT INTO `browsing_history` VALUES (29, 4014, 1, 58, '2026-01-28');
INSERT INTO `browsing_history` VALUES (30, 4014, 1, 57, '2026-01-28');
INSERT INTO `browsing_history` VALUES (31, 4014, 1, 56, '2026-01-28');
INSERT INTO `browsing_history` VALUES (32, 4016, 1, 58, '2026-01-28');
INSERT INTO `browsing_history` VALUES (33, 4013, 1, 57, '2026-01-28');
INSERT INTO `browsing_history` VALUES (34, 4013, 1, 55, '2026-01-28');
INSERT INTO `browsing_history` VALUES (35, 4013, 1, 56, '2026-01-28');
INSERT INTO `browsing_history` VALUES (36, 4016, 1, 56, '2026-01-28');
INSERT INTO `browsing_history` VALUES (37, 4016, 1, 57, '2026-01-28');
INSERT INTO `browsing_history` VALUES (38, 4012, 1, 58, '2026-01-28');
INSERT INTO `browsing_history` VALUES (39, 4012, 1, 45, '2026-01-28');
INSERT INTO `browsing_history` VALUES (40, 4012, 1, 52, '2026-01-28');
INSERT INTO `browsing_history` VALUES (41, 4006, 2, 2, '2026-01-28');
INSERT INTO `browsing_history` VALUES (42, 4006, 2, 3, '2026-01-28');
INSERT INTO `browsing_history` VALUES (43, 4006, 2, 4, '2026-01-28');
INSERT INTO `browsing_history` VALUES (44, 4015, 1, 58, '2026-01-28');
INSERT INTO `browsing_history` VALUES (45, 4012, 1, 56, '2026-01-28');
INSERT INTO `browsing_history` VALUES (46, 4008, 1, 58, '2026-01-28');
INSERT INTO `browsing_history` VALUES (47, 1, 2, 1008, '2026-01-28');
INSERT INTO `browsing_history` VALUES (48, 4012, 1, 57, '2026-01-28');
INSERT INTO `browsing_history` VALUES (49, 4012, 1, 54, '2026-01-28');
INSERT INTO `browsing_history` VALUES (50, 4006, 1, 59, '2026-01-28');
INSERT INTO `browsing_history` VALUES (51, 4013, 1, 59, '2026-01-28');
INSERT INTO `browsing_history` VALUES (52, 4013, 1, 54, '2026-01-28');
INSERT INTO `browsing_history` VALUES (53, 4013, 1, 52, '2026-01-28');
INSERT INTO `browsing_history` VALUES (54, 4015, 1, 59, '2026-01-28');
INSERT INTO `browsing_history` VALUES (55, 4016, 1, 59, '2026-01-28');
INSERT INTO `browsing_history` VALUES (56, 4015, 1, 55, '2026-01-28');
INSERT INTO `browsing_history` VALUES (57, 4015, 1, 56, '2026-01-28');
INSERT INTO `browsing_history` VALUES (58, 4015, 1, 57, '2026-01-28');
INSERT INTO `browsing_history` VALUES (59, 4012, 1, 59, '2026-01-28');
INSERT INTO `browsing_history` VALUES (60, 4012, 1, 36, '2026-01-28');
INSERT INTO `browsing_history` VALUES (61, 4012, 1, 41, '2026-01-28');
INSERT INTO `browsing_history` VALUES (62, 4017, 1, 58, '2026-01-28');
INSERT INTO `browsing_history` VALUES (63, 4016, 1, 55, '2026-01-28');
INSERT INTO `browsing_history` VALUES (64, 4012, 1, 55, '2026-01-28');
INSERT INTO `browsing_history` VALUES (65, 4014, 1, 59, '2026-01-28');
INSERT INTO `browsing_history` VALUES (66, 4017, 1, 41, '2026-01-28');
INSERT INTO `browsing_history` VALUES (67, 4017, 1, 57, '2026-01-28');
INSERT INTO `browsing_history` VALUES (68, 4009, 2, 1, '2026-01-28');
INSERT INTO `browsing_history` VALUES (69, 4017, 1, 59, '2026-01-28');
INSERT INTO `browsing_history` VALUES (70, 4014, 1, 58, '2026-01-29');
INSERT INTO `browsing_history` VALUES (71, 4015, 1, 55, '2026-01-29');
INSERT INTO `browsing_history` VALUES (72, 4013, 1, 56, '2026-01-29');
INSERT INTO `browsing_history` VALUES (73, 4006, 2, 1, '2026-01-29');
INSERT INTO `browsing_history` VALUES (74, 4006, 2, 1, '2026-01-29');
INSERT INTO `browsing_history` VALUES (75, 4013, 1, 58, '2026-01-29');
INSERT INTO `browsing_history` VALUES (76, 4013, 1, 57, '2026-01-29');
INSERT INTO `browsing_history` VALUES (77, 4015, 1, 58, '2026-01-29');
INSERT INTO `browsing_history` VALUES (78, 4013, 1, 59, '2026-01-29');
INSERT INTO `browsing_history` VALUES (79, 4017, 1, 58, '2026-01-29');
INSERT INTO `browsing_history` VALUES (80, 4016, 1, 59, '2026-01-29');
INSERT INTO `browsing_history` VALUES (81, 4016, 1, 41, '2026-01-29');
INSERT INTO `browsing_history` VALUES (82, 4016, 1, 58, '2026-01-29');
INSERT INTO `browsing_history` VALUES (83, 4014, 1, 59, '2026-01-29');
INSERT INTO `browsing_history` VALUES (84, 4015, 1, 57, '2026-01-29');
INSERT INTO `browsing_history` VALUES (85, 4017, 1, 59, '2026-01-29');
INSERT INTO `browsing_history` VALUES (86, 4016, 1, 30, '2026-01-29');
INSERT INTO `browsing_history` VALUES (87, 4016, 1, 31, '2026-01-29');
INSERT INTO `browsing_history` VALUES (88, 4012, 1, 41, '2026-01-29');
INSERT INTO `browsing_history` VALUES (89, 4012, 1, 57, '2026-01-29');
INSERT INTO `browsing_history` VALUES (90, 4012, 1, 30, '2026-01-29');
INSERT INTO `browsing_history` VALUES (91, 4013, 1, 41, '2026-01-29');
INSERT INTO `browsing_history` VALUES (92, 4017, 1, 52, '2026-01-29');
INSERT INTO `browsing_history` VALUES (93, 4012, 1, 58, '2026-01-29');
INSERT INTO `browsing_history` VALUES (94, 4012, 1, 31, '2026-01-29');
INSERT INTO `browsing_history` VALUES (95, 4017, 1, 41, '2026-01-29');
INSERT INTO `browsing_history` VALUES (96, 4014, 1, 41, '2026-01-29');
INSERT INTO `browsing_history` VALUES (97, 4014, 1, 31, '2026-01-29');
INSERT INTO `browsing_history` VALUES (98, 4013, 1, 60, '2026-01-29');
INSERT INTO `browsing_history` VALUES (99, 4013, 1, 61, '2026-01-29');
INSERT INTO `browsing_history` VALUES (100, 4006, 2, 2, '2026-01-29');
INSERT INTO `browsing_history` VALUES (101, 4017, 1, 60, '2026-01-29');
INSERT INTO `browsing_history` VALUES (102, 4017, 1, 61, '2026-01-29');
INSERT INTO `browsing_history` VALUES (103, 4012, 1, 61, '2026-01-29');
INSERT INTO `browsing_history` VALUES (104, 4017, 1, 62, '2026-01-29');
INSERT INTO `browsing_history` VALUES (105, 4017, 1, 63, '2026-01-29');
INSERT INTO `browsing_history` VALUES (106, 4012, 1, 63, '2026-01-29');
INSERT INTO `browsing_history` VALUES (107, 4012, 1, 62, '2026-01-29');
INSERT INTO `browsing_history` VALUES (108, 4012, 1, 60, '2026-01-29');
INSERT INTO `browsing_history` VALUES (109, 4015, 1, 63, '2026-01-29');
INSERT INTO `browsing_history` VALUES (110, 4016, 1, 63, '2026-01-29');
INSERT INTO `browsing_history` VALUES (111, 4016, 1, 61, '2026-01-29');
INSERT INTO `browsing_history` VALUES (112, 4012, 1, 63, '2026-01-30');
INSERT INTO `browsing_history` VALUES (113, 4012, 1, 62, '2026-01-30');
INSERT INTO `browsing_history` VALUES (114, 4012, 1, 60, '2026-01-30');
INSERT INTO `browsing_history` VALUES (115, 4012, 1, 58, '2026-01-30');
INSERT INTO `browsing_history` VALUES (116, 4006, 1, 58, '2026-01-30');
INSERT INTO `browsing_history` VALUES (117, 4017, 1, 58, '2026-01-30');
INSERT INTO `browsing_history` VALUES (118, 4006, 1, 41, '2026-01-30');
INSERT INTO `browsing_history` VALUES (119, 4012, 1, 61, '2026-01-30');
INSERT INTO `browsing_history` VALUES (120, 4012, 1, 41, '2026-01-30');
INSERT INTO `browsing_history` VALUES (121, 4012, 1, 64, '2026-01-30');
INSERT INTO `browsing_history` VALUES (122, 4012, 1, 57, '2026-01-30');
INSERT INTO `browsing_history` VALUES (123, 4012, 1, 56, '2026-01-30');
INSERT INTO `browsing_history` VALUES (124, 4012, 1, 55, '2026-01-30');
INSERT INTO `browsing_history` VALUES (125, 4016, 1, 65, '2026-01-30');
INSERT INTO `browsing_history` VALUES (126, 4012, 1, 65, '2026-01-30');
INSERT INTO `browsing_history` VALUES (127, 4014, 1, 65, '2026-01-30');
INSERT INTO `browsing_history` VALUES (128, 4013, 1, 66, '2026-01-30');
INSERT INTO `browsing_history` VALUES (129, 4013, 1, 58, '2026-01-30');
INSERT INTO `browsing_history` VALUES (130, 4013, 1, 59, '2026-01-30');
INSERT INTO `browsing_history` VALUES (131, 4017, 1, 64, '2026-01-30');
INSERT INTO `browsing_history` VALUES (132, 4017, 1, 61, '2026-01-30');
INSERT INTO `browsing_history` VALUES (133, 4013, 1, 63, '2026-01-30');
INSERT INTO `browsing_history` VALUES (134, 4013, 1, 61, '2026-01-30');
INSERT INTO `browsing_history` VALUES (135, 4016, 1, 61, '2026-01-30');
INSERT INTO `browsing_history` VALUES (136, 4015, 1, 66, '2026-01-30');
INSERT INTO `browsing_history` VALUES (137, 4015, 1, 65, '2026-01-30');
INSERT INTO `browsing_history` VALUES (138, 4015, 1, 61, '2026-01-30');
INSERT INTO `browsing_history` VALUES (139, 4015, 1, 62, '2026-01-30');
INSERT INTO `browsing_history` VALUES (140, 4017, 1, 66, '2026-01-30');
INSERT INTO `browsing_history` VALUES (141, 4006, 2, 1, '2026-01-31');
INSERT INTO `browsing_history` VALUES (142, 4013, 1, 61, '2026-01-31');
INSERT INTO `browsing_history` VALUES (143, 4013, 1, 67, '2026-01-31');
INSERT INTO `browsing_history` VALUES (144, 4014, 1, 57, '2026-01-31');
INSERT INTO `browsing_history` VALUES (145, 4014, 1, 68, '2026-01-31');
INSERT INTO `browsing_history` VALUES (146, 4014, 1, 58, '2026-01-31');
INSERT INTO `browsing_history` VALUES (147, 4017, 1, 64, '2026-01-31');
INSERT INTO `browsing_history` VALUES (148, 4014, 1, 41, '2026-01-31');
INSERT INTO `browsing_history` VALUES (149, 4014, 1, 59, '2026-01-31');
INSERT INTO `browsing_history` VALUES (150, 4013, 1, 60, '2026-01-31');
INSERT INTO `browsing_history` VALUES (151, 4011, 1, 69, '2026-01-31');
INSERT INTO `browsing_history` VALUES (152, 4016, 1, 69, '2026-01-31');
INSERT INTO `browsing_history` VALUES (153, 4016, 1, 68, '2026-01-31');
INSERT INTO `browsing_history` VALUES (154, 4016, 1, 67, '2026-01-31');
INSERT INTO `browsing_history` VALUES (155, 4016, 1, 58, '2026-01-31');
INSERT INTO `browsing_history` VALUES (156, 4013, 1, 68, '2026-01-31');
INSERT INTO `browsing_history` VALUES (157, 4013, 1, 69, '2026-01-31');
INSERT INTO `browsing_history` VALUES (158, 4013, 1, 58, '2026-01-31');
INSERT INTO `browsing_history` VALUES (159, 4012, 1, 40, '2026-01-31');
INSERT INTO `browsing_history` VALUES (160, 4016, 1, 73, '2026-01-31');
INSERT INTO `browsing_history` VALUES (161, 4016, 1, 76, '2026-01-31');
INSERT INTO `browsing_history` VALUES (162, 4006, 1, 59, '2026-01-31');
INSERT INTO `browsing_history` VALUES (163, 4012, 1, 71, '2026-01-31');
INSERT INTO `browsing_history` VALUES (164, 4012, 1, 76, '2026-01-31');
INSERT INTO `browsing_history` VALUES (165, 4006, 1, 70, '2026-01-31');
INSERT INTO `browsing_history` VALUES (166, 4006, 1, 77, '2026-01-31');
INSERT INTO `browsing_history` VALUES (167, 4006, 1, 78, '2026-01-31');
INSERT INTO `browsing_history` VALUES (168, 4006, 1, 58, '2026-01-31');
INSERT INTO `browsing_history` VALUES (169, 4009, 1, 73, '2026-02-01');
INSERT INTO `browsing_history` VALUES (170, 4012, 1, 73, '2026-02-02');

-- ----------------------------
-- Table structure for delivery_fee_config
-- ----------------------------
DROP TABLE IF EXISTS `delivery_fee_config`;
CREATE TABLE `delivery_fee_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配置名称',
  `base_fee` decimal(10, 2) NOT NULL COMMENT '起步价',
  `base_distance` decimal(10, 2) NOT NULL COMMENT '起步距离(公里)',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用 1-启用',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '配送费配置主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivery_fee_config
-- ----------------------------
INSERT INTO `delivery_fee_config` VALUES (1, '标准配送方案', 15.00, 3.00, 1, '2026-01-22 08:01:44', '2026-01-22 08:01:44');
INSERT INTO `delivery_fee_config` VALUES (2, '经济配送方案', 10.00, 5.00, 1, '2026-01-22 08:01:44', '2026-01-22 08:01:44');
INSERT INTO `delivery_fee_config` VALUES (3, '加急配送方案', 25.00, 2.00, 1, '2026-01-22 08:01:44', '2026-01-22 08:01:44');
INSERT INTO `delivery_fee_config` VALUES (8, '默认配送费配置', 0.00, 1.00, 1, '2026-01-06 13:53:48', '2026-01-14 09:40:58');
INSERT INTO `delivery_fee_config` VALUES (9, '夜间配送', 5.00, 2.00, 1, '2026-01-06 13:53:48', '2026-01-06 13:53:48');
INSERT INTO `delivery_fee_config` VALUES (10, '远距离配送', 5.00, 3.00, 1, '2026-01-06 13:53:48', '2026-01-06 13:53:48');
INSERT INTO `delivery_fee_config` VALUES (11, '默认配送费配置', 0.00, 1.00, 1, '2026-01-14 10:27:20', '2026-01-14 10:27:20');
INSERT INTO `delivery_fee_config` VALUES (12, '默认配送费配置', 0.00, 1.00, 1, '2026-01-14 10:27:42', '2026-01-14 10:27:42');

-- ----------------------------
-- Table structure for delivery_fee_rule
-- ----------------------------
DROP TABLE IF EXISTS `delivery_fee_rule`;
CREATE TABLE `delivery_fee_rule`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `config_id` int NOT NULL COMMENT '关联配置表id',
  `rule_type` enum('distance','time') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规则类型:distance距离,time时间',
  `distance_start` decimal(10, 2) NULL DEFAULT NULL COMMENT '起始距离(公里)',
  `distance_end` decimal(10, 2) NULL DEFAULT NULL COMMENT '结束距离(公里)',
  `price_per_km` decimal(10, 2) NULL DEFAULT NULL COMMENT '每公里价格',
  `time_start` time NULL DEFAULT NULL COMMENT '开始时间',
  `time_end` time NULL DEFAULT NULL COMMENT '结束时间',
  `time_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '时段附加费',
  `time_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '时段类型:daytime白天,night夜间',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '配送费规则明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivery_fee_rule
-- ----------------------------
INSERT INTO `delivery_fee_rule` VALUES (1, 1, 'distance', 0.00, 5.00, 8.00, NULL, NULL, NULL, NULL, 1);
INSERT INTO `delivery_fee_rule` VALUES (2, 1, 'distance', 5.01, 10.00, 6.50, NULL, NULL, NULL, NULL, 2);
INSERT INTO `delivery_fee_rule` VALUES (3, 1, 'distance', 10.01, 20.00, 5.50, NULL, NULL, NULL, NULL, 3);
INSERT INTO `delivery_fee_rule` VALUES (4, 1, 'distance', 20.01, 30.00, 4.80, NULL, NULL, NULL, NULL, 4);
INSERT INTO `delivery_fee_rule` VALUES (5, 1, 'distance', 30.01, 40.00, 4.20, NULL, NULL, NULL, NULL, 5);
INSERT INTO `delivery_fee_rule` VALUES (6, 1, 'distance', 40.01, 50.00, 3.80, NULL, NULL, NULL, NULL, 6);
INSERT INTO `delivery_fee_rule` VALUES (7, 1, 'distance', 50.01, 60.00, 3.50, NULL, NULL, NULL, NULL, 7);
INSERT INTO `delivery_fee_rule` VALUES (8, 1, 'distance', 60.01, 70.00, 3.20, NULL, NULL, NULL, NULL, 8);
INSERT INTO `delivery_fee_rule` VALUES (9, 1, 'distance', 70.01, 80.00, 3.00, NULL, NULL, NULL, NULL, 9);
INSERT INTO `delivery_fee_rule` VALUES (10, 1, 'time', NULL, NULL, NULL, '08:00:00', '22:00:00', 0.00, 'daytime', 10);
INSERT INTO `delivery_fee_rule` VALUES (11, 1, 'time', NULL, NULL, NULL, '22:00:00', '08:00:00', 15.00, 'night', 11);
INSERT INTO `delivery_fee_rule` VALUES (12, 1, 'time', NULL, NULL, NULL, '07:00:00', '09:00:00', 5.00, 'daytime', 12);
INSERT INTO `delivery_fee_rule` VALUES (13, 1, 'time', NULL, NULL, NULL, '17:00:00', '20:00:00', 5.00, 'daytime', 13);
INSERT INTO `delivery_fee_rule` VALUES (14, 2, 'distance', 0.00, 10.00, 5.00, NULL, NULL, NULL, NULL, 1);
INSERT INTO `delivery_fee_rule` VALUES (15, 2, 'distance', 10.01, 20.00, 4.50, NULL, NULL, NULL, NULL, 2);
INSERT INTO `delivery_fee_rule` VALUES (16, 2, 'distance', 20.01, 40.00, 3.80, NULL, NULL, NULL, NULL, 3);
INSERT INTO `delivery_fee_rule` VALUES (17, 2, 'distance', 40.01, 60.00, 3.00, NULL, NULL, NULL, NULL, 4);
INSERT INTO `delivery_fee_rule` VALUES (18, 2, 'distance', 60.01, 80.00, 2.50, NULL, NULL, NULL, NULL, 5);
INSERT INTO `delivery_fee_rule` VALUES (19, 2, 'time', NULL, NULL, NULL, '06:00:00', '23:00:00', 0.00, 'daytime', 6);
INSERT INTO `delivery_fee_rule` VALUES (20, 2, 'time', NULL, NULL, NULL, '23:00:00', '06:00:00', 20.00, 'night', 7);
INSERT INTO `delivery_fee_rule` VALUES (21, 3, 'distance', 0.00, 15.00, 12.00, NULL, NULL, NULL, NULL, 1);
INSERT INTO `delivery_fee_rule` VALUES (22, 3, 'distance', 15.01, 30.00, 10.00, NULL, NULL, NULL, NULL, 2);
INSERT INTO `delivery_fee_rule` VALUES (23, 3, 'distance', 30.01, 50.00, 8.50, NULL, NULL, NULL, NULL, 3);
INSERT INTO `delivery_fee_rule` VALUES (24, 3, 'distance', 50.01, 80.00, 7.00, NULL, NULL, NULL, NULL, 4);
INSERT INTO `delivery_fee_rule` VALUES (25, 3, 'time', NULL, NULL, NULL, '00:00:00', '23:59:59', 0.00, 'daytime', 5);

-- ----------------------------
-- Table structure for delivery_order
-- ----------------------------
DROP TABLE IF EXISTS `delivery_order`;
CREATE TABLE `delivery_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `merchant_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商家名称',
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配送地址',
  `receiver_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人电话',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `items_amount` decimal(10, 2) NOT NULL COMMENT '商品总金额',
  `delivery_fee` decimal(10, 2) NOT NULL COMMENT '配送费',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '订单总金额',
  `merchant_profit` decimal(10, 2) NULL DEFAULT NULL COMMENT '商家预计收入',
  `order_status` tinyint NOT NULL DEFAULT 1 COMMENT '订单状态:0-已取消 1-待支付 2-待接单 3-待取货 4-配送中 5-已完成',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态:0-未支付 1-已支付 2-已退款',
  `pay_method` tinyint NULL DEFAULT NULL COMMENT '支付方式:1-微信支付 2-支付宝 3-银行卡',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `rider_id` bigint NULL DEFAULT NULL COMMENT '配送骑手ID',
  `rider_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配送骑手姓名',
  `rider_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '骑手电话',
  `cancel_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '取消原因',
  `has_reviewed` tinyint(1) NULL DEFAULT 0 COMMENT '是否已评价',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `accept_time` datetime NULL DEFAULT NULL COMMENT '接单时间',
  `delivery_start_time` datetime NULL DEFAULT NULL COMMENT '配送开始时间',
  `expected_delivery_time` datetime NULL DEFAULT NULL COMMENT '预计送达时间',
  `completed_at` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `version` int NULL DEFAULT NULL COMMENT '乐观锁版本号',
  `transaction_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易号',
  `refund_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信退款单号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_merchant_id`(`merchant_id` ASC) USING BTREE,
  INDEX `idx_rider_id`(`rider_id` ASC) USING BTREE,
  INDEX `idx_order_status`(`order_status` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '外卖订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivery_order
-- ----------------------------
INSERT INTO `delivery_order` VALUES (1, 'Q20260105', 1, 1, '校园美食城', '石家庄', 'user', '12222222222', NULL, 8.00, 2.00, 10.00, 8.00, 2, 1, 1, '2026-01-07 20:14:21', 1010, '派大星', NULL, NULL, 0, '2026-01-06 20:39:41', NULL, '2026-01-07 20:39:47', '2026-01-07 20:39:50', NULL, '2026-01-07 20:55:32', '2026-01-07 20:55:29', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (3, 'DO20260107212010513d68e8a5', 1, 10, '幸福里便利店', '某某社区1号楼-1-101', '张三', '18888888888', NULL, 7.00, 2.00, 9.00, 7.00, 1, 0, 1, '2026-01-08 09:19:00', NULL, '', NULL, NULL, 0, '2026-01-07 21:20:10', NULL, NULL, '2026-01-07 22:20:11', NULL, '2026-01-07 21:20:11', '2026-01-08 09:23:01', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (4, 'Q20260106', 1, 1, '校园美食城', '裕华区', 'user1', '13333333333', NULL, 7.00, 2.00, 9.00, 7.00, 3, 1, 1, '2026-01-08 09:22:48', 1011, '小明骑手', NULL, NULL, 0, '2026-01-08 09:23:16', NULL, '2026-01-08 09:23:21', '2026-01-08 09:23:24', NULL, '2026-01-08 09:20:13', '2026-01-08 09:24:11', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (5, 'Q20260107', 1, 1, '校园美食城', '长安区', 'user2', '15555555555', NULL, 12.00, 1.00, 13.00, 12.00, 4, 1, 1, '2026-01-08 09:25:12', 2001, '李四骑手', NULL, NULL, 0, '2026-01-08 09:25:38', NULL, '2026-01-08 09:25:45', '2026-01-08 09:25:49', NULL, '2026-01-08 09:24:19', '2026-01-08 09:25:55', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (6, 'Q20260108', 1, 1, '校园美食城', '桥西区', 'user3', '16666666666', NULL, 131.00, 2.00, 15.00, 13.00, 5, 1, 1, '2026-01-08 09:27:10', 1011, '小明骑手', NULL, NULL, 0, '2026-01-08 09:27:25', NULL, '2026-01-08 09:27:28', '2026-01-08 09:27:32', NULL, '2026-01-08 09:26:16', '2026-01-08 09:27:28', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (7, 'DO202601221612211ba1f56482', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.44, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-22 16:12:21', NULL, NULL, '2026-01-22 17:12:22', NULL, '2026-01-22 16:12:22', '2026-01-28 09:34:59', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (8, 'DO20260122161235f1501a779d', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.44, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-22 16:12:35', NULL, NULL, '2026-01-22 17:12:35', NULL, '2026-01-22 16:12:35', '2026-01-28 09:34:57', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (9, 'DO20260126163455cc0ec66aee', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-26 16:34:55', NULL, NULL, '2026-01-26 17:34:56', NULL, '2026-01-26 16:34:56', '2026-01-28 09:34:56', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (10, 'DO20260126165525335be0e4f1', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.09, 3, 1, NULL, '2026-01-26 16:57:48', NULL, NULL, NULL, NULL, 0, '2026-01-26 16:55:25', '2026-02-09 15:32:44', NULL, '2026-01-26 17:55:25', NULL, '2026-01-26 16:55:25', '2026-02-09 15:32:44', NULL, NULL, '4200002924202601268825243055', NULL);
INSERT INTO `delivery_order` VALUES (11, 'DO2026012616552701d1af444e', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-26 16:55:28', NULL, NULL, '2026-01-26 17:55:28', NULL, '2026-01-26 16:55:28', '2026-01-28 09:34:53', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (12, 'DO202601281117537eb6076a9a', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 11:17:54', NULL, NULL, '2026-01-28 12:17:54', NULL, '2026-01-28 11:17:54', '2026-01-29 12:27:36', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (13, 'DO20260128111941b4551cdd34', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 11:19:41', NULL, NULL, '2026-01-28 12:19:41', NULL, '2026-01-28 11:19:41', '2026-01-29 12:27:37', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (14, 'DO20260128113223b58def6d48', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 11:32:23', NULL, NULL, '2026-01-28 12:32:24', NULL, '2026-01-28 11:32:24', '2026-01-29 12:27:38', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (15, 'DO202601281134572955d40ec0', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 11:34:58', NULL, NULL, '2026-01-28 12:34:58', NULL, '2026-01-28 11:34:58', '2026-01-29 12:27:39', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (16, 'DO20260128113602fc192da1fe', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.26, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 11:36:02', NULL, NULL, '2026-01-28 12:36:03', NULL, '2026-01-28 11:36:03', '2026-01-29 12:27:40', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (17, 'DO20260128121615127190f308', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', NULL, 0.01, 147.85, 0.01, 0.35, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 12:16:15', NULL, NULL, '2026-01-28 13:16:16', NULL, '2026-01-28 12:16:16', '2026-01-29 12:27:41', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (18, 'DO20260128122815b3b669956e', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', '', 0.01, 147.85, 0.01, 0.35, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 12:28:15', NULL, NULL, '2026-01-28 15:16:15', NULL, '2026-01-28 12:28:15', '2026-01-29 12:27:42', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (19, 'DO202601281905460814af8daa', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', '', 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-28 19:05:47', NULL, NULL, '2026-01-28 20:05:47', NULL, '2026-01-28 19:05:47', '2026-01-29 12:27:43', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (20, 'DO20260129192550316eed75db', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', '', 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-29 19:25:50', NULL, NULL, '2026-01-29 20:25:50', NULL, '2026-01-29 19:25:50', '2026-01-29 12:27:44', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (21, 'DO2026012919355314f5f172ce', 4006, 1, '校园美食城', '江苏省南京市江宁区河海大学2111', '诺', '18111111111', '', 0.01, 147.85, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-29 19:35:53', NULL, NULL, '2026-01-29 20:35:54', NULL, '2026-01-29 19:35:54', '2026-01-29 12:27:45', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (22, 'DO20260129194415c772cd6b7d', 4006, 1, '校园美食城', '江苏省南京市江宁区盛江花苑2111', '诺', '18111111111', '', 0.01, 67.00, 0.01, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-29 19:44:16', NULL, NULL, '2026-01-29 20:44:16', NULL, '2026-01-29 19:44:16', '2026-01-29 12:27:46', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (23, 'DO202601291959068e21029300', 4006, 1, '校园美食城', '江苏省南京市江宁区盛江花苑2111', '诺', '18111111111', '', 0.01, 67.00, 0.01, 0.18, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-29 19:59:06', NULL, NULL, '2026-01-29 20:59:06', NULL, '2026-01-29 19:59:06', '2026-01-29 12:27:49', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (24, 'DO20260129203626dfe253f95f', 4006, 1, '校园美食城', '江苏省南京市江宁区盛江花苑2111', '诺', '18111111111', '', 3.50, 67.00, 70.50, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-29 20:36:27', NULL, NULL, '2026-01-29 21:36:27', NULL, '2026-01-29 20:36:27', '2026-01-29 20:36:27', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (25, 'DO2026012920471004667dfe1c', 4006, 1, '校园美食城', '江苏省南京市江宁区盛江花苑2111', '诺', '18111111111', '', 3.50, 67.00, 70.50, 0.09, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2026-01-29 20:47:11', NULL, NULL, '2026-01-29 21:47:11', NULL, '2026-01-29 20:47:11', '2026-01-29 20:47:11', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (26, 'Q20260109', 1, 2, '快乐奶茶店', '石家庄', 'tbl', '11111111111', NULL, 8.00, 2.00, 10.00, 8.00, 1, 1, 1, '2026-02-05 11:45:33', 1010, '派大星', NULL, NULL, 0, '2026-02-05 11:48:15', NULL, NULL, '2026-02-05 12:48:29', NULL, '2026-02-05 11:46:03', '2026-02-05 11:46:08', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (27, 'DO20260209001', 1, 1, '校园美食城', '河北省石家庄市裕华区', 'rider', '13899990000', NULL, 15.00, 3.00, 18.00, 15.00, 1, 0, 1, '2026-02-09 10:00:00', NULL, NULL, NULL, NULL, 0, '2026-02-09 10:00:00', NULL, NULL, '2026-02-09 11:00:00', NULL, '2026-02-09 10:00:00', '2026-02-09 10:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (28, 'DO20260209002', 1, 1, '快乐奶茶店', '河北省石家庄市长安区', 'rider', '13899990000', NULL, 12.50, 2.50, 15.00, 12.50, 2, 1, 1, '2026-02-09 11:30:00', 1, 'rider', '13899990000', NULL, 0, '2026-02-09 11:30:00', NULL, '2026-02-09 11:35:00', '2026-02-09 12:30:00', NULL, '2026-02-09 11:30:00', '2026-02-09 11:30:00', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (29, 'DO20260209003', 1, 1, '汉堡王', '河北省石家庄市桥西区', 'rider', '13899990000', NULL, 25.00, 4.00, 29.00, 25.00, 3, 1, 1, '2026-02-09 14:00:00', 1, 'rider', '13899990000', NULL, 0, '2026-02-09 14:00:00', NULL, '2026-02-09 14:05:00', '2026-02-09 14:10:00', NULL, '2026-02-09 14:00:00', '2026-02-09 14:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `delivery_order` VALUES (30, 'DO20260209004', 1, 1, '肯德基', '河北省石家庄市新华区', 'rider', '13899990000', NULL, 32.00, 5.00, 37.00, 32.00, 4, 1, 1, '2026-02-09 16:00:00', 1, 'rider', '13899990000', NULL, 0, '2026-02-09 16:00:00', NULL, '2026-02-09 16:05:00', '2026-02-09 16:10:00', '2026-02-09 16:25:00', '2026-02-09 16:00:00', '2026-02-09 16:00:00', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for delivery_order_item
-- ----------------------------
DROP TABLE IF EXISTS `delivery_order_item`;
CREATE TABLE `delivery_order_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `product_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品图片URL',
  `spec` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品规格',
  `quantity` int NOT NULL COMMENT '商品数量',
  `price` decimal(10, 2) NOT NULL COMMENT '商品单价',
  `subtotal` decimal(10, 2) NOT NULL COMMENT '小计金额',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id` ASC) USING BTREE,
  INDEX `idx_product_id`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外卖订单商品明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivery_order_item
-- ----------------------------
INSERT INTO `delivery_order_item` VALUES (1, 3, 2, '可口可乐', 'http://example.com/product.jpg', NULL, 2, 3.50, 7.00, '2026-01-07 21:20:11', '2026-01-07 21:20:11');
INSERT INTO `delivery_order_item` VALUES (2, 7, 2, '可口可乐', 'http://example.com/product.jpg', NULL, 5, 3.50, 17.50, '2026-01-22 16:12:22', '2026-01-22 16:12:22');
INSERT INTO `delivery_order_item` VALUES (3, 8, 2, '可口可乐', 'http://example.com/product.jpg', NULL, 5, 3.50, 17.50, '2026-01-22 16:12:35', '2026-01-22 16:12:35');
INSERT INTO `delivery_order_item` VALUES (4, 9, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-26 16:34:56', '2026-01-26 16:34:56');
INSERT INTO `delivery_order_item` VALUES (5, 10, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-26 16:55:25', '2026-01-26 16:55:25');
INSERT INTO `delivery_order_item` VALUES (6, 11, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-26 16:55:28', '2026-01-26 16:55:28');
INSERT INTO `delivery_order_item` VALUES (7, 12, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-28 11:17:54', '2026-01-28 11:17:54');
INSERT INTO `delivery_order_item` VALUES (8, 13, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-28 11:19:41', '2026-01-28 11:19:41');
INSERT INTO `delivery_order_item` VALUES (9, 14, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-28 11:32:24', '2026-01-28 11:32:24');
INSERT INTO `delivery_order_item` VALUES (10, 15, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-28 11:34:58', '2026-01-28 11:34:58');
INSERT INTO `delivery_order_item` VALUES (11, 16, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 3, 3.50, 10.50, '2026-01-28 11:36:03', '2026-01-28 11:36:03');
INSERT INTO `delivery_order_item` VALUES (12, 17, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 4, 3.50, 14.00, '2026-01-28 12:16:16', '2026-01-28 12:16:16');
INSERT INTO `delivery_order_item` VALUES (13, 18, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 4, 3.50, 14.00, '2026-01-28 12:28:15', '2026-01-28 12:28:15');
INSERT INTO `delivery_order_item` VALUES (14, 19, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-28 19:05:47', '2026-01-28 19:05:47');
INSERT INTO `delivery_order_item` VALUES (15, 20, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-29 19:25:50', '2026-01-29 19:25:50');
INSERT INTO `delivery_order_item` VALUES (16, 21, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-29 19:35:54', '2026-01-29 19:35:54');
INSERT INTO `delivery_order_item` VALUES (17, 22, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-29 19:44:16', '2026-01-29 19:44:16');
INSERT INTO `delivery_order_item` VALUES (18, 23, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 2, 3.50, 7.00, '2026-01-29 19:59:06', '2026-01-29 19:59:06');
INSERT INTO `delivery_order_item` VALUES (19, 24, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-29 20:36:27', '2026-01-29 20:36:27');
INSERT INTO `delivery_order_item` VALUES (20, 25, 2, '可口可乐', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 3.50, 3.50, '2026-01-29 20:47:11', '2026-01-29 20:47:11');
INSERT INTO `delivery_order_item` VALUES (21, 27, 1, '宫保鸡丁套餐', 'https://example.com/product1.jpg', '大份', 1, 15.00, 15.00, '2026-02-09 10:00:00', '2026-02-09 10:00:00');
INSERT INTO `delivery_order_item` VALUES (22, 28, 2, '珍珠奶茶', 'https://example.com/product2.jpg', '中杯', 2, 6.00, 12.00, '2026-02-09 11:30:00', '2026-02-09 11:30:00');
INSERT INTO `delivery_order_item` VALUES (23, 28, 3, '薯条', 'https://example.com/product3.jpg', '大份', 1, 0.50, 0.50, '2026-02-09 11:30:00', '2026-02-09 11:30:00');
INSERT INTO `delivery_order_item` VALUES (24, 29, 4, '巨无霸汉堡套餐', 'https://example.com/product4.jpg', '套餐', 1, 25.00, 25.00, '2026-02-09 14:00:00', '2026-02-09 14:00:00');
INSERT INTO `delivery_order_item` VALUES (25, 30, 5, '香辣鸡腿堡套餐', 'https://example.com/product5.jpg', '套餐', 1, 18.00, 18.00, '2026-02-09 16:00:00', '2026-02-09 16:00:00');
INSERT INTO `delivery_order_item` VALUES (26, 30, 6, '可乐', 'https://example.com/product6.jpg', '大杯', 2, 7.00, 14.00, '2026-02-09 16:00:00', '2026-02-09 16:00:00');

-- ----------------------------
-- Table structure for forum_activity
-- ----------------------------
DROP TABLE IF EXISTS `forum_activity`;
CREATE TABLE `forum_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `activity_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动标题',
  `activity_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动内容',
  `activity_venue` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动场地',
  `publisher_id` bigint NULL DEFAULT NULL COMMENT '发布人ID',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '发布区域-学校ID',
  `max_participants` int UNSIGNED NULL DEFAULT 0 COMMENT '最大报名人数',
  `like_count` int UNSIGNED NULL DEFAULT 0 COMMENT '点赞数',
  `comment_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论数',
  `share_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '分享数',
  `current_participants` int UNSIGNED NULL DEFAULT 0 COMMENT '当前报名人数',
  `registration_start_time` datetime NULL DEFAULT NULL COMMENT '报名开始时间',
  `registration_end_time` datetime NULL DEFAULT NULL COMMENT '报名截止时间',
  `activity_time` datetime NOT NULL COMMENT '活动时间',
  `images` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '活动图片(JSON数组)',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审批表关联ID',
  `is_visible` tinyint NOT NULL DEFAULT 1 COMMENT '是否显示:0-隐藏 1-显示',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '活动状态:0-草稿 1-待审核 2-已发布 3-已取消',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_school`(`school_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_time`(`activity_time` ASC) USING BTREE,
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE,
  INDEX `idx_registration`(`registration_start_time` ASC, `registration_end_time` ASC) USING BTREE,
  INDEX `idx_visible`(`is_visible` ASC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_activity
-- ----------------------------
INSERT INTO `forum_activity` VALUES (2, '标题111111', '<p>12313</p>', '123', NULL, 1, 30, 1, 0, 0, 0, '2026-12-18 10:10:30', '2025-12-31 20:21:15', '2025-12-18 20:21:05', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', NULL, 1, 2, '2025-12-10 20:20:54', '2026-01-21 19:40:17', NULL);
INSERT INTO `forum_activity` VALUES (3, '我的漫展12', '玩', '西安', NULL, 10009, 30, 0, 1, 0, 0, '2025-12-18 10:10:30', '2025-12-20 10:10:30', '2025-12-18 10:10:30', NULL, NULL, 1, 0, '2025-12-13 16:51:09', '2026-01-14 09:47:23', NULL);
INSERT INTO `forum_activity` VALUES (11, '去旅游啊', '玩', NULL, NULL, 1, 30, 0, 0, 0, 1, '2025-12-18 10:10:30', '2025-12-20 10:10:30', '2025-12-18 10:10:30', NULL, NULL, 1, 0, '2025-12-15 14:22:27', '2025-12-16 17:35:21', NULL);
INSERT INTO `forum_activity` VALUES (13, '周一', '<p>11111111</p>', NULL, NULL, 1, 0, 0, 0, 0, 0, NULL, NULL, '2025-12-16 00:00:00', NULL, NULL, 1, 0, '2025-12-15 16:36:36', '2025-12-15 16:49:13', NULL);
INSERT INTO `forum_activity` VALUES (14, '汉堡', '<p>喀拉拉啊啊</p>', NULL, NULL, 1, 0, 0, 0, 0, 0, NULL, NULL, '2025-12-31 00:00:00', NULL, NULL, 1, 0, '2025-12-15 16:37:13', '2025-12-23 17:55:13', '2025-12-23 17:55:13');
INSERT INTO `forum_activity` VALUES (15, '旅游555', '玩', '西安', NULL, 1, 30, 0, 0, 0, 0, '2025-12-18 10:10:30', '2025-12-20 10:10:30', '2025-12-18 10:10:30', NULL, NULL, 1, 0, '2025-12-15 20:42:50', '2025-12-15 20:42:50', NULL);
INSERT INTO `forum_activity` VALUES (16, '旅游660', '玩', '西安', NULL, 1, 30, 0, 0, 0, 0, '2025-12-18 10:10:30', '2025-12-20 10:10:30', '2025-12-18 10:10:30', NULL, NULL, 1, 0, '2025-12-15 20:54:41', '2025-12-15 20:54:41', NULL);
INSERT INTO `forum_activity` VALUES (17, '旅游', '玩', '西安', NULL, 1, 30, 1, 0, 0, 0, '2025-12-18 10:10:30', '2025-12-20 10:10:30', '2025-12-18 10:10:30', NULL, 1040, 1, 2, '2025-12-16 09:18:28', '2026-01-04 17:10:57', '2026-01-04 17:10:57');
INSERT INTO `forum_activity` VALUES (18, '旅游111', '玩', '西安', NULL, 1, 30, 0, 0, 0, 0, '2025-12-18 10:10:30', '2025-12-20 10:10:30', '2025-12-18 10:10:30', NULL, 1042, 1, 3, '2025-12-16 09:21:07', '2025-12-29 09:41:52', '2025-12-29 09:41:52');
INSERT INTO `forum_activity` VALUES (19, '周三', '<p>吃冰激凌</p>', NULL, NULL, 1, NULL, 0, 0, 0, 0, NULL, NULL, '2025-12-17 00:00:00', NULL, 1044, 0, 2, '2025-12-16 12:03:39', '2025-12-23 17:37:45', '2025-12-23 17:37:45');
INSERT INTO `forum_activity` VALUES (20, '活动标题', '<p>活动内容</p>', NULL, NULL, 10009, 100, 0, 0, 0, 0, '2026-01-08 00:00:00', '2026-02-28 00:00:00', '2026-01-13 00:00:00', NULL, 1061, 1, 2, '2026-01-09 19:18:15', '2026-01-14 09:12:38', NULL);
INSERT INTO `forum_activity` VALUES (21, '测试活动标题', '<p>活动内容</p>', NULL, NULL, 10008, 200, 0, 0, 0, 3, '2026-01-01 00:00:00', '2026-01-23 00:00:00', '2026-01-20 00:00:00', NULL, 1062, 1, 2, '2026-01-13 15:08:59', '2026-01-16 09:42:23', NULL);
INSERT INTO `forum_activity` VALUES (22, '春游', '<p>去公园玩老鹰抓小鸡</p>', NULL, NULL, 10009, 1, 0, 18, 0, 1, '2026-01-08 00:00:00', '2026-02-27 00:00:00', '2026-02-28 00:00:00', NULL, 1063, 1, 2, '2026-01-14 12:13:00', '2026-01-23 07:04:45', NULL);
INSERT INTO `forum_activity` VALUES (26, '活动活动', '123123', '操场', 4006, 10024, 10, 0, 0, 0, 0, '2026-01-24 02:02:47', '2027-01-24 02:02:00', '2028-01-24 02:02:00', '[\"http://172.16.8.163:8094/uploads/2026-01-24/e1a21581073d4060a0f08bc35f1cc273.png\"]', NULL, 1, 0, '2026-01-24 02:04:49', '2026-01-24 02:04:49', NULL);
INSERT INTO `forum_activity` VALUES (29, '扫雪', '大家快来扫雪丫', '二食堂前小路', 4006, 10024, 30, 0, 7, 0, 0, '2026-01-24 03:34:58', '2026-02-12 03:34:00', '2026-02-13 05:30:00', '[\"http://172.16.8.163:8094/uploads/2026-01-24/7f3892593640487087556179a0fbb003.jpg\"]', NULL, 1, 0, '2026-01-24 03:38:01', '2026-01-29 06:26:19', NULL);
INSERT INTO `forum_activity` VALUES (30, '校园歌手大赛', '报名参加！', '大学生活动中心', 1, 1, 100, 3, 4, 0, 0, '2026-01-24 03:25:09', '2026-01-24 03:25:09', '2026-01-24 03:25:09', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', 1079, 1, 2, '2026-01-24 03:49:43', '2026-01-29 15:20:26', NULL);
INSERT INTO `forum_activity` VALUES (31, '种树', '123', '312', 4006, 10009, 2133, 4, 11, 0, 2, '2026-01-24 07:36:58', '2026-02-21 07:36:00', '2026-02-24 07:36:00', '[\"http://172.16.8.163:8094/uploads/2026-01-24/6616f68bd6fa4aebb9f5a0cbbc4d25e0.png\"]', 1080, 1, 2, '2026-01-24 07:37:36', '2026-01-31 07:28:11', NULL);
INSERT INTO `forum_activity` VALUES (32, '校园歌手大赛', '一年一度的校园歌手大赛即将开始，欢迎同学们踊跃报名参加！', '大学生活动中心', 1, 1, 100, 0, 0, 0, 0, '2026-01-30 01:44:32', '2026-02-20 01:44:32', '2026-02-28 01:44:32', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', 1090, 1, 0, '2026-01-30 09:45:29', '2026-01-30 09:45:29', NULL);
INSERT INTO `forum_activity` VALUES (33, '来来', '好想来', '办公室', 4017, 10019, 1000, 0, 0, 0, 0, '2026-01-30 00:00:00', '2027-01-27 23:59:59', '2027-01-30 09:00:00', '[\"https://oa.baoerkeji.com.cn/group2/uploads/2026-01-30/f0242f905e2c4e5c8b106857352aa48e.png\"]', 1091, 1, 0, '2026-01-30 10:59:29', '2026-01-30 10:59:29', NULL);
INSERT INTO `forum_activity` VALUES (34, '露营', '露营', '师大操场', 4010, 10009, 20, 0, 0, 0, 0, '2026-01-31 02:16:29', '2026-01-31 04:16:00', '2026-01-31 10:16:00', '[]', 1092, 1, 2, '2026-01-31 10:20:36', '2026-01-31 10:20:36', NULL);
INSERT INTO `forum_activity` VALUES (35, '露营', '露营123', '师大操场', 4010, 10009, 20, 0, 0, 0, 0, '2026-01-31 02:24:29', '2026-01-31 10:24:00', '2026-02-01 10:24:00', '[]', 1093, 1, 2, '2026-01-31 10:26:15', '2026-01-31 10:26:15', NULL);
INSERT INTO `forum_activity` VALUES (36, '露营', '露营234', '师大操场', 4010, 10009, 20, 1, 1, 0, 3, '2026-01-31 02:24:29', '2026-01-31 19:24:00', '2026-02-01 11:24:00', '[]', 1094, 1, 2, '2026-01-31 10:28:42', '2026-01-31 06:57:43', NULL);
INSERT INTO `forum_activity` VALUES (37, '参观保尔', '看人家敲代码', '河北师大科技楼C座', 4016, 10005, 20, 0, 2, 0, 0, '2026-01-31 16:00:00', '2026-02-01 16:00:00', '2026-02-03 02:00:00', '[]', 1095, 1, 0, '2026-01-31 11:07:02', '2026-01-31 03:36:13', NULL);
INSERT INTO `forum_activity` VALUES (38, '校园歌手大赛', '一年一度的校园歌手大赛即将开始，欢迎同学们踊跃报名参加！', '大学生活动中心', 1, 1, 100, 0, 0, 0, 0, '2026-01-31 03:32:41', '2026-01-31 03:32:41', '2026-01-31 03:32:41', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', 1096, 1, 0, '2026-01-31 11:32:48', '2026-01-31 11:32:48', NULL);
INSERT INTO `forum_activity` VALUES (39, '123', '123', '大学生活动中心', 1, 1, 100, 0, 0, 0, 0, '2026-01-31 03:32:41', '2026-01-31 03:32:41', '2026-01-31 03:32:41', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', 1097, 1, 0, '2026-01-31 11:33:13', '2026-01-31 11:33:13', NULL);
INSERT INTO `forum_activity` VALUES (40, '羽毛球', '羽毛球·', '体育产', 4012, 10022, 10, 0, 0, 0, 0, '2026-01-30 16:00:00', '2026-01-31 16:00:00', '2026-02-01 16:00:00', '[\"/uploads/2026-01-31/4a608ca797814129a7e920295bce43ce.jpeg\"]', 1098, 1, 0, '2026-01-31 12:13:35', '2026-01-31 12:13:35', NULL);
INSERT INTO `forum_activity` VALUES (41, '测试活动', '今天吃什么', '操场', 4014, 10022, 100, 0, 0, 0, 0, '2026-01-31 04:22:22', '2026-02-20 04:22:22', '2026-02-25 04:22:22', '[\"/uploads/2026-01-31/d6c4d68594b8465896ada3386cce569f.jpg\"]', 1099, 1, 0, '2026-01-31 12:22:32', '2026-01-31 12:22:32', NULL);
INSERT INTO `forum_activity` VALUES (42, '测试图片', '测试图片月', 'c座', 4016, 10005, 12, 0, 0, 0, 0, '2026-01-31 16:00:00', '2026-02-01 16:00:00', '2026-02-02 16:00:00', '[\"/uploads/2026-01-31/817a6416533c46cc9ddd905b1318157e.jpg\"]', 1100, 1, 0, '2026-01-31 13:27:55', '2026-01-31 13:27:55', NULL);

-- ----------------------------
-- Table structure for forum_activity_comment
-- ----------------------------
DROP TABLE IF EXISTS `forum_activity_comment`;
CREATE TABLE `forum_activity_comment`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `activity_id` bigint UNSIGNED NOT NULL COMMENT '活动ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '评论人ID',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `parent_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '父级评论ID(NULL表示根评论)',
  `root_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '根评论ID(方便查询整个评论树)',
  `level` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论层级(0-根评论 1-一级回复 2-二级回复)',
  `like_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数',
  `reply_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复数',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:1-正常 2-用户删除 3-管理员删除',
  `deleted_by` bigint UNSIGNED NULL DEFAULT NULL COMMENT '删除操作人ID',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_activity_time`(`activity_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_root`(`root_id` ASC, `level` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_activity_comment
-- ----------------------------
INSERT INTO `forum_activity_comment` VALUES (1, 3, 1, '这个活动真的很有趣！', 0, NULL, 0, 0, 1, 1, NULL, NULL, '2025-12-16 19:30:27', '2026-01-15 19:47:43');
INSERT INTO `forum_activity_comment` VALUES (2, 3, 1, '这个活动真的很有趣！', 1, 1, 1, 0, 0, 2, 1, '2025-12-16 19:48:35', '2025-12-16 19:36:58', '2025-12-16 19:36:58');
INSERT INTO `forum_activity_comment` VALUES (3, 3, 1, '评论内容', 0, 3, 0, 0, 0, 2, 1, '2026-01-09 19:43:44', '2026-01-09 19:43:10', '2026-01-09 19:43:10');
INSERT INTO `forum_activity_comment` VALUES (4, 22, 4006, '123', 0, 4, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:25:58', '2026-01-15 19:25:58');
INSERT INTO `forum_activity_comment` VALUES (5, 22, 4006, '123', 0, 5, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:31:08', '2026-01-15 19:31:08');
INSERT INTO `forum_activity_comment` VALUES (6, 22, 4006, '测试', 0, 6, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:31:33', '2026-01-15 19:31:33');
INSERT INTO `forum_activity_comment` VALUES (7, 22, 1, '评论内容', 0, 7, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:34:35', '2026-01-15 19:34:35');
INSERT INTO `forum_activity_comment` VALUES (8, 22, 4006, '评论内容', 0, 8, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:36:12', '2026-01-15 19:36:12');
INSERT INTO `forum_activity_comment` VALUES (9, 22, 4006, '评论内容', 0, 9, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:36:14', '2026-01-15 19:36:14');
INSERT INTO `forum_activity_comment` VALUES (10, 22, 4006, '评论内容', 0, 10, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:36:23', '2026-01-15 19:36:23');
INSERT INTO `forum_activity_comment` VALUES (11, 22, 4006, '评论内容', 0, 11, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:36:48', '2026-01-15 19:36:48');
INSERT INTO `forum_activity_comment` VALUES (12, 22, 4006, '测试活动评论', 0, 12, 0, 1, 0, 1, NULL, NULL, '2026-01-15 19:52:59', '2026-01-15 20:31:49');
INSERT INTO `forum_activity_comment` VALUES (13, 22, 4006, '评论内容', 0, 13, 0, 0, 1, 1, NULL, NULL, '2026-01-15 20:30:05', '2026-01-15 20:30:05');
INSERT INTO `forum_activity_comment` VALUES (14, 22, 4006, '回复wx_oOYcH7Z0_4ad179：测试二级评论', 13, 13, 1, 1, 5, 1, NULL, NULL, '2026-01-15 20:30:36', '2026-01-15 20:31:48');
INSERT INTO `forum_activity_comment` VALUES (15, 22, 4006, '回复wx_oOYcH7Z0_4ad179：三级', 14, 13, 2, 0, 0, 1, NULL, NULL, '2026-01-15 20:30:41', '2026-01-15 20:30:41');
INSERT INTO `forum_activity_comment` VALUES (16, 22, 4006, '回复wx_oOYcH7Z0_4ad179：三级评论', 14, 13, 2, 0, 2, 1, NULL, NULL, '2026-01-15 20:31:29', '2026-01-15 20:31:29');
INSERT INTO `forum_activity_comment` VALUES (17, 22, 12, '这个活动真的很有趣！', 14, 13, 2, 0, 0, 1, NULL, NULL, '2026-01-15 20:47:19', '2026-01-15 20:53:15');
INSERT INTO `forum_activity_comment` VALUES (18, 22, 12, '这个活动真的很有趣！', 16, 13, 2, 0, 0, 1, NULL, NULL, '2026-01-15 20:48:41', '2026-01-15 20:48:41');
INSERT INTO `forum_activity_comment` VALUES (19, 22, 4006, '回复wx_oOYcH7Z0_4ad179：测试三级评论', 14, 13, 2, 1, 0, 1, NULL, NULL, '2026-01-16 09:14:08', '2026-01-16 19:45:01');
INSERT INTO `forum_activity_comment` VALUES (20, 22, 4006, '回复测试昵称：三级', 14, 13, 2, 0, 0, 1, NULL, NULL, '2026-01-16 16:27:21', '2026-01-16 16:27:21');
INSERT INTO `forum_activity_comment` VALUES (21, 22, 4006, '回复测试昵称2：三级评论', 14, 13, 2, 0, 0, 1, NULL, NULL, '2026-01-16 19:14:17', '2026-01-16 19:14:17');
INSERT INTO `forum_activity_comment` VALUES (22, 31, 4017, '111', 0, 22, 0, 0, 1, 2, 4017, '2026-01-29 10:34:56', '2026-01-29 02:19:31', '2026-01-29 02:19:31');
INSERT INTO `forum_activity_comment` VALUES (23, 31, 4017, '111', 22, 22, 1, 0, 0, 2, 4017, '2026-01-29 10:34:53', '2026-01-29 02:19:39', '2026-01-29 02:19:39');
INSERT INTO `forum_activity_comment` VALUES (24, 31, 4017, '我是xfl', 0, 24, 0, 0, 1, 2, 4017, '2026-01-29 10:35:54', '2026-01-29 02:35:14', '2026-01-29 02:35:15');
INSERT INTO `forum_activity_comment` VALUES (25, 31, 4017, 'haha', 24, 24, 1, 0, 0, 2, 4017, '2026-01-29 10:35:51', '2026-01-29 02:35:21', '2026-01-29 02:35:21');
INSERT INTO `forum_activity_comment` VALUES (26, 31, 4017, 'xfl到此一游', 0, 26, 0, 1, 1, 1, NULL, NULL, '2026-01-29 02:51:23', '2026-01-29 12:08:11');
INSERT INTO `forum_activity_comment` VALUES (27, 31, 4017, '别游了', 26, 26, 1, 1, 0, 1, NULL, NULL, '2026-01-29 02:51:36', '2026-01-29 11:21:36');
INSERT INTO `forum_activity_comment` VALUES (28, 31, 4017, '111', 0, 28, 0, 0, 0, 2, 4017, '2026-01-29 11:29:45', '2026-01-29 03:29:33', '2026-01-29 03:29:33');
INSERT INTO `forum_activity_comment` VALUES (29, 31, 4013, 'nihaoa', 0, 29, 0, 0, 2, 1, NULL, NULL, '2026-01-29 03:39:40', '2026-01-29 03:39:40');
INSERT INTO `forum_activity_comment` VALUES (30, 31, 4013, 'adf', 0, 30, 0, 0, 0, 1, NULL, NULL, '2026-01-29 03:42:12', '2026-01-29 03:42:12');
INSERT INTO `forum_activity_comment` VALUES (31, 31, 4013, 'adsfadf', 0, 31, 0, 0, 0, 1, NULL, NULL, '2026-01-29 03:42:28', '2026-01-29 15:07:07');
INSERT INTO `forum_activity_comment` VALUES (32, 31, 4013, 'asd', 0, 32, 0, 1, 0, 1, NULL, NULL, '2026-01-29 03:43:45', '2026-01-29 19:40:17');
INSERT INTO `forum_activity_comment` VALUES (33, 31, 4013, 'adg', 0, 33, 0, 0, 0, 1, NULL, NULL, '2026-01-29 03:44:53', '2026-01-29 03:44:53');
INSERT INTO `forum_activity_comment` VALUES (34, 31, 4016, '你好啊种树', 0, 34, 0, 2, 0, 1, NULL, NULL, '2026-01-29 03:56:11', '2026-01-29 14:45:19');
INSERT INTO `forum_activity_comment` VALUES (35, 30, 4013, 'nihaoa', 0, 35, 0, 0, 0, 1, NULL, NULL, '2026-01-29 04:09:49', '2026-01-29 04:09:49');
INSERT INTO `forum_activity_comment` VALUES (36, 30, 4013, 'adaf', 0, 36, 0, 0, 0, 1, NULL, NULL, '2026-01-29 04:14:38', '2026-01-29 04:14:38');
INSERT INTO `forum_activity_comment` VALUES (37, 31, 4012, '评论', 0, 37, 0, 2, 0, 1, NULL, NULL, '2026-01-29 04:16:05', '2026-01-29 19:40:09');
INSERT INTO `forum_activity_comment` VALUES (38, 30, 4015, 'dmz到此一游', 0, 38, 0, 0, 0, 1, NULL, NULL, '2026-01-29 04:19:02', '2026-01-29 04:19:02');
INSERT INTO `forum_activity_comment` VALUES (39, 30, 4015, 'dmz2', 0, 39, 0, 0, 0, 1, NULL, NULL, '2026-01-29 04:21:32', '2026-01-29 04:21:32');
INSERT INTO `forum_activity_comment` VALUES (40, 29, 4013, 'nihaoa', 0, 40, 0, 0, 3, 1, NULL, NULL, '2026-01-29 04:23:23', '2026-01-29 14:39:08');
INSERT INTO `forum_activity_comment` VALUES (41, 29, 4013, 'adf', 29, 29, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:27:46', '2026-01-29 04:27:46');
INSERT INTO `forum_activity_comment` VALUES (42, 29, 4013, 'hello', 29, 29, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:31:14', '2026-01-29 04:31:14');
INSERT INTO `forum_activity_comment` VALUES (43, 29, 4013, 'hello', 0, 43, 0, 1, 2, 2, 4013, '2026-01-29 14:25:05', '2026-01-29 06:01:44', '2026-01-29 14:22:30');
INSERT INTO `forum_activity_comment` VALUES (44, 29, 4013, 'nihaoa', 43, 43, 1, 0, 0, 2, 4013, '2026-01-29 14:24:20', '2026-01-29 06:04:18', '2026-01-29 14:22:33');
INSERT INTO `forum_activity_comment` VALUES (45, 29, 4013, 'pinglun', 40, 40, 1, 0, 0, 2, 4013, '2026-01-29 14:25:52', '2026-01-29 06:04:38', '2026-01-29 06:04:38');
INSERT INTO `forum_activity_comment` VALUES (46, 29, 4013, 'nihaoa', 43, 43, 1, 0, 0, 1, NULL, NULL, '2026-01-29 06:25:00', '2026-01-29 06:25:00');
INSERT INTO `forum_activity_comment` VALUES (47, 29, 4013, 'nihao', 0, 47, 0, 0, 0, 1, NULL, NULL, '2026-01-29 06:25:58', '2026-01-29 06:25:58');
INSERT INTO `forum_activity_comment` VALUES (48, 29, 4013, 'hello', 40, 40, 1, 1, 0, 1, NULL, NULL, '2026-01-29 06:26:03', '2026-01-29 14:28:14');
INSERT INTO `forum_activity_comment` VALUES (49, 29, 4013, 'hi', 40, 40, 1, 1, 0, 1, NULL, NULL, '2026-01-29 06:26:06', '2026-01-29 14:26:08');
INSERT INTO `forum_activity_comment` VALUES (50, 29, 4013, 'haoaho', 0, 50, 0, 0, 0, 1, NULL, NULL, '2026-01-29 06:26:19', '2026-01-29 06:26:19');
INSERT INTO `forum_activity_comment` VALUES (51, 31, 4014, '种树好啊', 0, 51, 0, 1, 0, 1, NULL, NULL, '2026-01-29 07:02:21', '2026-01-29 15:02:33');
INSERT INTO `forum_activity_comment` VALUES (52, 31, 4015, 'cgdhbjfhgfyj', 0, 52, 0, 0, 0, 1, NULL, NULL, '2026-01-29 10:56:31', '2026-01-29 10:56:31');
INSERT INTO `forum_activity_comment` VALUES (53, 37, 4013, '一起吗', 0, 53, 0, 0, 1, 1, NULL, NULL, '2026-01-31 03:33:38', '2026-01-31 03:33:38');
INSERT INTO `forum_activity_comment` VALUES (54, 37, 4013, '好的，一起一起', 53, 53, 1, 1, 0, 1, NULL, NULL, '2026-01-31 03:36:13', '2026-01-31 11:36:21');
INSERT INTO `forum_activity_comment` VALUES (55, 36, 4006, '123', 0, 55, 0, 1, 0, 1, NULL, NULL, '2026-01-31 06:57:43', '2026-01-31 14:57:46');

-- ----------------------------
-- Table structure for forum_activity_registration
-- ----------------------------
DROP TABLE IF EXISTS `forum_activity_registration`;
CREATE TABLE `forum_activity_registration`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '报名ID',
  `activity_id` bigint UNSIGNED NOT NULL COMMENT '活动ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `status` tinyint NULL DEFAULT 1 COMMENT '报名状态:1-已报名 2-已取消 3-已签到',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `audit_result` int NULL DEFAULT NULL COMMENT '审核结果:1-通过 2-拒绝',
  `audit_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id` ASC, `user_id` ASC, `deleted_at` ASC) USING BTREE,
  INDEX `idx_activity`(`activity_id` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动报名表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_activity_registration
-- ----------------------------
INSERT INTO `forum_activity_registration` VALUES (1, 2, 1, 1, '2024-01-15 10:30:00', '2025-12-12 20:13:02', NULL, NULL, NULL);
INSERT INTO `forum_activity_registration` VALUES (5, 11, 12, 1, '2025-12-16 17:35:21', '2025-12-16 17:35:21', NULL, NULL, NULL);
INSERT INTO `forum_activity_registration` VALUES (7, 21, 1, 1, '2026-01-14 09:33:31', '2026-01-14 09:33:31', NULL, NULL, NULL);
INSERT INTO `forum_activity_registration` VALUES (29, 21, 4009, 1, '2026-01-14 14:07:53', '2026-01-14 14:07:53', NULL, NULL, NULL);
INSERT INTO `forum_activity_registration` VALUES (37, 21, 4006, 1, '2026-01-16 09:42:23', '2026-01-16 09:42:23', NULL, NULL, NULL);
INSERT INTO `forum_activity_registration` VALUES (38, 22, 4006, 1, '2026-01-23 07:04:45', '2026-01-23 07:04:45', NULL, NULL, NULL);
INSERT INTO `forum_activity_registration` VALUES (40, 24, 1, 1, '2026-01-23 08:41:27', '2026-01-23 11:46:00', NULL, 1, '审核通过');
INSERT INTO `forum_activity_registration` VALUES (41, 25, 1, 1, '2026-01-23 12:12:56', '2026-01-23 12:12:56', NULL, 1, '审核通过');
INSERT INTO `forum_activity_registration` VALUES (43, 31, 4006, 1, '2026-01-26 06:22:18', '2026-01-26 06:22:18', NULL, 0, '待审核');
INSERT INTO `forum_activity_registration` VALUES (63, 31, 4013, 1, '2026-01-29 06:40:23', '2026-01-29 06:40:23', NULL, 1, '待审核');
INSERT INTO `forum_activity_registration` VALUES (64, 31, 4014, 1, '2026-01-29 07:38:01', '2026-01-29 07:38:01', NULL, 1, '待审核');
INSERT INTO `forum_activity_registration` VALUES (65, 31, 4012, 1, '2026-01-29 10:40:13', '2026-01-29 10:40:13', NULL, 1, '待审核');
INSERT INTO `forum_activity_registration` VALUES (66, 36, 4010, 1, '2026-01-31 02:28:52', '2026-01-31 02:28:52', NULL, 0, '待审核');
INSERT INTO `forum_activity_registration` VALUES (67, 36, 4011, 1, '2026-01-31 02:30:31', '2026-01-31 02:30:31', NULL, 0, '待审核');
INSERT INTO `forum_activity_registration` VALUES (68, 31, 4017, 1, '2026-01-31 03:19:52', '2026-01-31 03:19:52', NULL, 1, '待审核');
INSERT INTO `forum_activity_registration` VALUES (69, 36, 4013, 1, '2026-01-31 03:24:28', '2026-01-31 03:24:28', NULL, 0, '待审核');

-- ----------------------------
-- Table structure for forum_announcement
-- ----------------------------
DROP TABLE IF EXISTS `forum_announcement`;
CREATE TABLE `forum_announcement`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告标题',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片URL',
  `is_display` tinyint NOT NULL DEFAULT 1 COMMENT '是否显示:0-不显示 1-显示',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '公告关联学校ID，null为系统公告',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加日期',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_display`(`is_display` ASC) USING BTREE,
  INDEX `idx_created`(`created_at` ASC) USING BTREE,
  INDEX `idx_school`(`school_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_announcement
-- ----------------------------
INSERT INTO `forum_announcement` VALUES (1, '测试', '', 0, NULL, '2025-12-08 09:37:44', '2025-12-08 10:18:19', '2025-12-08 10:18:19');
INSERT INTO `forum_announcement` VALUES (2, '测试', '', 0, NULL, '2025-12-08 09:41:19', '2025-12-08 10:18:17', '2025-12-08 10:18:17');
INSERT INTO `forum_announcement` VALUES (3, '11', '', 1, NULL, '2025-12-08 10:10:32', '2025-12-08 10:18:13', '2025-12-08 10:18:13');
INSERT INTO `forum_announcement` VALUES (4, '测试11', 'uploads/2025-12-06/0c576ac0095d42f2805b92eecbfc7adb.png', 1, 10005, '2025-12-08 10:18:01', '2025-12-23 17:56:43', '2025-12-23 17:56:43');
INSERT INTO `forum_announcement` VALUES (5, 'testtitle', NULL, 2, NULL, '2025-12-16 12:07:43', '2025-12-16 14:42:03', '2025-12-16 14:42:03');
INSERT INTO `forum_announcement` VALUES (6, 'testtitle', NULL, 0, NULL, '2025-12-16 12:16:52', '2025-12-16 14:00:07', '2025-12-16 14:00:07');
INSERT INTO `forum_announcement` VALUES (7, '测试', NULL, 1, NULL, '2025-12-16 14:27:44', '2025-12-16 14:41:59', '2025-12-16 14:41:59');
INSERT INTO `forum_announcement` VALUES (8, '测试', NULL, 1, NULL, '2025-12-16 14:32:09', '2025-12-16 14:41:55', '2025-12-16 14:41:55');
INSERT INTO `forum_announcement` VALUES (9, '123', NULL, 1, NULL, '2025-12-16 14:32:17', '2025-12-16 14:41:52', '2025-12-16 14:41:52');
INSERT INTO `forum_announcement` VALUES (10, '213', NULL, 1, NULL, '2025-12-16 14:41:42', '2025-12-16 14:42:01', '2025-12-16 14:42:01');
INSERT INTO `forum_announcement` VALUES (11, '测试2', '/uploads/2025-12-16/05bbde1926ee48228b39804eb9da0881.jpg', 0, 10010, '2025-12-16 14:42:13', '2025-12-23 17:56:43', '2025-12-23 17:56:43');
INSERT INTO `forum_announcement` VALUES (12, '测试3', '/uploads/2025-12-16/d050a7a7369f4060971cf6c60ed03e8b.jpg', 0, 10008, '2025-12-16 14:44:47', '2025-12-22 14:49:03', '2025-12-22 14:49:03');
INSERT INTO `forum_announcement` VALUES (13, 'ss', NULL, 0, NULL, '2025-12-22 14:14:14', '2025-12-22 14:48:35', '2025-12-22 14:48:35');
INSERT INTO `forum_announcement` VALUES (14, 'ss', NULL, 0, NULL, '2025-12-22 14:52:04', '2025-12-22 15:00:46', '2025-12-22 15:00:46');
INSERT INTO `forum_announcement` VALUES (15, 'ss', NULL, 0, NULL, '2025-12-22 14:55:17', '2025-12-22 15:00:50', '2025-12-22 15:00:50');
INSERT INTO `forum_announcement` VALUES (16, '测试', '/uploads/2026-01-26/2955357b76a744ed8fd3dc28d9c59cea.png', 0, NULL, '2025-12-22 14:57:30', '2025-12-22 14:57:30', NULL);
INSERT INTO `forum_announcement` VALUES (17, '测试', 'http://172.16.8.74:8093/uploads/2025-12-16/d050a7a7369f4060971cf6c60ed03e8b.jpg', 1, NULL, '2025-12-22 15:01:14', '2025-12-22 15:01:14', NULL);
INSERT INTO `forum_announcement` VALUES (18, '测试', 'http://172.16.8.74:8093/uploads/2025-12-16/d050a7a7369f4060971cf6c60ed03e8b.jpg', 0, NULL, '2025-12-22 15:01:15', '2025-12-22 15:02:51', '2025-12-22 15:02:51');
INSERT INTO `forum_announcement` VALUES (19, '测试', 'http://172.16.8.74:8093/uploads/2025-12-16/d050a7a7369f4060971cf6c60ed03e8b.jpg', 0, NULL, '2025-12-22 15:01:16', '2025-12-22 15:01:44', '2025-12-22 15:01:44');
INSERT INTO `forum_announcement` VALUES (20, '测试', 'http://172.16.8.74:8093/uploads/2025-12-16/d050a7a7369f4060971cf6c60ed03e8b.jpg', 0, NULL, '2025-12-23 09:28:52', '2025-12-23 09:43:18', '2025-12-23 09:43:18');
INSERT INTO `forum_announcement` VALUES (21, '测试', NULL, 0, NULL, '2025-12-23 09:29:00', '2025-12-23 09:43:08', '2025-12-23 09:43:08');
INSERT INTO `forum_announcement` VALUES (22, '测试', NULL, 1, NULL, '2025-12-23 09:29:43', '2025-12-23 09:43:10', '2025-12-23 09:43:10');
INSERT INTO `forum_announcement` VALUES (23, '测试', 'http://172.16.8.74:8093/uploads/2025-12-16/d050a7a7369f4060971cf6c60ed03e8b.jpg', 1, NULL, '2025-12-23 09:34:39', '2025-12-23 09:43:11', '2025-12-23 09:43:11');

-- ----------------------------
-- Table structure for forum_like_record
-- ----------------------------
DROP TABLE IF EXISTS `forum_like_record`;
CREATE TABLE `forum_like_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '点赞用户ID',
  `like_type` tinyint NOT NULL COMMENT '点赞类型：1=活动，2=帖子，3=活动评论，4=帖子评论',
  `like_id` bigint NOT NULL COMMENT '对应类型的主键ID（如活动ID、帖子ID）',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_type_id`(`user_id` ASC, `like_type` ASC, `like_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 927 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通用点赞记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_like_record
-- ----------------------------
INSERT INTO `forum_like_record` VALUES (56, 1, 1, 25, '2025-12-24 16:50:20');
INSERT INTO `forum_like_record` VALUES (59, 1, 1, 24, '2025-12-24 16:59:12');
INSERT INTO `forum_like_record` VALUES (60, 1, 1, 23, '2025-12-24 16:59:20');
INSERT INTO `forum_like_record` VALUES (72, 4006, 1, 24, '2025-12-24 17:11:32');
INSERT INTO `forum_like_record` VALUES (76, 1, 1, 20, '2025-12-24 17:14:53');
INSERT INTO `forum_like_record` VALUES (77, 4006, 1, 25, '2025-12-24 17:16:54');
INSERT INTO `forum_like_record` VALUES (78, 1, 1, 26, '2025-12-24 17:36:17');
INSERT INTO `forum_like_record` VALUES (79, 1, 1, 22, '2025-12-24 17:36:30');
INSERT INTO `forum_like_record` VALUES (83, 1, 1, 39, '2025-12-25 09:36:16');
INSERT INTO `forum_like_record` VALUES (84, 1, 1, 36, '2025-12-25 09:36:29');
INSERT INTO `forum_like_record` VALUES (86, 1, 1, 31, '2025-12-25 09:37:50');
INSERT INTO `forum_like_record` VALUES (89, 1, 1, 44, '2025-12-25 10:30:45');
INSERT INTO `forum_like_record` VALUES (90, 1, 1, 43, '2025-12-25 10:30:47');
INSERT INTO `forum_like_record` VALUES (91, 1, 1, 42, '2025-12-25 10:30:49');
INSERT INTO `forum_like_record` VALUES (92, 1, 1, 37, '2025-12-25 10:30:53');
INSERT INTO `forum_like_record` VALUES (93, 1, 1, 40, '2025-12-25 10:31:00');
INSERT INTO `forum_like_record` VALUES (96, 1, 1, 45, '2025-12-25 11:58:29');
INSERT INTO `forum_like_record` VALUES (115, 1, 1, 2, '2025-12-26 10:39:15');
INSERT INTO `forum_like_record` VALUES (117, 1, 1, 17, '2025-12-26 11:25:05');
INSERT INTO `forum_like_record` VALUES (146, 4006, 2, 42, '2025-12-29 09:57:00');
INSERT INTO `forum_like_record` VALUES (193, 4006, 2, 44, '2025-12-29 11:16:55');
INSERT INTO `forum_like_record` VALUES (195, 4006, 2, 45, '2025-12-29 11:18:36');
INSERT INTO `forum_like_record` VALUES (347, 1, 2, 35, '2025-12-31 12:26:49');
INSERT INTO `forum_like_record` VALUES (402, 1, 2, 36, '2025-12-31 16:24:02');
INSERT INTO `forum_like_record` VALUES (419, 1, 2, 40, '2026-01-04 10:07:29');
INSERT INTO `forum_like_record` VALUES (420, 1, 2, 39, '2026-01-04 10:07:31');
INSERT INTO `forum_like_record` VALUES (429, 1, 2, 38, '2026-01-04 10:35:47');
INSERT INTO `forum_like_record` VALUES (430, 1, 2, 41, '2026-01-04 10:35:51');
INSERT INTO `forum_like_record` VALUES (433, 1, 2, 42, '2026-01-04 11:16:13');
INSERT INTO `forum_like_record` VALUES (441, 1, 2, 37, '2026-01-04 11:25:50');
INSERT INTO `forum_like_record` VALUES (453, 1, 2, 18, '2026-01-04 14:06:33');
INSERT INTO `forum_like_record` VALUES (456, 4009, 2, 36, '2026-01-06 14:01:40');
INSERT INTO `forum_like_record` VALUES (465, 4006, 2, 36, '2026-01-09 14:26:02');
INSERT INTO `forum_like_record` VALUES (466, 1, 4, 1, '2026-01-15 16:53:32');
INSERT INTO `forum_like_record` VALUES (467, 12, 4, 1, '2026-01-15 16:55:54');
INSERT INTO `forum_like_record` VALUES (468, 12, 4, 49, '2026-01-15 16:57:05');
INSERT INTO `forum_like_record` VALUES (469, 1, 4, 32, '2026-01-15 17:02:16');
INSERT INTO `forum_like_record` VALUES (470, 1, 4, 37, '2026-01-15 17:09:36');
INSERT INTO `forum_like_record` VALUES (471, 1, 4, 39, '2026-01-15 17:10:39');
INSERT INTO `forum_like_record` VALUES (473, 12, 4, 47, '2026-01-15 17:11:42');
INSERT INTO `forum_like_record` VALUES (477, 4006, 4, 43, '2026-01-15 17:16:08');
INSERT INTO `forum_like_record` VALUES (484, 4006, 4, 36, '2026-01-15 17:17:26');
INSERT INTO `forum_like_record` VALUES (488, 4006, 4, 35, '2026-01-15 17:23:32');
INSERT INTO `forum_like_record` VALUES (489, 4006, 4, 45, '2026-01-15 17:23:34');
INSERT INTO `forum_like_record` VALUES (490, 4006, 4, 34, '2026-01-15 17:23:36');
INSERT INTO `forum_like_record` VALUES (493, 4006, 4, 32, '2026-01-15 17:31:05');
INSERT INTO `forum_like_record` VALUES (495, 4006, 2, 40, '2026-01-15 17:31:18');
INSERT INTO `forum_like_record` VALUES (500, 4006, 3, 14, '2026-01-15 20:31:48');
INSERT INTO `forum_like_record` VALUES (501, 4006, 3, 12, '2026-01-15 20:31:49');
INSERT INTO `forum_like_record` VALUES (503, 4006, 2, 51, '2026-01-16 09:13:33');
INSERT INTO `forum_like_record` VALUES (504, 4006, 2, 49, '2026-01-16 09:13:35');
INSERT INTO `forum_like_record` VALUES (507, 4006, 3, 19, '2026-01-16 19:45:00');
INSERT INTO `forum_like_record` VALUES (509, 4006, 2, 41, '2026-01-21 06:26:12');
INSERT INTO `forum_like_record` VALUES (510, 4006, 2, 52, '2026-01-21 08:20:35');
INSERT INTO `forum_like_record` VALUES (511, 4006, 1, 2, '2026-01-21 11:40:20');
INSERT INTO `forum_like_record` VALUES (514, 4006, 2, 56, '2026-01-22 09:51:51');
INSERT INTO `forum_like_record` VALUES (515, 4006, 2, 55, '2026-01-26 03:32:56');
INSERT INTO `forum_like_record` VALUES (516, 4006, 2, 57, '2026-01-26 06:18:21');
INSERT INTO `forum_like_record` VALUES (524, 4012, 2, 49, '2026-01-28 02:30:46');
INSERT INTO `forum_like_record` VALUES (525, 4012, 2, 50, '2026-01-28 02:39:38');
INSERT INTO `forum_like_record` VALUES (526, 4012, 2, 51, '2026-01-28 02:52:35');
INSERT INTO `forum_like_record` VALUES (570, 4014, 2, 57, '2026-01-28 04:12:09');
INSERT INTO `forum_like_record` VALUES (636, 4012, 2, 48, '2026-01-28 06:13:00');
INSERT INTO `forum_like_record` VALUES (661, 4013, 4, 60, '2026-01-28 08:23:56');
INSERT INTO `forum_like_record` VALUES (664, 4017, 2, 56, '2026-01-28 08:44:05');
INSERT INTO `forum_like_record` VALUES (665, 4017, 2, 55, '2026-01-28 08:44:58');
INSERT INTO `forum_like_record` VALUES (666, 4017, 2, 54, '2026-01-28 08:50:25');
INSERT INTO `forum_like_record` VALUES (667, 4017, 2, 53, '2026-01-28 08:52:55');
INSERT INTO `forum_like_record` VALUES (668, 4017, 2, 52, '2026-01-28 08:54:30');
INSERT INTO `forum_like_record` VALUES (673, 4013, 2, 41, '2026-01-28 09:24:19');
INSERT INTO `forum_like_record` VALUES (674, 4013, 2, 52, '2026-01-28 09:42:10');
INSERT INTO `forum_like_record` VALUES (675, 4013, 4, 74, '2026-01-28 09:47:09');
INSERT INTO `forum_like_record` VALUES (677, 4013, 4, 55, '2026-01-28 10:03:05');
INSERT INTO `forum_like_record` VALUES (678, 4017, 2, 57, '2026-01-28 11:13:47');
INSERT INTO `forum_like_record` VALUES (700, 4012, 2, 40, '2026-01-28 11:50:37');
INSERT INTO `forum_like_record` VALUES (715, 4015, 2, 55, '2026-01-28 12:01:13');
INSERT INTO `forum_like_record` VALUES (734, 4015, 4, 66, '2026-01-29 01:44:49');
INSERT INTO `forum_like_record` VALUES (735, 4015, 4, 60, '2026-01-29 01:44:54');
INSERT INTO `forum_like_record` VALUES (736, 4015, 4, 58, '2026-01-29 01:44:57');
INSERT INTO `forum_like_record` VALUES (755, 4013, 4, 54, '2026-01-29 02:00:42');
INSERT INTO `forum_like_record` VALUES (760, 4013, 4, 89, '2026-01-29 02:29:08');
INSERT INTO `forum_like_record` VALUES (761, 4013, 4, 95, '2026-01-29 02:29:34');
INSERT INTO `forum_like_record` VALUES (762, 4013, 4, 98, '2026-01-29 02:58:48');
INSERT INTO `forum_like_record` VALUES (764, 4013, 4, 56, '2026-01-29 03:19:17');
INSERT INTO `forum_like_record` VALUES (770, 4017, 3, 27, '2026-01-29 03:21:36');
INSERT INTO `forum_like_record` VALUES (777, 4017, 1, 31, '2026-01-29 03:38:08');
INSERT INTO `forum_like_record` VALUES (781, 4014, 4, 58, '2026-01-29 03:52:51');
INSERT INTO `forum_like_record` VALUES (783, 4014, 4, 61, '2026-01-29 03:53:08');
INSERT INTO `forum_like_record` VALUES (786, 4013, 4, 112, '2026-01-29 03:59:46');
INSERT INTO `forum_like_record` VALUES (795, 4017, 2, 58, '2026-01-29 04:06:48');
INSERT INTO `forum_like_record` VALUES (798, 4017, 2, 51, '2026-01-29 04:07:03');
INSERT INTO `forum_like_record` VALUES (800, 4015, 3, 26, '2026-01-29 04:08:11');
INSERT INTO `forum_like_record` VALUES (803, 4014, 2, 58, '2026-01-29 04:08:31');
INSERT INTO `forum_like_record` VALUES (804, 4015, 1, 31, '2026-01-29 04:09:28');
INSERT INTO `forum_like_record` VALUES (805, 4015, 3, 34, '2026-01-29 04:13:57');
INSERT INTO `forum_like_record` VALUES (807, 4013, 1, 30, '2026-01-29 04:15:06');
INSERT INTO `forum_like_record` VALUES (809, 4014, 2, 41, '2026-01-29 04:18:29');
INSERT INTO `forum_like_record` VALUES (811, 4014, 2, 59, '2026-01-29 04:19:52');
INSERT INTO `forum_like_record` VALUES (813, 4015, 1, 30, '2026-01-29 04:20:49');
INSERT INTO `forum_like_record` VALUES (818, 4017, 4, 61, '2026-01-29 04:27:14');
INSERT INTO `forum_like_record` VALUES (822, 4016, 1, 31, '2026-01-29 06:04:57');
INSERT INTO `forum_like_record` VALUES (831, 4013, 3, 43, '2026-01-29 06:22:31');
INSERT INTO `forum_like_record` VALUES (832, 4013, 3, 49, '2026-01-29 06:26:09');
INSERT INTO `forum_like_record` VALUES (834, 4013, 3, 48, '2026-01-29 06:28:14');
INSERT INTO `forum_like_record` VALUES (835, 4014, 3, 37, '2026-01-29 06:34:29');
INSERT INTO `forum_like_record` VALUES (837, 4014, 3, 34, '2026-01-29 06:34:50');
INSERT INTO `forum_like_record` VALUES (839, 4016, 3, 37, '2026-01-29 06:45:16');
INSERT INTO `forum_like_record` VALUES (843, 4013, 2, 59, '2026-01-29 06:48:04');
INSERT INTO `forum_like_record` VALUES (846, 4014, 3, 51, '2026-01-29 07:02:34');
INSERT INTO `forum_like_record` VALUES (849, 4016, 4, 58, '2026-01-29 07:02:47');
INSERT INTO `forum_like_record` VALUES (853, 4016, 2, 59, '2026-01-29 07:03:22');
INSERT INTO `forum_like_record` VALUES (854, 4016, 4, 72, '2026-01-29 07:03:25');
INSERT INTO `forum_like_record` VALUES (856, 4014, 1, 30, '2026-01-29 07:20:26');
INSERT INTO `forum_like_record` VALUES (857, 4014, 1, 31, '2026-01-29 07:22:11');
INSERT INTO `forum_like_record` VALUES (859, 4013, 4, 123, '2026-01-29 08:22:35');
INSERT INTO `forum_like_record` VALUES (860, 4017, 2, 61, '2026-01-29 08:55:30');
INSERT INTO `forum_like_record` VALUES (861, 4015, 2, 41, '2026-01-29 10:54:02');
INSERT INTO `forum_like_record` VALUES (865, 4015, 4, 127, '2026-01-29 10:54:32');
INSERT INTO `forum_like_record` VALUES (866, 4015, 2, 63, '2026-01-29 10:54:50');
INSERT INTO `forum_like_record` VALUES (868, 4015, 3, 32, '2026-01-29 11:40:18');
INSERT INTO `forum_like_record` VALUES (871, 4012, 2, 61, '2026-01-30 01:03:40');
INSERT INTO `forum_like_record` VALUES (873, 4012, 2, 60, '2026-01-30 01:05:23');
INSERT INTO `forum_like_record` VALUES (875, 4012, 2, 62, '2026-01-30 01:24:57');
INSERT INTO `forum_like_record` VALUES (880, 4012, 2, 58, '2026-01-30 01:55:02');
INSERT INTO `forum_like_record` VALUES (884, 4012, 4, 54, '2026-01-30 02:19:58');
INSERT INTO `forum_like_record` VALUES (885, 4012, 4, 77, '2026-01-30 02:20:08');
INSERT INTO `forum_like_record` VALUES (887, 4012, 4, 33, '2026-01-30 02:21:29');
INSERT INTO `forum_like_record` VALUES (894, 4012, 4, 127, '2026-01-30 03:10:47');
INSERT INTO `forum_like_record` VALUES (898, 4012, 2, 56, '2026-01-30 03:11:55');
INSERT INTO `forum_like_record` VALUES (899, 4012, 2, 65, '2026-01-30 03:12:00');
INSERT INTO `forum_like_record` VALUES (901, 4017, 2, 64, '2026-01-30 04:17:26');
INSERT INTO `forum_like_record` VALUES (907, 4013, 2, 60, '2026-01-31 02:15:15');
INSERT INTO `forum_like_record` VALUES (909, 4013, 2, 67, '2026-01-31 02:15:53');
INSERT INTO `forum_like_record` VALUES (910, 4011, 2, 69, '2026-01-31 03:02:05');
INSERT INTO `forum_like_record` VALUES (912, 4013, 1, 36, '2026-01-31 03:32:54');
INSERT INTO `forum_like_record` VALUES (913, 4013, 3, 54, '2026-01-31 03:36:20');
INSERT INTO `forum_like_record` VALUES (914, 4012, 2, 74, '2026-01-31 06:44:33');
INSERT INTO `forum_like_record` VALUES (919, 4006, 3, 55, '2026-01-31 06:57:45');
INSERT INTO `forum_like_record` VALUES (920, 4006, 2, 78, '2026-01-31 06:57:52');
INSERT INTO `forum_like_record` VALUES (921, 4006, 2, 59, '2026-01-31 06:58:52');
INSERT INTO `forum_like_record` VALUES (926, 4006, 2, 58, '2026-01-31 07:52:02');

-- ----------------------------
-- Table structure for forum_post
-- ----------------------------
DROP TABLE IF EXISTS `forum_post`;
CREATE TABLE `forum_post`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '发布人ID',
  `post_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '帖子标题',
  `post_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '帖子内容',
  `image_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '图片URL(JSON数组)',
  `tags` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签(逗号分隔)',
  `view_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览量',
  `like_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数',
  `share_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '转发数',
  `favorite_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏数',
  `comment_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论数',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审批表关联ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE,
  INDEX `idx_time`(`created_at` DESC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC, `id` ASC) USING BTREE,
  INDEX `idx_listhot`(`like_count` DESC, `share_count` DESC, `favorite_count` DESC) USING BTREE,
  CONSTRAINT `chk_counts` CHECK ((`view_count` >= 0) and (`like_count` >= 0))
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_post
-- ----------------------------
INSERT INTO `forum_post` VALUES (2, 1, '帖子1', '内容', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', NULL, 0, 11, 0, 0, 0, NULL, '2025-12-10 15:27:37', '2025-12-26 10:39:18', '2025-12-13 17:16:45');
INSERT INTO `forum_post` VALUES (3, 2, '帖子2', '内容', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', NULL, 0, 1, 0, 0, 0, NULL, '2025-12-10 15:28:18', '2025-12-11 09:09:52', NULL);
INSERT INTO `forum_post` VALUES (4, 3, '帖子3', '内容', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', NULL, 2, 5, 0, 0, 0, NULL, '2025-12-10 15:28:34', '2025-12-23 19:25:03', NULL);
INSERT INTO `forum_post` VALUES (5, 4, '帖子4', '4', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:15:18', '2025-12-10 16:18:26', NULL);
INSERT INTO `forum_post` VALUES (6, 5, '帖子5', '5', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:15:24', '2025-12-10 16:18:28', NULL);
INSERT INTO `forum_post` VALUES (7, 6, '帖子6', '6', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:15:33', '2025-12-10 16:18:30', NULL);
INSERT INTO `forum_post` VALUES (8, 7, '帖子7', '7', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:15:38', '2025-12-10 16:18:32', NULL);
INSERT INTO `forum_post` VALUES (9, 8, '帖子8', '', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:15:46', '2025-12-10 16:18:35', NULL);
INSERT INTO `forum_post` VALUES (10, 9, '帖子9', '', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:15:51', '2025-12-10 16:19:07', NULL);
INSERT INTO `forum_post` VALUES (11, 10, '帖子10', '', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:15:58', '2025-12-10 16:19:01', NULL);
INSERT INTO `forum_post` VALUES (12, 11, '11', '11', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-10 16:19:42', '2025-12-10 16:19:42', NULL);
INSERT INTO `forum_post` VALUES (13, 1, '我的新帖子', '帖子内容', '[\"/uploads/2025-12-15/61a02ed761394c7e8ff90c057adef3c3.png\"]', '123', 0, 0, 0, 0, 0, NULL, '2025-12-15 09:24:15', '2025-12-15 09:24:15', '2025-12-15 09:26:05');
INSERT INTO `forum_post` VALUES (14, 12, '我的新帖子2', '帖子内容', '[\"/uploads/2025-12-15/e30bfb0e67ad47fab5e84cb751fc754e.png\"]', '123', 0, 0, 0, 0, 0, 1038, '2025-12-15 09:40:29', '2025-12-15 11:17:07', '2025-12-15 11:17:07');
INSERT INTO `forum_post` VALUES (15, 12, '我的新帖子3', '帖子内容', '[\"/uploads/2025-12-15/460807c7abf54d6e84195c2d06d603ea.png\"]', '123', 0, 0, 0, 0, 0, NULL, '2025-12-15 09:51:17', '2025-12-15 09:51:17', '2025-12-15 09:54:05');
INSERT INTO `forum_post` VALUES (16, 12, '帖子', '内容', NULL, '123', 0, 0, 0, 0, 0, 1039, '2025-12-15 11:10:43', '2025-12-15 11:10:43', NULL);
INSERT INTO `forum_post` VALUES (17, 1, '我的新帖子3', '帖子内容', '[\"/uploads/2025-12-22/b0e41a4567614dd981ee41c60d008783.png\"]', NULL, 5, 0, 0, 0, 0, NULL, '2025-12-22 20:57:47', '2025-12-25 16:52:34', NULL);
INSERT INTO `forum_post` VALUES (18, 1, '我的新帖子9', '帖子内容', NULL, NULL, 0, 1, 0, 0, 0, NULL, '2025-12-23 10:00:31', '2026-01-04 14:06:33', NULL);
INSERT INTO `forum_post` VALUES (19, 4006, '测试', '测试111', NULL, NULL, 8, 0, 0, 0, 0, NULL, '2025-12-23 10:04:39', '2025-12-24 16:48:03', '2025-12-24 20:13:46');
INSERT INTO `forum_post` VALUES (20, 4006, '测试33', '333', NULL, NULL, 2, 1, 0, 0, 0, NULL, '2025-12-23 14:26:44', '2025-12-24 17:14:54', '2025-12-24 20:13:42');
INSERT INTO `forum_post` VALUES (21, 4006, '啊啊', '222', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2025-12-23 14:36:57', '2025-12-23 14:36:57', '2025-12-24 20:14:19');
INSERT INTO `forum_post` VALUES (22, 4006, '312', '23', '[\"http://172.16.8.74:8093/uploads/2025-12-23/85a8fce3eb964375ae02502d081e0f4f.png\"]', NULL, 0, 1, 0, 0, 0, NULL, '2025-12-23 15:56:54', '2025-12-24 17:36:31', '2025-12-24 20:13:28');
INSERT INTO `forum_post` VALUES (23, 4006, '测试11', '测试内容111', '[\"http://172.16.8.74:8093/uploads/2025-12-23/51ea547de2be4dda9bfc541a6522a865.png\",\"http://172.16.8.74:8093/uploads/2025-12-23/486a1e752b4344b38b1a2f65ec625f9c.jpg\"]', NULL, 0, 1, 0, 0, 0, NULL, '2025-12-23 15:57:41', '2025-12-24 17:11:34', '2025-12-24 20:13:24');
INSERT INTO `forum_post` VALUES (24, 4006, '1', '1', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-23/7cb93dd42bd24490a6eeea8f9f63f1ce.png\\\"\",\"\\\"http://172.16.8.74:8093/uploads/2025-12-23/9e6b4bc471da4749b8a0248111bc2234.jpg\\\"]\"]', NULL, 8, 2, 0, 0, 0, NULL, '2025-12-23 16:03:36', '2025-12-24 17:35:28', '2025-12-24 19:43:14');
INSERT INTO `forum_post` VALUES (25, 4006, '231', '132', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-23/21bb036cb8774876afd687a03e39974a.png\\\"]\"]', NULL, 17, 2, 0, 0, 0, NULL, '2025-12-23 17:15:33', '2025-12-24 19:20:35', '2025-12-24 20:13:06');
INSERT INTO `forum_post` VALUES (26, 4006, '测试标题', '内容内容内容内容', '[\"[object Array]\"]', NULL, 9, 1, 0, 0, 0, NULL, '2025-12-24 17:34:42', '2025-12-24 17:45:57', '2025-12-24 20:12:57');
INSERT INTO `forum_post` VALUES (27, 4006, '1', '1', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-24/25e5e13f24b8450286f5430092713766.png\\\"\",\"\\\"http://172.16.8.74:8093/uploads/2025-12-24/68f5470afccc4703bfa0ae3b0a9d0172.jpg\\\"]\"]', NULL, 12, 0, 0, 0, 0, NULL, '2025-12-24 17:36:28', '2025-12-24 17:49:14', '2025-12-24 20:12:22');
INSERT INTO `forum_post` VALUES (28, 4006, '测试', '测试内容123123', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-24/630da1ae28b842e9af440b91d7816a67.png\\\"\",\"\\\"http://172.16.8.74:8093/uploads/2025-12-24/dcad8a700de84e0ba830405a08c7b9c2.jpg\\\"]\"]', NULL, 0, 0, 0, 0, 0, NULL, '2025-12-24 19:11:04', '2025-12-24 19:11:04', '2025-12-24 20:12:16');
INSERT INTO `forum_post` VALUES (29, 1, '我的新帖子44', '帖子内容', '[\"/uploads/2025-12-24/577e989396274cb3ad0e40da62439cc7.png\"]', NULL, 0, 0, 0, 0, 0, NULL, '2025-12-24 19:31:44', '2025-12-24 19:31:44', NULL);
INSERT INTO `forum_post` VALUES (30, 4006, '12', '123', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-24/a29ec5fc29564aa8a96a2989225b4284.png\\\"\",\"\\\"http://172.16.8.74:8093/uploads/2025-12-24/49fafaaa7cf7441283ced1b2230c313f.jpg\\\"]\"]', NULL, 0, 0, 0, 0, 0, NULL, '2025-12-24 19:38:25', '2025-12-24 19:38:25', '2025-12-24 20:12:09');
INSERT INTO `forum_post` VALUES (31, 4006, '11', '112', '[\"[object Array]\"]', NULL, 0, 1, 0, 0, 0, NULL, '2025-12-24 19:40:03', '2025-12-25 09:37:50', '2025-12-24 20:12:03');
INSERT INTO `forum_post` VALUES (32, 4006, 'ceshi', '123', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-24/0089093e2859422dab9ffd983f71d0c0.png\\\"\",\"\\\"http://172.16.8.74:8093/uploads/2025-12-24/25dd62d9e3b24541aab3f452fb65e946.jpg\\\"]\"]', NULL, 0, 0, 0, 0, 0, NULL, '2025-12-24 20:00:25', '2025-12-24 20:00:25', '2025-12-24 20:12:00');
INSERT INTO `forum_post` VALUES (33, 4006, '123', '2', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-24/000354e9d08a4b50b4aaf914004ba587.png\\\"\",\"\\\"http://172.16.8.74:8093/uploads/2025-12-24/39b85e989eff45498649bfb6eb6b615f.jpg\\\"]\"]', NULL, 0, 0, 0, 0, 0, NULL, '2025-12-24 20:02:03', '2025-12-24 20:02:03', '2025-12-24 20:11:56');
INSERT INTO `forum_post` VALUES (34, 1, '我的新帖子444', '帖子内容', '[\"/uploads/2025-12-24/9930c8bb09e74a22a18660f28bd02b9a.png\"]', NULL, 0, 0, 0, 0, 0, NULL, '2025-12-24 20:35:07', '2025-12-24 20:35:07', NULL);
INSERT INTO `forum_post` VALUES (35, 4006, 'aaa', '213123', '[\"[\\\"http://172.16.8.74:8093/uploads/2025-12-24/74e3193c8a00475c9a0b45f62eaab4b7.png\\\"\",\"\\\"http://172.16.8.74:8093/uploads/2025-12-24/b5cb93d042464e8ba7c5fd55b4ce2dee.jpg\\\"]\"]', NULL, 0, 1, 0, 0, 0, NULL, '2025-12-24 20:38:54', '2026-01-16 19:00:24', NULL);
INSERT INTO `forum_post` VALUES (36, 4006, '213', '123', NULL, NULL, 24, 4, 0, 0, 0, NULL, '2025-12-24 20:49:09', '2026-01-28 19:41:15', NULL);
INSERT INTO `forum_post` VALUES (37, 4006, '123', '123', '[\"[object Array]\"]', NULL, 5, 2, 0, 0, 0, NULL, '2025-12-24 21:02:26', '2026-01-21 19:28:07', NULL);
INSERT INTO `forum_post` VALUES (38, 4006, 'ceshi', 'ceshi', NULL, NULL, 3, 1, 0, 0, 0, NULL, '2025-12-25 09:21:36', '2026-01-21 19:27:39', NULL);
INSERT INTO `forum_post` VALUES (39, 1, '测试', '内容', NULL, NULL, 5, 2, 0, 0, 0, NULL, '2025-12-25 09:33:20', '2026-01-04 10:07:31', NULL);
INSERT INTO `forum_post` VALUES (40, 1, '测试1', '内容', '[\"/uploads/2025-12-25/9a4a6a8cc5f8422f99a3ed287cd67974.png\"]', NULL, 23, 4, 0, 0, 0, NULL, '2025-12-25 09:34:46', '2026-01-31 12:14:48', NULL);
INSERT INTO `forum_post` VALUES (41, 4006, '13', '31', '[]', NULL, 203, 5, 0, 0, 19, NULL, '2025-12-25 09:52:29', '2026-01-31 10:20:07', NULL);
INSERT INTO `forum_post` VALUES (42, 1, '测试1', '内容', '[\"/uploads/2025-12-25/9a4a6a8cc5f8422f99a3ed287cd67974.png\"]', NULL, 36, 3, 0, 0, 0, NULL, '2025-12-25 09:56:21', '2026-01-04 11:16:14', '2025-12-29 16:26:29');
INSERT INTO `forum_post` VALUES (43, 1, '测试1', '内容', '[\"/uploads/2025-12-25/9a4a6a8cc5f8422f99a3ed287cd67974.png\"]', NULL, 6, 1, 0, 0, 0, NULL, '2025-12-25 10:02:45', '2026-01-04 11:16:24', '2025-12-29 14:28:45');
INSERT INTO `forum_post` VALUES (44, 1, '测试1', '内容', '[\"/uploads/2025-12-25/9a4a6a8cc5f8422f99a3ed287cd67974.png\"]', NULL, 37, 2, 0, 0, 0, NULL, '2025-12-25 10:02:48', '2026-01-04 11:27:00', '2025-12-29 14:27:40');
INSERT INTO `forum_post` VALUES (45, 4006, '测试a', 'abcccc', '[\"http://172.16.8.74:8094/uploads/2025-12-25/d11d3726b81e4053a26d23dca55cfe43.png\",\"http://172.16.8.74:8094/uploads/2025-12-25/f8253623b555494e9650ce6bd292f9c6.jpg\"]', NULL, 188, 2, 0, 0, 7, NULL, '2025-12-25 10:10:55', '2026-01-28 14:13:28', '2025-12-29 14:27:30');
INSERT INTO `forum_post` VALUES (46, 4006, 'ad', 'awd', '[]', NULL, 0, 0, 0, 0, 0, NULL, '2026-01-09 15:35:14', '2026-01-09 16:08:26', '2026-01-09 16:08:26');
INSERT INTO `forum_post` VALUES (47, 4006, '测试帖子', 'ccc', '[]', NULL, 0, 0, 0, 0, 0, NULL, '2026-01-15 20:39:29', '2026-01-15 20:39:29', '2026-01-16 12:05:30');
INSERT INTO `forum_post` VALUES (48, 4006, '测试帖子', 'ccc', '[]', NULL, 0, 1, 0, 0, 0, NULL, '2026-01-15 20:59:08', '2026-01-28 14:12:59', '2026-01-16 12:05:26');
INSERT INTO `forum_post` VALUES (49, 4006, '测试帖子', 'ccc', '[]', NULL, 0, 2, 0, 0, 0, NULL, '2026-01-15 20:59:31', '2026-01-28 10:30:46', '2026-01-16 12:05:23');
INSERT INTO `forum_post` VALUES (50, 4006, '测试帖子', 'ccc', '[]', NULL, 2, 1, 0, 0, 0, NULL, '2026-01-15 20:59:35', '2026-01-28 20:01:05', '2026-01-16 12:05:17');
INSERT INTO `forum_post` VALUES (51, 4006, '测试帖子', 'ccc', '[]', NULL, 0, 3, 0, 0, 0, NULL, '2026-01-15 20:59:37', '2026-01-29 12:07:03', '2026-01-16 12:05:14');
INSERT INTO `forum_post` VALUES (52, 4009, '测试只看楼主', '内容内容+', '[]', NULL, 18, 3, 0, 0, 2, NULL, '2026-01-21 06:53:35', '2026-01-29 12:00:00', NULL);
INSERT INTO `forum_post` VALUES (53, 4006, '测试图片', '图片', '[\"http://172.16.8.74:8094/uploads/2026-01-21/afa1f61552c14d4fa35793788741f69e.jpg\",\"http://172.16.8.74:8094/uploads/2026-01-21/b72dd88dc5de4780851b7daab6f85e07.png\"]', NULL, 10, 1, 0, 0, 0, NULL, '2026-01-21 11:28:39', '2026-01-28 19:50:25', NULL);
INSERT INTO `forum_post` VALUES (54, 4006, '测试11', '111', '[\"http://172.16.8.74:8094/uploads/2026-01-22/9e59e91a79fe40f2ae2712835964a954.png\",\"http://172.16.8.74:8094/uploads/2026-01-22/73e5761d4aff45979c284b98f0c4368f.png\"]', NULL, 7, 1, 0, 0, 1, NULL, '2026-01-22 06:24:48', '2026-01-28 19:54:31', NULL);
INSERT INTO `forum_post` VALUES (55, 12, '测试1', '内容', '[\"/uploads/2025-12-25/9a4a6a8cc5f8422f99a3ed287cd67974.png\"]', NULL, 31, 3, 0, 0, 1, NULL, '2026-01-22 08:31:24', '2026-01-30 10:20:01', NULL);
INSERT INTO `forum_post` VALUES (56, 4006, '创建贴子', '123', '[\"http://172.16.8.163:8094/uploads/2026-01-22/3e4c5218f27b46c6bea2ffc1e2a08c9d.png\",\"http://172.16.8.163:8094/uploads/2026-01-22/7284c18d56394a5cbc93dba1d104535a.jpg\"]', NULL, 113, 3, 0, 0, 3, NULL, '2026-01-22 09:50:19', '2026-01-30 11:11:56', NULL);
INSERT INTO `forum_post` VALUES (57, 4006, 'cehis', '123', '[\"http://172.16.8.163:8094/uploads/2026-01-26/5496d7e54805490ba11d1d9e6eec5114.png\"]', NULL, 61, 3, 0, 0, 3, NULL, '2026-01-26 14:18:10', '2026-01-31 09:41:14', NULL);
INSERT INTO `forum_post` VALUES (58, 4010, '123', '测试', '[\"http://172.16.8.163:8094/uploads/2026-01-26/8f9ad091b2494c2c85b9fea02013f8f1.jpg\"]', NULL, 709, 3, 0, 0, 14, NULL, '2026-01-26 17:51:55', '2026-01-31 15:53:06', NULL);
INSERT INTO `forum_post` VALUES (59, 4006, '21', '2112', '[]', NULL, 103, 4, 0, 0, 8, NULL, '2026-01-28 17:40:27', '2026-01-31 14:58:53', NULL);
INSERT INTO `forum_post` VALUES (60, 4013, '帖子标题111', '帖子内容222', '[\"http://tmp/sd7Zn17-r-Tk96876c5d3d3b3822e1abeca1a53151ba.jpg\",\"http://tmp/qFbMeLJg-Kakce147a69381c67a70a1378cff41c1b7f.png\"]', NULL, 10, 2, 0, 0, 0, NULL, '2026-01-29 16:09:35', '2026-01-31 10:57:40', NULL);
INSERT INTO `forum_post` VALUES (61, 4013, '衡水第一杆', '谁啊这么厉害', '[\"http://tmp/hA8xuz80oiBEce147a69381c67a70a1378cff41c1b7f.png\"]', NULL, 31, 2, 0, 0, 4, NULL, '2026-01-29 16:16:26', '2026-01-31 04:14:23', NULL);
INSERT INTO `forum_post` VALUES (62, 4012, 'daa', 'dsad', '[\"/uploads/2026-01-29/54987c945bf54a46a631c9efcd64c45e.png\"]', NULL, 5, 1, 0, 0, 0, NULL, '2026-01-29 18:09:49', '2026-01-30 12:26:36', NULL);
INSERT INTO `forum_post` VALUES (63, 4012, 'daa', 'dsad', '[\"/uploads/2026-01-29/54987c945bf54a46a631c9efcd64c45e.png\",\"/uploads/2026-01-29/2d06fcc9551e456d9c7e69c037a56107.jpeg\",\"/uploads/2026-01-29/cc2416b0ffc444a08493ccb7acd4848d.jpeg\"]', NULL, 39, 1, 0, 0, 2, NULL, '2026-01-29 18:17:05', '2026-01-30 04:15:02', NULL);
INSERT INTO `forum_post` VALUES (64, 4017, '去吃午饭', '去吧去吧', '[\"http://172.16.8.163:8094/uploads/2026-01-30/ec68a8de7cdf4194b5965bb282705d9f.png\"]', NULL, 13, 1, 0, 0, 1, NULL, '2026-01-30 10:05:42', '2026-01-31 09:53:00', NULL);
INSERT INTO `forum_post` VALUES (65, 4014, '测试帖子', '帖子内容', NULL, NULL, 14, 1, 0, 0, 1, NULL, '2026-01-30 10:21:02', '2026-01-30 12:25:05', NULL);
INSERT INTO `forum_post` VALUES (66, 4016, '月亮测试帖子', '月亮测试帖子的内容111', NULL, NULL, 5, 0, 0, 0, 0, NULL, '2026-01-30 11:36:30', '2026-01-30 12:27:57', NULL);
INSERT INTO `forum_post` VALUES (67, 4013, '哈喽', '你在干什么', '[\"http://tmp/fik689eBqC6Tce147a69381c67a70a1378cff41c1b7f.png\",\"http://tmp/fmr277qLRK8U96876c5d3d3b3822e1abeca1a53151ba.jpg\"]', NULL, 11, 1, 0, 0, 0, NULL, '2026-01-31 09:21:16', '2026-01-31 12:14:04', NULL);
INSERT INTO `forum_post` VALUES (68, 4014, '测试标题', '测试内容', '[\"/uploads/2026-01-31/8240b3362d5441c680c2a94bf8379a70.jpg\"]', NULL, 19, 0, 0, 0, 0, NULL, '2026-01-31 09:28:09', '2026-01-31 12:28:05', NULL);
INSERT INTO `forum_post` VALUES (69, 4010, '今儿天气特别好', '今心情好 想吃炒饼', '[]', NULL, 9, 1, 0, 0, 1, NULL, '2026-01-31 10:32:44', '2026-01-31 11:37:41', NULL);
INSERT INTO `forum_post` VALUES (70, 1, '我的新帖子', '这是帖子的内容', '[\"/uploads/2025-12-24/xxx.jpg\",\"/uploads/2025-12-24/yyy.png\"]', NULL, 2, 0, 0, 0, 0, NULL, '2026-01-31 11:45:23', '2026-01-31 14:46:35', NULL);
INSERT INTO `forum_post` VALUES (71, 4013, '111', '222', '[\"http://tmp/7cgkaciu5m6r96876c5d3d3b3822e1abeca1a53151ba.jpg\"]', NULL, 0, 0, 0, 0, 0, NULL, '2026-01-31 12:23:16', '2026-01-31 12:23:16', '2026-01-31 12:23:20');
INSERT INTO `forum_post` VALUES (72, 4013, '今天吃什么', '炒饼', '[\"http://tmp/nv1Ivjk8wt5Gce147a69381c67a70a1378cff41c1b7f.png\"]', NULL, 0, 0, 0, 0, 0, NULL, '2026-01-31 12:25:19', '2026-01-31 12:25:19', '2026-01-31 12:26:11');
INSERT INTO `forum_post` VALUES (73, 4015, '中午不吃了', '小程序太难了', '[\"https://oa.baoerkeji.com.cn/group2/uploads/2026-01-31/b4eec2debc9640338d243b56acab2717.png\"]', NULL, 6, 0, 0, 0, 0, NULL, '2026-01-31 12:26:45', '2026-02-02 09:14:32', NULL);
INSERT INTO `forum_post` VALUES (74, 4013, '吃什么', '炒饼', '[\"http://tmp/LdJ62yMH0vUB96876c5d3d3b3822e1abeca1a53151ba.jpg\"]', NULL, 0, 1, 0, 0, 0, NULL, '2026-01-31 12:27:52', '2026-01-31 14:44:34', '2026-01-31 12:28:15');
INSERT INTO `forum_post` VALUES (75, 4013, '吃什么', '米饭', '[]', NULL, 0, 0, 0, 0, 0, NULL, '2026-01-31 12:30:12', '2026-01-31 12:30:12', '2026-01-31 12:30:28');
INSERT INTO `forum_post` VALUES (76, 4016, '测试图片', '月亮测试图片', '[\"/uploads/2026-01-31/2c7bb2eb593842c6bb38808ffaf09204.jpg\"]', NULL, 7, 0, 0, 0, 0, NULL, '2026-01-31 13:08:44', '2026-01-31 14:44:45', NULL);
INSERT INTO `forum_post` VALUES (77, 4006, '123', '21', '[\"https://oa.baoerkeji.com.cn/group2/uploads/2026-01-31/d8479f0422bf47288640c11f1d472eba.jpg\"]', NULL, 2, 0, 0, 0, 0, NULL, '2026-01-31 14:56:39', '2026-01-31 14:56:47', NULL);
INSERT INTO `forum_post` VALUES (78, 4006, '231', '32', '[\"https://oa.baoerkeji.com.cn/group2/uploads/2026-01-31/f514ac0c5fdf4780b7a0e453ace2b155.jpg\",\"https://oa.baoerkeji.com.cn/group2/uploads/2026-01-31/1d56d296feff484b97b250070915ef97.png\"]', NULL, 15, 1, 0, 0, 1, NULL, '2026-01-31 14:57:09', '2026-01-31 07:44:35', NULL);

-- ----------------------------
-- Table structure for forum_post_comment
-- ----------------------------
DROP TABLE IF EXISTS `forum_post_comment`;
CREATE TABLE `forum_post_comment`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `post_id` bigint UNSIGNED NOT NULL COMMENT '帖子ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '评论人ID',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `parent_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '父级评论ID(NULL表示根评论)',
  `root_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '根评论ID(方便查询整个评论树)',
  `level` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论层级(0-根评论 1-一级回复 2-二级回复)',
  `like_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数',
  `reply_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复数',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:1-正常 2-用户删除 3-管理员删除',
  `deleted_by` bigint UNSIGNED NULL DEFAULT NULL COMMENT '删除操作人ID',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_post_time`(`post_id` ASC, `created_at` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_root`(`root_id` ASC, `level` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 138 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_post_comment
-- ----------------------------
INSERT INTO `forum_post_comment` VALUES (1, 2, 1, '111', NULL, 1, 0, 2, 0, 2, 1, '2025-12-13 17:16:45', '2025-12-11 17:49:17', '2026-01-15 16:55:55');
INSERT INTO `forum_post_comment` VALUES (2, 2, 2, '222', 1, 1, 1, 0, 0, 2, 1, '2025-12-13 17:16:45', '2025-12-11 17:49:44', '2025-12-13 17:16:45');
INSERT INTO `forum_post_comment` VALUES (3, 2, 3, '333', 1, 1, 1, 0, 0, 2, 1, '2025-12-13 17:16:45', '2025-12-11 17:50:01', '2025-12-13 17:16:45');
INSERT INTO `forum_post_comment` VALUES (4, 2, 4, '444', 1, 1, 1, 0, 0, 2, 1, '2025-12-13 17:16:45', '2025-12-11 17:50:14', '2025-12-13 17:16:45');
INSERT INTO `forum_post_comment` VALUES (5, 2, 3, '111', NULL, 5, 0, 0, 0, 2, 1, '2025-12-13 17:16:45', '2025-12-13 11:22:41', '2025-12-13 17:16:45');
INSERT INTO `forum_post_comment` VALUES (6, 2, 4, '222', 5, 5, 1, 0, 0, 2, 1, '2025-12-13 17:16:45', '2025-12-13 11:23:02', '2025-12-13 17:16:45');
INSERT INTO `forum_post_comment` VALUES (7, 13, 1, '111', 7, 7, 0, 0, 0, 2, 1, '2025-12-15 09:26:04', '2025-12-13 17:13:52', '2025-12-15 09:26:04');
INSERT INTO `forum_post_comment` VALUES (8, 14, 1, '111', 8, 8, 0, 0, 0, 2, 1, '2025-12-15 11:17:07', '2025-12-15 09:52:09', '2025-12-29 17:17:20');
INSERT INTO `forum_post_comment` VALUES (9, 14, 12, '这是一个评论', NULL, 9, 0, 0, 2, 2, 12, '2025-12-15 16:46:00', '2025-12-15 16:13:22', '2025-12-15 16:45:52');
INSERT INTO `forum_post_comment` VALUES (10, 14, 12, '这是一个评论', 9, 9, 1, 0, 0, 1, NULL, NULL, '2025-12-15 16:14:18', '2025-12-15 16:14:18');
INSERT INTO `forum_post_comment` VALUES (11, 14, 1, '这是一个评论', 9, 9, 1, 0, 0, 1, NULL, NULL, '2025-12-29 11:40:26', '2025-12-29 11:40:26');
INSERT INTO `forum_post_comment` VALUES (12, 1, 1, '这是一个评论', NULL, 1, 1, 0, 0, 1, NULL, NULL, '2025-12-29 11:41:31', '2025-12-29 11:41:31');
INSERT INTO `forum_post_comment` VALUES (13, 45, 4006, '评论内容', 0, 45, 0, 0, 0, 1, NULL, '2025-12-29 14:27:31', '2025-12-29 14:06:36', '2025-12-29 14:27:30');
INSERT INTO `forum_post_comment` VALUES (14, 45, 4006, '评论内容', 0, 45, 0, 0, 0, 1, NULL, '2025-12-29 14:27:31', '2025-12-29 14:06:44', '2025-12-29 14:27:30');
INSERT INTO `forum_post_comment` VALUES (15, 45, 4006, '评论内容', 0, 0, 0, 0, 0, 1, NULL, '2025-12-29 14:27:31', '2025-12-29 14:09:34', '2025-12-29 14:27:30');
INSERT INTO `forum_post_comment` VALUES (16, 45, 4006, '评论内容', 0, 45, 0, 0, 0, 1, NULL, '2025-12-29 14:27:31', '2025-12-29 14:13:52', '2025-12-29 14:27:30');
INSERT INTO `forum_post_comment` VALUES (17, 45, 4006, '评论内容', 0, 0, 0, 0, 0, 1, NULL, '2025-12-29 14:27:31', '2025-12-29 14:16:13', '2025-12-29 14:27:30');
INSERT INTO `forum_post_comment` VALUES (18, 45, 4006, '评论内容', 0, 1, 0, 0, 0, 1, NULL, '2025-12-29 14:27:31', '2025-12-29 14:16:38', '2025-12-29 14:27:30');
INSERT INTO `forum_post_comment` VALUES (19, 45, 4006, '评论内容', 0, 1, 0, 0, 0, 1, NULL, NULL, '2025-12-29 15:46:28', '2025-12-29 15:46:28');
INSERT INTO `forum_post_comment` VALUES (20, 45, 4006, '评论内容', 0, 1, 0, 0, 0, 1, NULL, NULL, '2025-12-29 15:49:21', '2025-12-29 15:49:21');
INSERT INTO `forum_post_comment` VALUES (21, 45, 4006, '评论内容11', 0, NULL, 0, 0, 0, 1, NULL, NULL, '2025-12-29 16:00:31', '2025-12-29 16:00:31');
INSERT INTO `forum_post_comment` VALUES (22, 2, 1, '这是一个评论', NULL, NULL, 1, 0, 0, 1, NULL, NULL, '2025-12-29 16:00:40', '2025-12-29 16:00:40');
INSERT INTO `forum_post_comment` VALUES (23, 45, 4006, '评论内容', 0, NULL, 0, 0, 0, 1, NULL, NULL, '2025-12-29 16:01:38', '2025-12-29 16:01:38');
INSERT INTO `forum_post_comment` VALUES (24, 45, 4006, '评论内容', 0, NULL, 0, 0, 0, 1, NULL, NULL, '2025-12-29 16:04:22', '2025-12-29 16:04:22');
INSERT INTO `forum_post_comment` VALUES (25, 45, 4006, '评论内容', 0, NULL, 1, 0, 0, 1, NULL, NULL, '2025-12-29 16:04:29', '2025-12-29 16:04:29');
INSERT INTO `forum_post_comment` VALUES (26, 45, 4006, '评论内容', 0, NULL, 1, 0, 0, 1, NULL, NULL, '2025-12-29 16:10:03', '2025-12-29 16:10:03');
INSERT INTO `forum_post_comment` VALUES (27, 2, 1, '这是一个评论', 0, 1, 1, 0, 0, 1, NULL, NULL, '2025-12-29 19:18:51', '2025-12-29 19:18:51');
INSERT INTO `forum_post_comment` VALUES (28, 2, 1, '这是一个评论', 0, 1, 1, 0, 0, 1, NULL, NULL, '2025-12-29 19:43:27', '2025-12-29 19:43:27');
INSERT INTO `forum_post_comment` VALUES (29, 2, 1, '这是一个评论', 0, 1, 1, 0, 0, 1, NULL, NULL, '2025-12-29 19:49:46', '2025-12-29 19:49:46');
INSERT INTO `forum_post_comment` VALUES (30, 2, 1, '这是一个评论', 0, 1, 1, 0, 0, 1, NULL, NULL, '2025-12-29 20:04:12', '2025-12-29 20:04:12');
INSERT INTO `forum_post_comment` VALUES (31, 2, 1, '这是一个评论', 0, 31, 0, 0, 0, 1, NULL, NULL, '2025-12-29 20:06:14', '2025-12-29 20:06:14');
INSERT INTO `forum_post_comment` VALUES (32, 41, 4006, '123', 0, 32, 0, 2, 5, 1, NULL, NULL, '2026-01-05 09:47:52', '2026-01-15 17:31:05');
INSERT INTO `forum_post_comment` VALUES (33, 41, 4006, '测试评论', 0, 33, 0, 1, 0, 1, NULL, NULL, '2026-01-05 10:03:42', '2026-01-30 10:21:27');
INSERT INTO `forum_post_comment` VALUES (34, 41, 4006, 'ceshi', 0, 34, 0, 1, 1, 1, NULL, NULL, '2026-01-05 10:04:23', '2026-01-15 17:23:37');
INSERT INTO `forum_post_comment` VALUES (35, 41, 4006, '测试评论2', 0, 35, 0, 1, 0, 1, NULL, NULL, '2026-01-05 10:05:07', '2026-01-15 17:23:32');
INSERT INTO `forum_post_comment` VALUES (36, 41, 4006, '测试22', 0, 36, 0, 1, 0, 1, NULL, NULL, '2026-01-05 10:07:54', '2026-01-15 17:17:27');
INSERT INTO `forum_post_comment` VALUES (37, 41, 4006, '回复wx_oOYcH7Z0_4ad179：嗯嗯', 32, 32, 1, 1, 3, 1, NULL, NULL, '2026-01-05 10:26:33', '2026-01-15 19:13:12');
INSERT INTO `forum_post_comment` VALUES (38, 41, 4006, '回复wx_oOYcH7Z0_4ad179：三级评论', 37, 32, 2, 0, 0, 1, NULL, NULL, '2026-01-05 10:35:32', '2026-01-05 10:35:32');
INSERT INTO `forum_post_comment` VALUES (39, 41, 4006, '回复wx_oOYcH7Z0_4ad179：测试2', 32, 32, 1, 1, 2, 1, NULL, NULL, '2026-01-05 19:57:47', '2026-01-15 17:33:13');
INSERT INTO `forum_post_comment` VALUES (40, 41, 4006, '回复wx_oOYcH7Z0_4ad179：222', 39, 32, 2, 0, 0, 1, NULL, NULL, '2026-01-05 19:57:59', '2026-01-05 19:57:59');
INSERT INTO `forum_post_comment` VALUES (41, 2, 12, '这是一个评论', 0, 41, 0, 0, 0, 1, NULL, NULL, '2026-01-06 09:29:37', '2026-01-06 09:29:37');
INSERT INTO `forum_post_comment` VALUES (42, 41, 4006, '回复wx_oOYcH7Z0_4ad179222：回复嗯嗯', 37, 32, 2, 0, 0, 1, NULL, NULL, '2026-01-06 10:27:44', '2026-01-06 10:27:44');
INSERT INTO `forum_post_comment` VALUES (43, 41, 4006, '回复wx_oOYcH7Z0_4ad179：第二条回复', 32, 32, 1, 1, 1, 1, NULL, NULL, '2026-01-06 10:28:16', '2026-01-15 17:16:09');
INSERT INTO `forum_post_comment` VALUES (44, 41, 4006, '回复wx_oOYcH7Z0_4ad179：第三条', 43, 32, 2, 0, 0, 1, NULL, NULL, '2026-01-06 10:28:24', '2026-01-06 10:28:24');
INSERT INTO `forum_post_comment` VALUES (45, 41, 4006, '回复wx_oOYcH7Z0_4ad179：ceshi2', 34, 34, 1, 1, 1, 1, NULL, NULL, '2026-01-06 11:29:18', '2026-01-15 17:23:35');
INSERT INTO `forum_post_comment` VALUES (46, 41, 4006, '回复wx_oOYcH7Z0_4ad179aa：cehshi3', 45, 34, 2, 0, 0, 1, NULL, NULL, '2026-01-06 11:29:32', '2026-01-06 11:29:32');
INSERT INTO `forum_post_comment` VALUES (47, 41, 4006, '回复wx_oOYcH7Z0_4ad179：测试3', 39, 32, 2, 1, 0, 1, NULL, NULL, '2026-01-06 11:40:02', '2026-01-15 17:11:43');
INSERT INTO `forum_post_comment` VALUES (48, 41, 4009, '回复wx_oOYcH7Z0_4ad179：第二个用户', 37, 32, 2, 0, 0, 1, NULL, NULL, '2026-01-06 12:02:21', '2026-01-06 12:02:21');
INSERT INTO `forum_post_comment` VALUES (49, 41, 4009, '回复wx_oOYcH7Z0_4ad179：测试第二个用户', 32, 32, 1, 1, 0, 1, NULL, NULL, '2026-01-06 20:24:15', '2026-01-15 17:31:10');
INSERT INTO `forum_post_comment` VALUES (50, 41, 12, '这是一个评论', 0, 0, 0, 0, 0, 2, 12, '2026-01-09 11:46:58', '2026-01-09 11:39:46', '2026-01-09 11:39:46');
INSERT INTO `forum_post_comment` VALUES (51, 41, 4006, '1.15', 0, 51, 0, 0, 0, 1, NULL, NULL, '2026-01-15 19:34:59', '2026-01-15 19:34:59');
INSERT INTO `forum_post_comment` VALUES (52, 52, 4009, '测试11', 0, 52, 0, 0, 0, 1, NULL, NULL, '2026-01-21 06:55:24', '2026-01-21 06:55:25');
INSERT INTO `forum_post_comment` VALUES (53, 52, 4006, '测试222', 0, 53, 0, 0, 0, 1, NULL, NULL, '2026-01-21 06:56:04', '2026-01-21 06:56:04');
INSERT INTO `forum_post_comment` VALUES (54, 56, 4006, '11', 0, 54, 0, 2, 2, 1, NULL, NULL, '2026-01-22 12:40:34', '2026-01-30 10:19:55');
INSERT INTO `forum_post_comment` VALUES (55, 57, 4006, '123123', 0, 55, 0, 1, 1, 1, NULL, NULL, '2026-01-26 06:18:19', '2026-01-28 18:03:04');
INSERT INTO `forum_post_comment` VALUES (56, 57, 4006, '回复测试昵称：1231', 55, 55, 1, 1, 1, 1, NULL, NULL, '2026-01-26 06:18:25', '2026-01-29 11:19:17');
INSERT INTO `forum_post_comment` VALUES (57, 57, 4006, '回复测试昵称：22222', 56, 55, 2, 0, 0, 1, NULL, NULL, '2026-01-26 06:18:32', '2026-01-26 06:18:32');
INSERT INTO `forum_post_comment` VALUES (58, 58, 4014, '评论内容', 0, 58, 0, 3, 3, 1, NULL, NULL, '2026-01-28 07:24:10', '2026-01-31 15:51:57');
INSERT INTO `forum_post_comment` VALUES (59, 58, 4013, '评论111', 0, 59, 0, 0, 0, 2, 4013, '2026-01-29 11:17:56', '2026-01-28 07:48:18', '2026-01-29 09:44:44');
INSERT INTO `forum_post_comment` VALUES (60, 58, 4014, '测试评论哈哈哈', 0, 60, 0, 2, 0, 1, NULL, NULL, '2026-01-28 07:59:26', '2026-01-29 12:20:54');
INSERT INTO `forum_post_comment` VALUES (61, 58, 4014, '这只是个测试的', 0, 61, 0, 2, 0, 1, NULL, NULL, '2026-01-28 08:04:18', '2026-01-29 12:27:14');
INSERT INTO `forum_post_comment` VALUES (62, 58, 4013, '这是什么帖子', 0, 62, 0, 0, 0, 2, 4013, '2026-01-29 11:20:37', '2026-01-28 08:27:50', '2026-01-28 08:27:50');
INSERT INTO `forum_post_comment` VALUES (63, 58, 4016, '厉害呀', 0, 63, 0, 0, 0, 1, NULL, NULL, '2026-01-28 08:30:01', '2026-01-28 08:30:01');
INSERT INTO `forum_post_comment` VALUES (64, 58, 4012, 'dfsf', 58, 58, 1, 0, 0, 1, NULL, NULL, '2026-01-28 08:53:05', '2026-01-29 12:27:24');
INSERT INTO `forum_post_comment` VALUES (65, 58, 4012, 'dsadsa', 0, 65, 0, 0, 0, 1, NULL, NULL, '2026-01-28 08:55:40', '2026-01-28 08:55:40');
INSERT INTO `forum_post_comment` VALUES (66, 58, 4012, '评论', 0, 66, 0, 1, 0, 1, NULL, NULL, '2026-01-28 08:58:28', '2026-01-29 09:44:49');
INSERT INTO `forum_post_comment` VALUES (67, 58, 4012, '评论', 0, 67, 0, 0, 0, 1, NULL, NULL, '2026-01-28 08:59:23', '2026-01-28 08:59:23');
INSERT INTO `forum_post_comment` VALUES (68, 58, 4012, '评论1', 0, 68, 0, 0, 0, 1, NULL, NULL, '2026-01-28 09:10:40', '2026-01-28 09:10:40');
INSERT INTO `forum_post_comment` VALUES (69, 58, 4012, '评论2', 0, 69, 0, 0, 0, 1, NULL, NULL, '2026-01-28 09:13:32', '2026-01-28 09:13:32');
INSERT INTO `forum_post_comment` VALUES (70, 58, 4012, '评论3', 0, 70, 0, 0, 0, 1, NULL, NULL, '2026-01-28 09:13:51', '2026-01-28 09:13:52');
INSERT INTO `forum_post_comment` VALUES (71, 54, 4012, '评论', 0, 71, 0, 0, 0, 1, NULL, NULL, '2026-01-28 09:14:24', '2026-01-28 09:14:24');
INSERT INTO `forum_post_comment` VALUES (72, 59, 4006, '12', 0, 72, 0, 1, 2, 1, NULL, NULL, '2026-01-28 09:40:59', '2026-01-29 15:03:25');
INSERT INTO `forum_post_comment` VALUES (73, 59, 4006, '回复测试昵称：123', 72, 72, 1, 0, 0, 1, NULL, NULL, '2026-01-28 09:42:01', '2026-01-28 09:42:01');
INSERT INTO `forum_post_comment` VALUES (74, 57, 4013, '评论一下下', 0, 74, 0, 1, 0, 2, 4013, '2026-01-29 11:26:01', '2026-01-28 09:47:05', '2026-01-28 17:47:08');
INSERT INTO `forum_post_comment` VALUES (75, 57, 4013, '评论', 0, 75, 0, 0, 0, 2, 4013, '2026-01-29 11:16:44', '2026-01-28 09:53:18', '2026-01-28 09:53:18');
INSERT INTO `forum_post_comment` VALUES (76, 59, 4016, '11', 0, 76, 0, 0, 1, 1, NULL, NULL, '2026-01-28 10:52:04', '2026-01-29 15:02:36');
INSERT INTO `forum_post_comment` VALUES (77, 55, 4015, '这是测试评论', 0, 77, 0, 1, 0, 1, NULL, NULL, '2026-01-28 11:24:09', '2026-01-30 10:20:05');
INSERT INTO `forum_post_comment` VALUES (78, 58, 4014, 'cecece', 0, 78, 0, 0, 0, 1, NULL, NULL, '2026-01-28 12:54:14', '2026-01-28 12:54:14');
INSERT INTO `forum_post_comment` VALUES (79, 58, 4017, '111', 59, 59, 1, 0, 0, 2, 4017, '2026-01-28 21:50:55', '2026-01-28 13:25:32', '2026-01-28 13:25:32');
INSERT INTO `forum_post_comment` VALUES (80, 58, 4017, '111', 0, 80, 0, 0, 0, 2, 4017, '2026-01-28 21:45:07', '2026-01-28 13:35:32', '2026-01-28 13:35:32');
INSERT INTO `forum_post_comment` VALUES (81, 58, 4017, '111', 0, 81, 0, 0, 0, 2, 4017, '2026-01-28 21:46:43', '2026-01-28 13:46:11', '2026-01-28 13:46:11');
INSERT INTO `forum_post_comment` VALUES (82, 59, 4017, '111', 72, 72, 1, 0, 0, 1, NULL, NULL, '2026-01-28 13:56:00', '2026-01-28 13:56:00');
INSERT INTO `forum_post_comment` VALUES (83, 58, 4017, '111', 66, 66, 1, 0, 0, 2, 4017, '2026-01-28 22:00:10', '2026-01-28 14:00:05', '2026-01-28 14:00:05');
INSERT INTO `forum_post_comment` VALUES (84, 59, 4017, '1111', 0, 84, 0, 0, 0, 2, 4017, '2026-01-28 22:00:24', '2026-01-28 14:00:22', '2026-01-28 14:00:22');
INSERT INTO `forum_post_comment` VALUES (85, 59, 4017, '111', 76, 76, 1, 0, 0, 2, 4017, '2026-01-28 22:00:38', '2026-01-28 14:00:35', '2026-01-28 14:00:35');
INSERT INTO `forum_post_comment` VALUES (86, 58, 4017, '111', 0, 86, 0, 0, 0, 2, 4017, '2026-01-28 22:16:48', '2026-01-28 14:16:46', '2026-01-28 14:16:46');
INSERT INTO `forum_post_comment` VALUES (87, 56, 4013, 'hello', 0, 87, 0, 0, 1, 2, 4013, '2026-01-29 10:58:16', '2026-01-29 01:28:32', '2026-01-29 01:28:32');
INSERT INTO `forum_post_comment` VALUES (88, 56, 4013, 'nihaoa', 54, 54, 1, 0, 0, 2, 4013, '2026-01-29 10:55:29', '2026-01-29 01:44:56', '2026-01-29 10:29:06');
INSERT INTO `forum_post_comment` VALUES (89, 56, 4013, 'nihao', 54, 54, 1, 1, 0, 2, 4013, '2026-01-29 10:55:37', '2026-01-29 01:46:25', '2026-01-29 10:29:08');
INSERT INTO `forum_post_comment` VALUES (90, 56, 4013, 'nihaoa', 54, 54, 1, 0, 0, 2, 4013, '2026-01-29 10:55:44', '2026-01-29 01:48:11', '2026-01-29 01:48:11');
INSERT INTO `forum_post_comment` VALUES (91, 59, 4016, '1111', 0, 91, 0, 0, 1, 1, NULL, NULL, '2026-01-29 01:49:28', '2026-01-29 01:49:29');
INSERT INTO `forum_post_comment` VALUES (92, 56, 4013, 'dadf', 54, 54, 1, 0, 0, 2, 4013, '2026-01-29 11:00:51', '2026-01-29 01:54:34', '2026-01-29 01:54:34');
INSERT INTO `forum_post_comment` VALUES (93, 58, 4017, '111', 78, 78, 1, 0, 0, 2, 4017, '2026-01-29 10:17:52', '2026-01-29 02:17:48', '2026-01-29 02:17:48');
INSERT INTO `forum_post_comment` VALUES (94, 56, 4013, 'hia', 87, 87, 1, 0, 0, 2, 4013, '2026-01-29 10:55:34', '2026-01-29 02:29:19', '2026-01-29 02:29:19');
INSERT INTO `forum_post_comment` VALUES (95, 56, 4013, 'aaadad', 87, 87, 1, 1, 0, 1, NULL, NULL, '2026-01-29 02:29:29', '2026-01-29 10:29:34');
INSERT INTO `forum_post_comment` VALUES (96, 56, 4013, '你好啊', 0, 96, 0, 0, 0, 2, 4013, '2026-01-29 11:57:44', '2026-01-29 02:58:27', '2026-01-29 02:58:27');
INSERT INTO `forum_post_comment` VALUES (97, 56, 4013, '回复内容', 96, 96, 1, 0, 0, 2, 4013, '2026-01-29 10:58:56', '2026-01-29 02:58:39', '2026-01-29 02:58:39');
INSERT INTO `forum_post_comment` VALUES (98, 56, 4013, '回复内容', 96, 96, 1, 1, 0, 2, 4013, '2026-01-29 10:58:51', '2026-01-29 02:58:46', '2026-01-29 10:58:48');
INSERT INTO `forum_post_comment` VALUES (99, 56, 4013, 'Nihaoa', 54, 54, 1, 0, 0, 2, 4013, '2026-01-29 11:01:13', '2026-01-29 02:59:09', '2026-01-29 02:59:09');
INSERT INTO `forum_post_comment` VALUES (100, 56, 4013, 'hahaha', 54, 54, 1, 0, 0, 2, 4013, '2026-01-29 11:01:04', '2026-01-29 02:59:58', '2026-01-29 02:59:58');
INSERT INTO `forum_post_comment` VALUES (101, 56, 4013, 'nihaoa', 54, 54, 1, 0, 0, 2, 4013, '2026-01-29 11:01:15', '2026-01-29 03:01:10', '2026-01-29 03:01:10');
INSERT INTO `forum_post_comment` VALUES (102, 56, 4013, 'haha', 54, 54, 1, 0, 0, 2, 4013, '2026-01-29 11:02:19', '2026-01-29 03:01:20', '2026-01-29 03:01:20');
INSERT INTO `forum_post_comment` VALUES (103, 56, 4013, 'haha', 0, 103, 0, 0, 1, 2, 4013, '2026-01-29 11:01:50', '2026-01-29 03:01:37', '2026-01-29 03:01:37');
INSERT INTO `forum_post_comment` VALUES (104, 56, 4013, 'nihao', 103, 103, 1, 0, 0, 1, NULL, NULL, '2026-01-29 03:01:44', '2026-01-29 03:01:44');
INSERT INTO `forum_post_comment` VALUES (105, 56, 4013, 'hhaa', 54, 54, 1, 0, 0, 1, NULL, NULL, '2026-01-29 03:02:24', '2026-01-29 03:02:24');
INSERT INTO `forum_post_comment` VALUES (106, 58, 4013, 'nihao', 59, 59, 1, 0, 0, 2, 4013, '2026-01-29 11:17:53', '2026-01-29 03:17:50', '2026-01-29 03:17:50');
INSERT INTO `forum_post_comment` VALUES (107, 59, 4013, 'nihao', 0, 107, 0, 0, 0, 2, 4013, '2026-01-29 11:18:36', '2026-01-29 03:18:27', '2026-01-29 03:18:27');
INSERT INTO `forum_post_comment` VALUES (108, 59, 4013, 'haha', 107, 107, 1, 0, 0, 2, 4013, '2026-01-29 11:18:33', '2026-01-29 03:18:31', '2026-01-29 03:18:31');
INSERT INTO `forum_post_comment` VALUES (109, 57, 4013, 'adsf', 0, 109, 0, 0, 0, 2, 4013, '2026-01-29 11:19:09', '2026-01-29 03:19:06', '2026-01-29 03:19:06');
INSERT INTO `forum_post_comment` VALUES (110, 58, 4014, '原神启动', 58, 58, 1, 0, 0, 1, NULL, NULL, '2026-01-29 03:33:19', '2026-01-29 12:27:12');
INSERT INTO `forum_post_comment` VALUES (111, 41, 4013, 'adf', 32, 32, 1, 0, 0, 1, NULL, NULL, '2026-01-29 03:57:21', '2026-01-29 03:57:21');
INSERT INTO `forum_post_comment` VALUES (112, 56, 4013, 'nhao', 54, 54, 1, 1, 0, 1, NULL, NULL, '2026-01-29 03:59:43', '2026-01-29 11:59:46');
INSERT INTO `forum_post_comment` VALUES (113, 59, 4013, 'sdafsad', 0, 113, 0, 0, 2, 2, 4013, '2026-01-29 12:01:41', '2026-01-29 04:01:32', '2026-01-29 04:01:32');
INSERT INTO `forum_post_comment` VALUES (114, 59, 4013, 'adf', 113, 113, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:01:38', '2026-01-29 04:01:38');
INSERT INTO `forum_post_comment` VALUES (115, 59, 4013, 'adsf', 113, 113, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:01:49', '2026-01-29 04:01:49');
INSERT INTO `forum_post_comment` VALUES (116, 59, 4013, 'adsf', 91, 91, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:02:01', '2026-01-29 04:02:01');
INSERT INTO `forum_post_comment` VALUES (117, 59, 4013, 'adsf', 91, 91, 1, 0, 0, 2, 4013, '2026-01-29 12:05:33', '2026-01-29 04:02:11', '2026-01-29 04:02:11');
INSERT INTO `forum_post_comment` VALUES (118, 59, 4013, 'asdf', 76, 76, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:02:16', '2026-01-29 04:02:16');
INSERT INTO `forum_post_comment` VALUES (119, 59, 4013, 'dasfdf', 0, 119, 0, 0, 0, 2, 4013, '2026-01-29 12:04:45', '2026-01-29 04:04:42', '2026-01-29 04:04:43');
INSERT INTO `forum_post_comment` VALUES (120, 59, 4013, 'adsfadf', 0, 120, 0, 0, 1, 2, 4013, '2026-01-29 12:04:54', '2026-01-29 04:04:48', '2026-01-29 04:04:48');
INSERT INTO `forum_post_comment` VALUES (121, 59, 4013, 'adf', 120, 120, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:04:52', '2026-01-29 04:04:52');
INSERT INTO `forum_post_comment` VALUES (122, 58, 4017, '111', 58, 58, 1, 0, 0, 1, NULL, NULL, '2026-01-29 04:21:18', '2026-01-29 12:27:11');
INSERT INTO `forum_post_comment` VALUES (123, 61, 4013, '有没有人看到啊', 0, 123, 0, 1, 0, 2, 4013, '2026-01-29 16:23:07', '2026-01-29 08:22:13', '2026-01-29 16:22:34');
INSERT INTO `forum_post_comment` VALUES (124, 61, 4013, 'pinglun', 0, 124, 0, 0, 1, 1, NULL, NULL, '2026-01-29 08:42:19', '2026-01-30 11:10:56');
INSERT INTO `forum_post_comment` VALUES (125, 61, 4013, '111', 124, 124, 1, 0, 0, 1, NULL, NULL, '2026-01-29 08:42:28', '2026-01-29 08:42:28');
INSERT INTO `forum_post_comment` VALUES (126, 61, 4017, '图片显示不出来', 0, 126, 0, 0, 1, 1, NULL, NULL, '2026-01-29 08:59:41', '2026-01-30 11:10:55');
INSERT INTO `forum_post_comment` VALUES (127, 63, 4015, 'dfrdfg', 0, 127, 0, 2, 1, 1, NULL, NULL, '2026-01-29 10:54:16', '2026-01-30 11:10:48');
INSERT INTO `forum_post_comment` VALUES (128, 58, 4017, '111', 0, 128, 0, 0, 0, 2, 4017, '2026-01-30 09:25:50', '2026-01-30 01:25:44', '2026-01-30 01:25:44');
INSERT INTO `forum_post_comment` VALUES (129, 65, 4012, '评论', 0, 129, 0, 0, 0, 1, NULL, NULL, '2026-01-30 03:12:21', '2026-01-30 11:12:29');
INSERT INTO `forum_post_comment` VALUES (130, 63, 4013, '回复', 127, 127, 1, 0, 0, 1, NULL, NULL, '2026-01-30 04:15:02', '2026-01-30 04:15:02');
INSERT INTO `forum_post_comment` VALUES (131, 64, 4017, 'hahaha', 0, 131, 0, 0, 0, 1, NULL, NULL, '2026-01-30 04:18:00', '2026-01-30 04:18:00');
INSERT INTO `forum_post_comment` VALUES (132, 61, 4013, '你看看是不是你的问题阿超', 126, 126, 1, 0, 0, 1, NULL, NULL, '2026-01-30 04:21:41', '2026-01-30 04:21:41');
INSERT INTO `forum_post_comment` VALUES (133, 66, 4017, '111', 0, 133, 0, 0, 0, 2, 4017, '2026-01-30 12:27:57', '2026-01-30 04:27:31', '2026-01-30 04:27:31');
INSERT INTO `forum_post_comment` VALUES (134, 64, 4017, '11', 0, 134, 0, 0, 0, 2, 4017, '2026-01-31 09:52:59', '2026-01-31 01:52:55', '2026-01-31 01:52:55');
INSERT INTO `forum_post_comment` VALUES (135, 69, 4011, '111', 0, 135, 0, 0, 0, 1, NULL, NULL, '2026-01-31 03:01:50', '2026-01-31 03:01:50');
INSERT INTO `forum_post_comment` VALUES (136, 61, 4013, 'dsaf', 0, 136, 0, 0, 0, 2, 4013, '2026-01-31 12:14:24', '2026-01-31 04:14:21', '2026-01-31 04:14:21');
INSERT INTO `forum_post_comment` VALUES (137, 78, 4006, '123', 0, 137, 0, 0, 0, 1, NULL, NULL, '2026-01-31 07:44:35', '2026-01-31 07:44:35');

-- ----------------------------
-- Table structure for mch_category
-- ----------------------------
DROP TABLE IF EXISTS `mch_category`;
CREATE TABLE `mch_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `mch_id` bigint NULL DEFAULT NULL COMMENT '商家id',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级分类ID（0表示顶级分类）',
  `level` tinyint NOT NULL DEFAULT 1 COMMENT '分类层级（1-一级，2-二级 3-三级)',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态:0-禁用 1-启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `is_global` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-全局分类 1-商家自定义分类',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_sort`(`sort_order` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 440 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_category
-- ----------------------------
INSERT INTO `mch_category` VALUES (1, '电子产品', 1, 0, 1, 1, 1, '2024-01-15 09:00:00', '2026-01-16 17:03:26', NULL, 0);
INSERT INTO `mch_category` VALUES (403, '蔬菜', 11, 0, 1, 2, 1, '2025-12-11 15:21:28', '2026-01-16 17:05:49', NULL, 0);
INSERT INTO `mch_category` VALUES (404, '叶类', NULL, 403, 2, 1, 1, '2025-12-11 15:22:04', '2026-01-16 17:07:34', NULL, 0);
INSERT INTO `mch_category` VALUES (405, '测试父分类', NULL, 0, 1, 2, 1, '2025-12-11 17:33:42', '2025-12-11 19:26:11', '2025-12-11 19:26:11', 0);
INSERT INTO `mch_category` VALUES (406, '测试子分类', NULL, 405, 2, 1, 0, '2025-12-11 17:36:24', '2025-12-11 19:26:08', '2025-12-11 19:26:08', 0);
INSERT INTO `mch_category` VALUES (407, '测试22', NULL, 0, 1, 1, 1, '2025-12-11 17:39:27', '2025-12-11 19:26:14', '2025-12-11 19:26:14', 0);
INSERT INTO `mch_category` VALUES (408, '1223', NULL, 407, 2, 1, 0, '2025-12-11 17:39:50', '2025-12-11 19:24:04', '2025-12-11 19:24:04', 0);
INSERT INTO `mch_category` VALUES (409, '肉蛋', 13, 0, 1, 1, 1, '2025-12-11 19:26:44', '2026-01-16 17:06:04', NULL, 0);
INSERT INTO `mch_category` VALUES (410, '牛肉', NULL, 409, 2, 1, 1, '2025-12-11 19:26:56', '2026-01-16 17:07:44', NULL, 0);
INSERT INTO `mch_category` VALUES (411, '羊肉', NULL, 409, 2, 2, 0, '2025-12-11 19:27:09', '2025-12-11 19:29:21', '2025-12-11 19:29:21', 0);
INSERT INTO `mch_category` VALUES (412, '早餐', NULL, 0, 1, 2, 1, '2025-12-12 16:06:11', '2025-12-23 09:23:00', '2025-12-23 09:23:00', 0);
INSERT INTO `mch_category` VALUES (413, '水果', NULL, 0, 1, 3, 1, '2025-12-22 11:18:48', '2025-12-29 09:26:48', '2025-12-29 09:26:48', 0);
INSERT INTO `mch_category` VALUES (414, '根类', NULL, 403, 2, 2, 1, '2025-12-22 11:29:08', '2025-12-29 09:26:52', '2025-12-29 09:26:52', 0);
INSERT INTO `mch_category` VALUES (415, '苹果', NULL, 413, 2, 2, 0, '2025-12-22 11:35:09', '2025-12-22 15:23:58', '2025-12-22 15:23:58', 0);
INSERT INTO `mch_category` VALUES (416, '香蕉', NULL, 413, 2, 1, 0, '2025-12-22 14:56:28', '2025-12-22 15:23:49', '2025-12-22 15:23:49', 0);
INSERT INTO `mch_category` VALUES (417, '橘子', NULL, 413, 2, 1, 1, '2025-12-22 15:01:13', '2025-12-23 09:22:01', '2025-12-23 09:22:01', 0);
INSERT INTO `mch_category` VALUES (418, 'ces', NULL, 409, 2, 1, 1, '2025-12-22 15:12:16', '2025-12-22 15:12:20', '2025-12-22 15:12:20', 0);
INSERT INTO `mch_category` VALUES (419, '11', NULL, 0, 1, 11, 1, '2025-12-22 15:12:45', '2025-12-22 15:12:49', '2025-12-22 15:12:49', 0);
INSERT INTO `mch_category` VALUES (420, '测试', NULL, 0, 1, 1, 1, '2025-12-22 15:13:17', '2025-12-22 15:13:46', '2025-12-22 15:13:46', 0);
INSERT INTO `mch_category` VALUES (421, '葡萄', NULL, 413, 2, 1, 1, '2025-12-22 15:24:13', '2025-12-22 17:05:10', '2025-12-22 17:05:10', 0);
INSERT INTO `mch_category` VALUES (422, '梨', NULL, 413, 2, 1, 1, '2025-12-22 15:26:34', '2025-12-23 10:30:53', '2025-12-23 10:30:53', 0);
INSERT INTO `mch_category` VALUES (423, '苹果', NULL, 413, 2, 1, 1, '2025-12-22 15:28:05', '2025-12-22 15:28:10', '2025-12-22 15:28:10', 0);
INSERT INTO `mch_category` VALUES (424, '测试分类111', 12, 0, 1, 1, 1, '2025-12-22 15:44:34', '2026-01-16 17:07:32', NULL, 0);
INSERT INTO `mch_category` VALUES (425, '测试子分类', NULL, 424, 2, 1, 1, '2025-12-22 15:44:49', '2025-12-22 15:44:49', NULL, 0);
INSERT INTO `mch_category` VALUES (426, '晚餐', 14, 0, 1, 1, 1, '2025-12-22 17:05:58', '2026-01-16 17:07:48', NULL, 0);
INSERT INTO `mch_category` VALUES (427, '梨', NULL, 403, 2, 1, 1, '2025-12-29 09:36:06', '2025-12-29 09:36:09', '2025-12-29 09:36:09', 0);
INSERT INTO `mch_category` VALUES (428, '水果', NULL, 0, 1, 2, 1, '2026-01-04 15:15:07', '2026-01-04 15:15:45', '2026-01-04 15:15:45', 0);
INSERT INTO `mch_category` VALUES (429, '香蕉', NULL, 428, 2, 2, 1, '2026-01-04 15:15:25', '2026-01-04 15:15:43', '2026-01-04 15:15:43', 0);
INSERT INTO `mch_category` VALUES (430, '梨', NULL, 428, 2, 1, 1, '2026-01-04 15:15:37', '2026-01-04 15:15:43', '2026-01-04 15:15:43', 0);
INSERT INTO `mch_category` VALUES (431, '电子产品', NULL, 0, 1, 55, 1, '2026-01-04 15:45:25', '2026-01-04 15:45:27', '2026-01-04 15:45:27', 0);
INSERT INTO `mch_category` VALUES (432, '鸡肉', NULL, 409, 2, 3, 1, '2026-01-04 17:02:48', '2026-01-04 17:02:48', NULL, 0);
INSERT INTO `mch_category` VALUES (433, '衣服', NULL, 0, 1, 1, 0, '2026-01-04 17:03:33', '2026-01-04 17:33:07', '2026-01-04 17:33:07', 0);
INSERT INTO `mch_category` VALUES (434, '11', NULL, 433, 2, 1, 1, '2026-01-04 17:04:52', '2026-01-04 17:33:06', '2026-01-04 17:33:06', 0);
INSERT INTO `mch_category` VALUES (435, '分类1', NULL, 0, 1, 1, 1, '2026-01-10 14:38:01', '2026-01-16 17:03:14', NULL, 1);
INSERT INTO `mch_category` VALUES (436, '分类', NULL, 0, 1, 1, 1, '2026-01-10 14:45:09', '2026-01-16 17:03:10', NULL, 1);
INSERT INTO `mch_category` VALUES (437, '小吃', 1, 0, 1, 1, 1, '2026-02-07 03:32:38', '2026-02-07 03:32:38', NULL, 1);
INSERT INTO `mch_category` VALUES (438, '健身', 1101, 0, 1, 1, 1, '2026-02-07 08:02:03', '2026-02-07 08:02:03', NULL, 1);
INSERT INTO `mch_category` VALUES (439, '健身2', 1101, 0, 1, 1, 1, '2026-02-07 08:26:47', '2026-02-07 08:26:47', NULL, 1);

-- ----------------------------
-- Table structure for mch_commission_config
-- ----------------------------
DROP TABLE IF EXISTS `mch_commission_config`;
CREATE TABLE `mch_commission_config`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `merchant_id` bigint UNSIGNED NOT NULL COMMENT '联表商家id',
  `commission_rate` tinyint UNSIGNED NOT NULL COMMENT '分佣比例，10代表10%',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_commission_config
-- ----------------------------
INSERT INTO `mch_commission_config` VALUES (3, 1002, 20, '2025-12-15 12:12:54', '2025-12-15 12:12:54', NULL);
INSERT INTO `mch_commission_config` VALUES (4, 1003, 21, '2025-12-15 12:12:54', '2025-12-15 12:12:54', NULL);
INSERT INTO `mch_commission_config` VALUES (5, 1004, 12, '2025-12-15 12:12:54', '2025-12-15 12:12:54', NULL);
INSERT INTO `mch_commission_config` VALUES (6, 1005, 25, '2025-12-15 12:12:54', '2025-12-15 12:12:54', NULL);
INSERT INTO `mch_commission_config` VALUES (7, 1006, 18, '2025-12-15 12:12:54', '2025-12-15 12:12:54', NULL);
INSERT INTO `mch_commission_config` VALUES (8, 1007, 8, '2025-12-15 12:12:54', '2025-12-15 12:12:54', NULL);
INSERT INTO `mch_commission_config` VALUES (9, 1008, 30, '2025-12-15 12:12:54', '2025-12-15 12:12:54', NULL);

-- ----------------------------
-- Table structure for mch_legacy_product
-- ----------------------------
DROP TABLE IF EXISTS `mch_legacy_product`;
CREATE TABLE `mch_legacy_product`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品标题',
  `selling_points` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品卖点',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品详情',
  `main_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品主图URL',
  `images` json NULL COMMENT '图片表',
  `category_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联的商品分类ID',
  `merchant_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属商家ID',
  `price` decimal(12, 2) NOT NULL COMMENT '价格',
  `profit_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'RATIO' COMMENT '收益类型:RATIO-按比例,FIXED-固定金额',
  `partner_profit` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '合伙人收益（按比例时为百分比如15.5，固定金额时为具体金额）',
  `merchant_profit` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '服务商家收益（按比例时为百分比，固定金额时为具体金额）',
  `spec_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'SINGLE' COMMENT '规格类型:SINGLE-统一规格,MULTIPLE-多规格',
  `spec_data` json NULL COMMENT '规格数据',
  `shelf_status` tinyint NOT NULL DEFAULT 0 COMMENT '上架状态:0-未上架 1-已上架',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联的审核记录ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category`(`category_id` ASC) USING BTREE,
  INDEX `idx_shelf_status`(`shelf_status` ASC) USING BTREE,
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC) USING BTREE,
  INDEX `idx_merchant`(`merchant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_legacy_product
-- ----------------------------
INSERT INTO `mch_legacy_product` VALUES (2, '可口可乐', '冰镇可口可乐 330ml', '冰爽解渴', '产品详细介绍...', 'https://qna.smzdm.com/202404/09/66149318f741e1607.jpg_fo742.jpg', NULL, 1, 1, 3.50, 'RATIO', 0.50, 2.50, 'SINGLE', NULL, 1, 1077, '2025-12-11 20:31:51', '2026-01-26 04:18:14', NULL);
INSERT INTO `mch_legacy_product` VALUES (3, 'kfc', '汉堡', '快餐', '汉堡', '', NULL, 404, 12, 50.00, 'RATIO', 0.00, 0.00, 'SINGLE', NULL, 1, 1023, '2025-12-11 20:36:32', '2026-01-16 17:06:34', '2025-12-20 17:34:26');
INSERT INTO `mch_legacy_product` VALUES (4, '麦当劳', '汉堡', '快餐', '<p>汉堡</p>', '/uploads/2025-12-26/96f0d07667094f218da7647be57bac76.png', NULL, 405, 11, 50.00, 'RATIO', 0.00, 0.00, 'SINGLE', NULL, 1, 1022, '2025-12-11 20:38:47', '2026-01-16 17:06:35', '2025-12-29 09:32:12');
INSERT INTO `mch_legacy_product` VALUES (5, '黄焖鸡米饭', '鸡肉', '主食', '<p>鸡肉</p>', '/uploads/2025-12-26/c026af29954b48b2b764f583aaee3be3.jpg', NULL, 426, 14, 16.00, 'RATIO', 0.00, 0.00, 'SINGLE', NULL, 1, 1021, '2025-12-11 20:39:40', '2026-01-16 17:23:49', NULL);
INSERT INTO `mch_legacy_product` VALUES (6, '包子', '早餐', '包子', '<p>包子</p>', '/uploads/2025-12-19/1a5e2888f509468f960b80142fba8a12.png', NULL, 412, 14, 1.50, 'RATIO', 0.00, 0.00, 'SINGLE', '[{\"name\": \"1/3\", \"price\": null, \"options\": [\"1\", \"3\"]}, {\"name\": \"1/4\", \"price\": null, \"options\": [\"1\", \"4\"]}, {\"name\": \"2/3\", \"price\": null, \"options\": [\"2\", \"3\"]}, {\"name\": \"2/4\", \"price\": null, \"options\": [\"2\", \"4\"]}]', 1, 1020, '2025-12-11 20:41:17', '2026-01-16 17:06:39', '2025-12-23 09:23:35');
INSERT INTO `mch_legacy_product` VALUES (10, '商品1', '商品标题', '商品买点', '商品详细描述', 'https://example.com/product1.jpg', NULL, 1, 1, 9.99, 'RATIO', 0.50, 2.50, 'SINGLE', '[{\"name\": \"尺寸\", \"options\": [{\"name\": \"大杯\", \"price\": 100}, {\"name\": \"小杯\", \"price\": 80}]}, {\"name\": \"辣度\", \"options\": [{\"name\": \"微辣\", \"price\": 0}, {\"name\": \"中辣\", \"price\": 5}, {\"name\": \"特辣\", \"price\": 10}]}]', 1, NULL, '2026-01-29 15:00:46', '2026-01-29 09:16:46', NULL);
INSERT INTO `mch_legacy_product` VALUES (11, '商品2', '商品标题', '商品买点', '商品详细描述', 'https://example.com/product1.jpg', NULL, 1, 1, 9.99, 'RATIO', 0.50, 2.50, 'MULTIPLE', '[{\"name\": \"尺寸\", \"options\": [{\"name\": \"大杯\", \"price\": 100}, {\"name\": \"小杯\", \"price\": 80}]}, {\"name\": \"辣度\", \"options\": [{\"name\": \"微辣\", \"price\": 0}, {\"name\": \"中辣\", \"price\": 5}, {\"name\": \"特辣\", \"price\": 10}]}]', 1, NULL, '2026-01-29 15:01:19', '2026-01-29 09:17:41', NULL);
INSERT INTO `mch_legacy_product` VALUES (12, '商品3', '商品标题', '商品买点', '商品详细描述', 'https://example.com/product1.jpg', NULL, 1, 1, 9.99, 'RATIO', 0.50, 2.50, 'MULTIPLE', '[{\"name\": \"尺寸\", \"options\": [{\"name\": \"大杯\", \"price\": 100}, {\"name\": \"小杯\", \"price\": 80}]}, {\"name\": \"辣度\", \"options\": [{\"name\": \"微辣\", \"price\": 0}, {\"name\": \"中辣\", \"price\": 5}, {\"name\": \"特辣\", \"price\": 10}]}]', 1, NULL, '2026-01-29 15:01:24', '2026-01-29 09:17:43', NULL);
INSERT INTO `mch_legacy_product` VALUES (13, '奶茶', '奶茶', '好喝', '11111', '/uploads/2026-02-07/34c25f5997ea47f7965b5f012daec3b2.jpeg', NULL, 405, 1105, 10.00, 'RATIO', 0.00, 0.00, 'MULTIPLE', '[{\"id\": 1770445529432, \"name\": \"份量\", \"options\": [{\"id\": 1770445539901, \"name\": \"小杯\", \"price\": 1}, {\"id\": 1770445544300, \"name\": \"大杯\", \"price\": 2}]}]', 0, NULL, '2026-02-07 14:25:50', '2026-02-07 14:25:50', NULL);
INSERT INTO `mch_legacy_product` VALUES (14, '面包', '面包', '好吃', '11', '/uploads/2026-02-07/983e9c474519492b87c737819d60b135.jpeg', NULL, NULL, 1105, 8.00, 'RATIO', 0.00, 0.00, 'MULTIPLE', '[]', 0, NULL, '2026-02-07 14:58:00', '2026-02-07 14:58:00', NULL);
INSERT INTO `mch_legacy_product` VALUES (15, '八宝粥', '八宝粥', '好喝', '111', '/uploads/2026-02-07/12e6ce2e240c46af901131fa23e7a48e.png', NULL, NULL, 1105, 6.00, 'RATIO', 0.00, 0.00, 'MULTIPLE', '[{\"id\": 1770447969591, \"name\": \"分量\", \"options\": [{\"id\": 1770447981811, \"name\": \"小杯\", \"price\": 1}]}]', 0, NULL, '2026-02-07 15:06:29', '2026-02-07 15:06:29', NULL);
INSERT INTO `mch_legacy_product` VALUES (16, '肥牛', '肥牛', '好吃', '嫩滑', '/uploads/2026-02-07/5cd71868b3bc40c2bd6eb4419f8217fa.png', NULL, 410, 1101, 20.00, 'RATIO', 0.00, 0.00, 'MULTIPLE', '[{\"name\": \"分量\", \"options\": [{\"name\": \"500g\", \"price\": 5}, {\"name\": \"1000g\", \"price\": 10}]}]', 0, NULL, '2026-02-07 17:11:56', '2026-02-09 01:52:55', NULL);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `sender_id` bigint NOT NULL COMMENT '发送者用户ID，0表示系统通知',
  `sender_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT '发送者类型：system系统/admin管理员/user用户/rider骑手/merchant商家',
  `receiver_id` bigint NOT NULL DEFAULT 0 COMMENT '接收者用户ID，0表示全体用户',
  `receiver_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT '接收者类型：user用户/rider骑手/merchant商家',
  `message_title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息标题',
  `message_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息内容',
  `message_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'system' COMMENT '消息类型：system系统通知/remind提醒/private私信',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_receiver_id`(`receiver_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_sender_type`(`sender_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '站内消息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 0, 'system', 1, 'merchant', '系统通知：新订单提醒', '您有一笔新订单需要处理，请及时查看并接单。订单号：ORD202602050001', 'system', '2026-02-05 12:15:21');
INSERT INTO `message` VALUES (2, 100, 'admin', 1, 'merchant', '重要提醒：结算通知', '本月结算通知已发布，请注意查看财务明细及结算时间安排', 'remind', '2026-02-05 12:16:21');
INSERT INTO `message` VALUES (3, 0, 'system', 1, 'merchant', '平台公告：服务升级', '为了提升服务质量，平台将于今晚23:00-24:00进行系统维护，请提前做好准备', 'system', '2026-02-05 12:17:21');

-- ----------------------------
-- Table structure for ord_order
-- ----------------------------
DROP TABLE IF EXISTS `ord_order`;
CREATE TABLE `ord_order`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `buyer_id` bigint UNSIGNED NOT NULL COMMENT '买家ID',
  `buyer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '买家姓名',
  `buyer_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '买家电话',
  `buyer_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '买家地址',
  `order_type` int NULL DEFAULT NULL COMMENT '订单类型:1-服务人员订单 2-商家订单 3-骑手订单',
  `order_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单分类信息（代取快递/商家外卖等）',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `buyer_amount` decimal(10, 2) NOT NULL COMMENT '买家付款金额/元',
  `pay_status` int NULL DEFAULT NULL COMMENT '支付状态:0-未支付 1-已支付 2-已退款',
  `pay_method` int NULL DEFAULT NULL COMMENT '支付方式:1-微信支付 2-支付宝 3-银行卡',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '学校ID',
  `partner_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '合伙人ID',
  `staff_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '服务人员ID',
  `merchant_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '商家ID',
  `rider_id` bigint NULL DEFAULT NULL COMMENT '骑手id',
  `partner_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务区域归属合伙人',
  `staff_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务人员',
  `staff_profit` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '服务人员收益',
  `merchant_profit` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '服务商家收益',
  `partner_profit` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '合伙人收益',
  `order_status` int NULL DEFAULT NULL COMMENT '订单状态:0-已取消 1-待支付 2-待接单 3-待取货 4-配送中 5-已完成',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no` ASC) USING BTREE,
  INDEX `idx_order_time`(`order_time` ASC) USING BTREE,
  INDEX `idx_status`(`order_status` ASC) USING BTREE,
  INDEX `idx_buyer`(`buyer_id` ASC) USING BTREE,
  INDEX `idx_school`(`school_id` ASC) USING BTREE,
  INDEX `idx_partner`(`partner_id` ASC) USING BTREE,
  INDEX `idx_staff`(`staff_id` ASC) USING BTREE,
  INDEX `idx_merchant`(`merchant_id` ASC) USING BTREE,
  INDEX `idx_pay_status`(`pay_status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ord_order
-- ----------------------------
INSERT INTO `ord_order` VALUES (23, 'S20260105', 2, '章鱼哥', '13333333333', '河北师大西门', 2, '商家外卖', '2026-01-05 09:36:39', 20.00, 0, 1, '2026-01-05 09:36:52', 10005, 20004, 5, 1, 401, '李四', '王五', 8.00, 10.00, 2.00, 2, '2026-01-05 09:39:31', '2026-01-07 19:24:19', NULL);
INSERT INTO `ord_order` VALUES (24, 'F20260105', 3, '海绵宝宝 ', '15555555555', '欢乐汇 ', 1, '代取快递', '2026-01-05 09:43:09', 5.00, 0, 1, '2026-01-05 09:43:26', 10004, 20004, 5, 2, 401, '李四', '王五', 1.00, 3.00, 1.00, 1, '2026-01-05 09:42:14', '2026-01-27 11:27:48', '2026-01-27 11:27:48');
INSERT INTO `ord_order` VALUES (25, 'Q20260105', 4, '派大星', '16666666666', '裕华区万达', 3, '商家外卖', '2026-01-05 09:47:12', 5.00, 0, 1, '2026-01-05 09:47:27', 10006, 20004, 5, 1, 401, '李四', '王五', 1.00, 3.00, 1.00, 0, '2026-01-05 09:46:29', '2026-01-05 09:48:36', NULL);
INSERT INTO `ord_order` VALUES (26, 'SO2026010611591352aabaec36', 1, '张三', '18888888888', '某某社区1号楼-1-101', 1, '帮我取', '2026-01-06 11:59:14', 5.00, 0, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-06 11:59:14', '2026-01-27 11:27:50', '2026-01-27 11:27:50');
INSERT INTO `ord_order` VALUES (27, 'DO20260107212010513d68e8a5', 1, '张三', '18888888888', '某某社区1号楼-1-101', 2, '外卖', '2026-01-07 21:20:10', 9.00, 0, NULL, NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-07 21:20:06', '2026-01-07 21:20:06', NULL);
INSERT INTO `ord_order` VALUES (28, 'SO20260121153433bcc8d6955c', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-21 15:34:34', 2.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-21 07:34:36', '2026-01-27 11:27:52', '2026-01-27 11:27:52');
INSERT INTO `ord_order` VALUES (29, 'SO2026012115490148805a6a73', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我取', '2026-01-21 15:49:02', 2.00, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-21 07:49:05', '2026-01-27 11:27:54', '2026-01-27 11:27:54');
INSERT INTO `ord_order` VALUES (30, 'SO20260121154932bd7ee00ee4', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我取', '2026-01-21 15:49:33', 2.00, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-21 07:49:36', '2026-01-27 11:27:56', '2026-01-27 11:27:56');
INSERT INTO `ord_order` VALUES (31, 'SO20260121155814cbb1701b24', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我取', '2026-01-21 15:58:14', 2.00, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-21 07:58:17', '2026-01-27 11:27:58', '2026-01-27 11:27:58');
INSERT INTO `ord_order` VALUES (32, 'SO20260121160105700c8a5df3', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我取', '2026-01-21 16:01:05', 2.00, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-21 08:01:08', '2026-01-27 11:27:33', '2026-01-27 11:27:33');
INSERT INTO `ord_order` VALUES (33, 'SO202601211607255f54423e3c', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我取', '2026-01-21 16:07:26', 2.00, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-21 08:07:29', '2026-01-27 11:27:35', '2026-01-27 11:27:35');
INSERT INTO `ord_order` VALUES (34, 'SO20260122145312567aa6463b', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我取', '2026-01-22 14:53:13', 2.00, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-22 06:53:14', '2026-01-27 11:27:23', '2026-01-27 11:27:23');
INSERT INTO `ord_order` VALUES (35, 'SO20260122145727095c293fd1', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-22 14:57:28', 2.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-22 06:57:28', '2026-01-27 11:27:24', '2026-01-27 11:27:24');
INSERT INTO `ord_order` VALUES (36, 'SO20260122150254a2336bc4dc', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-22 15:02:55', 2.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-22 07:02:56', '2026-01-27 11:27:26', '2026-01-27 11:27:26');
INSERT INTO `ord_order` VALUES (37, 'SO202601221537430b22596e5a', 4009, '王五二', '15193846743', '张家口职业技术学院', 1, '帮我取', '2026-01-22 15:37:44', 2.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-22 07:37:45', '2026-01-27 11:27:29', '2026-01-27 11:27:29');
INSERT INTO `ord_order` VALUES (38, 'DO202601221612211ba1f56482', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-22 16:12:21', 165.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-22 08:12:22', '2026-01-22 08:12:22', NULL);
INSERT INTO `ord_order` VALUES (39, 'DO20260122161235f1501a779d', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-22 16:12:35', 165.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-22 08:12:36', '2026-01-22 08:12:36', NULL);
INSERT INTO `ord_order` VALUES (40, 'SO2026012217000315fb533c50', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-22 17:00:04', 15.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 3, '2026-01-22 09:00:05', '2026-01-27 11:27:31', '2026-01-27 11:27:31');
INSERT INTO `ord_order` VALUES (41, 'SO20260122171549bcfed3fff0', 4009, '王五', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-22 17:15:50', 15.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 3, '2026-01-22 09:15:51', '2026-01-27 11:27:37', '2026-01-27 11:27:37');
INSERT INTO `ord_order` VALUES (42, 'SO2026012220290738a1b904b2', 4009, '安其拉', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-22 20:29:08', 15.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-22 12:29:09', '2026-01-27 11:27:39', '2026-01-27 11:27:39');
INSERT INTO `ord_order` VALUES (43, 'SO2026012220314783f619d2c0', 4009, '安其拉', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-22 20:31:48', 15.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-22 12:31:49', '2026-01-27 11:27:41', '2026-01-27 11:27:41');
INSERT INTO `ord_order` VALUES (44, 'SO20260123110626751bcdc891', 4009, '安其拉', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-23 11:06:27', 15.00, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-23 03:06:30', '2026-01-27 11:28:04', '2026-01-27 11:28:04');
INSERT INTO `ord_order` VALUES (45, 'SO20260124120414e8c502713b', 4009, '安其拉', '13904856343', '张家口职业技术学院', 1, '帮我送', '2026-01-24 12:04:15', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-24 04:04:14', '2026-01-27 11:28:06', '2026-01-27 11:28:06');
INSERT INTO `ord_order` VALUES (46, 'SO20260124144618b6b5797013', 4009, '2312312', '13903494723', '2133231', 1, '帮我送', '2026-01-24 14:46:19', 0.01, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-24 06:46:19', '2026-01-27 11:28:09', '2026-01-27 11:28:09');
INSERT INTO `ord_order` VALUES (47, 'SO20260126092124ae77f0fe55', 4009, '韩宝', '13904857743', '清华大学', 1, '帮我送', '2026-01-26 09:21:25', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 3, '2026-01-26 09:21:25', '2026-01-27 11:28:14', '2026-01-27 11:28:14');
INSERT INTO `ord_order` VALUES (48, 'SO20260126092947b066158a32', 4009, '老王', '13904856754', '滨海新区', 1, '帮我送', '2026-01-26 09:29:48', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 5, '2026-01-26 09:29:48', '2026-01-27 11:27:15', '2026-01-27 11:27:15');
INSERT INTO `ord_order` VALUES (49, 'SO2026012609464706720cbc70', 4009, '老王', '13904856754', '滨海新区', 1, '帮我取', '2026-01-26 09:46:47', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 5, '2026-01-26 09:46:47', '2026-01-27 11:27:09', '2026-01-27 11:27:09');
INSERT INTO `ord_order` VALUES (50, 'SO20260126102354c96f87ef65', 4009, '老楚', '13904895755', '大城县一中', 1, '帮我送', '2026-01-26 10:23:54', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 5, '2026-01-26 10:23:54', '2026-01-27 11:27:11', '2026-01-27 11:27:11');
INSERT INTO `ord_order` VALUES (51, 'SO202601261058577a134efed4', 4009, '老楚', '13904895755', '大城县一中', 1, '帮我送', '2026-01-26 10:58:58', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 5, '2026-01-26 10:58:58', '2026-01-27 11:28:35', '2026-01-27 11:28:35');
INSERT INTO `ord_order` VALUES (52, 'SO2026012611052224f9d07759', 4006, '往往', '16111111111', '沧州师范2号楼', 1, '帮我送', '2026-01-26 11:05:23', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-26 11:05:23', '2026-01-27 11:28:33', '2026-01-27 11:28:33');
INSERT INTO `ord_order` VALUES (53, 'SO20260126111601897a30e3ff', 4006, '往往', '16111111111', '沧州师范学院2号楼', 1, '帮我取', '2026-01-26 11:16:02', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 5, '2026-01-26 11:16:02', '2026-01-27 11:28:29', '2026-01-27 11:28:29');
INSERT INTO `ord_order` VALUES (54, 'SO20260126144335220503b52f', 4009, '老王', '13904856754', '滨海新区', 1, '帮我送', '2026-01-26 14:43:36', 0.01, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 5, '2026-01-26 14:43:36', '2026-01-27 11:28:27', '2026-01-27 11:28:27');
INSERT INTO `ord_order` VALUES (55, 'SO20260126162658d47d7c625d', 4009, '老王', '13904856754', '滨海新区', 1, '帮我送', '2026-01-26 16:26:58', 15.00, 0, NULL, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 3, '2026-01-26 16:26:58', '2026-01-27 11:28:26', '2026-01-27 11:28:26');
INSERT INTO `ord_order` VALUES (56, 'DO20260126163455cc0ec66aee', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-26 16:34:55', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-26 16:34:56', '2026-01-26 16:34:56', NULL);
INSERT INTO `ord_order` VALUES (57, 'DO20260126165525335be0e4f1', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-26 16:55:25', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-26 16:55:25', '2026-01-26 16:55:25', NULL);
INSERT INTO `ord_order` VALUES (58, 'DO2026012616552701d1af444e', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-26 16:55:28', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-26 16:55:28', '2026-01-26 16:55:28', NULL);
INSERT INTO `ord_order` VALUES (59, 'SO202601261755205ab068fa8e', 4010, '南迪', '15175837597', '西门', 1, '帮我送', '2026-01-26 17:55:21', 0.10, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-26 17:55:21', '2026-01-27 11:28:23', '2026-01-27 11:28:23');
INSERT INTO `ord_order` VALUES (60, 'SO20260127201132468c6de0ae', 4009, '孙少', '13604894575', '崇德楼101', 1, '帮我送', '2026-01-27 20:11:33', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-27 20:11:33', '2026-01-28 09:00:50', '2026-01-28 09:00:50');
INSERT INTO `ord_order` VALUES (61, 'SO202601272030298313001566', 4009, '张三', '15194847433', '东门', 1, '帮我送', '2026-01-27 20:30:29', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-27 20:30:29', '2026-01-28 09:00:51', '2026-01-28 09:00:51');
INSERT INTO `ord_order` VALUES (62, 'SO20260127205307486b37a890', 4009, '122', '15138983484', '玉华楼', 1, '帮我送', '2026-01-27 20:53:08', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-27 20:53:08', '2026-01-28 09:00:53', '2026-01-28 09:00:53');
INSERT INTO `ord_order` VALUES (63, 'SO20260128092245d3dcaeb359', 4009, '111', '13904595754', '南门11', 1, '帮我送', '2026-01-28 09:22:45', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 09:22:45', '2026-01-28 09:00:38', '2026-01-28 09:00:38');
INSERT INTO `ord_order` VALUES (64, 'SO202601280925215459f39df4', 4008, '11', '13648567555', '哈哈楼', 1, '帮我送', '2026-01-28 09:25:22', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 09:25:22', '2026-01-28 09:00:36', '2026-01-28 09:00:36');
INSERT INTO `ord_order` VALUES (65, 'SO20260128092912d11b1e1c99', 4008, '陈少', '13903846745', '崇德楼202', 1, '帮我送', '2026-01-28 09:29:13', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 09:29:13', '2026-01-28 09:00:40', '2026-01-28 09:00:40');
INSERT INTO `ord_order` VALUES (66, 'DO202601281117537eb6076a9a', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 11:17:54', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.09, 0.02, 1, '2026-01-28 11:17:54', '2026-01-28 11:17:54', NULL);
INSERT INTO `ord_order` VALUES (67, 'DO20260128111941b4551cdd34', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 11:19:41', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.09, 0.02, 1, '2026-01-28 11:19:41', '2026-01-28 11:19:41', NULL);
INSERT INTO `ord_order` VALUES (68, 'SO20260128112441161dbdb01e', 4008, '拉拉链', '13084746443', '师大西门', 1, '帮我送', '2026-01-28 11:24:41', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 11:24:41', '2026-01-28 09:00:34', '2026-01-28 09:00:34');
INSERT INTO `ord_order` VALUES (69, 'SO20260128112655c139351a3f', 4008, '孙少', '13904595843', '2号楼101', 1, '帮我送', '2026-01-28 11:26:55', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 11:26:55', '2026-01-28 09:00:32', '2026-01-28 09:00:32');
INSERT INTO `ord_order` VALUES (70, 'DO20260128113223b58def6d48', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 11:32:23', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.09, 0.02, 1, '2026-01-28 11:32:24', '2026-01-28 11:32:24', NULL);
INSERT INTO `ord_order` VALUES (71, 'DO202601281134572955d40ec0', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 11:34:58', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.09, 0.02, 1, '2026-01-28 11:34:58', '2026-01-28 11:34:58', NULL);
INSERT INTO `ord_order` VALUES (72, 'DO20260128113602fc192da1fe', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 11:36:02', 158.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.26, 0.05, 1, '2026-01-28 11:36:03', '2026-01-28 11:36:03', NULL);
INSERT INTO `ord_order` VALUES (73, 'DO20260128121615127190f308', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 12:16:15', 161.85, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.35, 0.07, 1, '2026-01-28 12:16:16', '2026-01-28 12:16:16', NULL);
INSERT INTO `ord_order` VALUES (74, 'DO20260128122815b3b669956e', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 12:28:15', 161.85, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.35, 0.07, 1, '2026-01-28 12:28:15', '2026-01-28 12:28:15', NULL);
INSERT INTO `ord_order` VALUES (75, 'SO202601281414551d8eba9997', 4009, '张武', '13904595745', '2号楼801', 1, '帮我送', '2026-01-28 14:14:56', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 14:14:56', '2026-01-28 09:00:42', '2026-01-28 09:00:42');
INSERT INTO `ord_order` VALUES (76, 'SO2026012814195544bab67936', 4009, '文档', '13905958555', '2号楼01', 1, '帮我送', '2026-01-28 14:19:55', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 14:19:55', '2026-01-28 09:00:44', '2026-01-28 09:00:44');
INSERT INTO `ord_order` VALUES (77, 'SO202601281438242bcfcc1935', 4008, '老刘', '13990458567', '精技六楼', 1, '帮我送', '2026-01-28 14:38:25', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 14:38:25', '2026-01-28 09:00:46', '2026-01-28 09:00:46');
INSERT INTO `ord_order` VALUES (78, 'SO202601281503209f09f1e3ae', 4009, '1111', '13690568654', '111', 1, '帮我送', '2026-01-28 15:03:21', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 15:03:21', '2026-01-28 09:00:56', '2026-01-28 09:00:56');
INSERT INTO `ord_order` VALUES (79, 'SO202601281555382c1924f5c5', 4008, '陈少', '13848585858', '经济类2号楼', 1, '帮我送', '2026-01-28 15:55:39', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 15:55:39', '2026-01-28 09:00:59', '2026-01-28 09:00:59');
INSERT INTO `ord_order` VALUES (80, 'SO20260128170513648245d5fa', 4008, '10086', '15273734676', '魔仙堡', 1, '帮我送', '2026-01-28 17:05:14', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 17:05:14', '2026-01-28 17:05:14', NULL);
INSERT INTO `ord_order` VALUES (81, 'SO20260128174357d3fe32188f', 4008, '王少', '13689568655', '科技楼C座', 1, '帮我送', '2026-01-28 17:43:58', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 17:43:58', '2026-01-28 17:43:58', NULL);
INSERT INTO `ord_order` VALUES (82, 'SO202601281840128af22d9a96', 4008, '1111', '13905958555', '和平路', 1, '帮我送', '2026-01-28 18:40:13', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 18:40:13', '2026-01-28 18:40:13', NULL);
INSERT INTO `ord_order` VALUES (83, 'DO202601281905460814af8daa', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-28 19:05:47', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.09, 0.02, 1, '2026-01-28 19:05:47', '2026-01-28 19:05:47', NULL);
INSERT INTO `ord_order` VALUES (84, 'SO2026012819164809580bd71a', 4008, '代少', '13648478532', '芳华路', 1, '帮我送', '2026-01-28 19:16:49', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 19:16:49', '2026-01-28 19:16:49', NULL);
INSERT INTO `ord_order` VALUES (85, 'SO20260128202334ea3dbea8ac', 4009, '111', '13909348948', '111', 1, '帮我送', '2026-01-28 20:23:35', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 20:23:35', '2026-01-28 20:23:35', NULL);
INSERT INTO `ord_order` VALUES (86, 'SO20260128203336ddbce7d9a0', 4009, '王昭君', '15137474333', '2号楼101', 1, '帮我送', '2026-01-28 20:33:37', 0.01, 0, 1, NULL, NULL, NULL, 4006, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 20:33:37', '2026-01-28 20:33:37', NULL);
INSERT INTO `ord_order` VALUES (87, 'SO20260128211130b61d444740', 4008, '钟馗', '13604948443', '2号楼010', 1, '帮我送', '2026-01-28 21:11:31', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-28 21:11:31', '2026-01-28 21:11:31', NULL);
INSERT INTO `ord_order` VALUES (88, 'DO20260129192550316eed75db', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-29 19:25:50', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.09, 0.02, 1, '2026-01-29 19:25:50', '2026-01-29 19:25:50', NULL);
INSERT INTO `ord_order` VALUES (89, 'DO2026012919355314f5f172ce', 4006, '诺', '18111111111', '江苏省南京市江宁区河海大学2111', 2, '外卖', '2026-01-29 19:35:53', 151.35, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 118.28, 0.09, 0.02, 1, '2026-01-29 19:35:54', '2026-01-29 19:35:54', NULL);
INSERT INTO `ord_order` VALUES (90, 'DO20260129194415c772cd6b7d', 4006, '诺', '18111111111', '江苏省南京市江宁区盛江花苑2111', 2, '外卖', '2026-01-29 19:44:16', 70.50, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 53.60, 0.09, 0.02, 1, '2026-01-29 19:44:16', '2026-01-29 19:44:16', NULL);
INSERT INTO `ord_order` VALUES (91, 'SO20260129194652186152aa81', 4009, '2344', '13694945674', '3289', 1, '帮我取', '2026-01-29 19:46:52', 0.01, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-29 19:46:52', '2026-01-29 19:46:52', NULL);
INSERT INTO `ord_order` VALUES (92, 'SO20260129195509ef0ccc036c', 4008, '哦哦张', '13905856845', '明镜楼012', 1, '帮我取', '2026-01-29 19:55:10', 0.01, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-29 19:55:10', '2026-01-29 19:55:10', NULL);
INSERT INTO `ord_order` VALUES (93, 'DO202601291959068e21029300', 4006, '诺', '18111111111', '江苏省南京市江宁区盛江花苑2111', 2, '外卖', '2026-01-29 19:59:06', 74.00, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 53.60, 0.18, 0.04, 1, '2026-01-29 19:59:06', '2026-01-29 19:59:06', NULL);
INSERT INTO `ord_order` VALUES (94, 'SO2026012920020916ae68ad61', 4008, '1222', '13676957844', '2123', 1, '帮我取', '2026-01-29 20:02:09', 0.01, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-29 20:02:09', '2026-01-29 20:02:09', NULL);
INSERT INTO `ord_order` VALUES (95, 'SO20260129200759ad6465ff7c', 4008, '王少', '15183490575', '9号楼010', 1, '帮我取', '2026-01-29 20:07:59', 0.01, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-01-29 20:07:59', '2026-01-29 20:07:59', NULL);
INSERT INTO `ord_order` VALUES (96, 'SO20260129201028ec79c43aed', 4006, '王五', '13905958566', '2号楼920', 1, '帮我送', '2026-01-29 20:10:29', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-29 20:10:29', '2026-01-29 20:10:29', NULL);
INSERT INTO `ord_order` VALUES (97, 'DO20260129203626dfe253f95f', 4006, '诺', '18111111111', '江苏省南京市江宁区盛江花苑2111', 2, '外卖', '2026-01-29 20:36:27', 70.50, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 53.60, 0.09, 0.02, 1, '2026-01-29 20:36:27', '2026-01-29 20:36:27', NULL);
INSERT INTO `ord_order` VALUES (98, 'DO2026012920471004667dfe1c', 4006, '诺', '18111111111', '江苏省南京市江宁区盛江花苑2111', 2, '外卖', '2026-01-29 20:47:11', 70.50, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 53.60, 0.09, 0.02, 1, '2026-01-29 20:47:11', '2026-01-29 20:47:11', NULL);
INSERT INTO `ord_order` VALUES (99, 'SO20260129205140e57f372be9', 4006, '哦哦王', '13903837445', '3号楼', 1, '帮我送', '2026-01-29 20:51:40', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-29 20:51:40', '2026-01-29 20:51:40', NULL);
INSERT INTO `ord_order` VALUES (100, 'SO202601310953569be84e6818', 4006, '2', '18331922881', '2', 1, '帮我送', '2026-01-31 09:53:56', 0.01, 0, 1, NULL, NULL, NULL, 4006, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-01-31 09:53:56', '2026-01-31 09:53:56', NULL);
INSERT INTO `ord_order` VALUES (101, 'SO20260131105337d9e4e5f3ca', 4010, '南迪', '15175837597', '师大西门', 1, '帮我送', '2026-01-31 10:53:38', 1.00, 0, 1, NULL, NULL, NULL, 4011, NULL, NULL, NULL, NULL, 0.20, 0.00, 0.00, 2, '2026-01-31 10:53:38', '2026-01-31 10:53:38', NULL);
INSERT INTO `ord_order` VALUES (102, 'SO20260131133518c3891230b6', 4015, 'bear', '16604593485', '河北省保定市容城县', 1, '帮我送', '2026-01-31 13:35:19', 10.00, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2.00, 0.00, 0.00, 1, '2026-01-31 13:35:19', '2026-01-31 13:35:19', NULL);
INSERT INTO `ord_order` VALUES (103, 'SO202602012154108fc1f1d2f1', 4008, '护护肤', '15133665558', '65', 1, '帮我送', '2026-02-01 21:54:11', 0.01, 0, 1, NULL, NULL, NULL, 4009, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 2, '2026-02-01 21:54:11', '2026-02-01 21:54:11', NULL);
INSERT INTO `ord_order` VALUES (104, 'SO20260201221914d197a66a1f', 4009, '1231', '13613132456', '123', 1, '帮我送', '2026-02-01 22:19:15', 0.01, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 0.00, 1, '2026-02-01 22:19:15', '2026-02-01 22:19:15', NULL);
INSERT INTO `ord_order` VALUES (105, 'SO20260201221914d197a66a1g', 4009, 'tbl', '12222222222', '河北师大', 1, '商家外卖', '2026-02-05 11:33:45', 0.01, 0, 1, NULL, NULL, NULL, 4009, 2, NULL, NULL, NULL, 0.00, 0.00, 0.00, NULL, '2026-02-05 03:34:45', '2026-02-05 03:40:59', NULL);

-- ----------------------------
-- Table structure for org_partner
-- ----------------------------
DROP TABLE IF EXISTS `org_partner`;
CREATE TABLE `org_partner`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `org_id` bigint UNSIGNED NOT NULL COMMENT '组织ID（学校ID或商家ID）',
  `org_type` tinyint NOT NULL COMMENT '组织类型：1-学校，2-商家',
  `partner_id` bigint UNSIGNED NOT NULL COMMENT '合伙人ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_org_partner`(`org_id` ASC, `org_type` ASC, `partner_id` ASC) USING BTREE COMMENT '组织-合伙人唯一索引',
  INDEX `idx_org`(`org_id` ASC, `org_type` ASC) USING BTREE COMMENT '组织查询索引',
  INDEX `idx_partner`(`partner_id` ASC) USING BTREE COMMENT '合伙人查询索引',
  INDEX `idx_created`(`created_at` ASC) USING BTREE COMMENT '创建时间索引',
  CONSTRAINT `chk_org_type` CHECK (`org_type` in (1,2))
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '组织-合伙人关联表（支持多对多）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of org_partner
-- ----------------------------

-- ----------------------------
-- Table structure for payment_order
-- ----------------------------
DROP TABLE IF EXISTS `payment_order`;
CREATE TABLE `payment_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '支付单ID',
  `payment_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付单号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `order_type` int NOT NULL COMMENT '订单类型:1-外卖订单 2-跑腿订单',
  `order_count` int NOT NULL DEFAULT 0 COMMENT '订单数量',
  `total_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '支付金额（元）',
  `pay_method` tinyint NOT NULL DEFAULT 1 COMMENT '支付方式:1-微信支付 2-支付宝',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态:0-待支付 1-已支付 2-支付失败',
  `transaction_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信支付交易号',
  `prepay_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信预支付交易会话标识',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '逻辑删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_payment_no`(`payment_no` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_pay_status`(`pay_status` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '支付单表（用于合并支付）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of payment_order
-- ----------------------------

-- ----------------------------
-- Table structure for payment_order_item
-- ----------------------------
DROP TABLE IF EXISTS `payment_order_item`;
CREATE TABLE `payment_order_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `payment_order_id` bigint NOT NULL COMMENT '支付单ID',
  `payment_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付单号',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',
  `order_type` tinyint NOT NULL COMMENT '订单类型：1-外卖订单 2-跑腿订单',
  `order_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '订单金额（元）',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_payment_order_id`(`payment_order_id` ASC) USING BTREE,
  INDEX `idx_payment_no`(`payment_no` ASC) USING BTREE,
  INDEX `idx_order_id_type`(`order_id` ASC, `order_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '支付单订单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of payment_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for rider_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `rider_withdrawal`;
CREATE TABLE `rider_withdrawal`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `withdrawal_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现单号',
  `rider_id` bigint UNSIGNED NOT NULL COMMENT '骑手ID',
  `amount` decimal(10, 2) NOT NULL COMMENT '申请提现金额/元',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际到账金额/元',
  `withdraw_type` tinyint NOT NULL COMMENT '提现方式:1-微信,2-支付宝,3-银行卡',
  `withdraw_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现账号',
  `withdraw_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现人姓名',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审核记录ID（关联audit_record表）',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '打款状态:0-未打款,1-已打款',
  `pay_operator_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '打款操作人ID',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '打款时间',
  `pay_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打款备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `balanceAfter` decimal(10, 2) NULL DEFAULT NULL COMMENT '提现后余额',
  `balanceBefore` decimal(10, 2) NULL DEFAULT NULL COMMENT '提现前余额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_withdrawal_no`(`withdrawal_no` ASC) USING BTREE COMMENT '提现单号唯一索引',
  INDEX `idx_rider`(`rider_id` ASC) USING BTREE COMMENT '骑手索引',
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE COMMENT '审核记录索引',
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE COMMENT '申请时间索引',
  INDEX `idx_pay_status`(`pay_status` ASC) USING BTREE COMMENT '打款状态索引'
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '骑手提现记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rider_withdrawal
-- ----------------------------
INSERT INTO `rider_withdrawal` VALUES (9, 'WD20251216000001', 1001, 164.25, 162.61, 1, 'wxid_167397', '张伟', 101, 1, 1, '2025-11-18 08:42:49', NULL, '2025-11-18 06:42:49', '2025-12-16 14:33:56', NULL, NULL);
INSERT INTO `rider_withdrawal` VALUES (10, 'WD20251216000002', 1002, 341.10, 337.69, 3, '62229958283570208', '李娜', 102, 1, 1, '2025-11-20 08:42:49', '打款成功', '2025-11-20 06:42:49', '2025-12-16 14:33:56', NULL, NULL);
INSERT INTO `rider_withdrawal` VALUES (11, 'WD20251216000003', 1003, 402.02, 398.00, 3, '62226630745888936', '王强', 103, 1, 1, '2025-11-22 08:42:49', NULL, '2025-11-22 06:42:49', '2025-12-16 14:33:56', NULL, NULL);
INSERT INTO `rider_withdrawal` VALUES (12, 'WD20251216000004', 1004, 118.55, 117.36, 3, '62226149181963464', '刘敏', 104, 1, 1, '2025-11-24 08:42:49', '打款成功', '2025-11-24 06:42:49', '2025-12-16 14:33:56', NULL, NULL);
INSERT INTO `rider_withdrawal` VALUES (13, 'WD20251216000005', 1005, 486.19, 481.33, 1, 'wxid_876395', '陈静', 105, 1, 1, '2025-11-26 08:42:49', NULL, '2025-11-26 06:42:49', '2025-12-16 14:33:56', NULL, NULL);
INSERT INTO `rider_withdrawal` VALUES (14, 'WD20251216000006', 1006, 291.29, NULL, 2, '18423440915', '杨帆', 106, 0, NULL, NULL, NULL, '2025-11-28 06:42:49', '2025-12-16 14:33:56', NULL, NULL);
INSERT INTO `rider_withdrawal` VALUES (15, 'WD20251216000007', 1007, 103.01, NULL, 2, '15965892974', '赵磊', 107, 0, NULL, NULL, NULL, '2025-11-30 06:42:49', '2025-12-16 14:33:56', NULL, NULL);
INSERT INTO `rider_withdrawal` VALUES (16, 'WD20251216000008', 1008, 69.96, NULL, 1, 'wxid_547602', '孙梅', 108, 0, NULL, NULL, NULL, '2025-12-02 06:42:49', '2025-12-16 14:33:56', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '组织-合伙人关联表（支持多对多）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of school_partner
-- ----------------------------
INSERT INTO `school_partner` VALUES (59, 10008, 20007, '2025-12-09 14:49:09', '2025-12-09 14:49:09');
INSERT INTO `school_partner` VALUES (68, 10004, 20006, '2025-12-09 17:14:59', '2025-12-09 17:14:59');
INSERT INTO `school_partner` VALUES (76, 10010, 20005, '2025-12-10 10:06:37', '2025-12-10 10:06:37');
INSERT INTO `school_partner` VALUES (79, 10010, 20010, '2025-12-16 09:19:34', '2025-12-16 09:19:34');
INSERT INTO `school_partner` VALUES (80, 10010, 20011, '2025-12-16 09:29:38', '2025-12-16 09:29:38');
INSERT INTO `school_partner` VALUES (89, 10018, 20007, '2025-12-30 09:32:22', '2025-12-30 09:32:22');
INSERT INTO `school_partner` VALUES (90, 10019, 20005, '2025-12-30 09:49:15', '2025-12-30 09:49:15');
INSERT INTO `school_partner` VALUES (91, 10020, 20004, '2025-12-30 09:51:09', '2025-12-30 09:51:09');
INSERT INTO `school_partner` VALUES (100, 10005, 20007, '2026-01-04 16:41:33', '2026-01-04 16:41:33');
INSERT INTO `school_partner` VALUES (102, 10024, 20005, '2026-01-22 14:17:01', '2026-01-22 14:17:01');
INSERT INTO `school_partner` VALUES (104, 10022, 20007, '2026-01-26 11:10:00', '2026-01-26 11:10:00');
INSERT INTO `school_partner` VALUES (105, 10021, 20007, '2026-01-26 18:10:52', '2026-01-26 18:10:52');

-- ----------------------------
-- Table structure for service_category
-- ----------------------------
DROP TABLE IF EXISTS `service_category`;
CREATE TABLE `service_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级ID（0为顶级分类）',
  `level` tinyint NOT NULL DEFAULT 1 COMMENT '分类层级：1-一级分类，2-二级分类',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `sort_order` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序（值越小越靠前显示）',
  `allow_offline_trade` tinyint NOT NULL DEFAULT 0 COMMENT '是否允许线下交易：0-否，1-是',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用 1-启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_level`(`parent_id` ASC, `level` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_sort`(`sort_order` ASC) USING BTREE,
  INDEX `idx_offline`(`allow_offline_trade` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 334 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务分类管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of service_category
-- ----------------------------
INSERT INTO `service_category` VALUES (313, 0, 1, '跑腿服务', 5, 1, 1, '2025-12-10 19:57:58', '2025-12-10 19:57:58');
INSERT INTO `service_category` VALUES (314, 1, 2, '代取快递', 1, 1, 1, '2025-12-10 19:57:58', '2025-12-10 19:57:58');
INSERT INTO `service_category` VALUES (315, 1, 2, '代买饭', 2, 1, 1, '2025-12-10 19:57:59', '2025-12-10 19:57:59');
INSERT INTO `service_category` VALUES (316, 1, 2, '代排队', 3, 1, 1, '2025-12-10 19:57:59', '2025-12-10 19:57:59');
INSERT INTO `service_category` VALUES (317, 1, 2, '代办事', 4, 1, 1, '2025-12-10 19:57:59', '2025-12-10 19:57:59');
INSERT INTO `service_category` VALUES (318, 313, 2, '代取外卖', 1, 0, 0, '2025-12-13 10:04:33', '2025-12-13 10:04:33');
INSERT INTO `service_category` VALUES (319, 0, 1, '服务类', 1, 1, 1, '2025-12-13 10:05:02', '2025-12-13 10:05:02');
INSERT INTO `service_category` VALUES (321, 319, 2, '美团外卖', 1, 0, 1, '2025-12-13 10:10:39', '2025-12-13 10:10:39');
INSERT INTO `service_category` VALUES (322, 319, 2, '代取快递', 1, 1, 1, '2025-12-17 09:17:17', '2025-12-17 09:17:17');
INSERT INTO `service_category` VALUES (323, 313, 2, '吃饭代', 2, 0, 1, '2025-12-17 09:17:44', '2025-12-17 09:17:44');
INSERT INTO `service_category` VALUES (324, 0, 1, '快递类', 1, 1, 1, '2025-12-26 12:04:07', '2025-12-26 12:04:07');
INSERT INTO `service_category` VALUES (325, 324, 2, '送快递', 1, 1, 1, '2025-12-26 12:04:26', '2025-12-26 12:04:26');
INSERT INTO `service_category` VALUES (332, 0, 1, '裤子1', 3, 1, 1, '2026-01-04 17:13:30', '2026-01-04 17:13:30');
INSERT INTO `service_category` VALUES (333, 332, 2, '123', 4, 1, 1, '2026-01-04 17:14:39', '2026-01-04 17:14:39');

-- ----------------------------
-- Table structure for service_commission_config
-- ----------------------------
DROP TABLE IF EXISTS `service_commission_config`;
CREATE TABLE `service_commission_config`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联表服务分类ID',
  `config_type` tinyint NOT NULL DEFAULT 1 COMMENT '配置类型：1-全局默认，2-服务分类',
  `commission_rate` tinyint NOT NULL COMMENT '分佣比例，10代表10%',
  `commission_type` tinyint NOT NULL COMMENT '分佣类型：1-商家分佣，2-服务分佣，3-合伙人分佣',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用 1-启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_category_type`(`category_id` ASC, `commission_type` ASC) USING BTREE,
  INDEX `idx_category`(`category_id` ASC) USING BTREE,
  INDEX `idx_type_status`(`commission_type` ASC, `status` ASC) USING BTREE,
  CONSTRAINT `chk_rate` CHECK ((`commission_rate` >= 0) and (`commission_rate` <= 100))
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务分佣配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of service_commission_config
-- ----------------------------
INSERT INTO `service_commission_config` VALUES (37, 313, 2, 20, 2, 1, '2025-12-10 20:00:00', '2025-12-10 20:00:00', NULL);
INSERT INTO `service_commission_config` VALUES (38, 314, 2, 90, 2, 1, '2025-12-10 20:28:14', '2025-12-10 20:28:14', NULL);
INSERT INTO `service_commission_config` VALUES (39, 315, 2, 90, 2, 1, '2025-12-10 20:29:09', '2025-12-10 20:29:09', NULL);
INSERT INTO `service_commission_config` VALUES (40, 318, 2, 30, 2, 1, '2025-12-17 09:31:49', '2025-12-17 09:31:49', NULL);
INSERT INTO `service_commission_config` VALUES (41, 319, 2, 40, 2, 1, '2025-12-17 09:46:40', '2025-12-17 09:46:40', NULL);
INSERT INTO `service_commission_config` VALUES (42, 321, 2, 50, 2, 1, '2025-12-17 09:46:55', '2025-12-17 09:46:55', NULL);
INSERT INTO `service_commission_config` VALUES (43, NULL, 1, 20, 1, 1, '2026-01-05 09:57:08', '2026-01-05 16:10:30', NULL);
INSERT INTO `service_commission_config` VALUES (44, NULL, 1, 20, 3, 1, '2026-01-05 09:57:20', '2026-01-05 16:10:39', NULL);

-- ----------------------------
-- Table structure for service_order
-- ----------------------------
DROP TABLE IF EXISTS `service_order`;
CREATE TABLE `service_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `service_type` int NULL DEFAULT NULL COMMENT '服务类型：1-帮我送 2-帮我取',
  `category_id` bigint NULL DEFAULT NULL COMMENT '服务分类ID',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务分类名称',
  `pickup_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取货地址',
  `pickup_contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取货联系人',
  `pickup_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取货联系电话',
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '送货地址',
  `delivery_contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '送货联系人',
  `delivery_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '送货联系电话',
  `item_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品描述',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注信息',
  `expected_pickup_time` datetime NULL DEFAULT NULL COMMENT '预计取货时间',
  `expected_delivery_time` datetime NULL DEFAULT NULL COMMENT '预计送达时间',
  `service_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '服务费用（即订单总金额）',
  `order_status` int NULL DEFAULT NULL COMMENT '订单状态:0-已取消  1-待接单 2-待取货 3-配送中 4-已完成',
  `pay_status` int NULL DEFAULT NULL COMMENT '支付状态:0-未支付 1-已支付 2-已退款',
  `pay_method` int NULL DEFAULT NULL COMMENT '支付方式:1-微信支付 2-支付宝 3-银行卡 4-线下',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `staff_id` bigint NULL DEFAULT NULL COMMENT '服务人员ID',
  `staff_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务人员姓名',
  `staff_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务人员电话',
  `cancel_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取消原因',
  `has_reviewed` tinyint(1) NULL DEFAULT 0 COMMENT '是否已评价',
  `version` int NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `order_time` datetime NULL DEFAULT NULL COMMENT '下单时间',
  `accept_time` datetime NULL DEFAULT NULL COMMENT '接单时间',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始服务时间',
  `completed_at` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `transaction_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信支付交易号',
  `refund_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信退款单号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_staff_id`(`staff_id` ASC) USING BTREE,
  INDEX `idx_order_status`(`order_status` ASC) USING BTREE,
  INDEX `idx_pay_status`(`pay_status` ASC) USING BTREE,
  INDEX `idx_created_at`(`created_at` ASC) USING BTREE,
  INDEX `idx_order_time`(`order_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务订单表（跑腿订单）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of service_order
-- ----------------------------
INSERT INTO `service_order` VALUES (1, NULL, 1, 1, NULL, NULL, '沧州', NULL, NULL, '石家庄', NULL, NULL, NULL, NULL, NULL, NULL, 50.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, '2025-12-16 09:29:39', '2025-12-16 17:21:28', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (2, 'SO2026010611591352aabaec36', 1, 2, 314, '代取快递', '某某大学快递站', '李四', '17777777777', '某某社区1号楼-1-101', '张三', '18888888888', '一件快递，不重', '请小心轻放', '2026-01-06 12:29:14', '2026-01-06 12:59:14', 5.00, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-06 11:59:14', NULL, NULL, NULL, '2026-01-06 11:59:14', '2026-01-06 11:59:14', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (3, 'SO20260121153433bcc8d6955c', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五', '13904856343', '张家口职业技术学院', '王五', '13904856343', '帮我送个汉堡', '帮我送个汉堡', '2026-01-21 15:44:34', '2026-01-21 15:44:34', 2.00, 2, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-21 15:34:34', '2026-01-28 11:18:55', NULL, NULL, '2026-01-21 15:34:34', '2026-01-21 15:34:34', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (4, 'SO2026012115490148805a6a73', 4009, 2, 322, '代取快递', '张家口职业技术学院', '王五', '13904856343', '张家口职业技术学院', '王五', '13904856343', '拿一个草莓蛋糕', '拿一个草莓蛋糕', '2026-01-21 15:59:02', '2026-01-21 15:59:02', 2.00, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-21 15:49:02', NULL, NULL, NULL, '2026-01-21 15:49:02', '2026-01-21 15:49:02', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (5, 'SO20260121154932bd7ee00ee4', 4009, 2, 322, '代取快递', '张家口职业技术学院', '王五', '13904856343', '张家口职业技术学院', '王五', '13904856343', '拿一个草莓蛋糕', '拿一个草莓蛋糕', '2026-01-21 15:59:33', '2026-01-21 15:59:33', 2.00, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-21 15:49:33', NULL, NULL, NULL, '2026-01-21 15:49:33', '2026-01-21 15:49:33', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (6, 'SO20260121155814cbb1701b24', 4009, 2, 322, '代取快递', '张家口职业技术学院', '王五', '13904856343', '张家口职业技术学院', '王五', '13904856343', '帮我寄个快递', '帮我寄个快递', '2026-01-21 16:08:14', '2026-01-21 16:08:14', 2.00, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-21 15:58:14', NULL, NULL, NULL, '2026-01-21 15:58:14', '2026-01-21 15:58:14', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (7, 'SO20260121160105700c8a5df3', 4009, 2, 322, '代取快递', '张家口职业技术学院', '王五', '13904856343', '张家口职业技术学院', '王五', '13904856343', '1231', '1231', '2026-01-21 16:11:05', '2026-01-21 16:11:05', 2.00, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-21 16:01:05', NULL, NULL, NULL, '2026-01-21 16:01:05', '2026-01-21 16:01:05', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (8, 'SO202601211607255f54423e3c', 4009, 2, 322, '代取快递', '张家口职业技术学院', '王五', '13904856343', '张家口职业技术学院', '王五', '13904856343', '代取快递', '代取快递', '2026-01-21 16:17:26', '2026-01-21 16:17:26', 2.00, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-21 16:07:26', NULL, NULL, NULL, '2026-01-21 16:07:26', '2026-01-21 16:07:26', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (9, 'SO20260122145312567aa6463b', 4009, 2, 322, '代取快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '王五', '13904856343', '帮我取个无骨鸡爪', '帮我取个无骨鸡爪', '2026-01-22 15:03:13', '2026-01-22 15:03:13', 2.00, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-22 14:53:13', NULL, NULL, NULL, '2026-01-22 14:53:13', '2026-01-22 14:53:13', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (10, 'SO20260122145727095c293fd1', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '王五', '13904856343', '再帮我拿无骨鸡爪', '再帮我拿无骨鸡爪', '2026-01-22 15:27:28', '2026-01-22 15:57:28', 2.00, 2, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-22 14:57:28', '2026-01-28 10:46:09', NULL, NULL, '2026-01-22 14:57:28', '2026-01-22 14:57:28', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (11, 'SO20260122150254a2336bc4dc', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '王五', '13904856343', '2132313', '2132313', '2026-01-22 15:12:55', '2026-01-22 15:12:55', 2.00, 2, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-22 15:02:55', '2026-01-28 10:09:15', NULL, NULL, '2026-01-22 15:02:55', '2026-01-22 15:02:55', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (12, 'SO202601221537430b22596e5a', 4009, 2, 322, '代取快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '王五二', '15193846743', '半个我取个快递', '半个我取个快递', '2026-01-22 15:47:44', '2026-01-22 15:47:44', 2.00, 2, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-22 15:37:44', '2026-01-28 09:47:31', NULL, NULL, '2026-01-22 15:37:44', '2026-01-22 15:37:44', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (13, 'SO2026012217000315fb533c50', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '王五', '13904856343', '帮我取', '帮我取', '2026-01-22 17:10:04', '2026-01-22 17:10:04', 15.00, 3, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 3, '2026-01-22 17:00:04', '2026-01-28 09:47:04', '2026-01-28 09:47:09', '2026-01-28 09:47:13', '2026-01-22 17:00:04', '2026-01-22 17:00:04', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (14, 'SO20260122171549bcfed3fff0', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '王五', '13904856343', '而微软头发', '而微软头发', '2026-01-22 17:45:50', '2026-01-22 18:15:50', 15.00, 3, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 3, '2026-01-22 17:15:50', '2026-01-28 09:45:57', '2026-01-28 09:46:08', '2026-01-28 09:46:16', '2026-01-22 17:15:50', '2026-01-22 17:15:50', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (15, 'SO2026012220290738a1b904b2', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '安其拉', '13904856343', '帮我代取无骨鸡爪', '帮我代取无骨鸡爪', '2026-01-22 20:39:08', '2026-01-22 20:39:08', 15.00, 2, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-22 20:29:08', '2026-01-28 09:41:13', NULL, NULL, '2026-01-22 20:29:08', '2026-01-22 20:29:08', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (16, 'SO2026012220314783f619d2c0', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '安其拉', '13904856343', '帮我买五袋无骨鸡爪', '帮我买五袋无骨鸡爪', '2026-01-22 21:01:48', '2026-01-22 21:31:48', 0.01, 2, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-22 20:31:48', '2026-01-28 09:33:27', NULL, NULL, '2026-01-22 20:31:48', '2026-01-23 11:54:02', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (17, 'SO20260123110626751bcdc892', 4009, 1, 325, '送快递', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '安其拉', '13904856343', '送一个炸鸡', '送一个炸鸡', '2026-01-23 11:16:27', '2026-01-23 11:16:27', 0.01, 3, 1, NULL, '2026-01-24 15:52:11', 4009, NULL, NULL, NULL, 0, 2, '2026-01-23 11:06:27', '2026-01-24 15:52:30', NULL, NULL, '2026-01-23 11:06:27', '2026-01-24 06:57:28', NULL, '4200002966202601245123573697', NULL);
INSERT INTO `service_order` VALUES (18, 'SO20260124120414e8c502713m', 4009, 1, 319, '服务类', '张家口职业技术学院', '王五二', '15193846743', '张家口职业技术学院', '安其拉', '13904856343', '送个外卖', '送个外卖', '2026-01-24 12:14:15', '2026-01-24 12:14:15', 0.01, 5, 1, NULL, '2026-01-24 15:24:54', 4009, NULL, NULL, NULL, 0, 4, '2026-01-24 12:04:15', '2026-01-24 15:47:44', '2026-01-24 15:59:14', '2026-01-25 13:55:31', '2026-01-24 12:04:15', '2026-01-24 07:24:16', NULL, '4200002929202601249006294194', NULL);
INSERT INTO `service_order` VALUES (19, 'SO20260124144618b6b5797013', 4009, 1, 319, '服务类', '213124', '2314123', '13904897533', '2133231', '2312312', '13903494723', '2131', '2131', '2026-01-24 15:16:19', '2026-01-24 15:46:19', 0.01, 2, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-24 14:46:19', '2026-01-28 10:41:57', NULL, NULL, '2026-01-24 14:46:19', '2026-01-24 14:46:19', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (20, 'SO20260126092124ae77f0fe55', 4009, 1, 319, '服务类', '清华大学', '章武', '13609487474', '清华大学', '韩宝', '13904857743', '帮我送个草莓蛋糕', '帮我送个草莓蛋糕', '2026-01-26 09:31:25', '2026-01-26 09:31:25', 0.01, 3, 1, NULL, '2026-01-26 09:21:52', 4009, NULL, NULL, NULL, 0, 2, '2026-01-26 09:21:25', '2026-01-26 09:23:00', NULL, NULL, '2026-01-26 09:21:25', '2026-01-26 09:21:25', NULL, '4200002924202601267324241808', NULL);
INSERT INTO `service_order` VALUES (21, 'SO20260126092947b066158a32', 4009, 1, 319, '服务类', '滨海新区', '张三', '13603489478', '滨海新区', '老王', '13904856754', '帮我取个快递', '帮我取个快递', '2026-01-26 09:39:48', '2026-01-26 09:39:48', 0.01, 5, 1, NULL, '2026-01-26 09:30:58', 4009, NULL, NULL, NULL, 0, 4, '2026-01-26 09:29:48', '2026-01-26 09:31:45', '2026-01-26 09:42:01', '2026-01-26 09:42:26', '2026-01-26 09:29:48', '2026-01-26 09:29:48', NULL, '4200002925202601260989844429', NULL);
INSERT INTO `service_order` VALUES (22, 'SO2026012609464706720cbc70', 4009, 2, 324, '快递类', '滨海新区', '张三', '13603489478', '滨海新区', '老王', '13904856754', '帮我取个无骨鸡爪', '帮我取个无骨鸡爪', '2026-01-26 09:56:47', '2026-01-26 09:56:47', 0.01, 5, 1, NULL, '2026-01-26 09:49:41', 4009, NULL, NULL, NULL, 0, 4, '2026-01-26 09:46:47', '2026-01-26 09:55:30', '2026-01-26 09:55:32', '2026-01-26 09:56:57', '2026-01-26 09:46:47', '2026-01-26 09:46:47', NULL, '4200002937202601260972447631', NULL);
INSERT INTO `service_order` VALUES (23, 'SO20260126102354c96f87ef65', 4009, 1, 319, '服务类', '大城县一中', '老陈', '13903489474', '大城县一中', '老楚', '13904895755', '帮我拿个快递', '帮我拿个快递', '2026-01-26 10:33:54', '2026-01-26 10:33:54', 0.01, 5, 1, NULL, '2026-01-26 10:24:25', 4009, NULL, NULL, NULL, 0, 4, '2026-01-26 10:23:54', '2026-01-26 10:24:42', '2026-01-26 10:24:44', '2026-01-26 10:25:41', '2026-01-26 10:23:54', '2026-01-26 10:23:54', NULL, '4200003010202601263217454764', NULL);
INSERT INTO `service_order` VALUES (24, 'SO202601261058577a134efed4', 4009, 1, 319, '服务类', '大城县一中', '老陈', '13903489474', '大城县一中', '老楚', '13904895755', '啦啦啦啦啦', '啦啦啦啦啦', '2026-01-26 11:08:58', '2026-01-26 11:08:58', 0.01, 5, 1, NULL, '2026-01-26 12:09:45', 4009, NULL, NULL, NULL, 0, 4, '2026-01-26 10:58:58', '2026-01-26 12:09:57', '2026-01-26 12:09:58', '2026-01-26 12:10:05', '2026-01-26 10:58:58', '2026-01-26 10:58:58', NULL, '4200002943202601263890960656', NULL);
INSERT INTO `service_order` VALUES (25, 'SO2026012611052224f9d07759', 4006, 1, 319, '服务类', '河北省沧州市运河区沧州师范学校', '测试1', '18131212121', '沧州师范2号楼', '往往', '16111111111', '啦啦啦啦啦啦', '啦啦啦啦啦啦', '2026-01-26 11:15:23', '2026-01-26 11:28:23', 0.01, 2, 1, NULL, '2026-01-26 11:07:25', NULL, NULL, NULL, NULL, 0, 1, '2026-01-26 11:05:23', NULL, NULL, NULL, '2026-01-26 11:05:23', '2026-01-26 11:05:23', NULL, '4200003008202601263470599467', NULL);
INSERT INTO `service_order` VALUES (26, 'SO20260126111601897a30e3ff', 4006, 2, 324, '快递类', '沧州师范学校', '测试1', '18131212121', '沧州师范学院2号楼', '往往', '16111111111', '带去快递', '带去快递', '2026-01-26 11:26:02', '2026-01-26 11:59:02', 0.01, 5, 1, NULL, '2026-01-26 11:16:39', 4006, NULL, NULL, NULL, 0, 4, '2026-01-26 11:16:02', '2026-01-26 11:16:56', '2026-01-26 11:16:58', '2026-01-26 11:17:04', '2026-01-26 11:16:02', '2026-01-26 11:16:02', NULL, '4200002946202601269125931483', NULL);
INSERT INTO `service_order` VALUES (27, 'SO20260126144335220503b52f', 4009, 1, 319, '服务类', '滨海新区', '张三', '13603489478', '滨海新区', '老王', '13904856754', '帮我取个快递', '帮我取个快递', '2026-01-26 14:53:36', '2026-01-26 14:53:36', 0.01, 5, 1, NULL, '2026-01-26 14:45:07', 4009, NULL, NULL, NULL, 0, 4, '2026-01-26 14:43:36', '2026-01-26 14:45:29', '2026-01-26 14:45:30', '2026-01-26 14:45:36', '2026-01-26 14:43:36', '2026-01-26 14:43:36', NULL, '4200003002202601263903855775', NULL);
INSERT INTO `service_order` VALUES (28, 'SO20260126162658d47d7c625d', 4009, 1, 319, '服务类', '滨海新区', '张三', '13603489478', '滨海新区', '老王', '13904856754', '帮我买无骨鸡爪', '帮我买无骨鸡爪', '2026-01-26 16:36:58', '2026-01-26 16:36:58', 15.00, 3, 0, NULL, NULL, 4009, NULL, NULL, NULL, 0, 6, '2026-01-26 16:26:58', '2026-01-27 20:31:01', '2026-01-27 20:31:03', '2026-01-28 09:08:17', '2026-01-26 16:26:58', '2026-01-26 16:26:58', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (29, 'SO202601261755205ab068fa8e', 4010, 1, 319, '服务类', '西门', '南迪', '15175837597', '西门', '南迪', '15175837597', '帮送西门包裹', '帮送西门包裹', '2026-01-26 18:25:21', '2026-01-26 18:55:21', 0.10, 2, 1, NULL, '2026-01-26 17:57:01', NULL, NULL, NULL, NULL, 0, 1, '2026-01-26 17:55:21', NULL, NULL, NULL, '2026-01-26 17:55:21', '2026-01-26 17:55:21', NULL, '4200002923202601264153380970', NULL);
INSERT INTO `service_order` VALUES (30, 'SO20260127201132468c6de0ae', 4009, 1, 319, '服务类', '西门', '张三', '13904845645', '崇德楼101', '孙少', '13604894575', '哇哈哈', '哇哈哈', '2026-01-27 20:41:33', '2026-01-27 21:11:33', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-27 20:11:33', '2026-01-28 10:41:55', NULL, NULL, '2026-01-27 20:11:33', '2026-01-27 20:11:33', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (31, 'SO202601272030298313001566', 4009, 1, 319, '服务类', '滨海新区西门', '孙少', '13904895755', '东门', '张三', '15194847433', '哇哈哈', '哇哈哈', '2026-01-27 21:00:29', '2026-01-27 21:30:29', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-27 20:30:29', '2026-01-28 10:41:48', NULL, NULL, '2026-01-27 20:30:29', '2026-01-27 20:30:29', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (32, 'SO20260127205307486b37a890', 4009, 1, 319, '服务类', '崇德楼101', '账号', '15133598346', '玉华楼', '122', '15138983484', '哇哈哈', '哇哈哈', '2026-01-27 21:03:08', '2026-01-29 12:38:08', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-27 20:53:08', '2026-01-28 10:41:35', NULL, NULL, '2026-01-27 20:53:08', '2026-01-27 20:53:08', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (33, 'SO20260128092245d3dcaeb359', 4009, 1, 319, '服务类', '西门', '11', '13904958565', '南门11', '111', '13904595754', '送快递', '送快递', '2026-01-28 09:52:45', '2026-01-28 10:22:45', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 09:22:45', '2026-01-28 10:41:15', NULL, NULL, '2026-01-28 09:22:45', '2026-01-28 09:22:45', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (34, 'SO202601280925215459f39df4', 4008, 1, 319, '服务类', '崇德楼111', '账户', '15104948958', '哈哈楼', '11', '13648567555', '哇哈哈', '哇哈哈', '2026-01-28 09:55:22', '2026-01-28 10:25:22', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 09:25:22', '2026-01-28 10:38:32', NULL, NULL, '2026-01-28 09:25:22', '2026-01-28 09:25:22', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (35, 'SO20260128092912d11b1e1c99', 4008, 1, 319, '服务类', '西门2号楼', '孙少', '15190349489', '崇德楼202', '陈少', '13903846745', '21222', '21222', '2026-01-28 09:39:13', '2026-01-28 23:21:13', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 09:29:13', '2026-01-28 10:31:44', NULL, NULL, '2026-01-28 09:29:13', '2026-01-28 09:29:13', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (36, 'SO20260128112441161dbdb01e', 4008, 1, 319, '服务类', '科技楼C座', '张三', '15139394784', '师大西门', '拉拉链', '13084746443', '拿个无骨鸡爪', '拿个无骨鸡爪', '2026-01-28 11:54:41', '2026-01-28 12:24:41', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 11:24:41', '2026-01-28 12:12:17', NULL, NULL, '2026-01-28 11:24:41', '2026-01-28 11:24:41', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (37, 'SO20260128112655c139351a3f', 4008, 1, 319, '服务类', '科技楼A座', '张三', '13904957852', '2号楼101', '孙少', '13904595843', '伴我拿个快递', '伴我拿个快递', '2026-01-28 11:36:55', '2026-01-28 15:39:55', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 11:26:55', '2026-01-28 11:28:18', NULL, NULL, '2026-01-28 11:26:55', '2026-01-28 11:26:55', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (38, 'SO202601281414551d8eba9997', 4009, 1, 319, '服务类', '食堂二楼', '王少', '13904857554', '2号楼801', '张武', '13904595745', '送个肯德基', '送个肯德基', '2026-01-28 14:44:56', '2026-01-28 15:14:56', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 14:14:56', '2026-01-28 14:15:06', NULL, NULL, '2026-01-28 14:14:56', '2026-01-28 14:14:56', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (39, 'SO2026012814195544bab67936', 4009, 1, 319, '服务类', '西门', '11', '13904589575', '2号楼01', '文档', '13905958555', '送个帽子', '送个帽子', '2026-01-28 14:49:55', '2026-01-28 15:19:55', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 14:19:55', '2026-01-28 14:33:08', NULL, NULL, '2026-01-28 14:19:55', '2026-01-28 14:19:55', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (40, 'SO202601281438242bcfcc1935', 4008, 1, 319, '服务类', '繁华楼101', '老张', '13905895754', '精技六楼', '老刘', '13990458567', '帮我拿个草莓蛋糕', '帮我拿个草莓蛋糕', '2026-01-28 15:08:25', '2026-01-28 15:38:25', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-28 14:38:25', '2026-01-28 14:39:37', NULL, NULL, '2026-01-28 14:38:25', '2026-01-28 14:38:25', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (41, 'SO202601281503209f09f1e3az', 4009, 1, 319, '服务类', '呃呃呃', '111', '13905894745', '111', '1111', '13690568654', '212', '212', '2026-01-28 15:33:21', '2026-01-28 16:03:21', 0.01, 3, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 2, '2026-01-28 15:03:21', '2026-01-28 15:51:31', NULL, '2026-01-28 15:51:36', '2026-01-28 15:03:21', '2026-01-28 10:40:12', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (42, 'SO202601281555382c1924f5cz', 4008, 1, 319, '服务类', '芳华路2017', '王少', '13984745655', '经济类2号楼', '陈少', '13848585858', '拿个书桌', '拿个书桌', '2026-01-28 16:05:39', '2026-01-30 06:54:39', 0.01, 3, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 2, '2026-01-28 15:55:39', '2026-01-28 15:56:48', NULL, '2026-01-28 15:56:50', '2026-01-28 15:55:39', '2026-01-28 10:40:16', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (43, 'SO20260128170513648245d5fz', 4008, 1, 319, '服务类', '爱情楼101', '陈少', '15139478554', '魔仙堡', '10086', '15273734676', '取个无怪乎鸡爪', '取个无怪乎鸡爪', '2026-01-28 17:35:14', '2026-01-28 18:05:14', 0.01, 3, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 2, '2026-01-28 17:05:14', '2026-01-28 17:06:50', NULL, '2026-01-28 17:06:51', '2026-01-28 17:05:14', '2026-01-28 10:40:18', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (44, 'SO20260128174357d3fe32188z', 4008, 1, 319, '服务类', '霞姐煎饼', '陈少', '13993845745', '科技楼C座', '王少', '13689568655', '拿个外卖', '拿个外卖', '2026-01-28 18:13:58', '2026-01-28 18:43:58', 0.01, 3, 1, 1, '2026-01-28 18:41:48', 4009, NULL, NULL, NULL, 0, 3, '2026-01-28 17:43:58', '2026-01-28 17:44:38', NULL, '2026-01-28 17:44:39', '2026-01-28 17:43:58', '2026-01-28 10:40:19', NULL, '4200002939202601287180783734', NULL);
INSERT INTO `service_order` VALUES (45, 'SO202601281840128af22d9a96', 4008, 1, 319, '服务类', '芳华路', '1111', '13903484745', '和平路', '1111', '13905958555', '哇哈哈', '哇哈哈', '2026-01-28 19:10:13', '2026-01-28 19:40:13', 0.01, 3, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 2, '2026-01-28 18:40:13', '2026-01-28 19:20:19', NULL, '2026-01-28 19:20:20', '2026-01-28 18:40:13', '2026-01-28 18:40:13', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (46, 'SO2026012819164809580bd71a', 4008, 1, 319, '服务类', '西门', '王少', '13940505551', '芳华路', '代少', '13648478532', '帮我送个无辜夹爪', '帮我送个无辜夹爪', '2026-01-28 19:46:49', '2026-01-28 20:16:49', 0.01, 3, 1, 1, '2026-01-28 19:19:12', 4009, NULL, NULL, NULL, 0, 3, '2026-01-28 19:16:49', '2026-01-28 19:17:41', NULL, '2026-01-28 19:17:42', '2026-01-28 19:16:49', '2026-01-28 19:16:49', NULL, '4200002931202601280577923455', NULL);
INSERT INTO `service_order` VALUES (47, 'SO20260128202334ea3dbea8ac', 4009, 1, 319, '服务类', '11', '11', '13904494223', '111', '111', '13909348948', '1111', '1111', '2026-01-28 20:53:35', '2026-01-28 21:23:35', 0.01, 3, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 2, '2026-01-28 20:23:35', '2026-01-29 19:47:07', NULL, '2026-01-29 19:47:08', '2026-01-28 20:23:35', '2026-01-28 20:23:35', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (48, 'SO20260128203336ddbce7d9a0', 4009, 1, 319, '服务类', '幸福路11111', '肯德基', '13904545834', '2号楼101', '王昭君', '15137474333', '取个无骨鸡爪', '取个无骨鸡爪', '2026-01-28 21:03:37', '2026-01-28 21:33:37', 0.01, 3, 1, 1, '2026-01-28 20:45:29', 4006, NULL, NULL, NULL, 0, 3, '2026-01-28 20:33:37', '2026-01-28 20:36:50', NULL, '2026-01-28 20:36:51', '2026-01-28 20:33:37', '2026-01-28 20:33:37', NULL, '4200003008202601285711480429', NULL);
INSERT INTO `service_order` VALUES (49, 'SO20260128211130b61d444740', 4008, 1, 319, '服务类', '和平路101', '米莱迪', '13904584567', '2号楼010', '钟馗', '13604948443', '送个鸡爪', '送个鸡爪', '2026-01-28 21:41:31', '2026-01-28 22:11:31', 0.01, 3, 1, 1, '2026-01-28 21:13:36', 4009, NULL, NULL, NULL, 0, 3, '2026-01-28 21:11:31', '2026-01-28 21:12:02', NULL, '2026-01-28 21:12:03', '2026-01-28 21:11:31', '2026-01-28 21:11:31', NULL, '4200002929202601289778927995', NULL);
INSERT INTO `service_order` VALUES (50, 'SO20260129194652186152aa81', 4009, 2, 319, '服务类', '3214', '1234', '13689474894', '3289', '2344', '13694945674', '拿个哇哈哈', '拿个哇哈哈', '2026-01-29 20:16:52', '2026-01-29 20:46:52', 0.01, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-29 19:46:52', NULL, NULL, NULL, '2026-01-29 19:46:52', '2026-01-29 19:46:52', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (51, 'SO20260129195509ef0ccc036c', 4008, 2, 319, '服务类', '福明路1010', '张二', '13648584043', '明镜楼012', '哦哦张', '13905856845', '蚕豆', '蚕豆', '2026-01-29 20:25:10', '2026-01-29 20:55:10', 0.01, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-29 19:55:10', NULL, NULL, NULL, '2026-01-29 19:55:10', '2026-01-29 19:55:10', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (52, 'SO2026012920020916ae68ad61', 4008, 2, 319, '服务类', '1', '121', '19048956743', '2123', '1222', '13676957844', '身法', '身法', '2026-01-29 20:32:09', '2026-01-29 21:02:09', 0.01, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-29 20:02:09', NULL, NULL, NULL, '2026-01-29 20:02:09', '2026-01-29 20:02:09', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (53, 'SO20260129200759ad6465ff7c', 4008, 2, 319, '服务类', '2号楼90-1', '孙少', '13904589574', '9号楼010', '王少', '15183490575', '无骨鸡爪', '无骨鸡爪', '2026-01-29 20:37:59', '2026-01-29 21:07:59', 0.01, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-29 20:07:59', NULL, NULL, NULL, '2026-01-29 20:07:59', '2026-01-29 20:07:59', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (54, 'SO20260129201028ec79c43aed', 4006, 1, 319, '服务类', '玉凤路', '张三', '13905485644', '2号楼920', '王五', '13905958566', '娃哈哈', '娃哈哈', '2026-01-29 20:40:29', '2026-01-29 21:10:29', 0.01, 2, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 1, '2026-01-29 20:10:29', '2026-01-29 20:11:51', NULL, NULL, '2026-01-29 20:10:29', '2026-01-29 20:10:29', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (55, 'SO20260129205140e57f372be9', 4006, 1, 319, '服务类', '2号楼1010', '孙少', '13908364759', '3号楼', '哦哦王', '13903837445', '无骨架抓', '无骨架抓', '2026-01-29 21:21:40', '2026-01-29 21:51:40', 0.01, 3, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 2, '2026-01-29 20:51:40', '2026-01-29 20:52:13', NULL, '2026-01-29 20:52:26', '2026-01-29 20:51:40', '2026-01-29 20:51:40', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (56, 'SO202601310953569be84e6818', 4006, 1, 319, '服务类', '1', '1', '18131711389', '2', '2', '18331922881', '111', '111', '2026-01-31 10:23:56', '2026-01-31 10:53:56', 0.01, 3, 1, 1, '2026-01-31 09:55:01', 4006, NULL, NULL, NULL, 0, 3, '2026-01-31 09:53:56', '2026-01-31 09:54:20', NULL, '2026-01-31 09:54:22', '2026-01-31 09:53:56', '2026-01-31 09:53:56', NULL, '4200002932202601316078829600', NULL);
INSERT INTO `service_order` VALUES (57, 'SO20260131105337d9e4e5f3ca', 4010, 1, 322, '代取快递', '师大西门', '南迪', '15175837597', '师大西门', '南迪', '15175837597', '测试服务', '测试服务', '2026-01-31 11:03:38', '2026-01-31 11:03:38', 1.00, 2, 0, 1, NULL, 4011, NULL, NULL, NULL, 0, 1, '2026-01-31 10:53:38', '2026-01-31 10:54:45', NULL, NULL, '2026-01-31 10:53:38', '2026-01-31 10:53:38', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (58, 'SO20260131133518c3891230b6', 4015, 1, 322, '代取快递', '河北省石家庄市桥西区', '明先', '15004359685', '河北省保定市容城县', 'bear', '16604593485', '好东西', '帮忙', '2026-01-31 13:45:19', '2026-02-01 02:30:19', 10.00, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-01-31 13:35:19', NULL, NULL, NULL, '2026-01-31 13:35:19', '2026-01-31 13:35:19', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (59, 'SO202602012154108fc1f1d2f1', 4008, 1, 322, '代取快递', '11', '111', '13903568646', '65', '护护肤', '15133665558', '拒绝', '拒绝', '2026-02-01 22:24:11', '2026-02-01 22:54:11', 0.01, 3, 0, 1, NULL, 4009, NULL, NULL, NULL, 0, 2, '2026-02-01 21:54:11', '2026-02-01 21:54:54', NULL, '2026-02-01 21:54:56', '2026-02-01 21:54:11', '2026-02-01 21:54:11', NULL, NULL, NULL);
INSERT INTO `service_order` VALUES (60, 'SO20260201221914d197a66a1f', 4009, 1, 314, '代取快递', '21', '312', '13908465433', '123', '1231', '13613132456', '无骨鸡爪', '无骨鸡爪', '2026-02-01 22:49:15', '2026-02-01 23:19:15', 0.01, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2026-02-01 22:19:15', NULL, NULL, NULL, '2026-02-01 22:19:15', '2026-02-01 22:19:15', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for statistics_category
-- ----------------------------
DROP TABLE IF EXISTS `statistics_category`;
CREATE TABLE `statistics_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `category_id` bigint UNSIGNED NOT NULL COMMENT '品类ID',
  `total_sales_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '成交金额',
  `total_order_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售订单数',
  `total_payment_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '打款金额',
  `total_transaction` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '流水',
  `statistics_day` date NOT NULL COMMENT '统计日',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_category_day`(`category_id` ASC, `statistics_day` ASC) USING BTREE,
  INDEX `idx_category`(`category_id` ASC) USING BTREE,
  INDEX `idx_created`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '品类销售统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of statistics_category
-- ----------------------------
INSERT INTO `statistics_category` VALUES (1, 1, 5000.00, 100, 4800.00, 5000.00, '2024-01-14', '2024-01-15 01:00:00', '2024-01-15 01:00:00');
INSERT INTO `statistics_category` VALUES (6, 0, 0.00, 0, 0.00, 0.00, '2025-12-25', '2025-12-11 20:33:27', '2025-12-15 10:50:53');
INSERT INTO `statistics_category` VALUES (7, 319, 0.01, 1, 0.01, 0.01, '2026-01-24', '2026-01-24 15:24:54', '2026-01-24 15:24:54');
INSERT INTO `statistics_category` VALUES (8, 325, 0.01, 1, 0.01, 0.01, '2026-01-24', '2026-01-24 15:52:11', '2026-01-24 15:52:11');
INSERT INTO `statistics_category` VALUES (9, 319, 0.16, 7, 0.16, 0.16, '2026-01-26', '2026-01-26 09:21:52', '2026-01-26 17:57:01');
INSERT INTO `statistics_category` VALUES (10, 324, 0.02, 2, 0.02, 0.02, '2026-01-26', '2026-01-26 09:49:41', '2026-01-26 11:16:39');
INSERT INTO `statistics_category` VALUES (11, 1, 3.50, 1, 3.50, 3.50, '2026-01-26', '2026-01-26 16:57:48', '2026-01-26 16:57:48');
INSERT INTO `statistics_category` VALUES (17, 319, 0.04, 4, 0.04, 0.04, '2026-01-28', '2026-01-28 18:41:48', '2026-01-28 21:13:36');
INSERT INTO `statistics_category` VALUES (18, 319, 0.01, 1, 0.01, 0.01, '2026-01-31', '2026-01-31 09:55:01', '2026-01-31 09:55:01');

-- ----------------------------
-- Table structure for statistics_merchant
-- ----------------------------
DROP TABLE IF EXISTS `statistics_merchant`;
CREATE TABLE `statistics_merchant`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `merchant_id` bigint UNSIGNED NOT NULL COMMENT '商家ID',
  `statistics_day` date NOT NULL COMMENT '统计时间',
  `total_sales_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '成交金额/元',
  `total_order_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '成交订单/单',
  `total_payment_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '打款金额',
  `total_transaction` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '总流水',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_merchant_month`(`merchant_id` ASC, `statistics_day` ASC) USING BTREE,
  UNIQUE INDEX `uk_merchant_day`(`merchant_id` ASC, `statistics_day` ASC) USING BTREE,
  INDEX `idx_month`(`statistics_day` ASC) USING BTREE,
  INDEX `idx_merchant`(`merchant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商家流水统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of statistics_merchant
-- ----------------------------
INSERT INTO `statistics_merchant` VALUES (6, 1001, '2025-12-15', 15800.50, 125, 14800.00, 15800.50, '2025-12-15 10:28:33', '2025-12-15 10:28:33');
INSERT INTO `statistics_merchant` VALUES (7, 1002, '2026-01-05', 8600.00, 68, 8200.00, 8600.00, '2025-12-15 10:28:33', '2026-01-05 10:16:57');
INSERT INTO `statistics_merchant` VALUES (8, 1003, '2025-12-15', 22300.80, 185, 21500.00, 22300.80, '2025-12-15 10:28:33', '2025-12-15 10:28:33');
INSERT INTO `statistics_merchant` VALUES (9, 1004, '2025-12-15', 5400.20, 42, 5100.00, 5400.20, '2025-12-15 10:28:33', '2025-12-15 10:28:33');
INSERT INTO `statistics_merchant` VALUES (10, 1005, '2025-12-15', 12700.60, 95, 12000.00, 12700.60, '2025-12-15 10:28:33', '2025-12-15 10:28:33');
INSERT INTO `statistics_merchant` VALUES (11, 1, '2026-01-26', 0.09, 1, 0.09, 0.09, '2026-01-26 16:57:48', '2026-01-26 16:57:48');
INSERT INTO `statistics_merchant` VALUES (12, 1006, '2026-01-30', 8800.40, 72, 8360.00, 8800.40, '2026-02-06 04:59:50', '2026-02-06 04:59:50');
INSERT INTO `statistics_merchant` VALUES (13, 1006, '2026-01-31', 9700.60, 80, 9215.00, 9700.60, '2026-02-06 04:59:50', '2026-02-06 04:59:50');
INSERT INTO `statistics_merchant` VALUES (14, 1006, '2026-02-01', 9200.30, 76, 8740.00, 9200.30, '2026-02-06 04:59:50', '2026-02-06 04:59:50');
INSERT INTO `statistics_merchant` VALUES (15, 1006, '2026-02-02', 10200.80, 85, 9690.00, 10200.80, '2026-02-06 04:59:50', '2026-02-06 04:59:50');
INSERT INTO `statistics_merchant` VALUES (16, 1006, '2026-02-03', 9500.50, 78, 9025.00, 9500.50, '2026-02-06 04:59:50', '2026-02-06 04:59:50');
INSERT INTO `statistics_merchant` VALUES (17, 1006, '2026-02-04', 9800.70, 82, 9310.00, 9800.70, '2026-02-06 04:59:50', '2026-02-06 04:59:50');
INSERT INTO `statistics_merchant` VALUES (18, 1006, '2026-02-05', 10400.00, 88, 9880.00, 10400.00, '2026-02-06 04:59:50', '2026-02-06 04:59:50');
INSERT INTO `statistics_merchant` VALUES (19, 1007, '2026-01-30', 7500.20, 62, 7125.00, 7500.20, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (20, 1007, '2026-01-31', 8200.50, 68, 7790.00, 8200.50, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (21, 1007, '2026-02-01', 7800.30, 65, 7410.00, 7800.30, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (22, 1007, '2026-02-02', 8600.80, 72, 8170.00, 8600.80, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (23, 1007, '2026-02-03', 8000.40, 67, 7600.00, 8000.40, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (24, 1007, '2026-02-04', 8300.60, 70, 7885.00, 8300.60, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (25, 1007, '2026-02-05', 8800.00, 75, 8360.00, 8800.00, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (26, 1008, '2026-01-30', 11200.80, 92, 10640.00, 11200.80, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (27, 1008, '2026-01-31', 12300.60, 102, 11685.00, 12300.60, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (28, 1008, '2026-02-01', 11700.40, 96, 11115.00, 11700.40, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (29, 1008, '2026-02-02', 13000.80, 108, 12350.00, 13000.80, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (30, 1008, '2026-02-03', 12100.50, 100, 11495.00, 12100.50, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (31, 1008, '2026-02-04', 12500.70, 104, 11875.00, 12500.70, '2026-02-06 05:00:56', '2026-02-06 05:00:56');
INSERT INTO `statistics_merchant` VALUES (32, 1008, '2026-02-05', 13300.00, 112, 12635.00, 13300.00, '2026-02-06 05:00:56', '2026-02-06 05:00:56');

-- ----------------------------
-- Table structure for statistics_service
-- ----------------------------
DROP TABLE IF EXISTS `statistics_service`;
CREATE TABLE `statistics_service`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `order_id` bigint NOT NULL COMMENT '订单表关联id',
  `service_staff_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '服务人员ID',
  `partner_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '合伙人ID',
  `partner_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '合伙人姓名',
  `total_sales_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '成交金额',
  `total_order_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单量',
  `total_payment_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '打款金额',
  `statistics_day` date NOT NULL COMMENT '统计日',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_service_order_day`(`order_id` ASC, `statistics_day` ASC) USING BTREE,
  INDEX `idx_staff`(`service_staff_id` ASC) USING BTREE,
  INDEX `idx_partner`(`partner_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of statistics_service
-- ----------------------------
INSERT INTO `statistics_service` VALUES (1, 1001, 201, 101, '李四', 1000.00, 20, 950.00, '2024-01-14', '2024-01-15 01:00:00', '2024-01-15 01:00:00');
INSERT INTO `statistics_service` VALUES (9, 18, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-24', '2026-01-24 15:24:54', '2026-01-24 15:24:54');
INSERT INTO `statistics_service` VALUES (11, 17, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-24', '2026-01-24 15:52:11', '2026-01-24 15:52:11');
INSERT INTO `statistics_service` VALUES (12, 20, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 09:21:52', '2026-01-26 09:21:52');
INSERT INTO `statistics_service` VALUES (13, 21, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 09:30:58', '2026-01-26 09:30:58');
INSERT INTO `statistics_service` VALUES (14, 22, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 09:49:41', '2026-01-26 09:49:41');
INSERT INTO `statistics_service` VALUES (15, 23, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 10:24:25', '2026-01-26 10:24:25');
INSERT INTO `statistics_service` VALUES (16, 25, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 11:07:25', '2026-01-26 11:07:25');
INSERT INTO `statistics_service` VALUES (17, 26, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 11:16:39', '2026-01-26 11:16:39');
INSERT INTO `statistics_service` VALUES (18, 24, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 12:09:45', '2026-01-26 12:09:45');
INSERT INTO `statistics_service` VALUES (19, 27, NULL, NULL, NULL, 0.01, 1, 0.01, '2026-01-26', '2026-01-26 14:45:07', '2026-01-26 14:45:07');
INSERT INTO `statistics_service` VALUES (21, 29, NULL, NULL, NULL, 0.10, 1, 0.10, '2026-01-26', '2026-01-26 17:57:01', '2026-01-26 17:57:01');
INSERT INTO `statistics_service` VALUES (27, 44, 4009, NULL, NULL, 0.01, 1, 0.01, '2026-01-28', '2026-01-28 18:41:48', '2026-01-28 18:41:48');
INSERT INTO `statistics_service` VALUES (28, 46, 4009, NULL, NULL, 0.01, 1, 0.01, '2026-01-28', '2026-01-28 19:19:12', '2026-01-28 19:19:12');
INSERT INTO `statistics_service` VALUES (29, 48, 4006, NULL, NULL, 0.01, 1, 0.01, '2026-01-28', '2026-01-28 20:45:29', '2026-01-28 20:45:29');
INSERT INTO `statistics_service` VALUES (30, 49, 4009, NULL, NULL, 0.01, 1, 0.01, '2026-01-28', '2026-01-28 21:13:36', '2026-01-28 21:13:36');
INSERT INTO `statistics_service` VALUES (31, 56, 4006, NULL, NULL, 0.01, 1, 0.01, '2026-01-31', '2026-01-31 09:55:01', '2026-01-31 09:55:01');

-- ----------------------------
-- Table structure for statistics_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `statistics_transaction_log`;
CREATE TABLE `statistics_transaction_log`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  `transaction_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流水号',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `transaction_type` tinyint(1) NOT NULL COMMENT '交易类型(1-订单收入 2-订单退款 3-商家/合伙人分佣 4-用户提现)',
  `amount` decimal(12, 2) NOT NULL COMMENT '金额',
  `balance_before` decimal(12, 2) NOT NULL COMMENT '交易前余额',
  `balance_after` decimal(12, 2) NOT NULL COMMENT '交易后余额',
  `related_order_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联订单ID',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `statistics_day` date NOT NULL COMMENT '统计日期',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_transaction_no`(`transaction_no` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_type`(`transaction_type` ASC) USING BTREE,
  INDEX `idx_created`(`created_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '平台流水表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of statistics_transaction_log
-- ----------------------------
INSERT INTO `statistics_transaction_log` VALUES (18, 'TRADE_1_1001', 101, 3, 1000.00, 1000.00, 2000.00, 1001, '合伙人李四订单分佣，单量：20', '2024-01-14', '2024-01-15 01:00:00');
INSERT INTO `statistics_transaction_log` VALUES (19, 'PAY_4_PAY1', 26, 3, 1280.50, 1280.50, 2561.00, NULL, '商家打款，支付单号：PAY1，收款账户：90001', '2025-12-19', '2025-12-09 09:26:55');
INSERT INTO `statistics_transaction_log` VALUES (20, 'PAY_5_PAY2', 26, 3, 1280.50, 1280.50, 2561.00, NULL, '商家打款，支付单号：PAY2，收款账户：90001', '2025-12-19', '2025-12-09 09:26:57');
INSERT INTO `statistics_transaction_log` VALUES (21, 'PAY_6_PAY3', 29, 3, 1280.50, 1280.50, 2561.00, NULL, '商家打款，支付单号：PAY3，收款账户：90001', '2025-12-19', '2025-12-09 10:13:41');
INSERT INTO `statistics_transaction_log` VALUES (22, 'PAY_7_PAY4', 29, 3, 1280.50, 1280.50, 2561.00, NULL, '商家打款，支付单号：PAY4，收款账户：90001', '2025-12-19', '2025-12-09 10:13:42');
INSERT INTO `statistics_transaction_log` VALUES (23, 'PAY_8_PAY5', 29, 3, 1280.50, 1280.50, 2561.00, NULL, '商家打款，支付单号：PAY5，收款账户：90001', '2025-12-19', '2025-12-09 10:13:43');
INSERT INTO `statistics_transaction_log` VALUES (24, 'PAY_9_PAY6', 29, 3, 1280.50, 1280.50, 2561.00, NULL, '商家打款，支付单号：PAY6，收款账户：90001', '2025-12-19', '2025-12-09 14:16:55');
INSERT INTO `statistics_transaction_log` VALUES (31, 'WITHDRAW_7_WD202312120001', 1, 4, 100.00, 100.00, 0.00, NULL, '用户张三提现，方式：微信，实际到账：99.00', '2025-12-12', '2025-12-12 09:28:57');
INSERT INTO `statistics_transaction_log` VALUES (32, 'WITHDRAW_8_WD202312120002', 2, 4, 100.00, 100.00, 0.00, NULL, '用户张三提现，方式：微信，实际到账：99.00', '2025-12-12', '2025-12-12 11:50:37');
INSERT INTO `statistics_transaction_log` VALUES (33, 'WITHDRAW_9_WD202312120003', 3, 4, 100.00, 100.00, 0.00, NULL, '用户张三提现，方式：微信，实际到账：100.00', '2025-12-12', '2025-12-12 11:57:31');
INSERT INTO `statistics_transaction_log` VALUES (44, 'TXN202601241524546529', 4009, 1, 0.01, 0.00, 0.00, 18, '服务订单支付成功：SO20260124120414e8c502713m', '2026-01-24', '2026-01-24 15:24:54');
INSERT INTO `statistics_transaction_log` VALUES (46, 'TXN202601241552109379', 4009, 1, 0.01, 0.00, 0.00, 17, '服务订单支付成功：SO20260123110626751bcdc892', '2026-01-24', '2026-01-24 15:52:11');
INSERT INTO `statistics_transaction_log` VALUES (47, 'TXN202601260921521212', 4009, 1, 0.01, 0.00, 0.00, 20, '服务订单支付成功：SO20260126092124ae77f0fe55', '2026-01-26', '2026-01-26 09:21:52');
INSERT INTO `statistics_transaction_log` VALUES (48, 'TXN202601260930589659', 4009, 1, 0.01, 0.00, 0.00, 21, '服务订单支付成功：SO20260126092947b066158a32', '2026-01-26', '2026-01-26 09:30:58');
INSERT INTO `statistics_transaction_log` VALUES (49, 'TXN202601260949415156', 4009, 1, 0.01, 0.00, 0.00, 22, '服务订单支付成功：SO2026012609464706720cbc70', '2026-01-26', '2026-01-26 09:49:41');
INSERT INTO `statistics_transaction_log` VALUES (50, 'TXN202601261024253715', 4009, 1, 0.01, 0.02, 0.02, 23, '服务订单支付成功：SO20260126102354c96f87ef65', '2026-01-26', '2026-01-26 10:24:25');
INSERT INTO `statistics_transaction_log` VALUES (51, 'TXN202601261107249457', 4006, 1, 0.01, 0.00, 0.00, 25, '服务订单支付成功：SO2026012611052224f9d07759', '2026-01-26', '2026-01-26 11:07:25');
INSERT INTO `statistics_transaction_log` VALUES (52, 'TXN202601261116389546', 4006, 1, 0.01, 0.00, 0.00, 26, '服务订单支付成功：SO20260126111601897a30e3ff', '2026-01-26', '2026-01-26 11:16:39');
INSERT INTO `statistics_transaction_log` VALUES (53, 'TXN202601261209444364', 4009, 1, 0.01, 0.02, 0.02, 24, '服务订单支付成功：SO202601261058577a134efed4', '2026-01-26', '2026-01-26 12:09:45');
INSERT INTO `statistics_transaction_log` VALUES (54, 'TL2026012612100595cbd54c', 4009, 3, 0.00, 0.02, 0.02, 24, '服务订单收益：SO202601261058577a134efed4', '2026-01-26', '2026-01-26 12:10:05');
INSERT INTO `statistics_transaction_log` VALUES (55, 'TXN202601261445067809', 4009, 1, 0.01, 0.02, 0.02, 27, '服务订单支付成功：SO20260126144335220503b52f', '2026-01-26', '2026-01-26 14:45:07');
INSERT INTO `statistics_transaction_log` VALUES (56, 'TL202601261445358c09465a', 4009, 3, 0.00, 0.02, 0.02, 27, '服务订单收益：SO20260126144335220503b52f', '2026-01-26', '2026-01-26 14:45:36');
INSERT INTO `statistics_transaction_log` VALUES (58, 'TXN202601261657489237', 4006, 1, 0.01, 0.00, 0.00, 10, '外卖订单支付成功：DO20260126165525335be0e4f1', '2026-01-26', '2026-01-26 16:57:48');
INSERT INTO `statistics_transaction_log` VALUES (59, 'TXN202601261657483002', 1, 3, 0.02, 0.00, 0.00, 10, '外卖订单合伙人分佣：DO20260126165525335be0e4f1', '2026-01-26', '2026-01-26 16:57:48');
INSERT INTO `statistics_transaction_log` VALUES (60, 'TXN202601261757012538', 4010, 1, 0.10, 0.00, 0.00, 29, '服务订单支付成功：SO202601261755205ab068fa8e', '2026-01-26', '2026-01-26 17:57:01');
INSERT INTO `statistics_transaction_log` VALUES (66, 'TXN202601281841473295', 4008, 1, 0.01, 0.00, 0.00, 44, '服务订单支付成功：SO20260128174357d3fe32188z', '2026-01-28', '2026-01-28 18:41:48');
INSERT INTO `statistics_transaction_log` VALUES (67, 'TL20260128184147c1ba2e58', 4009, 3, 0.00, 0.02, 0.02, 44, '服务订单佣金收益：SO20260128174357d3fe32188z', '2026-01-28', '2026-01-28 18:41:48');
INSERT INTO `statistics_transaction_log` VALUES (68, 'TXN202601281919120572', 4008, 1, 0.01, 0.00, 0.00, 46, '服务订单支付成功：SO2026012819164809580bd71a', '2026-01-28', '2026-01-28 19:19:12');
INSERT INTO `statistics_transaction_log` VALUES (69, 'TL202601281919129ce95c8b', 4009, 3, 0.00, 0.02, 0.02, 46, '服务订单佣金收益：SO2026012819164809580bd71a', '2026-01-28', '2026-01-28 19:19:12');
INSERT INTO `statistics_transaction_log` VALUES (70, 'TXN202601282045280585', 4009, 1, 0.01, 0.02, 0.02, 48, '服务订单支付成功：SO20260128203336ddbce7d9a0', '2026-01-28', '2026-01-28 20:45:29');
INSERT INTO `statistics_transaction_log` VALUES (71, 'TL20260128204528b46a410f', 4006, 3, 0.00, 0.00, 0.00, 48, '服务订单佣金收益：SO20260128203336ddbce7d9a0', '2026-01-28', '2026-01-28 20:45:29');
INSERT INTO `statistics_transaction_log` VALUES (72, 'TXN202601282113369489', 4008, 1, 0.01, 0.00, 0.00, 49, '服务订单支付成功：SO20260128211130b61d444740', '2026-01-28', '2026-01-28 21:13:36');
INSERT INTO `statistics_transaction_log` VALUES (73, 'TL20260128211336c557f4bb', 4009, 3, 0.00, 0.02, 0.02, 49, '服务订单佣金收益：SO20260128211130b61d444740', '2026-01-28', '2026-01-28 21:13:36');
INSERT INTO `statistics_transaction_log` VALUES (74, 'TXN202601310955012042', 4006, 1, 0.01, 0.00, 0.00, 56, '服务订单支付成功：SO202601310953569be84e6818', '2026-01-31', '2026-01-31 09:55:01');
INSERT INTO `statistics_transaction_log` VALUES (75, 'TL202601310955015b42897c', 4006, 3, 0.00, 0.00, 0.00, 56, '服务订单佣金收益：SO202601310953569be84e6818', '2026-01-31', '2026-01-31 09:55:01');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分组名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id\r\n\r\n\r\n\r\n',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `dict_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典键',
  `dict_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用,1-启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_key`(`dict_group` ASC, `dict_key` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_group`(`dict_group` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1192 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (66, 'common_status', NULL, '通用状态', '0', '禁用', 0, 1, '2025-12-09 11:09:36');
INSERT INTO `sys_dict` VALUES (67, 'common_status', 66, '通用状态', '1', '启用', 1, 1, '2025-12-09 11:09:36');
INSERT INTO `sys_dict` VALUES (68, 'user_type', NULL, '用户类型', 'NORMAL', '普通用户', 0, 1, '2025-12-09 11:09:36');
INSERT INTO `sys_dict` VALUES (69, 'user_type', 68, '用户类型', 'SERVICE_STAFF', '服务人员', 1, 1, '2025-12-09 11:09:36');
INSERT INTO `sys_dict` VALUES (70, 'withdraw_type', NULL, '提现方式', '1', '微信', 0, 1, '2025-12-09 11:09:36');
INSERT INTO `sys_dict` VALUES (71, 'withdraw_type', 70, '提现方式', '2', '支付宝', 1, 1, '2025-12-09 11:09:36');
INSERT INTO `sys_dict` VALUES (72, 'withdraw_type', 70, '提现方式', '3', '银行卡', 2, 1, '2025-12-09 11:09:36');
INSERT INTO `sys_dict` VALUES (73, 'pay_status', NULL, '打款状态', '0', '未打款', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (74, 'pay_status', 73, '打款状态', '1', '已打款', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (75, 'pay_status', 73, '打款状态', '2', '打款失败', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (76, 'audit_subject_type', NULL, '申请主体类型', 'MERCHANT', '商家', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (77, 'audit_subject_type', 76, '申请主体类型', 'SCHOOL', '学校', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (78, 'audit_biz_type', NULL, '审核业务类型', 'SETTLE_IN', '入驻', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (79, 'audit_biz_type', 78, '审核业务类型', 'WITHDRAW', '提现', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (80, 'audit_biz_type', 78, '审核业务类型', 'GOODS', '商品上架', 2, 0, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (81, 'audit_biz_type', 78, '审核业务类型', 'ACTIVITY', '活动', 3, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (82, 'audit_status', NULL, '审核状态', '0', '待审核', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (83, 'audit_status', 82, '审核状态', '1', '审核通过', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (84, 'audit_status', 82, '审核状态', '2', '审核失败', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (85, 'delivery_rule_type', NULL, '配送费规则类型', 'distance', '距离', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (86, 'delivery_rule_type', 85, '配送费规则类型', 'time', '时间', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (87, 'delivery_time_type', NULL, '配送时段类型', 'daytime', '白天', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (88, 'delivery_time_type', 87, '配送时段类型', 'night', '夜间', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (89, 'activity_status', NULL, '活动状态', '0', '草稿', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (90, 'activity_status', 89, '活动状态', '1', '待审核', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (91, 'activity_status', 89, '活动状态', '2', '已发布', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (92, 'activity_status', 89, '活动状态', '3', '已取消', 3, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (93, 'registration_status', NULL, '活动报名状态', '1', '已报名', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (94, 'registration_status', 93, '活动报名状态', '2', '已取消', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (95, 'registration_status', 93, '活动报名状态', '3', '已签到', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (96, 'is_visible', NULL, '是否显示', '0', '隐藏', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (97, 'is_visible', 96, '是否显示', '1', '显示', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (98, 'comment_status', NULL, '评论状态', '1', '正常', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (99, 'comment_status', 98, '评论状态', '2', '用户删除', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (100, 'comment_status', 98, '评论状态', '3', '管理员删除', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (101, 'category_level', NULL, '分类层级', '1', '一级', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (102, 'category_level', 101, '分类层级', '2', '二级', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (103, 'category_level', 101, '分类层级', '3', '三级', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (104, 'profit_type', NULL, '收益类型', 'RATIO', '按比例', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (105, 'profit_type', 104, '收益类型', 'FIXED', '固定金额', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (106, 'spec_type', NULL, '发布违规内容', 'SINGLE', '统一规格', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (107, 'spec_type', 106, '规格类型', 'MULTIPLE', '多规格', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (108, 'shelf_status', NULL, '上架状态', '0', '未上架', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (109, 'shelf_status', 108, '上架状态', '1', '已上架', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (110, 'order_type', NULL, '订单类型', '1', '服务人员订单', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (111, 'order_type', 110, '订单类型', '2', '商家订单', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (112, 'order_type', 110, '订单类型', '3', '骑手订单', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (113, 'order_pay_status', NULL, '订单支付状态', '0', '未支付', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (114, 'order_pay_status', 113, '订单支付状态', '1', '已支付', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (115, 'order_pay_status', 113, '订单支付状态', '2', '已退款', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (116, 'pay_method', NULL, '支付方式', '1', '微信', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (117, 'pay_method', 116, '支付方式', '2', '支付宝', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (118, 'pay_method', 116, '支付方式', '3', '银行卡', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (119, 'order_status', NULL, '订单状态', '0', '已取消', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (120, 'order_status', 119, '订单状态', '1', '进行中', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (121, 'order_status', 119, '订单状态', '2', '已完成', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (122, 'service_category_level', NULL, '服务分类层级', '1', '一级分类', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (123, 'service_category_level', 122, '服务分类层级', '2', '二级分类', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (124, 'allow_offline_trade', NULL, '是否允许线下交易', '0', '否', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (125, 'allow_offline_trade', 124, '是否允许线下交易', '1', '是', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (126, 'commission_config_type', NULL, '分佣配置类型', '1', '全局默认', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (127, 'commission_config_type', 126, '分佣配置类型', '2', '服务分类', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (128, 'commission_type', NULL, '分佣类型', '1', '商家分佣', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (129, 'commission_type', 128, '分佣类型', '2', '服务分佣', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (130, 'commission_type', 128, '分佣类型', '3', '合伙人分佣', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (131, 'transaction_type', NULL, '交易类型', '1', '订单收入', 0, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (132, 'transaction_type', 131, '交易类型', '2', '订单退款', 1, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (133, 'transaction_type', 131, '交易类型', '3', '商家/合伙人分佣', 2, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (134, 'transaction_type', 131, '交易类型', '4', '用户提现', 3, 1, '2025-12-09 11:09:37');
INSERT INTO `sys_dict` VALUES (135, 'menu_type', NULL, '菜单类型', '0', '目录', 0, 1, '2025-12-09 11:09:38');
INSERT INTO `sys_dict` VALUES (136, 'menu_type', 135, '菜单类型', '1', '菜单', 1, 1, '2025-12-09 11:09:38');
INSERT INTO `sys_dict` VALUES (138, 'is_frame', NULL, '是否外链', '0', '外链', 0, 1, '2025-12-09 11:09:38');
INSERT INTO `sys_dict` VALUES (139, 'is_frame', 138, '是否外链', '1', '站内路由', 1, 1, '2025-12-09 11:09:38');
INSERT INTO `sys_dict` VALUES (153, 'menu_type', 135, '啦啦啦啦啦', '10', '10', 0, 1, '2025-12-10 16:24:55');
INSERT INTO `sys_dict` VALUES (180, 'ORDER_STATUS', 119, '订单状态字典', 'PAID', '已支付', 2, 1, '2025-12-29 10:20:03');
INSERT INTO `sys_dict` VALUES (182, 'ShenHe', NULL, '订单状态字典', '0', '待审核', 2, 1, '2025-12-29 10:22:12');
INSERT INTO `sys_dict` VALUES (183, 'ShenHe', 182, '订单状态字典', '1', '审核通过', 2, 1, '2025-12-29 10:30:53');
INSERT INTO `sys_dict` VALUES (184, 'ShenHe', 182, '订单状态字典', '2', '审核拒绝', 2, 1, '2025-12-29 10:31:10');
INSERT INTO `sys_dict` VALUES (187, '审核组', NULL, '1', '待审核', '0', 0, 0, '2025-12-29 12:26:01');
INSERT INTO `sys_dict` VALUES (188, '审核组', NULL, '1', '待审核1', '0', 0, 0, '2025-12-29 12:26:17');
INSERT INTO `sys_dict` VALUES (192, 'ttt', NULL, 'qw', 'qqq221', 'www11', 0, 0, '2026-01-05 09:43:56');
INSERT INTO `sys_dict` VALUES (193, 'ttt', NULL, 'dssdfsas111', '1', '2', 0, 0, '2026-01-05 10:27:59');
INSERT INTO `sys_dict` VALUES (1191, 'pay_method', 116, '支付方式', '4', '线下', 3, 1, '2026-01-24 07:35:15');

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
  `is_frame` tinyint NULL DEFAULT 1 COMMENT '0:是外链 1:站内路由',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限标识符',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组件路径',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问路径',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-隐藏,1-显示',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 301010102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 0, 'setting', '系统设置', 1, 1, NULL, NULL, '/system', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (2, 0, 0, 'shop', '商家管理', 2, 1, NULL, NULL, '/merchant', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (3, 0, 0, 'order', '订单管理', 3, 1, NULL, NULL, '/order', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (4, 0, 0, 'message', '校园论坛', 4, 1, NULL, NULL, '/forum', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (5, 0, 0, 'appstore', '服务管理', 5, 1, NULL, NULL, '/service', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (6, 0, 0, 'user', '用户管理', 7, 1, NULL, NULL, '/app-user', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (7, 0, 0, 'bar-chart', '统计分析', 8, 1, NULL, NULL, '/statistics', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (8, 0, 0, 'audit', '审核管理', 9, 1, NULL, NULL, '/audit', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (9, 0, 0, 'car', '配送管理', 6, 1, NULL, NULL, '/delivery', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (101, 1, 1, NULL, '用户管理', 1, 1, 'system:user:list', 'system/user/index', '/system/user', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (102, 1, 1, NULL, '角色管理', 2, 1, 'system:role:list', 'system/role/index', '/system/role', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (103, 1, 1, NULL, '菜单管理', 3, 1, 'system:menu:list', 'system/menu/index', '/system/menu', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (104, 1, 1, NULL, '字典管理', 4, 1, 'system:dict:list', 'system/dict/index', '/system/dict', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (105, 1, 1, NULL, '服务分佣配置', 5, 1, 'system:serviceCommission:list', 'system/service-commission/index', '/system/service-commission', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (106, 1, 1, NULL, '学校管理', 6, 1, 'system:school:list', 'system/school/index', '/system/school', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (107, 1, 1, NULL, '合伙人管理', 7, 1, 'system:partner:list', 'system/partner/index', '/system/partner', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (108, 1, 1, NULL, '支付管理', 8, 1, 'system:settlement:list', 'system/settlement/index', '/system/settlement', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (201, 2, 1, NULL, '商家列表', 1, 1, 'merchant:list', 'merchant/list/index', '/merchant/list', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (202, 2, 1, NULL, '商品管理', 2, 1, 'mch:product:list', 'merchant/product/index', '/merchant/product', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (203, 2, 1, NULL, '分类管理', 3, 1, 'merchant:category:list', 'merchant/category/index', '/merchant/category', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (204, 2, 1, NULL, '分佣调控', 4, 1, 'merchant:commission:list', 'merchant/commission/index', '/merchant/commission', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (301, 3, 1, NULL, '服务人员订单', 1, 1, 'order:staff:list', 'order/staff/index', '/order/staff', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (302, 3, 1, NULL, '商家订单', 2, 1, 'order:merchant:list', 'order/merchant/index', '/order/merchant', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (401, 4, 1, NULL, '公告管理', 1, 1, 'forum:announcement:list', 'forum/announcement/index', '/forum/announcement', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (402, 4, 1, NULL, '活动管理', 2, 1, 'forum:activity:list', 'forum/activity/index', '/forum/activity', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (403, 4, 1, NULL, '帖子管理', 3, 1, 'forum:post:list', 'forum/post/index', '/forum/post', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (501, 5, 1, NULL, '服务分类管理', 1, 1, 'service:category:list', 'service/category/index', '/service/category', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (601, 6, 1, NULL, '用户管理', 1, 1, 'app:user:list', 'app-user/user/index', '/app-user/user', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (602, 6, 1, NULL, '提现记录', 2, 1, 'app:user:withdrawal:list', 'app-user/withdrawal/index', '/app-user/withdrawal', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (701, 7, 1, NULL, '交易流水统计', 1, 1, 'statistics:transaction:list', 'statistics/transaction/index', '/statistics/transaction', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (702, 7, 1, NULL, '商家流水统计', 2, 1, 'statistics:merchant:list', 'statistics/merchant/index', '/statistics/merchant', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (703, 7, 1, NULL, '服务人员流水', 3, 1, 'statistics:service:list', 'statistics/service/index', '/statistics/service', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (704, 7, 1, NULL, '分类销售统计', 4, 1, 'statistics:category:list', 'statistics/category/index', '/statistics/category', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (801, 8, 1, NULL, '合伙人审核', 1, 1, 'audit:partner:list', 'audit/partner/index', '/audit/partner', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (802, 8, 1, NULL, '商家入驻审核', 2, 1, 'audit:merchant:list', 'audit/merchant/index', '/audit/merchant', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (803, 8, 1, NULL, '服务人员审核', 3, 1, 'audit:staff:list', 'audit/staff/index', '/audit/staff', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (804, 8, 1, NULL, '骑手审核', 4, 1, 'audit:rider:list', 'audit/rider/list', '/audit/rider', 1, '2026-01-16 13:51:43');
INSERT INTO `sys_menu` VALUES (901, 9, 1, NULL, '骑手信息', 1, 1, 'delivery:rider:list', 'delivery/rider/index', '/delivery/rider', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (902, 9, 1, NULL, '骑手订单', 3, 1, 'delivery:order:list', 'delivery/order/index', '/delivery/order', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (903, 9, 1, NULL, '骑手提现管理', 4, 1, 'delivery:rider-withdrawal:list', 'delivery/rider-withdrawal/index', '/delivery/rider-withdrawal', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (904, 9, 1, NULL, '配送费配置', 2, 1, 'delivery:fee-config:list', 'delivery/fee-config/index', '/delivery/fee-config', 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10104, 101, 2, NULL, '删除用户', 4, 1, 'system:user:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10201, 102, 2, NULL, '新增角色', 1, 1, 'system:role:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10202, 102, 2, NULL, '编辑角色', 2, 1, 'system:role:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10203, 102, 2, NULL, '删除角色', 3, 1, 'system:role:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10301, 103, 2, NULL, '查询菜单', 1, 1, 'system:menu:query', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10302, 103, 2, NULL, '新增菜单', 2, 1, 'system:menu:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10303, 103, 2, NULL, '编辑菜单', 3, 1, 'system:menu:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10304, 103, 2, NULL, '删除菜单', 4, 1, 'system:menu:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10401, 104, 2, NULL, '查询字典', 1, 1, 'system:dict:query', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10402, 104, 2, NULL, '新增字典', 2, 1, 'system:dict:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10403, 104, 2, NULL, '编辑字典', 3, 1, 'system:dict:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10404, 104, 2, NULL, '删除字典', 4, 1, 'system:dict:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10501, 105, 2, NULL, '新增配置', 1, 1, 'system:serviceCommission:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10502, 105, 2, NULL, '编辑配置', 2, 1, 'system:serviceCommission:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10503, 105, 2, NULL, '删除配置', 3, 1, 'system:serviceCommission:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10601, 106, 2, NULL, '查询学校', 1, 1, 'system:school:query', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10602, 106, 2, NULL, '新增学校', 2, 1, 'system:school:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10603, 106, 2, NULL, '编辑学校', 3, 1, 'system:school:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10604, 106, 2, NULL, '删除学校', 4, 1, 'system:school:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10701, 107, 2, NULL, '查询合伙人', 1, 1, 'system:partner:query', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10702, 107, 2, NULL, '新增合伙人', 2, 1, 'system:partner:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10703, 107, 2, NULL, '编辑合伙人', 3, 1, 'system:partner:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10704, 107, 2, NULL, '删除合伙人', 4, 1, 'system:partner:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10705, 107, 2, NULL, '审核通过', 5, 1, 'system:partner:approve', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10706, 107, 2, NULL, '审核拒绝', 6, 1, 'system:partner:reject', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10801, 108, 2, NULL, '查询账户', 1, 1, 'system:settlement:query', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10802, 108, 2, NULL, '新增账户', 2, 1, 'system:settlement:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10803, 108, 2, NULL, '编辑账户', 3, 1, 'system:settlement:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (10804, 108, 2, NULL, '删除账户', 4, 1, 'system:settlement:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20101, 201, 2, NULL, '新增商家', 1, 1, 'merchant:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20102, 201, 2, NULL, '编辑商家', 2, 1, 'merchant:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20103, 201, 2, NULL, '删除商家', 3, 1, 'merchant:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20104, 201, 2, NULL, '重置密码', 4, 1, 'merchant:reset', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20201, 202, 2, NULL, '查询分类', 1, 1, 'merchant:category:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20202, 202, 2, NULL, '新增分类', 2, 1, 'merchant:category:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20203, 202, 2, NULL, '编辑分类', 3, 1, 'merchant:category:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20204, 202, 2, NULL, '删除分类', 4, 1, 'merchant:category:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20301, 203, 2, NULL, '查询商品', 1, 1, 'mch:product:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20302, 203, 2, NULL, '新增商品', 2, 1, 'mch:product:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20303, 203, 2, NULL, '编辑商品', 3, 1, 'mch:product:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20304, 203, 2, NULL, '删除商品', 4, 1, 'mch:product:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20305, 203, 2, NULL, '上下架', 5, 1, 'mch:product:shelf', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20306, 203, 2, NULL, '商品审核', 6, 1, 'mch:product:audit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20401, 204, 2, NULL, '编辑配置', 1, 1, 'merchant:commission:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (20402, 204, 2, NULL, '删除配置', 2, 1, 'merchant:commission:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (30101, 301, 2, NULL, '查询订单', 1, 1, 'order:staff:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (30102, 301, 2, NULL, '删除订单', 2, 1, 'order:staff:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (30201, 302, 2, NULL, '查询订单', 1, 1, 'order:merchant:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (30202, 302, 2, NULL, '删除订单', 2, 1, 'order:merchant:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40101, 401, 2, NULL, '查询公告', 1, 1, 'forum:announcement:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40102, 401, 2, NULL, '新增公告', 2, 1, 'forum:announcement:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40103, 401, 2, NULL, '编辑公告', 3, 1, 'forum:announcement:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40104, 401, 2, NULL, '删除公告', 4, 1, 'forum:announcement:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40201, 402, 2, NULL, '查询活动', 1, 1, 'forum:activity:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40202, 402, 2, NULL, '新增活动', 2, 1, 'forum:activity:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40203, 402, 2, NULL, '编辑活动', 3, 1, 'forum:activity:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40204, 402, 2, NULL, '删除活动', 4, 1, 'forum:activity:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40205, 402, 2, NULL, '审核通过', 5, 1, 'forum:activity:approve', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40206, 402, 2, NULL, '审核拒绝', 6, 1, 'forum:activity:reject', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40301, 403, 2, NULL, '查询帖子', 1, 1, 'forum:post:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40302, 403, 2, NULL, '审核通过', 2, 1, 'forum:post:approve', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40303, 403, 2, NULL, '审核拒绝', 3, 1, 'forum:post:reject', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (40304, 403, 2, NULL, '删除帖子', 4, 1, 'forum:post:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (50101, 501, 2, NULL, '查询分类', 1, 1, 'service:category:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (50102, 501, 2, NULL, '新增分类', 2, 1, 'service:category:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (50103, 501, 2, NULL, '编辑分类', 3, 1, 'service:category:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (50104, 501, 2, NULL, '删除分类', 4, 1, 'service:category:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60101, 601, 2, NULL, '查询骑手', 1, 1, 'delivery:rider:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60102, 601, 2, NULL, '新增骑手', 2, 1, 'delivery:rider:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60103, 601, 2, NULL, '编辑骑手', 3, 1, 'delivery:rider:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60104, 601, 2, NULL, '删除骑手', 4, 1, 'delivery:rider:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60105, 601, 2, NULL, '重置密码', 5, 1, 'delivery:rider:reset', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60201, 602, 2, NULL, '查询配置', 1, 1, 'delivery:fee-config:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60202, 602, 2, NULL, '新增配置', 2, 1, 'delivery:fee-config:add', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60203, 602, 2, NULL, '编辑配置', 3, 1, 'delivery:fee-config:edit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60204, 602, 2, NULL, '删除配置', 4, 1, 'delivery:fee-config:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60205, 602, 2, NULL, '启用停用', 5, 1, 'delivery:fee-config:status', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60301, 603, 2, NULL, '查询订单', 1, 1, 'delivery:order:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60302, 603, 2, NULL, '删除订单', 2, 1, 'delivery:order:delete', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60401, 604, 2, NULL, '审核通过', 1, 1, 'delivery:rider-withdrawal:approve', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (60402, 604, 2, NULL, '审核拒绝', 2, 1, 'delivery:rider-withdrawal:reject', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (70101, 701, 2, NULL, '更新状态', 1, 1, 'app:user:updateStatus', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (70201, 702, 2, NULL, '审核通过', 1, 1, 'app:user:withdrawal:approve', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (70202, 702, 2, NULL, '审核拒绝', 2, 1, 'app:user:withdrawal:reject', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (70203, 702, 2, NULL, '查询提现', 3, 1, 'user:withdrawal:list', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (80201, 802, 2, NULL, '查询详情', 1, 1, 'statistics:merchant:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (80301, 803, 2, NULL, '查询详情', 1, 1, 'statistics:service:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (80401, 804, 2, NULL, '查询详情', 1, 1, 'statistics:category:detail', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (90301, 903, 2, NULL, '审核操作', 1, 1, 'audit:merchant:audit', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (90401, 904, 2, NULL, '审核通过', 1, 1, 'audit:staff:approve', NULL, NULL, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_menu` VALUES (90402, 904, 2, NULL, '审核拒绝', 2, 1, 'audit:staff:reject', NULL, NULL, 1, '2025-12-10 12:31:21');

-- ----------------------------
-- Table structure for sys_merchant
-- ----------------------------
DROP TABLE IF EXISTS `sys_merchant`;
CREATE TABLE `sys_merchant`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `org_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家名称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家邮箱（用于登录）',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家密码（BCrypt加密，默认手机号后6位）',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详细地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营业执照',
  `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营分类ID',
  `payment_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打款账户',
  `partner_id` bigint NULL DEFAULT NULL COMMENT '合伙人ID',
  `minimum_order_amount` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '起送金额',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态:0-禁用 1-启用',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '关联的审核记录表ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `business_start_time` time NULL DEFAULT NULL COMMENT '营业开始时间',
  `business_end_time` time NULL DEFAULT NULL COMMENT '营业结束时间',
  `is_open` tinyint NULL DEFAULT 1 COMMENT '是否营业:0-休息 1-营业',
  `business_license_type` tinyint NULL DEFAULT NULL COMMENT '执照类型：1-企业 2-个体',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_merchant_email`(`email` ASC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC, `id` ASC) USING BTREE,
  INDEX `idx_partner`(`partner_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商家表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_merchant
-- ----------------------------
INSERT INTO `sys_merchant` VALUES (1, '校园美食城', 'food@campus.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '江苏省', '南京市', '江宁区', '中国江苏省南京市江宁区宁芜公路华格酒店', 'https://img1.icswb.com/site_14/2022/06/15/contribution/sub_105/104999/20220615/content_62a9fc2855778_compress1_watermark1.jpg', 'https://example.com/license.jpg', '王经理', '13800001001', 1, '403', '103', 1, 0.01, 1, 201, '2023-12-03 10:30:00', '2026-02-04 10:53:52', NULL, '07:00:00', '20:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (2, '快乐奶茶店', 'milktea@campus.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '江苏省', '南京市', '江宁区', '某某大学商业街A区12号', NULL, NULL, '陈老板', '13800001002', 2, 'null', '104', 1, 8.00, 1, 202, '2023-12-03 11:30:00', '2026-01-06 16:55:55', NULL, '08:00:00', '22:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (3, '书香文具店', 'books@campus.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '江苏省', '南京市', '江宁区', '某某大学商业街B区5号', NULL, NULL, '刘女士', '13800001003', 3, 'null', '105', 2, 5.00, 1, 203, '2023-12-03 14:30:00', '2026-01-06 16:55:58', NULL, '08:30:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (4, '便利超市', 'store@campus.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '江苏省', '南京市', '江宁区', '某某大学宿舍区5栋一楼', NULL, NULL, '赵先生', '13800001004', 4, 'null', '106', 2, 10.00, 1, 204, '2023-12-03 15:30:00', '2026-01-06 16:56:01', NULL, '07:30:00', '23:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (10, '幸福里便利店', 'merchant@campus.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '湖北省', '武汉市', '洪山区', '光谷大道环球港1层', NULL, NULL, '刘店长', '13900139000', 10, 'null', '31', 20005, 0.00, 1, NULL, '2025-12-10 20:37:06', '2026-01-06 20:52:24', '2025-12-11 12:07:41', '00:00:00', '00:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (11, '商家一', 'w111@163.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '北京市', '北京市', '西城区', '北京市北京市西城区', NULL, NULL, NULL, '18111111111', 0, 'null', '32', 20006, 0.00, 1, NULL, '2025-12-11 10:22:01', '2026-01-06 16:56:05', '2025-12-11 12:07:16', '00:00:00', '00:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (12, '传奇麻辣烫', '1322@163.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '天津市', '天津市', '和平区', '', NULL, NULL, NULL, '19111111111', 0, '404', '33', 20007, 0.00, 1, NULL, '2025-12-11 11:11:22', '2026-01-16 16:10:23', '2025-12-23 15:18:11', '00:00:00', '00:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (13, '小伙夫快餐', '123@163.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '河北省', '唐山市', '路北区', '', NULL, NULL, NULL, '18111111112', 0, 'null', '34', 20008, 0.00, 1, NULL, '2025-12-11 12:09:22', '2026-01-08 16:42:58', '2025-12-20 11:08:48', '00:00:00', '00:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (14, '花果山鲜花店', '13@123.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '广东省', '广州市', '天河区', '体育西路 100 号', NULL, NULL, '孙悟空', '13800000000', 0, '426', '35', 20008, 0.00, 0, NULL, '2025-12-11 12:36:38', '2026-01-16 17:23:55', NULL, '00:00:00', '00:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (15, '测试添加', '123@183.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '河北省', '沧州市', '运河区', '', NULL, NULL, NULL, '17111111111', 0, 'null', '36', 20005, 0.00, 1, 1019, '2025-12-11 14:56:20', '2026-01-06 16:56:13', '2025-12-20 10:46:08', '00:00:00', '00:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (17, '小熊奶茶店', 'xiaoxiong@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '北京市', '北京市', '海淀区', '中关村大街1号', NULL, NULL, '张三', '13812345678', 10, 'null', '40', 20004, 20.00, 1, NULL, '2025-12-20 15:39:48', '2026-01-06 16:56:15', '2025-12-23 09:15:11', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (18, '板面', '5236@qq.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '河北省', '衡水市', '安平县', NULL, NULL, NULL, NULL, '15512982628', 0, 'null', '41', 20008, 0.00, 1, NULL, '2025-12-20 16:14:19', '2026-01-06 16:56:18', '2025-12-23 09:11:53', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1002, '美食天地餐厅', 'meishi1002@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '北京市', '北京市', '海淀区', '中关村大街1号', NULL, NULL, '张经理', '13800001005', 10, 'null', NULL, 1001, 20.00, 1, NULL, '2025-12-20 09:00:00', '2026-01-08 16:43:03', NULL, '08:00:00', '22:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1003, '快乐奶茶店', 'kuaile1003@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '上海市', '上海市', '浦东新区', '陆家嘴环路500号', NULL, NULL, '李经理', '13800001006', 20, 'null', NULL, 1002, 15.00, 1, NULL, '2025-12-20 10:00:00', '2026-01-08 16:43:07', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1004, '校园便利店', 'bianli1004@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '广东省', '广州市', '天河区', '五山路华南理工大学', NULL, NULL, '王经理', '13800001010', 30, 'null', NULL, 1001, 10.00, 1, NULL, '2025-12-20 11:00:00', '2026-01-08 16:43:15', NULL, '07:00:00', '23:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1005, '麻辣香锅', 'mala1005@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '四川省', '成都市', '武侯区', '科华北路学府路口', NULL, NULL, '赵经理', '13800001015', 40, 'null', NULL, 1003, 25.00, 0, NULL, '2025-12-20 12:00:00', '2026-01-08 16:43:18', NULL, '10:00:00', '21:30:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1006, '鲜果时光', 'fruit1006@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '浙江省', '杭州市', '西湖区', '浙江大学玉泉校区', NULL, NULL, '刘经理', '13800001016', 50, 'null', NULL, 1002, 18.00, 0, NULL, '2025-12-20 13:00:00', '2026-01-08 16:43:25', NULL, '08:30:00', '20:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1007, '西北面馆', 'mian1007@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '陕西省', '西安市', '雁塔区', '小寨西路西安交大', NULL, NULL, '陈经理', '13800001007', 60, 'null', NULL, 1001, 12.00, 0, NULL, '2025-12-20 14:00:00', '2026-01-06 16:56:30', NULL, '06:30:00', '22:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1008, '烘焙工坊', 'bakery1008@example.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '江苏省', '南京市', '栖霞区', '仙林大道南京大学', NULL, NULL, '周经理', '13800001008', 70, 'null', NULL, 1003, 22.00, 0, NULL, '2025-12-20 15:00:00', '2026-01-06 16:56:33', NULL, '07:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1011, '快餐111', '', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '河北省', '衡水市', '安平县', NULL, NULL, NULL, NULL, '19322575739', 0, 'null', '41', 20008, 0.00, 0, NULL, '2025-12-22 20:09:08', '2026-01-06 16:56:35', '2025-12-23 09:07:45', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1022, '板面222', '6653@qq.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '河北省', '衡水市', '安平县', NULL, NULL, NULL, NULL, '15697692662', 0, 'null', '40', 20010, 0.00, 0, NULL, '2025-12-23 09:10:44', '2026-01-06 16:56:36', '2025-12-23 09:11:50', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1023, '安徽板面', '52145@qq.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '河北省', '衡水市', '安平县', NULL, NULL, NULL, NULL, '19322575739', 0, 'null', '107', 2, 0.00, 1, NULL, '2025-12-29 09:33:30', '2026-01-06 16:56:38', '2026-01-05 09:02:45', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1025, '板面', '5225@qq.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '河北省', '衡水市', '安平县', NULL, NULL, NULL, NULL, '15551242535', 0, 'null', '114', 20007, 0.00, 1, NULL, '2026-01-05 09:03:40', '2026-01-06 16:56:48', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1026, '校园便利店', 'store@example.com', '$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '北京市', '海淀区', '中关村', '中关村大街1号', NULL, NULL, '李四', '13900139000', 1, 'null', '115', 1, 20.00, 1, NULL, '2026-01-09 09:34:05', '2026-01-09 12:29:34', '2026-01-09 09:36:20', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1031, '校园便利店2', 'store2@example.com', '$2a$10$IBfSc1vI4qQ4CKvBEHhUve4YMIS4UJ7RIXJJl6/VdESOL4n1wn94O', '北京市', '海淀区', '中关村', '中关村大街1号', 'http://example.com/avatar.jpg', 'http://example.com/license.jpg', '李四', '13900139000', 1, '[1]', '115', 1, 20.00, 1, NULL, '2026-01-09 12:21:19', '2026-01-09 16:43:11', NULL, '07:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1035, '小馋猫', '123@qq.com', '123456', '河北省', '沧州市', '运河区', '沧州师范学院', 'http://172.16.8.163:8093/uploads/2026-01-24/36739742642f493389c685f7f5310c5d.png', 'http://172.16.8.163:8093/uploads/2026-01-24/e64fb28d29634c72a95c17ed00e8be95.jpg', NULL, '17111111111', 0, '432,426', '开户名1:6111111111111111111', 20006, 0.00, 1, NULL, '2026-01-24 01:27:58', '2026-01-24 01:27:58', NULL, '09:26:00', '21:26:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1036, '麦当劳', '321@qq.com', '123456', '河北省', '沧州市', '运河区', '沧州师范学院', 'http://172.16.8.163:8093/uploads/2026-01-24/a9b9b141b7f441fd81597b5210176f16.jpg', 'http://172.16.8.163:8093/uploads/2026-01-24/154e1adb5b184f5fb1bbd1b90dec84d2.png', NULL, '18111111111', 0, '426,435', 'kaihuim:6111111111111111', 20006, 0.00, 1, NULL, '2026-01-24 01:33:06', '2026-01-24 01:33:06', NULL, '09:31:00', '21:31:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1039, '烤肉拌饭', '3212@qq.com', '$2a$10$xZXd5tNSk152DCkmQrRMLe5gxSJYWZGPrAuCrW5umJGbAQjw21xWK', '河北省', '张家口市', '桥西区', '123', 'http://172.16.8.163:8093/uploads/2026-01-26/7c386993c7ec41e2a6ef591d69bb0b73.jpg', 'http://172.16.8.163:8093/uploads/2026-01-26/bf88beeb7c1d4e95aa96e4db63a20438.png', NULL, '19331688995', 0, '432,426', 'caaa:6222222222222222222', 20008, 0.00, 1, NULL, '2026-01-26 09:45:52', '2026-01-26 09:45:52', NULL, '03:44:00', '22:44:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1040, '喜羊羊', '1233@qq.com', '$2a$10$6vqXAAL5bGMiUL.27FmJiueQq5lP8ApViSGHwFE2gcFyNbB/mQ9D2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987189783', 0, '1', NULL, NULL, 0.00, 1, NULL, '2026-02-03 07:22:22', '2026-02-03 07:22:22', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1041, '沸羊羊', '123456@qq.com', '$2a$10$xuT3Yv.GpsfYbEEXi6.CEujV0H6qZhGTpRdw9ry8sgnDISZJemJEq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987189785', 0, '1', NULL, NULL, 0.00, 1, NULL, '2026-02-03 08:03:31', '2026-02-04 11:05:03', '2026-02-04 11:05:03', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1042, '美羊羊', '3229908350@qq.com', '$2a$10$WhYUFOpAuMdfW6xPaAzXFuBsCs0aYmk9zXUjiSYHmizDBjeMS7wUq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15003246516', 0, '1', NULL, NULL, 0.00, 1, NULL, '2026-02-03 08:37:20', '2026-02-04 08:31:52', '2026-02-04 08:31:52', NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1043, '美羊羊', '32244857@qq.com', '$2a$10$Y1aI7SVmdqoRjfyW5Ze8DuT.NW8WDludR/8xRiaba5P6VqZovviw2', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/c666faaa6d1a45ddaddbdf850d457301.png', NULL, '15003246517', 0, '410,411', NULL, 2, 0.00, 1, 1101, '2026-02-04 14:40:02', '2026-02-04 08:31:49', '2026-02-04 08:31:49', '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1044, 'Merchant697.0262697743811', NULL, '$2a$10$WU.tCu4rlP7NVAP6/cvBoO8f83NA7fSPTcIJmFVHJPxvKlu6x4CIi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987189713', 0, NULL, NULL, NULL, 0.00, 1, NULL, '2026-02-04 10:32:00', '2026-02-04 10:32:00', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1053, 'Merchant586.4088604932415', NULL, '$2a$10$xPRxq0Sp6Yro8.Uw9KoZrOQ9Iw9n990IbaBgYpdsZuqj3Wnyywq7S', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15003246512', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 11:28:16', '2026-02-04 11:28:16', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1054, 'Merchant753.0464672186035', NULL, '$2a$10$7uiYF6Ie/uR.qU0SE1W5iO6LuS4SX47MRtR1yZ5QarzsS0g3.W9ye', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '19322575595', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 11:47:33', '2026-02-04 11:47:33', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1055, '美羊羊店铺', '12345@qq.com', '$2a$10$b37GHNTJG7TuamjloIiiVubInAV80tk/qi9f/NI6XeANb6YNg5ckO', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/e6c4f92ed7f2417d84bd8dfbc1f2d696.png', NULL, '15003246512', 0, '411,412,410', NULL, 20005, 0.00, 0, NULL, '2026-02-04 19:50:51', '2026-02-04 11:55:56', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1056, 'Merchant361.68961691175616', NULL, '$2a$10$kCAITC27EEVK8RCDxXzzp.yok03xyuVYJXH78AOobikHzy5wF7aIe', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15003246510', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 11:56:31', '2026-02-04 11:56:31', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1058, 'Merchant132.2053205615906', NULL, '$2a$10$lJqB/YgAv3UXLqCFAbifheg0sKFVnIWu8icKx1OoJupxvBNfiZENO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '16003246516', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:06:39', '2026-02-04 12:06:39', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1059, 'Merchant19.29485598373426', NULL, '$2a$10$lcml6LJvFP2Laltgdfp8aO4MkWQ7VhGnd.mxrnk7tPOxr4UPjLrNG', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '16003246516', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:06:39', '2026-02-04 12:06:39', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1060, 'Merchant299.8821315387159', NULL, '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '17003246516', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:12:07', '2026-02-04 12:49:05', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1068, '伊丽莎白鼠的店铺', '123654@qq.com', '$2a$10$D7Sz.ER9pAHXrFCkhy8a1eIeZT7tQxWKFcE/gTcRE9Os5ZX7HHxDe', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/b3f7ae0f7cbd439fb2d0b6d5d5fdf0c9.png', NULL, '15987189785', 0, '431,432', NULL, 2, 0.00, 0, 1104, '2026-02-04 20:45:26', '2026-02-04 20:45:26', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1070, 'Merchant443.2043907501733', NULL, '$2a$10$PviH6uT8TDNqVo6Ee1MKTek/U0UiguVV0dDGows1tAfWWdYYzwr.a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '18003246516', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:46:33', '2026-02-04 12:46:33', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1071, 'Merchant689.2233615584069', NULL, '$2a$10$Fu9Hxo6h21H3CpxmQfSSP.vsaqgJnp5uolQG.51uHC58qiRPUAv66', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '18003246511', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:47:19', '2026-02-04 12:47:19', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1072, 'xfl', '123456987@qq.com', '$2a$10$dl1n2oBFmw4WLI55Yz/nxO1D6f/km5o1xv.c1ri7wUiWkMD66uzja', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/649a19f055e54bf3b4bb18e18d62f68d.jpeg', NULL, '18003246511', 0, '407,408', NULL, 1, 0.00, 0, 1105, '2026-02-04 20:49:17', '2026-02-04 20:49:17', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1073, 'Merchant772.1825683223927', NULL, '$2a$10$NMArR71AopvrcL5u4UUPBO205OeAb.S6OvM7l3IRLgDo1Q1cXnLLW', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '11111111112', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:51:21', '2026-02-04 12:51:21', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1074, 'Merchant696.9779259038752', NULL, '$2a$10$iyi.04qX1uIWccnq/ORGi.48J1oFcSl2VZLObZjRSRfG.yNo9CLFu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987189784', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:51:40', '2026-02-04 12:51:40', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1075, 'xxffll', '19813020@qq.com', '$2a$10$ZBywriRZM0ely5nUZ3jVPucPPdNM6kMY5ap5dw2qKMDJOvpjkehf6', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/cf535e9461874674996bcbd5ba57032a.jpeg', NULL, '15987189784', 0, '427,428', NULL, 20005, 0.00, 0, 1106, '2026-02-04 20:52:34', '2026-02-04 20:52:34', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1076, 'Merchant916.5209885088624', NULL, '$2a$10$xgyFC74kGOmnaKB96w59m.0ryKxUmaJ2G6EJvovJ2nbFOIlUXAiFm', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987189780', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-04 12:54:22', '2026-02-04 12:54:22', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1079, 'xxffll', '198130201@qq.com', '$2a$10$CHBY/zer2FM2moFslM0av.dHLWUArd/KqZdhr0bAg5D1cOhV0cT3W', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/cf535e9461874674996bcbd5ba57032a.jpeg', NULL, '15987189784', 0, '427,428', NULL, 20005, 0.00, 0, 1107, '2026-02-04 20:57:18', '2026-02-04 20:57:18', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1082, 'tbltbl', '1234569002@qq.com', '$2a$10$6YZfard1RhzdrLKslrpd4eTo91wCdI8gm9Zwm0CngQD5OCIChihES', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/327762b313bd473d9513175d58056503.png', NULL, '15987189780', 0, '423,417,411', NULL, 20007, 0.00, 0, 1108, '2026-02-04 20:58:32', '2026-02-04 20:58:32', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1083, '小吃', '12345611@qq.com', '$2a$10$wf3S3HvuUw4SfGgnPEx4Z.Z8gSu0Y3PymKP2SB4GtiVoBYBGuPq2a', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-04/a64c13eeb9dc4b8b97d7ec97906125a6.png', NULL, NULL, 0, '410', NULL, 1, 0.00, NULL, 1109, '2026-02-04 20:58:35', '2026-02-04 20:58:35', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1084, 'Merchant669.8407539208329', NULL, '$2a$10$p8bRhee3VpKVOPqtP.3GOOclRJwT5aTfwR0NjRh3lXc1rgCQzEGW.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15003246526', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-05 01:05:34', '2026-02-05 01:05:34', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1085, '美食街', '15003246526@qq.com', '$2a$10$2RW4CAm2rLmJTfed/lXQJuN1wZmtNpLa9xGfC8cpO03P/NpFXcJXS', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-05/a206b291ecfe40b0a6c8198964008375.png', NULL, '15003246526', 0, '411,412,410', NULL, 2, 0.00, 1, 1110, '2026-02-05 09:07:34', '2026-02-05 09:07:34', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1086, '伊丽莎白鼠', '1981302052@163.com', '$2a$10$CaR9MyAGJKnhy3fkkXJK0Om5Aq7BVjPiZZE5x1eRfRanAHhK1/3ya', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-05/b840abbef7c54bff9bc8c42877bc353e.jpeg', NULL, '15987189785', 0, '431,432', NULL, 1, 0.00, 1, 1111, '2026-02-05 09:09:00', '2026-02-05 09:09:00', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1087, 'Merchant902.8613381071563', NULL, '$2a$10$/G/dGdp0wri.ODb2U05mc.5.8N4x6oX3N9RT27wgv8GBo3Ja1HJiW', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15003246533', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-05 01:21:28', '2026-02-05 01:21:28', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1088, 'tblxfl', '15003246533@qq.com', '$2a$10$tCP1Zo/e9wFMijqlxTI5S.x6I64NmRDoApUsd4uvWC9A0f2SgrTU.', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-05/d2740156883749a4bdd7554de98da5cd.png', NULL, '15003246533', 0, '410,411,412', NULL, 20007, 0.00, 0, NULL, '2026-02-05 09:23:12', '2026-02-05 03:57:19', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1089, 'Merchant57.38030963379992', NULL, '$2a$10$oVIv2qxNJNa6iymGlxm3weBXNf/qOGS92kmeLZCXckM/pV9.1AwBq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15003246544', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-05 01:24:53', '2026-02-05 01:24:53', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1090, 'tbltbl123', '15003246543@qq.com', '$2a$10$ig0GFX4WfH3P3fxjcAd1E.hhsgUvfkiiCkKr0KeG2PCudVavIEYiC', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-05/2d5ec638625943bf971d8fd068dbeecb.png', NULL, '15003246543', 0, '411,412,413', NULL, 1, 0.00, 0, 1113, '2026-02-05 09:26:03', '2026-02-05 09:26:03', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1097, 'Merchant843.3341986103057', NULL, '$2a$10$1ch0R7fyrG.efA.JaLFwgeGtfZOe9LCyzSmYeGjGT8JzJr/H.QcPW', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '11111111111', 0, NULL, NULL, NULL, 0.00, NULL, NULL, '2026-02-05 01:41:21', '2026-02-05 01:41:21', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1100, 'Merchant180.36359305352534', NULL, '$2a$10$BZXju0sJtFMT62ZjqvyX5umvu4Oz7y9SiWbh7pnOpn8fWHU6P5CDK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15003246555', 0, NULL, NULL, NULL, 0.00, 1, NULL, '2026-02-05 03:27:19', '2026-02-05 03:27:19', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1101, '盖浇饭1', '15003246551@qq.com', '$2a$10$eHNr9UleCSvXFtfbT1ts0OYGu6BKTrtxa8PCfrIdrerbhq0R1qaXG', NULL, NULL, NULL, NULL, '/uploads/2026-02-05/415346d01faa4fdd827a8429ddff0218.png', '/uploads/2026-02-05/c25022e8dfd8464ab5c1dacf660c92ca.png', NULL, '15003246551', 0, '410,413,417', '1500324655112345678', 1, 0.00, 1, 1118, '2026-02-05 11:36:23', '2026-02-05 11:36:23', NULL, '09:00:00', '19:00:00', 0, NULL);
INSERT INTO `sys_merchant` VALUES (1102, 'Merchant429.427614380228', NULL, '$2a$10$p2ALLSjbgxEOaAFQPHqjiOrihMkJgUtnyMFTZOYEKI94habi.kmZC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987180000', 0, NULL, NULL, NULL, 0.00, 1, NULL, '2026-02-05 08:34:42', '2026-02-05 08:34:42', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1103, '伊丽莎白鼠', '741852963@qq.com', '$2a$10$MqOoSFrc2tHyG8lDmebtae/GGZh0BxHTev4cOLFeaAQOgHJ2xXvbS', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-05/c5af481b5876432ba201dca877bf359d.jpeg', NULL, '15987189785', 0, '410,411,412', '1234567891234567890', 1, 0.00, 0, 1119, '2026-02-05 16:39:08', '2026-02-05 16:39:08', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1104, 'Merchant642.7594369600988', NULL, '$2a$10$0C0g3PMiMlEECZmgru.LZO0DVygQnr9ExO4Q/WYCnDPN58nRohezG', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '13245678900', 0, NULL, NULL, NULL, 0.00, 1, NULL, '2026-02-05 08:46:50', '2026-02-05 08:46:50', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1105, '伊丽莎白鼠11', '13245678900@qq.com', '$2a$10$hJd1lPiH6aheUinPypkdCeY3jubWtrsnjcNtm0IbQ1r/jhHR3hFga', NULL, NULL, NULL, NULL, '/uploads/2026-02-07/90a2e74de7204890939622f8ddd17368.jpeg', '/uploads/2026-02-05/5a069a575bab4f3490c82149ed721684.jpeg', NULL, '13245678911', 0, '404,405,412,413,414', '1234567891234567890', 1, 0.00, 1, 1120, '2026-02-05 16:48:41', '2026-02-05 16:48:41', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1106, 'Merchant577.3586625535722', NULL, '$2a$10$V6UpFMFM.QoSzOimdSqkVOYZkjwm9g2n9VJ6otCutJv99fMuih4Ie', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987185555', 0, NULL, NULL, NULL, 0.00, 1, NULL, '2026-02-06 11:23:17', '2026-02-06 11:23:17', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1107, '柏林饭店', '15987185555@qq.com', '$2a$10$MqsY0bZ8dR9OE..PIrbYeutY7WHVWEcni3QHyIKjDAk3/LuaF.Np.', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-06/dd117c5c101641f3844c7bac38ec8e8b.png', NULL, '15987185555', 0, '410,411', '1234567891234567890', 20004, 0.00, 0, 1121, '2026-02-06 19:25:00', '2026-02-06 19:25:00', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1108, 'Merchant911.1910901559027', NULL, '$2a$10$MRc2NlJRDWE8yd8xdb4cvuboa8vtpyi5l2u0DfOvC.b8eRWAQQSMO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '15987185554', 0, NULL, NULL, NULL, 0.00, 1, NULL, '2026-02-06 11:26:28', '2026-02-06 11:26:28', NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_merchant` VALUES (1109, '柏林二号', '15987185553@qq.com', '$2a$10$jRFmQwQoQ7B/MPmrJUn9XuA2Cu7ZV2QxelUXHv/dtndsefW95phDG', NULL, NULL, NULL, NULL, NULL, '/uploads/2026-02-06/ecb5e1cc572e4e41bb1df3695cabc0e8.png', NULL, '15987185553', 0, '410,411', '1234567891234567890', 20005, 0.00, 1, 1122, '2026-02-06 19:27:40', '2026-02-06 19:27:40', NULL, '09:00:00', '21:00:00', 1, NULL);
INSERT INTO `sys_merchant` VALUES (1110, 'Merchant754.7109701818971', NULL, '$2a$10$46nYhc8v2SEj7VHVNAKmSujyg6pzj9fM3dXO9bJC1O2a8TbtCsoia', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'string', 0, NULL, NULL, NULL, 0.00, 1, NULL, '2026-02-07 10:03:09', '2026-02-07 10:03:09', NULL, NULL, NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_partner
-- ----------------------------
DROP TABLE IF EXISTS `sys_partner`;
CREATE TABLE `sys_partner`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '合伙人ID',
  `partner_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '合伙人姓名',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '合伙人邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `invite_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推广码(邀请合伙人)',
  `invite_code_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推广码相对路径',
  `payment_account_id` bigint UNSIGNED NOT NULL COMMENT '打款账户关联id',
  `commission_rate` decimal(5, 2) NOT NULL DEFAULT 0.00 COMMENT '分佣比例(%)',
  `parent_id` bigint UNSIGNED NULL DEFAULT 0 COMMENT '上级合伙人ID(0代表无上级)',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '关联的审核表id',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:1启用 0禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`invite_code` ASC) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC, `id` ASC) USING BTREE,
  CONSTRAINT `chk_partner_rate` CHECK ((`commission_rate` >= 0) and (`commission_rate` <= 100))
) ENGINE = InnoDB AUTO_INCREMENT = 20012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '合伙人表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_partner
-- ----------------------------
INSERT INTO `sys_partner` VALUES (1, '张伟', 'zhangwei@partner.com', '13900000001', 'INVITE_P001', '/qrcodes/partner/INVITE_P001.png', 101, 5.00, 0, NULL, 1, '2023-12-01 09:00:00', '2023-12-01 09:00:00', NULL);
INSERT INTO `sys_partner` VALUES (2, '李娜', 'lina@partner.com', '13900000002', 'INVITE_P002', '/qrcodes/partner/INVITE_P002.png', 102, 4.50, 0, NULL, 1, '2023-12-01 10:00:00', '2023-12-01 10:00:00', NULL);
INSERT INTO `sys_partner` VALUES (20004, '张三', 'zhangsan1_new@example.com', '13800138001', 'P138001425', 'E:\\school\\group2\\uploads\\QrCode\\P138001425.jpg', 17, 0.00, 20007, 1012, 1, '2025-12-08 11:07:17', '2025-12-08 14:25:51', NULL);
INSERT INTO `sys_partner` VALUES (20005, '张飞', 'zhangsan3_new@example.com', '13800138002', 'P138002114', 'E:\\school\\group2\\uploads\\QrCode\\P138002114.jpg', 18, 0.00, 20006, 1013, 1, '2025-12-08 11:29:31', '2025-12-09 14:56:55', NULL);
INSERT INTO `sys_partner` VALUES (20006, '张有', '', '13800138003', 'P138003730', 'E:\\school\\group2\\uploads\\QrCode\\P138003730.jpg', 19, 0.00, 0, 1014, 1, '2025-12-08 11:36:13', '2025-12-08 11:36:13', NULL);
INSERT INTO `sys_partner` VALUES (20007, '章鱼哥', 'zhangsan_new6@example.com', '13800138004', 'P138004273', 'E:\\school\\group2\\uploads\\QrCode\\P138004273.jpg', 20, 0.00, 20006, 1015, 1, '2025-12-08 11:41:48', '2025-12-08 20:57:24', NULL);
INSERT INTO `sys_partner` VALUES (20008, '用户123', 'zhangsan_new@example.com', '13801138000', 'P138000480', 'E:\\school\\group2\\uploads\\QrCode\\P138000480.jpg', 21, 0.00, 20007, 1016, 1, '2025-12-08 11:44:48', '2025-12-08 14:24:02', NULL);
INSERT INTO `sys_partner` VALUES (20009, '刺客五六七', 'zhangsan11@example.com', '13211111116', 'P111116362', 'E:\\school\\group2\\uploads\\QrCode\\P111116362.jpg', 30, 0.00, 20007, 1017, 1, '2025-12-09 09:36:45', '2025-12-09 11:48:53', '2025-12-09 11:48:53');
INSERT INTO `sys_partner` VALUES (20010, '啦啦啦啦啦啦啦', NULL, '13903234567', 'P234567627', 'E:\\school\\group2\\uploads\\QrCode\\P234567627.jpg', 37, 0.00, 20008, 1041, 1, '2025-12-16 09:19:34', '2025-12-16 09:19:34', NULL);
INSERT INTO `sys_partner` VALUES (20011, '周二', NULL, '13613132345', 'P132345810', 'E:\\school\\group2\\uploads\\QrCode\\P132345810.jpg', 38, 0.00, 20008, 1043, 1, '2025-12-16 09:29:38', '2025-12-16 09:29:38', NULL);

-- ----------------------------
-- Table structure for sys_payment_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_payment_record`;
CREATE TABLE `sys_payment_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `payment_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付单号(PAY开头)',
  `settlement_account_id` bigint UNSIGNED NOT NULL COMMENT '关联的打款账户ID',
  `target_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '默认账户' COMMENT '目标账户类型',
  `payment_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收款账户',
  `payment_amount` decimal(15, 2) NOT NULL COMMENT '打款金额',
  `payment_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '支付日期',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `target_id` bigint NULL DEFAULT NULL COMMENT '打款目标ID',
  `statistics_day` date NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_payment_no`(`payment_no` ASC) USING BTREE,
  INDEX `idx_payment_account`(`settlement_account_id` ASC) USING BTREE,
  INDEX `idx_payment_date`(`payment_date` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '支付流水记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_payment_record
-- ----------------------------
INSERT INTO `sys_payment_record` VALUES (4, 'PAY1', 26, 'MERCHANT', '90001', 1280.50, '2025-12-19 11:16:25', '2025-12-09 09:26:55', NULL, NULL);
INSERT INTO `sys_payment_record` VALUES (5, 'PAY2', 26, 'MERCHANT', '90001', 1280.50, '2025-12-19 11:16:28', '2025-12-09 09:26:57', NULL, NULL);
INSERT INTO `sys_payment_record` VALUES (6, 'PAY3', 29, 'MERCHANT', '90001', 1280.50, '2025-12-19 11:16:31', '2025-12-09 10:13:41', NULL, NULL);
INSERT INTO `sys_payment_record` VALUES (7, 'PAY4', 29, 'MERCHANT', '90001', 1280.50, '2025-12-19 11:16:33', '2025-12-09 10:13:42', NULL, NULL);
INSERT INTO `sys_payment_record` VALUES (8, 'PAY5', 29, 'MERCHANT', '90001', 1280.50, '2025-12-19 11:16:37', '2025-12-09 10:13:43', NULL, NULL);
INSERT INTO `sys_payment_record` VALUES (9, 'PAY6', 29, 'MERCHANT', '90001', 1280.50, '2025-12-19 11:16:39', '2025-12-09 14:16:55', NULL, NULL);
INSERT INTO `sys_payment_record` VALUES (10, 'PAY202602050001', 114, 'MERCHANT', '6222021234567890883', 1500.00, '2026-02-05 12:22:34', '2026-02-05 12:22:34', 1, '2026-02-05');
INSERT INTO `sys_payment_record` VALUES (11, 'PAY202602050002', 114, 'MERCHANT', '6222021234567890883', 2350.50, '2026-02-05 13:22:34', '2026-02-05 13:22:34', 1, '2026-02-05');
INSERT INTO `sys_payment_record` VALUES (12, 'PAY202602040001', 114, 'MERCHANT', '6222021234567890883', 980.75, '2026-02-04 12:22:34', '2026-02-04 12:22:34', 1, '2026-02-04');
INSERT INTO `sys_payment_record` VALUES (13, 'PAY202602030001', 114, 'MERCHANT', '6222021234567890883', 3200.00, '2026-02-03 12:22:34', '2026-02-03 12:22:34', 1, '2026-02-03');

-- ----------------------------
-- Table structure for sys_rider
-- ----------------------------
DROP TABLE IF EXISTS `sys_rider`;
CREATE TABLE `sys_rider`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '骑手表id',
  `merchant_id` bigint NOT NULL COMMENT '商家id（标识骑手属于哪个商家）',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '骑手用户名',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '骑手性别',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '骑手密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '骑手手机号码',
  `emergency_contact_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '紧急联系人姓名',
  `emergency_contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '紧急联系人电话',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `commission_total` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计收益',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '骑手的余额',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` datetime NULL DEFAULT NULL,
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_num` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联动审核表id',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'openId',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `idCardFront` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证正面照片地址',
  `idCardBack` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证反面照片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2006 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_rider
-- ----------------------------
INSERT INTO `sys_rider` VALUES (1, 1, 'rider', '男', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '13899990000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-02-07 07:42:04', '2026-02-07 07:42:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_rider` VALUES (1010, 1, '派大星', '男', 'rider123', '13333333333', '赵六', '13800000099', '广东省', '广州市', '天河区', '体育西路 120 号', 0.00, 0.00, '2025-12-29 19:12:29', '2025-12-29 19:38:23', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_rider` VALUES (1011, 1, '小明骑手', '男', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '13800138000', '张三', '13900139000', '广东省', '广州市', '天河区', 'XX路XX号', 0.00, 0.00, '2025-12-29 19:37:54', '2026-02-09 10:01:00', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL);
INSERT INTO `sys_rider` VALUES (2001, 1, '李四骑手', '男', '123456', '13811111111', '李华', '13322222222', '河北省', '邯郸市', '丛台区', 'XX路XX号', 0.00, 0.00, '2026-01-06 20:54:36', '2026-02-09 09:59:20', NULL, '李明', '130429200303171111', 1025, NULL, '', NULL, NULL);
INSERT INTO `sys_rider` VALUES (2002, 1, 'test_rider_001', '男', '$2a$10$8HYzGk5w8wGqUYeU5t7FguW4fUcGx5eQJzL8V9m0N1O2P3Q4R5S6', '13800138000', '紧急联系人', '13800138001', '北京市', '北京市', '朝阳区', '测试地址123号', 0.00, 0.00, '2026-01-15 10:19:39', '2026-01-15 10:19:39', NULL, '测试骑手', '110101199001011234', 1, 'test_openid_001', 'avatar/test.png', 'idcard/front/test.png', 'idcard/back/test.png');
INSERT INTO `sys_rider` VALUES (2003, 1, '15230752833', '未知', '$2a$10$tKQVUimpJQ.B5PtlYFuQzOkEwn0thAQJrWHceSjYy24/.XqTE11Yu', '15230752833', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-02-09 11:05:00', '2026-02-10 01:58:00', NULL, NULL, NULL, 1123, NULL, NULL, NULL, NULL);
INSERT INTO `sys_rider` VALUES (2004, 1, '15230752834', '未知', '$2a$10$ZetiOxhzJGyq6zB8yTw7Sen9oobKwhM77.MV1NVI9mxgIymHQptcK', '15230752834', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2026-02-09 11:14:57', '2026-02-09 11:14:57', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_rider` VALUES (2005, 1, '15003246510', '男', '$2a$10$We2k8JG0MiwZMqGV9uZAJujfxTEh9/3aMrTPjEKCoDaCunAt5dVSO', '15003246510', '小超', '13000000000', '河北省', '石家庄市', '裕华区', NULL, NULL, NULL, '2026-02-10 01:08:26', '2026-02-10 01:08:26', NULL, NULL, NULL, NULL, NULL, 'http://172.16.8.163:8095/uploads/2026-02-10/48a7a8db1f4d4bb4aeef4a73bf0a826a.png', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限字符',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-停用,1-启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_name`(`role_name` ASC) USING BTREE,
  UNIQUE INDEX `uk_role_code`(`role_code` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'SUPER_ADMIN', 1, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21');
INSERT INTO `sys_role` VALUES (2, '订单管理员', 'ORDER_ADMIN', 2, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21');
INSERT INTO `sys_role` VALUES (3, '财务管理员', 'FINANCE_ADMIN', 3, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21');
INSERT INTO `sys_role` VALUES (4, '商家管理员', 'MERCHANT_ADMIN', 4, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21');
INSERT INTO `sys_role` VALUES (5, '系统管理员', 'SYSTEM_ADMIN', 5, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21');
INSERT INTO `sys_role` VALUES (8, '社区管理员', 'Admin_Test', 0, 1, '2025-12-23 17:14:36', '2025-12-23 17:14:36');
INSERT INTO `sys_role` VALUES (18, 'test', 'TestAdmin', 0, 0, '2026-01-05 09:57:50', '2026-01-06 11:54:00');
INSERT INTO `sys_role` VALUES (19, 'test2', 'test2', 0, 1, '2026-01-05 10:32:49', '2026-01-05 10:32:49');
INSERT INTO `sys_role` VALUES (20, 'Test3', 'Test33', 0, 0, '2026-01-05 11:23:00', '2026-01-05 11:23:00');
INSERT INTO `sys_role` VALUES (21, 'hgh', 'gghgg', 0, 1, '2026-01-06 11:37:43', '2026-01-06 12:05:09');
INSERT INTO `sys_role` VALUES (23, 'Test5', 'super', 0, 1, '2026-01-07 09:47:44', '2026-01-07 09:47:44');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `menu_id` bigint UNSIGNED NOT NULL COMMENT '菜单ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_menu`(`role_id` ASC, `menu_id` ASC) USING BTREE,
  INDEX `idx_menu`(`menu_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2419 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2, 3, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (2, 2, 301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (3, 2, 30101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (4, 2, 30102, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (5, 2, 302, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (6, 2, 30201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (7, 2, 30202, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (8, 2, 6, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (9, 2, 603, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (10, 2, 60301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (11, 2, 60302, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (12, 2, 8, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (13, 2, 801, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (14, 2, 802, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (15, 2, 80201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (16, 2, 803, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (17, 2, 80301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (18, 3, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (19, 3, 108, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (20, 3, 10801, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (21, 3, 10802, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (22, 3, 10803, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (23, 3, 10804, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (24, 3, 6, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (25, 3, 604, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (26, 3, 60401, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (27, 3, 60402, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (28, 3, 7, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (29, 3, 702, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (30, 3, 70201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (31, 3, 70202, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (32, 3, 70203, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (33, 3, 9, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (34, 3, 901, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (35, 3, 8, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (36, 3, 801, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (37, 3, 802, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (38, 3, 80201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (39, 3, 803, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (40, 3, 80301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (41, 3, 804, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (42, 3, 80401, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (43, 4, 2, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (44, 4, 201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (45, 4, 20101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (46, 4, 20102, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (47, 4, 20103, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (48, 4, 20104, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (49, 4, 202, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (50, 4, 20201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (51, 4, 20202, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (52, 4, 20203, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (53, 4, 20204, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (54, 4, 203, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (55, 4, 20301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (56, 4, 20302, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (57, 4, 20303, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (58, 4, 20304, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (59, 4, 20305, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (60, 4, 20306, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (61, 4, 204, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (62, 4, 20401, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (63, 4, 20402, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (64, 4, 9, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (65, 4, 903, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (66, 4, 90301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (67, 4, 8, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (68, 4, 802, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (69, 4, 80201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (70, 4, 804, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (71, 4, 80401, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (72, 5, 1, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (73, 5, 101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (74, 5, 10101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (75, 5, 10102, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (76, 5, 10103, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (77, 5, 10104, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (78, 5, 102, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (79, 5, 10201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (80, 5, 10202, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (81, 5, 10203, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (82, 5, 103, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (83, 5, 10301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (84, 5, 10302, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (85, 5, 10303, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (86, 5, 10304, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (87, 5, 104, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (88, 5, 10401, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (89, 5, 10402, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (90, 5, 10403, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (91, 5, 10404, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (92, 5, 105, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (93, 5, 10501, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (94, 5, 10502, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (95, 5, 10503, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (96, 5, 106, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (97, 5, 10601, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (98, 5, 10602, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (99, 5, 10603, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (100, 5, 10604, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (101, 5, 107, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (102, 5, 10701, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (103, 5, 10702, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (104, 5, 10703, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (105, 5, 10704, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (106, 5, 10705, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (107, 5, 10706, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (108, 5, 108, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (109, 5, 10801, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (110, 5, 10802, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (111, 5, 10803, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (112, 5, 10804, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (113, 5, 5, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (114, 5, 501, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (115, 5, 50101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (116, 5, 50102, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (117, 5, 50103, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (118, 5, 50104, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (119, 5, 6, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (120, 5, 601, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (121, 5, 60101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (122, 5, 60102, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (123, 5, 60103, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (124, 5, 60104, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (125, 5, 60105, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (126, 5, 602, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (127, 5, 60201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (128, 5, 60202, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (129, 5, 60203, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (130, 5, 60204, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (131, 5, 60205, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (132, 5, 4, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (133, 5, 401, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (134, 5, 40101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (135, 5, 40102, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (136, 5, 40103, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (137, 5, 40104, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (138, 5, 402, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (139, 5, 40201, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (140, 5, 40202, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (141, 5, 40203, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (142, 5, 40204, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (143, 5, 40205, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (144, 5, 40206, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (145, 5, 403, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (146, 5, 40301, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (147, 5, 40302, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (148, 5, 40303, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (149, 5, 40304, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (150, 5, 7, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (151, 5, 701, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (152, 5, 70101, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (153, 5, 9, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (154, 5, 901, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (155, 5, 902, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (156, 5, 904, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (157, 5, 90401, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (158, 5, 90402, '2025-12-10 12:31:21');
INSERT INTO `sys_role_menu` VALUES (160, 8, 402, '2025-12-23 17:14:36');
INSERT INTO `sys_role_menu` VALUES (161, 17, 602, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (162, 17, 801, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (163, 17, 103, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (164, 17, 102, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (165, 17, 904, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (166, 17, 803, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (167, 17, 702, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (168, 17, 302, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (169, 17, 401, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (170, 17, 201, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (171, 17, 301, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (172, 17, 108, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (173, 17, 107, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (174, 17, 104, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (175, 17, 203, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (176, 17, 402, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (177, 17, 101, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (178, 17, 902, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (179, 17, 403, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (180, 17, 903, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (181, 17, 802, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (182, 17, 701, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (183, 17, 501, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (184, 17, 604, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (185, 17, 204, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (186, 17, 804, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (187, 17, 202, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (188, 17, 603, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (189, 17, 105, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (190, 17, 106, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (191, 17, 601, '2026-01-04 16:14:30');
INSERT INTO `sys_role_menu` VALUES (223, 19, 602, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (224, 19, 801, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (225, 19, 103, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (226, 19, 102, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (227, 19, 904, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (228, 19, 803, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (229, 19, 702, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (230, 19, 302, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (231, 19, 401, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (232, 19, 201, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (233, 19, 301, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (234, 19, 108, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (235, 19, 107, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (236, 19, 104, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (237, 19, 203, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (238, 19, 402, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (239, 19, 101, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (240, 19, 902, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (241, 19, 403, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (242, 19, 903, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (243, 19, 802, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (244, 19, 701, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (245, 19, 501, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (246, 19, 604, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (247, 19, 204, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (248, 19, 804, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (249, 19, 202, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (250, 19, 603, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (251, 19, 105, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (252, 19, 106, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (253, 19, 601, '2026-01-05 10:32:49');
INSERT INTO `sys_role_menu` VALUES (254, 20, 602, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (255, 20, 801, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (256, 20, 103, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (257, 20, 102, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (258, 20, 904, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (259, 20, 803, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (260, 20, 702, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (261, 20, 302, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (262, 20, 401, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (263, 20, 201, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (264, 20, 301, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (265, 20, 108, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (266, 20, 107, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (267, 20, 104, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (268, 20, 203, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (269, 20, 402, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (270, 20, 101, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (271, 20, 902, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (272, 20, 403, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (273, 20, 903, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (274, 20, 802, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (275, 20, 701, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (276, 20, 501, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (277, 20, 604, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (278, 20, 204, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (279, 20, 804, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (280, 20, 202, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (281, 20, 603, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (282, 20, 105, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (283, 20, 106, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (284, 20, 601, '2026-01-05 11:23:01');
INSERT INTO `sys_role_menu` VALUES (502, 18, 602, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (503, 18, 801, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (504, 18, 103, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (505, 18, 102, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (506, 18, 904, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (507, 18, 803, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (508, 18, 702, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (509, 18, 302, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (510, 18, 401, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (511, 18, 201, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (512, 18, 301, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (513, 18, 108, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (514, 18, 107, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (515, 18, 104, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (516, 18, 203, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (517, 18, 402, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (518, 18, 101, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (519, 18, 902, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (520, 18, 403, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (521, 18, 903, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (522, 18, 802, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (523, 18, 701, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (524, 18, 501, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (525, 18, 604, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (526, 18, 204, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (527, 18, 804, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (528, 18, 202, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (529, 18, 603, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (530, 18, 105, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (531, 18, 106, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (532, 18, 601, '2026-01-06 11:54:01');
INSERT INTO `sys_role_menu` VALUES (2250, 21, 20102, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2251, 21, 801, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2252, 21, 20301, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2253, 21, 10301, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2254, 21, 60204, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2255, 21, 60301, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2256, 21, 90401, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2257, 21, 10403, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2258, 21, 10101, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2259, 21, 40101, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2260, 21, 20302, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2261, 21, 10604, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2262, 21, 60202, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2263, 21, 10502, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2264, 21, 10801, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2265, 21, 20101, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2266, 21, 10203, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2267, 21, 1001, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2268, 21, 70101, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2269, 21, 60205, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2270, 21, 80401, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2271, 21, 10401, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2272, 21, 20103, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2273, 21, 50104, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2274, 21, 10601, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2275, 21, 60105, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2276, 21, 20306, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2277, 21, 80201, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2278, 21, 10704, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2279, 21, 20201, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2280, 21, 40202, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2281, 21, 70203, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2282, 21, 50101, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2283, 21, 10302, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2284, 21, 60401, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2285, 21, 70202, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2286, 21, 10103, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2287, 21, 10202, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2288, 21, 20402, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2289, 21, 60102, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2290, 21, 10201, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2291, 21, 10402, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2292, 21, 10706, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2293, 21, 20304, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2294, 21, 30101, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2295, 21, 20305, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2296, 21, 10501, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2297, 21, 10701, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2298, 21, 20204, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2299, 21, 40104, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2300, 21, 10602, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2301, 21, 10702, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2302, 21, 40201, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2303, 21, 90402, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2304, 21, 10802, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2305, 21, 40103, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2306, 21, 60402, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2307, 21, 30102, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2308, 21, 10503, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2309, 21, 60103, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2310, 21, 40206, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2311, 21, 10102, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2312, 21, 20203, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2313, 21, 40205, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2314, 21, 30202, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2315, 21, 90301, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2316, 21, 60104, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2317, 21, 50102, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2318, 21, 10803, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2319, 21, 40304, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2320, 21, 40204, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2321, 21, 60201, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2322, 21, 40203, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2323, 21, 60203, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2324, 21, 50103, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2325, 21, 40102, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2326, 21, 20401, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2327, 21, 20303, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2328, 21, 60101, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2329, 21, 10104, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2330, 21, 40303, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2331, 21, 20202, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2332, 21, 10703, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2333, 21, 10304, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2334, 21, 10705, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2335, 21, 40301, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2336, 21, 60302, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2337, 21, 80301, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2338, 21, 10603, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2339, 21, 902, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2340, 21, 10804, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2341, 21, 20104, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2342, 21, 40302, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2343, 21, 70201, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2344, 21, 10404, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2345, 21, 30201, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2346, 21, 10303, '2026-01-07 12:04:36');
INSERT INTO `sys_role_menu` VALUES (2347, 23, 40103, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2348, 23, 20102, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2349, 23, 20301, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2350, 23, 30102, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2351, 23, 10301, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2352, 23, 10503, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2353, 23, 90401, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2354, 23, 10403, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2355, 23, 40206, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2356, 23, 20203, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2357, 23, 40101, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2358, 23, 40205, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2359, 23, 30202, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2360, 23, 20302, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2361, 23, 10604, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2362, 23, 10502, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2363, 23, 90301, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2364, 23, 10801, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2365, 23, 20101, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2366, 23, 10203, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2367, 23, 50102, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2368, 23, 10803, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2369, 23, 10401, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2370, 23, 20103, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2371, 23, 50104, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2372, 23, 40304, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2373, 23, 40204, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2374, 23, 10601, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2375, 23, 40203, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2376, 23, 20306, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2377, 23, 50103, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2378, 23, 10704, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2379, 23, 40102, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2380, 23, 20401, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2381, 23, 20201, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2382, 23, 20303, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2383, 23, 901, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2384, 23, 40202, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2385, 23, 50101, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2386, 23, 10302, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2387, 23, 10104, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2388, 23, 40303, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2389, 23, 20202, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2390, 23, 10703, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2391, 23, 10202, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2392, 23, 10304, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2393, 23, 20402, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2394, 23, 10201, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2395, 23, 10402, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2396, 23, 10705, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2397, 23, 40301, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2398, 23, 10706, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2399, 23, 20304, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2400, 23, 30101, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2401, 23, 10603, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2402, 23, 20305, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2403, 23, 10501, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2404, 23, 10804, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2405, 23, 10701, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2406, 23, 20204, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2407, 23, 20104, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2408, 23, 40302, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2409, 23, 40104, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2410, 23, 10602, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2411, 23, 10404, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2412, 23, 902, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2413, 23, 10702, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2414, 23, 40201, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2415, 23, 90402, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2416, 23, 10802, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2417, 23, 30201, '2026-01-31 09:57:49');
INSERT INTO `sys_role_menu` VALUES (2418, 23, 10303, '2026-01-31 09:57:49');

-- ----------------------------
-- Table structure for sys_school
-- ----------------------------
DROP TABLE IF EXISTS `sys_school`;
CREATE TABLE `sys_school`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '学校ID',
  `org_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '校名',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详细地址',
  `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:1启用 0禁用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_deleted`(`deleted_at` ASC, `id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10025 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学校表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_school
-- ----------------------------
INSERT INTO `sys_school` VALUES (10004, '大城县一中', '河北省', '廊坊市', '大城县', '2街1111号', '王老师', '13111111111', 1, '2025-12-08 09:39:57', '2025-12-09 17:14:59', NULL);
INSERT INTO `sys_school` VALUES (10005, '滨海新区', '天津市', '天津市', '红桥区', '11111号', '章鱼哥', '13800138004', 1, '2025-12-08 09:44:42', '2026-01-04 16:41:33', NULL);
INSERT INTO `sys_school` VALUES (10006, '北京大学2', '北京市', '北京市', '海淀区', '颐和园路5号', '李老师', '13800001111', 0, '2025-12-09 14:17:56', '2025-12-09 14:24:01', '2025-12-09 14:24:01');
INSERT INTO `sys_school` VALUES (10007, '唐山学院', '河北省', '唐山市', '路南区', '唐', 'whx', '18111111111', 0, '2025-12-09 14:20:02', '2025-12-09 14:23:58', '2025-12-09 14:23:58');
INSERT INTO `sys_school` VALUES (10008, '大同大学', '山西省', '大同市', '云冈区', '2222街1111', '骑士', '13903234536', 1, '2025-12-09 14:28:48', '2025-12-09 14:49:09', NULL);
INSERT INTO `sys_school` VALUES (10009, '河师大', '河北省', '石家庄市', '裕华区', '建设南大街', '王大熊', '139023248765', 1, '2025-12-09 14:55:00', '2025-12-30 14:49:00', NULL);
INSERT INTO `sys_school` VALUES (10010, '灵犀大学2', '山西省', '临汾市', '乡宁县', '1街11号', '伽罗', '139023876564', 1, '2025-12-09 14:58:03', '2025-12-10 10:06:37', NULL);
INSERT INTO `sys_school` VALUES (10011, '111', '天津市', '天津市', '南开区', '1111', '11111', '13903234376', 0, '2025-12-09 16:06:59', '2025-12-09 16:07:01', '2025-12-09 16:07:01');
INSERT INTO `sys_school` VALUES (10012, '天津大学', '天津市', '天津市', '津南区', '112街2号', '齐老师', '13908764567', 0, '2025-12-26 09:03:38', '2025-12-30 15:56:37', '2025-12-30 15:56:37');
INSERT INTO `sys_school` VALUES (10013, '清北', '辽宁省', '沈阳市', '和平区', '123街', '老张', '13908765346', 0, '2025-12-26 09:25:09', '2025-12-26 12:09:52', '2025-12-26 12:09:52');
INSERT INTO `sys_school` VALUES (10014, '提u的护国', '北京市', '北京市', '石景山区', '12街', '老王', '13908765341', 0, '2025-12-26 09:25:42', '2025-12-26 09:27:38', '2025-12-26 09:27:38');
INSERT INTO `sys_school` VALUES (10015, '大学', '山西省', '大同市', '新荣区', '滨海路', '老七', '13908765146', 0, '2025-12-26 09:26:16', '2025-12-26 09:27:36', '2025-12-26 09:27:36');
INSERT INTO `sys_school` VALUES (10016, '工业大学', '山西省', '太原市', '迎泽区', '啦啦啦路', '老袁', '13908125346', 0, '2025-12-26 09:26:48', '2025-12-26 09:27:34', '2025-12-26 09:27:34');
INSERT INTO `sys_school` VALUES (10017, '职业人技术学习中O·', '河北省', '保定市', '阜平县', '12路', '老啊', '13918765346', 0, '2025-12-26 09:27:24', '2025-12-26 09:27:31', '2025-12-26 09:27:31');
INSERT INTO `sys_school` VALUES (10018, '北大', '北京市', '海淀区', '北京市', '北京', '章鱼哥', '13800138004', 1, '2025-12-30 09:32:22', '2025-12-30 09:32:22', NULL);
INSERT INTO `sys_school` VALUES (10019, '清华', '北京市', '海淀区', '北京市', '北京', '张飞', '13800138002', 1, '2025-12-30 09:49:15', '2025-12-30 09:49:15', NULL);
INSERT INTO `sys_school` VALUES (10020, '河北科技', '北京市', '海淀区', '北京市', '河北', '张三', '13800138001', 1, '2025-12-30 09:51:09', '2025-12-30 09:51:09', NULL);
INSERT INTO `sys_school` VALUES (10021, '西门', '河北省', '石家庄市', '裕华区', 'test', '章鱼哥', '13800138004', 1, '2025-12-30 11:37:19', '2026-01-26 18:10:52', NULL);
INSERT INTO `sys_school` VALUES (10022, '沧州师范学院', '河北省', '沧州市', '运河区', 'dd', '章鱼哥', '13800138004', 1, '2025-12-30 11:40:52', '2026-01-26 11:10:00', NULL);
INSERT INTO `sys_school` VALUES (10023, 'aa', '北京市', '海淀区', '北京市', 'tst', '用户123', '13801138000', 0, '2025-12-30 15:08:36', '2025-12-30 15:58:06', '2025-12-30 15:58:06');
INSERT INTO `sys_school` VALUES (10024, '张家口职业技术学院', '河北省', '张家口市', '桥东区', '确认路111', '张飞', '13800138002', 1, '2026-01-04 16:40:38', '2026-01-22 14:17:01', NULL);

-- ----------------------------
-- Table structure for sys_settlement_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_settlement_account`;
CREATE TABLE `sys_settlement_account`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户名称',
  `account_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户编码(系统自动生成)',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系方式',
  `contact_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系邮箱(可选)',
  `account_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '账户类型 1用户账户信息 2平台账户',
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结算账户所属用户类型（例如：合伙人、商家、服务人员等），与app_user表无关',
  `bank_account_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '打款银行卡号',
  `bank_account_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开户名',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '最后一次的支付时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_account_code`(`account_code` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_phone`(`contact_phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统结算账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_settlement_account
-- ----------------------------
INSERT INTO `sys_settlement_account` VALUES (17, '张三', 'PARTNER17651632367599319', '13800138001', 'zhangsan1_new@example.com', 1, '合伙人', '6222021234567890123', '张三', NULL, 1, '2025-12-08 11:07:16', '2025-12-08 14:25:51', NULL);
INSERT INTO `sys_settlement_account` VALUES (18, '张飞', 'PARTNER17651645717044751', '13800138002', 'zhangsan3_new@example.com', 1, '合伙人', '6222021234567890123', '张三', NULL, 1, '2025-12-08 11:29:31', '2025-12-09 14:56:55', NULL);
INSERT INTO `sys_settlement_account` VALUES (19, '张有', 'PARTNER17651649734585978', '13800138003', NULL, 1, '合伙人', '6222021234567890123', '农商银行', NULL, 1, '2025-12-08 11:36:13', '2025-12-08 11:36:13', NULL);
INSERT INTO `sys_settlement_account` VALUES (20, '章鱼哥', 'PARTNER17651653087575299', '13800138004', 'zhangsan_new6@example.com', 1, '合伙人', '6222021234567890883', '中国银行', NULL, 1, '2025-12-08 11:41:48', '2025-12-08 20:57:24', NULL);
INSERT INTO `sys_settlement_account` VALUES (21, '用户123', 'PARTNER17651654887873320', '13801138000', 'zhangsan_new@example.com', 1, '合伙人', '6222021234567891123', '中国银行', NULL, 1, '2025-12-08 11:44:48', '2025-12-08 14:18:45', NULL);
INSERT INTO `sys_settlement_account` VALUES (22, '李四', 'SA17651943622972228', '13900139000', 'lisi@example.com', 2, NULL, '6222021234567890456', '李四', NULL, 1, '2025-12-08 19:46:02', '2025-12-08 20:21:06', '2025-12-08 20:21:06');
INSERT INTO `sys_settlement_account` VALUES (23, '李四123', 'SA17651948805538482', '13900139001', NULL, 2, NULL, '6222021234567890426', '李四', NULL, 1, '2025-12-08 19:54:40', '2025-12-08 20:22:13', '2025-12-08 20:22:13');
INSERT INTO `sys_settlement_account` VALUES (24, '李四123', 'SA17651948887503602', '13900139002', NULL, 2, NULL, '62220212', '李四', NULL, 1, '2025-12-08 19:54:48', '2025-12-08 20:22:24', '2025-12-08 20:22:24');
INSERT INTO `sys_settlement_account` VALUES (25, '李四123', 'SA17651951603803180', '13900139021', NULL, 2, NULL, '62220212123', '李四', NULL, 1, '2025-12-08 19:59:20', '2025-12-09 09:15:22', '2025-12-09 09:15:22');
INSERT INTO `sys_settlement_account` VALUES (26, '测试1', 'SA17651961361586333', '19131111111', NULL, 2, NULL, '123123', 'whx', '2025-12-09 09:14:00', 1, '2025-12-08 20:15:36', '2025-12-09 09:14:18', NULL);
INSERT INTO `sys_settlement_account` VALUES (27, 'ces', 'SA17651961824570020', '14111111111', NULL, 2, NULL, '1323123', 'qwe', NULL, 1, '2025-12-08 20:16:22', '2025-12-08 20:19:27', '2025-12-08 20:19:27');
INSERT INTO `sys_settlement_account` VALUES (28, '测试', 'SA17651967392378618', '18111111111', NULL, 2, NULL, '13213', '测试', NULL, 1, '2025-12-08 20:25:39', '2025-12-08 20:25:55', '2025-12-08 20:25:55');
INSERT INTO `sys_settlement_account` VALUES (29, '测试', 'SA17651983186807635', '19111111111', NULL, 2, NULL, '1011111', 'aaa', '2025-12-19 20:51:00', 1, '2025-12-08 20:51:58', '2026-01-04 16:47:33', '2026-01-04 16:47:33');
INSERT INTO `sys_settlement_account` VALUES (30, '刺客五六七', 'PARTNER17652442047602670', '13211111116', 'zhangsan11@example.com', 1, '合伙人', '6222021221123456789', '农商银行', NULL, 1, '2025-12-09 09:36:44', '2025-12-09 11:48:53', '2025-12-09 11:48:53');
INSERT INTO `sys_settlement_account` VALUES (31, '幸福里便利店', 'MERCHANT_17653702260634576', '13900139000', NULL, 1, '商家', '6217000011112222333', '刘店长', NULL, 1, '2025-12-10 20:37:06', '2025-12-10 20:37:06', NULL);
INSERT INTO `sys_settlement_account` VALUES (32, '商家一', 'MERCHANT_17654197218976206', '18111111111', NULL, 1, '商家', '514222222222', '往往', NULL, 1, '2025-12-11 10:22:01', '2025-12-11 10:22:01', NULL);
INSERT INTO `sys_settlement_account` VALUES (33, '商家二', 'MERCHANT_17654226823864326', '19111111111', NULL, 1, '商家', '321231231', '往往', NULL, 1, '2025-12-11 11:11:22', '2025-12-23 15:18:11', '2025-12-23 15:18:11');
INSERT INTO `sys_settlement_account` VALUES (34, '小伙夫快餐', 'MERCHANT_17654261623213560', '18111111111', NULL, 1, '商家', '123123123', '往往', NULL, 1, '2025-12-11 12:09:22', '2025-12-11 12:09:22', NULL);
INSERT INTO `sys_settlement_account` VALUES (35, '花果山鲜花店', 'MERCHANT_17654277986108613', '13800000000', NULL, 1, NULL, '62220123123123', '用户123', NULL, 1, '2025-12-11 12:36:38', '2025-12-11 12:36:38', NULL);
INSERT INTO `sys_settlement_account` VALUES (36, '测试添加', 'MERCHANT_17654361801448692', '17111111111', NULL, 1, NULL, '411111111', '往往', NULL, 1, '2025-12-11 14:56:20', '2025-12-11 14:56:20', NULL);
INSERT INTO `sys_settlement_account` VALUES (37, '啦啦啦啦啦啦啦', 'PARTNER17658479727385409', '13903234567', NULL, 1, '合伙人', '6222021001123456789', '农商银行', NULL, 1, '2025-12-16 09:19:32', '2025-12-16 09:19:32', NULL);
INSERT INTO `sys_settlement_account` VALUES (38, '周二', 'PARTNER17658485781152815', '13613132345', NULL, 1, '合伙人', '6222021001123434789', '中国银行', NULL, 1, '2025-12-16 09:29:38', '2025-12-16 09:29:38', NULL);
INSERT INTO `sys_settlement_account` VALUES (40, '小熊奶茶店', 'MERCHANT_17662163904026741', '13812345678', NULL, 1, '商家', '6222021001123456789', '啦啦啦啦啦啦啦', NULL, 1, '2025-12-20 15:39:48', '2025-12-20 15:39:48', NULL);
INSERT INTO `sys_settlement_account` VALUES (41, '板面', 'MERCHANT_17662184593961129', '15512982628', NULL, 1, '商家', '6222021234567891123', '用户123', NULL, 1, '2025-12-20 16:14:19', '2025-12-20 16:14:19', NULL);
INSERT INTO `sys_settlement_account` VALUES (101, '张伟结算账户', 'SA202312230101', '13900000001', 'zhangwei@partner.com', 1, '合伙人', '6222000000000001', '张伟', NULL, 1, '2023-12-01 09:00:00', '2023-12-01 09:00:00', NULL);
INSERT INTO `sys_settlement_account` VALUES (102, '李娜', 'SA202312230102', '13900000002', 'lina@partner.com', 1, '合伙人', '6222000000000002', '李娜', NULL, 1, '2023-12-01 10:00:00', '2025-12-26 09:04:07', NULL);
INSERT INTO `sys_settlement_account` VALUES (103, '校园美食城结算账户', 'SA202312230103', '13800001001', 'food@campus.com', 1, '商家', '6222000000001001', '校园美食城', NULL, 1, '2023-12-02 09:00:00', '2023-12-02 09:00:00', NULL);
INSERT INTO `sys_settlement_account` VALUES (104, '快乐奶茶店结算账户', 'SA202312230104', '13800001002', 'milktea@campus.com', 1, '商家', '6222000000001002', '快乐奶茶店', NULL, 1, '2023-12-02 10:00:00', '2023-12-02 10:00:00', NULL);
INSERT INTO `sys_settlement_account` VALUES (105, '书香文具店结算账户', 'SA202312230105', '13800001003', 'books@campus.com', 1, '商家', '6222000000001003', '书香文具店', NULL, 1, '2023-12-02 11:00:00', '2023-12-02 11:00:00', NULL);
INSERT INTO `sys_settlement_account` VALUES (106, '便利超市结算账户', 'SA202312230106', '13800001004', 'store@campus.com', 1, '商家', '6222000000001004', '便利超市', NULL, 1, '2023-12-02 12:00:00', '2023-12-02 12:00:00', NULL);
INSERT INTO `sys_settlement_account` VALUES (107, '安徽板面', 'MERCHANT_17669720103369353', '19322575739', NULL, 1, '商家', '6222000000000002', '李娜', NULL, 1, '2025-12-29 09:33:30', '2025-12-29 09:33:30', NULL);
INSERT INTO `sys_settlement_account` VALUES (108, '支付11', 'SA17670792289522596', '15512682622', '3452@qq.com', 2, NULL, '5255336522', '小李', '2025-12-30 07:30:00', 1, '2025-12-30 15:20:28', '2026-01-04 10:53:28', '2026-01-04 10:53:28');
INSERT INTO `sys_settlement_account` VALUES (109, '支付222', 'SA17670813409736573', '19322575736', '52336@qq.com', 2, NULL, '52236974', '小张', '2025-12-30 07:55:00', 1, '2025-12-30 15:55:40', '2025-12-30 16:00:47', '2025-12-30 16:00:47');
INSERT INTO `sys_settlement_account` VALUES (110, '测试666', 'SA17674953811063081', '15512982325', '52214@qq.com', 2, NULL, '511452', '小李', '2026-01-04 02:56:00', 1, '2026-01-04 10:56:21', '2026-01-04 10:56:21', NULL);
INSERT INTO `sys_settlement_account` VALUES (111, 'test1', 'SA17675165393968507', '15987189785', '1981302052@qq.com', 2, NULL, '11111111111111111111', '嘎嘎嘎', '2026-01-04 08:48:00', 1, '2026-01-04 16:48:59', '2026-01-04 16:49:09', '2026-01-04 16:49:09');
INSERT INTO `sys_settlement_account` VALUES (112, 'test1111', 'SA17675167223882858', '15987189785', '111111@qq.com', 2, NULL, '111111', '发给哈哈嗝', '2026-01-04 12:53:00', 1, '2026-01-04 16:52:02', '2026-01-07 12:12:50', '2026-01-07 12:12:50');
INSERT INTO `sys_settlement_account` VALUES (114, '板面', 'MERCHANT_17675750209630077', '15551242535', NULL, 1, '商家', '6222021234567890883', '章鱼哥', NULL, 1, '2026-01-05 09:03:40', '2026-01-05 09:03:40', NULL);
INSERT INTO `sys_settlement_account` VALUES (115, '校园便利店', 'MERCHANT_17679224467454745', '13900139000', NULL, 1, '商家', '6222021234567890123', '李四', NULL, 1, '2026-01-09 09:34:05', '2026-01-09 09:34:05', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '系统用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '系统用户名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系统用户邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '系统用户密码',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像url',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登陆时间',
  `last_login_ip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更改时间',
  `deleted_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `status` tinyint UNSIGNED NULL DEFAULT 1 COMMENT '状态 0禁用 1启用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone` ASC) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin@campus-errand.com', '$2a$10$ZUh.a8caUHfFwRkAtVep8eS6o75W2B1ubK4bPLWKvnlBH26wlh/tS', '/uploads/2025-12-10/53b9d5a2cf1c4880a7ee07a0176bc79e.jpg', '13800138000', '2026-02-06 19:28:41', '172.16.9.115', '2025-12-10 12:31:21', '2026-01-05 09:22:50', NULL, 1);
INSERT INTO `sys_user` VALUES (2, 'root', NULL, '$2a$10$g9yzU0njlASkM6rL5p84eeA1j.xyuCQ84a4TB3k58NojccjIJ2HgG', '/uploads/2025-12-16/98ea37b376c44962972402878f8034f1.png', '13903335675', '2026-02-03 09:25:42', '172.16.8.104', '2025-12-12 09:45:09', '2025-12-12 09:45:09', NULL, 1);
INSERT INTO `sys_user` VALUES (3, 'rooter1', NULL, '$2a$10$5oDkN7GSjcGNwgk/D6Zw9O5QYwJQCdSz1TfeuLj6Eg7F9FfSgHCvS', '/uploads/2025-12-12/46e0b4c7a2a742a4bbe31ea4bd5fe1e2.png', '13787645869', NULL, NULL, '2025-12-12 09:46:44', '2025-12-12 09:46:44', NULL, 1);
INSERT INTO `sys_user` VALUES (4, 'laoliu', NULL, '$2a$10$E6UZ3zqQTDuRkL6b1X5igO0L72Lzy0H7b4yFvH9xfcIyWTJPTMqbS', '/uploads/2025-12-16/693966b7dcd04d78acd1a37022e2b4bd.jpg', '18301575581', '2026-01-04 14:42:22', '172.16.8.115', '2025-12-16 19:31:14', '2025-12-16 19:31:14', NULL, 1);
INSERT INTO `sys_user` VALUES (5, 'laoliuAbc', NULL, '$2a$10$KjuO5WUtJ076qX4CEHfFP.QEbYSIiE.oH0p1YC58Y/gmGAMyeVZOi', '/uploads/2025-12-18/40621258b323440ca198df6fee791d9c.jpg', '18365767787', NULL, NULL, '2025-12-18 14:31:31', '2025-12-18 14:31:31', NULL, 1);
INSERT INTO `sys_user` VALUES (6, 'laozhang', NULL, '$2a$10$clDrcRNMgmLxymcS5aWehu1DIn3ywUQsoQyuueKTMAwOON6HxgN.m', '/uploads/2026-01-06/ebb05aa2b2f64bd49e7cdf1e8820fa8b.jpg', '18365767789', NULL, NULL, '2025-12-18 14:33:31', '2025-12-18 14:33:31', NULL, 1);
INSERT INTO `sys_user` VALUES (7, 'zhangsan', NULL, '$2a$10$vBttUI/uEitNy0SyRJCHH.sYq.LwTHtl8HUWN6qlB3YHqv5igUt4C', '/uploads/2026-01-06/4b0f50c13384498e803423e3fe072d7d.jpg', '18365767781', '2026-01-07 09:33:21', '172.16.8.252', '2025-12-18 14:42:39', '2025-12-18 14:42:39', NULL, 1);
INSERT INTO `sys_user` VALUES (8, 'lisi', NULL, '$2a$10$.G.dm1giAhCm6iF1WwpeV.Cuf9Pcd8Ggx8Ak2ug.hTEsHueGh7Zji', '/uploads/2025-12-18/84a79be8521c43f0a61cc5df601fcbd8.jpg', '18502154451', NULL, NULL, '2025-12-18 15:06:15', '2025-12-22 15:40:24', '2025-12-22 15:40:24', 1);
INSERT INTO `sys_user` VALUES (9, 'lin', NULL, '$2a$10$s1ZKrayK/IgruS/CddMba.vWQ9gkL6jdU/wAEh3fyFm.vFIkP9/YO', '/uploads/2025-12-20/b3db814c639c40d0b4dcee7f4eb4f0e9.jpg', '15987189785', NULL, NULL, '2025-12-20 18:18:11', '2025-12-22 19:12:36', '2025-12-22 19:12:36', 1);
INSERT INTO `sys_user` VALUES (10, 'abc', NULL, '$2a$10$Xx2QPEgEGJlqcynGTZaDo.aPR.mbnw7KB1teSao.GrDXwymPsNkUS', '/uploads/2025-12-26/c432c6ebee5c48f4abcdcd32adabf537.png', '18111111111', NULL, NULL, '2025-12-26 11:47:46', '2026-01-04 16:13:06', '2026-01-04 16:13:06', 1);
INSERT INTO `sys_user` VALUES (11, 'aaaaaa', NULL, '$2a$10$gF1IVnrUXQrY8yJ/.mjiXeFS144goTrry6WKduzUgh1yi4tsGzTZq', '/uploads/2025-12-26/194dada1c4d0413d83c7ce6aa526bd5f.jpg', '19111111111', '2025-12-26 11:55:23', '172.16.8.104', '2025-12-26 11:54:31', '2026-01-04 14:37:20', '2026-01-04 14:37:20', 1);
INSERT INTO `sys_user` VALUES (13, 'adadad', NULL, '$2a$10$GlZ23IjZ8QmFHnD2Vqb/2ulJCvlmlBzOIo2F1dnNSMFmyPQ6pKsTi', '/uploads/2026-01-06/54af365a680642a6b11958dd95d15db0.jpg', '18131711389', '2026-01-07 19:12:29', '172.16.8.252', '2026-01-06 12:07:48', '2026-01-06 12:07:48', NULL, 1);
INSERT INTO `sys_user` VALUES (14, 'abcabc', NULL, '$2a$10$94Ibf8uJGsK5SjbF93PyMul5vNxtQDO41v2VZL.2ZujdS1nGAi2Hy', '/uploads/2026-01-07/11ac6b75d0914bf78065a341804a2028.jpg', '16511111111', '2026-01-07 19:13:14', '172.16.8.252', '2026-01-07 09:48:33', '2026-01-07 19:26:35', '2026-01-07 19:26:35', 1);
INSERT INTO `sys_user` VALUES (17, 'zxczxc', NULL, '$2a$10$KX30WYRRHUgwXGbzHnCW9.8X2ilXgJKAZiFUPFLGVjZzRx6Xp1A3O', '/uploads/2026-01-07/6ce9d455f7e2453888093111c7f847ce.png', '17111111111', '2026-01-07 20:18:02', '172.16.8.252', '2026-01-07 19:28:03', '2026-01-07 19:28:03', NULL, 1);
INSERT INTO `sys_user` VALUES (21, 'test222', NULL, '$2a$10$/a1YBOM9CVX4JZaInsLbsubm/9.KzzYslzGmVLSpp9NxvcoUDhtqq', '/uploads/2026-01-07/5e78611e8c6d40ea93c4dcc44911bfa8.png', '19332133213', '2026-01-07 20:17:27', '172.16.8.252', '2026-01-07 20:00:16', '2026-01-07 20:00:16', NULL, 1);
INSERT INTO `sys_user` VALUES (22, 'aaaa', NULL, '$2a$10$.lMPfoQezrAESgrDHeMfb.OMgAQ8RA8meLP3brg3VrG7QYC4K4Qg.', '/uploads/2026-01-31/9620ebdcf058429884113b6f29daa042.png', '18131722121', '2026-01-31 09:58:24', '172.16.8.104', '2026-01-31 01:57:30', '2026-01-31 01:57:30', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` bigint UNSIGNED NOT NULL COMMENT '角色ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_role`(`user_id` ASC, `role_id` ASC) USING BTREE,
  INDEX `idx_role`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (8, 3, 1, '2025-12-12 09:46:44');
INSERT INTO `sys_user_role` VALUES (9, 3, 3, '2025-12-12 09:46:44');
INSERT INTO `sys_user_role` VALUES (10, 3, 2, '2025-12-12 09:46:44');
INSERT INTO `sys_user_role` VALUES (11, 3, 4, '2025-12-12 09:46:44');
INSERT INTO `sys_user_role` VALUES (12, 3, 5, '2025-12-12 09:46:44');
INSERT INTO `sys_user_role` VALUES (23, 1, 1, '2026-01-05 10:06:12');
INSERT INTO `sys_user_role` VALUES (36, 6, 18, '2026-01-06 12:02:36');
INSERT INTO `sys_user_role` VALUES (37, 7, 1, '2026-01-06 12:03:25');
INSERT INTO `sys_user_role` VALUES (38, 7, 2, '2026-01-06 12:03:25');
INSERT INTO `sys_user_role` VALUES (39, 7, 3, '2026-01-06 12:03:25');
INSERT INTO `sys_user_role` VALUES (40, 7, 4, '2026-01-06 12:03:25');
INSERT INTO `sys_user_role` VALUES (41, 7, 5, '2026-01-06 12:03:25');
INSERT INTO `sys_user_role` VALUES (42, 7, 8, '2026-01-06 12:03:25');
INSERT INTO `sys_user_role` VALUES (52, 2, 1, '2026-01-06 20:08:20');
INSERT INTO `sys_user_role` VALUES (53, 2, 2, '2026-01-06 20:08:20');
INSERT INTO `sys_user_role` VALUES (54, 2, 3, '2026-01-06 20:08:20');
INSERT INTO `sys_user_role` VALUES (55, 2, 4, '2026-01-06 20:08:20');
INSERT INTO `sys_user_role` VALUES (56, 2, 5, '2026-01-06 20:08:20');
INSERT INTO `sys_user_role` VALUES (57, 2, 21, '2026-01-06 20:08:20');
INSERT INTO `sys_user_role` VALUES (66, 13, 1, '2026-01-07 09:30:31');
INSERT INTO `sys_user_role` VALUES (67, 13, 2, '2026-01-07 09:30:31');
INSERT INTO `sys_user_role` VALUES (68, 13, 3, '2026-01-07 09:30:31');
INSERT INTO `sys_user_role` VALUES (69, 13, 4, '2026-01-07 09:30:31');
INSERT INTO `sys_user_role` VALUES (70, 13, 5, '2026-01-07 09:30:31');
INSERT INTO `sys_user_role` VALUES (71, 13, 8, '2026-01-07 09:30:31');
INSERT INTO `sys_user_role` VALUES (72, 13, 21, '2026-01-07 09:30:31');
INSERT INTO `sys_user_role` VALUES (89, 17, 1, '2026-01-07 19:50:59');
INSERT INTO `sys_user_role` VALUES (92, 21, 21, '2026-01-07 20:13:49');
INSERT INTO `sys_user_role` VALUES (93, 22, 23, '2026-01-31 01:57:30');

SET FOREIGN_KEY_CHECKS = 1;
