package com.prosperitasglobal.sendsolv.notafiscal.controller;

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
import com.prosperitasglobal.sendsolv.model.FormaPg;
import com.prosperitasglobal.sendsolv.model.HistoricoNF;
import com.prosperitasglobal.sendsolv.model.Incidencia;
import com.prosperitasglobal.sendsolv.model.ItensEspeciais;
import com.prosperitasglobal.sendsolv.model.Marca;
import com.prosperitasglobal.sendsolv.model.NFStatus;
import com.prosperitasglobal.sendsolv.model.NotaFiscalEntrada;
import com.prosperitasglobal.sendsolv.model.NotaFiscalItens;
import com.prosperitasglobal.sendsolv.model.NotaTypeEnum;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.StatusNF;
import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.Tributacao;
import com.prosperitasglobal.sendsolv.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalEntradaResponse;
import com.prosperitasglobal.sendsolv.model.response.NotaFiscalResponse;
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
	private INotaFiscalBAI locationBAI;

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public INotaFiscalBAI getNotaFiscalBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setNotaFiscalBAI(INotaFiscalBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * NotaFiscal edit mav.
	 *
	 * @param locationId the location id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView locationEditMAV(Integer locationId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(locationId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchNotaFiscalById(new FetchByIdRequest(locationId))));

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
	 * Fetch location by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the location response
	 */
	public NotaFiscalResponse fetchNotaFiscalByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		NotaFiscalResponse locationResponse = new NotaFiscalResponse();
		try
		{

			// locationResponse = Mock();
			// getNotaFiscalBAI().fetchNotaFiscalByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	/**
	 * Fetch location by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	public NotaFiscalResponse fetchNotaFiscalById(FetchByIdRequest fetchByIdRequest)
	{

		NotaFiscalResponse locationResponse = new NotaFiscalResponse();
		try
		{

			// locationResponse = MockById();
			// getNotaFiscalBAI().fetchNotaFiscalById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return locationResponse;
	}

	/**
	 * Insert one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	public NotaFiscalEntradaResponse insert(NotaFiscalEntradaMaintenanceRequest locationRequest)
	{
		NotaFiscalEntradaResponse locationResponse = new NotaFiscalEntradaResponse();

		try
		{
			locationRequest.setNotafiscalList(insertMockNotaFiscal(PersistanceActionEnum.INSERT));
			locationRequest.getNotafiscalList().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getNotaFiscalBAI().insertNotaFiscalEntrada(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

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
		conhecimentoTransporte.setEspecie("ddd");
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

	public List<FormaPg> insertFormaPg(PersistanceActionEnum modelAction)
	{
		List<FormaPg> formaPgList = new ArrayList<FormaPg>();
		FormaPg formaPg = new FormaPg(1, PersistanceActionEnum.NONE);
		formaPgList.add(formaPg);

		formaPg = new FormaPg(2, PersistanceActionEnum.NONE);
		formaPgList.add(formaPg);

		formaPg = new FormaPg(3, PersistanceActionEnum.NONE);
		formaPgList.add(formaPg);

		return formaPgList;
	}

	public List<Contas> insertContasPagarReceber(PersistanceActionEnum modelAction, ContasTypeEnum tipe)
	{
		List<Contas> contasPagarList = new ArrayList<Contas>();
		Contas contasPagar = new Contas();
		contasPagar.setModelAction(modelAction);
		contasPagar.setId(1);
		contasPagar.setIdFornecedor(1);
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
		contasPagar.setIdFornecedor(1);
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
		contasPagar.setIdFornecedor(1);
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

	public List<HistoricoNF> historicoNF(PersistanceActionEnum modelAction)
	{
		List<HistoricoNF> historicoNFList = new ArrayList<HistoricoNF>();
		HistoricoNF historicoNF = new HistoricoNF();
		historicoNF.setModelAction(PersistanceActionEnum.INSERT);
		historicoNF.setId(1);
		historicoNF.setNotaId(1);
		Date a = new Date();
		historicoNF.setData(a.getTime());
		historicoNF.setNotaTypeEnumList(new ArrayList<NotaTypeEnum>());
		historicoNF.getNotaTypeEnumList().add(NotaTypeEnum.ENTRADA);
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

	public NotaFiscalEntrada insertMockNotaFiscal(PersistanceActionEnum modelAction)
	{
		NotaFiscalEntrada notaFiscal = new NotaFiscalEntrada();

		Date a = new Date();
		notaFiscal.setId(1);
		notaFiscal.setNotaType(NotaTypeEnum.ENTRADA);
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
		notaFiscal.setHistoricoNFList(historicoNF(modelAction));
		notaFiscal.setNotaFiscalItens(notaFiscalItens(modelAction));

		return notaFiscal;

	}
}
