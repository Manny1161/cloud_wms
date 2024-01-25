package wms.eurotech.kondawms;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConSQL {
    Connection con;
    public Connection conclass()
    {
        String ip="192.168.0.216",port="1434",db="Eurotech",uname="sa",pass="30015911ca!";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+db+";user="+uname+";"+"password="+pass+";";
            con = DriverManager.getConnection(ConnectURL);
        }
        catch (Exception e)
        {
            Log.e("Error is ", e.getMessage());
        }
        return con;
    }

}
