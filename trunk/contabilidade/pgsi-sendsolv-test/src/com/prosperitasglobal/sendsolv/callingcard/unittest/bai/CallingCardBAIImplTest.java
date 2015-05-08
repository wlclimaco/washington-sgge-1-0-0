package com.prosperitasglobal.sendsolv.callingcard.unittest.bai;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.bai.ICallingCardBAI;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.prosperitasglobal.sendsolv.callingcard.model.response.CallingCardMaintenanceResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class OrganizationBAITest.
 */
@ContextConfiguration(locations =
{"classpath:com/prosperitasglobal/sendsolv/callingcard/unittest/bai/conf/callingcardbaiimpltest.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"
})
public class CallingCardBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(CallingCardBAIImplTest.class);

	private static final String PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_PERSON_ID_REQUIRED =
			"sendsolv.base.callingcardvalidator.person.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_AMOUNT_REQUIRED =
			"sendsolv.base.callingcardvalidator.amount.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_OBJECT_REQUIRED =
			"sendsolv.base.callingcardvalidator.object.required";

	/** The organization bai. */
	private ICallingCardBAI callingCardBAI;

	/** The mock organization bac. */
	private ICallingCardBAC mockCallingCardBAC;

	// error response object to be used in several tests.
	/** The err response. */
	private InternalResultsResponse<CallingCardInfo> errResponse;

	/**
	 * Gets the err response.
	 *
	 * @return the err response
	 */
	public InternalResultsResponse<CallingCardInfo> getErrResponse()
	{
		return errResponse;
	}

	/**
	 * Sets the err response.
	 *
	 * @param errResponse the err response
	 */
	public void setErrResponse(InternalResultsResponse<CallingCardInfo> errResponse)
	{
		this.errResponse = errResponse;
	}

	/**
	 * The Constructor.
	 */
	public CallingCardBAIImplTest()
	{
		// setup the error response object.
		setErrResponse(new InternalResultsResponse<CallingCardInfo>());
		getErrResponse().setStatus(Status.UnspecifiedError);
	}

	/**
	 * Test insert location.
	 */
	@Test
	public void testAssignCard()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(1);
		callingCardInfo.setModelAction(PersistanceActionEnum.INSERT);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().assignCard(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().assignCard(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's assignCard was called 1 time.
		Mockito.verify(getMockCallingCardBAC()).assignCard(request);

	}

	@Test
	public void testAssignCardNoObject()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardMaintenanceResponse response = getCallingCardBAI().assignCard(request);

		assertEquals("", 1, response.getMessageList().size());
		assertEquals("", PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_OBJECT_REQUIRED,
				response.getMessageList().get(0).getMessageInfo().getCode());

	}

	@Test
	public void testAssignCardNoId()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setModelAction(PersistanceActionEnum.INSERT);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().assignCard(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().assignCard(request);

		assertEquals("", 1, response.getMessageList().size());
		assertEquals("", PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_PERSON_ID_REQUIRED,
				response.getMessageList().get(0).getMessageInfo().getCode());

	}

	@Test
	public void testRefillCard()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(1);
		callingCardInfo.setAmount(new BigDecimal(1));
		callingCardInfo.setModelAction(PersistanceActionEnum.UPDATE);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().refillCard(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().refillCard(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's assignCard was called 1 time.
		Mockito.verify(getMockCallingCardBAC()).refillCard(request);

	}

	@Test
	public void testRefillCardNoIdNoAmount()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setModelAction(PersistanceActionEnum.UPDATE);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().refillCard(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().refillCard(request);

		assertEquals("", 2, response.getMessageList().size());

		assertEquals("", PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_PERSON_ID_REQUIRED,
				response.getMessageList().get(0).getMessageInfo().getCode());
		assertEquals("", PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_AMOUNT_REQUIRED,
				response.getMessageList().get(1).getMessageInfo().getCode());
	}

	@Test
	public void testRefillCardNoAmount()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(1);
		callingCardInfo.setModelAction(PersistanceActionEnum.UPDATE);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().refillCard(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().refillCard(request);

		assertEquals("", 1, response.getMessageList().size());

		assertEquals("", PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_AMOUNT_REQUIRED,
				response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testGetBalance()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setPersonId(1);
		callingCardInfo.setModelAction(PersistanceActionEnum.FETCH);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().getCardBalance(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().getCardBalance(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's assignCard was called 1 time.
		Mockito.verify(getMockCallingCardBAC()).getCardBalance(request);
	}

	@Test
	public void testGetBalanceNoId()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setModelAction(PersistanceActionEnum.INSERT);

		request.setCallingCardInfo(callingCardInfo);

		CallingCardMaintenanceResponse response = getCallingCardBAI().getCardBalance(request);

		assertEquals("", 1, response.getMessageList().size());

		assertEquals("", PROSPERITASGLOBAL_BASE_CALLINGCARDVALIDATOR_PERSON_ID_REQUIRED,
				response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAvailablePins()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setModelAction(PersistanceActionEnum.FETCH);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().fetchAvailablePins(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().fetchAvailablePins(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's assignCard was called 1 time.
		Mockito.verify(getMockCallingCardBAC()).fetchAvailablePins(request);
	}

	@Test
	public void testRetrieveMorePinNumbers()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		CallingCardInfo callingCardInfo = new CallingCardInfo();
		callingCardInfo.setModelAction(PersistanceActionEnum.FETCH);

		request.setCallingCardInfo(callingCardInfo);

		Mockito.when(getMockCallingCardBAC().retrieveMorePinNumbers(request)).thenReturn(returnInsert(callingCardInfo));

		CallingCardMaintenanceResponse response = getCallingCardBAI().retrieveMorePinNumbers(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's assignCard was called 1 time.
		Mockito.verify(getMockCallingCardBAC()).retrieveMorePinNumbers(request);
	}

	private InternalResultsResponse<CallingCardInfo> returnInsert(CallingCardInfo callingCardInfo)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();
		response.addResult(callingCardInfo);
		return response;
	}

	public ICallingCardBAC getMockCallingCardBAC()
	{
		return mockCallingCardBAC;
	}

	@Resource
	public void setMockCallingCardBAC(ICallingCardBAC mockCallingCardBAC)
	{
		this.mockCallingCardBAC = mockCallingCardBAC;
	}

	public ICallingCardBAI getCallingCardBAI()
	{
		return callingCardBAI;
	}

	@Resource
	public void setCallingCardBAI(ICallingCardBAI callingCardBAI)
	{
		this.callingCardBAI = callingCardBAI;
	}
}
