package seg.user.interface3125.thehominator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Enumeration;


public class repair_request extends Activity {

    private LinearLayout fixed2;
    private LinearLayout fixed1;
    private CheckBox c1;
    private CheckBox c2;
    private CheckBox c3;
    private EditText et1;
    private EditText et2;

    private boolean isTwo = true;
    private enum Item {
        none, plumbing, electrical, other;
    }

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_request);
        addListenerOnSpinnerItemSelection();
        fixed1 = (LinearLayout) findViewById(R.id.fixed1);
        fixed2 = (LinearLayout) findViewById(R.id.fixed2);
        c1 = (CheckBox) findViewById(R.id.cB1);
        c2 = (CheckBox) findViewById(R.id.cB2);
        c3 = (CheckBox)findViewById(R.id.cB3);
        et1 = (EditText)findViewById(R.id.repair_editText);
        et2 = (EditText) findViewById(R.id.repair_editText1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_repair_request, menu);
        return true;
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(this));
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

    public void submitR(View view) {
        if ( validate() ) {
            toast("Your request has been submitted");
            finish();
        }
    }

    private boolean validate() {
        if ( (fixed1.getVisibility() == View.INVISIBLE) && (fixed2.getVisibility() == View.INVISIBLE)  ) {
            toast("Select an option.");
            return false;
        }
        if ( fixed1.getVisibility() == View.VISIBLE ) {
            return validateFixed1();
        } else if ( fixed2.getVisibility() == View.VISIBLE ) {
            return validateFixed2();
        }
        return false;
    }

    private boolean validateFixed1() {
        if ( !c1.isChecked() && !c2.isChecked() && !c3.isChecked() ) {
            toast("You must check at least one box.");
            return false;
        }
        if ( et1.getText().length() <= 0 ) {
            toast("Please fill in the field");
            return false;
        }
        return true;
    }

    private boolean validateFixed2() {
        if ( et2.getText().length() <= 0 ) {
            toast("Please fill in the field");
            return false;
        }
        return true;
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        private Context context;
        private Item item = Item.none;

        public CustomOnItemSelectedListener(Context context) {
            this.context = context;
        }

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            switch (pos) {
                case 1:
                    plumbing();
                    break;
                case 2:
                    electrical();
                    break;
                case 3:
                    other();
                    break;

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

        public void plumbing() {
            if ( item == Item.plumbing) {
                return;
            } else {
                item = Item.plumbing;
            }

            LinearLayout fixed2 = (LinearLayout) findViewById(R.id.fixed2);
            fixed2.setVisibility(View.INVISIBLE);

            LinearLayout fixed1 = (LinearLayout) findViewById(R.id.fixed1);
            fixed1.setVisibility(View.VISIBLE);

        }

        public void electrical() {
            if ( item == Item.electrical) {
                return;
            } else {
                item = Item.electrical;
            }

            LinearLayout fixed1 = (LinearLayout) findViewById(R.id.fixed1);
            fixed1.setVisibility(View.INVISIBLE);

            LinearLayout fixed2 = (LinearLayout) findViewById(R.id.fixed2);
            fixed2.setVisibility(View.VISIBLE);
        }

        public void other() {
            if ( item == Item.other) {
                return;
            } else {
                item = Item.other;
            }

            LinearLayout fixed1 = (LinearLayout) findViewById(R.id.fixed1);
            fixed1.setVisibility(View.INVISIBLE);

            LinearLayout fixed2 = (LinearLayout) findViewById(R.id.fixed2);
            fixed2.setVisibility(View.VISIBLE);
        }

    }
}
