package com.rxjava.single_maybe_completable;

import com.rxjava.utils.DateUtil;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Single;

public class SingleJust {
    public static void main(String[] args){
        Single.just(DateUtil.getNowDate())
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, "# 날짜시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
    }
}
