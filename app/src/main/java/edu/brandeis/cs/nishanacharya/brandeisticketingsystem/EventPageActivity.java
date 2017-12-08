package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventPageActivity extends AppCompatActivity {

    String eventName;
    String eventLocation;
    String eventDescription;
    String eventDate;
    String eventTime;
    int EVENT_NAME = 0;
    int EVENT_DESCRIPTION = 1;
    int EVENT_LOCATION = 2;
    int EVENT_DATE = 3;
    int EVENT_TIME = 4;
    TicketDataHandler dh;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent receiveIntent = getIntent();
        final Bundle extras = receiveIntent.getExtras();
        final String[] eventInfo;
        if(extras != null) {
            eventInfo = extras.getStringArray("eventInfo");
            this.eventName = eventInfo[EVENT_NAME];
            this.eventDescription = eventInfo[EVENT_DESCRIPTION];
            this.eventLocation = eventInfo[EVENT_LOCATION];
            this.eventDate = eventInfo[EVENT_DATE];
            this.eventTime = eventInfo[EVENT_TIME];
        }
        final TextView name = findViewById(R.id.eventName);
        TextView location = findViewById(R.id.eventLocation);
        TextView description = findViewById(R.id.eventDescription);
        TextView date = findViewById(R.id.eventDate);
        TextView time = findViewById(R.id.eventTime);

        name.setText(eventName);
        description.setText(eventDescription);
        location.setText(eventLocation);
        date.setText(eventDate);
        time.setText(eventTime);

        Button add_button = findViewById(R.id.purchaseEvent);
        add_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dh = new TicketDataHandler(view.getContext());
                if(extras != null) {
                    dh.insertTicket(FirebaseAuth.getInstance().getCurrentUser().getUid(), name.getText().toString());
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("You have successfully purchased your ticket");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "Return to Events",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(EventPageActivity.this, EventViewerActivity.class));
                            }
                        });

                builder.setNegativeButton(
                        "Return to Home",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(EventPageActivity.this, HomeActivity.class));
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
                alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(0xffbdbdbd);
                alert.getButton(alert.BUTTON_POSITIVE).setTextColor(0xffbdbdbd);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu item) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.directory,item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_Button:
                Intent homeIntent = new Intent(this, HomeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void saveToCalendar(View view){

        // set date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        long millis = 0;

        try {
            date = sdf.parse(this.eventDate + " " + this.eventTime  + ":00");
            millis = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // get text from views
        TextView eventName = (TextView) findViewById(R.id.eventName);
        TextView location = (TextView) findViewById(R.id.eventLocation);
        TextView description = (TextView) findViewById(R.id.eventDescription);

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
