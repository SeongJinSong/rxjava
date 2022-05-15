package com.rxjava.backpressure;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class DropOldest {
    public static void main(String[] args) {
        System.out.println("# start :" + TimeUtil.getCurrentTimeFormatted());
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log("#interval doOnNext", data))
                //버퍼안에 들어올 수 있는 데이터 개수
                .onBackpressureBuffer(
                        2,
                        () -> Logger.log("overflow!"),
                        BackpressureOverflowStrategy.DROP_OLDEST
                )
                .doOnNext(data -> Logger.log("#onBackpressureBuffer doOnNext()", data))
                //소비자 쪽에서 요청하는 데이터 개수
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
        TimeUtil.sleep(2800L);
    }
}
