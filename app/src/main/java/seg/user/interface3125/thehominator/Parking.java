package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Parking extends Activity {

    private EditText vMaked;
    private EditText vModeled;
    private EditText vPlated;
    private EditText vColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        vMaked = (EditText) findViewById(R.id.vMaked);
        vModeled = (EditText) findViewById(R.id.vModeled);
        vPlated = (EditText) findViewById(R.id.vPlated);
        vColor = (EditText) findViewById(R.id.vColored);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parking, menu);
        return true;
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

    public void parkingOnClick(View view) {
       if (validate()) {
           Toast.makeText(this, "Your request has been submitted, you'll receive an email shortly.", Toast.LENGTH_LONG).show();
           finish();
       }
    }

    private boolean validate() {
        if ( vMaked.getText().length() <= 0 ) {
            Toast.makeText(this, "Please fill out your vehicle make.", Toast.LENGTH_LONG).show();
            return false;
        } else if ( vModeled.getText().length() <= 0 ) {
            Toast.makeText(this, "Please fill out your vehicle model number.", Toast.LENGTH_LONG).show();
            return false;
        } else if ( vPlated.getText().length() <=1  ) {
            Toast.makeText(this, "Please fill out your vehicle plate number.", Toast.LENGTH_LONG).show();
            return false;
        } else if ( vColor.getText().length() <= 0 ) {
            Toast.makeText(this, "Please fill out your vehicle color.", Toast.LENGTH_LONG).show();
            return false;
        } else if ( vColor.getText().toString().contains(" ") ) {
            Toast.makeText(this, "Please enter a valid color.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
