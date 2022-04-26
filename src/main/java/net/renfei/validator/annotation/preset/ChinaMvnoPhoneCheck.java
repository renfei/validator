package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.ChinaMvnoPhoneVerifierImpl;

import java.lang.annotation.*;

/**
 * 中国虚拟运营商手机号验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = ChinaMvnoPhoneVerifierImpl.class)
public @interface ChinaMvnoPhoneCheck {
    String businessName() default "";
}
