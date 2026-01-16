
package com.example.common.security.annotation;

import com.example.common.security.component.ResourceServerAutoConfiguration;
import com.example.common.security.component.ResourceServerConfiguration;
import com.example.common.security.feign.FeignClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用资源服务器注解
 * <p>
 * 通过导入相关配置类启用资源服务器功能
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import({ ResourceServerAutoConfiguration.class, ResourceServerConfiguration.class,
		FeignClientConfiguration.class })
public @interface EnableResourceServer {

}
