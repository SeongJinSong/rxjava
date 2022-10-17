package com.rxjava.blockingXXX;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.rxjava.common.Car;
import org.junit.jupiter.api.Test;

/**
 * blockingFirst를 사용한 통지된 첫번째 데이터를 테스트하는 예제
 */
public class BlockingFirstTest {
    // Car 리스트 중에서 첫번째 Car를 테스트
    @Test
    public void getCarStreamFirstTest(){
        // when
        Car car = SampleObservable.getCarStream().blockingFirst();
        String actual = car.getCarName();

        // then
        assertThat(actual, is("말리부"));
    }

    @Test
    public void getSalesOfBranchAFirstTest(){
        // when
        int actual = SampleObservable.getSalesOfBranchA()
                .take(6)
                .blockingFirst();

        // then
        assertThat(actual, is(15_000_000));
    }
}
