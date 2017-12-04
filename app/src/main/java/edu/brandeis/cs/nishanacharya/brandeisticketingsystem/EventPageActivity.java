package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventPageActivity extends AppCompatActivity {

    String eventName;
    String eventLocation;
    String eventDescription;
    String eventDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
    }

    protected void onCreate(Bundle savedInstanceState, String eventName, String eventLocation, String eventDescription, String eventDate) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;     // Must be in the format "2017/12/04 18:10:45"
        this.eventDate = "2018/01/01 18:10:45";     // Test for now
    }

    public void saveToCalendar(View view){

        // set date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        long millis = 0;

        try {
            date = sdf.parse(eventDate);
            millis = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // get text from views
        TextView eventName = (TextView) findViewById(R.id.eventName);
        TextView location = (TextView) findViewById(R.id.eventLocation);
        TextView description = (TextView) findViewById(R.id.eventDescription);
//        EditText date = (EditText) findViewById(R.id.dateInput);

        //creates calender event
        Intent calenderIntent = new Intent(Intent.ACTION_EDIT);
        calenderIntent.setType("vnd.android.cursor.item/event");
        calenderIntent.putExtra(CalendarContract.Events.TITLE, eventName.getText().toString());
        calenderIntent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
        calenderIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
        calenderIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, millis);

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(calenderIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(calenderIntent);
        } else {
            Toast.makeText(this, "You're calender app is not set up correctly.", Toast.LENGTH_SHORT).show();
        }
    }
}
