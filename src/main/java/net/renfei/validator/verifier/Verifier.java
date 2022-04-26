package net.renfei.validator.verifier;

import net.renfei.validator.entity.VerifiedReportDetail;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 验证者接口
 *
 * @author renfei
 */
public interface Verifier {
    /**
     * 验证执行方法
     *
     * @param annotation 命中注解
     * @param object     校验的对象
     * @param field      命中字段
     * @return
     */
    VerifiedReportDetail verification(Annotation annotation, Object object, Field field);
}
