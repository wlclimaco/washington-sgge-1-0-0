package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.model.ContactParticipantId;
import com.prosperitasglobal.sendsolv.model.LocationParticipantId;
import com.prosperitasglobal.sendsolv.model.MemberParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantId;
import com.prosperitasglobal.sendsolv.model.OrganizationParticipantId;
import com.prosperitasglobal.sendsolv.model.ParticipantId;
import com.prosperitasglobal.sendsolv.model.ParticipantIdTypeEnum;
import com.prosperitasglobal.sendsolv.model.RecipientParticipantId;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivityParticipantId;
import com.prosperitasglobal.sendsolv.model.TransferSettingParticipantId;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class ParticipantDACTest. This test covers Member/Recipient Participant Id's.
 */
public class ParticipantIdDACTest extends AbstractTestBaseDAC
{
	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(ParticipantIdDACTest.class);

	/** The location dac. */
	private IParticipantIdDAC participantIdDAC;

	/**
	 * This method is executed before each @Test annotated method.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteParticipantId.sql", false);
	}

	/**
	 * Common routine for checking the results of the DAC method against the expected results.
	 *
	 * @param response The response from the DAC method call.
	 * @param participantId The participant id from the DAC method call.
	 * @param participantIdType The expected identifier type.
	 * @param upperId The expected upper id.
	 * @param lowerId The expected lower id.
	 * @param sequence The expected sequence.
	 */
	private static void checkResults(InternalResponse response, ParticipantId participantId,
			ParticipantIdTypeEnum participantIdType, Character upperId, Character lowerId, Integer sequence)
	{
		CommonTestRoutines.assertResponse(response);

		LOG.debug(participantId.getParticipantIdType() + " ID: " + CommonRoutines.formatParticipantId(participantId));

		Assert.assertEquals("Error, ParticipantIdType " + participantId.getParticipantIdType()
				+ " not ParticipantIdType " + participantIdType, participantId.getParticipantIdType(),
				participantIdType);
		Assert.assertEquals("Error, UpperId " + participantId.getUpperId() + " not UpperId " + upperId,
				participantId.getUpperId(), upperId);
		Assert.assertEquals("Error, LowerId " + participantId.getLowerId() + " not LowerId " + lowerId,
				participantId.getLowerId(), lowerId);
		Assert.assertEquals("Error, Sequence " + participantId.getSequence() + " not Sequence " + sequence,
				Integer.toString(participantId.getSequence()), Integer.toString(sequence));
	}

	/**
	 * Gets the participant id dac.
	 *
	 * @return the participant id dac
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
	}

	/**
	 * Sets the participant id dac.
	 *
	 * @param participantIdDAC the participant id dac
	 */
	@Resource
	public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	{
		this.participantIdDAC = participantIdDAC;
	}

	/**
	 * Test fetching of next contact id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextContactParticipantIdInitialRequest()
	{
		InternalResultsResponse<ContactParticipantId> response =
				getParticipantIdDAC().fetchNextContactParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.CONTACT, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next contact id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextContactParticipantIdSecondRequest()
	{
		InternalResultsResponse<ContactParticipantId> response =
				getParticipantIdDAC().fetchNextContactParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.CONTACT, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextContactParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.CONTACT, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next location id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextLocationParticipantIdInitialRequest()
	{
		InternalResultsResponse<LocationParticipantId> response =
				getParticipantIdDAC().fetchNextLocationParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.LOCATION, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next location id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextLocationParticipantIdSecondRequest()
	{
		InternalResultsResponse<LocationParticipantId> response =
				getParticipantIdDAC().fetchNextLocationParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.LOCATION, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextLocationParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.LOCATION, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next money transfer batch id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextMoneyTransferBatchParticipantIdInitialRequest()
	{
		InternalResultsResponse<MoneyTransferBatchParticipantId> response =
				getParticipantIdDAC().fetchNextMoneyTransferBatchParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MONEY_TRANSFER_BATCH, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next money transfer batch id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextMoneyTransferBatchParticipantIdSecondRequest()
	{
		InternalResultsResponse<MoneyTransferBatchParticipantId> response =
				getParticipantIdDAC().fetchNextMoneyTransferBatchParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MONEY_TRANSFER_BATCH, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextMoneyTransferBatchParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MONEY_TRANSFER_BATCH, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next money transfer id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextMoneyTransferParticipantIdInitialRequest()
	{
		InternalResultsResponse<MoneyTransferParticipantId> response =
				getParticipantIdDAC().fetchNextMoneyTransferParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MONEY_TRANSFER, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next money transfer id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextMoneyTransferParticipantIdSecondRequest()
	{
		InternalResultsResponse<MoneyTransferParticipantId> response =
				getParticipantIdDAC().fetchNextMoneyTransferParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MONEY_TRANSFER, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextMoneyTransferParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MONEY_TRANSFER, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next member id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextMemberParticipantIdInitialRequest()
	{
		InternalResultsResponse<MemberParticipantId> response = getParticipantIdDAC().fetchNextMemberParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MEMBER, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next member id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextMemberParticipantIdSecondRequest()
	{
		InternalResultsResponse<MemberParticipantId> response = getParticipantIdDAC().fetchNextMemberParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MEMBER, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextMemberParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.MEMBER, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next organization id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextOrganizationParticipantIdInitialRequest()
	{
		InternalResultsResponse<OrganizationParticipantId> response =
				getParticipantIdDAC().fetchNextOrganizationParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.ORGANIZATION, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next organization id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextOrganizationParticipantIdSecondRequest()
	{
		InternalResultsResponse<OrganizationParticipantId> response =
				getParticipantIdDAC().fetchNextOrganizationParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.ORGANIZATION, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextOrganizationParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.ORGANIZATION, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next recipient id when a previous one isn't on the db.
	 */
	@Test
	public void testFetchNextRecipientParticipantIdInitialRequest()
	{
		InternalResultsResponse<RecipientParticipantId> response =
				getParticipantIdDAC().fetchNextRecipientParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.RECIPIENT, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next recipient id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextRecipientParticipantIdSecondRequest()
	{
		InternalResultsResponse<RecipientParticipantId> response =
				getParticipantIdDAC().fetchNextRecipientParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.RECIPIENT, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextRecipientParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.RECIPIENT, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next transfer setting id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextTransferSettingParticipantIdInitialRequest()
	{
		InternalResultsResponse<TransferSettingParticipantId> response =
				getParticipantIdDAC().fetchNextTransferSettingParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.TRANSFER_SETTING, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next suspicious activity id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextTransferSettingParticipantIdSecondRequest()
	{
		InternalResultsResponse<TransferSettingParticipantId> response =
				getParticipantIdDAC().fetchNextTransferSettingParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.TRANSFER_SETTING, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextTransferSettingParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.TRANSFER_SETTING, 'A', 'A', 2);
	}

	/**
	 * Test fetching of next transfer setting id when a previous one is not found on the db.
	 */
	@Test
	public void testFetchNextSuspiciousActivityParticipantIdInitialRequest()
	{
		InternalResultsResponse<SuspiciousActivityParticipantId> response =
				getParticipantIdDAC().fetchNextSuspiciousActivityParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.SUSPICIOUS_ACTIVITY, 'A', 'A', 1);
	}

	/**
	 * Test fetching of next suspicious activity id when a previous one already exists on the db.
	 */
	@Test
	public void testFetchNextSuspiciousActivityParticipantIdSecondRequest()
	{
		InternalResultsResponse<SuspiciousActivityParticipantId> response =
				getParticipantIdDAC().fetchNextSuspiciousActivityParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.SUSPICIOUS_ACTIVITY, 'A', 'A', 1);
		response = getParticipantIdDAC().fetchNextSuspiciousActivityParticipantId();
		checkResults(response, response.getFirstResult(), ParticipantIdTypeEnum.SUSPICIOUS_ACTIVITY, 'A', 'A', 2);
	}
}
