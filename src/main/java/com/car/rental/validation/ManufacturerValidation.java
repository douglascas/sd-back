package com.car.rental.validation;

import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.model.orm.Manufacturer;
import com.car.rental.model.repository.ManufacturerRepository;
import com.car.rental.service.AbstractMessage;
import com.car.rental.service.fipe.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManufacturerValidation extends AbstractMessage {

    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private FipeService fipeService;

    /**
     * Validates if the manufacturer's record exists.
     *
     * @param dto
     * @return {@link Manufacturer
     * @throws IOException
     */
    public Manufacturer validateRegistration(ManufacturerDTO dto) throws IOException {
        Optional<Manufacturer> manufacturer = this.manufacturerRepository.findById(dto.getId());

        if ( manufacturer.isPresent() ){
            return manufacturer.get();
        }

        return this.validateFipe(dto);
    }

    /**
     * Validates if there is a manufacturer registration in FIPE.
     *
     * @param dto
     * @return {@link Manufacturer}
     * @throws IOException
     */
    private Manufacturer validateFipe(ManufacturerDTO dto) throws IOException {
        ResponseEntity<List<ManufacturerDTO>> entity = this.fipeService.manufactures();

        if ( Objects.isNull(entity.getBody()) || entity.getBody().isEmpty() ){
            throwsException("error.manufacturer.not.found");
        }

        ManufacturerDTO manufacturerDTO = entity.getBody().stream()
                                .filter(ma -> ma.getId().compareTo(dto.getId()) == 0)
                                .findAny()
                                .orElse(null);

        if ( Objects.isNull(manufacturerDTO) ){
            throwsException("error.manufacturer.not.found");
        }

        return mapper.map(manufacturerDTO, Manufacturer.class);
    }

}
