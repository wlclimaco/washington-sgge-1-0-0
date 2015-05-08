package com.prosperitasglobal.sendsolv.pricing.controller.unitest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.payments.controller.unittest.TransactionViewControllerTest;

public class PricingViewControllerTest extends AbstractTestBase
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransactionViewControllerTest.class);

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE =
			"Unexpected exception while testing response";

	ILocationBAI locationBAI;

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

	@Test
	public void loadList()
	{
		// Mock Organization Response
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());

		for (int i = 0; i < 5; i++)
		{
			Location location = new Location();
			location.setBusinessType(BusinessTypeEnum.LOCATION);
			location.setId(i);
			response.getLocationList().add(location);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationByRequest(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(response);

		try
		{
			performTestGet("/pricing/profile_list")
			.andExpect(view().name(containsString("/pricing/pricing_main")))
					.andExpect(
							(model().size(1))).andExpect(model().attributeExists("response"));

			Mockito.verify(
					getLocationBAI().fetchLocationByRequest(
							Matchers.any(PagedInquiryRequest.class)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE;
			LOG.error(msg, e);
		}

	}

}
