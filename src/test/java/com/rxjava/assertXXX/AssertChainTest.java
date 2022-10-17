package com.rxjava.assertXXX;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * assertXXX 함수를 체인으로 연결하여 다양한 케이스의 검증을 할 수 있는 예제
 */
public class AssertChainTest {
    @Test
    public void assertChainTest() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .test()
                .awaitDone(1100L, TimeUnit.MILLISECONDS)
                .assertNotComplete()
                .assertNoErrors()
                .assertValueCount(5);
    }
}
