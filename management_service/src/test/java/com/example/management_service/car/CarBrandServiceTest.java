package com.example.management_service.car;

import com.example.management_service.modules.car.entities.CarBrand;
import com.example.management_service.modules.car.exceptions.CarBrandNotFoundException;
import com.example.management_service.modules.car.mappers.CarBrandMapper;
import com.example.management_service.modules.car.repository.car_brand.CarBrandRepository;
import com.example.management_service.modules.car.services.car_brand.implementation.CarBrandService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarBrandServiceTest {

    @Mock
    private CarBrandRepository carBrandRepository;

    @Mock
    private CarBrandMapper carBrandMapper;

    @InjectMocks
    private CarBrandService carBrandService;

    private CarBrand carBrand;

    @BeforeEach
    void setUp() {
        carBrand = new CarBrand();
        carBrand.setId(1L);
        carBrand.setTitle("Toyota");
    }

    @Test
    void testGetAllCarBrands() {
        List<CarBrand> carBrands = new ArrayList<>();
        carBrands.add(carBrand);

        when(carBrandRepository.findAll()).thenReturn(carBrands);

        List<CarBrand> result = carBrandService.getAllCarBrands();

        assertEquals(carBrands, result);
    }

    @Test
    void testGetCarBrandById() {
        when(carBrandRepository.findById(1L)).thenReturn(Optional.of(carBrand));

        CarBrand result = carBrandService.getCarBrandById(1L);

        assertEquals(carBrand, result);
    }

    @Test
    void testGetCarBrandByIdNotFound() {
        when(carBrandRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CarBrandNotFoundException.class, () -> carBrandService.getCarBrandById(2L));
    }

    @Test
    void testCreateCarBrand() {
        when(carBrandRepository.save(carBrand)).thenReturn(carBrand);

        CarBrand result = carBrandService.createCarBrand(carBrand);

        assertEquals(carBrand, result);
    }

    @Test
    void testUpdateCarBrandNotFound() {
        when(carBrandRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CarBrandNotFoundException.class, () -> carBrandService.updateCarBrand(2L, carBrand));
    }

    @Test
    void testDeleteCarBrand() {
        when(carBrandRepository.existsById(1L)).thenReturn(true);

        boolean result = carBrandService.deleteCarBrand(1L);

        assertTrue(result);
        verify(carBrandRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCarBrandNotFound() {
        when(carBrandRepository.existsById(2L)).thenReturn(false);

        assertThrows(CarBrandNotFoundException.class, () -> carBrandService.deleteCarBrand(2L));
    }
}
