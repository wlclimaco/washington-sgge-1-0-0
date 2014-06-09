package com.qat.samples.sysmgmt.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.dac.IControleAcessDAC;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Class ControleAcessDACImpl. (Data Access Component - DAC)
 */
public class ControleAcessDACImpl extends SqlSessionDaoSupport implements IControleAcessDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "ControleAcessMap.";

	/** The Constant STMT_INSERT. */
	private static final String STMT_INSERT = NAMESPACE + "insertControleAcess";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchControleAcessById";

	private static final String STMT_FETCH_TYPE = NAMESPACE + "fetchControleAcessByType";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllControleAcesss";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchControleAcessRowCount";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchAllControleAcesssRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.dac.IControleAcessDAC#insertControleAcess(com.qat.samples.sysmgmt.base.model.
	 * ControleAcess)
	 */
	@Override
	public InternalResponse insertControleAcess(ControleAcess controleAcess)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doInsert(getSqlSession(), STMT_INSERT, controleAcess, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.dac.IControleAcessDAC#fetchAllControleAcesss(com.qat.samples.sysmgmt.util.ControleAcess)
	 */
	@Override
	public List<ControleAcess> fetchAllControleAcesss(ControleAcess controleAcess)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL, controleAcess);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.dac.IControleAcessDAC#fetchControleAcessById(com.qat.samples.sysmgmt.util.ControleAcess)
	 */
	@Override
	public ControleAcess fetchControleAcessById(ControleAcess controleAcess)
	{
		return (ControleAcess)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH,
				controleAcess.getControleid());
	}

	@Override
	public ControleAcess fetchControleAcessByType(ControleAcess controleAcess)
	{
		return (ControleAcess)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH_TYPE,
				controleAcess.getControleid());
	}

}
