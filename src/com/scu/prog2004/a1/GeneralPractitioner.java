package com.scu.prog2004.a1;

/**
 * The general practitioner subclass inherits from the HealthProfessional base class and implements the HealthService interface.
 * Add a unique attribute of "whether after-work services are provided", and rewrite the method to reflect the differences from other subclasses (polymorphism core).
 */
public class GeneralPractitioner extends HealthProfessional implements HealthService {
    private boolean hasAfterHoursService;  // Unique attribute: Whether after-work service is provided

    /**
     * Default constructor: Initialize the default value
     */
    public GeneralPractitioner() {
        super();
        this.hasAfterHoursService = false;
    }

    /**
     * Full-parameter constructor: Call the constructor of the parent class + verify that the major must be a full discipline
     * @param id Doctor ID (>0）
     * @param name Name (non-empty)
     * @param specialization Professional field (must be GENERAL_"MEDICINE"）
     * @param hasAfterHoursService Is after-work service provided
     * @throws HealthcareException Throw it out when the major does not match
     */
    public GeneralPractitioner(long id, String name, Specialization specialization, boolean hasAfterHoursService) {
        super(id, name, specialization);  // Reuse the parameter validation of the parent class
        // Additional verification: The specialty of a general practitioner must be general medicine
        if (specialization != Specialization.GENERAL_MEDICINE) {
            throw new HealthcareException("General Practitioner must specialize in GENERAL_MEDICINE");
        }
        this.hasAfterHoursService = hasAfterHoursService;
    }

    /**
     * Rewrite the printing method: Supplement unique attributes (polymorphism manifestation 1)
     */
    @Override
    public void printDetails() {
        System.out.println("=== General Practitioner Details ===");
        super.printDetails();  // Reuse the print logic of the parent class
        System.out.printf("  Offers After-Hours Service: %s%n",
                hasAfterHoursService ? "Yes" : "No");
    }

    /**
     * Implementation interface method: Describe the general practice service (polymorphism manifestation 2)
     */
    @Override
    public String provideServiceDescription() {
        return "Provides general medical care" +
                (hasAfterHoursService ? " (including after-hours service)" : "");
    }

    /**
     * Implementation interface method: General practitioners do not provide emergency services (polymorphic manifestation 3)
     */
    @Override
    public boolean isEmergencyServiceAvailable() {
        return false;
    }

    // Getter/Setter(Encapsulate unique attributes）
    public boolean isHasAfterHoursService() { return hasAfterHoursService; }
    public void setHasAfterHoursService(boolean hasAfterHoursService) {
        this.hasAfterHoursService = hasAfterHoursService;
    }

    /**
     * Rewrite toString: Include subclass information (Debugging-friendly)
     */
    @Override
    public String toString() {
        return "GeneralPractitioner{id=" + getId() + ", name='" + getName() + "'}";
    }
}
