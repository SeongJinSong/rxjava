package com.rxjava.scheduler;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;

/**
 * Scheduler.io()를 이용한 파일 입/출력 예제
 * 1. subscribeOn() 만 지정하면 데이터 통지 및 처리를 모두 RxCachedThreadScheduler 쓰레드에서 실행한다.
 *
 * 2. subscribeOn(), observeOn()을 모두 지정하면 데이터 통지와 데이터 처리를 개별 쓰레드에서 진행한다.
 *
 * 3. observeOn()을 여러개 지정하면 지정한 다음의 데이터 처리를 각각 개별 쓰레드에서 진행한다.
 */
public class SchedulerIO {
    public static void main(String[] args) {
        File[] files = new File("src/main/java/com/rxjava/").listFiles();

        Observable.fromArray(files)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data.getName()))
                .filter(data -> data.isDirectory())
                .map(dir -> dir.getName())
                .subscribeOn(Schedulers.io())
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);

        System.out.println("-----------------------------------");

        Observable.fromArray(files)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .filter(data -> data.isDirectory())
                .map(dir -> dir.getName())
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);

        System.out.println("-----------------------------------");

        Observable.fromArray(files)
                .doOnNext(file -> Logger.log(LogType.DO_ON_NEXT, "# 데이터 통지"))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .filter(data -> data.isDirectory())
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# filter() 거침"))
                .observeOn(Schedulers.computation())
                .map(dir -> dir.getName())
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# map() 거침"))
                .observeOn(Schedulers.computation())
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
    }
}
