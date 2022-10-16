package com.rxjava.operator.utility;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;

/**
 * Material 예제
 *
 * Material/Dematerial 연산자의 실제 활용 예제
 * - 특정 Observable 에서 에러가 발생 할 경우 해당 에러에 대해서 구체적으로 처리할 수 있다.
 */
public class Material {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6)
            .materialize()
            .subscribe(notification -> {
                String notificationType =
                        notification.isOnNext() ? "onNext()" : (notification.isOnError() ? "onError()" : "onComplete()");
                Logger.log(LogType.PRINT, "notification 타입: " + notificationType);
                Logger.log(LogType.ON_NEXT, notification.getValue());
            });

        System.out.println("-----------------------------------");

        Observable.concatEager(
                Observable.just(
                        getDBUser().subscribeOn(Schedulers.io()),
                        getAPIUser()
                                .subscribeOn(Schedulers.io())
                                .materialize()
                                .map(notification -> {
                                    if (notification.isOnError()) {
                                        // 관리자에게 에러 발생을 알림
                                        Logger.log(LogType.PRINT, "# API user 에러 발생!");
                                    }
                                    return notification;
                                })
                                .filter(notification -> !notification.isOnError())
                                .dematerialize(notification -> notification)
                )
        ).subscribe(
                data -> Logger.log(LogType.ON_NEXT, data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
        );

        TimeUtil.sleep(1000L);
    }

    private static Observable<String> getDBUser() {
        return Observable.fromIterable(Arrays.asList("DB user1", "DB user2", "DB user3", "DB user4", "DB user5"));
    }

    private static Observable<String> getAPIUser() {
        return Observable
                .just("API user1", "API user2", "Not User", "API user4", "API user5")
                .map(user -> {
                    if(user.equals("Not User"))
                        throw new RuntimeException();
                    return user;
                });
    }
}
