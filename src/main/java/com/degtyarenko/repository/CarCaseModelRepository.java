package com.degtyarenko.repository;

import com.degtyarenko.entity.CarCaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Car case model repository.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */

public interface CarCaseModelRepository extends JpaRepository<CarCaseModel, Long> {

    CarCaseModel findByCarCaseIdAndModelId(Long idCarCase, Long idModel);


}
