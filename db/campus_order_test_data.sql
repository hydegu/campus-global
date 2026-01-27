-- ============================================
-- Order模块数据库测试数据SQL脚本
-- ============================================
-- 说明：
-- 1. 本脚本包含order_main、order_delivery、order_errand三张表的测试数据
-- 2. 测试数据涵盖不同状态、类型、边界值、特殊字符等场景
-- 3. 确保数据之间的关联性和一致性
-- 4. 每条SQL语句包含完整的字段列表
-- ============================================

-- ============================================
-- 一、order_main表测试数据
-- ============================================

-- 测试数据1：正常外卖订单 - 待支付状态
-- 测试目的：验证待支付状态的基本订单创建
-- 覆盖场景：订单类型=外卖，支付状态=待支付，订单状态=待支付
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  1, '20260119120001ABCDEF', 1, 1001, '张三', '13800138001', 
  58.50, 58.50, 0, NULL, NULL, 
  NULL, 1, NULL, NULL, 
  1, 2001, '美味快餐店', 
  101, 201, 45.00, 8.50, 
  5.00, 0, '不要辣', '2026-01-19 12:10:00', 
  '2026-01-19 12:30:00', NULL, 2.50, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL
);

-- 测试数据2：正常外卖订单 - 已支付，待接单状态
-- 测试目的：验证已支付订单的接单流程
-- 覆盖场景：订单类型=外卖，支付状态=已支付，订单状态=待接单
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  2, '20260119120002GHIJKL', 1, 1002, '李四', '13800138002', 
  78.00, 78.00, 1, 2, '2026-01-19 12:05:00', 
  'WX202601191200001', 2, NULL, NULL, 
  1, 2002, '川菜馆', 
  101, 201, 60.00, 12.00, 
  6.00, 0, '多放辣椒', '2026-01-19 12:10:00', 
  '2026-01-19 12:35:00', NULL, 3.20, 
  '2026-01-19 12:00:00', '2026-01-19 12:05:00', NULL
);

-- 测试数据3：正常外卖订单 - 待取货状态
-- 测试目的：验证骑手接单后的待取货状态
-- 覆盖场景：订单类型=外卖，订单状态=待取货，有骑手信息
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  3, '20260119120003MNOPQR', 1, 1003, '王五', '13800138003', 
  45.00, 45.00, 1, 2, '2026-01-19 12:10:00', 
  'WX202601191200002', 3, NULL, NULL, 
  2, 3001, '骑手-赵六', 
  101, 201, 35.00, 7.00, 
  3.00, 1, '快点送', '2026-01-19 12:15:00', 
  '2026-01-19 12:40:00', NULL, 1.80, 
  '2026-01-19 12:00:00', '2026-01-19 12:20:00', NULL
);

-- 测试数据4：正常外卖订单 - 配送中状态
-- 测试目的：验证骑手取货后的配送中状态
-- 覆盖场景：订单类型=外卖，订单状态=配送中
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  4, '20260119120004STUVWX', 1, 1004, '赵六', '13800138004', 
  128.50, 128.50, 1, 1, '2026-01-19 12:15:00', 
  'ONLINE202601191200001', 4, NULL, NULL, 
  2, 3002, '骑手-孙七', 
  101, 201, 100.00, 20.00, 
  8.50, 2, '不要放香菜', '2026-01-19 12:20:00', 
  '2026-01-19 12:50:00', NULL, 4.50, 
  '2026-01-19 12:00:00', '2026-01-19 12:35:00', NULL
);

-- 测试数据5：正常外卖订单 - 已送达状态
-- 测试目的：验证订单送达后的状态更新
-- 覆盖场景：订单类型=外卖，订单状态=已送达，有实际送达时间
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  5, '20260119120005YZABCD', 1, 1005, '孙七', '13800138005', 
  32.00, 32.00, 1, 2, '2026-01-19 11:50:00', 
  'WX202601191200003', 5, NULL, NULL, 
  2, 3003, '骑手-周八', 
  101, 201, 25.00, 5.00, 
  2.00, 3, '谢谢', '2026-01-19 11:55:00', 
  '2026-01-19 12:20:00', '2026-01-19 12:18:00', 1.20, 
  '2026-01-19 11:45:00', '2026-01-19 12:18:00', NULL
);

-- 测试数据6：正常外卖订单 - 已完成状态
-- 测试目的：验证订单完成流程
-- 覆盖场景：订单类型=外卖，订单状态=已完成
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  6, '20260119110006EFGHIJ', 1, 1006, '周八', '13800138006', 
  56.00, 56.00, 1, 1, '2026-01-19 11:40:00', 
  'ONLINE202601191100001', 7, NULL, NULL, 
  2, 3004, '骑手-吴九', 
  101, 201, 43.00, 9.00, 
  4.00, 4, '很好吃', '2026-01-19 11:45:00', 
  '2026-01-19 12:15:00', '2026-01-19 12:14:00', 2.00, 
  '2026-01-19 11:35:00', '2026-01-19 12:15:00', NULL
);

-- 测试数据7：正常外卖订单 - 已取消（用户取消）
-- 测试目的：验证用户取消订单流程
-- 覆盖场景：订单类型=外卖，订单状态=已取消，取消类型=用户取消
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  7, '20260119105007KLMNOP', 1, 1007, '吴九', '13800138007', 
  42.00, 42.00, 0, NULL, NULL, 
  NULL, 6, 1, '2026-01-19 10:55:00', 
  1, 2003, '奶茶店', 
  101, 201, 32.00, 7.00, 
  3.00, 1, '不想要了', '2026-01-19 10:50:00', 
  '2026-01-19 11:15:00', NULL, 1.50, 
  '2026-01-19 10:50:00', '2026-01-19 10:55:00', NULL
);

-- 测试数据8：正常外卖订单 - 已取消（商家取消）
-- 测试目的：验证商家取消订单流程
-- 覆盖场景：订单类型=外卖，订单状态=已取消，取消类型=商家取消
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  8, '20260119105008QRSTUV', 1, 1008, '郑十', '13800138008', 
  65.00, 65.00, 1, 2, '2026-01-19 10:45:00', 
  'WX202601191050001', 6, 2, '2026-01-19 10:50:00', 
  1, 2004, '烧烤店', 
  101, 201, 50.00, 10.00, 
  5.00, 1, '食材不足', '2026-01-19 10:50:00', 
  '2026-01-19 11:20:00', NULL, 2.80, 
  '2026-01-19 10:40:00', '2026-01-19 10:50:00', NULL
);

-- 测试数据9：正常外卖订单 - 已取消（超时取消）
-- 测试目的：验证超时自动取消订单流程
-- 覆盖场景：订单类型=外卖，订单状态=已取消，取消类型=超时取消
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  9, '20260119100009WXYZAB', 1, 1009, '陈十一', '13800138009', 
  38.00, 38.00, 1, 1, '2026-01-19 10:00:00', 
  'ONLINE202601191000001', 6, 3, '2026-01-19 10:30:00', 
  1, 2005, '面馆', 
  101, 201, 29.00, 6.00, 
  3.00, 1, '超时未接单', '2026-01-19 10:05:00', 
  '2026-01-19 10:35:00', NULL, 1.60, 
  '2026-01-19 10:00:00', '2026-01-19 10:30:00', NULL
);

-- 测试数据10：正常外卖订单 - 售后中状态
-- 测试目的：验证售后流程
-- 覆盖场景：订单类型=外卖，订单状态=售后中
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  10, '20260119090010CDEFGH', 1, 1010, '林十二', '13800138010', 
  88.00, 88.00, 2, 2, '2026-01-19 09:10:00', 
  'WX202601190900001', 8, NULL, NULL, 
  2, 3005, '骑手-黄十三', 
  101, 201, 68.00, 14.00, 
  6.00, 3, '申请退款', '2026-01-19 09:15:00', 
  '2026-01-19 09:45:00', '2026-01-19 09:42:00', 3.20, 
  '2026-01-19 09:00:00', '2026-01-19 09:50:00', NULL
);

-- 测试数据11：正常服务订单 - 待接单状态
-- 测试目的：验证服务订单创建流程
-- 覆盖场景：订单类型=服务，订单状态=待接单
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  11, '20260119120011IJKLMN', 2, 1011, '黄十三', '13800138011', 
  15.00, 15.00, 1, 2, '2026-01-19 12:00:00', 
  'WX202601191200004', 2, NULL, NULL, 
  2, NULL, NULL, 
  101, 201, 12.00, 2.00, 
  1.00, 0, '帮我取快递', '2026-01-19 12:05:00', 
  '2026-01-19 12:30:00', NULL, 1.50, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL
);

-- 测试数据12：正常服务订单 - 配送中状态
-- 测试目的：验证服务订单配送中状态
-- 覆盖场景：订单类型=服务，订单状态=配送中
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  12, '20260119115012OPQRST', 2, 1012, '杨十四', '13800138012', 
  20.00, 20.00, 1, 1, '2026-01-19 11:40:00', 
  'ONLINE202601191150001', 4, NULL, NULL, 
  2, 4001, '服务人员-朱十五', 
  101, 201, 16.00, 3.00, 
  1.00, 1, '帮我买药', '2026-01-19 11:45:00', 
  '2026-01-19 12:20:00', NULL, 2.00, 
  '2026-01-19 11:35:00', '2026-01-19 12:00:00', NULL
);

-- 测试数据13：正常服务订单 - 已完成状态
-- 测试目的：验证服务订单完成流程
-- 覆盖场景：订单类型=服务，订单状态=已完成
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  13, '20260119110013UVWXYZ', 2, 1013, '朱十五', '13800138013', 
  18.00, 18.00, 1, 2, '2026-01-19 11:20:00', 
  'WX202601191100002', 7, NULL, NULL, 
  2, 4002, '服务人员-秦十六', 
  101, 201, 14.00, 3.00, 
  1.00, 2, '帮我打印', '2026-01-19 11:25:00', 
  '2026-01-19 11:55:00', '2026-01-19 11:53:00', 1.80, 
  '2026-01-19 11:15:00', '2026-01-19 11:55:00', NULL
);

-- 测试数据14：边界值测试 - 最大金额
-- 测试目的：验证金额字段的最大值边界
-- 覆盖场景：total_amount和actual_amount字段的最大值测试
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  14, '20260119120014MAXVAL', 1, 1014, '秦十六', '13800138014', 
  99999999.99, 99999999.99, 1, 1, '2026-01-19 12:00:00', 
  'ONLINE202601191200002', 1, NULL, NULL, 
  1, 2006, '豪华餐厅', 
  101, 201, 79999999.99, 14999999.99, 
  4999999.99, 0, '最大金额测试', '2026-01-19 12:05:00', 
  '2026-01-19 12:35:00', NULL, 10.00, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL
);

-- 测试数据15：边界值测试 - 最小金额
-- 测试目的：验证金额字段的最小值边界
-- 覆盖场景：total_amount和actual_amount字段的最小值测试
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  15, '20260119120015MINVAL', 2, 1015, '许十七', '13800138015', 
  0.01, 0.01, 1, 2, '2026-01-19 12:00:00', 
  'WX202601191200005', 7, NULL, NULL, 
  2, 4003, '服务人员-何十八', 
  101, 201, 0.01, 0.00, 
  0.00, 1, '最小金额测试', '2026-01-19 12:05:00', 
  '2026-01-19 12:10:00', '2026-01-19 12:08:00', 0.10, 
  '2026-01-19 12:00:00', '2026-01-19 12:10:00', NULL
);

-- 测试数据16：特殊字符测试 - 备注包含特殊字符
-- 测试目的：验证特殊字符的处理能力
-- 覆盖场景：remark字段包含特殊字符和emoji
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  16, '20260119120016SPECIAL', 1, 1016, '何十八', '13800138016', 
  52.00, 52.00, 1, 2, '2026-01-19 12:00:00', 
  'WX202601191200006', 7, NULL, NULL, 
  2, 3006, '骑手-吕十九', 
  101, 201, 40.00, 8.00, 
  4.00, 2, '特殊字符测试：@#$%^&*()_+-=[]{}|;:'"<>,.?/~`！@#￥%……&*（）——+【】{}|；：''""《》？。/、😀🎉🍔🚀', '2026-01-19 12:05:00', 
  '2026-01-19 12:35:00', '2026-01-19 12:33:00', 2.20, 
  '2026-01-19 12:00:00', '2026-01-19 12:35:00', NULL
);

-- 测试数据17：空值测试 - 部分字段为空
-- 测试目的：验证可选字段为空的处理
-- 覆盖场景：school_id、partner_id、distance等字段为NULL
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  17, '20260119120017NULLTEST', 1, 1017, '吕十九', '13800138017', 
  48.00, 48.00, 1, 1, '2026-01-19 12:00:00', 
  'ONLINE202601191200003', 1, NULL, NULL, 
  1, 2007, '快餐店', 
  NULL, NULL, 37.00, 7.00, 
  4.00, 0, '空值测试', NULL, 
  NULL, NULL, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00', NULL
);

-- 测试数据18：软删除测试 - 已删除订单
-- 测试目的：验证软删除功能
-- 覆盖场景：delete_at字段不为NULL
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  18, '20260119110018DELETED', 1, 1018, '施二十', '13800138018', 
  35.00, 35.00, 1, 2, '2026-01-19 11:30:00', 
  'WX202601191100003', 6, 1, '2026-01-19 11:35:00', 
  1, 2008, '小吃店', 
  101, 201, 27.00, 5.00, 
  3.00, 1, '软删除测试', '2026-01-19 11:35:00', 
  '2026-01-19 12:05:00', NULL, 1.50, 
  '2026-01-19 11:30:00', '2026-01-19 11:35:00', '2026-01-19 11:35:00'
);

-- 测试数据19：其他类型订单测试
-- 测试目的：验证订单类型=其他
-- 覆盖场景：订单类型=3（其他）
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  19, '20260119120019OTHER', 3, 1019, '张二十一', '13800138019', 
  100.00, 100.00, 1, 3, '2026-01-19 12:00:00', 
  'OFFLINE202601191200001', 7, NULL, NULL, 
  2, 4004, '服务人员-王二十二', 
  101, 201, 80.00, 15.00, 
  5.00, 2, '线下支付测试', '2026-01-19 12:05:00', 
  '2026-01-19 12:35:00', '2026-01-19 12:33:00', 3.50, 
  '2026-01-19 12:00:00', '2026-01-19 12:35:00', NULL
);

-- 测试数据20：部分退款状态测试
-- 测试目的：验证部分退款状态
-- 覆盖场景：pay_status=2（部分退款）
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  20, '20260119100020PARTIAL', 1, 1020, '王二十二', '13800138020', 
  100.00, 100.00, 2, 2, '2026-01-19 10:00:00', 
  'WX202601191000002', 7, NULL, NULL, 
  2, 3007, '骑手-李二十三', 
  101, 201, 78.00, 15.00, 
  7.00, 3, '部分退款测试', '2026-01-19 10:05:00', 
  '2026-01-19 10:35:00', '2026-01-19 10:32:00', 3.80, 
  '2026-01-19 10:00:00', '2026-01-19 10:35:00', NULL
);

-- 测试数据21：全额退款状态测试
-- 测试目的：验证全额退款状态
-- 覆盖场景：pay_status=3（全额退款）
INSERT INTO `order_main` (
  `id`, `order_no`, `order_type`, `user_id`, `user_name`, `user_phone`, 
  `total_amount`, `actual_amount`, `pay_status`, `pay_method`, `pay_time`, 
  `pay_channel_no`, `order_status`, `cancel_type`, `cancel_time`, 
  `service_provider_type`, `service_provider_id`, `service_provider_name`, 
  `school_id`, `partner_id`, `estimated_provider_income`, `estimated_partner_income`, 
  `estimated_platform_income`, `version`, `remark`, `estimated_start_time`, 
  `estimated_delivery_time`, `actual_delivery_time`, `distance`, 
  `create_at`, `update_at`, `delete_at`
) VALUES (
  21, '20260119090021FULLREF', 1, 1021, '李二十三', '13800138021', 
  68.00, 68.00, 3, 2, '2026-01-19 09:00:00', 
  'WX202601190900002', 6, 1, '2026-01-19 09:30:00', 
  1, 2009, '火锅店', 
  101, 201, 53.00, 10.00, 
  5.00, 1, '全额退款测试', '2026-01-19 09:05:00', 
  '2026-01-19 09:35:00', NULL, 2.60, 
  '2026-01-19 09:00:00', '2026-01-19 09:30:00', NULL
);

-- ============================================
-- 二、order_delivery表测试数据
-- ============================================

-- 测试数据1：正常外卖订单配送信息 - 待支付状态
-- 测试目的：验证外卖订单配送信息创建
-- 覆盖场景：关联order_main表id=1，无骑手信息
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  1, 1, 2001, 5001, 
  50.00, 8.50, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00'
);

-- 测试数据2：正常外卖订单配送信息 - 待接单状态
-- 测试目的：验证已支付订单配送信息
-- 覆盖场景：关联order_main表id=2，无骑手信息
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  2, 2, 2002, 5002, 
  66.00, 12.00, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:05:00'
);

-- 测试数据3：正常外卖订单配送信息 - 待取货状态
-- 测试目的：验证骑手接单后的配送信息
-- 覆盖场景：关联order_main表id=3，有骑手信息
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  3, 3, 2003, 5003, 
  38.00, 7.00, 3001, 
  '2026-01-19 12:00:00', '2026-01-19 12:20:00'
);

-- 测试数据4：正常外卖订单配送信息 - 配送中状态
-- 测试目的：验证配送中订单配送信息
-- 覆盖场景：关联order_main表id=4，有骑手信息
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  4, 4, 2004, 5004, 
  108.50, 20.00, 3002, 
  '2026-01-19 12:00:00', '2026-01-19 12:35:00'
);

-- 测试数据5：正常外卖订单配送信息 - 已送达状态
-- 测试目的：验证已送达订单配送信息
-- 覆盖场景：关联order_main表id=5，有骑手信息
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  5, 5, 2005, 5005, 
  27.00, 5.00, 3003, 
  '2026-01-19 11:45:00', '2026-01-19 12:18:00'
);

-- 测试数据6：正常外卖订单配送信息 - 已完成状态
-- 测试目的：验证已完成订单配送信息
-- 覆盖场景：关联order_main表id=6，有骑手信息
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  6, 6, 2006, 5006, 
  47.00, 9.00, 3004, 
  '2026-01-19 11:35:00', '2026-01-19 12:15:00'
);

-- 测试数据7：正常外卖订单配送信息 - 已取消状态
-- 测试目的：验证已取消订单配送信息
-- 覆盖场景：关联order_main表id=7，无骑手信息
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  7, 7, 2003, 5007, 
  35.00, 7.00, NULL, 
  '2026-01-19 10:50:00', '2026-01-19 10:55:00'
);

-- 测试数据8：边界值测试 - 最大配送费
-- 测试目的：验证配送费最大值边界
-- 覆盖场景：delivery_fee字段最大值测试
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  8, 14, 2006, 5008, 
  99999999.99, 99999999.99, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00'
);

-- 测试数据9：边界值测试 - 最小配送费
-- 测试目的：验证配送费最小值边界
-- 覆盖场景：delivery_fee字段最小值测试
INSERT INTO `order_delivery` (
  `id`, `order_id`, `merchant_id`, `delivery_address_id`, 
  `goods_amount`, `delivery_fee`, `rider_id`, 
  `created_at`, `updated_at`
) VALUES (
  9, 15, 2007, 5009, 
  0.01, 0.00, 4003, 
  '2026-01-19 12:00:00', '2026-01-19 12:10:00'
);

-- ============================================
-- 三、order_errand表测试数据
-- ============================================

-- 测试数据1：正常服务订单跑腿信息 - 待接单状态
-- 测试目的：验证服务订单跑腿信息创建
-- 覆盖场景：关联order_main表id=11，无服务人员信息
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  1, 11, 15.00, 1, 
  '取件地址A', '送件地址B', '帮我取个快递，大概3公斤', 
  3.00, 30.00, 20.00, 15.00, 9000.00, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00'
);

-- 测试数据2：正常服务订单跑腿信息 - 配送中状态
-- 测试目的：验证配送中服务订单跑腿信息
-- 覆盖场景：关联order_main表id=12，有服务人员信息
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  2, 12, 20.00, 2, 
  '药店地址C', '宿舍地址D', '帮我买点感冒药', 
  0.50, 10.00, 8.00, 5.00, 400.00, 4001, 
  '2026-01-19 11:35:00', '2026-01-19 12:00:00'
);

-- 测试数据3：正常服务订单跑腿信息 - 已完成状态
-- 测试目的：验证已完成服务订单跑腿信息
-- 覆盖场景：关联order_main表id=13，有服务人员信息
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  3, 13, 18.00, 3, 
  '打印店E', '教学楼F', '帮我打印一份资料，双面打印', 
  0.20, 21.00, 29.70, 0.10, 62.44, 4002, 
  '2026-01-19 11:15:00', '2026-01-19 11:55:00'
);

-- 测试数据4：边界值测试 - 最大物品尺寸
-- 测试目的：验证物品尺寸最大值边界
-- 覆盖场景：length、width、height字段最大值测试
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  4, 11, 50.00, 4, 
  '大件物品取件点', '大件物品送达点', '帮我搬个大件物品', 
  50.00, 100.00, 100.00, 100.00, 1000000.0000, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00'
);

-- 测试数据5：边界值测试 - 最小物品尺寸
-- 测试目的：验证物品尺寸最小值边界
-- 覆盖场景：length、width、height字段最小值测试
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  5, 12, 5.00, 5, 
  '小件物品取件点', '小件物品送达点', '帮我拿个小件物品', 
  0.01, 0.10, 0.10, 0.10, 0.0010, 4001, 
  '2026-01-19 11:35:00', '2026-01-19 12:00:00'
);

-- 测试数据6：空值测试 - 部分字段为空
-- 测试目的：验证可选字段为空的处理
-- 覆盖场景：item_description、item_weight、length、width、height、volume为NULL
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  6, 13, 10.00, 6, 
  '取件点G', '送达点H', NULL, 
  NULL, NULL, NULL, NULL, NULL, 4002, 
  '2026-01-19 11:15:00', '2026-01-19 11:55:00'
);

-- 测试数据7：特殊字符测试 - 物品描述包含特殊字符
-- 测试目的：验证特殊字符的处理能力
-- 覆盖场景：item_description字段包含特殊字符和emoji
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  7, 11, 25.00, 7, 
  '特殊字符取件点', '特殊字符送达点', '特殊字符测试：@#$%^&*()_+-=[]{}|;:''"<>,.?/~`！@#￥%……&*（）——+【】{}|；：''""《》？。/、😀🎉📦🚚', 
  5.50, 40.00, 30.00, 20.00, 24000.00, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00'
);

-- 测试数据8：边界值测试 - 最大服务费
-- 测试目的：验证服务费最大值边界
-- 覆盖场景：service_fee字段最大值测试
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  8, 12, 99999999.99, 8, 
  '高价值物品取件点', '高价值物品送达点', '高价值物品配送', 
  100.00, 50.00, 50.00, 50.00, 125000.0000, 4001, 
  '2026-01-19 11:35:00', '2026-01-19 12:00:00'
);

-- 测试数据9：边界值测试 - 最小服务费
-- 测试目的：验证服务费最小值边界
-- 覆盖场景：service_fee字段最小值测试
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  9, 13, 0.01, 9, 
  '低价值物品取件点', '低价值物品送达点', '低价值物品配送', 
  0.01, 5.00, 5.00, 5.00, 125.0000, 4002, 
  '2026-01-19 11:15:00', '2026-01-19 11:55:00'
);

-- 测试数据10：边界值测试 - 最大物品重量
-- 测试目的：验证物品重量最大值边界
-- 覆盖场景：item_weight字段最大值测试
INSERT INTO `order_errand` (
  `id`, `order_id`, `service_fee`, `service_type_id`, 
  `pickup_address_id`, `delivery_address_id`, `item_description`, 
  `item_weight`, `length`, `width`, `height`, `volume`, `staff_id`, 
  `created_at`, `updated_at`
) VALUES (
  10, 11, 100.00, 10, 
  '重物取件点', '重物送达点', '帮我搬个重物', 
  999999.99, 80.00, 60.00, 50.00, 240000.0000, NULL, 
  '2026-01-19 12:00:00', '2026-01-19 12:00:00'
);

-- ============================================
-- 测试数据总结
-- ============================================
-- 
-- order_main表：21条测试数据
-- - 覆盖所有订单状态（1-待支付、2-待接单、3-待取货、4-配送中、5-已送达、6-已取消、7-已完成、8-售后中）
-- - 覆盖所有订单类型（1-外卖、2-服务、3-其他）
-- - 覆盖所有支付状态（0-待支付、1-已支付、2-部分退款、3-全额退款）
-- - 覆盖所有支付方式（1-在线支付、2-微信、3-线下支付）
-- - 覆盖所有取消类型（1-用户取消、2-商家取消、3-超时取消）
-- - 边界值测试：最大金额、最小金额
-- - 特殊字符测试：备注包含特殊字符和emoji
-- - 空值测试：部分字段为NULL
-- - 软删除测试：delete_at字段不为NULL
-- 
-- order_delivery表：9条测试数据
-- - 关联order_main表的外卖订单
-- - 覆盖不同订单状态的配送信息
-- - 边界值测试：最大配送费、最小配送费
-- - 空值测试：rider_id为NULL
-- 
-- order_errand表：10条测试数据
-- - 关联order_main表的服务订单
-- - 覆盖不同订单状态的跑腿信息
-- - 边界值测试：最大物品尺寸、最小物品尺寸、最大服务费、最小服务费、最大物品重量
-- - 空值测试：部分字段为NULL
-- - 特殊字符测试：物品描述包含特殊字符和emoji
-- 
-- 数据关联性：
-- - order_delivery表的order_id关联order_main表的id（外卖订单）
-- - order_errand表的order_id关联order_main表的id（服务订单）
-- - 确保数据的一致性和完整性
-- 
-- ============================================
