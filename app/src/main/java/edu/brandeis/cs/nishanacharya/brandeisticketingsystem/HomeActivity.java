package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    private final String[] ADMINS = {"fAoRjapHEqhGmTTHTH4mNu1DFAu1", "XEtFwBtFXGeq9iK2r0NYDJ6Lvj82"};
    private boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.admin = false;
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

        adminOrNot();

        Button buttonMyInfo = (Button) findViewById(R.id.myinfo_button);
        buttonMyInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, UserProfileActivity.class));

            }
        });

        signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);
    }

    private void adminOrNot() {
        for(int i =0; i < this.ADMINS.length; i++){
            if (firebaseAuth.getCurrentUser().getUid().equals(ADMINS[i])){
                this.admin = true;
                break;
            }
        }
        if(admin){
            setContentView(R.layout.activity_admin_home);
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            adminOptions();
        } else {
            setContentView(R.layout.activity_home);
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            regularOptions();
        }
    }

    private void adminOptions() {
        Button createEvent = (Button) findViewById(R.id.create_event_button);
        createEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CreateEventActivity.class));

            }
        });

        Button editEvent = (Button) findViewById(R.id.edit_event_button);
        editEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, EditEventActivity.class));
            }
        });

        Button buttonQR = (Button) findViewById(R.id.qr_event_button);
        buttonQR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QRReader.class));
            }
        });
    }

    private void regularOptions() {
        Button mTickets = (Button) findViewById(R.id.tickets_button);
        mTickets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, TicketViewerActivity.class));
            }
        });

        Button mEvents = (Button) findViewById(R.id.events_button);
        mEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, EventViewerActivity.class));
            }
        });

        Button mQR = (Button) findViewById(R.id.qr_event_button);
        mQR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QRGenerator.class));
            }
        });
    }

    private void logInOnSignUp(final String userName, String emailAddress, String password) {
        firebaseAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            RegularUser newUser = new RegularUser(userName);
                            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(newUser);
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
