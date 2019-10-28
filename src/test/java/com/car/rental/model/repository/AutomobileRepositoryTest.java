package com.car.rental.model.repository;

import com.car.rental.model.orm.Automobile;
import com.car.rental.model.orm.Category;
import com.car.rental.model.orm.Manufacturer;
import com.car.rental.model.orm.ModelYear;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AutomobileRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AutomobileRepository automobileRepository;

    @Test
    public void whenFindFirstByManufacturerIdAndModelYearFipeIdAndModelYearYearAndCategoryId_thenReturnAutomobile(){
        ModelYear modelYear = new ModelYear("3522", "Eclipse GT 3.8 V6 267cv", "2019 gasolina");
        Category category = new Category(2, "Medium Hatch");
        Manufacturer manufacturer = new Manufacturer(31, "KIA");

        Automobile automobile = new Automobile(manufacturer, modelYear, category);
        this.entityManager.persistAndFlush(automobile);

        Automobile found = this.automobileRepository.findFirstByManufacturerIdAndModelYearFipeIdAndModelYearYearAndCategoryId(
                manufacturer.getId(), modelYear.getFipeId(), modelYear.getYear(), category.getId() );

        assertThat(found.getCategory().getId()).isEqualTo(category.getId());
        assertThat(found.getManufacturer().getId()).isEqualTo(manufacturer.getId());
        assertThat(found.getModelYear().getFipeId()).isEqualTo(modelYear.getFipeId());
    }
}
