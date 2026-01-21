
package com.example.common.security.component;

import cn.hutool.extra.spring.SpringUtil;
import com.example.common.security.annotation.Inner;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 资源服务器对外直接暴露URL配置类
 * <p>
 * 用于配置不需要认证即可访问的URL路径，支持路径变量替换
 */
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class PermitAllUrlProperties implements InitializingBean {

	private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

	private static final String[] DEFAULT_IGNORE_URLS = new String[]{"/actuator/**", "/error", "/v3/api-docs"};

	@Getter
	@Setter
	private List<String> urls = new ArrayList<>();

	/**
	 * 初始化方法，在属性设置完成后执行 收集带有@Inner注解的Controller方法路径，并将路径中的变量替换为*
	 */
	@Override
	public void afterPropertiesSet() {
		log.info("=== PermitAllUrlProperties 初始化开始 ===");
		urls.addAll(Arrays.asList(DEFAULT_IGNORE_URLS));
		log.info("默认URL列表: {}", urls);
		
		RequestMappingHandlerMapping mapping = SpringUtil.getBean("requestMappingHandlerMapping");
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
		log.info("找到的HandlerMethod数量: {}", map.size());
		
		log.info("开始收集带有@Inner注解的Controller方法路径...");
		
		map.keySet().forEach(info -> {
			HandlerMethod handlerMethod = map.get(info);
			Inner inner = handlerMethod.getMethodAnnotation(Inner.class);
			if (inner == null) {
				inner = handlerMethod.getBeanType().getAnnotation(Inner.class);
			}
			if (inner != null) {
				log.info("发现带有@Inner注解的方法: {} - {}", 
					handlerMethod.getBeanType().getSimpleName(), 
					handlerMethod.getMethod().getName());
				
				try {
					if (info.getPathPatternsCondition() != null) {
						log.info("PathPatternsCondition类型: {}", info.getPathPatternsCondition().getClass().getName());
						log.info("PathPatternsCondition可用方法: {}", 
							Arrays.stream(info.getPathPatternsCondition().getClass().getMethods())
								.map(m -> m.getName())
								.sorted()
								.toList());
						
						info.getPathPatternsCondition().getPatterns().forEach(pathPattern -> {
							String url = pathPattern.getPatternString();
							String processedUrl = PATTERN.matcher(url).replaceAll("*");
							log.info("添加路径到允许列表: {} -> {}", url, processedUrl);
							urls.add(processedUrl);
						});
					} else {
						log.warn("PathPatternsCondition为null，尝试使用PatternsCondition");
						if (info.getPatternsCondition() != null) {
							info.getPatternsCondition().getPatterns().forEach(url -> {
								String processedUrl = PATTERN.matcher(url).replaceAll("*");
								log.info("添加路径到允许列表(Spring Boot 2.x API): {} -> {}", url, processedUrl);
								urls.add(processedUrl);
							});
						}
					}
				} catch (Exception e) {
					log.error("使用Spring Boot 3.x API失败: {}", e.getMessage(), e);
					try {
						if (info.getPatternsCondition() != null) {
							info.getPatternsCondition().getPatterns().forEach(url -> {
								String processedUrl = PATTERN.matcher(url).replaceAll("*");
								log.info("添加路径到允许列表(Spring Boot 2.x API): {} -> {}", url, processedUrl);
								urls.add(processedUrl);
							});
						}
					} catch (Exception e2) {
						log.error("使用Spring Boot 2.x API也失败: {}", e2.getMessage(), e2);
					}
				}
			}
		});
		
		log.info("=== 最终允许无认证访问的URL列表: {} ===", urls);
	}
}