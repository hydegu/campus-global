package com.example.order.biz.config;

import com.example.common.core.exception.ResourceNotFoundException;
import com.example.order.api.entity.OrderMain;
import com.example.order.api.enums.OrderEventsEnum;
import com.example.order.api.enums.OrderStatusEnum;
import com.example.order.biz.mapper.OrderMainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;

import java.util.EnumSet;
import java.util.Optional;

@Configuration
@EnableStateMachine(name = "orderStateMachine")
@RequiredArgsConstructor
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderEventsEnum> {

    private final OrderMainMapper orderMainMapper;

    /**
     * 配置状态
     */
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventsEnum> states) throws Exception{
        states
                .withStates()
                .initial(OrderStatusEnum.WAIT_ACCEPT)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    /**
     * 状态转换事件关系
     */
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum,OrderEventsEnum> transitions) throws Exception{
        transitions
                .withExternal().source(OrderStatusEnum.WAIT_ACCEPT).target(OrderStatusEnum.WAIT_PICKUP)
                .event(OrderEventsEnum.ACCEPT)
                .and()
                .withExternal().source(OrderStatusEnum.WAIT_PICKUP).target(OrderStatusEnum.DELIVERING)
                .event(OrderEventsEnum.PICK_UP)
                .and()
                .withExternal().source(OrderStatusEnum.DELIVERING).target(OrderStatusEnum.DELIVERED)
                .event(OrderEventsEnum.ARRIVE)
                .and()
                .withExternal().source(OrderStatusEnum.DELIVERED).target((OrderStatusEnum.COMPLETED))
                .event(OrderEventsEnum.CONFIRM)
                // 取消流转 自动校验订单归属
                .and()
                .withExternal().source(OrderStatusEnum.WAIT_ACCEPT).target(OrderStatusEnum.CANCELLED)
                .event(OrderEventsEnum.CANCEL)
                .guard(cancelGuard())
                .and()
                .withExternal().source(OrderStatusEnum.WAIT_PICKUP).target(OrderStatusEnum.CANCELLED)
                .event(OrderEventsEnum.CANCEL)
                .guard(cancelGuard())
                .and()
                .withExternal().source(OrderStatusEnum.DELIVERING).target(OrderStatusEnum.CANCELLED)
                .event(OrderEventsEnum.CANCEL)
                .guard(cancelGuard());
    }

    @Bean
    public Guard<OrderStatusEnum, OrderEventsEnum> cancelGuard() {
        return context -> {
            // 从消息头中取出业务参数
            String operatorId = (String) context.getMessageHeader("operatorId");
            String orderId = (String) context.getMessageHeader("orderId");

            // 查订单归属用户
            OrderMain order = Optional.ofNullable(orderMainMapper.selectById(orderId)).orElseThrow(() -> new ResourceNotFoundException("未找到此订单"));

            // 只有订单归属用户才能取消
            return order.getUserId().equals(Long.valueOf(operatorId));
        };
    }


}
