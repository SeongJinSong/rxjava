package com.rxjava.paradigm;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ToDoSample {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Reactive Programing 규칙
         * 1. 데이터 발생
         * 2. 데이터를 가공하고
         * 3. 데이터를 구독해서
         * 처리한다.
         */
        Observable.just(100, 200, 300, 400, 500)
                // 각각의 데이터가 발행이 될때 실행된다.
                .doOnNext(data -> System.out.println(getThreadName() + " : " + "#doOnNext() " + data))
                /**
                 * 스케쥴러를 지정
                 */
                //데이터의 발행 또는 데이터의 흐름을 결정짓는다 ex)다른 스레드에서 실행을 하게 된다.
                .subscribeOn(Schedulers.io())
                //발행된 데이터를 구독해서 처리하는 스레드를 지정하게 된다.
                .observeOn(Schedulers.computation())
                .filter(number -> number > 300)
                .subscribe(num -> System.out.println(getThreadName() + " : result : " + num));

        Thread.sleep(500);
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
