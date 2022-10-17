package com.rxjava.blockingXXX;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.rxjava.common.CarMaker;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * RxJava의 API를 사용하지 않은 Unit Test 예제
 */
public class UnitTestNotByRxJava {
    @Test
    public void getCarMakerStreamSyncTest(){
        List<CarMaker> carMakerList = new ArrayList<>();
        SampleObservable.getCarMakerStream()
                .subscribe(data -> carMakerList.add(data));
        assertThat(carMakerList.size(), is(5));
    }
}
