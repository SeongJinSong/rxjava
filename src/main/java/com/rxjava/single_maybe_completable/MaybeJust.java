package com.rxjava.single_maybe_completable;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Maybe;

public class MaybeJust {
    public static void main(String[] args){
//        Maybe.just(DateUtil.getNowDate())
//                .subscribe(
//                        data -> Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: " + data),
//                        error -> Logger.log(LogType.ON_ERROR, error),
//                        () -> Logger.log(LogType.ON_COMPLETE)
//                );

        Maybe.empty()
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
