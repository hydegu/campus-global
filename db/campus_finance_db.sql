/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_finance_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 10/02/2026 15:27:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for finance_transaction
-- ----------------------------
DROP TABLE IF EXISTS `finance_transaction`;
CREATE TABLE `finance_transaction`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `transaction_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流水号',
  `user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
  `transaction_type` tinyint UNSIGNED NOT NULL COMMENT '交易类型:1-打款 2-消费 3-退款 4-提现',
  `amount` decimal(15, 2) NOT NULL COMMENT '交易金额(正数为收入,负数为支出)',
  `related_type` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '关联业务类型:1-订单 2-提现 3-退款',
  `related_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联业务ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户姓名（冗余）',
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户电话（冗余）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_transaction_no`(`transaction_no` ASC) USING BTREE,
  INDEX `idx_type`(`transaction_type` ASC) USING BTREE,
  INDEX `idx_related`(`related_type` ASC, `related_id` ASC) USING BTREE,
  INDEX `idx_create_at`(`create_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '财务流水表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of finance_transaction
-- ----------------------------
INSERT INTO `finance_transaction` VALUES (4, 'TRX20260208130001', NULL, 1, 100.00, 4, 1, '打款给商家-校园便利店', '2026-02-08 13:00:00', '2026-02-08 13:00:00', NULL, NULL, NULL);
INSERT INTO `finance_transaction` VALUES (5, 'TRX20260208140001', NULL, 1, 150.00, 4, 2, '打款给商家-川菜馆', '2026-02-08 14:00:00', '2026-02-08 14:00:00', NULL, NULL, NULL);
INSERT INTO `finance_transaction` VALUES (6, 'TRX20260208140002', 2006, 4, -50.00, 2, 1, '用户提现-刘小花', '2026-02-08 14:00:00', '2026-02-08 14:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (7, 'TRX20260208150001', 2007, 4, -80.00, 2, 2, '用户提现-陈小明', '2026-02-08 15:00:00', '2026-02-08 15:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (8, 'TRX20260208160001', 2006, 3, -30.00, 3, 103, '订单退款-部分退款', '2026-02-08 16:00:00', '2026-02-08 16:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (9, 'TRX20260125100001', 2006, 2, 85.50, 1, 103, '订单支付-水果超市', '2026-01-25 10:00:00', '2026-01-25 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (10, 'TRX20260130100001', 2007, 2, 32.00, 1, 104, '订单支付-川菜馆', '2026-01-30 10:00:00', '2026-01-30 10:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (11, 'TRX20260128110001', 2008, 2, 65.00, 1, NULL, '订单支付-奶茶店', '2026-01-28 11:00:00', '2026-01-28 11:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (12, 'TRX20251220100001', 2006, 2, 120.00, 1, NULL, '订单支付-校园便利店', '2025-12-20 10:00:00', '2025-12-20 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (13, 'TRX20251225110001', 2007, 2, 95.50, 1, NULL, '订单支付-水果超市', '2025-12-25 11:00:00', '2025-12-25 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (14, 'TRX20251230120001', 2008, 2, 45.00, 1, NULL, '订单支付-奶茶店', '2025-12-30 12:00:00', '2025-12-30 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (15, 'TRX20251115100001', 2006, 2, 78.00, 1, NULL, '订单支付-川菜馆', '2025-11-15 10:00:00', '2025-11-15 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (16, 'TRX20251120110001', 2007, 2, 56.00, 1, NULL, '订单支付-校园便利店', '2025-11-20 11:00:00', '2025-11-20 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (17, 'TRX20251125120001', 2008, 2, 88.50, 1, NULL, '订单支付-水果超市', '2025-11-25 12:00:00', '2025-11-25 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (18, 'TRX20251010100001', 2006, 2, 105.00, 1, NULL, '订单支付-奶茶店', '2025-10-10 10:00:00', '2025-10-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (19, 'TRX20251015110001', 2007, 2, 67.50, 1, NULL, '订单支付-川菜馆', '2025-10-15 11:00:00', '2025-10-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (20, 'TRX20251020120001', 2008, 2, 42.00, 1, NULL, '订单支付-校园便利店', '2025-10-20 12:00:00', '2025-10-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (21, 'TRX20250910100001', 2006, 2, 92.00, 1, NULL, '订单支付-水果超市', '2025-09-10 10:00:00', '2025-09-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (22, 'TRX20250915110001', 2007, 2, 73.00, 1, NULL, '订单支付-奶茶店', '2025-09-15 11:00:00', '2025-09-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (23, 'TRX20250920120001', 2008, 2, 58.50, 1, NULL, '订单支付-川菜馆', '2025-09-20 12:00:00', '2025-09-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (24, 'TRX20250810100001', 2006, 2, 115.00, 1, NULL, '订单支付-校园便利店', '2025-08-10 10:00:00', '2025-08-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (25, 'TRX20250815110001', 2007, 2, 84.00, 1, NULL, '订单支付-水果超市', '2025-08-15 11:00:00', '2025-08-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (26, 'TRX20250820120001', 2008, 2, 49.50, 1, NULL, '订单支付-奶茶店', '2025-08-20 12:00:00', '2025-08-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (27, 'TRX20250710100001', 2006, 2, 98.00, 1, NULL, '订单支付-川菜馆', '2025-07-10 10:00:00', '2025-07-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (28, 'TRX20250715110001', 2007, 2, 76.50, 1, NULL, '订单支付-校园便利店', '2025-07-15 11:00:00', '2025-07-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (29, 'TRX20250720120001', 2008, 2, 52.00, 1, NULL, '订单支付-水果超市', '2025-07-20 12:00:00', '2025-07-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (30, 'TRX20250610100001', 2006, 2, 110.00, 1, NULL, '订单支付-奶茶店', '2025-06-10 10:00:00', '2025-06-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (31, 'TRX20250615110001', 2007, 2, 89.00, 1, NULL, '订单支付-川菜馆', '2025-06-15 11:00:00', '2025-06-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (32, 'TRX20250620120001', 2008, 2, 64.50, 1, NULL, '订单支付-校园便利店', '2025-06-20 12:00:00', '2025-06-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (33, 'TRX20250510100001', 2006, 2, 125.00, 1, NULL, '订单支付-水果超市', '2025-05-10 10:00:00', '2025-05-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (34, 'TRX20250515110001', 2007, 2, 93.50, 1, NULL, '订单支付-奶茶店', '2025-05-15 11:00:00', '2025-05-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (35, 'TRX20250520120001', 2008, 2, 68.00, 1, NULL, '订单支付-川菜馆', '2025-05-20 12:00:00', '2025-05-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (36, 'TRX20250410100001', 2006, 2, 108.00, 1, NULL, '订单支付-校园便利店', '2025-04-10 10:00:00', '2025-04-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (37, 'TRX20250415110001', 2007, 2, 81.00, 1, NULL, '订单支付-水果超市', '2025-04-15 11:00:00', '2025-04-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (38, 'TRX20250420120001', 2008, 2, 55.50, 1, NULL, '订单支付-奶茶店', '2025-04-20 12:00:00', '2025-04-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (39, 'TRX20250310100001', 2006, 2, 118.00, 1, NULL, '订单支付-川菜馆', '2025-03-10 10:00:00', '2025-03-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (40, 'TRX20250315110001', 2007, 2, 86.50, 1, NULL, '订单支付-校园便利店', '2025-03-15 11:00:00', '2025-03-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (41, 'TRX20250320120001', 2008, 2, 61.00, 1, NULL, '订单支付-水果超市', '2025-03-20 12:00:00', '2025-03-20 12:00:00', NULL, '周小丽', '13900139008');
INSERT INTO `finance_transaction` VALUES (42, 'TRX20250210100001', 2006, 2, 132.00, 1, NULL, '订单支付-奶茶店', '2025-02-10 10:00:00', '2025-02-10 10:00:00', NULL, '刘小花', '13900139006');
INSERT INTO `finance_transaction` VALUES (43, 'TRX20250215110001', 2007, 2, 95.00, 1, NULL, '订单支付-川菜馆', '2025-02-15 11:00:00', '2025-02-15 11:00:00', NULL, '陈小明', '13900139007');
INSERT INTO `finance_transaction` VALUES (44, 'TRX20250220120001', 2008, 2, 73.50, 1, NULL, '订单支付-校园便利店', '2025-02-20 12:00:00', '2025-02-20 12:00:00', NULL, '周小丽', '13900139008');

-- ----------------------------
-- Table structure for finance_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `finance_withdrawal`;
CREATE TABLE `finance_withdrawal`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `withdrawal_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现单号',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户姓名（冗余）',
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户电话（冗余）',
  `amount` decimal(15, 2) NOT NULL COMMENT '提现金额',
  `withdraw_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '提现方式:1-微信',
  `withdraw_account` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现账号',
  `withdraw_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现人姓名',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态:0-待审核 1-审核通过 2-审核拒绝 3-打款中 4-打款成功 5-打款失败',
  `audit_id` bigint NOT NULL COMMENT '审核记录ID',
  `pay_operator_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '打款操作人ID',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '打款时间',
  `pay_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打款备注',
  `out_bill_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家单号',
  `transfer_bill_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信转账单号',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单据状态',
  `transaction_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联财务流水ID',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `audit_id`) USING BTREE,
  UNIQUE INDEX `uk_withdrawal_no`(`withdrawal_no` ASC) USING BTREE,
  INDEX `idx_user_status`(`user_id` ASC, `status` ASC, `create_at` DESC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE,
  INDEX `idx_created_at`(`create_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '提现申请表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of finance_withdrawal
-- ----------------------------
INSERT INTO `finance_withdrawal` VALUES (1, 'WD20260208100001', 2006, '刘小花', '13900139006', 50.00, 1, 'wxid_lxh123456', '刘小花', 4, 1001, 1004, '2026-02-08 14:00:00', '提现审核通过，打款成功', 'OUT2026020800001', 'WX2026020814000001', 'SUCCESS', NULL, '2026-02-08 10:00:00', '2026-02-08 14:00:00');
INSERT INTO `finance_withdrawal` VALUES (2, 'WD20260208110002', 2007, '陈小明', '13900139007', 80.00, 1, 'wxid_cxm123456', '陈小明', 4, 1001, 1004, '2026-02-08 15:00:00', '提现审核通过，打款成功', 'OUT2026020800002', 'WX2026020815000001', 'SUCCESS', NULL, '2026-02-08 11:00:00', '2026-02-08 15:00:00');

-- ----------------------------
-- Table structure for payment_account
-- ----------------------------
DROP TABLE IF EXISTS `payment_account`;
CREATE TABLE `payment_account`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户名称',
  `account_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户编码(系统自动生成)',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
  `contact_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `bank_account_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `bank_account_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开户名',
  `bank_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户银行(如:中国工商银行)',
  `bank_branch` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户支行',
  `last_payment_time` datetime NULL DEFAULT NULL COMMENT '最后一次的支付时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_account_code`(`account_code` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_deleted_at`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of payment_account
-- ----------------------------

-- ----------------------------
-- Table structure for payment_record
-- ----------------------------
DROP TABLE IF EXISTS `payment_record`;
CREATE TABLE `payment_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `payment_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '打款单号',
  `payer_account_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '打款账户ID(关联企业账户表)',
  `target_id` bigint UNSIGNED NOT NULL COMMENT '打款目标id',
  `target_type` tinyint NOT NULL COMMENT '打款目标类型 1-商家 2-骑手 3-合伙人',
  `account_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '账户类型(1:银行卡; 2:微信; 3:支付宝)',
  `account_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收款账号',
  `account_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收款人姓名',
  `bank_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_branch` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户支行',
  `payment_amount` decimal(15, 2) NOT NULL DEFAULT 0.00 COMMENT '打款金额',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态:1-待打款 2-已打款 3-打款失败',
  `pay_operator_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '打款操作人',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '打款时间',
  `pay_serial_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '第三方流水号',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_payment_no`(`payment_no` ASC) USING BTREE,
  INDEX `idx_target`(`target_type` ASC, `target_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC, `created_at` DESC) USING BTREE,
  INDEX `idx_payer_account`(`payer_account_id` ASC) USING BTREE,
  INDEX `idx_pay_serial_no`(`pay_serial_no` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '打款记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of payment_record
-- ----------------------------
INSERT INTO `payment_record` VALUES (1, 'PAY20260208130001', 1, 2001, 1, 2, 'wxid_merchant001', '校园便利店', NULL, NULL, 100.00, 2, 1004, '2026-02-08 13:00:00', 'WX2026020813000001', '打款给商家-校园便利店', '2026-02-08 13:00:00', '2026-02-08 13:00:00');
INSERT INTO `payment_record` VALUES (2, 'PAY20260208140002', 1, 2002, 1, 2, 'wxid_merchant002', '川菜馆', NULL, NULL, 150.00, 2, 1004, '2026-02-08 14:00:00', 'WX2026020814000001', '打款给商家-川菜馆', '2026-02-08 14:00:00', '2026-02-08 14:00:00');

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
