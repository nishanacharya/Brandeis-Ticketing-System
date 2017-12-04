package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventPageActivity extends AppCompatActivity {

    String eventName;
    String eventLocation;
    String eventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
    }

    protected void onCreate(Bundle savedInstanceState, String eventName, String eventLocation, String eventDescription) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
    }
}
