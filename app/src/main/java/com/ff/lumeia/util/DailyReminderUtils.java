package com.ff.lumeia.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ff.lumeia.service.DailyReminderService;

import java.util.Calendar;

/**
 * Created by feifan on 16/2/17.
 * Contacts me:404619986@qq.com
 */
public class DailyReminderUtils {
    private DailyReminderUtils() {
        throw new UnsupportedOperationException("can not be instanced!");
    }

    public static void register(Context context) {
        Calendar today = Calendar.getInstance();
        Calendar now = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 13);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        if (now.after(today)) {
            return;
        }

        Intent intent = new Intent("me.drakeet.meizhi.alarm");
        intent.setClass(context, DailyReminderService.class);

        PendingIntent broadcast = PendingIntent.getBroadcast(context, 233, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        manager.set(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), broadcast);
    }
}
