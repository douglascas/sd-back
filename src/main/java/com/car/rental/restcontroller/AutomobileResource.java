package com.car.rental.restcontroller;

import com.car.rental.constants.AppConstants;
import com.car.rental.dto.CategoryDTO;
import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.dto.fipe.ModelDTO;
import com.car.rental.dto.ModelYearDTO;
import com.car.rental.model.orm.Category;
import com.car.rental.service.automobile.CategoryService;
import com.car.rental.service.fipe.FipeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = AppConstants.PATH)
public class AutomobileResource {

        @Autowired
        private ModelMapper mapper;
        @Autowired
        private FipeService fipeService;
        @Autowired
        private CategoryService categoryService;

        @ApiOperation(value = "List the manufacturers", response = ManufacturerDTO.class, notes = "This operation lists all manufactures.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                        @ApiResponse(code = 500, message = "unexpected error") })
        @RequestMapping(value = "/manufacturers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ManufacturerDTO>> manufacturers() throws IOException {
                ResponseEntity<List<ManufacturerDTO>> entity = this.fipeService.manufactures();

                if (Objects.nonNull(entity) && entity.hasBody()) {
                        List<ManufacturerDTO> manufactures = entity.getBody();
                        return new ResponseEntity<>(manufactures, HttpStatus.OK);
                }

                return new ResponseEntity<>(HttpStatus.OK);
        }

        @ApiOperation(value = "List the models", response = ModelDTO.class, notes = "This operation lists all models of the informed manufacturer.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                        @ApiResponse(code = 500, message = "unexpected error") })
        @RequestMapping(value = "/manufacturers/{id}/models", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ModelDTO>> models(@PathVariable Integer id) throws IOException {
                ResponseEntity<List<ModelDTO>> entity = this.fipeService.models(id);

                if (Objects.nonNull(entity) && entity.hasBody()) {
                        List<ModelDTO> models = entity.getBody();
                        return new ResponseEntity<>(models, HttpStatus.OK);
                }

                return new ResponseEntity<>(HttpStatus.OK);
        }

        @ApiOperation(value = "List the years", response = ModelDTO.class, notes = "This operation lists each year of the informed model and manufacturer.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                        @ApiResponse(code = 500, message = "unexpected error") })
        @RequestMapping(value = "/manufacturers/{manufactureId}/models/{modelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ModelYearDTO>> modelYear(@PathVariable Integer manufactureId,
                        @PathVariable Integer modelId) throws IOException {
                ResponseEntity<List<ModelYearDTO>> entity = this.fipeService.modelsYear(manufactureId, modelId);

                if (Objects.nonNull(entity) && entity.hasBody()) {
                        List<ModelYearDTO> models = entity.getBody();
                        return new ResponseEntity<>(models, HttpStatus.OK);
                }

                return new ResponseEntity<>(HttpStatus.OK);
        }

        @ApiOperation(value = "List the categories", response = CategoryDTO.class, notes = "This operation lists all categories.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                        @ApiResponse(code = 500, message = "unexpected error") })
        @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<CategoryDTO>> list() throws IOException {
                List<Category> categories = this.categoryService.listAll();

                if (Objects.isNull(categories) || categories.isEmpty()) {
                        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
                }

                List<CategoryDTO> dtos = categories.stream().map(cat -> {
                        return mapper.map(cat, CategoryDTO.class);
                }).collect(Collectors.toList());

                return new ResponseEntity<>(dtos, HttpStatus.OK);
        }

        @ApiOperation(value = "Search category by id", response = CategoryDTO.class, notes = "This operation search category by id.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
                        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                        @ApiResponse(code = 500, message = "unexpected error") })
        @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id) throws IOException {
                Optional<Category> category = this.categoryService.findById(id);

                if (category.isPresent()) {
                        return new ResponseEntity<>(mapper.map(category.get(), CategoryDTO.class), HttpStatus.OK);
                }

                return new ResponseEntity<>(HttpStatus.OK);
        }

}
