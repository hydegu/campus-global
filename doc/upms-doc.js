{
    "openapi": "3.0.1",
    "info": {
    "title": "校园生活服务平台 - 管理端API",
        "description": "提供学校、用户、订单、服务等管理功能的管理端接口文档",
        "contact": {
        "name": "开发团队",
            "email": "dev@campus-app.com"
    },
    "license": {
        "name": "Apache 2.0",
            "url": "https://www.apache.org/licenses/LICENSE-2.0.html"
    },
    "version": "v1.0.0"
},
    "servers": [
    {
        "url": "http://172.16.8.163:8093",
        "description": "Generated server url"
    }
],
    "security": [
    {
        "Bearer Authentication": []
    }
],
    "tags": [
    {
        "name": "骑手提现审核",
        "description": "骑手提现记录的查询和审核功能。提供骑手提现记录的分页查询、详情查看、审核通过和审核拒绝操作。所有接口需要相应的配送管理权限。"
    },
    {
        "name": "用户提现管理",
        "description": "管理员对用户提现申请进行管理功能。提供用户提现记录的分页查询、审核通过和审核拒绝操作。所有接口需要相应的用户管理权限。"
    },
    {
        "name": "商家入驻管理",
        "description": "商家入驻申请的提交、查询、编辑、删除、审核等功能。提供商家入驻申请的分页查询、详情查看、新增、编辑、删除和审核操作。所有接口需要相应的商家管理权限。"
    },
    {
        "name": "商家管理",
        "description": "已审核商家的日常管理，与商家入驻审核分离"
    },
    {
        "name": "认证管理",
        "description": "用户登录、登出、令牌刷新等认证相关接口"
    },
    {
        "name": "商家分佣调控",
        "description": "商家分佣调控配置的管理功能。提供分佣配置的分页查询、修改和删除操作。所有接口需要相应的商家管理权限。"
    },
    {
        "name": "服务人员审核",
        "description": "管理员对服务人员申请进行审核功能。提供服务人员审核申请的分页查询、审核通过和审核拒绝操作。所有接口需要相应的审核权限。"
    },
    {
        "name": "商品管理",
        "description": "管理员对商家提交的商品进行管理功能。提供商品信息的分页查询、编辑、删除、上下架和审核操作。所有接口需要相应的商品管理权限。"
    },
    {
        "name": "用户管理",
        "description": "管理员对C端用户进行管理功能。提供用户信息的分页查询和状态修改（拉黑/解封）操作。所有接口需要相应的用户管理权限。"
    },
    {
        "name": "骑手管理",
        "description": "骑手的增删改查和密码重置功能。提供骑手信息的分页查询、详情查询、新增、编辑、删除和密码重置操作。所有接口需要相应的配送权限。"
    },
    {
        "name": "帖子评论管理",
        "description": "论坛帖子评论的管理功能。提供评论的删除操作。所有接口需要相应的论坛管理权限。"
    },
    {
        "name": "配送费设置",
        "description": "配送费配置的增删改查和状态管理功能。提供配送费配置的分页查询、详情查询、新增、编辑、删除和状态管理操作。主表：delivery_fee_config，明细表：delivery_fee_rule。所有接口需要相应的配送管理权限。"
    },
    {
        "name": "合伙人管理",
        "description": "合伙人的增删改查及审核功能"
    },
    {
        "name": "系统用户管理",
        "description": "负责系统用户（管理员、后台操作员等）的增删改查功能"
    },
    {
        "name": "帖子管理",
        "description": "论坛帖子的管理功能。提供帖子信息的分页查询、详情查询（包含评论）和删除操作。所有接口需要相应的论坛管理权限。"
    },
    {
        "name": "菜单管理",
        "description": "系统菜单的增删改查功能"
    },
    {
        "name": "骑手订单管理",
        "description": "骑手订单的查询功能。提供骑手订单的分页查询和详情查看操作。注：骑手订单不在此处创建，而是通过订单分配流程自动创建。所有接口需要相应的配送管理权限。"
    },
    {
        "name": "统计数据",
        "description": "平台流水、商家流水、服务人员流水、品类销售等统计接口"
    },
    {
        "name": "服务分佣配置",
        "description": "服务分佣配置的管理功能。提供服务分佣配置的分页查询、新增、修改和删除操作。所有接口需要相应的系统管理权限。"
    },
    {
        "name": "公告管理",
        "description": "论坛公告的管理功能。提供公告的分页查询、详情查询、新增、修改和删除操作。支持图片上传。所有接口需要相应的论坛管理权限。"
    },
    {
        "name": "平台账户管理",
        "description": "平台账户的增删改查及打款历史记录查询功能。提供平台账户信息的分页查询、详情查询、新增、修改、删除以及打款历史记录的查询和新增操作。所有接口需要相应的系统权限。"
    },
    {
        "name": "论坛活动管理",
        "description": "论坛活动的增删改查及审核功能"
    },
    {
        "name": "字典管理",
        "description": "系统字典的增删改查功能，提供字典数据的分页查询、树形结构查询、详情查询、新增、更新和删除操作。所有接口需要相应的系统权限。"
    },
    {
        "name": "骑手申请审核",
        "description": "骑手申请记录的查询和审核功能。提供骑手申请记录的分页查询、详情查看、审核通过和审核拒绝操作。所有接口需要相应的管理权限。"
    },
    {
        "name": "学校管理",
        "description": "学校信息的增删改查功能"
    },
    {
        "name": "商家订单管理",
        "description": "管理员对商家订单进行管理（列表查询、详情查看、删除）"
    },
    {
        "name": "服务人员订单管理",
        "description": "管理员对服务人员订单进行管理功能。提供服务人员订单的分页查询、详情查看、完成和删除操作。所有接口需要相应的订单管理权限。"
    },
    {
        "name": "服务分类管理",
        "description": "服务分类的增删改查功能。提供服务分类的树形结构查询、详情查询、新增、修改和删除操作。所有接口需要相应的服务管理权限。"
    },
    {
        "name": "角色管理",
        "description": "系统角色的增删改查功能"
    },
    {
        "name": "商家分类管理",
        "description": "商家分类的增删改查功能"
    }
],
    "paths": {
    "/api/user/withdrawal/{id}/reject": {
        "put": {
            "tags": [
                "用户提现管理"
            ],
                "summary": "审核拒绝提现申请",
                "description": "审核拒绝指定的用户提现申请。需要提供审核意见（必填）。需要权限：appUser:withdrawal:reject",
                "operationId": "rejectUserWithdrawal",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "提现记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/WithdrawalAuditDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/user/withdrawal/{id}/approve": {
        "put": {
            "tags": [
                "用户提现管理"
            ],
                "summary": "审核通过提现申请",
                "description": "审核通过指定的用户提现申请。需要提供审核意见和实际到账金额。需要权限：appUser:withdrawal:approve",
                "operationId": "approveUserWithdrawal",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "提现记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/WithdrawalAuditDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/user/withdrawal/status": {
        "put": {
            "tags": [
                "用户提现管理"
            ],
                "summary": "接收交易状态通知",
                "description": "接收前端系统发送的交易状态通知（ok/fail/cancel），更新交易流水和用户提现记录。需要权限：appUser:withdrawal:updateStatus",
                "operationId": "updateTransactionStatus",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/TransactionStatusNotificationDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultTransactionStatusUpdateResponse"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/user/app-user/{id}/status": {
        "put": {
            "tags": [
                "用户管理"
            ],
                "summary": "修改用户状态（拉黑/解封）",
                "description": "修改指定用户的状态，支持拉黑或解封操作。需要提供目标状态和操作原因。需要权限：app:user:updateStatus",
                "operationId": "updateUserStatus",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "用户ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/UserStatusDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user/{id}": {
        "get": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "获取用户详情",
                "description": "根据用户ID查询系统用户详细信息，返回包含角色、权限等完整信息。需要权限：system:user:query",
                "operationId": "getSystemUserDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSysUserVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "更新系统用户",
                "description": "更新系统用户信息，所有字段均可选。支持上传头像文件（支持jpg/png/gif/webp等图片格式，最大5MB）。业务逻辑：1. 验证用户存在 2. 更新用户信息 3. 更新角色关联 4. 如果上传了头像，替换原头像。需要权限：system:user:edit",
                "operationId": "updateSystemUser",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "username",
                    "in": "query",
                    "description": "用户名",
                    "required": false,
                    "schema": {
                        "maxLength": 20,
                        "minLength": 3,
                        "pattern": "^[a-zA-Z0-9_]{3,20}$",
                        "type": "string",
                        "description": "用户名",
                        "example": "admin"
                    },
                    "example": "admin"
                },
                {
                    "name": "email",
                    "in": "query",
                    "description": "邮箱",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "邮箱",
                        "example": "admin@example.com"
                    },
                    "example": "admin@example.com"
                },
                {
                    "name": "phone",
                    "in": "query",
                    "description": "手机号",
                    "required": false,
                    "schema": {
                        "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "手机号",
                        "example": 13800138000
                    },
                    "example": 13800138000
                },
                {
                    "name": "password",
                    "in": "query",
                    "description": "密码（不传则不修改密码）",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "密码（不传则不修改密码）",
                        "example": "NewAdmin@123"
                    },
                    "example": "NewAdmin@123"
                },
                {
                    "name": "confirmPassword",
                    "in": "query",
                    "description": "确认密码",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "确认密码",
                        "example": "NewAdmin@123"
                    },
                    "example": "NewAdmin@123"
                },
                {
                    "name": "roleIds",
                    "in": "query",
                    "description": "角色ID列表（不传则不修改角色）",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "角色ID列表（不传则不修改角色）",
                        "example": [1, 2, 3]
                    },
                    "example": [1, 2, 3]
                },
                {
                    "name": "avatar",
                    "in": "query",
                    "description": "头像URL（内部使用，由文件上传后自动设置）",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "头像URL（内部使用，由文件上传后自动设置）"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "multipart/form-data": {
                        "schema": {
                            "type": "object",
                                "properties": {
                                "avatarFile": {
                                    "type": "string",
                                        "format": "binary"
                                }
                            }
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "删除系统用户",
                "description": "删除指定的系统用户（软删除）。执行软删除，设置deleted_at字段，不会物理删除数据。需要权限：system:user:delete",
                "operationId": "deleteSystemUser",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user/{id}/status": {
        "put": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "修改用户状态",
                "description": "启用或禁用系统用户，禁用后用户无法登录系统",
                "operationId": "updateUserStatus_1",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "status",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int32"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user/{id}/reset-password": {
        "put": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "重置用户密码",
                "description": "重置系统用户密码为手机号后6位。需要权限：system:user:edit",
                "operationId": "resetUserPassword",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/settlement-account/{id}": {
        "get": {
            "tags": [
                "平台账户管理"
            ],
                "summary": "获取平台账户详情",
                "description": "根据账户ID查询平台账户详细信息。需要权限：system:settlement:query",
                "operationId": "getSettlementAccountDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "账户ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSettlementAccountVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "平台账户管理"
            ],
                "summary": "修改平台账户",
                "description": "更新平台账户信息，所有字段均可选。注意：paymentDate字段仅为前端展示，不会更新到数据库。需要权限：system:settlement:edit",
                "operationId": "updateSettlementAccount",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "账户ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SettlementAccountUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "平台账户管理"
            ],
                "summary": "删除平台账户",
                "description": "删除指定的平台账户。需要权限：system:settlement:delete",
                "operationId": "deleteSettlementAccount",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "账户ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/role/{id}": {
        "put": {
            "tags": [
                "角色管理"
            ],
                "summary": "修改角色",
                "description": "更新系统角色信息，所有字段均可选。需要权限：system:role:edit",
                "operationId": "updateSystemRole",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysRoleUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "角色管理"
            ],
                "summary": "删除角色",
                "description": "硬删除系统角色，删除后不可恢复",
                "operationId": "deleteSystemRole",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/partner/{id}": {
        "get": {
            "tags": [
                "合伙人管理"
            ],
                "summary": "获取合伙人编辑详情",
                "description": "根据合伙人ID查询合伙人详细信息，返回包含管理区域名称和ID、打款银行卡号、所有合伙人列表的详细信息。需要权限：system:partner:query",
                "operationId": "getPartnerDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSysPartnerDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "合伙人管理"
            ],
                "summary": "更新合伙人",
                "description": "更新合伙人信息，所有字段均可选",
                "operationId": "updatePartner",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysPartnerUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "合伙人管理"
            ],
                "summary": "删除合伙人",
                "description": "删除指定的合伙人（软删除）。需要权限：system:partner:delete",
                "operationId": "deletePartner",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/service/commission-config/{id}": {
        "put": {
            "tags": [
                "服务分佣配置"
            ],
                "summary": "修改服务分佣配置",
                "description": "更新服务分佣配置信息，所有字段均可选。需要权限：system:serviceCommission:edit",
                "operationId": "updateServiceCommissionConfig",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ServiceCommissionConfigUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "服务分佣配置"
            ],
                "summary": "删除服务分佣配置",
                "description": "删除指定的服务分佣配置。需要权限：system:serviceCommission:delete",
                "operationId": "deleteServiceCommissionConfig",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/service-category/{id}": {
        "get": {
            "tags": [
                "服务分类管理"
            ],
                "summary": "获取分类详情",
                "description": "根据分类ID查询服务分类的详细信息，包括分类名称、父分类、排序、状态等。需要权限：service:category:detail",
                "operationId": "getServiceCategoryDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "分类ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultServiceCategoryDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "服务分类管理"
            ],
                "summary": "修改分类",
                "description": "更新服务分类信息，包括分类名称、父分类、排序、状态等。需要权限：service:category:edit",
                "operationId": "updateServiceCategory",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "分类ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ServiceCategoryUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "服务分类管理"
            ],
                "summary": "删除分类",
                "description": "删除指定的服务分类。删除前会检查分类下是否有子分类或关联的服务，如有则不允许删除。需要权限：service:category:delete",
                "operationId": "deleteServiceCategory",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "分类ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/school/{id}": {
        "get": {
            "tags": [
                "学校管理"
            ],
                "summary": "查询学校详情",
                "description": "根据ID查询学校的详细信息",
                "operationId": "getSchoolById",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSysSchoolVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "学校管理"
            ],
                "summary": "部分更新学校信息",
                "description": "更新学校信息，只更新传入的字段。可以更新学校名称、地址、状态等信息。需要权限：system:school:edit",
                "operationId": "partialUpdateSchool",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SchoolPartialUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "学校管理"
            ],
                "summary": "删除学校",
                "description": "删除指定的学校。需要权限：system:school:delete",
                "operationId": "deleteSchool",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/order/staff-order/{id}/complete": {
        "put": {
            "tags": [
                "服务人员订单管理"
            ],
                "summary": "完成服务人员订单",
                "description": "将指定的服务人员订单标记为已完成。需要权限：order:staff:complete",
                "operationId": "completeStaffOrder",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "订单ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/order/merchant-order/{id}/complete": {
        "put": {
            "tags": [
                "商家订单管理"
            ],
                "summary": "完成商家订单",
                "description": "将指定的商家订单标记为已完成。需要权限：order:merchant:complete",
                "operationId": "completeMerchantOrder",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant/{id}": {
        "get": {
            "tags": [
                "商家管理"
            ],
                "summary": "获取商家详情",
                "description": "根据商家ID查询商家详细信息，返回包含关联的合伙人、结算账户等完整信息。需要权限：merchant:detail",
                "operationId": "getMerchantDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSysMerchantDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "商家管理"
            ],
                "summary": "编辑商家",
                "description": "更新商家信息，所有字段均可选",
                "operationId": "updateMerchant",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysMerchantUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "商家管理"
            ],
                "summary": "删除商家",
                "description": "删除指定的商家。删除策略：商家记录（sys_merchant）软删除（设置deleted_at）。需要权限：merchant:delete",
                "operationId": "deleteMerchant",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant/settle-in/{id}": {
        "get": {
            "tags": [
                "商家入驻管理"
            ],
                "summary": "获取商家入驻申请详情",
                "description": "根据商家ID查询商家入驻申请详细信息，返回包含关联的合伙人、结算账户、审核记录等完整信息。需要权限：audit:merchant:detail",
                "operationId": "getMerchantSettleInDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商家ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultMerchantSettleInVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "商家入驻管理"
            ],
                "summary": "编辑商家入驻申请",
                "description": "更新商家入驻申请信息，所有字段均可选。状态校验：只能编辑待审核状态（audit_status = 0）的申请。若申请已审核（通过或拒绝），则抛出BusinessException。需要权限：audit:merchant:edit",
                "operationId": "updateMerchantSettleInApplication",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商家ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/MerchantSettleInUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "商家入驻管理"
            ],
                "summary": "删除商家入驻申请",
                "description": "删除指定的商家入驻申请。状态校验：只能删除待审核状态（audit_status = 0）的申请。若申请已审核（通过或拒绝），则抛出BusinessException。删除策略：- 审核记录（audit_record）：硬删除（该表无deleted_at字段） - 商家记录（sys_merchant）：不删除，仅解除与审核记录的关联（audit_id设为null） - 结算账户（sys_settlement_account）：不删除（可能被其他商家关联）。需要权限：audit:merchant:delete",
                "operationId": "deleteMerchantSettleInApplication",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商家ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/menu/{id}": {
        "get": {
            "tags": [
                "菜单管理"
            ],
                "summary": "获取菜单详情",
                "description": "根据菜单ID查询菜单详细信息。需要权限：system:menu:query",
                "operationId": "getMenuDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSysMenuVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "菜单管理"
            ],
                "summary": "更新菜单",
                "description": "更新系统菜单信息，所有字段均可选。需要权限：system:menu:edit",
                "operationId": "updateMenu",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysMenuUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "菜单管理"
            ],
                "summary": "删除菜单",
                "description": "删除指定的系统菜单",
                "operationId": "deleteMenu",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch/product/{id}": {
        "get": {
            "tags": [
                "商品管理"
            ],
                "summary": "获取商品编辑详情",
                "description": "根据商品ID查询商品编辑详情，返回包含完整商品信息、规格数据、图片列表的详细信息。需要权限：mch:product:detail",
                "operationId": "getProductDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商品ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultProductEditVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "商品管理"
            ],
                "summary": "更新商品",
                "description": "更新商品信息，包括商品名称、标题、价格、收益配置、规格数据等。支持上传商品主图和商品图片列表（支持jpg/png/gif/webp等图片格式，最大5MB）。需要权限：mch:product:edit",
                "operationId": "updateProduct",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商品ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                },
                {
                    "name": "productName",
                    "in": "query",
                    "description": "产品名称",
                    "required": true,
                    "schema": {
                        "maxLength": 100,
                        "minLength": 0,
                        "type": "string",
                        "description": "产品名称",
                        "example": "可口可乐"
                    },
                    "example": "可口可乐"
                },
                {
                    "name": "title",
                    "in": "query",
                    "description": "商品标题",
                    "required": true,
                    "schema": {
                        "maxLength": 200,
                        "minLength": 0,
                        "type": "string",
                        "description": "商品标题",
                        "example": "冰镇可口可乐 330ml"
                    },
                    "example": "冰镇可口可乐 330ml"
                },
                {
                    "name": "sellingPoints",
                    "in": "query",
                    "description": "商品卖点",
                    "required": false,
                    "schema": {
                        "maxLength": 500,
                        "minLength": 0,
                        "type": "string",
                        "description": "商品卖点",
                        "example": "冰爽解渴"
                    },
                    "example": "冰爽解渴"
                },
                {
                    "name": "description",
                    "in": "query",
                    "description": "商品详情",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "商品详情",
                        "example": "产品详细介绍..."
                    },
                    "example": "产品详细介绍..."
                },
                {
                    "name": "mainImage",
                    "in": "query",
                    "description": "商品主图URL",
                    "required": false,
                    "schema": {
                        "maxLength": 255,
                        "minLength": 0,
                        "type": "string",
                        "description": "商品主图URL",
                        "example": "http://example.com/product.jpg"
                    },
                    "example": "http://example.com/product.jpg"
                },
                {
                    "name": "images",
                    "in": "query",
                    "description": "图片列表",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "图片列表",
                        "example": [
                            "http://example.com/img1.jpg",
                            "http://example.com/img2.jpg"
                        ]
                    },
                    "example": [
                        "http://example.com/img1.jpg",
                        "http://example.com/img2.jpg"
                    ]
                },
                {
                    "name": "categoryId",
                    "in": "query",
                    "description": "关联的商品分类ID",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "关联的商品分类ID",
                        "example": 1
                    },
                    "example": 1
                },
                {
                    "name": "price",
                    "in": "query",
                    "description": "价格",
                    "required": true,
                    "schema": {
                        "minimum": 0,
                        "type": "string",
                        "description": "价格",
                        "example": 3.5
                    },
                    "example": 3.5
                },
                {
                    "name": "profitType",
                    "in": "query",
                    "description": "收益类型（RATIO-按比例，FIXED-固定金额）",
                    "required": true,
                    "schema": {
                        "pattern": "^RATIO$|^FIXED$",
                        "type": "string",
                        "description": "收益类型（RATIO-按比例，FIXED-固定金额）",
                        "example": "RATIO"
                    },
                    "example": "RATIO"
                },
                {
                    "name": "partnerProfit",
                    "in": "query",
                    "description": "合伙人收益",
                    "required": true,
                    "schema": {
                        "minimum": 0,
                        "type": "string",
                        "description": "合伙人收益",
                        "example": 0.5
                    },
                    "example": 0.5
                },
                {
                    "name": "merchantProfit",
                    "in": "query",
                    "description": "服务商家收益",
                    "required": true,
                    "schema": {
                        "minimum": 0,
                        "type": "string",
                        "description": "服务商家收益",
                        "example": 2.5
                    },
                    "example": 2.5
                },
                {
                    "name": "specType",
                    "in": "query",
                    "description": "规格类型（SINGLE-统一规格，MULTIPLE-多规格）",
                    "required": true,
                    "schema": {
                        "pattern": "^SINGLE$|^MULTIPLE$",
                        "type": "string",
                        "description": "规格类型（SINGLE-统一规格，MULTIPLE-多规格）",
                        "example": "SINGLE"
                    },
                    "example": "SINGLE"
                },
                {
                    "name": "specData",
                    "in": "query",
                    "description": "规格数据",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "规格数据",
                        "example": [
                            {
                                "name": "默认规格",
                                "price": "3.50"
                            }
                        ]
                    },
                    "example": [
                        {
                            "name": "默认规格",
                            "price": "3.50"
                        }
                    ]
                },
                {
                    "name": "imageFiles",
                    "in": "query",
                    "description": "商品图片列表（可选，支持多张图片上传）",
                    "required": false,
                    "schema": {
                        "type": "array",
                        "items": {
                            "type": "string",
                            "format": "binary"
                        }
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "multipart/form-data": {
                        "schema": {
                            "type": "object",
                                "properties": {
                                "mainImageFile": {
                                    "type": "string",
                                        "description": "商品主图文件（可选，支持jpg/png/gif/webp等图片格式，最大5MB）",
                                        "format": "binary"
                                }
                            }
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "商品管理"
            ],
                "summary": "删除商品",
                "description": "删除指定的商品。需要权限：mch:product:delete",
                "operationId": "deleteProduct",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商品ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch/product/{id}/shelf-status": {
        "put": {
            "tags": [
                "商品管理"
            ],
                "summary": "修改商品上下架状态",
                "description": "修改商品的上下架状态。业务规则：- 只有审核通过（audit_status = 1）的商品才能上架 - 下架操作无限制。需要权限：mch:product:shelf",
                "operationId": "updateProductShelfStatus",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商品ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ProductShelfStatusDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch/commission/{id}": {
        "put": {
            "tags": [
                "商家分佣调控"
            ],
                "summary": "修改商家分佣调控配置",
                "description": "更新指定商家的分佣调控配置。需要提供新的分佣配置参数。需要权限：merchant:commission:edit",
                "operationId": "updateMerchantCommission",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "分佣配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/MchCommissionUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "商家分佣调控"
            ],
                "summary": "删除商家分佣调控配置",
                "description": "删除指定的商家分佣调控配置。需要权限：merchant:commission:delete",
                "operationId": "deleteMerchantCommission",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "分佣配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch-category/{id}": {
        "get": {
            "tags": [
                "商家分类管理"
            ],
                "summary": "获取商家分类详情",
                "description": "根据分类ID查询商家分类的详细信息，包括分类名称、父分类、排序、状态等。需要权限：merchant:category:detail",
                "operationId": "getMchCategoryDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultMchCategoryDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "商家分类管理"
            ],
                "summary": "修改商家分类",
                "description": "更新商家分类信息，包括分类名称、父分类、排序、状态等。需要权限：merchant:category:edit",
                "operationId": "updateMchCategory",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/MchCategoryUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "商家分类管理"
            ],
                "summary": "删除分类",
                "description": "删除指定的商家分类",
                "operationId": "deleteCategory",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/dict/{id}": {
        "get": {
            "tags": [
                "字典管理"
            ],
                "summary": "获取字典详情",
                "description": "根据ID查询字典详细信息，包括字典名称、编码、值、排序、状态等。需要权限：system:dict:query",
                "operationId": "getDictDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "字典ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSysDictVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "字典管理"
            ],
                "summary": "部分更新字典",
                "description": "部分更新字典信息，只更新传入的字段。可以更新字典名称、值、排序、状态等信息。需要权限：system:dict:edit",
                "operationId": "updateDict",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "字典ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysDictPartialUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "字典管理"
            ],
                "summary": "删除字典",
                "description": "删除指定的系统字典。删除前会检查字典是否被引用，如果有引用则不允许删除。需要权限：system:dict:delete",
                "operationId": "deleteDict",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "字典ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/{id}": {
        "get": {
            "tags": [
                "骑手管理"
            ],
                "summary": "获取骑手详情",
                "description": "根据骑手ID查询骑手详细信息，包含商家信息和订单统计（总订单数、已完成订单数、今日订单数、本月订单数）。需要权限：delivery:rider:detail",
                "operationId": "getRiderDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "骑手ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSysRiderDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "骑手管理"
            ],
                "summary": "编辑骑手",
                "description": "更新骑手信息，所有字段均可选。业务逻辑：1. 若修改手机号，验证手机号唯一性（排除自身） 2. 若修改商家，验证商家是否存在。需要权限：delivery:rider:edit",
                "operationId": "updateRider",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "骑手ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/RiderUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "骑手管理"
            ],
                "summary": "删除骑手",
                "description": "删除指定的骑手账号。删除策略：- 检查是否有未完成订单（order_status=1），如有则不允许删除 - 软删除（设置deleted_at）。需要权限：delivery:rider:delete",
                "operationId": "deleteRider",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "骑手ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/{id}/reset-password": {
        "put": {
            "tags": [
                "骑手管理"
            ],
                "summary": "重置骑手密码",
                "description": "重置骑手登录密码。业务逻辑：1. 验证骑手是否存在 2. 使用BCrypt加密新密码。新密码长度为6-20字符。需要权限：delivery:rider:reset",
                "operationId": "resetRiderPassword",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "骑手ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ResetPasswordDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/withdrawal/{id}/reject": {
        "put": {
            "tags": [
                "骑手提现审核"
            ],
                "summary": "审核拒绝骑手提现",
                "description": "审核拒绝指定的骑手提现申请。必填字段：拒绝理由。业务逻辑：1. 验证提现记录存在且审核状态为待审核（避免重复审核） 2. 更新audit_record状态为审核拒绝，记录审核人、审核时间和拒绝理由。需要权限：delivery:rider-withdrawal:reject",
                "operationId": "rejectRiderWithdrawal",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "提现记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/RiderWithdrawalRejectDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/withdrawal/{id}/approve": {
        "put": {
            "tags": [
                "骑手提现审核"
            ],
                "summary": "审核通过骑手提现",
                "description": "审核通过指定的骑手提现申请。可选字段：实际到账金额（默认为申请金额）、审核意见。业务逻辑：1. 验证提现记录存在且审核状态为待审核（避免重复审核） 2. 验证骑手余额是否充足 3. 更新audit_record状态为审核通过，记录审核人和审核时间 4. 立即扣减骑手余额（余额 = 余额 - 提现金额） 5. 更新打款状态为已打款，记录打款时间和操作人。需要权限：delivery:rider-withdrawal:approve",
                "operationId": "approveRiderWithdrawal",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "提现记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/RiderWithdrawalApproveDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/fee-config/{id}": {
        "get": {
            "tags": [
                "配送费设置"
            ],
                "summary": "获取配送费配置详情",
                "description": "根据配置ID查询配送费配置详情，返回包含主表信息和关联的计费规则列表（距离规则、时段规则）。需要权限：delivery:fee-config:detail",
                "operationId": "getDeliveryFeeConfigDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int32"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultDeliveryFeeConfigDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "配送费设置"
            ],
                "summary": "编辑配送费配置",
                "description": "更新配送费配置信息，所有字段均可选。业务逻辑：1. 更新主表delivery_fee_config 2. 如果提供了规则列表：- 先删除该配置的所有旧规则（DELETE WHERE config_id = #{id}） - 再批量插入新规则 3. 主从表操作在同一事务中。需要权限：delivery:fee-config:edit",
                "operationId": "updateDeliveryFeeConfig",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int32"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/FeeConfigUpdateDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "配送费设置"
            ],
                "summary": "删除配送费配置",
                "description": "删除指定的配送费配置。删除策略：- 主表和明细表级联删除（先删除关联的所有规则，再删除配置） - 硬删除（直接DELETE）。需要权限：delivery:fee-config:delete",
                "operationId": "deleteDeliveryFeeConfig",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int32"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/fee-config/{id}/status": {
        "put": {
            "tags": [
                "配送费设置"
            ],
                "summary": "启用/禁用配送费配置",
                "description": "更新配送费配置的启用状态。业务逻辑：- 如果是启用操作（status=1）：先将所有配置禁用，再启用当前配置 - 确保系统中仅有一个配置处于启用状态 - 如果是禁用操作（status=0）：直接禁用。需要权限：delivery:fee-config:status",
                "operationId": "updateDeliveryFeeConfigStatus",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "配置ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int32"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/FeeConfigStatusDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/announcement/{id}": {
        "get": {
            "tags": [
                "公告管理"
            ],
                "summary": "公告详情",
                "description": "根据公告ID查询公告详细信息，包括标题、内容、图片、发布时间等。需要权限：forum:announcement:detail",
                "operationId": "getAnnouncementDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "公告ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultForumAnnouncementVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "公告管理"
            ],
                "summary": "修改公告",
                "description": "更新公告信息，包括标题、内容、图片等。支持上传新的公告图片（可选）。需要权限：forum:announcement:edit",
                "operationId": "updateAnnouncement",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "公告ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                },
                {
                    "name": "title",
                    "in": "query",
                    "description": "公告标题",
                    "required": true,
                    "schema": {
                        "type": "string",
                        "description": "公告标题",
                        "example": "系统维护通知"
                    },
                    "example": "系统维护通知"
                },
                {
                    "name": "image",
                    "in": "query",
                    "description": "公告图片",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "公告图片",
                        "example": "http://example.com/announcement.jpg"
                    },
                    "example": "http://example.com/announcement.jpg"
                },
                {
                    "name": "isDisplay",
                    "in": "query",
                    "description": "显示状态(1-显示 0-隐藏)",
                    "required": true,
                    "schema": {
                        "maximum": 1,
                        "minimum": 0,
                        "type": "string",
                        "description": "显示状态(1-显示 0-隐藏)",
                        "example": 1
                    },
                    "example": 1
                },
                {
                    "name": "schoolId",
                    "in": "query",
                    "description": "关联学校ID，null表示系统公告",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "关联学校ID，null表示系统公告",
                        "example": 1
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "multipart/form-data": {
                        "schema": {
                            "type": "object",
                                "properties": {
                                "imageFile": {
                                    "type": "string",
                                        "description": "公告图片文件（可选）",
                                        "format": "binary"
                                }
                            }
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "公告管理"
            ],
                "summary": "删除公告",
                "description": "删除指定的公告。需要权限：forum:announcement:delete",
                "operationId": "deleteAnnouncement",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "公告ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/admin/rider/apply/reject/{id}": {
        "put": {
            "tags": [
                "骑手申请审核"
            ],
                "summary": "审核拒绝骑手申请",
                "description": "根据申请记录ID审核拒绝骑手申请，更新审核状态为已拒绝。业务逻辑：1.验证申请记录存在且审核状态为\"待审核\"（避免重复审核） 2.更新audit_record状态为\"审核拒绝\"，记录审核人、审核时间和拒绝理由。需要权限：admin:rider-apply:reject",
                "operationId": "rejectRiderApply",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "申请记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1025
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/RiderApplyRejectDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/admin/rider/apply/approve/{id}": {
        "put": {
            "tags": [
                "骑手申请审核"
            ],
                "summary": "审核通过骑手申请",
                "description": "根据申请记录ID审核通过骑手申请，更新审核状态为已通过。业务逻辑：1.验证申请记录存在且审核状态为\"待审核\"（避免重复审核） 2.更新audit_record状态为\"审核通过\"，记录审核人和审核时间。需要权限：admin:rider-apply:approve",
                "operationId": "approveRiderApply",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "申请记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1025
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/RiderApplyApproveDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/activity/{id}": {
        "get": {
            "tags": [
                "论坛活动管理"
            ],
                "summary": "获取活动详情",
                "description": "根据活动ID查询活动详细信息，包括报名情况统计，供用户查看特定活动的详细信息。需要权限：forum:activity:detail",
                "operationId": "getActivityDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "dto",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ForumUserDetailDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultForumActivityDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "put": {
            "tags": [
                "论坛活动管理"
            ],
                "summary": "更新活动信息",
                "description": "更新活动信息，供管理员或活动发起者修改活动信息。支持上传图片。需要权限：forum:activity:edit",
                "operationId": "updateActivity",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "活动ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                },
                {
                    "name": "activityTitle",
                    "in": "query",
                    "description": "活动标题",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动标题",
                        "example": "校园篮球赛"
                    },
                    "example": "校园篮球赛"
                },
                {
                    "name": "activityContent",
                    "in": "query",
                    "description": "活动内容",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动内容",
                        "example": "欢迎参加校园篮球赛"
                    },
                    "example": "欢迎参加校园篮球赛"
                },
                {
                    "name": "activityVenue",
                    "in": "query",
                    "description": "活动场地",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动场地",
                        "example": "体育馆"
                    },
                    "example": "体育馆"
                },
                {
                    "name": "schoolId",
                    "in": "query",
                    "description": "学校ID",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "学校ID",
                        "example": 1
                    },
                    "example": 1
                },
                {
                    "name": "maxParticipants",
                    "in": "query",
                    "description": "最大报名人数",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "最大报名人数",
                        "example": 100
                    },
                    "example": 100
                },
                {
                    "name": "registrationStartTime",
                    "in": "query",
                    "description": "报名开始时间",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "报名开始时间",
                        "example": "2024-01-01T10:00:00"
                    },
                    "example": "2024-01-01T10:00:00"
                },
                {
                    "name": "registrationEndTime",
                    "in": "query",
                    "description": "报名截止时间",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "报名截止时间",
                        "example": "2024-01-05T18:00:00"
                    },
                    "example": "2024-01-05T18:00:00"
                },
                {
                    "name": "activityTime",
                    "in": "query",
                    "description": "活动时间",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动时间",
                        "example": "2024-01-10T14:00:00"
                    },
                    "example": "2024-01-10T14:00:00"
                },
                {
                    "name": "image",
                    "in": "query",
                    "description": "活动图片",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动图片",
                        "example": "http://example.com/image.jpg"
                    },
                    "example": "http://example.com/image.jpg"
                },
                {
                    "name": "isVisible",
                    "in": "query",
                    "description": "是否显示(1-显示 0-隐藏)",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "是否显示(1-显示 0-隐藏)",
                        "example": 1
                    },
                    "example": 1
                },
                {
                    "name": "imageFiles",
                    "in": "query",
                    "required": false,
                    "schema": {
                        "type": "array",
                        "items": {
                            "type": "string",
                            "format": "binary"
                        }
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "论坛活动管理"
            ],
                "summary": "删除活动",
                "description": "删除指定的活动（软删除），供管理员或活动发起者删除活动。需要权限：forum:activity:delete",
                "operationId": "deleteActivity",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/user/withdrawal/transfer": {
        "post": {
            "tags": [
                "用户提现管理"
            ],
                "summary": "发起商家转账",
                "description": "平台向用户发起转账操作。支持批量转账，记录转账状态。需要权限：appUser:withdrawal:transfer",
                "operationId": "transferUserWithdrawal",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/UserTransferRequest"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultUserTransferResponse"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user": {
        "post": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "新增系统用户",
                "description": "创建新的系统用户。必填字段：用户名、手机号；可选字段：邮箱、角色ID、头像文件（支持jpg/png/gif/webp等图片格式，最大5MB）。业务逻辑：1. 验证用户名和手机号唯一性 2. 保存用户信息 3. 关联角色 4. 设置默认密码（手机号后6位）。需要权限：system:user:add",
                "operationId": "addSystemUser",
                "parameters": [
                {
                    "name": "username",
                    "in": "query",
                    "description": "用户名",
                    "required": true,
                    "schema": {
                        "maxLength": 20,
                        "minLength": 3,
                        "pattern": "^[a-zA-Z0-9_]{3,20}$",
                        "type": "string",
                        "description": "用户名",
                        "example": "admin"
                    },
                    "example": "admin"
                },
                {
                    "name": "email",
                    "in": "query",
                    "description": "邮箱",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "邮箱",
                        "example": "admin@example.com"
                    },
                    "example": "admin@example.com"
                },
                {
                    "name": "phone",
                    "in": "query",
                    "description": "手机号",
                    "required": true,
                    "schema": {
                        "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "手机号",
                        "example": 13800138000
                    },
                    "example": 13800138000
                },
                {
                    "name": "password",
                    "in": "query",
                    "description": "密码（不传则默认为手机号后6位）",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "密码（不传则默认为手机号后6位）",
                        "example": "Admin@123"
                    },
                    "example": "Admin@123"
                },
                {
                    "name": "confirmPassword",
                    "in": "query",
                    "description": "确认密码",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "确认密码",
                        "example": "Admin@123"
                    },
                    "example": "Admin@123"
                },
                {
                    "name": "roleIds",
                    "in": "query",
                    "description": "角色ID列表",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "角色ID列表",
                        "example": [1, 2, 3]
                    },
                    "example": [1, 2, 3]
                },
                {
                    "name": "avatar",
                    "in": "query",
                    "description": "头像URL（内部使用，由文件上传后自动设置）",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "头像URL（内部使用，由文件上传后自动设置）"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "multipart/form-data": {
                        "schema": {
                            "type": "object",
                                "properties": {
                                "avatarFile": {
                                    "type": "string",
                                        "format": "binary"
                                }
                            }
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/settlement-account": {
        "post": {
            "tags": [
                "平台账户管理"
            ],
                "summary": "新增平台账户",
                "description": "创建新的平台账户。必填字段：账户名称、联系方式、银行卡号、开户名。注意：paymentDate字段仅为前端展示，不会存储到数据库，account_code自动生成。需要权限：system:settlement:add",
                "operationId": "addSettlementAccount",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SettlementAccountAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/settlement-account/{id}/payment-history": {
        "get": {
            "tags": [
                "平台账户管理"
            ],
                "summary": "查询打款历史记录",
                "description": "查询指定结算账户的打款历史记录。点击结算账户列表中的历史记录按钮时调用此接口。联表查询结算账户和支付记录表，返回该账户的所有打款流水。支持按关键字、打款账户、支付日期范围等条件筛选。需要权限：system:settlement:paymentHistory",
                "operationId": "getPaymentHistory",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "结算账户ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                },
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含关键字、打款账户、支付日期范围等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/PaymentHistoryQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultPaymentHistoryVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "post": {
            "tags": [
                "平台账户管理"
            ],
                "summary": "增加打款记录",
                "description": "为指定的结算账户增加打款记录。需要提供收款账户、用户类型、打款金额等信息。需要权限：system:settlement:paymentHistory:add",
                "operationId": "addPaymentHistory",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "结算账户ID，用于找到对应的打款账户",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/PaymentHistoryAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/role": {
        "post": {
            "tags": [
                "角色管理"
            ],
                "summary": "新增角色",
                "description": "创建新的系统角色。必填字段：角色名称、权限字符、角色顺序、状态、菜单权限集。需要权限：system:role:add",
                "operationId": "addSystemRole",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysRoleAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/partner": {
        "post": {
            "tags": [
                "合伙人管理"
            ],
                "summary": "新增合伙人",
                "description": "创建新的合伙人。必填字段：姓名、手机号、状态。需要权限：system:partner:add",
                "operationId": "addPartner",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysPartnerAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/partner/{id}/reject": {
        "post": {
            "tags": [
                "合伙人管理"
            ],
                "summary": "审批拒绝合伙人申请",
                "description": "审核拒绝合伙人申请，需填写拒绝原因",
                "operationId": "rejectPartner",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "auditOpinion",
                    "in": "query",
                    "required": false,
                    "schema": {
                        "type": "string"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/partner/{id}/approve": {
        "post": {
            "tags": [
                "合伙人管理"
            ],
                "summary": "审批通过合伙人申请",
                "description": "审核通过合伙人申请，可附带审核意见",
                "operationId": "approvePartner",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "auditOpinion",
                    "in": "query",
                    "required": false,
                    "schema": {
                        "type": "string"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/service/commission-config": {
        "post": {
            "tags": [
                "服务分佣配置"
            ],
                "summary": "新增服务分佣配置",
                "description": "创建新的服务分佣配置。必填字段：配置类型、分佣比例、分佣类型、状态；可选字段：分类ID。需要权限：system:serviceCommission:add",
                "operationId": "addServiceCommissionConfig",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ServiceCommissionConfigAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/service-category": {
        "post": {
            "tags": [
                "服务分类管理"
            ],
                "summary": "新增分类",
                "description": "创建新的服务分类。需要提供分类名称、父分类ID等基本信息。需要权限：service:category:add",
                "operationId": "addServiceCategory",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ServiceCategoryAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/school": {
        "post": {
            "tags": [
                "学校管理"
            ],
                "summary": "新增学校",
                "description": "创建新的学校。需要提供学校名称、地址等基本信息。需要权限：system:school:add",
                "operationId": "addSchool",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SchoolAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant": {
        "post": {
            "tags": [
                "商家管理"
            ],
                "summary": "新增商家",
                "description": "创建新的商家。必填字段：商家名称、联系方式、所属合伙人ID、结算卡号、开户名。业务逻辑：1. 验证所属合伙人是否存在 2. 若提供结算账户ID，验证其有效性；否则自动创建结算账户 3. 创建商家记录 4. 设置默认密码（手机号后6位）。需要权限：merchant:add",
                "operationId": "addMerchant",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysMerchantAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant/{id}/reset-password": {
        "post": {
            "tags": [
                "商家管理"
            ],
                "summary": "重置商家密码",
                "description": "重置商家密码为手机号后6位",
                "operationId": "resetMerchantPassword",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant/settle-in": {
        "post": {
            "tags": [
                "商家入驻管理"
            ],
                "summary": "新增商家入驻申请",
                "description": "创建新的商家入驻申请。必填字段：商家名称、联系方式、所属合伙人ID、结算卡号、开户名。业务逻辑：1. 验证所属合伙人是否存在 2. 若提供结算账户ID，验证其有效性；否则自动创建结算账户 3. 创建商家记录（status=0待审核） 4. 创建审核记录（audit_status=0待审核） 5. 关联商家与审核记录。需要权限：audit:merchant:add",
                "operationId": "addMerchantSettleInApplication",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/MerchantSettleInAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant/settle-in/{id}/audit": {
        "post": {
            "tags": [
                "商家入驻管理"
            ],
                "summary": "审核商家入驻申请",
                "description": "审核指定的商家入驻申请。必填字段：审核结果（1-通过，2-拒绝）；可选字段：审核意见（最多500字符）。业务逻辑：1. 验证申请是否存在且关联了审核记录 2. 状态校验：只能审核待审核状态（audit_status = 0）的申请 3. 获取当前登录用户作为审核人（使用SecurityUtils） 4. 更新审核记录：设置审核状态、审核意见、审核时间、审核人信息 5. 更新商家状态：- 审核通过（audit_status = 1）→ merchant.status = 1（启用） - 审核拒绝（audit_status = 2）→ merchant.status = 0（禁用）。需要权限：audit:merchant:update",
                "operationId": "auditMerchantSettleInApplication",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商家ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/MerchantAuditDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/menu": {
        "post": {
            "tags": [
                "菜单管理"
            ],
                "summary": "新增菜单",
                "description": "创建新的系统菜单。必填字段：菜单名称、菜单类型、路径等。需要权限：system:menu:add",
                "operationId": "addMenu",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysMenuAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch/product/{id}/audit": {
        "post": {
            "tags": [
                "商品管理"
            ],
                "summary": "审核商品",
                "description": "对商品进行审核（通过/拒绝）。业务逻辑：1. 验证商品是否存在 2. 如果商品没有关联审核记录，自动创建一个审核记录 3. 获取当前登录用户作为审核人（使用SecurityUtils） 4. 更新审核记录：设置审核状态、审核意见、审核时间、审核人信息 5. 如果审核拒绝且商品已上架，自动下架商品。需要权限：mch:product:audit",
                "operationId": "auditProduct",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "商品ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ProductAuditDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch-category": {
        "post": {
            "tags": [
                "商家分类管理"
            ],
                "summary": "新增商家分类",
                "description": "创建新的商家分类。需要提供分类名称、父分类ID等基本信息。需要权限：merchant:category:add",
                "operationId": "addMchCategory",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/MchCategoryAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/dict": {
        "post": {
            "tags": [
                "字典管理"
            ],
                "summary": "新增字典",
                "description": "创建新的系统字典，需要提供字典名称、编码、值等基本信息。字典编码必须唯一。需要权限：system:dict:add",
                "operationId": "addDict",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/SysDictAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider": {
        "post": {
            "tags": [
                "骑手管理"
            ],
                "summary": "新增骑手",
                "description": "创建新的骑手账号。必填字段：所属商家、姓名、手机号、密码。业务逻辑：1. 验证手机号唯一性 2. 验证所属商家是否存在 3. 密码使用BCrypt加密 4. 初始余额和累计佣金为0。需要权限：delivery:rider:add",
                "operationId": "addRider",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/RiderAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/fee-config": {
        "post": {
            "tags": [
                "配送费设置"
            ],
                "summary": "新增配送费配置",
                "description": "创建新的配送费配置。必填字段：配置名称、基础费用、基础距离；可选字段：计费规则列表（支持距离规则和时段规则）。业务逻辑：1. 主从表事务：先插入主表delivery_fee_config 2. 再批量插入明细表delivery_fee_rule（如果有规则） 3. 新增时默认为禁用状态（status=0）。需要权限：delivery:fee-config:add",
                "operationId": "addDeliveryFeeConfig",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/FeeConfigAddDTO"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/auth/refresh": {
        "post": {
            "tags": [
                "认证管理"
            ],
                "summary": "刷新令牌",
                "description": "使用刷新令牌获取新的访问令牌，支持Cookie模式和请求体模式",
                "operationId": "refreshToken",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/RefreshRequest"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultAuthResponse"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/auth/logout": {
        "post": {
            "tags": [
                "认证管理"
            ],
                "summary": "用户登出",
                "description": "用户登出系统，清除服务器端的会话信息和令牌。支持Cookie模式和请求体模式",
                "operationId": "logout",
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultString"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/auth/login": {
        "post": {
            "tags": [
                "认证管理"
            ],
                "summary": "用户登录",
                "description": "用户登录系统，验证用户名和密码，返回访问令牌和刷新令牌。支持Cookie模式和请求体模式返回令牌",
                "operationId": "login",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/LoginRequest"
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultAuthResponse"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/audit/staff/{id}/reject": {
        "post": {
            "tags": [
                "服务人员审核"
            ],
                "summary": "审核拒绝服务人员申请",
                "description": "审核拒绝指定的服务人员申请。可选提供审核意见。需要权限：audit:staff:reject",
                "operationId": "rejectServiceStaff",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "用户ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                },
                {
                    "name": "auditOpinion",
                    "in": "query",
                    "description": "审核意见（可选）",
                    "required": false,
                    "schema": {
                        "type": "string"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/audit/staff/{id}/approve": {
        "post": {
            "tags": [
                "服务人员审核"
            ],
                "summary": "审核通过服务人员申请",
                "description": "审核通过指定的服务人员申请。可选提供审核意见。需要权限：audit:staff:approve",
                "operationId": "approveServiceStaff",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "用户ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                },
                {
                    "name": "auditOpinion",
                    "in": "query",
                    "description": "审核意见（可选）",
                    "required": false,
                    "schema": {
                        "type": "string"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/announcement": {
        "post": {
            "tags": [
                "公告管理"
            ],
                "summary": "新增公告",
                "description": "创建新的公告。支持上传公告图片（可选）。需要提供标题、内容等基本信息。需要权限：forum:announcement:add",
                "operationId": "addAnnouncement",
                "parameters": [
                {
                    "name": "title",
                    "in": "query",
                    "description": "公告标题",
                    "required": true,
                    "schema": {
                        "type": "string",
                        "description": "公告标题",
                        "example": "系统维护通知"
                    },
                    "example": "系统维护通知"
                },
                {
                    "name": "image",
                    "in": "query",
                    "description": "公告图片",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "公告图片",
                        "example": "http://example.com/announcement.jpg"
                    },
                    "example": "http://example.com/announcement.jpg"
                },
                {
                    "name": "isDisplay",
                    "in": "query",
                    "description": "显示状态(1-显示 0-隐藏)",
                    "required": true,
                    "schema": {
                        "maximum": 1,
                        "minimum": 0,
                        "type": "string",
                        "description": "显示状态(1-显示 0-隐藏)",
                        "example": 1
                    },
                    "example": 1
                },
                {
                    "name": "schoolId",
                    "in": "query",
                    "description": "关联学校ID，null表示系统公告",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "关联学校ID，null表示系统公告",
                        "example": 1
                    },
                    "example": 1
                }
            ],
                "requestBody": {
                "content": {
                    "multipart/form-data": {
                        "schema": {
                            "type": "object",
                                "properties": {
                                "imageFile": {
                                    "type": "string",
                                        "description": "公告图片文件（可选）",
                                        "format": "binary"
                                }
                            }
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/activity": {
        "post": {
            "tags": [
                "论坛活动管理"
            ],
                "summary": "创建新活动",
                "description": "供管理员或活动发起者创建新活动，支持上传图片",
                "operationId": "addActivity",
                "parameters": [
                {
                    "name": "activityTitle",
                    "in": "query",
                    "description": "活动标题",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动标题",
                        "example": "校园篮球赛"
                    },
                    "example": "校园篮球赛"
                },
                {
                    "name": "activityContent",
                    "in": "query",
                    "description": "活动内容",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动内容",
                        "example": "欢迎参加校园篮球赛"
                    },
                    "example": "欢迎参加校园篮球赛"
                },
                {
                    "name": "activityVenue",
                    "in": "query",
                    "description": "活动场地",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动场地",
                        "example": "体育馆"
                    },
                    "example": "体育馆"
                },
                {
                    "name": "schoolId",
                    "in": "query",
                    "description": "学校ID",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "学校ID",
                        "example": 1
                    },
                    "example": 1
                },
                {
                    "name": "maxParticipants",
                    "in": "query",
                    "description": "最大报名人数",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "最大报名人数",
                        "example": 100
                    },
                    "example": 100
                },
                {
                    "name": "registrationStartTime",
                    "in": "query",
                    "description": "报名开始时间",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "报名开始时间",
                        "example": "2024-01-01T10:00:00"
                    },
                    "example": "2024-01-01T10:00:00"
                },
                {
                    "name": "registrationEndTime",
                    "in": "query",
                    "description": "报名截止时间",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "报名截止时间",
                        "example": "2024-01-05T18:00:00"
                    },
                    "example": "2024-01-05T18:00:00"
                },
                {
                    "name": "activityTime",
                    "in": "query",
                    "description": "活动时间",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动时间",
                        "example": "2024-01-10T14:00:00"
                    },
                    "example": "2024-01-10T14:00:00"
                },
                {
                    "name": "images",
                    "in": "query",
                    "description": "活动图片(JSON数组)",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "活动图片(JSON数组)",
                        "example": [
                            "http://example.com/image1.jpg",
                            "http://example.com/image2.jpg"
                        ]
                    },
                    "example": [
                        "http://example.com/image1.jpg",
                        "http://example.com/image2.jpg"
                    ]
                },
                {
                    "name": "isVisible",
                    "in": "query",
                    "description": "是否显示(1-显示 0-隐藏)",
                    "required": false,
                    "schema": {
                        "type": "string",
                        "description": "是否显示(1-显示 0-隐藏)",
                        "example": 1
                    },
                    "example": 1
                },
                {
                    "name": "imageFiles",
                    "in": "query",
                    "required": false,
                    "schema": {
                        "type": "array",
                        "items": {
                            "type": "string",
                            "format": "binary"
                        }
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/activity/{id}/reject": {
        "post": {
            "tags": [
                "论坛活动管理"
            ],
                "summary": "审核拒绝活动",
                "description": "供管理员审核活动，审核拒绝后活动将被取消",
                "operationId": "rejectActivity",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ForumActivityAuditDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/activity/{id}/approve": {
        "post": {
            "tags": [
                "论坛活动管理"
            ],
                "summary": "审核通过活动",
                "description": "供管理员审核活动，审核通过后活动将发布",
                "operationId": "approveActivity",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "$ref": "#/components/schemas/ForumActivityAuditDTO"
                        }
                    }
                }
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/user/withdrawal/list": {
        "get": {
            "tags": [
                "用户提现管理"
            ],
                "summary": "分页查询提现记录列表",
                "description": "根据查询条件分页查询用户提现记录列表，支持按用户名、手机号、审核状态、提现方式、申请时间等条件筛选。需要权限：appUser:withdrawal:list",
                "operationId": "listUserWithdrawals",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含用户名、手机号、审核状态、提现方式、申请时间等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/WithdrawalQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultWithdrawalListVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/user/app-user/list": {
        "get": {
            "tags": [
                "用户管理"
            ],
                "summary": "分页查询用户列表",
                "description": "根据查询条件分页查询用户列表，支持按用户名、手机号、状态、学校、用户类型、注册时间等条件筛选。需要权限：app:user:list",
                "operationId": "listUsers",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含用户名、手机号、状态、学校、用户类型、注册时间等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/UserQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultUserListVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user/permissions": {
        "get": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "获取当前用户权限",
                "description": "通过当前登录用户查询其拥有的权限字符集，超管返回*",
                "operationId": "getUserPermissions",
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultSetString"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user/menus": {
        "get": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "获取当前用户菜单",
                "description": "通过当前登录用户查询其拥有的菜单树结构，返回用户可见的所有菜单项",
                "operationId": "getUserMenus",
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListSysMenuVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user/list": {
        "get": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "分页查询系统用户列表",
                "description": "根据查询条件分页查询系统用户列表，支持按用户名、邮箱、手机号、状态等条件筛选。返回包含用户信息的分页结果。需要权限：system:user:list",
                "operationId": "listSystemUsers",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/SysUserQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSysUserVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/settlement-account/list": {
        "get": {
            "tags": [
                "平台账户管理"
            ],
                "summary": "分页查询平台账户列表",
                "description": "根据查询条件分页查询平台账户列表，支持按关键字、银行卡号、支付时间范围、用户类型、状态等条件筛选。返回包含最后一次支付日期的账户列表。需要权限：system:settlement:list",
                "operationId": "listSettlementAccounts",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含关键字、银行卡号、支付时间范围、用户类型、状态等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/SettlementAccountQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSettlementAccountVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/role/list": {
        "get": {
            "tags": [
                "角色管理"
            ],
                "summary": "分页查询系统角色列表",
                "description": "根据查询条件分页查询系统角色列表，支持按角色名称、权限字符、角色状态等条件筛选。返回包含角色信息的分页结果。需要权限：system:role:list",
                "operationId": "listSystemRoles",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/SysRoleQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSysRoleVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/partner/list": {
        "get": {
            "tags": [
                "合伙人管理"
            ],
                "summary": "分页查询合伙人列表",
                "description": "根据查询条件分页查询合伙人列表，支持按合伙人姓名、管理区域、状态、手机号、邮箱等条件筛选。需要权限：system:partner:list",
                "operationId": "listPartners",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/SysPartnerQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSysPartnerVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/statistics/transactionLog": {
        "get": {
            "tags": [
                "统计数据"
            ],
                "summary": "平台流水统计",
                "description": "获取平台整体流水统计数据",
                "operationId": "getTransactionLog",
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultTransactionLogVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/statistics/serviceLog/list": {
        "get": {
            "tags": [
                "统计数据"
            ],
                "summary": "服务人员流水日志列表",
                "description": "查询服务人员流水日志列表，针对每个服务人员只返回一行数据。支持按日期范围等条件筛选。需要权限：statistics:service-log:list",
                "operationId": "getServiceLogList",
                "parameters": [
                {
                    "name": "dto",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ServiceLogListDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListServiceLogListVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/statistics/serviceLog/detail/{serviceStaffId}": {
        "get": {
            "tags": [
                "统计数据"
            ],
                "summary": "服务人员流水日志详情",
                "description": "查询服务人员流水日志详情页，返回该服务人员的所有明细记录、汇总数据和月度图表数据。需要权限：statistics:service-log:detail",
                "operationId": "getServiceLogDetail",
                "parameters": [
                {
                    "name": "serviceStaffId",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "dto",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ServiceLogDetailDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultServiceLogDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/statistics/merchantLog/list": {
        "get": {
            "tags": [
                "统计数据"
            ],
                "summary": "商家流水日志列表",
                "description": "查询商家流水日志列表，针对每个商家只返回一行数据。支持按日期范围等条件筛选。需要权限：statistics:merchant-log:list",
                "operationId": "getMerchantLogList",
                "parameters": [
                {
                    "name": "dto",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/MerchantLogListDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListMerchantLogListVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/statistics/merchantLog/detail/{merchantId}": {
        "get": {
            "tags": [
                "统计数据"
            ],
                "summary": "商家流水日志详情",
                "description": "查询商家流水日志详情页，返回该商家的所有明细记录、汇总数据和月度图表数据。需要权限：statistics:merchant-log:detail",
                "operationId": "getMerchantLogDetail",
                "parameters": [
                {
                    "name": "merchantId",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "dto",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/MerchantLogDetailDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultMerchantLogDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/statistics/categorySales/list": {
        "get": {
            "tags": [
                "统计数据"
            ],
                "summary": "品类销售统计列表",
                "description": "查询品类销售统计列表，针对每个品类只返回一行数据。支持按日期范围等条件筛选。需要权限：statistics:category-log:list",
                "operationId": "getCategorySalesList",
                "parameters": [
                {
                    "name": "dto",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/CategorySalesListDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListCategorySalesListVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/statistics/categorySales/detail/{categoryId}": {
        "get": {
            "tags": [
                "统计数据"
            ],
                "summary": "品类销售统计详情",
                "description": "返回该品类的所有明细记录、汇总数据和月度图表数据",
                "operationId": "getCategorySalesDetail",
                "parameters": [
                {
                    "name": "categoryId",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                },
                {
                    "name": "dto",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/CategorySalesDetailDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultCategorySalesDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/service/commission-config/list": {
        "get": {
            "tags": [
                "服务分佣配置"
            ],
                "summary": "分页查询服务分佣配置列表",
                "description": "根据查询条件分页查询服务分佣配置列表，支持按分类ID、配置类型、分佣类型、状态等条件筛选。需要权限：system:serviceCommission:list",
                "operationId": "listServiceCommissionConfigs",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含分类ID、配置类型、分佣类型、状态等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ServiceCommissionConfigQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultServiceCommissionConfigVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/service-category/tree": {
        "get": {
            "tags": [
                "服务分类管理"
            ],
                "summary": "查询服务分类树",
                "description": "获取服务分类的树形结构，包含所有分类及其子分类的层级关系。需要权限：service:category:list",
                "operationId": "getServiceCategoryTree",
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListServiceCategoryTreeVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/school/list": {
        "get": {
            "tags": [
                "学校管理"
            ],
                "summary": "分页查询学校列表",
                "description": "根据查询条件分页查询学校列表，支持按学校名称、状态等条件筛选。需要权限：system:school:list",
                "operationId": "listSchools",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/SchoolQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSysSchoolVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/post/{id}": {
        "get": {
            "tags": [
                "帖子管理"
            ],
                "summary": "获取帖子详情及评论",
                "description": "根据帖子ID查询帖子详细信息，同时返回该帖子的所有评论列表。返回数据包含帖子详情和评论树形结构。需要权限：forum:post:detail",
                "operationId": "getPostDetailWithComments",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "帖子ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultForumPostCommentTreeVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "帖子管理"
            ],
                "summary": "删除帖子",
                "description": "删除指定的帖子（软删除）。同时会删除该帖子下的所有评论。需要权限：forum:post:delete",
                "operationId": "deletePost",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "帖子ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/post/list": {
        "get": {
            "tags": [
                "帖子管理"
            ],
                "summary": "分页查询帖子列表",
                "description": "根据查询条件分页查询帖子列表，支持按标题、内容、发布时间等条件筛选。返回包含用户名的分页结果。需要权限：forum:post:list",
                "operationId": "listPosts",
                "parameters": [
                {
                    "name": "queryDTO",
                    "in": "query",
                    "description": "查询条件，包含标题、内容、发布时间等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ForumPostQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultForumPostQueryVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/order/staff-order/{id}": {
        "get": {
            "tags": [
                "服务人员订单管理"
            ],
                "summary": "获取服务人员订单详情",
                "description": "根据订单ID查询服务人员订单详细信息，包括服务人员信息、用户信息、订单详情、支付信息等。需要权限：order:staff:detail",
                "operationId": "getStaffOrderDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "订单ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultStaffOrderDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "服务人员订单管理"
            ],
                "summary": "删除服务人员订单",
                "description": "删除指定的服务人员订单。需要权限：order:staff:delete",
                "operationId": "deleteStaffOrder",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "订单ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/order/staff-order/list": {
        "get": {
            "tags": [
                "服务人员订单管理"
            ],
                "summary": "分页查询服务人员订单列表",
                "description": "根据查询条件分页查询服务人员订单列表，支持按服务人员名称、订单编号、订单状态、订单类型、时间范围等条件筛选。需要权限：order:staff:list",
                "operationId": "listStaffOrders",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含服务人员名称、订单编号、订单状态、订单类型、时间范围等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/StaffOrderQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultStaffOrderListVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/order/merchant-order/{id}": {
        "get": {
            "tags": [
                "商家订单管理"
            ],
                "summary": "获取商家订单详情",
                "description": "根据订单ID查询商家订单详细信息，包括商家信息、用户信息、订单详情、支付信息等。需要权限：order:merchant:detail",
                "operationId": "getMerchantOrderDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultMerchantOrderDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        },
        "delete": {
            "tags": [
                "商家订单管理"
            ],
                "summary": "删除商家订单",
                "description": "删除指定的商家订单",
                "operationId": "deleteMerchantOrder",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/order/merchant-order/list": {
        "get": {
            "tags": [
                "商家订单管理"
            ],
                "summary": "分页查询商家订单列表",
                "description": "根据查询条件分页查询商家订单列表，支持按商家名称、订单编号、订单类别、订单状态等条件筛选。需要权限：order:merchant:list",
                "operationId": "listMerchantOrders",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/MerchantOrderQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultMerchantOrderListVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant/settle-in/list": {
        "get": {
            "tags": [
                "商家入驻管理"
            ],
                "summary": "分页查询商家入驻申请列表",
                "description": "根据查询条件分页查询商家入驻申请列表，支持按商家名称、审核状态、所属合伙人ID等条件筛选。返回包含合伙人、结算账户、审核记录等关联信息的分页结果。需要权限：audit:merchant:list",
                "operationId": "listMerchantSettleInApplications",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含商家名称、审核状态、所属合伙人ID等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/MerchantSettleInQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultMerchantSettleInVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/merchant/list": {
        "get": {
            "tags": [
                "商家管理"
            ],
                "summary": "分页查询商家列表",
                "description": "根据查询条件分页查询商家管理列表，支持按商家名称、审核状态、商家状态、所属合伙人ID等条件筛选。返回包含合伙人、结算账户等关联信息的分页结果。需要权限：merchant:list",
                "operationId": "listMerchants",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/SysMerchantQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSysMerchantVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/menu/tree": {
        "get": {
            "tags": [
                "菜单管理"
            ],
                "summary": "获取菜单树",
                "description": "查询所有系统菜单，返回树形结构的菜单列表。需要权限：system:menu:list",
                "operationId": "getMenuTree",
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListSysMenuVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch/product/list": {
        "get": {
            "tags": [
                "商品管理"
            ],
                "summary": "分页查询商品列表",
                "description": "根据查询条件分页查询商品列表，支持按关键字、分类ID、商家ID、上架状态、审核状态等条件筛选。返回包含商家收益动态计算、分类名称、商家名称、审核状态的商品列表。需要权限：mch:product:list",
                "operationId": "listProducts",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含关键字、分类ID、商家ID、上架状态、审核状态等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ProductQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultProductVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch/commission/list": {
        "get": {
            "tags": [
                "商家分佣调控"
            ],
                "summary": "分页查询商家分佣调控列表",
                "description": "根据查询条件分页查询商家分佣调控列表，支持按商家名称进行模糊搜索。返回包含商家分佣配置信息的列表。需要权限：merchant:commission:list",
                "operationId": "listMerchantCommissions",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含商家名称等筛选条件（支持模糊搜索）",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/MchCommissionConfigQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultMchCommissionConfigVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/mch-category/tree": {
        "get": {
            "tags": [
                "商家分类管理"
            ],
                "summary": "查询商家分类树",
                "description": "获取商家分类的树形结构，包含所有分类及其子分类的层级关系。需要权限：merchant:category:list",
                "operationId": "getMchCategoryTree",
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListMchCategoryTreeVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/dict/list": {
        "get": {
            "tags": [
                "字典管理"
            ],
                "summary": "分页查询字典列表",
                "description": "根据查询条件分页查询字典列表，支持按字典名称、编码、状态等条件筛选。需要权限：system:dict:list",
                "operationId": "listDicts",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含分页参数和筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/SysDictQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSysDictVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/dict/group/{id}": {
        "get": {
            "tags": [
                "字典管理"
            ],
                "summary": "查询字典树形结构",
                "description": "根据父ID查询字典及其子字典树形结构，返回包含父字典详情及其子字典的树形数据。需要权限：system:dict:query",
                "operationId": "getDictTree",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "父字典ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int32"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultListSysDictVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/withdrawal/{id}": {
        "get": {
            "tags": [
                "骑手提现审核"
            ],
                "summary": "获取骑手提现记录详情",
                "description": "根据提现记录ID查询骑手提现记录详细信息，返回包含骑手、商家、审核、打款等完整信息。需要权限：delivery:rider-withdrawal:detail",
                "operationId": "getRiderWithdrawalDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "提现记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultRiderWithdrawalDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/withdrawal/list": {
        "get": {
            "tags": [
                "骑手提现审核"
            ],
                "summary": "分页查询骑手提现记录列表",
                "description": "根据查询条件分页查询骑手提现记录列表，支持按骑手姓名、手机号、审核状态、打款状态、提现方式、时间范围等条件筛选。返回包含骑手信息、商家信息、余额、金额、审核和打款状态的分页结果。需要权限：delivery:rider-withdrawal:list",
                "operationId": "listRiderWithdrawals",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含骑手姓名、手机号、审核状态、打款状态、提现方式、时间范围等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/RiderWithdrawalQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultRiderWithdrawalVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/order/{id}": {
        "get": {
            "tags": [
                "骑手订单管理"
            ],
                "summary": "获取骑手订单详情",
                "description": "根据订单ID查询骑手订单详细信息，返回包含完整的骑手、商家、买家信息，配送费、骑手佣金、支付信息等。需要权限：delivery:rider-order:detail",
                "operationId": "getRiderOrderDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "订单ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultRiderOrderDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/order/list": {
        "get": {
            "tags": [
                "骑手订单管理"
            ],
                "summary": "分页查询骑手订单列表",
                "description": "根据查询条件分页查询骑手订单列表，支持按骑手姓名、订单号、订单状态、时间范围等条件筛选。返回包含骑手信息、商家信息、买家信息、配送费、骑手佣金、订单状态等的分页结果。订单类型：1=服务人员订单, 2=商家订单, 3=骑手订单；订单状态：0=已取消, 1=进行中, 2=已完成。需要权限：delivery:rider-order:list",
                "operationId": "listRiderOrders",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含骑手姓名、订单号、订单状态、时间范围等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/RiderOrderQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultRiderOrderVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/rider/list": {
        "get": {
            "tags": [
                "骑手管理"
            ],
                "summary": "分页查询骑手列表",
                "description": "根据查询条件分页查询骑手列表，支持按骑手姓名、手机号、所属商家、城市等条件筛选。返回包含商家名称、地址、余额、累计佣金等信息的列表。需要权限：delivery:rider:list",
                "operationId": "listRiders",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含骑手姓名、手机号、所属商家、城市等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/RiderQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultSysRiderVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/delivery/fee-config/list": {
        "get": {
            "tags": [
                "配送费设置"
            ],
                "summary": "分页查询配送费配置列表",
                "description": "根据查询条件分页查询配送费配置列表，支持按配置名称、状态等条件筛选。返回包含基础费用、基础距离、状态等信息的配置列表。需要权限：delivery:fee-config:list",
                "operationId": "listDeliveryFeeConfigs",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含配置名称、状态等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/FeeConfigQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultDeliveryFeeConfigVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/audit/staff/list": {
        "get": {
            "tags": [
                "服务人员审核"
            ],
                "summary": "分页查询服务人员审核列表",
                "description": "根据查询条件分页查询服务人员审核列表，支持按审核状态等条件筛选。需要权限：audit:staff:list",
                "operationId": "listServiceStaffAudits",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含审核状态等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ServiceStaffAuditQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultServiceStaffAuditVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/announcement/list": {
        "get": {
            "tags": [
                "公告管理"
            ],
                "summary": "公告列表（分页）",
                "description": "根据查询条件分页查询公告列表，支持按标题、发布时间等条件筛选。需要权限：forum:announcement:list",
                "operationId": "listAnnouncements",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含标题、发布时间等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ForumAnnouncementQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultForumAnnouncementVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/admin/rider/apply/list": {
        "get": {
            "tags": [
                "骑手申请审核"
            ],
                "summary": "分页查询骑手申请列表",
                "description": "根据查询条件分页查询骑手申请列表，支持按骑手姓名、手机号、审核状态、时间范围等条件筛选。返回包含骑手信息、审核状态、申请时间等的分页结果。审核状态：0-待审核，1-已通过，2-已拒绝。需要权限：admin:rider-apply:list",
                "operationId": "listRiderApply",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "description": "查询条件，包含骑手姓名、手机号、审核状态、时间范围等筛选条件",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/RiderApplyQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultRiderApplyVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/admin/rider/apply/detail/{id}": {
        "get": {
            "tags": [
                "骑手申请审核"
            ],
                "summary": "获取骑手申请详情",
                "description": "根据申请记录ID查询骑手申请详细信息，返回包含骑手详细信息、审核状态、审核人、审核时间等完整字段。需要权限：admin:rider-apply:detail",
                "operationId": "getRiderApplyDetail",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "申请记录ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1025
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultRiderApplyDetailVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/activity/list": {
        "get": {
            "tags": [
                "论坛活动管理"
            ],
                "summary": "分页查询活动列表",
                "description": "根据查询条件分页查询活动列表，供用户浏览所有公开活动，支持按条件筛选。需要权限：forum:activity:list",
                "operationId": "listActivities",
                "parameters": [
                {
                    "name": "query",
                    "in": "query",
                    "required": true,
                    "schema": {
                        "$ref": "#/components/schemas/ForumActivityQueryDTO"
                    }
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultPageResultForumActivityQueryVO"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/sys/user/batch": {
        "delete": {
            "tags": [
                "系统用户管理"
            ],
                "summary": "批量删除系统用户",
                "description": "批量删除系统用户（软删除）。批量软删除，设置deleted_at字段。需要权限：system:user:delete",
                "operationId": "batchDeleteSystemUsers",
                "requestBody": {
                "content": {
                    "application/json": {
                        "schema": {
                            "type": "array",
                                "items": {
                                "type": "integer",
                                    "format": "int64"
                            }
                        }
                    }
                },
                "required": true
            },
            "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    },
    "/api/comment/{id}": {
        "delete": {
            "tags": [
                "帖子评论管理"
            ],
                "summary": "删除帖子评论",
                "description": "删除指定的帖子评论。需要权限：forum:comment:delete",
                "operationId": "deletePostComment",
                "parameters": [
                {
                    "name": "id",
                    "in": "path",
                    "description": "评论ID",
                    "required": true,
                    "schema": {
                        "type": "integer",
                        "format": "int64"
                    },
                    "example": 1
                }
            ],
                "responses": {
                "409": {
                    "description": "数据冲突，可能是以下情况：\n资源已存在\n\n版本冲突 例如：数据已被其他用户修改\n\n当前状态不允许操作 例如：订单已完成，无法取消\n\n并发任务冲突 例如：已有任务已在运行，无法启动新任务\n\n业务规则冲突，例如：不能重复购买\n\n依赖关系冲突 例如：该分类下还有商品，无法删除\n",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 409,
                                    "message": "数据冲突",
                                    "result": null
                            }
                        }
                    }
                },
                "400": {
                    "description": "参数校验失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 400,
                                    "message": "参数校验失败: xxxxx",
                                    "result": null
                            }
                        }
                    }
                },
                "200": {
                    "description": "成功",
                        "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/ResultVoid"
                            }
                        }
                    }
                },
                "500": {
                    "description": "服务器错误",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 500,
                                    "message": "服务器错误",
                                    "result": null
                            }
                        }
                    }
                },
                "403": {
                    "description": "无权限访问",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 403,
                                    "message": "当前用户没有权限访问",
                                    "result": null
                            }
                        }
                    }
                },
                "401": {
                    "description": "验证失败",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 401,
                                    "message": "验证失败",
                                    "result": null
                            }
                        }
                    }
                },
                "404": {
                    "description": "数据不存在",
                        "content": {
                        "application/json": {
                            "example": {
                                "code": 404,
                                    "message": "数据不存在",
                                    "result": null
                            }
                        }
                    }
                }
            }
        }
    }
},
    "components": {
    "schemas": {
        "WithdrawalAuditDTO": {
            "type": "object",
                "properties": {
                "auditOpinion": {
                    "type": "string",
                        "description": "审核意见（审核拒绝时必填）",
                        "example": "审核拒绝"
                },
                "actualAmount": {
                    "type": "number",
                        "description": "实际到账金额（审核通过时可选，如果与申请金额不同，需要填写）",
                        "example": 100
                }
            },
            "description": "审核DTO，包含审核意见和实际到账金额"
        },
        "ResultVoid": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "object",
                        "description": "返回的结果",
                        "example": "数据内容"
                }
            },
            "description": "统一响应结果"
        },
        "TransactionStatusNotificationDTO": {
            "required": [
                "status",
                "transactionNo",
                "withdrawalId"
            ],
                "type": "object",
                "properties": {
                "withdrawalId": {
                    "type": "integer",
                        "description": "提现记录ID",
                        "format": "int64",
                        "example": 1
                },
                "transactionNo": {
                    "type": "string",
                        "description": "交易流水号",
                        "example": "WTH20250108123456789"
                },
                "status": {
                    "type": "string",
                        "description": "交易状态：ok-成功，fail-失败，cancel-取消",
                        "example": "ok"
                },
                "failReason": {
                    "type": "string",
                        "description": "失败原因（当status为fail时必填）",
                        "example": "账号异常"
                },
                "batchId": {
                    "type": "string",
                        "description": "微信转账批次单号",
                        "example": "1030000071100999991182020050700019480001"
                },
                "detailId": {
                    "type": "string",
                        "description": "微信转账明细单号",
                        "example": "1030000071100999991182020050700019480001_0"
                }
            },
            "description": "交易状态通知，包含提现记录ID、交易流水号、状态等信息"
        },
        "ResultTransactionStatusUpdateResponse": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/TransactionStatusUpdateResponse"
                }
            },
            "description": "统一响应结果"
        },
        "TransactionStatusUpdateResponse": {
            "type": "object",
                "properties": {
                "withdrawalId": {
                    "type": "integer",
                        "description": "提现记录ID",
                        "format": "int64",
                        "example": 1
                },
                "withdrawalNo": {
                    "type": "string",
                        "description": "提现单号",
                        "example": "WTH20250108123456789"
                },
                "transactionNo": {
                    "type": "string",
                        "description": "交易流水号",
                        "example": "WTH20250108123456789"
                },
                "payStatus": {
                    "type": "integer",
                        "description": "更新后的打款状态：0-未打款，1-已打款，2-打款失败",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "string",
                        "description": "更新状态",
                        "example": "SUCCESS"
                },
                "failReason": {
                    "type": "string",
                        "description": "失败原因",
                        "example": "账号异常"
                },
                "updateTime": {
                    "type": "string",
                        "description": "更新时间",
                        "example": "2025-01-08T12:00:00"
                }
            },
            "description": "交易状态更新响应DTO",
                "example": "数据内容"
        },
        "UserStatusDTO": {
            "required": [
                "status"
            ],
                "type": "object",
                "properties": {
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "目标状态：0-拉黑，1-解封",
                        "format": "int32",
                        "example": 0
                },
                "reason": {
                    "type": "string",
                        "description": "操作原因（可选但建议填写）",
                        "example": "违规操作"
                }
            },
            "description": "状态修改DTO，包含目标状态和操作原因"
        },
        "SettlementAccountUpdateDTO": {
            "type": "object",
                "properties": {
                "accountName": {
                    "type": "string",
                        "description": "账户名称",
                        "example": "平台结算账户"
                },
                "contactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "联系方式",
                        "example": "13800138000"
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡号",
                        "example": "6222021234567890123"
                },
                "paymentTime": {
                    "type": "string",
                        "description": "支付日期",
                        "format": "date-time"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "张三"
                }
            },
            "description": "修改平台账户信息，所有字段均可选"
        },
        "SysRoleUpdateDTO": {
            "type": "object",
                "properties": {
                "roleName": {
                    "type": "string",
                        "description": "系统角色名",
                        "example": "超级管理员"
                },
                "roleCode": {
                    "type": "string",
                        "description": "角色权限字符",
                        "example": "admin"
                },
                "perms": {
                    "uniqueItems": true,
                        "type": "array",
                        "description": "菜单权限集合",
                        "example": [
                        "system:user:list",
                        "system:user:add"
                    ],
                        "items": {
                        "type": "string",
                            "description": "菜单权限集合",
                            "example": "[\"system:user:list\",\"system:user:add\"]"
                    }
                },
                "sort": {
                    "type": "integer",
                        "description": "排序权重数",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "系统角色状态",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "系统角色更新DTO，所有字段均为可选，仅更新传入的字段"
        },
        "SysPartnerUpdateDTO": {
            "type": "object",
                "properties": {
                "partnerName": {
                    "type": "string",
                        "description": "合伙人姓名",
                        "example": "张三"
                },
                "email": {
                    "type": "string",
                        "description": "合伙人邮箱",
                        "example": "zhangsan@example.com"
                },
                "phone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "手机号",
                        "example": "13800138000"
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "张三"
                },
                "commissionRate": {
                    "maximum": 100,
                        "exclusiveMaximum": false,
                        "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "分佣比例(%)",
                        "example": 10
                },
                "parentId": {
                    "type": "integer",
                        "description": "上级合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                },
                "schoolIds": {
                    "type": "array",
                        "description": "关联的学校ID列表，可为空（表示不负责任何区域）",
                        "example": [1, 2, 3],
                        "items": {
                        "type": "integer",
                            "description": "关联的学校ID列表，可为空（表示不负责任何区域）",
                            "format": "int64"
                    }
                }
            },
            "description": "合伙人更新DTO"
        },
        "ServiceCommissionConfigUpdateDTO": {
            "required": [
                "commissionRate"
            ],
                "type": "object",
                "properties": {
                "commissionRate": {
                    "maximum": 100,
                        "minimum": 0,
                        "type": "integer",
                        "description": "分佣比例(%)，10代表10%",
                        "format": "int32",
                        "example": 10
                }
            },
            "description": "更新配置信息，所有字段均可选"
        },
        "ServiceCategoryUpdateDTO": {
            "required": [
                "allowOfflineTrade",
                "categoryName",
                "parentId",
                "sortOrder",
                "status"
            ],
                "type": "object",
                "properties": {
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "代取快递"
                },
                "parentId": {
                    "minimum": 0,
                        "type": "integer",
                        "description": "父级分类ID",
                        "format": "int64",
                        "example": 0
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序值",
                        "format": "int32",
                        "example": 1
                },
                "allowOfflineTrade": {
                    "type": "integer",
                        "description": "是否允许线下交易(1-允许 0-不允许)",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态(1-启用 0-禁用)",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "修改分类信息，包含分类名称、父分类ID、排序、状态等"
        },
        "SchoolPartialUpdateDTO": {
            "type": "object",
                "properties": {
                "orgName": {
                    "type": "string"
                },
                "province": {
                    "type": "string"
                },
                "city": {
                    "type": "string"
                },
                "district": {
                    "type": "string"
                },
                "address": {
                    "type": "string"
                },
                "contactPerson": {
                    "type": "string"
                },
                "contactPhone": {
                    "type": "string"
                },
                "partnerId": {
                    "type": "integer",
                        "format": "int64"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "SysMerchantUpdateDTO": {
            "type": "object",
                "properties": {
                "orgName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "email": {
                    "type": "string",
                        "description": "商家邮箱（用于登录）",
                        "example": "merchant@example.com"
                },
                "password": {
                    "type": "string",
                        "description": "商家密码（BCrypt加密），仅当需要修改密码时提供",
                        "example": "NewMerchant@123"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "minimumOrderAmount": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "起送金额/元（0表示无起送限制）",
                        "example": 20
                },
                "contactPerson": {
                    "type": "string",
                        "description": "联系人",
                        "example": "李四"
                },
                "contactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "联系电话",
                        "example": "13900139000"
                },
                "sortOrder": {
                    "minimum": 0,
                        "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "结算账户卡号",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "李四"
                },
                "partnerId": {
                    "type": "integer",
                        "description": "所属合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "商家更新DTO，用于商家管理界面编辑商家信息"
        },
        "MerchantSettleInUpdateDTO": {
            "type": "object",
                "properties": {
                "orgName": {
                    "type": "string"
                },
                "contactPerson": {
                    "type": "string"
                },
                "contactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string"
                },
                "province": {
                    "type": "string"
                },
                "city": {
                    "type": "string"
                },
                "district": {
                    "type": "string"
                },
                "address": {
                    "type": "string"
                },
                "partnerId": {
                    "type": "integer",
                        "format": "int64"
                },
                "bankAccountNumber": {
                    "type": "string"
                },
                "bankAccountName": {
                    "type": "string"
                }
            },
            "description": "编辑商家入驻申请DTO，所有字段均可选"
        },
        "SysMenuUpdateDTO": {
            "required": [
                "menuName",
                "menuType",
                "parentId"
            ],
                "type": "object",
                "properties": {
                "parentId": {
                    "type": "integer",
                        "description": "父菜单ID(0为根菜单)",
                        "format": "int64",
                        "example": 0
                },
                "menuType": {
                    "type": "integer",
                        "description": "类型:0-目录,1-菜单,2-按钮",
                        "format": "int32",
                        "example": 1
                },
                "menuIcon": {
                    "type": "string",
                        "description": "图标",
                        "example": "el-icon-menu"
                },
                "menuName": {
                    "type": "string",
                        "description": "菜单名称",
                        "example": "用户管理"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "isFrame": {
                    "type": "integer",
                        "description": "0:是外链 1:站内路由",
                        "format": "int32",
                        "example": 1
                },
                "perms": {
                    "type": "string",
                        "description": "权限",
                        "example": "system:user:list"
                },
                "component": {
                    "type": "string",
                        "description": "组件路径",
                        "example": "/system/user/index"
                },
                "path": {
                    "type": "string",
                        "description": "访问路径",
                        "example": "/system/user"
                },
                "status": {
                    "type": "integer",
                        "description": "状态:0-隐藏,1-显示",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "菜单更新DTO"
        },
        "ProductShelfStatusDTO": {
            "required": [
                "shelfStatus"
            ],
                "type": "object",
                "properties": {
                "shelfStatus": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "format": "int32"
                }
            },
            "description": "上下架状态DTO（0-下架，1-上架）"
        },
        "MchCommissionUpdateDTO": {
            "required": [
                "commissionRate"
            ],
                "type": "object",
                "properties": {
                "commissionRate": {
                    "maximum": 100,
                        "minimum": 0,
                        "type": "integer",
                        "description": "佣金比例(%)",
                        "format": "int32",
                        "example": 10
                }
            },
            "description": "新的分佣配置信息"
        },
        "MchCategoryUpdateDTO": {
            "required": [
                "categoryName",
                "parentId",
                "sortOrder",
                "status"
            ],
                "type": "object",
                "properties": {
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "餐饮"
                },
                "parentId": {
                    "minimum": 0,
                        "type": "integer",
                        "description": "父级分类ID",
                        "format": "int64",
                        "example": 0
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序值",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "商家分类更新DTO"
        },
        "SysDictPartialUpdateDTO": {
            "type": "object",
                "properties": {
                "dictGroup": {
                    "type": "string",
                        "description": "分组名称",
                        "example": "order_status"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父ID",
                        "format": "int32",
                        "example": 0
                },
                "remark": {
                    "type": "string",
                        "description": "备注",
                        "example": "订单状态字典"
                },
                "dictKey": {
                    "type": "string",
                        "description": "字典键",
                        "example": "pending"
                },
                "dictValue": {
                    "type": "string",
                        "description": "字典值",
                        "example": "待处理"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:0-禁用,1-启用",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "字典信息，包含需要更新的字段"
        },
        "RiderUpdateDTO": {
            "type": "object",
                "properties": {
                "username": {
                    "maxLength": 50,
                        "minLength": 2,
                        "type": "string",
                        "description": "骑手用户名",
                        "example": "rider001"
                },
                "password": {
                    "maxLength": 50,
                        "minLength": 6,
                        "type": "string",
                        "description": "骑手密码",
                        "example": "NewRider@123"
                },
                "phone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "骑手手机号码",
                        "example": "13800138000"
                },
                "emergencyContactName": {
                    "type": "string",
                        "description": "紧急联系人姓名",
                        "example": "张三"
                },
                "emergencyContactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "紧急联系人电话",
                        "example": "13900139000"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "gender": {
                    "maxLength": 10,
                        "minLength": 0,
                        "type": "string",
                        "description": "性别",
                        "example": "男"
                }
            },
            "description": "编辑骑手信息，所有字段均可选"
        },
        "ResetPasswordDTO": {
            "required": [
                "newPassword"
            ],
                "type": "object",
                "properties": {
                "newPassword": {
                    "maxLength": 20,
                        "minLength": 6,
                        "type": "string",
                        "description": "新密码",
                        "example": "NewPassword@123"
                }
            },
            "description": "重置密码信息，包含新密码（6-20字符）"
        },
        "RiderWithdrawalRejectDTO": {
            "required": [
                "auditOpinion"
            ],
                "type": "object",
                "properties": {
                "auditorId": {
                    "type": "integer",
                        "format": "int64"
                },
                "auditorName": {
                    "type": "string"
                },
                "auditOpinion": {
                    "type": "string"
                }
            },
            "description": "审核拒绝DTO，必填：拒绝理由"
        },
        "RiderWithdrawalApproveDTO": {
            "type": "object",
                "properties": {
                "auditorId": {
                    "type": "integer",
                        "description": "审核人ID（从登录用户获取）",
                        "format": "int64",
                        "example": 1
                },
                "auditorName": {
                    "type": "string",
                        "description": "审核人姓名（从登录用户获取）",
                        "example": "张三"
                },
                "auditOpinion": {
                    "type": "string",
                        "description": "审核意见（可选）",
                        "example": "审核通过"
                },
                "actualAmount": {
                    "type": "number",
                        "description": "实际到账金额（可选，默认等于申请金额）",
                        "example": 100
                }
            },
            "description": "审核通过DTO，可选：实际到账金额（默认为申请金额）、审核意见"
        },
        "FeeConfigUpdateDTO": {
            "required": [
                "baseDistance",
                "baseFee",
                "configName",
                "rules"
            ],
                "type": "object",
                "properties": {
                "configName": {
                    "maxLength": 50,
                        "minLength": 0,
                        "type": "string",
                        "description": "配置名称",
                        "example": "校园配送费配置"
                },
                "baseFee": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "起步价（元）",
                        "example": 5
                },
                "baseDistance": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "起步距离（公里）",
                        "example": 3
                },
                "rules": {
                    "maxItems": 2147483647,
                        "minItems": 1,
                        "type": "array",
                        "description": "规则明细列表",
                        "example": [
                        {
                            "distanceRange": "3-5",
                            "fee": "2.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/FeeRuleDTO"
                    }
                }
            },
            "description": "编辑配送费配置信息，所有字段均可选"
        },
        "FeeRuleDTO": {
            "required": [
                "ruleType"
            ],
                "type": "object",
                "properties": {
                "ruleType": {
                    "pattern": "^(distance|time)$",
                        "type": "string",
                        "description": "规则类型：distance-距离规则，time-时间规则",
                        "example": "distance"
                },
                "distanceStart": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "起始距离（公里），距离规则时必填",
                        "example": 3
                },
                "distanceEnd": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "结束距离（公里），距离规则时必填",
                        "example": 5
                },
                "pricePerKm": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "每公里价格（元），距离规则时必填",
                        "example": 2
                },
                "timeStart": {
                    "pattern": "^([01]\\d|2[0-3]):[0-5]\\d$",
                        "type": "string",
                        "description": "开始时间（格式：HH:mm），时间规则时必填",
                        "example": "08:00"
                },
                "timeEnd": {
                    "pattern": "^([01]\\d|2[0-3]):[0-5]\\d$",
                        "type": "string",
                        "description": "结束时间（格式：HH:mm），时间规则时必填",
                        "example": "18:00"
                },
                "timeFee": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "时段附加费（元），时间规则时必填",
                        "example": 5
                },
                "timeType": {
                    "pattern": "^(daytime|night)$",
                        "type": "string",
                        "description": "时段类型：daytime-白天，night-夜间",
                        "example": "daytime"
                },
                "sortOrder": {
                    "minimum": 0,
                        "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "配送费规则DTO",
                "example": [
                {
                    "distanceRange": "3-5",
                    "fee": "2.00"
                }
            ]
        },
        "FeeConfigStatusDTO": {
            "required": [
                "status"
            ],
                "type": "object",
                "properties": {
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "状态DTO（status: 0=禁用, 1=启用）"
        },
        "RiderApplyRejectDTO": {
            "required": [
                "auditOpinion",
                "auditorId",
                "auditorName"
            ],
                "type": "object",
                "properties": {
                "auditorId": {
                    "type": "integer",
                        "description": "审核人ID",
                        "format": "int64",
                        "example": 1
                },
                "auditorName": {
                    "maxLength": 20,
                        "minLength": 0,
                        "type": "string",
                        "description": "审核人姓名",
                        "example": "admin"
                },
                "auditOpinion": {
                    "maxLength": 500,
                        "minLength": 0,
                        "type": "string",
                        "description": "审核意见",
                        "example": "资料不全，审核不通过"
                }
            },
            "description": "审核拒绝DTO，必填拒绝理由"
        },
        "RiderApplyApproveDTO": {
            "required": [
                "auditorId",
                "auditorName"
            ],
                "type": "object",
                "properties": {
                "auditorId": {
                    "type": "integer",
                        "description": "审核人ID",
                        "format": "int64",
                        "example": 1
                },
                "auditorName": {
                    "maxLength": 20,
                        "minLength": 0,
                        "type": "string",
                        "description": "审核人姓名",
                        "example": "admin"
                },
                "auditOpinion": {
                    "maxLength": 500,
                        "minLength": 0,
                        "type": "string",
                        "description": "审核意见",
                        "example": "资料齐全，审核通过"
                }
            },
            "description": "审核通过DTO，可选审核意见"
        },
        "UserTransferRequest": {
            "required": [
                "amount",
                "userId",
                "withdrawalId"
            ],
                "type": "object",
                "properties": {
                "userId": {
                    "type": "integer",
                        "description": "用户ID",
                        "format": "int64",
                        "example": 1
                },
                "withdrawalId": {
                    "type": "integer",
                        "description": "提现记录id",
                        "format": "int64",
                        "example": 1
                },
                "amount": {
                    "minimum": 0.01,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "转账金额（元）",
                        "example": 100
                },
                "remark": {
                    "maxLength": 200,
                        "minLength": 0,
                        "type": "string",
                        "description": "转账备注",
                        "example": "佣金提现"
                }
            },
            "description": "转账请求，包含用户ID、转账金额、提现方式等信息"
        },
        "ResultUserTransferResponse": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/UserTransferResponse"
                }
            },
            "description": "统一响应结果"
        },
        "UserTransferResponse": {
            "type": "object",
                "properties": {
                "withdrawalId": {
                    "type": "integer",
                        "description": "提现ID",
                        "format": "int64",
                        "example": 1
                },
                "withdrawalNo": {
                    "type": "string",
                        "description": "提现单号",
                        "example": "WTH20250108123456789"
                },
                "userId": {
                    "type": "integer",
                        "description": "用户ID",
                        "format": "int64",
                        "example": 1
                },
                "amount": {
                    "type": "number",
                        "description": "转账金额（元）",
                        "example": 100
                },
                "actualAmount": {
                    "type": "number",
                        "description": "实际到账金额（元）",
                        "example": 99.5
                },
                "withdrawType": {
                    "type": "integer",
                        "description": "提现方式：1-微信，2-支付宝，3-银行卡",
                        "format": "int32",
                        "example": 1
                },
                "withdrawAccount": {
                    "type": "string",
                        "description": "提现账号",
                        "example": "wx1234567890"
                },
                "withdrawName": {
                    "type": "string",
                        "description": "提现人姓名",
                        "example": "张三"
                },
                "payStatus": {
                    "type": "integer",
                        "description": "打款状态：0-未打款，1-已打款，2-打款失败",
                        "format": "int32",
                        "example": 0
                },
                "transferTime": {
                    "type": "string",
                        "description": "转账时间",
                        "format": "date-time"
                },
                "batchId": {
                    "type": "string",
                        "description": "微信转账批次单号",
                        "example": "1030000071100999991182020050700019480001"
                },
                "detailId": {
                    "type": "string",
                        "description": "微信转账明细单号",
                        "example": "1030000071100999991182020050700019480001_0"
                },
                "status": {
                    "type": "string",
                        "description": "转账状态",
                        "example": "PROCESSING"
                },
                "failReason": {
                    "type": "string",
                        "description": "转账失败原因",
                        "example": "账号异常"
                },
                "packageInfo": {
                    "type": "string",
                        "description": "跳转领取页面的package信息",
                        "example": "affffddafdfafddffda=="
                }
            },
            "description": "用户转账响应DTO",
                "example": "数据内容"
        },
        "SettlementAccountAddDTO": {
            "required": [
                "accountName",
                "bankAccountName",
                "bankAccountNumber",
                "contactPhone"
            ],
                "type": "object",
                "properties": {
                "accountName": {
                    "type": "string",
                        "description": "账户名称",
                        "example": "平台结算账户"
                },
                "contactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "联系方式",
                        "example": "13800138000"
                },
                "contactEmail": {
                    "type": "string",
                        "description": "联系邮箱，可选",
                        "example": "admin@example.com"
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡号",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "张三"
                },
                "paymentTime": {
                    "type": "string",
                        "description": "支付日期",
                        "format": "date-time"
                }
            },
            "description": "新增平台账户信息，必填：账户名称、联系方式、银行卡号、开户名"
        },
        "PaymentHistoryAddDTO": {
            "required": [
                "paymentAccount",
                "paymentAmount",
                "targetType"
            ],
                "type": "object",
                "properties": {
                "paymentAccount": {
                    "type": "integer",
                        "format": "int64"
                },
                "targetType": {
                    "type": "string"
                },
                "paymentAmount": {
                    "minimum": 0.01,
                        "exclusiveMinimum": false,
                        "type": "number"
                }
            },
            "description": "打款记录信息，包含收款账户、用户类型、打款金额等"
        },
        "SysRoleAddDTO": {
            "required": [
                "perms",
                "roleCode",
                "roleName",
                "sort",
                "status"
            ],
                "type": "object",
                "properties": {
                "roleName": {
                    "type": "string",
                        "description": "系统角色名",
                        "example": "超级管理员"
                },
                "roleCode": {
                    "type": "string",
                        "description": "角色权限字符",
                        "example": "admin"
                },
                "perms": {
                    "uniqueItems": true,
                        "type": "array",
                        "description": "菜单权限集合",
                        "example": [
                        "system:user:list",
                        "system:user:add"
                    ],
                        "items": {
                        "type": "string",
                            "description": "菜单权限集合",
                            "example": "[\"system:user:list\",\"system:user:add\"]"
                    }
                },
                "sort": {
                    "type": "integer",
                        "description": "排序权重数",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "系统角色状态",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "系统角色新增DTO"
        },
        "SysPartnerAddDTO": {
            "required": [
                "bankAccountNumber",
                "partnerName",
                "phone"
            ],
                "type": "object",
                "properties": {
                "partnerName": {
                    "type": "string",
                        "description": "合伙人姓名",
                        "example": "张三"
                },
                "email": {
                    "type": "string",
                        "description": "合伙人邮箱",
                        "example": "zhangsan@example.com"
                },
                "phone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "手机号",
                        "example": "13800138000"
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "张三"
                },
                "commissionRate": {
                    "maximum": 100,
                        "exclusiveMaximum": false,
                        "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "分佣比例(%)",
                        "example": 10
                },
                "parentId": {
                    "type": "integer",
                        "description": "上级合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                },
                "schoolIds": {
                    "type": "array",
                        "description": "关联的学校ID列表，可为空（表示不负责任何区域）",
                        "example": [1, 2, 3],
                        "items": {
                        "type": "integer",
                            "description": "关联的学校ID列表，可为空（表示不负责任何区域）",
                            "format": "int64"
                    }
                }
            },
            "description": "合伙人新增DTO"
        },
        "ServiceCommissionConfigAddDTO": {
            "required": [
                "categoryId",
                "commissionRate"
            ],
                "type": "object",
                "properties": {
                "categoryId": {
                    "type": "integer",
                        "description": "服务分类ID（必须是已存在的分类）",
                        "format": "int64",
                        "example": 1
                },
                "commissionRate": {
                    "maximum": 100,
                        "minimum": 0,
                        "type": "integer",
                        "description": "分佣比例(%)，10代表10%",
                        "format": "int32",
                        "example": 10
                },
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "状态：0-禁用，1-启用，默认为1",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "新增配置信息，必填：配置类型、分佣比例、分佣类型、状态；可选：分类ID"
        },
        "ServiceCategoryAddDTO": {
            "required": [
                "allowOfflineTrade",
                "categoryName",
                "parentId",
                "sortOrder",
                "status"
            ],
                "type": "object",
                "properties": {
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "代取快递"
                },
                "parentId": {
                    "minimum": 0,
                        "type": "integer",
                        "description": "父级分类ID",
                        "format": "int64",
                        "example": 0
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序值",
                        "format": "int32",
                        "example": 1
                },
                "allowOfflineTrade": {
                    "type": "integer",
                        "description": "是否允许线下交易(1-允许 0-不允许)",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态(1-启用 0-禁用)",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "新增分类信息，包含分类名称、父分类ID等"
        },
        "SchoolAddDTO": {
            "required": [
                "address",
                "city",
                "contactPerson",
                "contactPhone",
                "district",
                "orgName",
                "province",
                "status"
            ],
                "type": "object",
                "properties": {
                "orgName": {
                    "type": "string"
                },
                "province": {
                    "type": "string"
                },
                "city": {
                    "type": "string"
                },
                "district": {
                    "type": "string"
                },
                "address": {
                    "type": "string"
                },
                "contactPerson": {
                    "type": "string"
                },
                "contactPhone": {
                    "type": "string"
                },
                "partnerId": {
                    "type": "integer",
                        "format": "int64"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "SysMerchantAddDTO": {
            "required": [
                "bankAccountName",
                "bankAccountNumber",
                "city",
                "contactPhone",
                "district",
                "minimumOrderAmount",
                "orgName",
                "partnerId",
                "province"
            ],
                "type": "object",
                "properties": {
                "orgName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "email": {
                    "type": "string",
                        "description": "商家邮箱（用于登录）",
                        "example": "merchant@example.com"
                },
                "password": {
                    "type": "string",
                        "description": "商家密码（BCrypt加密），如不提供，默认使用手机号后6位",
                        "example": "Merchant@123"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "minimumOrderAmount": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "起送金额/元（0表示无起送限制）",
                        "example": 20
                },
                "contactPerson": {
                    "type": "string",
                        "description": "联系人",
                        "example": "李四"
                },
                "contactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "联系电话",
                        "example": "13900139000"
                },
                "sortOrder": {
                    "minimum": 0,
                        "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "结算账户卡号",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "李四"
                },
                "partnerId": {
                    "type": "integer",
                        "description": "所属合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "商家新增DTO，用于商家管理界面直接创建商家"
        },
        "MerchantSettleInAddDTO": {
            "required": [
                "bankAccountName",
                "bankAccountNumber",
                "city",
                "contactPhone",
                "district",
                "orgName",
                "partnerId",
                "province"
            ],
                "type": "object",
                "properties": {
                "orgName": {
                    "type": "string"
                },
                "contactPerson": {
                    "type": "string"
                },
                "contactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string"
                },
                "province": {
                    "type": "string"
                },
                "city": {
                    "type": "string"
                },
                "district": {
                    "type": "string"
                },
                "address": {
                    "type": "string"
                },
                "partnerId": {
                    "type": "integer",
                        "format": "int64"
                },
                "bankAccountNumber": {
                    "type": "string"
                },
                "bankAccountName": {
                    "type": "string"
                }
            },
            "description": "新增商家入驻申请DTO，必填：商家名称、联系方式、所属合伙人ID、结算卡号、开户名"
        },
        "MerchantAuditDTO": {
            "required": [
                "auditStatus"
            ],
                "type": "object",
                "properties": {
                "auditStatus": {
                    "maximum": 2,
                        "minimum": 1,
                        "type": "integer",
                        "description": "审核结果（1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                },
                "auditOpinion": {
                    "maxLength": 500,
                        "minLength": 0,
                        "type": "string",
                        "description": "审核意见（可选，建议长度限制在500字符内）",
                        "example": "商家资质齐全，审核通过"
                }
            },
            "description": "审核数据DTO，必填字段：审核结果（1-通过，2-拒绝），可选字段：审核意见（最多500字符）"
        },
        "SysMenuAddDTO": {
            "required": [
                "menuName",
                "menuType",
                "parentId"
            ],
                "type": "object",
                "properties": {
                "parentId": {
                    "type": "integer",
                        "description": "父菜单ID(0为根菜单）",
                        "format": "int64",
                        "example": 0
                },
                "menuType": {
                    "type": "integer",
                        "description": "类型:0-目录,1-菜单,2-按钮",
                        "format": "int32",
                        "example": 1
                },
                "menuIcon": {
                    "type": "string",
                        "description": "图标",
                        "example": "el-icon-menu"
                },
                "menuName": {
                    "type": "string",
                        "description": "菜单名称",
                        "example": "用户管理"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "isFrame": {
                    "type": "integer",
                        "description": "0:是外链 1:站内路由",
                        "format": "int32",
                        "example": 1
                },
                "perms": {
                    "type": "string",
                        "description": "权限",
                        "example": "system:user:list"
                },
                "component": {
                    "type": "string",
                        "description": "组件路径",
                        "example": "/system/user/index"
                },
                "path": {
                    "type": "string",
                        "description": "访问路径",
                        "example": "/system/user"
                },
                "status": {
                    "type": "integer",
                        "description": "状态:0-隐藏,1-显示",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "菜单新增DTO"
        },
        "ProductAuditDTO": {
            "required": [
                "auditStatus"
            ],
                "type": "object",
                "properties": {
                "auditStatus": {
                    "maximum": 2,
                        "minimum": 1,
                        "type": "integer",
                        "description": "审核状态（1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                },
                "auditOpinion": {
                    "maxLength": 500,
                        "minLength": 0,
                        "type": "string",
                        "description": "审核意见",
                        "example": "商品信息完整，审核通过"
                }
            },
            "description": "审核数据DTO，必填字段：审核结果（1-通过，2-拒绝），可选字段：审核意见（最多500字符）"
        },
        "MchCategoryAddDTO": {
            "required": [
                "categoryName",
                "parentId",
                "sortOrder",
                "status"
            ],
                "type": "object",
                "properties": {
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "餐饮"
                },
                "parentId": {
                    "minimum": 0,
                        "type": "integer",
                        "description": "父级分类ID",
                        "format": "int64",
                        "example": 0
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序值",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "商家分类新增DTO"
        },
        "SysDictAddDTO": {
            "required": [
                "dictGroup",
                "dictKey",
                "dictValue",
                "status"
            ],
                "type": "object",
                "properties": {
                "dictGroup": {
                    "type": "string",
                        "description": "分组名称",
                        "example": "order_status"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父ID",
                        "format": "int32",
                        "example": 0
                },
                "remark": {
                    "type": "string",
                        "description": "备注",
                        "example": "订单状态字典"
                },
                "dictKey": {
                    "type": "string",
                        "description": "字典键",
                        "example": "pending"
                },
                "dictValue": {
                    "type": "string",
                        "description": "字典值",
                        "example": "待处理"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:0-禁用,1-启用",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "字典信息，包含字典名称、编码、值、排序、状态等"
        },
        "RiderAddDTO": {
            "required": [
                "city",
                "gender",
                "merchantId",
                "password",
                "phone",
                "province",
                "username"
            ],
                "type": "object",
                "properties": {
                "merchantId": {
                    "type": "integer",
                        "description": "所属商家ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "maxLength": 50,
                        "minLength": 2,
                        "type": "string",
                        "description": "骑手用户名",
                        "example": "rider001"
                },
                "password": {
                    "maxLength": 20,
                        "minLength": 6,
                        "type": "string",
                        "description": "骑手密码",
                        "example": "Rider@123"
                },
                "phone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "骑手手机号码",
                        "example": "13800138000"
                },
                "emergencyContactName": {
                    "type": "string",
                        "description": "紧急联系人姓名",
                        "example": "张三"
                },
                "emergencyContactPhone": {
                    "pattern": "^1[3-9]\\d{9}$",
                        "type": "string",
                        "description": "紧急联系人电话",
                        "example": "13900139000"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "gender": {
                    "type": "string",
                        "description": "性别",
                        "example": "男"
                }
            },
            "description": "新增骑手信息，包含所属商家、姓名、手机号、密码等必填字段"
        },
        "FeeConfigAddDTO": {
            "required": [
                "baseDistance",
                "baseFee",
                "configName",
                "rules"
            ],
                "type": "object",
                "properties": {
                "configName": {
                    "maxLength": 50,
                        "minLength": 0,
                        "type": "string",
                        "description": "配置名称",
                        "example": "校园配送费配置"
                },
                "baseFee": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "起步价（元）",
                        "example": 5
                },
                "baseDistance": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number",
                        "description": "起步距离（公里）",
                        "example": 3
                },
                "rules": {
                    "maxItems": 2147483647,
                        "minItems": 1,
                        "type": "array",
                        "description": "规则明细列表",
                        "example": [
                        {
                            "distanceRange": "3-5",
                            "fee": "2.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/FeeRuleDTO"
                    }
                }
            },
            "description": "新增配送费配置信息，必填：配置名称、基础费用、基础距离；可选：计费规则列表（支持距离规则和时段规则）"
        },
        "RefreshRequest": {
            "type": "object",
                "properties": {
                "refreshToken": {
                    "type": "string",
                        "description": "刷新令牌（Cookie模式下不需要）",
                        "example": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                }
            },
            "description": "刷新令牌请求"
        },
        "AuthResponse": {
            "type": "object",
                "properties": {
                "accessToken": {
                    "type": "string",
                        "description": "AccessToken（访问令牌）",
                        "example": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                },
                "refreshToken": {
                    "type": "string",
                        "description": "RefreshToken（刷新令牌）- Cookie模式下为null",
                        "example": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                },
                "accessTokenExpire": {
                    "type": "integer",
                        "description": "AccessToken有效期（毫秒）",
                        "format": "int64",
                        "example": 7200000
                },
                "refreshTokenExpire": {
                    "type": "integer",
                        "description": "RefreshToken有效期（毫秒）- Cookie模式下为null",
                        "format": "int64",
                        "example": 604800000
                },
                "menus": {
                    "type": "array",
                        "description": "用户菜单列表（树形结构）",
                        "example": [
                        {
                            "id": 1,
                            "menuName": "用户管理",
                            "children": []
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/SysMenuVO"
                    }
                },
                "username": {
                    "type": "string",
                        "description": "用户名",
                        "example": "admin"
                },
                "avatar": {
                    "type": "string",
                        "description": "用户头像URL",
                        "example": "http://example.com/avatar.jpg"
                }
            },
            "description": "认证响应",
                "example": "数据内容"
        },
        "ResultAuthResponse": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/AuthResponse"
                }
            },
            "description": "统一响应结果"
        },
        "SysMenuVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "菜单ID",
                        "format": "int64",
                        "example": 1
                },
                "parentId": {
                    "type": "integer",
                        "description": "父菜单ID(0为根菜单）",
                        "format": "int64",
                        "example": 0
                },
                "menuType": {
                    "type": "integer",
                        "description": "类型:0-目录,1-菜单,2-按钮",
                        "format": "int32",
                        "example": 1
                },
                "menuIcon": {
                    "type": "string",
                        "description": "图标",
                        "example": "el-icon-menu"
                },
                "menuName": {
                    "type": "string",
                        "description": "菜单名称",
                        "example": "用户管理"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "isFrame": {
                    "type": "integer",
                        "description": "0:是外链 1:站内路由",
                        "format": "int32",
                        "example": 1
                },
                "perms": {
                    "type": "string",
                        "description": "权限",
                        "example": "system:user:list"
                },
                "component": {
                    "type": "string",
                        "description": "组件路径",
                        "example": "/system/user/index"
                },
                "path": {
                    "type": "string",
                        "description": "访问路径",
                        "example": "/system/user"
                },
                "status": {
                    "type": "integer",
                        "description": "状态:0-隐藏,1-显示",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "children": {
                    "type": "array",
                        "description": "子菜单列表",
                        "example": [
                        {
                            "id": 2,
                            "menuName": "用户列表",
                            "children": []
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/SysMenuVO"
                    }
                }
            },
            "description": "菜单VO",
                "example": [
                {
                    "id": 1,
                    "menuName": "用户管理",
                    "children": []
                }
            ]
        },
        "ResultString": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "string",
                        "description": "返回的结果",
                        "example": "数据内容"
                }
            },
            "description": "统一响应结果"
        },
        "LoginRequest": {
            "required": [
                "identifier",
                "password"
            ],
                "type": "object",
                "properties": {
                "identifier": {
                    "type": "string",
                        "description": "用户名或邮箱或手机号",
                        "example": "admin"
                },
                "password": {
                    "type": "string",
                        "description": "密码",
                        "example": "password123"
                }
            },
            "description": "用户登录请求"
        },
        "ForumActivityAuditDTO": {
            "type": "object",
                "properties": {
                "auditOpinion": {
                    "type": "string",
                        "description": "审核意见",
                        "example": "活动内容合规，审核通过"
                }
            },
            "description": "论坛活动审核DTO"
        },
        "WithdrawalQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "username": {
                    "type": "string"
                },
                "phone": {
                    "type": "string"
                },
                "auditStatus": {
                    "type": "integer",
                        "format": "int32"
                },
                "withdrawType": {
                    "type": "integer",
                        "format": "int32"
                },
                "startTime": {
                    "type": "string",
                        "format": "date-time"
                },
                "endTime": {
                    "type": "string",
                        "format": "date-time"
                }
            }
        },
        "PageResultWithdrawalListVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/WithdrawalListVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultWithdrawalListVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultWithdrawalListVO"
                }
            },
            "description": "统一响应结果"
        },
        "WithdrawalListVO": {
            "type": "object",
                "properties": {
                "withdrawalId": {
                    "type": "integer",
                        "description": "提现记录ID",
                        "format": "int64",
                        "example": 1
                },
                "withdrawalNo": {
                    "type": "string",
                        "description": "提现单号",
                        "example": "TX20240101001"
                },
                "username": {
                    "type": "string",
                        "description": "用户名称",
                        "example": "张三"
                },
                "phone": {
                    "type": "string",
                        "description": "联系方式（已脱敏）",
                        "example": "138****8000"
                },
                "balance": {
                    "type": "number",
                        "description": "用户当前余额/元",
                        "example": 500
                },
                "amount": {
                    "type": "number",
                        "description": "申请提现金额/元",
                        "example": 100
                },
                "actualAmount": {
                    "type": "number",
                        "description": "实际到账金额/元（可能为空）",
                        "example": 100
                },
                "schoolName": {
                    "type": "string",
                        "description": "所在社区/学校名称",
                        "example": "清华大学"
                },
                "withdrawType": {
                    "type": "integer",
                        "description": "提现平台：1-微信，2-支付宝，3-银行卡",
                        "format": "int32",
                        "example": 1
                },
                "withdrawTypeText": {
                    "type": "string",
                        "description": "提现平台文本",
                        "example": "微信"
                },
                "withdrawAccount": {
                    "type": "string",
                        "description": "提现账号",
                        "example": "wx123456"
                },
                "withdrawName": {
                    "type": "string",
                        "description": "提现人姓名",
                        "example": "张三"
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态：0-待审核，1-审核通过，2-审核拒绝",
                        "format": "int32",
                        "example": 1
                },
                "auditStatusText": {
                    "type": "string",
                        "description": "审核状态文本",
                        "example": "审核通过"
                },
                "createdAt": {
                    "type": "string",
                        "description": "申请时间",
                        "format": "date-time"
                }
            },
            "description": "提现记录列表VO"
        },
        "UserQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "username": {
                    "type": "string"
                },
                "phone": {
                    "type": "string"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                },
                "schoolId": {
                    "type": "integer",
                        "format": "int64"
                },
                "roleId": {
                    "type": "integer",
                        "format": "int64"
                },
                "roleName": {
                    "type": "string"
                },
                "startTime": {
                    "type": "string",
                        "format": "date-time"
                },
                "endTime": {
                    "type": "string",
                        "format": "date-time"
                }
            }
        },
        "PageResultUserListVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/UserListVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultUserListVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultUserListVO"
                }
            },
            "description": "统一响应结果"
        },
        "UserListVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "用户ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "type": "string",
                        "description": "用户名",
                        "example": "user001"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号（已脱敏）",
                        "example": "138****8000"
                },
                "code": {
                    "type": "string",
                        "description": "学号",
                        "example": "20240001"
                },
                "avatar": {
                    "type": "string",
                        "description": "头像URL",
                        "example": "http://example.com/avatar.jpg"
                },
                "gender": {
                    "type": "string",
                        "description": "性别",
                        "example": "男"
                },
                "schoolName": {
                    "type": "string",
                        "description": "所属学校名称",
                        "example": "清华大学"
                },
                "balance": {
                    "type": "number",
                        "description": "账户余额/元",
                        "example": 500
                },
                "commissionTotal": {
                    "type": "number",
                        "description": "累计分佣收益/元",
                        "example": 1000
                },
                "roleId": {
                    "type": "string",
                        "description": "用户角色id",
                        "example": "1"
                },
                "roleName": {
                    "type": "string",
                        "description": "角色名",
                        "example": "普通用户"
                },
                "status": {
                    "type": "integer",
                        "description": "用户状态：1-正常，0-已拉黑",
                        "format": "int32",
                        "example": 1
                },
                "statusText": {
                    "type": "string",
                        "description": "状态文本：正常/已拉黑",
                        "example": "正常"
                },
                "lastLoginTime": {
                    "type": "string",
                        "description": "最后登录时间",
                        "format": "date-time"
                },
                "createdAt": {
                    "type": "string",
                        "description": "注册时间",
                        "format": "date-time"
                }
            },
            "description": "用户列表VO"
        },
        "ResultSysUserVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SysUserVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysUserVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "系统用户ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "type": "string",
                        "description": "用户名",
                        "example": "admin"
                },
                "email": {
                    "type": "string",
                        "description": "邮箱",
                        "example": "admin@example.com"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号",
                        "example": "13800138000"
                },
                "avatar": {
                    "type": "string",
                        "description": "头像URL",
                        "example": "http://example.com/avatar.jpg"
                },
                "status": {
                    "type": "integer",
                        "description": "状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                },
                "lastLoginTime": {
                    "type": "string",
                        "description": "最后登录时间",
                        "format": "date-time"
                },
                "lastLoginIp": {
                    "type": "string",
                        "description": "最后登录IP",
                        "example": "192.168.1.1"
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "roleIds": {
                    "type": "array",
                        "description": "用户拥有的角色ID列表",
                        "example": [1, 2, 3],
                        "items": {
                        "type": "integer",
                            "description": "用户拥有的角色ID列表",
                            "format": "int64"
                    }
                },
                "roleNames": {
                    "type": "array",
                        "description": "用户拥有的角色名称列表",
                        "example": [
                        "超级管理员",
                        "普通管理员"
                    ],
                        "items": {
                        "type": "string",
                            "description": "用户拥有的角色名称列表",
                            "example": "[\"超级管理员\",\"普通管理员\"]"
                    }
                }
            },
            "description": "系统用户视图对象",
                "example": "数据内容"
        },
        "ResultSetString": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "uniqueItems": true,
                        "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "type": "string",
                            "description": "返回的结果",
                            "example": "数据内容"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "ResultListSysMenuVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "$ref": "#/components/schemas/SysMenuVO"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "SysUserQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "username": {
                    "type": "string",
                        "description": "用户名（模糊查询）",
                        "example": "admin"
                },
                "email": {
                    "type": "string",
                        "description": "邮箱（模糊查询）",
                        "example": "admin@example.com"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号（模糊查询）",
                        "example": "13800138000"
                },
                "status": {
                    "maximum": 1,
                        "minimum": 0,
                        "type": "integer",
                        "description": "状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "系统用户查询DTO"
        },
        "PageResultSysUserVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SysUserVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSysUserVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSysUserVO"
                }
            },
            "description": "统一响应结果"
        },
        "ResultSettlementAccountVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SettlementAccountVO"
                }
            },
            "description": "统一响应结果"
        },
        "SettlementAccountVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "主键ID",
                        "format": "int64",
                        "example": 1
                },
                "accountName": {
                    "type": "string",
                        "description": "账户名称",
                        "example": "平台结算账户"
                },
                "accountCode": {
                    "type": "string",
                        "description": "账户编码(系统自动生成)",
                        "example": "ACC001"
                },
                "contactPhone": {
                    "type": "string",
                        "description": "联系方式",
                        "example": "13800138000"
                },
                "contactEmail": {
                    "type": "string",
                        "description": "联系邮箱(可选)",
                        "example": "admin@example.com"
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡号",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "张三"
                },
                "userType": {
                    "type": "string",
                        "description": "用户类型",
                        "example": "platform"
                },
                "paymentTime": {
                    "type": "string",
                        "description": "最后一次支付日期（从支付记录表联查获取）",
                        "format": "date-time"
                },
                "status": {
                    "type": "integer",
                        "description": "状态(0-禁用 1-启用)",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "结算账户视图对象，用于列表展示",
                "example": "数据内容"
        },
        "PaymentHistoryQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "settlementAccountId": {
                    "type": "integer",
                        "format": "int64"
                },
                "keyword": {
                    "type": "string"
                },
                "paymentAccount": {
                    "type": "string"
                },
                "paymentDateStart": {
                    "type": "string",
                        "format": "date-time"
                },
                "paymentDateEnd": {
                    "type": "string",
                        "format": "date-time"
                }
            }
        },
        "PageResultPaymentHistoryVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/PaymentHistoryVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "PaymentHistoryVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "支付记录ID",
                        "format": "int64",
                        "example": 1
                },
                "settlementAccountId": {
                    "type": "integer",
                        "description": "平台账户ID",
                        "format": "int64",
                        "example": 1
                },
                "accountName": {
                    "type": "string",
                        "description": "平台账户名称",
                        "example": "平台结算账户"
                },
                "contactPhone": {
                    "type": "string",
                        "description": "平台联系方式",
                        "example": "13800138000"
                },
                "targetType": {
                    "type": "string",
                        "description": "目标类型",
                        "example": "rider"
                },
                "paymentAccount": {
                    "type": "string",
                        "description": "平台打款账户（来自支付记录表）",
                        "example": "6222021234567890123"
                },
                "paymentAmount": {
                    "type": "number",
                        "description": "打款金额（来自支付记录表）",
                        "example": 5000
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "收款账户（即结算账户的银行卡号）",
                        "example": "6222021234567890123"
                },
                "paymentDate": {
                    "type": "string",
                        "description": "支付日期（来自支付记录表）",
                        "format": "date-time"
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间（来自支付记录表）",
                        "format": "date-time"
                }
            },
            "description": "打款历史记录视图对象，联表查询结算账户和支付记录"
        },
        "ResultPageResultPaymentHistoryVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultPaymentHistoryVO"
                }
            },
            "description": "统一响应结果"
        },
        "SettlementAccountQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "keyword": {
                    "type": "string"
                },
                "bankAccountNumber": {
                    "type": "string"
                },
                "paymentTime": {
                    "type": "string",
                        "format": "date-time"
                },
                "userType": {
                    "type": "string"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "PageResultSettlementAccountVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SettlementAccountVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSettlementAccountVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSettlementAccountVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysRoleQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "roleName": {
                    "type": "string"
                },
                "roleCode": {
                    "type": "string"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "PageResultSysRoleVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SysRoleVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSysRoleVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSysRoleVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysRoleVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "角色ID",
                        "format": "int64",
                        "example": 1
                },
                "roleName": {
                    "type": "string",
                        "description": "角色名称",
                        "example": "超级管理员"
                },
                "roleCode": {
                    "type": "string",
                        "description": "权限字符",
                        "example": "admin"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:0-停用,1-启用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "permissions": {
                    "uniqueItems": true,
                        "type": "array",
                        "description": "权限标识字符",
                        "example": [
                        "system:user:list",
                        "system:user:add"
                    ],
                        "items": {
                        "type": "string",
                            "description": "权限标识字符",
                            "example": "[\"system:user:list\",\"system:user:add\"]"
                    }
                }
            },
            "description": "系统角色视图对象，用于返回给前端"
        },
        "PartnerOption": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "合伙人姓名",
                        "example": "张三"
                }
            },
            "description": "合伙人选项（用于下拉选择）",
                "example": [
                {
                    "id": 1,
                    "partnerName": "张三"
                }
            ]
        },
        "ResultSysPartnerDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SysPartnerDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysPartnerDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "合伙人姓名",
                        "example": "张三"
                },
                "email": {
                    "type": "string",
                        "description": "合伙人邮箱",
                        "example": "zhangsan@example.com"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号",
                        "example": "13800138000"
                },
                "paymentAccountId": {
                    "type": "integer",
                        "description": "打款账户关联id",
                        "format": "int64",
                        "example": 1
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡号",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "银行卡开户名",
                        "example": "张三"
                },
                "commissionRate": {
                    "type": "number",
                        "description": "分佣比例(%)",
                        "example": 10
                },
                "parentId": {
                    "type": "integer",
                        "description": "上级合伙人ID",
                        "format": "int64",
                        "example": 0
                },
                "parentName": {
                    "type": "string",
                        "description": "上级合伙人姓名",
                        "example": "李四"
                },
                "auditId": {
                    "type": "integer",
                        "description": "关联的审核表id",
                        "format": "int64",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态:0-待审核,1-审核通过,2-审核拒绝",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "allPartners": {
                    "type": "array",
                        "description": "所有合伙人列表（用于选择上级合伙人）",
                        "example": [
                        {
                            "id": 1,
                            "partnerName": "张三"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/PartnerOption"
                    }
                }
            },
            "description": "合伙人详情视图对象（用于编辑页面），包含合伙人详细信息和辅助数据（如所有合伙人列表）",
                "example": "数据内容"
        },
        "SysPartnerQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "partnerName": {
                    "type": "string"
                },
                "orgName": {
                    "type": "string"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                },
                "phone": {
                    "type": "string"
                },
                "email": {
                    "type": "string"
                }
            }
        },
        "PageResultSysPartnerVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SysPartnerVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSysPartnerVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSysPartnerVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysPartnerVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "合伙人姓名",
                        "example": "张三"
                },
                "schoolIds": {
                    "type": "array",
                        "description": "管理学校id(列表)",
                        "example": [1, 2, 3],
                        "items": {
                        "type": "integer",
                            "description": "管理学校id(列表)",
                            "format": "int64"
                    }
                },
                "schoolNames": {
                    "type": "array",
                        "description": "管理学校名(列表)",
                        "example": [
                        "清华大学",
                        "北京大学"
                    ],
                        "items": {
                        "type": "string",
                            "description": "管理学校名(列表)",
                            "example": "[\"清华大学\",\"北京大学\"]"
                    }
                },
                "email": {
                    "type": "string",
                        "description": "合伙人邮箱",
                        "example": "zhangsan@example.com"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号",
                        "example": "13800138000"
                },
                "inviteCode": {
                    "type": "string",
                        "description": "推广码(邀请合伙人)",
                        "example": "ABC123"
                },
                "inviteCodePath": {
                    "type": "string",
                        "description": "推广码二维码路径",
                        "example": "/qrcode/ABC123.png"
                },
                "paymentAccountId": {
                    "type": "integer",
                        "description": "打款账户关联id",
                        "format": "int64",
                        "example": 1
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡号",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "银行卡开户名",
                        "example": "张三"
                },
                "commissionRate": {
                    "type": "number",
                        "description": "分佣比例(%)",
                        "example": 10
                },
                "parentId": {
                    "type": "integer",
                        "description": "上级合伙人ID",
                        "format": "int64",
                        "example": 0
                },
                "parentName": {
                    "type": "string",
                        "description": "上级合伙人姓名",
                        "example": "李四"
                },
                "auditId": {
                    "type": "integer",
                        "description": "关联的审核表id",
                        "format": "int64",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态:0-待审核,1-审核通过,2-审核拒绝",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "合伙人视图对象，用于返回给前端"
        },
        "MonthlyTurnoverItem": {
            "type": "object",
                "properties": {
                "month": {
                    "type": "string"
                },
                "turnover": {
                    "type": "number"
                }
            },
            "description": "平台每月流水",
                "example": [
                {
                    "month": "2024-01",
                    "amount": "100000.00"
                }
            ]
        },
        "ResultTransactionLogVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/TransactionLogVO"
                }
            },
            "description": "统一响应结果"
        },
        "TransactionLogVO": {
            "type": "object",
                "properties": {
                "todayPaymentAmount": {
                    "type": "number",
                        "description": "今日打款金额/元",
                        "example": 5000
                },
                "todayWithdrawalAmount": {
                    "type": "number",
                        "description": "今日提现金额/元",
                        "example": 2000
                },
                "todayTurnover": {
                    "type": "number",
                        "description": "今日流水/元（总交易额）",
                        "example": 7000
                },
                "todayTransactionAmount": {
                    "type": "number",
                        "description": "今日成交金额/元",
                        "example": 6500
                },
                "todayServiceRevenue": {
                    "type": "number",
                        "description": "今日服务收益/元",
                        "example": 1500
                },
                "todayOrderCount": {
                    "type": "integer",
                        "description": "今日成交订单/单",
                        "format": "int32",
                        "example": 50
                },
                "monthlyData": {
                    "type": "array",
                        "description": "平台每月流水",
                        "example": [
                        {
                            "month": "2024-01",
                            "amount": "100000.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/MonthlyTurnoverItem"
                    }
                }
            },
            "description": "交易流水统计VO",
                "example": "数据内容"
        },
        "ServiceLogListDTO": {
            "type": "object",
                "properties": {
                "serviceStaffId": {
                    "type": "integer",
                        "format": "int64"
                },
                "serviceStaffName": {
                    "type": "string"
                },
                "startDate": {
                    "type": "string",
                        "format": "date"
                },
                "endDate": {
                    "type": "string",
                        "format": "date"
                }
            }
        },
        "ResultListServiceLogListVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "$ref": "#/components/schemas/ServiceLogListVO"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "ServiceLogListVO": {
            "type": "object",
                "properties": {
                "serviceStaffId": {
                    "type": "integer",
                        "description": "服务人员ID",
                        "format": "int64",
                        "example": 1
                },
                "serviceStaffName": {
                    "type": "string",
                        "description": "服务人员名称",
                        "example": "王五"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "成交金额/元",
                        "example": 1000
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "成交订单/单",
                        "format": "int32",
                        "example": 50
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "打款金额/元",
                        "example": 800
                },
                "updatedAt": {
                    "type": "string",
                        "description": "操作时间",
                        "format": "date-time"
                }
            },
            "description": "服务人员流水日志列表VO，每个服务人员聚合为一行数据",
                "example": "数据内容"
        },
        "ServiceLogDetailDTO": {
            "type": "object",
                "properties": {
                "startDate": {
                    "type": "string",
                        "format": "date"
                },
                "endDate": {
                    "type": "string",
                        "format": "date"
                }
            }
        },
        "ResultServiceLogDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/ServiceLogDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "ServiceLogDetailItem": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "统计ID",
                        "format": "int64",
                        "example": 1
                },
                "statisticsDay": {
                    "type": "string",
                        "description": "统计日期",
                        "format": "date",
                        "example": "2024-01-01"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "成交金额/元",
                        "example": 100
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "成交订单/单",
                        "format": "int32",
                        "example": 10
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "打款金额/元",
                        "example": 80
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "流水明细记录项",
                "example": {
                "statisticsDay": "2024-01-01",
                    "totalSalesAmount": "100.00"
            }
        },
        "ServiceLogDetailVO": {
            "type": "object",
                "properties": {
                "serviceStaffId": {
                    "type": "integer",
                        "description": "服务人员ID",
                        "format": "int64",
                        "example": 1
                },
                "serviceStaffName": {
                    "type": "string",
                        "description": "服务人员名称",
                        "example": "王五"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "汇总：总成交金额/元",
                        "example": 10000
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "汇总：总成交订单/单",
                        "format": "int32",
                        "example": 500
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "汇总：总打款金额/元",
                        "example": 8000
                },
                "records": {
                    "type": "array",
                        "description": "该服务人员的所有流水明细记录",
                        "example": [
                        {
                            "statisticsDay": "2024-01-01",
                            "totalSalesAmount": "100.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/ServiceLogDetailItem"
                    }
                },
                "monthlyData": {
                    "type": "array",
                        "description": "服务人员每月流水数据（用于柱状图展示）",
                        "example": [
                        {
                            "month": "2024-01",
                            "amount": "10000.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/MonthlyTurnoverItem"
                    }
                }
            },
            "description": "服务人员流水日志详情VO，返回某个服务人员的所有流水明细记录、汇总数据和月度图表数据",
                "example": "数据内容"
        },
        "MerchantLogListDTO": {
            "type": "object",
                "properties": {
                "merchantId": {
                    "type": "integer",
                        "format": "int64"
                },
                "merchantName": {
                    "type": "string"
                },
                "startDate": {
                    "type": "string",
                        "format": "date"
                },
                "endDate": {
                    "type": "string",
                        "format": "date"
                }
            }
        },
        "MerchantLogListVO": {
            "type": "object",
                "properties": {
                "merchantId": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "statisticsDay": {
                    "type": "string",
                        "description": "月份（格式：2025.1 或 2025-01）",
                        "format": "date"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "成交金额/元",
                        "example": 10000
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "成交订单/单",
                        "format": "int32",
                        "example": 500
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "打款金额/元",
                        "example": 8000
                },
                "totalTransaction": {
                    "type": "number",
                        "description": "总流水/元",
                        "example": 9000
                },
                "updatedAt": {
                    "type": "string",
                        "description": "操作时间",
                        "format": "date-time"
                }
            },
            "description": "商家流水日志列表VO",
                "example": "数据内容"
        },
        "ResultListMerchantLogListVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "$ref": "#/components/schemas/MerchantLogListVO"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "MerchantLogDetailDTO": {
            "type": "object",
                "properties": {
                "startDate": {
                    "type": "string",
                        "format": "date"
                },
                "endDate": {
                    "type": "string",
                        "format": "date"
                }
            }
        },
        "MerchantLogDetailItem": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "统计ID",
                        "format": "int64",
                        "example": 1
                },
                "statisticsDay": {
                    "type": "string",
                        "description": "统计日期",
                        "format": "date",
                        "example": "2024-01-01"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "成交金额/元",
                        "example": 100
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "成交订单/单",
                        "format": "int32",
                        "example": 10
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "打款金额/元",
                        "example": 80
                },
                "totalTransaction": {
                    "type": "number",
                        "description": "总流水/元",
                        "example": 90
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "流水明细记录项",
                "example": {
                "statisticsDay": "2024-01-01",
                    "totalSalesAmount": "100.00"
            }
        },
        "MerchantLogDetailVO": {
            "type": "object",
                "properties": {
                "merchantId": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "汇总：总成交金额/元",
                        "example": 10000
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "汇总：总成交订单/单",
                        "format": "int32",
                        "example": 500
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "汇总：总打款金额/元",
                        "example": 8000
                },
                "totalTransaction": {
                    "type": "number",
                        "description": "汇总：总流水/元",
                        "example": 9000
                },
                "records": {
                    "type": "array",
                        "description": "该商家的所有流水明细记录",
                        "example": [
                        {
                            "statisticsDay": "2024-01-01",
                            "totalSalesAmount": "100.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/MerchantLogDetailItem"
                    }
                },
                "monthlyData": {
                    "type": "array",
                        "description": "商家每月流水数据（用于柱状图展示）",
                        "example": [
                        {
                            "month": "2024-01",
                            "amount": "10000.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/MonthlyTurnoverItem"
                    }
                }
            },
            "description": "商家流水日志详情VO，返回某个商家的所有流水明细记录、汇总数据和月度图表数据",
                "example": "数据内容"
        },
        "ResultMerchantLogDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/MerchantLogDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "CategorySalesListDTO": {
            "type": "object",
                "properties": {
                "categoryId": {
                    "type": "integer",
                        "format": "int64"
                },
                "categoryName": {
                    "type": "string"
                },
                "startDate": {
                    "type": "string",
                        "format": "date"
                },
                "endDate": {
                    "type": "string",
                        "format": "date"
                }
            }
        },
        "CategorySalesListVO": {
            "type": "object",
                "properties": {
                "categoryId": {
                    "type": "integer",
                        "description": "分类ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "餐饮"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "成交金额（聚合）",
                        "example": 10000
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "销售订单数（聚合）",
                        "format": "int32",
                        "example": 500
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "打款金额（聚合）",
                        "example": 8000
                },
                "totalTransaction": {
                    "type": "number",
                        "description": "流水（聚合）",
                        "example": 9000
                },
                "updatedAt": {
                    "type": "string",
                        "description": "最后更新时间",
                        "format": "date-time"
                }
            },
            "description": "分类销售统计列表VO",
                "example": "数据内容"
        },
        "ResultListCategorySalesListVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "$ref": "#/components/schemas/CategorySalesListVO"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "CategorySalesDetailDTO": {
            "type": "object",
                "properties": {
                "startDate": {
                    "type": "string",
                        "format": "date"
                },
                "endDate": {
                    "type": "string",
                        "format": "date"
                }
            }
        },
        "CategorySalesDetailItem": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "统计ID",
                        "format": "int64",
                        "example": 1
                },
                "statisticsDay": {
                    "type": "string",
                        "description": "统计日",
                        "format": "date",
                        "example": "2024-01-01"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "成交金额",
                        "example": 100
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "销售订单数",
                        "format": "int32",
                        "example": 10
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "打款金额",
                        "example": 80
                },
                "totalTransaction": {
                    "type": "number",
                        "description": "流水",
                        "example": 90
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "分类销售明细条目",
                "example": {
                "statisticsDay": "2024-01-01",
                    "totalSalesAmount": "100.00"
            }
        },
        "CategorySalesDetailVO": {
            "type": "object",
                "properties": {
                "categoryId": {
                    "type": "integer",
                        "description": "分类ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "餐饮"
                },
                "totalSalesAmount": {
                    "type": "number",
                        "description": "成交金额（汇总）",
                        "example": 10000
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "销售订单数（汇总）",
                        "format": "int32",
                        "example": 500
                },
                "totalPaymentAmount": {
                    "type": "number",
                        "description": "打款金额（汇总）",
                        "example": 8000
                },
                "totalTransaction": {
                    "type": "number",
                        "description": "流水（汇总）",
                        "example": 9000
                },
                "records": {
                    "type": "array",
                        "description": "明细记录列表",
                        "example": [
                        {
                            "statisticsDay": "2024-01-01",
                            "totalSalesAmount": "100.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/CategorySalesDetailItem"
                    }
                },
                "monthlyData": {
                    "type": "array",
                        "description": "月度流水数据（用于柱状图）",
                        "example": [
                        {
                            "month": "2024-01",
                            "amount": "10000.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/MonthlyTurnoverItem"
                    }
                }
            },
            "description": "分类销售统计详情VO",
                "example": "数据内容"
        },
        "ResultCategorySalesDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/CategorySalesDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "ServiceCommissionConfigQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "categoryId": {
                    "type": "integer",
                        "format": "int64"
                },
                "configType": {
                    "type": "integer",
                        "format": "int32"
                },
                "commissionType": {
                    "type": "integer",
                        "format": "int32"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "PageResultServiceCommissionConfigVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/ServiceCommissionConfigVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultServiceCommissionConfigVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultServiceCommissionConfigVO"
                }
            },
            "description": "统一响应结果"
        },
        "ServiceCommissionConfigVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "主键ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryId": {
                    "type": "integer",
                        "description": "关联服务分类ID",
                        "format": "int64",
                        "example": 1
                },
                "configType": {
                    "type": "integer",
                        "description": "配置类型：1-全局默认，2-服务分类",
                        "format": "int32",
                        "example": 2
                },
                "commissionRate": {
                    "type": "integer",
                        "description": "分佣比例(%)，10代表10%",
                        "format": "int32",
                        "example": 10
                },
                "commissionType": {
                    "type": "integer",
                        "description": "分佣类型：1-商家分佣，2-服务分佣，3-合伙人分佣",
                        "format": "int32",
                        "example": 2
                },
                "status": {
                    "type": "integer",
                        "description": "状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父级ID（0为顶级分类）",
                        "format": "int64",
                        "example": 0
                },
                "level": {
                    "type": "integer",
                        "description": "分类层级：1-一级分类，2-二级分类",
                        "format": "int32",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "代取快递"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序（值越小越靠前显示）",
                        "format": "int32",
                        "example": 1
                },
                "allowOfflineTrade": {
                    "type": "integer",
                        "description": "是否允许线下交易：0-否，1-是",
                        "format": "int32",
                        "example": 1
                },
                "categoryStatus": {
                    "type": "integer",
                        "description": "服务分类的状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                },
                "children": {
                    "type": "array",
                        "description": "子分类列表（用于构建树形结构）",
                        "items": {
                        "$ref": "#/components/schemas/ServiceCommissionConfigVO"
                    }
                },
                "categoryCode": {
                    "type": "integer",
                        "description": "分类编码（对应数据库中的id字段）",
                        "format": "int64",
                        "example": 1
                }
            },
            "description": "服务分佣配置视图对象，用于返回给前端"
        },
        "ResultServiceCategoryDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/ServiceCategoryDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "ServiceCategoryDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "分类ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "代取快递"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父节点ID",
                        "format": "int64",
                        "example": 0
                },
                "level": {
                    "type": "integer",
                        "description": "层级",
                        "format": "int32",
                        "example": 1
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "allowOfflineTrade": {
                    "type": "integer",
                        "description": "是否允许线下交易(1-允许 0-不允许)",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态(1-启用 0-禁用)",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "服务分类详情VO",
                "example": "数据内容"
        },
        "ResultListServiceCategoryTreeVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "$ref": "#/components/schemas/ServiceCategoryTreeVO"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "ServiceCategoryTreeVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "分类ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "代取快递"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父节点ID",
                        "format": "int64",
                        "example": 0
                },
                "level": {
                    "type": "integer",
                        "description": "层级",
                        "format": "int32",
                        "example": 1
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "allowOfflineTrade": {
                    "type": "integer",
                        "description": "是否允许线下交易(1-允许 0-不允许)",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态(1-启用 0-禁用)",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "children": {
                    "type": "array",
                        "description": "子节点列表",
                        "items": {
                        "$ref": "#/components/schemas/ServiceCategoryTreeVO"
                    }
                }
            },
            "description": "服务分类树形VO",
                "example": "数据内容"
        },
        "ResultSysSchoolVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SysSchoolVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysSchoolVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "学校ID",
                        "format": "int64",
                        "example": 1
                },
                "orgName": {
                    "type": "string",
                        "description": "校名",
                        "example": "清华大学"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "清华园1号"
                },
                "contactPerson": {
                    "type": "string",
                        "description": "联系人",
                        "example": "张三"
                },
                "contactPhone": {
                    "type": "string",
                        "description": "联系电话",
                        "example": "13800138000"
                },
                "partnerId": {
                    "type": "integer",
                        "description": "合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "学校VO",
                "example": "数据内容"
        },
        "SchoolQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "orgName": {
                    "type": "string"
                },
                "province": {
                    "type": "string"
                },
                "city": {
                    "type": "string"
                },
                "district": {
                    "type": "string"
                },
                "partnerId": {
                    "type": "integer",
                        "format": "int64"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "PageResultSysSchoolVO": {
            "type": "object",
                "properties": {
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "pages": {
                    "type": "integer",
                        "format": "int64"
                },
                "list": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SysSchoolVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSysSchoolVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSysSchoolVO"
                }
            },
            "description": "统一响应结果"
        },
        "ForumPostCommentQueryVo": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "评论ID",
                        "format": "int64",
                        "example": 1
                },
                "postId": {
                    "type": "integer",
                        "description": "帖子ID",
                        "format": "int64",
                        "example": 1
                },
                "userId": {
                    "type": "integer",
                        "description": "用户ID",
                        "format": "int64",
                        "example": 1
                },
                "userName": {
                    "type": "string",
                        "description": "用户名",
                        "example": "张三"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父评论ID",
                        "format": "int64",
                        "example": 0
                },
                "rootId": {
                    "type": "integer",
                        "description": "根评论ID",
                        "format": "int64",
                        "example": 1
                },
                "commentContent": {
                    "type": "string",
                        "description": "评论内容",
                        "example": "很好的分享！"
                },
                "level": {
                    "type": "integer",
                        "description": "层级",
                        "format": "int32",
                        "example": 1
                },
                "likeCount": {
                    "type": "integer",
                        "description": "点赞次数",
                        "format": "int32",
                        "example": 10
                },
                "replyCount": {
                    "type": "integer",
                        "description": "回复次数",
                        "format": "int32",
                        "example": 5
                },
                "status": {
                    "type": "integer",
                        "description": "状态",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "childComments": {
                    "type": "array",
                        "description": "子评论列表",
                        "items": {
                        "$ref": "#/components/schemas/ForumPostCommentQueryVo"
                    }
                },
                "deletedAt": {
                    "type": "string",
                        "description": "删除时间",
                        "format": "date-time"
                }
            },
            "description": "论坛帖子评论查询VO"
        },
        "ForumPostCommentTreeVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "评论ID",
                        "format": "int64",
                        "example": 1
                },
                "userId": {
                    "type": "integer",
                        "description": "用户ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "type": "string",
                        "description": "用户名",
                        "example": "张三"
                },
                "postTitle": {
                    "type": "string",
                        "description": "帖子标题",
                        "example": "校园生活分享"
                },
                "postContent": {
                    "type": "string",
                        "description": "帖子内容",
                        "example": "分享校园生活..."
                },
                "imageUrls": {
                    "type": "array",
                        "description": "图片URL列表",
                        "example": [
                        "http://example.com/img1.jpg",
                        "http://example.com/img2.jpg"
                    ],
                        "items": {
                        "type": "string",
                            "description": "图片URL列表",
                            "example": "[\"http://example.com/img1.jpg\",\"http://example.com/img2.jpg\"]"
                    }
                },
                "tags": {
                    "type": "array",
                        "description": "标签列表",
                        "example": [
                        "生活",
                        "分享"
                    ],
                        "items": {
                        "type": "string",
                            "description": "标签列表",
                            "example": "[\"生活\",\"分享\"]"
                    }
                },
                "viewCount": {
                    "type": "integer",
                        "description": "浏览次数",
                        "format": "int32",
                        "example": 100
                },
                "likeCount": {
                    "type": "integer",
                        "description": "点赞次数",
                        "format": "int32",
                        "example": 50
                },
                "shareCount": {
                    "type": "integer",
                        "description": "分享次数",
                        "format": "int32",
                        "example": 10
                },
                "favoriteCount": {
                    "type": "integer",
                        "description": "收藏次数",
                        "format": "int32",
                        "example": 20
                },
                "commentCount": {
                    "type": "integer",
                        "description": "评论次数",
                        "format": "int32",
                        "example": 30
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "comments": {
                    "type": "array",
                        "description": "评论列表",
                        "items": {
                        "$ref": "#/components/schemas/ForumPostCommentQueryVo"
                    }
                },
                "deletedAt": {
                    "type": "string",
                        "description": "删除时间",
                        "format": "date-time"
                }
            },
            "description": "论坛帖子评论树VO",
                "example": "数据内容"
        },
        "ResultForumPostCommentTreeVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/ForumPostCommentTreeVO"
                }
            },
            "description": "统一响应结果"
        },
        "ForumPostQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "postTitle": {
                    "type": "string"
                },
                "postContent": {
                    "type": "string"
                },
                "tags": {
                    "type": "string"
                },
                "userId": {
                    "type": "integer",
                        "format": "int64"
                },
                "username": {
                    "type": "string"
                }
            }
        },
        "ForumPostQueryVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "帖子ID",
                        "format": "int64",
                        "example": 1
                },
                "userId": {
                    "type": "integer",
                        "description": "用户ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "type": "string",
                        "description": "用户名",
                        "example": "张三"
                },
                "postTitle": {
                    "type": "string",
                        "description": "帖子标题",
                        "example": "校园生活分享"
                },
                "postContent": {
                    "type": "string",
                        "description": "帖子内容",
                        "example": "分享校园生活..."
                },
                "tags": {
                    "type": "string",
                        "description": "标签",
                        "example": "[\"生活\",\"分享\"]"
                },
                "viewCount": {
                    "type": "integer",
                        "description": "浏览次数",
                        "format": "int32",
                        "example": 100
                },
                "likeCount": {
                    "type": "integer",
                        "description": "点赞次数",
                        "format": "int32",
                        "example": 50
                },
                "shareCount": {
                    "type": "integer",
                        "description": "分享次数",
                        "format": "int32",
                        "example": 10
                },
                "favoriteCount": {
                    "type": "integer",
                        "description": "收藏次数",
                        "format": "int32",
                        "example": 20
                },
                "commentCount": {
                    "type": "integer",
                        "description": "评论次数",
                        "format": "int32",
                        "example": 30
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "deletedAt": {
                    "type": "string",
                        "description": "删除时间",
                        "format": "date-time"
                }
            },
            "description": "论坛帖子查询VO"
        },
        "PageResultForumPostQueryVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/ForumPostQueryVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultForumPostQueryVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultForumPostQueryVO"
                }
            },
            "description": "统一响应结果"
        },
        "ResultStaffOrderDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/StaffOrderDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "StaffOrderDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "订单ID",
                        "format": "int64",
                        "example": 1
                },
                "orderNo": {
                    "type": "string",
                        "description": "订单编号",
                        "example": "ORD20240101001"
                },
                "staffId": {
                    "type": "integer",
                        "description": "服务人员ID",
                        "format": "int64",
                        "example": 1
                },
                "staffName": {
                    "type": "string",
                        "description": "服务人员名称",
                        "example": "王五"
                },
                "staffPhone": {
                    "type": "string",
                        "description": "服务人员联系电话",
                        "example": "13900139000"
                },
                "orderType": {
                    "type": "integer",
                        "description": "订单类型（1-服务人员订单，2-商家订单，3-骑手订单）",
                        "format": "int32",
                        "example": 1
                },
                "orderCategory": {
                    "type": "string",
                        "description": "订单分类信息（如\"代取快递\"\"代拿外卖\"等）",
                        "example": "代取快递"
                },
                "orderTime": {
                    "type": "string",
                        "description": "下单时间",
                        "format": "date-time"
                },
                "buyerId": {
                    "type": "integer",
                        "description": "买家ID",
                        "format": "int64",
                        "example": 1
                },
                "buyerName": {
                    "type": "string",
                        "description": "买家姓名",
                        "example": "赵六"
                },
                "buyerPhone": {
                    "type": "string",
                        "description": "买家电话",
                        "example": "13800138000"
                },
                "buyerAddress": {
                    "type": "string",
                        "description": "买家地址",
                        "example": "学生宿舍3号楼101室"
                },
                "buyerAmount": {
                    "type": "number",
                        "description": "买家付款金额",
                        "example": 15
                },
                "payStatus": {
                    "type": "integer",
                        "description": "支付状态（0-未支付，1-已支付，2-已退款）",
                        "format": "int32",
                        "example": 1
                },
                "payMethod": {
                    "type": "integer",
                        "description": "支付方式（1-微信，2-支付宝，3-银行卡）",
                        "format": "int32",
                        "example": 1
                },
                "payTime": {
                    "type": "string",
                        "description": "支付时间",
                        "format": "date-time"
                },
                "orderStatus": {
                    "type": "integer",
                        "description": "订单状态（0-已取消，1-进行中，2-已完成）",
                        "format": "int32",
                        "example": 2
                },
                "orderStatusDesc": {
                    "type": "string",
                        "description": "订单状态描述",
                        "example": "已完成"
                },
                "staffProfit": {
                    "type": "number",
                        "description": "服务人员收益",
                        "example": 10
                },
                "merchantProfit": {
                    "type": "number",
                        "description": "服务商家收益",
                        "example": 3
                },
                "partnerProfit": {
                    "type": "number",
                        "description": "合伙人收益",
                        "example": 2
                },
                "partnerId": {
                    "type": "integer",
                        "description": "合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "合伙人名称",
                        "example": "张三"
                },
                "merchantId": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "schoolId": {
                    "type": "integer",
                        "description": "学校ID",
                        "format": "int64",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "服务人员订单详情VO",
                "example": "数据内容"
        },
        "StaffOrderQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "staffName": {
                    "type": "string"
                },
                "orderNo": {
                    "type": "string"
                },
                "orderStatus": {
                    "type": "integer",
                        "format": "int32"
                },
                "orderCategory": {
                    "type": "string"
                },
                "startTime": {
                    "type": "string",
                        "format": "date-time"
                },
                "endTime": {
                    "type": "string",
                        "format": "date-time"
                }
            }
        },
        "PageResultStaffOrderListVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/StaffOrderListVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultStaffOrderListVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultStaffOrderListVO"
                }
            },
            "description": "统一响应结果"
        },
        "StaffOrderListVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "订单ID",
                        "format": "int64",
                        "example": 1
                },
                "staffId": {
                    "type": "integer",
                        "description": "服务人员ID",
                        "format": "int64",
                        "example": 1
                },
                "staffName": {
                    "type": "string",
                        "description": "服务人员名称",
                        "example": "王五"
                },
                "orderType": {
                    "type": "integer",
                        "description": "订单类型（1-服务人员订单，2-商家订单，3-骑手订单）",
                        "format": "int32",
                        "example": 1
                },
                "orderCategory": {
                    "type": "string",
                        "description": "订单分类信息（如\"代取快递\"\"代拿外卖\"等）",
                        "example": "代取快递"
                },
                "orderNo": {
                    "type": "string",
                        "description": "订单编号",
                        "example": "ORD20240101001"
                },
                "createdAt": {
                    "type": "string",
                        "description": "下单时间（订单创建时间）",
                        "format": "date-time"
                },
                "buyerAmount": {
                    "type": "number",
                        "description": "买家付款金额（单位：元）",
                        "example": 15
                },
                "buyerAddress": {
                    "type": "string",
                        "description": "买家地址（配送/服务地址）",
                        "example": "学生宿舍3号楼101室"
                },
                "buyerName": {
                    "type": "string",
                        "description": "买家姓名",
                        "example": "赵六"
                },
                "orderStatus": {
                    "type": "integer",
                        "description": "订单状态（0-已取消，1-进行中，2-已完成）",
                        "format": "int32",
                        "example": 2
                },
                "orderStatusDesc": {
                    "type": "string",
                        "description": "订单状态描述",
                        "example": "已完成"
                },
                "staffProfit": {
                    "type": "number",
                        "description": "服务人员收益",
                        "example": 10
                },
                "merchantProfit": {
                    "type": "number",
                        "description": "服务商家收益",
                        "example": 3
                },
                "partnerProfit": {
                    "type": "number",
                        "description": "合伙人收益",
                        "example": 2
                }
            },
            "description": "服务人员订单列表VO"
        },
        "MerchantOrderDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "订单ID",
                        "format": "int64",
                        "example": 1
                },
                "orderNo": {
                    "type": "string",
                        "description": "订单编号",
                        "example": "ORD20240101001"
                },
                "merchantId": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "orgName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "merchantPhone": {
                    "type": "string",
                        "description": "商家联系电话",
                        "example": "13900139000"
                },
                "merchantAddress": {
                    "type": "string",
                        "description": "商家地址（完整地址：省+市+区+详细地址）",
                        "example": "北京市海淀区中关村大街1号"
                },
                "orderCategory": {
                    "type": "string",
                        "description": "订单信息/订单类别（如\"商家外卖\"\"商品销售\"等）",
                        "example": "商家外卖"
                },
                "createdAt": {
                    "type": "string",
                        "description": "下单时间（订单创建时间）",
                        "format": "date-time"
                },
                "orderTime": {
                    "type": "string",
                        "description": "订单时间（与createdAt相同，兼容前端）",
                        "format": "date-time"
                },
                "buyerAmount": {
                    "type": "number",
                        "description": "付款金额/元",
                        "example": 25.5
                },
                "buyerId": {
                    "type": "integer",
                        "description": "买家ID",
                        "format": "int64",
                        "example": 1
                },
                "buyerName": {
                    "type": "string",
                        "description": "付款人姓名",
                        "example": "张三"
                },
                "buyerPhone": {
                    "type": "string",
                        "description": "买家联系电话",
                        "example": "13800138000"
                },
                "buyerAddress": {
                    "type": "string",
                        "description": "买家地址/配送地址",
                        "example": "学生宿舍3号楼101室"
                },
                "orderStatus": {
                    "type": "integer",
                        "description": "订单状态（0-已取消，1-进行中，2-已完成）",
                        "format": "int32",
                        "example": 2
                },
                "orderStatusDesc": {
                    "type": "string",
                        "description": "订单状态描述",
                        "example": "已完成"
                },
                "payStatus": {
                    "type": "integer",
                        "description": "支付状态（0-未支付，1-已支付，2-已退款）",
                        "format": "int32",
                        "example": 1
                },
                "payMethod": {
                    "type": "integer",
                        "description": "支付方式（1-微信，2-支付宝，3-银行卡）",
                        "format": "int32",
                        "example": 1
                },
                "payTime": {
                    "type": "string",
                        "description": "支付时间",
                        "format": "date-time"
                },
                "merchantProfit": {
                    "type": "number",
                        "description": "商家收益/元",
                        "example": 20
                },
                "partnerId": {
                    "type": "integer",
                        "description": "合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "所属合伙人名称",
                        "example": "李四"
                },
                "partnerProfit": {
                    "type": "number",
                        "description": "合伙人收益/元",
                        "example": 5.5
                },
                "staffId": {
                    "type": "integer",
                        "description": "服务人员ID（如果有）",
                        "format": "int64",
                        "example": 1
                },
                "staffName": {
                    "type": "string",
                        "description": "服务人员姓名（如果有）",
                        "example": "王五"
                },
                "staffProfit": {
                    "type": "number",
                        "description": "服务人员收益/元（如果有）",
                        "example": 3
                },
                "schoolId": {
                    "type": "integer",
                        "description": "学校ID",
                        "format": "int64",
                        "example": 1
                },
                "updatedAt": {
                    "type": "string",
                        "description": "最后更新时间",
                        "format": "date-time"
                }
            },
            "description": "商家订单详情VO",
                "example": "数据内容"
        },
        "ResultMerchantOrderDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/MerchantOrderDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "MerchantOrderQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "orgName": {
                    "type": "string"
                },
                "orderNo": {
                    "type": "string"
                },
                "orderCategory": {
                    "type": "string"
                },
                "orderStatus": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "MerchantOrderListVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "订单ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantId": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "orgName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "orderCategory": {
                    "type": "string",
                        "description": "订单信息/订单类别（如\"商家外卖\"\"商品销售\"等）",
                        "example": "商家外卖"
                },
                "orderNo": {
                    "type": "string",
                        "description": "订单编号",
                        "example": "ORD20240101001"
                },
                "createdAt": {
                    "type": "string",
                        "description": "下单时间（订单创建时间）",
                        "format": "date-time"
                },
                "buyerAmount": {
                    "type": "number",
                        "description": "付款金额/元",
                        "example": 25.5
                },
                "buyerName": {
                    "type": "string",
                        "description": "付款人姓名",
                        "example": "李四"
                },
                "orderStatus": {
                    "type": "integer",
                        "description": "订单状态（0-已取消，1-进行中，2-已完成）",
                        "format": "int32",
                        "example": 2
                },
                "orderStatusDesc": {
                    "type": "string",
                        "description": "订单状态描述",
                        "example": "已完成"
                }
            },
            "description": "商家订单列表VO"
        },
        "PageResultMerchantOrderListVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/MerchantOrderListVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultMerchantOrderListVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultMerchantOrderListVO"
                }
            },
            "description": "统一响应结果"
        },
        "ResultSysMerchantDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SysMerchantDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysMerchantDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "orgName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "email": {
                    "type": "string",
                        "description": "商家邮箱",
                        "example": "merchant@example.com"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "contactPerson": {
                    "type": "string",
                        "description": "联系人",
                        "example": "李四"
                },
                "contactPhone": {
                    "type": "string",
                        "description": "联系电话",
                        "example": "13900139000"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "paymentAccount": {
                    "type": "string",
                        "description": "打款账户",
                        "example": "支付宝:13800138000"
                },
                "bankAccountNumber": {
                    "type": "string",
                        "description": "打款银行卡号",
                        "example": "6222021234567890123"
                },
                "bankAccountName": {
                    "type": "string",
                        "description": "开户名",
                        "example": "李四"
                },
                "partnerId": {
                    "type": "integer",
                        "description": "所属合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "所属合伙人姓名（关联查询）",
                        "example": "张三"
                },
                "auditId": {
                    "type": "integer",
                        "description": "审核记录ID",
                        "format": "int64",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态（0-待审核，1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "商家详情视图对象，用于商家管理界面详情展示和编辑",
                "example": "数据内容"
        },
        "MerchantSettleInVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "orgName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "contactPerson": {
                    "type": "string",
                        "description": "联系人",
                        "example": "李四"
                },
                "contactPhone": {
                    "type": "string",
                        "description": "联系方式（电话/手机）",
                        "example": "13900139000"
                },
                "province": {
                    "type": "string",
                        "description": "所在省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "所在市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "所在区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "partnerId": {
                    "type": "integer",
                        "description": "所属合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "所属合伙人名称（关联查询 sys_partner 表）",
                        "example": "张三"
                },
                "paymentAccount": {
                    "type": "string",
                        "description": "打款账户",
                        "example": "支付宝:13800138000"
                },
                "auditId": {
                    "type": "integer",
                        "description": "审核记录ID",
                        "format": "int64",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态（0-待审核，1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                },
                "auditorName": {
                    "type": "string",
                        "description": "审核人姓名",
                        "example": "管理员"
                },
                "auditTime": {
                    "type": "string",
                        "description": "审核时间",
                        "format": "date-time"
                },
                "auditOpinion": {
                    "type": "string",
                        "description": "审核意见",
                        "example": "审核通过"
                },
                "applyTime": {
                    "type": "string",
                        "description": "申请入驻时间（直接返回创建时间 created_at）",
                        "format": "date-time"
                },
                "status": {
                    "type": "integer",
                        "description": "商家状态（0-禁用，1-启用）",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "商家入驻申请列表展示VO，用于入驻申请列表页面展示",
                "example": "数据内容"
        },
        "ResultMerchantSettleInVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/MerchantSettleInVO"
                }
            },
            "description": "统一响应结果"
        },
        "MerchantSettleInQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "orgName": {
                    "type": "string"
                },
                "auditStatus": {
                    "type": "integer",
                        "format": "int32"
                },
                "partnerId": {
                    "type": "integer",
                        "format": "int64"
                }
            }
        },
        "PageResultMerchantSettleInVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/MerchantSettleInVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultMerchantSettleInVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultMerchantSettleInVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysMerchantQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "orgName": {
                    "type": "string"
                },
                "auditStatus": {
                    "type": "integer",
                        "format": "int32"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                },
                "partnerId": {
                    "type": "integer",
                        "format": "int64"
                }
            }
        },
        "PageResultSysMerchantVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SysMerchantVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSysMerchantVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSysMerchantVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysMerchantVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "orgName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "email": {
                    "type": "string",
                        "description": "商家邮箱",
                        "example": "merchant@example.com"
                },
                "contactPerson": {
                    "type": "string",
                        "description": "联系人",
                        "example": "李四"
                },
                "contactPhone": {
                    "type": "string",
                        "description": "联系电话",
                        "example": "13900139000"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "partnerId": {
                    "type": "integer",
                        "description": "所属合伙人ID",
                        "format": "int64",
                        "example": 1
                },
                "partnerName": {
                    "type": "string",
                        "description": "所属合伙人姓名（关联查询）",
                        "example": "张三"
                },
                "paymentAccount": {
                    "type": "string",
                        "description": "打款账户",
                        "example": "支付宝:13800138000"
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态（0-待审核，1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态:1启用 0禁用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "商家列表视图对象，用于商家管理主界面列表展示"
        },
        "ResultSysMenuVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SysMenuVO"
                }
            },
            "description": "统一响应结果"
        },
        "ProductEditVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "产品ID",
                        "format": "int64",
                        "example": 1
                },
                "productName": {
                    "type": "string",
                        "description": "产品名称",
                        "example": "可口可乐"
                },
                "title": {
                    "type": "string",
                        "description": "商品标题",
                        "example": "冰镇可口可乐 330ml"
                },
                "sellingPoints": {
                    "type": "string",
                        "description": "商品卖点",
                        "example": "冰爽解渴"
                },
                "description": {
                    "type": "string",
                        "description": "商品详情",
                        "example": "产品详细介绍..."
                },
                "mainImage": {
                    "type": "string",
                        "description": "商品主图URL",
                        "example": "http://example.com/product.jpg"
                },
                "images": {
                    "type": "array",
                        "description": "图片列表",
                        "example": [
                        "http://example.com/img1.jpg",
                        "http://example.com/img2.jpg"
                    ],
                        "items": {
                        "type": "string",
                            "description": "图片列表",
                            "example": "[\"http://example.com/img1.jpg\",\"http://example.com/img2.jpg\"]"
                    }
                },
                "categoryId": {
                    "type": "integer",
                        "description": "商品分类ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantId": {
                    "type": "integer",
                        "description": "所属商家ID",
                        "format": "int64",
                        "example": 1
                },
                "price": {
                    "type": "number",
                        "description": "价格",
                        "example": 3.5
                },
                "profitType": {
                    "type": "string",
                        "description": "收益类型（RATIO-按比例，FIXED-固定金额）",
                        "example": "RATIO"
                },
                "partnerProfit": {
                    "type": "number",
                        "description": "合伙人收益",
                        "example": 0.5
                },
                "merchantProfit": {
                    "type": "number",
                        "description": "服务商家收益",
                        "example": 2.5
                },
                "specType": {
                    "type": "string",
                        "description": "规格类型（SINGLE-统一规格，MULTIPLE-多规格）",
                        "example": "SINGLE"
                },
                "specData": {
                    "type": "array",
                        "description": "规格数据",
                        "example": [
                        {
                            "name": "默认规格",
                            "price": "3.50"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/SpecItem"
                    }
                },
                "shelfStatus": {
                    "type": "integer",
                        "description": "上架状态（0-未上架，1-已上架）",
                        "format": "int32",
                        "example": 1
                },
                "auditId": {
                    "type": "integer",
                        "description": "审核记录ID",
                        "format": "int64",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态（0-待审核，1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "商品编辑详情VO，用于商品编辑页面，包含完整的商品信息",
                "example": "数据内容"
        },
        "ResultProductEditVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/ProductEditVO"
                }
            },
            "description": "统一响应结果"
        },
        "SpecItem": {
            "required": [
                "name",
                "options",
                "price"
            ],
                "type": "object",
                "properties": {
                "name": {
                    "type": "string"
                },
                "options": {
                    "type": "array",
                        "items": {
                        "type": "string"
                    }
                },
                "price": {
                    "minimum": 0,
                        "exclusiveMinimum": false,
                        "type": "number"
                }
            },
            "description": "规格数据",
                "example": [
                {
                    "name": "默认规格",
                    "price": "3.50"
                }
            ]
        },
        "ProductQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "keyword": {
                    "type": "string",
                        "description": "关键字（搜索商品名称或标题）",
                        "example": "可乐"
                },
                "categoryId": {
                    "type": "integer",
                        "description": "商品分类ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantId": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "shelfStatus": {
                    "type": "integer",
                        "description": "上架状态（0-未上架，1-已上架）",
                        "format": "int32",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态（0-待审核，1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "商品查询DTO"
        },
        "PageResultProductVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/ProductVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ProductVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "产品ID",
                        "format": "int64",
                        "example": 1
                },
                "productName": {
                    "type": "string",
                        "description": "产品名称",
                        "example": "可口可乐"
                },
                "title": {
                    "type": "string",
                        "description": "商品标题",
                        "example": "冰镇可口可乐 330ml"
                },
                "mainImage": {
                    "type": "string",
                        "description": "商品主图URL",
                        "example": "http://example.com/product.jpg"
                },
                "price": {
                    "type": "number",
                        "description": "价格",
                        "example": 3.5
                },
                "profitType": {
                    "type": "string",
                        "description": "收益类型（RATIO-按比例，FIXED-固定金额）",
                        "example": "RATIO"
                },
                "partnerProfit": {
                    "type": "number",
                        "description": "合伙人收益",
                        "example": 0.5
                },
                "merchantProfit": {
                    "type": "number",
                        "description": "服务商家收益（原值）",
                        "example": 2.5
                },
                "merchantProfitAmount": {
                    "type": "number",
                        "description": "服务商家实际收益（动态计算），RATIO：price × merchantProfit% / 100，FIXED：merchantProfit",
                        "example": 2.5
                },
                "categoryId": {
                    "type": "integer",
                        "description": "商品分类ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "商品分类名称（关联查询 mch_category 表）",
                        "example": "饮料"
                },
                "merchantId": {
                    "type": "integer",
                        "description": "所属商家ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantName": {
                    "type": "string",
                        "description": "所属商家名称（关联查询 sys_merchant 表）",
                        "example": "校园便利店"
                },
                "shelfStatus": {
                    "type": "integer",
                        "description": "上架状态（0-未上架，1-已上架）",
                        "format": "int32",
                        "example": 1
                },
                "auditId": {
                    "type": "integer",
                        "description": "审核记录ID",
                        "format": "int64",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态（0-待审核，1-审核通过，2-审核拒绝）",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "商品列表展示VO，用于商品列表页面展示"
        },
        "ResultPageResultProductVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultProductVO"
                }
            },
            "description": "统一响应结果"
        },
        "MchCommissionConfigQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "orgName": {
                    "type": "string"
                }
            }
        },
        "MchCommissionConfigVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantId": {
                    "type": "integer",
                        "description": "关联商家id",
                        "format": "int64",
                        "example": 1
                },
                "orgName": {
                    "type": "string",
                        "description": "商家名",
                        "example": "校园便利店"
                },
                "commissionRate": {
                    "type": "integer",
                        "description": "分佣比例(%)，10代表10%",
                        "format": "int32",
                        "example": 10
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "商家分佣调控VO"
        },
        "PageResultMchCommissionConfigVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/MchCommissionConfigVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultMchCommissionConfigVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultMchCommissionConfigVO"
                }
            },
            "description": "统一响应结果"
        },
        "MchCategoryDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "分类ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "餐饮"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父节点ID",
                        "format": "int64",
                        "example": 0
                },
                "level": {
                    "type": "integer",
                        "description": "层级",
                        "format": "int32",
                        "example": 1
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态(1-启用 0-禁用)",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "商品分类详情VO",
                "example": "数据内容"
        },
        "ResultMchCategoryDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/MchCategoryDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "MchCategoryTreeVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "分类ID",
                        "format": "int64",
                        "example": 1
                },
                "categoryName": {
                    "type": "string",
                        "description": "分类名称",
                        "example": "餐饮"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父节点ID",
                        "format": "int64",
                        "example": 0
                },
                "level": {
                    "type": "integer",
                        "description": "层级",
                        "format": "int32",
                        "example": 1
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                },
                "status": {
                    "type": "integer",
                        "description": "状态(1-启用 0-禁用)",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "children": {
                    "type": "array",
                        "description": "子节点列表",
                        "items": {
                        "$ref": "#/components/schemas/MchCategoryTreeVO"
                    }
                }
            },
            "description": "商品分类树VO",
                "example": "数据内容"
        },
        "ResultListMchCategoryTreeVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "$ref": "#/components/schemas/MchCategoryTreeVO"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "ResultSysDictVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SysDictVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysDictVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "字典ID",
                        "format": "int64",
                        "example": 1
                },
                "dictGroup": {
                    "type": "string",
                        "description": "分组名称",
                        "example": "order_status"
                },
                "parentId": {
                    "type": "integer",
                        "description": "父ID",
                        "format": "int32",
                        "example": 0
                },
                "remark": {
                    "type": "string",
                        "description": "备注",
                        "example": "订单状态字典"
                },
                "dictKey": {
                    "type": "string",
                        "description": "字典键",
                        "example": "pending"
                },
                "dictValue": {
                    "type": "string",
                        "description": "字典值",
                        "example": "待处理"
                },
                "status": {
                    "type": "integer",
                        "description": "状态:0-禁用,1-启用",
                        "format": "int32",
                        "example": 1
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "children": {
                    "type": "array",
                        "description": "子节点列表（树形结构时使用）",
                        "items": {
                        "$ref": "#/components/schemas/SysDictVO"
                    }
                }
            },
            "description": "系统字典VO",
                "example": "数据内容"
        },
        "SysDictQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "dictGroup": {
                    "type": "string",
                        "description": "分组名称（模糊查询）",
                        "example": "order_status"
                },
                "dictKey": {
                    "type": "string",
                        "description": "字典键（模糊查询）",
                        "example": "pending"
                },
                "status": {
                    "type": "integer",
                        "description": "状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "字典查询DTO"
        },
        "PageResultSysDictVO": {
            "type": "object",
                "properties": {
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "pages": {
                    "type": "integer",
                        "format": "int64"
                },
                "list": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SysDictVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSysDictVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSysDictVO"
                }
            },
            "description": "统一响应结果"
        },
        "ResultListSysDictVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "type": "array",
                        "description": "返回的结果",
                        "example": "数据内容",
                        "items": {
                        "$ref": "#/components/schemas/SysDictVO"
                    }
                }
            },
            "description": "统一响应结果"
        },
        "ResultSysRiderDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/SysRiderDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysRiderDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "type": "string",
                        "description": "骑手用户名",
                        "example": "rider001"
                },
                "gender": {
                    "type": "string",
                        "description": "性别",
                        "example": "男"
                },
                "password": {
                    "type": "string",
                        "description": "密码（脱敏显示）",
                        "example": "******"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号（脱敏）",
                        "example": "138****8888"
                },
                "merchantId": {
                    "type": "integer",
                        "description": "所属商家ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantName": {
                    "type": "string",
                        "description": "所属商家名称",
                        "example": "校园便利店"
                },
                "merchantAddress": {
                    "type": "string",
                        "description": "商家地址",
                        "example": "中关村大街1号"
                },
                "merchantPhone": {
                    "type": "string",
                        "description": "商家电话",
                        "example": "13900139000"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "北京市"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "海淀区"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "中关村"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "中关村大街1号"
                },
                "fullAddress": {
                    "type": "string",
                        "description": "完整地址",
                        "example": "北京市海淀区中关村大街1号"
                },
                "balance": {
                    "type": "number",
                        "description": "账户余额/元",
                        "example": 500
                },
                "commissionTotal": {
                    "type": "number",
                        "description": "累计收益/元",
                        "example": 1000
                },
                "emergencyContactName": {
                    "type": "string",
                        "description": "紧急联系人姓名",
                        "example": "张三"
                },
                "emergencyContactPhone": {
                    "type": "string",
                        "description": "紧急联系人电话（脱敏）",
                        "example": "139****9999"
                },
                "createdAt": {
                    "type": "string",
                        "description": "注册时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "totalOrderCount": {
                    "type": "integer",
                        "description": "总订单数",
                        "format": "int32",
                        "example": 100
                },
                "completedOrderCount": {
                    "type": "integer",
                        "description": "已完成订单数",
                        "format": "int32",
                        "example": 80
                },
                "todayOrderCount": {
                    "type": "integer",
                        "description": "今日订单数",
                        "format": "int32",
                        "example": 5
                },
                "monthOrderCount": {
                    "type": "integer",
                        "description": "本月订单数",
                        "format": "int32",
                        "example": 50
                }
            },
            "description": "骑手详情视图对象",
                "example": "数据内容"
        },
        "ResultRiderWithdrawalDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/RiderWithdrawalDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "RiderWithdrawalDetailVO": {
            "type": "object",
                "properties": {
                "withdrawalId": {
                    "type": "integer",
                        "description": "提现记录ID",
                        "format": "int64",
                        "example": 1
                },
                "withdrawalNo": {
                    "type": "string",
                        "description": "提现单号",
                        "example": "TX20240101001"
                },
                "riderId": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 1
                },
                "riderName": {
                    "type": "string",
                        "description": "骑手姓名",
                        "example": "李四"
                },
                "riderPhone": {
                    "type": "string",
                        "description": "骑手手机号（脱敏）",
                        "example": "139****9000"
                },
                "merchantName": {
                    "type": "string",
                        "description": "所属商家名称",
                        "example": "校园便利店"
                },
                "balance": {
                    "type": "number",
                        "description": "骑手当前余额/元",
                        "example": 500
                },
                "amount": {
                    "type": "number",
                        "description": "申请提现金额/元",
                        "example": 100
                },
                "actualAmount": {
                    "type": "number",
                        "description": "实际到账金额/元",
                        "example": 100
                },
                "withdrawType": {
                    "type": "integer",
                        "description": "提现方式：1-微信，2-支付宝，3-银行卡",
                        "format": "int32",
                        "example": 1
                },
                "withdrawTypeText": {
                    "type": "string",
                        "description": "提现方式文本",
                        "example": "微信"
                },
                "withdrawAccount": {
                    "type": "string",
                        "description": "提现账号",
                        "example": "wx123456"
                },
                "withdrawName": {
                    "type": "string",
                        "description": "提现人姓名",
                        "example": "李四"
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态：0-待审核，1-已通过，2-已拒绝",
                        "format": "int32",
                        "example": 1
                },
                "auditStatusText": {
                    "type": "string",
                        "description": "审核状态文本",
                        "example": "已通过"
                },
                "createdAt": {
                    "type": "string",
                        "description": "申请时间",
                        "format": "date-time"
                },
                "auditorName": {
                    "type": "string",
                        "description": "审核人姓名",
                        "example": "管理员"
                },
                "auditTime": {
                    "type": "string",
                        "description": "审核时间",
                        "format": "date-time"
                },
                "auditOpinion": {
                    "type": "string",
                        "description": "审核意见",
                        "example": "审核通过"
                },
                "payStatus": {
                    "type": "integer",
                        "description": "打款状态：0-未打款，1-已打款",
                        "format": "int32",
                        "example": 1
                },
                "payStatusText": {
                    "type": "string",
                        "description": "打款状态文本",
                        "example": "已打款"
                },
                "payOperatorName": {
                    "type": "string",
                        "description": "打款操作人姓名",
                        "example": "财务"
                },
                "payTime": {
                    "type": "string",
                        "description": "打款时间",
                        "format": "date-time"
                },
                "payRemark": {
                    "type": "string",
                        "description": "打款备注",
                        "example": "打款成功"
                }
            },
            "description": "骑手提现记录详情视图对象",
                "example": "数据内容"
        },
        "RiderWithdrawalQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "riderName": {
                    "type": "string"
                },
                "riderPhone": {
                    "type": "string"
                },
                "auditStatus": {
                    "type": "integer",
                        "format": "int32"
                },
                "payStatus": {
                    "type": "integer",
                        "format": "int32"
                },
                "withdrawType": {
                    "type": "integer",
                        "format": "int32"
                },
                "startTime": {
                    "type": "string",
                        "format": "date-time"
                },
                "endTime": {
                    "type": "string",
                        "format": "date-time"
                }
            }
        },
        "PageResultRiderWithdrawalVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/RiderWithdrawalVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultRiderWithdrawalVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultRiderWithdrawalVO"
                }
            },
            "description": "统一响应结果"
        },
        "RiderWithdrawalVO": {
            "type": "object",
                "properties": {
                "withdrawalId": {
                    "type": "integer",
                        "description": "提现记录ID",
                        "format": "int64",
                        "example": 1
                },
                "withdrawalNo": {
                    "type": "string",
                        "description": "提现单号",
                        "example": "TX20240101001"
                },
                "riderId": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 1
                },
                "riderName": {
                    "type": "string",
                        "description": "骑手姓名（联表查询）",
                        "example": "张三"
                },
                "riderPhone": {
                    "type": "string",
                        "description": "骑手手机号（脱敏）",
                        "example": "138****8000"
                },
                "merchantName": {
                    "type": "string",
                        "description": "所属商家名称（联表查询）",
                        "example": "校园便利店"
                },
                "balance": {
                    "type": "number",
                        "description": "骑手当前余额/元（联表查询）",
                        "example": 500
                },
                "amount": {
                    "type": "number",
                        "description": "申请提现金额/元",
                        "example": 100
                },
                "actualAmount": {
                    "type": "number",
                        "description": "实际到账金额/元",
                        "example": 100
                },
                "withdrawType": {
                    "type": "integer",
                        "description": "提现方式：1-微信，2-支付宝，3-银行卡",
                        "format": "int32",
                        "example": 1
                },
                "withdrawTypeText": {
                    "type": "string",
                        "description": "提现方式文本",
                        "example": "微信"
                },
                "withdrawAccount": {
                    "type": "string",
                        "description": "提现账号",
                        "example": "wx123456"
                },
                "withdrawName": {
                    "type": "string",
                        "description": "提现人姓名",
                        "example": "张三"
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态：0-待审核，1-已通过，2-已拒绝",
                        "format": "int32",
                        "example": 1
                },
                "auditStatusText": {
                    "type": "string",
                        "description": "审核状态文本",
                        "example": "已通过"
                },
                "payStatus": {
                    "type": "integer",
                        "description": "打款状态：0-未打款，1-已打款",
                        "format": "int32",
                        "example": 1
                },
                "payStatusText": {
                    "type": "string",
                        "description": "打款状态文本",
                        "example": "已打款"
                },
                "createdAt": {
                    "type": "string",
                        "description": "申请时间",
                        "format": "date-time"
                }
            },
            "description": "骑手提现记录列表视图对象"
        },
        "ResultRiderOrderDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/RiderOrderDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "RiderOrderDetailVO": {
            "type": "object",
                "properties": {
                "orderId": {
                    "type": "integer",
                        "description": "订单ID",
                        "format": "int64",
                        "example": 1
                },
                "orderNo": {
                    "type": "string",
                        "description": "订单编号",
                        "example": "ORD20240101001"
                },
                "orderType": {
                    "type": "integer",
                        "description": "订单类型：1服务人员订单 2商家订单 3骑手订单",
                        "format": "int32",
                        "example": 3
                },
                "orderTypeText": {
                    "type": "string",
                        "description": "订单类型文本",
                        "example": "骑手订单"
                },
                "orderCategory": {
                    "type": "string",
                        "description": "订单分类信息",
                        "example": "配送服务"
                },
                "buyerId": {
                    "type": "integer",
                        "description": "买家ID",
                        "format": "int64",
                        "example": 1
                },
                "buyerName": {
                    "type": "string",
                        "description": "买家姓名",
                        "example": "张三"
                },
                "buyerPhone": {
                    "type": "string",
                        "description": "买家电话",
                        "example": "13800138000"
                },
                "buyerAddress": {
                    "type": "string",
                        "description": "买家地址",
                        "example": "学生宿舍3号楼101室"
                },
                "buyerAmount": {
                    "type": "number",
                        "description": "买家付款金额/元",
                        "example": 25
                },
                "riderId": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 1
                },
                "riderName": {
                    "type": "string",
                        "description": "骑手姓名",
                        "example": "李四"
                },
                "riderPhone": {
                    "type": "string",
                        "description": "骑手手机号",
                        "example": "13900139000"
                },
                "riderProfit": {
                    "type": "number",
                        "description": "骑手收益/元",
                        "example": 5
                },
                "merchantId": {
                    "type": "integer",
                        "description": "商家ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantName": {
                    "type": "string",
                        "description": "商家名称",
                        "example": "校园便利店"
                },
                "merchantProfit": {
                    "type": "number",
                        "description": "商家收益/元",
                        "example": 15
                },
                "orderStatus": {
                    "type": "integer",
                        "description": "订单状态：0-已取消，1-进行中，2-已完成",
                        "format": "int32",
                        "example": 2
                },
                "orderStatusText": {
                    "type": "string",
                        "description": "订单状态文本",
                        "example": "已完成"
                },
                "payStatus": {
                    "type": "integer",
                        "description": "支付状态：0未支付 1已支付 2已退款",
                        "format": "int32",
                        "example": 1
                },
                "payStatusText": {
                    "type": "string",
                        "description": "支付状态文本",
                        "example": "已支付"
                },
                "payMethod": {
                    "type": "integer",
                        "description": "支付方式：1微信 2支付宝 3银行卡",
                        "format": "int32",
                        "example": 1
                },
                "payMethodText": {
                    "type": "string",
                        "description": "支付方式文本",
                        "example": "微信"
                },
                "orderTime": {
                    "type": "string",
                        "description": "下单时间",
                        "format": "date-time"
                },
                "payTime": {
                    "type": "string",
                        "description": "支付时间",
                        "format": "date-time"
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "骑手订单详情视图对象",
                "example": "数据内容"
        },
        "RiderOrderQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "riderName": {
                    "type": "string"
                },
                "orderNo": {
                    "type": "string"
                },
                "orderStatus": {
                    "type": "integer",
                        "format": "int32"
                },
                "startTime": {
                    "type": "string",
                        "format": "date-time"
                },
                "endTime": {
                    "type": "string",
                        "format": "date-time"
                }
            }
        },
        "PageResultRiderOrderVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/RiderOrderVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultRiderOrderVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultRiderOrderVO"
                }
            },
            "description": "统一响应结果"
        },
        "RiderOrderVO": {
            "type": "object",
                "properties": {
                "orderId": {
                    "type": "integer",
                        "description": "订单ID",
                        "format": "int64",
                        "example": 1
                },
                "orderNo": {
                    "type": "string",
                        "description": "订单编号",
                        "example": "ORD20240101001"
                },
                "orderType": {
                    "type": "integer",
                        "description": "订单类型：1服务人员订单 2商家订单 3骑手订单",
                        "format": "int32",
                        "example": 3
                },
                "orderTypeText": {
                    "type": "string",
                        "description": "订单类型文本",
                        "example": "骑手订单"
                },
                "riderId": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 1
                },
                "riderName": {
                    "type": "string",
                        "description": "骑手姓名（联表查询）",
                        "example": "李四"
                },
                "riderPhone": {
                    "type": "string",
                        "description": "骑手手机号",
                        "example": "13900139000"
                },
                "merchantName": {
                    "type": "string",
                        "description": "商家名称（联表查询）",
                        "example": "校园便利店"
                },
                "buyerName": {
                    "type": "string",
                        "description": "买家姓名",
                        "example": "张三"
                },
                "buyerAddress": {
                    "type": "string",
                        "description": "买家地址",
                        "example": "学生宿舍3号楼101室"
                },
                "deliveryFee": {
                    "type": "number",
                        "description": "配送费/元",
                        "example": 5
                },
                "riderProfit": {
                    "type": "number",
                        "description": "骑手收益/元",
                        "example": 3
                },
                "orderStatus": {
                    "type": "integer",
                        "description": "订单状态：0-已取消，1-进行中，2-已完成",
                        "format": "int32",
                        "example": 2
                },
                "orderStatusText": {
                    "type": "string",
                        "description": "订单状态文本",
                        "example": "已完成"
                },
                "createdAt": {
                    "type": "string",
                        "description": "下单时间",
                        "format": "date-time"
                },
                "completedAt": {
                    "type": "string",
                        "description": "完成时间",
                        "format": "date-time"
                }
            },
            "description": "骑手订单列表视图对象"
        },
        "RiderQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "username": {
                    "type": "string"
                },
                "phone": {
                    "type": "string"
                },
                "merchantId": {
                    "type": "integer",
                        "format": "int64"
                },
                "city": {
                    "type": "string"
                }
            }
        },
        "PageResultSysRiderVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/SysRiderVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultSysRiderVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultSysRiderVO"
                }
            },
            "description": "统一响应结果"
        },
        "SysRiderVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "type": "string",
                        "description": "骑手用户名",
                        "example": "rider001"
                },
                "gender": {
                    "type": "string",
                        "description": "性别",
                        "example": "男"
                },
                "password": {
                    "type": "string",
                        "description": "密码（脱敏显示）",
                        "example": "******"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号（脱敏：138****8888）",
                        "example": "138****8888"
                },
                "merchantId": {
                    "type": "integer",
                        "description": "所属商家ID",
                        "format": "int64",
                        "example": 1
                },
                "merchantName": {
                    "type": "string",
                        "description": "所属商家名称（联表查询）",
                        "example": "校园便利店"
                },
                "city": {
                    "type": "string",
                        "description": "城市",
                        "example": "北京市"
                },
                "fullAddress": {
                    "type": "string",
                        "description": "详细地址（省市区+详细地址组合）",
                        "example": "北京市海淀区中关村大街1号"
                },
                "balance": {
                    "type": "number",
                        "description": "账户余额/元",
                        "example": 500
                },
                "commissionTotal": {
                    "type": "number",
                        "description": "累计收益/元",
                        "example": 1000
                },
                "emergencyContactName": {
                    "type": "string",
                        "description": "紧急联系人姓名",
                        "example": "张三"
                },
                "emergencyContactPhone": {
                    "type": "string",
                        "description": "紧急联系人电话（脱敏）",
                        "example": "139****9999"
                },
                "createdAt": {
                    "type": "string",
                        "description": "注册时间",
                        "format": "date-time"
                }
            },
            "description": "骑手列表视图对象"
        },
        "DeliveryFeeConfigDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "配置ID",
                        "format": "int32",
                        "example": 1
                },
                "configName": {
                    "type": "string",
                        "description": "配置名称",
                        "example": "校园配送费配置"
                },
                "baseFee": {
                    "type": "number",
                        "description": "起步价/元",
                        "example": 5
                },
                "baseDistance": {
                    "type": "number",
                        "description": "起步距离/公里",
                        "example": 3
                },
                "status": {
                    "type": "integer",
                        "description": "状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                },
                "statusText": {
                    "type": "string",
                        "description": "状态文本",
                        "example": "启用"
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "distanceFee": {
                    "type": "number",
                        "description": "距离费（元/公里）",
                        "example": 2
                },
                "dayTimeFee": {
                    "type": "number",
                        "description": "白天时段附加费（元）",
                        "example": 5
                },
                "nightTimeFee": {
                    "type": "number",
                        "description": "夜间时段附加费（元）",
                        "example": 8
                },
                "rules": {
                    "type": "array",
                        "description": "规则明细列表",
                        "example": [
                        {
                            "ruleType": "distance",
                            "distanceStart": "3.00"
                        }
                    ],
                        "items": {
                        "$ref": "#/components/schemas/FeeRuleVO"
                    }
                }
            },
            "description": "配送费配置详情视图对象",
                "example": "数据内容"
        },
        "FeeRuleVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "规则ID",
                        "format": "int32",
                        "example": 1
                },
                "ruleType": {
                    "type": "string",
                        "description": "规则类型：distance-距离规则，time-时间规则",
                        "example": "distance"
                },
                "ruleTypeText": {
                    "type": "string",
                        "description": "规则类型文本",
                        "example": "距离规则"
                },
                "distanceStart": {
                    "type": "number",
                        "description": "起始距离（公里）",
                        "example": 3
                },
                "distanceEnd": {
                    "type": "number",
                        "description": "结束距离（公里）",
                        "example": 5
                },
                "pricePerKm": {
                    "type": "number",
                        "description": "每公里价格（元）",
                        "example": 2
                },
                "timeStart": {
                    "type": "string",
                        "description": "开始时间（HH:mm）",
                        "example": "08:00"
                },
                "timeEnd": {
                    "type": "string",
                        "description": "结束时间（HH:mm）",
                        "example": "18:00"
                },
                "timeFee": {
                    "type": "number",
                        "description": "时段附加费（元）",
                        "example": 5
                },
                "timeType": {
                    "type": "string",
                        "description": "时段类型：daytime-白天，night-夜间",
                        "example": "daytime"
                },
                "sortOrder": {
                    "type": "integer",
                        "description": "排序",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "配送费规则视图对象",
                "example": [
                {
                    "ruleType": "distance",
                    "distanceStart": "3.00"
                }
            ]
        },
        "ResultDeliveryFeeConfigDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/DeliveryFeeConfigDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "FeeConfigQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "configName": {
                    "type": "string"
                },
                "status": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "DeliveryFeeConfigVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "配置ID",
                        "format": "int32",
                        "example": 1
                },
                "configName": {
                    "type": "string",
                        "description": "配置名称",
                        "example": "校园配送费配置"
                },
                "baseFee": {
                    "type": "number",
                        "description": "起步价/元",
                        "example": 5
                },
                "baseDistance": {
                    "type": "number",
                        "description": "起步距离/公里",
                        "example": 3
                },
                "status": {
                    "type": "integer",
                        "description": "状态：0-禁用，1-启用",
                        "format": "int32",
                        "example": 1
                },
                "statusText": {
                    "type": "string",
                        "description": "状态文本",
                        "example": "启用"
                },
                "ruleCount": {
                    "type": "integer",
                        "description": "规则数量（统计）",
                        "format": "int32",
                        "example": 5
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "配送费配置列表视图对象"
        },
        "PageResultDeliveryFeeConfigVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/DeliveryFeeConfigVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultDeliveryFeeConfigVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultDeliveryFeeConfigVO"
                }
            },
            "description": "统一响应结果"
        },
        "ServiceStaffAuditQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "auditStatus": {
                    "type": "integer",
                        "format": "int32"
                }
            }
        },
        "PageResultServiceStaffAuditVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/ServiceStaffAuditVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultServiceStaffAuditVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultServiceStaffAuditVO"
                }
            },
            "description": "统一响应结果"
        },
        "ServiceStaffAuditVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "用户ID",
                        "format": "int64",
                        "example": 1
                },
                "username": {
                    "type": "string",
                        "description": "人员名称",
                        "example": "王五"
                },
                "phone": {
                    "type": "string",
                        "description": "联系方式",
                        "example": "13900139000"
                },
                "schoolName": {
                    "type": "string",
                        "description": "服务所在区域（学校名称）",
                        "example": "清华大学"
                },
                "auditId": {
                    "type": "integer",
                        "description": "审核记录ID",
                        "format": "int64",
                        "example": 1
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态：0-待审核，1-审核通过，2-审核拒绝",
                        "format": "int32",
                        "example": 1
                },
                "auditOpinion": {
                    "type": "string",
                        "description": "审核意见",
                        "example": "审核通过"
                },
                "createdAt": {
                    "type": "string",
                        "description": "申请时间",
                        "format": "date-time"
                },
                "auditTime": {
                    "type": "string",
                        "description": "审核时间",
                        "format": "date-time"
                }
            },
            "description": "服务人员审核列表VO"
        },
        "ForumAnnouncementVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "公告ID",
                        "format": "int64",
                        "example": 1
                },
                "title": {
                    "type": "string",
                        "description": "公告标题",
                        "example": "系统维护通知"
                },
                "image": {
                    "type": "string",
                        "description": "公告图片",
                        "example": "http://example.com/announcement.jpg"
                },
                "isDisplay": {
                    "type": "integer",
                        "description": "显示状态(1-显示 0-隐藏)",
                        "format": "int32",
                        "example": 1
                },
                "schoolId": {
                    "type": "integer",
                        "description": "关联学校ID",
                        "format": "int64",
                        "example": 1
                },
                "schoolName": {
                    "type": "string",
                        "description": "学校名称（关联查询）",
                        "example": "清华大学"
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                }
            },
            "description": "公告VO",
                "example": "数据内容"
        },
        "ResultForumAnnouncementVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/ForumAnnouncementVO"
                }
            },
            "description": "统一响应结果"
        },
        "ForumAnnouncementQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "title": {
                    "type": "string"
                },
                "isDisplay": {
                    "type": "integer",
                        "format": "int32"
                },
                "schoolId": {
                    "type": "integer",
                        "format": "int64"
                }
            }
        },
        "PageResultForumAnnouncementVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/ForumAnnouncementVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultForumAnnouncementVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultForumAnnouncementVO"
                }
            },
            "description": "统一响应结果"
        },
        "RiderApplyQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "页码",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "riderName": {
                    "maxLength": 20,
                        "minLength": 0,
                        "type": "string",
                        "description": "骑手姓名",
                        "example": "李四骑手"
                },
                "riderPhone": {
                    "maxLength": 11,
                        "minLength": 0,
                        "type": "string",
                        "description": "骑手手机号",
                        "example": "13811111111"
                },
                "auditStatus": {
                    "maximum": 2,
                        "minimum": 0,
                        "type": "integer",
                        "description": "审核状态：0-待审核，1-已通过，2-已拒绝",
                        "format": "int32",
                        "example": 1
                },
                "startTime": {
                    "type": "string",
                        "description": "开始时间",
                        "example": "2023-01-01 00:00:00"
                },
                "endTime": {
                    "type": "string",
                        "description": "结束时间",
                        "example": "2023-12-31 23:59:59"
                }
            }
        },
        "PageResultRiderApplyVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/RiderApplyVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultRiderApplyVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultRiderApplyVO"
                }
            },
            "description": "统一响应结果"
        },
        "RiderApplyVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "申请ID",
                        "format": "int64",
                        "example": 1025
                },
                "auditNo": {
                    "type": "string",
                        "description": "审核编号",
                        "example": "AU202312120002"
                },
                "riderId": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 2001
                },
                "riderName": {
                    "type": "string",
                        "description": "骑手姓名",
                        "example": "李四骑手"
                },
                "riderPhone": {
                    "type": "string",
                        "description": "骑手手机号",
                        "example": "138****1111"
                },
                "gender": {
                    "type": "string",
                        "description": "骑手性别",
                        "example": "男"
                },
                "fullAddress": {
                    "type": "string",
                        "description": "省市区",
                        "example": "河北省邯郸市丛台区"
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态：0-待审核，1-已通过，2-已拒绝",
                        "format": "int32",
                        "example": 1
                },
                "auditStatusText": {
                    "type": "string",
                        "description": "审核状态文本",
                        "example": "已通过"
                },
                "createdAt": {
                    "type": "string",
                        "description": "申请时间",
                        "format": "date-time"
                },
                "idNum": {
                    "type": "string",
                        "description": "身份证号",
                        "example": "130429200303171111"
                },
                "avatar": {
                    "type": "string",
                        "description": "头像",
                        "example": "http://example.com/avatar.jpg"
                }
            }
        },
        "ResultRiderApplyDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/RiderApplyDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "RiderApplyDetailVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "申请ID",
                        "format": "int64",
                        "example": 1025
                },
                "auditNo": {
                    "type": "string",
                        "description": "审核编号",
                        "example": "AU202312120002"
                },
                "riderId": {
                    "type": "integer",
                        "description": "骑手ID",
                        "format": "int64",
                        "example": 2001
                },
                "riderName": {
                    "type": "string",
                        "description": "骑手姓名",
                        "example": "李四骑手"
                },
                "riderPhone": {
                    "type": "string",
                        "description": "骑手手机号",
                        "example": "13811111111"
                },
                "gender": {
                    "type": "string",
                        "description": "骑手性别",
                        "example": "男"
                },
                "emergencyContactName": {
                    "type": "string",
                        "description": "紧急联系人姓名",
                        "example": "李华"
                },
                "emergencyContactPhone": {
                    "type": "string",
                        "description": "紧急联系人电话",
                        "example": "13322222222"
                },
                "province": {
                    "type": "string",
                        "description": "省",
                        "example": "河北省"
                },
                "city": {
                    "type": "string",
                        "description": "市",
                        "example": "邯郸市"
                },
                "district": {
                    "type": "string",
                        "description": "区",
                        "example": "丛台区"
                },
                "address": {
                    "type": "string",
                        "description": "详细地址",
                        "example": "XX路XX号"
                },
                "auditStatus": {
                    "type": "integer",
                        "description": "审核状态：0-待审核，1-已通过，2-已拒绝",
                        "format": "int32",
                        "example": 1
                },
                "auditStatusText": {
                    "type": "string",
                        "description": "审核状态文本",
                        "example": "已通过"
                },
                "auditorName": {
                    "type": "string",
                        "description": "审核人姓名",
                        "example": "admin"
                },
                "auditTime": {
                    "type": "string",
                        "description": "审核时间",
                        "format": "date-time"
                },
                "auditOpinion": {
                    "type": "string",
                        "description": "审核意见",
                        "example": "资料齐全，审核通过"
                },
                "createdAt": {
                    "type": "string",
                        "description": "申请时间",
                        "format": "date-time"
                },
                "realName": {
                    "type": "string",
                        "description": "真实姓名",
                        "example": "李明"
                },
                "idNum": {
                    "type": "string",
                        "description": "身份证号",
                        "example": "130429200303171111"
                },
                "auditId": {
                    "type": "integer",
                        "description": "联动审核表 id",
                        "format": "int64",
                        "example": 1025
                },
                "openId": {
                    "type": "string",
                        "description": "openId",
                        "example": "o123456789"
                },
                "avatar": {
                    "type": "string",
                        "description": "头像",
                        "example": "http://example.com/avatar.jpg"
                },
                "idCardFront": {
                    "type": "string",
                        "description": "身份证正面照片地址",
                        "example": "http://example.com/idcard-front.jpg"
                },
                "idCardBack": {
                    "type": "string",
                        "description": "身份证反面照片地址",
                        "example": "http://example.com/idcard-back.jpg"
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ForumUserDetailDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "userName": {
                    "type": "string"
                },
                "phone": {
                    "type": "string"
                }
            }
        },
        "ForumActivityDetailVO": {
            "type": "object",
                "properties": {
                "userName": {
                    "type": "string",
                        "description": "用户名",
                        "example": "张三"
                },
                "phone": {
                    "type": "string",
                        "description": "手机号",
                        "example": "13800138000"
                },
                "status": {
                    "type": "integer",
                        "description": "状态",
                        "format": "int32",
                        "example": 1
                },
                "createTime": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                }
            },
            "description": "论坛活动详情VO"
        },
        "PageResultForumActivityDetailVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/ForumActivityDetailVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultForumActivityDetailVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultForumActivityDetailVO"
                }
            },
            "description": "统一响应结果"
        },
        "ForumActivityQueryDTO": {
            "type": "object",
                "properties": {
                "page": {
                    "minimum": 1,
                        "type": "integer",
                        "description": "当前页码，从1开始",
                        "format": "int32",
                        "example": 1
                },
                "size": {
                    "maximum": 100,
                        "minimum": 1,
                        "type": "integer",
                        "description": "每页条数",
                        "format": "int32",
                        "example": 10
                },
                "activityTitle": {
                    "type": "string"
                },
                "activityVenue": {
                    "type": "string"
                },
                "registrationTime": {
                    "type": "string"
                }
            }
        },
        "ForumActivityQueryVO": {
            "type": "object",
                "properties": {
                "id": {
                    "type": "integer",
                        "description": "活动ID",
                        "format": "int64",
                        "example": 1
                },
                "activityTitle": {
                    "type": "string",
                        "description": "活动标题",
                        "example": "校园篮球赛"
                },
                "maxParticipants": {
                    "type": "integer",
                        "description": "最大报名人数",
                        "format": "int32",
                        "example": 100
                },
                "currentParticipants": {
                    "type": "integer",
                        "description": "当前报名人数",
                        "format": "int32",
                        "example": 50
                },
                "activityTime": {
                    "type": "string",
                        "description": "活动时间",
                        "format": "date-time"
                },
                "status": {
                    "type": "integer",
                        "description": "活动状态(0-草稿 1-待审核 2-已发布 3-已取消)",
                        "format": "int32",
                        "example": 2
                },
                "createdAt": {
                    "type": "string",
                        "description": "创建时间",
                        "format": "date-time"
                },
                "updatedAt": {
                    "type": "string",
                        "description": "更新时间",
                        "format": "date-time"
                },
                "isVisible": {
                    "type": "integer",
                        "description": "是否显示(1-显示 0-隐藏)",
                        "format": "int32",
                        "example": 1
                }
            },
            "description": "论坛活动查询VO"
        },
        "PageResultForumActivityQueryVO": {
            "type": "object",
                "properties": {
                "current": {
                    "type": "integer",
                        "format": "int64"
                },
                "pageSize": {
                    "type": "integer",
                        "format": "int64"
                },
                "total": {
                    "type": "integer",
                        "format": "int64"
                },
                "records": {
                    "type": "array",
                        "items": {
                        "$ref": "#/components/schemas/ForumActivityQueryVO"
                    }
                }
            },
            "description": "返回的结果",
                "example": "数据内容"
        },
        "ResultPageResultForumActivityQueryVO": {
            "type": "object",
                "properties": {
                "code": {
                    "type": "integer",
                        "description": "状态码",
                        "format": "int32",
                        "example": 200
                },
                "message": {
                    "type": "string",
                        "description": "描述信息",
                        "example": "操作成功"
                },
                "result": {
                    "$ref": "#/components/schemas/PageResultForumActivityQueryVO"
                }
            },
            "description": "统一响应结果"
        }
    },
    "securitySchemes": {
        "Bearer Authentication": {
            "type": "http",
                "description": "请在下方输入JWT令牌（无需添加'Bearer '前缀）",
                "scheme": "bearer",
                "bearerFormat": "JWT"
        }
    }
}
}