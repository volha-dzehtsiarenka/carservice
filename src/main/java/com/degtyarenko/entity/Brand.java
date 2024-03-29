package com.degtyarenko.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Class contains information about car <b>brands</b> with properties
 * <b>id</b> and <b>brandName</b>
 *
 * @author Degtyarenko Olga
 * @version 1.0
 * @since 2022-12-22
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "brand")
@RequiredArgsConstructor
@ToString(exclude = "models")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    @NotNull
    private String brandName;

    @OneToMany(mappedBy = "brand")
    @JsonBackReference
    @Hidden
    private Set<Model> models;

}
