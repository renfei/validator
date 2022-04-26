package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.NotNullVerifierImpl;

import java.lang.annotation.*;

/**
 * 非空验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = NotNullVerifierImpl.class)
public @interface NotNullCheck {
    String businessName() default "";
}
