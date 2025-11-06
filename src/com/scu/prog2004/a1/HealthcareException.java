package com.scu.prog2004.a1;

/**
 * Medical business custom exceptions, encapsulating all business errors within the system (such as illegal parameters, professional mismatch).
 * Inheriting RuntimeException, there is no need to enforce try-catch, which is flexible enough to adapt to business scenarios.
 */
public class HealthcareException extends RuntimeException {
    /**
     * Constructor: Receives error messages
     * @param message Incorrect description (such as "Incorrect format of mobile phone number")
     */
    public HealthcareException(String message) {
        super(message);
    }
}