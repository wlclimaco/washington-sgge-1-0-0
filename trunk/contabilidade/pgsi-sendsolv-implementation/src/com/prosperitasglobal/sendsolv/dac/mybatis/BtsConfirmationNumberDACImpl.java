package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IBtsConfirmationNumberDAC;
import com.prosperitasglobal.sendsolv.model.BtsConfirmationNumber;
import com.prosperitasglobal.sendsolv.util.CommonRoutines;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * Class is this link between the SendSolv system and the datastore which contains the last BTS confirmation number for
 * a specific prefix number. Its sole purpose is to define the methods needed for getting the next available BTS
 * confirmation number for the prefix number.
 */
public class BtsConfirmationNumberDACImpl extends SqlSessionDaoSupport implements IBtsConfirmationNumberDAC
{
	/*
	 * BTS Confirmation Number SQL statements.
	 */
	private static final String BTS_CONFIRMATION_NUMBER_NAMESPACE = "BtsConfirmationNumberMap.";
	private static final String BTS_CONFIRMATION_NUMBER_STMT_INSERT = BTS_CONFIRMATION_NUMBER_NAMESPACE
			+ "insertBtsConfirmationNumber";
	private static final String BTS_CONFIRMATION_NUMBER_STMT_UPDATE = BTS_CONFIRMATION_NUMBER_NAMESPACE
			+ "updateBtsConfirmationNumber";
	private static final String BTS_CONFIRMATION_NUMBER_STMT_FETCH_BY_PREFIX_NUMBER_FOR_UPDATE =
			BTS_CONFIRMATION_NUMBER_NAMESPACE + "fetchBtsConfirmationNumberByPrefixNumberForUpdate";

	/**
	 * Fetch the next available BTS confirmation number for the BTS prefix number in the SendSolv system.
	 *
	 * @param btsPrefixNumber The BTS assigned prefix number.
	 * @return The {@link InternalResultsResponse} containing the next available unique {@link BtsConfirmationNumber}.
	 */
	@Override
	public InternalResultsResponse<BtsConfirmationNumber> fetchNextBtsConfirmationNumber(Integer btsPrefixNumber)
	{
		InternalResultsResponse<BtsConfirmationNumber> response = new InternalResultsResponse<BtsConfirmationNumber>();

		BtsConfirmationNumber confirmationNumber =
				(BtsConfirmationNumber)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						BTS_CONFIRMATION_NUMBER_STMT_FETCH_BY_PREFIX_NUMBER_FOR_UPDATE, btsPrefixNumber);

		// If no confirmation number is found, insert an initial one.
		if (ValidationUtil.isNull(confirmationNumber))
		{
			confirmationNumber =
					CommonRoutines.generateConfirmationNumber(new BtsConfirmationNumber(btsPrefixNumber, 0), response);

			int insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), BTS_CONFIRMATION_NUMBER_STMT_INSERT,
							confirmationNumber, response);

			if (!response.isInError() && (insertCount > 0))
			{
				response.addResult(confirmationNumber);
			}
		}
		// Update the existing one returned from the database.
		else
		{
			confirmationNumber = CommonRoutines.generateConfirmationNumber(confirmationNumber, response);

			// If the next confirmation number was built with no errors, then save it to the database.
			if (!response.isInError())
			{
				int updateCount =
						QATMyBatisDacHelper.doUpdate(getSqlSession(), BTS_CONFIRMATION_NUMBER_STMT_UPDATE,
								confirmationNumber, response);

				if (!response.isInError() && (updateCount > 0))
				{
					response.addResult(confirmationNumber);
				}
			}
		}

		return response;
	}
}
