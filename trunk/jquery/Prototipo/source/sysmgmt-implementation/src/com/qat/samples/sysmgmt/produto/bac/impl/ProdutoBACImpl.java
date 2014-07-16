package com.qat.samples.sysmgmt.produto.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dac.IPrecoDAC;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.response.InternalResponseLocal;
import com.qat.samples.sysmgmt.produto.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Tabelapreco;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;

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

	private IPrecoDAC precoDAC; // injected by Spring through setter

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

	public IPrecoDAC getPrecoDAC()
	{
		return precoDAC;
	}

	public void setPrecoDAC(IPrecoDAC precoDAC)
	{
		this.precoDAC = precoDAC;
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
		InternalResponse response = new InternalResponse();
		InternalResponseLocal internalResponse = (InternalResponseLocal)getProdutoDAC().insertProduto(produto);
		if (internalResponse.getStatus() == Status.OperationSuccess)
		{

			for (Integer i = 0; i < produto.getPrecos().size(); i++)
			{
				produto.getPrecos().get(i).setIdProduto(internalResponse.getId());
				response = getPrecoDAC().insertPreco(produto.getPrecos().get(i));
			}
		}

		return response;
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
		FetchByIdRequest fetchByIdRequest = new FetchByIdRequest();
		Tabelapreco preco = new Tabelapreco();
		if (internalResponse.getStatus() == Status.OperationSuccess)
		{

			for (Integer i = 0; i < produto.getPrecos().size(); i++)
			{
				if (produto.getPrecos().get(i).getPrecoid() != 0)
				{
					fetchByIdRequest.setFetchId(produto.getPrecos().get(i).getPrecoid());
					preco = getPrecoDAC().fetchPrecoById(fetchByIdRequest);
					if ((preco.getPreco() == produto.getPrecos().get(i).getPreco())
							&& (preco.getPrecopromo() == produto.getPrecos().get(i).getPrecopromo()))
					{
						internalResponse = getPrecoDAC().updatePreco(produto.getPrecos().get(i));
					}
					else
					{
						internalResponse = getPrecoDAC().insertPreco(produto.getPrecos().get(i));
					}
				}
				else
				{
					internalResponse = getPrecoDAC().insertPreco(produto.getPrecos().get(i));
				}
			}
		}

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
	public InternalResponse insertCadastro(Cadastro cadastro)
	{
		return getProdutoDAC().insertCadastro(cadastro);
	}

	@Override
	public InternalResponse updateCadastro(Cadastro cadastro)
	{
		// produto.setPrice(ProdutoBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getProdutoDAC().updateCadastro(cadastro);
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

	@Override
	public InternalResponse deleteCadastro(Cadastro cadastro)
	{
		InternalResponse internalResponse = getProdutoDAC().deleteCadastro(cadastro);
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

	@Override
	public void refreshCadastros(Integer refreshNumber)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public InternalResultsResponse<Cadastro> fetchCadastroById(FetchByIdRequest request)
	{
		InternalResultsResponse<Cadastro> response = new InternalResultsResponse<Cadastro>();
		Cadastro cadastro = new Cadastro(request.getFetchId());
		response.getResultsList().add(getProdutoDAC().fetchCadastroById(cadastro));
		return response;
	}

	@Override
	public InternalResultsResponse<Cadastro> fetchAllCadastros(CadastroInquiryRequest cadastro)

	{
		InternalResultsResponse<Cadastro> response = new InternalResultsResponse<Cadastro>();
		response.getResultsList().addAll(getProdutoDAC().fetchAllCadastros(cadastro));
		return response;
	}

	@Override
	public InternalResultsResponse<Cadastro> fetchCadastrosByRequest(CadastroInquiryRequest request)
	{
		return getProdutoDAC().fetchCadastrosByRequest(request);
	}

}
