package com.rxjava.subject;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectEx {
    public static void main(String[] args) {
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.onNext(3000);
        subject.onNext(2500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 1 : " + price));
        subject.onNext(3500);

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 2 : " + price));
        subject.onNext(3300);

        subject.onComplete();

        subject.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 3 : " + price));

        System.out.println("-----------------------------------");

        ReplaySubject<Integer> subject2 = ReplaySubject.createWithSize(2);
        subject2.onNext(3000);
        subject2.onNext(2500);

        subject2.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 1 : " + price));
        subject2.onNext(3500);

        subject2.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 2 : " + price));
        subject2.onNext(3300);

        subject2.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 3 : " + price));
        subject2.onNext(3400);

        subject2.onComplete();

        subject2.subscribe(price -> Logger.log(LogType.ON_NEXT, "# 소비자 4 : " + price));
    }
}
