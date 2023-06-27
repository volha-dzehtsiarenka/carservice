package com.degtyarenko.repository;

import com.degtyarenko.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Model repository.
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findByModelName(String modelName);

}
