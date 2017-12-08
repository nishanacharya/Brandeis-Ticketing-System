package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmailAddress;
    private EditText editPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;
    private FirebaseAuth firebaseAuth;
    private static final String UNIVERSITY_EMAIL = "@brandeis.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        logInView();
    }

    private void logInView() {
        editEmailAddress = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            logIn();
        } else if (view == buttonSignUp){
            finish();
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    private void logIn() {
        String emailAddress = editEmailAddress.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if(validation(emailAddress, password)){
            firebaseAuth.signInWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                finish();
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                //go to user profile
                            } else {
                                //Sign up Failure Message for now
                                Toast.makeText(MainActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }

    private boolean validation(String emailAddress, String password) {
        if(emailAddress.isEmpty()){
            Toast.makeText(this, getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.isEmpty()){
            Toast.makeText(this, getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!emailAddress.endsWith(UNIVERSITY_EMAIL)){
            Toast.makeText(this, "The app is only available for Brandeis Students", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
