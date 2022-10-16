package com.rxjava.operator.change;

import com.rxjava.common.Car;
import com.rxjava.common.CarMaker;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.Map;

public class Practice {
    public static void main(String[] args) {
        //문제 1 : range, filter, map을 이용하여 1부터 15까지의 숫자 중에서 2의 배수만 필터링 한 후, 필터링된 숫자에 제곱한 숫자를 출력하세요
        Observable.range(1, 15)
                .filter(num -> num % 2 != 0)
                .map(num -> num * num)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        //문제 2 : range, filter, flatmap을 이용하여 2에서 9까지의 구구단 중에서 짝수단만 출력하세요.
        Observable.range(1, 9)
                .filter(num -> num % 2 == 0)
                .flatMap(num -> Observable.range(1,9),
                        (sourceData, transformedData) ->
                                sourceData + " * " + transformedData + " = " + sourceData * transformedData)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        //문제 3 : toMap 을 이용하여 SampleData.carList 의 car 객체들을 carName을 key로, carMaker를 value로 가지는 Map을 출력하세요.
        Single<Map<String, CarMaker>> single = Observable.fromIterable(SampleData.carList)
                .toMap(
                        Car::getCarName,
                        Car::getCarMaker
                );
        single.subscribe(map -> Logger.log(LogType.ON_NEXT, map));
    }
}
