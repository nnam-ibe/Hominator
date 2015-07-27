package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Khalid on 2015-07-23.
 */
public class RegisterActivity2 extends Activity implements View.OnClickListener{

    Intent oldIntent;
    Bundle old;

    EditText eAddress;
    Button registerLastPage;
    Spinner commSpinner;
    String community;

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

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
            case R.id.bRegisLast:
                String homeAddress = eAddress.getText().toString();

                Intent myIntent = new Intent(this, RegisterActivity3.class);

                myIntent.putExtra("firstName",firstName);
                myIntent.putExtra("lastName",lastName);
                myIntent.putExtra("email",email);
                myIntent.putExtra("username",username);
                myIntent.putExtra("password",password);
                myIntent.putExtra("address",homeAddress);
                myIntent.putExtra("community", community);

                startActivity(myIntent);
                break;

        }
    }
}

