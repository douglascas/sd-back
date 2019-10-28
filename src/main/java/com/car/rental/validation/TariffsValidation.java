package com.car.rental.validation;

import com.car.rental.dto.tariff.TariffsDTO;
import com.car.rental.model.orm.Automobile;
import com.car.rental.model.orm.Tariffs;
import com.car.rental.model.repository.TariffsRepository;
import com.car.rental.service.AbstractMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class TariffsValidation extends AbstractMessage {

    @Autowired
    private TariffsRepository tariffsRepository;
    @Autowired
    private AutomobileValidation automobileValidation;


    /**
     * Valida se o registro pode ser criado.
     *
     * @param tariffsDTO
     * @return {@link Automobile}
     * @throws IOException
     */
    public Automobile validateCreate(TariffsDTO tariffsDTO) throws IOException {
        Automobile automobile = this.automobileValidation.validateRegistration(
                tariffsDTO.getManufacturer(), tariffsDTO.getModel(), tariffsDTO.getModelYear(), tariffsDTO.getCategory());

        if (Objects.nonNull(automobile) ) {
            this.validateExistence(automobile);
        }
        return automobile;
    }

    /**
     * Validates if there is a register equal to the one being created.
     * @param automobile
     * @throws IOException
     */
    public void validateExistence(Automobile automobile) throws IOException {
        Tariffs tariffs = this.tariffsRepository.findByAutomobileIdAndActivedTrue(automobile.getId());

        if ( Objects.nonNull(tariffs) ) {
            throwsException("error.tariffs.register.exists");
        }
    }

}
