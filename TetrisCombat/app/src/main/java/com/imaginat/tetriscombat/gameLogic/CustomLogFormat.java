package com.imaginat.tetriscombat.gameLogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by nat on 3/27/16.
 */
public class CustomLogFormat extends Formatter {
    //
    // Create a DateFormat to format the logger timestamp.

    //
    private static final DateFormat df = new SimpleDateFormat("hh:mm:ss.SSS");

    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date(record.getMillis()))).append(" -------------------------LOG ENTRY---------\r\n ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }

    public String getHead(Handler h) {
        return super.getHead(h);
    }

    public String getTail(Handler h) {
        return super.getTail(h);
    }

}

