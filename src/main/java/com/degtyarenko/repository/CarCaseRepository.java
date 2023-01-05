package com.degtyarenko.repository;

import com.degtyarenko.entity.CarCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Car case repository.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Repository
public interface CarCaseRepository extends JpaRepository<CarCase, Long> {

    CarCase findByName(String name);

}
