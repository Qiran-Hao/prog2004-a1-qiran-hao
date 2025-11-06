package com.scu.prog2004.a1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 健康服务预约系统主类（支持模式自由切换）
 * 功能：自动测试模式与交互式模式可反复切换，直至主动退出系统
 * 符合OOP四大原则、异常处理规范及代码可读性要求
 */
public class AssignmentOne {

    /**
     * 创建预约（含参数验证与异常处理）
     * @param appointments 存储预约的集合（非null）
     * @param patientName 患者姓名（非空）
     * @param mobile 患者手机号（04开头10位数字）
     * @param timeSlot 预约时间（HH:mm格式）
     * @param doctor 预约的医生（非null）
     * @return true=创建成功；false=创建失败
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
     * 打印所有预约详情（体现多态传递）
     * @param appointments 存储预约的集合（可为null或空）
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
     * 取消预约（通过手机号匹配）
     * @param appointments 存储预约的集合（非null）
     * @param mobile 患者手机号（用于匹配）
     * @return true=取消成功；false=未找到对应预约
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
        System.out.println("4. Return to Mode Selection"); // 修改为返回模式选择
        System.out.println("======================================");
        System.out.print("Please select an option (1-4): ");
    }

    /**
     * 选择预约的医生（交互式模式辅助方法）
     * @param scanner 输入扫描器（非null）
     * @return 选中的医生对象（非null）
     */
    private static HealthProfessional selectDoctor(Scanner scanner) {
        System.out.println("\n--- Available Doctors ---");
        System.out.println("1. Dr. Emily Smith (General Practitioner, After-hours: Yes)");
        System.out.println("2. Dr. James Brown (General Practitioner, After-hours: No)");
        System.out.println("3. Dr. Michael Lee (Cardiologist, Sub-specialty: Heart Failure)");
        System.out.println("4. Dr. Sarah Chen (Cardiologist, Sub-specialty: Electrophysiology)");
        System.out.print("Select a doctor (1-4): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 吸收换行符

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
     * 自动测试模式：执行核心功能测试后返回模式选择界面
     * @param appointments 存储预约的集合（非null）
     */
    private static void runAutoTest(ArrayList<Appointment> appointments) {
        System.out.println("\n=== Running Auto-Test Mode ===");
        try {
            // 创建3个全科医生 + 2个心脏病专家（多态体现）
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

            // 打印所有医生详情（验证多态打印）
            System.out.println("\n--- All Health Professionals ---");
            gp1.printDetails();
            gp2.printDetails();
            gp3.printDetails();
            cardio1.printDetails();
            cardio2.printDetails();
            System.out.println("------------------------------");

            // 创建4个合法预约 + 1个非法预约（异常测试）
            createAppointment(appointments, "John Doe", "0412345678", "09:30", gp1);
            createAppointment(appointments, "Mike Taylor", "0423456789", "11:00", gp2);
            createAppointment(appointments, "Jane Smith", "0487654321", "14:00", cardio1);
            createAppointment(appointments, "Emily Davis", "0498765432", "15:30", cardio2);
            createAppointment(appointments, "Bob Wilson", "12345", "10:00", gp3); // 非法手机号

            // 打印预约（验证创建结果）
            printAppointments(appointments);

            // 取消预约（验证集合操作）
            System.out.println("\n--- Cancelling appointment for 0412345678 ---");
            cancelAppointment(appointments, "0412345678");

            // 再次打印（验证取消结果）
            printAppointments(appointments);
            System.out.println("\n--- Auto-Test Completed ---");

        } catch (HealthcareException e) {
            System.out.println("❌ Auto-test failed: " + e.getMessage());
        }
        // 执行完毕后自动返回模式选择界面
    }

    /**
     * 交互式模式：手动操作后可返回模式选择界面
     * @param appointments 存储预约的集合（非null）
     * @param scanner 输入扫描器（非null）
     */
    private static void runInteractiveMode(ArrayList<Appointment> appointments, Scanner scanner) {
        System.out.println("\n=== Entering Interactive Mode ===");
        System.out.println("(Select 4 to return to mode selection)");

        while (true) { // 交互式内部循环
            showMenu();
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // 吸收换行符
            } catch (Exception e) {
                System.out.println("⚠️ Invalid input, please enter 1-4.");
                scanner.nextLine(); // 清除错误输入
                continue;
            }

            switch (choice) {
                case 1: // 创建新预约
                    System.out.println("\n--- Create New Appointment ---");
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter patient mobile (04xxxxxxx): ");
                    String mobile = scanner.nextLine();

                    System.out.print("Enter appointment time (HH:mm): ");
                    String time = scanner.nextLine();

                    HealthProfessional doctor = selectDoctor(scanner);
                    createAppointment(appointments, name, mobile, time, doctor);
                    break;

                case 2: // 打印所有预约
                    printAppointments(appointments);
                    break;

                case 3: // 取消预约
                    System.out.println("\n--- Cancel Appointment ---");
                    System.out.print("Enter patient mobile to cancel: ");
                    String cancelMobile = scanner.nextLine();
                    cancelAppointment(appointments, cancelMobile);
                    break;

                case 4: // 返回模式选择界面
                    System.out.println("👋 Exiting Interactive Mode. Returning to main menu...");
                    return; // 跳出当前方法，回到外层循环

                default:
                    System.out.println("❌ Invalid option. Please enter 1-4.");
            }
        }
    }

    /**
     * 主方法：外层循环实现模式自由切换
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // 外层循环：持续显示模式选择界面，直至选择退出系统
        while (true) {
            System.out.println("\n=== Health Service Booking System ===");
            System.out.println("1. Run Auto-Test (verify core functions)");
            System.out.println("2. Enter Interactive Mode (manual operation)");
            System.out.println("3. Exit System"); // 新增完全退出选项
            System.out.print("Select mode (1-3): ");

            int mode;
            try {
                mode = scanner.nextInt();
                scanner.nextLine(); // 吸收换行符
            } catch (Exception e) {
                System.out.println("⚠️ Invalid input, please enter 1-3.");
                scanner.nextLine(); // 清除错误输入
                continue; // 重新显示选择界面
            }

            // 根据选择执行对应逻辑
            switch (mode) {
                case 1:
                    runAutoTest(appointments); // 自动测试后返回循环
                    break;
                case 2:
                    runInteractiveMode(appointments, scanner); // 手动模式返回后继续循环
                    break;
                case 3: // 完全退出系统
                    System.out.println("👋 Exiting system. Thank you!");
                    scanner.close();
                    System.exit(0); // 终止程序
                default:
                    System.out.println("❌ Invalid option. Please enter 1-3.");
            }
        }
    }
}