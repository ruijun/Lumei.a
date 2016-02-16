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

package com.ff.lumeia.ui.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;

import com.ff.lumeia.R;
import com.ff.lumeia.presenter.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 基础Activity
 * Created by feifan on 16/1/26.
 * Contacts me:404619986@qq.com
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T presenter;
    protected ActionBar actionBar;
    protected boolean isToolBarHiding = false;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.app_bar)
    protected AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        initPresenter();
        checkPresenterIsNull();
        initToolbar();
    }

    protected abstract int provideContentViewId();

    protected abstract void initPresenter();

    private void checkPresenterIsNull() {
        if (presenter == null) {
            throw new IllegalStateException("presenter must be init in initPresenter() method ");
        }
    }

    protected void initToolbar() {
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new IllegalStateException("Toolbar is null!");
        }
        actionBar.setDisplayHomeAsUpEnabled(canBack());
    }

    protected int provideMenuResId() {
        return -1;
    }

    protected boolean canBack() {
        return true;
    }

    protected void hideOrShowToolBar() {
        appBar.animate()
                .translationY(isToolBarHiding ? 0 : -appBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isToolBarHiding = !isToolBarHiding;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (provideMenuResId() < 0) {
            return true;
        }
        getMenuInflater().inflate(provideMenuResId(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        presenter.release();
    }
}
