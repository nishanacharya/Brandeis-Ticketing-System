package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Triple Stuffed Oreo on 12/2/2017.
 */

public class TicketViewerActivity extends AppCompatActivity {
    private TicketAdapter adapter;
    TicketDataHandler dh;
    private ArrayList<EventHolder> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tickets);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final ListView listView = (ListView) findViewById(R.id.listView);
        dh = new TicketDataHandler(this);
        list = dh.getData("brandeis");
        dh.testInsert();
        adapter = new TicketAdapter(this, R.layout.ticket_entry, list);
        listView.setAdapter(adapter);

        // Calls QRcode generator when user clicks on a ticket in their list of tickets
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView tID = (TextView) view.findViewById(R.id.event_name);
                startActivity(new Intent(TicketViewerActivity.this, QRGenerator.class));
            }
        });
    }
}
