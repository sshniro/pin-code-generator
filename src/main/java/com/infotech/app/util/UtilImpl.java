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

    private String addPaddingToNumbers(String pinCode, int paddingLength) {
        String padding = "";
        if (pinCode.length() < paddingLength) {
            int difference = paddingLength - pinCode.length();
            for (int i = 0; i < difference; i++) {
                padding = padding + "0";
            }

        } else if (pinCode.length() == paddingLength) {
            return pinCode;
        }
        return padding + pinCode;
    }
}
