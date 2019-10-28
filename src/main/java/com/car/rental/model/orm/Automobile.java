package com.car.rental.model.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "automobile")
public class Automobile implements Serializable {

    private static final long serialVersionUID = -2653461354450477519L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_year_id", nullable = false)
    private ModelYear modelYear;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "picture_url")
    private String pictureUrl;

    public Automobile() {
    }

    public Automobile(Manufacturer manufacturer, ModelYear modelYear, Category category) {
        this.manufacturer = manufacturer;
        this.modelYear = modelYear;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    public ModelYear getModelYear() {
        return modelYear;
    }
    public void setModelYear(ModelYear modelYear) {
        this.modelYear = modelYear;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
