package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.ChinaPhoneCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 中国手机号校验
 *
 * @author renfei
 */
public class ChinaPhoneVerifierImpl implements Verifier {
    private static final String CHINA_PHONE_ALL = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35-8]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[0135-9]\\d{2}|66\\d{2})\\d{6}$";

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        try {
            report.setPassed(field.get(object) != null && field.get(object).toString().matches(CHINA_PHONE_ALL));
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
        report.setBusinessName(((ChinaPhoneCheck) annotation).businessName());
        return report;
    }
}
