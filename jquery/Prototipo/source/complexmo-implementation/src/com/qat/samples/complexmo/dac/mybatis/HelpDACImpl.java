package com.qat.samples.complexmo.dac.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.complexmo.dac.IHelpDAC;
import com.qat.samples.complexmo.model.Help;
import com.qat.samples.complexmo.model.request.HelpInquiryRequest;

/**
 * The Class HelpDACImpl.
 */
public class HelpDACImpl extends SqlSessionDaoSupport implements IHelpDAC
{
	private static final String NAMESPACE = "HelpMap.";
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllHelp";
	private static final String STMT_FETCH_ALL_PAGE = NAMESPACE + "fetchHelpByPage";

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IHelpDAC#fetchAllHelp()
	 */
	@Override
	public List<Help> fetchAllHelp()
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IHelpDAC#fetchHelpByPage(com.qat.samples.complexmo.model.request.
	 * HelpInquiryRequest)
	 */
	@Override
	public List<Help> fetchHelpByPage(HelpInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL_PAGE, request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IHelpDAC#fetchAllHelp(int, int)
	 */
	@Override
	public List<Help> fetchAllHelp(int startRow, int max)
	{
		// Don't use this mybatis method where you pass in the RowBouonds (startRow and max count) to return.
		// It's only here for purposes of showing how inefficient it is.
		// Use the fetch-by-page technique instead.
		return getSqlSession().selectList(STMT_FETCH_ALL, new RowBounds(startRow, max));
	}
}
