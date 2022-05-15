package com.rxjava.utils;

public class Logger {
    public static void log(String msg){
        String time = TimeUtil.getCurrentTimeFormatted();
        System.out.println(msg + " | " + Thread.currentThread().getName() + " | " + time);
    }

    public static void log(String msg, Object obj){
        String time = TimeUtil.getCurrentTimeFormatted();
        System.out.println(msg + " | " + Thread.currentThread().getName() + " | " + time + " | "  +obj);
    }

    public static void log(LogType logType){
        String time = TimeUtil.getCurrentTimeFormatted();
        System.out.println(logType.getLogType() + " | " + Thread.currentThread().getName() + " | " + time);
    }

    public static void log(LogType logType, Object obj){
        String time = TimeUtil.getCurrentTimeFormatted();
        System.out.println(logType.getLogType() + " | " + Thread.currentThread().getName() + " | " + time + " | "  +obj);
    }

    public static void don(Object obj){
        long time = System.currentTimeMillis() - TimeUtil.start;
        System.out.println("doOnNext() | " + Thread.currentThread().getName() + " | " + time + " | " + obj);
    }

    public static void on(Object obj){
        long time = System.currentTimeMillis() - TimeUtil.start;;
        System.out.println("onNext() | " + Thread.currentThread().getName() + " | " + time + " | "  + obj);
    }

    public static void oe(Object obj){
        long time = System.currentTimeMillis() - TimeUtil.start;;
        System.out.println("onError() | " + Thread.currentThread().getName() + " | " + time + " | "  + obj);
    }

    public static void oc(){
        long time = System.currentTimeMillis() - TimeUtil.start;
        System.out.println("onComplete() | " + Thread.currentThread().getName() + " | " + time + " | " );
    }

    public static void print(Object obj){
        System.out.println(obj);
    }
}
