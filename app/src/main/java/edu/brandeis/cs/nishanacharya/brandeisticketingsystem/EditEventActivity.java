package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditEventActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editEventName;
    private EditText editEventDescription;
    private EditText editEventLocation;
    private DatePicker editDate;
    private TimePicker editTime;
    private Button buttonEditEvent;
    private Button buttonDeleteEvent;
    private String date;
    private String time;
    private EventDataHandler eventDataHandler;
    private String[] eventInfo;
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_DESCRIPTION = "event_description";
    private static final String EVENT_LOCATION = "event_location";
    private static final String EVENT_DATE = "event_date";
    private static final String EVENT_TIME = "event_time";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        eventDataHandler = new EventDataHandler(this);
        Intent receiveIntent = getIntent();
        Bundle extras = receiveIntent.getExtras();
        if(extras != null) {
            eventInfo = extras.getStringArray("eventInfo");
        } else {
            eventInfo = new String[]{};
        }
        editEvent();
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
        editEventName.setText(eventInfo[1]);
        editEventDescription.setText(eventInfo[2]);
        editEventLocation.setText(eventInfo[3]);
        date = "";
        time = "";
        editDate.init(editDate.getYear(), editDate.getMonth(), editDate.getDayOfMonth(),new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker arg0, int year, int monthIn, int dayIn) {
                String day = "";
                String month = "";
                monthIn++;
                if(dayIn < 10){
                    day = "0" + dayIn;
                }
                if(monthIn < 10){
                    month = "0" + monthIn;
                }
                date = year + "/" + month + "/" + day;
            }
        } );

        editTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDayIn, int minuteIn) {
                String hourOfDay = "";
                String minute = "";
                if(hourOfDayIn < 10){
                    hourOfDay = "0" + hourOfDay;
                }
                if(minuteIn < 10){
                    minute = "0" + minuteIn;
                }
                time = hourOfDay + ":" + minute + ":00";
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonEditEvent){
            if(editEventName.getText().toString().equals("") || editEventDescription.getText().toString().equals("")|| editEventLocation.getText().toString().equals("")|| date.equals("")|| time.equals("")){
                Toast.makeText(EditEventActivity.this, "Please enter all the information", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put(EVENT_NAME, editEventName.getText().toString());
                contentValues.put(EVENT_DESCRIPTION, editEventDescription.getText().toString());
                contentValues.put(EVENT_LOCATION, editEventLocation.getText().toString());
                contentValues.put(EVENT_DATE, date);
                contentValues.put(EVENT_TIME, time);
                eventDataHandler.update(eventInfo[0], contentValues);
                Toast.makeText(EditEventActivity.this, "Event has been changed", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(EditEventActivity.this, EventViewerActivity.class));
            }
        } else if (view == buttonDeleteEvent){
            eventDataHandler.delete(eventInfo[0]);
            Toast.makeText(EditEventActivity.this, "Event has been deleted", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(EditEventActivity.this, EventViewerActivity.class));
        }
    }
}
