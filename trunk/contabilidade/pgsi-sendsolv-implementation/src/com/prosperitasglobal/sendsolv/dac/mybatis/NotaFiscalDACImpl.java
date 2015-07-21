package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.util.Date;
import java.util.Map;

import com.prosperitasglobal.cbof.dac.INoteDAC;
import com.prosperitasglobal.sendsolv.dac.ICfopDAC;
import com.prosperitasglobal.sendsolv.dac.IConhecimentoTransporteDAC;
import com.prosperitasglobal.sendsolv.dac.IFormaPagamentoDAC;
import com.prosperitasglobal.sendsolv.dac.IFornecedorDAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.dac.IItensEspeciaisDAC;
import com.prosperitasglobal.sendsolv.dac.INFStatusDAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC;
import com.prosperitasglobal.sendsolv.dac.INotaFiscalItensDAC;
import com.prosperitasglobal.sendsolv.dac.IServicoItensDAC;
import com.prosperitasglobal.sendsolv.dac.ITransportadorDAC;
import com.prosperitasglobal.sendsolv.dac.ITributacaoDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ContasDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.FormaPagamentoDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.HistoricoNFDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.ItensEspeciaisDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NFstatusDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NotaFiscalItensDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.NotesDACD;
import com.prosperitasglobal.sendsolv.dacd.mybatis.TributacaoDACD;

/**
 * The Class NotaFiscalDACImpl.
 */
public class NotaFiscalDACImpl extends SqlSessionDaoSupport implements INotaFiscalDAC
{

	/** The Constant EMPRESA_NAMESPACE. */
	private static final String EMPRESA_NAMESPACE = "NotaFiscalMap.";

	/** The Constant EMPRESA_STMT_FETCH_COUNT. */
	private static final String EMPRESA_STMT_FETCH_COUNT = EMPRESA_NAMESPACE + "fetchNotaFiscalRowCount";

	/** The Constant EMPRESA_STMT_FETCH_ALL_BY_REQUEST. */
	private static final String EMPRESA_STMT_FETCH_ALL_BY_REQUEST = EMPRESA_NAMESPACE
			+ "fetchAllNotaFiscalsByRequest";

	/** The Constant EMPRESA_STMT_FETCH_BY_ID. */
	private static final String EMPRESA_STMT_FETCH_BY_ID = EMPRESA_NAMESPACE + "fetchNotaFiscalById";

	/** The Constant EMPRESA_STMT_INSERT. */
	private static final String EMPRESA_STMT_INSERT = EMPRESA_NAMESPACE + "insertNotaFiscal";

	/** The Constant EMPRESA_STMT_UPDATE. */
	private static final String EMPRESA_STMT_UPDATE = EMPRESA_NAMESPACE + "updateNotaFiscal";

	/** The Constant EMPRESA_STMT_DELETE. */
	private static final String EMPRESA_STMT_DELETE = EMPRESA_NAMESPACE + "deleteNotaFiscalById";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotaFiscalDACImpl.class);

	/** The valid sort fields for an cnae inquiry. Will be injected by Spring. */
	private Map<String, String> cnaeInquiryValidSortFields;

	private ITransportadorDAC transportadorDac;
	private IConhecimentoTransporteDAC conhecimentoTransporteDac;
	private ITributacaoDAC tributacaoDac;
	private IFormaPagamentoDAC formapgDAC;
	private INotaFiscalItensDAC notaItensDAC;
	private INoteDAC noteDAC;
	private IItensEspeciaisDAC itensEspeciaisDAC;
	private ICfopDAC cfopDAC;
	private IServicoItensDAC servicoItensDAC;
	private INFStatusDAC nfStatusDAC;
	private IFornecedorDAC fornecedorDAC;
	private IHistoricoDAC historicoDAC;

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
	 * @return the transportadorDac
	 */
	public ITransportadorDAC getTransportadorDac()
	{
		return transportadorDac;
	}

	/**
	 * @param transportadorDac the transportadorDac to set
	 */
	public void setTransportadorDac(ITransportadorDAC transportadorDac)
	{
		this.transportadorDac = transportadorDac;
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
	public ITributacaoDAC getTributacaoDac()
	{
		return tributacaoDac;
	}

	/**
	 * @param tributacaoDac the tributacaoDac to set
	 */
	public void setTributacaoDac(ITributacaoDAC tributacaoDac)
	{
		this.tributacaoDac = tributacaoDac;
	}

	/**
	 * @return the formapgDAC
	 */
	public IFormaPagamentoDAC getFormapgDAC()
	{
		return formapgDAC;
	}

	/**
	 * @param formapgDAC the formapgDAC to set
	 */
	public void setFormapgDAC(IFormaPagamentoDAC formapgDAC)
	{
		this.formapgDAC = formapgDAC;
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
	 * @return the nfStatusDAC
	 */
	public INFStatusDAC getNfStatusDAC()
	{
		return nfStatusDAC;
	}

	/**
	 * @param nfStatusDAC the nfStatusDAC to set
	 */
	public void setNfStatusDAC(INFStatusDAC nfStatusDAC)
	{
		this.nfStatusDAC = nfStatusDAC;
	}

	/**
	 * @return the fornecedorDAC
	 */
	public IFornecedorDAC getFornecedorDAC()
	{
		return fornecedorDAC;
	}

	/**
	 * @param fornecedorDAC the fornecedorDAC to set
	 */
	public void setFornecedorDAC(IFornecedorDAC fornecedorDAC)
	{
		this.fornecedorDAC = fornecedorDAC;
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
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();
		Integer historicoId = insertprocess(request.getEmpId(),request.getUserId(),"InsertNfEntrada", response);


		if(request.getModelAction == PersistanceActionEnum.INSERT){

		request.setProcessId(historicoId);
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), "NotaFiscalEntradaMap.insertNotaFiscal", request, response);


		Integer historicoItensId = insertprocessItens(historicoId,request.getId(), TabelaEnum.NOTAFISCAL, AcaoEnum.INSERT)
		}else{
			historicoId = request.getProcessId();
		}
		insertCount =+ insertNF(request,response,historicoId,
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteNotaFiscalEntrada(NotaFiscalEntrada request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<NotaFiscalEntrada> fetchNotaFiscalEntradaByRequest(NotaFiscalInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> insertNotaFiscalSaida(NotaFiscalSaida request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> updateNotaFiscalSaida(NotaFiscalSaida request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteNotaFiscalSaida(NotaFiscalSaida request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<NotaFiscalSaida> fetchNotaFiscalSaidaByRequest(NotaFiscalInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> insertPedidoCompras(PedidoCompras request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> updatePedidoCompras(PedidoCompras request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deletePedidoComprasl(PedidoCompras request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<PedidoCompras> fetchPedidoComprasByRequest(NotaFiscalInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Orcamento> insertOrcamento(Orcamento request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Orcamento> updateOrcamento(Orcamento request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteOrcamento(Orcamento request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(NotaFiscalInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasByRequest(Contas request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Contas> insertContas(Contas request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Contas> updateContas(Contas request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteContas(Contas request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Contas> fetchContasById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Integer insertNF(NotaFiscal notafiscal, InternalResultsResponse<?> response, Integer processId,
			Integer historicoId)
	{
		Integer insertCount = 0;

		// ConhecimentoTransporte
		insertCount +=
				ConecimentoTransporteDACD.maintainConecimentoTransporteAssociations(
						notafiscal.getConhecimentoTransporte(), response, notafiscal.getId(), null, null,
						null, getConhecimentoTransporteDAC(), getStatusDAC(), getHistoricoDAC(),
						notafiscal.getEmprId(),
						pessoa.getCreateUser(), processId, historicoId);

		// Tributacao
		insertCount +=
				TributacaoDACD.maintainTributacaoAssociations(notafiscal.getTributacao(), response, notafiscal.getId(),
						null,
						null,
						null, getTributacaoDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						produto.getCreateUser(), processId);

		// FormaPg
		insertCount +=
				FormaPagamentoDACD.maintainFormaPgAssociations(pessoa.getFormaPagamentoList(), response,
						notafiscal.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// NotaFiscalItens
		insertCount +=
				NotaFiscalItensDACD.maintainNotaFiscalItensAssociations(notafiscal.getNotaFiscalItens(), response,
						notafiscal.getId(),
						null,
						null,
						TabelaEnum.PESSOA, getFormaPgDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// Note
		insertCount +=
				NotesDACD.maintainNoteAssociations(notafiscal.getNotes(), response, notafiscal.getId(), null,
						null,
						TabelaEnum.PESSOA, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// Contas
		insertCount +=
				ContasDACD.maintainContasAssociations(notafiscal.getNotes(), response, notafiscal.getId(), null,
						null,
						TabelaEnum.PESSOA, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// ItensEspeciais
		insertCount +=
				ItensEspeciaisDACD.maintainItensEspeciaisAssociations(notafiscal.getItensEspeciais(), response,
						notafiscal.getId(), null,
						null,
						TabelaEnum.PESSOA, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// NFStatus
		insertCount +=
				NFstatusDACD.maintainNFStatusAssociations(notafiscal.getNFStatus(), response, notafiscal.getId(), null,
						null,
						TabelaEnum.PESSOA, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
						notafiscal.getCreateUser(), processId, historicoId);

		// HistoricoNF
		HistoricoNFDACD.maintainProfissaoAssociations(notafiscal.getHistoricoNF(), response, notafiscal.getId(), null,
				null,
				TabelaEnum.PESSOA, getNoteDAC(), getStatusDAC(), getHistoricoDAC(), notafiscal.getEmprId(),
				notafiscal.getCreateUser(), processId, historicoId);

		return count;
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

		count = getHistoricoDAC().insertHistorico(historico, statementName, response);
		QATMyBatisDacHelper.doInsert(getSqlSession(), "HistoricoMap.insertHistorico", historico, response);

		return count;
	}

	public Integer insertprocessItens(Integer historicoId, Integer parentId, TabelaEnum tabela, AcaoEnum acao)
	{
		Integer count = 0;

		HistoricoItens historicoItens = new HistoricoItens();
		historicoItens.setIdHist(historicoId);
		historicoItens.setProcessId(0);
		historicoItens.setParentId(parentId);
		historicoItens.setTabelaEnum(TabelaEnum);
		historicoItens.setAcaoType(AcaoEnum);

		count = getHistoricoDAC().insertHistoricoItens(historicoItens, statementName, response);

		Integer processId = historicoId;
		return count;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#insertNotaFiscal(com.prosperitasglobal.sendsolv.model
	 * .NotaFiscal)
	 */
	// @Override
	// public InternalResultsResponse<NotaFiscal> insertNotaFiscal(NotaFiscal cnae)
	// {
	// Integer insertCount = 0;
	// InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();
	//
	// // First insert the root
	// // Is successful the unique-id will be populated back into the object.
	// insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), EMPRESA_STMT_INSERT, cnae, response);
	//
	// if (response.isInError())
	// {
	// return response;
	// }
	// // Next traverse the object graph and "maintain" the associations
	// insertCount += maintainNotaFiscalAssociations(cnae, response);
	//
	// // Finally, if something was inserted then add the NotaFiscal to the result.
	// if (insertCount > 0)
	// {
	// response.addResult(cnae);
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#updateNotaFiscal(com.prosperitasglobal.sendsolv.model
	// * .NotaFiscal)
	// */
	// @Override
	// public InternalResultsResponse<NotaFiscal> updateNotaFiscal(NotaFiscal cnae)
	// {
	// Integer updateCount = 0;
	// InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();
	//
	// // First update the root if necessary.
	// if (!ValidationUtil.isNull(cnae.getModelAction())
	// && (cnae.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
	// {
	// updateCount =
	// QATMyBatisDacHelper.doUpdate(getSqlSession(), EMPRESA_STMT_UPDATE, cnae,
	// response);
	// }
	//
	// if (response.isInError())
	// {
	// return response;
	// }
	// // Next traverse the object graph and "maintain" the associations
	// updateCount += maintainNotaFiscalAssociations(cnae, response);
	//
	// // Finally, if something was updated then add the Person to the result.
	// if (updateCount > 0)
	// {
	// response.addResult(cnae);
	// }
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#deleteNotaFiscal(com.prosperitasglobal.sendsolv.model
	// * .NotaFiscal)
	// */
	// @Override
	// public InternalResponse deleteNotaFiscal(NotaFiscal cnae)
	// {
	// InternalResponse response = new InternalResponse();
	// QATMyBatisDacHelper.doRemove(getSqlSession(), EMPRESA_STMT_DELETE, cnae, response);
	//
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#fetchNotaFiscalById(FetchByIdRequest)
	// */
	// @Override
	// public InternalResultsResponse<NotaFiscal> fetchNotaFiscalById(FetchByIdRequest request)
	// {
	// InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();
	// QATMyBatisDacHelper.doQueryForList(getSqlSession(), EMPRESA_STMT_FETCH_BY_ID, request, response);
	// return response;
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.prosperitasglobal.sendsolv.dac.INotaFiscalDAC#fetchNotaFiscalByRequest(com.prosperitasglobal.sendsolv
	// * .model.request.PagedInquiryRequest)
	// */
	// @Override
	// public InternalResultsResponse<NotaFiscal> fetchNotaFiscalByRequest(PagedInquiryRequest request)
	// {
	// InternalResultsResponse<NotaFiscal> response = new InternalResultsResponse<NotaFiscal>();
	//
	// /*
	// * Helper method to translation from the user friendly" sort field names to the
	// * actual database column names.
	// */
	// QATMyBatisDacHelper.translateSortFields(request, getNotaFiscalInquiryValidSortFields());
	//
	// PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, EMPRESA_STMT_FETCH_COUNT,
	// EMPRESA_STMT_FETCH_ALL_BY_REQUEST, response);
	// return response;
	// }
	//
	// /**
	// * Maintain cnae associations.
	// *
	// * @param cnae the cnae
	// * @param response the response
	// * @return the integer
	// */
	// private Integer maintainNotaFiscalAssociations(NotaFiscal cnae,
	// InternalResultsResponse<NotaFiscal> response)
	// {
	// Integer count = 0;
	// // First Maintain Contacts
	// // if (ValidationUtil.isNullOrEmpty(cnae.getContactList()))
	// // {
	// // return count;
	// // }
	// // // For Each Contact...
	// // for (Contact contact : cnae.getContactList())
	// // {
	// // // Make sure we set the parent key
	// // contact.setParentKey(cnae.getId());
	// //
	// // if (ValidationUtil.isNull(contact.getModelAction()))
	// // {
	// // continue;
	// // }
	// // switch (contact.getModelAction())
	// // {
	// // case INSERT:
	// // count = getContactDAC().insertContact(contact,
	// // EMPRESA_STMT_ASSOC_ORG_TO_CONTACT, response);
	// // break;
	// // case UPDATE:
	// // count = getContactDAC().updateContact(contact, response);
	// // break;
	// // case DELETE:
	// // count = getContactDAC().deleteBusinessContact(contact, response);
	// // break;
	// // default:
	// // if (LOG.isDebugEnabled())
	// // {
	// // LOG.debug("ModelAction for NotaFiscal missing!");
	// // }
	// // break;
	// // }
	// // }
	// return count;
	// }
	//
	// @Override
	// public InternalResultsResponse<NotaFiscal> fetchAllNotaFiscals()
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }
}
