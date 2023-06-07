package com.degtyarenko.controller;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.kafka.KafkaProducerService;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

class BrandControllerTest {

    @Mock
    private BrandService brandService;
    @Mock
    private BrandMapper brandMapper;
    @Mock
    private KafkaProducerService kafkaProducerService;
    private BrandController brandController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        brandController = new BrandController(brandService, brandMapper, kafkaProducerService);
    }

    @Test
    void testFindingAllBrands() {
        List<Brand> brands = new ArrayList<>();
        Brand brand1 = new Brand();
        brand1.setId(1L);
        brand1.setBrandName("Toyota");
        Brand brand2 = new Brand();
        brand2.setId(2L);
        brand2.setBrandName("Honda");
        brands.add(brand1);
        brands.add(brand2);

        List<BrandDto> brandDto = new ArrayList<>();
        BrandDto brandDto1 = new BrandDto();
        brandDto1.setId(1L);
        brandDto1.setBrandName("Toyota");
        BrandDto brandDto2 = new BrandDto();
        brandDto2.setId(2L);
        brandDto2.setBrandName("Honda");
        brandDto.add(brandDto1);
        brandDto.add(brandDto2);

        when(brandService.findAll()).thenReturn(brands);
        when(brandMapper.toBrandDtoList(brands)).thenReturn(brandDto);
        ResponseEntity<List<BrandDto>> response = brandController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

    @Test
    void testFindingBrandByIdWhenIdExists() {
        Long brandId = 1L;
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setBrandName("Toyota");

        BrandDto brandDto = new BrandDto();
        brandDto.setId(brandId);
        brandDto.setBrandName("Toyota");

        when(brandService.findById(brandId)).thenReturn(brand);
        when(brandMapper.toBrandDto(brand)).thenReturn(brandDto);
        ResponseEntity<BrandDto> response = brandController.findById(brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

    @Test
    void testDeletingBrandById() {
        Long brandId = 1L;
        ResponseEntity<String> response = brandController.deleteById(brandId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted successful !", response.getBody());
    }

    @Test
    void testCreatingBrand() {
        BrandSaveDto brandSaveDto = new BrandSaveDto();
        brandSaveDto.setBrandName("Toyota");

        Brand brand = new Brand();
        brand.setId(1L);
        brand.setBrandName("Toyota");

        BrandDto brandDto = new BrandDto();
        brandDto.setId(1L);
        brandDto.setBrandName("Toyota");

        when(brandMapper.toBrand(brandSaveDto)).thenReturn(brand);
        when(brandService.create(brandSaveDto)).thenReturn(brand);
        when(brandMapper.toBrandDto(brand)).thenReturn(brandDto);

        ResponseEntity<BrandDto> response = brandController.createBrand(brandSaveDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

    @Test
    void testUpdatingBrand() {
        Long brandId = 1L;
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brandId);
        brandDto.setBrandName("Toyota");
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setBrandName("Toyota");

        when(brandMapper.toBrand(brandDto)).thenReturn(brand);
        when(brandService.update(brandDto)).thenReturn(brand);
        when(brandMapper.toBrandDto(brand)).thenReturn(brandDto);

        ResponseEntity<BrandDto> response = brandController.updateBrand(brandDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandDto, response.getBody());
    }

}
