package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class EditEventActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editEventName;
    private EditText editEventDescription;
    private EditText editEventLocation;
    private DatePicker editDate;
    private TimePicker editTime;
    private Button buttonEditEvent;
    private Button buttonDeleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        editEvent();

        Intent receiveIntent = getIntent();
        Bundle extras = receiveIntent.getExtras();
        String[] eventInfo;
        if(extras != null) {
            eventInfo = extras.getStringArray("eventInfo");
        }
    }

    private void editEvent() {
        editEventName = (EditText) findViewById(R.id.editEventName);
        editEventDescription = (EditText) findViewById(R.id.editEventDescription);
        editEventLocation = (EditText) findViewById(R.id.editEventLocation);
        editDate = (DatePicker) findViewById(R.id.editEventDate);
        editTime = (TimePicker) findViewById(R.id.editEventTime);
        buttonEditEvent = (Button) findViewById(R.id.buttonSaveEvent);
        buttonDeleteEvent = (Button) findViewById(R.id.buttonDeleteEvent);
        buttonEditEvent.setOnClickListener(this);
        prefill();
    }

    private void prefill() {
        // prefill text here
    }

    @Override
    public void onClick(View view) {
        if(view == buttonEditEvent){
            //edit text
            //message that change was made
            //go to particular ticket
        } else if (view == buttonDeleteEvent){
            //delete event
            //message
            //go to home
        }
    }
}
