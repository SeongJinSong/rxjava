package com.rxjava.single_maybe_completable;

import com.rxjava.utils.DateUtil;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Maybe;

public class MaybeLamda {
    public static void main(String[] args){
        Maybe<String> maybe = Maybe.create(emitter -> {
            emitter.onSuccess(DateUtil.getNowDate());
//            emitter.onComplete();
        });

        maybe.subscribe(
                data -> Logger.log(LogType.ON_SUCCESS, "# 현재 날짜시각: " + data),
                error -> Logger.log(LogType.ON_ERROR, error),
                () -> Logger.log(LogType.ON_COMPLETE)
        );
    }
}
