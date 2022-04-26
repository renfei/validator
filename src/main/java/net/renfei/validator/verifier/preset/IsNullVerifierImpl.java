package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.IsNullCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 非Null校验
 *
 * @author renfei
 */
public class IsNullVerifierImpl implements Verifier {
    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        IsNullCheck isNullCheck = (IsNullCheck) annotation;
        field.setAccessible(true);
        try {
            report.setPassed(field.get(object) == null);
            report.setFieldValue(field.getType().getName());
        } catch (IllegalAccessException e) {
            report.setPassed(false);
            e.printStackTrace();
        }
        report.setFieldName(field.getName());
        report.setFieldType(field.getClass().getName());
        report.setVerificationTime(new Date());
        report.setVerifierName(this.getClass().getName());
        report.setValidatorName(annotation.toString());
        report.setBusinessName(isNullCheck.businessName());
        return report;
    }
}
