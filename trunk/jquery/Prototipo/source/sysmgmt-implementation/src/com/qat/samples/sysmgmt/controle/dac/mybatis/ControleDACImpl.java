package com.qat.samples.sysmgmt.controle.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.sysmgmt.controle.dac.IControleDAC;
import com.qat.samples.sysmgmt.controle.model.Controle;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Class ControleDACImpl. (Data Access Component - DAC)
 */
public class ControleDACImpl extends SqlSessionDaoSupport implements IControleDAC
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "ControleMap.";

	/** The Constant STMT_FETCH. */
	private static final String STMT_FETCH = NAMESPACE + "fetchAllControles";

	/** The Constant STMT_FETCH_ALL. */
	private static final String STMT_FETCH_ALL = NAMESPACE + "fetchAllAcessos";

	/** The Constant STMT_FETCH_COUNT. */
	private static final String STMT_FETCH_COUNT = NAMESPACE + "fetchControleByPage";

	/** The Constant STMT_FETCH_ALL_REQUEST. */
	private static final String STMT_FETCH_ALL_REQUEST = NAMESPACE + "fetchControleByAction";

	@Override
	public List<Controle> fetchAllControles(ControleInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL, request);
	}

	@Override
	public List<ControleAcess> fetchAllAcessos(AcessosInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL, request);
	}

	@Override
	public List<Controle> fetchControleByPage(ControleInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL, request);
	}

	@Override
	public List<Controle> fetchControlesByRequest(ControleInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL, request);
	}

	@Override
	public List<Controle> fetchControleByAction(ControleInquiryRequest request)
	{
		return QATMyBatisDacHelper.doQueryForList(getSqlSession(), STMT_FETCH_ALL, request);
	}
}
