package com.wigner.BoardGameWishlistFinder.vallidations;

import com.wigner.BoardGameWishlistFinder.annotations.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordValidation implements ConstraintValidator<Password, String> {

    List<String> weakPasswords;

    @Override
    public void initialize(Password constraintAnnotation) {
        weakPasswords = Arrays.asList("123456789", "987654321", "000000000");
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password != null && (!weakPasswords.contains(password));
    }
}
