package com.car.rental.validation;

import com.car.rental.dto.ModelYearDTO;
import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.dto.fipe.ModelDTO;
import com.car.rental.model.orm.ModelYear;
import com.car.rental.model.repository.ModelYearRepository;
import com.car.rental.service.AbstractMessage;
import com.car.rental.service.fipe.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ModelYearValidation extends AbstractMessage {

    @Autowired
    private ModelYearRepository modelYearRepository;
    @Autowired
    private FipeService fipeService;

    /**
     * Validates if model registration for the informed manufacturer exists.
     *
     * @param manufacturerDTO
     * @param modelDTO
     * @param modelYearDTO
     * @return {@link ModelYear}
     * @throws IOException
     */
    public ModelYear validateRegistration(ManufacturerDTO manufacturerDTO, ModelDTO modelDTO, ModelYearDTO modelYearDTO) throws IOException {
        ModelYear modelYear = this.modelYearRepository.findFirstByFipeIdAndNameAndYear(modelDTO.getId(), modelDTO.getName(), modelYearDTO.getName());

        if ( Objects.nonNull(modelYear) ) {
            return modelYear;
        }

        return this.validateFipe(manufacturerDTO, modelDTO, modelYearDTO);
    }

    /**
     * Validates if there is a model year registration in FIPE.
     *
     * @param manufacturerDTO
     * @param modelDTO
     * @param modelYearDTO
     * @return {@link ModelYear}
     * @throws IOException
     */
    private ModelYear validateFipe(ManufacturerDTO manufacturerDTO, ModelDTO modelDTO, ModelYearDTO modelYearDTO) throws IOException {
        ResponseEntity<List<ModelYearDTO>> entity = this.fipeService.modelsYear(manufacturerDTO.getId(), Integer.parseInt(modelDTO.getId()));

        if (Objects.isNull(entity) || entity.getBody().isEmpty()){
            throwsException("error.model.year.not.found");
        }

        ModelYearDTO yearDTO = entity.getBody().stream()
                                .filter(ma -> ma.getId().compareTo(modelYearDTO.getId()) == 0)
                                .findAny()
                                .orElse(null);

        return new ModelYear(modelDTO.getId(), modelDTO.getName(), yearDTO.getName());
    }

}
