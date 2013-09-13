package com.sensus.dm.common.device.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.bcl.ICustomSearchBCL;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.device.model.response.CustomSearchResponse;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class CustomSearchBCFImplTest.
 * 
 * @author QAT Global
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/device/customsearchbcfimpltest.xml"})
public class CustomSearchBCFImplTest extends AbstractTestBaseBusiness
{

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant SHOULD_BRING_ONE_CUSTOM_SEARCH. */
	private static final String SHOULD_BRING_ONE_CUSTOM_SEARCH = "should bring one custom search ";

	/** The Constant SHOULD_BRING_THE_ID. */
	private static final String SHOULD_BRING_THE_ID = "should bring the ID";

	/** The Constant INSERT_CUSTOM_SEARCH. */
	private static final String INSERT_CUSTOM_SEARCH = "insertCustomSearch";

	/** The Constant DELETE_CUSTOM_SEARCH. */
	private static final String DELETE_CUSTOM_SEARCH = "deleteCustomSearch";

	/** The Constant FETCH_ALL_CUSTOM_SEARCH. */
	private static final String FETCH_ALL_CUSTOM_SEARCH = "fetchAllCustomSearch";

	/** The Constant UPDATE_COLUMN_FILTERS. */
	private static final String UPDATE_COLUMN_FILTERS = "updateColumnFilters";

	/** The Constant FETCH_ALL_COLUMN_FILTERS. */
	private static final String FETCH_ALL_COLUMN_FILTERS = "fetchAllColumnFilters";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant SENSUS_EPM_CUSTOMSEARCHVALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_EPM_CUSTOMSEARCHVALIDATOR_NAME_REQUIRED =
			"sensus.epm.customsearchvalidator.name.required";

	/** The Constant SENSUS_EPM_CUSTOMSEARCHVALIDATOR_DEVICED_TYPE_REQUIRED. */
	private static final String SENSUS_EPM_CUSTOMSEARCHVALIDATOR_DEVICED_TYPE_REQUIRED =
			"sensus.epm.customsearchvalidator.device.type.required";

	/** The Constant SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_REQUIRED. */
	private static final String SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_REQUIRED =
			"sensus.epm.customsearchvalidator.customsearch.required";

	/** The Constant SENSUS_EPM_DEVICEVALIDATOR_SEARCH_NAME_INVALID. */
	private static final String SENSUS_EPM_DEVICEVALIDATOR_SEARCH_NAME_INVALID =
			"sensus.epm.devicevalidator.search.name.invalid";

	/** The Constant SENSUS_EPM_DEVICEVALIDATOR_SEARCH_DESCRIPTION_INVALID. */
	private static final String SENSUS_EPM_DEVICEVALIDATOR_SEARCH_DESCRIPTION_INVALID =
			"sensus.epm.devicevalidator.search.description.invalid";

	/** The Constant SENSUS_EPM_CUSTOMSEARCHVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_EPM_CUSTOMSEARCHVALIDATOR_ID_REQUIRED =
			"sensus.epm.customsearchvalidator.id.required";

	/** The Constant SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED =
			"sensus.epm.propertyvalidator.property.required";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The custom search bcf. */
	private ICustomSearchBCF customSearchBCF; // injected by Spring through setter

	/**
	 * Gets the custom search bcf.
	 * 
	 * @return the custom search bcf
	 */
	public ICustomSearchBCF getCustomSearchBCF()
	{
		return customSearchBCF;
	}

	/**
	 * Sets the custom search bcf.
	 * 
	 * @param customSearchBCF the new custom search bcf
	 */
	@Resource
	public void setCustomSearchBCF(ICustomSearchBCF customSearchBCF)
	{
		this.customSearchBCF = customSearchBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:

	/**
	 * Sets the custom search area.
	 */
	public void setCustomSearchArea()
	{
		setArea(getCustomSearchBCF(), EPMAreaEnum.CUSTOM_SEARCH);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setCustomSearchArea();
	}

	/**
	 * Removes the custom search area.
	 */
	@After
	public void resetMocksToCustomSearchArea()
	{
		resetMocksData(getCustomSearchBCF());
		setCustomSearchArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		// Validation Situation - user context required
		CustomSearchRequest request = new CustomSearchRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		CustomSearchResponse response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(new UserContext());
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Validation Situation - custom search required
		request = TestBaseUtil.createCustomSearchRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_REQUIRED);

		// Validation Situation - service type required
		request = TestBaseUtil.createCustomSearchRequest();
		request.setCustomSearch(TestBaseUtil.createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
		request.setTenant(TestBaseUtil.createTenant());
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - tenant required
		request = TestBaseUtil.createCustomSearchRequest();
		request.setCustomSearch(TestBaseUtil.createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(null);
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Validation Situation - customer id required
		request = TestBaseUtil.createCustomSearchRequest();
		request.setCustomSearch(TestBaseUtil.createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(new DMTenant(null));
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Validation Situation - name and device type required
		request = TestBaseUtil.createCustomSearchRequest();
		request.setCustomSearch(new CustomSearch());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		request.getCustomSearch().setName(null);
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_CUSTOMSEARCHVALIDATOR_NAME_REQUIRED,
				SENSUS_EPM_CUSTOMSEARCHVALIDATOR_DEVICED_TYPE_REQUIRED);

		// Validation Situation - name and description invalid
		request.getCustomSearch()
				.setName(StringUtils.repeat("n", ONE_HUNDRED_ONE));
		request.getCustomSearch()
				.setDescription(StringUtils.repeat("d", HUNDRED_FIFTY_ONE));
		request.getCustomSearch().setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_DEVICEVALIDATOR_SEARCH_NAME_INVALID,
				SENSUS_EPM_DEVICEVALIDATOR_SEARCH_DESCRIPTION_INVALID);

		// Success situation
		request.setCustomSearch(TestBaseUtil.createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_CUSTOM_SEARCH, 1, response.getCustomSearches().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getCustomSearches().get(0).getId());

		// Error situation
		setSituation(getCustomSearchBCF(), SituationsEnum.ERROR, ICustomSearchBCL.class,
				INSERT_CUSTOM_SEARCH);
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToCustomSearchArea();

		// Exception situation
		setSituation(getCustomSearchBCF(), SituationsEnum.EXCEPTION, ICustomSearchBCL.class,
				INSERT_CUSTOM_SEARCH);
		response = getCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		// Validation Situation - user context required
		CustomSearchRequest request = new CustomSearchRequest();
		CustomSearchResponse response = getCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(new UserContext());
		response = getCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Validation Situation - custom search required
		request = TestBaseUtil.createCustomSearchRequest();
		response = getCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_REQUIRED);

		// Validation Situation - id required
		request.setCustomSearch(new CustomSearch());
		response = getCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_CUSTOMSEARCHVALIDATOR_ID_REQUIRED);

		// Success situation
		request.setCustomSearch(TestBaseUtil.createCustomSearch(DeviceTypeEnum.ELECTRIC_METER));
		response = getCustomSearchBCF().deleteCustomSearch(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getCustomSearchBCF(), SituationsEnum.ERROR, ICustomSearchBCL.class,
				DELETE_CUSTOM_SEARCH);
		response = getCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToCustomSearchArea();

		// Exception situation
		setSituation(getCustomSearchBCF(), SituationsEnum.EXCEPTION, ICustomSearchBCL.class,
				DELETE_CUSTOM_SEARCH);
		response = getCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		// Validation Situation - user context required
		InquiryCustomSearchRequest request = new InquiryCustomSearchRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		InquiryCustomSearchResponse response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(new UserContext());
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Validation Situation - sort expression required
		request = new InquiryCustomSearchRequest(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(TestBaseUtil.createTenant());
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED);
		assertNotNull(response);

		// Validation Situation - page size and start row
		request.addSortExpressions(TestBaseUtil.createSortExpression());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		TestBaseUtil.createInvalidPageSizeStartRow(request);
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertMessages(response, START_ROW_INVALID, PAGE_SIZE_INVALID);

		// Validation Situation - Service Type
		request = TestBaseUtil.createInquiryCustomSearchRequest();
		request.setServiceTypeEnum(null);
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertMessages(response, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - tenant required
		request = TestBaseUtil.createInquiryCustomSearchRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(null);
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Validation Situation - customer id required
		request = TestBaseUtil.createInquiryCustomSearchRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(new DMTenant(null));
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Success Situation
		request = TestBaseUtil.createInquiryCustomSearchRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_CUSTOM_SEARCH, 1, response.getCustomSearches().size());
		assertNotNull(SHOULD_BRING_THE_ID, response.getCustomSearches().get(0).getId());

		// Error situation
		setSituation(getCustomSearchBCF(), SituationsEnum.ERROR, ICustomSearchBCL.class,
				FETCH_ALL_CUSTOM_SEARCH);
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToCustomSearchArea();

		// Exception situation
		setSituation(getCustomSearchBCF(), SituationsEnum.EXCEPTION, ICustomSearchBCL.class,
				FETCH_ALL_CUSTOM_SEARCH);
		response = getCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all column filters.
	 */
	@Test
	public void testFetchAllColumnFilters()
	{
		// Validation Situation - list type enumeration required
		ColumnFilterRequest request = new ColumnFilterRequest();
		ColumnFilterResponse response = getCustomSearchBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED);

		// Validation Situation - user context required
		request.setProperties(TestBaseUtil.createPropertyList(PropertyEnum.ELECTRIC_METER_COLUMN));
		response = getCustomSearchBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(new UserContext());
		response = getCustomSearchBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Success Situation
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getCustomSearchBCF().fetchAllColumnFilters(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(SHOULD_BRING_ONE_CUSTOM_SEARCH, 1, response.getListColumn().size());

		// Error situation
		setSituation(getCustomSearchBCF(), SituationsEnum.ERROR, ICustomSearchBCL.class,
				FETCH_ALL_COLUMN_FILTERS);
		response = getCustomSearchBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToCustomSearchArea();

		// Exception situation
		setSituation(getCustomSearchBCF(), SituationsEnum.EXCEPTION, ICustomSearchBCL.class,
				FETCH_ALL_COLUMN_FILTERS);
		response = getCustomSearchBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test insert column filters.
	 */
	@Test
	public void testUpdateColumnFilters()
	{
		// Validation Situation - list type enumeration required
		ColumnFilterRequest request = new ColumnFilterRequest();
		ColumnFilterResponse response = getCustomSearchBCF().updateColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_EPM_PROPERTYVALIDATOR_PROPERTY_REQUIRED);

		// Validation Situation - user context required
		request.setPropertyEnum(PropertyEnum.ELECTRIC_METER_COLUMN);
		response = getCustomSearchBCF().updateColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(new UserContext());
		response = getCustomSearchBCF().updateColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_ID_REQUIRED);

		// Success Situation
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getCustomSearchBCF().updateColumnFilters(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getCustomSearchBCF(), SituationsEnum.ERROR, ICustomSearchBCL.class,
				UPDATE_COLUMN_FILTERS);
		response = getCustomSearchBCF().updateColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToCustomSearchArea();

		// Exception situation
		setSituation(getCustomSearchBCF(), SituationsEnum.EXCEPTION, ICustomSearchBCL.class,
				UPDATE_COLUMN_FILTERS);
		response = getCustomSearchBCF().updateColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
	}
}
