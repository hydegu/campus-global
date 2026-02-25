package com.example.order.biz.processor;

import com.example.order.api.entity.OrderMain;
import com.example.order.api.enums.OrderEventsEnum;
import com.example.order.api.enums.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderProcessor {

    private final StateMachine<OrderStatusEnum, OrderEventsEnum> orderStateMachine;

    public boolean process(OrderMain order,OrderEventsEnum event){
        Message<OrderEventsEnum> message = MessageBuilder.withPayload(event)
                .setHeader("order",order).build();
        boolean b = sendEvent(message);
        return b;
    }

    @SneakyThrows
    private boolean sendEvent(Message<OrderEventsEnum> message){
        List<StateMachineEventResult<OrderStatusEnum, OrderEventsEnum>> results =
                orderStateMachine
                        .sendEventCollect(Mono.just(message))
                        .block(); // 同步等待结果（非响应式项目用 block()）
        return results != null && results.stream()
                .anyMatch(r -> r.getResultType() == StateMachineEventResult.ResultType.ACCEPTED);
    }
}
