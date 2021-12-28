package com.afc.springreact.shared;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { FileTypeValidator.class })
public @interface FileType {
    String message() default "{hoaxify.constraint.username.FileType.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	String[] types();
}
