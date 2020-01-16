package com.anderscore.testcontainers.service;

import com.anderscore.testcontainers.config.TestConfig;
import com.anderscore.testcontainers.data.Scheduler;
import com.anderscore.testcontainers.extension.DbContainerExtension;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

//tag::SchedulerService[]
//tag::DatabaseExtensions[]
@SpringJUnitConfig(TestConfig.class)
@ActiveProfiles("test")
@ExtendWith(DbContainerExtension.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public class SchedulerServiceTest {
//end::DatabaseExtensions[]

    @Inject
    private SchedulerService schedulerService;

    @Test
    @FlywayTest(locationsForMigrate = {"db/scheduler_data"})
    public void findAll() {
        List<Scheduler> result = schedulerService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void store() {
        Scheduler scheduler = new Scheduler();
        scheduler.setName("ElBarto");

        schedulerService.store(scheduler);

        Optional<Scheduler> saveScheduler = schedulerService.findByName("ElBarto");

        assertTrue(saveScheduler.isPresent());
        assertEquals(saveScheduler.get().getName(), scheduler.getName());
    }
}

//end::SchedulerService[]