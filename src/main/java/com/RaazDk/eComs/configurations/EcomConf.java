package com.RaazDk.eComs.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EcomConf  implements WebMvcConfigurer {
    @Value("${file.upload-dir}")
    private String resourceLocation;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:E:/eclipse-workspace/eComs/src/main/resources/static/uploads/");
    }
}
