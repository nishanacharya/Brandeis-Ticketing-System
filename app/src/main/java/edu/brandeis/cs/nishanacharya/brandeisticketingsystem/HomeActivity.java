package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView welcome;
    private Button signOutButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);
        welcome(user);
    }

    private void welcome(FirebaseUser user) {
        welcome = (TextView) findViewById(R.id.textWelcome);
        welcome.setText(user.getEmail());
    }

    @Override
    public void onClick(View view) {
        if(view == signOutButton){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
    }
}
