
-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 0, 'setting', '系统设置', 1, NULL, 1, NULL, '/system', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (2, 0, 0, 'shop', '商家管理', 2, NULL, 1, NULL, '/merchant', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (3, 0, 0, 'order', '订单管理', 3, NULL, 1, NULL, '/order', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (4, 0, 0, 'message', '校园论坛', 4, NULL, 1, NULL, '/forum', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (5, 0, 0, 'appstore', '服务管理', 5, NULL, 1, NULL, '/service', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (6, 0, 0, 'user', '用户管理', 7, NULL, 1, NULL, '/app-user', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (7, 0, 0, 'bar-chart', '统计分析', 8, NULL, 1, NULL, '/statistics', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (8, 0, 0, 'audit', '审核管理', 9, NULL, 1, NULL, '/audit', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (9, 0, 0, 'car', '配送管理', 6, NULL, 1, NULL, '/delivery', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (101, 1, 1, NULL, '用户管理', 1, 'system:user:list', 1, 'system/user/index', '/system/user', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (102, 1, 1, NULL, '角色管理', 2, 'system:role:list', 1, 'system/role/index', '/system/role', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (103, 1, 1, NULL, '菜单管理', 3, 'system:menu:list', 1, 'system/menu/index', '/system/menu', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (1031, 103, 2, NULL, '菜单查询', 1, 'system:menu:query', 1, NULL, NULL, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (1032, 103, 2, NULL, '菜单新增', 2, 'system:menu:add', 1, NULL, NULL, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (1033, 103, 2, NULL, '菜单编辑', 3, 'system:menu:edit', 1, NULL, NULL, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (1034, 103, 2, NULL, '菜单删除', 4, 'system:menu:delete', 1, NULL, NULL, 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (104, 1, 1, NULL, '字典管理', 4, 'system:dict:list', 1, 'system/dict/index', '/system/dict', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (105, 1, 1, NULL, '服务分佣配置', 5, 'system:serviceCommission:list', 1, 'system/service-commission/index', '/system/service-commission', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (106, 1, 1, NULL, '学校管理', 6, 'system:school:list', 1, 'system/school/index', '/system/school', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (107, 1, 1, NULL, '合伙人管理', 7, 'system:partner:list', 1, 'system/partner/index', '/system/partner', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (108, 1, 1, NULL, '支付管理', 8, 'system:settlement:list', 1, 'system/settlement/index', '/system/settlement', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (201, 2, 1, NULL, '商家列表', 1, 'merchant:list', 1, 'merchant/list/index', '/merchant/list', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (202, 2, 1, NULL, '商品管理', 2, 'mch:product:list', 1, 'merchant/product/index', '/merchant/product', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (203, 2, 1, NULL, '分类管理', 3, 'merchant:category:list', 1, 'merchant/category/index', '/merchant/category', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (204, 2, 1, NULL, '分佣调控', 4, 'merchant:commission:list', 1, 'merchant/commission/index', '/merchant/commission', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (301, 3, 1, NULL, '服务人员订单', 1, 'order:staff:list', 1, 'order/staff/index', '/order/staff', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (302, 3, 1, NULL, '商家订单', 2, 'order:merchant:list', 1, 'order/merchant/index', '/order/merchant', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (401, 4, 1, NULL, '公告管理', 1, 'forum:announcement:list', 1, 'forum/announcement/index', '/forum/announcement', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (402, 4, 1, NULL, '活动管理', 2, 'forum:activity:list', 1, 'forum/activity/index', '/forum/activity', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (403, 4, 1, NULL, '帖子管理', 3, 'forum:post:list', 1, 'forum/post/index', '/forum/post', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (501, 5, 1, NULL, '服务分类管理', 1, 'service:category:list', 1, 'service/category/index', '/service/category', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (601, 6, 1, NULL, '用户管理', 1, 'app:user:list', 1, 'app-user/user/index', '/app-user/user', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (602, 6, 1, NULL, '提现记录', 2, 'app:user:withdrawal:list', 1, 'app-user/withdrawal/index', '/app-user/withdrawal', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (701, 7, 1, NULL, '交易流水统计', 1, 'statistics:transaction:list', 1, 'statistics/transaction/index', '/statistics/transaction', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (702, 7, 1, NULL, '商家流水统计', 2, 'statistics:merchant:list', 1, 'statistics/merchant/index', '/statistics/merchant', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (703, 7, 1, NULL, '服务人员流水', 3, 'statistics:service:list', 1, 'statistics/service/index', '/statistics/service', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (704, 7, 1, NULL, '分类销售统计', 4, 'statistics:category:list', 1, 'statistics/category/index', '/statistics/category', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (801, 8, 1, NULL, '合伙人审核', 1, 'audit:partner:list', 1, 'audit/partner/index', '/audit/partner', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (802, 8, 1, NULL, '商家入驻审核', 2, 'audit:merchant:list', 1, 'audit/merchant/index', '/audit/merchant', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (803, 8, 1, NULL, '服务人员审核', 3, 'audit:staff:list', 1, 'audit/staff/index', '/audit/staff', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (804, 8, 1, NULL, '骑手审核', 4, 'audit:rider:list', 1, 'audit/rider/list', '/audit/rider', 1, '2026-01-16 13:51:43', '2026-01-16 13:51:43', NULL);
INSERT INTO `sys_menu` VALUES (901, 9, 1, NULL, '骑手信息', 1, 'delivery:rider:list', 1, 'delivery/rider/index', '/delivery/rider', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (902, 9, 1, NULL, '骑手订单', 3, 'delivery:order:list', 1, 'delivery/order/index', '/delivery/order', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (903, 9, 1, NULL, '骑手提现管理', 4, 'delivery:rider-withdrawal:list', 1, 'delivery/rider-withdrawal/index', '/delivery/rider-withdrawal', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);
INSERT INTO `sys_menu` VALUES (904, 9, 1, NULL, '配送费配置', 2, 'delivery:fee-config:list', 1, 'delivery/fee-config/index', '/delivery/fee-config', 1, '2025-12-10 12:31:21', '2025-12-10 12:31:21', NULL);

SET FOREIGN_KEY_CHECKS = 1;
