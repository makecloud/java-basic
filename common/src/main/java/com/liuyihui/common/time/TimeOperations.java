package com.liuyihui.common.time;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        String times = "16:59:00";
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times.split(":")[0]));
        calendar2.set(Calendar.MINUTE, Integer.parseInt(times.split(":")[1]));
        calendar2.set(Calendar.SECOND, Integer.parseInt(times.split(":")[2]));
        System.out.println((calendar2.getTimeInMillis() - System.currentTimeMillis()) / 1000);
    }

    /**
     * 按时间触发
     */
    @Test
    public void timedRun() {
        long flag = 0L;
        while (true) {
//            Long nowMills = 1586852630000l;

            Long nowMills = System.currentTimeMillis();

            if ((nowMills - flag) < 5) {
                System.out.println("捕获到5秒的非毫秒精确倍秒,<5ms,忽略");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            if (nowMills % 5000 < 5) {
                System.out.println("捕获5秒的非毫秒精确倍秒 " + nowMills);
                flag = nowMills;
            }


            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试时间字符串转date后，date年份日期是现在还是1970
     */
    @Test
    public void testDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("HH:mm:ss", Locale.CHINESE).parse("18:45:2");
        } catch (ParseException e) {
            return;
        }


        Calendar calendar = Calendar.getInstance();

        System.out.println(new Date());
        System.out.println(date);
        System.out.println(date.getHours());
        System.out.println(date.getMinutes());
        System.out.println(date.getSeconds());
        System.out.println(calendar.getTime());
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());


        calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
        calendar.set(Calendar.MINUTE, date.getMinutes());
        calendar.set(Calendar.SECOND, date.getSeconds());
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println(calendar.getTime());
        System.out.println(calendar.getTimeInMillis());
        System.out.println(System.currentTimeMillis());
    }
}
