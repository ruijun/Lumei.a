/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 * Copyright (C) 2015 GuDong <maoruibin9035@gmail.com>
 * Copyright (C) 2016 Panl <panlei106@gmail.com>
 * CopyRight (C) 2016 ChristianFF <feifan0322@gmail.com>
 *
 * Lumei.a is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Lumei.a is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Lumei.a.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ff.lumeia;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.ff.lumeia.util.SharedPreferenceUtils;
import com.litesuits.orm.LiteOrm;

/**
 * Created by feifan on 16/1/26.
 * Contacts me:404619986@qq.com
 */
public class LumeiaApp extends Application {
    private static final String DB_NAME = "Lumei.a.db";
    public static Context appContext;
    public static LiteOrm myDatabase;
    public static Drawable meiziDeliverDrawable;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        SharedPreferenceUtils.init(this);
        createDB();
    }

    private void createDB() {
        myDatabase = LiteOrm.newSingleInstance(appContext, DB_NAME);
        if (BuildConfig.DEBUG) {
            myDatabase.setDebugged(true);
        }
    }
}
