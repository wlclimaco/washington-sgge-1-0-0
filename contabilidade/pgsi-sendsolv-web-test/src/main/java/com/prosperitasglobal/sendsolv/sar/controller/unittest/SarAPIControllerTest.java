package com.prosperitasglobal.sendsolv.sar.controller.unittest;

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

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;
import com.prosperitasglobal.sendsolv.model.response.SarResponse;
import com.qat.framework.model.response.MaintenanceResponse;

public class SarAPIControllerTest extends AbstractTestBase
{

	private static final String API_SAR_EDIT = "/api/sar/update";

	private static final String API_SAR_FETCH = "/api/sar/fetch";

	private static final String API_SAR_ADD = "/api/sar/insert";

	private static final String SEARCH = "/api/sar/search";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private IMemberBAI memberBAI;

	private ISuspiciousActivityBAI suspiciousActivityBAI;

	/** The Organization BAI. */
	private IOrganizationBAI organizationBAI;

	private IRecipientBAI recipientBAI;

	private ILiaisonBAI liaisonBAI;

	/** The Organization BAI. */
	private ILocationBAI locationBAI;

	private static final Logger LOG = LoggerFactory.getLogger(SarAPIControllerTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * @return the suspiciousActivityBAI
	 */
	public ISuspiciousActivityBAI getSuspiciousActivityBAI()
	{
		return suspiciousActivityBAI;
	}

	/**
	 * @param suspiciousActivityBAI the suspiciousActivityBAI to set
	 */
	@Resource
	public void setSuspiciousActivityBAI(ISuspiciousActivityBAI suspiciousActivityBAI)
	{
		this.suspiciousActivityBAI = suspiciousActivityBAI;
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
	public void fetchAllSuspiciousActivity()
	{

		// Mock Response 1
		SarResponse response = new SarResponse();
		response.setSuspiciousActivityList(new ArrayList<SuspiciousActivity>());
		for (int i = 0; i < 5; i++)
		{
			SuspiciousActivity sar = new SuspiciousActivity();
			sar.setBusinessKey("SAR- " + i);
			sar.setId(i);

			response.getSuspiciousActivityList().add(sar);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getSuspiciousActivityBAI().fetchSuspiciousActivityByRequest(
						Matchers.any(SarInquiryRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest(API_SAR_FETCH).andExpect(
					jsonPath("$.suspiciousActivityList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new SarResponse();
			response.setOperationSuccess(true);
			response.setSuspiciousActivityList(new ArrayList<SuspiciousActivity>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getSuspiciousActivityBAI().fetchSuspiciousActivityByRequest(
							Matchers.any(SarInquiryRequest.class)))
							.thenReturn(response);

			performTest(API_SAR_FETCH).andExpect(
					jsonPath("$.suspiciousActivityList", hasSize(0))).andExpect(
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
	public void InsertSuspiciousActivity()
	{

		// Mock Response 1
		MaintenanceResponse response = new MaintenanceResponse();
		response.setOperationSuccess(Boolean.TRUE);

		Mockito.when(
				getSuspiciousActivityBAI().insertSuspiciousActivity(
						Matchers.any(SarMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"userContext\":{\"userId\":\"washington\",\"id\":null,\"userRole\":null,\"localeString\":null,\"tenant\":null,\"authorities\":null},\"suspiciousActivity\":{\"type\":\"MEMBER\",\"summary\":\"Test jUnit\",\"detail\":\"Activity Detail\",\"activityStartDateTimeUTC\":1425178800000,\"activityStopDateTimeUTC\":1426042800000,\"status\":\"PGSI_REVIEW\",\"statusDateTime\":1426103327349,\"amountAssociated\":\"1.99\",\"personList\":[{\"id\":250}],\"businessList\":[{\"id\":8,\"businessType\":\"LOCATION\"},{\"id\":20,\"businessType\":\"LOCATION\"}],\"modelAction\":\"INSERT\"}}");
			performTest(API_SAR_ADD)
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new MaintenanceResponse();
			response.setOperationSuccess(Boolean.FALSE);

			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getSuspiciousActivityBAI().insertSuspiciousActivity(
							Matchers.any(SarMaintenanceRequest.class))).thenReturn(response);

			performTest(API_SAR_ADD).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void UpdateSuspiciousActivity()
	{

		// Mock Response 1
		MaintenanceResponse response = new MaintenanceResponse();
		response.setOperationSuccess(Boolean.TRUE);

		Mockito.when(
				getSuspiciousActivityBAI().insertSuspiciousActivity(
						Matchers.any(SarMaintenanceRequest.class))).thenReturn(response);
		try
		{

			setData("{\"userContext\":{\"userId\":\"washington\",\"id\":null,\"userRole\":null,\"localeString\":null,\"tenant\":null,\"authorities\":null},\"suspiciousActivity\":{\"type\":\"MEMBER\",\"summary\":\"Test jUnit\",\"detail\":\"Activity Detail\",\"activityStartDateTimeUTC\":1425178800000,\"activityStopDateTimeUTC\":1426042800000,\"status\":\"PGSI_REVIEW\",\"statusDateTime\":1426103327349,\"amountAssociated\":\"1.99\",\"personList\":[{\"id\":250}],\"businessList\":[{\"id\":8,\"businessType\":\"LOCATION\"},{\"id\":20,\"businessType\":\"LOCATION\"}],\"modelAction\":\"INSERT\"}}");
			performTest(API_SAR_EDIT).andExpect(
					jsonPath("$.memberList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new MaintenanceResponse();
			response.setOperationSuccess(Boolean.FALSE);

			Mockito.when(
					getSuspiciousActivityBAI().insertSuspiciousActivity(
							Matchers.any(SarMaintenanceRequest.class))).thenReturn(response);

			performTest(API_SAR_EDIT).andExpect(
					jsonPath("$.memberList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(false)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void search()
	{

		// Mock Response 1
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		for (int i = 0; i < 5; i++)
		{
			Location location = new Location();
			location.setName("SAR- " + i);
			location.setId(i);

			response.getLocationList().add(location);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{
			// ================= Location ============================
			setData("{\"stringId\":\"LAA00001\"}");

			performTest(SEARCH).andExpect(
					jsonPath("$.locationList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);
			// ================= Organization ============================

			// Mock Response 1
			OrganizationResponse organizationResponse = new OrganizationResponse();
			organizationResponse.setOrganizationList(new ArrayList<Organization>());
			for (int i = 0; i < 5; i++)
			{
				Organization organization = new Organization();
				organization.setName("SAR- " + i);
				organization.setId(i);

				organizationResponse.getOrganizationList().add(organization);
			}

			// When BAI is invoked with any request, return response 1
			Mockito.when(
					getOrganizationBAI().fetchOrganizationById(
							Matchers.any(FetchByIdRequest.class)))
							.thenReturn(organizationResponse);

			setData("{\"stringId\":\"OAA00001\"}");

			performTest(SEARCH).andExpect(
					jsonPath("$.organizationList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

			// ================= Member ============================

			// Mock Response 1
			MemberResponse responseMember = new MemberResponse();
			responseMember.setMemberList(new ArrayList<Member>());
			for (int i = 0; i < 5; i++)
			{
				Member member = new Member();
				member.setLastName("SAR- " + i);
				member.setId(i);

				responseMember.getMemberList().add(member);
			}

			// When BAI is invoked with any request, return response 1
			Mockito.when(
					getMemberBAI().fetchMemberById(
							Matchers.any(FetchByIdRequest.class)))
							.thenReturn(responseMember);

			setData("{\"stringId\":\"MAA00001\"}");

			performTest(SEARCH).andExpect(
					jsonPath("$.memberList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

			// ================= Liaison ============================

			// Mock Response 1
			LiaisonResponse responseLiaison = new LiaisonResponse();
			responseLiaison.setLiaisonList(new ArrayList<Liaison>());
			for (int i = 0; i < 5; i++)
			{
				Liaison location = new Liaison();
				location.setLastName("SAR- " + i);
				location.setId(i);

				responseLiaison.getLiaisonList().add(location);
			}

			// When BAI is invoked with any request, return response 1
			Mockito.when(
					getLiaisonBAI().fetchLiaisonById(
							Matchers.any(FetchByIdRequest.class)))
							.thenReturn(responseLiaison);

			setData("{\"stringId\":\"CAA00001\"}");

			performTest(SEARCH).andExpect(
					jsonPath("$.liaisonList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

			// ================= Recipient ============================

			// Mock Response 1
			RecipientResponse responseRecipient = new RecipientResponse();
			responseRecipient.setRecipientList(new ArrayList<Recipient>());
			for (int i = 0; i < 5; i++)
			{
				Recipient location = new Recipient();
				location.setLastName("SAR- " + i);
				location.setId(i);

				responseRecipient.getRecipientList().add(location);
			}

			// When BAI is invoked with any request, return response 1
			Mockito.when(
					getRecipientBAI().fetchRecipientById(
							Matchers.any(FetchByIdRequest.class)))
							.thenReturn(responseRecipient);

			setData("{\"stringId\":\"RAA00001\"}");

			performTest(SEARCH).andExpect(
					jsonPath("$.recipientList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
