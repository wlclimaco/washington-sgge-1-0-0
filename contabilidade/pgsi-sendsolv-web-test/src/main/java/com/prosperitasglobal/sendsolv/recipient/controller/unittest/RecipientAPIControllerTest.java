package com.prosperitasglobal.sendsolv.recipient.controller.unittest;

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
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;

public class RecipientAPIControllerTest extends AbstractTestBase
{
	private static final String API_MEMBER_DELETE = "/api/recipient/delete";

	private static final String API_MEMBER_EDIT = "/api/recipient/update";

	private static final String API_MEMBER_FETCHALL = "/api/recipient/fetchall";

	private static final String API_MEMBER_FETCH = "/api/recipient/fetch";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String API_MEMBER_ADD = "/api/recipient/insert";
	private static final String API_APPLY_STATUS = "/api/recipient/applyStatus";

	private IRecipientBAI recipientBAI;

	private static final Logger LOG = LoggerFactory.getLogger(RecipientAPIControllerTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public IRecipientBAI getRecipientBAI()
	{
		return recipientBAI;
	}

	@Resource
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
	}

	@Test
	public void fetchAllRecipient()
	{

		// Mock Response 1
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		for (int i = 0; i < 5; i++)
		{
			Recipient liaison = new Recipient();
			liaison.setLastName("test " + i);
			liaison.setId(i);

			response.getRecipientList().add(liaison);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getRecipientBAI().fetchRecipientByRequest(
						Matchers.any(RecipientInquiryRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest(API_MEMBER_FETCHALL).andExpect(
					jsonPath("$.recipientList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new RecipientResponse();
			response.setOperationSuccess(true);
			response.setRecipientList(new ArrayList<Recipient>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getRecipientBAI().fetchRecipientByRequest(
							Matchers.any(RecipientInquiryRequest.class)))
							.thenReturn(response);

			performTest(API_MEMBER_FETCHALL).andExpect(
					jsonPath("$.recipientList", hasSize(0))).andExpect(
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
	public void fetchRecipient()
	{

		// Mock Response 1
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());

		Recipient liaison = new Recipient();
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getRecipientList().add(liaison);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getRecipientBAI().fetchRecipientById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"id\":25}");

			performTest(API_MEMBER_FETCH).andExpect(
					jsonPath("$.recipientList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new RecipientResponse();
			response.setOperationSuccess(true);
			response.setRecipientList(new ArrayList<Recipient>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getRecipientBAI().fetchRecipientById(
							Matchers.any(FetchByIdRequest.class)))
							.thenReturn(response);

			performTest(API_MEMBER_FETCH).andExpect(
					jsonPath("$.recipientList", hasSize(0))).andExpect(
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
	public void InsertRecipient()
	{

		// Mock Response 1
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());

		Recipient liaison = new Recipient();
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getRecipientList().add(liaison);

		Mockito.when(
				getRecipientBAI().insertRecipient(
						Matchers.any(RecipientMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"recipient\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":1,\"personTypeValue\":3,\"id\":0,\"nameList\":[{\"id\":\"0\",\"otherName\":\"teste@outmail.com\",\"modelAction\":\"INSERT\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":null},\"prefix\":{\"id\":1524},\"modelAction\":\"INSERT\",\"noteList\":[{\"noteText\":\"note text\",\"modelAction\":\"INSERT\"}],\"risk\":{\"riskLevelValue\":1,\"riskLevelNote\":\"risknote\",\"modelAction\":\"INSERT\",\"version\":10},\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Av Joao Pinheiro 540\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"Uberaba\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"8\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":1111111,\"extension\":10,\"country\":{\"code\":\"MEX\"},\"priorityValue\":1},{\"contactTypeValue\":\"1\",\"id\":null,\"type\":\"email\",\"modelAction\":\"INSERT\",\"emailAddress\":\"teste@outmail.com\",\"priorityValue\":1}],\"version\":null,\"documentList\":[{\"value\":\"111111111\",\"modelAction\":\"INSERT\",\"parentKey\":0,\"parentKeyValue\":3,\"documentType\":{\"id\":11}}]}}");
			performTest(API_MEMBER_ADD).andExpect(
					jsonPath("$.recipientList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new RecipientResponse();
			response.setOperationSuccess(false);
			response.setRecipientList(new ArrayList<Recipient>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getRecipientBAI().insertRecipient(
							Matchers.any(RecipientMaintenanceRequest.class)))
							.thenReturn(response);

			performTest(API_MEMBER_ADD).andExpect(
					jsonPath("$.recipientList", hasSize(0))).andExpect(
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
	public void UpdateRecipient()
	{

		// Mock Response 1
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());

		Recipient liaison = new Recipient();
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getRecipientList().add(liaison);
		response.setOperationSuccess(true);

		Mockito.when(
				getRecipientBAI().updateRecipient(
						Matchers.any(RecipientMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"recipient\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":1,\"personTypeValue\":3,\"id\":3247,\"nameList\":[{\"id\":\"4629\",\"otherName\":\"teste@outmail.com\",\"modelAction\":\"UPDATE\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":1525},\"prefix\":{\"id\":1524},\"modelAction\":\"UPDATE\",\"contactList\":[{\"modelAction\":\"UPDATE\",\"addressLine1\":\"Av Joao Pinheiro 540\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"Uberaba\",\"version\":0,\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":\"29509\"},{\"contactTypeValue\":\"8\",\"id\":\"29510\",\"type\":\"phone\",\"modelAction\":\"UPDATE\",\"number\":1111111,\"extension\":10,\"version\":0,\"country\":{\"code\":\"MEX\"},\"priorityValue\":1},{\"contactTypeValue\":\"1\",\"id\":29511,\"type\":\"email\",\"modelAction\":\"UPDATE\",\"emailAddress\":\"ssadsasd@hotmail.com\",\"version\":0,\"priorityValue\":1}],\"version\":0,\"documentList\":[{\"value\":\"888888888\",\"modelAction\":\"UPDATE\",\"parentKey\":3247,\"parentKeyValue\":3,\"documentType\":{\"id\":11}}]}}");
			performTest(API_MEMBER_EDIT).andExpect(
					jsonPath("$.recipientList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new RecipientResponse();
			response.setOperationSuccess(false);
			response.setRecipientList(new ArrayList<Recipient>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getRecipientBAI().updateRecipient(
							Matchers.any(RecipientMaintenanceRequest.class)))
							.thenReturn(response);

			performTest(API_MEMBER_EDIT).andExpect(
					jsonPath("$.recipientList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(false)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void DeleteRecipient()
	{

		// Mock Response 1
		setData("{\"recipient\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":1,\"personTypeValue\":3,\"id\":0,\"nameList\":[{\"id\":\"0\",\"otherName\":\"teste@outmail.com\",\"modelAction\":\"INSERT\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":null},\"prefix\":{\"id\":1524},\"modelAction\":\"INSERT\",\"noteList\":[{\"noteText\":\"note text\",\"modelAction\":\"INSERT\"}],\"risk\":{\"riskLevelValue\":1,\"riskLevelNote\":\"risknote\",\"modelAction\":\"INSERT\",\"version\":10},\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Av Joao Pinheiro 540\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"Uberaba\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"8\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":1111111,\"extension\":10,\"country\":{\"code\":\"MEX\"},\"priorityValue\":1},{\"contactTypeValue\":\"1\",\"id\":null,\"type\":\"email\",\"modelAction\":\"INSERT\",\"emailAddress\":\"teste@outmail.com\",\"priorityValue\":1}],\"version\":null,\"documentList\":[{\"value\":\"111111111\",\"modelAction\":\"INSERT\",\"parentKey\":0,\"parentKeyValue\":3,\"documentType\":{\"id\":11}}]}}");
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getRecipientBAI().deleteRecipient(
						Matchers.any(RecipientMaintenanceRequest.class)))
						.thenReturn(response);

		try
		{

			performTest(API_MEMBER_DELETE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Error situation ==================================

			response = new RecipientResponse();
			response.setOperationSuccess(false);
			response.setRecipientList(new ArrayList<Recipient>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getRecipientBAI().deleteRecipient(
							Matchers.any(RecipientMaintenanceRequest.class)))
							.thenReturn(response);

			performTest(API_MEMBER_DELETE).andExpect(
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
	public void ApplyStatus()
	{
		// Mock Response 1
		setData("{\"recipient\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":1,\"personTypeValue\":3,\"id\":0,\"nameList\":[{\"id\":\"0\",\"otherName\":\"teste@outmail.com\",\"modelAction\":\"INSERT\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":null},\"prefix\":{\"id\":1524},\"modelAction\":\"INSERT\",\"noteList\":[{\"noteText\":\"note text\",\"modelAction\":\"INSERT\"}],\"risk\":{\"riskLevelValue\":1,\"riskLevelNote\":\"risknote\",\"modelAction\":\"INSERT\",\"version\":10},\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Av Joao Pinheiro 540\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"Uberaba\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"8\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":1111111,\"extension\":10,\"country\":{\"code\":\"MEX\"},\"priorityValue\":1},{\"contactTypeValue\":\"1\",\"id\":null,\"type\":\"email\",\"modelAction\":\"INSERT\",\"emailAddress\":\"teste@outmail.com\",\"priorityValue\":1}],\"version\":null,\"documentList\":[{\"value\":\"111111111\",\"modelAction\":\"INSERT\",\"parentKey\":0,\"parentKeyValue\":3,\"documentType\":{\"id\":11}}]}}");
		RecipientResponse response = new RecipientResponse();
		response.setRecipientList(new ArrayList<Recipient>());
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getRecipientBAI().applyStatus(
						Matchers.any(RecipientMaintenanceRequest.class)))
						.thenReturn(response);

		try
		{

			performTest(API_APPLY_STATUS).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Error situation ==================================

			response = new RecipientResponse();
			response.setOperationSuccess(false);
			response.setRecipientList(new ArrayList<Recipient>());
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}

}
