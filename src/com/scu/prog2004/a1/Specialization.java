package com.scu.prog2004.a1;

/**
 * 枚举类：封装健康从业者专业领域，避免字符串拼写错误
 */
public enum Specialization {
    // 枚举常量，每个常量对应一个专业领域
    GENERAL_MEDICINE("General Medicine"),
    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology");

    private final String description; // 专业领域的描述

    // 枚举构造器（仅内部调用）
    Specialization(String description) {
        this.description = description;
    }

    // Getter方法，对外暴露描述
    public String getDescription() {
        return description;
    }
}
