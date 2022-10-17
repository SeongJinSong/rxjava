package com.rxjava.scheduler;

import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import java.util.Collections;

/**
 * Schedulers.computatioin() 을 이용해서 계산 작업을 처리하는 예제
 */
public class SchedulerComputation {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> observable2 = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> observable3 = Observable.fromIterable(SampleData.incheonPM10List);

        Observable<Integer> observable4 = Observable.range(1, 24);

        Observable source = Observable.zip(observable1, observable2, observable3, observable4,
                (data1, data2, data3, hour) -> hour + "시: " + Collections.max(Arrays.asList(data1, data2, data3)));

        source.subscribeOn(Schedulers.computation())
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        source.subscribeOn(Schedulers.computation())
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(500L);
    }
}
