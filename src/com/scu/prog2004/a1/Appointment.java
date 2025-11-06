package com.scu.prog2004.a1;

/**
 * true= Non-empty; false= Empty or null
 * The "has-a" relationship (one appointment associated with one doctor) is reflected through combination, and parameter verification depends on the utility class.
 */
public class Appointment {
    private String patientName;  // Patient's name
    private String mobile;       // Patient's mobile phone number (for canceling appointments)
    private String timeSlot;     // Appointment time (HH:mm)
    private HealthProfessional doctor;  // Associated doctors (combination relationship）

    /**
     * Default constructor: Initialize the default value
     */
    public Appointment() {
        this.patientName = "Unknown Patient";
        this.mobile = "0000000000";
        this.timeSlot = "00:00";
        this.doctor = new HealthProfessional();
    }

    /**
     * Full-parameter constructor: Verify the validity of parameters through utility classes
     * @param patientName Patient's name (non-empty)
     * @param mobile Patient's mobile phone number (10 digits starting with 04)
     * @param timeSlot Appointment time (HH:mm)
     * @param doctor The doctor who made the appointment (not null）
     * @throws HealthcareException Throw when the parameter is illegal
     */
    public Appointment(String patientName, String mobile, String timeSlot, HealthProfessional doctor) {
        // Verify the patient's name (reusable tool class)
        if (!ValidationUtil.isValidName(patientName)) {
            throw new HealthcareException("Invalid patient name (cannot be empty)");
        }
        // Verify mobile phone number
        if (!ValidationUtil.isValidMobile(mobile)) {
            throw new HealthcareException("Invalid mobile number (must be 04xxxxxxx)");
        }
        // Verification time format
        if (!ValidationUtil.isValidTime(timeSlot)) {
            throw new HealthcareException("Invalid time slot (format: HH:mm)");
        }
        // Verify that the doctor is not null
        if (doctor == null) {
            throw new HealthcareException("Selected doctor cannot be null");
        }

        this.patientName = patientName;
        this.mobile = mobile;
        this.timeSlot = timeSlot;
        this.doctor = doctor;
    }

    /**
     * Print appointment details: Call the doctor's printDetails (polymorphic delivery)
     */
    public void printDetails() {
        System.out.println("=== Appointment Details ===");
        System.out.printf(
                "  Patient Name: %s%n" +
                        "  Patient Mobile: %s%n" +
                        "  Appointment Time: %s%n" +
                        "  Doctor Info:%n",
                this.patientName, this.mobile, this.timeSlot
        );
        this.doctor.printDetails();  // Polymorphism: Automatically invoke the printDetails of subclasses
        System.out.println();  // Blank line separation
    }

    //Getter (No Setter is provided to ensure that the core information cannot be modified after the reservation is created and to maintain security)
    public String getMobile() { return mobile; }
    public HealthProfessional getDoctor() { return doctor; }
    public String getPatientName() { return patientName; }
    public String getTimeSlot() { return timeSlot; }

    /**
     * Rewrite toString: for easier debugging
     */
    @Override
    public String toString() {
        return "Appointment{patient=" + patientName + ", mobile=" + mobile + ", doctor=" + doctor + "}";
    }
}
