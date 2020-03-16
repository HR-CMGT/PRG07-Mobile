package nl.hr.cmtprg037.week5_workshop_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    public static boolean getHelloSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MainActivity.PREF_KEY_HELLO,
                MainActivity.PREF_DEFAULT_HELLO);
    }
}
