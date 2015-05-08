package com.prosperitasglobal.sendsolv.unittest.validation;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.validation.LiaisonValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationContextIndicator;

/**
 * The Class ValidatorsTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/unittest-datasource-txn-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/sendsolv-dac-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
@ActiveProfiles("sqlserver")
public class ValidatorsTest extends AbstractJUnit4SpringContextTests
{

	/** The liaison validator. */
	private LiaisonValidator liaisonValidator;

	/**
	 * Gets the liaison validator.
	 *
	 * @return the liaison validator
	 */
	public LiaisonValidator getLiaisonValidator()
	{
		return liaisonValidator;
	}

	/**
	 * Sets the liaison validator.
	 *
	 * @param liaisonValidator the liaison validator
	 */
	@Resource
	public void setLiaisonValidator(LiaisonValidator liaisonValidator)
	{
		this.liaisonValidator = liaisonValidator;
	}

	/**
	 * Test liaison validator.
	 */
	@Test
	public void testLiaisonValidator()
	{
		ValidationContext validationContext = new ValidationContext();

		Liaison liaison = new Liaison();

		validationContext.getObjectsToBeValidated().put(Liaison.class.getSimpleName(), liaison);
		validationContext.putValidationContextIndicator(ValidationContextIndicator.INSERT);

		getLiaisonValidator().validate(validationContext);

		Assert.assertTrue(validationContext.getMessages().size() > 0);

	}
}
