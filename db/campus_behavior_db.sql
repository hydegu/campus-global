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

 Date: 28/01/2026 14:51:13
*/

-- ----------------------------
-- Table structure for app_file
-- ----------------------------
DROP TABLE IF EXISTS `app_file`;
CREATE TABLE `app_file` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存储文件名',
  `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '原始文件名',
  `file_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件存储路径',
  `file_size` bigint NOT NULL COMMENT '文件大小（字节）',
  `file_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件类型（MIME类型）',
  `file_extension` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件扩展名',
  `bucket_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存储桶名称',
  `upload_user_id` bigint NOT NULL COMMENT '上传用户ID',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务类型（avatar-头像，content_image-内容图片，attachment-附件等）',
  `business_id` bigint NULL DEFAULT NULL COMMENT '业务ID（关联的业务记录ID）',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-已删除，1-正常',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_upload_user`(`upload_user_id` ASC) USING BTREE COMMENT '上传用户索引',
  INDEX `idx_business`(`business_type` ASC, `business_id` ASC) USING BTREE COMMENT '业务关联索引',
  INDEX `idx_bucket`(`bucket_name` ASC) USING BTREE COMMENT '存储桶索引',
  INDEX `idx_delete`(`delete_at` ASC) USING BTREE COMMENT '删除时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '行为模块文件信息表' ROW_FORMAT = DYNAMIC;