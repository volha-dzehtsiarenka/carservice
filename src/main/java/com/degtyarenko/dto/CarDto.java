package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class CarDto extends AbstractDto {

    private Long carCaseModelId;
    private LocalDate dateOfIssue;

    @Schema(example = "123123DFFFFF465646FG4", required = true, minLength = 1, maxLength = 30)
    @Size(min = 1, max = 30)
    private String vinCode;

    @Size(min = 2, max = 20)
    private String color;

}
