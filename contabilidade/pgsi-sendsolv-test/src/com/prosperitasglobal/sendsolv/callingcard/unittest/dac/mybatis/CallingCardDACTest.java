package com.prosperitasglobal.sendsolv.callingcard.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.Member;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CodeValueDACTest.
 */
public class CallingCardDACTest extends AbstractTestBaseDAC
{
	private static final int TWO = 2;
	private static final int PIN_ID_FROM_DB1 = 8435958;
	private static final int PIN_ID_FROM_DB2 = 8435959;
	/** The Constant RESPONSE_NOT_NULL. */
	private static final String RESPONSE_NOT_NULL = "Response object should not to be null";

	@Test
	public void testGeneratePin()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();

		// It will retrieve 4 cards because 2 are inserted by the setUp() routine
		InternalResultsResponse<CallingCardInfo> response2 = getCallingCardDACImpl().fetchAvailablePins(request);
		assertEquals("Should retrieve 2 cards.", TWO, response2.getResultsList().size());
	}

	@Test
	public void testAssignCardToMember()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();

		// Insert a dummy Member
		Member member = insertMember();
		callingCardInfo.setPersonId(member.getId());
		request.setCallingCardInfo(callingCardInfo);

		// Assign one card
		InternalResultsResponse<CallingCardInfo> response = getCallingCardDACImpl().updateCallingCard(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("", (Integer)PIN_ID_FROM_DB1, response.getFirstResult().getCallingCardId());

		// Assign another card
		response = getCallingCardDACImpl().updateCallingCard(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("", (Integer)PIN_ID_FROM_DB2, response.getFirstResult().getCallingCardId());

		// Try assigning another card. Should fail.
		response = getCallingCardDACImpl().updateCallingCard(request);
		assertNotNull(RESPONSE_NOT_NULL, response);
		assertNotNull("Message list object should not to be null", response.getMessageInfoList());
		assertEquals("Status should be NoRowsFound", Status.NoRowsFoundError, response.getStatus());
	}

	@Test
	public void testFetchIdPinByPersonId()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();

		// Insert a dummy Member
		Member member = insertMember();
		callingCardInfo.setCallingCardId(2);
		callingCardInfo.setCallingCardNumber("AA");
		callingCardInfo.setLotCode("lotTest");
		callingCardInfo.setCreateDateUTC(1l);
		callingCardInfo.setCreateUser("pgsi");
		callingCardInfo.setPersonId(member.getId());
		request.setCallingCardInfo(callingCardInfo);

		// Assign one card
		InternalResultsResponse<CallingCardInfo> response = getCallingCardDACImpl().insertCallingCard(request);
		CommonTestRoutines.assertResponse(response);
		assertEquals("", (Integer)2, response.getFirstResult().getCallingCardId());

		InternalResultsResponse<Integer> integerResponse = getCallingCardDACImpl().fetchIdPinByPersonId(member.getId());
		assertEquals("", (Integer)2, integerResponse.getFirstResult());

	}

	@Test
	public void testAddCardToDatabase()
	{
		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		CallingCardInfo callingCardInfo = new CallingCardInfo();

		callingCardInfo.setCallingCardId(1);
		callingCardInfo.setCallingCardNumber("1");
		callingCardInfo.setCreateDateUTC(1l);
		callingCardInfo.setLotCode("pgsi");
		callingCardInfo.setCreateUser("pgsi");

		request.setCallingCardInfo(callingCardInfo);

		// Assign one card
		InternalResultsResponse<CallingCardInfo> response = getCallingCardDACImpl().insertCallingCard(request);
		CommonTestRoutines.assertResponse(response);
	}

	@Before
	public void setUp()
	{
		// Add two cards
		executeSqlScript("com/prosperitasglobal/sendsolv/callingcard/unittest/dac/mybatis/conf/insertCallingCard.sql",
				false);
	}
}
