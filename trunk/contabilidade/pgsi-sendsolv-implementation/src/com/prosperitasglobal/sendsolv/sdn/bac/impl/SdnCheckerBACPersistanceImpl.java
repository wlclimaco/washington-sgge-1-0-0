package com.prosperitasglobal.sendsolv.sdn.bac.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.sdn.dac.ISdnCheckerDAC;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class SdnCheckerBACPersistanceImpl implements the persistence logic for the BAC. It was separated so the main BAC
 * can be used without any persistence.
 */
public class SdnCheckerBACPersistanceImpl extends SdnCheckerBACImpl
{
	/*
	 * 3-Gravar uid quando criar no banco
	 * 4-Usar uid para comparar os xml ExtendedSdnEntry e sdn_match_record
	 */

	private ISdnCheckerDAC sdnCheckerDAC;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SdnCheckerBACPersistanceImpl.class);

	@Override
	protected void saveSdnFieldsToDatabase(
			InternalResultsResponse<SdnStatusHistory> response, SdnMatch request)
	{

		InternalResponse internalResponse = new InternalResponse();

		SdnStatusHistory currentSdnStatusHistory = null;
		SdnStatusHistory newSdnStatusHistory = response.getFirstResult();

		// Verify if the user already has history and decide what to do
		InternalResultsResponse<SdnStatusHistory> currentSdnStatusHistoryList =
				getSdnCheckerDAC().fetchCurrentSdnStatusHistory(request.getMatchType(), request.getParentKey());

		// If error is not NoRowsFound, get out
		if (currentSdnStatusHistoryList.isInError()
				&& !Status.NoRowsFoundError.equals(currentSdnStatusHistoryList.getStatus()))
		{
			// get out
			response.merge(currentSdnStatusHistoryList);
			return;
		}
		else
		{
			// Retrieve current history
			if (currentSdnStatusHistoryList.getResultsList().size() > 0)
			{
				currentSdnStatusHistory = currentSdnStatusHistoryList.getFirstResult();
			}
		}

		if (ValidationUtil.isNullOrEmpty(newSdnStatusHistory.getSdnMatchRecordList()))
		{
			// Matches do not exist
			if (ValidationUtil.isNull(currentSdnStatusHistory))
			{
				// No Matches/No History
				// Do nothing
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.NEUTRAL);
			}
			else
			{
				// No Matches/History
				internalResponse = processNoMatchWithHistory(newSdnStatusHistory, currentSdnStatusHistory, request);
			}
		}
		else
		{
			// Matches exist
			if (ValidationUtil.isNull(currentSdnStatusHistory))
			{
				// Matches/No History --> create history
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_INVESTIGATION);
				internalResponse = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory, request);
			}
			else
			{
				// Matches/History
				internalResponse = processMatchWithHistory(newSdnStatusHistory, currentSdnStatusHistory, request);
			}
		}

		if (internalResponse.isInError())
		{
			response.merge(internalResponse);
		}
	}

	@Override
	public InternalResultsResponse<SdnStatusHistory> fetchCurrentSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		InternalResultsResponse<SdnStatusHistory> response =
				getSdnCheckerDAC().fetchCurrentSdnStatusHistory(request.getMatchType(),
						request.getSdnStatusHistory().getParentKey());

		prepareHistoryResponse(response);

		return response;
	}

	@Override
	public InternalResultsResponse<SdnStatusHistory> fetchFullSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		InternalResultsResponse<SdnStatusHistory> response =
				getSdnCheckerDAC().fetchFullSdnStatusHistory(request.getMatchType(),
						request.getSdnStatusHistory().getParentKey());

		prepareHistoryResponse(response);

		return response;
	}

	@Override
	public InternalResponse updateSdnStatusHistory(SdnStatusHistoryRequest request)
	{
		InternalResponse response = new InternalResponse();

		// The new one is the one passed in, but make sure to null the parent
		SdnStatusHistory newSdnStatusHistory = request.getSdnStatusHistory();
		newSdnStatusHistory.setParentSdnStatusHistoryId(null);

		// The old one is the current one read from DB
		InternalResultsResponse<SdnStatusHistory> internalResultsResponse =
				getSdnCheckerDAC().fetchCurrentSdnStatusHistory(request.getMatchType(),
						request.getSdnStatusHistory().getParentKey());

		if (internalResultsResponse.isInError())
		{
			response.merge(internalResultsResponse);
		}
		else
		{
			SdnStatusHistory currentSdnStatusHistory = internalResultsResponse.getFirstResult();

			// The sdnMatch is based on matchType and parentKey, which are the same for both.
			SdnMatch sdnMatch = new SdnMatch();
			sdnMatch.setMatchType(request.getMatchType());
			sdnMatch.setParentKey(request.getSdnStatusHistory().getParentKey());

			response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory,
					sdnMatch);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<SdnHistory> fetchSdnStatusHistoryByRequest(
			SdnStatusHistoryInquiryRequest request)
	{
		return getSdnCheckerDAC().fetchSdnStatusHistoryByRequest(request);
	}

	/**
	 * Prepare history response. Notice that if no history was found, it creates a dummy one just to indicate the
	 * neutral status.
	 *
	 * @param response the response
	 */
	private void prepareHistoryResponse(InternalResultsResponse<SdnStatusHistory> response)
	{
		if (response.isInError())
		{
			// If no history found, create a dummy History with Neutral Status
			if (Status.NoRowsFoundError.equals(response.getStatus()))
			{
				SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
				sdnStatusHistory.setSdnStatus(SDNStatusEnum.NEUTRAL);

				response.getResultsList().add(sdnStatusHistory);
				response.setStatus(Status.OperationSuccess);
			}
		}
	}

	/**
	 * Process the situation when there was NO MATCH and HISTORY.
	 *
	 * @param newSdnStatusHistory the new sdn status history
	 * @param currentSdnStatusHistory the current sdn status history
	 * @param request the request
	 * @return the internal response
	 */
	private InternalResponse processNoMatchWithHistory(SdnStatusHistory newSdnStatusHistory,
			SdnStatusHistory currentSdnStatusHistory, SdnMatch request)
	{
		InternalResponse response = new InternalResponse();

		// No Matches/History
		SDNStatusEnum currentStatus = currentSdnStatusHistory.getSdnStatus();

		switch (currentStatus)
		{
			case NEUTRAL:
				// Do nothing
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.NEUTRAL);
				break;

			case PENDING_INVESTIGATION:
				// Create Pending PGSI Neutral & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_NEUTRAL);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory, request);
				break;

			case PENDING_FEDERAL_INVESTIGATION:
				// Create Pending PGSI Neutral & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_NEUTRAL);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory, request);
				break;

			case POSITIVE:
				// Create Pending PGSI Neutral & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_NEUTRAL);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory, request);
				break;

			case FALSE_POSITIVE:
				// Create Neutral & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.NEUTRAL);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory, request);
				break;

			case PENDING_NEUTRAL:
				// Do nothing
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_NEUTRAL);
				break;

			default:
				break;

		}

		return response;
	}

	/**
	 * Process the situation when there was a MATCH and HISTORY.
	 *
	 * @param newSdnStatusHistory the new sdn status history
	 * @param currentSdnStatusHistory the current sdn status history
	 * @param request the request
	 * @return the internal response
	 */
	private InternalResponse processMatchWithHistory(SdnStatusHistory newSdnStatusHistory,
			SdnStatusHistory currentSdnStatusHistory, SdnMatch request)
	{
		InternalResponse response = new InternalResponse();

		// Matches/History
		SDNStatusEnum currentStatus = currentSdnStatusHistory.getSdnStatus();

		switch (currentStatus)
		{
			case NEUTRAL:

				// Create Pending PGSI investigation & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_INVESTIGATION);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory,
						request);
				break;

			case PENDING_INVESTIGATION:

				// Create Pending PGSI investigation & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_INVESTIGATION);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory,
						request);
				break;

			case PENDING_FEDERAL_INVESTIGATION:
			{
				/*
				 * Verify if matches are equal.
				 * If yes, do nothing.
				 * If not, create Pending PGSI investigation & link.
				 */
				if (matchesAreEqual(newSdnStatusHistory, currentSdnStatusHistory))
				{
					// do nothing.
					newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_FEDERAL_INVESTIGATION);
				}
				else
				{
					// Create Pending PGSI investigation & link
					newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_INVESTIGATION);
					response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory,
							request);
				}
				break;
			}
			case POSITIVE:

				/*
				 * Verify if matches are equal.
				 * If yes, do nothing.
				 * If not, create Pending PGSI investigation & link.
				 */
				if (matchesAreEqual(newSdnStatusHistory, currentSdnStatusHistory))
				{
					// do nothing.
					newSdnStatusHistory.setSdnStatus(SDNStatusEnum.POSITIVE);
				}
				else
				{
					// Create Pending PGSI investigation & link
					newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_INVESTIGATION);
					response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory,
							request);
				}
				break;

			case FALSE_POSITIVE:

				// Create Pending PGSI investigation & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_INVESTIGATION);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory,
						request);
				break;

			case PENDING_NEUTRAL:

				// Create Pending PGSI investigation & link
				newSdnStatusHistory.setSdnStatus(SDNStatusEnum.PENDING_INVESTIGATION);
				response = createSdnStatusHistory(newSdnStatusHistory, currentSdnStatusHistory,
						request);
				break;

			default:

				break;

		}

		return response;
	}

	/**
	 * Verify if two {@link SdnStatusHistory} are equal by checking their corresponding Xml entries as String
	 *
	 * @param newSdnStatusHistory the new sdn status history
	 * @param currentSdnStatusHistory the current sdn status history
	 * @return true, if successful
	 */
	private boolean matchesAreEqual(SdnStatusHistory newSdnStatusHistory, SdnStatusHistory currentSdnStatusHistory)
	{
		for (SdnMatchRecord newSdnMatchRecord : newSdnStatusHistory.getSdnMatchRecordList())
		{
			boolean found = false;
			for (SdnMatchRecord currentSdnMatchRecord : currentSdnStatusHistory.getSdnMatchRecordList())
			{
				if (newSdnMatchRecord.getXmlRecord().equalsIgnoreCase(currentSdnMatchRecord.getXmlRecord()))
				{
					found = true;
				}
			}

			if (!found)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Creates the {@link SdnStatusHistory} in the database. if there was one {@link SdnStatusHistory} before, it makes
	 * the newly added record the 'parent' of the old one by updating ParentSdnStatusHistoryId attribute.
	 *
	 * @param newSdnStatusHistory the new sdn status history
	 * @param currentSdnStatusHistory the current sdn status history
	 * @param request the request
	 * @return the internal response
	 */
	private InternalResponse createSdnStatusHistory(SdnStatusHistory newSdnStatusHistory,
			SdnStatusHistory currentSdnStatusHistory, SdnMatch request)
	{
		InternalResponse response = getSdnCheckerDAC().insertSdnStatusHistory(newSdnStatusHistory, request);

		// If there is a previous record, link it to the new one
		if (!response.isInError() && !ValidationUtil.isNull(currentSdnStatusHistory))
		{
			currentSdnStatusHistory.setParentSdnStatusHistoryId(newSdnStatusHistory.getId());
			response = getSdnCheckerDAC().updateSdnHistory(currentSdnStatusHistory);
		}

		return response;
	}

	public ISdnCheckerDAC getSdnCheckerDAC()
	{
		return sdnCheckerDAC;
	}

	public void setSdnCheckerDAC(ISdnCheckerDAC sdnCheckerDAC)
	{
		this.sdnCheckerDAC = sdnCheckerDAC;
	}
}
