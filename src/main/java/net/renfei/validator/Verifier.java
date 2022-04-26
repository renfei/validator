package net.renfei.validator;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.entity.VerifiedReport;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.utils.FieldUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 执行校验者
 *
 * @author renfei
 */
public class Verifier {
    /**
     * 执行验证
     *
     * @param object 被验证的对象
     * @return VerifiedReport
     */
    public VerifiedReport verification(Object object) {
        final FieldUtil fieldUtil = new FieldUtil();
        final List<Field> fieldList = fieldUtil.getAllField(object);
        final VerifiedReport verifiedReport = new VerifiedReport();
        verifiedReport.setObjectName(object.getClass().getName());
        for (Field field : fieldList
        ) {
            for (Annotation annotation : field.getAnnotations()
            ) {
                if (annotation instanceof Validator) {
                    Validator validator = field.getDeclaredAnnotation(Validator.class);
                    settingReport(verifiedReport, verifier(validator.verifier(), annotation, object, field));
                } else {
                    for (Annotation subAnnotation : annotation.annotationType().getDeclaredAnnotations()
                    ) {
                        if (subAnnotation instanceof Validator) {
                            Validator validator = annotation.annotationType().getDeclaredAnnotation(Validator.class);
                            settingReport(verifiedReport, verifier(validator.verifier(), annotation, object, field));
                        }
                    }
                }
            }
        }
        return verifiedReport;
    }

    /**
     * 生成验证报告
     *
     * @param verifiedReport 验证报告
     * @param detail         验证明细
     */
    private void settingReport(VerifiedReport verifiedReport, VerifiedReportDetail detail) {
        if (verifiedReport.getPassed()) {
            if (!detail.getPassed()) {
                verifiedReport.setPassed(false);
            }
        }
        verifiedReport.getDetailList().add(detail);
    }

    /**
     * 执行验证器
     *
     * @param verifierClass 验证器
     * @param annotation    注解
     * @param object        被验证对象
     * @param field         被验证字段
     * @return VerifiedReportDetail
     */
    private VerifiedReportDetail verifier(Class<? extends net.renfei.validator.verifier.Verifier> verifierClass,
                                          Annotation annotation, Object object, Field field) {
        net.renfei.validator.verifier.Verifier verifier;
        try {
            verifier = verifierClass.newInstance();
            return verifier.verification(annotation, object, field);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
