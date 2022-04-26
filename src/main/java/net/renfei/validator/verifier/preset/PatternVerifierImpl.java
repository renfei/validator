package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.PatternCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 正则表达式校验
 *
 * @author renfei
 */
public class PatternVerifierImpl implements Verifier {

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        PatternCheck patternCheck = (PatternCheck) annotation;
        try {
            report.setPassed(field.get(object) != null && field.get(object).toString().matches(patternCheck.regexp()));
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
        report.setBusinessName(patternCheck.businessName());
        return report;
    }
}
