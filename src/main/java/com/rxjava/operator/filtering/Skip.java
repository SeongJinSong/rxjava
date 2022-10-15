package com.rxjava.operator.filtering;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;

public class Skip {
    public static void main(String[] args) {
        Observable.range(1, 15)
                .skip(3)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        Observable.interval(300L, TimeUnit.MILLISECONDS)
                .skip(1000L, TimeUnit.MILLISECONDS)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(3000L);
    }
}
