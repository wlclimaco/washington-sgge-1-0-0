package com.prosperitasglobal.sendsolv.sdn.controller.unittest;

import static org.hamcrest.Matchers.containsString;
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

import com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.SarResponse;

/**
 * The Class MemberViewControllerTest.
 */
public class SdnViewControllerTest extends AbstractTestBase
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SdnAPIControllerTest.class);

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private ISuspiciousActivityBAI suspiciousActivityBAI;

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

	@Test
	public void loadList()
	{

		// Mock Organization Response
		SarResponse response = new SarResponse();
		response.setSuspiciousActivityList(new ArrayList<SuspiciousActivity>());
		for (int i = 0; i < 5; i++)
		{
			SuspiciousActivity suspiciousActivity = new SuspiciousActivity();
			suspiciousActivity.setId(i);
			response.getSuspiciousActivityList().add(suspiciousActivity);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getSuspiciousActivityBAI().fetchSuspiciousActivityByRequest(
						Matchers.any(SarInquiryRequest.class)))
				.thenReturn(response);
		try
		{
			setData("");

			performTestGet("/sdn").andExpect(view().name(containsString("/sdn/sdn_tabs")));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

}
