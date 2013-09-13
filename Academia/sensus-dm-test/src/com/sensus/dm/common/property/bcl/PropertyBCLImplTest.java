package com.sensus.dm.common.property.bcl;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class PropertyBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/property/propertybclimpltest.xml"})
public class PropertyBCLImplTest extends AbstractTestBaseBusiness
{

	/** The property bcl. */
	private IPropertyBCL propertyBCL; // injected by Spring through setter

	/**
	 * Gets the property bcl.
	 * 
	 * @return the property bcl
	 */
	public IPropertyBCL getPropertyBCL()
	{
		return propertyBCL;
	}

	/**
	 * Sets the property bcl.
	 * 
	 * @param propertyBCL the new property bcl
	 */
	@Resource(name = "propertyBCLTarget")
	public void setPropertyBCL(IPropertyBCL propertyBCL)
	{
		this.propertyBCL = propertyBCL;
	}

	/**
	 * Sets the property area.
	 */
	public void setPropertyArea()
	{
		setArea(getPropertyBCL(), EPMAreaEnum.PROPERTY);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setPropertyArea();
	}

	/**
	 * Removes the property area.
	 */
	@After
	public void resetMocksToPropertyArea()
	{
		resetMocksData(getPropertyBCL());
		setPropertyArea();
	}

	/**
	 * Test upsert property.
	 */
	@Test
	public void testUpsertProperty()
	{
		// Success situation
		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		InternalResponse response = getPropertyBCL().upsertProperty(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test delete property.
	 */
	@Test
	public void testDeleteProperty()
	{
		// Success situation
		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		InternalResponse response = getPropertyBCL().deleteProperty(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test delete property provider.
	 */
	@Test
	public void testDeletePropertyProvider()
	{
		// Success situation
		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		InternalResponse response = getPropertyBCL().deletePropertyProvider(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch property.
	 */
	@Test
	public void testFetchProperty()
	{
		// Success situation
		PropertyRequest request = TestBaseUtil.createPropertyRequest();

		InternalResultsResponse<Property> response = getPropertyBCL().fetchProperty(request);
		TestBaseUtil.assertResultResponse(response);
	}
}
