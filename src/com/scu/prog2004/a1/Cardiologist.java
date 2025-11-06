package com.scu.prog2004.a1;

/**
 * The subclass of cardiologist inherits from the base class and implements the service interface.
 * The unique attributes of "sub-specialties" have been newly added, and the rewriting method reflects the polymorphic differences from general practitioners.
 */
public class Cardiologist extends HealthProfessional implements HealthService {
    private String subSpecialty;  // Unique attributes: Subspecialty (such as "heart failure")

    /**
     * Default constructor: Initialize the default value
     */
    public Cardiologist() {
        super();
        this.subSpecialty = "General Cardiology";
    }

    /**
     * Full-parameter constructor: Call the constructor of the parent class + validate the specialties and subspecialties
     * @param id Doctor ID (>0）
     * @param name Name (non-empty)
     * @param specialization Professional field (must be CARDIOLOGY)
     * @param subSpecialty Sub-specialty (non-empty)
     * @throws HealthcareException Throw it when the major does not match or the sub-major is empty
     */
    public Cardiologist(long id, String name, Specialization specialization, String subSpecialty) {
        super(id, name, specialization);  // Reuse parent class validation
        // Verification 1: The major must be cardiology
        if (specialization != Specialization.CARDIOLOGY) {
            throw new HealthcareException("Cardiologist must specialize in CARDIOLOGY");
        }
        // Verification 2: Sub-specialties are not empty
        if (subSpecialty == null || subSpecialty.trim().isEmpty()) {
            throw new HealthcareException("Sub-specialty cannot be null or empty");
        }
        this.subSpecialty = subSpecialty;
    }

    /**
     * Rewrite the printing method: Supplement subspecialties (polymorphic manifestation)
     */
    @Override
    public void printDetails() {
        System.out.println("=== Cardiologist Details ===");
        super.printDetails();  //Reuse the logic of the parent class
        System.out.printf("  Sub-Specialty: %s%n", this.subSpecialty);
    }

    /**
     * Implementation interface method: Describe cardiology specialist services
     */
    @Override
    public String provideServiceDescription() {
        return "Specializes in cardiology, sub-field: " + this.subSpecialty;
    }

    /**
     * Implementation interface method: Cardiologists provide emergency services (polymorphic differences from general practitioners)
     */
    @Override
    public boolean isEmergencyServiceAvailable() {
        return true;
    }

    // Getter/Setter(Including verification）
    public String getSubSpecialty() { return subSpecialty; }
    public void setSubSpecialty(String subSpecialty) {
        if (subSpecialty == null || subSpecialty.trim().isEmpty()) {
            throw new HealthcareException("Sub-specialty cannot be empty");
        }
        this.subSpecialty = subSpecialty;
    }

    @Override
    public String toString() {
        return "Cardiologist{id=" + getId() + ", name='" + getName() + "'}";
    }
}
