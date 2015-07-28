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
    TextView tfName, tlName, tEmail, tUsername, tPassword, eMsgfName, eMsglName, eMsgusername, eMsgpassword, eMsgemail, eMsgusernameTaken, eMsgemailUsed;

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

        eMsgfName = (TextView) findViewById(R.id.errorMsgfirstName);
        eMsglName = (TextView) findViewById(R.id.errorMsglastName);
        eMsgusername = (TextView) findViewById(R.id.errorMsgUsername);
        eMsgusernameTaken = (TextView) findViewById(R.id.errorMsgUsernameTaken);
        eMsgpassword = (TextView) findViewById(R.id.errorMsgPassword);
        eMsgemail = (TextView) findViewById(R.id.errorMsgEmail);
        eMsgemailUsed = (TextView) findViewById(R.id.errorMsgEmailUsed);

        eMsgfName.setVisibility(View.GONE);
        eMsglName.setVisibility(View.GONE);
        eMsgusername.setVisibility(View.GONE);
        eMsgusernameTaken.setVisibility(View.GONE);
        eMsgpassword.setVisibility(View.GONE);
        eMsgemailUsed.setVisibility(View.GONE);
        eMsgemail.setVisibility(View.GONE);


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

    public static int validateUsername (String username){
        Cursor query = db.authenticateUsername(db,username);
        query.moveToFirst();
        int num = 2;


        if(query.getCount() == 0 && username.matches( "^[a-zA-Z0-9_-]{5,12}$") ){
           num = 0 ;
        }
        else if (query.getCount() != 0 && username.matches( "^[a-zA-Z0-9_-]{5,12}$")){
           num = 1;
        }
        else{ num = 2;}


        return num;
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

                eMsgfName.setVisibility(View.GONE);
                eMsglName.setVisibility(View.GONE);
                eMsgusername.setVisibility(View.GONE);
                eMsgusernameTaken.setVisibility(View.GONE);
                eMsgpassword.setVisibility(View.GONE);
                eMsgemailUsed.setVisibility(View.GONE);
                eMsgemail.setVisibility(View.GONE);

                String firstName = efName.getText().toString();
                String lastName = elName.getText().toString();
                String email = eEmail.getText().toString();
                String username = eUsername.getText().toString();
                String password = ePassword.getText().toString();


                if((validateEmail(email))==false ||(validateFirstName(firstName))== false||(validateLastName(lastName))== false||(validateUsername(username) == 1 || validateUsername(username) == 2) || validatePassword(password) == false){
                    Toast.makeText(getBaseContext(),"One or more fields have been entered incorrectly. Fields in red must be re-entered.", Toast.LENGTH_LONG).show();


                    if((validateEmail(email))== false){
                        tEmail.setTextColor(Color.parseColor("#d61a1a"));
                        eMsgemail.setVisibility(View.VISIBLE);
                    }
                    if((validateUsername(username)) == 2){
                        eMsgusername.setVisibility(View.VISIBLE);
                        tUsername.setTextColor(Color.parseColor("#d61a1a"));
                    }
                    if((validateUsername(username)) == 1){
                        eMsgusernameTaken.setVisibility(View.VISIBLE);
                        tUsername.setTextColor(Color.parseColor("#d61a1a"));
                    }

                    if((validateFirstName(firstName)) == false){
                        eMsgfName.setVisibility(View.VISIBLE);
                        tfName.setTextColor(Color.parseColor("#d61a1a"));
                    }
                    if((validateLastName(lastName)) == false){
                        eMsglName.setVisibility(View.VISIBLE);
                        tlName.setTextColor(Color.parseColor("#d61a1a"));
                    }

                    if((validatePassword(password)) == false){
                        eMsgpassword.setVisibility(View.VISIBLE);
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

