package com.web.batch.config;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

import static org.springframework.aot.hint.predicate.RuntimeHintsPredicates.resource;

public class GlobalConfig {

    private String schedulerCronExample1;

    private boolean local;
    private boolean dev;
    private boolean prod;

    @PostConstruct
    public void init() {
//        Resource resource = resourceLoader.getResource(re)
//        Properties properties = PropertiesLoaderUtils.LoadProperties(resource);
//        this.schedulerCronExample1 =
    }
}
