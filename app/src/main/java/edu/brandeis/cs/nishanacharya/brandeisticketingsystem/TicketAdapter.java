package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Triple Stuffed Oreo on 12/3/2017.
 */

class TicketAdapter extends ArrayAdapter {

    ArrayList<EventHolder> list;
    Context context;

    public TicketAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context = context;
        list = objects;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ticket_entry, parent, false);
        }
        TextView name = view.findViewById(R.id.ticket_name);
        TextView location = view.findViewById(R.id.ticket_location);
        TextView date = view.findViewById(R.id.ticket_date);
        TextView time = view.findViewById(R.id.ticket_time);
        TextView price = view.findViewById(R.id.ticket_price);
        TextView limit = view.findViewById(R.id.ticket_limit);

        EventHolder event = list.get(position);
        final String event_name = event.getName();
        if(event != null) {
            name.setText(event.getName());
            location.setText(event.getLocation());
            date.setText(event.getDate());
            time.setText(event.getTime());
            price.setText(event.getPrice());
            limit.setText(event.getLimit());
        }

        Button add_button = view.findViewById(R.id.delete_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TicketDataHandler db = new TicketDataHandler(context);
                db.delete(event_name);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
