package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.LengthMinVerifierImpl;

import java.lang.annotation.*;

/**
 * 数据最小长度验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = LengthMinVerifierImpl.class)
public @interface LengthMinCheck {
    /**
     * 长度
     *
     * @return
     */
    long min() default 0;

    String businessName() default "";
}
