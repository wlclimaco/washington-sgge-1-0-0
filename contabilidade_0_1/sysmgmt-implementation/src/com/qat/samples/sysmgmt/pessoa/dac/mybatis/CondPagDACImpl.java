package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.condpag.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.TipoPag;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.ICondPagDAC;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class CondPagDACImpl extends SqlSessionDaoSupport implements ICondPagDAC
{
	/** The Constant FORMAPG_NAMESPACE. */
	private static final String FORMAPG_NAMESPACE = "CondPagMap.";

	/** The Constant FORMAPG_STMT_UPDATE. */
	private static final String FORMAPG_STMT_UPDATE = FORMAPG_NAMESPACE + "updateCondPag";

	/** The Constant FORMAPG_STMT_DELETE_PERSON_FORMAPG. */
	private static final String FORMAPG_STMT_DELETE_PERSON_FORMAPG = FORMAPG_NAMESPACE + "deletePersonCondPag";

	private static final String FORMAPG_PESSOA_STMT_UPDATE = FORMAPG_NAMESPACE + "updateCondPagPessoa";

	/** The Constant FORMAPG_STMT_UPDATE_PHONE. */
	private static final String FORMAPG_PESSOA_STMT_INSERT = FORMAPG_NAMESPACE + "insertCondPagPessoa";

	/** The Constant FORMAPG_STMT_DELETE_PERSON_FORMAPG. */
	private static final String FORMAPG_PESSOA_STMT_DELETE = FORMAPG_NAMESPACE + "deleteCondPagByIdPessoa";

	private static final String FORMAPG_STMT_FETCH_BY_ID = FORMAPG_NAMESPACE + "fetchCondPagById";

	/** The Constant FORMAPG_STMT_INSERT. */
	private static final String FORMAPG_STMT_INSERT = FORMAPG_NAMESPACE + "insertCondPag";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String FORMAPG_STMT_FETCH_COUNT = FORMAPG_NAMESPACE + "fetchCondPagRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String FORMAPG_STMT_FETCH_ALL_BY_REQUEST = FORMAPG_NAMESPACE
			+ "fetchAllCondPagByRequest";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CondPagDACImpl.class);

	private static final String TIPOPAG_STMT_INSERT = FORMAPG_NAMESPACE + "insertTipoPag";

	private static final String TIPOPAG_STMT_UPDATE = FORMAPG_NAMESPACE + "updateTipoPag";

	private static final String TIPOPAG_PESSOA_STMT_DELETE = FORMAPG_NAMESPACE + "deleteTipoPagById";

	@Override
	public Integer updateCondPag(CondPag condPag)
	{
		Integer updateCount = 0;
		InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(condPag.getModelAction())
				&& (condPag.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), FORMAPG_STMT_UPDATE, condPag, response);

			if (updateCount == 1)
			{
				condPag.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertCondPag(CondPag condPag)
	{
		Integer insertCount = 0;
		InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();
		// First insert the root contato data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), FORMAPG_STMT_INSERT, condPag, response);

		return insertCount;
	}

	@Override
	public Integer deleteCondPag(CondPag condPag)
	{
		InternalResponse response = new InternalResponse();
		return QATMyBatisDacHelper.doRemove(getSqlSession(), FORMAPG_STMT_DELETE_PERSON_FORMAPG, condPag, response);
	}

	@Override
	public InternalResultsResponse<CondPag> fetchCondPagById(Integer id)
	{
		InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FORMAPG_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	@Override
	public InternalResultsResponse<CondPag> fetchCondPagByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<CondPag> response = new InternalResultsResponse<CondPag>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, FORMAPG_STMT_FETCH_COUNT,
				FORMAPG_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	@Override
	public Integer updateCondPagPessoa(CondPagPessoa condPag)
	{
		Integer updateCount = 0;
		InternalResultsResponse<CondPagPessoa> response = new InternalResultsResponse<CondPagPessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(condPag.getModelAction())
				&& (condPag.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), FORMAPG_PESSOA_STMT_UPDATE, condPag, response);

			if (updateCount == 1)
			{
				condPag.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertCondPagPessoa(CondPagPessoa condPag)
	{
		Integer insertCount = 0;
		InternalResultsResponse<CondPagPessoa> response = new InternalResultsResponse<CondPagPessoa>();
		// First insert the root contato data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), FORMAPG_PESSOA_STMT_INSERT, condPag, response);

		return insertCount;
	}

	@Override
	public Integer deleteCondPagPessoa(CondPagPessoa condPag)
	{
		InternalResponse response = new InternalResponse();
		return QATMyBatisDacHelper.doRemove(getSqlSession(), FORMAPG_PESSOA_STMT_DELETE, condPag, response);
	}

	@Override
	public Integer updateTipoPag(TipoPag tipoPag)
	{
		Integer updateCount = 0;
		InternalResultsResponse<TipoPag> response = new InternalResultsResponse<TipoPag>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(tipoPag.getModelAction())
				&& (tipoPag.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), TIPOPAG_STMT_UPDATE, tipoPag, response);

			if (updateCount == 1)
			{
				tipoPag.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertTipoPag(TipoPag tipoPag)
	{
		Integer insertCount = 0;
		InternalResultsResponse<TipoPag> response = new InternalResultsResponse<TipoPag>();
		// First insert the root contato data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), TIPOPAG_STMT_INSERT, tipoPag, response);

		return insertCount;
	}

	@Override
	public Integer deleteTipoPag(TipoPag tipoPag)
	{
		InternalResponse response = new InternalResponse();
		return QATMyBatisDacHelper.doRemove(getSqlSession(), TIPOPAG_PESSOA_STMT_DELETE, tipoPag, response);
	}

}
