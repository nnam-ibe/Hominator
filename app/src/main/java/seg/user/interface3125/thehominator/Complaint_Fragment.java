package seg.user.interface3125.thehominator;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Complaint_Fragment extends Fragment {
//
//    RelativeLayout noiseComplaintView;
//
//    private TextView date;
//    private TextView time;
//
//    private SimpleDateFormat dateFormatter;
//    private Calendar calValidator;
//    private Calendar dateCal;
//    private Calendar timeCal;
//
//    private DatePickerDialog datePickerDialog;
//    private TimePickerDialog timePickerDialog;



    Spinner spinner;

    public Complaint_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_complaint, container, false);
//        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA);
//        calValidator = Calendar.getInstance();
//        dateCal=Calendar.getInstance();
//        timeCal=Calendar.getInstance();
//
//        date = (TextView) rootView.findViewById(R.id.complaint_date_set);
//        date.setText(dateFormatter.getDateInstance().format(new Date()));
//
//        time= (TextView) rootView.findViewById(R.id.complaint_time_set);
//        time.setText(dateFormatter.getTimeInstance().format(new Date()));

        HomePage hP = (HomePage)getActivity();
        hP.setUpCompliant(rootView);
        return rootView;
    }







}
