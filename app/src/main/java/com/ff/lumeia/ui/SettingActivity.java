package com.ff.lumeia.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.ff.lumeia.R;
import com.ff.lumeia.presenter.SettingPresenter;
import com.ff.lumeia.ui.base.BaseActivity;
import com.ff.lumeia.util.DailyReminderUtils;
import com.ff.lumeia.util.SharedPreferenceUtils;
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

    public static class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        private SwitchPreference dailyPreference;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_preference);

            dailyPreference = (SwitchPreference) findPreference("daily_reminder");
            boolean isOpen = SharedPreferenceUtils.getBoolean("daily_reminder", true);
            dailyPreference.setChecked(isOpen);
            dailyPreference.setOnPreferenceChangeListener(this);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            if (preference == dailyPreference) {
                boolean isOpen = (boolean) o;
                if (isOpen) {
                    DailyReminderUtils.register();
                }
                SharedPreferenceUtils.putBoolean("daily_reminder", isOpen);
                dailyPreference.setChecked(isOpen);
                return true;
            }
            return false;
        }
    }

}
