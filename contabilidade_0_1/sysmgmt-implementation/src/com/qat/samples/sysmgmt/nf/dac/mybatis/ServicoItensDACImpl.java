package com.qat.samples.sysmgmt.nf.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.nf.dac.IServicoItensDAC;
import com.qat.samples.sysmgmt.nf.model.ServicoItens;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ServicoItensDACImpl extends SqlSessionDaoSupport implements IServicoItensDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServicoItensDACImpl.class);
	private static final String CONTACT_STMT_INSERT = null;
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertServicoItens(com.prosperitasglobal.cbof.
	 * model.ServicoItens,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertServicoItens(ServicoItens servicoItens, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root servicoItens data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, servicoItens, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, servicoItens, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IServicoItensDAC#deleteBusinessServicoItens(com.prosperitasglobal
	 * .cbof.model.ServicoItens,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteServicoItens(ServicoItens servicoItens,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				servicoItens, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateServicoItens(com.prosperitasglobal.cbof.
	 * model.ServicoItens,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateServicoItens(ServicoItens servicoItens,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(servicoItens.getModelAction())
				&& (servicoItens.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, servicoItens, response);

			if (updateCount == 1)
			{
				servicoItens.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchServicoItensByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<ServicoItens> fetchServicoItensByRequest(
			PagedInquiryRequest request)
	{
		InternalResultsResponse<ServicoItens> response =
				new InternalResultsResponse<ServicoItens>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request,
				EMPRESA_STMT_FETCH_COUNT,
				EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IServicoItensDAC#maintainServicoItensAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */

	public Integer maintainServicoItensAssociations(List<ServicoItens> servicoItensList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain ServicoItenss
		if (ValidationUtil.isNullOrEmpty(servicoItensList))
		{
			return count;
		}
		// For Each ServicoItens...
		for (ServicoItens servicoItens : servicoItensList)
		{
			// Make sure we set the parent key
			servicoItens.setParentId(parentId);

			if (ValidationUtil.isNull(servicoItens.getModelAction()))
			{
				continue;
			}
			switch (servicoItens.getModelAction())
			{
				case INSERT:
					count += insertServicoItens(servicoItens, associateStatement, response);
					break;
				case UPDATE:
					count += updateServicoItens(servicoItens, response);
					break;
				case DELETE:
					count += deleteServicoItens(servicoItens, response);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("ModelAction for Organization missing!");
					}
					break;
			}
		}
		return count;
	}

	@Override
	public InternalResultsResponse<ServicoItens> fetchServicoItensById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
