package seg.user.interface3125.thehominator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Preferences extends PreferenceFragment {
    private String username;
    private String fname;
    private String lname;
    private String address;
    private String email;

    public Preferences(){
        String[] vs = SettingActivity.getValues();
        username = vs[0];
        fname = vs[1];
        lname = vs[2];
        address = vs[3];
        email = vs[4];
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference pref =  findPreference("username");
        pref.setSummary(username);

        initValue(findPreference("firstname"), fname);
        initValue(findPreference("lastname"), lname);
        initValue(findPreference("email"), email);
        initValue(findPreference("address"), address);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
                SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                          String key) {
                        Preference pref = findPreference(key);
                        if (pref instanceof EditTextPreference) {
                            EditTextPreference etp = (EditTextPreference) pref;
                            if (!key.equals("email")){
                                pref.setSummary(etp.getText());
                            } else {
                                if ( RegisterActivity.validateEmail(etp.getText()) ){
                                    pref.setSummary(etp.getText());
                                } else {
                                }
                            }
                        }
                    }
                };

        preferences
                .registerOnSharedPreferenceChangeListener(spChanged);
    }

    public void initValue(Preference p, String value) {
        p.setSummary(value);
    }
}