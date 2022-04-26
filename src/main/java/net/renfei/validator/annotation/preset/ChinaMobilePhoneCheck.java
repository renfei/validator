package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.ChinaMobilePhoneVerifierImpl;

import java.lang.annotation.*;

/**
 * 中国移动手机号验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = ChinaMobilePhoneVerifierImpl.class)
public @interface ChinaMobilePhoneCheck {
    String businessName() default "";
}
