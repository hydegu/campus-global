
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
		urls.addAll(Arrays.asList(DEFAULT_IGNORE_URLS));
		RequestMappingHandlerMapping mapping = SpringUtil.getBean("requestMappingHandlerMapping");
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

		map.keySet().forEach(info -> {
			HandlerMethod handlerMethod = map.get(info);

		});
	}
}