package com.prosperitasglobal.sendsolv.member.controller.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

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
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;

/**
 * The Class MemberViewControllerTest.
 */
public class MemberViewControllerTest extends AbstractTestBase
{
	/** The URL mapping constants. */
	private static final String FETCH_LIST = "/member";

	/** The Constant FETCH_ADD. */
	private static final String FETCH_ADD = "/member/add";

	private static final String FETCH_EDIT = "/member/edit";

	/** The Constant EDIT_VIEW. */
	private static final String EDIT_VIEW = "/member/editView";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW = "/member/view";

	private static final String DIALOG_CREATE = "/member/dialogCreate";

	/** The Constant FETCH_VIEW_INFO. */
	private static final String FETCH_VIEW_INFO = "/member/view/info";

	private static final String TRANSFER_ID = "?transferId=10";

	/** The Constant ADD_TRANSFER_DIALOG. */
	private static final String ADD_TRANSFER_DIALOG = "/member/addTransferDialog";

	/** The Constant ADD_TRANSFER. */
	private static final String ADD_TRANSFER = "/member/addTransfer";

	/** The Constant INITIAL_LOAD. */
	private static final String INITIAL_LOAD = "?initialLoad=false&transferId=10";

	/** The view mapping constants . */
	private static final String VIEW_MEMBER_MAIN = "/member/member_main";

	/** The Constant VIEW_MEMBER_ADD. */
	private static final String VIEW_MEMBER_ADD = "/member/member_create";

	private static final String VIEW_MEMBER_FORM = "/member/member_form";

	/** The Constant VIEW_PERSON_VIEW. */
	private static final String VIEW_PERSON_VIEW = "/person/person_view";

	/** The Constant VIEW_MEMBER_DIALOG_ADD. */
	private static final String VIEW_MEMBER_DIALOG_ADD = "/member/member_dialog_create";

	private static final String TRANSFER_TRANSFER_SETTING_FORM = "transfer/transferSetting_form";

	/** The Constant VIEW_TRANSFER_ADD. */
	private static final String VIEW_TRANSFER_ADD = "/transfer/transfer_create";

	/** The Response constants. */
	public static final String COUNTRIES = "countries";

	/** The Constant NUMBER_OF_EMPLOYEES. */
	public static final String NUMBER_OF_EMPLOYEES = "number_of_employees";

	/** The Constant NUMBER_OF_MIGRANT_WORKERS. */
	public static final String NUMBER_OF_MIGRANT_WORKERS = "number_of_migrant_workers";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant MEMBER_ID. */
	private static final String MEMBER_ID = "?memberId=2974";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MemberAPIControllerTest.class);

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String FETCH_MEMBER_BY_RECIPIENT = "/member/fetchMemberByRecipient";

	private static final String RECIPIENT_ID = "?recipientId=2974";

	private static final String RESPONSE = "response";

	private static final String VIEW_MEMBER_BY_RECIPIENT = "/member/memberByRecipient_main";

	/** The member bai. */
	private IMemberBAI memberBAI;

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
	 * Load member list.
	 */
	@Test
	public void loadMemberList()
	{

		// Mock Organization Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		for (int i = 0; i < 5; i++)
		{
			Member member = new Member();
			member.setId(i);
			response.getMemberList().add(member);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMemberBAI().fetchMemberByRequest(
						Matchers.any(MemberInquiryRequest.class)))
				.thenReturn(response);
		try
		{
			setData("");

			performTestGet(FETCH_LIST).andExpect(view().name(containsString(VIEW_MEMBER_MAIN))).andExpect(
					(model().size(1)));

			Mockito.calls(1);

			// Check for success, model
			performTestGet(FETCH_LIST + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_MEMBER_MAIN)))
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
	 * Load view update.
	 */
	@Test
	public void loadAdd()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_ADD + MEMBER_ID)
			.andExpect(view().name(containsString("/member/member_create"))).andExpect(
					(model().size(4))).andExpect(model().attributeExists("response"));

			Mockito.calls(1);

			// Check for success, model
			performTestGet(FETCH_ADD + INITIAL_LOAD).andExpect(view().name(containsString("/member/member_create")))
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
	public void loadViewInfo()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_VIEW_INFO + MEMBER_ID)
			.andExpect(view().name(containsString(VIEW_PERSON_VIEW))).andExpect(
					(model().size(1))).andExpect(model().attributeExists("response"));
			Mockito.calls(1);

			// Check for success, model
			performTestGet(FETCH_VIEW_INFO + MEMBER_ID).andExpect(
					view().name(containsString(VIEW_PERSON_VIEW)))
					.andExpect(model().size(1));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void loadAddTransfer()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(ADD_TRANSFER + MEMBER_ID)
			.andExpect(view().name(containsString(VIEW_TRANSFER_ADD))).andExpect(
					(model().size(3))).andExpect(model().attributeExists("response"));

			Mockito.calls(1);

			// Check for success, model
			performTestGet(ADD_TRANSFER + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_TRANSFER_ADD)))
			.andExpect(model().size(2));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void loadDialogAddTransfer()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(ADD_TRANSFER_DIALOG + TRANSFER_ID)
			.andExpect(view().name(containsString("/transfer/transferSetting_update_dialog"))).andExpect(
					(model().size(1))).andExpect(model().attributeExists("response"));
			Mockito.calls(1);

			// Check for success, model
			performTestGet(ADD_TRANSFER_DIALOG + INITIAL_LOAD).andExpect(
					view().name(containsString("/transfer/transferSetting_update_dialog")))
					.andExpect(model().size(1));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

	@Test
	public void loadTransfer()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();

		List<TransferSetting> listTransfer = new ArrayList<TransferSetting>();
		for (Integer i = 0; i < 5; i++)
		{
			TransferSetting transfer = new TransferSetting();
			transfer.setId(i);
			listTransfer.add(transfer);
		}
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(DIALOG_CREATE + TRANSFER_ID)
			.andExpect(view().name(containsString(TRANSFER_TRANSFER_SETTING_FORM)));

			Mockito.calls(1);

			// Check for success, model
			performTestGet(DIALOG_CREATE + INITIAL_LOAD).andExpect(
					view().name(containsString(TRANSFER_TRANSFER_SETTING_FORM)))
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
	public void loadViewUpdate()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(EDIT_VIEW + MEMBER_ID)
			.andExpect(view().name(containsString(VIEW_MEMBER_DIALOG_ADD))).andExpect(
					(model().size(4))).andExpect(model().attributeExists("response"));
			Mockito.calls(1);

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

	/**
	 * Load member add.
	 */
	@Test
	public void loadMemberAdd()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_EDIT + MEMBER_ID)
			.andExpect(view().name(containsString(VIEW_MEMBER_FORM)))
			.andExpect((model().size(4)))
			.andExpect(model().attributeExists("response"))
			.andExpect(model().attributeExists("countries"));

			Mockito.calls(1);

			// Check for success, model
			performTestGet(FETCH_EDIT + INITIAL_LOAD).andExpect(view().name(containsString(VIEW_MEMBER_FORM)))
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
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_VIEW + MEMBER_ID)
			.andExpect(view().name(containsString("member/member_view"))).andExpect(
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
	public void loadListMemberByRecipient()
	{
		// Mock Member Response
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		Member member = new Member();
		member.setId(1);
		response.getMemberList().add(member);

		// When BAI is invoked with any request, return Organization response
		Mockito.when(
				getMemberBAI().fetchMemberByRequest(
						Matchers.any(MemberInquiryRequest.class)))
						.thenReturn(response);

		try
		{

			setData("");
			performTestGet(FETCH_MEMBER_BY_RECIPIENT + RECIPIENT_ID)
			.andExpect(view().name(containsString(VIEW_MEMBER_BY_RECIPIENT))).andExpect(
					(model().size(1))).andExpect(model().attributeExists(RESPONSE));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
