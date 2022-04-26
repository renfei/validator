package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.LengthMaxVerifierImpl;

import java.lang.annotation.*;

/**
 * 数据最大长度验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = LengthMaxVerifierImpl.class)
public @interface LengthMaxCheck {
    /**
     * 长度
     *
     * @return
     */
    long max() default 0;

    String businessName() default "";
}
