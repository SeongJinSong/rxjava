package com.rxjava.assertXXX;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * assertNoValues를 이용해 통지 시점까지 통지된 데이터가 없는지 검증하는 예제
 */
public class AssertNoValuesTest {
    @Test
    public void assertNoValuesTest(){
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .filter(data -> data > 5)
                .test()
                .awaitDone(1000L, TimeUnit.MILLISECONDS)
                .assertNoValues();
    }
}
