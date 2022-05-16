package com.rxjava.functional;

import com.rxjava.common.*;

import java.util.Arrays;
import java.util.List;

public class FunctionalLamdaInterface {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car(CarMaker.HYUNDAE, CarType.SUV, "팰리세이드", 28000000, true),
                new Car(CarMaker.SAMSUNG, CarType.SEDAN, "SM5", 35000000, true),
                new Car(CarMaker.CHEVROLET, CarType.SUV, "트래버스", 50000000, true),
                new Car(CarMaker.KIA, CarType.SEDAN, "K5", 20000000, false),
                new Car(CarMaker.SSANGYOUNG, CarType.SUV, "티볼리", 23000000, true)
        );
        List<Car> carsFilteredByPrice = CarFiler.filterCarByCustomPredicate(cars, (Car car) -> car.getCarPrice() > 30000000);
        for (Car car : carsFilteredByPrice){
            System.out.println("차 이름:" + car.getCarName() + ", 가격:" + car.getCarPrice());
        }
        List<Car> carsFilteredByCarType = CarFiler.filterCarByCustomPredicate(cars, car->car.getCarType().equals(CarType.SUV));
        for (Car car : carsFilteredByCarType) {
            System.out.println("차 이름"+car.getCarName()+ ", 차종:"+car.getCarType());
        }
    }
}
