package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.MaxCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 最大值校验
 *
 * @author renfei
 */
public class MaxVerifierImpl implements Verifier {

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        MaxCheck maxCheck = (MaxCheck) annotation;
        try {
            if (field.get(object) == null) {
                report.setPassed(false);
            } else {
                if (field.get(object) instanceof Short) {
                    report.setPassed(((Short) field.get(object)) <= maxCheck.max());
                } else if (field.get(object) instanceof Integer) {
                    report.setPassed(((Integer) field.get(object)) <= maxCheck.max());
                } else if (field.get(object) instanceof Long) {
                    report.setPassed(((Long) field.get(object)) <= maxCheck.max());
                } else if (field.get(object) instanceof Float) {
                    report.setPassed(((Float) field.get(object)) <= maxCheck.max());
                } else if (field.get(object) instanceof Double) {
                    report.setPassed(((Double) field.get(object)) <= maxCheck.max());
                } else if (field.get(object) instanceof BigInteger) {
                    report.setPassed(((BigInteger) field.get(object)).compareTo(new BigInteger(maxCheck.max() + "")) < 1);
                } else if (field.get(object) instanceof BigDecimal) {
                    report.setPassed(((BigDecimal) field.get(object)).compareTo(new BigDecimal(maxCheck.max() + "")) < 1);
                } else if (field.get(object) instanceof AtomicInteger) {
                    report.setPassed(((AtomicInteger) field.get(object)).get() <= maxCheck.max());
                } else if (field.get(object) instanceof AtomicLong) {
                    report.setPassed(((AtomicLong) field.get(object)).get() <= maxCheck.max());
                }else {
                    System.err.println("[" + this.getClass().getName() + "]未知的类型：" + field.get(object).getClass().getName());
                    report.setPassed(false);
                }
            }
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
        report.setBusinessName(maxCheck.businessName());
        return report;
    }
}
