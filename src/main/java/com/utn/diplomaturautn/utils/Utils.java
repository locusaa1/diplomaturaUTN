package com.utn.diplomaturautn.utils;

import com.utn.diplomaturautn.exception.InvalidCallException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Utils {

    public static void compareDatesThrowingExceptions(Timestamp from, Timestamp to) {

        if (from.after(to)) {

            throw new InvalidCallException("The date \"from\" must be before the date \"to\"");
        } else if (to.after(Timestamp.valueOf(LocalDateTime.now()))) {

            throw new InvalidCallException("The date \"to\" must be before the actual date");
        }
    }
}
