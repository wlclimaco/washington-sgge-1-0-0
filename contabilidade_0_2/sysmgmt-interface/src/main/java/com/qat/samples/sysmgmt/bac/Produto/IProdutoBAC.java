package com.qat.samples.sysmgmt.bac.Produto;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.GrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IProdutoBAC. (Business Area Component - BAC)
 */
public interface IProdutoBAC
{



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

}
