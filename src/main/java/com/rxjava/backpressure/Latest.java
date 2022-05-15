package com.rxjava.backpressure;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Latest {
    public static void main(String[] args) {
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log("#interval doOnNext()", data))
                .onBackpressureLatest()
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
        TimeUtil.sleep(5500L);
    }
}
