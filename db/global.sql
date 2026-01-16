/*
 Navicat Premium Dump SQL

 Source Server         : 本地Mysql
 Source Server Type    : MySQL
 Source Server Version : 80043 (8.0.43)
 Source Host           : localhost:3306
 Source Schema         : campus_service_platform_db

 Target Server Type    : MySQL
 Target Server Version : 80043 (8.0.43)
 File Encoding         : 65001

 Date: 14/01/2026 11:41:21
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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `receiver_lat` decimal(10, 6) NOT NULL COMMENT '收货地址纬度',
  `receiver_lng` decimal(10, 6) NOT NULL COMMENT '收货地址经度',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_delete_time`(`delete_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for app_browsing_history
-- ----------------------------
DROP TABLE IF EXISTS `app_browsing_history`;
CREATE TABLE `app_browsing_history`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '浏览记录ID，自增主键',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID，关联用户表',
  `content_type` tinyint NOT NULL COMMENT '内容类型：1-论坛帖子，2-外卖商家',
  `content_id` bigint UNSIGNED NOT NULL COMMENT '内容ID（论坛帖子ID或商家ID）',
  `created_date` date NOT NULL COMMENT '创建日期，用于分区',
  PRIMARY KEY (`id`, `created_date`) USING BTREE,
  INDEX `idx_content`(`content_type` ASC, `content_id` ASC) USING BTREE,
  INDEX `idx_created_date`(`created_date` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户浏览记录表' ROW_FORMAT = DYNAMIC PARTITION BY RANGE (to_days(`created_date`))
PARTITIONS 4
(PARTITION `p_202512` VALUES LESS THAN (739982) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 ,
PARTITION `p_202601` VALUES LESS THAN (740013) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 ,
PARTITION `p_202602` VALUES LESS THAN (740041) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 ,
PARTITION `p_max` VALUES LESS THAN (MAXVALUE) ENGINE = InnoDB MAX_ROWS = 0 MIN_ROWS = 0 )
;

-- ----------------------------
-- Table structure for app_user_address
-- ----------------------------
DROP TABLE IF EXISTS `app_user_address`;
CREATE TABLE `app_user_address`  (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `address_id` bigint NOT NULL COMMENT '地址id',
  PRIMARY KEY (`user_id`, `address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for audit_record
-- ----------------------------
DROP TABLE IF EXISTS `audit_record`;
CREATE TABLE `audit_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `audit_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核编号',
  `biz_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审核项:MERCHANT_SETTLE-商家入驻 WITHDRAW-提现 GOODS-商品上架 STAFF_APPLY-服务人员 RIDER_APPLY-骑手',
  `applicant_id` bigint UNSIGNED NOT NULL COMMENT '申请人ID',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '审核状态:0-待审核 1-审核通过 2-审核拒绝',
  `auditor_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审核人ID',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_audit_no`(`audit_no` ASC) USING BTREE,
  INDEX `idx_applicant`(`applicant_id` ASC, `status` ASC) USING BTREE,
  INDEX `idx_delete`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '审核记录表' ROW_FORMAT = Dynamic;

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
  `user_type` tinyint UNSIGNED NOT NULL COMMENT '1-系统后台 2-普通用户/服务者 3-商家 4骑手',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登陆时间',
  `last_login_ip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_delete_time`(`delete_at` ASC) USING BTREE,
  INDEX `idx_user_type`(`user_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
-- Table structure for errand_caregory
-- ----------------------------
DROP TABLE IF EXISTS `errand_caregory`;
CREATE TABLE `errand_caregory`  (
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
-- Table structure for finance_transaction
-- ----------------------------
DROP TABLE IF EXISTS `finance_transaction`;
CREATE TABLE `finance_transaction`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `transaction_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流水号',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `transaction_type` tinyint UNSIGNED NOT NULL COMMENT '交易类型:1-打款 2-消费 3-退款 4-提现',
  `amount` decimal(15, 2) NOT NULL COMMENT '交易金额(正数为收入,负数为支出)',
  `related_type` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '关联业务类型:1-订单 2-提现 3-退款',
  `related_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联业务ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_transaction_no`(`transaction_no` ASC) USING BTREE,
  INDEX `idx_type`(`transaction_type` ASC) USING BTREE,
  INDEX `idx_related`(`related_type` ASC, `related_id` ASC) USING BTREE,
  INDEX `idx_create_at`(`create_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '财务流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `finance_withdrawal`;
CREATE TABLE `finance_withdrawal`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `withdrawal_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现单号',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `amount` decimal(15, 2) NOT NULL COMMENT '提现金额',
  `withdraw_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '提现方式:1-微信',
  `withdraw_account` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现账号',
  `withdraw_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提现人姓名',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态:0-待审核 1-审核通过 2-审核拒绝 3-打款中 4-打款成功 5-打款失败',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '审核记录ID',
  `pay_operator_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '打款操作人ID',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '打款时间',
  `pay_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打款备注',
  `out_bill_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家单号',
  `transfer_bill_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信转账单号',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单据状态',
  `transaction_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联财务流水ID',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_withdrawal_no`(`withdrawal_no` ASC) USING BTREE,
  INDEX `idx_user_status`(`user_id` ASC, `status` ASC, `create_at` DESC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE,
  INDEX `idx_created_at`(`create_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '提现申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for forum_activity
-- ----------------------------
DROP TABLE IF EXISTS `forum_activity`;
CREATE TABLE `forum_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `activity_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动标题',
  `activity_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动内容',
  `activity_venue` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动场地',
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
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审批表关联ID(审批活动）',
  `is_visible` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示(1-显示 0-隐藏)',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '活动状态(0-草稿 1-待审核 2-已发布 3-已取消)',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_school`(`school_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_time`(`activity_time` ASC) USING BTREE,
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE,
  INDEX `idx_registration`(`registration_start_time` ASC, `registration_end_time` ASC) USING BTREE,
  INDEX `idx_visible`(`is_visible` ASC) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动表' ROW_FORMAT = DYNAMIC;

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
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(1-正常 2-用户删除 3-管理员删除)',
  `deleted_by` bigint UNSIGNED NULL DEFAULT NULL COMMENT '删除操作人ID',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_activity_time`(`activity_id` ASC, `create_at` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_root`(`root_id` ASC, `level` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for forum_activity_registration
-- ----------------------------
DROP TABLE IF EXISTS `forum_activity_registration`;
CREATE TABLE `forum_activity_registration`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '报名ID',
  `activity_id` bigint UNSIGNED NOT NULL COMMENT '活动ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '报名状态(1-已报名 2-已取消 3-已签到)',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id` ASC, `user_id` ASC, `delete_at` ASC) USING BTREE,
  INDEX `idx_activity`(`activity_id` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动报名表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for forum_announcement
-- ----------------------------
DROP TABLE IF EXISTS `forum_announcement`;
CREATE TABLE `forum_announcement`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告标题',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片URL',
  `is_display` tinyint NOT NULL DEFAULT 1 COMMENT '是否显示:0-不显示,1-显示',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '公告关联学校ID，null为系统公告',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_display`(`is_display` ASC) USING BTREE,
  INDEX `idx_created`(`create_at` ASC) USING BTREE,
  INDEX `idx_school`(`school_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for forum_like_record
-- ----------------------------
DROP TABLE IF EXISTS `forum_like_record`;
CREATE TABLE `forum_like_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '点赞用户ID',
  `like_type` tinyint NOT NULL COMMENT '点赞类型：1=活动，2=帖子，3=活动评论，4=帖子评论',
  `like_id` bigint NOT NULL COMMENT '对应类型的主键ID（如活动ID、帖子ID）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_type_id`(`user_id` ASC, `like_type` ASC, `like_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 466 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通用点赞记录表' ROW_FORMAT = DYNAMIC;

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
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_audit`(`audit_id` ASC) USING BTREE,
  INDEX `idx_time`(`create_at` DESC) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC, `id` ASC) USING BTREE,
  INDEX `idx_listhot`(`like_count` DESC, `share_count` DESC, `favorite_count` DESC) USING BTREE,
  CONSTRAINT `chk_counts` CHECK ((`view_count` >= 0) and (`like_count` >= 0))
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子表' ROW_FORMAT = DYNAMIC;

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
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(1-正常 2-用户删除 3-管理员删除)',
  `deleted_by` bigint UNSIGNED NULL DEFAULT NULL COMMENT '删除操作人ID',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_post_time`(`post_id` ASC, `create_at` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_root`(`root_id` ASC, `level` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子评论表' ROW_FORMAT = DYNAMIC;

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
-- Table structure for mch_product_category
-- ----------------------------
DROP TABLE IF EXISTS `mch_product_category`;
CREATE TABLE `mch_product_category`  (
  `product_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `category_id` bigint UNSIGNED NOT NULL COMMENT '商品分类id',
  PRIMARY KEY (`product_id`, `category_id`) USING BTREE,
  INDEX `idx_category_id`(`category_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商家-商品分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mch_product_spec
-- ----------------------------
DROP TABLE IF EXISTS `mch_product_spec`;
CREATE TABLE `mch_product_spec`  (
  `product_id` bigint UNSIGNED NOT NULL COMMENT '商品id',
  `spec_id` bigint UNSIGNED NOT NULL COMMENT '规格id',
  PRIMARY KEY (`product_id`, `spec_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品-规格中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mch_spec
-- ----------------------------
DROP TABLE IF EXISTS `mch_spec`;
CREATE TABLE `mch_spec`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` bigint UNSIGNED NOT NULL COMMENT '商品ID',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品规格表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `sender_id` bigint NOT NULL COMMENT '发送者用户ID，0表示系统通知',
  `sender_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '发送者类型：1系统/2管理员/3用户/4骑手/5商家',
  `receiver_id` bigint NULL DEFAULT NULL COMMENT '接收者用户ID',
  `receiver_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '接收者类型：1用户/2骑手/3商家',
  `message_title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息标题',
  `message_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息内容',
  `message_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'system' COMMENT '消息类型：system系统通知/remind提醒/private私信',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_receiver_id`(`receiver_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_sender_type`(`sender_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '站内消息表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外卖订单扩展表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_errand
-- ----------------------------
DROP TABLE IF EXISTS `order_errand`;
CREATE TABLE `order_errand`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` bigint UNSIGNED NOT NULL COMMENT '订单主表ID',
  `service_fee` decimal(10, 2) NOT NULL COMMENT '服务费',
  `service_type_id` tinyint NOT NULL COMMENT '服务分类id',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '跑腿订单扩展表' ROW_FORMAT = Dynamic;

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
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态:0-待支付 1-已支付 2-已退款 3-全额退款',
  `pay_method` tinyint NULL DEFAULT NULL COMMENT '支付方式:1-在线支付 2-微信 3-其他',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `pay_channel_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '第三方支付流水号',
  `order_status` tinyint NOT NULL DEFAULT 1 COMMENT '订单状态:1-待支付 2-待接单 3-待取货 4-配送中 5-已送达 6-已取消 6-售后中',
  `cancel_type` tinyint NULL DEFAULT NULL COMMENT '取消类型:1-用户取消 2-商家取消 3-超时取消',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `service_provider_type` tinyint NULL DEFAULT NULL COMMENT '服务提供方类型:1-商家 2-服务人员',
  `service_provider_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '服务提供方ID',
  `service_provider_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务提供方名称',
  `school_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '学校ID',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单主表' ROW_FORMAT = Dynamic;

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
-- Table structure for payment_record
-- ----------------------------
DROP TABLE IF EXISTS `payment_record`;
CREATE TABLE `payment_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `payment_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '打款单号',
  `payer_account_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '打款账户ID(关联企业账户表)',
  `target_id` bigint UNSIGNED NOT NULL COMMENT '打款目标id',
  `target_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '打款目标类型',
  `account_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '账户类型(1:银行卡; 2:微信; 3:支付宝)',
  `account_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收款账号',
  `account_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收款人姓名',
  `bank_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_branch` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户支行',
  `payment_amount` decimal(15, 2) NOT NULL DEFAULT 0.00 COMMENT '打款金额',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态:0-待确认 1-待打款 2-已打款 3-打款失败',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '打款记录表' ROW_FORMAT = Dynamic;

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
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

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
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `dict_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典键',
  `dict_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用,1-启用',
  `level` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '层级 1-一级 2-二级',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 194 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表' ROW_FORMAT = Dynamic;

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
  `is_frame` tinyint NULL DEFAULT 1 COMMENT '0:是外链 1:站内路由',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组件路径',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问路径',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-隐藏,1-显示',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 301010102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '终端信息表' ROW_FORMAT = Dynamic;

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
-- Table structure for user_app
-- ----------------------------
DROP TABLE IF EXISTS `user_app`;
CREATE TABLE `user_app`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `base_user_id` bigint UNSIGNED NULL DEFAULT NULL,
  `gender` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未知 1-男 2-女',
  `openid` char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_mch
-- ----------------------------
DROP TABLE IF EXISTS `user_mch`;
CREATE TABLE `user_mch`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `base_user_id` bigint UNSIGNED NOT NULL,
  `mch_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商户名',
  `logo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商户logo',
  `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `business_license_urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营业执照路径，以\",\"分割',
  `partner_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '合伙人id',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `minimum_order_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最低起送金额(默认0)',
  `audit_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '审查表id（审查商户资格）',
  `business_start_time` time NULL DEFAULT NULL COMMENT '开始营业时间',
  `business_end_time` time NULL DEFAULT NULL COMMENT '结束营业时间',
  `is_open` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否营业 0-休息 1-营业（默认休息）',
  `address_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联表地址id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_user_id`(`base_user_id` ASC) USING BTREE,
  INDEX `idx_delete_time`(`delete_time` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE,
  INDEX `idx_is_open`(`is_open` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_partner
-- ----------------------------
DROP TABLE IF EXISTS `user_partner`;
CREATE TABLE `user_partner`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '合伙人ID',
  `partner_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '合伙人姓名',
  `invite_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推广码(邀请合伙人)',
  `invite_code_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推广码相对路径',
  `card_number` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行卡号',
  `commission_rate` decimal(5, 2) NOT NULL DEFAULT 0.00 COMMENT '分佣比例(%)',
  `parent_id` bigint UNSIGNED NULL DEFAULT 0 COMMENT '上级合伙人ID(0代表无上级)',
  `audit_id` bigint NULL DEFAULT NULL COMMENT '关联的审核表id',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态:1启用 0禁用',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`invite_code` ASC) USING BTREE,
  INDEX `idx_deleted`(`delete_at` ASC, `id` ASC) USING BTREE,
  CONSTRAINT `chk_partner_rate` CHECK ((`commission_rate` >= 0) and (`commission_rate` <= 100))
) ENGINE = InnoDB AUTO_INCREMENT = 20012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '合伙人表' ROW_FORMAT = DYNAMIC;

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
  `commission_total` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计收益',
  `address_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '联表地址id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_user_id`(`base_user_id` ASC) USING BTREE,
  UNIQUE INDEX `idx_openid`(`openid` ASC) USING BTREE,
  INDEX `idx_delete_time`(`delete_time` ASC) USING BTREE,
  INDEX `idx_audit_id`(`audit_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_sys
-- ----------------------------
DROP TABLE IF EXISTS `user_sys`;
CREATE TABLE `user_sys`  (
  `id` bigint NOT NULL,
  `base_user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_user_id`(`base_user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
