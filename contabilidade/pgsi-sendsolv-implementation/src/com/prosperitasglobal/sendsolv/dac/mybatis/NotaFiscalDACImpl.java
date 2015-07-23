package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Date;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.dac.ICfopDAC;
import com.prosperitasglobal.sendsolv.dac.IConhecimentoTransporteDAC;
import com.prosperitasglobal.sendsolv.dac.IContasDAC;
import com.prosperitasglobal.sendsolv.dac.IFormaPagamentoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoNFDAC;
import com.prosperitasglobal.sendsolv.dac.IItensEspeciaisDAC;
import com.prosperitasglobal.sendsolv.dac.INFStatusDAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalItensDAC;
import com.prosperitasglobal.sendsolv.dac.IServicoItensDAC;
import com.prosperitasglobal.sendsolv.dac.IStatusDAC;
import com.prosperitasglobal.sendsolv.dac.ITributacaoDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ConhecimentoTransporteDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ContasDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.FormaPagamentoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.HistoricoNFDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ItensEspeciaisDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NFstatusDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NotaFiscalItensDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NotesDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.TributacaoDACD;
import com.prosperitasglobal.sendsolv.model.AcaoEnum;
import com.prosperitasglobal.sendsolv.model.Contas;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.HistoricoItens;
import com.prosperitasglobal.sendsolv.model.NotaFiscal;
import com.prosperitasglobal.sendsolv.model.NotaFiscalEntrada;
import com.prosperitasglobal.sendsolv.model.NotaFiscalSaida;
import com.prosperitasglobal.sendsolv.model.Orcamento;
import com.prosperitasglobal.sendsolv.model.PedidoCompras;
import com.prosperitasglobal.sendsolv.model.TabelaEnum;
import com.prosperitasglobal.sendsolv.model.request.ContasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasInquiryRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class NotaFiscalDACImpl.
 */
public class NotaFiscalDACImpl extends SqlSessionDaoSupport implements INotaFiscalDAC
{

	/** The Constant NOTAFISCAL_NAMESPACE. */
	private static final String NOTAFISCAL_NAMESPACE = "NotaFiscalMap.";

	/** The Constant NOTAFISCAL_STMT_FETCH_BY_ID. */
	private static final String NOTAFISCAL_STMT_FETCH_BY_ID = NOTAFISCAL_NAMESPACE + "fetchNotaFiscalById";

	/** The Constant NOTAFISCAL_STMT_INSERT. */
	private static final String NOTAFISCAL_STMT_INSERT = NOTAFISCAL_NAMESPACE + "insertNotaFiscal";

	/** The Constant NOTAFISCAL_STMT_UPDATE. */
	private static final String NOTAFISCAL_STMT_UPDATE = NOTAFISCAL_NAMESPACE + "updateNotaFiscal";

	/** The Constant NOTAFISCAL_STMT_DELETE. */
	private static final String NOTAFISCAL_STMT_DELETE = NOTAFISCAL_NAMESPACE + "deleteNotaFiscalById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotaFiscalDACImpl.class);

	/** The valid sort fields for an cnae inquiry. Will be injected by Spring. */
	private Map<String, String> cnaeInquiryValidSortFields;

	private IConhecimentoTransporteDAC conhecimentoTransporteDac;
	private ITributacaoDAC tributacaoDAC;
	private IFormaPagamentoDAC formaPgDAC;
	private INotaFiscalItensDAC notaItensDAC;
	private INoteDAC noteDAC;
	private IItensEspeciaisDAC itensEspeciaisDAC;
	private ICfopDAC cfopDAC;
	private IServicoItensDAC servicoItensDAC;
	private IHistoricoDAC historicoDAC;
	private IStatusDAC statusDAC;
	private IContasDAC contasDAC;
	private INFStatusDAC nFStatusDAC;
	private IHistoricoNFDAC historicoNFDAC;

	/**
	 * @return the contasDAC
	 */
	public IContasDAC getContasDAC()
	{
		return contasDAC;
	}

	/**
	 * @param contasDAC the contasDAC to set
	 */
	public void setContasDAC(IContasDAC contasDAC)
	{
		this.contasDAC = contasDAC;
	}

	/**
	 * @return the nFStatusDAC
	 */
	public INFStatusDAC getnFStatusDAC()
	{
		return nFStatusDAC;
	}

	/**
	 * @param nFStatusDAC the nFStatusDAC to set
	 */
	public void setnFStatusDAC(INFStatusDAC nFStatusDAC)
	{
		this.nFStatusDAC = nFStatusDAC;
	}

	/**
	 * @return the historicoNFDAC
	 */
	public IHistoricoNFDAC getHistoricoNFDAC()
	{
		return historicoNFDAC;
	}

	/**
	 * @param historicoNFDAC the historicoNFDAC to set
	 */
	public void setHistoricoNFDAC(IHistoricoNFDAC historicoNFDAC)
	{
		this.historicoNFDAC = historicoNFDAC;
	}

	/**
	 * @return the statusDAC
	 */
	public IStatusDAC getStatusDAC()
	{
		return statusDAC;
	}

	/**
	 * @param statusDAC the statusDAC to set
	 */
	public void setStatusDAC(IStatusDAC statusDAC)
	{
		this.statusDAC = statusDAC;
	}

	/**
	 * @return the cnaeInquiryValidSortFields
	 */
	public Map<String, String> getCnaeInquiryValidSortFields()
	{
		return cnaeInquiryValidSortFields;
	}

	public IHistoricoDAC getHistoricoDAC()
	{
		return historicoDAC;
	}

	public void setHistoricoDAC(IHistoricoDAC historicoDAC)
	{
		this.historicoDAC = historicoDAC;
	}

	/**
	 * @param cnaeInquiryValidSortFields the cnaeInquiryValidSortFields to set
	 */
	public void setCnaeInquiryValidSortFields(Map<String, String> cnaeInquiryValidSortFields)
	{
		this.cnaeInquiryValidSortFields = cnaeInquiryValidSortFields;
	}

	/**
	 * @return the conhecimentoTransporteDac
	 */
	public IConhecimentoTransporteDAC getConhecimentoTransporteDac()
	{
		return conhecimentoTransporteDac;
	}

	/**
	 * @param conhecimentoTransporteDac the conhecimentoTransporteDac to set
	 */
	public void setConhecimentoTransporteDac(IConhecimentoTransporteDAC conhecimentoTransporteDac)
	{
		this.conhecimentoTransporteDac = conhecimentoTransporteDac;
	}

	/**
	 * @return the tributacaoDac
	 */
	public ITributacaoDAC getTributacaoDAC()
	{
		return tributacaoDAC;
	}

	/**
	 * @param tributacaoDac the tributacaoDac to set
	 */
	public void setTributacaoDAC(ITributacaoDAC tributacaoDAC)
	{
		this.tributacaoDAC = tributacaoDAC;
	}

	/**
	 * @return the formaPgDAC
	 */
	public IFormaPagamentoDAC getFormaPgDAC()
	{
		return formaPgDAC;
	}

	/**
	 * @param formaPgDAC the formaPgDAC to set
	 */
	public void setFormaPgDAC(IFormaPagamentoDAC formaPgDAC)
	{
		this.formaPgDAC = formaPgDAC;
	}

	/**
	 * @return the notaItensDAC
	 */
	public INotaFiscalItensDAC getNotaItensDAC()
	{
		return notaItensDAC;
	}

	/**
	 * @param notaItensDAC the notaItensDAC to set
	 */
	public void setNotaItensDAC(INotaFiscalItensDAC notaItensDAC)
	{
		this.notaItensDAC = notaItensDAC;
	}

	/**
	 * @return the noteDAC
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * @param noteDAC the noteDAC to set
	 */
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/**
	 * @return the itensEspeciaisDAC
	 */
	public IItensEspeciaisDAC getItensEspeciaisDAC()
	{
		return itensEspeciaisDAC;
	}

	/**
	 * @param itensEspeciaisDAC the itensEspeciaisDAC to set
	 */
	public void setItensEspeciaisDAC(IItensEspeciaisDAC itensEspeciaisDAC)
	{
		this.itensEspeciaisDAC = itensEspeciaisDAC;
	}

	/**
	 * @return the cfopDAC
	 */
	public ICfopDAC getCfopDAC()
	{
		return cfopDAC;
	}

	/**
	 * @param cfopDAC the cfopDAC to set
	 */
	public void setCfopDAC(ICfopDAC cfopDAC)
	{
		this.cfopDAC = cfopDAC;
	}

	/**
	 * @return the servicoItensDAC
	 */
	public IServicoItensDAC getServicoItensDAC()
	{
		return servicoItensDAC;
	}

	/**
	 * @param servicoItensDAC the servicoItensDAC to set
	 */
	public void setServicoItensDAC(IServicoItensDAC servicoItensDAC)
	{
		this.servicoItensDAC = servicoItensDAC;
	}

	/**
	 * Get the valid sort fields for the cnae inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the cnae inquiry.
	 */
	public Map<String, String> getNotaFiscalInquiryValidSortFields()
	{
		return cnaeInquiryValidSortFields;
	}

	/**
	 * Set the valid sort fields for the cnae inquiry. Attribute injected by Spring.
	 *
	 * @param cnaeInquiryValidSortFields The valid sort fields for the cnae inquiry to set.
	 */
	public void setNotaFiscalInquiryValidSortFields(Map<String, String> cnaeInquiryValidSortFields)
	{
		this.cnaeInquiryValidSortFields = cnaeInquiryValidSortFields;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> insertNotaFiscalEntrada(NotaFiscalEntrada request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_INSERT, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> updateNotaFiscalEntrada(NotaFiscalEntrada request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_INSERT, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteNotaFiscalEntrada(NotaFiscalEntrada request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_INSERT, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), NOTAFISCAL_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaByRequest(NotaFiscalInquiryRequest request)
	{
		InternalResultsResponse<NotaFiscalEntrada> response = new InternalResultsResponse<NotaFiscalEntrada>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request,
				"NotaFiscalEntradaMap.fetchNotaFiscalEntradaRowCount",
				"NotaFiscalEntradaMap.fetchAllNotaFiscalEntradasByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaida request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_UPDATE, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.UPDATE);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaida request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_DELETE, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.DELETE);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteNotaFiscalSaida(NotaFiscalSaida request)
	{
		InternalResultsResponse response = new InternalResultsResponse();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_INSERT, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), NOTAFISCAL_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaByRequest(NotaFiscalInquiryRequest request)
	{
		InternalResultsResponse<NotaFiscalSaida> response = new InternalResultsResponse<NotaFiscalSaida>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request,
				"NotaFiscalEntradaMap.fetchNotaFiscalSaidaRowCount",
				"NotaFiscalEntradaMap.fetchAllNotaFiscalSaidasByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoCompras request)
	{
		InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_INSERT, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoCompras request)
	{
		InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
		Integer historicoId = request.getProcessId();
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.UPDATE)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_UPDATE, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.UPDATE);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deletePedidoCompras(PedidoCompras request)
	{
		InternalResultsResponse response = new InternalResultsResponse();
		Integer historicoId = request.getProcessId();
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_DELETE, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.DELETE);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request)
	{
		InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), NOTAFISCAL_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(PedidoComprasInquiryRequest request)
	{
		InternalResultsResponse<PedidoCompras> response = new InternalResultsResponse<PedidoCompras>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request,
				"NotaFiscalEntradaMap.fetchPedidoComprasRowCount",
				"NotaFiscalEntradaMap.fetchAllPedidoComprasByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Orcamento> insertOrcamento(Orcamento request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
		Integer historicoId = insertprocess(request.getEmprId(), request.getUserId(), "InsertNfEntrada", response);
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_INSERT, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Orcamento> updateOrcamento(Orcamento request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
		Integer historicoId = request.getProcessId();
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_UPDATE, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.UPDATE);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteOrcamento(Orcamento request)
	{
		InternalResultsResponse response = new InternalResultsResponse();
		Integer historicoId = request.getProcessId();
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_INSERT, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT);
		}
		else
		{
			historicoId = request.getProcessId();
		}
		insertCount = +insertNF(request, response, historicoId,
				historicoId);

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), NOTAFISCAL_STMT_FETCH_BY_ID, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoInquiryRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		/*
		 * Helper method to translation from the user friendly" sort field names to the
		 * actual database column names.
		 */
		// QATMyBatisDacHelper.translateSortFields(request, getEmpresaInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request,
				"NotaFiscalEntradaMap.fetchOrcamentoRowCount",
				"NotaFiscalEntradaMap.fetchAllOrcamentosByRequest", response);
		return response;
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasByRequest(ContasInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Contas> insertContas(Contas request)
	{
		InternalResultsResponse<Contas> response = new InternalResultsResponse<Contas>();
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), "ContasMap.insertContas", request,
							response);

		}

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Contas> updateContas(Contas request)
	{
		InternalResultsResponse<Contas> response = new InternalResultsResponse<Contas>();
		Integer historicoId = request.getProcessId();
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.INSERT)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_UPDATE, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.UPDATE);
		}
		else
		{
			historicoId = request.getProcessId();
		}

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResponse deleteContas(Contas request)
	{
		InternalResultsResponse response = new InternalResultsResponse();
		Integer historicoId = request.getProcessId();
		Integer insertCount = 0;

		if (request.getModelAction() == PersistanceActionEnum.DELETE)
		{

			request.setProcessId(historicoId);
			insertCount =
					QATMyBatisDacHelper.doInsert(getSqlSession(), NOTAFISCAL_STMT_DELETE, request,
							response);

			Integer historicoItensId =
					insertprocessItens(historicoId, request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.DELETE);
		}
		else
		{
			historicoId = request.getProcessId();
		}

		if (response.isInError())
		{
			return response;
		}

		if (insertCount > 0)
		{
			response.addResult(request);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request)
	{
		InternalResultsResponse<Contas> response = new InternalResultsResponse<Contas>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), "ContasMap.fetchContasById", request, response);
		return response;
	}

	public Integer insertNF(NotaFiscal notafiscal, InternalResultsResponse<?> response, Integer processId,
			Integer historicoId)
	{
		Integer insertCount = 0;

		// ConhecimentoTransporte
		insertCount +=
				ConhecimentoTransporteDACD.maintainConecimentoTransporteAssociations(
						notafiscal.getConhecimentoTransporte(), response, notafiscal.getId(), null, null,
						null, getConhecimentoTransporteDac(), getStatusDAC(), getHistoricoDAC(),
						notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// Tributacao
		insertCount +=
				TributacaoDACD.maintainTributacaoAssociations(notafiscal.getTributosList().get(0), response,
						notafiscal.getId(),
						null,
						null,
						null, getTributacaoDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId);

		// FormaPg
		insertCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(notafiscal.getFormaPagList(), response,
						notafiscal.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), historicoId, historicoId);

		// NotaFiscalItens
		insertCount +=
				NotaFiscalItensDACD.maintainNotaFiscalItensAssociations(notafiscal.getNotaFiscalItens(), response,
						notafiscal.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getNotaItensDAC(), getStatusDAC(), getHistoricoDAC(),
						notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, getTributacaoDAC());

		// Note
		insertCount +=
				NotesDACD.maintainNoteAssociations(notafiscal.getNotes(), response, notafiscal.getId(), null,
						null,
						TabelaEnum.PESSOA, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// Contas
		insertCount +=
				ContasDACD.maintainContasAssociations(notafiscal.getContaspagarList(), response, notafiscal.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getContasDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// ItensEspeciais
		insertCount +=
				ItensEspeciaisDACD.maintainItensEspeciaisAssociations(notafiscal.getItensEspeciais(), response,
						notafiscal.getId(), null,
						null,
						TabelaEnum.PESSOA, getItensEspeciaisDAC(), getStatusDAC(), getHistoricoDAC(),
						notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId);

		// NFStatus
		insertCount +=
				NFstatusDACD.maintainNFStatusAssociations(notafiscal.getNfStatusList(), response, notafiscal.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getnFStatusDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId);

		// HistoricoNF
		insertCount +=
				HistoricoNFDACD.maintainHistoricoNFAssociations(notafiscal.getHistoricoNFList(), response,
						notafiscal.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getHistoricoNFDAC(), getStatusDAC(), getHistoricoDAC(),
						notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		return 1;
	}

	public Integer insertprocess(Integer emprId, String userId, String statementName,
			InternalResultsResponse<?> response)
	{
		Integer count = 0;

		Historico historico = new Historico();
		historico.setEmprId(emprId);
		historico.setUserId(userId);
		historico.setProcessId(0);
		Date a = new Date();
		historico.setData(a.getTime());

		QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		return historico.getId();
	}

	public Integer insertprocessItens(Integer historicoId, Integer parentId, TabelaEnum tabela, AcaoEnum acao)
	{
		Integer count = 0;
		InternalResultsResponse<HistoricoItens> response = new InternalResultsResponse<HistoricoItens>();
		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(0);
		historicoItens.setParentId(parentId);
		historicoItens.setTabelaEnum(tabela);
		historicoItens.setAcaoType(acao);

		QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistoricoItens", historicoItens, response);
		return historicoItens.getId();
	}
}
