package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.UnifiedCodeVerifierImpl;

import java.lang.annotation.*;

/**
 * 统一社会信用代码检查
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = UnifiedCodeVerifierImpl.class)
public @interface UnifiedCodeCheck {
    String businessName() default "";
}
