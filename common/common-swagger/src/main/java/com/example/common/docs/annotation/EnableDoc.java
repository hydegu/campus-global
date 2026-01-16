
package com.example.common.docs.annotation;

import com.example.common.core.factory.YamlPropertySourceFactory;
import com.example.common.docs.config.OpenAPIDefinitionImportSelector;
import com.example.common.docs.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.*;

/**
 * Spring文档支持
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import(OpenAPIDefinitionImportSelector.class)
@PropertySource(value = "classpath:openapi-config.yaml", factory = YamlPropertySourceFactory.class)
public @interface EnableDoc {

	/**
	 * 网关路由前缀
	 * @return String
	 */
	String value();

}
