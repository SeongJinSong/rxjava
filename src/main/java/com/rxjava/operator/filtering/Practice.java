package com.rxjava.operator.filtering;

import com.rxjava.common.CarMaker;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class Practice {
    public static void main(String[] args) {
        //문제 1 : filter를 이용하여 SampleData.carList 중에서 CarMaker가 SSANGYOUNG인 차들의 carName을 출력하세요.
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker().equals(CarMaker.SSANGYOUNG))
                .subscribe(car -> Logger.log(LogType.ON_NEXT, car.getCarName()));

        System.out.println("-----------------------------------");

        //문제 2 : interval, takeWhile을 이용하여 발행된 숫자가 10이 아닌동안 데이터를 1초에 한번씩 계속 출력하세요.
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .takeWhile(data -> data != 10)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
        TimeUtil.sleep(10000L);

        System.out.println("-----------------------------------");

        //문제 3 : interval, skipUntil, timer를 이용하여 1초에 한번씩 발행된 데이터 중에서 3초 후에 발행된 데이터만 10까지 출력하세요.
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .skipUntil(Observable.timer(3000L, TimeUnit.MILLISECONDS))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(11500L);

        System.out.println("-----------------------------------");

        //문제 4 : range, skipLast를 이용하여 1부터 15까지의 숫자중에서 마지막에 발행된 숫자 3개를 제외한 나머지 숫자를 출력하세요
        Observable.range(1, 15)
                .skipLast(3)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
