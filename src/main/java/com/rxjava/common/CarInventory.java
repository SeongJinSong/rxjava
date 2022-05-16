package com.rxjava.common;

public class CarInventory {
    private int incomingCount;
    private int totalCount;

    public CarInventory(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getExpectedTotalCount(int incomingCount){
        return totalCount + incomingCount;
    }
}