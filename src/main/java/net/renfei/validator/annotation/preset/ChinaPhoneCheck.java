package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.ChinaPhoneVerifierImpl;

import java.lang.annotation.*;

/**
 * 中国手机号验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = ChinaPhoneVerifierImpl.class)
public @interface ChinaPhoneCheck {
    String businessName() default "";
}
