package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmailAddress;
    private EditText editPassword;
    private Button buttonSignUp;
    private static final String UNIVERSITY_EMAIL = "@brandeis.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginView();
    }

    private void loginView(){
        editEmailAddress = (EditText) findViewById(R.id.editTextEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignUp){
            registerUser();
        }
    }

    private void registerUser() {
        String emailAddress = editEmailAddress.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        validation(emailAddress, password);
    }

    private boolean validation(String emailAddress, String password) {
        if(emailAddress.isEmpty()){
            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.isEmpty()){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!emailAddress.endsWith(UNIVERSITY_EMAIL)){
            Toast.makeText(this, "The app is only available for Brandeis Students", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
