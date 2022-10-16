package com.rxjava.operator.combine;

import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * zip을 이용해 2개의 Observable이 통지하는 데이터 중에서 통지되는 순서가 일치하는 데이터들을 조합하는 예제
 *
 * 서울, 부산, 인천의 시간별 미세먼지 농도를 시간별로 zip한 후, 시간별로 가장 높은 미세먼지 농도를 출력하는 예제
 */
public class Zip {
    public static void main(String[] args) {
        Observable<Long> observable1 =
                Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .take(4);

        Observable<Long> observable2 =
                Observable.interval(400L, TimeUnit.MILLISECONDS)
                        .take(6);

        Observable.zip(observable1, observable2, (data1, data2) -> data1 + data2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);

        System.out.println("-----------------------------------");

        Observable<Integer> observableA = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> observableB = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> observableC = Observable.fromIterable(SampleData.incheonPM10List);

        Observable<Integer> observableD = Observable.range(1, 24);

        Observable.zip(observableA, observableB, observableC, observableD,
                        (data1, data2, data3, hour) -> hour + "시: " + Collections.max(Arrays.asList(data1, data2, data3)))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
