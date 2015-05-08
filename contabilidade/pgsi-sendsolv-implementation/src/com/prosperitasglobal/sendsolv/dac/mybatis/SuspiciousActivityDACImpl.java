package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class SuspiciousActivityDACImpl.
 */
public class SuspiciousActivityDACImpl extends SqlSessionDaoSupport implements ISuspiciousActivityDAC
{

	/** The Constant SAR_NAMESPACE. */
	private static final String SAR_NAMESPACE = "SuspiciousActivityMap.";

	/** The Constant DELETE_SUSPICIOUS_ACTIVITY. */
	private static final String DELETE_SUSPICIOUS_ACTIVITY = SAR_NAMESPACE + "deleteSuspiciousActivity";

	/** The Constant SUSPICIOUS_ACTIVITY_FETCH_COUNT. */
	private static final String SUSPICIOUS_ACTIVITY_FETCH_COUNT = SAR_NAMESPACE + "fetchSuspiciousActivityRowCount";

	/** The Constant SUSPICIOUS_ACTIVITY_FETCH_BY_ID. */
	private static final String SUSPICIOUS_ACTIVITY_FETCH_BY_ID = SAR_NAMESPACE + "fetchSuspiciousActivityByIdRequest";

	/** The Constant BUSINESS_SUSPICIOUS_ACTIVITY_FETCH_BY_ID. */
	private static final String BUSINESS_SUSPICIOUS_ACTIVITY_FETCH_BY_ID = SAR_NAMESPACE
			+ "fetchBusinessSuspiciousActivityByIdRequest";

	/** The Constant SUSPICIOUS_ACTIVITY_FETCH_ALL_BY_REQUEST. */
	private static final String SUSPICIOUS_ACTIVITY_FETCH_ALL_BY_REQUEST = SAR_NAMESPACE
			+ "fetchSuspiciousActivityByRequest";

	/** The Constant SUSPICIOUS_ACTIVITY_INSERT. */
	private static final String SUSPICIOUS_ACTIVITY_INSERT = SAR_NAMESPACE
			+ "insertSuspiciousActivity";

	/** The Constant SUSPICIOUS_ACTIVITY_INSERT_ASSOCIATIONS. */
	private static final String SUSPICIOUS_ACTIVITY_INSERT_ASSOCIATIONS = SAR_NAMESPACE
			+ "insertSuspiciousActivityAssociations";

	/** The suspicious activity valid sort fields. */
	private Map<String, String> suspiciousActivityValidSortFields;

	/**
	 * @return the suspiciousActivityValidSortFields
	 */
	public Map<String, String> getSuspiciousActivityValidSortFields()
	{
		return suspiciousActivityValidSortFields;
	}

	/**
	 * @param suspiciousActivityValidSortFields the suspiciousActivityValidSortFields to set
	 */
	public void setSuspiciousActivityValidSortFields(Map<String, String> suspiciousActivityValidSortFields)
	{
		this.suspiciousActivityValidSortFields = suspiciousActivityValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC#insertSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertSuspiciousActivity(SarMaintenanceRequest request)
	{

		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		QATMyBatisDacHelper.doInsert(getSqlSession(), SUSPICIOUS_ACTIVITY_INSERT,
				request.getSuspiciousActivity(), response);

		if (response.isInError())
		{
			return response;
		}

		maintainSuspiciousActivityAssociations(request.getSuspiciousActivity(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC#deleteSuspiciousActivity(com.prosperitasglobal.sendsolv
	 * .model.request.SarMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteSuspiciousActivity(SarMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		QATMyBatisDacHelper.doRemove(getSqlSession(), DELETE_SUSPICIOUS_ACTIVITY, request.getSuspiciousActivity(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC#fetchSuspiciousActivityByRequest(com.prosperitasglobal
	 * .sendsolv.model.request.SarInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<SuspiciousActivity> fetchSuspiciousActivityByRequest(SarInquiryRequest request)
	{
		InternalResultsResponse<SuspiciousActivity> response = new InternalResultsResponse<SuspiciousActivity>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getSuspiciousActivityValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, SUSPICIOUS_ACTIVITY_FETCH_COUNT,
				SUSPICIOUS_ACTIVITY_FETCH_ALL_BY_REQUEST, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC#fetchSuspiciousActivityByIdRequest
	 * (com.prosperitasglobal.cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<SuspiciousActivity> fetchSuspiciousActivityByIdRequest(FetchByIdRequest request)
	{
		InternalResultsResponse<SuspiciousActivity> response = new InternalResultsResponse<SuspiciousActivity>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), SUSPICIOUS_ACTIVITY_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC#fetchBusinessSuspiciousActivityByIdRequest(com.
	 * prosperitasglobal.cbof.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<SuspiciousActivity> fetchBusinessSuspiciousActivityByIdRequest(
			FetchByIdRequest request)
	{
		InternalResultsResponse<SuspiciousActivity> response = new InternalResultsResponse<SuspiciousActivity>();

		QATMyBatisDacHelper
				.doQueryForList(getSqlSession(), BUSINESS_SUSPICIOUS_ACTIVITY_FETCH_BY_ID, request, response);
		return response;
	}

	/**
	 * Maintain suspicious activity associations.
	 *
	 * @param suspiciousActivity the suspicious activity
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainSuspiciousActivityAssociations(SuspiciousActivity suspiciousActivity,
			InternalResponse response)
	{
		return QATMyBatisDacHelper.doInsert(getSqlSession(), SUSPICIOUS_ACTIVITY_INSERT_ASSOCIATIONS,
				suspiciousActivity, response);

	}

}
