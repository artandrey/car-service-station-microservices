package com.example.management_service.car;

import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.modules.car.exceptions.CarModelNotFoundException;
import com.example.management_service.modules.car.mappers.CarModelMapper;
import com.example.management_service.modules.car.repository.car_model.CarModelRepository;
import com.example.management_service.modules.car.services.car_model.implementation.CarModelService;

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
public class CarModelServiceTest {

    @Mock
    private CarModelRepository carModelRepository;

    @Mock
    private CarModelMapper carModelMapper;

    @InjectMocks
    private CarModelService carModelService;

    private CarModel carModel;

    @BeforeEach
    void setUp() {
        carModel = new CarModel();
        carModel.setId(1L);
        carModel.setTitle("Corolla");
    }

    @Test
    void testGetAllCarModels() {
        List<CarModel> carModels = new ArrayList<>();
        carModels.add(carModel);

        when(carModelRepository.findAll()).thenReturn(carModels);

        List<CarModel> result = carModelService.getAllCarModels();

        assertEquals(carModels, result);
    }

    @Test
    void testGetCarModelById() {
        when(carModelRepository.findById(1L)).thenReturn(Optional.of(carModel));

        CarModel result = carModelService.getCarModelById(1L);

        assertEquals(carModel, result);
    }

    @Test
    void testGetCarModelByIdNotFound() {
        when(carModelRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CarModelNotFoundException.class, () -> carModelService.getCarModelById(2L));
    }

    @Test
    void testCreateCarModel() {
        when(carModelRepository.save(carModel)).thenReturn(carModel);

        CarModel result = carModelService.createCarModel(carModel);

        assertEquals(carModel, result);
    }

    @Test
    void testUpdateCarModel() {
        CarModel updatedCarModel = new CarModel();
        updatedCarModel.setId(1L);
        updatedCarModel.setTitle("Camry");

        when(carModelRepository.findById(1L)).thenReturn(Optional.of(carModel));
        when(carModelMapper.updateFromEntity(updatedCarModel, carModel)).thenReturn(updatedCarModel);
        when(carModelRepository.save(updatedCarModel)).thenReturn(updatedCarModel);

        CarModel result = carModelService.updateCarModel(1L, updatedCarModel);

        assertEquals(updatedCarModel, result);
    }

    @Test
    void testUpdateCarModelNotFound() {
        when(carModelRepository.findById(2L)).thenReturn(Optional.empty());

        CarModel updatedCarModel = new CarModel();
        updatedCarModel.setId(2L);
        updatedCarModel.setTitle("Camry");

        assertThrows(CarModelNotFoundException.class, () -> carModelService.updateCarModel(2L, updatedCarModel));
    }

    @Test
    void testDeleteCarModel() {
        when(carModelRepository.existsById(1L)).thenReturn(true);

        boolean result = carModelService.deleteCarModel(1L);

        assertTrue(result);
        verify(carModelRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCarModelNotFound() {
        when(carModelRepository.existsById(2L)).thenReturn(false);

        assertThrows(CarModelNotFoundException.class, () -> carModelService.deleteCarModel(2L));
    }
}