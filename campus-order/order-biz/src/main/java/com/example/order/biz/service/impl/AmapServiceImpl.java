package com.example.order.biz.service.impl;

import com.example.common.core.exception.BusinessException;
import com.example.order.biz.service.AmapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmapServiceImpl implements AmapService {

	@Value("${amap.api.key:}")
	private String amapApiKey;

	private final RestTemplate restTemplate;

	@Override
	public BigDecimal getDistance(String origin, String destination) {
		if (origin == null || origin.isEmpty() || destination == null || destination.isEmpty()) {
			throw new BusinessException("INVALID_PARAM", "起点和终点不能为空");
		}

		String url = buildDistanceUrl(origin, destination);

		try {
			String response = restTemplate.getForObject(url, String.class);
			return parseDistanceFromResponse(response);
		} catch (Exception e) {
			log.error("调用高德地图API失败，origin：{}，destination：{}", origin, destination, e);
			throw new BusinessException("API_CALL_FAILED", "获取距离失败");
		}
	}

	@Override
	public Integer getDuration(String origin, String destination) {
		if (origin == null || origin.isEmpty() || destination == null || destination.isEmpty()) {
			throw new BusinessException("INVALID_PARAM", "起点和终点不能为空");
		}

		String url = buildDistanceUrl(origin, destination);

		try {
			String response = restTemplate.getForObject(url, String.class);
			return parseDurationFromResponse(response);
		} catch (Exception e) {
			log.error("调用高德地图API失败，origin：{}，destination：{}", origin, destination, e);
			throw new BusinessException("API_CALL_FAILED", "获取预计时间失败");
		}
	}

	private String buildDistanceUrl(String origin, String destination) {
		return String.format("https://restapi.amap.com/v3/distance?origins=%s&destination=%s&type=1&output=json&key=%s",
				origin, destination, amapApiKey);
	}

	private BigDecimal parseDistanceFromResponse(String response) {
		if (response == null || response.isEmpty()) {
			return null;
		}

		try {
			int distanceIndex = response.indexOf("\"distance\"");
			if (distanceIndex == -1) {
				return null;
			}

			int colonIndex = response.indexOf(":", distanceIndex);
			int endIndex = response.indexOf(",", colonIndex);

			String distanceStr = response.substring(colonIndex + 1, endIndex).trim();
			int distanceMeters = Integer.parseInt(distanceStr);

			return new BigDecimal(distanceMeters).divide(new BigDecimal("1000"), 2, java.math.RoundingMode.HALF_UP);
		} catch (Exception e) {
			log.error("解析距离失败，response：{}", response, e);
			return null;
		}
	}

	private Integer parseDurationFromResponse(String response) {
		if (response == null || response.isEmpty()) {
			return null;
		}

		try {
			int durationIndex = response.indexOf("\"duration\"");
			if (durationIndex == -1) {
				return null;
			}

			int colonIndex = response.indexOf(":", durationIndex);
			int endIndex = response.indexOf(",", colonIndex);

			String durationStr = response.substring(colonIndex + 1, endIndex).trim();
			int durationSeconds = Integer.parseInt(durationStr);

			return durationSeconds / 60;
		} catch (Exception e) {
			log.error("解析时间失败，response：{}", response, e);
			return null;
		}
	}
}
