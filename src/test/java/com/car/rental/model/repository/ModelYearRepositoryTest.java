package com.car.rental.model.repository;

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
public class ModelYearRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ModelYearRepository modelYearRepository;

    @Test
    public void whenFirstByFipeIdAndNameAndYear_thenReturnModelYear(){
        ModelYear modelYear = new ModelYear("3522", "Eclipse GT 3.8 V6 267cv", "2019 gasolina");
        this.entityManager.persistAndFlush(modelYear);

        ModelYear found = this.modelYearRepository.findFirstByFipeIdAndNameAndYear(modelYear.getFipeId(), modelYear.getName(), modelYear.getYear());
        assertThat(found.getFipeId()).isEqualTo(modelYear.getFipeId());
    }

}
