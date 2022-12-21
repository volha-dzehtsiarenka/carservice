package com.degtyarenko.repository;

import com.degtyarenko.entity.CarCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCaseRepository extends JpaRepository<CarCase, Long> {

}
