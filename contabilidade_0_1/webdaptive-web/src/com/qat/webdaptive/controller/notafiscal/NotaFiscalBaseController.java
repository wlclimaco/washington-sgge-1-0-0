package com.qat.webdaptive.controller.notafiscal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.INotaFiscalBAI;
import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.Classificacao;
import com.prosperitasglobal.sendsolv.model.ConhecimentoTransporte;
import com.prosperitasglobal.sendsolv.model.Contas;
import com.prosperitasglobal.sendsolv.model.ContasTypeEnum;
import com.prosperitasglobal.sendsolv.model.Csosn;
import com.prosperitasglobal.sendsolv.model.Cst;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.Estado;
import com.prosperitasglobal.sendsolv.model.FormaPgPessoa;
import com.prosperitasglobal.sendsolv.model.HistoricoNF;
import com.prosperitasglobal.sendsolv.model.Incidencia;
import com.prosperitasglobal.sendsolv.model.ItensEspeciais;
import com.prosperitasglobal.sendsolv.model.Marca;
import com.prosperitasglobal.sendsolv.model.NFStatus;
import com.prosperitasglobal.sendsolv.model.NotaFiscal;
import com.prosperitasglobal.sendsolv.model.NotaFiscalEntrada;
import com.prosperitasglobal.sendsolv.model.NotaFiscalItens;
import com.prosperitasglobal.sendsolv.model.NotaFiscalSaida;
import com.prosperitasglobal.sendsolv.model.NotaTypeEnum;
import com.prosperitasglobal.sendsolv.model.Orcamento;
import com.prosperitasglobal.sendsolv.model.PedidoCompras;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.StatusNF;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.Tributacao;
import com.prosperitasglobal.sendsolv.model.request.CaixaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CondPgInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ContasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PedidoComprasMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.CaixaResponse;
import com.prosperitasglobal.sendsolv.model.response.CondPgResponse;
import com.prosperitasglobal.sendsolv.model.response.ContasResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalEntradaResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalSaidaResponse;
import com.prosperitasglobal.sendsolv.model.response.OrcamentoResponse;
import com.prosperitasglobal.sendsolv.model.response.PedidoComprasResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.validation.ValidationUtil;

public class NotaFiscalBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "NotaFiscalBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The NotaFiscal BAI. */
	private INotaFiscalBAI notaFiscalBAI;

	/**
	 * Gets the notaFiscal bai.
	 *
	 * @return the notaFiscal bai
	 */
	public INotaFiscalBAI getNotaFiscalBAI()
	{
		return notaFiscalBAI;
	}

	/**
	 * Sets the notaFiscal bai.
	 *
	 * @param notaFiscalBAI the notaFiscal bai
	 */
	@Resource
	public void setNotaFiscalBAI(INotaFiscalBAI notaFiscalBAI)
	{
		this.notaFiscalBAI = notaFiscalBAI;
	}

	/**
	 * NotaFiscal edit mav.
	 *
	 * @param notaFiscalId the notaFiscal id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView notaFiscalEditMAV(Integer notaFiscalId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(notaFiscalId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchNotaFiscalById(new FetchByIdRequest(notaFiscalId))));

				return modelAndView;
			}

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
				modelAndView.addObject(RESPONSE, null);
			}
		}

		return modelAndView;
	}

	/**
	 * Fetch notaFiscal by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the notaFiscal response
	 */
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaByRequest(NotaFiscalInquiryRequest pagedInquiryRequest)
	{

		NotaFiscalEntradaResponse notaFiscalResponse = new NotaFiscalEntradaResponse();
		try
		{

			notaFiscalResponse = getNotaFiscalBAI().fetchNotaFiscalEntradaByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	// caixa
	public CaixaResponse fetchCaixaByRequest(CaixaInquiryRequest pagedInquiryRequest)
	{

		CaixaResponse notaFiscalResponse = new CaixaResponse();
		try
		{

			notaFiscalResponse = getNotaFiscalBAI().fetchCaixaByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	// contas
	public ContasResponse fetchContasByRequest(ContasInquiryRequest pagedInquiryRequest)
	{

		ContasResponse notaFiscalResponse = new ContasResponse();
		try
		{

			notaFiscalResponse = getNotaFiscalBAI().fetchContasByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	// cond Pag
	public CondPgResponse fetchCondPgByRequest(CondPgInquiryRequest pagedInquiryRequest)
	{

		CondPgResponse notaFiscalResponse = new CondPgResponse();
		try
		{

			notaFiscalResponse = getNotaFiscalBAI().fetchCondPgByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	// Saida
	public NotaFiscalSaidaResponse fetchNotaFiscalSaidaByRequest(NotaFiscalInquiryRequest pagedInquiryRequest)
	{

		NotaFiscalSaidaResponse notaFiscalResponse = new NotaFiscalSaidaResponse();
		try
		{

			notaFiscalResponse = getNotaFiscalBAI().fetchNotaFiscalSaidaByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	// pedidoCompras
	public PedidoComprasResponse fetchPedidoComprasByRequest(PedidoComprasInquiryRequest pagedInquiryRequest)
	{

		PedidoComprasResponse notaFiscalResponse = new PedidoComprasResponse();
		try
		{

			notaFiscalResponse = getNotaFiscalBAI().fetchPedidoComprasByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	// Orcamento
	public OrcamentoResponse fetchOrcamentoByRequest(OrcamentoInquiryRequest pagedInquiryRequest)
	{

		OrcamentoResponse notaFiscalResponse = new OrcamentoResponse();
		try
		{

			notaFiscalResponse = getNotaFiscalBAI().fetchOrcamentoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	/**
	 * Fetch notaFiscal by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the notaFiscal response
	 */
	public NotaFiscalResponse fetchNotaFiscalById(FetchByIdRequest fetchByIdRequest)
	{

		NotaFiscalResponse notaFiscalResponse = new NotaFiscalResponse();
		try
		{

			// notaFiscalResponse = MockById();
			// getNotaFiscalBAI().fetchNotaFiscalById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return notaFiscalResponse;
	}

	/**
	 * Insert one notaFiscal.
	 *
	 * @param notaFiscalRequest the notaFiscal request
	 * @return the response
	 */
	public NotaFiscalEntradaResponse insertEntrada(NotaFiscalEntradaMaintenanceRequest notaFiscalRequest)
	{
		NotaFiscalEntradaResponse notaFiscalResponse = new NotaFiscalEntradaResponse();

		try
		{
			notaFiscalRequest.setNotafiscal((NotaFiscalEntrada)insertMockNotaFiscal(PersistanceActionEnum.INSERT,
					NotaTypeEnum.ENTRADA));
			notaFiscalRequest.getNotafiscal().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			notaFiscalResponse = getNotaFiscalBAI().insertNotaFiscalEntrada(notaFiscalRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			notaFiscalResponse = null;
		}

		return notaFiscalResponse;

	}

	// saida
	public NotaFiscalSaidaResponse insertSaida(NotaFiscalSaidaMaintenanceRequest notaFiscalRequest)
	{
		NotaFiscalSaidaResponse notaFiscalResponse = new NotaFiscalSaidaResponse();

		try
		{
			notaFiscalRequest.setNotafiscal((NotaFiscalSaida)insertMockNotaFiscal(PersistanceActionEnum.INSERT,
					NotaTypeEnum.SAIDA));
			notaFiscalRequest.getNotafiscal().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			notaFiscalResponse = getNotaFiscalBAI().insertNotaFiscalSaida(notaFiscalRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			notaFiscalResponse = null;
		}

		return notaFiscalResponse;

	}

	// pedido compra
	public PedidoComprasResponse insertCompras(PedidoComprasMaintenanceRequest notaFiscalRequest)
	{
		PedidoComprasResponse notaFiscalResponse = new PedidoComprasResponse();

		try
		{
			notaFiscalRequest.setPedidoCompras((PedidoCompras)insertMockNotaFiscal(PersistanceActionEnum.INSERT,
					NotaTypeEnum.PEDIDOCOMPRAS));
			notaFiscalRequest.getPedidoCompras().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			notaFiscalResponse = getNotaFiscalBAI().insertPedidoCompras(notaFiscalRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			notaFiscalResponse = null;
		}

		return notaFiscalResponse;

	}

	// orcamento
	public OrcamentoResponse insertOrcamento(OrcamentoMaintenanceRequest notaFiscalRequest)
	{
		OrcamentoResponse notaFiscalResponse = new OrcamentoResponse();

		try
		{
			notaFiscalRequest.setOrcamento((Orcamento)insertMockNotaFiscal(PersistanceActionEnum.INSERT,
					NotaTypeEnum.ORCAMENTO));
			notaFiscalRequest.getOrcamento().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			notaFiscalResponse = getNotaFiscalBAI().insertOrcamento(notaFiscalRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			notaFiscalResponse = null;
		}

		return notaFiscalResponse;

	}

	public ConhecimentoTransporte insertConhecimentoTransporte(PersistanceActionEnum modelAction)
	{

		ConhecimentoTransporte conhecimentoTransporte = new ConhecimentoTransporte();

		conhecimentoTransporte.setModelAction(modelAction);
		conhecimentoTransporte.setId(1);
		conhecimentoTransporte.setIdNota(0);
		conhecimentoTransporte.setTransportador(new Transportador(1));
		conhecimentoTransporte.setRemetente("Test");
		conhecimentoTransporte.setVrTotalMercadorias(new Double(9.99));
		conhecimentoTransporte.setApCreIcms(1);
		conhecimentoTransporte.setFretePorConta(1);
		conhecimentoTransporte.setPlaca("jhu-0102");
		conhecimentoTransporte.setEstado(new Estado(1));
		conhecimentoTransporte.setMarca(new Marca(1));
		conhecimentoTransporte.setEspecie(new Double(9.99));
		conhecimentoTransporte.setVolume(new Double(.99));
		conhecimentoTransporte.setPesoLiquido(new Double(1.99));
		conhecimentoTransporte.setPesoBruto(new Double(3.99));

		return conhecimentoTransporte;

	}

	public List<Tributacao> insertTributacao(PersistanceActionEnum modelAction)
	{

		List<Tributacao> tributacaoList = new ArrayList<Tributacao>();
		Tributacao tributacao = new Tributacao();
		tributacao.setId(1);
		tributacao.setCst(new Cst(1));
		tributacao.setIcms(new Double(9.99));
		tributacao.setSt(1);
		tributacao.setMva(new Double(9.99));
		tributacao.setCsosn(new Csosn(1));
		tributacao.setIpi(new Double(0.99));
		tributacao.setIat(1);
		tributacao.setIppt(1);
		tributacao.setPisconfins(1);
		tributacao.setIncidencia(new Incidencia("001", "teste"));
		tributacaoList.add(tributacao);

		return tributacaoList;
	}

	public List<FormaPgPessoa> insertFormaPg(PersistanceActionEnum modelAction)
	{
		List<FormaPgPessoa> formaPgList = new ArrayList<FormaPgPessoa>();
		FormaPgPessoa formaPg = new FormaPgPessoa();
		formaPg.setId(1);
		formaPg.setModelAction(PersistanceActionEnum.NONE);
		formaPgList.add(formaPg);

		formaPg = new FormaPgPessoa();
		formaPg.setId(2);
		formaPg.setModelAction(PersistanceActionEnum.NONE);
		formaPgList.add(formaPg);

		formaPg = new FormaPgPessoa();
		formaPg.setId(3);
		formaPg.setModelAction(PersistanceActionEnum.NONE);
		formaPgList.add(formaPg);

		return formaPgList;
	}

	public List<Contas> insertContasPagarReceber(PersistanceActionEnum modelAction, ContasTypeEnum tipe)
	{
		List<Contas> contasPagarList = new ArrayList<Contas>();
		Contas contasPagar = new Contas();
		contasPagar.setModelAction(modelAction);
		contasPagar.setId(1);
		contasPagar.setPessoaId(1);
		contasPagar.setDocId(100);
		contasPagar.setContasTypeEnum(tipe);
		contasPagar.setNumeroParc(3);
		contasPagar.setParcela(1);
		contasPagar.setValorOriginal(new Double(9.99));
		Date a = new Date();
		contasPagar.setDataVencimento(a.getTime());
		contasPagar.setDataGeracao(a.getTime());
		contasPagar.setDataPagamento(a.getTime());
		contasPagar.setJuros(new Double(0.99));
		contasPagar.setTaxa(new Double(0.99));
		contasPagar.setValorTotal(new Double(9.99));
		contasPagarList.add(contasPagar);

		contasPagar = new Contas();
		contasPagar.setModelAction(modelAction);
		contasPagar.setId(1);
		contasPagar.setPessoaId(1);
		contasPagar.setDocId(100);
		contasPagar.setContasTypeEnum(tipe);
		contasPagar.setNumeroParc(3);
		contasPagar.setParcela(2);
		contasPagar.setValorOriginal(new Double(9.99));
		a = new Date();
		contasPagar.setDataVencimento(a.getTime());
		contasPagar.setDataGeracao(a.getTime());
		contasPagar.setDataPagamento(a.getTime());
		contasPagar.setJuros(new Double(0.99));
		contasPagar.setTaxa(new Double(0.99));
		contasPagar.setValorTotal(new Double(9.99));
		contasPagarList.add(contasPagar);

		contasPagar = new Contas();
		contasPagar.setModelAction(modelAction);
		contasPagar.setId(1);
		contasPagar.setPessoaId(1);
		contasPagar.setDocId(100);
		contasPagar.setContasTypeEnum(tipe);
		contasPagar.setNumeroParc(3);
		contasPagar.setParcela(3);
		contasPagar.setValorOriginal(new Double(9.99));
		a = new Date();
		contasPagar.setDataVencimento(a.getTime());
		contasPagar.setDataGeracao(a.getTime());
		contasPagar.setDataPagamento(a.getTime());
		contasPagar.setJuros(new Double(0.99));
		contasPagar.setTaxa(new Double(0.99));
		contasPagar.setValorTotal(new Double(9.99));
		contasPagarList.add(contasPagar);

		return contasPagarList;
	}

	public List<ItensEspeciais> insertItensEspeciaiso(PersistanceActionEnum modelAction)
	{
		List<ItensEspeciais> itensEspeciaisList = new ArrayList<ItensEspeciais>();
		ItensEspeciais itensEspeciais = new ItensEspeciais();
		itensEspeciais.setModelAction(modelAction);
		itensEspeciais.setId(1);
		itensEspeciais.setIdNota(1);
		itensEspeciais.setNome("Teste");
		itensEspeciais.setItem(1);
		itensEspeciais.setValor(new Double(9.99));
		itensEspeciais.setBaseCalculo(new Double(9.99));
		itensEspeciais.setAliguotaICMS(new Double(9.99));
		itensEspeciais.setValorICMS(new Double(9.99));
		itensEspeciaisList.add(itensEspeciais);

		return itensEspeciaisList;
	}

	public List<NFStatus> nFStatusSocio(PersistanceActionEnum modelAction)
	{
		List<NFStatus> nFStatusList = new ArrayList<NFStatus>();
		NFStatus nFStatus = new NFStatus();
		nFStatus.setModelAction(PersistanceActionEnum.INSERT);
		nFStatus.setId(1);
		nFStatus.setStatus(new StatusNF("TRANSMITIDA"));
		Date a = new Date();
		nFStatus.setDataMudanca(a.getTime());
		nFStatusList.add(nFStatus);

		return nFStatusList;
	}

	public List<HistoricoNF> historicoNF(PersistanceActionEnum modelAction, NotaTypeEnum notaType)
	{
		List<HistoricoNF> historicoNFList = new ArrayList<HistoricoNF>();
		HistoricoNF historicoNF = new HistoricoNF();
		historicoNF.setModelAction(PersistanceActionEnum.INSERT);
		historicoNF.setId(1);
		historicoNF.setNotaId(1);
		Date a = new Date();
		historicoNF.setData(a.getTime());
		historicoNF.setNotaTypeEnum(notaType);
		historicoNFList.add(historicoNF);

		return historicoNFList;
	}

	public List<NotaFiscalItens> notaFiscalItens(PersistanceActionEnum modelAction)
	{
		List<NotaFiscalItens> notaFiscalItensList = new ArrayList<NotaFiscalItens>();
		NotaFiscalItens notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(1));
		notaFiscalItens.setQnt(new Double(9.99));
		notaFiscalItens.setVrUnitario(new Double(9.99));
		notaFiscalItens.setVrDesconto(new Double(9.99));
		notaFiscalItens.setCfop(new Cfop(1, PersistanceActionEnum.NONE));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setCfop(new Cfop(1, PersistanceActionEnum.NONE));
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(2));
		notaFiscalItens.setQnt(new Double(9.99));
		notaFiscalItens.setVrUnitario(new Double(9.99));
		notaFiscalItens.setVrDesconto(new Double(9.99));
		notaFiscalItens.setCfop(new Cfop(1, PersistanceActionEnum.NONE));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(3));
		notaFiscalItens.setQnt(new Double(9.99));
		notaFiscalItens.setVrUnitario(new Double(9.99));
		notaFiscalItens.setVrDesconto(new Double(9.99));
		notaFiscalItens.setCfop(new Cfop(1, PersistanceActionEnum.NONE));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(4));
		notaFiscalItens.setQnt(new Double(9.99));

		notaFiscalItens.setVrUnitario(new Double(9.99));

		notaFiscalItens.setVrDesconto(new Double(9.99));

		notaFiscalItens.setCfop(new Cfop(1, PersistanceActionEnum.NONE));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(5));
		notaFiscalItens.setQnt(new Double(9.99));

		notaFiscalItens.setVrUnitario(new Double(9.99));

		notaFiscalItens.setVrDesconto(new Double(9.99));

		notaFiscalItens.setCfop(new Cfop(1, PersistanceActionEnum.NONE));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);
		return notaFiscalItensList;
	}

	public NotaFiscal insertMockNotaFiscal(PersistanceActionEnum modelAction, NotaTypeEnum notaType)
	{
		NotaFiscalEntrada notaFiscal = new NotaFiscalEntrada();

		Date a = new Date();
		notaFiscal.setId(1);
		notaFiscal.setModelAction(modelAction);
		notaFiscal.setSerie("01");
		notaFiscal.setOrdem("01");
		notaFiscal.setNumero("123");
		notaFiscal.setTipo("NF");
		notaFiscal.setNfValor(new Double(9.99));
		notaFiscal.setBxEstoque(1);
		notaFiscal.setDescItens(1);
		notaFiscal.setPcCusto(1);
		notaFiscal.setDataEmissao(a.getTime());
		notaFiscal.setDataSaida(a.getTime());
		notaFiscal.setDataEntrada(a.getTime());
		notaFiscal.setModelo(1);
		notaFiscal.setTransportador(new Transportador(1));
		notaFiscal.setCfop(new Cfop(1, PersistanceActionEnum.NONE));
		notaFiscal.setConhecimentoTransporte(insertConhecimentoTransporte(modelAction));
		notaFiscal.setEmpresa(new Empresa(1));
		notaFiscal.setTributosList(insertTributacao(modelAction));
		notaFiscal.setFormaPagList(insertFormaPg(modelAction));
		notaFiscal.setNoteList(new ArrayList<Note>());
		notaFiscal.getNoteList().add(new Note("Test"));
		notaFiscal.setContaspagarList(insertContasPagarReceber(modelAction, ContasTypeEnum.PAGAR));
		// notaFiscal.setContasReceberList(insertContasPagarReceber(modelAction, ContasTypeEnum.RECEBER));
		notaFiscal.setItensEspeciais(insertItensEspeciaiso(modelAction));
		notaFiscal.setNfStatusList(nFStatusSocio(modelAction));
		notaFiscal.setHistoricoNFList(historicoNF(modelAction, notaType));
		notaFiscal.setNotaFiscalItens(notaFiscalItens(modelAction));

		return notaFiscal;

	}
}
