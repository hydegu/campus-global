package com.example.order.biz.service;

import com.example.order.api.dto.DeliveryCalculateDTO;
import com.example.order.api.vo.DeliveryCalculateVO;

public interface DeliveryFeeService {

	DeliveryCalculateVO calculateDeliveryFee(DeliveryCalculateDTO calculateDTO);
}
