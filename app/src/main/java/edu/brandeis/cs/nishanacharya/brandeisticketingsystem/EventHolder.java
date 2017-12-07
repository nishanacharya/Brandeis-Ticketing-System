package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

/**
 * Created by Triple Stuffed Oreo on 11/26/2017.
 */

class EventHolder {

    private String name;
    private String location;
    private String date;
    private String time;
    private String uniqueEventId;
    private String description;

    public EventHolder(){}

    public EventHolder(String Name, String Location, String Date, String Time){
        name = Name;
        location = Location;
        date = Date;
        time = Time;
    }

    public String getName(){ return name; }

    public String getLocation(){ return location; }

    public String getDate(){ return date; }

    public String getTime(){ return time; }

    public String getUniqueEventId(){ return uniqueEventId; }

    public String getDescription(){ return description; }

    public void setName(String Name){ name = Name; }

    public void setLocation(String Location){ location = Location; }

    public void setDate(String Date){ date = Date; }

    public void setTime(String Time){ time = Time; }

    public void setUniqueEventId(String UniqueEventId){ uniqueEventId = UniqueEventId; }

    public void setDescription(String Description){ description = Description; }
}

