package com.rxjava.operator.create;

import com.rxjava.utils.LogType;
import com.rxjava.utils.Logger;
import java.util.concurrent.Future;

public class FromFutureAsync {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        CarRepairShop shop = new CarRepairShop();

        //차량 수리비
        Future<Integer> future =  shop.getCarRepairCostAsync(10);

        //회사에 병가 신청
        requestSickLeave("20170903-01");

        //보험 접수
        requestInsurance("44나44444");

        try {
            int carRepairCost = future.get();
            Logger.log(LogType.PRINT, "# (1) 차량 수리비 계산이 완료되었습니다.");
            Logger.log(LogType.PRINT, "# 차량 수리비는 " + carRepairCost + "원 입니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        double executeTime = (endTime - startTime) / 1000.0;
        System.out.println();
        System.out.println("# 처리 시간: " + executeTime + "초");
    }

    private static void requestSickLeave(String empNumber) {
        try {
            Thread.sleep(1000);
            Logger.log(LogType.PRINT, "# (2) 병가 신청이 완료되었습니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void requestInsurance(String carNumber) {
        try {
            Thread.sleep(1000);
            Logger.log(LogType.PRINT, "# (3) 보험 접수가 완료 되었습니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
