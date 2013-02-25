package com.sensus.mlc.wui.smartpoint.unittest.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.base.util.SessionAuthenticationTestUtil;
import com.sensus.mlc.wui.settings.unittest.action.SettingsBCFMockImpl;
import com.sensus.mlc.wui.smartpoint.action.SmartpointAjaxAction;
import com.sensus.mlc.wui.tag.unittest.action.TagBCFMockImpl;

/**
 * Test SmartpointAjaxAction.
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class SmartpointAjaxActionTest extends StrutsSpringTestCase
{

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.StrutsSpringTestCase#getContextLocations()
	 */
	@Override
	public String getContextLocations()
	{
		return "classpath:context/sensus-mlc-wui-unittest-context-test.xml";
	}

	/** The Constant MESSAGE_RESPONSE_OBJECT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_PRESENT = "Response object present";

	/** The Constant MESSAGE_STRUTS_OUTCOME. */
	private static final String MESSAGE_STRUTS_OUTCOME = "Struts outcome";

	/** The Constant MESSAGE_RESPONSE_OBJECT_NOT_PRESENT. */
	private static final String MESSAGE_RESPONSE_OBJECT_NOT_PRESENT = "Response object not present";

	/** The logger. */
	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Test update list light protected.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdateListLightProtected() throws Exception
	{
		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		// Test failure due to missing light intensity
		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/updateListLightProtected.action");
		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();
		request.setSession(SessionAuthenticationTestUtil.setSessionTest());
		action.setServletRequest(request);

		LightRequest lightRequest = new LightRequest();

		lightRequest.setPaginationAllSelected(false);
		lightRequest.setProtect(true);
		List<Integer> selectionPaginationIds = new ArrayList<Integer>();
		lightRequest.setSelectionPaginationIds(selectionPaginationIds);

		action.setLightRequest(lightRequest);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointUpdaterBCFMockImpl bcf = (SmartPointUpdaterBCFMockImpl)action.getSmartPointUpdaterBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/updateListLightProtected.action");
		action = (SmartpointAjaxAction)proxy.getAction();

		lightRequest = new LightRequest();

		lightRequest.setPaginationAllSelected(false);
		lightRequest.setProtect(true);
		selectionPaginationIds = new ArrayList<Integer>();
		selectionPaginationIds.add(new Integer(23));
		lightRequest.setSelectionPaginationIds(selectionPaginationIds);

		action.setLightRequest(lightRequest);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test insert list smartpoint to tag.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testInsertListSmartpointToTag() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/insertListSmartpointToTag.action");
		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();

		TagRequest tagRequest = new TagRequest();

		action.setTagRequest(tagRequest);
		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/insertListSmartpointToTag.action");
		action = (SmartpointAjaxAction)proxy.getAction();

		tagRequest = new TagRequest();

		List<Tag> tags = new ArrayList<Tag>();

		Tag tag01 = new Tag();
		tags.add(tag01);

		tagRequest.setTags(tags);

		List<Integer> lights = new ArrayList<Integer>();

		lights.add(1);

		tagRequest.setSelectionPaginationIds(lights);

		action.setTagRequest(tagRequest);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/

		logger.debug("TESTING EXCEPTION");

		proxy = getActionProxy("/smartpoint/insertListSmartpointToTag.action");
		action = (SmartpointAjaxAction)proxy.getAction();

		bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());

	}

	/**
	 * Test remove list smartpoint to tag.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testRemoveListSmartpointToTag() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/removeListSmartpointToTag.action");
		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();

		TagRequest tagRequest = new TagRequest();

		action.setTagRequest(tagRequest);

		SessionAuthenticationTestUtil.setMockSession();

		TagBCFMockImpl bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/removeListSmartpointToTag.action");
		action = (SmartpointAjaxAction)proxy.getAction();

		tagRequest = new TagRequest();

		List<Tag> tags = new ArrayList<Tag>();

		Tag tag01 = new Tag();
		tags.add(tag01);

		tagRequest.setTags(tags);

		List<Integer> smartpoints = new ArrayList<Integer>();

		Integer smp01 = new Integer(1001);
		smartpoints.add(smp01);

		action.setTagRequest(tagRequest);

		bcf = (TagBCFMockImpl)action.getTagBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test clear smart points alerts.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testClearSmartPointsAlerts() throws Exception
	{

		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/clearSmartPointsAlerts.action");
		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();

		LightRequest request = new LightRequest();

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointProcessorBCFMockImpl bcf = (SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/clearSmartPointsAlerts.action");
		action = (SmartpointAjaxAction)proxy.getAction();

		request = new LightRequest();

		Light light = new Light();
		request.setLights(new ArrayList<Light>());
		request.getLights().add(light);

		List<Integer> ids = new ArrayList<Integer>();

		for (int i = 0; i <= 5; i++)
		{
			ids.add(i);
		}

		request.setSelectionPaginationIds(ids);

		action.setLightRequest(request);

		bcf = (SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test update smart points status.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdateSmartPointsStatus() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/updateSmartPointsStatus.action");
		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();

		LightRequest request = new LightRequest();

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointProcessorBCFMockImpl bcf = (SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);
		String response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/updateSmartPointsStatus.action");
		action = (SmartpointAjaxAction)proxy.getAction();

		request = new LightRequest();

		Light light = new Light();
		request.getLights().add(light);

		List<Integer> ids = new ArrayList<Integer>();

		for (int i = 0; i <= 5; i++)
		{
			ids.add(i);
		}

		request.setSelectionPaginationIds(ids);

		action.setLightRequest(request);

		bcf = (SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);
		response = proxy.execute();

		/** Asserts **/
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, response);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

	}

	/**
	 * Test save search.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testSaveSearch() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/saveSearch.action");

		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		CustomSearchRequest customSearchReq = new CustomSearchRequest();
		action.setCustomSearchRequest(customSearchReq);
		// Create REQUEST mock ---- END

		SmartPointUpdaterBCFMockImpl bcf = (SmartPointUpdaterBCFMockImpl)action.getSmartPointUpdaterBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/saveSearch.action");
		action = (SmartpointAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		customSearchReq = new CustomSearchRequest();

		CustomSearch customSearch = new CustomSearch();
		customSearch.setName("Custom Search NAME Mock");
		customSearch.setDescription("Custom Search DESCRIPTION Mock");

		List<Column> listColumns = new ArrayList<Column>();
		Column column1 = new Column();
		column1.setColumnEnum(SmartPointColumnEnum.POLE_ID);

		listColumns.add(column1);

		Column column2 = new Column();
		column2.setColumnEnum(SmartPointColumnEnum.RNI_ID);

		listColumns.add(column2);

		customSearch.setListColumn(listColumns);
		customSearchReq.setCustomSearch(customSearch);
		action.setCustomSearchRequest(customSearchReq);
		// Create REQUEST mock ---- END

		bcf = (SmartPointUpdaterBCFMockImpl)action.getSmartPointUpdaterBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/

		logger.debug("TESTING EXCEPTION");

		proxy = getActionProxy("/smartpoint/saveSearch.action");
		action = (SmartpointAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		customSearchReq = new CustomSearchRequest();

		customSearch = new CustomSearch();
		customSearch.setName("Custom Search NAME Mock");

		listColumns = new ArrayList<Column>();
		column1 = new Column();
		column1.setColumnEnum(SmartPointColumnEnum.POLE_ID);

		listColumns.add(column1);

		customSearch.setListColumn(listColumns);
		customSearchReq.setCustomSearch(customSearch);
		action.setCustomSearchRequest(customSearchReq);
		// Create REQUEST mock ---- END

		bcf = (SmartPointUpdaterBCFMockImpl)action.getSmartPointUpdaterBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());

	}

	/**
	 * Test insert column filter.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testInsertColumnFilter() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/ajax.insertColumnFilter.action");

		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
		action.setColumnFilterRequest(columnFilterRequest);
		// Create REQUEST mock ---- END

		SettingsBCFMockImpl bcf = (SettingsBCFMockImpl)action.getSettingsBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Failure", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/ajax.insertColumnFilter.action");
		action = (SmartpointAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		columnFilterRequest = new ColumnFilterRequest();

		List<Column> listColumns = new ArrayList<Column>();

		Column column1 = new Column();
		column1.setColumnEnum(SmartPointColumnEnum.POLE_ID);
		Column column2 = new Column();
		column2.setColumnEnum(SmartPointColumnEnum.RNI_ID);

		listColumns.add(column1);
		listColumns.add(column2);

		columnFilterRequest.setListColumn(listColumns);

		List<Filter> filters = new ArrayList<Filter>();

		Filter filter = new Filter();

		filter.setFilterEnum(SmartPointFilterEnum.GROUPS);

		filters.add(filter);

		columnFilterRequest.setFilters(filters);
		columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);

		action.setColumnFilterRequest(columnFilterRequest);
		// Create REQUEST mock ---- END

		bcf = (SettingsBCFMockImpl)action.getSettingsBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING EXCEPTION
		 * 
		 ***********************************/

		logger.debug("TESTING EXCEPTION");

		proxy = getActionProxy("/smartpoint/ajax.insertColumnFilter.action");
		action = (SmartpointAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		columnFilterRequest = new ColumnFilterRequest();

		listColumns = new ArrayList<Column>();

		column1 = new Column();
		column1.setColumnEnum(SmartPointColumnEnum.POLE_ID);
		listColumns.add(column1);

		action.setColumnFilterRequest(columnFilterRequest);

		columnFilterRequest.setListColumn(listColumns);

		filters = new ArrayList<Filter>();

		filter = new Filter();

		filter.setFilterEnum(SmartPointFilterEnum.GROUPS);

		filters.add(filter);

		columnFilterRequest.setFilters(filters);

		// Create REQUEST mock ---- END

		bcf = (SettingsBCFMockImpl)action.getSettingsBCF();
		bcf.setMode(BaseMockImpl.MODE_EXCEPTION);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNull(MESSAGE_RESPONSE_OBJECT_NOT_PRESENT, action.getResponse());

	}

	/**
	 * Test update light status.
	 */
	@Test
	public void testUpdateLightStatus()
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		LightRequest lightRequest = new LightRequest();

		SmartpointAjaxAction action =
				(SmartpointAjaxAction)getActionProxy("/smartpoint/ajax.updateLightStatus.action").getAction();
		action.setServletRequest(request);

		SessionAuthenticationTestUtil.setMockSession();

		SmartPointProcessorBCFMockImpl smartpointBCF =
				(SmartPointProcessorBCFMockImpl)action.getSmartPointProcessorBCF();

		logger.info("Testing reset values error...");
		smartpointBCF.setMode(BaseMockImpl.MODE_FAILURE);
		logger.info("Testing reset values for light success...");
		smartpointBCF.setMode(BaseMockImpl.MODE_SUCCESS);
		action.setLightRequest(lightRequest);
		assertEquals(Action.SUCCESS, action.updateLightStatus());
	}

	/**
	 * Test update list group protected.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testFetchLightsToMap() throws Exception
	{
		SessionAuthenticationTestUtil.setAuthenticationTest();
		SessionAuthenticationTestUtil.setMockSession();

		request.setSession(SessionAuthenticationTestUtil.setSessionTest());

		/***********************************
		 * 
		 * TESTING FAILURE
		 * 
		 ***********************************/

		logger.debug("TESTING FAILURE");

		ActionProxy proxy = getActionProxy("/smartpoint/ajax.fetchLightsToMap.action");

		SmartpointAjaxAction action = (SmartpointAjaxAction)proxy.getAction();

		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		InquiryLightRequest inquiryLightRequest = new InquiryLightRequest();
		action.setInquiryLightRequest(inquiryLightRequest);
		// Create REQUEST mock ---- END

		SmartPointAccessorBCFMockImpl bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_FAILURE);

		/** Asserts **/
		String actionResponse = proxy.execute();
		assertNotNull(action.getResponse());
		assertEquals("Operation is Failed", false, action.getResponse().isOperationSuccess());

		/***********************************
		 * 
		 * TESTING SUCCESS
		 * 
		 ***********************************/

		logger.debug("TESTING SUCCESS");

		proxy = getActionProxy("/smartpoint/ajax.fetchLightsToMap.action");
		action = (SmartpointAjaxAction)proxy.getAction();
		SessionAuthenticationTestUtil.setMockSession();
		action.setServletRequest(request);

		// Create REQUEST mock ---- BEGIN
		// inquiryLightRequest = new GroupRequest();

		inquiryLightRequest.setBottomLeftLat(28.951829032407);
		inquiryLightRequest.setBottomLeftLon(-81.751966796745);

		inquiryLightRequest.setTopRightLat(42.319083058091);
		inquiryLightRequest.setTopRightLon(-75.270033203254);

		inquiryLightRequest.setMaxSmartpointCount(50);

		inquiryLightRequest.setTimezone("America/Resolute");

		inquiryLightRequest.setSelectionPaginationIds(new ArrayList<Integer>());
		// inquiryLightRequest.setGroup(group);
		action.setInquiryLightRequest(inquiryLightRequest);
		// Create REQUEST mock ---- END

		bcf = (SmartPointAccessorBCFMockImpl)action.getSmartPointAccessorBCF();
		bcf.setMode(BaseMockImpl.MODE_SUCCESS);

		/** Asserts **/
		actionResponse = proxy.execute();
		assertEquals(MESSAGE_STRUTS_OUTCOME, Action.SUCCESS, actionResponse);
		assertNotNull(MESSAGE_RESPONSE_OBJECT_PRESENT, action.getResponse());
		assertEquals("Operation is Success", true, action.getResponse().isOperationSuccess());
	}
}