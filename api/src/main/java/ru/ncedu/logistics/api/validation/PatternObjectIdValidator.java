package ru.ncedu.logistics.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PatternObjectIdValidator implements ConstraintValidator<PatternObjectId, String> {

    private static final Pattern OBJECT_ID_PATTERN = Pattern.compile("[0-9a-f]{24}");

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return OBJECT_ID_PATTERN.matcher(string).matches();
    }
}
