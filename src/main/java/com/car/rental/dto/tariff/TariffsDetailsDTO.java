package com.car.rental.dto.tariff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Vehicle Rental Rate Details")
public class TariffsDetailsDTO {

    @ApiModelProperty(notes = "The tariffs id", position = 1)
    private Long id;

    @ApiModelProperty(notes = "The manufacturer", position = 2)
    private String automobileManufacturerName;

    @ApiModelProperty(notes = "The category", position = 3)
    private String automobileCategoryName;

    @ApiModelProperty(notes = "The model", position = 4)
    private String automobileModelYearName;

    @ApiModelProperty(notes = "The model year", position = 5)
    private String automobileModelYearYear;

    @ApiModelProperty(notes = "The picture url", position = 6)
    private String automobilePictureUrl;

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
    public String getAutomobileManufacturerName() {
        return automobileManufacturerName;
    }
    public void setAutomobileManufacturerName(String automobileManufacturerName) {
        this.automobileManufacturerName = automobileManufacturerName;
    }
    public String getAutomobileCategoryName() {
        return automobileCategoryName;
    }
    public void setAutomobileCategoryName(String automobileCategoryName) {
        this.automobileCategoryName = automobileCategoryName;
    }
    public String getAutomobileModelYearName() {
        return automobileModelYearName;
    }
    public void setAutomobileModelYearName(String automobileModelYearName) {
        this.automobileModelYearName = automobileModelYearName;
    }
    public String getAutomobileModelYearYear() {
        return automobileModelYearYear;
    }
    public void setAutomobileModelYearYear(String automobileModelYearYear) {
        this.automobileModelYearYear = automobileModelYearYear;
    }
    public String getAutomobilePictureUrl() {
        return automobilePictureUrl;
    }
    public void setAutomobilePictureUrl(String automobilePictureUrl) {
        this.automobilePictureUrl = automobilePictureUrl;
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
