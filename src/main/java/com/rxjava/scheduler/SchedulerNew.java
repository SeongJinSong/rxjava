package com.rxjava.scheduler;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Schedulers.newThread()를 이용하여 구독할때 마다 새로운 쓰레드를 생성하는 예제
 * - 쓰레드 생성 비용이 들고, 재사용 되지 않으므로 권장 되지 않는 방법이다.
 */
public class SchedulerNew {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5");

        observable.subscribeOn(Schedulers.newThread())
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.newThread())
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));


        TimeUtil.sleep(300L);
    }
}
