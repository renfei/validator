package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.ChinaMvnoPhoneCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 中国虚拟运营商手机号校验
 *
 * @author renfei
 */
public class ChinaMvnoPhoneVerifierImpl implements Verifier {
    private static final String CHINA_PHONE_MVNO_ALL = "^(?:\\+?86)?1(?:7[01]|6[257])\\d{8}$";

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        try {
            report.setPassed(field.get(object) != null && field.get(object).toString().matches(CHINA_PHONE_MVNO_ALL));
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
        report.setBusinessName(((ChinaMvnoPhoneCheck) annotation).businessName());
        return report;
    }
}
