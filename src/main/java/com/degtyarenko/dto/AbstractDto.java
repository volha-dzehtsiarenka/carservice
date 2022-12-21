package com.degtyarenko.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDto {

    @Hidden
    private Long id;

}
