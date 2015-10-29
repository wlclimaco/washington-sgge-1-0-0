package com.qat.webdaptive.controller.produto.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.bai.IProdutoBAI;
import com.prosperitasglobal.sendsolv.model.CfopPessoa;
import com.prosperitasglobal.sendsolv.model.Classificacao;
import com.prosperitasglobal.sendsolv.model.Csosn;
import com.prosperitasglobal.sendsolv.model.Cst;
import com.prosperitasglobal.sendsolv.model.Custo;
import com.prosperitasglobal.sendsolv.model.CustoItem;
import com.prosperitasglobal.sendsolv.model.Estoque;
import com.prosperitasglobal.sendsolv.model.EstoqueTypeEnum;
import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.GrupoProd;
import com.prosperitasglobal.sendsolv.model.Incidencia;
import com.prosperitasglobal.sendsolv.model.MarcaProd;
import com.prosperitasglobal.sendsolv.model.Porcao;
import com.prosperitasglobal.sendsolv.model.PorcaoItem;
import com.prosperitasglobal.sendsolv.model.PrecoTypeEnum;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.Rentabilidade;
import com.prosperitasglobal.sendsolv.model.RentabilidadeItens;
import com.prosperitasglobal.sendsolv.model.RentabilidadeTypeEnum;
import com.prosperitasglobal.sendsolv.model.SubGrupoProd;
import com.prosperitasglobal.sendsolv.model.TabPreco;
import com.prosperitasglobal.sendsolv.model.Tributacao;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.UniMedProd;
import com.prosperitasglobal.sendsolv.model.request.CfopInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClassificacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.GrupoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MarcaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.SubGrupoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.CfopResponse;
import com.prosperitasglobal.sendsolv.model.response.ClassificacaoResponse;
import com.prosperitasglobal.sendsolv.model.response.GrupoResponse;
import com.prosperitasglobal.sendsolv.model.response.MarcaResponse;
import com.prosperitasglobal.sendsolv.model.response.ProdutoResponse;
import com.prosperitasglobal.sendsolv.model.response.SubGrupoResponse;
import com.prosperitasglobal.sendsolv.model.response.UniMedResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.validation.ValidationUtil;

public class ProdutoBaseController extends UtilControllerD
{

	/** The Response constants. */
	public static final String RESPONSE = "response";

	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProdutoBaseController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ProdutoBaseController";

	/** The Constant ENROLLED_MEMBERS. */
	private static final String ENROLLED_MEMBERS = "enrolled_members";

	/** The Produto BAI. */
	private IProdutoBAI produtoBAI;

	/**
	 * Gets the produto bai.
	 *
	 * @return the produto bai
	 */
	public IProdutoBAI getProdutoBAI()
	{
		return produtoBAI;
	}

	/**
	 * Sets the produto bai.
	 *
	 * @param produtoBAI the produto bai
	 */
	@Resource
	public void setProdutoBAI(IProdutoBAI produtoBAI)
	{
		this.produtoBAI = produtoBAI;
	}

	/**
	 * Produto edit mav.
	 *
	 * @param produtoId the produto id
	 * @param returnViewName the return view name
	 * @param isSelect the is select
	 * @return the model and view
	 */
	protected ModelAndView produtoEditMAV(Integer produtoId, String returnViewName, Boolean isSelect,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);

		try
		{

			if (isSelect)
			{
				// modelAndView = listSelectBusiness(modelAndView, request);
			}
			if (!ValidationUtil.isNullOrZero(produtoId))
			{

				modelAndView.addObject(RESPONSE,
						getMapper().writeValueAsString(fetchProdutoById(new FetchByIdRequest(produtoId))));

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
	 * Fetch produto by request.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the produto response
	 */
	public ProdutoResponse fetchProdutoByRequest(ProdutoInquiryRequest pagedInquiryRequest)
	{

		ProdutoResponse produtoResponse = new ProdutoResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchProdutoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	public CfopResponse fetchCfopByRequest(CfopInquiryRequest pagedInquiryRequest)
	{

		CfopResponse produtoResponse = new CfopResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchCfopByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	// unimed
	public UniMedResponse fetchUniMedByRequest(UniMedInquiryRequest pagedInquiryRequest)
	{

		UniMedResponse produtoResponse = new UniMedResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchUniMedByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	// grupo
	public GrupoResponse fetchGrupoByRequest(GrupoInquiryRequest pagedInquiryRequest)
	{

		GrupoResponse produtoResponse = new GrupoResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchGrupoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	// subgrupo
	public SubGrupoResponse fetchSubGrupoByRequest(SubGrupoInquiryRequest pagedInquiryRequest)
	{

		SubGrupoResponse produtoResponse = new SubGrupoResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchSubGrupoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	// marca
	public MarcaResponse fetchMarcaByRequest(MarcaInquiryRequest pagedInquiryRequest)
	{

		MarcaResponse produtoResponse = new MarcaResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchMarcaByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	// classificacao
	public ClassificacaoResponse fetchClassificacaoByRequest(ClassificacaoInquiryRequest pagedInquiryRequest)
	{

		ClassificacaoResponse produtoResponse = new ClassificacaoResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchClassificacaoByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	// cfop
	public CfopResponse fetchProdutoByRequest(CfopInquiryRequest pagedInquiryRequest)
	{

		CfopResponse produtoResponse = new CfopResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchCfopByRequest(pagedInquiryRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	/**
	 * Fetch produto by id.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the produto response
	 */
	public ProdutoResponse fetchProdutoById(FetchByIdRequest fetchByIdRequest)
	{

		ProdutoResponse produtoResponse = new ProdutoResponse();
		try
		{

			produtoResponse = getProdutoBAI().fetchProdutoById(fetchByIdRequest);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			}
		}

		return produtoResponse;
	}

	public ProdutoResponse insert(ProdutoMaintenanceRequest locationRequest)
	{
		ProdutoResponse locationResponse = new ProdutoResponse();

		try
		{
			locationRequest.setProduto(mockProduto(PersistanceActionEnum.INSERT));
			locationRequest.getProduto().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getProdutoBAI().insertProduto(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	public Produto mockProduto(PersistanceActionEnum model)
	{
		Produto produto = new Produto();

		produto.setId(1);
		produto.setCodigo("0001");
		produto.setEmprId(1);
		produto.setCdBarras("0001010101110101");
		produto.setDataCreate(new Long("14327833577780"));
		produto.setProduto("Produto 0001");
		produto.setAplicacao("Descricao");
		produto.setLocalizacao("Z001,E005");
		produto.setDataValidade(new Long("14327833577780"));
		produto.setComissao("1.5");
		produto.setFracao("10");
		produto.setPesoBruto(new Double(1.5));
		produto.setPesoLiquido(new Double(1.2));
		produto.setClassificacao(new Classificacao(1));
		produto.setUniMed(new UniMedProd(1));
		produto.setGrupo(new GrupoProd(1));
		produto.setSubGrupo(new SubGrupoProd(1));
		produto.setMarca(new MarcaProd(1));
		produto.setTributacao(mockTributacao(model));
		produto.setEstoqueList(mockEstoque(model));
		produto.setPrecoList(mockPreco(model));
		produto.setCustoList(mockCusto(model));
		produto.setPorcaoList(mockPorcao(model));
		produto.setRentabilidadeList(mockRentabilidade(model));
		produto.setCfopList(new ArrayList<CfopPessoa>());
		produto.getCfopList().add(new CfopPessoa(1, PersistanceActionEnum.NONE));
		produto.setFornecedorList(new ArrayList<Fornecedor>());
		produto.getFornecedorList().add(new Fornecedor());

		return produto;

	}

	public Tributacao mockTributacao(PersistanceActionEnum model)
	{
		Tributacao tributacao = new Tributacao();

		tributacao.setId(1);
		tributacao.setCst(new Cst(1));
		tributacao.setIcms(new Double(10.60));
		tributacao.setSt(10);
		tributacao.setMva(new Double(10.60));
		tributacao.setCsosn(new Csosn(1));
		tributacao.setIpi(50.99);
		tributacao.setIat(1);
		// tributacao.setTributacao.setpisconfins(1);
		tributacao.setIncidencia(new Incidencia());
		return tributacao;
	}

	public List<Estoque> mockEstoque(PersistanceActionEnum model)
	{
		List<Estoque> estoqueList = new ArrayList<Estoque>();

		Estoque estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.MINIMO);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(10));
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.MAXIMO);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(100));
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.INICIAL);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(1));
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.ATUAL);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(16));
		estoqueList.add(estoque);

		return estoqueList;
	}

	public List<TabPreco> mockPreco(PersistanceActionEnum model)
	{
		List<TabPreco> precoList = new ArrayList<TabPreco>();

		TabPreco preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.CUSTO);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.VENDA);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.COMPRA);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.PROMOCAO);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		return precoList;
	}

	public List<Custo> mockCusto(PersistanceActionEnum model)
	{
		List<Custo> custoList = new ArrayList<Custo>();

		Custo custo = new Custo();
		custo.setId(1);
		custo.setCusto(new CustoItem(1));
		custo.setValor((double)10);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(2);
		custo.setCusto(new CustoItem(2));
		custo.setValor((double)20);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(3);
		custo.setCusto(new CustoItem(3));
		custo.setValor((double)30);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(4);
		custo.setCusto(new CustoItem(4));
		custo.setValor((double)40);
		custoList.add(custo);

		return custoList;
	}

	public List<Porcao> mockPorcao(PersistanceActionEnum model)
	{
		List<Porcao> porcaoList = new ArrayList<Porcao>();
		List<PorcaoItem> porcaoItemList = new ArrayList<PorcaoItem>();

		PorcaoItem item = new PorcaoItem();
		item.setId(1);
		item.setNome("AMINIACIDOS");
		item.setPorcao(new Double(20));
		item.setVd(new Double(20));
		item.setUnimed(new UniMed(2));
		porcaoItemList.add(item);

		item = new PorcaoItem();
		item.setId(2);
		item.setNome("PROTEINA");
		item.setPorcao(new Double(20));
		item.setVd(new Double(20));
		item.setUnimed(new UniMed(2));
		porcaoItemList.add(item);

		item = new PorcaoItem();
		item.setId(3);
		item.setNome("GORDURA");
		item.setPorcao(new Double(20));
		item.setVd(new Double(20));
		item.setUnimed(new UniMed(2));
		porcaoItemList.add(item);

		Porcao porcao = new Porcao();
		porcao.setId(1);
		porcao.setPorcaoItens(porcaoItemList);
		porcao.setValor(new Double(100));
		porcaoList.add(porcao);

		return porcaoList;
	}

	public List<Rentabilidade> mockRentabilidade(PersistanceActionEnum model)
	{
		List<Rentabilidade> rentabilidadeList = new ArrayList<Rentabilidade>();
		List<RentabilidadeItens> rentabilidadeItensList = new ArrayList<RentabilidadeItens>();

		RentabilidadeItens item = new RentabilidadeItens();
		item.setId(1);
		item.setProduto(new Produto(1));
		item.setValor(new Double(10));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PRODUTO);
		rentabilidadeItensList.add(item);

		item = new RentabilidadeItens();
		item.setId(2);
		item.setProduto(new Produto(2));
		item.setValor(new Double(5));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PRODUTO);
		rentabilidadeItensList.add(item);

		item = new RentabilidadeItens();
		item.setId(3);
		item.setProduto(new Produto(3));
		item.setValor(new Double(1));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PERDA);
		rentabilidadeItensList.add(item);

		Rentabilidade rentabilidade = new Rentabilidade();
		rentabilidade.setId(1);
		rentabilidade.setRentabilidadeList(rentabilidadeItensList);
		rentabilidadeList.add(rentabilidade);

		return rentabilidadeList;
	}
}
