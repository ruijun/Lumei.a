package com.ff.lumeia.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.ff.lumeia.LumeiaApp;
import com.ff.lumeia.service.DailyReminderReceiver;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by feifan on 16/2/17.
 * Contacts me:404619986@qq.com
 */
public class DailyReminderUtils {
    private DailyReminderUtils() {
        throw new UnsupportedOperationException("can not be instanced!");
    }

    public static void register() {
        Intent intent = new Intent(LumeiaApp.appContext, DailyReminderReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(LumeiaApp.appContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstTime = SystemClock.elapsedRealtime();
        long systemTime = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 9);
        calendar.set(Calendar.SECOND, 30);
        calendar.set(Calendar.MILLISECOND, 0);
        long selectTime = calendar.getTimeInMillis();
        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if (systemTime > selectTime) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            selectTime = calendar.getTimeInMillis();
        }
        firstTime += selectTime - systemTime;
        AlarmManager manager = (AlarmManager) LumeiaApp.appContext.getSystemService(Context.ALARM_SERVICE);
        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
    }
}
