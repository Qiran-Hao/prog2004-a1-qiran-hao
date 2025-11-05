package com.scu.prog2004.a1;

/**
 * Enumerate the professional fields of health practitioners, encapsulate all legal specialties, and avoid string errors.
 * Each enumeration value contains both Chinese and English descriptions to enhance readability.
 */
public enum Specialization {
    GENERAL_MEDICINE("General Medicine", "全科医学"),
    CARDIOLOGY("Cardiology", "心脏内科"),
    DERMATOLOGY("Dermatology", "皮肤科");

    private final String englishDesc; // English description (adapted for internationalization）
    private final String chineseDesc; // Chinese description

    // 构造器：初始化描述信息
    Specialization(String englishDesc, String chineseDesc) {
        this.englishDesc = englishDesc;
        this.chineseDesc = chineseDesc;
    }

    // Getter方法（封装属性，不提供Setter确保枚举不可变）
    public String getEnglishDesc() { return englishDesc; }
    public String getChineseDesc() { return chineseDesc; }

    // Rewrite toString: By default, return an English description (for easy printing)
    @Override
    public String toString() {
        return englishDesc;
    }
}
