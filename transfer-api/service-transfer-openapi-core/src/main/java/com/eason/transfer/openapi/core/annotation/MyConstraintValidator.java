package com.eason.transfer.openapi.core.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {
    
    private boolean require = false;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        require = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String target, ConstraintValidatorContext constraintValidatorContext) {
        if (target.equals("eason")) {
            return true;
        }
        return false;
    }


}
