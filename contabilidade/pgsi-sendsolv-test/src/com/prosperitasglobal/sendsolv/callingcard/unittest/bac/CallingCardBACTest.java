package com.prosperitasglobal.sendsolv.callingcard.unittest.bac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.dac.ICallingCardDAC;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CodeValueDACTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/callingcard/unittest/bac/conf/callingcardbacimpltest.xml"})
public class CallingCardBACTest extends AbstractJUnit4SpringContextTests
{
	private static final int PIN_ID_1 = 8435958;
	private static final int TWO = 2;

	private ICallingCardBAC callingCardBACImpl;
	private ICallingCardDAC callingCardDAC;

	private static final Logger LOG = LoggerFactory.getLogger(CallingCardBACTest.class);

	/**
	 * Initializes the environment before execution.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getCallingCardDAC());
	}

	@Test
	public void testGeneratePin()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();

		// I am using the 'any' method because the request is created inside the BAC, so I cannot pass the one above.
		// With 'any' mockito does not care about the instance of the object.
		Mockito.when(getCallingCardDAC().insertCallingCard(any(CallingCardMaintenanceRequest.class)))
		.thenReturn(new InternalResultsResponse<CallingCardInfo>());

		InternalResultsResponse<CallingCardInfo> response = getCallingCardBACImpl().retrieveMorePinNumbers(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("Should insert 2 cards.", TWO, response.getResultsList().size());

		Mockito.verify(getCallingCardDAC(), Mockito.never()).insertCallingCard(
				any(CallingCardMaintenanceRequest.class));

	}

	@Test
	public void testAssignCardToMemberNotEnoughAvailable()
	{
		// Dummy Member
		int personId = 1;

		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(personId);

		request.setCallingCardInfo(callingCardInfo);

		InternalResultsResponse<CallingCardInfo> ccResponse = generateCallingCardMockResponse();
		ccResponse.setStatus(Status.NoRowsFoundError);
		Mockito.when(getCallingCardDAC().updateCallingCard(request)).thenReturn(ccResponse)
				.thenReturn(generateCallingCardMockResponse());
		Mockito.when(getCallingCardDAC().fetchIdPinByPersonId(personId)).thenReturn(generateIntegerMockResponse());
		Mockito.when(getCallingCardDAC().fetchAvailablePins(any(CallingCardMaintenanceRequest.class))).thenReturn(
				generateCallingCardMockResponse());
		Mockito.when(getCallingCardDAC().insertCallingCard(any(CallingCardMaintenanceRequest.class)))
				.thenReturn(new InternalResultsResponse<CallingCardInfo>());

		// Assign one card
		InternalResultsResponse<CallingCardInfo> response = getCallingCardBACImpl().assignCard(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("", (Integer)PIN_ID_1, response.getFirstResult().getCallingCardId());

		Mockito.verify(getCallingCardDAC(), Mockito.times(1)).updateCallingCard(request);
		Mockito.verify(getCallingCardDAC(), Mockito.never()).fetchIdPinByPersonId(personId);
		Mockito.verify(getCallingCardDAC(), Mockito.never()).fetchAvailablePins(
				any(CallingCardMaintenanceRequest.class));
		Mockito.verify(getCallingCardDAC(), Mockito.never()).insertCallingCard(
				any(CallingCardMaintenanceRequest.class));
	}

	@Test
	public void testAssignCardToMemberNoneAvailable()
	{
		// Dummy Member
		int personId = 1;

		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(personId);

		request.setCallingCardInfo(callingCardInfo);

		InternalResultsResponse<CallingCardInfo> ccResponse = generateCallingCardMockResponse();
		ccResponse.setStatus(Status.NoRowsFoundError);
		Mockito.when(getCallingCardDAC().updateCallingCard(request)).thenReturn(ccResponse)
				.thenReturn(generateCallingCardMockResponse());
		Mockito.when(getCallingCardDAC().fetchIdPinByPersonId(personId)).thenReturn(generateIntegerMockResponse());
		Mockito.when(getCallingCardDAC().fetchAvailablePins(any(CallingCardMaintenanceRequest.class))).thenReturn(
				ccResponse);

		// Assign one card
		InternalResultsResponse<CallingCardInfo> response = getCallingCardBACImpl().assignCard(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("", (Integer)PIN_ID_1, response.getFirstResult().getCallingCardId());

		Mockito.verify(getCallingCardDAC(), Mockito.times(1)).updateCallingCard(request);
		Mockito.verify(getCallingCardDAC(), Mockito.never()).fetchIdPinByPersonId(personId);
	}

	@Test
	public void testAssignCardToMemberEnoughAvailable()
	{
		// Dummy Member
		int personId = 1;

		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(personId);

		request.setCallingCardInfo(callingCardInfo);

		InternalResultsResponse<CallingCardInfo> ccResponse = generateCallingCardMockResponse();
		Mockito.when(getCallingCardDAC().updateCallingCard(request)).thenReturn(ccResponse)
				.thenReturn(generateCallingCardMockResponse());
		Mockito.when(getCallingCardDAC().fetchIdPinByPersonId(personId)).thenReturn(generateIntegerMockResponse());
		Mockito.when(getCallingCardDAC().fetchAvailablePins(any(CallingCardMaintenanceRequest.class))).thenReturn(
				generateCallingCardMockResponse(TWO));
		Mockito.when(getCallingCardDAC().insertCallingCard(any(CallingCardMaintenanceRequest.class)))
				.thenReturn(new InternalResultsResponse<CallingCardInfo>());

		// Assign one card
		InternalResultsResponse<CallingCardInfo> response = getCallingCardBACImpl().assignCard(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("", (Integer)PIN_ID_1, response.getFirstResult().getCallingCardId());

		Mockito.verify(getCallingCardDAC(), Mockito.times(1)).updateCallingCard(request);
		Mockito.verify(getCallingCardDAC(), Mockito.never()).fetchIdPinByPersonId(personId);
		Mockito.verify(getCallingCardDAC(), Mockito.never()).fetchAvailablePins(
				any(CallingCardMaintenanceRequest.class));
		Mockito.verify(getCallingCardDAC(), Mockito.never()).insertCallingCard(
				any(CallingCardMaintenanceRequest.class));
	}

	@Test
	public void testRefillCard()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();

		// Dummy Member
		int personId = 1;

		callingCardInfo.setPersonId(personId);
		callingCardInfo.setAmount(new BigDecimal(1));

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getCallingCardDAC().fetchIdPinByPersonId(personId)).thenReturn(generateIntegerMockResponse());

		InternalResultsResponse<CallingCardInfo> response = getCallingCardBACImpl().refillCard(request);

		CommonTestRoutines.assertResponse(response);
	}

	@Test
	public void testGetCardBalance()
	{
		// Dummy Member
		int personId = 1;

		MathContext mc = new MathContext(2); // 2 precision

		BigDecimal zero2Precision = new BigDecimal("0.00", mc);
		BigDecimal delta = new BigDecimal(0.1, mc);
		BigDecimal negativeDelta = new BigDecimal(-0.1, mc);

		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(personId);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getCallingCardDAC().updateCallingCard(request)).thenReturn(generateCallingCardMockResponse());
		Mockito.when(getCallingCardDAC().fetchIdPinByPersonId(personId)).thenReturn(generateIntegerMockResponse());

		// Assign one card
		InternalResultsResponse<CallingCardInfo> response = getCallingCardBACImpl().assignCard(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("", (Integer)PIN_ID_1, response.getFirstResult().getCallingCardId());

		// Get initial balance
		response = getCallingCardBACImpl().getCardBalance(request);
		CommonTestRoutines.assertResponse(response);
		BigDecimal balance = response.getFirstResult().getBalance();

		LOG.debug("the initial balance is:" + balance);

		// Add delta
		request.getCallingCardInfo().setAmount(delta);
		response = getCallingCardBACImpl().refillCard(request);
		CommonTestRoutines.assertResponse(response);

		// Get updated balance
		response = getCallingCardBACImpl().getCardBalance(request);
		CommonTestRoutines.assertResponse(response);

		// assert difference from manual calculation
		BigDecimal newbalance = balance.add(delta);
		LOG.debug("the new manual balance is:" + newbalance);

		BigDecimal subtraction = newbalance.subtract(response.getFirstResult().getBalance(), mc);
		LOG.debug("the subtraction balance is:" + subtraction);
		LOG.debug("the subtraction float is:" + subtraction.floatValue());

		BigDecimal difference =
				balance.add(delta).subtract(response.getFirstResult().getBalance(), mc);

		assertTrue("(1) difference should be 0.00, but is " + difference, difference.equals(zero2Precision));

		// new manual balance
		balance = response.getFirstResult().getBalance();

		// Add -delta
		request.getCallingCardInfo().setAmount(negativeDelta);
		response = getCallingCardBACImpl().refillCard(request);
		CommonTestRoutines.assertResponse(response);

		// Get updated balance
		response = getCallingCardBACImpl().getCardBalance(request);
		CommonTestRoutines.assertResponse(response);

		// assert difference from manual calculation
		difference =
				balance.add(negativeDelta).subtract(response.getFirstResult().getBalance(), mc);
		assertTrue("(2) difference should be 0.00, but is " + difference, difference.equals(zero2Precision));
	}

	private InternalResultsResponse<Integer> generateIntegerMockResponse()
	{
		InternalResultsResponse<Integer> mockResponse = new InternalResultsResponse<Integer>();
		mockResponse.addResult(PIN_ID_1);

		return mockResponse;
	}

	private InternalResultsResponse<CallingCardInfo> generateCallingCardMockResponse()
	{
		return generateCallingCardMockResponse(1);
	}

	private InternalResultsResponse<CallingCardInfo> generateCallingCardMockResponse(Integer minimumQty)
	{
		InternalResultsResponse<CallingCardInfo> mockResponse = new InternalResultsResponse<CallingCardInfo>();
		CallingCardInfo mockCallingCardInfo = new CallingCardInfo();
		mockCallingCardInfo.setCallingCardId(PIN_ID_1);

		for (int i = 0; i < minimumQty; i++)
		{
			mockResponse.addResult(mockCallingCardInfo);
		}

		return mockResponse;
	}

	public ICallingCardBAC getCallingCardBACImpl()
	{
		return callingCardBACImpl;
	}

	@Resource
	public void setCallingCardBACImpl(ICallingCardBAC callingCardBACImpl)
	{
		this.callingCardBACImpl = callingCardBACImpl;
	}

	public ICallingCardDAC getCallingCardDAC()
	{
		return callingCardDAC;
	}

	@Resource
	public void setCallingCardDAC(ICallingCardDAC mockCallingCardDAC)
	{
		callingCardDAC = mockCallingCardDAC;
	}
}
