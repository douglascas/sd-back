package com.car.rental.model.repository;

import com.car.rental.model.orm.ModelYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModelYearRepository extends JpaRepository<ModelYear, Long>, JpaSpecificationExecutor<ModelYear> {

    ModelYear findFirstByFipeIdAndNameAndYear(String fipeId, String name, String year);
}
