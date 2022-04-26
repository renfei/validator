package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.ChinaMobilePhoneCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 中国移动手机号校验
 *
 * @author renfei
 */
public class ChinaMobilePhoneVerifierImpl implements Verifier {
    private static final String CHINA_PHONE_CHINA_MOBILE = "^(?:\\+?86)?1(?:3(?:4[^9\\D]|[5-9]\\d)|5[^3-6\\D]\\d|8[23478]\\d|[79]8\\d)\\d{7}$";

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        try {
            report.setPassed(field.get(object) != null && field.get(object).toString().matches(CHINA_PHONE_CHINA_MOBILE));
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
        report.setBusinessName(((ChinaMobilePhoneCheck) annotation).businessName());
        return report;
    }
}
