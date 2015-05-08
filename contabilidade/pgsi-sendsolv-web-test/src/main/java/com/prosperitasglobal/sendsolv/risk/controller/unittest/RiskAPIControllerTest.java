package com.prosperitasglobal.sendsolv.risk.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.document.controller.unittest.DocumentAPIControllerTest;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.RiskResponse;

public class RiskAPIControllerTest extends AbstractTestBase
{

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final Logger LOG = LoggerFactory.getLogger(DocumentAPIControllerTest.class);

	/** The organization bai. */
	private IOrganizationBAI organizationBAI;

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
	public void edit()
	{

		// Mock Response 1
		RiskResponse response = new RiskResponse();
		response.setRiskList(new ArrayList<Risk>());

		Risk risk = new Risk();
		risk.setParentKey(1000);
		risk.setParentKeyType(BusinessTypeEnum.ORGANIZATION);
		risk.setRiskLevelNote("noteText");

		response.getRiskList().add(risk);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().updateRisk(
						Matchers.any(RiskMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"risk\":{\"parentKey\":8417,\"parentKeyType\":1,\"riskLevelValue\":3,\"riskLevelNote\":\"note Risk\",\"modelAction\":\"UPDATE\"}}");

			performTest("/api/risk/edit").andExpect(
					jsonPath("$.riskList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new RiskResponse();
			response.setOperationSuccess(true);
			response.setRiskList(new ArrayList<Risk>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().updateRisk(
							Matchers.any(RiskMaintenanceRequest.class)))
					.thenReturn(response);

			performTest("/api/risk/edit").andExpect(
					jsonPath("$.riskList", hasSize(0))).andExpect(
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
			Mockito.when(getOrganizationBAI().updateRisk(
					Matchers.any(RiskMaintenanceRequest.class))).thenThrow(new NullPointerException());

			performTest("/api/risk/edit");

			Mockito.calls(1);

		}

		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
