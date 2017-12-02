package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private TicketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mTickets = (Button) findViewById(R.id.tickets_button);
        mTickets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventViewerActivity.class));
            }
        });

        Button mEvents = (Button) findViewById(R.id.events_button);
        mTickets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventViewerActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                adapter.insert(data);
            }
        }
    }

}
