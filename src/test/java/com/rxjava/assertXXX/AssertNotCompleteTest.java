package com.rxjava.assertXXX;

import com.rxjava.blockingXXX.SampleObservable;
import com.rxjava.utils.TimeUtil;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * assertNotComplete를 이용하여 A 지점과 B 지점의 매출 합계 처리가 지정된 시간안에 끝나지않는지 검증하는 예제
 */
public class AssertNotCompleteTest {
    @Test
    public void assertNotCompleteTest() {
        SampleObservable.getSalesOfBranchA()
                .zipWith(
                        SampleObservable.getSalesOfBranchB(),
                        (a, b) -> {
                            TimeUtil.sleep(1000L);
                            return a + b;
                        }
                )
                .test()
                .awaitDone(3000L, TimeUnit.MILLISECONDS)
                .assertNotComplete();
    }
}
