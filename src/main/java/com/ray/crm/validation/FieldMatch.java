package com.ray.crm.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = FieldMatchImpl.class)
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
public @interface FieldMatch {
	String message() default "The password must match";
	String first();
	String second();
	
	
	@Retention(RUNTIME)
	@Target({ TYPE, ANNOTATION_TYPE })
	@interface List
	{
		FieldMatch[] value();
	}
	
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
