package com.car.rental.restcontroller;

import com.car.rental.constants.AppConstants;
import com.car.rental.dto.tariff.TariffsDTO;
import com.car.rental.dto.tariff.TariffsDetailsDTO;
import com.car.rental.model.orm.Tariffs;
import com.car.rental.service.AbstractMessage;
import com.car.rental.service.automobile.TariffsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = AppConstants.PATH)
public class TariffsResource extends AbstractMessage {

    @Autowired
    private TariffsService tariffsService;


    @ApiOperation(value = "Create a tariffs", response = TariffsDetailsDTO.class,
            notes = "This operation creates a new tariffs.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Created"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "unexpected error")
    })
    @Transactional
    @RequestMapping(value = "/tariffs",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TariffsDetailsDTO> create(@Validated @RequestBody TariffsDTO dto) throws IOException {
        Tariffs tariffs = this.tariffsService.create(dto);

        return new ResponseEntity<>(mapper.map(tariffs, TariffsDetailsDTO.class), HttpStatus.CREATED);
    }

    @ApiOperation(value = "List the tariffs", response = TariffsDetailsDTO.class,
            notes = "This operation lists all tariffs.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "unexpected error")
    })
    @RequestMapping(value = "/tariffs",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TariffsDetailsDTO>> list(Integer categoryId, Pageable pageable) {
        Page<Tariffs> tariffs = this.tariffsService.list(categoryId, pageable);

        List<TariffsDetailsDTO> dtos = new ArrayList<>();
        dtos.addAll(
                tariffs.stream().map(tar -> {
                    return mapper.map(tar, TariffsDetailsDTO.class);
                }).collect(Collectors.toList())
        );

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ApiOperation(value = "List the tariffs", response = TariffsDetailsDTO.class,
            notes = "This operation lists all tariffs.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "unexpected error")
    })
    @RequestMapping(value = "/tariffs/{id}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TariffsDetailsDTO> findById(@PathVariable Long id) {
        Tariffs tariffs = this.tariffsService.findById(id);

        if ( Objects.isNull(tariffs) ) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(mapper.map(tariffs, TariffsDetailsDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "List the tariffs", response = TariffsDetailsDTO.class,
            notes = "This operation lists all tariffs.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted, record no longer exists"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "unexpected error")
    })
    @Transactional
    @RequestMapping(value = "/tariffs/{id}",
            method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws IOException {
        this.tariffsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
