package com.prosperitasglobal.sendsolv.contact.controller.unittest;

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
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;

public class ContactViewControllerTest extends AbstractTestBase
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "/contact";
	private static final String FETCH_ADD = "/contact/insert";
	private static final String EDIT = "/contact/update";

	private static final String INITIAL_LOAD = "?initialLoad=false";

	/** The view mapping constants . */
	private static final String VIEW_CONTACT_MAIN = "/contact/contact_main";
	private static final String VIEW_CONTACT_ADD = "/contact/contact_dialog_create";
	private static final String VIEW_CONTACT_DIALOG_ADD = "/contact/contact_dialog_create";

	/** The Response constants */
	public static final String COUNTRIES = "countries";
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";
	public static final String STATUS = "status";
	private static final String CONTACT_ID = "?contactId=2974";

	private static final Logger LOG = LoggerFactory.getLogger(ContactAPIControllerTest.class);

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private ILiaisonBAI contactBAI;

	private ICountryBAI countryBAI;

	private IRangeBAI rangeBAI;

	private IIndustryClassificationBAI industryClassificationBAI;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	public ILiaisonBAI getContactBAI()
	{
		return contactBAI;
	}

	@Resource
	public void setContactBAI(ILiaisonBAI contactBAI)
	{
		this.contactBAI = contactBAI;
	}

	public ICountryBAI getCountryBAI()
	{
		return countryBAI;
	}

	@Resource
	public void setCountryBAI(ICountryBAI countryBAI)
	{
		this.countryBAI = countryBAI;
	}

	public IRangeBAI getRangeBAI()
	{
		return rangeBAI;
	}

	@Resource
	public void setRangeBAI(IRangeBAI rangeBAI)
	{
		this.rangeBAI = rangeBAI;
	}

	public IIndustryClassificationBAI getIndustryClassificationBAI()
	{
		return industryClassificationBAI;
	}

	@Resource
	public void setIndustryClassificationBAI(IIndustryClassificationBAI industryClassificationBAI)
	{
		this.industryClassificationBAI = industryClassificationBAI;
	}

	@Test
	public void loadContactList()
	{

		// Mock Organization Response
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());
		for (int i = 0; i < 5; i++)
		{
			Liaison contact = new Liaison();
			contact.setId(i);
			response.getLiaisonList().add(contact);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getContactBAI().fetchLiaisonByRequest(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(response);
		try
		{
			setData("");

			performTestGet(FETCH_LIST).andExpect(view().name(containsString(VIEW_CONTACT_MAIN))).andExpect(
					(model().size(0)));

			// Check for success, model
			performTestGet(FETCH_LIST + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_CONTACT_MAIN)))
			.andExpect(model().size(1));

			Mockito.verify(
					getContactBAI().fetchLiaisonByRequest(
							Matchers.any(PagedInquiryRequest.class)));

			Mockito.reset(
					getContactBAI().fetchLiaisonByRequest(
							Matchers.any(PagedInquiryRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void loadUpdate()
	{
		// Mock Contact Response
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());
		Liaison contact = new Liaison();
		contact.setId(1);
		response.getLiaisonList().add(contact);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getContactBAI().fetchLiaisonById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(EDIT + CONTACT_ID)
			.andExpect(view().name(containsString(VIEW_CONTACT_DIALOG_ADD))).andExpect(
					(model().size(4))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(EDIT + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_CONTACT_DIALOG_ADD)))
			.andExpect(model().size(4));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void loadContactAdd()
	{
		// Mock Contact Response
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());
		Liaison contact = new Liaison();

		contact.setId(1);
		response.getLiaisonList().add(contact);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getContactBAI().fetchLiaisonById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_ADD + CONTACT_ID)
			.andExpect(view().name(containsString(VIEW_CONTACT_ADD))).andExpect(
					(model().size(4))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(FETCH_ADD + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_CONTACT_ADD)))
			.andExpect(model().size(4));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

}
