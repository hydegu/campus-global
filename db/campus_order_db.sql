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

 Date: 19/01/2026 11:26:32
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外卖订单扩展表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for order_errand
-- ----------------------------
DROP TABLE IF EXISTS `order_errand`;
CREATE TABLE `order_errand`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` bigint UNSIGNED NOT NULL COMMENT '订单主表ID',
  `service_fee` decimal(10, 2) NOT NULL COMMENT '服务费',
  `service_type_id` bigint UNSIGNED NOT NULL COMMENT '服务分类id',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '跑腿订单扩展表' ROW_FORMAT = DYNAMIC;

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
  `cancel_type` tinyint NULL DEFAULT NULL COMMENT '取消类型:1-用户取消 2-商家取消 3-超时取消',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单主表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
