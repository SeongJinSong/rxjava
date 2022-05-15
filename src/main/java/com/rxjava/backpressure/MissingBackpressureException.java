package com.rxjava.backpressure;

import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class MissingBackpressureException {
    public static void main(String[] args) throws InterruptedException {
        //데이터 통지
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.don(data))
                .observeOn(Schedulers.computation())
                //데이터 구독
                .subscribe(
                        data -> {
                            Logger.print("# 소비자 처리 대기 중..");
                            TimeUtil.sleep(1000L);
                            Logger.on(data);
                        },
                        error -> Logger.oe(error),
                        ()-> Logger.oc()
                );
        Thread.sleep(2000L);
    }
}
