package seg.user.interface3125.thehominator;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class HomePage extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    DBHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        db = new DBHelper(this);

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[7];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_home_black_24dp, "Home");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_drawer, "Home Emergency");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_drawer, "File A Complaint");
        drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_build_black_24dp, "Request A Service");
        drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_payment_black_18dp, "Bills");
        drawerItem[5] = new ObjectDrawerItem(R.drawable.ic_settings_black_18dp, "Settings");
        drawerItem[6] = new ObjectDrawerItem(R.drawable.ic_exit_to_app_black_24dp, "Logout");
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        setFragment(new Feed_Fragment(), 0, Screen.feed);
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
                fragment = new Bills_Fragment();
                setFragment(fragment, position, Screen.bills);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 5:
                fragment = new Settings_Fragment();
                setFragment(fragment, position, Screen.settings);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
            case 6:
                fragment = new Logout_Fragment();
                setFragment(fragment, position, Screen.logout);
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
        }
    }
}
