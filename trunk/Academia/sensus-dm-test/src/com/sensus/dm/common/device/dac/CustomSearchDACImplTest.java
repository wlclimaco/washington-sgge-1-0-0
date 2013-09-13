package com.sensus.dm.common.device.dac;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class CustomSearchDACImplTest.
 * 
 * @author QAT Global.
 */
public class CustomSearchDACImplTest extends AbstractTestBaseDAC
{
	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS. */
	private static final String SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS =
			"sensus.epm.customsearchvalidator.customsearch.already.exists";

	// -------------------------------------------------------------------------
	// Getters and setters:

	/** The custom search default. */
	private CustomSearch customSearchDefault;

	/**
	 * Gets the custom search default.
	 * 
	 * @return the custom search default
	 */
	public CustomSearch getCustomSearchDefault()
	{
		return customSearchDefault;
	}

	/**
	 * Sets the custom search default.
	 * 
	 * @param customSearchDefault the new custom search default
	 */
	public void setCustomSearchDefault(CustomSearch customSearchDefault)
	{
		this.customSearchDefault = customSearchDefault;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests Settings:

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		setCacheStatementScope(getCustomSearchDAC());
		setCustomSearchDefault(insertCustomSearch());
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test can insert custom search.
	 */
	@Test
	public void testCanInsertCustomSearch()
	{
		// Situation custom search can be inserted
		CustomSearchRequest request = TestBaseUtil.createCustomSearchRequestWithLocaleOnUserContext();
		request.setCustomSearch(new CustomSearch("CustomSearchNameTest", "CustomSearchDescriptionTest"));
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		InternalResultsResponse<Boolean> response = getCustomSearchDAC().canCustomSearchBeInserted(request);
		TestBaseUtil.assertResultResponse(response);

		// Situation custom search cannot be inserted
		request.setCustomSearch(getCustomSearchDefault());
		response = getCustomSearchDAC().canCustomSearchBeInserted(request);
		assertMessages(response, SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS);
		assertEquals("Status should be OperationSuccess", Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		InquiryCustomSearchRequest inquiryPaginationRequest = TestBaseUtil.createInquiryCustomSearchRequest();
		inquiryPaginationRequest.setTenant(TestBaseUtil.createTenant());
		InternalResultsResponse<CustomSearch> response =
				getCustomSearchDAC().fetchAllCustomSearch(inquiryPaginationRequest);
		TestBaseUtil.assertResultResponse(response);
	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		// Success Situation
		CustomSearchRequest request = TestBaseUtil.createCustomSearchRequest();
		request.setCustomSearch(getCustomSearchDefault());

		InternalResponse response = getCustomSearchDAC().deleteCustomSearch(request);
		TestBaseUtil.assertResponse(response);

		// Error Situation
		response = getCustomSearchDAC().deleteCustomSearch(request);
		assertEquals(Status.NoRowsRemovedError, response.getStatus());
	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		insertCustomSearch();
	}

	/**
	 * Test fetch all column filter.
	 */
	@Test
	public void testFetchAllColumnFilter()
	{
		upsertProperty(PropertyEnum.ELECTRIC_METER_COLUMN);

		ColumnFilterRequest request = new ColumnFilterRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setProperties(TestBaseUtil.createPropertyList(PropertyEnum.ELECTRIC_METER_COLUMN));
		request.setPropertyProviderType(TestBaseUtil.USER_PROVIDER + " " + TestBaseUtil.USER);
		request.setPropertyEnum(PropertyEnum.ELECTRIC_METER_COLUMN);

		InternalResultsResponse<Property> response = getCustomSearchDAC().fetchAllColumnFilter(request);
		TestBaseUtil.assertResultResponse(response);
	}
}
