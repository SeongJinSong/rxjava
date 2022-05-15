package com.rxjava;

import io.reactivex.Observable;

public class HelloRxJava {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("Helo", "RxJava");
        observable.subscribe(data -> System.out.println(data));
    }
}
