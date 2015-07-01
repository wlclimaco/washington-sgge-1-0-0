package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IFornecedorDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class FornecedorDACImpl extends SqlSessionDaoSupport implements IFornecedorDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FornecedorDACImpl.class);
	private static final String CONTACT_STMT_INSERT = null;
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertFornecedor(com.prosperitasglobal.cbof.
	 * model.Fornecedor,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertFornecedor(Fornecedor conhecimentoTransporte, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root conhecimentoTransporte data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, conhecimentoTransporte, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, conhecimentoTransporte, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IFornecedorDAC#deleteBusinessFornecedor(com.prosperitasglobal
	 * .cbof.model.Fornecedor,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteFornecedor(Fornecedor conhecimentoTransporte,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				conhecimentoTransporte, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateFornecedor(com.prosperitasglobal.cbof.
	 * model.Fornecedor,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateFornecedor(Fornecedor conhecimentoTransporte,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(conhecimentoTransporte.getModelAction())
				&& (conhecimentoTransporte.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, conhecimentoTransporte, response);

			if (updateCount == 1)
			{
				conhecimentoTransporte.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchFornecedorByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(
			PagedInquiryRequest request)
	{
		InternalResultsResponse<Fornecedor> response =
				new InternalResultsResponse<Fornecedor>();

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
	 * com.prosperitasglobal.cbof.dac.IFornecedorDAC#maintainFornecedorAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */

	public Integer maintainFornecedorAssociations(List<Fornecedor> conhecimentoTransporteList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Fornecedors
		if (ValidationUtil.isNullOrEmpty(conhecimentoTransporteList))
		{
			return count;
		}
		// For Each Fornecedor...
		for (Fornecedor conhecimentoTransporte : conhecimentoTransporteList)
		{
			// Make sure we set the parent key
			conhecimentoTransporte.setParentId(parentId);

			if (ValidationUtil.isNull(conhecimentoTransporte.getModelAction()))
			{
				continue;
			}
			switch (conhecimentoTransporte.getModelAction())
			{
				case INSERT:
					count += insertFornecedor(conhecimentoTransporte, associateStatement, response);
					break;
				case UPDATE:
					count += updateFornecedor(conhecimentoTransporte, response);
					break;
				case DELETE:
					count += deleteFornecedor(conhecimentoTransporte, response);
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
