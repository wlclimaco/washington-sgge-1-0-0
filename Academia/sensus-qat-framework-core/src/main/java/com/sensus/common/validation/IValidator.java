package com.sensus.common.validation;

/**
 * This is the primary interface every "validator" must implement.
 * In general the appropriate validate methods will be invoked from the {@link ValidationUtil} class using the given
 * parameters. The validation method will perform the required validation and post messages to the included message
 * collection.
 */
public interface IValidator
{

	/**
	 * Perform validation using the validation context instance.
	 * 
	 * @param validationContext the validation context
	 */
	void validate(ValidationContext validationContext);

}
