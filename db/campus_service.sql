/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_service

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 28/01/2026 14:54:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commission_config
-- ----------------------------
DROP TABLE IF EXISTS `commission_config`;
CREATE TABLE `commission_config`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联表服务分类ID',
  `config_type` tinyint NOT NULL DEFAULT 1 COMMENT '配置类型：1-全局默认，2-服务分佣 3-商家分佣 4-合伙人分佣',
  `commission_rate` tinyint NOT NULL COMMENT '分佣比例，10代表10%',
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category`(`category_id` ASC) USING BTREE,
  CONSTRAINT `chk_rate` CHECK ((`commission_rate` >= 0) and (`commission_rate` <= 100))
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务分佣配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of commission_config
-- ----------------------------

-- ----------------------------
-- Table structure for errand_category
-- ----------------------------
DROP TABLE IF EXISTS `errand_category`;
CREATE TABLE `errand_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级ID（0为顶级分类）',
  `level` tinyint NOT NULL DEFAULT 1 COMMENT '分类层级：1-一级分类，2-二级分类',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `sort_order` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序（值越小越靠前显示）',
  `allow_offline_trade` tinyint NOT NULL DEFAULT 0 COMMENT '是否允许线下交易：0-否，1-是',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_level`(`parent_id` ASC, `level` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_sort`(`sort_order` ASC) USING BTREE,
  INDEX `idx_offline`(`allow_offline_trade` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 334 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务分类管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of errand_category
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
