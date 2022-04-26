package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.ChinaUnicomPhoneVerifierImpl;

import java.lang.annotation.*;

/**
 * 中国联通手机号验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = ChinaUnicomPhoneVerifierImpl.class)
public @interface ChinaUnicomPhoneCheck {
    String businessName() default "";
}
