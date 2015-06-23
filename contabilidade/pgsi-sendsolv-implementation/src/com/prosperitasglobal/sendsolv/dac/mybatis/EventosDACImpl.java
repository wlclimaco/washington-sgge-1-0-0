package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dac.IEventosDAC;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CnaeDACImpl.
 */
public class EventosDACImpl extends SqlSessionDaoSupport implements IEventosDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "EventoMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateEvento";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessEvento";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertEvento";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EventosDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#insertEvento(com.prosperitasglobal.cbof.model.Evento,
	 * java.lang.String, com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer insertEvento(Eventos endereco, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root endereco data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, endereco, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), statementName, endereco, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateEvento(com.prosperitasglobal.cbof.model.Evento,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateEvento(Eventos endereco, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(endereco.getModelAction())
				&& (endereco.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, endereco, response);

			if (updateCount == 1)
			{
				endereco.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public Integer deleteEvento(Eventos evento, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, evento, response);
	}

	@Override
	public InternalResultsResponse<Eventos> fetchEventosByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Integer maintainEstoqueAssociations(List<Eventos> estoqueList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;
		// First Maintain Estoques
		if (ValidationUtil.isNullOrEmpty(estoqueList))
		{
			return count;
		}
		// For Each Estoque...
		for (Eventos estoque : estoqueList)
		{
			// Make sure we set the parent key
			estoque.setParentId(parentId);

			if (ValidationUtil.isNull(estoque.getModelAction()))
			{
				continue;
			}
			switch (estoque.getModelAction())
			{
				case INSERT:
					count += insertEvento(estoque, associateStatement, response);
					break;
				case UPDATE:
					count += updateEvento(estoque, response);
					break;
				case DELETE:
					count += deleteEvento(estoque, response);
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
