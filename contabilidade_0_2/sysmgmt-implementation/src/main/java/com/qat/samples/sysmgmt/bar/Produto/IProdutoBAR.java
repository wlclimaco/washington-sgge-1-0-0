package com.qat.samples.sysmgmt.bar.Produto;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface ProdutoBAR.. (Data Access Component - DAC)
 */
public interface IProdutoBAR
{

	/**
	 * Fetch produto by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request);

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
	 * Fetch marca by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Marca> fetchMarcaById(FetchByIdRequest request);

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
	 * Fetch grupo by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Grupo> fetchGrupoById(FetchByIdRequest request);

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
	public InternalResultsResponse<SubGrupo> fetchSubGrupoById(FetchByIdRequest request);

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
	public InternalResultsResponse<UniMed> fetchUniMedById(FetchByIdRequest request);

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

}
