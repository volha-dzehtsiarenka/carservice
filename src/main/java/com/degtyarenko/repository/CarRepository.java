package com.degtyarenko.repository;

import com.degtyarenko.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Car repository.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByVinCode(String vinCode);

}
