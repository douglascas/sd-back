package com.car.rental.model.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "model_year")
public class ModelYear implements Serializable {

    private static final long serialVersionUID = 6297045868979716033L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fipe_id", length = 50, nullable = false)
    private String fipeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year", length = 100, nullable = false)
    private String year;

    public ModelYear() {
    }

    public ModelYear(String fipeId, String name, String year) {
        this.fipeId = fipeId;
        this.name = name;
        this.year = year;
    }

    public ModelYear(String fipeId, String name) {
        this.fipeId = fipeId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFipeId() {
        return fipeId;
    }
    public void setFipeId(String fipeId) {
        this.fipeId = fipeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelYear modelYear = (ModelYear) o;
        return Objects.equals(id, modelYear.id) &&
                Objects.equals(name, modelYear.name) &&
                Objects.equals(year, modelYear.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year);
    }
}
