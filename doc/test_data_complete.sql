-- ========================================
-- Campus UPMS 完整测试数据SQL
-- 基于OpenAPI文档生成的全面测试数据
-- ========================================

-- ========================================
-- 第一部分：基础配置数据
-- ========================================

-- 1.1 字典数据（sys_dict）
-- 一级字典
INSERT INTO sys_dict (id, parent_id, dict_key, dict_value, remark, sort_order, status, level, create_at, update_at) VALUES
(1, NULL, 'AUDIT_STATUS', '审核状态', '审核状态字典', 1, 1, 1, NOW(), NOW()),
(2, NULL, 'USER_STATUS', '用户状态', '用户状态字典', 2, 1, 1, NOW(), NOW()),
(3, NULL, 'MENU_TYPE', '菜单类型', '菜单类型字典', 3, 1, 1, NOW(), NOW()),
(4, NULL, 'BIZ_TYPE', '业务类型', '审核业务类型字典', 4, 1, 1, NOW(), NOW()),
(5, NULL, 'GENDER', '性别', '性别字典', 5, 1, 1, NOW(), NOW());

-- 二级字典 - 审核状态
INSERT INTO sys_dict (id, parent_id, dict_key, dict_value, remark, sort_order, status, level, create_at, update_at) VALUES
(101, 1, 'PENDING', '待审核', '审核状态-待审核', 1, 1, 2, NOW(), NOW()),
(102, 1, 'APPROVED', '审核通过', '审核状态-审核通过', 2, 1, 2, NOW(), NOW()),
(103, 1, 'REJECTED', '审核拒绝', '审核状态-审核拒绝', 3, 1, 2, NOW(), NOW());

-- 二级字典 - 用户状态
INSERT INTO sys_dict (id, parent_id, dict_key, dict_value, remark, sort_order, status, level, create_at, update_at) VALUES
(201, 2, 'NORMAL', '正常', '用户状态-正常', 1, 1, 2, NOW(), NOW()),
(202, 2, 'BLOCKED', '拉黑', '用户状态-拉黑', 2, 1, 2, NOW(), NOW());

-- 二级字典 - 菜单类型
INSERT INTO sys_dict (id, parent_id, dict_key, dict_value, remark, sort_order, status, level, create_at, update_at) VALUES
(301, 3, 'DIRECTORY', '目录', '菜单类型-目录', 1, 1, 2, NOW(), NOW()),
(302, 3, 'MENU', '菜单', '菜单类型-菜单', 2, 1, 2, NOW(), NOW()),
(303, 3, 'BUTTON', '按钮', '菜单类型-按钮', 3, 1, 2, NOW(), NOW());

-- 二级字典 - 业务类型
INSERT INTO sys_dict (id, parent_id, dict_key, dict_value, remark, sort_order, status, level, create_at, update_at) VALUES
(401, 4, 'MERCHANT_SETTLE', '商家入驻', '业务类型-商家入驻', 1, 1, 2, NOW(), NOW()),
(402, 4, 'WITHDRAW', '提现', '业务类型-提现', 2, 1, 2, NOW(), NOW()),
(403, 4, 'GOODS', '商品上架', '业务类型-商品上架', 3, 1, 2, NOW(), NOW()),
(404, 4, 'STAFF_APPLY', '服务人员', '业务类型-服务人员申请', 4, 1, 2, NOW(), NOW()),
(405, 4, 'RIDER_APPLY', '骑手', '业务类型-骑手申请', 5, 1, 2, NOW(), NOW()),
(406, 4, 'PARTNER_APPLY', '合伙人', '业务类型-合伙人申请', 6, 1, 2, NOW(), NOW());

-- 二级字典 - 性别
INSERT INTO sys_dict (id, parent_id, dict_key, dict_value, remark, sort_order, status, level, create_at, update_at) VALUES
(501, 5, 'UNKNOWN', '未知', '性别-未知', 1, 1, 2, NOW(), NOW()),
(502, 5, 'MALE', '男', '性别-男', 2, 1, 2, NOW(), NOW()),
(503, 5, 'FEMALE', '女', '性别-女', 3, 1, 2, NOW(), NOW());

-- 1.2 菜单数据（sys_menu）
-- 一级菜单（目录）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(1, 0, 0, 'el-icon-user', '用户管理', 1, NULL, 1, NULL, '/system/user', 1, NOW(), NOW()),
(2, 0, 0, 'el-icon-s-custom', '角色管理', 2, NULL, 1, NULL, '/system/role', 1, NOW(), NOW()),
(3, 0, 0, 'el-icon-menu', '菜单管理', 3, NULL, 1, NULL, '/system/menu', 1, NOW(), NOW()),
(4, 0, 0, 'el-icon-notebook', '字典管理', 4, NULL, 1, NULL, '/system/dict', 1, NOW(), NOW()),
(5, 0, 0, 'el-icon-school', '学校管理', 5, NULL, 1, NULL, '/system/school', 1, NOW(), NOW()),
(6, 0, 0, 'el-icon-s-check', '审核管理', 6, NULL, 1, NULL, '/system/audit', 1, NOW(), NOW()),
(7, 0, 0, 'el-icon-setting', '系统管理', 7, NULL, 1, NULL, '/system/config', 1, NOW(), NOW());

-- 二级菜单（用户管理下）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(11, 1, 1, NULL, '系统用户', 1, 'system:user:list', 1, '/system/user/index', 'sys-user', 1, NOW(), NOW()),
(12, 1, 1, NULL, '普通用户', 2, 'system:appuser:list', 1, '/system/appuser/index', 'app-user', 1, NOW(), NOW()),
(13, 1, 1, NULL, '骑手用户', 3, 'system:rider:list', 1, '/system/rider/index', 'rider-user', 1, NOW(), NOW()),
(14, 1, 1, NULL, '商家用户', 4, 'system:mch:list', 1, '/system/mch/index', 'mch-user', 1, NOW(), NOW()),
(15, 1, 1, NULL, '合伙人用户', 5, 'system:partner:list', 1, '/system/partner/index', 'partner-user', 1, NOW(), NOW());

-- 二级菜单（角色管理下）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(21, 2, 1, NULL, '角色列表', 1, 'system:role:list', 1, '/system/role/index', 'role-list', 1, NOW(), NOW()),
(22, 2, 2, NULL, '角色新增', 2, 'system:role:add', 1, NULL, NULL, 1, NOW(), NOW()),
(23, 2, 2, NULL, '角色编辑', 3, 'system:role:edit', 1, NULL, NULL, 1, NOW(), NOW()),
(24, 2, 2, NULL, '角色删除', 4, 'system:role:delete', 1, NULL, NULL, 1, NOW(), NOW());

-- 二级菜单（菜单管理下）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(31, 3, 1, NULL, '菜单列表', 1, 'system:menu:list', 1, '/system/menu/index', 'menu-list', 1, NOW(), NOW()),
(32, 3, 2, NULL, '菜单新增', 2, 'system:menu:add', 1, NULL, NULL, 1, NOW(), NOW()),
(33, 3, 2, NULL, '菜单编辑', 3, 'system:menu:edit', 1, NULL, NULL, 1, NOW(), NOW()),
(34, 3, 2, NULL, '菜单删除', 4, 'system:menu:delete', 1, NULL, NULL, 1, NOW(), NOW());

-- 二级菜单（字典管理下）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(41, 4, 1, NULL, '字典列表', 1, 'system:dict:list', 1, '/system/dict/index', 'dict-list', 1, NOW(), NOW()),
(42, 4, 2, NULL, '字典新增', 2, 'system:dict:add', 1, NULL, NULL, 1, NOW(), NOW()),
(43, 4, 2, NULL, '字典编辑', 3, 'system:dict:edit', 1, NULL, NULL, 1, NOW(), NOW()),
(44, 4, 2, NULL, '字典删除', 4, 'system:dict:delete', 1, NULL, NULL, 1, NOW(), NOW());

-- 二级菜单（学校管理下）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(51, 5, 1, NULL, '学校列表', 1, 'system:school:list', 1, '/system/school/index', 'school-list', 1, NOW(), NOW()),
(52, 5, 2, NULL, '学校新增', 2, 'system:school:add', 1, NULL, NULL, 1, NOW(), NOW()),
(53, 5, 2, NULL, '学校编辑', 3, 'system:school:edit', 1, NULL, NULL, 1, NOW(), NOW()),
(54, 5, 2, NULL, '学校删除', 4, 'system:school:delete', 1, NULL, NULL, 1, NOW(), NOW());

-- 二级菜单（审核管理下）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(61, 6, 1, NULL, '骑手审核', 1, 'system:rider:audit', 1, '/system/audit/rider', 'rider-audit', 1, NOW(), NOW()),
(62, 6, 1, NULL, '合伙人审核', 2, 'system:partner:audit', 1, '/system/audit/partner', 'partner-audit', 1, NOW(), NOW()),
(63, 6, 1, NULL, '商家入驻审核', 3, 'system:mch:audit', 1, '/system/audit/mch', 'mch-audit', 1, NOW(), NOW()),
(64, 6, 1, NULL, '服务人员审核', 4, 'system:staff:audit', 1, '/system/audit/staff', 'staff-audit', 1, NOW(), NOW());

-- 二级菜单（系统管理下）
INSERT INTO sys_menu (id, parent_id, menu_type, menu_icon, menu_name, sort_order, permission, is_frame, component, path, status, create_at, update_at) VALUES
(71, 7, 1, NULL, '客户端管理', 1, 'system:client:list', 1, '/system/client/index', 'client-list', 1, NOW(), NOW());

-- 1.3 角色数据（role）
INSERT INTO role (id, role_name, role_code, sort_order, status, create_at, update_at) VALUES
(1, '超级管理员', 'super_admin', 1, 1, NOW(), NOW()),
(2, '系统管理员', 'admin', 2, 1, NOW(), NOW()),
(3, '审核员', 'auditor', 3, 1, NOW(), NOW()),
(4, '运营人员', 'operator', 4, 1, NOW(), NOW()),
(5, '测试角色', 'test_role', 5, 0, NOW(), NOW());

-- 1.4 角色菜单关联数据（role_menu）
-- 超级管理员拥有所有菜单权限
INSERT INTO role_menu (role_id, menu_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7),
(1, 11), (1, 12), (1, 13), (1, 14), (1, 15),
(1, 21), (1, 22), (1, 23), (1, 24),
(1, 31), (1, 32), (1, 33), (1, 34),
(1, 41), (1, 42), (1, 43), (1, 44),
(1, 51), (1, 52), (1, 53), (1, 54),
(1, 61), (1, 62), (1, 63), (1, 64),
(1, 71);

-- 系统管理员拥有部分权限
INSERT INTO role_menu (role_id, menu_id) VALUES
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(2, 11), (2, 12), (2, 13), (2, 14), (2, 15),
(2, 21), (2, 22), (2, 23),
(2, 31), (2, 32), (2, 33),
(2, 41), (2, 42), (2, 43),
(2, 51), (2, 52), (2, 53);

-- 审核员拥有审核相关权限
INSERT INTO role_menu (role_id, menu_id) VALUES
(3, 1), (3, 6),
(3, 11), (3, 12), (3, 13), (3, 14), (3, 15),
(3, 61), (3, 62), (3, 63), (3, 64);

-- 运营人员拥有基础查询权限
INSERT INTO role_menu (role_id, menu_id) VALUES
(4, 1), (4, 2), (4, 5),
(4, 11), (4, 12), (4, 13), (4, 14), (4, 15),
(4, 21), (4, 51);

-- 1.5 客户端数据（sys_oauth_client_details）
-- 注意：client_secret使用明文存储，不需要BCrypt加密
INSERT INTO sys_oauth_client_details (id, client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, access_token_validity, refresh_token_validity, create_at, update_at) VALUES
(1, 'web', 'web_secret_123', 'all', 'password,refresh_token', 'http://127.0.0.1:8080/callback', 7200, 2592000, NOW(), NOW()),
(2, 'app', 'app_secret_123', 'all', 'password,refresh_token', 'http://127.0.0.1:8080/callback', 7200, 2592000, NOW(), NOW()),
(3, 'miniapp', 'miniapp_secret_123', 'all', 'password,refresh_token', 'http://127.0.0.1:8080/callback', 7200, 2592000, NOW(), NOW());

-- ========================================
-- 第二部分：用户相关测试数据
-- ========================================

-- 2.1 系统用户（base_user + user_sys）
-- 密码统一为：123456（BCrypt加密后的值）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(1001, 'admin', '超级管理员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/admin.jpg', '13800138001', 'admin@campus.com', 1, NOW(), NOW(), NOW(), '127.0.0.1'),
(1002, 'sysadmin', '系统管理员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/sysadmin.jpg', '13800138002', 'sysadmin@campus.com', 1, NOW(), NOW(), NOW(), '127.0.0.1'),
(1003, 'auditor', '审核员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/auditor.jpg', '13800138003', 'auditor@campus.com', 1, NOW(), NOW(), NOW(), '127.0.0.1'),
(1004, 'operator', '运营人员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/operator.jpg', '13800138004', 'operator@campus.com', 1, NOW(), NOW(), NOW(), '127.0.0.1'),
(1005, 'testuser', '测试用户', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 0, 'https://example.com/avatar/testuser.jpg', '13800138005', 'testuser@campus.com', 1, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_sys (id, base_user_id, real_name, gender) VALUES
(1, 1001, '张三', 1),
(2, 1002, '李四', 1),
(3, 1003, '王五', 2),
(4, 1004, '赵六', 1),
(5, 1005, '测试员', 0);

-- 系统用户角色关联（user_role）
INSERT INTO user_role (user_id, role_id) VALUES
(1001, 1),
(1002, 2),
(1003, 3),
(1004, 4),
(1005, 5);

-- 2.2 普通用户（base_user + user_app）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(2001, 'student1', '学生张小明', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/student1.jpg', '13900139001', 'student1@campus.com', 2, NOW(), NOW(), NOW(), '127.0.0.1'),
(2002, 'student2', '学生李小红', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/student2.jpg', '13900139002', 'student2@campus.com', 2, NOW(), NOW(), NOW(), '127.0.0.1'),
(2003, 'student3', '学生王小刚', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 0, 'https://example.com/avatar/student3.jpg', '13900139003', 'student3@campus.com', 2, NOW(), NOW(), NOW(), '127.0.0.1'),
(2004, 'student4', '学生赵小美', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/student4.jpg', '13900139004', 'student4@campus.com', 2, NOW(), NOW(), NOW(), '127.0.0.1'),
(2005, 'student5', '学生陈小龙', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/student5.jpg', '13900139005', 'student5@campus.com', 2, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_app (id, base_user_id, gender, openid, stu_code, balance, total_amount, school_id, audit_id, real_name, id_card, create_time, update_time) VALUES
(101, 2001, 1, 'o1234567890abcdef1234567890ab', '2021001', 100.00, 500.00, 1001, 4001, '张小明', '110101200001011234', NOW(), NOW()),
(102, 2002, 2, 'o1234567890abcdef1234567890ac', '2021002', 200.50, 800.00, 1001, 4002, '李小红', '110101200001021235', NOW(), NOW()),
(103, 2003, 1, 'o1234567890abcdef1234567890ad', '2021003', 50.00, 300.00, 1001, 4003, '王小刚', '110101200001031236', NOW(), NOW()),
(104, 2004, 2, 'o1234567890abcdef1234567890ae', '2021004', 150.00, 600.00, 1002, NULL, '赵小美', '110101200001041237', NOW(), NOW()),
(105, 2005, 1, 'o1234567890abcdef1234567890af', '2021005', 80.00, 400.00, 1002, NULL, '陈小龙', '110101200001051238', NOW(), NOW());

-- 2.3 骑手用户（base_user + user_rider）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(3001, 'rider1', '骑手刘大伟', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/rider1.jpg', '13700137001', 'rider1@campus.com', 4, NOW(), NOW(), NOW(), '127.0.0.1'),
(3002, 'rider2', '骑手孙小芳', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/rider2.jpg', '13700137002', 'rider2@campus.com', 4, NOW(), NOW(), NOW(), '127.0.0.1'),
(3003, 'rider3', '骑手周小军', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 0, 'https://example.com/avatar/rider3.jpg', '13700137003', 'rider3@campus.com', 4, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_rider (id, base_user_id, real_name, id_card, card_number, audit_id, create_at, update_at) VALUES
(201, 3001, '刘大伟', '110101199001011234', '6222021234567890123', 1001, NOW(), NOW()),
(202, 3002, '孙小芳', '110101199002021235', '6222021234567890124', 1002, NOW(), NOW()),
(203, 3003, '周小军', '110101199003031236', '6222021234567890125', 1003, NOW(), NOW());

-- 2.4 商家用户（base_user + user_mch）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(4001, 'mch1', '校园便利店', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/mch1.jpg', '13600136001', 'mch1@campus.com', 3, NOW(), NOW(), NOW(), '127.0.0.1'),
(4002, 'mch2', '美味餐厅', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/mch2.jpg', '13600136002', 'mch2@campus.com', 3, NOW(), NOW(), NOW(), '127.0.0.1'),
(4003, 'mch3', '水果超市', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 0, 'https://example.com/avatar/mch3.jpg', '13600136003', 'mch3@campus.com', 3, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_mch (id, base_user_id, mch_name, address_id, logo, contact_name, business_license_urls, partner_id, id_card, minimum_order_amount, card_number, audit_id, business_start_time, business_end_time, is_open, create_time, update_time) VALUES
(301, 4001, '校园便利店', 1, 'https://example.com/logo/mch1.jpg', '张老板', 'https://example.com/license/mch1.jpg', 5001, '110101198001011234', 10.00, '6222021234567890126', 3001, '08:00:00', '22:00:00', 1, NOW(), NOW()),
(302, 4002, '美味餐厅', 2, 'https://example.com/logo/mch2.jpg', '李大厨', 'https://example.com/license/mch2.jpg', 5001, '110101198002021235', 20.00, '6222021234567890127', 3002, '10:00:00', '21:00:00', 1, NOW(), NOW()),
(303, 4003, '水果超市', 3, 'https://example.com/logo/mch3.jpg', '王经理', 'https://example.com/license/mch3.jpg', 5002, '110101198003031236', 15.00, '6222021234567890128', 3003, '09:00:00', '20:00:00', 0, NOW(), NOW());

-- 2.5 合伙人用户（base_user + user_partner）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(5001, 'partner1', '合伙人吴大明', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/partner1.jpg', '13500135001', 'partner1@campus.com', 5, NOW(), NOW(), NOW(), '127.0.0.1'),
(5002, 'partner2', '合伙人郑小红', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/partner2.jpg', '13500135002', 'partner2@campus.com', 5, NOW(), NOW(), NOW(), '127.0.0.1'),
(5003, 'partner3', '合伙人王小华', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 0, 'https://example.com/avatar/partner3.jpg', '13500135003', 'partner3@campus.com', 5, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_partner (id, base_user_id, partner_name, invite_code, invite_code_path, card_number, commission_rate, parent_id, audit_id, create_at, update_at) VALUES
(401, 5001, '吴大明', 'INVITE001', 'https://example.com/qrcode/partner1.jpg', '6222021234567890129', 5.00, 0, 2001, NOW(), NOW()),
(402, 5002, '郑小红', 'INVITE002', 'https://example.com/qrcode/partner2.jpg', '6222021234567890130', 6.00, 401, 2002, NOW(), NOW()),
(403, 5003, '王小华', 'INVITE003', 'https://example.com/qrcode/partner3.jpg', '6222021234567890131', 4.50, 401, 2003, NOW(), NOW());

-- ========================================
-- 第三部分：学校和地址测试数据
-- ========================================

-- 3.1 地址数据（address）
INSERT INTO address (id, province, city, district, detail_address, contact_phone, contact_name, receiver_lat, receiver_lng, create_time, update_time) VALUES
(1, '北京市', '北京市', '海淀区', '中关村大街1号', '13800138001', '张三', 39.980000, 116.310000, NOW(), NOW()),
(2, '北京市', '北京市', '朝阳区', '建国路88号', '13800138002', '李四', 39.920000, 116.460000, NOW(), NOW()),
(3, '上海市', '上海市', '浦东新区', '世纪大道100号', '13800138003', '王五', 31.230000, 121.470000, NOW(), NOW()),
(4, '上海市', '上海市', '黄浦区', '南京东路200号', '13800138004', '赵六', 31.230000, 121.480000, NOW(), NOW()),
(5, '广东省', '深圳市', '南山区', '科技园南区', '13800138005', '钱七', 22.540000, 113.950000, NOW(), NOW());

-- 3.2 学校数据（sys_school）
INSERT INTO sys_school (id, school_name, address_id, status, create_at, update_at) VALUES
(1001, '北京大学', 1, 1, NOW(), NOW()),
(1002, '清华大学', 2, 1, NOW(), NOW()),
(1003, '复旦大学', 3, 1, NOW(), NOW()),
(1004, '上海交通大学', 4, 1, NOW(), NOW()),
(1005, '深圳大学', 5, 0, NOW(), NOW());

-- 3.3 学校-合伙人关联数据（school_partner）
INSERT INTO school_partner (id, org_id, partner_id, created_at, updated_at) VALUES
(1, 1001, 5001, NOW(), NOW()),
(2, 1002, 5001, NOW(), NOW()),
(3, 1003, 5002, NOW(), NOW()),
(4, 1004, 5002, NOW(), NOW()),
(5, 1005, 5003, NOW(), NOW());

-- ========================================
-- 第四部分：审核记录测试数据
-- ========================================

-- 4.1 审核记录（audit_record）
-- 骑手申请审核
INSERT INTO audit_record (id, audit_no, biz_type, applicant_id, remark, status, auditor_id, create_at, update_at) VALUES
(1001, 'AUD202501170001', 'RIDER_APPLY', 3001, '申请成为骑手', 1, 1003, NOW(), NOW()),
(1002, 'AUD202501170002', 'RIDER_APPLY', 3002, '申请成为骑手', 1, 1003, NOW(), NOW()),
(1003, 'AUD202501170003', 'RIDER_APPLY', 3003, '申请成为骑手', 0, NULL, NOW(), NOW());

-- 合伙人申请审核
INSERT INTO audit_record (id, audit_no, biz_type, applicant_id, remark, status, auditor_id, create_at, update_at) VALUES
(2001, 'AUD202501170004', 'PARTNER_APPLY', 5001, '申请成为合伙人', 1, 1003, NOW(), NOW()),
(2002, 'AUD202501170005', 'PARTNER_APPLY', 5002, '申请成为合伙人', 1, 1003, NOW(), NOW()),
(2003, 'AUD202501170006', 'PARTNER_APPLY', 5003, '申请成为合伙人', 0, NULL, NOW(), NOW());

-- 商家入驻审核
INSERT INTO audit_record (id, audit_no, biz_type, applicant_id, remark, status, auditor_id, create_at, update_at) VALUES
(3001, 'AUD202501170007', 'MERCHANT_SETTLE', 4001, '商家入驻申请', 1, 1003, NOW(), NOW()),
(3002, 'AUD202501170008', 'MERCHANT_SETTLE', 4002, '商家入驻申请', 1, 1003, NOW(), NOW()),
(3003, 'AUD202501170009', 'MERCHANT_SETTLE', 4003, '商家入驻申请', 0, NULL, NOW(), NOW());

-- 服务人员申请审核
INSERT INTO audit_record (id, audit_no, biz_type, applicant_id, remark, status, auditor_id, create_at, update_at) VALUES
(4001, 'AUD202501170010', 'STAFF_APPLY', 2001, '申请成为服务人员', 1, 1003, NOW(), NOW()),
(4002, 'AUD202501170011', 'STAFF_APPLY', 2002, '申请成为服务人员', 2, 1003, NOW(), NOW()),
(4003, 'AUD202501170012', 'STAFF_APPLY', 2003, '申请成为服务人员', 0, NULL, NOW(), NOW());

-- ========================================
-- 第五部分：文件管理测试数据
-- ========================================

-- 5.1 文件数据（sys_file）
INSERT INTO sys_file (id, file_name, bucket_name, original, type, file_size, create_at, update_at) VALUES
(1, 'avatar_admin.jpg', 'campus-avatar', 'admin.jpg', 'image/jpeg', 102400, NOW(), NOW()),
(2, 'license_mch1.jpg', 'campus-license', 'mch1_license.jpg', 'image/jpeg', 204800, NOW(), NOW()),
(3, 'logo_mch1.jpg', 'campus-logo', 'mch1_logo.jpg', 'image/jpeg', 153600, NOW(), NOW()),
(4, 'qrcode_partner1.jpg', 'campus-qrcode', 'partner1_qrcode.jpg', 'image/jpeg', 51200, NOW(), NOW()),
(5, 'document_contract.pdf', 'campus-doc', 'contract.pdf', 'application/pdf', 512000, NOW(), NOW());

-- ========================================
-- 第六部分：用户地址关联测试数据
-- ========================================

-- 6.1 用户地址关联（app_user_address）
INSERT INTO app_user_address (user_id, address_id) VALUES
(2001, 1),
(2001, 2),
(2002, 2),
(2002, 3),
(2003, 1),
(2004, 3),
(2004, 4),
(2005, 4);

-- ========================================
-- 第七部分：边界条件和异常场景测试数据
-- ========================================

-- 7.1 被拉黑的用户（用于测试状态修改接口）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(9001, 'blocked_user', '被拉黑用户', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 0, 'https://example.com/avatar/blocked.jpg', '13900139999', 'blocked@campus.com', 2, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_app (id, base_user_id, gender, openid, stu_code, balance, total_amount, school_id, audit_id, real_name, id_card, create_time, update_time) VALUES
(901, 9001, 1, 'o1234567890abcdef1234567890zz', '2099999', 0.00, 0.00, 1001, NULL, '被拉黑用户', '110101200001019999', NOW(), NOW());

-- 7.2 待审核的骑手（用于测试审核接口）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(9002, 'pending_rider', '待审核骑手', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/pending_rider.jpg', '13700137999', 'pending_rider@campus.com', 4, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_rider (id, base_user_id, real_name, id_card, card_number, audit_id, create_at, update_at) VALUES
(901, 9002, '待审核骑手', '110101199901019999', '6222021234567890999', 1003, NOW(), NOW());

-- 7.3 待审核的商家（用于测试商家入驻审核接口）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(9003, 'pending_mch', '待审核商家', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/pending_mch.jpg', '13600136999', 'pending_mch@campus.com', 3, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_mch (id, base_user_id, mch_name, address_id, logo, contact_name, business_license_urls, partner_id, id_card, minimum_order_amount, card_number, audit_id, business_start_time, business_end_time, is_open, create_time, update_time) VALUES
(901, 9003, '待审核商家', 5, 'https://example.com/logo/pending_mch.jpg', '待审核联系人', 'https://example.com/license/pending_mch.jpg', 5001, '110101198001019999', 10.00, '6222021234567890998', 3003, '08:00:00', '22:00:00', 0, NOW(), NOW());

-- 7.4 待审核的合伙人（用于测试合伙人审核接口）
INSERT INTO base_user (id, username, nickname, password, status, avatar, phone, email, user_type, create_at, update_at, last_login_at, last_login_ip) VALUES
(9004, 'pending_partner', '待审核合伙人', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1, 'https://example.com/avatar/pending_partner.jpg', '13500135999', 'pending_partner@campus.com', 3, NOW(), NOW(), NOW(), '127.0.0.1');

INSERT INTO user_partner (id, base_user_id, partner_name, invite_code, invite_code_path, card_number, commission_rate, parent_id, audit_id, create_at, update_at) VALUES
(901, 9004, '待审核合伙人', 'INVITE999', 'https://example.com/qrcode/pending_partner.jpg', '6222021234567890997', 5.00, 0, 2003, NOW(), NOW());

-- ========================================
-- 第八部分：测试数据说明
-- ========================================

/*
本SQL文件包含了完整的测试数据，覆盖了以下业务场景：

1. 基础配置数据：
   - 5个一级字典，20个二级字典
   - 7个一级菜单，25个二级菜单
   - 5个角色（超级管理员、系统管理员、审核员、运营人员、测试角色）
   - 3个OAuth2客户端（web、app、miniapp）

2. 用户相关数据：
   - 5个系统用户（包含不同角色）
   - 5个普通用户（包含正常和拉黑状态）
   - 3个骑手用户（包含不同审核状态）
   - 3个商家用户（包含不同审核状态）
   - 3个合伙人用户（包含不同审核状态）

3. 学校和地址数据：
   - 5个地址记录
   - 5个学校记录（包含启用和禁用状态）
   - 5条学校-合伙人关联记录

4. 审核记录数据：
   - 3条骑手申请审核（已通过、已通过、待审核）
   - 3条合伙人申请审核（已通过、已通过、待审核）
   - 3条商家入驻审核（已通过、已通过、待审核）
   - 3条服务人员申请审核（已通过、已拒绝、待审核）

5. 边界条件和异常场景：
   - 被拉黑的用户（用于测试状态修改接口）
   - 待审核的骑手（用于测试审核接口）
   - 待审核的商家（用于测试商家入驻审核接口）
   - 待审核的合伙人（用于测试合伙人审核接口）

所有密码统一为：123456（BCrypt加密）
所有用户默认状态为正常（status=1），部分用户设置为拉黑（status=0）
所有审核记录包含三种状态：待审核（0）、审核通过（1）、审核拒绝（2）

测试数据可用于验证以下接口：
- 用户管理：增删改查、状态修改、密码重置
- 角色管理：增删改查、权限分配
- 菜单管理：增删改查、树形结构查询
- 字典管理：增删改查、树形结构查询
- 学校管理：增删改查、合伙人关联
- 审核管理：骑手审核、合伙人审核、商家入驻审核、服务人员审核
- 客户端管理：查询客户端详情
*/
