package com.rxjava.method_reference;

import com.rxjava.common.Car;

import java.util.function.Function;

/**
 * Class Name::instance method 메서드 레퍼런스 예
 */
public class ClassNameInstanceMethod {
    public static void main(String[] args) {
        Function<Car, String> f1 = car -> car.getCarName();
        String carName1 = f1.apply(new Car("트래버스"));
        System.out.println(carName1);

        Function<Car, String> f2 = Car::getCarName;
        String carName2 = f2.apply(new Car("팰리세이드"));
        System.out.println(carName2);
    }
}
