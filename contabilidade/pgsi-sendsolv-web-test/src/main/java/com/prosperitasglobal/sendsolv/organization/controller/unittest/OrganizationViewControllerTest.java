package com.prosperitasglobal.sendsolv.organization.controller.unittest;

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
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.request.FetchByCodeRequest;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.model.request.RangeRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;
import com.prosperitasglobal.cbof.model.response.CountryResponse;
import com.prosperitasglobal.cbof.model.response.RangeResponse;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.PayrollTypeEnum;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;

/**
 * The Class OrganizationViewControllerTest.
 */
public class OrganizationViewControllerTest extends AbstractTestBase
{

	/** The Constant ORGANIZATION_ID. */
	private static final String ORGANIZATION_ID = "?organizationId=2974";

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	private static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	private static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant COUNTRIES. */
	private static final String COUNTRIES = "countries";

	/** The Constant RESPONSE. */
	private static final String RESPONSE = "response";

	private static final String ENROLLED_MEMBERS = "enrolled_organization";

	/** The URL mapping constants. */
	private static final String FETCH_LIST = "/organization";

	/** The Constant FETCH_ADD. */
	private static final String FETCH_ADD = "/organization/edit";

	/** The Constant INITIAL_LOAD. */
	private static final String INITIAL_LOAD = "?initialLoad=false";

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	/** The view mapping constants . */
	private static final String VIEW_ORGANIZATION_MAIN = "/organization/organization_main";

	/** The Constant VIEW_ORGANIZATION_ADD. */
	private static final String VIEW_ORGANIZATION_ADD = "/organization/organization_create";

	/** The Constant VIEW_ORGANIZATION. */
	private static final String VIEW_ORGANIZATION = "/organization/organization_view";

	/** The Constant VIEW_ORGANIZATION_DIALOG_ADD. */
	private static final String VIEW_ORGANIZATION_DIALOG_ADD = "/organization/organization_dialog_create";

	/** The organization bai. */
	private IOrganizationBAI organizationBAI;

	/** The location bai. */
	private ILocationBAI locationBAI;

	/** The country bai. */
	private ICountryBAI countryBAI;

	/** The range bai. */
	private IRangeBAI rangeBAI;

	/** The member bai. */
	private IMemberBAI memberBAI;

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

	/** The industry classification bai. */
	private IIndustryClassificationBAI industryClassificationBAI;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationAPIControllerTest.class);

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
	 * Gets the organization bai.
	 *
	 * @return the organization bai
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * Sets the organization bai.
	 *
	 * @param organizationBAI the organization bai
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
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
	 * Load organization list.
	 */
	@Test
	public void loadOrganizationList()
	{

		// Mock Organization Response
		OrganizationResponse response = new OrganizationResponse();
		response.setOrganizationList(new ArrayList<Organization>());
		for (int i = 0; i < 5; i++)
		{
			Organization organization = new Organization();
			organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
			organization.setId(i);
			organization.setIsPayrollCentralized(PayrollTypeEnum.CENTRALIZED);
			response.getOrganizationList().add(organization);
		}

		Mockito.when(
				getOrganizationBAI().fetchOrganizationByRequest(
						Matchers.any(PagedInquiryRequest.class)))
						.thenReturn(response);
		try
		{
			setData("");

			performTestGet(FETCH_LIST).andExpect(view().name(containsString(VIEW_ORGANIZATION_MAIN))).andExpect(
					(model().size(1))).andExpect(model().attributeExists(RESPONSE));

			// Check for success, model
			performTestGet(FETCH_LIST + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_ORGANIZATION_MAIN)))
			.andExpect(model().size(1));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	/**
	 * Load organization add.
	 */
	@Test
	public void loadOrganizationAdd()
	{

		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setMemberList(new ArrayList<Member>());
		for (int i = 0; i < 5; i++)
		{
			Member member = new Member();
			member.setId(i);
			memberResponse.getMemberList().add(member);
		}

		// Mock Organization Response
		OrganizationResponse response = new OrganizationResponse();
		response.setOrganizationList(new ArrayList<Organization>());
		Organization organization = new Organization();
		organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		organization.setId(1);
		organization.setIsPayrollCentralized(PayrollTypeEnum.CENTRALIZED);
		response.getOrganizationList().add(organization);

		// Mock Location Response
		LocationResponse locationResponse = new LocationResponse();
		locationResponse.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		locationResponse.getLocationList().add(location);

		// ============= Mock Country Response
		CountryResponse countryResponse = new CountryResponse();
		countryResponse.setCountryList(new ArrayList<Country>());
		for (int i = 0; i < 5; i++)
		{
			Country country = new Country();
			country.setCode("Code_" + i);
			country.setDescription("Description_" + i);
			countryResponse.getCountryList().add(country);
		}
		// ============= Mock Range Response
		RangeResponse rangeResponse = new RangeResponse();
		rangeResponse.setRangeList(new ArrayList<Range>());
		for (int i = 0; i < 5; i++)
		{
			Range range = new Range();
			range.setId(i);
			range.setName("Name_" + i);
			rangeResponse.getRangeList().add(range);
		}

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getOrganizationBAI().fetchOrganizationById(
						Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);

		// When BAI is invoked with any request, return Country response
		Mockito.when(
				getCountryBAI().fetchAllCountry(
						Matchers.any(FetchByCodeRequest.class)))
				.thenReturn(countryResponse);

		// When BAI is invoked with any request, return Country response
		Mockito.when(
				getCountryBAI().fetchAllKnownCountry(
						Matchers.any(FetchByCodeRequest.class)))
				.thenReturn(countryResponse);

		// When BAI is invoked with any request, return Range response
		Mockito.when(
				getRangeBAI().fetchAllRange(
						Matchers.any(RangeRequest.class)))
				.thenReturn(rangeResponse);

		Mockito.when(
				getLocationBAI().fetchLocationByOrganization(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(locationResponse);

		Mockito.when(
				getMemberBAI().fetchMemberByRequest(Matchers.any(MemberInquiryRequest.class)))
				.thenReturn(memberResponse);

		try
		{

			setData("");
			performTestGet(FETCH_ADD + ORGANIZATION_ID)
					.andExpect(view().name(containsString(VIEW_ORGANIZATION_ADD)))
					.andExpect((model().size(8)))
					.andExpect(model().attributeExists(RESPONSE))
					.andExpect(model().attributeExists(ENROLLED_MEMBERS))
					.andExpect(model().attributeExists(COUNTRIES))
					.andExpect(model().attributeExists(NUMBER_OF_EMPLOYEES))
					.andExpect(model().attributeExists(NUMBER_OF_MIGRANT_WORKERS));

			// Check for success, model
			performTestGet(FETCH_ADD + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_ORGANIZATION_ADD)))
					.andExpect(model().size(5));

			Mockito.verify(getOrganizationBAI().fetchOrganizationById(
					Matchers.any(FetchByIdRequest.class)));

			// When BAI is invoked with any request, return Country response
			Mockito.verify(
					getCountryBAI().fetchAllCountry(
							Matchers.any(FetchByCodeRequest.class)));

			// When BAI is invoked with any request, return Range response
			Mockito.verify(
					getRangeBAI().fetchAllRange(
							Matchers.any(RangeRequest.class)));

			// When BAI is invoked with any request, return CodeValue response
			Mockito.verify(
					getIndustryClassificationBAI().fetchAllSIC(
							Matchers.any(CodeValueRequest.class)));

			// When BAI is invoked with any request, return CodeValue response
			Mockito.verify(
					getIndustryClassificationBAI().fetchAllNAICS(
							Matchers.any(CodeValueRequest.class)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	// =============

	/**
	 * Load view.
	 */
	@Test
	public void loadView()
	{

		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setMemberList(new ArrayList<Member>());
		for (int i = 0; i < 5; i++)
		{
			Member member = new Member();
			member.setId(i);
			memberResponse.getMemberList().add(member);
		}

		// Mock Organization Response
		OrganizationResponse response = new OrganizationResponse();
		response.setOrganizationList(new ArrayList<Organization>());
		Organization organization = new Organization();
		organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		organization.setId(1);
		organization.setIsPayrollCentralized(PayrollTypeEnum.CENTRALIZED);
		response.getOrganizationList().add(organization);

		// Mock Location Response
		LocationResponse locationResponse = new LocationResponse();
		locationResponse.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		locationResponse.getLocationList().add(location);

		Mockito.when(
				getLocationBAI().fetchLocationByOrganization(
						Matchers.any(PagedInquiryRequest.class)))
						.thenReturn(locationResponse);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getOrganizationBAI().fetchOrganizationById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		Mockito.when(
				getMemberBAI().fetchMemberByRequest(Matchers.any(MemberInquiryRequest.class)))
				.thenReturn(memberResponse);

		try
		{

			setData("");
			performTestGet("/organization/view?organizationId=8417")
			.andExpect(view().name(containsString(VIEW_ORGANIZATION))).andExpect(
					(model().size(8))).andExpect(model().attributeExists(RESPONSE))
					.andExpect(model().attributeExists(ENROLLED_MEMBERS));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	/**
	 * Load view update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void loadViewUpdate() throws Exception
	{

		// ============= Mock Country Response
		CountryResponse countryResponse = new CountryResponse();
		countryResponse.setCountryList(new ArrayList<Country>());
		for (int i = 0; i < 5; i++)
		{
			Country country = new Country();
			country.setCode("Code_" + i);
			country.setDescription("Description_" + i);
			countryResponse.getCountryList().add(country);
		}
		// ============= Mock Range Response
		RangeResponse rangeResponse = new RangeResponse();
		rangeResponse.setRangeList(new ArrayList<Range>());
		for (int i = 0; i < 5; i++)
		{
			Range range = new Range();
			range.setId(i);
			range.setName("Name_" + i);
			rangeResponse.getRangeList().add(range);
		}

		// ============ Mock Code Value Response
		CodeValueResponse codeValueResponse = new CodeValueResponse();
		codeValueResponse.setCodeValueList(new ArrayList<CodeValue>());
		for (int i = 0; i < 5; i++)
		{
			CodeValue codeValue = new CodeValue();
			codeValue.setId(i);
			codeValue.setCode("Code_" + i);
			codeValueResponse.getCodeValueList().add(codeValue);
		}

		// When BAI is invoked with any request, return Country response
		Mockito.when(
				getCountryBAI().fetchAllCountry(
						Matchers.any(FetchByCodeRequest.class)))
						.thenReturn(countryResponse);

		// When BAI is invoked with any request, return Range response
		Mockito.when(
				getRangeBAI().fetchAllRange(
						Matchers.any(RangeRequest.class)))
						.thenReturn(rangeResponse);

		// When BAI is invoked with any request, return CodeValue response
		Mockito.when(
				getIndustryClassificationBAI().fetchAllSIC(
						Matchers.any(CodeValueRequest.class)))
						.thenReturn(codeValueResponse);

		// When BAI is invoked with any request, return CodeValue response
		Mockito.when(
				getIndustryClassificationBAI().fetchAllNAICS(
						Matchers.any(CodeValueRequest.class)))
						.thenReturn(codeValueResponse);

		try
		{

			setData("");
			performTestGet("/organization/editView")
			.andExpect(view().name(containsString(VIEW_ORGANIZATION_DIALOG_ADD))).andExpect(
					(model().size(5))).andExpect(model().attributeExists(COUNTRIES))
					.andExpect(model().attributeExists(NUMBER_OF_EMPLOYEES))
					.andExpect(model().attributeExists(NUMBER_OF_MIGRANT_WORKERS));

			// Check for success, model
			performTestGet("/organization/editView").andExpect(
					view().name(containsString(VIEW_ORGANIZATION_DIALOG_ADD)))
					.andExpect(model().size(5));

			performTestGet("/organization/editView");

			Mockito.calls(1);

			// When BAI is invoked with any request, return Country response
			Mockito.verify(
					getCountryBAI().fetchAllCountry(
							Matchers.any(FetchByCodeRequest.class)));

			// When BAI is invoked with any request, return Range response
			Mockito.verify(
					getRangeBAI().fetchAllRange(
							Matchers.any(RangeRequest.class)));

			// When BAI is invoked with any request, return CodeValue response
			Mockito.verify(
					getIndustryClassificationBAI().fetchAllSIC(
							Matchers.any(CodeValueRequest.class)));

			// When BAI is invoked with any request, return CodeValue response
			Mockito.verify(
					getIndustryClassificationBAI().fetchAllNAICS(
							Matchers.any(CodeValueRequest.class)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}
}