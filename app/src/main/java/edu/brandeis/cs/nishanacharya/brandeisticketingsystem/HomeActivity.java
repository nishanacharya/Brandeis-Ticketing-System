package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private String userName;
    private String emailAddress;
    private String password;
    private Button signOutButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Intent receiveIntent = getIntent();
        Bundle extras = receiveIntent.getExtras();
        if(extras != null){
            this.userName = extras.getString(getString(R.string.user_name));
            this.emailAddress = extras.getString(getString(R.string.email_address));
            this.password = extras.getString(getString(R.string.password));
            if(userName != null && emailAddress != null && password != null){
                logInOnSignUp(userName, emailAddress, password);
            }
        }
        setContentView(R.layout.activity_home);




        signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);
    }

    private void logInOnSignUp(final String userName, String emailAddress, String password) {
        firebaseAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            RegularUser newUser = new RegularUser(userName);
                            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(newUser);

                            //go to user profile
                        } else {
                            //Singup Failure Message for now
                            Toast.makeText(HomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == signOutButton){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }
    }
}
