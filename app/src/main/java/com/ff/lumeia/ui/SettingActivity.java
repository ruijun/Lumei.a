package com.ff.lumeia.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.ff.lumeia.R;
import com.ff.lumeia.presenter.SettingPresenter;
import com.ff.lumeia.ui.base.BaseActivity;
import com.ff.lumeia.view.ISettingView;

public class SettingActivity extends BaseActivity<SettingPresenter> implements ISettingView {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initPresenter() {
        presenter = new SettingPresenter(this, this);
        presenter.init();
    }

    @Override
    public void init() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.setting_container, new SettingFragment())
                .commit();
    }

    public static class SettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_preference);
        }
    }

}
