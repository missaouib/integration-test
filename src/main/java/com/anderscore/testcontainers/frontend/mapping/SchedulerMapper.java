package com.anderscore.testcontainers.frontend.mapping;

import com.anderscore.testcontainers.data.Scheduler;

public interface SchedulerMapper {
	
	SchedulerUi asUi(Scheduler scheduler);
	
	Scheduler asEntity(SchedulerUi schedulerUi);
}