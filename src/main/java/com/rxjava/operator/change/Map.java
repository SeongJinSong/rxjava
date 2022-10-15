package com.rxjava.operator.change;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;

public class Map {
    public static void main(String[] args) {
        List<Integer> oddList = Arrays.asList(1, 3, 5, 7);
        Observable.fromIterable(oddList)
                .map(num -> "1을 더한 결과: " + (num + 1))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");

        Observable.just("korea", "america", "canada", "paris", "japan", "china")
                .filter(country -> country.length() == 5 )
                .map(country -> country.toUpperCase().charAt(0) + country.substring(1))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
