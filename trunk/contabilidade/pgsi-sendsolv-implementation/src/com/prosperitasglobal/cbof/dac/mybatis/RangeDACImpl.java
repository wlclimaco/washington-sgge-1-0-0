package com.prosperitasglobal.cbof.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.IRangeDAC;
import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.cbof.model.request.RangeRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class RangeDACImpl.
 */
public class RangeDACImpl extends SqlSessionDaoSupport implements IRangeDAC
{

	/** The Constant RANGE_NAMESPACE. */
	private static final String RANGE_NAMESPACE = "rangeMap.";

	/** The Constant RANGE_NAMESPACE_STMT_FETCH_RANGE_BY_ID. */
	private static final String RANGE_NAMESPACE_STMT_FETCH_RANGE_BY_ID = RANGE_NAMESPACE + "fetchRangeById";

	/** The Constant RANGE_NAMESPACE_STMT_FETCH_ALL_RANGES. */
	private static final String RANGE_NAMESPACE_STMT_FETCH_ALL_RANGES = RANGE_NAMESPACE + "fetchAllRanges";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IRangeDAC#fetchAllRanges(com.prosperitasglobal.cbof.model.request.RangeRequest)
	 */
	@Override
	public InternalResultsResponse<Range> fetchAllRanges(RangeRequest request)
	{
		InternalResultsResponse<Range> response = new InternalResultsResponse<Range>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), RANGE_NAMESPACE_STMT_FETCH_ALL_RANGES,
				request, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IRangeDAC#fetchRangeById(com.prosperitasglobal.cbof.model.request.RangeRequest)
	 */
	@Override
	public InternalResultsResponse<Range> fetchRangeById(RangeRequest request)
	{
		InternalResultsResponse<Range> response = new InternalResultsResponse<Range>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), RANGE_NAMESPACE_STMT_FETCH_RANGE_BY_ID,
				request.getId(), response);

		return response;
	}

}
