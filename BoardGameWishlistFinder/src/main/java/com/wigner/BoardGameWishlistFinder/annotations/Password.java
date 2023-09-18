package com.wigner.BoardGameWishlistFinder.annotations;

import com.wigner.BoardGameWishlistFinder.vallidations.PasswordValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "Please choose a strong password!";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};

}
