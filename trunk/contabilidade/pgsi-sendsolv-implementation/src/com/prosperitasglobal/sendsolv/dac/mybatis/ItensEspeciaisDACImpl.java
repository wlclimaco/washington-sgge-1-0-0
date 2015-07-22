package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IItensEspeciaisDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.ItensEspeciais;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonBusinessObjectsDACImpl.
 */
public class ItensEspeciaisDACImpl extends SqlSessionDaoSupport implements IItensEspeciaisDAC
{

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ItensEspeciaisDACImpl.class);
	private static final String CONTACT_STMT_INSERT = "ItensEspeciaisMap.insertItensEspeciais";
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = null;
	private static final String CONTACT_STMT_UPDATE = null;
	private static final String EMPRESA_STMT_FETCH_COUNT = null;
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = null;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertItensEspeciais(com.prosperitasglobal.cbof.
	 * model.ItensEspeciais,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertItensEspeciais(ItensEspeciais itensEspeciais, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root itensEspeciais data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, itensEspeciais, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, itensEspeciais, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IItensEspeciaisDAC#deleteBusinessItensEspeciais(com.prosperitasglobal
	 * .cbof.model.ItensEspeciais,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteItensEspeciais(ItensEspeciais itensEspeciais,
			InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT,
				itensEspeciais, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateItensEspeciais(com.prosperitasglobal.cbof.
	 * model.ItensEspeciais,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateItensEspeciais(ItensEspeciais itensEspeciais,
			InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(itensEspeciais.getModelAction())
				&& (itensEspeciais.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper
							.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, itensEspeciais, response);

			if (updateCount == 1)
			{
				itensEspeciais.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#fetchItensEspeciaisByParent(java.lang.Integer,
	 * com.prosperitasglobal.sendsolv.model.BusinessTypeEnum)
	 */
	@Override
	public InternalResultsResponse<ItensEspeciais> fetchItensEspeciaisByRequest(
			PagedInquiryRequest request)
	{
		InternalResultsResponse<ItensEspeciais> response =
				new InternalResultsResponse<ItensEspeciais>();

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
	 * com.prosperitasglobal.cbof.dac.IItensEspeciaisDAC#maintainItensEspeciaisAssociations(java.util
	 * .List, java.lang.Integer,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	public Integer maintainItensEspeciaisAssociations(List<ItensEspeciais> itensEspeciaisList,
			Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain ItensEspeciaiss
		if (ValidationUtil.isNullOrEmpty(itensEspeciaisList))
		{
			return count;
		}
		// For Each ItensEspeciais...
		for (ItensEspeciais itensEspeciais : itensEspeciaisList)
		{
			// Make sure we set the parent key
			itensEspeciais.setParentId(parentId);

			if (ValidationUtil.isNull(itensEspeciais.getModelAction()))
			{
				continue;
			}
			switch (itensEspeciais.getModelAction())
			{
				case INSERT:
					count += insertItensEspeciais(itensEspeciais, associateStatement, response);
					break;
				case UPDATE:
					count += updateItensEspeciais(itensEspeciais, response);
					break;
				case DELETE:
					count += deleteItensEspeciais(itensEspeciais, response);
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
