package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEventName;
    private EditText editEventDescription;
    private EditText editEventLocation;
    private DatePicker editDate;
    private TimePicker editTime;
    private Button buttonCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        createEvent();
    }

    private void createEvent() {
        editEventName = (EditText) findViewById(R.id.editEventName);
        editEventDescription = (EditText) findViewById(R.id.editEventDescription);
        editEventLocation = (EditText) findViewById(R.id.editEventLocation);
        editDate = (DatePicker) findViewById(R.id.editEventDate);
        editTime = (TimePicker) findViewById(R.id.editEventTime);
        buttonCreateEvent = (Button) findViewById(R.id.buttonCreateEvent);
        buttonCreateEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonCreateEvent){


        }
    }
}
