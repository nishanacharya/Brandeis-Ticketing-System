package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Triple Stuffed Oreo on 12/1/2017.
 */

public class EventViewerActivity extends AppCompatActivity {

    private EventAdapter adapter;
    private final String[] ADMINS = {"fAoRjapHEqhGmTTHTH4mNu1DFAu1", "XEtFwBtFXGeq9iK2r0NYDJ6Lvj82"};
    private boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_events);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ListView listView = (ListView) findViewById(R.id.ticketListView);
        String[] from = { "_id", "event_name", "event_location", "event_date", "event_time"};
        int[] to = { R.id.event_name, R.id.event_location, R.id.event_date, R.id.event_time };
        adapter = new EventAdapter(this, R.layout.event_entry, null, from, to, 0);
        listView.setAdapter(adapter);

        // Calls QRcode generator when user clicks on a ticket in their list of tickets
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick (AdapterView < ? > adapterView, View view,int position,
                long id){
                 if(isAdmin()){
                     TextView name = view.findViewById(R.id.event_name);
                     TextView description = view.findViewById(R.id.event_description);
                     TextView location = view.findViewById(R.id.event_location);
                     TextView date = view.findViewById(R.id.event_date);
                     TextView time = view.findViewById(R.id.event_time);
                     TextView columnID = view.findViewById(R.id.columnID);
                     String[] eventInfo = {columnID.getText().toString(), name.getText().toString()
                             , description.getText().toString(),  location.getText().toString(),
                             date.getText().toString(),  time.getText().toString()};
                     Intent intent = new Intent(EventViewerActivity.this,EditEventActivity.class);
                     intent.putExtra("eventInfo",  eventInfo);
                     startActivity(intent);
                 }  else {
                     TextView name = view.findViewById(R.id.event_name);
                     TextView description = view.findViewById(R.id.event_description);
                     TextView location = view.findViewById(R.id.event_location);
                     TextView date = view.findViewById(R.id.event_date);
                     TextView time = view.findViewById(R.id.event_time);
                     String[] eventInfo = {name.getText().toString(), description.getText().toString(),
                             location.getText().toString(), date.getText().toString(),
                             time.getText().toString()};

                     Intent intent = new Intent(EventViewerActivity.this, EventPageActivity.class);
                     intent.putExtra("eventInfo", eventInfo);
                     startActivity(intent);
                 }
                 listView.setAdapter(adapter);
            }
        });

    }

    private boolean isAdmin(){
        for(int i =0; i < this.ADMINS.length; i++){
            if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(ADMINS[i])){
                return true;
            }
        }
        return false;
    }

}