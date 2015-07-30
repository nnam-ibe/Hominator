package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener{

    Button bLogin;
    EditText eUsername, ePassword;
    TextView registerLink, forgotPassLink;
    DBHelper helper;
    String tempUsername, tempPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        

        helper = new DBHelper(this);

        eUsername = (EditText) findViewById(R.id.tusername);
        ePassword = (EditText) findViewById(R.id.tpassword);
        registerLink = (TextView) findViewById(R.id.lRegister);
        forgotPassLink= (TextView) findViewById(R.id.lFPass);
        bLogin = (Button) findViewById(R.id.bLogin);


        registerLink.setOnClickListener(this);
        forgotPassLink.setOnClickListener(this);
        bLogin.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
            case R.id.bLogin:
                tempUsername = eUsername.getText().toString();
                tempPassword = ePassword.getText().toString();

                    Cursor query = helper.authenticateUserLogin(helper,tempUsername,tempPassword);
                    query.moveToFirst();

                    System.out.println(" Query count: " + query.getCount());
                    //System.out.println("DB Username: "+query.getString(0)+" , DB Pass: "+query.getString(1));

                    boolean loginFlag = false;
                    boolean newScreen = false;

                       do{
                        if(query.getCount() == 1){
                            loginFlag = true;
                        }
                    }while(query.moveToNext());

                    if(loginFlag){
                        Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        newScreen = true;
                        //startActivity(new Intent(this, HomePage.class));
                    }
                    else{Toast.makeText(getBaseContext(), "Login Failed. Username or password incorrect", Toast.LENGTH_SHORT).show();}

                    if(newScreen){
                        Intent myIntent = new Intent(this,HomePage.class);
                        myIntent.putExtra("username",tempUsername);
                        myIntent.putExtra("login", true);
                        startActivity(myIntent);
                        this.finish();
                        break;
                    }


                //Cursor cursor = helper.
                //User user = new User(null,null,null,null,null,null,null);
                //userLocalStore.storeUserData(user);
                break;
            case R.id.lRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.lFPass:
                startActivity(new Intent(this, ForgotPassActivity.class));
                break;
        }
    }
}