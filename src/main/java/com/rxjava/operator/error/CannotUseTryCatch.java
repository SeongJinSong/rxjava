package com.rxjava.operator.error;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;

public class CannotUseTryCatch {
    public static void main(String[] args) {
        try{
            Observable.just(2)
                    .map(num -> num / 0)
                    .subscribe(System.out::println);
        }catch (Exception e){
            Logger.log(LogType.PRINT, "# 에러 처리가 필요: " + e.getCause());
        }
    }
}
