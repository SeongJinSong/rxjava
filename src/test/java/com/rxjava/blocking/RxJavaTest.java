package com.rxjava.blocking;

import org.junit.jupiter.api.BeforeAll;

public class RxJavaTest {
    protected static SampleObservable sampleObservable;

    @BeforeAll
    public static void setup(){
        sampleObservable = new SampleObservable();
    }
}
