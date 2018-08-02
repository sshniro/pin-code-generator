package com.infotech.app.util;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UtilImpl implements Util {

    public String getCurrentTimeStamp() {
        Date date = new Date();
        long time = date.getTime();

        Timestamp ts = new Timestamp(time);
        return ts.toString();
    }
}
