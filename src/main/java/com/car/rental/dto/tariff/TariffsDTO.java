package com.car.rental.dto.tariff;

import com.car.rental.dto.CategoryDTO;
import com.car.rental.dto.ModelYearDTO;
import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.dto.fipe.ModelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Vehicle Rental Fee")
public class TariffsDTO {

    @ApiModelProperty(notes = "The tariffs id", position = 1)
    private Long id;

    @ApiModelProperty(notes = "The manufacturer", position = 2)
    private ManufacturerDTO manufacturer;

    @ApiModelProperty(notes = "The category", position = 3)
    private CategoryDTO category;

    @ApiModelProperty(notes = "The model", position = 4)
    private ModelDTO model;

    @ApiModelProperty(notes = "The model year", position = 5)
    private ModelYearDTO modelYear;

    @ApiModelProperty(notes = "The image base64", position = 6)
    private String imageBase64;

    @ApiModelProperty(notes = "Rental amount weekday", position = 7)
    private BigDecimal Weekday;

    @ApiModelProperty(notes = "Rental amount weekend day", position = 8)
    private BigDecimal WeekendDay;

    @ApiModelProperty(notes = "Rental amount weekday loyalty", position = 9)
    private BigDecimal WeekdayLoyalty;

    @ApiModelProperty(notes = "Rental amount weekend day loyalty", position = 10)
    private BigDecimal WeekendDayLoyalty;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ManufacturerDTO getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(ManufacturerDTO manufacturer) {
        this.manufacturer = manufacturer;
    }
    public CategoryDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    public ModelDTO getModel() {
        return model;
    }
    public void setModel(ModelDTO model) {
        this.model = model;
    }
    public ModelYearDTO getModelYear() {
        return modelYear;
    }
    public void setModelYear(ModelYearDTO modelYear) {
        this.modelYear = modelYear;
    }
    public String getImageBase64() {
        return imageBase64;
    }
    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
    public BigDecimal getWeekday() {
        return Weekday;
    }
    public void setWeekday(BigDecimal weekday) {
        Weekday = weekday;
    }
    public BigDecimal getWeekendDay() {
        return WeekendDay;
    }
    public void setWeekendDay(BigDecimal weekendDay) {
        WeekendDay = weekendDay;
    }
    public BigDecimal getWeekdayLoyalty() {
        return WeekdayLoyalty;
    }
    public void setWeekdayLoyalty(BigDecimal weekdayLoyalty) {
        WeekdayLoyalty = weekdayLoyalty;
    }
    public BigDecimal getWeekendDayLoyalty() {
        return WeekendDayLoyalty;
    }
    public void setWeekendDayLoyalty(BigDecimal weekendDayLoyalty) {
        WeekendDayLoyalty = weekendDayLoyalty;
    }
}
