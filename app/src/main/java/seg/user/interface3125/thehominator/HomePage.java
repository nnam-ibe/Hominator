package seg.user.interface3125.thehominator;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//import android.support.v4.app.NotificationCompat;


public class HomePage extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener {

    String username;
    Boolean flag = false;


    Intent oldIntent;
    Bundle old;

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    RelativeLayout noiseComplaintView;

    private TextView date;
    private TextView time;

    private SimpleDateFormat dateFormatter;
    private Calendar calValidator;
    private Calendar dateCal;
    private Calendar timeCal;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Feed_Fragment feed = new Feed_Fragment();

    DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        db = new DBHelper(this);

        oldIntent = getIntent();
        if(!(oldIntent == null)){
            old = oldIntent.getExtras();
            if ( old != null ) {
                flag = old.getBoolean("login");
            } else {
                flag = true;
            }
            System.out.println("Flag: "+flag);
            if(!flag){
                startActivity(new Intent(this,LoginActivity.class));
            }
            else{
                username = old.getString("username");
                db.updateUserLogin(username,db,1);

                mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerList = (ListView) findViewById(R.id.left_drawer);

                ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[6];

                drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_home_black_24dp, "Home");
                drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_warning_black_24dp, "Home Emergency");
                drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_thumb_down_black_24dp, "File A Complaint");
                drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_build_black_24dp, "Request A Service");
                drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_settings_black_18dp, "Settings");
                drawerItem[5] = new ObjectDrawerItem(R.drawable.ic_exit_to_app_black_24dp, "Logout");
                DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);

                mDrawerList.setAdapter(adapter);
                mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
                setFragment(feed, 0, Screen.feed);
                new Thread()
                {
                    public void run()
                    {
                        makeNotification();
                    }
                }.start();
            }
        } else{
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.home_page, menu);
//            restoreActionBar();
//            return true;
//        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
       /* switch ((view.getId())){
            case R.id.loginButton:
                startActivity(new Intent(this, LoginActivity.class));
                break;*/
       // }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomePage) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public void setFragment(Fragment fragment, int position, Screen screen) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(screen.toString())
                .commit();
        mDrawerList.setItemChecked(position, true);
        setTitle(mNavigationDrawerItemTitles[position]);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() <= 1) {
            this.finish();
        } else {
            try {
                getFragmentManager().popBackStack();
                FragmentManager fm = getFragmentManager();
                int entry =  fm.getBackStackEntryCount();
                String tag = fm.getBackStackEntryAt(entry-2).getName();
                Screen screen = Screen.enumValue(tag);
                mDrawerList.setItemChecked(screen.getPosition(), true);
                setTitle(mNavigationDrawerItemTitles[screen.getPosition()]);
            } catch (ArrayIndexOutOfBoundsException e) {
                this.finish();
            }
        }
    }

    public void repairScreen(View view){
        Intent intent = new Intent(this, repair_request.class);
        startActivity(intent);
    }

    public void partyScreen(View view) {
        Intent intent = new Intent(this, Party_Room.class);
        startActivity(intent);
    }

    public void contact(View view) {
        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }

    public void parking(View view) {
        Intent intent = new Intent(this, Parking.class);
        startActivity(intent);
    }

    public void emergencySubmit(View view) {
        EditText subject = (EditText) findViewById(R.id.emer_subjectTextView);
        EditText desc = (EditText) findViewById(R.id.emer_descTextView);

        if( subject.getText().toString().isEmpty() ) {
            toast("Please fill in a subject");
            return;
        } else if (desc.getText().toString().isEmpty() ) {
            toast("Please fill in a description");
            return;
        }
        toast("Your emergency has been submitted. We'll send someone to you right away.");
        onBackPressed();
    }

    public enum Screen{
        feed(0), emergency(1), complaint(2), service(3), bills(4), settings(5), logout(6);

        private int position;

        Screen(int position) {
            this.position=position;
        }

        private int getPosition(){return position;}

        public static Screen enumValue(String value){
            switch (value) {
                case "feed":
                    return Screen.feed;
                case "emergency":
                    return Screen.emergency;
                case "complaint":
                    return Screen.complaint;
                case "service":
                    return Screen.service;
                case "bills":
                    return Screen.bills;
                case "settings":
                    return Screen.settings;
                case "logout":
                    return Screen.logout;
            }
            return Screen.feed;
        }
    }

    private enum Item{
        none, noise, loitering, property, other;
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Feed_Fragment();
                setFragment(fragment, position, Screen.feed);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 1:
                fragment = new Emergency_Fragment();
                setFragment(fragment, position, Screen.emergency);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 2:
                fragment = new Complaint_Fragment();
                setFragment(fragment, position, Screen.complaint);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 3:
                fragment = new Service_Fragment();
                setFragment(fragment, position, Screen.service);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 4:
                String extras[] = setUpSettingExtras();
                Intent i = new Intent(this, SettingActivity.class);
                i.putExtra("username",extras[0]);
                i.putExtra("firstname",extras[1]);
                i.putExtra("lastname",extras[2]);
                i.putExtra("address",extras[3]);
                i.putExtra("email",extras[4]);
                startActivity(i);
                break;
            case 5:
                //while (getFragmentManager())
                getFragmentManager().popBackStack();
                flag = false;
                db.updateUserLogin(username,db,0);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    public void complaintOnClick(View view ) {
        if( optionIsValid() && dateIsValid() &&  editTextValid()  ) {
            toast("Your complaint has been submitted, you'll receive an email shortly.");
            onBackPressed();
        }
    }

    private boolean dateIsValid() {
        calValidator = Calendar.getInstance();
        if (dateCal.after(calValidator) ) {
            toast("Please choose a valid date.");
            return false;
        }
        return true;
    }

    private boolean editTextValid() {
        EditText editText = (EditText) findViewById(R.id.comp_editText);
        if ( editText.getText().toString().isEmpty() ) {
            toast("Please enter description.");
            return false;
        }
        return true;
    }

    private boolean optionIsValid() {
        Spinner sp = (Spinner) findViewById(R.id.complaintSpinner);
        if ( sp.getSelectedItemPosition() == 0 ) {
            toast("Please select a complaint type");
            return false;
        }
        return true;
    }

    private void setDateAndTime(){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateCal.set(year, monthOfYear, dayOfMonth);
                date.setText(dateFormatter.format(dateCal.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH) );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + 1000);

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                timeCal.set(Calendar.MINUTE, minute);
                time.setText(dateFormatter.getTimeInstance().format(timeCal.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), false);
    }

    public void setUpCompliant(View rootView){
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA);
        calValidator = Calendar.getInstance();
        dateCal=Calendar.getInstance();
        timeCal=Calendar.getInstance();

        date = (TextView) rootView.findViewById(R.id.complaint_date_set);
        date.setText(dateFormatter.getDateInstance().format(new Date()));

        time= (TextView) rootView.findViewById(R.id.complaint_time_set);
        time.setText(dateFormatter.getTimeInstance().format(new Date()));

        setDateAndTime();
    }

    private void makeNotification( ) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean on = preferences.getBoolean("SWITCH", true);
        if ( !on ) {
            return;
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        intent.putExtra("username", username);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n  = new Notification.Builder(this)
                .setSmallIcon(R.drawable.communitylogobig)
                .setContentTitle(getString(R.string.feed_bbq_title))
                .setContentText(getString(R.string.feed_bbq_desc))
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        notificationManager.notify(0, n);
        feed.addItem();
    }

    private String[] setUpSettingExtras(){
        UserInformation ui = db.getInfo(username);
        String extras[] = new String[5];
        extras[0] = ui.getUsername();
        extras[1] = ui.getFirstname();
        extras[2] = ui.getLastname();
        extras[3] = ui.getAddress();
        extras[4] = ui.getEmail();
        return  extras;
    }
}
