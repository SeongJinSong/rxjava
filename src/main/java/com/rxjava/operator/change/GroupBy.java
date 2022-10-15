package com.rxjava.operator.change;

import com.rxjava.common.Car;
import com.rxjava.common.CarMaker;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observables.GroupedObservable;

/**
 * Car 제조사 별로 그룹으로 묶어서 데이터를 통지하는 예제
 *
 * Group으로 묶은 데이터들 중에서 filter를 이용해 필터링한 Group의 데이터만 출력하는 예제
 *
 * 제조사를 그룹으로 묶어서 자동차 명을 출력하는 예제
 *
 * 제조사 별로 그룹으로 묶은 후, 제조사 별 차량 가격의 합계를 구하는 예제
 */
public class GroupBy {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
                Observable.fromIterable(SampleData.carList).groupBy(car -> car.getCarMaker());

        observable.subscribe(
                groupedObservable -> groupedObservable.subscribe(
                        car -> Logger.log(
                                LogType.ON_NEXT, "Group: " +
                                        groupedObservable.getKey() +
                                        "\t Car name: " + car.getCarName())
                )
        );

        System.out.println("-----------------------------------");

        Observable<GroupedObservable<CarMaker, Car>> observable2 =
                Observable.fromIterable(SampleData.carList).groupBy(Car::getCarMaker);

        observable2.subscribe(
                groupedObservable ->
                        groupedObservable
                                .filter(car -> groupedObservable.getKey().equals(CarMaker.CHEVROLET))
                                .subscribe(
                                        car -> Logger.log(
                                                LogType.PRINT, "Group: "
                                                        + groupedObservable.getKey()
                                                        + "\t Car name: " + car.getCarName())
                                )
        );

        System.out.println("-----------------------------------");

        Observable<GroupedObservable<CarMaker, Car>> observable3 =
                Observable.fromIterable(SampleData.carList)
                        .groupBy(Car::getCarMaker);

        observable3
                .flatMapSingle(carGroup ->
                        carGroup.flatMap(car ->
                                        Observable.just(car.getCarName()))
                                .toList()
                )
                .subscribe(System.out::println);

        System.out.println("-----------------------------------");

        Observable<GroupedObservable<CarMaker, Car>> observable4 =
                Observable.fromIterable(SampleData.carList)
                        .groupBy(car -> car.getCarMaker());

        observable4
                .flatMapSingle(carGroup ->
                        Single.just(carGroup.getKey())
                                .zipWith(
                                        carGroup.flatMap(car ->
                                                        Observable.just(car.getCarPrice()))
                                                .reduce((p1, p2)-> p1 + p2)
                                                .toSingle()
                                        , (key, sum) -> key + ": " + sum
                                )
                )
                .subscribe(System.out::println);
    }
}
