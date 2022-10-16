package com.rxjava.operator.combine;

import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.NumberUtil;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

/**
 * - 각 Observable에서 통지할 때 마다 모든 Observable에서 마지막으로 통지한 데이터들을 함수형 인터페이스에 반환하고,
 * 이를 가공해서 통지하는 예제.
 * - 각 Observable 중 하나의 Observable에서만 통지가 발생하더라도 이미 통지한 Observable의 마지막 데이터와
 * 함께 전달된다.
 *
 * 랜덤 온도 데이터와 습도 데이터를 최신 데이터로 가져오는 예제
 */
public class CombineLatest {
    public static void main(String[] args) {
        Observable<Long> observable1 =
                Observable.interval(500L, TimeUnit.MILLISECONDS)
//                        .doOnNext(data -> Logger.don("# observable 1 : " + data))
                        .take(4);

        Observable<Long> observable2 =
                Observable.interval(700L, TimeUnit.MILLISECONDS)
//                        .doOnNext(data -> Logger.don("# observable 2 : " + data))
                        .take(4);

        Observable.combineLatest(
                        observable1,
                        observable2,
                        (data1, data2) -> "data1: " + data1 + "\tdata2: " + data2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);

        System.out.println("-----------------------------------");

        // 랜덤 온도 데이터
        Observable<Integer> observableA = Observable.interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.temperatureOfSeoul[NumberUtil.randomRange(0, 5)]);

        // 랜덤 습도 데이터
        Observable<Integer> observableB = Observable.interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.humidityOfSeoul[NumberUtil.randomRange(0, 5)]);

        Observable.combineLatest(observableA, observableB,
                        (temperature, humidity) -> "최신 온습도 데이터 - 온도: " + temperature + "도\t습도: " + humidity + "%")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
