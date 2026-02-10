/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_forum_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 10/02/2026 15:27:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for forum_activity
-- ----------------------------
DROP TABLE IF EXISTS `forum_activity`;
CREATE TABLE `forum_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `activity_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动标题',
  `activity_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动内容',
  `activity_venue` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动场地',
  `publisher_id` bigint NULL DEFAULT NULL COMMENT '发布人Id',
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
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_activity
-- ----------------------------
INSERT INTO `forum_activity` VALUES (24, '校园歌手大赛', '校园歌手大赛即将开始，欢迎同学们踊跃报名参加！', '大学生活动中心', 1001, 1003, 100, 1, 0, 0, 3, '2023-09-01 00:00:00', '2026-09-30 00:00:00', '2026-10-15 00:00:00', '[\"/uploads/2025-12-08/659e21fb40ce4ff4acba950200217bcc.png\",\"/uploads/2025-12-08/91be451eae9d4c24a1048a642053168c.webp\"]', 2015710533714206721, 1, 2, '2026-01-26 08:56:57', '2026-01-28 14:22:39', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_activity_comment
-- ----------------------------
INSERT INTO `forum_activity_comment` VALUES (4, 21, 1001, '评论内容', 0, 4, 0, 0, 0, 2, 1001, '2026-01-21 11:18:56', '2026-01-21 11:18:56', '2026-01-22 11:56:04');
INSERT INTO `forum_activity_comment` VALUES (5, 21, 1001, '评论内容', 0, 5, 0, 0, 0, 1, NULL, '2026-01-22 03:27:46', '2026-01-22 03:27:46', NULL);

-- ----------------------------
-- Table structure for forum_activity_registration
-- ----------------------------
DROP TABLE IF EXISTS `forum_activity_registration`;
CREATE TABLE `forum_activity_registration`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '报名ID',
  `activity_id` bigint UNSIGNED NOT NULL COMMENT '活动ID',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '报名状态(1-已报名 2-已取消 3-已签到)',
  `audit_result` int NULL DEFAULT NULL COMMENT '审核状态（0-待审核 1-通过 2-未通过）',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '软删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id` ASC, `user_id` ASC, `delete_at` ASC) USING BTREE,
  INDEX `idx_activity`(`activity_id` ASC) USING BTREE,
  INDEX `idx_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '活动报名表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_activity_registration
-- ----------------------------
INSERT INTO `forum_activity_registration` VALUES (8, 24, 1001, 1, NULL, '2026-01-27 06:44:10', '2026-01-28 11:59:28', NULL);

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
-- Records of forum_announcement
-- ----------------------------

-- ----------------------------
-- Table structure for forum_like_record
-- ----------------------------
DROP TABLE IF EXISTS `forum_like_record`;
CREATE TABLE `forum_like_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '点赞用户ID',
  `like_type` tinyint NOT NULL COMMENT '点赞类型：1=活动，2=帖子，3=活动评论，4=帖子评论',
  `like_id` bigint NOT NULL COMMENT '对应类型的主键ID（如活动ID、帖子ID）',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_type_id`(`user_id` ASC, `like_type` ASC, `like_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 470 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通用点赞记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_like_record
-- ----------------------------
INSERT INTO `forum_like_record` VALUES (469, 1001, 1, 24, '2026-01-28 06:22:39', '2026-01-28 06:22:39', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_post
-- ----------------------------
INSERT INTO `forum_post` VALUES (47, 1001, '校园生活的美好时光', '今天在校园里遇到了很多有趣的事情...', NULL, NULL, 1, 0, 0, 0, 1, NULL, '2026-01-19 11:38:09', '2026-01-28 06:24:31', NULL);
INSERT INTO `forum_post` VALUES (48, 1001, '校园生活的美好时光', '今天在校园里遇到了很多有趣的事情...', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2026-01-20 09:08:23', '2026-01-20 09:08:23', NULL);
INSERT INTO `forum_post` VALUES (49, 1001, '校园生活的美好时光', '今天在校园里遇到了很多有趣的事情...', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2026-01-28 06:23:31', '2026-01-28 06:23:31', NULL);
INSERT INTO `forum_post` VALUES (50, 1001, '校园生活的美好时光', '今天在校园里遇到了很多有趣的事情...', NULL, NULL, 1, 0, 0, 0, 1, NULL, '2026-01-28 06:24:51', '2026-01-28 07:16:05', NULL);
INSERT INTO `forum_post` VALUES (51, 1001, '校园生活的美好时光', '今天在校园里遇到了', NULL, NULL, 0, 0, 0, 0, 0, NULL, '2026-01-28 07:14:58', '2026-01-28 07:14:58', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum_post_comment
-- ----------------------------
INSERT INTO `forum_post_comment` VALUES (51, 47, 1001, '评论内容', 0, 51, 0, 0, 1, 1, NULL, NULL, '2026-01-20 09:08:43', '2026-01-22 19:47:24');
INSERT INTO `forum_post_comment` VALUES (52, 47, 1001, '评论内容', 51, 51, 1, 0, 0, 1, NULL, NULL, '2026-01-22 03:22:27', '2026-01-22 03:22:27');
INSERT INTO `forum_post_comment` VALUES (53, 47, 1001, '评论内容', 52, 51, 2, 0, 0, 2, 1001, '2026-01-28 14:24:32', '2026-01-22 03:23:04', '2026-01-22 03:23:04');
INSERT INTO `forum_post_comment` VALUES (54, 50, 1001, '评论内容', 0, 54, 0, 0, 0, 1, NULL, NULL, '2026-01-28 07:16:05', '2026-01-28 07:16:05');

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
