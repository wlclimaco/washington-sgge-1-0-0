package com.sensus.dm.common.property.dac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Class PropertyDACImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:unittest-datasource-txn-context.xml",
		"classpath:com/sensus/dm/common/property/propertydacimpltest.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class PropertyDACImplTest extends AbstractTransactionalJUnit4SpringContextTests
{
	/** The Constant SYSTEM_USER. */
	private static final String SYSTEM_USER = "0";

	/** The Constant USER_PROVIDER. */
	private static final String USER_PROVIDER = "EPM.User";

	/** The Constant USER_ROD. */
	private static final String USER_ROD = "rod";

	/** The Constant EN_US. */
	private static final String EN_US = "en_US";

	/** The Constant INVALID_LANGUAGE. */
	private static final String INVALID_LANGUAGE = "XX_XX";

	/** The property DAC interface - injected. */
	private IPropertyDAC propertyDAC;

	/**
	 * Gets the property dac.
	 * 
	 * @return the property dac
	 */
	public IPropertyDAC getPropertyDAC()
	{
		return propertyDAC;
	}

	/**
	 * Sets the property dac.
	 * 
	 * @param propertyDAC the new property dac
	 */
	@Resource
	public void setPropertyDAC(IPropertyDAC propertyDAC)
	{
		this.propertyDAC = propertyDAC;
	}

	/**
	 * Test fetch property.
	 */
	@Test
	public void testFetchProperty()
	{
		// Insert Property
		testUpsertProperty();

		// Without Property Name
		PropertyRequest propertyRequest = new PropertyRequest();
		Property property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(USER_ROD);
		propertyRequest.setProperties(Arrays.asList(property));
		InternalResultsResponse<Property> response = getPropertyDAC().fetchProperty(propertyRequest);
		assertNotNull(response);
		assertEquals(false, response.isInError());
		assertNotNull(response.getResultsList());
		for (Property newProperty : response.getResultsList())
		{
			assertNotNull(newProperty.getPropertyName());
			assertNotNull(newProperty.getPropertyValue());
			assertNotNull(newProperty.toString());
		}

		// With Property Name
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());
		propertyRequest.setProperties(Arrays.asList(property));
		response = getPropertyDAC().fetchProperty(propertyRequest);
		assertEquals(false, response.isInError());
		assertNotNull(response.getResultsList());
		assertEquals(true, response.hasResults());
		property = response.getFirstResult();
		assertNotNull(property);
		assertNotNull(property.getPropertyName());
		assertNotNull(property.getPropertyValue());
	}

	/**
	 * Test upsert properties.
	 */
	@Test
	public void testUpsertProperty()
	{
		PropertyRequest request = new PropertyRequest();

		UserContext userContext = new UserContext();
		userContext.setUserId(USER_ROD);
		request.setUserContext(userContext);

		Property property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(USER_ROD);
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());
		property.setPropertyValue(EN_US);
		List<Property> properties = new ArrayList<Property>();
		properties.add(property);
		request.setProperties(properties);
		InternalResponse response = getPropertyDAC().upsertProperty(request);
		assertNotNull(response);
		assertEquals(false, response.isInError());
		// Test Failure
		property = new Property();
		property.setProviderId(USER_ROD);
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());
		property.setPropertyValue(INVALID_LANGUAGE);
		properties = new ArrayList<Property>();
		properties.add(property);
		request.setProperties(properties);
		request.setPropertyProviderType(USER_PROVIDER);

		response = getPropertyDAC().upsertProperty(request);

		assertNotNull(response);
		assertTrue(response.isInError());
		assertNotNull(response.getMessageInfoList());

		// Multiple insertion success
		properties = new ArrayList<Property>();

		property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(USER_ROD);
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());
		property.setPropertyValue(EN_US);
		properties.add(property);

		property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(USER_ROD);
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());
		property.setPropertyValue(EN_US);
		properties.add(property);

		properties.add(property);
		request.setAlwaysInsert(true);
		request.setProperties(properties);

		response = getPropertyDAC().upsertProperty(request);

		assertNotNull(response);
		assertEquals(false, response.isInError());

		// Multiple insertion, one fails

		properties = new ArrayList<Property>();
		property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(USER_ROD);
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());
		property.setPropertyValue(EN_US);
		properties.add(property);
		property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(SYSTEM_USER);
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());
		property.setPropertyValue(INVALID_LANGUAGE);
		properties.add(property);
		request.setProperties(properties);

		response = getPropertyDAC().upsertProperty(request);

		assertNotNull(response);
		assertTrue(response.isInError());
		assertNotNull(response.getMessageInfoList());
	}

	/**
	 * Test upsert settings.
	 */
	@Test
	public void testDeleteProperty()
	{
		// Success Situation
		PropertyRequest request = new PropertyRequest();

		Property property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(USER_ROD);
		property.setPropertyName(PropertyEnum.LANGUAGE.getValue());

		List<Property> properties = new ArrayList<Property>();
		properties.add(property);
		request.setProperties(properties);

		InternalResponse response = getPropertyDAC().deleteProperty(request);

		assertNotNull(response);
		assertEquals(false, response.isInError());

		property = new Property();
		property.setProviderId(USER_ROD);

		properties = new ArrayList<Property>();
		properties.add(property);
		request.setProperties(properties);
		request.setPropertyProviderType(USER_PROVIDER);

		response = getPropertyDAC().deleteProperty(request);

		assertNotNull(response);
		assertEquals(false, response.isInError());

		// Error Situation
		request = new PropertyRequest();

		request.setPropertyProviderType(USER_PROVIDER);

		response = getPropertyDAC().deleteProperty(request);

		assertNotNull(response);
		assertNotNull(response.getMessageInfoList());
		assertEquals(true, response.isInError());

	}

	/**
	 * Test delete property provider.
	 */
	@Test
	public void testDeletePropertyProvider()
	{
		// Success Situation
		PropertyRequest request = new PropertyRequest();

		Property property = new Property();
		property.setProviderType(USER_PROVIDER);
		property.setProviderId(USER_ROD);

		List<Property> properties = new ArrayList<Property>();
		properties.add(property);
		request.setProperties(properties);

		InternalResponse response = getPropertyDAC().deletePropertyProvider(request);

		assertNotNull(response);
		assertEquals(false, response.isInError());

		// Error Situation
		request = new PropertyRequest();

		request.setPropertyProviderType(USER_PROVIDER);

		response = getPropertyDAC().deletePropertyProvider(request);

		assertNotNull(response);
		assertNotNull(response.getMessageInfoList());
		assertEquals(true, response.isInError());

	}
}
