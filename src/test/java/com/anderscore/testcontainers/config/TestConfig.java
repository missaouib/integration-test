package com.anderscore.testcontainers.config;

import com.anderscore.testcontainers.data.SchedulerRepository;
import com.anderscore.testcontainers.frontend.mapping.SchedulerMapper;
import com.anderscore.testcontainers.service.SchedulerService;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//tag::TestConfig[]
@Configuration
@Profile("test")
@Import(TestPersistenceConfig.class)
@ComponentScan(basePackageClasses = {SchedulerMapper.class, SchedulerService.class})
@EnableJpaRepositories(basePackageClasses = SchedulerRepository.class)
public class TestConfig {
}
//tag::TestConfig[]