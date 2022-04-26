package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.LengthMinCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 数据长度最大校验
 *
 * @author renfei
 */
public class LengthMinVerifierImpl implements Verifier {

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        LengthMinCheck lengthMinCheck = (LengthMinCheck) annotation;
        try {
            if (field.get(object) == null) {
                report.setPassed(false);
            } else {
                if (field.get(object).getClass().isArray()) {
                    report.setPassed(((Object[]) field.get(object)).length >= lengthMinCheck.min());
                } else if (field.get(object) instanceof String) {
                    report.setPassed(field.get(object).toString().length() >= lengthMinCheck.min());
                } else if (field.get(object) instanceof List) {
                    report.setPassed(((List<?>) field.get(object)).size() >= lengthMinCheck.min());
                } else if (field.get(object) instanceof Map) {
                    report.setPassed(((Map<?, ?>) field.get(object)).size() >= lengthMinCheck.min());
                } else if (field.get(object) instanceof AtomicIntegerArray) {
                    report.setPassed(((AtomicIntegerArray) field.get(object)).length() >= lengthMinCheck.min());
                } else if (field.get(object) instanceof AtomicLongArray) {
                    report.setPassed(((AtomicLongArray) field.get(object)).length() >= lengthMinCheck.min());
                } else if (field.get(object) instanceof AtomicReferenceArray) {
                    report.setPassed(((AtomicReferenceArray<?>) field.get(object)).length() >= lengthMinCheck.min());
                } else {
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
        report.setBusinessName(lengthMinCheck.businessName());
        return report;
    }
}
