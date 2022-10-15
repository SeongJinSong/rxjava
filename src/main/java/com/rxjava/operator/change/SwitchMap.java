package com.rxjava.operator.change;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 원본 소스의 처리 속도가 빨라서 현재 처리 중이던 작업을 중단하는 예제
 *
 * switchMap 대신 concatMap을 쓸 경우 비효율적인 검색 예제
 *
 * switchMap을 이용한 효율적인 키워드 검색 예제
 */
public class SwitchMap {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " + TimeUtil.getCurrentTimeFormatted());
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .switchMap(
                        num -> Observable.interval(300L, TimeUnit.MILLISECONDS)
                                .take(10)
                                .skip(1)
                                .map(row -> num + " * " + row + " = " + num * row)
                )
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        Thread.sleep(5000);

        System.out.println("-----------------------------------");

        TimeUtil.start();
        Searcher searcher = new Searcher();
        // 사용자가 입력하는 검색어라고 가정한다.
        final List<String> keywords = Arrays.asList("M", "Ma", "Mal", "Malay");

        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .concatMap(data -> { /** concatMap을 사용했기때문에 매번 모든 키워드 검색 결과를  다 가져온다.*/
                    String keyword = keywords.get(data.intValue()); // 데이터베이스에서 조회한다고 가정한다.

                    return Observable.just(searcher.search(keyword))
                            .doOnNext(notUse -> System.out.println("================================================================="))
                            .delay(1000L, TimeUnit.MILLISECONDS);
                })
                .flatMap(resultList -> Observable.fromIterable(resultList))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> {},
                        () -> {
                            TimeUtil.end();
                            TimeUtil.takeTime();
                        }
                );


        TimeUtil.sleep(6000L);

        System.out.println("-----------------------------------");
        TimeUtil.start();
        Searcher searcher3 = new Searcher();
        // 사용자가 입력하는 검색어라고 가정한다.
        final List<String> keywords3 = Arrays.asList("M", "Ma", "Mal", "Malay");

        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .switchMap(data -> { /** switchMap을 사용했기 때문에 마지막 키워드를 사용한 최신 검색 결과만 가져온다 */
                    String keyword = keywords3.get(data.intValue()); // 데이터베이스에서 조회한다고 가정한다.

                    return Observable.just(searcher3.search(keyword))
                            .delay(1000L, TimeUnit.MILLISECONDS);
                })
                .flatMap(resultList -> Observable.fromIterable(resultList))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> {},
                        () -> {
                            TimeUtil.end();
                            TimeUtil.takeTime();
                        }
                );

        TimeUtil.sleep(2000L);
    }
}
