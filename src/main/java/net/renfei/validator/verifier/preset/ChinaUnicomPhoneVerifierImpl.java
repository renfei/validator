package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.ChinaUnicomPhoneCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 中国联通手机号校验
 *
 * @author renfei
 */
public class ChinaUnicomPhoneVerifierImpl implements Verifier {
    private static final String CHINA_PHONE_CHINA_UNICOM = "^(?:\\+?86)?1(?:3[0-2]|[578][56]|66)\\d{8}$";

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        try {
            report.setPassed(field.get(object) != null && field.get(object).toString().matches(CHINA_PHONE_CHINA_UNICOM));
            report.setFieldValue(field.get(object) == null ? null : field.get(object).toString());
            report.setFieldType(field.getType().getName());
        } catch (IllegalAccessException e) {
            report.setPassed(false);
            e.printStackTrace();
        }
        report.setFieldName(field.getName());
        report.setVerificationTime(new Date());
        report.setVerifierName(this.getClass().getName());
        report.setValidatorName(annotation.toString());
        report.setBusinessName(((ChinaUnicomPhoneCheck) annotation).businessName());
        return report;
    }
}
