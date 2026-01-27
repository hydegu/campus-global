package com.example.order.biz.service;

import java.math.BigDecimal;

public interface AmapService {

	BigDecimal getDistance(String origin, String destination);

	Integer getDuration(String origin, String destination);
}
