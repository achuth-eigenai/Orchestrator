package com.eigenai.orchestrator.util;

import java.time.Instant;
import java.util.Date;

/**
 * Date Util.
 */

public class DateUtil {

    /**
     * After an hour.
     * @return
     */
    public static Date afterAnHour() {
        Date expiration = new Date();
        long expTimeInMillis = Instant.now().toEpochMilli();
        expTimeInMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeInMillis);
        return expiration;
    }

}
