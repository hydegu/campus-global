# 外卖订单管理功能开发计划

## 项目概述
开发campus-order模块的外卖订单管理功能，包括订单创建、骑手接单、取货、配送、取消等核心业务流程，集成高德地图API获取配送距离和预计送达时间，并基于配送费配置表计算配送费。

## 模块结构
- **order-api**: API定义层（枚举、实体、DTO、VO、Feign接口）
- **order-biz**: 业务实现层（Mapper、Service、Controller）

## 开发阶段

### 阶段一：数据库设计补充
**状态**: 待确认

**任务**:
- 确认商家数据库中的配送费配置表结构
  - `delivery_fee_config` (配送费配置主表)
  - `delivery_fee_rule` (配送费规则明细表)
- 确认订单数据库中的表结构
  - `order_main` (订单主表)
  - `order_delivery` (外卖订单扩展表)

**数据库表说明**:
- `order_main`: 订单主表，存储订单基本信息
- `order_delivery`: 外卖订单扩展表，存储配送相关信息
- `delivery_fee_config`: 配送费配置主表（位于campus_merchant_db）
- `delivery_fee_rule`: 配送费规则明细表（位于campus_merchant_db）

---

### 阶段二：order-api模块开发
**状态**: 待开发

**目录结构**:
```
order-api/src/main/java/com/example/order/api/
├── enums/          # 枚举类
├── entity/         # 实体类
├── dto/            # 数据传输对象
├── vo/             # 视图对象
└── feign/          # Feign远程调用接口
```

#### 2.1 枚举类 (enums/)

**OrderTypeEnum** - 订单类型
```java
- TAKEOUT(1, "外卖")
- SERVICE(2, "服务")
- OTHER(3, "其他")
```

**OrderStatusEnum** - 订单状态
```java
- WAIT_PAY(1, "待支付")
- WAIT_ACCEPT(2, "待接单")
- WAIT_PICKUP(3, "待取货")
- DELIVERING(4, "配送中")
- DELIVERED(5, "已送达")
- CANCELLED(6, "已取消")
- COMPLETED(7, "已完成")
- AFTER_SALE(8, "售后中")
```

**PayStatusEnum** - 支付状态
```java
- UNPAID(0, "待支付")
- PAID(1, "已支付")
- PARTIAL_REFUND(2, "部分退款")
- FULL_REFUND(3, "全额退款")
```

**PayMethodEnum** - 支付方式
```java
- ONLINE(1, "在线支付")
- WECHAT(2, "微信")
- OFFLINE(3, "线下支付")
- OTHER(4, "其他")
```

**CancelTypeEnum** - 取消类型
```java
- USER_CANCEL(1, "用户取消")
- MERCHANT_CANCEL(2, "商家取消")
- RIDER_CANCEL(3, "骑手取消")
- TIMEOUT_CANCEL(4, "超时取消")
```

#### 2.2 实体类 (entity/)

**OrderMain** - 订单主表实体
- 对应数据库表: `order_main`
- 字段: id, orderNo, orderType, userId, userName, userPhone, totalAmount, actualAmount, payStatus, payMethod, payTime, orderStatus, cancelType, cancelTime, serviceProviderType, serviceProviderId, serviceProviderName, schoolId, partnerId, estimatedProviderIncome, estimatedPartnerIncome, estimatedPlatformIncome, version, remark, estimatedStartTime, estimatedDeliveryTime, actualDeliveryTime, distance, createAt, updateAt, deleteAt

**OrderDelivery** - 外卖订单扩展表实体
- 对应数据库表: `order_delivery`
- 字段: id, orderId, merchantId, deliveryAddressId, goodsAmount, deliveryFee, riderId, createdAt, updatedAt

#### 2.3 DTO类 (dto/)

**OrderCreateDTO** - 订单创建请求
- merchantId: 商家ID
- deliveryAddressId: 收货地址ID
- goodsList: 商品列表
- remark: 订单备注

**OrderQueryDTO** - 订单查询请求
- orderNo: 订单编号
- orderStatus: 订单状态
- orderType: 订单类型
- startTime: 开始时间
- endTime: 结束时间
- pageNum: 页码
- pageSize: 每页数量

**OrderAcceptDTO** - 骑手接单请求
- orderId: 订单ID

**OrderPickupDTO** - 骑手取货请求
- orderId: 订单ID

**OrderDeliverDTO** - 骑手送达请求
- orderId: 订单ID

**DeliveryCalculateDTO** - 配送费计算请求
- merchantId: 商家ID
- deliveryAddressId: 收货地址ID
- goodsAmount: 商品金额

#### 2.4 VO类 (vo/)

**OrderVO** - 订单列表展示
- id: 订单ID
- orderNo: 订单编号
- orderType: 订单类型
- orderStatus: 订单状态
- totalAmount: 订单总金额
- actualAmount: 实付金额
- serviceProviderName: 服务提供方名称
- estimatedDeliveryTime: 预计送达时间
- createAt: 创建时间

**OrderDetailVO** - 订单详情展示
- 包含OrderVO所有字段
- userName: 用户姓名
- userPhone: 用户电话
- payStatus: 支付状态
- payMethod: 支付方式
- payTime: 支付时间
- deliveryFee: 配送费
- riderName: 骑手姓名
- riderPhone: 骑手电话
- actualDeliveryTime: 实际送达时间
- distance: 配送距离
- remark: 订单备注

**DeliveryCalculateVO** - 配送费计算结果
- distance: 距离（公里）
- deliveryFee: 配送费
- estimatedDeliveryTime: 预计送达时间

#### 2.5 Feign接口 (feign/)

**RemoteUserService** - 调用用户服务
- 通过不同参数获取和编辑骑手信息
- 继承自campus-upms模块的RemoteUserService

**RemoteMerchantService** - 调用商家服务
- 获取配送费配置信息
- TODO: 待商家模块开发后实现

---

### 阶段三：order-biz模块开发
**状态**: 待开发

**目录结构**:
```
order-biz/src/main/java/com/example/order/biz/
├── mapper/         # MyBatis Mapper接口
├── service/        # 业务服务层
└── controller/     # REST控制器
```

#### 3.1 Mapper接口 (mapper/)

**OrderMainMapper**
- 继承BaseMapper<OrderMain>

**OrderDeliveryMapper**
- 继承BaseMapper<OrderDelivery>

#### 3.2 Service层 (service/)

**OrderService** - 订单核心业务
```java
- createOrder(OrderCreateDTO createDTO): 创建订单
- acceptOrder(Long orderId, Long riderId): 骑手接单
- pickupOrder(Long orderId): 骑手取货
- deliverOrder(Long orderId): 骑手送达
- cancelOrder(Long orderId, Integer cancelType): 取消订单
- getOrderDetail(Long orderId): 获取订单详情
- listOrders(OrderQueryDTO queryDTO): 订单列表查询
```

**DeliveryFeeService** - 配送费计算
```java
- calculateDeliveryFee(DeliveryCalculateDTO calculateDTO): 计算配送费
  - TODO: 调用商家模块获取配送费配置（待商家模块开发后替换）
- getEstimatedDeliveryTime(Long merchantId, Long deliveryAddressId): 获取预计送达时间
```

**AmapService** - 高德地图API集成
```java
- getDistanceAndDuration(String origin, String destination): 获取距离和预计时间
- getRoutePlanning(String origin, String destination): 路径规划
```

#### 3.3 Controller层 (controller/)

**OrderController**
```java
- POST /api/order/create - 创建订单
- POST /api/order/accept - 骑手接单
- POST /api/order/pickup - 骑手取货
- POST /api/order/deliver - 骑手送达
- POST /api/order/cancel - 取消订单
- GET /api/order/list - 订单列表
- GET /api/order/{id} - 订单详情
```

**DeliveryController**
```java
- POST /api/delivery/calculate - 计算配送费和预计时间
```

---

### 阶段四：高德地图API集成
**状态**: 待开发

**任务**:
1. 配置高德地图API Key
2. 封装高德地图API调用工具类
   - 距离测量API
   - 路径规划API
   - 骑行路径规划（适合外卖配送）
3. 实现距离、预计送达时间获取
4. 结合配送费配置表计算配送费（TODO标记，待商家模块开发后替换）

**高德API文档**: https://lbs.amap.com/api/

---

### 阶段五：配置与依赖
**状态**: 待开发

**任务**:
1. 配置application.yml
   - 数据库配置
   - 高德地图API配置
   - Nacos配置中心
2. 配置order-api模块pom.xml依赖
   - common-core
   - common-feign
   - common-mybatis
   - upms-api
3. 配置order-biz模块pom.xml依赖
   - order-api
   - upms-api
   - common-oss
   - common-feign
   - common-security
   - common-swagger
   - mybatis-plus
   - mysql-connector
   - nacos-discovery
   - nacos-config
   - undertow
4. 配置Feign客户端

---

### 阶段六：测试
**状态**: 待开发

**任务**:
1. 单元测试（Service层）
2. 集成测试（Controller层）
3. 高德API调用测试
4. 配送费计算测试（使用TODO模拟数据）

---

## 关键技术点

### 1. 配送费计算逻辑
- 使用商家数据库中的 `delivery_fee_config` 和 `delivery_fee_rule` 表
- TODO标记：待商家模块开发后通过RemoteMerchantService获取配置
- 计算公式：起步价 + (超出距离 × 每公里价格) + 时段附加费

### 2. 高德地图API集成
- 距离测量API: 计算商家到收货地址的距离
- 路径规划API: 获取预计配送时间
- 骑行路径规划: 适合外卖配送场景

### 3. 骑手信息管理
- 使用RemoteUserService获取和编辑骑手信息
- 通过不同参数区分用户类型（用户/骑手/商家）

### 4. 订单状态流转
```
待支付 → 待接单 → 待取货 → 配送中 → 已送达 → 已完成
         ↓
      已取消
```

---

## 预估工作量

| 阶段 | 任务 | 预估时间 |
|------|------|----------|
| 阶段一 | 数据库设计补充 | 0.5天 |
| 阶段二 | order-api模块开发 | 1.5天 |
| 阶段三 | order-biz模块开发 | 2天 |
| 阶段四 | 高德地图API集成 | 1天 |
| 阶段五 | 配置与依赖 | 0.5天 |
| 阶段六 | 测试 | 0.5天 |
| **总计** | | **6天** |

---

## 依赖关系

### 外部依赖
- campus-upms: 用户服务（骑手信息）
- campus-merchant: 商家服务（配送费配置，待开发）

### 内部依赖
- common-core: 核心工具类
- common-feign: Feign调用
- common-mybatis: MyBatis集成
- common-security: 安全模块
- common-swagger: 接口文档

---

## 注意事项

1. **TODO标记**: 配送费计算中调用商家模块的逻辑需要标记TODO，待商家模块开发后替换
2. **枚举调整**:
   - PayStatusEnum状态2改为"部分退款"
   - CancelTypeEnum新增状态4"骑手取消"
3. **代码规范**: 遵循项目现有代码风格和命名规范
4. **异常处理**: 统一异常处理机制
5. **日志记录**: 关键业务操作记录日志
6. **事务管理**: 使用Seata分布式事务

---

## 更新记录

| 日期 | 版本 | 更新内容 |
|------|------|----------|
| 2026-01-19 | 1.0 | 初始版本 |

---

## 附录

### 数据库表结构参考
- `campus_order_db.sql`: 订单数据库表结构
- `campus_merchant_db.sql`: 商家数据库表结构（包含配送费配置表）

### 相关文档
- 高德地图API文档: https://lbs.amap.com/api/
- 项目开发指南: `开发指南.md`
