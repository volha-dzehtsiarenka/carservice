package com.degtyarenko.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
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
    private String brandName;

    @OneToMany(mappedBy = "brand")
    @JsonBackReference
    private Set<Model> models;

}
