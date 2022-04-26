package net.renfei.validator.annotation;

import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.*;

/**
 * 验证器注解
 *
 * @author renfei
 */
@Inherited
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validator {
    /**
     * 验证器实现类
     *
     * @return
     */
    Class<? extends Verifier> verifier();

    /**
     * 附加信息，自定义使用
     *
     * @return
     */
    String value() default "";

    /**
     * 校验字段的业务名称，方便展示详情
     *
     * @return
     */
    String businessName() default "";

    /**
     * 长度
     *
     * @return
     */
    long length() default 0;

    /**
     * 字段类型
     *
     * @return
     */
    Class<?> fieldType() default Object.class;

    /**
     * 最大，根据校验器的实现可能是数值也可能是长度等
     *
     * @return
     */
    long max() default 0;

    /**
     * 最小，根据校验器的实现可能是数值也可能是长度等
     *
     * @return
     */
    long min() default 0;

    /**
     * 字符，根据校验器的实现可能是包含也可能是不包含等
     *
     * @return
     */
    String character() default "";

    /**
     * 正则表达式
     *
     * @return
     */
    String regexp() default "";
}
