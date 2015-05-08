package com.prosperitasglobal.sendsolv.contact.controller.unittest;

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
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.LiaisonTypeEnum;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;

public class ContactAPIControllerTest extends AbstractTestBase
{
	private static final String API_CONTACT_DELETE = "/api/contact/delete";

	private static final String API_CONTACT_EDIT = "/api/contact/update";

	private static final String API_CONTACT_FETCHALL = "/api/contact/fetchall";

	private static final String API_CONTACT_FETCH = "/api/contact/fetch";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String API_CONTACT_ADD = "/api/contact/insert";

	private ILiaisonBAI liaisonBAI;
	private static final Logger LOG = LoggerFactory.getLogger(ContactAPIControllerTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public ILiaisonBAI getLiaisonBAI()
	{
		return liaisonBAI;
	}

	@Resource
	public void setLiaisonBAI(ILiaisonBAI liaisonBAI)
	{
		this.liaisonBAI = liaisonBAI;

		Mockito.calls(1);
	}

	@Test
	public void fetchAllContact()
	{

		// Mock Response 1
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());
		for (int i = 0; i < 5; i++)
		{
			Liaison liaison = new Liaison();
			liaison.setLiaisonType(LiaisonTypeEnum.HUMAN_RESOURCES);
			liaison.setLocationId(100 + i);
			liaison.setLastName("test " + i);
			liaison.setId(i);

			response.getLiaisonList().add(liaison);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLiaisonBAI().fetchLiaisonByRequest(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest(API_CONTACT_FETCHALL).andExpect(
					jsonPath("$.liaisonList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new LiaisonResponse();
			response.setOperationSuccess(true);
			response.setLiaisonList(new ArrayList<Liaison>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLiaisonBAI().fetchLiaisonByRequest(
							Matchers.any(PagedInquiryRequest.class)))
					.thenReturn(response);

			performTest(API_CONTACT_FETCHALL).andExpect(
					jsonPath("$.liaisonList", hasSize(0))).andExpect(
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
	public void fetchContact()
	{

		// Mock Response 1
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());

		Liaison liaison = new Liaison();
		liaison.setLiaisonType(LiaisonTypeEnum.HUMAN_RESOURCES);
		liaison.setLocationId(100);
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getLiaisonList().add(liaison);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLiaisonBAI().fetchLiaisonById(
						Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"id\":25}");

			performTest(API_CONTACT_FETCH).andExpect(
					jsonPath("$.liaisonList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new LiaisonResponse();
			response.setOperationSuccess(true);
			response.setLiaisonList(new ArrayList<Liaison>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLiaisonBAI().fetchLiaisonById(
							Matchers.any(FetchByIdRequest.class)))
					.thenReturn(response);

			performTest(API_CONTACT_FETCH).andExpect(
					jsonPath("$.liaisonList", hasSize(0))).andExpect(
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
	public void InsertLiaison()
	{

		// Mock Response 1
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());

		Liaison liaison = new Liaison();
		liaison.setLiaisonType(LiaisonTypeEnum.HUMAN_RESOURCES);
		liaison.setLocationId(100);
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getLiaisonList().add(liaison);

		Mockito.when(
				getLiaisonBAI().insertLiaison(
						Matchers.any(LiaisonMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"liaison\":{\"liaisonTypeValue\":1,\"locationId\":11846,\"prefix\":{\"id\":1520},\"firstName\":\"FirstName\",\"middleName\":\"contactMiddlename\",\"lastName\":\"contactLastname\",\"genderValue\":1,\"personTypeValue\":1,\"id\":2209,\"nameList\":[{\"otherName\":\"contactMaidenname\",\"id\":3592,\"modelAction\":\"UPDATE\"}],\"dateOfBirth\":1412910000000,\"suffix\":{\"id\":1525},\"pepStatusValue\":2,\"title\":\"Contact Title\",\"modelAction\":\"UPDATE\",\"noteList\":[{\"noteText\":\"sadasdasdsa\",\"modelAction\":\"UPDATE\"}],\"risk\":{\"riskLevelValue\":3,\"riskLevelNote\":\"Risk Note Text\",\"modelAction\":\"UPDATE\",\"version\":10},\"documentList\":[{\"value\":\"555555555\",\"modelAction\":\"UPDATE\",\"parentKey\":11846,\"parentKeyValue\":1,\"documentType\":{\"id\":11}}],\"contactList\":[{\"modelAction\":\"UPDATE\",\"addressLine1\":\"R JoÃ£o Pinheiro 540\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"UBERABA\",\"version\":1,\"stateProvinceRegion\":{\"id\":2},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-2403\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":\"28439\"},{\"contactTypeValue\":\"5\",\"id\":\"28440\",\"type\":\"phone\",\"modelAction\":\"UPDATE\",\"number\":91782776,\"extension\":10,\"version\":1,\"country\":{\"code\":\"USA\"},\"priorityValue\":1},{\"contactTypeValue\":\"3\",\"id\":28441,\"type\":\"email\",\"modelAction\":\"UPDATE\",\"emailAddress\":\"admin@qat.com\",\"version\":1,\"priorityValue\":1}],\"version\":1}}");

			performTest(API_CONTACT_ADD).andExpect(
					jsonPath("$.liaisonList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new LiaisonResponse();
			response.setOperationSuccess(false);
			response.setLiaisonList(new ArrayList<Liaison>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLiaisonBAI().insertLiaison(
							Matchers.any(LiaisonMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_CONTACT_ADD).andExpect(
					jsonPath("$.liaisonList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

			Mockito.when(
					getLiaisonBAI().insertLiaison(
							Matchers.any(LiaisonMaintenanceRequest.class))).thenThrow(new RuntimeException());
			performTest(API_CONTACT_ADD);

			Mockito.verify(getLiaisonBAI().insertLiaison(
					Matchers.any(LiaisonMaintenanceRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void UpdateLiaison()
	{

		// Mock Response 1
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());

		Liaison liaison = new Liaison();
		liaison.setLiaisonType(LiaisonTypeEnum.HUMAN_RESOURCES);
		liaison.setLocationId(100);
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getLiaisonList().add(liaison);
		response.setOperationSuccess(true);

		Mockito.when(
				getLiaisonBAI().updateLiaison(
						Matchers.any(LiaisonMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"liaison\":{\"liaisonTypeValue\":1,\"locationId\":11846,\"prefix\":{\"id\":1520},\"firstName\":\"FirstName\",\"middleName\":\"contactMiddlename\",\"lastName\":\"contactLastname\",\"genderValue\":1,\"personTypeValue\":1,\"id\":2209,\"nameList\":[{\"otherName\":\"contactMaidenname\",\"id\":3592,\"modelAction\":\"UPDATE\"}],\"dateOfBirth\":1412910000000,\"suffix\":{\"id\":1525},\"pepStatusValue\":2,\"title\":\"Contact Title\",\"modelAction\":\"UPDATE\",\"noteList\":[{\"noteText\":\"sadasdasdsa\",\"modelAction\":\"UPDATE\"}],\"risk\":{\"riskLevelValue\":3,\"riskLevelNote\":\"Risk Note Text\",\"modelAction\":\"UPDATE\",\"version\":10},\"documentList\":[{\"value\":\"555555555\",\"modelAction\":\"UPDATE\",\"parentKey\":11846,\"parentKeyValue\":1,\"documentType\":{\"id\":11}}],\"contactList\":[{\"modelAction\":\"UPDATE\",\"addressLine1\":\"R JoÃ£o Pinheiro 540\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"UBERABA\",\"version\":1,\"stateProvinceRegion\":{\"id\":2},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-2403\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":\"28439\"},{\"contactTypeValue\":\"5\",\"id\":\"28440\",\"type\":\"phone\",\"modelAction\":\"UPDATE\",\"number\":91782776,\"extension\":10,\"version\":1,\"country\":{\"code\":\"USA\"},\"priorityValue\":1},{\"contactTypeValue\":\"3\",\"id\":28441,\"type\":\"email\",\"modelAction\":\"UPDATE\",\"emailAddress\":\"admin@qat.com\",\"version\":1,\"priorityValue\":1}],\"version\":1}}");
			performTest(API_CONTACT_EDIT).andExpect(
					jsonPath("$.liaisonList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new LiaisonResponse();
			response.setOperationSuccess(false);
			response.setLiaisonList(new ArrayList<Liaison>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLiaisonBAI().updateLiaison(
							Matchers.any(LiaisonMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_CONTACT_EDIT).andExpect(
					jsonPath("$.liaisonList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.when(
					getLiaisonBAI().updateLiaison(
							Matchers.any(LiaisonMaintenanceRequest.class))).thenThrow(new RuntimeException());

			performTest(API_CONTACT_EDIT);

			Mockito.verify(getLiaisonBAI().updateLiaison(
					Matchers.any(LiaisonMaintenanceRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void DeleteLiaison()
	{

		// Mock Response 1
		setData("{\"liaison\":{\"id\":2140,\"modelAction\":\"DELETE\"}}");
		LiaisonResponse response = new LiaisonResponse();
		response.setLiaisonList(new ArrayList<Liaison>());
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLiaisonBAI().deleteLiaison(
						Matchers.any(LiaisonMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			performTest(API_CONTACT_DELETE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Error situation ==================================

			response = new LiaisonResponse();
			response.setOperationSuccess(false);
			response.setLiaisonList(new ArrayList<Liaison>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLiaisonBAI().deleteLiaison(
							Matchers.any(LiaisonMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_CONTACT_DELETE).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

			Mockito.when(
					getLiaisonBAI().deleteLiaison(
							Matchers.any(LiaisonMaintenanceRequest.class))).thenThrow(new RuntimeException());

			performTest(API_CONTACT_DELETE);

			Mockito.verify(getLiaisonBAI().deleteLiaison(
					Matchers.any(LiaisonMaintenanceRequest.class)));
			Mockito.reset(getLiaisonBAI().deleteLiaison(
					Matchers.any(LiaisonMaintenanceRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
