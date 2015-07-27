package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Khalid on 2015-07-23.
 */
public class ForgotPassActivity extends Activity implements View.OnClickListener{


    EditText fpEmail;
    Button bResetPass;
    DBHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        helper = new DBHelper(this);


        bResetPass = (Button) findViewById(R.id.bResetPass);
        bResetPass.setOnClickListener(this);

        fpEmail = (EditText) findViewById(R.id.fpEmail);
    }

    public static boolean validateEmail (String email, DBHelper db){
        Cursor query = db.authenticateEmail(db, email);
        query.moveToFirst();

        boolean validEmail = false;
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;

        if(query.getCount() == 0 && emailPattern.matcher(email).matches()){
            validEmail = true;
        }

        return validEmail;
    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
            case R.id.bResetPass:
                String email = fpEmail.getText().toString();

                Boolean authFlag = validateEmail(email, helper);

                if(authFlag){

                    Toast.makeText(getBaseContext(),"Email has been sent to the email provided with password reset instructions.", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(this, LoginActivity.class);

                    startActivity(myIntent);
                    break;
                } else{
                    Toast.makeText(getBaseContext(), "Invalid email entered. Please make sure that you've entered a valid email address.",Toast.LENGTH_LONG).show();
                }
        }
    }
}

