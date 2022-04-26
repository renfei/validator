package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.IsNullVerifierImpl;

import java.lang.annotation.*;

/**
 * 为空验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = IsNullVerifierImpl.class)
public @interface IsNullCheck {
    String businessName() default "";
}
