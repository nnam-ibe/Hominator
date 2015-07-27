package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Khalid on 2015-07-23.
 */
public class RegisterActivity extends Activity implements View.OnClickListener{
    EditText efName, elName, eEmail, eUsername, ePassword;
    TextView tfName, tlName, tEmail, tUsername, tPassword;

    Button registerNxtPage;

    int defaultColor;
    static DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBHelper(this);

        efName = (EditText) findViewById(R.id.efirstname);
        elName = (EditText) findViewById(R.id.elastname);
        eEmail = (EditText) findViewById(R.id.eemail);
        eUsername = (EditText) findViewById(R.id.eusername);
        ePassword = (EditText) findViewById(R.id.epassword);

        tfName = (TextView) findViewById(R.id.firstname);
        tlName = (TextView) findViewById(R.id.lastname);
        tEmail = (TextView) findViewById(R.id.email);
        tUsername = (TextView) findViewById(R.id.username);
        tPassword = (TextView) findViewById(R.id.password);


        defaultColor = tEmail.getCurrentTextColor();


        registerNxtPage = (Button) findViewById(R.id.bRegisNext);

        registerNxtPage.setOnClickListener(this);

    }

    public static boolean validateFirstName( String firstName )
    {
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    }

    public static boolean validateLastName( String lastName )
    {
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    }

    //Validates email
    public static boolean validateEmail (String email){
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        return emailPattern.matcher(email).matches();
    }
    public static boolean validatePassword (String password){
        return password.matches( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$" );
    }

    public static boolean validateUsername (String username){
        Cursor query = db.authenticateUsername(db,username);
        query.moveToFirst();

        boolean validUsername = false;

        if(query.getCount() == 0 && username.matches( "^[a-zA-Z0-9_-]{3,10}$") ){
            validUsername = true;
        }


        return validUsername;
    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
            case R.id.bRegisNext:

                tEmail.setTextColor(defaultColor);
                tUsername.setTextColor(defaultColor);
                tfName.setTextColor(defaultColor);
                tlName.setTextColor(defaultColor);
                tPassword.setTextColor(defaultColor);

                String firstName = efName.getText().toString();
                String lastName = elName.getText().toString();
                String email = eEmail.getText().toString();
                String username = eUsername.getText().toString();
                String password = ePassword.getText().toString();


                if((validateEmail(email))==false ||(validateFirstName(firstName))== false||(validateLastName(lastName))== false||validateUsername(username) == false || validatePassword(password) == false){
                    Toast.makeText(getBaseContext(),"One or more fields have been entered incorrectly. Fields in red must be re-entered.", Toast.LENGTH_LONG).show();


                    if((validateEmail(email))== false){
                        tEmail.setTextColor(Color.parseColor("#d61a1a"));
                    }
                    if((validateUsername(username)) == false){
                        tUsername.setTextColor(Color.parseColor("#d61a1a"));
                    }
                    if((validateFirstName(firstName)) == false){
                        tfName.setTextColor(Color.parseColor("#d61a1a"));
                    }
                    if((validateLastName(lastName)) == false){
                        tlName.setTextColor(Color.parseColor("#d61a1a"));
                    }

                    if((validatePassword(password)) == false){
                        tPassword.setTextColor(Color.parseColor("#d61a1a"));
                    }
                } else{

                    tEmail.setTextColor(defaultColor);
                    tUsername.setTextColor(defaultColor);
                    tfName.setTextColor(defaultColor);
                    tlName.setTextColor(defaultColor);
                    tPassword.setTextColor(defaultColor);

                    Intent myIntent = new Intent(this, RegisterActivity2.class);
                    myIntent.putExtra("firstName",firstName);
                    myIntent.putExtra("lastName",lastName);
                    myIntent.putExtra("email", email);
                    myIntent.putExtra("username", username);
                    myIntent.putExtra("password", password);

                    startActivity(myIntent);
                    break;

                }

        }
    }
}

