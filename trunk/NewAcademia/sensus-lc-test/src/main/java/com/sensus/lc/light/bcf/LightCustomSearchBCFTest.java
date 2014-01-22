package com.sensus.lc.light.bcf;

import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.bcl.ILightCustomSearchBCL;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.light.model.response.CustomSearchResponse;
import com.sensus.lc.light.model.response.LightFilterResponse;

@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/light/lightCustomSearchbcfimpletest.xml"})
public class LightCustomSearchBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant DEFAULT_LIGHT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG =
			"sensus.mlc.lightCustombcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_ALREADY_EXIST. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_ALREADY_EXIST =
			"sensus.mlc.customsearchvalidator.customsearch.already.exists";

	/** The light custom search bcf. */
	private ILightCustomSearchBCF lightCustomSearchBCF;

	/**
	 * Gets the light custom search bcf.
	 *
	 * @return the light custom search bcf
	 */
	public ILightCustomSearchBCF getLightCustomSearchBCF()
	{
		return lightCustomSearchBCF;
	}

	/**
	 * Sets the light custom search bcf.
	 *
	 * @param lightCustomSearchBCF the new light custom search bcf
	 */
	@Resource
	public void setLightCustomSearchBCF(ILightCustomSearchBCF lightCustomSearchBCF)
	{
		this.lightCustomSearchBCF = lightCustomSearchBCF;
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setLightArea();
	}

	/**
	 * Sets the smart point area.
	 */
	public void setLightArea()
	{
		setArea(getLightCustomSearchBCF(), LCAreaEnum.LIGHT);
	}

	/**
	 * Reset mocks to smart point area.
	 */
	@After
	public void resetMocksToLightArea()
	{
		resetMocksData(getLightCustomSearchBCF());
		setLightArea();
	}

	/**
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		// Test Validation Situation - User Context null
		LightRequest request = new LightRequest();
		request.setUserContext(null);
		CustomSearchResponse response = getLightCustomSearchBCF().fetchAllCustomSearch(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new LightRequest();
		request.setUserContext(new UserContext());
		response = getLightCustomSearchBCF().fetchAllCustomSearch(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new LightRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightCustomSearchBCF().fetchAllCustomSearch(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		request = new LightRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightCustomSearchBCF().fetchAllCustomSearch(request);
		assertTrue(response.isOperationSuccess());
		resetMocksToLightArea();

		// Error situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.ERROR, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.EXCEPTION, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch light filters.
	 */
	@Test
	public void testFetchLightFilters()
	{
		// Test Validation Situation - User Context null
		LightFilterRequest request = new LightFilterRequest();
		request.setUserContext(null);
		LightFilterResponse response = getLightCustomSearchBCF().fetchLightFilters(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new LightFilterRequest();
		request.setUserContext(new UserContext());
		response = getLightCustomSearchBCF().fetchLightFilters(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new LightFilterRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightCustomSearchBCF().fetchLightFilters(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		request = new LightFilterRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightCustomSearchBCF().fetchLightFilters(request);
		assertTrue(response.isOperationSuccess());
		resetMocksToLightArea();

		// Error situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.ERROR, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().fetchLightFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.EXCEPTION, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().fetchLightFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);
	}

	@Test
	public void testFetchAllColumnFilters()
	{
		// Test Validation Situation - User Context null
		ColumnFilterRequest request = new ColumnFilterRequest();
		request.setUserContext(null);
		ColumnFilterResponse response = getLightCustomSearchBCF().fetchAllColumnFilters(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new ColumnFilterRequest();
		request.setUserContext(new UserContext());
		response = getLightCustomSearchBCF().fetchAllColumnFilters(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new ColumnFilterRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightCustomSearchBCF().fetchAllColumnFilters(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		request = new ColumnFilterRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightCustomSearchBCF().fetchAllColumnFilters(request);
		assertTrue(response.isOperationSuccess());
		resetMocksToLightArea();

		// Error situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.ERROR, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.EXCEPTION, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		// Test Validation Situation - User Context null
		CustomSearchRequest request = new CustomSearchRequest();
		// request.setUserContext(null);
		CustomSearchResponse response = getLightCustomSearchBCF().insertCustomSearch(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new CustomSearchRequest();
		request.setUserContext(new UserContext());
		response = getLightCustomSearchBCF().insertCustomSearch(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new CustomSearchRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightCustomSearchBCF().insertCustomSearch(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Validation Situation - Custom with the same name.
		request = new CustomSearchRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName("Test Custom Search");
		request.setCustomSearch(customSearch);
		response = getLightCustomSearchBCF().insertCustomSearch(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_ALREADY_EXIST);

		// Success situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.VALIDATION, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().insertCustomSearch(request);
		assertTrue(response.isOperationSuccess());
		resetMocksToLightArea();

		// Error situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.ERROR, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.EXCEPTION, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);

	}

	/**
	 * Insert column filters.
	 */
	@Test
	public void insertColumnFilters()
	{
		// Test Validation Situation - User Context null
		ColumnFilterRequest request = new ColumnFilterRequest();
		request.setUserContext(null);
		ColumnFilterResponse response = getLightCustomSearchBCF().insertColumnFilters(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new ColumnFilterRequest();
		request.setUserContext(new UserContext());
		response = getLightCustomSearchBCF().insertColumnFilters(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new ColumnFilterRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightCustomSearchBCF().insertColumnFilters(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		request = new ColumnFilterRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightCustomSearchBCF().insertColumnFilters(request);
		assertTrue(response.isOperationSuccess());
		resetMocksToLightArea();

		// Error situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.ERROR, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().insertColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.EXCEPTION, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().insertColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		// Test Validation Situation - User Context null
		CustomSearchRequest request = new CustomSearchRequest();
		request.setUserContext(null);
		CustomSearchResponse response = getLightCustomSearchBCF().deleteCustomSearch(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new CustomSearchRequest();
		request.setUserContext(new UserContext());
		response = getLightCustomSearchBCF().deleteCustomSearch(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new CustomSearchRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightCustomSearchBCF().deleteCustomSearch(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Success situation
		request = new CustomSearchRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightCustomSearchBCF().deleteCustomSearch(request);
		assertTrue(response.isOperationSuccess());
		resetMocksToLightArea();

		// Error situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.ERROR, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightCustomSearchBCF(), SituationsEnum.EXCEPTION, ILightCustomSearchBCL.class);
		response = getLightCustomSearchBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_CUSTOM_SEARCH_BCF_EXCEPTION_MSG);
	}

}
