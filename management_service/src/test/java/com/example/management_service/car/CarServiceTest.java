package com.example.management_service.car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.management_service.modules.car.entities.Car;
import com.example.management_service.modules.car.entities.CarModel;
import com.example.management_service.modules.car.exceptions.CarNotFoundException;
import com.example.management_service.modules.car.mappers.CarMapper;
import com.example.management_service.modules.car.repository.car.CarRepository;
import com.example.management_service.modules.car.services.car.implementation.CarService;
import com.example.management_service.modules.car.services.car_model.ICarModelService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private ICarModelService carModelService;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarService carService;

    @Test
    public void testGetCars() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        cars.add(new Car());

        when(carRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(cars));

        List<Car> result = carService.getCars(pageRequest);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(carRepository, times(1)).findAll(pageRequest);
    }

    @Test
    public void testGetOwnerCars() {
        Long userId = 1L;
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        cars.add(new Car());

        when(carRepository.findByOwnerId(userId, pageRequest)).thenReturn(cars);

        List<Car> result = carService.getOwnerCars(userId, pageRequest);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(carRepository, times(1)).findByOwnerId(userId, pageRequest);
    }

    @Test
    public void testCreateCar() {
        Car car = new Car();
        CarModel carModel = new CarModel();
        carModel.setId(1L);
        car.setCarModel(carModel);

        when(carModelService.getCarModelById(carModel.getId())).thenReturn(carModel);
        when(carRepository.save(car)).thenReturn(car);

        Car createdCar = carService.createCar(car);

        assertNotNull(createdCar);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testDeleteCar() {
        Long carId = 1L;

        when(carRepository.existsById(carId)).thenReturn(true);

        boolean result = carService.deleteCar(carId);

        assertTrue(result);
        verify(carRepository, times(1)).deleteById(carId);
    }

    @Test
    public void testGetCarById() {
        Long carId = 1L;
        Car car = new Car();
        car.setId(carId);

        when(carRepository.existsById(carId)).thenReturn(true);
        when(carRepository.getReferenceById(carId)).thenReturn(car);

        Car result = carService.getCarById(carId);

        assertNotNull(result);
        assertEquals(carId, result.getId());
    }

    @Test
    public void testGetCarByIdNotFound() {
        Long carId = 1L;

        when(carRepository.existsById(carId)).thenReturn(false);

        assertThrows(CarNotFoundException.class, () -> carService.getCarById(carId));
    }
}