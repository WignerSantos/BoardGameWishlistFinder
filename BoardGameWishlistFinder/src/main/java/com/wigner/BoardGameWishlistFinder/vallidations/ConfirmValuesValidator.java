package com.wigner.BoardGameWishlistFinder.vallidations;

import com.wigner.BoardGameWishlistFinder.annotations.ConfirmValuesMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ConfirmValuesValidator implements ConstraintValidator<ConfirmValuesMatch, Object> {

    private String field;

    private String fieldMatch;

    @Override
    public void initialize(ConfirmValuesMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {

        Object fieldValue = new BeanWrapperImpl(obj).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(obj).getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return false;
        }

    }
}
