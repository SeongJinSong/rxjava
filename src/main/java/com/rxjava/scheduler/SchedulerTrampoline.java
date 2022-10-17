package com.rxjava.scheduler;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Schedulers.trampoline()을 이용하여 현재 실행되고 있는 쓰레드의 대기큐에 처리 작업을 등록하는 예제
 * - 대기 큐에 등록되는 순서대로 작업을 처리한다.
 */
public class SchedulerTrampoline {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5");

        observable.subscribeOn(Schedulers.trampoline())
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.trampoline())
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
