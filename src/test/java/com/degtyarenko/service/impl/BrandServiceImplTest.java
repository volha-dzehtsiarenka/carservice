package com.degtyarenko.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class BrandServiceImplTest {

    private BrandServiceImpl brandService;

    @BeforeEach
    void prepare() {
        System.out.println("Before each " + this);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        brandService.findById(1L);
        assertTrue(true);

    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}
