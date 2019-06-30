package com.liuyihui.common.time;

import org.junit.Test;

import java.util.Calendar;

public class TimeOperations {

    @Test
    public void test1() {
        //明天xx点的毫秒
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        System.out.println(calendar.getTime());
        long countDown = (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000;
        System.out.println(countDown);


        //
        String times="16:59:00";
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times.split(":")[0]));
        calendar2.set(Calendar.MINUTE, Integer.parseInt(times.split(":")[1]));
        calendar2.set(Calendar.SECOND, Integer.parseInt(times.split(":")[2]));
        System.out.println((calendar2.getTimeInMillis()-System.currentTimeMillis())/1000);
    }
}
