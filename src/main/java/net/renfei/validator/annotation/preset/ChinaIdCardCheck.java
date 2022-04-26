package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.ChinaIdCardVerifierImpl;

import java.lang.annotation.*;

/**
 * 中国身份证号码验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = ChinaIdCardVerifierImpl.class)
public @interface ChinaIdCardCheck {
    String businessName() default "";
}
