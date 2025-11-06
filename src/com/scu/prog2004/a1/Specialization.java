package com.scu.prog2004.a1;

/**
 * 健康从业者的专业领域枚举，限定所有合法专业，避免字符串类型错误。
 * 包含中英文描述，增强展示可读性。
 */
public enum Specialization {
    GENERAL_MEDICINE("General Medicine", "全科医学"),  // 全科
    CARDIOLOGY("Cardiology", "心脏内科"),              // 心脏科
    DERMATOLOGY("Dermatology", "皮肤科");              // 皮肤科（扩展用）

    private final String englishDesc;  // 英文描述
    private final String chineseDesc;  // 中文描述

    /**
     * 枚举构造器（默认私有）
     * @param englishDesc 英文专业名
     * @param chineseDesc 中文专业名
     */
    Specialization(String englishDesc, String chineseDesc) {
        this.englishDesc = englishDesc;
        this.chineseDesc = chineseDesc;
    }

    // Getter（无Setter，确保枚举不可变）
    public String getEnglishDesc() { return englishDesc; }
    public String getChineseDesc() { return chineseDesc; }

    /**
     * 重写toString，默认返回英文描述（便于打印）
     */
    @Override
    public String toString() {
        return englishDesc;
    }
}