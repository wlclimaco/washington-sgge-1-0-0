/** create by system gera-java version 1.0.0 13/05/2016 17:59 : 56*/
package com.qat.samples.sysmgmt.bar.Produto;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.CustoItens;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.MarcaProduto;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.PorcaoItens;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoParent;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoParentInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface ProdutoBAR.. (Data Access Component - DAC)
 */
public interface IProdutoBAR
{

	/**
	 * Fetch produtoparent by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public ProdutoParent fetchProdutoParentById(FetchByIdRequest request);

	/**
* Insert produtoparent.
*
* @param produtoparent the produtoparent
*
* @return the internal response
*/
	public InternalResponse insertProdutoParent(ProdutoParent produtoparent);

	/**
* Update produtoparent.
*
* @param produtoparent the produtoparent
*
* @return the internal response
*/
	public InternalResponse updateProdutoParent(ProdutoParent produtoparent);

	/**
* Delete produtoparent.
*
* @param produtoparent the produtoparent
*
* @return the internal response
*/
	public InternalResponse deleteProdutoParentById(ProdutoParent produtoparent);

	/**
* Delete all produtoparents.
*
* @return the internal response
*/
	public InternalResponse deleteAllProdutoParents();

	/**
* Fetch all produtoparents.
*
* @return the list< produtoparent>
*/
	public InternalResultsResponse<ProdutoParent> fetchAllProdutoParents(ProdutoParent  produtoparent);

	/**
* Fetch produtoparents by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ProdutoParent> fetchProdutoParentsByRequest(ProdutoParentInquiryRequest request);

	/**
	 * Fetch produto by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Produto fetchProdutoById(FetchByIdRequest request);

	/**
* Insert produto.
*
* @param produto the produto
*
* @return the internal response
*/
	public InternalResponse insertProduto(Produto produto);

	/**
* Update produto.
*
* @param produto the produto
*
* @return the internal response
*/
	public InternalResponse updateProduto(Produto produto);

	/**
* Delete produto.
*
* @param produto the produto
*
* @return the internal response
*/
	public InternalResponse deleteProdutoById(Produto produto);

	/**
* Delete all produtos.
*
* @return the internal response
*/
	public InternalResponse deleteAllProdutos();

	/**
* Fetch all produtos.
*
* @return the list< produto>
*/
	public InternalResultsResponse<Produto> fetchAllProdutos(Produto  produto);

	/**
* Fetch produtos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Produto> fetchProdutosByRequest(ProdutoInquiryRequest request);

	/**
	 * Fetch cfop by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Cfop fetchCfopById(FetchByIdRequest request);

	/**
* Insert cfop.
*
* @param cfop the cfop
*
* @return the internal response
*/
	public InternalResponse insertCfop(Cfop cfop);

	/**
* Update cfop.
*
* @param cfop the cfop
*
* @return the internal response
*/
	public InternalResponse updateCfop(Cfop cfop);

	/**
* Delete cfop.
*
* @param cfop the cfop
*
* @return the internal response
*/
	public InternalResponse deleteCfopById(Cfop cfop);

	/**
* Delete all cfops.
*
* @return the internal response
*/
	public InternalResponse deleteAllCfops();

	/**
* Fetch all cfops.
*
* @return the list< cfop>
*/
	public InternalResultsResponse<Cfop> fetchAllCfops(Cfop  cfop);

	/**
* Fetch cfops by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request);

	/**
	 * Fetch marca by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Marca fetchMarcaById(FetchByIdRequest request);

	/**
* Insert marca.
*
* @param marca the marca
*
* @return the internal response
*/
	public InternalResponse insertMarca(Marca marca);

	/**
* Update marca.
*
* @param marca the marca
*
* @return the internal response
*/
	public InternalResponse updateMarca(Marca marca);

	/**
* Delete marca.
*
* @param marca the marca
*
* @return the internal response
*/
	public InternalResponse deleteMarcaById(Marca marca);

	/**
* Delete all marcas.
*
* @return the internal response
*/
	public InternalResponse deleteAllMarcas();

	/**
* Fetch all marcas.
*
* @return the list< marca>
*/
	public InternalResultsResponse<Marca> fetchAllMarcas(Marca  marca);

	/**
* Fetch marcas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Marca> fetchMarcasByRequest(MarcaInquiryRequest request);

	/**
	 * Fetch marcaproduto by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public MarcaProduto fetchMarcaProdutoById(FetchByIdRequest request);

	/**
* Insert marcaproduto.
*
* @param marcaproduto the marcaproduto
*
* @return the internal response
*/
	public InternalResponse insertMarcaProduto(MarcaProduto marcaproduto);

	/**
* Update marcaproduto.
*
* @param marcaproduto the marcaproduto
*
* @return the internal response
*/
	public InternalResponse updateMarcaProduto(MarcaProduto marcaproduto);

	/**
* Delete marcaproduto.
*
* @param marcaproduto the marcaproduto
*
* @return the internal response
*/
	public InternalResponse deleteMarcaProdutoById(MarcaProduto marcaproduto);

	/**
* Delete all marcaprodutos.
*
* @return the internal response
*/
	public InternalResponse deleteAllMarcaProdutos();

	/**
* Fetch all marcaprodutos.
*
* @return the list< marcaproduto>
*/
	public InternalResultsResponse<MarcaProduto> fetchAllMarcaProdutos(MarcaProduto  marcaproduto);

	/**
* Fetch marcaprodutos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<MarcaProduto> fetchMarcaProdutosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch grupo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Grupo fetchGrupoById(FetchByIdRequest request);

	/**
* Insert grupo.
*
* @param grupo the grupo
*
* @return the internal response
*/
	public InternalResponse insertGrupo(Grupo grupo);

	/**
* Update grupo.
*
* @param grupo the grupo
*
* @return the internal response
*/
	public InternalResponse updateGrupo(Grupo grupo);

	/**
* Delete grupo.
*
* @param grupo the grupo
*
* @return the internal response
*/
	public InternalResponse deleteGrupoById(Grupo grupo);

	/**
* Delete all grupos.
*
* @return the internal response
*/
	public InternalResponse deleteAllGrupos();

	/**
* Fetch all grupos.
*
* @return the list< grupo>
*/
	public InternalResultsResponse<Grupo> fetchAllGrupos(Grupo  grupo);

	/**
* Fetch grupos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Grupo> fetchGruposByRequest(GrupoInquiryRequest request);

	/**
	 * Fetch subgrupo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public SubGrupo fetchSubGrupoById(FetchByIdRequest request);

	/**
* Insert subgrupo.
*
* @param subgrupo the subgrupo
*
* @return the internal response
*/
	public InternalResponse insertSubGrupo(SubGrupo subgrupo);

	/**
* Update subgrupo.
*
* @param subgrupo the subgrupo
*
* @return the internal response
*/
	public InternalResponse updateSubGrupo(SubGrupo subgrupo);

	/**
* Delete subgrupo.
*
* @param subgrupo the subgrupo
*
* @return the internal response
*/
	public InternalResponse deleteSubGrupoById(SubGrupo subgrupo);

	/**
* Delete all subgrupos.
*
* @return the internal response
*/
	public InternalResponse deleteAllSubGrupos();

	/**
* Fetch all subgrupos.
*
* @return the list< subgrupo>
*/
	public InternalResultsResponse<SubGrupo> fetchAllSubGrupos(SubGrupo  subgrupo);

	/**
* Fetch subgrupos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<SubGrupo> fetchSubGruposByRequest(SubGrupoInquiryRequest request);

	/**
	 * Fetch unimed by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public UniMed fetchUniMedById(FetchByIdRequest request);

	/**
* Insert unimed.
*
* @param unimed the unimed
*
* @return the internal response
*/
	public InternalResponse insertUniMed(UniMed unimed);

	/**
* Update unimed.
*
* @param unimed the unimed
*
* @return the internal response
*/
	public InternalResponse updateUniMed(UniMed unimed);

	/**
* Delete unimed.
*
* @param unimed the unimed
*
* @return the internal response
*/
	public InternalResponse deleteUniMedById(UniMed unimed);

	/**
* Delete all unimeds.
*
* @return the internal response
*/
	public InternalResponse deleteAllUniMeds();

	/**
* Fetch all unimeds.
*
* @return the list< unimed>
*/
	public InternalResultsResponse<UniMed> fetchAllUniMeds(UniMed  unimed);

	/**
* Fetch unimeds by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<UniMed> fetchUniMedsByRequest(UniMedInquiryRequest request);

	/**
	 * Fetch tributacao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Tributacao fetchTributacaoById(FetchByIdRequest request);

	/**
* Insert tributacao.
*
* @param tributacao the tributacao
*
* @return the internal response
*/
	public InternalResponse insertTributacao(Tributacao tributacao);

	/**
* Update tributacao.
*
* @param tributacao the tributacao
*
* @return the internal response
*/
	public InternalResponse updateTributacao(Tributacao tributacao);

	/**
* Delete tributacao.
*
* @param tributacao the tributacao
*
* @return the internal response
*/
	public InternalResponse deleteTributacaoById(Tributacao tributacao);

	/**
* Delete all tributacaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllTributacaos();

	/**
* Fetch all tributacaos.
*
* @return the list< tributacao>
*/
	public InternalResultsResponse<Tributacao> fetchAllTributacaos(Tributacao  tributacao);

	/**
* Fetch tributacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Tributacao> fetchTributacaosByRequest(TributacaoInquiryRequest request);

	/**
	 * Fetch custo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Custo fetchCustoById(FetchByIdRequest request);

	/**
* Insert custo.
*
* @param custo the custo
*
* @return the internal response
*/
	public InternalResponse insertCusto(Custo custo);

	/**
* Update custo.
*
* @param custo the custo
*
* @return the internal response
*/
	public InternalResponse updateCusto(Custo custo);

	/**
* Delete custo.
*
* @param custo the custo
*
* @return the internal response
*/
	public InternalResponse deleteCustoById(Custo custo);

	/**
* Delete all custos.
*
* @return the internal response
*/
	public InternalResponse deleteAllCustos();

	/**
* Fetch all custos.
*
* @return the list< custo>
*/
	public InternalResultsResponse<Custo> fetchAllCustos(Custo  custo);

	/**
* Fetch custos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Custo> fetchCustosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch custoitens by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public CustoItens fetchCustoItensById(FetchByIdRequest request);

	/**
* Insert custoitens.
*
* @param custoitens the custoitens
*
* @return the internal response
*/
	public InternalResponse insertCustoItens(CustoItens custoitens);

	/**
* Update custoitens.
*
* @param custoitens the custoitens
*
* @return the internal response
*/
	public InternalResponse updateCustoItens(CustoItens custoitens);

	/**
* Delete custoitens.
*
* @param custoitens the custoitens
*
* @return the internal response
*/
	public InternalResponse deleteCustoItensById(CustoItens custoitens);

	/**
* Delete all custoitenss.
*
* @return the internal response
*/
	public InternalResponse deleteAllCustoItenss();

	/**
* Fetch all custoitenss.
*
* @return the list< custoitens>
*/
	public InternalResultsResponse<CustoItens> fetchAllCustoItenss(CustoItens  custoitens);

	/**
* Fetch custoitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CustoItens> fetchCustoItenssByRequest(PagedInquiryRequest request);

	/**
	 * Fetch estoque by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Estoque fetchEstoqueById(FetchByIdRequest request);

	/**
* Insert estoque.
*
* @param estoque the estoque
*
* @return the internal response
*/
	public InternalResponse insertEstoque(Estoque estoque);

	/**
* Update estoque.
*
* @param estoque the estoque
*
* @return the internal response
*/
	public InternalResponse updateEstoque(Estoque estoque);

	/**
* Delete estoque.
*
* @param estoque the estoque
*
* @return the internal response
*/
	public InternalResponse deleteEstoqueById(Estoque estoque);

	/**
* Delete all estoques.
*
* @return the internal response
*/
	public InternalResponse deleteAllEstoques();

	/**
* Fetch all estoques.
*
* @return the list< estoque>
*/
	public InternalResultsResponse<Estoque> fetchAllEstoques(Estoque  estoque);

	/**
* Fetch estoques by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Estoque> fetchEstoquesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch porcao by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Porcao fetchPorcaoById(FetchByIdRequest request);

	/**
* Insert porcao.
*
* @param porcao the porcao
*
* @return the internal response
*/
	public InternalResponse insertPorcao(Porcao porcao);

	/**
* Update porcao.
*
* @param porcao the porcao
*
* @return the internal response
*/
	public InternalResponse updatePorcao(Porcao porcao);

	/**
* Delete porcao.
*
* @param porcao the porcao
*
* @return the internal response
*/
	public InternalResponse deletePorcaoById(Porcao porcao);

	/**
* Delete all porcaos.
*
* @return the internal response
*/
	public InternalResponse deleteAllPorcaos();

	/**
* Fetch all porcaos.
*
* @return the list< porcao>
*/
	public InternalResultsResponse<Porcao> fetchAllPorcaos(Porcao  porcao);

	/**
* Fetch porcaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Porcao> fetchPorcaosByRequest(PagedInquiryRequest request);

	/**
	 * Fetch porcaoitens by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public PorcaoItens fetchPorcaoItensById(FetchByIdRequest request);

	/**
* Insert porcaoitens.
*
* @param porcaoitens the porcaoitens
*
* @return the internal response
*/
	public InternalResponse insertPorcaoItens(PorcaoItens porcaoitens);

	/**
* Update porcaoitens.
*
* @param porcaoitens the porcaoitens
*
* @return the internal response
*/
	public InternalResponse updatePorcaoItens(PorcaoItens porcaoitens);

	/**
* Delete porcaoitens.
*
* @param porcaoitens the porcaoitens
*
* @return the internal response
*/
	public InternalResponse deletePorcaoItensById(PorcaoItens porcaoitens);

	/**
* Delete all porcaoitenss.
*
* @return the internal response
*/
	public InternalResponse deleteAllPorcaoItenss();

	/**
* Fetch all porcaoitenss.
*
* @return the list< porcaoitens>
*/
	public InternalResultsResponse<PorcaoItens> fetchAllPorcaoItenss(PorcaoItens  porcaoitens);

	/**
* Fetch porcaoitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<PorcaoItens> fetchPorcaoItenssByRequest(PagedInquiryRequest request);

	/**
	 * Fetch rentabilidade by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Rentabilidade fetchRentabilidadeById(FetchByIdRequest request);

	/**
* Insert rentabilidade.
*
* @param rentabilidade the rentabilidade
*
* @return the internal response
*/
	public InternalResponse insertRentabilidade(Rentabilidade rentabilidade);

	/**
* Update rentabilidade.
*
* @param rentabilidade the rentabilidade
*
* @return the internal response
*/
	public InternalResponse updateRentabilidade(Rentabilidade rentabilidade);

	/**
* Delete rentabilidade.
*
* @param rentabilidade the rentabilidade
*
* @return the internal response
*/
	public InternalResponse deleteRentabilidadeById(Rentabilidade rentabilidade);

	/**
* Delete all rentabilidades.
*
* @return the internal response
*/
	public InternalResponse deleteAllRentabilidades();

	/**
* Fetch all rentabilidades.
*
* @return the list< rentabilidade>
*/
	public InternalResultsResponse<Rentabilidade> fetchAllRentabilidades(Rentabilidade  rentabilidade);

	/**
* Fetch rentabilidades by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Rentabilidade> fetchRentabilidadesByRequest(PagedInquiryRequest request);

	/**
	 * Fetch rentabilidadeitens by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public RentabilidadeItens fetchRentabilidadeItensById(FetchByIdRequest request);

	/**
* Insert rentabilidadeitens.
*
* @param rentabilidadeitens the rentabilidadeitens
*
* @return the internal response
*/
	public InternalResponse insertRentabilidadeItens(RentabilidadeItens rentabilidadeitens);

	/**
* Update rentabilidadeitens.
*
* @param rentabilidadeitens the rentabilidadeitens
*
* @return the internal response
*/
	public InternalResponse updateRentabilidadeItens(RentabilidadeItens rentabilidadeitens);

	/**
* Delete rentabilidadeitens.
*
* @param rentabilidadeitens the rentabilidadeitens
*
* @return the internal response
*/
	public InternalResponse deleteRentabilidadeItensById(RentabilidadeItens rentabilidadeitens);

	/**
* Delete all rentabilidadeitenss.
*
* @return the internal response
*/
	public InternalResponse deleteAllRentabilidadeItenss();

	/**
* Fetch all rentabilidadeitenss.
*
* @return the list< rentabilidadeitens>
*/
	public InternalResultsResponse<RentabilidadeItens> fetchAllRentabilidadeItenss(RentabilidadeItens  rentabilidadeitens);

	/**
* Fetch rentabilidadeitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<RentabilidadeItens> fetchRentabilidadeItenssByRequest(PagedInquiryRequest request);

	public MarcaProduto fetchMarcaProdutosById(FetchByIdRequest request);

}
