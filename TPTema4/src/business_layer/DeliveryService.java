package business_layer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DeliveryService implements IDeliveryServiceProcessing{
    @Override
    public String estimateDeliveryTime(String date) {
        String out="";
        String[] dd=date.split("/");
        date=dd[1];
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {

        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 30);
        String newTime = df.format(cal.getTime());
        out=out+newTime+"-";
        try {
            d=df.parse(newTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal=Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE,30);
        newTime=df.format(cal.getTime());
        out=out+newTime;
        return out;
    }
}
