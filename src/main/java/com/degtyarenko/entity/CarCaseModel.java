package com.degtyarenko.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class contains information about car case and models with properties
 * <b>id</b>, <b>carCase</b> and <b>model</b>
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "car_case_model")
@ToString
public class CarCaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_case_id")
    @JsonManagedReference
    private CarCase carCase;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonManagedReference
    private Model model;

}
