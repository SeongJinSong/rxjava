package com.rxjava.operator.utility;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import com.rxjava.utils.NumberUtil;
import com.rxjava.utils.TimeUtil;
import io.reactivex.Observable;

/**
 * timeInterval을 이용해서 데이터가 통지되는데 걸린 시간을 통지하는 예제
 */
public class TimeInverval {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7, 9)
            .delay(item -> {
                TimeUtil.sleep(NumberUtil.randomRange(100, 1000));
                return Observable.just(item);
            })
            .timeInterval()
            .subscribe(
                    timed -> Logger.log(LogType.ON_NEXT, "# 통지하는데 걸린 시간: " + timed.time() + "\t# 통지된 데이터: " + timed.value())
            );
    }
}
