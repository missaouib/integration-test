package com.anderscore.testcontainers.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {
    
	Optional<Scheduler> findByName(@Param(value = "name") String name);
}