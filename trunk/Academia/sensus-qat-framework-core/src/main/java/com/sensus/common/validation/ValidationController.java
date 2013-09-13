package com.sensus.common.validation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.sensus.common.exception.SensusRuntimeException;
import com.sensus.common.util.SensusMessageUtil;

/**
 * This controller manages the execution of a list of {@link IValidator} instances.<br/>
 * The list of {@link IValidator} instances is specified using the Spring context.
 * When one the "validate" methods is invoked upon this class the list of validators is iterated through invoking
 * each of the validators and passing into them the {@link ValidationContext}.
 * Once configured using the Spring context this bean is then injected into the BAI client which requires
 * validation to be performed. Sample configuration follows:
 *
 * <pre>
 * 	&lt;bean id="validationController" class="com.sensus.common.validation.ValidationController">
 * 		&lt;property name="validators">
 * 			&lt;list >
 * 				&lt;ref bean="validator1" />
 * 				&lt;ref bean="validator2" />
 * 			&lt;/list>
 * 		&lt;/property>
 * 	&lt;/bean>
 * 	&lt;bean id="validator1" class="com.sensus.commontest.base.unittest.Validator1"/>
 * 	&lt;bean id="validator2" class="com.sensus.commontest.base.unittest.Validator2"/>
 * </pre>
 *
 * The {@link #allowEmptyValidatorList} property can be used during testing so you don't have to mock-up validators.
 * However, this property should never be true in production environments.
 */
public class ValidationController implements InitializingBean
{
	private static final Logger LOG = LoggerFactory.getLogger(ValidationController.class);
	private List<IValidator> validators = new ArrayList<IValidator>();
	private boolean allowEmptyValidatorList = false;

	/**
	 * This method can be used for both domain and business type validations. With the invocation of this method the
	 * ValidationController will iterator through the configured {@link IValidator} list invoking each one.
	 * If the {@link ValidationContext#isStopProcessing()} is set to true than subsequent validator processing will
	 * be terminated and this method will return immediately.
	 *
	 * @param validationContext An instance of {@link ValidationContext} containing all the data and arguments required
	 *            for validation.
	 * @return true indicating there are no error or fatal type messages posted to the validation context
	 *         message list. {@link SensusMessageUtil#hasErrors(List, boolean)} with the includeFatal parameter=true.
	 */
	public boolean validate(ValidationContext validationContext)
	{
		if (checkValidationContext(validationContext))
		{
			return false;
		}

		for (IValidator validator : getValidators())
		{
			validator.validate(validationContext);
			if (validationContext.isStopProcessing())
			{
				break;
			}
		}

		return !SensusMessageUtil.hasErrors(validationContext.getMessages(), true);
	}

	/**
	 * If the context is null or the object to be validated is empty that is bad so we log an error and return false.
	 */
	private boolean checkValidationContext(ValidationContext validationContext)
	{
		if (validationContext == null)
		{
			throw new SensusRuntimeException("Validation context is null.");
		}

		if ((validationContext.getObjectsToBeValidated() == null)
				|| (validationContext.getObjectsToBeValidated().size() == 0))
		{
			LOG.error("Objects to be validated is empty.");
		}

		return false;
	}

	/**
	 * A list of {@link IValidator} implementations in the form of a map with the key-value indicating the order in
	 * which the validators should be invoked.
	 *
	 * @param newValidators the new validators
	 */
	public void setValidators(List<IValidator> newValidators)
	{
		if (newValidators.size() > 0)
		{
			validators.addAll(newValidators);
		}
	}

	/**
	 * Gets the validators collection.
	 *
	 * @return the validators
	 */
	protected List<IValidator> getValidators()
	{
		return validators;
	}

	/**
	 * Sets the allow empty validator list. Should only be used in the context of testing.
	 *
	 * @param allowEmptyValidatorList True indicating to ignore when the validator collection is empty.
	 */
	public void setAllowEmptyValidatorList(boolean allowEmptyValidatorList)
	{
		this.allowEmptyValidatorList = allowEmptyValidatorList;
	}

	/**
	 * Checks if we should allow the validator collection to be empty. Should only be used in the context of testing.
	 *
	 * @return True indicating to ignore when the validator collection is empty.
	 */
	public boolean isAllowEmptyValidatorList()
	{
		return allowEmptyValidatorList;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 * Invoked after this bean is completely initialized and this is where we insure all the required properties are
	 * set.
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		if ((validators.size() == 0) && !isAllowEmptyValidatorList())
		{
			throw new Exception("The validators property must be set.");
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug(getValidators().size() + " validators loaded.");
		}
	}

}
