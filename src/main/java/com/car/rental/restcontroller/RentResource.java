package com.car.rental.restcontroller;

import com.car.rental.constants.AppConstants;
import com.car.rental.dto.rent.CheaperRentDTO;
import com.car.rental.dto.rent.CheaperRentFilterDTO;
import com.car.rental.service.rent.CheaperRentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = AppConstants.PATH)
public class RentResource {

    @Autowired
    private CheaperRentService cheaperRentService;


    @ApiOperation(value = "Find the car with the cheapest rent", response = CheaperRentDTO.class,
            notes = "This operation find the car with the cheapest rent.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "unexpected error")
    })
    @RequestMapping(value = "/cheaper-rent",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CheaperRentDTO> cheaperRent(@Validated CheaperRentFilterDTO filter) throws IOException {
        return new ResponseEntity<>(this.cheaperRentService.cheaperRent(filter), HttpStatus.OK);
    }

}
