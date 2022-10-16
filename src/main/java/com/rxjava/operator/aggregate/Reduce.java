package com.rxjava.operator.aggregate;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

/**
 * reduce를 이용해 1부터 10까지의 sum을 구하는 예제(초기값 없음)
 *
 * reduce를 이용해 1부터 10까지 sum을 구하는 예제(초기값 있음)
 *
 * reduce를 이용해 문자열이 누적되어 처리되어 출력되는 예제
 */
public class Reduce {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce((x, y) -> x + y)
                .subscribe(result -> Logger.log(LogType.ON_NEXT, "# 1부터 10까지의 누적 합계: " + result));

        System.out.println("-----------------------------------");

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce(0, (x, y) -> {
                    Logger.log(LogType.PRINT, "# reduce 입력 값 : " + x + ", " + y);
                    return x + y;
                })
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        Observable.just("a", "b", "c", "d", "e")
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce((x, y) -> "(" + x + ", " + y + ")")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
