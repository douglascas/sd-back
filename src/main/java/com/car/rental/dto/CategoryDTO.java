package com.car.rental.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Vehicle category")
public class CategoryDTO {

    @ApiModelProperty(notes = "The category id", position = 1)
    private Integer id;
    @ApiModelProperty(notes = "The category name", position = 2)
    private String name;

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
