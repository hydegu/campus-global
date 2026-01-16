
package com.example.common.feign.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 启用Feign客户端注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.springframework.cloud.openfeign.EnableFeignClients
public @interface EnableFeignClients {

	/**
	 * {@link #basePackages()}属性的别名。允许更简洁的注解声明
	 * @return 'basePackages'数组
	 */
	String[] value() default {};

	/**
	 * 扫描注解组件的基础包路径
	 * <p>
	 * 与{@link #value()}互为别名且互斥
	 * <p>
	 * @return 基础包路径数组
	 */
	@AliasFor(annotation = org.springframework.cloud.openfeign.EnableFeignClients.class, attribute = "basePackages")
	String[] basePackages() default { "com.example" };

}
