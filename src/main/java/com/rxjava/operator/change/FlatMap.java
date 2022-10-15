package com.rxjava.operator.change;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

/**
 * 유형 1 : flapMap을 이용한 1 대 다 mapping 예제
 *   - flapMap을 이용한 구구단의 2단 출력 예제
 *
 * 유형 2 : flatMap 두번째 유형을 이용한 구구단의 2단 출력 예제
 */
public class FlatMap {
    public static void main(String[] args) {
        Observable.just("Hello")
                .flatMap(hello -> Observable.just("자바", "파이썬", "안드로이드").map(lang -> hello + ", " + lang))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        Observable.range(2, 1)
                .flatMap(
                        num -> Observable.range(1, 9)
                                .map(row -> num + " * " + row + " = " + num * row)
                )
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        Observable.range(2, 1)
                .flatMap(
                        data -> Observable.range(1, 9),
                        (sourceData, transformedData) ->
                                sourceData + " * " + transformedData + " = " + sourceData * transformedData
                )
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
