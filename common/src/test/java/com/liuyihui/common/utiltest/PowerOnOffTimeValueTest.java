package com.liuyihui.common.utiltest;

import com.liuyihui.common.util.TimeUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PowerOnOffTimeValueTest {

    private static final int MIN = 60000;
    private static final int DAY = 86400000;
    private static final byte DISABLE_TIME_SPAN = 2; //如果开机和关机时间间隔小于2分钟，默认为不关机


    @Test
    public synchronized void sendTSDPowerOnOff() {
        String startTime = "12:45:00";
        String closeTime = "23:00:00";


        long startTriggerTime = getPowerOnOffTimeMillis(startTime);
        long closeTriggerTime = getPowerOnOffTimeMillis(closeTime);

        if (startTriggerTime < closeTriggerTime) {
            startTriggerTime = startTriggerTime + DAY;
        }

        long startTimeSpan = (startTriggerTime - closeTriggerTime) / MIN;
        if (startTimeSpan < DISABLE_TIME_SPAN) {
            return;
        }

        System.out.println(startTriggerTime/1000 + "");
        System.out.println(closeTriggerTime/1000 + "");

        //先计算sleep
        final int sleepSecond = (int) ((startTriggerTime - closeTriggerTime) / 1000);

        //关闭时间小于当前,+ day
        if (System.currentTimeMillis() > closeTriggerTime) {
            closeTriggerTime = closeTriggerTime + DAY;
        }
        int delayPowerOffSecond = (int) ((closeTriggerTime - System.currentTimeMillis()) / 1000);


        /*powerOffTimer = Observable.timer(delayPowerOffSecond, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Intent intentSetOff = new Intent();
                        intentSetOff.setAction("wits.com.simahuan.shutdown");
                        context.sendBroadcast(intentSetOff);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(TAG, "sendPowerOnOff: ", throwable);
                    }
                });*/

        System.out.println("delay poweroff sec " + delayPowerOffSecond + ", sleepSec " + sleepSecond);
        System.out.println("set powerOnOff complete");
    }


    private long getPowerOnOffTimeMillis(String time) {
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
        boolean secondSame = closeHour == currentHour && closeMinutes == currentMinute && closeSecond < currentSecond;

        if (hourSame || minuteSame || secondSame) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, closeHour);
        calendar.set(Calendar.MINUTE, closeMinutes);
        calendar.set(Calendar.SECOND, closeSecond);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
