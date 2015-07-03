package com.prosperitasglobal.sendsolv.produto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.prosperitasglobal.controller.delegate.UtilControllerD;
import com.prosperitasglobal.sendsolv.empresa.controller.Socio;
import com.prosperitasglobal.sendsolv.model.Csosn;
import com.prosperitasglobal.sendsolv.model.Cst;
import com.prosperitasglobal.sendsolv.model.CustoItem;
import com.prosperitasglobal.sendsolv.model.EstoqueTypeEnum;
import com.prosperitasglobal.sendsolv.model.Incidencia;
import com.prosperitasglobal.sendsolv.model.LIst;
import com.prosperitasglobal.sendsolv.model.PorcaoItem;
import com.prosperitasglobal.sendsolv.model.PrecoTypeEnum;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.RentabilidadeTypeEnum;
import com.prosperitasglobal.sendsolv.model.UniMed;

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

	public Produto mockProduto(ModelAction model)
	{
		Produto produto = new Produto();

		produto.setId(1);
		produto.setCodigo("0001");
		produto.setCdbarra("0001010101110101");
		produto.setDataCreate(new Long("14327833577780"));
		produto.setProduto("Produto 0001");
		produto.setAplicacao("Descricao");
		produto.setLocalizacao("Z001,E005");
		produto.setDataValidade(new Long("14327833577780"));
		produto.setComissao("1.5");
		produto.setFracao("10");
		produto.setPesoBruto(new Double(1.5));
		produto.setPesoLiquido(new Double(1.2));
		produto.setModoUso("modo de uso");
		produto.setClassificacao(new Classificacao(1));
		produto.setUniMed(new UniMed(1));
		produto.setGrupo(new Grupo(1));
		produto.setSubGrupo(new SubGrupo(1));
		produto.setMarca(new Marca(1));
		produto.setTributacao(mockTributacao(model));
		produto.setEstoque(mockEstoque(model));
		produto.setPrecoList(mockPreco(model));
		produto.setCustoList(mockCusto(model));
		produto.setPorcaoList(mockPorcao(model));
		produto.setRentabilidadeList();
		produto.setCfopList(new ArrayList<Cfop>());
		produto.getCfopList().add(new Cfop(1));
		produto.setFornecedorList(new ArrayList<Fornecedor>());
		produto.getFornecedorList().add(new Fornecedor(1));

		return produto;

	}

	public Tributacao mockTributacao(ModelAction model)
	{
		Tributacao tributacao = new Tributacao();

		tributacao.setId;
		tributacao.setCst(new Cst(1));
		tributacao.setIcms(new Double(10.60));
		tributacao.setSt(10);
		tributacao.setMva(new Double(10.60));
		tributacao.setCsosn(new Csosn(1));
		tributacao.setIpi(50.99);
		tributacao.setIat(1);
		tributacao.setTributacao.setpisconfins(1);
		tributacao.setIncidencia(new Incidencia());
		return tributacao;
	}

	public List<Estoque> mockEstoque(ModelAction model)
	{
		List<Estoque> estoqueList = new ArrayList<Estoque>();

		Estoque estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.MINIMO);
		estoque.setUltimoMov(new Long(14327833577780));
		estoque.setQuant(new Double(10));
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.MAXIMO);
		estoque.setUltimoMov(new Long(14327833577780));
		estoque.setQuant(new Double(100));
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.INICIAL);
		estoque.setUltimoMov(new Long(14327833577780));
		estoque.setQuant(new Double(1));
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.ATUAL);
		estoque.setUltimoMov(new Long(14327833577780));
		estoque.setQuant(new Double(16));
		estoqueList.add(estoque);

		return estoqueList;
	}

	public List<TabPreco> mockPreco(ModelAction model)
	{
		List<TabPreco> precoList = new ArrayList<TabPreco>();

		TabPreco preco = new TabPreco();
		preco.setId(1);
		preco.setdataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.CUSTO);
		preco.setvalor(new Double(10.90));
		preco.setdataProInicial(new Long("14327833577780"));
		preco.setdataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setdataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.VENDA);
		preco.setvalor(new Double(19.90));
		preco.setdataProInicial(new Long("14327833577780"));
		preco.setdataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setdataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.COMPRA);
		preco.setvalor(new Double(9.90));
		preco.setdataProInicial(new Long("14327833577780"));
		preco.setdataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setdataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.PROMOCAO);
		preco.setvalor(new Double(15.99));
		preco.setdataProInicial(new Long("14327833577780"));
		preco.setdataProFinal(new Long("14327833577780"));
		precoList.add(preco);

		return precoList;
	}

	public List<Custo> mockCusto(ModelAction model)
	{
		List<Custo> custoList = new ArrayList<Custo>();

		Custo custo = new Custo();
		custo.setId(1);
		custo.setCusto(new CustoItem(1));
		custo.setValor(10);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(2);
		custo.setCusto(new CustoItem(2));
		custo.setValor(20);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(3);
		custo.setCusto(new CustoItem(3));
		custo.setValor(30);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(4);
		custo.setCusto(new CustoItem(4));
		custo.setValor(40);
		custoList.add(custo);

		return precoList;
	}

	public List<Porcao> mockPorcao(ModelAction model)
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
		porcaoList.add(custo);

		return porcaoList;
	}

	public List<Rentabilidade> mockRentabilidade(ModelAction model)
	{
		List<Rentabilidade> rentabilidadeList = new ArrayList<Rentabilidade>();
		List<RentabilidadeItem> rentabilidadeItemList = new ArrayList<RentabilidadeItem>();

		RentabilidadeItem item = new RentabilidadeItem();
		item.setId(1);
		item.setProduto(new Produto(1));
		item.setValor(new Double(10));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PRODUTO);
		rentabilidadeItemList.add(item);

		item = new RentabilidadeItem();
		item.setId(2);
		item.setProduto(new Produto(2));
		item.setValor(new Double(5));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PRODUTO);
		rentabilidadeItemList.add(item);

		item = new RentabilidadeItem();
		item.setId(3);
		item.setProduto(new Produto(3));
		item.setValor(new Double(1));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PERDA);
		rentabilidadeItemList.add(item);

		Rentabilidade rentabilidade = new Rentabilidade();
		porcao.setId(1);
		porcao.setRentabilidadeList(porcaoItemList);
		rentabilidadeList.add(custo);

		return rentabilidadeList;
	}
}
