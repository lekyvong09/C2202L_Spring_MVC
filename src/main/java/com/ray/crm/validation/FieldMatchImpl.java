package com.ray.crm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchImpl implements ConstraintValidator<FieldMatch, Object>{

	private String firstFieldName;
	private String secondFieldName;
	private String message;
	
	
	
	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}



	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
	
		boolean result = true;
			
		try {
			Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
			Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
			
			result = firstObj == null && secondObj == null 
					|| firstObj != null && firstObj.equals(secondObj);
			
		} catch (Exception ignore) {
			/// ignore
		}
		
		
		if (!result) {
			context.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(secondFieldName)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		}
		
		return result;
	}
	
}
