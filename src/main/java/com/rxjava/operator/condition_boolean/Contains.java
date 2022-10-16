package com.rxjava.operator.condition_boolean;

import com.rxjava.common.CarMaker;
import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

/**
 * 통지된 데이터 중에 파라미터로 입력한 조건에 맞는 데이터가 있는지 판단하는 예제
 */
public class Contains {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .contains(CarMaker.SAMSUNG)
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
