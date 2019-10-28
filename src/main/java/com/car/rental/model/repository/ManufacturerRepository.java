package com.car.rental.model.repository;

import com.car.rental.model.orm.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ManufacturerRepository
    extends JpaRepository<Manufacturer, Integer>, JpaSpecificationExecutor<Manufacturer> {
}
