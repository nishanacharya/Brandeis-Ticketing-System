package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Triple Stuffed Oreo on 12/1/2017.
 */

public class EventViewerActivity extends AppCompatActivity {

    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_events);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ListView listView = (ListView) findViewById(R.id.ticketListView);
        String[] from = { "_id", "event_name", "event_location", "event_date",
                "event_time", "event_price", "event_limit"};
        int[] to = { R.id.event_name, R.id.event_location, R.id.event_date, R.id.event_time,
                R.id.event_price, R.id.event_limit};
        adapter = new EventAdapter(this, R.layout.event_entry, null, from, to, 0, "brandeis");
        listView.setAdapter(adapter);

        // Calls QRcode generator when user clicks on a ticket in their list of tickets
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startActivity(new Intent(EventViewerActivity.this, EventPageActivity.class));
            }
        });
    }

}
