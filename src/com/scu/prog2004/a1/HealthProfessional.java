package com.scu.prog2004.a1;

/**
 * 基类：所有健康从业者的抽象父类，体现OOP抽象原则
 */
public class HealthProfessional {
    // 私有属性（封装，仅通过Getter/Setter访问）
    private long id;
    private String name;
    private Specialization specialization; // 关联枚举类，增强类型安全

    // 默认构造器：初始化默认值，避免空指针
    public HealthProfessional() {
        this.id = 0;
        this.name = "Unknown";
        this.specialization = Specialization.GENERAL_MEDICINE;
    }

    // 全参构造器：含参数验证
    public HealthProfessional(long id, String name, Specialization specialization) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    // 打印方法：格式清晰，便于子类重写
    public void printDetails() {
        System.out.printf(
                "Health Professional Details:%n" +
                        "  ID: %d%n" +
                        "  Name: %s%n" +
                        "  Specialization: %s%n",
                this.id, this.name, this.specialization.getDescription()
        );
    }

    // Getter/Setter：封装属性访问
    public long getId() { return id; }
    public void setId(long id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }
    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
