package com.eason.transfer.openapi.core.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {

    @Autowired
    private MongoTemplate mongoTemplate;

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
