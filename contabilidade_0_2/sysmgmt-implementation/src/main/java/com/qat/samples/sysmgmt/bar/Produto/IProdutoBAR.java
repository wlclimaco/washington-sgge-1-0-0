/** create by system gera-java version 1.0.0 29/08/2016 22:9 : 4*/
package com.qat.samples.sysmgmt.bar.Produto;
/** create by system gera-java version 1.0.0 29/08/2016 22:23 : 40*/

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Categoria;
import com.qat.samples.sysmgmt.produto.model.Cofins;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.CustoItens;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.ICMSOpInter;
import com.qat.samples.sysmgmt.produto.model.Icms;
import com.qat.samples.sysmgmt.produto.model.Ipi;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.MarcaProduto;
import com.qat.samples.sysmgmt.produto.model.Pis;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.PorcaoItens;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoEmpresa;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.CofinsInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.IcmsInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.IpiInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PisInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoEmpresaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
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
	 * Fetch produtoempresa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public ProdutoEmpresa fetchProdutoEmpresaById(FetchByIdRequest request);

	/**
* Insert produtoempresa.
*
* @param produtoempresa the produtoempresa
*
* @return the internal response
*/
	public InternalResponse insertProdutoEmpresa(ProdutoEmpresa produtoempresa);

	/**
* Update produtoempresa.
*
* @param produtoempresa the produtoempresa
*
* @return the internal response
*/
	public InternalResponse updateProdutoEmpresa(ProdutoEmpresa produtoempresa);

	/**
* Delete produtoempresa.
*
* @param produtoempresa the produtoempresa
*
* @return the internal response
*/
	public InternalResponse deleteProdutoEmpresaById(ProdutoEmpresa produtoempresa);

	/**
* Delete all produtoempresas.
*
* @return the internal response
*/
	public InternalResponse deleteAllProdutoEmpresas();

	/**
* Fetch all produtoempresas.
*
* @return the list< produtoempresa>
*/
	public InternalResultsResponse<ProdutoEmpresa> fetchAllProdutoEmpresas(ProdutoEmpresa  produtoempresa);

	/**
* Fetch produtoempresas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ProdutoEmpresa> fetchProdutoEmpresasByRequest(ProdutoEmpresaInquiryRequest request);

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
	 * Fetch icms by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Icms fetchIcmsById(FetchByIdRequest request);

	/**
* Insert icms.
*
* @param icms the icms
*
* @return the internal response
*/
	public InternalResponse insertIcms(Icms icms);

	/**
* Update icms.
*
* @param icms the icms
*
* @return the internal response
*/
	public InternalResponse updateIcms(Icms icms);

	/**
* Delete icms.
*
* @param icms the icms
*
* @return the internal response
*/
	public InternalResponse deleteIcmsById(Icms icms);

	/**
* Delete all icmss.
*
* @return the internal response
*/
	public InternalResponse deleteAllIcmss();

	/**
* Fetch all icmss.
*
* @return the list< icms>
*/
	public InternalResultsResponse<Icms> fetchAllIcmss(Icms  icms);

	/**
* Fetch icmss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Icms> fetchIcmssByRequest(IcmsInquiryRequest request);

	/**
	 * Fetch pis by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Pis fetchPisById(FetchByIdRequest request);

	/**
* Insert pis.
*
* @param pis the pis
*
* @return the internal response
*/
	public InternalResponse insertPis(Pis pis);

	/**
* Update pis.
*
* @param pis the pis
*
* @return the internal response
*/
	public InternalResponse updatePis(Pis pis);

	/**
* Delete pis.
*
* @param pis the pis
*
* @return the internal response
*/
	public InternalResponse deletePisById(Pis pis);

	/**
* Delete all piss.
*
* @return the internal response
*/
	public InternalResponse deleteAllPiss();

	/**
* Fetch all piss.
*
* @return the list< pis>
*/
	public InternalResultsResponse<Pis> fetchAllPiss(Pis  pis);

	/**
* Fetch piss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Pis> fetchPissByRequest(PisInquiryRequest request);

	/**
	 * Fetch ipi by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Ipi fetchIpiById(FetchByIdRequest request);

	/**
* Insert ipi.
*
* @param ipi the ipi
*
* @return the internal response
*/
	public InternalResponse insertIpi(Ipi ipi);

	/**
* Update ipi.
*
* @param ipi the ipi
*
* @return the internal response
*/
	public InternalResponse updateIpi(Ipi ipi);

	/**
* Delete ipi.
*
* @param ipi the ipi
*
* @return the internal response
*/
	public InternalResponse deleteIpiById(Ipi ipi);

	/**
* Delete all ipis.
*
* @return the internal response
*/
	public InternalResponse deleteAllIpis();

	/**
* Fetch all ipis.
*
* @return the list< ipi>
*/
	public InternalResultsResponse<Ipi> fetchAllIpis(Ipi  ipi);

	/**
* Fetch ipis by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Ipi> fetchIpisByRequest(IpiInquiryRequest request);

	/**
	 * Fetch cofins by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Cofins fetchCofinsById(FetchByIdRequest request);

	/**
* Insert cofins.
*
* @param cofins the cofins
*
* @return the internal response
*/
	public InternalResponse insertCofins(Cofins cofins);

	/**
* Update cofins.
*
* @param cofins the cofins
*
* @return the internal response
*/
	public InternalResponse updateCofins(Cofins cofins);

	/**
* Delete cofins.
*
* @param cofins the cofins
*
* @return the internal response
*/
	public InternalResponse deleteCofinsById(Cofins cofins);

	/**
* Delete all cofinss.
*
* @return the internal response
*/
	public InternalResponse deleteAllCofinss();

	/**
* Fetch all cofinss.
*
* @return the list< cofins>
*/
	public InternalResultsResponse<Cofins> fetchAllCofinss(Cofins  cofins);

	/**
* Fetch cofinss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cofins> fetchCofinssByRequest(CofinsInquiryRequest request);

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

	/**
	 * Fetch categoria by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Categoria fetchCategoriaById(FetchByIdRequest request);

	/**
* Insert categoria.
*
* @param categoria the categoria
*
* @return the internal response
*/
	public InternalResponse insertCategoria(Categoria categoria);

	/**
* Update categoria.
*
* @param categoria the categoria
*
* @return the internal response
*/
	public InternalResponse updateCategoria(Categoria categoria);

	/**
* Delete categoria.
*
* @param categoria the categoria
*
* @return the internal response
*/
	public InternalResponse deleteCategoriaById(Categoria categoria);

	/**
* Delete all categorias.
*
* @return the internal response
*/
	public InternalResponse deleteAllCategorias();

	/**
* Fetch all categorias.
*
* @return the list< categoria>
*/
	public InternalResultsResponse<Categoria> fetchAllCategorias(Categoria  categoria);

	/**
* Fetch categorias by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Categoria> fetchCategoriasByRequest(PagedInquiryRequest request);

	/**
	 * Fetch icmsopinter by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public ICMSOpInter fetchICMSOpInterById(FetchByIdRequest request);

	/**
* Insert icmsopinter.
*
* @param icmsopinter the icmsopinter
*
* @return the internal response
*/
	public InternalResponse insertICMSOpInter(ICMSOpInter icmsopinter);

	/**
* Update icmsopinter.
*
* @param icmsopinter the icmsopinter
*
* @return the internal response
*/
	public InternalResponse updateICMSOpInter(ICMSOpInter icmsopinter);

	/**
* Delete icmsopinter.
*
* @param request the icmsopinter
*
* @return the internal response
*/
	public InternalResponse deleteICMSOpInterById(ICMSOpInter request);

	/**
* Delete all icmsopinters.
*
* @return the internal response
*/
	public InternalResponse deleteAllICMSOpInters();

	/**
* Fetch all icmsopinters.
*
* @return the list< icmsopinter>
*/
	public InternalResultsResponse<ICMSOpInter> fetchAllICMSOpInters(ICMSOpInter  icmsopinter);

	/**
* Fetch icmsopinters by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ICMSOpInter> fetchICMSOpIntersByRequest(PagedInquiryRequest request);


	/**
	 * Fetch produto by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Servico fetchServicoById(FetchByIdRequest request);

	/**
* Insert produto.
*
* @param produto the produto
*
* @return the internal response
*/
	public InternalResponse insertServico(Servico produto);

	/**
* Update produto.
*
* @param produto the produto
*
* @return the internal response
*/
	public InternalResponse updateServico(Servico produto);

	/**
* Delete produto.
*
* @param produto the produto
*
* @return the internal response
*/
	public InternalResponse deleteServicoById(Servico produto);

	/**
* Delete all produtos.
*
* @return the internal response
*/
	public InternalResponse deleteAllServicos();

	/**
* Fetch all produtos.
*
* @return the list< produto>
*/
	public InternalResultsResponse<Servico> fetchAllServicos(Servico  produto);

	/**
* Fetch produtos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Servico> fetchServicosByRequest(ServicoInquiryRequest request);

}
