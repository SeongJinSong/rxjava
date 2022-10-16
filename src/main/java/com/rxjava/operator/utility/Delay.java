package com.rxjava.operator.utility;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

/**
 * 통지된 데이터를 소비자 쪽에서 전달 받는 시간을 일정 시간동안 지연 시키는 예제
 *
 * 통지되는 데이터 각각에 지연 시간을 적용하는 예제:q!
 */
public class Delay {
    public static void main(String[] args) {
        Logger.log(LogType.PRINT, "# 실행 시작 시간: " + TimeUtil.getCurrentTimeFormatted());

        Observable.just(1, 3, 4, 6)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .delay(2000L, TimeUnit.MILLISECONDS)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(2500L);

        System.out.println("-----------------------------------");

        Observable.just(1,3,5,7)
                .flatMap(item -> {
                    TimeUtil.sleep(1000L);
                    return Observable.just(item); // 새로운 Observable의 통지 시점에, 원본 데이터를 통지한다.
                }).subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
