package com.anderscore.testcontainers.frontend.mapping;

import java.io.Serializable;
import java.util.Objects;

public class SchedulerUi implements Serializable{
	private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
    
    public boolean hasId() {
    	return id != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulerUi scheduler = (SchedulerUi) o;
        return id.equals(scheduler.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}