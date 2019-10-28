package com.car.rental.model.repository;

import com.car.rental.model.orm.Tariffs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface TariffsRepository extends JpaRepository<Tariffs, Long>, JpaSpecificationExecutor<Tariffs> {

    Tariffs findByAutomobileIdAndActivedTrue(Long automobileId);

    Tariffs findByActivedTrueAndId(Long id);

    Page<Tariffs> findByActivedTrueAndAutomobileCategoryId(Integer categoryId, Pageable pageable);

    Page<Tariffs> findByActivedTrue(Pageable pageable);

    List<Tariffs> findByActivedTrue();
}
