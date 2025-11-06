package com.scu.prog2004.a1;

/**
 * Enumerate the professional fields of health practitioners, limit all legal specialties, and avoid string type errors.
 * It includes both Chinese and English descriptions to enhance the readability of the display.
 */
public enum Specialization {
    GENERAL_MEDICINE("General Medicine", "全科医学"),  // 全科
    CARDIOLOGY("Cardiology", "心脏内科"),              // 心脏科
    DERMATOLOGY("Dermatology", "皮肤科");              // 皮肤科（扩展用）

    private final String englishDesc;  // 英文描述
    private final String chineseDesc;  // 中文描述

    /**
     * Enumeration constructor (Private by default
     * @param englishDesc English major name
     * @param chineseDesc Chinese major name
     */
    Specialization(String englishDesc, String chineseDesc) {
        this.englishDesc = englishDesc;
        this.chineseDesc = chineseDesc;
    }

    // GetterChinese major name
    public String getEnglishDesc() { return englishDesc; }
    public String getChineseDesc() { return chineseDesc; }

    /**
     * Rewrite toString. By default, it returns an English description (for easy printing).
     */
    @Override
    public String toString() {
        return englishDesc;
    }
}