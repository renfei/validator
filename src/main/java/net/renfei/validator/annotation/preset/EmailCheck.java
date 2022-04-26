package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.EmailVerifierImpl;

import java.lang.annotation.*;

/**
 * Email验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = EmailVerifierImpl.class)
public @interface EmailCheck {
    String businessName() default "";
}
