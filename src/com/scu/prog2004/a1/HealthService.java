package com.scu.prog2004.a1;

/**
 * The health service interface defines the service capabilities that all practitioners must achieve.
 * Embody the principle of abstraction and unify the standards for service description and emergency service judgment.
 */
public interface HealthService {
    /**
     * Description of the provided service content (such as "general medical services")
     * @return Service description string (non-empty)
     */
    String provideServiceDescription();

    /**
     * Determine whether emergency services are provided (such as by cardiologists but not by general practitioners)
     * @return true=Provide emergency services；false=Not provided
     */
    boolean isEmergencyServiceAvailable();
}