package com.car.rental.model.repository;

import com.car.rental.model.orm.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AutomobileRepository extends JpaRepository<Automobile, Long>, JpaSpecificationExecutor<Automobile> {

    Automobile findFirstByManufacturerIdAndModelYearFipeIdAndModelYearYearAndCategoryId(Integer ManufacturerId,
            String modelYearFipeId, String ModelYearYear, Integer categoryId);
}
