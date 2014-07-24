package com.qat.samples.sysmgmt.produto.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Embalagem;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Tabelapreco;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.EmbalagemInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;

/**
 * The Interface IProdutoDAC. (Data Access Component - DAC)
 */
public interface IProdutoDAC
{

	/**
	 * Insert produto.
	 * 
	 * @param produto the produto
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
	public InternalResponse deleteProduto(Produto produto);

	/**
	 * Delete all produtos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllProdutos();

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * @return the list< produto>
	 */
	public List<Produto> fetchAllProdutos(ProdutoInquiryRequest request);

	/**
	 * Fetch all produtos marca.
	 * 
	 * @param request the request
	 * @return the list
	 */
	public List<Tabelapreco> fetchAllProdutosMarca(ProdutoInquiryRequest request);

	/**
	 * Fetch all produtos menu.
	 * 
	 * @param request the request
	 * @return the list
	 */
	public List<Tabelapreco> fetchAllProdutosMenu(ProdutoInquiryRequest request);

	/**
	 * Fetch all produtos uni med.
	 * 
	 * @param request the request
	 * @return the list
	 */
	public List<Tabelapreco> fetchAllProdutosUniMed(ProdutoInquiryRequest request);

	/**
	 * Fetch all produtos preco.
	 * 
	 * @param request the request
	 * @return the list
	 */
	public List<Tabelapreco> fetchAllProdutosPreco(ProdutoInquiryRequest request);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Produto fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch produtos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Produto> fetchProdutosByRequest(PagedInquiryRequest request);

	// /=====================

	/**
	 * Insert produto.
	 * 
	 * @param produto the produto
	 * @return the internal response
	 */
	public InternalResponse insertCadastro(Cadastro produto);

	/**
	 * Update produto.
	 * 
	 * @param produto the produto
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateCadastro(Cadastro produto);

	/**
	 * Delete produto.
	 * 
	 * @param produto the produto
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteCadastro(Cadastro produto);

	/**
	 * Delete all produtos.
	 * 
	 * @param cadastro the cadastro
	 * @return the internal response
	 */
	public InternalResponse deleteAllCadastros(Cadastro cadastro);

	/**
	 * Fetch all produtos.
	 * 
	 * @param cadastro the cadastro
	 * @return the list< produto>
	 */
	public List<Cadastro> fetchAllCadastros(CadastroInquiryRequest cadastro);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Cadastro fetchCadastroById(Cadastro request);

	/**
	 * Fetch produtos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cadastro> fetchCadastrosByRequest(CadastroInquiryRequest request);

	// ===

	/**
	 * Insert produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public InternalResponse insertEmbalagem(Embalagem request);

	/**
	 * Update produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public InternalResponse updateEmbalagem(Embalagem request);

	/**
	 * Delete produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public InternalResponse deleteEmbalagem(Embalagem request);

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	public List<Embalagem> fetchAllEmbalagems(EmbalagemInquiryRequest request);

	// ================
	/**
	 * Insert produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public InternalResponse insertUniMed(Embalagem request);

	/**
	 * Update produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public InternalResponse updateUniMed(Embalagem request);

	/**
	 * Delete produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	public InternalResponse deleteUniMed(Embalagem request);

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	public List<UniMed> fetchAllUniMeds(EmbalagemInquiryRequest request);

}
