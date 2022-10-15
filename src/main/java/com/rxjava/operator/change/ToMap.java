package com.rxjava.operator.change;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.Map;

/**
 * 통지된 데이터에서 map의 키를 생성한 후, 각각의 키별로 원본 통지 데이터를 매핑해서 Map으로 반환하는 예제
 *
 * 원본 데이터를 변환한 값과 각각의 key를 매핑하여 Map으로 통지하는 예제
 */
public class ToMap {
    public static void main(String[] args) {
        Single<Map<String, String>> single =
                Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                        .toMap(data -> data.split("-")[0]); // 반환값은 Map의 key가 된다.

        single.subscribe(map -> Logger.log(LogType.ON_NEXT, map));

        System.out.println("-----------------------------------");

        Single<Map<String, String>> single2 = Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                .toMap(
                        data -> data.split("-")[0],
                        data -> data.split("-")[1]
                );

        single2.subscribe(map -> Logger.log(LogType.ON_NEXT, map));
    }
}
