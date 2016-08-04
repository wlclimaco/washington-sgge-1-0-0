/** create by system gera-java version 1.0.0 04/08/2016 14:46 : 15*/
package com.qat.samples.sysmgmt.bac.Produto;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
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
import com.qat.samples.sysmgmt.produto.model.request.CustoItensMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.CustoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.EstoqueMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.PorcaoItensMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.PorcaoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoParentInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoParentMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.RentabilidadeItensMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.RentabilidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IProdutoBAC. (Business Area Component - BAC)
 */
public interface IProdutoBAC
{



//===================================### PRODUTOPARENT ####======================================
	/**

	/**
	 * Insert produtoparent.
	 *
* @param request the produtoparent maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ProdutoParent> insertProdutoParent(ProdutoParentMaintenanceRequest request);

	/**
* Update produtoparent.
*
* @param request the produtoparent maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ProdutoParent> updateProdutoParent(ProdutoParentMaintenanceRequest request);

	/**
* Delete produtoparent.
*
* @param request the produtoparent maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<ProdutoParent> deleteProdutoParent(ProdutoParentMaintenanceRequest request);

	/**
* Refresh produtoparents.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<ProdutoParent> refreshProdutoParents(RefreshRequest request);

	/**
* Fetch produtoparent by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ProdutoParent> fetchProdutoParentById(FetchByIdRequest request);

	/**
* Fetch all produtoparents.
*
* @return the internal results response< produtoparent>
*/
	public InternalResultsResponse<ProdutoParent> fetchAllProdutoParents(ProdutoParent  produtoparent);

	/**
* Fetch produtoparents by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<ProdutoParent> fetchProdutoParentsByRequest(ProdutoParentInquiryRequest request);


//===================================### PRODUTO ####======================================
	/**

	/**
	 * Insert produto.
	 *
* @param request the produto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Produto> insertProduto(ProdutoMaintenanceRequest request);

	/**
* Update produto.
*
* @param request the produto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Produto> updateProduto(ProdutoMaintenanceRequest request);

	/**
* Delete produto.
*
* @param request the produto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Produto> deleteProduto(ProdutoMaintenanceRequest request);

	/**
* Refresh produtos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Produto> refreshProdutos(RefreshRequest request);

	/**
* Fetch produto by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request);

	/**
* Fetch all produtos.
*
* @return the internal results response< produto>
*/
	public InternalResultsResponse<Produto> fetchAllProdutos(Produto  produto);

	/**
* Fetch produtos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Produto> fetchProdutosByRequest(ProdutoInquiryRequest request);


//===================================### CFOP ####======================================
	/**

	/**
	 * Insert cfop.
	 *
* @param request the cfop maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> insertCfop(CfopMaintenanceRequest request);

	/**
* Update cfop.
*
* @param request the cfop maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> updateCfop(CfopMaintenanceRequest request);

	/**
* Delete cfop.
*
* @param request the cfop maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> deleteCfop(CfopMaintenanceRequest request);

	/**
* Refresh cfops.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Cfop> refreshCfops(RefreshRequest request);

	/**
* Fetch cfop by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> fetchCfopById(FetchByIdRequest request);

	/**
* Fetch all cfops.
*
* @return the internal results response< cfop>
*/
	public InternalResultsResponse<Cfop> fetchAllCfops(Cfop  cfop);

	/**
* Fetch cfops by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cfop> fetchCfopsByRequest(CfopInquiryRequest request);


//===================================### MARCA ####======================================
	/**

	/**
	 * Insert marca.
	 *
* @param request the marca maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Marca> insertMarca(MarcaMaintenanceRequest request);

	/**
* Update marca.
*
* @param request the marca maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Marca> updateMarca(MarcaMaintenanceRequest request);

	/**
* Delete marca.
*
* @param request the marca maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Marca> deleteMarca(MarcaMaintenanceRequest request);

	/**
* Refresh marcas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Marca> refreshMarcas(RefreshRequest request);

	/**
* Fetch marca by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Marca> fetchMarcaById(FetchByIdRequest request);

	/**
* Fetch all marcas.
*
* @return the internal results response< marca>
*/
	public InternalResultsResponse<Marca> fetchAllMarcas(Marca  marca);

	/**
* Fetch marcas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Marca> fetchMarcasByRequest(MarcaInquiryRequest request);


//===================================### MARCAPRODUTO ####======================================
	/**

	/**
	 * Insert marcaproduto.
	 *
* @param request the marcaproduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<MarcaProduto> insertMarcaProduto(MarcaProdutoMaintenanceRequest request);

	/**
* Update marcaproduto.
*
* @param request the marcaproduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<MarcaProduto> updateMarcaProduto(MarcaProdutoMaintenanceRequest request);

	/**
* Delete marcaproduto.
*
* @param request the marcaproduto maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<MarcaProduto> deleteMarcaProduto(MarcaProdutoMaintenanceRequest request);

	/**
* Refresh marcaprodutos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<MarcaProduto> refreshMarcaProdutos(RefreshRequest request);

	/**
* Fetch marcaproduto by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<MarcaProduto> fetchMarcaProdutoById(FetchByIdRequest request);

	/**
* Fetch all marcaprodutos.
*
* @return the internal results response< marcaproduto>
*/
	public InternalResultsResponse<MarcaProduto> fetchAllMarcaProdutos(MarcaProduto  marcaproduto);

	/**
* Fetch marcaprodutos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<MarcaProduto> fetchMarcaProdutosByRequest(PagedInquiryRequest request);


//===================================### GRUPO ####======================================
	/**

	/**
	 * Insert grupo.
	 *
* @param request the grupo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Grupo> insertGrupo(GrupoMaintenanceRequest request);

	/**
* Update grupo.
*
* @param request the grupo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Grupo> updateGrupo(GrupoMaintenanceRequest request);

	/**
* Delete grupo.
*
* @param request the grupo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Grupo> deleteGrupo(GrupoMaintenanceRequest request);

	/**
* Refresh grupos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Grupo> refreshGrupos(RefreshRequest request);

	/**
* Fetch grupo by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Grupo> fetchGrupoById(FetchByIdRequest request);

	/**
* Fetch all grupos.
*
* @return the internal results response< grupo>
*/
	public InternalResultsResponse<Grupo> fetchAllGrupos(Grupo  grupo);

	/**
* Fetch grupos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Grupo> fetchGruposByRequest(GrupoInquiryRequest request);


//===================================### SUBGRUPO ####======================================
	/**

	/**
	 * Insert subgrupo.
	 *
* @param request the subgrupo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<SubGrupo> insertSubGrupo(SubGrupoMaintenanceRequest request);

	/**
* Update subgrupo.
*
* @param request the subgrupo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<SubGrupo> updateSubGrupo(SubGrupoMaintenanceRequest request);

	/**
* Delete subgrupo.
*
* @param request the subgrupo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<SubGrupo> deleteSubGrupo(SubGrupoMaintenanceRequest request);

	/**
* Refresh subgrupos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<SubGrupo> refreshSubGrupos(RefreshRequest request);

	/**
* Fetch subgrupo by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<SubGrupo> fetchSubGrupoById(FetchByIdRequest request);

	/**
* Fetch all subgrupos.
*
* @return the internal results response< subgrupo>
*/
	public InternalResultsResponse<SubGrupo> fetchAllSubGrupos(SubGrupo  subgrupo);

	/**
* Fetch subgrupos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<SubGrupo> fetchSubGruposByRequest(SubGrupoInquiryRequest request);


//===================================### UNIMED ####======================================
	/**

	/**
	 * Insert unimed.
	 *
* @param request the unimed maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<UniMed> insertUniMed(UniMedMaintenanceRequest request);

	/**
* Update unimed.
*
* @param request the unimed maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<UniMed> updateUniMed(UniMedMaintenanceRequest request);

	/**
* Delete unimed.
*
* @param request the unimed maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<UniMed> deleteUniMed(UniMedMaintenanceRequest request);

	/**
* Refresh unimeds.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<UniMed> refreshUniMeds(RefreshRequest request);

	/**
* Fetch unimed by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<UniMed> fetchUniMedById(FetchByIdRequest request);

	/**
* Fetch all unimeds.
*
* @return the internal results response< unimed>
*/
	public InternalResultsResponse<UniMed> fetchAllUniMeds(UniMed  unimed);

	/**
* Fetch unimeds by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<UniMed> fetchUniMedsByRequest(UniMedInquiryRequest request);


//===================================### TRIBUTACAO ####======================================
	/**

	/**
	 * Insert tributacao.
	 *
* @param request the tributacao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Tributacao> insertTributacao(TributacaoMaintenanceRequest request);

	/**
* Update tributacao.
*
* @param request the tributacao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Tributacao> updateTributacao(TributacaoMaintenanceRequest request);

	/**
* Delete tributacao.
*
* @param request the tributacao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Tributacao> deleteTributacao(TributacaoMaintenanceRequest request);

	/**
* Refresh tributacaos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Tributacao> refreshTributacaos(RefreshRequest request);

	/**
* Fetch tributacao by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Tributacao> fetchTributacaoById(FetchByIdRequest request);

	/**
* Fetch all tributacaos.
*
* @return the internal results response< tributacao>
*/
	public InternalResultsResponse<Tributacao> fetchAllTributacaos(Tributacao  tributacao);

	/**
* Fetch tributacaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Tributacao> fetchTributacaosByRequest(TributacaoInquiryRequest request);


//===================================### CUSTO ####======================================
	/**

	/**
	 * Insert custo.
	 *
* @param request the custo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Custo> insertCusto(CustoMaintenanceRequest request);

	/**
* Update custo.
*
* @param request the custo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Custo> updateCusto(CustoMaintenanceRequest request);

	/**
* Delete custo.
*
* @param request the custo maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Custo> deleteCusto(CustoMaintenanceRequest request);

	/**
* Refresh custos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Custo> refreshCustos(RefreshRequest request);

	/**
* Fetch custo by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Custo> fetchCustoById(FetchByIdRequest request);

	/**
* Fetch all custos.
*
* @return the internal results response< custo>
*/
	public InternalResultsResponse<Custo> fetchAllCustos(Custo  custo);

	/**
* Fetch custos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Custo> fetchCustosByRequest(PagedInquiryRequest request);


//===================================### CUSTOITENS ####======================================
	/**

	/**
	 * Insert custoitens.
	 *
* @param request the custoitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<CustoItens> insertCustoItens(CustoItensMaintenanceRequest request);

	/**
* Update custoitens.
*
* @param request the custoitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<CustoItens> updateCustoItens(CustoItensMaintenanceRequest request);

	/**
* Delete custoitens.
*
* @param request the custoitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<CustoItens> deleteCustoItens(CustoItensMaintenanceRequest request);

	/**
* Refresh custoitenss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<CustoItens> refreshCustoItenss(RefreshRequest request);

	/**
* Fetch custoitens by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CustoItens> fetchCustoItensById(FetchByIdRequest request);

	/**
* Fetch all custoitenss.
*
* @return the internal results response< custoitens>
*/
	public InternalResultsResponse<CustoItens> fetchAllCustoItenss(CustoItens  custoitens);

	/**
* Fetch custoitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<CustoItens> fetchCustoItenssByRequest(PagedInquiryRequest request);


//===================================### ESTOQUE ####======================================
	/**

	/**
	 * Insert estoque.
	 *
* @param request the estoque maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Estoque> insertEstoque(EstoqueMaintenanceRequest request);

	/**
* Update estoque.
*
* @param request the estoque maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Estoque> updateEstoque(EstoqueMaintenanceRequest request);

	/**
* Delete estoque.
*
* @param request the estoque maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Estoque> deleteEstoque(EstoqueMaintenanceRequest request);

	/**
* Refresh estoques.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Estoque> refreshEstoques(RefreshRequest request);

	/**
* Fetch estoque by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Estoque> fetchEstoqueById(FetchByIdRequest request);

	/**
* Fetch all estoques.
*
* @return the internal results response< estoque>
*/
	public InternalResultsResponse<Estoque> fetchAllEstoques(Estoque  estoque);

	/**
* Fetch estoques by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Estoque> fetchEstoquesByRequest(PagedInquiryRequest request);


//===================================### PORCAO ####======================================
	/**

	/**
	 * Insert porcao.
	 *
* @param request the porcao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Porcao> insertPorcao(PorcaoMaintenanceRequest request);

	/**
* Update porcao.
*
* @param request the porcao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Porcao> updatePorcao(PorcaoMaintenanceRequest request);

	/**
* Delete porcao.
*
* @param request the porcao maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Porcao> deletePorcao(PorcaoMaintenanceRequest request);

	/**
* Refresh porcaos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Porcao> refreshPorcaos(RefreshRequest request);

	/**
* Fetch porcao by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Porcao> fetchPorcaoById(FetchByIdRequest request);

	/**
* Fetch all porcaos.
*
* @return the internal results response< porcao>
*/
	public InternalResultsResponse<Porcao> fetchAllPorcaos(Porcao  porcao);

	/**
* Fetch porcaos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Porcao> fetchPorcaosByRequest(PagedInquiryRequest request);


//===================================### PORCAOITENS ####======================================
	/**

	/**
	 * Insert porcaoitens.
	 *
* @param request the porcaoitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<PorcaoItens> insertPorcaoItens(PorcaoItensMaintenanceRequest request);

	/**
* Update porcaoitens.
*
* @param request the porcaoitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<PorcaoItens> updatePorcaoItens(PorcaoItensMaintenanceRequest request);

	/**
* Delete porcaoitens.
*
* @param request the porcaoitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<PorcaoItens> deletePorcaoItens(PorcaoItensMaintenanceRequest request);

	/**
* Refresh porcaoitenss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<PorcaoItens> refreshPorcaoItenss(RefreshRequest request);

	/**
* Fetch porcaoitens by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<PorcaoItens> fetchPorcaoItensById(FetchByIdRequest request);

	/**
* Fetch all porcaoitenss.
*
* @return the internal results response< porcaoitens>
*/
	public InternalResultsResponse<PorcaoItens> fetchAllPorcaoItenss(PorcaoItens  porcaoitens);

	/**
* Fetch porcaoitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<PorcaoItens> fetchPorcaoItenssByRequest(PagedInquiryRequest request);


//===================================### RENTABILIDADE ####======================================
	/**

	/**
	 * Insert rentabilidade.
	 *
* @param request the rentabilidade maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Rentabilidade> insertRentabilidade(RentabilidadeMaintenanceRequest request);

	/**
* Update rentabilidade.
*
* @param request the rentabilidade maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Rentabilidade> updateRentabilidade(RentabilidadeMaintenanceRequest request);

	/**
* Delete rentabilidade.
*
* @param request the rentabilidade maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Rentabilidade> deleteRentabilidade(RentabilidadeMaintenanceRequest request);

	/**
* Refresh rentabilidades.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Rentabilidade> refreshRentabilidades(RefreshRequest request);

	/**
* Fetch rentabilidade by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Rentabilidade> fetchRentabilidadeById(FetchByIdRequest request);

	/**
* Fetch all rentabilidades.
*
* @return the internal results response< rentabilidade>
*/
	public InternalResultsResponse<Rentabilidade> fetchAllRentabilidades(Rentabilidade  rentabilidade);

	/**
* Fetch rentabilidades by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Rentabilidade> fetchRentabilidadesByRequest(PagedInquiryRequest request);


//===================================### RENTABILIDADEITENS ####======================================
	/**

	/**
	 * Insert rentabilidadeitens.
	 *
* @param request the rentabilidadeitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<RentabilidadeItens> insertRentabilidadeItens(RentabilidadeItensMaintenanceRequest request);

	/**
* Update rentabilidadeitens.
*
* @param request the rentabilidadeitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<RentabilidadeItens> updateRentabilidadeItens(RentabilidadeItensMaintenanceRequest request);

	/**
* Delete rentabilidadeitens.
*
* @param request the rentabilidadeitens maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<RentabilidadeItens> deleteRentabilidadeItens(RentabilidadeItensMaintenanceRequest request);

	/**
* Refresh rentabilidadeitenss.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<RentabilidadeItens> refreshRentabilidadeItenss(RefreshRequest request);

	/**
* Fetch rentabilidadeitens by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<RentabilidadeItens> fetchRentabilidadeItensById(FetchByIdRequest request);

	/**
* Fetch all rentabilidadeitenss.
*
* @return the internal results response< rentabilidadeitens>
*/
	public InternalResultsResponse<RentabilidadeItens> fetchAllRentabilidadeItenss(RentabilidadeItens  rentabilidadeitens);

	/**
* Fetch rentabilidadeitenss by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<RentabilidadeItens> fetchRentabilidadeItenssByRequest(PagedInquiryRequest request);

}
