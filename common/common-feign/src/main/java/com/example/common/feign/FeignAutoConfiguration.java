
package com.example.common.feign;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.example.common.feign.core.FeignInnerRequestInterceptor;
import com.example.common.feign.core.FeignRequestCloseInterceptor;
import com.example.common.feign.sentinel.ext.SentinelFeign;
import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Sentinel Feign 自动配置类
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class FeignAutoConfiguration {

	/**
	 * 创建Feign.Builder实例，支持Sentinel功能
	 * @return Feign.Builder实例
	 * @ConditionalOnMissingBean 当容器中不存在该类型bean时创建
	 * @ConditionalOnProperty 当配置feign.sentinel.enabled为true时生效
	 * @Scope 指定bean作用域为prototype
	 */
	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "feign.sentinel.enabled")
	public Feign.Builder feignSentinelBuilder() {
		return SentinelFeign.builder();
	}

	/**
	 * 创建并返回FeignRequestCloseInterceptor实例
	 * @return FeignRequestCloseInterceptor实例
	 */
	@Bean
	public FeignRequestCloseInterceptor FeignRequestCloseInterceptor() {
		return new FeignRequestCloseInterceptor();
	}

	/**
	 * 创建并返回FeignInnerRequestInterceptor实例
	 * @return FeignInnerRequestInterceptor 内部请求拦截器实例
	 */
	@Bean
	public FeignInnerRequestInterceptor FeignInnerRequestInterceptor() {
		return new FeignInnerRequestInterceptor();
	}

}
