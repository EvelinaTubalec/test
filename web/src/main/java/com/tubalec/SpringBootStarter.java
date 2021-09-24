package com.tubalec;

import com.tubalec.beans.ApplicationBeans;
import com.tubalec.beans.PersistenceBeanConfiguration;
import com.tubalec.beans.SecurityConfig;
import com.tubalec.beans.SwaggerConfig;
import com.tubalec.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.tubalec")
@EnableCaching
@EnableSwagger2
@Import({
        ApplicationBeans.class,
        SwaggerConfig.class,
        PersistenceBeanConfiguration.class,
        WebSecurityConfiguration.class,
        SecurityConfig.class})
public class SpringBootStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
