package com.prosperitasglobal.sendsolv.member.controller.unittest;

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
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;

public class MemberAPIControllerTest extends AbstractTestBase
{
	private static final String API_MEMBER_DELETE = "/api/member/delete";

	private static final String API_MEMBER_EDIT = "/api/member/update";

	private static final String API_MEMBER_FETCHALL = "/api/member/fetchall";

	private static final String API_MEMBER_FETCH = "/api/member/fetch";

	private static final String APPLY_STATUS = "/api/member/applyStatus";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String API_MEMBER_ADD = "/api/member/insert";

	private IMemberBAI memberBAI;

	private static final Logger LOG = LoggerFactory.getLogger(MemberAPIControllerTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	@Test
	public void fetchAllMember()
	{

		// Mock Response 1
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		for (int i = 0; i < 5; i++)
		{
			Member liaison = new Member();
			liaison.setLastName("test " + i);
			liaison.setId(i);

			response.getMemberList().add(liaison);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMemberBAI().fetchMemberByRequest(
						Matchers.any(MemberInquiryRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest(API_MEMBER_FETCHALL).andExpect(
					jsonPath("$.memberList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new MemberResponse();
			response.setOperationSuccess(true);
			response.setMemberList(new ArrayList<Member>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMemberBAI().fetchMemberByRequest(
							Matchers.any(MemberInquiryRequest.class)))
					.thenReturn(response);

			performTest(API_MEMBER_FETCHALL).andExpect(
					jsonPath("$.memberList", hasSize(0))).andExpect(
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
	public void fetchMember()
	{

		// Mock Response 1
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());

		Member liaison = new Member();
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getMemberList().add(liaison);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMemberBAI().fetchMemberById(
						Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"id\":25}");

			performTest(API_MEMBER_FETCH).andExpect(
					jsonPath("$.memberList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new MemberResponse();
			response.setOperationSuccess(true);
			response.setMemberList(new ArrayList<Member>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMemberBAI().fetchMemberById(
							Matchers.any(FetchByIdRequest.class)))
					.thenReturn(response);

			performTest(API_MEMBER_FETCH).andExpect(
					jsonPath("$.memberList", hasSize(0))).andExpect(
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
	public void InsertMember()
	{

		// Mock Response 1
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());

		Member liaison = new Member();
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getMemberList().add(liaison);

		Mockito.when(
				getMemberBAI().insertMember(
						Matchers.any(MemberMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"member\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":2,\"personTypeValue\":3,\"id\":0,\"nameList\":[{\"otherName\":\"othernames\",\"id\":null,\"modelAction\":\"INSERT\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":1525},\"modelAction\":\"INSERT\",\"noteList\":[{\"noteText\":\"note text\",\"modelAction\":\"INSERT\"}],\"risk\":{\"riskLevelValue\":0,\"modelAction\":\"INSERT\",\"version\":10},\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\" Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":null},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":1111111,\"extension\":10,\"country\":{\"code\":\"USA\"},\"priorityValue\":1},{\"contactTypeValue\":\"3\",\"id\":null,\"type\":\"email\",\"modelAction\":\"INSERT\",\"emailAddress\":\"teste@outmail.com\",\"priorityValue\":1}],\"documentList\":[{\"id\":5,\"parentKey\":0,\"parentKeyType\":3,\"documentType\":{\"id\":5},\"modelAction\":\"INSERT\",\"issueCountry\":{\"code\":\"USA\"},\"issueStateProvinceRegion\":{\"code\":\"3\"},\"expirationDate\":1412910000000,\"value\":\"111111111111\"}],\"countryUsageList\":[{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":3},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":2},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":1}],\"preferredLanguage\":{\"id\":\"1\"},\"employmentInfoList\":[{\"id\":\"\",\"locationId\":\"11824\",\"locationName\":\"1448_location\",\"currentPay\":\"1234\",\"hireDate\":1388541600000}]}}");
			performTest(API_MEMBER_ADD).andExpect(
					jsonPath("$.memberList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new MemberResponse();
			response.setOperationSuccess(false);
			response.setMemberList(new ArrayList<Member>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMemberBAI().insertMember(
							Matchers.any(MemberMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_MEMBER_ADD).andExpect(
					jsonPath("$.memberList", hasSize(0))).andExpect(
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
	public void UpdateMember()
	{

		// Mock Response 1
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());

		Member liaison = new Member();
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getMemberList().add(liaison);
		response.setOperationSuccess(true);

		Mockito.when(
				getMemberBAI().updateMember(
						Matchers.any(MemberMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"member\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":2,\"personTypeValue\":3,\"id\":0,\"nameList\":[{\"otherName\":\"othernames\",\"id\":null,\"modelAction\":\"INSERT\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":1525},\"modelAction\":\"INSERT\",\"noteList\":[{\"noteText\":\"note text\",\"modelAction\":\"INSERT\"}],\"risk\":{\"riskLevelValue\":0,\"modelAction\":\"INSERT\",\"version\":10},\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\" Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":null},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":1111111,\"extension\":10,\"country\":{\"code\":\"USA\"},\"priorityValue\":1},{\"contactTypeValue\":\"3\",\"id\":null,\"type\":\"email\",\"modelAction\":\"INSERT\",\"emailAddress\":\"teste@outmail.com\",\"priorityValue\":1}],\"documentList\":[{\"id\":5,\"parentKey\":0,\"parentKeyType\":3,\"documentType\":{\"id\":5},\"modelAction\":\"INSERT\",\"issueCountry\":{\"code\":\"USA\"},\"issueStateProvinceRegion\":{\"code\":\"3\"},\"expirationDate\":1412910000000,\"value\":\"111111111111\"}],\"countryUsageList\":[{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":3},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":2},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":1}],\"preferredLanguage\":{\"id\":\"1\"},\"employmentInfoList\":[{\"id\":\"\",\"locationId\":\"11824\",\"locationName\":\"1448_location\",\"currentPay\":\"1234\",\"hireDate\":1388541600000}]}}");
			performTest(API_MEMBER_EDIT).andExpect(
					jsonPath("$.memberList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new MemberResponse();
			response.setOperationSuccess(false);
			response.setMemberList(new ArrayList<Member>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMemberBAI().updateMember(
							Matchers.any(MemberMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_MEMBER_EDIT).andExpect(
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
	public void DeleteMember()
	{

		// Mock Response 1
		setData("{\"member\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":2,\"personTypeValue\":3,\"id\":0,\"nameList\":[{\"otherName\":\"othernames\",\"id\":null,\"modelAction\":\"INSERT\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":1525},\"modelAction\":\"INSERT\",\"noteList\":[{\"noteText\":\"note text\",\"modelAction\":\"INSERT\"}],\"risk\":{\"riskLevelValue\":0,\"modelAction\":\"INSERT\",\"version\":10},\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\" Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":null},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":1111111,\"extension\":10,\"country\":{\"code\":\"USA\"},\"priorityValue\":1},{\"contactTypeValue\":\"3\",\"id\":null,\"type\":\"email\",\"modelAction\":\"INSERT\",\"emailAddress\":\"teste@outmail.com\",\"priorityValue\":1}],\"documentList\":[{\"id\":5,\"parentKey\":0,\"parentKeyType\":3,\"documentType\":{\"id\":5},\"modelAction\":\"INSERT\",\"issueCountry\":{\"code\":\"USA\"},\"issueStateProvinceRegion\":{\"code\":\"3\"},\"expirationDate\":1412910000000,\"value\":\"111111111111\"}],\"countryUsageList\":[{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":3},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":2},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":1}],\"preferredLanguage\":{\"id\":\"1\"},\"employmentInfoList\":[{\"id\":\"\",\"locationId\":\"11824\",\"locationName\":\"1448_location\",\"currentPay\":\"1234\",\"hireDate\":1388541600000}]}}");
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getMemberBAI().deleteMember(
						Matchers.any(MemberMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			performTest(API_MEMBER_DELETE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Error situation ==================================

			response = new MemberResponse();
			response.setOperationSuccess(false);
			response.setMemberList(new ArrayList<Member>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMemberBAI().deleteMember(
							Matchers.any(MemberMaintenanceRequest.class)))
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
		MemberResponse response = new MemberResponse();
		response.setMemberList(new ArrayList<Member>());

		Member liaison = new Member();
		liaison.setLastName("test ");
		liaison.setId(1);

		response.getMemberList().add(liaison);
		response.setOperationSuccess(true);

		Mockito.when(
				getMemberBAI().applyStatus(
						Matchers.any(MemberMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"member\":{\"firstName\":\"firstname\",\"middleName\":\"middlename\",\"lastName\":\"lastname\",\"genderValue\":2,\"personTypeValue\":3,\"id\":0,\"nameList\":[{\"otherName\":\"othernames\",\"id\":null,\"modelAction\":\"INSERT\"}],\"dateOfBirth\":1286679600000,\"suffix\":{\"id\":1525},\"modelAction\":\"INSERT\",\"noteList\":[{\"noteText\":\"note text\",\"modelAction\":\"INSERT\"}],\"risk\":{\"riskLevelValue\":0,\"modelAction\":\"INSERT\",\"version\":10},\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\" Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":null},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":1111111,\"extension\":10,\"country\":{\"code\":\"USA\"},\"priorityValue\":1},{\"contactTypeValue\":\"3\",\"id\":null,\"type\":\"email\",\"modelAction\":\"INSERT\",\"emailAddress\":\"teste@outmail.com\",\"priorityValue\":1}],\"documentList\":[{\"id\":5,\"parentKey\":0,\"parentKeyType\":3,\"documentType\":{\"id\":5},\"modelAction\":\"INSERT\",\"issueCountry\":{\"code\":\"USA\"},\"issueStateProvinceRegion\":{\"code\":\"3\"},\"expirationDate\":1412910000000,\"value\":\"111111111111\"}],\"countryUsageList\":[{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":3},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":2},{\"id\":0,\"country\":{\"code\":\"USA\"},\"usageValue\":1}],\"preferredLanguage\":{\"id\":\"1\"},\"employmentInfoList\":[{\"id\":\"\",\"locationId\":\"11824\",\"locationName\":\"1448_location\",\"currentPay\":\"1234\",\"hireDate\":1388541600000}]}}");
			performTest(APPLY_STATUS).andExpect(
					jsonPath("$.memberList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new MemberResponse();
			response.setOperationSuccess(false);
			response.setMemberList(new ArrayList<Member>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getMemberBAI().applyStatus(
							Matchers.any(MemberMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(APPLY_STATUS).andExpect(
					jsonPath("$.memberList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
