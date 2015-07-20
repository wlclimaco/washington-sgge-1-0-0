package com.prosperitasglobal.sendsolv.notafiscal.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;

/**
 * The Class EmpresaBaseController.
 */

/**
 * @author Flavio Tosta.
 *
 */
public class NotaFiscalBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "EmpresaBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Empresa BAI. */
	private IEmpresaBAI locationBAI;

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public IEmpresaBAI getEmpresaBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setEmpresaBAI(IEmpresaBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Empresa edit mav.
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
						getMapper().writeValueAsString(fetchEmpresaById(new FetchByIdRequest(locationId))));

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
	public EmpresaResponse fetchEmpresaByRequest(PagedInquiryRequest pagedInquiryRequest)
	{

		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			// locationResponse = Mock();
			// getEmpresaBAI().fetchEmpresaByRequest(pagedInquiryRequest);

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
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest fetchByIdRequest)
	{

		EmpresaResponse locationResponse = new EmpresaResponse();
		try
		{

			// locationResponse = MockById();
			// getEmpresaBAI().fetchEmpresaById(fetchByIdRequest);

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
	public NotaFiscalResponse insert(NotaFiscalMaintenanceRequest locationRequest)
	{
		NotaFiscalResponse locationResponse = new NotaFiscalResponse();

		try
		{
			locationRequest.setNotaFiscal(insertMockNotaFiscal);
			locationRequest.getEmpresa().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getNotaFiscalBAI().insertNotaFiscal(locationRequest);
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
		conhecimentoTransporte.setEestado(new Estado(1));
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

	public List<ContasPagar> insertContasPagarReceber(PersistanceActionEnum modelAction, ContasTypeEnum tipe)
	{
		List<ContasPagar> contasPagarList = new ArrayList<ContasPagar>();
		ContasPagar contasPagar = new ContasPagar();
		contasPagar.setModelAction(modelAction);
		contasPagar.setId(1);
		contasPagar.setidFornecedor(1);
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

		contasPagar = new ContasPagar();
		contasPagar.setModelAction(modelAction);
		contasPagar.setId(1);
		contasPagar.setidFornecedor(1);
		contasPagar.setDocId(100);
		contasPagar.setContasTypeEnum(tipe);
		contasPagar.setNumeroParc(3);
		contasPagar.setParcela(2);
		contasPagar.setValorOriginal(new Double(9.99));
		Date a = new Date();
		contasPagar.setDataVencimento(a.getTime());
		contasPagar.setDataGeracao(a.getTime());
		contasPagar.setDataPagamento(a.getTime());
		contasPagar.setJuros(new Double(0.99));
		contasPagar.setTaxa(new Double(0.99));
		contasPagar.setValorTotal(new Double(9.99));
		contasPagarList.add(contasPagar);

		contasPagar = new ContasPagar();
		contasPagar.setModelAction(modelAction);
		contasPagar.setId(1);
		contasPagar.setidFornecedor(1);
		contasPagar.setDocId(100);
		contasPagar.setContasTypeEnum(tipe);
		contasPagar.setNumeroParc(3);
		contasPagar.setParcela(3);
		contasPagar.setValorOriginal(new Double(9.99));
		Date a = new Date();
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
		itensEspeciais.setvalorICMS(new Double(9.99));
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
		notaFiscalItens.setNotaId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(1));
		notaFiscalItens.setQnt(new Double(9.99));
		notaFiscalItens.setVrUnitario(new Double(9.99));
		notaFiscalItens.setVrDesconto(new Double(9.99));
		notaFiscalItens.setCfop(new Cfop(1));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setNotaId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(2));
		notaFiscalItens.setQnt(new Double(9.99));
		notaFiscalItens.setVrUnitario(new Double(9.99));
		notaFiscalItens.setVrDesconto(new Double(9.99));
		notaFiscalItens.setCfop(new Cfop(1));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setNotaId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(3));
		notaFiscalItens.setQnt(new Double(9.99));
		notaFiscalItens.setVrUnitario(new Double(9.99));
		notaFiscalItens.setVrDesconto(new Double(9.99));
		notaFiscalItens.setCfop(new Cfop(1));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setNotaId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(4));
		notaFiscalItens.setQnt(new Double(9.99));

		notaFiscalItens.setVrUnitario(new Double(9.99));

		notaFiscalItens.setVrDesconto(new Double(9.99));

		notaFiscalItens.setCfop(new Cfop(1));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);

		notaFiscalItens = new NotaFiscalItens();
		notaFiscalItens.setModelAction(PersistanceActionEnum.INSERT);
		notaFiscalItens.setId(1);
		notaFiscalItens.setNotaId(1);
		notaFiscalItens.setIdNota(1);
		notaFiscalItens.setProduto(new Produto(5));
		notaFiscalItens.setQnt(new Double(9.99));

		notaFiscalItens.setVrUnitario(new Double(9.99));

		notaFiscalItens.setVrDesconto(new Double(9.99));

		notaFiscalItens.setCfop(new Cfop(1));
		notaFiscalItens.setCrt(new Cst(1));
		notaFiscalItens.setClassificacao(new Classificacao(1));
		notaFiscalItens.setTributosList(insertTributacao(modelAction));
		notaFiscalItensList.add(notaFiscalItens);
		return notaFiscalItensList;
	}

	public NotaFiscal insertMockNotaFiscal(PersistanceActionEnum modelAction)
	{
		NotaFiscal notaFiscal = new NotaFiscal();

		Date a = new Date();
		notaFiscal.setId(1);
		notaFiscal.setNotaType(NotaTypeEnum.ENTRADA);
		notaFiscal.setSerie("01");
		notaFiscal.setOrdem("01");
		notaFiscal.setNumero(123);
		notaFiscal.setTipo("NF");
		notaFiscal.setNfValor(new Double(9.99));
		notaFiscal.setBxEstoque(Boolean.TRUE);
		notaFiscal.setDescItens(Boolean.TRUE);
		notaFiscal.setPcCusto(Boolean.TRUE);
		notaFiscal.setDataEmissao(a.getTime());
		notaFiscal.setDataSaida(a.getTime());
		notaFiscal.setDataEntrada(a.getTime());
		notaFiscal.setModelo("NF");
		notaFiscal.setTransportador(new Transportador(1));
		notaFiscal.setCfop(new Cfop(1));
		notaFiscal.setConhecimentoTransporte(insertConhecimentoTransporte(modelAction));
		notaFiscal.setEmpresa(new Empresa(1));
		notaFiscal.setTributosList(insertTributacao(modelAction));
		notaFiscal.setFormaPagList(insertFormaPg(modelAction));
		notaFiscal.setNoteList(new ArrayList<Note>());
		notaFiscal.getNoteList().add(new Note("Test"));
		notaFiscal.setContaspagarList(insertContasPagarReceber(modelAction, ContasTypeEnum.PAGAR));
		notaFiscal.setContasReceberList(insertContasPagarReceber(modelAction, ContasTypeEnum.RECEBER));
		notaFiscal.setItensEspeciais(insertItensEspeciaiso(modelAction));
		notaFiscal.setNfStatusList(nFStatusSocio(modelAction));
		notaFiscal.setHistoricoNFList(historicoNF(modelAction));
		notaFiscal.setNotaFiscalItens(notaFiscalItens(modelAction));

		return notaFiscal;

	}
}
