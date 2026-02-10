-- 创建数据库
CREATE DATABASE IF NOT EXISTS `campus_order_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `campus_order_db`;

/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_order_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 10/02/2026 15:27:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_delivery
-- ----------------------------
DROP TABLE IF EXISTS `order_delivery`;
CREATE TABLE `order_delivery`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` bigint UNSIGNED NOT NULL COMMENT '订单主表ID',
  `merchant_id` bigint UNSIGNED NOT NULL COMMENT '商家ID',
  `delivery_address_id` bigint UNSIGNED NOT NULL COMMENT '收货地址id',
  `goods_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '商品总金额',
  `delivery_fee` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '配送费',
  `rider_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '骑手ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_id`(`order_id` ASC) USING BTREE,
  INDEX `idx_merchant`(`merchant_id` ASC) USING BTREE,
  INDEX `idx_rider`(`rider_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外卖订单扩展表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_delivery
-- ----------------------------
INSERT INTO `order_delivery` VALUES (1, 1, 2001, 5001, 50.00, 8.50, NULL, '2026-01-19 12:00:00', '2026-01-19 12:00:00');
INSERT INTO `order_delivery` VALUES (2, 2, 2002, 5002, 66.00, 12.00, NULL, '2026-01-19 12:00:00', '2026-01-19 12:05:00');
INSERT INTO `order_delivery` VALUES (3, 3, 2003, 5003, 38.00, 7.00, 3001, '2026-01-19 12:00:00', '2026-01-19 12:20:00');
INSERT INTO `order_delivery` VALUES (4, 4, 2004, 5004, 108.50, 20.00, 3002, '2026-01-19 12:00:00', '2026-01-19 12:35:00');
INSERT INTO `order_delivery` VALUES (5, 5, 2005, 5005, 27.00, 5.00, 3003, '2026-01-19 11:45:00', '2026-01-19 12:18:00');
INSERT INTO `order_delivery` VALUES (6, 6, 2006, 5006, 47.00, 9.00, 3004, '2026-01-19 11:35:00', '2026-01-19 12:15:00');
INSERT INTO `order_delivery` VALUES (7, 7, 2003, 5007, 35.00, 7.00, NULL, '2026-01-19 10:50:00', '2026-01-19 10:55:00');
INSERT INTO `order_delivery` VALUES (8, 14, 2006, 5008, 99999999.99, 99999999.99, NULL, '2026-01-19 12:00:00', '2026-01-19 12:00:00');
INSERT INTO `order_delivery` VALUES (9, 15, 2007, 5009, 0.01, 0.00, 4003, '2026-01-19 12:00:00', '2026-01-19 12:10:00');

-- ----------------------------
-- Table structure for order_errand
-- ----------------------------
DROP TABLE IF EXISTS `order_errand`;
CREATE TABLE `order_errand`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` bigint UNSIGNED NOT NULL COMMENT '订单主表ID',
  `service_fee` decimal(10, 2) NOT NULL COMMENT '服务费',
  `service_type_id` bigint NOT NULL COMMENT '服务分类id',
  `pickup_address_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '取货地址id',
  `delivery_address_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '送货地址id',
  `item_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品描述',
  `item_weight` decimal(8, 2) NULL DEFAULT NULL COMMENT '物品重量(kg)',
  `length` decimal(10, 2) NULL DEFAULT NULL COMMENT '长度(cm)',
  `width` decimal(10, 2) NULL DEFAULT NULL COMMENT '宽度(cm)',
  `height` decimal(10, 2) NULL DEFAULT NULL COMMENT '高度(cm)',
  `volume` decimal(10, 4) NULL DEFAULT NULL COMMENT '体积(cm³，可计算得出)',
  `staff_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '服务人员ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_id`(`order_id` ASC) USING BTREE,
  INDEX `idx_staff`(`staff_id` ASC) USING BTREE,
  INDEX `idx_service_type`(`service_type_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '跑腿订单扩展表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_errand
-- ----------------------------
INSERT INTO `order_errand` VALUES (1, 11, 15.00, 1, '取件地址A', '送件地址B', '帮我取个快递，大概3公斤', 3.00, 30.00, 20.00, 15.00, 9000.0000, NULL, '2026-01-19 12:00:00', '2026-01-19 12:00:00');
INSERT INTO `order_errand` VALUES (2, 12, 20.00, 2, '药店地址C', '宿舍地址D', '帮我买点感冒药', 0.50, 10.00, 8.00, 5.00, 400.0000, 4001, '2026-01-19 11:35:00', '2026-01-19 12:00:00');
INSERT INTO `order_errand` VALUES (3, 13, 18.00, 3, '打印店E', '教学楼F', '帮我打印一份资料，双面打印', 0.20, 21.00, 29.70, 0.10, 62.4400, 4002, '2026-01-19 11:15:00', '2026-01-19 11:55:00');

-- ----------------------------
-- Table structure for order_main
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `order_type` tinyint NOT NULL COMMENT '订单类型:1-外卖 2-服务 3-其他',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '下单用户ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户电话',
  `total_amount` decimal(12, 2) NOT NULL COMMENT '订单总金额',
  `actual_amount` decimal(12, 2) NOT NULL COMMENT '实付金额',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态:0-待支付 1-已支付 2-部分退款 3-全额退款',
  `pay_method` tinyint NULL DEFAULT NULL COMMENT '支付方式:1-在线支付 2-微信 3-线下支付 4-其他',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `pay_channel_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '第三方支付流水号',
  `order_status` tinyint NOT NULL DEFAULT 1 COMMENT '订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 7-已完成 8-售后中',
  `cancel_type` tinyint NULL DEFAULT NULL COMMENT '取消类型:1-用户取消 2-商家取消 3-骑手取消 4-超时取消',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `service_provider_type` tinyint NULL DEFAULT NULL COMMENT '服务提供方类型:1-商家 2-服务人员',
  `service_provider_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '服务提供方ID',
  `service_provider_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务提供方名称',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '学校/社区ID',
  `partner_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '合伙人ID',
  `estimated_provider_income` decimal(12, 2) NULL DEFAULT NULL COMMENT '服务提供方预计收益',
  `estimated_partner_income` decimal(12, 2) NULL DEFAULT NULL COMMENT '合伙人预计收益',
  `estimated_platform_income` decimal(12, 2) NULL DEFAULT NULL COMMENT '平台预计收益',
  `version` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单备注',
  `estimated_start_time` datetime NULL DEFAULT NULL COMMENT '预计开始时间',
  `estimated_delivery_time` datetime NULL DEFAULT NULL COMMENT '预计送达时间',
  `actual_delivery_time` datetime NULL DEFAULT NULL COMMENT '实际送达时间',
  `distance` decimal(8, 2) NULL DEFAULT NULL COMMENT '距离',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no` ASC) USING BTREE,
  INDEX `idx_provider`(`service_provider_type` ASC, `service_provider_id` ASC, `order_status` ASC) USING BTREE,
  INDEX `idx_pay_status`(`pay_status` ASC, `pay_time` DESC) USING BTREE,
  INDEX `idx_school_partner`(`school_id` ASC, `partner_id` ASC) USING BTREE,
  INDEX `idx_delete_at`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_main
-- ----------------------------
INSERT INTO `order_main` VALUES (1, '20260119120001ABCDEF', 1, 1001, '张三', '13800138001', 58.50, 58.50, 0, NULL, NULL, NULL, 1, NULL, NULL, 1, 2001, '美味快餐店', 101, 201, 45.00, 8.50, 5.00, 0, '不要辣', '2026-01-19 12:10:00', '2026-01-19 12:30:00', NULL, 2.50, '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL);
INSERT INTO `order_main` VALUES (2, '20260119120002GHIJKL', 1, 1002, '李四', '13800138002', 78.00, 78.00, 1, 2, '2026-01-19 12:05:00', 'WX202601191200001', 2, NULL, NULL, 1, 2002, '川菜馆', 101, 201, 60.00, 12.00, 6.00, 0, '多放辣椒', '2026-01-19 12:10:00', '2026-01-19 12:35:00', NULL, 3.20, '2026-01-19 12:00:00', '2026-01-19 12:05:00', NULL);
INSERT INTO `order_main` VALUES (3, '20260119120003MNOPQR', 1, 1003, '王五', '13800138003', 45.00, 45.00, 1, 2, '2026-01-19 12:10:00', 'WX202601191200002', 3, NULL, NULL, 2, 3001, '骑手-赵六', 101, 201, 35.00, 7.00, 3.00, 1, '快点送', '2026-01-19 12:15:00', '2026-01-19 12:40:00', NULL, 1.80, '2026-01-19 12:00:00', '2026-01-19 12:20:00', NULL);
INSERT INTO `order_main` VALUES (4, '20260119120004STUVWX', 1, 1004, '赵六', '13800138004', 128.50, 128.50, 1, 1, '2026-01-19 12:15:00', 'ONLINE202601191200001', 4, NULL, NULL, 2, 3002, '骑手-孙七', 101, 201, 100.00, 20.00, 8.50, 2, '不要放香菜', '2026-01-19 12:20:00', '2026-01-19 12:50:00', NULL, 4.50, '2026-01-19 12:00:00', '2026-01-19 12:35:00', NULL);
INSERT INTO `order_main` VALUES (5, '20260119120005YZABCD', 1, 1005, '孙七', '13800138005', 32.00, 32.00, 1, 2, '2026-01-19 11:50:00', 'WX202601191200003', 5, NULL, NULL, 2, 3003, '骑手-周八', 101, 201, 25.00, 5.00, 2.00, 3, '谢谢', '2026-01-19 11:55:00', '2026-01-19 12:20:00', '2026-01-19 12:18:00', 1.20, '2026-01-19 11:45:00', '2026-01-19 12:18:00', NULL);
INSERT INTO `order_main` VALUES (6, '20260119110006EFGHIJ', 1, 1006, '周八', '13800138006', 56.00, 56.00, 1, 1, '2026-01-19 11:40:00', 'ONLINE202601191100001', 7, NULL, NULL, 2, 3004, '骑手-吴九', 101, 201, 43.00, 9.00, 4.00, 4, '很好吃', '2026-01-19 11:45:00', '2026-01-19 12:15:00', '2026-01-19 12:14:00', 2.00, '2026-01-19 11:35:00', '2026-01-19 12:15:00', NULL);
INSERT INTO `order_main` VALUES (7, '20260119105007KLMNOP', 1, 1007, '吴九', '13800138007', 42.00, 42.00, 0, NULL, NULL, NULL, 6, 1, '2026-01-19 10:55:00', 1, 2003, '奶茶店', 101, 201, 32.00, 7.00, 3.00, 1, '不想要了', '2026-01-19 10:50:00', '2026-01-19 11:15:00', NULL, 1.50, '2026-01-19 10:50:00', '2026-01-19 10:55:00', NULL);
INSERT INTO `order_main` VALUES (8, '20260119105008QRSTUV', 1, 1008, '郑十', '13800138008', 65.00, 65.00, 1, 2, '2026-01-19 10:45:00', 'WX202601191050001', 6, 2, '2026-01-19 10:50:00', 1, 2004, '烧烤店', 101, 201, 50.00, 10.00, 5.00, 1, '食材不足', '2026-01-19 10:50:00', '2026-01-19 11:20:00', NULL, 2.80, '2026-01-19 10:40:00', '2026-01-19 10:50:00', NULL);
INSERT INTO `order_main` VALUES (9, '20260119100009WXYZAB', 1, 1009, '陈十一', '13800138009', 38.00, 38.00, 1, 1, '2026-01-19 10:00:00', 'ONLINE202601191000001', 6, 3, '2026-01-19 10:30:00', 1, 2005, '面馆', 101, 201, 29.00, 6.00, 3.00, 1, '超时未接单', '2026-01-19 10:05:00', '2026-01-19 10:35:00', NULL, 1.60, '2026-01-19 10:00:00', '2026-01-19 10:30:00', NULL);
INSERT INTO `order_main` VALUES (10, '20260119090010CDEFGH', 1, 1010, '林十二', '13800138010', 88.00, 88.00, 2, 2, '2026-01-19 09:10:00', 'WX202601190900001', 8, NULL, NULL, 2, 3005, '骑手-黄十三', 101, 201, 68.00, 14.00, 6.00, 3, '申请退款', '2026-01-19 09:15:00', '2026-01-19 09:45:00', '2026-01-19 09:42:00', 3.20, '2026-01-19 09:00:00', '2026-01-19 09:50:00', NULL);
INSERT INTO `order_main` VALUES (11, '20260119120011IJKLMN', 2, 1011, '黄十三', '13800138011', 15.00, 15.00, 1, 2, '2026-01-19 12:00:00', 'WX202601191200004', 2, NULL, NULL, 2, NULL, NULL, 101, 201, 12.00, 2.00, 1.00, 0, '帮我取快递', '2026-01-19 12:05:00', '2026-01-19 12:30:00', NULL, 1.50, '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL);
INSERT INTO `order_main` VALUES (12, '20260119115012OPQRST', 2, 1012, '杨十四', '13800138012', 20.00, 20.00, 1, 1, '2026-01-19 11:40:00', 'ONLINE202601191150001', 4, NULL, NULL, 2, 4001, '服务人员-朱十五', 101, 201, 16.00, 3.00, 1.00, 1, '帮我买药', '2026-01-19 11:45:00', '2026-01-19 12:20:00', NULL, 2.00, '2026-01-19 11:35:00', '2026-01-19 12:00:00', NULL);
INSERT INTO `order_main` VALUES (13, '20260119110013UVWXYZ', 2, 1013, '朱十五', '13800138013', 18.00, 18.00, 1, 2, '2026-01-19 11:20:00', 'WX202601191100002', 7, NULL, NULL, 2, 4002, '服务人员-秦十六', 101, 201, 14.00, 3.00, 1.00, 2, '帮我打印', '2026-01-19 11:25:00', '2026-01-19 11:55:00', '2026-01-19 11:53:00', 1.80, '2026-01-19 11:15:00', '2026-01-19 11:55:00', NULL);
INSERT INTO `order_main` VALUES (14, '20260119120014MAXVAL', 1, 1014, '秦十六', '13800138014', 99999999.99, 99999999.99, 1, 1, '2026-01-19 12:00:00', 'ONLINE202601191200002', 1, NULL, NULL, 1, 2006, '豪华餐厅', 101, 201, 79999999.99, 14999999.99, 4999999.99, 0, '最大金额测试', '2026-01-19 12:05:00', '2026-01-19 12:35:00', NULL, 10.00, '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL);
INSERT INTO `order_main` VALUES (15, '20260119120015MINVAL', 2, 1015, '许十七', '13800138015', 0.01, 0.01, 1, 2, '2026-01-19 12:00:00', 'WX202601191200005', 7, NULL, NULL, 2, 4003, '服务人员-何十八', 101, 201, 0.01, 0.00, 0.00, 1, '最小金额测试', '2026-01-19 12:05:00', '2026-01-19 12:10:00', '2026-01-19 12:08:00', 0.10, '2026-01-19 12:00:00', '2026-01-19 12:10:00', NULL);
INSERT INTO `order_main` VALUES (17, '20260119120017NULLTEST', 1, 1017, '吕十九', '13800138017', 48.00, 48.00, 1, 1, '2026-01-19 12:00:00', 'ONLINE202601191200003', 1, NULL, NULL, 1, 2007, '快餐店', NULL, NULL, 37.00, 7.00, 4.00, 0, '空值测试', NULL, NULL, NULL, NULL, '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL);
INSERT INTO `order_main` VALUES (18, '20260119110018DELETED', 1, 1018, '施二十', '13800138018', 35.00, 35.00, 1, 2, '2026-01-19 11:30:00', 'WX202601191100003', 6, 1, '2026-01-19 11:35:00', 1, 2008, '小吃店', 101, 201, 27.00, 5.00, 3.00, 1, '软删除测试', '2026-01-19 11:35:00', '2026-01-19 12:05:00', NULL, 1.50, '2026-01-19 11:30:00', '2026-01-19 11:35:00', '2026-01-19 11:35:00');
INSERT INTO `order_main` VALUES (19, '20260119120019OTHER', 3, 1019, '张二十一', '13800138019', 100.00, 100.00, 1, 3, '2026-01-19 12:00:00', 'OFFLINE202601191200001', 7, NULL, NULL, 2, 4004, '服务人员-王二十二', 101, 201, 80.00, 15.00, 5.00, 2, '线下支付测试', '2026-01-19 12:05:00', '2026-01-19 12:35:00', '2026-01-19 12:33:00', 3.50, '2026-01-19 12:00:00', '2026-01-19 12:35:00', NULL);
INSERT INTO `order_main` VALUES (20, '20260119100020PARTIAL', 1, 1020, '王二十二', '13800138020', 100.00, 100.00, 2, 2, '2026-01-19 10:00:00', 'WX202601191000002', 7, NULL, NULL, 2, 3007, '骑手-李二十三', 101, 201, 78.00, 15.00, 7.00, 3, '部分退款测试', '2026-01-19 10:05:00', '2026-01-19 10:35:00', '2026-01-19 10:32:00', 3.80, '2026-01-19 10:00:00', '2026-01-19 10:35:00', NULL);
INSERT INTO `order_main` VALUES (21, '20260119090021FULLREF', 1, 1021, '李二十三', '13800138021', 68.00, 68.00, 3, 2, '2026-01-19 09:00:00', 'WX202601190900002', 6, 1, '2026-01-19 09:30:00', 1, 2009, '火锅店', 101, 201, 53.00, 10.00, 5.00, 1, '全额退款测试', '2026-01-19 09:05:00', '2026-01-19 09:35:00', NULL, 2.60, '2026-01-19 09:00:00', '2026-01-19 09:30:00', NULL);
INSERT INTO `order_main` VALUES (100, '20260208100001ABCDEF', 1, 2006, '刘小花', '13900139006', 58.50, 58.50, 1, 2, '2026-02-08 10:05:00', 'WX202602081000001', 7, NULL, NULL, 1, 2001, '校园便利店', 101, 201, 45.00, 8.50, 5.00, 0, '不要辣', '2026-02-08 10:10:00', '2026-02-08 10:30:00', '2026-02-08 10:28:00', 2.50, '2026-02-08 10:00:00', '2026-02-08 10:28:00', NULL);
INSERT INTO `order_main` VALUES (101, '20260208110002GHIJKL', 1, 2007, '陈小明', '13900139007', 78.00, 78.00, 1, 2, '2026-02-08 11:05:00', 'WX202602081100001', 7, NULL, NULL, 1, 2002, '川菜馆', 101, 201, 60.00, 12.00, 6.00, 0, '多放辣椒', '2026-02-08 11:10:00', '2026-02-08 11:35:00', '2026-02-08 11:33:00', 3.20, '2026-02-08 11:00:00', '2026-02-08 11:33:00', NULL);
INSERT INTO `order_main` VALUES (102, '20260208120003MNOPQR', 1, 2008, '周小丽', '13900139008', 45.00, 45.00, 1, 2, '2026-02-08 12:05:00', 'WX202602081200001', 7, NULL, NULL, 1, 2001, '校园便利店', 101, 201, 35.00, 7.00, 3.00, 0, '快点送', '2026-02-08 12:10:00', '2026-02-08 12:40:00', '2026-02-08 12:38:00', 1.80, '2026-02-08 12:00:00', '2026-02-08 12:38:00', NULL);
INSERT INTO `order_main` VALUES (103, '20260205100001STUVWX', 1, 2006, '刘小花', '13900139006', 128.50, 128.50, 1, 1, '2026-02-05 10:15:00', 'ONLINE202602051000001', 7, NULL, NULL, 1, 2003, '水果超市', 101, 201, 100.00, 20.00, 8.50, 0, '不要放香菜', '2026-02-05 10:20:00', '2026-02-05 10:50:00', '2026-02-05 10:48:00', 4.50, '2026-02-05 10:00:00', '2026-02-05 10:48:00', NULL);
INSERT INTO `order_main` VALUES (104, '20260130100001YZABCD', 1, 2007, '陈小明', '13900139007', 32.00, 32.00, 1, 2, '2026-01-30 10:05:00', 'WX202601301000001', 7, NULL, NULL, 1, 2002, '川菜馆', 101, 201, 25.00, 5.00, 2.00, 0, '谢谢', '2026-01-30 10:10:00', '2026-01-30 10:20:00', '2026-01-30 10:18:00', 1.20, '2026-01-30 10:00:00', '2026-01-30 10:18:00', NULL);

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

SET FOREIGN_KEY_CHECKS = 1;
