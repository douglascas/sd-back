package com.car.rental.validation;

import com.car.rental.dto.CategoryDTO;
import com.car.rental.dto.ModelYearDTO;
import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.dto.fipe.ModelDTO;
import com.car.rental.model.orm.Automobile;
import com.car.rental.model.repository.AutomobileRepository;
import com.car.rental.service.AbstractMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class AutomobileValidation extends AbstractMessage {

    @Autowired
    private AutomobileRepository automobileRepository;
    @Autowired
    private ManufacturerValidation manufacturerValidation;
    @Autowired
    private ModelValidation modelValidation;
    @Autowired
    private ModelYearValidation modelYearValidation;

    /**
     * Valid if the car registration exists, if it exists returns.
     * @param manufacturer
     * @param model
     * @param modelYear
     * @param category
     * @return {@link Automobile}
     * @throws IOException
     */
    public Automobile validateRegistration(ManufacturerDTO manufacturer, ModelDTO model, ModelYearDTO modelYear, CategoryDTO category) throws IOException {
        Automobile automobile = this.automobileRepository.findFirstByManufacturerIdAndModelYearFipeIdAndModelYearYearAndCategoryId(
                manufacturer.getId(), model.getId(), modelYear.getName(), category.getId());

        if (Objects.nonNull(automobile)) {
            return automobile;
        }

        this.manufacturerValidation.validateRegistration(manufacturer);
        this.modelValidation.validateRegistration(manufacturer, model);
        this.modelYearValidation.validateRegistration(manufacturer, model, modelYear);

        return null;
    }

}
