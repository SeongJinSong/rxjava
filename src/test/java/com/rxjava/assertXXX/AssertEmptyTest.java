package com.rxjava.assertXXX;

import com.rxjava.blockingXXX.SampleObservable;
import com.rxjava.common.Car;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * assertEmpty를 사용하여 해당 시점까지 통지된 데이터가 있는지 검증하는 예제
 */
public class AssertEmptyTest {
    // 테스트 실패 예제
    @Test
    public void getCarStreamEmptyFailTest(){
        // when
        Observable<Car> observable = SampleObservable.getCarStream();
        TestObserver<Car> observer = observable.test();

        // then
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
    }
    // 테스트 성공 예제
    @Test
    public void getCarStreamEmptySuccessTest(){
        // when
        Observable<Car> observable = SampleObservable.getCarStream();
        TestObserver<Car> observer = observable.delay(1000L, TimeUnit.MILLISECONDS).test();

        // then
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
    }
}
