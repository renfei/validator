package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.PatternVerifierImpl;

import java.lang.annotation.*;

/**
 * 正则表达式验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = PatternVerifierImpl.class)
public @interface PatternCheck {
    /**
     * 正则表达式
     *
     * @return
     */
    String regexp() default "";

    String businessName() default "";
}
