package com.rxjava.operator.change;

import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;

/**
 * 각각의 통지 데이터를 List로 변환해서 Single로 한번만 통지하는 예제
 *
 * 각각의 통지될 Car 객체를 List로 변환해서 Single로 한번만 통지하는 예제
 */
public class ToList {
    public static void main(String[] args) {
        Single<List<Integer>> single = Observable.just(1, 3, 5, 7, 9)
                .toList();

        single.subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        Observable.fromIterable(SampleData.carList)
                .toList()
                .subscribe(carList -> Logger.log(LogType.ON_NEXT, carList));
    }
}
