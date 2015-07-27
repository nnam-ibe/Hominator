package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Party_Room extends Activity {

    private TextView date;
    private TextView sTime; //Start time
    private TextView eTime; //End time

    private SimpleDateFormat dateFormatter;
    private Calendar calValidator;
    private Calendar dateCal;
    private Calendar sCal;
    private Calendar eCal;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TimePickerDialog eTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party__room);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA);
        calValidator = Calendar.getInstance();
        dateCal = Calendar.getInstance();
        sCal = Calendar.getInstance();
        eCal = Calendar.getInstance();

        date = (TextView)findViewById(R.id.party_date_set);
        date.setText(dateFormatter.getDateInstance().format(new Date()));

        sTime = (TextView)findViewById(R.id.party_time_set);
        sTime.setText(dateFormatter.getTimeInstance().format(new Date()));

        eTime = (TextView)findViewById(R.id.party_endTime_set);
        eTime.setText(dateFormatter.getTimeInstance().format(new Date()));

        setDateTimeField();
    }

    private void setDateTimeField() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        sTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        eTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTimePickerDialog.show();
            }
        });

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateCal.set(year, monthOfYear, dayOfMonth);
                date.setText(dateFormatter.format(dateCal.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH) );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                sCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                sCal.set(Calendar.MINUTE, minute);
                sTime.setText(dateFormatter.getTimeInstance().format(sCal.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), false);

        eTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                eCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                eCal.set(Calendar.MINUTE, minute);
                eTime.setText(dateFormatter.getTimeInstance().format(eCal.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_party__room, menu);
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


    public void  partyOnClick(View view ) {
        if( dateIsValid() && sTimeIsValid() && eTimeIsValid() ) {
            toast("Your request has been submitted, you'll receive an email shortly.");
            finish();
        }
    }

    private boolean dateIsValid() {
        calValidator = Calendar.getInstance();
        calValidator.set(Calendar.HOUR_OF_DAY, 7);
        if( calValidator.after(dateCal) ) {
            toast("Please choose a valid date.");
            return false;
        }
        return true;
    }

    private boolean sTimeIsValid() {
        calValidator.set(dateCal.get(Calendar.YEAR), dateCal.get(Calendar.MONTH), dateCal.get(Calendar.DAY_OF_MONTH), 9, 59, 59);
        sCal.set(dateCal.get(Calendar.YEAR), dateCal.get(Calendar.MONTH), dateCal.get(Calendar.DAY_OF_MONTH));
        if( calValidator.after(sCal) ) {
            toast("Please choose a valid start time. Must start after 10AM.");
            return false;
        }
        return true;
    }

    private boolean eTimeIsValid() {
        calValidator.set(dateCal.get(Calendar.YEAR), dateCal.get(Calendar.MONTH), dateCal.get(Calendar.DAY_OF_MONTH), 22, 00, 59);
        eCal.set(dateCal.get(Calendar.YEAR), dateCal.get(Calendar.MONTH), dateCal.get(Calendar.DAY_OF_MONTH));
        if( eCal.after(calValidator) ) {
            toast("Please choose a valid end time. Must end before 10PM");
            return false;
        } else if (!eCal.after(sCal)) {
            toast("Your end time must be after start time");
            return false;
        }
        return true;
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }
}
