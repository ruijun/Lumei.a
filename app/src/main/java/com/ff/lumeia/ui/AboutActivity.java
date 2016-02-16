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

package com.ff.lumeia.ui;

import android.content.Intent;
import android.widget.TextView;

import com.ff.lumeia.BuildConfig;
import com.ff.lumeia.LumeiaConfig;
import com.ff.lumeia.R;
import com.ff.lumeia.presenter.AboutPresenter;
import com.ff.lumeia.ui.base.BaseActivity;
import com.ff.lumeia.view.IAboutView;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity<AboutPresenter> implements IAboutView {

    @Bind(R.id.text_version)
    TextView textVersion;

    @OnClick(R.id.fab_thumb_up)
    void fabClick() {
        showMyGitHub();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initPresenter() {
        presenter = new AboutPresenter(this, this);
        presenter.init();
    }

    @Override
    public void init() {
        textVersion.setText(String.format("Version: %s", BuildConfig.VERSION_NAME));
    }

    @Override
    public void showMyGitHub() {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(LumeiaConfig.WEB_TITLE, getString(R.string.app_name));
        intent.putExtra(LumeiaConfig.WEB_URL, getString(R.string.github_lumeia));
        startActivity(intent);
    }
}
