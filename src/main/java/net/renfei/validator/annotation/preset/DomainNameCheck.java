package net.renfei.validator.annotation.preset;

import net.renfei.validator.annotation.Validator;
import net.renfei.validator.verifier.preset.DomainNameVerifierImpl;

import java.lang.annotation.*;

/**
 * 域名格式验证注解
 *
 * @author renfei
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = DomainNameVerifierImpl.class)
public @interface DomainNameCheck {
    String businessName() default "";
}
