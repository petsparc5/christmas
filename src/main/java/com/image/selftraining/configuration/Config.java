package com.image.selftraining.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.image.selftraining.App;

@ComponentScan(basePackageClasses=App.class)
@Configuration
public class Config {
}
