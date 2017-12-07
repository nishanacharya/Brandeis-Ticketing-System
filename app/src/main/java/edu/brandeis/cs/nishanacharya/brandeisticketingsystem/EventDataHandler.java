package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Triple Stuffed Oreo on 11/26/2017.
 */

class EventDataHandler extends SQLiteOpenHelper {


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

    public EventDataHandler(Context context) {
        super(context, DATABASE_NAME, null, 35);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + MASTER_TABLE_NAME + " ( " + COLUMN_ID
                + " integer primary key autoincrement, "
                + USER_ID
                + " Text," + EVENT_NAME
                + " Text)");

        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID
                + " integer primary key autoincrement, "
                + EVENT_NAME
                + " Text," + EVENT_DESCRIPTION
                + " Text," + EVENT_LOCATION
                + " Text," + EVENT_DATE
                + " Text," + EVENT_TIME
                + " Text)");

        insertTestData(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MASTER_TABLE_NAME);
        onCreate(db);
    }

    public Cursor getCursor(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void insertEvent(String Name, String Description, String Location, String Date, String Time){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_NAME, Name);
        contentValues.put(EVENT_DESCRIPTION, Description);
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

    public  void delete(String string){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{ string });
    }

    public void update(String columnID, ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{ columnID });
    }

    public boolean doesTableExist(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + MASTER_TABLE_NAME, null );
        if(cur != null){
            cur.moveToFirst();
        }
        return cur.getInt(0) == 0;
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

    private void insertTestData(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_NAME, "Into the Woods");
        contentValues.put(EVENT_DESCRIPTION, "");
        contentValues.put(EVENT_LOCATION, "Mainstage Theater, Spingold");
        contentValues.put(EVENT_DATE, "2017/12/12");
        contentValues.put(EVENT_TIME, "18:00");

        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(EVENT_NAME, "Brandeis Jazz Ensemble");
        contentValues.put(EVENT_DESCRIPTION, "");
        contentValues.put(EVENT_LOCATION, "Slosberg Music Center");
        contentValues.put(EVENT_DATE, "2017/12/09");
        contentValues.put(EVENT_TIME, "20:00");

        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(EVENT_NAME, "Lois Foster Gallery");
        contentValues.put(EVENT_DESCRIPTION, "");
        contentValues.put(EVENT_LOCATION, "Rose Art Museum");
        contentValues.put(EVENT_DATE, "2018/01/04");
        contentValues.put(EVENT_TIME, "15:00");

        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(EVENT_NAME, "Mela");
        contentValues.put(EVENT_DESCRIPTION, "");
        contentValues.put(EVENT_LOCATION, "Levin Ballroom Usdan");
        contentValues.put(EVENT_DATE, "2018/03/04");
        contentValues.put(EVENT_TIME, "18:00");

        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(EVENT_NAME, "K-Nite");
        contentValues.put(EVENT_DESCRIPTION, "");
        contentValues.put(EVENT_LOCATION, "Levin Ballroom");
        contentValues.put(EVENT_DATE, "2018/03/04");
        contentValues.put(EVENT_TIME, "18:00");

        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(EVENT_NAME, "Library Party");
        contentValues.put(EVENT_DESCRIPTION, "");
        contentValues.put(EVENT_LOCATION, "Farber Library");
        contentValues.put(EVENT_DATE, "2017/12/14");
        contentValues.put(EVENT_TIME, "20:00");

        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(EVENT_NAME, "Senior Week Kickoff");
        contentValues.put(EVENT_DESCRIPTION, "");
        contentValues.put(EVENT_LOCATION, "Great Lawn");
        contentValues.put(EVENT_DATE, "2018/05/14");
        contentValues.put(EVENT_TIME, "12:00");

        db.insert(TABLE_NAME, null, contentValues);
    }
}