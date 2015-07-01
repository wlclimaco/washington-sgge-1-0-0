package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.INotaFiscalItensDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.NotaFiscalItens;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class NotaFiscalItensDACImpl extends SqlSessionDaoSupport implements INotaFiscalItensDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotaFiscalItensDACImpl.class);
	private static final String CONTACT_STMT_INSERT = null;
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertNotaFiscalItens(com.prosperitasglobal.cbof.
	 * model.NotaFiscalItens,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertNotaFiscalItens(NotaFiscalItens notaFiscalItens, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root notaFiscalItens data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, notaFiscalItens, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), statementName, notaFiscalItens, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.INotaFiscalItensDAC#deleteBusinessNotaFiscalItens(com.prosperitasglobal
	 * .cbof.model.NotaFiscalItens,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteNotaFiscalItens(NotaFiscalItens notaFiscalItens,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				notaFiscalItens, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateNotaFiscalItens(com.prosperitasglobal.cbof.
	 * model.NotaFiscalItens,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateNotaFiscalItens(NotaFiscalItens notaFiscalItens,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(notaFiscalItens.getModelAction())
				&& (notaFiscalItens.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
					.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, notaFiscalItens, response);

			if (updateCount == 1)
			{
				notaFiscalItens.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchNotaFiscalItensByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<NotaFiscalItens> fetchNotaFiscalItensByRequest(
			PagedInquiryRequest request)
			{
		InternalResultsResponse<NotaFiscalItens> response =
				new InternalResultsResponse<NotaFiscalItens>();

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
	 * com.prosperitasglobal.cbof.dac.INotaFiscalItensDAC#maintainNotaFiscalItensAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainNotaFiscalItensAssociations(List<NotaFiscalItens> notaFiscalItensList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain NotaFiscalItenss
		if (ValidationUtil.isNullOrEmpty(notaFiscalItensList))
		{
			return count;
		}
		// For Each NotaFiscalItens...
		for (NotaFiscalItens notaFiscalItens : notaFiscalItensList)
		{
			// Make sure we set the parent key
			notaFiscalItens.setParentId(parentId);

			if (ValidationUtil.isNull(notaFiscalItens.getModelAction()))
			{
				continue;
			}
			switch (notaFiscalItens.getModelAction())
			{
				case INSERT:
					count += insertNotaFiscalItens(notaFiscalItens, associateStatement, response);
					break;
				case UPDATE:
					count += updateNotaFiscalItens(notaFiscalItens, response);
					break;
				case DELETE:
					count += deleteNotaFiscalItens(notaFiscalItens, response);
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
}
