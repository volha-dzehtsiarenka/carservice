package com.degtyarenko.repository;

import com.degtyarenko.entity.CarCaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCaseModelRepository extends JpaRepository<CarCaseModel, Long> {

}
