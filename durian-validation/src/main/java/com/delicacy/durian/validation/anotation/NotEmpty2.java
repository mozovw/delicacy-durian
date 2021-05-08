package com.delicacy.durian.validation.anotation;



import com.delicacy.durian.validation.validator.NotEmptyValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NotEmptyValidator.class)
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public @interface NotEmpty2 {

    String message() default "{javax.validation.constraints.NotEmpty2.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}