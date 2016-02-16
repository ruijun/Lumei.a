package com.ff.lumeia.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.ff.lumeia.LumeiaConfig;
import com.ff.lumeia.R;
import com.ff.lumeia.presenter.WebVideoPresenter;
import com.ff.lumeia.ui.base.BaseActivity;
import com.ff.lumeia.ui.widget.WebVideoView;
import com.ff.lumeia.view.IWebVideoView;

import butterknife.Bind;

public class WebVideoActivity extends BaseActivity<WebVideoPresenter> implements IWebVideoView {

    @Bind(R.id.web_video_view)
    WebVideoView webVideoView;
    @Bind(R.id.number_progress_bar)
    NumberProgressBar numberProgressBar;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web_video;
    }

    @Override
    protected void initPresenter() {
        presenter = new WebVideoPresenter(this, this);
        presenter.init();
    }

    @Override
    public void init() {
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        appBar.setBackgroundColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBar.setElevation(0);
        }

        setTitle(getIntent().getStringExtra(LumeiaConfig.WEB_TITLE));
        presenter.loadWebVideo(webVideoView, getIntent().getStringExtra(LumeiaConfig.WEB_URL));
    }

    public static void start(Context context, String title, String url) {
        Intent starter = new Intent(context, WebVideoActivity.class);
        starter.putExtra(LumeiaConfig.WEB_TITLE, title);
        starter.putExtra(LumeiaConfig.WEB_URL, url);
        context.startActivity(starter);
    }

    @Override
    public void showProgressBar(int progress) {
        if (numberProgressBar == null) return;
        numberProgressBar.setProgress(progress);
        if (progress == 100) {
            numberProgressBar.setVisibility(View.GONE);
        } else {
            numberProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setWebTitle(String title) {
        setTitle(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webVideoView != null) {
            webVideoView.resumeTimers();
            webVideoView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webVideoView != null) {
            webVideoView.onPause();
            webVideoView.pauseTimers();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webVideoView != null) {
            webVideoView.setWebViewClient(null);
            webVideoView.setWebChromeClient(null);
            webVideoView.destroy();
        }
    }
}
