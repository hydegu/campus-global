/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_merchant_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 27/01/2026 18:09:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for delivery_fee_config
-- ----------------------------
DROP TABLE IF EXISTS `delivery_fee_config`;
CREATE TABLE `delivery_fee_config`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配置名称',
  `base_fee` decimal(10, 2) NOT NULL COMMENT '起步价',
  `base_distance` decimal(10, 2) NOT NULL COMMENT '起步距离(公里，此距离内采用起步价)',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态:0禁用,1启用',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_delete`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '配送费配置主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivery_fee_config
-- ----------------------------

-- ----------------------------
-- Table structure for delivery_fee_rule
-- ----------------------------
DROP TABLE IF EXISTS `delivery_fee_rule`;
CREATE TABLE `delivery_fee_rule`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `config_id` bigint NOT NULL COMMENT '关联配置表id',
  `rule_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '规则类型:1-距离,2-时间',
  `distance_start` decimal(10, 2) NULL DEFAULT NULL COMMENT '起始距离(公里)',
  `distance_end` decimal(10, 2) NULL DEFAULT NULL COMMENT '结束距离(公里)',
  `price_per_km` decimal(10, 2) NULL DEFAULT NULL COMMENT '每公里价格',
  `time_start` time NULL DEFAULT NULL COMMENT '开始时间',
  `time_end` time NULL DEFAULT NULL COMMENT '结束时间',
  `time_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '时段附加费',
  `time_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '时段类型:1-白天 2-夜间',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_delete`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '配送费规则明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivery_fee_rule
-- ----------------------------

-- ----------------------------
-- Table structure for mch_category
-- ----------------------------
DROP TABLE IF EXISTS `mch_category`;
CREATE TABLE `mch_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级分类ID（0表示顶级分类）',
  `level` tinyint NOT NULL DEFAULT 1 COMMENT '分类层级（1-一级，2-二级 3-三级)',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用,1-启用',
  `is_global` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-全局分类 1-商家自定义分类',
  `mch_id` bigint NULL DEFAULT NULL COMMENT '商家id',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_sort`(`sort_order` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 437 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_category
-- ----------------------------

-- ----------------------------
-- Table structure for mch_product
-- ----------------------------
DROP TABLE IF EXISTS `mch_product`;
CREATE TABLE `mch_product`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品标题',
  `selling_points` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品卖点',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品详情',
  `main_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品主图URL',
  `images` json NULL COMMENT '图片URL数组',
  `category_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联的商品分类ID',
  `merchant_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '所属商家ID',
  `price` decimal(12, 2) NOT NULL COMMENT '价格',
  `profit_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '收益类型:1-按比例,2-固定金额',
  `partner_profit` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '合伙人收益（按比例时为百分比，固定金额时为具体金额）',
  `merchant_profit` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '服务商家收益（按比例时为百分比，固定金额时为具体金额）',
  `spec_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '规格类型:1-统一规格,2-多规格',
  `shelf_status` tinyint NOT NULL DEFAULT 0 COMMENT '上架状态:0-未上架,1-已上架',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联的审核记录ID（审核商品）',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category`(`category_id` ASC) USING BTREE,
  INDEX `idx_shelf_status`(`shelf_status` ASC) USING BTREE,
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC) USING BTREE,
  INDEX `idx_merchant`(`merchant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_product
-- ----------------------------

-- ----------------------------
-- Table structure for mch_product_category
-- ----------------------------
DROP TABLE IF EXISTS `mch_product_category`;
CREATE TABLE `mch_product_category`  (
  `product_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `category_id` bigint UNSIGNED NOT NULL COMMENT '商品分类id',
  PRIMARY KEY (`product_id`, `category_id`) USING BTREE,
  INDEX `idx_category_id`(`category_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商家-商品分类关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_product_category
-- ----------------------------

-- ----------------------------
-- Table structure for mch_product_spec
-- ----------------------------
DROP TABLE IF EXISTS `mch_product_spec`;
CREATE TABLE `mch_product_spec`  (
  `product_id` bigint UNSIGNED NOT NULL COMMENT '商品id',
  `spec_id` bigint UNSIGNED NOT NULL COMMENT '规格id',
  PRIMARY KEY (`product_id`, `spec_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品-规格中间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_product_spec
-- ----------------------------

-- ----------------------------
-- Table structure for mch_spec
-- ----------------------------
DROP TABLE IF EXISTS `mch_spec`;
CREATE TABLE `mch_spec`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` bigint UNSIGNED NOT NULL COMMENT '商品ID',
  `group_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格组名',
  `spec_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格名称(如:大份、中份、小份)',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `spec_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格图片',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态(0:下架 1:上架)',
  `is_default` tinyint NOT NULL DEFAULT 0 COMMENT '是否默认规格 0否 1是',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dish`(`product_id` ASC, `status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品规格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mch_spec
-- ----------------------------

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

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
