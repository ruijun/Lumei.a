package com.ff.lumeia.presenter;

import android.content.Context;
import android.media.MediaPlayer;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.ff.lumeia.view.IWebVideoView;

/**
 * Created by feifan on 16/2/15.
 * Contacts me:404619986@qq.com
 */
public class WebVideoPresenter extends BasePresenter<IWebVideoView> {
    public WebVideoPresenter(IWebVideoView iView, Context context) {
        super(iView, context);
    }

    public void loadWebVideo(WebView webView, String url) {
        webView.setWebChromeClient(new Chrome());
        webView.loadUrl(url);
    }

    private class Chrome extends WebChromeClient
            implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer player) {
            if (player != null) {
                if (player.isPlaying()) player.stop();
                player.reset();
                player.release();
                player = null;
            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            iView.showProgressBar(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            iView.setWebTitle(title);
        }
    }
}
