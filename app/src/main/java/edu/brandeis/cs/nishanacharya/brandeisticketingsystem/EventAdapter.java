package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Triple Stuffed Oreo on 11/26/2017.
 */

class EventAdapter extends SimpleCursorAdapter {

    private EventDataHandler db;
    private String userName;

    public EventAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags){
        super(context, layout, c, from, to, flags);
        db = new EventDataHandler(context);
        this.userName = userName;
        super.changeCursor(db.getCursor());
    }

    public void insert(Intent data){
        db.insertEvent(data.getExtras().getString("name"), data.getExtras().getString("description"),
                data.getExtras().getString("location"), data.getExtras().getString("date"),
                data.getExtras().getString("time"));
        EventAdapter.super.changeCursor(db.getCursor());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.event_entry, null);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor){
        if(cursor != null) {
            final String event_name = cursor.getString(1);
            final String[] event_info = {cursor.getString(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("event_name")),
                    cursor.getString(cursor.getColumnIndex("event_description")),
                    cursor.getString(cursor.getColumnIndex("event_location")),
                    cursor.getString(cursor.getColumnIndex("event_date")),
                    cursor.getString(cursor.getColumnIndex("event_time"))};
            TextView name = view.findViewById(R.id.event_name);
            TextView uniqueID = view.findViewById(R.id.eventUniqueID);
            TextView columnID = view.findViewById(R.id.columnID);
            TextView description = view.findViewById(R.id.event_description);
            TextView location = view.findViewById(R.id.event_location);
            TextView date = view.findViewById(R.id.event_date);
            TextView time = view.findViewById(R.id.event_time);

            name.setText(cursor.getString(cursor.getColumnIndex("event_name")));
            uniqueID.setText(cursor.getString(cursor.getColumnIndex("event_name")) +
                    cursor.getString(cursor.getColumnIndex("_id")));
            columnID.setText(cursor.getString(cursor.getColumnIndex("_id")));
            location.setText(cursor.getString(cursor.getColumnIndex("event_location")));
            date.setText(cursor.getString(cursor.getColumnIndex("event_date")));
            time.setText(cursor.getString(cursor.getColumnIndex("event_time")));
        }
    }
}