package com.car.rental.dto.fipe;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Vehicle manufacturer")
public class ManufacturerDTO {

    @ApiModelProperty(notes = "The manufacturer id", position = 1)
    private Integer id;
    @ApiModelProperty(notes = "The manufacturer name", position = 2)
    private String name;

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
