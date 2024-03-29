package com.rxjava.blockingXXX;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.rxjava.common.Car;
import org.junit.jupiter.api.Test;

/**
 * blockingLast를 사용한 통지된 마지막 데이터를 테스트하는 예제
 */
public class BlockingLastTest {
    // Car 리스트 중 마지막 Car 테스트
    @Test
    public void getCarStreamLastTest() {
        // when
        Car car = SampleObservable.getCarStream().blockingLast();
        String actual = car.getCarName();

        // then
        assertThat(actual, is("SM5"));
    }

    // A 지점의 월간 매출액 중 6월 달 매출액 테스트
    @Test
    public void getSalesOfBranchALastTest() {
        // when
        int actual = SampleObservable.getSalesOfBranchA()
                .take(6)
                .blockingLast();

        // then
        assertThat(actual, is(40_000_000));
    }
}
