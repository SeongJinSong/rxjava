package com.rxjava.single_maybe_completable;

import com.rxjava.utils.DateUtil;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Single;

public class SingleLamda {
    public static void main(String[] args){
        Single<String> single = Single.create(emitter -> emitter.onSuccess(DateUtil.getNowDate()));

        single.subscribe(
                data -> Logger.log(LogType.ON_SUCCESS, "# 날짜시각: " + data),
                error -> Logger.log(LogType.ON_ERROR, error)
        );
    }
}
