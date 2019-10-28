package com.car.rental.dto.rent;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel(description = "Cheaper rent filter")
public class CheaperRentFilterDTO {

    @ApiModelProperty(value = "Start date. Pattern used: yyyy-MM-dd, month, day, year.", required = true, example = "2019-06-20", position = 1)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "error.tariffs.cheaper.rent.start.date.not.null")
    private LocalDate startDate;

    @ApiModelProperty(value = "End date. Pattern used: yyyy-MM-dd, month, day, year.", required = true, example = "2019-06-21", position = 2)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "error.tariffs.cheaper.rent.end.date.not.null")
    private LocalDate endDate;

    @ApiModelProperty(value = "Participate in the loyalty program", example = "true", required = true, position = 3)
    @NotNull(message = "error.tariffs.cheaper.rent.loyalty.program.not.null")
    private Boolean loyaltyProgram;

    @ApiModelProperty(value = "E-mail to receive cheap rent information", example = "email@email.com", position = 4)
    private String email;

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Boolean getLoyaltyProgram() {
        return loyaltyProgram;
    }
    public void setLoyaltyProgram(Boolean loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
