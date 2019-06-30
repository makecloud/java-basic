package com.liuyihui.common.utiltest;

import com.liuyihui.common.util.TimeUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TSDPowerOnOffTest {
    private static final int MIN = 60000;
    private static final int DAY = 86400000;
    private static final byte DISABLE_TIME_SPAN = 2; //如果开机和关机时间间隔小于2分钟，默认为不关机


    @Test
    public synchronized void sendTSDPowerOnOff() {
        String startTime = "08:59:00";
        String closeTime = "20:28:00";


        long startTriggerTime = getSystemControlTime(startTime);
        long closeTriggerTime = getSystemControlTime(closeTime);

        if (startTriggerTime < closeTriggerTime) {
            startTriggerTime = startTriggerTime + DAY;
        }
        long startTimeSpan = (startTriggerTime - closeTriggerTime) / MIN;
        if (startTimeSpan < DISABLE_TIME_SPAN) {
            return;
        }


        //先计算sleep
        final int sleepSecond = (int) ((startTriggerTime - closeTriggerTime) / 1000);

        //关闭时间小于当前,+day
        if (System.currentTimeMillis() > closeTriggerTime) {
            closeTriggerTime = closeTriggerTime + DAY;
        }
        int delayPowerOffSecond = (int) ((closeTriggerTime - System.currentTimeMillis()) / 1000);

/*
        tsdPowerOffTimer = Observable.timer(delayPowerOffSecond, 1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        SignwayManager
                                .getInstance(PlayerManager.applicationContext)
                                .setDeepStandbyWakeTime(sleepSecond);
                    }
                });*/

        System.out.println("delay" + delayPowerOffSecond + ", sleep" + sleepSecond);
        System.out.println("set TSD3288 powerOnOff complete");
    }


    private long getSystemControlTime(String time) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = TimeUtils.string2Date(time, timeFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int closeHour = calendar.get(Calendar.HOUR_OF_DAY);
        int closeMinutes = calendar.get(Calendar.MINUTE);
        int closeSecond = calendar.get(Calendar.SECOND);

        calendar.setTimeInMillis(System.currentTimeMillis());
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentSecond = calendar.get(Calendar.SECOND);

        boolean hourSame = closeHour < currentHour;
        boolean minuteSame = closeHour == currentHour && closeMinutes < currentMinute;
        boolean secondSame = closeHour == currentHour
                && closeMinutes == currentMinute
                && closeSecond < currentSecond;

        if (hourSame || minuteSame || secondSame) {
//            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, closeHour);
        calendar.set(Calendar.MINUTE, closeMinutes);
        calendar.set(Calendar.SECOND, closeSecond);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
