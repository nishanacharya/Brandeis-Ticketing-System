package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by Triple Stuffed Oreo on 12/2/2017.
 */

public class TicketViewerActivity extends AppCompatActivity {

    private TicketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tickets);
        final ListView listView = (ListView) findViewById(R.id.listView);
        String[] from = { "_id", "event_name", "event_location", "event_date",
                "event_time", "event_price", "event_limit"};
        int[] to = { R.id.event_name, R.id.event_location, R.id.event_date, R.id.event_time,
                R.id.event_price, R.id.event_limit};
        adapter = new TicketAdapter(this, R.layout.ticket_entry, null, from, to, 0, "brandeis");
        listView.setAdapter(adapter);
    }
}
