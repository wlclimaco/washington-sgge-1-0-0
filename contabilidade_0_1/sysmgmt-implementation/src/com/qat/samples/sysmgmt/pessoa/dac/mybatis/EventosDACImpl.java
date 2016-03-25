package com.qat.samples.sysmgmt.pessoa.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.dp.EventoPessoa;
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IEventosDAC;

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
	public Integer insertEvento(Eventos endereco, String string, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<Eventos>();
		// First insert the root endereco data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, endereco, response);

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
		response = new InternalResultsResponse<Eventos>();
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

	@Override
	public Integer updateEventoPessoa(EventoPessoa evento, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;
		response = new InternalResultsResponse<EventoPessoa>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(evento.getModelAction())
				&& (evento.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), "EventoMap.updateEventoPessoa", evento,
							response);
		}

		if (response.isInError())
		{
			return null;
		}

		// Finally, if something was updated then add the Person to the result.
		// if (updateCount > 0)
		// {
		// response.addResult(evento);
		// }

		return updateCount;
	}

	@Override
	public Integer insertEventoPessoa(EventoPessoa evento, String string, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		response = new InternalResultsResponse<EventoPessoa>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "EventoMap.insertEventoPessoa", evento,
						response);

		// Finally, if something was inserted then add the Beneficios to the result.
		// if (insertCount > 0)
		// {
		// response.addResult(evento);
		// }

		return insertCount;
	}

	@Override
	public Integer deleteEventoPessoa(EventoPessoa evento, InternalResultsResponse<?> response)
	{
		QATMyBatisDacHelper.doRemove(getSqlSession(), "EventoMap.deleteEventoPessoaById", evento, response);
		if (response.isInError())
		{
			return null;
		}
		else
		{
			return 1;
		}
	}

	@Override
	public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
