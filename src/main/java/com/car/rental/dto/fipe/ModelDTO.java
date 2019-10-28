package com.car.rental.dto.fipe;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Vehicle model")
public class ModelDTO {

    @ApiModelProperty(notes = "The model id", position = 1)
    private String id;
    @ApiModelProperty(notes = "The model name", position = 2)
    private String name;

    public ModelDTO() {
    }

    public ModelDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
