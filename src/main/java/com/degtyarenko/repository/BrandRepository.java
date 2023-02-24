package com.degtyarenko.repository;

import com.degtyarenko.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Brand repository.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByBrandName(String name);

}
