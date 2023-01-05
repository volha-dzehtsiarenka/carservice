package com.degtyarenko.repository;

import com.degtyarenko.entity.CarCaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Car case model repository.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Repository
public interface CarCaseModelRepository extends JpaRepository<CarCaseModel, Long> {

    CarCaseModel findByCarCase_IdAndModel_Id(Long idCarCase, Long idModel);



}
