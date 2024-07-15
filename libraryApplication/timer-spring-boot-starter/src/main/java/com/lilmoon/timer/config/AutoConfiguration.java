package com.lilmoon.timer.config;

import com.lilmoon.timer.aspect.TimerAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableConfigurationProperties(TimerProperties.class)
@ConditionalOnProperty(name = "logger.execution.timer.enabled",havingValue = "true",matchIfMissing = true)
public class AutoConfiguration {
    @Bean
    TimerAspect timerAspect(TimerProperties properties){
        return new TimerAspect(properties);
    }
}
