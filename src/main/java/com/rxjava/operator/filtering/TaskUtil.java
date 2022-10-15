package com.rxjava.operator.filtering;

import com.rxjava.common.Car;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

/**
 * 유형 1 : 파리미터로 지정한 조건이 될 때까지 데이터를 계속 발행
 *
 * 유형 2 : 파라미터로 받은 Flowable/Observable이 최초로 데이터를 발행할 때까지 계속 데이터를 발행
 *  timer와 함께 사용하여 특정 시점이 되기전까지 데이터를 발행하는데 활용하기 용이
 */
public class TaskUtil {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .takeUntil((Car car) -> car.getCarName().equals("트랙스"))
                .subscribe(car -> System.out.println(car.getCarName()));

        TimeUtil.sleep(300L);

        System.out.println("-----------------------------------");

        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .takeUntil(Observable.timer(5500L, TimeUnit.MILLISECONDS))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(5500L);
    }
}
