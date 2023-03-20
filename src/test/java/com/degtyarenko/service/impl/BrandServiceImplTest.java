package com.degtyarenko.service.impl;

import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.repository.BrandRepository;

import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.mapstruct.factory.Mappers;
import org.springframework.test.web.servlet.MockMvc;

/**
 * description
 *
 * @author Degtyarenko Olga
 * @since 24.02.2023
 */
class BrandServiceImplTest {

    private static MockMvc mockMvc;
    private static final BrandRepository brandRepository = mock(BrandRepository.class);
    private static final BrandMapper brandMapper = Mappers.getMapper(BrandMapper.class);

    @AfterEach
    void clearAllMocks() {
        clearInvocations();
    }


}
