package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Khalid on 2015-07-23.
 */
public class RegisterActivity2 extends Activity implements View.OnClickListener{

    Intent oldIntent;
    Bundle old;

    EditText eAddress;
    TextView eMsgaddress;
    TextView tHomeAddress;
    Button registerLastPage;
    Spinner commSpinner;
    String community;

    int defaultColor;

    String firstName, lastName, email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        oldIntent = getIntent();
        old = oldIntent.getExtras();

        firstName =  old.getString("firstName");
        lastName = old.getString("lastName");
        email = old.getString("email");
        username = old.getString("username");
        password = old.getString("password");


        registerLastPage = (Button) findViewById(R.id.bRegisLast);
        registerLastPage.setOnClickListener(this);

        eMsgaddress = (TextView) findViewById(R.id.errorMsgAddress);
        eMsgaddress.setVisibility(View.GONE);
        tHomeAddress = (TextView) findViewById(R.id.t2ndHomeAddress);

        defaultColor = tHomeAddress.getCurrentTextColor();

        eAddress = (EditText) findViewById(R.id.e2ndHomeAddress);
        commSpinner = (Spinner) findViewById(R.id.communitySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.communityArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        commSpinner.setAdapter(adapter);



        commSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                community = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public static boolean validateAddress (String address){
        return address.matches( "^((Flat [1-9][0-9]*, )?([1-9][0-9]* ))?([A-Z][a-z]* )*([A-Z][a-z]*)$" );
    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
            case R.id.bRegisLast:
                String homeAddress = eAddress.getText().toString();

                tHomeAddress.setTextColor(defaultColor);

                if(validateAddress(homeAddress) == false){
                    Toast.makeText(getBaseContext(),"Address field has been entered incorrectly.",Toast.LENGTH_LONG).show();
                    tHomeAddress.setTextColor(Color.parseColor("#d61a1a"));
                    eMsgaddress.setVisibility(View.VISIBLE);

                } else {


                    Intent myIntent = new Intent(this, RegisterActivity3.class);

                    myIntent.putExtra("firstName", firstName);
                    myIntent.putExtra("lastName", lastName);
                    myIntent.putExtra("email", email);
                    myIntent.putExtra("username", username);
                    myIntent.putExtra("password", password);
                    myIntent.putExtra("address", homeAddress);
                    myIntent.putExtra("community", community);

                    startActivity(myIntent);
                    break;
                }

        }
    }
}

