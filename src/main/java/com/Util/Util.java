package com.Util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aspect.AspectException;
/**
 * 
 * @author rajesh
 *
 */

public final class Util {



    public static boolean isNull(Object obj) {
        return null == obj ? true : false;
    }

    @SuppressWarnings("unchecked")
    public static boolean isNullList(Object obj) {
        boolean isNullList = true;
        if (obj != null) {
            if (obj instanceof List) {
                List<Object> list = (List<Object>) obj;
                isNullList = (list.isEmpty() || list.size() < 1);
            }
        }
        return isNullList;
    }

  
   
    
    public static Date getCurrentDate() throws ParseException {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "dd-MM-yyyy");
     

        Date date = new Date();
        String timeStamp = timeStampFormat.format(date);
        return timeStampFormat.parse(timeStamp);
    }

    
    public static void throwException(String msg) {
        throw new AspectException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failure",msg );
    }

    
       

   
}
