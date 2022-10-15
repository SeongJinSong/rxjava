package com.rxjava.operator.filtering;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

/**
 * 지정한 갯수만큼 데이터를 발행
 */
public class Take {
    public static void main(String[] args) {
        Observable.just("a", "b", "c", "d")
                .take(2)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .take(3500L, TimeUnit.MILLISECONDS)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3500L);
    }
}
