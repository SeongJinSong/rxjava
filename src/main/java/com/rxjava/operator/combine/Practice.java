package com.rxjava.operator.combine;

import com.rxjava.common.SampleData;
import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        /**
         * 1. zip을 이용하여 각 지점별 월별 매출
         * (SampleData.salesOfBranchA, SampleData.salesOfBranchB, SamepleData.salesOfBranchC)을
         * 각각의 월별로 합산하여 통합 월별 매출을 출력하세요.
         *
         * 지점별 월별 매출 List(salesOfBranchA, salesOfBranchB, salesOfBranchC는 index가 빠른 요소부터 1월입니다.)
         */
        Observable<Integer> observable1 = Observable.fromIterable(SampleData.salesOfBranchA);
        Observable<Integer> observable2 = Observable.fromIterable(SampleData.salesOfBranchB);
        Observable<Integer> observable3 = Observable.fromIterable(SampleData.salesOfBranchC);
        Observable<Integer> observable4 = Observable.range(1, 12);
        Observable.zip(observable1, observable2, observable3, observable4,
                        (data1, data2, data3, data4) -> data4 + "월 통합매출: " + ((int)data1 + (int)data2 + (int)data3))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        System.out.println("-----------------------------------");
        List<Observable<Integer>> salesList = Arrays.asList(
                Observable.fromIterable(SampleData.salesOfBranchA),
                Observable.fromIterable(SampleData.salesOfBranchB),
                Observable.fromIterable(SampleData.salesOfBranchC)
        );

        Observable.zip(salesList, sales -> (int)sales[0] + (int)sales[1] + (int)sales[2])
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));


    }
}
