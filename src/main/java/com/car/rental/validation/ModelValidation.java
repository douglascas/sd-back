package com.car.rental.validation;

import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.dto.fipe.ModelDTO;
import com.car.rental.model.orm.ModelYear;
import com.car.rental.service.AbstractMessage;
import com.car.rental.service.fipe.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ModelValidation extends AbstractMessage {

    @Autowired
    private FipeService fipeService;

    /**
     * Validates if model registration for the informed manufacturer exists.
     *
     * @param manufacturerDTO
     * @param modelDTO
     * @return {@link ModelYear}
     * @throws IOException
     */
    public ModelYear validateRegistration(ManufacturerDTO manufacturerDTO, ModelDTO modelDTO) throws IOException {
        ResponseEntity<List<ModelDTO>> entity = this.fipeService.models(manufacturerDTO.getId());

        if (Objects.isNull(entity.getBody()) || entity.getBody().isEmpty()){
            throwsException("error.manufacturer.model.not.found");
        }

        ModelDTO model = entity.getBody().stream()
                                .filter(ma -> ma.getId().compareTo(modelDTO.getId()) == 0)
                                .findAny()
                                .orElse(null);

        if ( Objects.isNull(model) ){
            throwsException("error.manufacturer.model.not.found");
        }

        return new ModelYear(model.getId(), model.getName());
    }

}
