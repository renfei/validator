package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.MinVerifierImpl;

import java.lang.annotation.*;

/**
 * 最小值验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = MinVerifierImpl.class)
public @interface MinCheck {
    long min() default 0;

    String businessName() default "";
}
