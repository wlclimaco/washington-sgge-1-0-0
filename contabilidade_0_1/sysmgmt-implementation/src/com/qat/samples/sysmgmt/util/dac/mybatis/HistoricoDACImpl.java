package com.qat.samples.sysmgmt.util.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.alerta.Alertas;
import com.qat.samples.sysmgmt.alerta.model.request.AlertasInquiryRequest;
import com.qat.samples.sysmgmt.dacd.mybatis.PagedResultsDACD;
import com.qat.samples.sysmgmt.historico.Historico;
import com.qat.samples.sysmgmt.historico.HistoricoItens;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.dac.IHistoricoDAC;

/**
 * The Class HistoricoDACImpl.
 */
public class HistoricoDACImpl extends SqlSessionDaoSupport implements IHistoricoDAC
{
	/** The Constant CONTACT_NAMESPACE. */
	private static final String CONTACT_NAMESPACE = "HistoricoMap.";

	/** The Constant CONTACT_STMT_UPDATE. */
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "updateHistorico";

	/** The Constant CONTACT_STMT_DELETE_BUSINESS_CONTACT. */
	private static final String CONTACT_STMT_DELETE_BUSINESS_CONTACT = CONTACT_NAMESPACE + "deleteBusinessHistorico";

	/** The Constant CONTACT_STMT_INSERT. */
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "insertHistorico";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HistoricoDACImpl.class);

	@Override
	public Integer insertHistorico(Historico historico, String statementName, InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root historico data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, historico, response);

		// Associate with parent using statement name passed as parameter
		insertCount +=
				QATMyBatisDacHelper
						.doInsert(getSqlSession(), statementName, historico, response);

		return insertCount;
	}

	@Override
	public Integer insertHistoricoItens(HistoricoItens historico, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer insertCount = 0;
		// First insert the root historico data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historico, response);

		return insertCount;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IHistoricoDAC#deleteBusinessHistorico(com.prosperitasglobal.cbof.model.Historico,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer deleteHistorico(Historico historico, InternalResultsResponse<?> response)
	{
		return QATMyBatisDacHelper.doRemove(getSqlSession(), CONTACT_STMT_DELETE_BUSINESS_CONTACT, historico, response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICommonBusinessObjectsDAC#updateHistorico(com.prosperitasglobal.cbof.model.Historico
	 * ,
	 * com.qat.framework.model.response.InternalResultsResponse)
	 */
	@Override
	public Integer updateHistorico(Historico historico, InternalResultsResponse<?> response)
	{
		Integer updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(historico.getModelAction())
				&& (historico.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, historico, response);

			if (updateCount == 1)
			{
				historico.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		return updateCount;
	}

	@Override
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public InternalResultsResponse<Historico> fetchHistoricoByRequest(HistoricoInquiryRequest request)
	// {
	// InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();
	//
	// /*
	// * Helper method to translation from the user friendly" sort field names to the
	// * actual database column names.
	// */
	// // QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());
	//
	// PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "HistoricoMap.fetchHistoricoRowCount",
	// "HistoricoMap.fetchAllHistoricosByRequest", response);
	// return response;
	// }

	@Override
	public InternalResultsResponse<Alertas> fetchAlertasByRequest(AlertasInquiryRequest request)
	{
		InternalResultsResponse<Alertas> response = new InternalResultsResponse<Alertas>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, "HistoricoMap.fetchAlertasRowCount",
				"HistoricoMap.fetchAllAlertasByRequest", response);
		return response;
	}

}
