package com.scu.prog2004.a1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class of Health Service reservation System (Supporting free mode switching)
 * Function: The automatic test mode and interactive mode can be switched repeatedly until the system exits actively
 */
public class AssignmentOne {

    /**
     * Create an appointment (including parameter verification and exception handling)
     * @param appointments Store the collection of reservations (not null)
     * @param patientName Patient's name (non-empty)
     * @param mobile Patient's mobile phone number (10 digits starting with 04)
     * @param timeSlot Appointment time (HH:mm format)
     * @param doctor The doctor who made the appointment (not null）
     * @return true= Creation successful; false= Creation failed
     */
    public static boolean createAppointment(
            ArrayList<Appointment> appointments,
            String patientName, String mobile, String timeSlot,
            HealthProfessional doctor) {
        try {
            if (appointments == null) {
                throw new HealthcareException("Appointment list cannot be null");
            }
            Appointment newAppt = new Appointment(patientName, mobile, timeSlot, doctor);
            appointments.add(newAppt);
            System.out.println("✅ Appointment created for " + patientName);
            return true;
        } catch (HealthcareException e) {
            System.out.println("❌ Failed to create appointment: " + e.getMessage());
            return false;
        }
    }

    /**
     * Print all reservation details (reflecting polymorphic transmission)
     * @param appointments Store the set of reservations (which can be null or empty)
     */
    public static void printAppointments(ArrayList<Appointment> appointments) {
        System.out.println("\n=== All Appointments ===");
        if (appointments == null || appointments.isEmpty()) {
            System.out.println("  No appointments found.");
            return;
        }
        for (int i = 0; i < appointments.size(); i++) {
            System.out.printf("--- Appointment %d ---%n", i + 1);
            appointments.get(i).printDetails();
        }
    }

    /**
     * Cancel the reservation (through mobile phone number matching)
     * @param appointments Store the collection of reservations (not null)
     * @param mobile Patient's mobile phone number (for matching)
     * @return true= Cancellation successful; false= No corresponding appointment found
     */
    public static boolean cancelAppointment(ArrayList<Appointment> appointments, String mobile) {
        if (appointments == null) {
            System.out.println("❌ Appointment list cannot be null");
            return false;
        }
        boolean isCancelled = appointments.removeIf(appt -> appt.getMobile().equals(mobile));
        System.out.println(isCancelled ?
                "✅ Appointment cancelled" :
                "❌ No appointment found for mobile: " + mobile);
        return isCancelled;
    }

    /**
     * 展示交互式菜单（手动操作模式）
     */
    private static void showMenu() {
        System.out.println("\n===== Health Service Booking System =====");
        System.out.println("1. Create New Appointment");
        System.out.println("2. Print All Appointments");
        System.out.println("3. Cancel Appointment (by mobile)");
        System.out.println("4. Return to Mode Selection"); // Modify it to return mode selection
        System.out.println("======================================");
        System.out.print("Please select an option (1-4): ");
    }

    /**
     * Select the doctor to make an appointment with (interactive mode assistance method
     * @param scanner Input scanner (not null)
     * @return The selected doctor object (not null)
     */
    private static HealthProfessional selectDoctor(Scanner scanner) {
        System.out.println("\n--- Available Doctors ---");
        System.out.println("1. Dr. Emily Smith (General Practitioner, After-hours: Yes)");
        System.out.println("2. Dr. James Brown (General Practitioner, After-hours: No)");
        System.out.println("3. Dr. Michael Lee (Cardiologist, Sub-specialty: Heart Failure)");
        System.out.println("4. Dr. Sarah Chen (Cardiologist, Sub-specialty: Electrophysiology)");
        System.out.print("Select a doctor (1-4): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Absorb line breaks

        switch (choice) {
            case 1:
                return new GeneralPractitioner(101, "Dr. Emily Smith",
                        Specialization.GENERAL_MEDICINE, true);
            case 2:
                return new GeneralPractitioner(102, "Dr. James Brown",
                        Specialization.GENERAL_MEDICINE, false);
            case 3:
                return new Cardiologist(201, "Dr. Michael Lee",
                        Specialization.CARDIOLOGY, "Heart Failure & Transplant");
            case 4:
                return new Cardiologist(202, "Dr. Sarah Chen",
                        Specialization.CARDIOLOGY, "Cardiac Electrophysiology");
            default:
                System.out.println("⚠️ Invalid choice, defaulting to Dr. Emily Smith");
                return new GeneralPractitioner(101, "Dr. Emily Smith",
                        Specialization.GENERAL_MEDICINE, true);
        }
    }

    /**
     * Automatic test mode: After conducting core function tests, return to the mode selection interface
     * @param appointments Store the collection of reservations (not null)
     */
    private static void runAutoTest(ArrayList<Appointment> appointments) {
        System.out.println("\n=== Running Auto-Test Mode ===");
        try {
            // Create 3 general practitioners and 2 cardiologists (polymorphic manifestations)
            HealthProfessional gp1 = new GeneralPractitioner(101, "Dr. Emily Smith",
                    Specialization.GENERAL_MEDICINE, true);
            HealthProfessional gp2 = new GeneralPractitioner(102, "Dr. James Brown",
                    Specialization.GENERAL_MEDICINE, false);
            HealthProfessional gp3 = new GeneralPractitioner(103, "Dr. Lisa Wilson",
                    Specialization.GENERAL_MEDICINE, true);
            HealthProfessional cardio1 = new Cardiologist(201, "Dr. Michael Lee",
                    Specialization.CARDIOLOGY, "Heart Failure & Transplant");
            HealthProfessional cardio2 = new Cardiologist(202, "Dr. Sarah Chen",
                    Specialization.CARDIOLOGY, "Cardiac Electrophysiology");

            // Print all doctor details (verify polymorphic printing)
            System.out.println("\n--- All Health Professionals ---");
            gp1.printDetails();
            gp2.printDetails();
            gp3.printDetails();
            cardio1.printDetails();
            cardio2.printDetails();
            System.out.println("------------------------------");

            // Create 4 legal appointments and 1 illegal appointment (abnormal test)
            createAppointment(appointments, "John Doe", "0412345678", "09:30", gp1);
            createAppointment(appointments, "Mike Taylor", "0423456789", "11:00", gp2);
            createAppointment(appointments, "Jane Smith", "0487654321", "14:00", cardio1);
            createAppointment(appointments, "Emily Davis", "0498765432", "15:30", cardio2);
            createAppointment(appointments, "Bob Wilson", "12345", "10:00", gp3); // 非法手机号

            // Print the reservation (verify the creation result)
            printAppointments(appointments);

            // Cancel the reservation (verification collection operation)
            System.out.println("\n--- Cancelling appointment for 0412345678 ---");
            cancelAppointment(appointments, "0412345678");

            // Print again (verify the cancellation result)
            printAppointments(appointments);
            System.out.println("\n--- Auto-Test Completed ---");

        } catch (HealthcareException e) {
            System.out.println("❌ Auto-test failed: " + e.getMessage());
        }
        // After execution is completed, it will automatically return to the mode selection interface
    }

    /**
     * Interactive mode: After manual operation, you can return to the mode selection interface
     * @param appointments Store the collection of reservations (not null)
     * @param scanner Input scanner (not null)
     */
    private static void runInteractiveMode(ArrayList<Appointment> appointments, Scanner scanner) {
        System.out.println("\n=== Entering Interactive Mode ===");
        System.out.println("(Select 4 to return to mode selection)");

        while (true) { // Interactive internal loop
            showMenu();
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Absorb line breaks
            } catch (Exception e) {
                System.out.println("⚠️ Invalid input, please enter 1-4.");
                scanner.nextLine(); // Clear incorrect input
                continue;
            }

            switch (choice) {
                case 1: // Create a new appointment
                    System.out.println("\n--- Create New Appointment ---");
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter patient mobile (04xxxxxxxx): ");
                    String mobile = scanner.nextLine();

                    System.out.print("Enter appointment time (HH:mm): ");
                    String time = scanner.nextLine();

                    HealthProfessional doctor = selectDoctor(scanner);
                    createAppointment(appointments, name, mobile, time, doctor);
                    break;

                case 2: // Print all appointments
                    printAppointments(appointments);
                    break;

                case 3: // Cancel the reservation
                    System.out.println("\n--- Cancel Appointment ---");
                    System.out.print("Enter patient mobile to cancel: ");
                    String cancelMobile = scanner.nextLine();
                    cancelAppointment(appointments, cancelMobile);
                    break;

                case 4: // Return to the mode selection interface
                    System.out.println("👋 Exiting Interactive Mode. Returning to main menu...");
                    return; // Jump out of the current method and return to the outer loop

                default:
                    System.out.println("❌ Invalid option. Please enter 1-4.");
            }
        }
    }

    /**
     * The main method: The outer loop realizes the free switching of modes
     * @param args Command-line parameters (not used）
     */
    public static void main(String[] args) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Outer loop: Continuously display the mode selection interface until you choose to exit the system
        while (true) {
            System.out.println("\n=== Health Service Booking System ===");
            System.out.println("1. Run Auto-Test (verify core functions)");
            System.out.println("2. Enter Interactive Mode (manual operation)");
            System.out.println("3. Exit System"); // A new option to completely exit has been added
            System.out.print("Select mode (1-3): ");

            int mode;
            try {
                mode = scanner.nextInt();
                scanner.nextLine(); // Absorb line breaks
            } catch (Exception e) {
                System.out.println("⚠️ Invalid input, please enter 1-3.");
                scanner.nextLine(); // Clear incorrect input
                continue; // Re-display the selection interface
            }

            // Execute the corresponding logic according to the selection
            switch (mode) {
                case 1:
                    runAutoTest(appointments); // Return to the loop after automatic testing
                    break;
                case 2:
                    runInteractiveMode(appointments, scanner); // Continue the loop after the manual mode returns
                    break;
                case 3: // Completely exit the system
                    System.out.println("👋 Exiting system. Thank you!");
                    scanner.close();
                    System.exit(0); // Terminate the procedure
                default:
                    System.out.println("❌ Invalid option. Please enter 1-3.");
            }
        }
    }
}