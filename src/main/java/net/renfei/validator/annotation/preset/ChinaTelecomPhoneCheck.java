package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.ChinaTelecomPhoneVerifierImpl;

import java.lang.annotation.*;

/**
 * 中国电信手机号验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = ChinaTelecomPhoneVerifierImpl.class)
public @interface ChinaTelecomPhoneCheck {
    String businessName() default "";
}
