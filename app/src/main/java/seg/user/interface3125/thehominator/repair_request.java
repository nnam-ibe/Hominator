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

    private enum Item {
        none, plumbing, electrical, other;
    }

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_request);
        addListenerOnSpinnerItemSelection();
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

    private void clearView() {
        LinearLayout fixed1 = (LinearLayout)findViewById(R.id.fixed1);
        fixed1.removeAllViews();

        LinearLayout fixed2 = (LinearLayout)findViewById(R.id.fixed1);
        fixed2.removeAllViews();
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
