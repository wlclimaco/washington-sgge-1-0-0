package com.qat.samples.sysmgmt.produto.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * Implementation of the IProdutoBAC leveraging a BAD, ProdutoBAD.
 */
public class ProdutoBACImpl implements IProdutoBAC
{

	/** The Constant REFRESH_SEED. */
	private static final int REFRESH_SEED = 1050;

	/** The Constant UPDATE_SEED. */
	private static final int UPDATE_SEED = 3000;

	/** The Constant INSERT_SEED. */
	private static final int INSERT_SEED = 9000;

	/** The Constant MINIMUM_ENTRIES. */
	private static final int MINIMUM_ENTRIES = 5;

	/** The Constant DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG = "sysmgmt.base.produtobacimpl.defaultexception";

	/** The produto dac. */
	private IProdutoDAC produtoDAC; // injected by Spring through setter

	/**
	 * Spring Sets the produto dac.
	 * 
	 * @param produtoDAC the new produto dac
	 */
	public void setProdutoDAC(IProdutoDAC produtoDAC)
	{
		this.produtoDAC = produtoDAC;
	}

	/**
	 * Gets the produto dac.
	 * 
	 * @return the produto dac
	 */
	public IProdutoDAC getProdutoDAC()
	{
		return produtoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProdutoBAC#insertProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse insertProduto(Produto produto)
	{
		// produto.setPrice(ProdutoBAD.calculatePrice(INSERT_SEED));
		return getProdutoDAC().insertProduto(produto);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProdutoBAC#updateProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse updateProduto(Produto produto)
	{
		// produto.setPrice(ProdutoBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getProdutoDAC().updateProduto(produto);
		// Check for error because in business case all non-success returns are failures (updating of zero rows or
		// optimistic locking error) according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse
							.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProdutoBAC#deleteProduto(com.qat.samples.sysmgmt.base.model.Produto)
	 */
	@Override
	public InternalResponse deleteProduto(Produto produto)
	{
		InternalResponse internalResponse = getProdutoDAC().deleteProduto(produto);
		// Check for error because in business case all non-success returns are failures (removal of zero rows)
		// according to the business
		if (internalResponse.getStatus() != Status.OperationSuccess)
		{
			internalResponse.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {internalResponse
							.getStatus().toString()});
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IProdutoBAC#refreshProdutos(int)
	 */
	@Override
	public void refreshProdutos(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getProdutoDAC().deleteAllProdutos();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getProdutoDAC().insertProduto(new Produto());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IProdutoBAC#fetchAllProdutos()
	 */
	@Override
	public InternalResultsResponse<Produto> fetchAllProdutos()
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		response.getResultsList().addAll(getProdutoDAC().fetchAllProdutos());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IProdutoBAC#fetchProdutoById(com.qat.samples.sysmgmt.base.model.Produto
	 * )
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		response.getResultsList().add(getProdutoDAC().fetchProdutoById(request));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.IProdutoBAC#fetchProdutosByRequest(com.qat.samples.sysmgmt.model.request.
	 * ProdutoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutosByRequest(PagedInquiryRequest request)
	{
		return getProdutoDAC().fetchProdutosByRequest(request);
	}

	@Override
	public InternalResponse insertCadastro(Cadastro procedure)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse updateCadastro(Cadastro procedure)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteCadastro(Cadastro procedure)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshCadastros(Integer refreshNumber)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public InternalResultsResponse<Cadastro> fetchCadastroById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cadastro> fetchAllCadastros()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Cadastro> fetchCadastrosByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
