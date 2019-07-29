package com.delicacy.durian.idworker.autoconfig;


import com.delicacy.durian.idworker.idworker.IdWorker;
import com.delicacy.durian.idworker.utils.IdWorkerUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "idworker", name = {"workerId"})
@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerAutoConfiguration {
    @Autowired
    private IdWorkerProperties idWorkerProperties;

    @Bean
    IdWorker idWorker() {
        IdWorker idWorker = new IdWorker(idWorkerProperties.getWorkerId());
        IdWorkerUtil.setIdWorker(idWorker);
        return idWorker;
    }
}

@ConfigurationProperties(prefix = "idworker")
@Getter
@Setter
class IdWorkerProperties {
    private int workerId;
}