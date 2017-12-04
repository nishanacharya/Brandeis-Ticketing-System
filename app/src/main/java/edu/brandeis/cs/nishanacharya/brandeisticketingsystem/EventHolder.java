package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

/**
 * Created by Triple Stuffed Oreo on 11/26/2017.
 */

class EventHolder {

    private String name;
    private String location;
    private String date;
    private String time;
    private String price;
    private String limit;

    public EventHolder(){}

    public EventHolder(String Name, String Location, String Date, String Time, String Price , String Limit){
        name = Name;
        location = Location;
        date = Date;
        time = Time;
        price = Price;
        limit = Limit;
    }

    public String getName(){ return name; }

    public String getLocation(){ return location; }

    public String getDate(){ return date; }

    public String getTime(){ return time; }

    public String getPrice(){ return price; }

    public String getLimit(){ return limit;}

    public void setName(String Name){ name = Name; }

    public void setLocation(String Location){ location = Location; }

    public void setDate(String Date){ date = Date; }

    public void setTime(String Time){ time = Time; }

    public void setPrice(String Price){ price = Price; }

    public void setLimit(String Limit){ limit = Limit; }
}


