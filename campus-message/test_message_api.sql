-- ========================================
-- Message服务测试SQL语句
-- 用途：测试message服务的各种接口场景
-- 包含：正常流程、边界条件、异常情况
-- ========================================

-- ========================================
-- 第一部分：创建数据库和表结构
-- ========================================

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS campus_message_test DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE campus_message_test;

-- 创建消息表
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `sender_id` bigint NOT NULL COMMENT '发送者用户ID，0表示系统通知',
  `sender_type` int NOT NULL COMMENT '发送者类型：1系统/2管理员/3用户/4骑手/5商家/6合伙人',
  `receiver_id` bigint NOT NULL COMMENT '接收者用户ID',
  `receiver_type` int NOT NULL COMMENT '接收者类型：1用户/2骑手/3商家/4合伙人',
  `message_title` varchar(200) NOT NULL COMMENT '消息标题',
  `message_content` text NOT NULL COMMENT '消息内容',
  `message_type` varchar(20) NOT NULL COMMENT '消息类型：system系统通知/remind提醒/private私信',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`),
  KEY `idx_receiver_id` (`receiver_id`),
  KEY `idx_sender_type` (`sender_type`),
  KEY `idx_message_type` (`message_type`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='站内消息表';

-- ========================================
-- 第二部分：插入测试数据
-- ========================================

-- 2.1 正常流程测试数据
-- 场景1：系统发送系统通知（sender_id=0, sender_type=1, message_type=system）
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(0, 1, 1001, 1, '系统维护通知', '系统将于今晚22:00-24:00进行维护，请提前保存数据。', 'system', NOW()),
(0, 1, 1002, 1, '新功能上线', '校园配送系统已上线新功能，欢迎体验！', 'system', NOW()),
(0, 1, 2001, 2, '骑手认证提醒', '请及时完成骑手认证，以免影响接单。', 'system', NOW());

-- 场景2：管理员发送提醒消息（sender_type=2, message_type=remind）
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(100, 2, 1001, 1, '订单提醒', '您的订单已发货，请注意查收。', 'remind', NOW()),
(100, 2, 1002, 1, '活动提醒', '校园美食节活动即将开始，欢迎参与！', 'remind', NOW()),
(100, 2, 2001, 2, '配送提醒', '您有新的配送任务，请及时处理。', 'remind', NOW());

-- 场景3：用户发送私信（sender_type=3, message_type=private）
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(1001, 3, 1002, 1, '你好', '请问这个商品还有库存吗？', 'private', NOW()),
(1002, 3, 1001, 1, '回复', '有的，您可以下单购买。', 'private', NOW()),
(1001, 3, 1003, 1, '团购邀请', '要不要一起团购这个商品？', 'private', NOW());

-- 场景4：骑手发送提醒和私信（sender_type=4, message_type=remind/private）
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(2001, 4, 1001, 1, '配送提醒', '您的订单已送达，请确认收货。', 'remind', NOW()),
(2001, 4, 1001, 1, '联系', '请问您在家吗？', 'private', NOW()),
(2002, 4, 1002, 1, '配送提醒', '订单正在配送中，预计10分钟到达。', 'remind', NOW());

-- 场景5：商家发送提醒和私信（sender_type=5, message_type=remind/private）
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(3001, 5, 1001, 1, '优惠提醒', '今日全场8折，欢迎选购！', 'remind', NOW()),
(3001, 5, 1002, 1, '新品推荐', '我们推出了新品奶茶，快来尝尝吧！', 'remind', NOW()),
(3001, 5, 1001, 1, '咨询', '请问您对商品满意吗？', 'private', NOW());

-- 场景6：合伙人发送提醒和私信（sender_type=6, message_type=remind/private）
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(4001, 6, 1001, 1, '合作提醒', '新的合作项目即将启动，请做好准备。', 'remind', NOW()),
(4001, 6, 2001, 2, '任务提醒', '本周配送任务已分配，请查看详情。', 'remind', NOW()),
(4001, 6, 1002, 1, '沟通', '关于合作事宜，方便聊聊吗？', 'private', NOW());

-- 2.2 边界条件测试数据
-- 场景7：长标题和长内容测试
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(100, 2, 1001, 1, '这是一个非常长的消息标题用于测试系统对长标题的处理能力，确保在边界条件下系统仍然能够正常工作', '这是一个非常长的消息内容，用于测试系统对长内容的处理能力。在实际应用中，消息内容可能会包含大量的文本信息，包括订单详情、活动介绍、产品说明等等。系统需要能够正确处理这些长文本内容，确保不会出现截断、乱码等问题。同时，还需要考虑性能问题，确保在处理大量长文本时系统仍然能够保持良好的响应速度。', 'remind', NOW());

-- 场景8：特殊字符测试
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(100, 2, 1001, 1, '特殊字符测试！@#$%^&*()', '测试内容：<>&"''以及中文、English、日本語、한국어等不同语言的字符。', 'remind', NOW());

-- 场景9：空格和换行测试
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(100, 2, 1001, 1, '  空格测试  ', '第一行内容
第二行内容
第三行内容', 'remind', NOW());

-- 2.3 时间范围测试数据
-- 场景10：不同时间的消息（用于时间范围查询测试）
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(0, 1, 1001, 1, '历史消息1', '这是一条历史消息。', 'system', DATE_SUB(NOW(), INTERVAL 7 DAY)),
(0, 1, 1001, 1, '历史消息2', '这是一条历史消息。', 'system', DATE_SUB(NOW(), INTERVAL 3 DAY)),
(0, 1, 1001, 1, '今天消息', '这是一条今天的消息。', 'system', NOW()),
(0, 1, 1001, 1, '未来消息', '这是一条未来的消息（测试用）。', 'system', DATE_ADD(NOW(), INTERVAL 1 DAY));

-- 2.4 不同接收者类型测试数据
-- 场景11：发送给不同类型的接收者
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES 
(100, 2, 1001, 1, '用户消息', '发送给普通用户的消息。', 'remind', NOW()),
(100, 2, 2001, 2, '骑手消息', '发送给骑手的消息。', 'remind', NOW()),
(100, 2, 3001, 3, '商家消息', '发送给商家的消息。', 'remind', NOW()),
(100, 2, 4001, 4, '合伙人消息', '发送给合伙人的消息。', 'remind', NOW());

-- 2.5 大量数据测试（用于分页查询测试）
-- 场景12：为用户1001创建25条消息，测试分页功能
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
SELECT 
    100 + n, 
    2, 
    1001, 
    1, 
    CONCAT('测试消息', n), 
    CONCAT('这是第', n, '条测试消息，用于测试分页查询功能。'), 
    'remind', 
    NOW()
FROM (
    SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION
    SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION
    SELECT 11 UNION SELECT 12 UNION SELECT 13 UNION SELECT 14 UNION SELECT 15 UNION
    SELECT 16 UNION SELECT 17 UNION SELECT 18 UNION SELECT 19 UNION SELECT 20 UNION
    SELECT 21 UNION SELECT 22 UNION SELECT 23 UNION SELECT 24 UNION SELECT 25
) AS numbers;

-- ========================================
-- 第三部分：查询验证数据
-- ========================================

-- 3.1 验证数据插入成功
SELECT '=== 数据插入验证 ===' AS '验证项';
SELECT 
    COUNT(*) AS '总消息数',
    COUNT(DISTINCT receiver_id) AS '接收者数量',
    COUNT(DISTINCT sender_id) AS '发送者数量'
FROM `message`;

-- 3.2 按消息类型统计
SELECT '=== 按消息类型统计 ===' AS '验证项';
SELECT 
    message_type,
    COUNT(*) AS '消息数量'
FROM `message`
GROUP BY message_type;

-- 3.3 按发送者类型统计
SELECT '=== 按发送者类型统计 ===' AS '验证项';
SELECT 
    sender_type,
    COUNT(*) AS '消息数量'
FROM `message`
GROUP BY sender_type;

-- 3.4 按接收者类型统计
SELECT '=== 按接收者类型统计 ===' AS '验证项';
SELECT 
    receiver_type,
    COUNT(*) AS '消息数量'
FROM `message`
GROUP BY receiver_type;

-- 3.5 测试分页查询（查询用户1001的消息，每页10条）
SELECT '=== 分页查询测试（第1页） ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
ORDER BY create_time DESC
LIMIT 0, 10;

SELECT '=== 分页查询测试（第2页） ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
ORDER BY create_time DESC
LIMIT 10, 10;

-- 3.6 测试按发送者类型筛选
SELECT '=== 按发送者类型筛选（系统消息） ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND sender_type = 1
ORDER BY create_time DESC;

-- 3.7 测试按消息类型筛选
SELECT '=== 按消息类型筛选（系统通知） ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND message_type = 'system'
ORDER BY create_time DESC;

-- 3.8 测试按时间范围筛选
SELECT '=== 按时间范围筛选（最近7天） ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND create_time >= DATE_SUB(NOW(), INTERVAL 7 DAY)
ORDER BY create_time DESC;

-- 3.9 测试组合查询（发送者类型 + 消息类型 + 时间范围）
SELECT '=== 组合查询测试 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND sender_type = 2
  AND message_type = 'remind'
  AND create_time >= DATE_SUB(NOW(), INTERVAL 3 DAY)
ORDER BY create_time DESC;

-- 3.10 测试查询消息详情
SELECT '=== 查询消息详情 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    receiver_type,
    message_title,
    message_content,
    message_type,
    create_time
FROM `message`
WHERE id = 1;

-- 3.11 测试统计用户消息数量
SELECT '=== 统计用户消息数量 ===' AS '验证项';
SELECT 
    receiver_id AS '用户ID',
    COUNT(*) AS '消息总数',
    SUM(CASE WHEN message_type = 'system' THEN 1 ELSE 0 END) AS '系统通知数',
    SUM(CASE WHEN message_type = 'remind' THEN 1 ELSE 0 END) AS '提醒数',
    SUM(CASE WHEN message_type = 'private' THEN 1 ELSE 0 END) AS '私信数'
FROM `message`
WHERE receiver_id = 1001
GROUP BY receiver_id;

-- 3.12 测试查询最新消息
SELECT '=== 查询最新消息 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
ORDER BY create_time DESC
LIMIT 5;

-- 3.13 测试查询未读消息（假设没有is_read字段，这里演示如何扩展）
SELECT '=== 查询最近24小时的消息（模拟未读） ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND create_time >= DATE_SUB(NOW(), INTERVAL 24 HOUR)
ORDER BY create_time DESC;

-- 3.14 测试查询特定发送者的消息
SELECT '=== 查询特定发送者的消息 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND sender_id = 100
ORDER BY create_time DESC;

-- 3.15 测试查询包含特定关键词的消息
SELECT '=== 查询包含特定关键词的消息 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_content,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND (message_title LIKE '%订单%' OR message_content LIKE '%订单%')
ORDER BY create_time DESC;

-- ========================================
-- 第四部分：异常场景测试数据
-- ========================================

-- 场景13：测试删除不存在的消息（模拟DELETE操作）
SELECT '=== 测试删除不存在的消息 ===' AS '验证项';
DELETE FROM `message` WHERE id = 999999;
SELECT ROW_COUNT() AS '受影响的行数（应为0）';

-- 场景14：测试查询不存在的消息
SELECT '=== 测试查询不存在的消息 ===' AS '验证项';
SELECT * FROM `message` WHERE id = 999999;

-- 场景15：测试查询不存在的用户的消息
SELECT '=== 测试查询不存在的用户的消息 ===' AS '验证项';
SELECT * FROM `message` WHERE receiver_id = 999999;

-- ========================================
-- 第五部分：清理测试环境
-- ========================================

-- 清理所有测试数据
-- DELETE FROM `message`;

-- 或者只清理特定用户的数据
-- DELETE FROM `message` WHERE receiver_id = 1001;

-- 删除测试数据库（谨慎使用）
-- DROP DATABASE IF EXISTS campus_message_test;

-- ========================================
-- 第六部分：性能测试SQL
-- ========================================

-- 6.1 测试索引使用情况
SELECT '=== 检查索引使用情况 ===' AS '验证项';
EXPLAIN SELECT * FROM `message` WHERE receiver_id = 1001 ORDER BY create_time DESC LIMIT 10;

-- 6.2 测试复合查询性能
SELECT '=== 测试复合查询性能 ===' AS '验证项';
EXPLAIN SELECT * FROM `message` 
WHERE receiver_id = 1001 
  AND sender_type = 2 
  AND message_type = 'remind'
ORDER BY create_time DESC;

-- 6.3 测试统计查询性能
SELECT '=== 测试统计查询性能 ===' AS '验证项';
EXPLAIN SELECT 
    receiver_id,
    sender_type,
    message_type,
    COUNT(*) AS count
FROM `message`
GROUP BY receiver_id, sender_type, message_type;

-- ========================================
-- 第七部分：数据完整性约束测试
-- ========================================

-- 场景16：测试NOT NULL约束（应该失败）
-- INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
-- VALUES (NULL, 2, 1001, 1, '测试', '测试内容', 'remind', NOW());

-- 场景17：测试主键约束（应该失败）
-- INSERT INTO `message` (`id`, `sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
-- VALUES (1, 100, 2, 1001, 1, '测试', '测试内容', 'remind', NOW());

-- ========================================
-- 第八部分：业务规则验证SQL
-- ========================================

-- 验证规则1：系统发送者(sender_type=1)只能发送系统通知(message_type=system)
SELECT '=== 验证系统发送者消息类型 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    message_type,
    CASE 
        WHEN sender_type = 1 AND message_type != 'system' THEN '违反规则'
        ELSE '符合规则'
    END AS '验证结果'
FROM `message`
WHERE sender_type = 1;

-- 验证规则2：用户(sender_type=3)只能发送私信(message_type=private)
SELECT '=== 验证用户消息类型 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    message_type,
    CASE 
        WHEN sender_type = 3 AND message_type != 'private' THEN '违反规则'
        ELSE '符合规则'
    END AS '验证结果'
FROM `message`
WHERE sender_type = 3;

-- 验证规则3：管理员/骑手/商家/合伙人(sender_type=2/4/5/6)只能发送提醒(message_type=remind)或私信(message_type=private)
SELECT '=== 验证其他发送者消息类型 ===' AS '验证项';
SELECT 
    id,
    sender_id,
    sender_type,
    message_type,
    CASE 
        WHEN sender_type IN (2, 4, 5, 6) AND message_type NOT IN ('remind', 'private') THEN '违反规则'
        ELSE '符合规则'
    END AS '验证结果'
FROM `message`
WHERE sender_type IN (2, 4, 5, 6);

-- ========================================
-- 第九部分：模拟API接口测试
-- ========================================

-- 9.1 模拟发送消息接口（POST /api/message/send）
-- 示例：系统发送系统通知
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES (0, 1, 1001, 1, '系统通知测试', '这是一条系统通知测试消息。', 'system', NOW());

-- 示例：管理员发送提醒
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES (100, 2, 1001, 1, '提醒测试', '这是一条提醒测试消息。', 'remind', NOW());

-- 示例：用户发送私信
INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
VALUES (1001, 3, 1002, 1, '私信测试', '这是一条私信测试消息。', 'private', NOW());

-- 9.2 模拟分页查询接口（GET /api/message/list）
-- 查询用户1001的消息列表，第1页，每页10条
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_content,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
ORDER BY create_time DESC
LIMIT 0, 10;

-- 查询用户1001的系统通知，第1页，每页10条
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    message_title,
    message_content,
    message_type,
    create_time
FROM `message`
WHERE receiver_id = 1001
  AND message_type = 'system'
ORDER BY create_time DESC
LIMIT 0, 10;

-- 9.3 模拟查询消息详情接口（GET /api/message/{id}）
SELECT 
    id,
    sender_id,
    sender_type,
    receiver_id,
    receiver_type,
    message_title,
    message_content,
    message_type,
    create_time
FROM `message`
WHERE id = 1;

-- 9.4 模拟删除消息接口（DELETE /api/message/{id}）
-- 删除消息ID为1的消息
DELETE FROM `message` WHERE id = 1;

-- ========================================
-- 第十部分：压力测试数据生成
-- ========================================

-- 为压力测试生成大量数据（可选，谨慎使用）
-- 为用户1001生成1000条消息
-- INSERT INTO `message` (`sender_id`, `sender_type`, `receiver_id`, `receiver_type`, `message_title`, `message_content`, `message_type`, `create_time`)
-- SELECT 
--     100 + (n % 50),
--     2,
--     1001,
--     1,
--     CONCAT('压力测试消息', n),
--     CONCAT('这是第', n, '条压力测试消息，用于测试系统性能。'),
--     'remind',
--     DATE_SUB(NOW(), INTERVAL (n % 30) DAY)
-- FROM (
--     SELECT 1 AS n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION
--     SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION
--     SELECT 11 UNION SELECT 12 UNION SELECT 13 UNION SELECT 14 UNION SELECT 15 UNION
--     SELECT 16 UNION SELECT 17 UNION SELECT 18 UNION SELECT 19 UNION SELECT 20 UNION
--     SELECT 21 UNION SELECT 22 UNION SELECT 23 UNION SELECT 24 UNION SELECT 25 UNION
--     SELECT 26 UNION SELECT 27 UNION SELECT 28 UNION SELECT 29 UNION SELECT 30 UNION
--     SELECT 31 UNION SELECT 32 UNION SELECT 33 UNION SELECT 34 UNION SELECT 35 UNION
--     SELECT 36 UNION SELECT 37 UNION SELECT 38 UNION SELECT 39 UNION SELECT 40 UNION
--     SELECT 41 UNION SELECT 42 UNION SELECT 43 UNION SELECT 44 UNION SELECT 45 UNION
--     SELECT 46 UNION SELECT 47 UNION SELECT 48 UNION SELECT 49 UNION SELECT 50 UNION
--     SELECT 51 UNION SELECT 52 UNION SELECT 53 UNION SELECT 54 UNION SELECT 55 UNION
--     SELECT 56 UNION SELECT 57 UNION SELECT 58 UNION SELECT 59 UNION SELECT 60 UNION
--     SELECT 61 UNION SELECT 62 UNION SELECT 63 UNION SELECT 64 UNION SELECT 65 UNION
--     SELECT 66 UNION SELECT 67 UNION SELECT 68 UNION SELECT 69 UNION SELECT 70 UNION
--     SELECT 71 UNION SELECT 72 UNION SELECT 73 UNION SELECT 74 UNION SELECT 75 UNION
--     SELECT 76 UNION SELECT 77 UNION SELECT 78 UNION SELECT 79 UNION SELECT 80 UNION
--     SELECT 81 UNION SELECT 82 UNION SELECT 83 UNION SELECT 84 UNION SELECT 85 UNION
--     SELECT 86 UNION SELECT 87 UNION SELECT 88 UNION SELECT 89 UNION SELECT 90 UNION
--     SELECT 91 UNION SELECT 92 UNION SELECT 93 UNION SELECT 94 UNION SELECT 95 UNION
--     SELECT 96 UNION SELECT 97 UNION SELECT 98 UNION SELECT 99 UNION SELECT 100
-- ) AS numbers
-- CROSS JOIN (
--     SELECT 1 AS m UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION
--     SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10
-- ) AS multipliers
-- WHERE n + (m - 1) * 100 <= 1000;

-- ========================================
-- 测试完成
-- ========================================
SELECT '=== 测试SQL执行完成 ===' AS '状态';
SELECT NOW() AS '执行时间';
