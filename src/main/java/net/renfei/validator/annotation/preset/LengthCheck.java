package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.LengthVerifierImpl;

import java.lang.annotation.*;

/**
 * 数据长度验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = LengthVerifierImpl.class)
public @interface LengthCheck {
    /**
     * 长度
     *
     * @return
     */
    long length() default 0;

    String businessName() default "";
}
