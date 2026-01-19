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

 Date: 16/01/2026 21:41:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for audit_record
-- ----------------------------
DROP TABLE IF EXISTS `audit_record`;
CREATE TABLE `audit_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `audit_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核编号',
  `biz_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核项:MERCHANT_SETTLE-商家入驻 WITHDRAW-提现 GOODS-商品上架 STAFF_APPLY-服务人员 RIDER_APPLY-骑手',
  `applicant_id` bigint UNSIGNED NOT NULL COMMENT '申请人ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '审核状态:0-待审核 1-审核通过 2-审核拒绝',
  `auditor_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审核人ID',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_audit_no`(`audit_no` ASC) USING BTREE,
  INDEX `idx_applicant`(`applicant_id` ASC, `status` ASC) USING BTREE,
  INDEX `idx_delete`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '审核记录表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
