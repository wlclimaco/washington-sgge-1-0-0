package com.prosperitasglobal.sendsolv.organization.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.PayrollTypeEnum;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;

public class OrganizationAPIControllerTest extends AbstractTestBase
{
	private static final String API_ORGANIZATION_DELETE = "/api/organization/delete";

	private static final String API_ORGANIZATION_EDIT = "/api/organization/edit";

	private static final String CODE = "code";

	private static final String APPLY_STATUS = "/api/member/applyStatus";

	private static final String API_ORGANIZATION_FETCHALL = "/api/organization/fetchall";

	private static final String API_ORGANIZATION_FETCH = "/api/organization/fetch";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String API_ORGANIZATION_ADD = "/api/organization/add";

	private IOrganizationBAI organizationBAI;
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationAPIControllerTest.class);

	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	@Test
	public void wfetchOrganizations()
	{

		// Mock Response 1
		OrganizationResponse response = new OrganizationResponse();
		response.setOrganizationList(new ArrayList<Organization>());
		Organization organization = new Organization();
		organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		organization.setId(1);
		organization.setIsPayrollCentralized(PayrollTypeEnum.CENTRALIZED);
		response.getOrganizationList().add(organization);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().fetchOrganizationById(
						Matchers.any(FetchByIdRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"id\":25}");

			performTest(API_ORGANIZATION_FETCH).andExpect(
					jsonPath("$.organizationList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new OrganizationResponse();
			response.setOperationSuccess(true);
			response.setOrganizationList(new ArrayList<Organization>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().fetchOrganizationById(
							Matchers.any(FetchByIdRequest.class)))
							.thenReturn(response);

			performTest(API_ORGANIZATION_FETCH).andExpect(
					jsonPath("$.organizationList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.verify(
					getOrganizationBAI().fetchOrganizationById(
							Matchers.any(FetchByIdRequest.class)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

		try
		{
			Mockito.when(getOrganizationBAI().fetchOrganizationById(
					Matchers.any(FetchByIdRequest.class))).thenThrow(new NullPointerException());

			performTest(API_ORGANIZATION_FETCH);

			Mockito.calls(1);

		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void fetchAllOrganizations()
	{

		// Mock Response 1
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

		// Mockito.verify(
		// getOrganizationBAI().fetchOrganizationByRequest(
		// Matchers.any(PagedInquiryRequest.class)));
		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().fetchOrganizationByRequest(
						Matchers.any(PagedInquiryRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest(API_ORGANIZATION_FETCHALL).andExpect(
					jsonPath("$.organizationList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new OrganizationResponse();
			response.setOperationSuccess(true);
			response.setOrganizationList(new ArrayList<Organization>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().fetchOrganizationByRequest(
							Matchers.any(PagedInquiryRequest.class)))
							.thenReturn(response);

			performTest(API_ORGANIZATION_FETCHALL).andExpect(
					jsonPath("$.organizationList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

		try
		{
			// ============================= Exception situation ==================================

			Mockito.when(
					getOrganizationBAI().fetchOrganizationByRequest(
							Matchers.any(PagedInquiryRequest.class))).thenThrow(new NullPointerException());

			performTest(API_ORGANIZATION_FETCHALL);

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void InsertOrganization()
	{

		// Mock Response 1
		OrganizationResponse response = new OrganizationResponse();

		response.setOrganizationList(new ArrayList<Organization>());
		Organization organization = new Organization();
		organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		organization.setId(1);
		organization.setIsPayrollCentralized(PayrollTypeEnum.CENTRALIZED);
		response.getOrganizationList().add(organization);

		Mockito.when(
				getOrganizationBAI().insertOrganization(
						Matchers.any(OrganizationMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"organization\":{\"id\":\"11846\",\"country\":{\"code\":\"USA\"},\"employerIdentificationNumber\":\"123456789\",\"name\":\"00D4_organization\",\"northAmericanIndustryClassificationSystemCode\":{\"code\":\"111421\"},\"standardIndustrialClassificationCode\":{\"code\":\"5900\"},\"numberOfEmployees\":{\"id\":24},\"numberOfMigrantWorkers\":{\"id\":3},\"statusValue\":1,\"modelAction\":\"UPDATE\",\"isPayrollCentralizedValue\":1,\"numberOfLocations\":\"8\",\"dbaName\":\"07CE_dba\",\"businessTypeValue\":1,\"version\":0,\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":123456789,\"extension\":100,\"country\":{\"code\":\"USA\"},\"priorityValue\":1}]}}");

			performTest(API_ORGANIZATION_ADD).andExpect(
					jsonPath("$.organizationList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new OrganizationResponse();
			response.setOperationSuccess(false);
			response.setOrganizationList(new ArrayList<Organization>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().insertOrganization(
							Matchers.any(OrganizationMaintenanceRequest.class))).thenReturn(response);

			performTest(API_ORGANIZATION_ADD);

			Mockito.when(
					getOrganizationBAI().insertOrganization(
							Matchers.any(OrganizationMaintenanceRequest.class))).thenReturn(response);

			Mockito.verify(getOrganizationBAI().insertOrganization(
					Matchers.any(OrganizationMaintenanceRequest.class)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void UpdateOrganization()
	{

		// Mock Response 1
		OrganizationResponse response = new OrganizationResponse();

		response.setOrganizationList(new ArrayList<Organization>());
		Organization organization = new Organization();
		organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		organization.setId(1);
		organization.setIsPayrollCentralized(PayrollTypeEnum.CENTRALIZED);
		response.getOrganizationList().add(organization);
		response.setOperationSuccess(true);

		Mockito.when(
				getOrganizationBAI().updateOrganization(
						Matchers.any(OrganizationMaintenanceRequest.class))).thenReturn(response);
		try
		{

			setData("{\"organization\":{\"id\":\"11846\",\"country\":{\"code\":\"USA\"},\"employerIdentificationNumber\":\"123456789\",\"name\":\"00D4_organization\",\"northAmericanIndustryClassificationSystemCode\":{\"code\":\"111421\"},\"standardIndustrialClassificationCode\":{\"code\":\"5900\"},\"numberOfEmployees\":{\"id\":24},\"numberOfMigrantWorkers\":{\"id\":3},\"statusValue\":1,\"modelAction\":\"UPDATE\",\"isPayrollCentralizedValue\":1,\"numberOfLocations\":\"8\",\"dbaName\":\"07CE_dba\",\"businessTypeValue\":1,\"version\":0,\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":123456789,\"extension\":100,\"country\":{\"code\":\"USA\"},\"priorityValue\":1}]}}");

			performTest(API_ORGANIZATION_EDIT).andExpect(
					jsonPath("$.organizationList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new OrganizationResponse();
			response.setOperationSuccess(false);
			response.setOrganizationList(new ArrayList<Organization>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().updateOrganization(
							Matchers.any(OrganizationMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_ORGANIZATION_EDIT).andExpect(
					jsonPath("$.organizationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

		try
		{
			// ============================= Exception situation ==================================

			Mockito.when(
					getOrganizationBAI().updateOrganization(
							Matchers.any(OrganizationMaintenanceRequest.class))).thenThrow(new NullPointerException());

			performTest(API_ORGANIZATION_EDIT);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void DeleteOrganization()
	{

		// Mock Response 1
		setData("{\"organization\":{\"id\":8537}}");
		OrganizationResponse response = new OrganizationResponse();
		response.getOrganizationList().add(null);
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().deleteOrganization(
						Matchers.any(OrganizationMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			performTest(API_ORGANIZATION_DELETE).andExpect(
					jsonPath("$.organizationList[0]", nullValue()))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Error situation ==================================

			response = new OrganizationResponse();
			response.setOperationSuccess(false);
			response.setOrganizationList(null);
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().deleteOrganization(
							Matchers.any(OrganizationMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_ORGANIZATION_DELETE).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
		try
		{
			// ============================= Exception situation ==================================

			Mockito.when(
					getOrganizationBAI().deleteOrganization(
							Matchers.any(OrganizationMaintenanceRequest.class))).thenThrow(new NullPointerException());

			performTest(API_ORGANIZATION_DELETE);

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
		setData("{\"organization\":{\"id\":8537}}");
		OrganizationResponse response = new OrganizationResponse();
		response.getOrganizationList().add(null);
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().applyStatus(
						Matchers.any(OrganizationMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			performTest("/api/organization/applyStatus").andExpect(
					jsonPath("$.organizationList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new OrganizationResponse();
			response.setOperationSuccess(false);
			response.setOrganizationList(null);
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().applyStatus(
							Matchers.any(OrganizationMaintenanceRequest.class)))
					.thenReturn(response);

			performTest("/api/organization/applyStatus").andExpect(
					jsonPath("$.organizationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.when(
					getOrganizationBAI().applyStatus(
							Matchers.any(OrganizationMaintenanceRequest.class))).thenThrow(new RuntimeException());

			performTest("/api/organization/applyStatus");

			Mockito.verify(getOrganizationBAI().applyStatus(
					Matchers.any(OrganizationMaintenanceRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
