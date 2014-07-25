package com.qat.samples.sysmgmt.produto.bac.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.qat.samples.sysmgmt.produto.model.Embalagem;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Tabelapreco;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.EmbalagemInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.util.TableTypeEnum;

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
		if (!produto.getTabela().equals(TableTypeEnum.TABPRECO))
		{
			InternalResponse response = new InternalResponse();
			InternalResponseLocal internalResponse = (InternalResponseLocal)getProdutoDAC().insertProduto(produto);
			if (internalResponse.getStatus() == Status.OperationSuccess)
			{

				for (Integer i = 0; i < produto.getPrecos().size(); i++)
				{
					produto.getPrecos().get(i).setIdProduto(new Produto(internalResponse.getId()));
					response = getPrecoDAC().insertPreco(produto.getPrecos().get(i));
				}
			}

			return response;
		}
		else
		{
			InternalResponse response = new InternalResponse();
			response = getPrecoDAC().insertPreco(produto.getPrecos().get(0));
			return response;
		}
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
					if (((preco.getPreco() - produto.getPrecos().get(i).getPreco()) > new Double(0))
							&& ((preco.getPrecopromo() - produto.getPrecos().get(i).getPrecopromo()) > new Double(0)))
					{

						internalResponse = getPrecoDAC().insertPreco(produto.getPrecos().get(i));
					}
					else
					{
						internalResponse = getPrecoDAC().updatePreco(produto.getPrecos().get(i));
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
	public InternalResultsResponse<Produto> fetchAllProdutos(ProdutoInquiryRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();
		if (request.getProduto().getTabela().equals(TableTypeEnum.TABPRECO))
		{
			List<Tabelapreco> a = getProdutoDAC().fetchAllProdutosPreco(request);
			Produto produto = new Produto();
			produto.setNome("test");
			produto.setPrecos(a);
			List<Produto> b = new ArrayList<Produto>();
			b.add(produto);
			response.getResultsList().addAll(b);
		}
		else if (request.getProduto().getTabela().equals(TableTypeEnum.MARCA))
		{
			List<Tabelapreco> a = getProdutoDAC().fetchAllProdutosMarca(request);
			List<Produto> b = new ArrayList<Produto>();
			for (Integer i = 0; i < a.size(); i++)
			{
				Produto produto = new Produto();
				produto = a.get(i).getIdProduto();
				b.add(produto);
			}
			response.getResultsList().addAll(b);
		}
		else if (request.getProduto().getTabela().equals(TableTypeEnum.MENU))
		{
			List<Tabelapreco> a = getProdutoDAC().fetchAllProdutosMenu(request);
			List<Produto> b = new ArrayList<Produto>();
			for (Integer i = 0; i < a.size(); i++)
			{
				Produto produto = new Produto();
				produto = a.get(i).getIdProduto();
				b.add(produto);
			}
			response.getResultsList().addAll(b);
		}
		else if (request.getProduto().getTabela().equals(TableTypeEnum.SUBMENU))
		{
			List<Tabelapreco> a = getProdutoDAC().fetchAllProdutosMenu(request);
			List<Produto> b = new ArrayList<Produto>();
			for (Integer i = 0; i < a.size(); i++)
			{
				Produto produto = new Produto();
				produto = a.get(i).getIdProduto();
				b.add(produto);
			}
			response.getResultsList().addAll(b);
		}
		else if (request.getProduto().getTabela().equals(TableTypeEnum.TRIMENU))
		{
			List<Tabelapreco> a = getProdutoDAC().fetchAllProdutosMenu(request);

			List<Produto> b = new ArrayList<Produto>();
			for (Integer i = 0; i < a.size(); i++)
			{
				Produto produto = new Produto();
				produto = a.get(i).getIdProduto();
				b.add(produto);
			}
			response.getResultsList().addAll(b);
		}
		else if (request.getProduto().getTabela().equals(TableTypeEnum.UNIMED))
		{
			List<Tabelapreco> a = getProdutoDAC().fetchAllProdutosUniMed(request);

			List<Produto> b = new ArrayList<Produto>();
			for (Integer i = 0; i < a.size(); i++)
			{
				Produto produto = new Produto();
				produto = a.get(i).getIdProduto();
				b.add(produto);
			}
			response.getResultsList().addAll(b);
		}
		else if (request.getProduto().getTabela().equals(TableTypeEnum.SUPERMERCADO))
		{
			List<Tabelapreco> a = getProdutoDAC().fetchAllProdutosSupermercado(request);
			Produto produto = new Produto();
			List<Produto> b = new ArrayList<Produto>();
			for (Integer i = 0; i < a.size(); i++)
			{
				produto = new Produto();
				produto = a.get(i).getIdProduto();
				b.add(produto);
			}
			response.getResultsList().addAll(b);
		}
		else
		{
			response.getResultsList().addAll(getProdutoDAC().fetchAllProdutos(request));
		}

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

	@Override
	public InternalResponse deletePreco(Produto procedure)
	{
		InternalResponse internalResponse = getPrecoDAC().deletePreco(procedure.getPrecos().get(0));
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
	public InternalResponse insertEmbalagem(Embalagem request)
	{
		InternalResponse response = new InternalResponse();
		response = getProdutoDAC().insertEmbalagem(request);
		return response;
	}

	@Override
	public InternalResponse updateEmbalagem(Embalagem request)
	{
		InternalResponse response = new InternalResponse();
		response = getProdutoDAC().updateEmbalagem(request);
		return response;
	}

	@Override
	public InternalResponse deleteEmbalagem(Embalagem request)
	{
		InternalResponse response = new InternalResponse();
		response = getProdutoDAC().deleteEmbalagem(request);
		if (response.getStatus() != Status.OperationSuccess)
		{
			response.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {response
							.getStatus().toString()});
		}
		return response;
	}

	@Override
	public InternalResultsResponse<Embalagem> fetchAllEmbalagems(EmbalagemInquiryRequest request)
	{
		InternalResultsResponse<Embalagem> response = new InternalResultsResponse<Embalagem>();
		response.getResultsList().addAll(getProdutoDAC().fetchAllEmbalagems(request));
		return response;
	}

	@Override
	public InternalResponse insertUniMed(Embalagem request)
	{
		InternalResponse response = new InternalResponse();
		response = getProdutoDAC().insertUniMed(request);
		return response;
	}

	@Override
	public InternalResponse updateUniMed(Embalagem request)
	{
		InternalResponse response = new InternalResponse();
		response = getProdutoDAC().updateUniMed(request);
		return response;
	}

	@Override
	public InternalResponse deleteUniMed(Embalagem request)
	{
		InternalResponse response = new InternalResponse();
		response = getProdutoDAC().deleteUniMed(request);
		if (response.getStatus() != Status.OperationSuccess)
		{
			response.addMessage(DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG, Message.MessageSeverity.Error,
					Message.MessageLevel.Object, new Object[] {response
							.getStatus().toString()});
		}
		return response;
	}

	@Override
	public InternalResultsResponse<UniMed> fetchAllUniMeds(EmbalagemInquiryRequest request)
	{
		InternalResultsResponse<UniMed> response = new InternalResultsResponse<UniMed>();
		response.getResultsList().addAll(getProdutoDAC().fetchAllUniMeds(request));
		return response;
	}

}
