package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Triple Stuffed Oreo on 11/26/2017.
 */

class TicketDataHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "TicketManagement.db";
    private static final String TABLE_NAME = "event_table";
    private static final String MASTER_TABLE_NAME = "ticket_table";
    private static final String COLUMN_ID = "_id";
    private static final String USER_ID = "user_id";
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_DESCRIPTION = "event_description";
    private static final String EVENT_LOCATION = "event_location";
    private static final String EVENT_DATE = "event_date";
    private static final String EVENT_TIME = "event_time";

    public TicketDataHandler(Context context) {
        super(context, DATABASE_NAME, null, 27);
    }

    @Override
    public void onCreate(SQLiteDatabase db){}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MASTER_TABLE_NAME);
        onCreate(db);
    }

    public Cursor getCursor(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + MASTER_TABLE_NAME, null);
    }

    public Cursor getCursor(String UserName){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + USER_ID + " FROM " + TABLE_NAME + " WHERE user_id = '" + UserName + "'", null);
    }

    public void insertEvent(String Name, String Location, String Time, String Date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_NAME, Name);
        contentValues.put(EVENT_LOCATION, Location);
        contentValues.put(EVENT_DATE, Date);
        contentValues.put(EVENT_TIME, Time);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public void insertTicket(String UserName, String EventName){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, UserName);
        contentValues.put(EVENT_NAME, EventName);

        db.insert(MASTER_TABLE_NAME, null, contentValues);
    }

    public  void delete(String String){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MASTER_TABLE_NAME, EVENT_NAME + " = ?", new String[]{ String });
    }

    ArrayList getData(String UserName){
        //System.out.println(UserName);
        ArrayList<EventHolder> list = new ArrayList<>();
        ArrayList<String> userTickets = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor tickets = db.rawQuery("SELECT * FROM " + MASTER_TABLE_NAME, null);
        while(tickets.moveToNext()){
            if(tickets.getString(tickets.getColumnIndex(USER_ID)).contentEquals(UserName)){
                userTickets.add(tickets.getString(2));
            }
        }

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int size = userTickets.size();

        while(c.moveToNext() && size > 0){
            if(userTickets.contains(c.getString(1))) {
                EventHolder holder = new EventHolder();
                holder.setName(c.getString(c.getColumnIndex(EVENT_NAME)));
                holder.setLocation(c.getString(c.getColumnIndex(EVENT_LOCATION)));
                holder.setDate(c.getString(c.getColumnIndex(EVENT_DATE)));
                holder.setTime(c.getString(c.getColumnIndex(EVENT_TIME)));
                list.add(holder);
            }
        }
        return list;
    }

    public void testInsert(){
        insertEvent("Into the Woods", "Mainstage Theater, Spingold", "6PM-9PM", "December 12th");
        insertEvent("Brandeis Jazz Ensemble", "Slosberg Music Center", "8PM", "December 20th");
        insertEvent("Lois Foster Gallery","Rose Art Museum", "9PM", "January 12th");
        insertEvent("Mela", "Levin Ballroom Usdan", "6PM", "February 22nd");
        insertEvent("K-Nite", "Levin Ballroom", "8PM-10PM", "April 24th");
        insertEvent("Senior Week Kickoff", "Great Lawn", "12PM", "May 12th");
        insertEvent("Library Party","Farber Library", "9PM-12PM", "November 12th");
    }
}
