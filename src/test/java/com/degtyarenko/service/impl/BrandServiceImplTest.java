package com.degtyarenko.service.impl;

import com.degtyarenko.dto.BrandDto;
import com.degtyarenko.dto.BrandSaveDto;
import com.degtyarenko.entity.Brand;
import com.degtyarenko.mappers.BrandMapper;
import com.degtyarenko.repository.BrandRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * description
 *
 * @author Degtyarenko Olga
 * @since 24.02.2023
 */
@RunWith(MockitoJUnitRunner.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void testFindAll() {
        List<Brand> brands = Arrays.asList(new Brand(), new Brand());
        Mockito.when(brandRepository.findAll()).thenReturn(brands);
        List<Brand> result = brandService.findAll();
        Assert.assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Brand brand = new Brand();
        brand.setId(id);
        Mockito.when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        Brand result = brandService.findById(id);
        Assert.assertEquals(id, result.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Long id = 1L;
        Mockito.when(brandRepository.findById(id)).thenReturn(Optional.empty());
        brandService.findById(id);
    }

    @Test
    void testCreate() {
        BrandSaveDto dto = new BrandSaveDto();
        Brand brand = new Brand();
        Mockito.when(brandRepository.findByBrandName(dto.getBrandName())).thenReturn(null);
        Mockito.when(brandMapper.toBrand(dto)).thenReturn(brand);
        Mockito.when(brandRepository.save(brand)).thenReturn(brand);
        Brand result = brandService.create(dto);
        Assert.assertEquals(brand, result);
    }

    @Test
    void testCreateAlreadyExists() {
        BrandSaveDto dto = new BrandSaveDto();
        Brand brand = new Brand();
        Mockito.when(brandRepository.findByBrandName(dto.getBrandName())).thenReturn(brand);
        brandService.create(dto);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        Mockito.when(brandRepository.findById(id)).thenReturn(Optional.of(new Brand()));
        brandService.delete(id);
        Mockito.verify(brandRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void testDeleteNotFound() {
        Long id = 1L;
        Mockito.when(brandRepository.findById(id)).thenReturn(Optional.empty());
        brandService.delete(id);
    }

    @Test
    void testUpdate() {
        BrandDto dto = new BrandDto();
        dto.setId(1L);
        Brand brand = new Brand();
        Mockito.when(brandRepository.findByBrandName(dto.getBrandName())).thenReturn(null);
        Mockito.when(brandRepository.findById(dto.getId())).thenReturn(Optional.of(brand));
        Mockito.when(brandMapper.toBrand(dto)).thenReturn(brand);
        Mockito.when(brandRepository.save(brand)).thenReturn(brand);
        Brand result = brandService.update(dto);
        Assert.assertEquals(brand, result);
    }

    @Test
    void testUpdateAlreadyExists() {
        BrandDto dto = new BrandDto();
        dto.setId(1L);
        Brand brand = new Brand();
        Mockito.when(brandRepository.findByBrandName(dto.getBrandName())).thenReturn(brand);
        brandService.update(dto);
    }

    @Test
    void testUpdateNotFound() {
        BrandDto dto = new BrandDto();
        dto.setId(1L);
        Mockito.when(brandRepository.findById(dto.getId())).thenReturn(Optional.empty());
        brandService.update(dto);
    }
}
