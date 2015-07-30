package seg.user.interface3125.thehominator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;


/**
 * Created by nnamdi on 15-07-28.
 */
public class SettingActivity extends PreferenceActivity {

    protected Preferences preferences;
    private static String uname;
    private static String fname;
    private static String lname;
    private static String addr;
    private static String uEmail;
    private static Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uname = getIntent().getStringExtra("username");
        fname = getIntent().getStringExtra(getString(R.string.pref_fname));
        lname = getIntent().getStringExtra("lastname");
        addr = getIntent().getStringExtra("address");
        uEmail = getIntent().getStringExtra("email");
        context = this;
        preferences = new Preferences();
        getFragmentManager().beginTransaction().replace(android.R.id.content, preferences).commit();
    }

    public static String[] getValues(){
        return new String[]{uname, fname, lname, addr, uEmail};
    }


}
