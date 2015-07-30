package seg.user.interface3125.thehominator;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Khalid on 2015-07-23.
 */
public class RegisterActivity3 extends Activity implements View.OnClickListener{

    Intent oldIntent;
    Bundle old;
    DBHelper helper;
    TextView dpfirstName, dplastName, dpemail, dpusername, dppassword, dpaddress, dpcommunity;
    Button registerComplete;


    String firstName, lastName, email, username, password, address, community;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        helper = new DBHelper(this);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("App.db", MODE_PRIVATE, null);
        helper.onUpgrade(db,1,2);
        oldIntent = getIntent();
        old = oldIntent.getExtras();

        firstName =  old.getString("firstName");
        lastName = old.getString("lastName");
        email = old.getString("email");
        username = old.getString("username");
        password = old.getString("password");
        address = old.getString("address");
        community = old.getString("community");

        registerComplete = (Button) findViewById(R.id.bRegisComplete);
        registerComplete.setOnClickListener(this);

        dpfirstName = (TextView) findViewById(R.id.dpfirstName);
        dplastName = (TextView) findViewById(R.id.dpLastname);
        dpemail = (TextView) findViewById(R.id.dpemail);
        dpusername = (TextView) findViewById(R.id.dpusername);
        dppassword = (TextView) findViewById(R.id.dppassword);
        dpaddress = (TextView) findViewById(R.id.dpaddress);
        dpcommunity = (TextView) findViewById(R.id.dpcommunity);

        dpfirstName.setText(firstName);
        dplastName.setText(lastName);
        dpemail.setText(email);
        dpusername.setText(username);
        dppassword.setText(password);
        dpaddress.setText(address);
        dpcommunity.setText(community);

    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())){
            case R.id.bRegisComplete:
                User registeredUser = new User(firstName,lastName, email, username, password, address, community, 0);
                helper.insertUser(registeredUser);
                Toast.makeText(getBaseContext(),"Registration Complete", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                this.finish();
                break;

        }
    }
}

