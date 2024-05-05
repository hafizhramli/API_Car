package com.example.juniorprogrammer.apicrud.controllers;

import com.example.juniorprogrammer.apicrud.entities.CarEntity;
import com.example.juniorprogrammer.apicrud.entities.CarImage;
import com.example.juniorprogrammer.apicrud.repositories.CarRepository;
import com.example.juniorprogrammer.apicrud.response.CommonResponse;
import com.example.juniorprogrammer.apicrud.response.CommonResponseGenerator;
import com.example.juniorprogrammer.apicrud.services.CarService;
import com.example.juniorprogrammer.apicrud.wrappers.CarImageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car")
public class CarController {


    @Autowired
    CarService carService;

    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @GetMapping(value = "checkAPI")
    public CommonResponse<String> checkApi() {
        return commonResponseGenerator.succesResponse("Hello World", "Succes check API");
    }

    @PostMapping(value = "addNewCar")
    public  CommonResponse<CarEntity> addNewCar(@RequestBody CarEntity param) {
        try {
            CarEntity car = carService.addCar(param);
            return commonResponseGenerator.succesResponse(car, "Succes add new data");
        }catch (Exception e) {
            return  commonResponseGenerator.failedResponse(e.getMessage());

        }
    }
    @GetMapping(value = "getAllCar")
    public CommonResponse<List<CarEntity>> getAllCar(){
        try {
            List<CarEntity> carList = carService.getallCar();
            return commonResponseGenerator.succesResponse(carList, "Succes get all data");
        } catch (Exception e) {
            return  commonResponseGenerator.failedResponse(e.getMessage());
        }
    }
    @GetMapping(value = "getById")
    public CommonResponse<CarEntity> getById(@RequestParam int id) {
        try {
            CarEntity car = carService.getCarById(id);
            return commonResponseGenerator.succesResponse(car, "Succes get by id: ");
        } catch (Exception e) {
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }
    @PostMapping(value = "updateCar")
    public CommonResponse<CarEntity> updateCar(@RequestBody CarEntity param) {
        try {
            CarEntity car = carService.updateCar(param);
            return commonResponseGenerator.succesResponse(car, "Succes update data: ");
        } catch (Exception e) {
            return commonResponseGenerator.failedResponse(e.getMessage()+" for id: "+param.getId());
        }
    }

    @GetMapping(value = "deleteCar")
    public CommonResponse<List<CarEntity>> deleteCar(@RequestParam int id) {
        try {
            carService.deleteCar(id);
            List<CarEntity> carList = carService.getallCar();
            return commonResponseGenerator.succesResponse(carList, "Succes delete car");
        } catch (Exception e) {
            return commonResponseGenerator.failedResponse(e.getMessage());

        }
    }
    
    @PostMapping(value = "upload-car")
    public CommonResponse<CarImageWrapper> upload(@RequestBody CarImageWrapper param) {
        try {
            CarImage car = carService.upload(param);
            return commonResponseGenerator.succesResponse(car, "Succes Upload Image");
        } catch (Exception e) {
            return commonResponseGenerator.failedResponse(e.getMessage());

        }
    }
}
