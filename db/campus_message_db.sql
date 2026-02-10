-- 创建数据库
CREATE DATABASE IF NOT EXISTS `campus_message_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `campus_message_db`;

/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 90500 (9.5.0)
 Source Host           : 49.232.245.147:3306
 Source Schema         : campus_message_db

 Target Server Type    : MySQL
 Target Server Version : 90500 (9.5.0)
 File Encoding         : 65001

 Date: 10/02/2026 15:27:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `sender_id` bigint NOT NULL COMMENT '发送者用户ID，0表示系统通知',
  `sender_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '发送者类型：1系统/2管理员/3用户/4骑手/5商家/6合伙人',
  `receiver_id` bigint NULL DEFAULT NULL COMMENT '接收者用户ID',
  `receiver_type` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '接收者类型：1用户/2骑手/3商家/4合伙人',
  `message_title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息标题',
  `message_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息内容',
  `message_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'system' COMMENT '消息类型：system系统通知/remind提醒/private私信',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_receiver_id`(`receiver_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_sender_type`(`sender_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 127 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '站内消息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (2, 0, 1, 1002, 1, '新功能上线', '校园配送系统已上线新功能，欢迎体验！', 'system', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (3, 0, 1, 2001, 2, '骑手认证提醒', '请及时完成骑手认证，以免影响接单。', 'system', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (4, 100, 2, 1001, 1, '订单提醒', '您的订单已发货，请注意查收。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (5, 100, 2, 1002, 1, '活动提醒', '校园美食节活动即将开始，欢迎参与！', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (6, 100, 2, 2001, 2, '配送提醒', '您有新的配送任务，请及时处理。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (7, 1001, 3, 1002, 1, '你好', '请问这个商品还有库存吗？', 'private', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (8, 1002, 3, 1001, 1, '回复', '有的，您可以下单购买。', 'private', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (9, 1001, 3, 1003, 1, '团购邀请', '要不要一起团购这个商品？', 'private', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (10, 2001, 4, 1001, 1, '配送提醒', '您的订单已送达，请确认收货。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (11, 2001, 4, 1001, 1, '联系', '请问您在家吗？', 'private', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (12, 2002, 4, 1002, 1, '配送提醒', '订单正在配送中，预计10分钟到达。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (13, 3001, 5, 1001, 1, '优惠提醒', '今日全场8折，欢迎选购！', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (14, 3001, 5, 1002, 1, '新品推荐', '我们推出了新品奶茶，快来尝尝吧！', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (15, 3001, 5, 1001, 1, '咨询', '请问您对商品满意吗？', 'private', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (16, 4001, 6, 1001, 1, '合作提醒', '新的合作项目即将启动，请做好准备。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (17, 4001, 6, 2001, 2, '任务提醒', '本周配送任务已分配，请查看详情。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (18, 4001, 6, 1002, 1, '沟通', '关于合作事宜，方便聊聊吗？', 'private', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (19, 100, 2, 1001, 1, '这是一个非常长的消息标题用于测试系统对长标题的处理能力，确保在边界条件下系统仍然能够正常工作', '这是一个非常长的消息内容，用于测试系统对长内容的处理能力。在实际应用中，消息内容可能会包含大量的文本信息，包括订单详情、活动介绍、产品说明等等。系统需要能够正确处理这些长文本内容，确保不会出现截断、乱码等问题。同时，还需要考虑性能问题，确保在处理大量长文本时系统仍然能够保持良好的响应速度。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (20, 100, 2, 1001, 1, '特殊字符测试！@#$%^&*()', '测试内容：<>&\"\'以及中文、English、日本語、한국어等不同语言的字符。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (21, 100, 2, 1001, 1, '  空格测试  ', '第一行内容\r\n第二行内容\r\n第三行内容', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (22, 0, 1, 1001, 1, '历史消息1', '这是一条历史消息。', 'system', '2026-01-12 02:27:37');
INSERT INTO `message` VALUES (23, 0, 1, 1001, 1, '历史消息2', '这是一条历史消息。', 'system', '2026-01-16 02:27:37');
INSERT INTO `message` VALUES (24, 0, 1, 1001, 1, '今天消息', '这是一条今天的消息。', 'system', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (25, 0, 1, 1001, 1, '未来消息', '这是一条未来的消息（测试用）。', 'system', '2026-01-20 02:27:37');
INSERT INTO `message` VALUES (26, 100, 2, 1001, 1, '用户消息', '发送给普通用户的消息。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (27, 100, 2, 2001, 2, '骑手消息', '发送给骑手的消息。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (28, 100, 2, 3001, 3, '商家消息', '发送给商家的消息。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (29, 100, 2, 4001, 4, '合伙人消息', '发送给合伙人的消息。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (30, 101, 2, 1001, 1, '测试消息1', '这是第1条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (31, 102, 2, 1001, 1, '测试消息2', '这是第2条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (32, 103, 2, 1001, 1, '测试消息3', '这是第3条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (33, 104, 2, 1001, 1, '测试消息4', '这是第4条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (34, 105, 2, 1001, 1, '测试消息5', '这是第5条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (35, 106, 2, 1001, 1, '测试消息6', '这是第6条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (36, 107, 2, 1001, 1, '测试消息7', '这是第7条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (37, 108, 2, 1001, 1, '测试消息8', '这是第8条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (38, 109, 2, 1001, 1, '测试消息9', '这是第9条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (39, 110, 2, 1001, 1, '测试消息10', '这是第10条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (40, 111, 2, 1001, 1, '测试消息11', '这是第11条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (41, 112, 2, 1001, 1, '测试消息12', '这是第12条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (42, 113, 2, 1001, 1, '测试消息13', '这是第13条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (43, 114, 2, 1001, 1, '测试消息14', '这是第14条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (44, 115, 2, 1001, 1, '测试消息15', '这是第15条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (45, 116, 2, 1001, 1, '测试消息16', '这是第16条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (46, 117, 2, 1001, 1, '测试消息17', '这是第17条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (47, 118, 2, 1001, 1, '测试消息18', '这是第18条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (48, 119, 2, 1001, 1, '测试消息19', '这是第19条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (49, 120, 2, 1001, 1, '测试消息20', '这是第20条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (50, 121, 2, 1001, 1, '测试消息21', '这是第21条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (51, 122, 2, 1001, 1, '测试消息22', '这是第22条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (52, 123, 2, 1001, 1, '测试消息23', '这是第23条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (53, 124, 2, 1001, 1, '测试消息24', '这是第24条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (54, 125, 2, 1001, 1, '测试消息25', '这是第25条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:27:37');
INSERT INTO `message` VALUES (61, 0, 1, 1001, 1, '系统通知测试', '这是一条系统通知测试消息。', 'system', '2026-01-19 02:27:38');
INSERT INTO `message` VALUES (62, 100, 2, 1001, 1, '提醒测试', '这是一条提醒测试消息。', 'remind', '2026-01-19 02:27:38');
INSERT INTO `message` VALUES (63, 1001, 3, 1002, 1, '私信测试', '这是一条私信测试消息。', 'private', '2026-01-19 02:27:38');
INSERT INTO `message` VALUES (64, 0, 1, 1001, 1, '系统维护通知', '系统将于今晚22:00-24:00进行维护，请提前保存数据。', 'system', '2026-01-19 02:28:10');
INSERT INTO `message` VALUES (65, 0, 1, 1002, 1, '新功能上线', '校园配送系统已上线新功能，欢迎体验！', 'system', '2026-01-19 02:28:10');
INSERT INTO `message` VALUES (66, 0, 1, 2001, 2, '骑手认证提醒', '请及时完成骑手认证，以免影响接单。', 'system', '2026-01-19 02:28:10');
INSERT INTO `message` VALUES (67, 100, 2, 1001, 1, '订单提醒', '您的订单已发货，请注意查收。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (68, 100, 2, 1002, 1, '活动提醒', '校园美食节活动即将开始，欢迎参与！', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (69, 100, 2, 2001, 2, '配送提醒', '您有新的配送任务，请及时处理。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (70, 1001, 3, 1002, 1, '你好', '请问这个商品还有库存吗？', 'private', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (71, 1002, 3, 1001, 1, '回复', '有的，您可以下单购买。', 'private', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (72, 1001, 3, 1003, 1, '团购邀请', '要不要一起团购这个商品？', 'private', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (73, 2001, 4, 1001, 1, '配送提醒', '您的订单已送达，请确认收货。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (74, 2001, 4, 1001, 1, '联系', '请问您在家吗？', 'private', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (75, 2002, 4, 1002, 1, '配送提醒', '订单正在配送中，预计10分钟到达。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (76, 3001, 5, 1001, 1, '优惠提醒', '今日全场8折，欢迎选购！', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (77, 3001, 5, 1002, 1, '新品推荐', '我们推出了新品奶茶，快来尝尝吧！', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (78, 3001, 5, 1001, 1, '咨询', '请问您对商品满意吗？', 'private', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (79, 4001, 6, 1001, 1, '合作提醒', '新的合作项目即将启动，请做好准备。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (80, 4001, 6, 2001, 2, '任务提醒', '本周配送任务已分配，请查看详情。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (81, 4001, 6, 1002, 1, '沟通', '关于合作事宜，方便聊聊吗？', 'private', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (82, 100, 2, 1001, 1, '这是一个非常长的消息标题用于测试系统对长标题的处理能力，确保在边界条件下系统仍然能够正常工作', '这是一个非常长的消息内容，用于测试系统对长内容的处理能力。在实际应用中，消息内容可能会包含大量的文本信息，包括订单详情、活动介绍、产品说明等等。系统需要能够正确处理这些长文本内容，确保不会出现截断、乱码等问题。同时，还需要考虑性能问题，确保在处理大量长文本时系统仍然能够保持良好的响应速度。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (83, 100, 2, 1001, 1, '特殊字符测试！@#$%^&*()', '测试内容：<>&\"\'以及中文、English、日本語、한국어等不同语言的字符。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (84, 100, 2, 1001, 1, '  空格测试  ', '第一行内容\r\n第二行内容\r\n第三行内容', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (85, 0, 1, 1001, 1, '历史消息1', '这是一条历史消息。', 'system', '2026-01-12 02:28:11');
INSERT INTO `message` VALUES (86, 0, 1, 1001, 1, '历史消息2', '这是一条历史消息。', 'system', '2026-01-16 02:28:11');
INSERT INTO `message` VALUES (87, 0, 1, 1001, 1, '今天消息', '这是一条今天的消息。', 'system', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (88, 0, 1, 1001, 1, '未来消息', '这是一条未来的消息（测试用）。', 'system', '2026-01-20 02:28:11');
INSERT INTO `message` VALUES (89, 100, 2, 1001, 1, '用户消息', '发送给普通用户的消息。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (90, 100, 2, 2001, 2, '骑手消息', '发送给骑手的消息。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (91, 100, 2, 3001, 3, '商家消息', '发送给商家的消息。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (92, 100, 2, 4001, 4, '合伙人消息', '发送给合伙人的消息。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (93, 101, 2, 1001, 1, '测试消息1', '这是第1条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (94, 102, 2, 1001, 1, '测试消息2', '这是第2条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (95, 103, 2, 1001, 1, '测试消息3', '这是第3条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (96, 104, 2, 1001, 1, '测试消息4', '这是第4条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (97, 105, 2, 1001, 1, '测试消息5', '这是第5条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (98, 106, 2, 1001, 1, '测试消息6', '这是第6条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (99, 107, 2, 1001, 1, '测试消息7', '这是第7条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (100, 108, 2, 1001, 1, '测试消息8', '这是第8条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (101, 109, 2, 1001, 1, '测试消息9', '这是第9条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (102, 110, 2, 1001, 1, '测试消息10', '这是第10条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (103, 111, 2, 1001, 1, '测试消息11', '这是第11条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (104, 112, 2, 1001, 1, '测试消息12', '这是第12条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (105, 113, 2, 1001, 1, '测试消息13', '这是第13条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (106, 114, 2, 1001, 1, '测试消息14', '这是第14条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (107, 115, 2, 1001, 1, '测试消息15', '这是第15条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (108, 116, 2, 1001, 1, '测试消息16', '这是第16条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (109, 117, 2, 1001, 1, '测试消息17', '这是第17条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (110, 118, 2, 1001, 1, '测试消息18', '这是第18条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (111, 119, 2, 1001, 1, '测试消息19', '这是第19条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (112, 120, 2, 1001, 1, '测试消息20', '这是第20条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (113, 121, 2, 1001, 1, '测试消息21', '这是第21条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (114, 122, 2, 1001, 1, '测试消息22', '这是第22条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (115, 123, 2, 1001, 1, '测试消息23', '这是第23条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (116, 124, 2, 1001, 1, '测试消息24', '这是第24条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (117, 125, 2, 1001, 1, '测试消息25', '这是第25条测试消息，用于测试分页查询功能。', 'remind', '2026-01-19 02:28:11');
INSERT INTO `message` VALUES (124, 0, 1, 1001, 1, '系统通知测试', '这是一条系统通知测试消息。', 'system', '2026-01-19 02:28:12');
INSERT INTO `message` VALUES (125, 100, 2, 1001, 1, '提醒测试', '这是一条提醒测试消息。', 'remind', '2026-01-19 02:28:12');
INSERT INTO `message` VALUES (126, 1001, 3, 1002, 1, '私信测试', '这是一条私信测试消息。', 'private', '2026-01-19 02:28:12');

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
