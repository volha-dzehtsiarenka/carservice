package com.degtyarenko.service.impl;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.exeption.EntityIsUsedException;
import com.degtyarenko.exeption.EntityNotFoundException;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * description
 *
 * @author Degtyarenko Olga
 * @since 06.06.2023
 */

class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;
    @Mock
    private BrandMapper brandMapper;
    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandService = new BrandServiceImpl(brandRepository, brandMapper);
    }

    @Test
    void testFindingAllBrands() {
        List<Brand> expectedBrands = new ArrayList<>();
        when(brandRepository.findAll()).thenReturn(expectedBrands);
        List<Brand> actualBrands = brandService.findAll();
        assertEquals(expectedBrands, actualBrands);
    }

    @Test
    void testFindingBrandByIdWhenIdExists() {
        Long id = 1L;
        Brand expectedBrand = new Brand();
        when(brandRepository.findById(id)).thenReturn(Optional.of(expectedBrand));
        Brand actualBrand = brandService.findById(id);
        assertEquals(expectedBrand, actualBrand);
    }

    @Test
    void testThrowExceptionWhenBrandByIdNonExistingId() {
        Long id = 1L;
        when(brandRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> brandService.findById(id));
    }

    @Test
    void testCreatingBrandWhenDoesNotExist() {
        BrandSaveDto brandDto = new BrandSaveDto();
        Brand newBrand = new Brand();
        when(brandRepository.findByBrandName(brandDto.getBrandName())).thenReturn(null);
        when(brandMapper.toBrand(brandDto)).thenReturn(newBrand);
        when(brandRepository.save(newBrand)).thenReturn(newBrand);
        Brand createdBrand = brandService.create(brandDto);
        assertEquals(newBrand, createdBrand);
    }

    @Test
    void testThrowExceptionWhenCreateBrandAlreadyExists() {
        BrandSaveDto brandDto = new BrandSaveDto();
        Brand existingBrand = new Brand();
        when(brandRepository.findByBrandName(brandDto.getBrandName())).thenReturn(existingBrand);
        assertThrows(EntityIsUsedException.class, () -> brandService.create(brandDto));
    }

    @Test
    void testDeleteBrandWhenIdExists() {
        Long id = 1L;
        when(brandRepository.existsById(id)).thenReturn(true);
        assertDoesNotThrow(() -> brandService.delete(id));
    }

    @Test
    void testNotThrowExceptionWhenDeleteBrandNonExistingId() {
        Long id = 1L;
        when(brandRepository.existsById(id)).thenReturn(false);
        assertDoesNotThrow(() -> brandService.delete(id));
    }

    @Test
    void testUpdateBrandWhenDoesNotExist() {
        BrandDto brandDto = new BrandDto();
        Brand existingBrand = new Brand();
        when(brandRepository.findByBrandName(brandDto.getBrandName())).thenReturn(null);
        when(brandRepository.findById(brandDto.getId())).thenReturn(Optional.of(existingBrand));
        Brand updatedBrand = new Brand();
        when(brandMapper.toBrand(brandDto)).thenReturn(updatedBrand);
        when(brandRepository.save(updatedBrand)).thenReturn(updatedBrand);
        Brand updated = brandService.update(brandDto);
        assertEquals(updatedBrand, updated);
    }

    @Test
    void testThrowExceptionWhenUpdateBrandAlreadyExists() {
        BrandDto brandDto = new BrandDto();
        brandDto.setBrandName("Toyota");
        Brand existingBrand = new Brand();

        when(brandRepository.findByBrandName(brandDto.getBrandName())).thenReturn(existingBrand);
        when(brandRepository.findById(brandDto.getId())).thenReturn(Optional.of(new Brand()));

        assertThrows(EntityIsUsedException.class, () -> brandService.update(brandDto));
    }

}
