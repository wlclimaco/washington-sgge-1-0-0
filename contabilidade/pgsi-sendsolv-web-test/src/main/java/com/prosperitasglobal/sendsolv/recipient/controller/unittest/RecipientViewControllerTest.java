package com.prosperitasglobal.sendsolv.recipient.controller.unittest;

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
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;

/**
 * The Class RecipientViewControllerTest.
 */
public class RecipientViewControllerTest extends AbstractTestBase
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "/recipient";

	/** The Constant FETCH_ADD. */
	private static final String FETCH_ADD = "/recipient/edit";

	/** The Constant EDIT_VIEW. */
	private static final String EDIT_VIEW = "/recipient/editView";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW = "/recipient/view";

	/** The Constant VIEW_MEMBER_ADD. */
	private static final String VIEW_MEMBER_ADD = "/recipient/recipient_create";

	/** The Constant INITIAL_LOAD. */
	private static final String INITIAL_LOAD = "?initialLoad=false";

	private static final String RECIPIENT_ID = "?recipientId=10";

	/** The Constant FETCH_VIEW_INFO. */
	private static final String FETCH_VIEW_INFO = "/recipient/view/info";

	/** The view mapping constants . */
	private static final String VIEW_MEMBER_MAIN = "/recipient/recipient_main";

	/** The Constant FETCH_RECIPIENT_BY_MEMBER. */
	private static final String FETCH_RECIPIENT_BY_MEMBER = "/recipient/fetchRecipientByMember";

	private static final String VIEW_MEMBER_FORM = "/recipient/recipient_form";

	/** The Constant VIEW_MEMBER_DIALOG_ADD. */
	private static final String VIEW_MEMBER_DIALOG_ADD = "/recipient/recipient_dialog_create";

	/** The Constant VIEW_RECIPIENT_BY_MEMBER. */
	private static final String VIEW_RECIPIENT_BY_MEMBER = "recipient/recipientByMember_main";

	/** The Response constants. */
	public static final String COUNTRIES = "countries";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant VIEW_RECIPIENT_INFO. */
	private static final String VIEW_RECIPIENT_INFO = "/recipient/recipient_info";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RecipientAPIControllerTest.class);

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	/** The recipient bai. */
	private IRecipientBAI recipientBAI;

	/** The country bai. */
	private ICountryBAI countryBAI;

	/** The range bai. */
	private IRangeBAI rangeBAI;

	/** The industry classification bai. */
	private IIndustryClassificationBAI industryClassificationBAI;

	/** The exception. */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Gets the recipient bai.
	 *
	 * @return the recipient bai
	 */
	public IRecipientBAI getRecipientBAI()
	{
		return recipientBAI;
	}

	/**
	 * Sets the recipient bai.
	 *
	 * @param recipientBAI the recipient bai
	 */
	@Resource
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
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
	 * Load recipient list.
	 */
	@Test
	public void loadRecipientList()
	{

		// Mock Organization Response
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		for (int i = 0; i < 5; i++)
		{
			Recipient recipient = new Recipient();
			recipient.setId(i);
			response.getRecipientList().add(recipient);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getRecipientBAI().fetchRecipientByRequest(
						Matchers.any(RecipientInquiryRequest.class)))
				.thenReturn(response);
		try
		{
			setData("");

			performTestGet(FETCH_LIST).andExpect(view().name(containsString(VIEW_MEMBER_MAIN))).andExpect(
					(model().size(1))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(FETCH_LIST + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_MEMBER_MAIN)))
			.andExpect(model().size(1));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void loadListRecipientByMember()
	{

		// Mock Organization Response
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		for (int i = 0; i < 5; i++)
		{
			Recipient recipient = new Recipient();
			recipient.setId(i);
			response.getRecipientList().add(recipient);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getRecipientBAI().fetchRecipientByRequest(
						Matchers.any(RecipientInquiryRequest.class)))
				.thenReturn(response);
		try
		{
			setData("");

			performTestGet(FETCH_RECIPIENT_BY_MEMBER).andExpect(view().name(containsString(VIEW_RECIPIENT_BY_MEMBER)))
					.andExpect(
							(model().size(1))).andExpect(model().attributeExists("response"));

			performTestGet(FETCH_RECIPIENT_BY_MEMBER + "?memberId=10")
					.andExpect(view().name(containsString(VIEW_RECIPIENT_BY_MEMBER)))
					.andExpect(
							(model().size(1))).andExpect(model().attributeExists("response"));

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
	 */
	@Test
	public void loadViewUpdate()
	{
		// Mock Recipient Response
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		Recipient recipient = new Recipient();
		recipient.setId(1);
		response.getRecipientList().add(recipient);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getRecipientBAI().fetchRecipientById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);
		try
		{

			setData("");
			performTestGet(EDIT_VIEW + RECIPIENT_ID)
			.andExpect(view().name(containsString(VIEW_MEMBER_DIALOG_ADD))).andExpect(
					(model().size(4))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(EDIT_VIEW + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_MEMBER_DIALOG_ADD)))
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
	public void loadViewAdd()
	{
		// Mock Recipient Response
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		Recipient recipient = new Recipient();
		recipient.setId(1);
		response.getRecipientList().add(recipient);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getRecipientBAI().insertRecipient(
						Matchers.any(RecipientMaintenanceRequest.class)))
						.thenReturn(response);
		try
		{

			setData("");
			performTestGet(FETCH_ADD + RECIPIENT_ID)
			.andExpect(view().name(containsString("/recipient/recipient_form"))).andExpect(
					(model().size(4))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(FETCH_ADD + INITIAL_LOAD).andExpect(
					view().name(containsString("/recipient/recipient_form")))
					.andExpect(model().size(4));

			Mockito.when(
					getRecipientBAI().insertRecipient(
							Matchers.any(RecipientMaintenanceRequest.class))).thenThrow(new RuntimeException());

			performTestGet(FETCH_ADD);

			Mockito.verify(getRecipientBAI().insertRecipient(
					Matchers.any(RecipientMaintenanceRequest.class)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	/**
	 * Load recipient add.
	 */
	@Test
	public void loadRecipientAdd()
	{
		// Mock Recipient Response
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		Recipient recipient = new Recipient();
		recipient.setId(1);
		response.getRecipientList().add(recipient);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getRecipientBAI().fetchRecipientById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_ADD + RECIPIENT_ID)
			.andExpect(view().name(containsString(VIEW_MEMBER_FORM))).andExpect(
					(model().size(4))).andExpect(model().attributeExists("response"));

			// Check for success, model
			performTestGet(FETCH_ADD + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_MEMBER_FORM)))
			.andExpect(model().size(4));

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
		// Mock Recipient Response
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		Recipient recipient = new Recipient();
		recipient.setId(1);
		response.getRecipientList().add(recipient);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getRecipientBAI().fetchRecipientById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_VIEW + RECIPIENT_ID)
			.andExpect(view().name(containsString("recipient/recipient_view"))).andExpect(
					(model().size(1))).andExpect(model().attributeExists("response"));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void loadTab()
	{
		// Mock Recipient Response
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		Recipient recipient = new Recipient();
		recipient.setId(1);
		response.getRecipientList().add(recipient);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getRecipientBAI().fetchRecipientById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_VIEW_INFO + RECIPIENT_ID)
			.andExpect(view().name(containsString(VIEW_RECIPIENT_INFO))).andExpect(
					(model().size(1))).andExpect(model().attributeExists("response"));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

}
