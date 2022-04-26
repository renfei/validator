package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.MaxVerifierImpl;

import java.lang.annotation.*;

/**
 * 最大值验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = MaxVerifierImpl.class)
public @interface MaxCheck {
    long max() default 0;

    String businessName() default "";
}
