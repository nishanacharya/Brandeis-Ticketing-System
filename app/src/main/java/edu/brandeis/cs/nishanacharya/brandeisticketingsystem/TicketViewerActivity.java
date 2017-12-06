package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Triple Stuffed Oreo on 12/2/2017.
 */

public class TicketViewerActivity extends AppCompatActivity {
    private TicketAdapter adapter;
    TicketDataHandler dh;
    private ArrayList<EventHolder> list;
    private ListView listView;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tickets);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        listView = (ListView) findViewById(R.id.ticketListView);
        dh = new TicketDataHandler(this);
        list = dh.getData(getString(R.string.brandeis));
        adapter = new TicketAdapter(this, R.layout.ticket_entry, list);
        listView.setAdapter(adapter);

        // Calls QRcode generator when user clicks on a ticket in their list of tickets
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get user ID and ticket ID and send to QR Generator
                String uniqueTicketID = savedInstanceState.getString("userId") +
                        list.get(position).getUniqueEventId();    // = userID + ticketID;


                Intent QRActivity =new Intent(TicketViewerActivity.this, QRGenerator.class);
                QRActivity.putExtra(getString(R.string.unique_qr_id), uniqueTicketID);
                startActivity(QRActivity);
            }
        });
    }
}