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

package com.ff.lumeia.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 动态ImageView
 * Created by feifan on 16/1/29.
 * Contacts me:404619986@qq.com
 */
public class VideoImageView extends ImageView implements Animator.AnimatorListener {

    private boolean scale = false;


    public VideoImageView(Context context) {
        this(context, null);
    }


    public VideoImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public VideoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        nextAnimation();
    }


    private void nextAnimation() {
        AnimatorSet anim = new AnimatorSet();
        if (scale) {
            anim.playTogether(ObjectAnimator.ofFloat(this, "scaleX", 1.5f, 1f),
                    ObjectAnimator.ofFloat(this, "scaleY", 1.5f, 1f));
        } else {
            anim.playTogether(ObjectAnimator.ofFloat(this, "scaleX", 1, 1.5f),
                    ObjectAnimator.ofFloat(this, "scaleY", 1, 1.5f));
        }

        anim.setDuration(10240);
        anim.addListener(this);
        anim.start();
        scale = !scale;
    }


    @Override
    public void onAnimationCancel(Animator arg0) {
    }


    @Override
    public void onAnimationEnd(Animator animator) {
        nextAnimation();
    }


    @Override
    public void onAnimationRepeat(Animator arg0) {
    }


    @Override
    public void onAnimationStart(Animator arg0) {
    }
}
