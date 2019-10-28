package com.car.rental.service.automobile;

import com.car.rental.dto.tariff.TariffsDTO;
import com.car.rental.model.orm.*;
import com.car.rental.model.repository.AutomobileRepository;
import com.car.rental.model.repository.ModelYearRepository;
import com.car.rental.model.repository.TariffsRepository;
import com.car.rental.service.AbstractMessage;
import com.car.rental.validation.AutomobileValidation;
import com.car.rental.validation.ManufacturerValidation;
import com.car.rental.validation.ModelValidation;
import com.car.rental.validation.TariffsValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.io.IOException;
import java.util.*;

@Service
public class TariffsService extends AbstractMessage {

    @Autowired
    private TariffsRepository tariffsRepository;
    @Autowired
    private AutomobileRepository automobileRepository;
    @Autowired
    private TariffsValidation tariffsValidation;
    @Autowired
    private AutomobileValidation automobileValidation;
    @Autowired
    private ManufacturerValidation manufacturerValidation;
    @Autowired
    private ModelValidation modelValidation;
    @Autowired
    private ModelYearRepository modelYearRepository;

    /**
     * Creates a new tariff record for a vehicle.
     *
     * @param dto
     * @return {@link Tariffs}
     * @throws IOException
     */
    @Transient
    public Tariffs create(TariffsDTO dto) throws IOException {
        Automobile automobile = this.tariffsValidation.validateCreate(dto);

        Tariffs tariffs = mapper.map(dto, Tariffs.class);
        if ( Objects.isNull(automobile) ) {
            tariffs.setAutomobile( new Automobile() );

            this.checkAndSet(dto, tariffs);

            tariffs.getAutomobile().setCategory( mapper.map(dto.getCategory(), Category.class) );
            tariffs.getAutomobile().getModelYear().setYear( dto.getModelYear().getName() );

            this.modelYearRepository.save( tariffs.getAutomobile().getModelYear() );
            this.automobileRepository.save( tariffs.getAutomobile() );
        } else {
            tariffs.setAutomobile( automobile );
        }

        return this.tariffsRepository.save( tariffs );
    }

    /**
     * Validates the information and set.
     * @param dto
     * @param tariffs
     * @throws IOException
     */
    private void checkAndSet(TariffsDTO dto, Tariffs tariffs) throws IOException {
        Manufacturer manufacturer = this.manufacturerValidation.validateRegistration(dto.getManufacturer());
        ModelYear modelYear = this.modelValidation.validateRegistration(dto.getManufacturer(), dto.getModel());

        tariffs.getAutomobile().setManufacturer( Objects.isNull(manufacturer) ? mapper.map(dto.getManufacturer(), Manufacturer.class) : manufacturer );
        tariffs.getAutomobile().setModelYear( Objects.isNull(modelYear) ? new ModelYear(dto.getModel().getId(), dto.getModel().getName(), dto.getModelYear().getName()) : modelYear );
    }

    /**
     * Search tariffs.
     *
     * @param categoryId
     * @param pageable
     * @return array of {@link Tariffs}, or empty list
     */
    @Transient
    public Page<Tariffs> list(Integer categoryId, Pageable pageable){
        if ( Objects.isNull(categoryId) ) {
            return this.tariffsRepository.findByActivedTrue(pageable);
        }
        return this.tariffsRepository.findByActivedTrueAndAutomobileCategoryId(categoryId, pageable);
    }

    /**
     * Search by id.
     * @param id
     * @return {@link Tariffs}
     */
    @Transient
    public Tariffs findById(Long id){
        return this.tariffsRepository.findByActivedTrueAndId(id);
    }


    /**
     * Logically deletes tariffs.
     * @param id
     * @throws IOException
     */
    @Transient
    public void delete(Long id) throws IOException {
        Optional<Tariffs> tariffs = this.tariffsRepository.findById(id);

        if ( Objects.isNull(tariffs.get()) ) {
            throwsException("error.tariffs.not.exists");
        }

        tariffs.get().setActived(Boolean.FALSE);
        this.tariffsRepository.save(tariffs.get());
    }

}