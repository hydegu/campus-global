FROM registry.cn-hangzhou.aliyuncs.com/dockerhub_mirror/mysql-server:8.0.32

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./campus_behavior_db.sql /docker-entrypoint-initdb.d
COPY ./campus_finance_db.sql /docker-entrypoint-initdb.d
COPY ./campus_forum_db.sql /docker-entrypoint-initdb.d
COPY ./campus_merchant_db.sql /docker-entrypoint-initdb.d
COPY ./campus_message_db.sql /docker-entrypoint-initdb.d
COPY ./campus_order_db.sql /docker-entrypoint-initdb.d
COPY ./campus_service_db.sql /docker-entrypoint-initdb.d
COPY ./campus_upms_db.sql /docker-entrypoint-initdb.d
COPY ./nacos_config.sql /docker-entrypoint-initdb.d
COPY ./seata.sql /docker-entrypoint-initdb.d

EXPOSE 3306
