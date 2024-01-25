package wms.eurotech.kondawms;

public class Items {
    String item,location,time,quantity;

    public Items(String item, String location, String time)
    {
        this.item = item;
        this.location = location;
        this.time = time;
    }

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

}
