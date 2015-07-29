package seg.user.interface3125.thehominator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


/**
 * Created by nnamdi on 15-07-28.
 */
public class SettingActivity extends PreferenceActivity {

    protected Preferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = new Preferences();
        getFragmentManager().beginTransaction().replace(android.R.id.content, preferences).commit();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", "Bob");
        editor.commit();
    }

}
