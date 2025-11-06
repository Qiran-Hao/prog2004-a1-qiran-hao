package com.scu.prog2004.a1;

import java.util.regex.Pattern;

/**
 * Verification tool class, encapsulating all general parameter verification logic (mobile phone number, time format, name).
 * Static method design, no instantiation required, focuses on verification functionality (Single Responsibility principle).
 */
public class ValidationUtil {
    // Regular Australian mobile phone numbers: starting with 04 +8 digits (total 10 digits)
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^04\\d{8}$");
    // Regular time format: HH:mm (hours 00-23, minutes 00-59)
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]\\d|2[0-3]):[0-5]\\d$");

    /**
     * Verify the format of the mobile phone number
     * @param mobile The mobile phone number to be verified
     * @return true=Correct format; false= Format error
     */
    public static boolean isValidMobile(String mobile) {
        return mobile != null && MOBILE_PATTERN.matcher(mobile).matches();
    }

    /**
     * Verification time format (HH:mm)
     * @param time The time string to be verified
     * @return true= Correct format; false= Format error
     */
    public static boolean isValidTime(String time) {
        return time != null && TIME_PATTERN.matcher(time).matches();
    }

    /**
     * Verify that the name is non-empty (after removing the first and last Spaces)
     * @param name Name to be verified
     * @return true= Non-empty; false= Empty or null
     */
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}