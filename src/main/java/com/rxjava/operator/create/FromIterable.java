package com.rxjava.operator.create;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;

public class FromIterable {
    public static void main(String[] args) {
        List<String> countries = Arrays.asList("Korea", "Canada", "USA", "Italy");
        Observable.fromIterable(countries)
                .subscribe(country -> Logger.log(LogType.ON_NEXT, country));
    }
}
