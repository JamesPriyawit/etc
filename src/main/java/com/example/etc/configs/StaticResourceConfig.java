package com.example.etc.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Created by nitiphon on 7/13/2020 AD.
 */

@Configuration
public class StaticResourceConfig extends WebMvcConfigurerAdapter {

    @Value("${file.outputPath}")
    private String fileOutputPath;
    @Value("${file.outputUrl}")
    private String fileOutputUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Uploaded images
        registry.addResourceHandler(this.fileOutputUrl + "/**")
                .addResourceLocations("file:" + this.fileOutputPath + "/")
                .resourceChain(true)
                .addResolver(new GzipResourceResolver())
                .addResolver(new PathResourceResolver());
    }

}
