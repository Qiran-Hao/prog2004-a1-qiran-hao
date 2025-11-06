package com.scu.prog2004.a1;

/**
 * The base class for all health practitioners, encapsulating common attributes (ID, name, professional field) and behaviors (print details).
 * Strict encapsulation is achieved through private attributes +Get/Set, and all parameter validations throw custom exceptions.
 */
public class HealthProfessional {
    // Private attributes (Encapsulated core: Only accessible through methods)
    private long id;  // Unique identifier (must be greater than 0）
    private String name;  // Name (non-empty)
    private Specialization specialization;  // Professional field (non-null）

    /**
     * Default constructor: Initialize the default value to avoid null Pointers
     */
    public HealthProfessional() {
        this.id = 0;
        this.name = "Unknown";
        this.specialization = Specialization.GENERAL_MEDICINE;
    }

    /**
     * Full-parameter constructor: Includes parameter validation to ensure data legitimacy
     * @param id Practitioner ID (must be greater than 0）
     * @param name Name (non-empty)
     * @param specialization Professional field (non-null）
     * @throws HealthcareException Throw when the parameter is illegal
     */
    public HealthProfessional(long id, String name, Specialization specialization) {
        // Verification ID (If illegal, throw a custom exception)
        if (id <= 0) {
            throw new HealthcareException("ID must be a positive integer (当前值: " + id + ")");
        }
        // Verify the name
        if (name == null || name.trim().isEmpty()) {
            throw new HealthcareException("Name cannot be null or empty");
        }
        // Verify the professional field
        if (specialization == null) {
            throw new HealthcareException("Specialization cannot be null");
        }

        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    /**
     * Print the details of the practitioner (can be overridden by subclasses to reflect polymorphism)
     */
    public void printDetails() {
        System.out.printf(
                "Health Professional Details:%n" +
                        "  ID: %d%n" +
                        "  Name: %s%n" +
                        "  Specialization: %s%n",
                this.id, this.name, this.specialization.getEnglishDesc()
        );
    }

    // Getter/SetterPrint the details of the practitioner (can be overridden by subclasses to reflect polymorphism)
    public long getId() { return id; }
    public void setId(long id) {
        if (id <= 0) {
            throw new HealthcareException("ID must be positive (Current value: " + id + ")");
        }
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new HealthcareException("Name cannot be empty");
        }
        this.name = name;
    }

    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) {
        if (specialization == null) {
            throw new HealthcareException("Specialization cannot be null");
        }
        this.specialization = specialization;
    }

    /**
     * Rewrite toString: for easier debugging (displaying core information)
     */
    @Override
    public String toString() {
        return "HealthProfessional{id=" + id + ", name='" + name + "'}";
    }
}