package com.prosperitasglobal.sendsolv.location.controller.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.bai.IIndustryClassificationBAI;
import com.prosperitasglobal.cbof.bai.IRangeBAI;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;

/**
 * The Class LocationViewControllerTest.
 */
public class LocationViewControllerTest extends AbstractTestBase
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "/location";

	/** The Constant FETCH_ADD. */
	private static final String FETCH_ADD = "/location/edit";

	/** The Constant EDIT_VIEW. */
	private static final String EDIT_VIEW = "/location/editView";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW = "/location/view";

	/** The Constant FETCH_ORGANIZATION_BYLOCATION. */
	private static final String FETCH_ORGANIZATION_BYLOCATION = "/location/fetchOrganizationBylocation?locationId=8417";

	/** The Constant INITIAL_LOAD. */
	private static final String INITIAL_LOAD = "?initialLoad=false";

	/** The view mapping constants . */
	private static final String VIEW_LOCATION_MAIN = "/location/location_main";

	/** The Constant VIEW_LOCATION_ADD. */
	private static final String VIEW_LOCATION_ADD = "/location/location_create";

	/** The Constant ORGANIZATION_BY_LOCATION_MAIN. */
	private static final String ORGANIZATION_BY_LOCATION_MAIN = "/organization/organizationBylocation_main";

	/** The Constant VIEW_LOCATION_DIALOG_ADD. */
	private static final String VIEW_LOCATION_DIALOG_ADD = "/location/location_dialog_create";

	/** The Response constants. */
	public static final String COUNTRIES = "countries";

	public static final String RESPONSE = "response";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant LOCATION_ID. */
	private static final String LOCATION_ID = "?locationId=2974";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LocationAPIControllerTest.class);

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The location bai. */
	private ILocationBAI locationBAI;

	/** The member bai. */
	private IMemberBAI memberBAI;

	/** The country bai. */
	private ICountryBAI countryBAI;

	/** The range bai. */
	private IRangeBAI rangeBAI;

	/** The industry classification bai. */
	private IIndustryClassificationBAI industryClassificationBAI;

	/**
	 * Gets the member bai.
	 *
	 * @return the member bai
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the member bai
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/** The exception. */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Gets the country bai.
	 *
	 * @return the country bai
	 */
	public ICountryBAI getCountryBAI()
	{
		return countryBAI;
	}

	/**
	 * Sets the country bai.
	 *
	 * @param countryBAI the country bai
	 */
	@Resource
	public void setCountryBAI(ICountryBAI countryBAI)
	{
		this.countryBAI = countryBAI;
	}

	/**
	 * Gets the range bai.
	 *
	 * @return the range bai
	 */
	public IRangeBAI getRangeBAI()
	{
		return rangeBAI;
	}

	/**
	 * Sets the range bai.
	 *
	 * @param rangeBAI the range bai
	 */
	@Resource
	public void setRangeBAI(IRangeBAI rangeBAI)
	{
		this.rangeBAI = rangeBAI;
	}

	/**
	 * Gets the industry classification bai.
	 *
	 * @return the industry classification bai
	 */
	public IIndustryClassificationBAI getIndustryClassificationBAI()
	{
		return industryClassificationBAI;
	}

	/**
	 * Sets the industry classification bai.
	 *
	 * @param industryClassificationBAI the industry classification bai
	 */
	@Resource
	public void setIndustryClassificationBAI(IIndustryClassificationBAI industryClassificationBAI)
	{
		this.industryClassificationBAI = industryClassificationBAI;
	}

	/**
	 * Load location by organization list.
	 */
	@Test
	public void loadLocationByOrganizationList()
	{

		// Mock Organization Response
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		for (int i = 0; i < 5; i++)
		{
			Location location = new Location();
			location.setBusinessType(BusinessTypeEnum.LOCATION);
			location.setId(i);
			response.getLocationList().add(location);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationByOrganization(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(response);
		try
		{
			setData("");

			performTestGet(FETCH_ORGANIZATION_BYLOCATION)
			.andExpect(view().name(containsString(ORGANIZATION_BY_LOCATION_MAIN)))
					.andExpect(
							(model().size(1))).andExpect(model().attributeExists("response"));

			Mockito.verify(
					getLocationBAI().fetchLocationByOrganization(
							Matchers.any(PagedInquiryRequest.class)));

			Mockito.reset(
					getLocationBAI().fetchLocationByOrganization(
							Matchers.any(PagedInquiryRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
		// ============================= Exception situation ==================================

		try
		{
			// Check for exception, model
			Mockito.when(
					getLocationBAI().fetchLocationByOrganization(
							Matchers.any(PagedInquiryRequest.class))).thenThrow(new RuntimeException());

			performTestGet(FETCH_ORGANIZATION_BYLOCATION);

			Mockito.verify(
					getLocationBAI().fetchLocationByOrganization(
							Matchers.any(PagedInquiryRequest.class)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	/**
	 * Load location list.
	 */
	@Test
	public void loadLocationList()
	{

		// Mock Organization Response
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		for (int i = 0; i < 5; i++)
		{
			Location location = new Location();
			location.setBusinessType(BusinessTypeEnum.LOCATION);
			location.setId(i);
			response.getLocationList().add(location);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationByRequest(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(response);
		try
		{
			setData("");

			performTestGet(FETCH_LIST).andExpect(view().name(containsString(VIEW_LOCATION_MAIN))).andExpect(
					(model().size(1))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(FETCH_LIST + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_LOCATION_MAIN)))
			.andExpect(model().size(1));

			Mockito.verify(
					getLocationBAI().fetchLocationByRequest(
							Matchers.any(PagedInquiryRequest.class)));

			Mockito.reset(
					getLocationBAI().fetchLocationByRequest(
							Matchers.any(PagedInquiryRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	/**
	 * Load view update.
	 */
	@Test
	public void loadViewUpdate()
	{
		// Mock Location Response
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		response.getLocationList().add(location);

		// Mock Member Response
		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		memberResponse.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getLocationBAI().fetchLocationById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		Mockito.when(
				getMemberBAI().fetchMemberByRequest(
						Matchers.any(MemberInquiryRequest.class)))
						.thenReturn(memberResponse);

		try
		{

			setData("");
			performTestGet(EDIT_VIEW + LOCATION_ID)
			.andExpect(view().name(containsString(VIEW_LOCATION_DIALOG_ADD))).andExpect(
					(model().size(7))).andExpect(model().attributeExists(RESPONSE));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	/**
	 * Load location add.
	 */
	@Test
	public void loadLocationAdd()
	{
		// Mock Location Response
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		response.getLocationList().add(location);

		// Mock Member Response
		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		memberResponse.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getLocationBAI().fetchLocationById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		Mockito.when(
				getMemberBAI().fetchMemberByRequest(
						Matchers.any(MemberInquiryRequest.class)))
						.thenReturn(memberResponse);

		try
		{

			setData("");
			performTestGet(FETCH_ADD + LOCATION_ID)
			.andExpect(view().name(containsString(VIEW_LOCATION_ADD)))
			.andExpect((model().size(7)))
			.andExpect(model().attributeExists(RESPONSE))
			.andExpect(model().attributeExists(ENROLLED_MEMBERS));

			// Check for success, model
			performTestGet(FETCH_ADD + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_LOCATION_ADD)))
			.andExpect(model().size(5));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	/**
	 * Load view.
	 */
	@Test
	public void loadView()
	{
		// Mock Location Response
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		response.getLocationList().add(location);

		// Mock Member Response
		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		memberResponse.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getLocationBAI().fetchLocationById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		Mockito.when(
				getMemberBAI().fetchMemberByRequest(
						Matchers.any(MemberInquiryRequest.class)))
						.thenReturn(memberResponse);

		try
		{

			setData("");
			performTestGet(FETCH_VIEW + LOCATION_ID)
			.andExpect(view().name(containsString("location/location_tabs")))
			.andExpect((model().size(0)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

}
