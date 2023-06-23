package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.kafka.KafkaProducerService;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * description
 *
 * @author Degtyarenko Olga
 * @since 06.06.2023
 */

@ExtendWith(MockitoExtension.class)
class BrandControllerTest {

    private static final Long BRAND_ID = 1L;
    private static final String HONDA = "Honda";
    private static final String TOYOTA = "Toyota";
    private static final String DELETED_SUCCESSFUL = "Deleted successful !";
    private static final long BRAND_ID_2 = 2L;
    @Mock
    private BrandService brandService;
    @Mock
    private BrandMapper brandMapper;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    void setup() {
        brandController = new BrandController(brandService, brandMapper, kafkaProducerService);
    }

    @Test
    void testFindingAllBrands() {
        List<Brand> brands = new ArrayList<>();
        Brand brand1 = new Brand();
        brand1.setId(BRAND_ID);
        brand1.setBrandName(TOYOTA);
        Brand brand2 = new Brand();
        brand2.setId(BRAND_ID_2);
        brand2.setBrandName(HONDA);
        brands.add(brand1);
        brands.add(brand2);

        List<BrandDto> brandDto = new ArrayList<>();
        BrandDto brandDto1 = new BrandDto();
        brandDto1.setId(BRAND_ID);
        brandDto1.setBrandName(TOYOTA);
        BrandDto brandDto2 = new BrandDto();
        brandDto2.setId(BRAND_ID_2);
        brandDto2.setBrandName(HONDA);
        brandDto.add(brandDto1);
        brandDto.add(brandDto2);

        when(brandService.findAll()).thenReturn(brands);
        when(brandMapper.toBrandDtoList(brands)).thenReturn(brandDto);
        ResponseEntity<List<BrandDto>> response =
                ResponseEntity.status(HttpStatus.OK).body(brandController.findAll());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

    @Test
    void testFindingBrandByIdWhenIdExists() {
        Long brandId = BRAND_ID;
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setBrandName(TOYOTA);

        BrandDto brandDto = new BrandDto();
        brandDto.setId(brandId);
        brandDto.setBrandName(TOYOTA);

        when(brandService.findById(brandId)).thenReturn(brand);
        when(brandMapper.toBrandDto(brand)).thenReturn(brandDto);
        ResponseEntity<BrandDto> response =
                ResponseEntity.status(HttpStatus.OK).body(brandController.findById(brandId));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

    @Test
    void testDeletingBrandById() {
        ResponseEntity<String> response =
                ResponseEntity.status(HttpStatus.OK).body(brandController.deleteById(BRAND_ID));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DELETED_SUCCESSFUL, response.getBody());
    }

    @Test
    void testCreatingBrand() {
        BrandSaveDto brandSaveDto = new BrandSaveDto();
        brandSaveDto.setBrandName(TOYOTA);

        Brand brand = new Brand();
        brand.setId(BRAND_ID);
        brand.setBrandName(TOYOTA);

        BrandDto brandDto = new BrandDto();
        brandDto.setId(BRAND_ID);
        brandDto.setBrandName(TOYOTA);

        when(brandService.create(brandSaveDto)).thenReturn(brand);
        when(brandMapper.toBrandDto(brand)).thenReturn(brandDto);

        ResponseEntity<BrandDto> response =
                ResponseEntity.status(HttpStatus.CREATED).body(brandController.createBrand(brandSaveDto));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

    @Test
    void testUpdatingBrand() {
        Long brandId = BRAND_ID;
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brandId);
        brandDto.setBrandName(TOYOTA);
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setBrandName(TOYOTA);

        when(brandService.update(brandDto)).thenReturn(brand);
        when(brandMapper.toBrandDto(brand)).thenReturn(brandDto);

        ResponseEntity<BrandDto> response =
                ResponseEntity.status(HttpStatus.OK).body(brandController.updateBrand(brandDto));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

}
