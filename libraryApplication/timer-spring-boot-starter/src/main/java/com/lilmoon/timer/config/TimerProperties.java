package com.lilmoon.timer.config;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("logging.timer.level")
@Data
//@ConditionalOnProperty(prefix = "custom.logging.timer", name = "measure", havingValue = "enable")
public class TimerProperties {
    private Level logLevel = Level.WARN;
}
