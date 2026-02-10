/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_behavior_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 10/02/2026 15:26:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_browsing_history
-- ----------------------------
DROP TABLE IF EXISTS `app_browsing_history`;
CREATE TABLE `app_browsing_history`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '浏览记录ID，自增主键',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID，关联用户表',
  `content_type` tinyint NOT NULL COMMENT '内容类型：1-论坛帖子，2-外卖商家',
  `content_id` bigint UNSIGNED NOT NULL COMMENT '内容ID（论坛帖子ID或商家ID）',
  `content_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容标题（冗余）',
  `content_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容图片（冗余）',
  `content_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容描述（冗余）',
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
-- Records of app_browsing_history
-- ----------------------------

-- ----------------------------
-- Table structure for app_file
-- ----------------------------
DROP TABLE IF EXISTS `app_file`;
CREATE TABLE `app_file`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存储文件名',
  `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `file_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件存储路径',
  `file_size` bigint NULL DEFAULT 0 COMMENT '文件大小（字节）',
  `file_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型（MIME类型）',
  `file_extension` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `bucket_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储桶名称',
  `upload_user_id` bigint NULL DEFAULT NULL COMMENT '上传用户ID',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务类型（avatar-头像，content_image-内容图片，attachment-附件等）',
  `business_id` bigint NULL DEFAULT NULL COMMENT '业务ID（关联的业务记录ID）',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态：0-已删除，1-正常',
  `create_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_business`(`business_type` ASC, `business_id` ASC) USING BTREE COMMENT '业务关联索引',
  INDEX `idx_upload_user`(`upload_user_id` ASC) USING BTREE COMMENT '用户上传索引'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of app_file
-- ----------------------------
INSERT INTO `app_file` VALUES (1, 'd813f29a038444f4945d33a3224f7b61.png', '设置.png', 'default/2026/02/05/d813f29a038444f4945d33a3224f7b61.png', 6367, 'image/png', 'png', 'campus-default', 1001, NULL, NULL, 1, '2026-02-05 17:35:12', '2026-02-05 17:35:12', NULL);
INSERT INTO `app_file` VALUES (2, 'c312e3f0922b4df39a168605f8e1682d.png', '设置.png', 'default/2026/02/05/c312e3f0922b4df39a168605f8e1682d.png', 6367, 'image/png', 'png', 'campus-default', 1001, NULL, NULL, 1, '2026-02-05 17:48:41', '2026-02-05 17:48:41', NULL);
INSERT INTO `app_file` VALUES (3, '7aa3aaa07cdf4104990cb74fb616a6ec.png', '设置.png', 'default/2026/02/05/7aa3aaa07cdf4104990cb74fb616a6ec.png', 6367, 'image/png', 'png', 'campus-default', 1001, NULL, NULL, 1, '2026-02-05 18:07:21', '2026-02-05 18:07:21', NULL);
INSERT INTO `app_file` VALUES (4, '0abf36c3aeaf4af0abb4a3105110b203.png', '设置.png', 'default/2026/02/05/0abf36c3aeaf4af0abb4a3105110b203.png', 6367, 'image/png', 'png', 'campus-default', 1001, NULL, NULL, 1, '2026-02-05 18:13:24', '2026-02-05 18:13:24', NULL);
INSERT INTO `app_file` VALUES (5, 'fdbf579ff9dc4441a6032bddbc5ff129.png', '设置.png', 'default/2026/02/05/fdbf579ff9dc4441a6032bddbc5ff129.png', 6367, 'image/png', 'png', 'campus-default', 1001, NULL, NULL, 1, '2026-02-05 20:34:38', '2026-02-05 20:34:38', NULL);

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
