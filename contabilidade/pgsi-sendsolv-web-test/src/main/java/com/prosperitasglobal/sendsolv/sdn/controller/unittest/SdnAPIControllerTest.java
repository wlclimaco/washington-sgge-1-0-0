package com.prosperitasglobal.sendsolv.sdn.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.IMoneyTransferBAI;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.sdn.bai.ISdnCheckerBAI;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnHistoryResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnStatusHistoryResponse;

public class SdnAPIControllerTest extends AbstractTestBase
{

	private static final String API_SAR_EDIT = "/api/sdn/update";

	private static final String API_SAR_FETCH = "/api/sdn/fetch";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private IMemberBAI memberBAI;

	private ISdnCheckerBAI sdnCheckerBAI;

	private IMoneyTransferBAI moneyTransferBAI;

	/** The Organization BAI. */
	private ISdnCheckerBAI sdnBAI;

	/** The Organization BAI. */
	private IOrganizationBAI organizationBAI;

	private IRecipientBAI recipientBAI;

	private ILiaisonBAI liaisonBAI;

	/** The Organization BAI. */
	private ILocationBAI locationBAI;

	private static final Logger LOG = LoggerFactory.getLogger(SdnAPIControllerTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * @return the sdnCheckerBAI
	 */
	public ISdnCheckerBAI getSdnCheckerBAI()
	{
		return sdnCheckerBAI;
	}

	/**
	 * @param sdnCheckerBAI the sdnCheckerBAI to set
	 */
	@Resource
	public void setSdnCheckerBAI(ISdnCheckerBAI sdnCheckerBAI)
	{
		this.sdnCheckerBAI = sdnCheckerBAI;
	}

	/**
	 * @return the moneyTransferBAI
	 */
	public IMoneyTransferBAI getMoneyTransferBAI()
	{
		return moneyTransferBAI;
	}

	/**
	 * @param moneyTransferBAI the moneyTransferBAI to set
	 */
	@Resource
	public void setMoneyTransferBAI(IMoneyTransferBAI moneyTransferBAI)
	{
		this.moneyTransferBAI = moneyTransferBAI;
	}

	/**
	 * @return the sdnBAI
	 */
	public ISdnCheckerBAI getSdnBAI()
	{
		return sdnBAI;
	}

	/**
	 * @param sdnBAI the sdnBAI to set
	 */
	@Resource
	public void setSdnBAI(ISdnCheckerBAI sdnBAI)
	{
		this.sdnBAI = sdnBAI;
	}

	/**
	 * @return the organizationBAI
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * @param organizationBAI the organizationBAI to set
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	/**
	 * @return the recipientBAI
	 */
	public IRecipientBAI getRecipientBAI()
	{
		return recipientBAI;
	}

	/**
	 * @param recipientBAI the recipientBAI to set
	 */
	@Resource
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
	}

	/**
	 * @return the liaisonBAI
	 */
	public ILiaisonBAI getLiaisonBAI()
	{
		return liaisonBAI;
	}

	/**
	 * @param liaisonBAI the liaisonBAI to set
	 */
	@Resource
	public void setLiaisonBAI(ILiaisonBAI liaisonBAI)
	{
		this.liaisonBAI = liaisonBAI;
	}

	/**
	 * @return the locationBAI
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * @param locationBAI the locationBAI to set
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * @return the memberBAI
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * @param memberBAI the memberBAI to set
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	@Test
	public void fetchAll()
	{

		// Mock Response 1
		SdnHistoryResponse response = new SdnHistoryResponse();
		response.setSdnHistoryList(new ArrayList<SdnHistory>());
		for (int i = 0; i < 5; i++)
		{
			SdnHistory sar = new SdnHistory();
			sar.setName("SAR- " + i);
			sar.setNoteText("noteText");

			response.getSdnHistoryList().add(sar);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getSdnCheckerBAI().fetchSdnStatusHistoryByRequest(
						Matchers.any(SdnStatusHistoryInquiryRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest(API_SAR_FETCH).andExpect(
					jsonPath("$.sdnHistoryList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new SdnHistoryResponse();
			response.setOperationSuccess(true);
			response.setSdnHistoryList(new ArrayList<SdnHistory>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getSdnCheckerBAI().fetchSdnStatusHistoryByRequest(
							Matchers.any(SdnStatusHistoryInquiryRequest.class)))
							.thenReturn(response);

			performTest(API_SAR_FETCH).andExpect(
					jsonPath("$.sdnHistoryList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void Update()
	{

		// Mock Response 1
		SdnStatusHistoryResponse response = new SdnStatusHistoryResponse();
		response.setOperationSuccess(Boolean.TRUE);

		Mockito.when(
				getSdnCheckerBAI().updateSdnStatusHistory(
						Matchers.any(SdnStatusHistoryRequest.class))).thenReturn(response);
		try
		{

			setData("{\"userContext\":{\"userId\":\"washington\",\"id\":null,\"userRole\":null,\"localeString\":null,\"tenant\":null,\"authorities\":null},\"matchType\":\"INDIVIDUAL\",\"sdnStatusHistory\":{\"id\":165,\"parentKey\":346,\"modelAction\":\"UPDATE\",\"sdnStatusValue\":2,\"noteText\":\"Reason test Was\"}}");
			performTest(API_SAR_EDIT).andExpect(
					jsonPath("$.sdnStatusHistoryList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new SdnStatusHistoryResponse();
			response.setOperationSuccess(Boolean.FALSE);

			Mockito.when(
					getSdnCheckerBAI().updateSdnStatusHistory(
							Matchers.any(SdnStatusHistoryRequest.class))).thenReturn(response);

			performTest(API_SAR_EDIT).andExpect(
					jsonPath("$.sdnStatusHistoryList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
