package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Triple Stuffed Oreo on 11/26/2017.
 */

class EventAdapter extends SimpleCursorAdapter {

    private EventDataHandler db;
    private String userName;

    public EventAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags, String userName){
        super(context, layout, c, from, to, flags);
        db = new EventDataHandler(context);
        this.userName = userName;
        super.changeCursor(db.getCursor());
    }

    public void insert(Intent data){
        EventHolder holder = new EventHolder(data.getExtras().getString("name"),
                data.getExtras().getString("location"), data.getExtras().getString("date"),
                data.getExtras().getString("time"), data.getExtras().getString("price"),
                data.getExtras().getString("limit"));
        db.insertEvent(holder.getName(), holder.getLocation(), holder.getTime(),
                holder.getDate(), holder.getPrice(), holder.getLimit());
        EventAdapter.super.changeCursor(db.getCursor());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.event_entry, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        final String event_name = cursor.getString(1);
        TextView name = view.findViewById(R.id.event_name);
        TextView location = view.findViewById(R.id.event_location);
        TextView date = view.findViewById(R.id.event_date);
        TextView time = view.findViewById(R.id.event_time);
        TextView price = view.findViewById(R.id.event_price);
        TextView limit = view.findViewById(R.id.event_limit);

        name.setText(cursor.getString(cursor.getColumnIndex("event_name")));
        location.setText(cursor.getString(cursor.getColumnIndex("event_location")));
        date.setText(cursor.getString(cursor.getColumnIndex("event_date")));
        time.setText(cursor.getString(cursor.getColumnIndex("event_time")));
        price.setText(cursor.getString(cursor.getColumnIndex("event_price")));
        limit.setText(cursor.getString(cursor.getColumnIndex("event_limit")));

        Button add_button = view.findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db.insertTicket(userName, event_name);
                EventAdapter.super.changeCursor(db.getCursor());
            }
        });
    }
}
