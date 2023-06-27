package com.degtyarenko.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Class contains information about car <b>models</b> with properties
 * <b>id</b>, <b>modelName</b> and <b>brand</b>
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Entity
@Table(name = "model")
@RequiredArgsConstructor
@ToString(exclude = "carCaseModel")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonBackReference
    @OneToMany(mappedBy = "model")
    private Set<CarCaseModel> carCaseModel;

}
