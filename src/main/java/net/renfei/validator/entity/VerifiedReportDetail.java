package net.renfei.validator.entity;

import java.util.Date;

/**
 * 验证报告
 *
 * @author renfei
 */
public class VerifiedReportDetail {
    private Boolean passed;
    private String fieldName;
    private String fieldType;
    private String fieldValue;
    private String businessName;
    private String verifierName;
    private String validatorName;
    private Date verificationTime;

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getVerifierName() {
        return verifierName;
    }

    public void setVerifierName(String verifierName) {
        this.verifierName = verifierName;
    }

    public String getValidatorName() {
        return validatorName;
    }

    public void setValidatorName(String validatorName) {
        this.validatorName = validatorName;
    }

    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
    }

    @Override
    public String toString() {
        return "\n\tVerifiedReportDetail{\n" +
                "\t\tpassed=" + passed +
                ",\n\t\tfieldName='" + fieldName + '\'' +
                ",\n\t\tfieldType='" + fieldType + '\'' +
                ",\n\t\tfieldValue='" + fieldValue + '\'' +
                ",\n\t\tbusinessName='" + businessName + '\'' +
                ",\n\t\tverifierName='" + verifierName + '\'' +
                ",\n\t\tvalidatorName='" + validatorName + '\'' +
                ",\n\t\tverificationTime=" + verificationTime +
                "}\n";
    }
}
