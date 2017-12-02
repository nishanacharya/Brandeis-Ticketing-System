package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Triple Stuffed Oreo on 11/26/2017.
 */

class TicketDataHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "TicketManagement.db";
    private static final String TABLE_NAME = "event_table";
    private static final String COLUMN_ID = "_id";
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_LOCATION = "event_location";
    private static final String EVENT_DATE = "event_date";
    private static final String EVENT_TIME = "event_time";
    private static final String EVENT_PRICE = "event_price";
    private static final String EVENT_LIMIT = "event_limit";

    public TicketDataHandler(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID
                + " integer primary key autoincrement, "
                + EVENT_NAME
                + " Text," + EVENT_LOCATION
                + " Text," + EVENT_DATE
                + " Text," + EVENT_TIME
                + " Text," + EVENT_PRICE
                + " Text," + EVENT_LIMIT
                + " Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getCursor(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void insert(String Name, String Location, String Time, String Date, String Price, String Limit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("event_name", Name);
        contentValues.put("event_location", Location);
        contentValues.put("event_date", Date);
        contentValues.put("event_time", Time);
        contentValues.put("event_price", Price);
        contentValues.put("event_limit", Limit);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public  void delete(String string){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, EVENT_NAME + " = ?", new String[]{ string });
    }

    public boolean doesTableExist(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null );
        if(cur != null){
            cur.moveToFirst();
        }
        return cur.getInt(0) == 0;
    }
}
