# 服务订单（跑腿订单）管理功能开发计划

## 项目概述
开发campus-order模块的服务订单（跑腿订单）管理功能，包括订单创建、服务人员接单、取货、送达、取消等核心业务流程，集成高德地图API获取预计送达时间，配送费由DTO传递（不计算）。

## 模块结构
- **order-api**: API定义层（枚举、实体、DTO、VO、Feign接口）
- **order-biz**: 业务实现层（Mapper、Service、Controller）

## 开发阶段

### 阶段一：数据库表结构确认
**状态**: 待确认

**任务**:
- 确认服务订单数据库表结构
  - `order_main` (订单主表，order_type=2表示服务订单)
  - `order_errand` (跑腿订单扩展表)
- 确认商家数据库中的服务分类表
  - `errand_caregory` (服务分类管理表)

**数据库表说明**:
- `order_main`: 订单主表，存储订单基本信息（order_type=2表示服务订单）
- `order_errand`: 跑腿订单扩展表，存储服务订单特有信息
- `errand_caregory`: 服务分类表（位于campus_merchant_db）

---

### 阶段二：order-api模块开发
**状态**: 待开发

**目录结构**:
```
order-api/src/main/java/com/example/order/api/
├── enums/          # 枚举类（部分已存在）
├── entity/         # 实体类（部分已存在）
├── dto/            # 数据传输对象（部分已存在）
├── vo/             # 视图对象（部分已存在）
└── feign/          # Feign远程调用接口（部分已存在）
```

#### 2.1 枚举类 (enums/)
**已有枚举类**（复用）:
- OrderTypeEnum - 订单类型（包含SERVICE=2）
- OrderStatusEnum - 订单状态
- PayStatusEnum - 支付状态
- PayMethodEnum - 支付方式
- CancelTypeEnum - 取消类型（注意：服务订单取消类型不包含骑手取消）

#### 2.2 实体类 (entity/)

**OrderErrand** - 跑腿订单扩展表实体（新增）
- 对应数据库表: `order_errand`
- 字段: id, orderId, serviceFee, serviceTypeId, pickupAddressId, deliveryAddressId, itemDescription, itemWeight, length, width, height, volume, staffId, createdAt, updatedAt

#### 2.3 DTO类 (dto/)

**ErrandCreateDTO** - 服务订单创建请求（新增）
- serviceTypeId: 服务分类ID
- pickupAddressId: 取货地址ID
- deliveryAddressId: 送货地址ID
- itemDescription: 物品描述
- itemWeight: 物品重量(kg)
- length: 长度
- width: 宽度
- height: 高度
- deliveryFee: 配送费（由前端传递，不计算）
- remark: 订单备注

**ErrandAcceptDTO** - 服务人员接单请求（新增）
- orderId: 订单ID

**ErrandPickupDTO** - 服务人员取货请求（新增）
- orderId: 订单ID

**ErrandDeliverDTO** - 服务人员送达请求（新增）
- orderId: 订单ID

**ErrandQueryDTO** - 服务订单查询请求（新增）
- orderNo: 订单编号
- orderStatus: 订单状态
- serviceTypeId: 服务分类ID
- startTime: 开始时间
- endTime: 结束时间
- pageNum: 页码
- pageSize: 每页数量

#### 2.4 VO类 (vo/)

**ErrandDetailVO** - 服务订单详情展示（新增）
- 包含OrderDetailVO所有字段
- serviceFee: 服务费
- serviceTypeName: 服务分类名称
- pickupAddress: 取货地址信息
- deliveryAddress: 送货地址信息
- itemDescription: 物品描述
- itemWeight: 物品重量
- itemSize: 物品尺寸（长x宽x高）
- volume: 体积
- staffName: 服务人员姓名
- staffPhone: 服务人员电话

#### 2.5 Feign接口 (feign/)
**已有Feign接口**（复用）:
- RemoteUserService - 调用用户服务（获取服务人员信息）
- RemoteMerchantService - 调用商家服务（获取服务分类信息）

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

**OrderErrandMapper**（新增）
- 继承BaseMapper<OrderErrand>

#### 3.2 Service层 (service/)

**ErrandService** - 服务订单核心业务（新增）
```java
- createErrand(ErrandCreateDTO createDTO): 创建服务订单
- acceptErrand(ErrandAcceptDTO acceptDTO): 服务人员接单
- pickupErrand(ErrandPickupDTO pickupDTO): 服务人员取货
- deliverErrand(ErrandDeliverDTO deliverDTO): 服务人员送达
- cancelErrand(Long orderId, Integer cancelType): 取消订单
- getErrandDetail(Long orderId): 获取订单详情
- listErrands(ErrandQueryDTO queryDTO): 订单列表查询
```

**AmapService** - 高德地图API集成（已有，复用）
```java
- getDistance(String origin, String destination): 获取距离
- getDuration(String origin, String destination): 获取预计时间
```

#### 3.3 Controller层 (controller/)

**ErrandController**（新增）
```java
- POST /api/errand/create - 创建服务订单
- POST /api/errand/accept - 服务人员接单
- POST /api/errand/pickup - 服务人员取货
- POST /api/errand/deliver - 服务人员送达
- POST /api/errand/cancel - 取消订单
- GET /api/errand/list - 服务订单列表
- GET /api/errand/{id} - 服务订单详情
```

---

### 阶段四：高德地图API集成
**状态**: 已完成（复用外卖订单的AmapService）

**任务**:
- 复用已有的AmapService实现
- 在创建服务订单时调用高德地图API获取预计送达时间
- 配送费由DTO传递，不进行计算

---

### 阶段五：配置与依赖
**状态**: 已完成（复用外卖订单的配置）

**任务**:
- 复用order-biz的pom.xml配置
- 复用application.yml配置
- 无需额外配置

---

### 阶段六：测试
**状态**: 待开发

**任务**:
1. 单元测试（Service层）
2. 集成测试（Controller层）
3. 高德API调用测试
4. 配送费传递测试

---

## 关键技术点

### 1. 配送费处理
- **与外卖订单的区别**：配送费由前端DTO传递，不进行计算
- 直接使用DTO中的deliveryFee字段
- 不需要调用商家服务获取配送费配置

### 2. 高德地图API集成
- 距离测量API: 计算取货地址到送货地址的距离
- 路径规划API: 获取预计配送时间
- 复用已有的AmapService实现

### 3. 服务人员信息管理
- 使用RemoteUserService获取服务人员信息
- 通过不同参数区分用户类型（用户/服务人员）
- staffId对应order_main表中的service_provider_id

### 4. 订单状态流转
```
待支付 → 待接单 → 待取货 → 配送中 → 已送达 → 已完成
         ↓
      已取消
```

### 5. 物品信息
- 支持物品描述、重量、尺寸（长x宽x高）
- 可选字段，根据服务类型决定是否必填
- 体积可自动计算：长 × 宽 × 高

---

## 预估工作量

| 阶段 | 任务 | 预估时间 |
|------|------|----------|
| 阶段一 | 数据库表结构确认 | 0.5天 |
| 阶段二 | order-api模块开发 | 1天 |
| 阶段三 | order-biz模块开发 | 1.5天 |
| 阶段四 | 高德地图API集成 | 0天（复用） |
| 阶段五 | 配置与依赖 | 0天（复用） |
| 阶段六 | 测试 | 0.5天 |
| **总计** | | **3.5天** |

---

## 依赖关系

### 外部依赖
- campus-upms: 用户服务（服务人员信息）
- campus-merchant: 商家服务（服务分类信息，可选）

### 内部依赖
- common-core: 核心工具类
- common-feign: Feign调用
- common-mybatis: MyBatis集成
- common-security: 安全模块
- common-swagger: 接口文档

---

## 注意事项

1. **配送费处理**：配送费由DTO传递，不进行计算
2. **服务人员**：使用staffId字段，对应order_main表的service_provider_id
3. **物品信息**：支持描述、重量、尺寸等可选字段
4. **取消类型**：服务订单取消类型不包含"骑手取消"
5. **代码规范**：遵循项目现有代码风格和命名规范
6. **异常处理**：统一异常处理机制
7. **日志记录**：关键业务操作记录日志
8. **事务管理**：使用Seata分布式事务

---

## 更新记录

| 日期 | 版本 | 更新内容 |
|------|------|----------|
| 2026-01-19 | 1.0 | 初始版本 |

---

## 附录

### 数据库表结构参考
- `campus_order_db.sql`: 订单数据库表结构
  - `order_main`: 订单主表
  - `order_errand`: 跑腿订单扩展表
- `campus_merchant_db.sql`: 商家数据库表结构
  - `errand_caregory`: 服务分类管理表

### 相关文档
- 高德地图API文档: https://lbs.amap.com/api/
- 项目开发指南: `开发指南.md`
- 外卖订单开发计划: `order_development_plan.md`
