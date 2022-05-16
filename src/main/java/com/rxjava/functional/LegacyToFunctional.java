package com.rxjava.functional;

import com.rxjava.common.Car;
import com.rxjava.common.CarMaker;
import com.rxjava.common.CarType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LegacyToFunctional {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car(CarMaker.HYUNDAE, CarType.SUV, "팰리세이드", 28000000, true),
                new Car(CarMaker.SAMSUNG, CarType.SEDAN, "SM5", 35000000, true),
                new Car(CarMaker.CHEVROLET, CarType.SUV, "트래버스", 50000000, true),
                new Car(CarMaker.KIA, CarType.SEDAN, "K5", 20000000, false),
                new Car(CarMaker.SSANGYOUNG, CarType.SUV, "티볼리", 23000000, true)
        );
        Collections.sort(cars, (car1, car2)->car1.getCarPrice()-car2.getCarPrice());
        for (Car car : cars){
            System.out.println("차 이름:" + car.getCarName() + ", 가격: " + car.getCarPrice());
        }
    }
}
