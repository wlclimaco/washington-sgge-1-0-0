package com.prosperitasglobal.sendsolv.unittest.util;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.sendsolv.model.BtsConfirmationNumber;
import com.prosperitasglobal.sendsolv.model.ContactParticipantId;
import com.prosperitasglobal.sendsolv.model.LocationParticipantId;
import com.prosperitasglobal.sendsolv.model.MemberParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchParticipantId;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantId;
import com.prosperitasglobal.sendsolv.model.OrganizationParticipantId;
import com.prosperitasglobal.sendsolv.model.ParticipantId;
import com.prosperitasglobal.sendsolv.model.ParticipantIdTypeEnum;
import com.prosperitasglobal.sendsolv.model.RecipientParticipantId;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.response.InternalResponse;

/**
 * CommonRoutinesTest junit.
 *
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/unittest-datasource-txn-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/sendsolv-dac-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
@ActiveProfiles("sqlserver")
public class CommonRoutinesTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	protected static final Logger LOG = LoggerFactory.getLogger(CommonRoutinesTest.class);

	/** Constant int 5. */
	private static final int CHECK_DIGIT_5 = 5;

	/** Constant int 7. */
	private static final int CHECK_DIGIT_7 = 7;

	/** Constant int 999999. */
	private static final int SEQUENCE_999999 = 999999;

	/** Constant int 70. */
	private static final int SEQUENCE_70 = 70;

	/** Constant int 71. */
	private static final int SEQUENCE_71 = 71;

	/** Constant pgsi prefix number. */
	private static final int PGSI_PREFIX_NUMBER = 1685;

	/** Constant int 5. */
	private static final int FIVE = 5;

	/** Constant int 4. */
	private static final int FOUR = 4;

	/** Constant int 99999. */
	private static final int MAX_ALLOWED_INT = 99999;

	/**
	 * Common routine to make sure the participant id has been incremented to the correct value.
	 *
	 * @param response The response after the increment.
	 * @param participantId The participant id that was incremented.
	 * @param upperId The expected upperId after the increment.
	 * @param lowerId The expected lowerId after the increment.
	 * @param sequence The expected sequence after the increment.
	 */
	private static void checkParticipantId(InternalResponse response, ParticipantId participantId, char upperId,
			char lowerId, int sequence)
	{
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isInError());
		Assert.assertEquals(participantId.getUpperId(), new Character(upperId));
		Assert.assertEquals(participantId.getLowerId(), new Character(lowerId));
		Assert.assertEquals(participantId.getSequence(), new Integer(sequence));
	}

	/**
	 * Common routine to make sure the BTS confirmation number has been incremented to the correct value.
	 *
	 * @param response The response after the increment.
	 * @param confirmationNumber The generated BTS confirmation number.
	 * @param checkDigit The expected check digit.
	 * @param sequence The expected sequence after the increment.
	 * @param pgsiAssignedNumber The pgsi assigned number.
	 */
	private static void checkBtsConfirmationNumber(InternalResponse response, BtsConfirmationNumber confirmationNumber,
			int pgsiAssignedNumber, int sequence, int checkDigit)
	{
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.isInError());
		Assert.assertEquals(confirmationNumber.getSequence(), new Integer(sequence));
		Assert.assertEquals(confirmationNumber.getPrefixNumber(), new Integer(pgsiAssignedNumber));
		Assert.assertEquals(confirmationNumber.getCheckDigit(), new Integer(checkDigit));
	}

	/**
	 * Test the formatting of the money transfer BTS confirmation number.
	 * <p>
	 * The number is formatted as follows (length=11): ####%%%%%%@
	 * <p>
	 * <li>#### - The PGSi prefix assigned by BTS</li><br>
	 * <li>%%%%%% - The sequence.</li><br>
	 * <li>@ - The check digit.</li><br>
	 * <br>
	 * Example: 16850000012
	 */
	@Test
	public void testFormatBtsConfirmationNumber()
	{
		BtsConfirmationNumber confirmationNumber = new BtsConfirmationNumber(PGSI_PREFIX_NUMBER, 1);
		confirmationNumber.setCheckDigit(2);
		String stringConfirmationNumber = CommonRoutines.formatBtsConfirmationNumber(confirmationNumber);
		Assert.assertEquals("Incorrect confirmation number formatted!", "16850000012", stringConfirmationNumber);
		LOG.debug("BTS Confirmation Number: " + CommonRoutines.formatBtsConfirmationNumber(confirmationNumber));
	}

	/**
	 * Test the formatting of the participant id for a location. The id for location will start with a LOC, followed by
	 * the upperId, lowerId, and the sequence (length of sequence equal to the maximumSequence).
	 * Example: LAA00000
	 */
	@Test
	public void testFormatParticipantIdContact()
	{
		ContactParticipantId contactParticipantId = new ContactParticipantId('B', 'C', 1, MAX_ALLOWED_INT);
		String participantId = CommonRoutines.formatParticipantId(contactParticipantId);
		Assert.assertEquals(ParticipantIdTypeEnum.CONTACT.getIdentifierCharacters() + "BC00001", participantId);
		LOG.debug("Contact ID: " + CommonRoutines.formatParticipantId(contactParticipantId));
	}

	/**
	 * Test the formatting of the participant id for a location. The id for location will start with a LOC, followed by
	 * the upperId, lowerId, and the sequence (length of sequence equal to the maximumSequence).
	 * Example: LAA00000
	 */
	@Test
	public void testFormatParticipantIdLocation()
	{
		LocationParticipantId locationParticipantId = new LocationParticipantId('A', 'C', 0, MAX_ALLOWED_INT);
		String participantId = CommonRoutines.formatParticipantId(locationParticipantId);
		Assert.assertEquals(ParticipantIdTypeEnum.LOCATION.getIdentifierCharacters() + "AC00000", participantId);
		LOG.debug("Location ID: " + CommonRoutines.formatParticipantId(locationParticipantId));
	}

	/**
	 * Test the formatting of the participant id for a money transfer. The id for money transfer will start with an S,
	 * followed by the upperId, lowerId, and the sequence (length of sequence equal to the maximumSequence).
	 * Example: SAA00000
	 */
	@Test
	public void testFormatParticipantIdMoneyTransfer()
	{
		MoneyTransferParticipantId moneyTransferParticipantId =
				new MoneyTransferParticipantId('A', 'Z', 1, MAX_ALLOWED_INT);
		String participantId = CommonRoutines.formatParticipantId(moneyTransferParticipantId);
		Assert.assertEquals(ParticipantIdTypeEnum.MONEY_TRANSFER.getIdentifierCharacters() + "AZ00001",
				participantId);
		LOG.debug("MoneyTransfer ID: " + CommonRoutines.formatParticipantId(moneyTransferParticipantId));
	}

	/**
	 * Test the formatting of the participant id for a money transfer batch. The id for batch will start with an MTB,
	 * followed by the upperId, lowerId, and the sequence (length of sequence equal to the maximumSequence).
	 * Example: BAA00000
	 */
	@Test
	public void testFormatParticipantIdMoneyTransferBatch()
	{
		MoneyTransferBatchParticipantId moneyTransferBatchParticipantId =
				new MoneyTransferBatchParticipantId('A', 'Z', 0, MAX_ALLOWED_INT);
		String participantId = CommonRoutines.formatParticipantId(moneyTransferBatchParticipantId);
		Assert.assertEquals(ParticipantIdTypeEnum.MONEY_TRANSFER_BATCH.getIdentifierCharacters() + "AZ00000",
				participantId);
		LOG.debug("MoneyTransferBatch ID: " + CommonRoutines.formatParticipantId(moneyTransferBatchParticipantId));
	}

	/**
	 * Test the formatting of the participant id for a member. The id for member will start with an M, followed by
	 * the upperId, lowerId, and the sequence (length of sequence equal to the maximumSequence).
	 * Example: MAA00000
	 */
	@Test
	public void testFormatParticipantIdMember()
	{
		MemberParticipantId memberParticipantId = new MemberParticipantId('A', 'A', 0, MAX_ALLOWED_INT);
		String participantId = CommonRoutines.formatParticipantId(memberParticipantId);
		Assert.assertEquals(ParticipantIdTypeEnum.MEMBER.getIdentifierCharacters() + "AA00000", participantId);
		LOG.debug("Member ID: " + CommonRoutines.formatParticipantId(memberParticipantId));
	}

	/**
	 * Test the formatting of the participant id for an organization. The id for organization will start with a ORG,
	 * followed by
	 * the upperId, lowerId, and the sequence (length of sequence equal to the maximumSequence).
	 * Example: OAA00000
	 */
	@Test
	public void testFormatParticipantIdOrganization()
	{
		OrganizationParticipantId organizationParticipantId =
				new OrganizationParticipantId('A', 'D', 0, MAX_ALLOWED_INT);
		String participantId = CommonRoutines.formatParticipantId(organizationParticipantId);
		Assert.assertEquals(ParticipantIdTypeEnum.ORGANIZATION.getIdentifierCharacters() + "AD00000", participantId);
		LOG.debug("Organization ID: " + CommonRoutines.formatParticipantId(organizationParticipantId));
	}

	/**
	 * Test the formatting of the participant id for a recipient. The id for recipient will start with an R, followed by
	 * the upperId, lowerId, and the sequence (length of sequence equal to the maximumSequence).
	 * Example: RFC99999
	 */
	@Test
	public void testFormatParticipantIdRecipient()
	{
		RecipientParticipantId recipientParticipantId =
				new RecipientParticipantId('F', 'C', MAX_ALLOWED_INT, MAX_ALLOWED_INT);
		String participantId = CommonRoutines.formatParticipantId(recipientParticipantId);
		Assert.assertEquals(ParticipantIdTypeEnum.RECIPIENT.getIdentifierCharacters() + "FC99999", participantId);
		LOG.debug("Recipient ID: " + CommonRoutines.formatParticipantId(recipientParticipantId));
	}

	/**
	 * Test the generation of the pin number.
	 */
	@Test
	public void testGeneratePinIdentifier()
	{
		// 4 digits
		String newId = CommonRoutines.generatePinIdentifier(FOUR);
		Assert.assertNotNull("", newId);
		Assert.assertEquals("", FOUR, newId.length());

		// 5 digits
		newId = CommonRoutines.generatePinIdentifier(FIVE);
		Assert.assertNotNull("", newId);
		Assert.assertEquals("", FIVE, newId.length());
	}

	/**
	 * Test the incrementing of the participant id when the sequence has hit the max. The lowerId should incremented to
	 * a B and the sequence set to 1.
	 * Example: A,A,99999 incremented to A,B,00001
	 */
	@Test
	public void testIncrementMemberParticipantIdMaxSequence()
	{
		MemberParticipantId memberParticipantId = new MemberParticipantId('A', 'A', MAX_ALLOWED_INT, MAX_ALLOWED_INT);
		LOG.debug("Member ID Before Max Sequence: " + CommonRoutines.formatParticipantId(memberParticipantId));
		InternalResponse response = new InternalResponse();
		CommonRoutines.incrementParticipantId(memberParticipantId, response);
		checkParticipantId(response, memberParticipantId, 'A', 'B', 1);
		LOG.debug("Member ID After Max Sequence: " + CommonRoutines.formatParticipantId(memberParticipantId));
	}

	/**
	 * Test the incrementing of the participant id when the sequence has hit the max and the lowerId is a Z. The upperId
	 * should be incremented to a B, the lowerId should set to an A and the sequence set to 1.
	 * Example: A,Z,99999 incremented to B,A,00001
	 */
	@Test
	public void testIncrementMemberParticipantIdMaxSequenceMaxLowerId()
	{
		MemberParticipantId memberParticipantId = new MemberParticipantId('A', 'Z', MAX_ALLOWED_INT, MAX_ALLOWED_INT);
		LOG.debug("Member ID Before Max Sequence and Max Lower ID: "
				+ CommonRoutines.formatParticipantId(memberParticipantId));
		InternalResponse response = new InternalResponse();
		CommonRoutines.incrementParticipantId(memberParticipantId, response);
		checkParticipantId(response, memberParticipantId, 'B', 'A', 1);
		LOG.debug("Member ID After Max Sequence and Max Lower ID: "
				+ CommonRoutines.formatParticipantId(memberParticipantId));
	}

	/**
	 * Test the incrementing of the participant id when the sequence has hit the max and the lowerId is a Z and the
	 * upperId is a Z. We should get an error because in this case, we are out of values to increment. We have hit
	 * the max and a system change needs to happen.
	 * Example: Z,Z,99999 isn't incremented and an error is returned.
	 */
	@Test
	public void testIncrementMemberParticipantIdMaxSequenceMaxLowerIdMaxUpperId()
	{
		MemberParticipantId memberParticipantId = new MemberParticipantId('Z', 'Z', MAX_ALLOWED_INT, MAX_ALLOWED_INT);
		LOG.debug("Member ID Before Max Sequence, Max Lower ID, Max Upper ID: "
				+ CommonRoutines.formatParticipantId(memberParticipantId));
		InternalResponse response = new InternalResponse();
		CommonRoutines.incrementParticipantId(memberParticipantId, response);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.isInError());
	}

	/**
	 * Test the incrementing of the participant id when the sequence has not hit the max. The sequence should be
	 * incremented.
	 * Example: A,A,00001 incremented to A,A,00002
	 */
	@Test
	public void testIncrementMemberParticipantIdNoMax()
	{
		MemberParticipantId memberParticipantId = new MemberParticipantId('A', 'A', 1, MAX_ALLOWED_INT);
		LOG.debug("Member ID Before Increment: " + CommonRoutines.formatParticipantId(memberParticipantId));
		InternalResponse response = new InternalResponse();
		CommonRoutines.incrementParticipantId(memberParticipantId, response);
		checkParticipantId(response, memberParticipantId, 'A', 'A', 2);
		LOG.debug("Member ID After Increment: " + CommonRoutines.formatParticipantId(memberParticipantId));
	}

	/**
	 * Test the auto generation of the confirmation number, previous sequence is 0.
	 */
	@Test
	public void testGenerateConfirmationNumberPreviousSequence0()
	{
		InternalResponse response = new InternalResponse();
		BtsConfirmationNumber confirmationNumber = new BtsConfirmationNumber(PGSI_PREFIX_NUMBER, 0);
		confirmationNumber.setCheckDigit(1);
		LOG.debug("Confirmation Sequence 0: " + CommonRoutines.formatBtsConfirmationNumber(confirmationNumber));
		BtsConfirmationNumber newConfirmationNumber =
				CommonRoutines.generateConfirmationNumber(confirmationNumber, response);
		LOG.debug("Confirmation Sequence 0 Incremented: "
				+ CommonRoutines.formatBtsConfirmationNumber(newConfirmationNumber));
		checkBtsConfirmationNumber(response, newConfirmationNumber, PGSI_PREFIX_NUMBER, 1, CHECK_DIGIT_7);
	}

	/**
	 * Test the auto generation of the confirmation number, previous sequence is 1.
	 */
	@Test
	public void testGenerateConfirmationNumberPreviousSequence1()
	{
		InternalResponse response = new InternalResponse();
		BtsConfirmationNumber confirmationNumber = new BtsConfirmationNumber(PGSI_PREFIX_NUMBER, 1);
		confirmationNumber.setCheckDigit(1);
		LOG.debug("Confirmation Sequence 1: " + CommonRoutines.formatBtsConfirmationNumber(confirmationNumber));
		BtsConfirmationNumber newConfirmationNumber =
				CommonRoutines.generateConfirmationNumber(confirmationNumber, response);
		LOG.debug("Confirmation Sequence 1 Incremented: "
				+ CommonRoutines.formatBtsConfirmationNumber(newConfirmationNumber));
		checkBtsConfirmationNumber(response, newConfirmationNumber, PGSI_PREFIX_NUMBER, 2, CHECK_DIGIT_5);
	}

	/**
	 * Test the auto generation of the confirmation number, previous sequence is 9.
	 */
	@Test
	public void testGenerateConfirmationNumberPreviousSequence9()
	{
		InternalResponse response = new InternalResponse();
		BtsConfirmationNumber confirmationNumber = new BtsConfirmationNumber(PGSI_PREFIX_NUMBER, SEQUENCE_70);
		confirmationNumber.setCheckDigit(1);
		LOG.debug("Confirmation Sequence 9: " + CommonRoutines.formatBtsConfirmationNumber(confirmationNumber));
		BtsConfirmationNumber newConfirmationNumber =
				CommonRoutines.generateConfirmationNumber(confirmationNumber, response);
		LOG.debug("Confirmation Sequence 9 Incremented: "
				+ CommonRoutines.formatBtsConfirmationNumber(newConfirmationNumber));
		checkBtsConfirmationNumber(response, newConfirmationNumber, PGSI_PREFIX_NUMBER, SEQUENCE_71, 0);
	}

	/**
	 * Test the auto generation of the confirmation number, previous sequence is Maxed.
	 */
	@Test
	public void testGenerateConfirmationNumberPreviousSequenceMax()
	{
		InternalResponse response = new InternalResponse();
		BtsConfirmationNumber confirmationNumber =
				new BtsConfirmationNumber(PGSI_PREFIX_NUMBER, SEQUENCE_999999);
		confirmationNumber.setCheckDigit(1);
		LOG.debug("Confirmation Sequence Max: " + CommonRoutines.formatBtsConfirmationNumber(confirmationNumber));
		CommonRoutines.generateConfirmationNumber(confirmationNumber, response);
		Assert.assertEquals("Status should be SystemError", InternalResponse.Status.SystemError, response.getStatus());
	}
}
