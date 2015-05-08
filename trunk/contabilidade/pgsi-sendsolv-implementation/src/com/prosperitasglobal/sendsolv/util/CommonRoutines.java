package com.prosperitasglobal.sendsolv.util;

import java.util.Random;

import com.prosperitasglobal.sendsolv.model.BtsConfirmationNumber;
import com.prosperitasglobal.sendsolv.model.ParticipantId;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;

/**
 * The Class CommonRoutines.
 */
public final class CommonRoutines
{
	private static final String SENDSOLV_BASE_PARTICIPANTID_MAXEDOUT_ERROR =
			"sendsolv.base.participantid.maxedout.error";
	private static final int NINE = 9;
	private static final int TEN = 10;
	private static final int TWENTY = 20;

	/**
	 * A private default constructor.
	 */
	private CommonRoutines()
	{
	}

	/**
	 * Format the BTS confirmation number in the format (length=11): ####%%%%%%@:
	 * <p>
	 * <li>#### - The PGSi prefix assigned by BTS</li><br>
	 * <li>%%%%%% - The sequence.</li><br>
	 * <li>@ - The check digit.</li><br>
	 *
	 * @param confirmationNumber The confirmation number generated.
	 * @return The string containing the object formatted in the BTS String format.
	 */
	public static String formatBtsConfirmationNumber(BtsConfirmationNumber confirmationNumber)
	{
		return String.format("%04d", confirmationNumber.getPrefixNumber())
				+ String.format("%06d", confirmationNumber.getSequence())
				+ confirmationNumber.getCheckDigit().toString();
	}

	/**
	 * Format the participant id in the format: zz99999 where zz is the upperId and lowerId, and 99999 is the sequence.
	 *
	 * @param participantId The participant id object to format as a String.
	 * @return The string containing the object formatted in the participant id String format.
	 */
	public static String formatParticipantId(ParticipantId participantId)
	{
		String returnString =
				participantId.getParticipantIdType().getIdentifierCharacters()
						+ Character.toString(participantId.getUpperId());
		returnString = returnString + Character.toString(participantId.getLowerId());
		return returnString
				+ String.format("%0" + participantId.getMaximumSequence().toString().length() + "d",
						participantId.getSequence());
	}

	/**
	 * Increment the current values in the participant id to the next available id. The incrementing follows this flow:
	 *
	 * <li>1. Once the sequence hits the maximum allowed for it, the lowerId is incremented to the next character (i.e A
	 * to a B) and the sequence is set to a 1.</li> <li>2. Once the lowerId hits the maximum allowed (i.e a Z), it is
	 * set back to an A, and the upperId is incremented to the next character (i.e A to B).</li> <li>3. Once the upperId
	 * hits the maximum allowed (i.e a Z), an error is set in the response indicating that all available characters and
	 * numbers have been used up and a system change is needed to continue.</li><br>
	 * <br>
	 *
	 * @param participantId The participant id to increment.
	 * @param response The response, that will contain errors if processing wasn't successful.
	 */
	public static void incrementParticipantId(ParticipantId participantId, InternalResponse response)
	{
		// If the current sequence is less than the maximum, we simply increment it
		if (participantId.getSequence() < participantId.getMaximumSequence())
		{
			participantId.setSequence(participantId.getSequence() + 1);
		}
		else
		{
			// Set sequence back to 1.
			participantId.setSequence(1);

			// If the lower id is not already a Z, we simply increment the value to the next character.
			if (participantId.getLowerId() != 'Z')
			{
				char lowerId = participantId.getLowerId().charValue();
				lowerId++;
				participantId.setLowerId(new Character(lowerId));
			}
			else
			{
				// Set lower id back to an A.
				participantId.setLowerId('A');

				// If the upper id is not already a Z, we simply increment the value to the next character.
				if (participantId.getUpperId() != 'Z')
				{
					char upperId = participantId.getUpperId().charValue();
					upperId++;
					participantId.setUpperId(new Character(upperId));
				}
				else
				{
					// Problem! We are out of values!
					response.addObjectErrorMessage(SENDSOLV_BASE_PARTICIPANTID_MAXEDOUT_ERROR,
							new Object[] {participantId.getParticipantIdType().name()});
					response.setStatus(Status.SystemError);
				}
			}
		}
	}

	/**
	 * Generates a random pin identifier with the size passed in.
	 *
	 * @param size the size
	 * @return the string (pin)
	 */
	public static String generatePinIdentifier(Integer size)
	{
		Random rand = new Random();

		return Integer.valueOf(rand.nextInt(Integer.MAX_VALUE)).toString().substring(0, size);
	}

	/**
	 * Generates a confirmation number according to algorithm provided by BTS
	 *
	 * <li>1. Multiply last digit of prefix by 2.</li><br>
	 * <li>2. Add 1 to last number used and split into digits.[1][2][3][4][5][6]</li><br>
	 * <li>3. Multiply digits [1][3][5] individually by 1 and [2][4][6] by 2. If any individual result > 10, add digits
	 * together.</li><br>
	 * <li>4. Add the individual results, divide by 10 and get remainder R.</li><br>
	 * <li>5. if R > 0, check digit = 10 - R. If R = 0 check digit = 0</li><br>
	 * <br>
	 *
	 * @param confirmationNumber The last confirmation number generated.
	 * @param response The response, that will contain errors if processing wasn't successful.
	 * @return the long
	 */
	public static BtsConfirmationNumber generateConfirmationNumber(BtsConfirmationNumber confirmationNumber,
			InternalResponse response)
	{
		final int maxNumber = 999999;
		Integer nextNumber = confirmationNumber.getSequence() + 1;

		BtsConfirmationNumber nextConfirmationNumber =
				new BtsConfirmationNumber(confirmationNumber.getPrefixNumber(), nextNumber);

		/* If sequence isn't greater than the maximum, calculate the check digit. */
		if (nextConfirmationNumber.getSequence() <= maxNumber)
		{
			// start with weight = 2
			int weight = 2;
			Integer digit = 0;

			// Last digit of prefix * weight
			Integer sum = guaranteeOneDigit((nextConfirmationNumber.getPrefixNumber() % TEN) * weight);

			// cycle through all digits
			while (nextNumber > 0)
			{
				// get digit * weight
				digit = guaranteeOneDigit((nextNumber % TEN) * weight);

				// accumulate
				sum = sum + digit;

				// get next number
				nextNumber = nextNumber / TEN;

				// alternate weight value
				if (weight == 1)
				{
					weight = 2;
				}
				else
				{
					weight = 1;
				}
			}

			// calculate check digit
			if ((sum % TEN) > 0)
			{
				nextConfirmationNumber.setCheckDigit(TEN - (sum % TEN));
			}
			else
			{
				nextConfirmationNumber.setCheckDigit(0);
			}
		}

		else
		{
			// Problem! We are out of values!
			response.addObjectErrorMessage(SENDSOLV_BASE_PARTICIPANTID_MAXEDOUT_ERROR);
			response.setStatus(Status.SystemError);
		}

		return nextConfirmationNumber;
	}

	/**
	 * Given a 2 digit number between 10 and 19, returns sum of digits, else returns the number.
	 *
	 * @param theNumber the one or two digit number
	 * @return the one digit number or original number
	 */
	private static Integer guaranteeOneDigit(Integer theNumber)
	{
		Integer returnNumber = theNumber;
		if ((theNumber > NINE) && (theNumber < TWENTY))
		{
			returnNumber = (theNumber % TEN) + 1;
		}
		return returnNumber;
	}
}
