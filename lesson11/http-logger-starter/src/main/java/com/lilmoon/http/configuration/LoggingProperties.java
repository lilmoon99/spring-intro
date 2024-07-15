package com.lilmoon.http.configuration;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("http.logging")
@Data
public class LoggingProperties {

    /**
     * Вкл/Выкл логирование тела запроса
     */
    private boolean logBody = false;

    private Level logLevel = Level.DEBUG;
}
