package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.clinica.Exame;
import com.qat.samples.sysmgmt.clinica.ExamePessoa;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IExameDAC;

/**
 * The Class ExameDACImpl.
 */
public class ExameDACImpl extends SqlSessionDaoSupport implements IExameDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "ExameMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchExameRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllExamesByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchExameById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertExame";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateExame";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteExameById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ExameDACImpl.class);

	/** The valid sort fields for an banco inquiry. Will be injected by Spring. */
	private Map<String, String> bancoInquiryValidSortFields;

	/**
	 * Get the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 * 
	 * @return The valid sort fields for the banco inquiry.
	 */
	public Map<String, String> getExameInquiryValidSortFields()
	{
		return bancoInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the banco inquiry. Attribute injected by Spring.
	 * 
	 * @param bancoInquiryValidSortFields The valid sort fields for the banco inquiry to set.
	 */
	public void setExameInquiryValidSortFields(Map<String, String> bancoInquiryValidSortFields)
	{
		this.bancoInquiryValidSortFields = bancoInquiryValidSortFields;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IExameDAC#insertExame(com.prosperitasglobal.sendsolv.model
	 * .Exame)
	 */
	@Override
	public Integer insertExame(Exame banco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, banco, response);

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was inserted then add the Exame to the result.
		if (insertCount > 0)
		{
			response.addResult(banco);
		}

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IExameDAC#updateExame(com.prosperitasglobal.sendsolv.model
	 * .Exame)
	 */
	@Override
	public Integer updateExame(Exame banco)
	{
		Integer updateCount = 0;
		InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(banco.getModelAction())
				&& (banco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, banco,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(banco);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IExameDAC#deleteExame(com.prosperitasglobal.sendsolv.model
	 * .Exame)
	 */
	@Override
	public Integer deleteExame(Exame banco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, banco, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.IExameDAC#fetchExameById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Exame> fetchExameById(FetchByIdRequest request)
	{
		InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.IExameDAC#fetchExameByRequest(com.prosperitasglobal.sendsolv
	 * .model.request.PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Exame> fetchExameByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Exame> response = new InternalResultsResponse<Exame>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getExameInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/**
	 * Maintain banco associations.
	 * 
	 * @param banco the banco
	 * @param response the response
	 * @return the integer
	 */
	private Integer maintainExameAssociations(Exame banco,
			InternalResultsResponse<Exame> response)
	{
		Integer count = 0;

		return count;
	}

	@Override
	public InternalResultsResponse<Exame> fetchAllExames()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateExamePessoa(ExamePessoa banco)
	{
		Integer updateCount = 0;
		InternalResultsResponse<ExamePessoa> response = new InternalResultsResponse<ExamePessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(banco.getModelAction())
				&& (banco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "ExameMap.updateExamePessoa", banco,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		if (updateCount > 0)
		{
			response.addResult(banco);
		}

		return updateCount;
	}

	@Override
	public Integer insertExamePessoa(ExamePessoa banco)
	{
		Integer insertCount = 0;
		InternalResultsResponse<ExamePessoa> response = new InternalResultsResponse<ExamePessoa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "ExameMap.insertExame", banco.getExame(), response);

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), "ExameMap.insertExamePessoa", banco, response);

		if (response.isInError())
		{
			return null;
		}
		// Finally, if something was inserted then add the Exame to the result.
		if (insertCount > 0)
		{
			response.addResult(banco);
		}

		return insertCount;
	}

	@Override
	public Integer deleteExamePessoa(ExamePessoa banco)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), "ExameMap.deleteExamePessoa", banco, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}
}
