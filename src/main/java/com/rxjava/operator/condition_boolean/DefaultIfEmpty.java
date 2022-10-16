package com.rxjava.operator.condition_boolean;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

public class DefaultIfEmpty {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .filter(num -> num > 10)
                .defaultIfEmpty(10)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
