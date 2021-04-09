package com.obieliakov.tasksmanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tasksmanager")
@Data
public class TaskManagerProperties {
    private String apiUrl;
}
