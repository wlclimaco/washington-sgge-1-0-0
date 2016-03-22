package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.FormaPgPessoa;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IFormaPagamentoDAC;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class FormaPgDACImpl extends SqlSessionDaoSupport implements IFormaPagamentoDAC
{
	/** The Constant FORMAPG_NAMESPACE. */
	private static final String FORMAPG_NAMESPACE = "FormaPgMap.";

	/** The Constant FORMAPG_STMT_UPDATE. */
	private static final String FORMAPG_STMT_UPDATE = FORMAPG_NAMESPACE + "updateFormaPg";

	/** The Constant FORMAPG_STMT_DELETE_PERSON_FORMAPG. */
	private static final String FORMAPG_STMT_DELETE_PERSON_FORMAPG = FORMAPG_NAMESPACE + "deletePersonFormaPg";

	private static final String FORMAPG_PESSOA_STMT_UPDATE = FORMAPG_NAMESPACE + "updateFormaPgPessoa";

	/** The Constant FORMAPG_STMT_UPDATE_PHONE. */
	private static final String FORMAPG_PESSOA_STMT_INSERT = FORMAPG_NAMESPACE + "insertFormaPgPessoa";

	/** The Constant FORMAPG_STMT_DELETE_PERSON_FORMAPG. */
	private static final String FORMAPG_PESSOA_STMT_DELETE = FORMAPG_NAMESPACE + "deleteFormaPgByIdPessoa";

	private static final String FORMAPG_STMT_FETCH_BY_ID = FORMAPG_NAMESPACE + "fetchFormaPgById";

	/** The Constant FORMAPG_STMT_INSERT. */
	private static final String FORMAPG_STMT_INSERT = FORMAPG_NAMESPACE + "insertFormaPg";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String FORMAPG_STMT_FETCH_COUNT = FORMAPG_NAMESPACE + "fetchFormaPgRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String FORMAPG_STMT_FETCH_ALL_BY_REQUEST = FORMAPG_NAMESPACE
			+ "fetchAllFormaPgByRequest";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FormaPgDACImpl.class);

	@Override
	public Integer updateFormaPg(FormaPg formaPg, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		response = new InternalResultsResponse<FormaPg>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(formaPg.getModelAction())
				&& (formaPg.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), FORMAPG_STMT_UPDATE, formaPg, response);

			if (updateCount == 1)
			{
				formaPg.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertFormaPg(FormaPg formaPg, String string,InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<FormaPg>();
		// First insert the root contato data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), FORMAPG_STMT_INSERT, formaPg, response);

		return insertCount;
	}

	@Override
	public Integer deleteFormaPg(FormaPg formaPg, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), FORMAPG_STMT_DELETE_PERSON_FORMAPG, formaPg, response);
	}

	@Override
	public InternalResultsResponse<FormaPg> fetchFormaPgById(Integer id)
	{
		InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FORMAPG_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	@Override
	public InternalResultsResponse<FormaPg> fetchFormaPgByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<FormaPg> response = new InternalResultsResponse<FormaPg>();

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
	public Integer updateFormaPgPessoa(FormaPgPessoa formaPg, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		InternalResultsResponse<FormaPgPessoa> response = new InternalResultsResponse<FormaPgPessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(formaPg.getModelAction())
				&& (formaPg.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), FORMAPG_PESSOA_STMT_UPDATE, formaPg, response);

			if (updateCount == 1)
			{
				formaPg.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer insertFormaPgPessoa(FormaPgPessoa formaPg,String string, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<FormaPgPessoa>();
		// First insert the root contato data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), FORMAPG_PESSOA_STMT_INSERT, formaPg, response);

		return insertCount;
	}

	@Override
	public Integer deleteFormaPgPessoa(FormaPgPessoa formaPg, InternalResultsResponse<?> response)
	{
		InternalResponse response = new InternalResponse();
		return QATMyBatisDacHelper.doRemove(getSqlSession(), FORMAPG_PESSOA_STMT_DELETE, formaPg, response);
	}

}
