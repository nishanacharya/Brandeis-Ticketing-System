package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editName;
    private EditText editEmailAddress;
    private EditText editPassword;
    private Button buttonSignUp;
    private Button buttonSignIn;
    private FirebaseAuth firebaseAuth;
    private static final String UNIVERSITY_EMAIL = "@brandeis.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        loginView();
    }

    private void loginView(){
        editName = (EditText) findViewById(R.id.editTextName);
        editEmailAddress = (EditText) findViewById(R.id.editTextEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignUp) {
            registerUser();
        }
        if(view == buttonSignIn){
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    private void registerUser() {
        final String name = editName.getText().toString().trim();
        final String emailAddress = editEmailAddress.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        if(validation(name, emailAddress, password)){
            firebaseAuth.createUserWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent userInfo = new Intent(MainActivity.this, HomeActivity.class);
                                userInfo.putExtra("userName", name);
                                userInfo.putExtra("emailAddress", emailAddress);
                                userInfo.putExtra("password", password);
                                finish();
                                startActivity(userInfo);
                            } else {
                                //Sign Up failure message for now
                                Toast.makeText(MainActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private boolean validation(String name, String emailAddress, String password) {

        if(name.isEmpty()){
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(emailAddress.isEmpty()){
            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!emailAddress.endsWith(UNIVERSITY_EMAIL)){
            Toast.makeText(this, "The app is only available for Brandeis Students", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.isEmpty()){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
