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

package com.ff.lumeia.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * 提示工具类
 * Created by feifan on 16/1/27.
 * Contacts me:404619986@qq.com
 */
public class TipsUtils {
    private TipsUtils() {
        throw new UnsupportedOperationException("can not be instanced!");
    }


    public static void showSnackWithAction(View view, String message, int duration, String tip, View.OnClickListener onClickListener) {
        Snackbar.make(view, message, duration)
                .setAction(tip, onClickListener)
                .show();
    }

    public static void showSnack(View view, String message, int duration) {
        Snackbar.make(view, message, duration)
                .show();
    }
}
