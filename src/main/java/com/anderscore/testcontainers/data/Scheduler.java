package com.anderscore.testcontainers.data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="scheduler")
public class Scheduler implements Serializable, Comparable<Scheduler>{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheduler scheduler = (Scheduler) o;
        return id.equals(scheduler.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
	@Override
	public int compareTo(Scheduler other) {
		return id.compareTo(other.id);
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