package com.qat.samples.sysmgmt.listaCompras.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.listaCompras.bac.IListaComprasBAC;
import com.qat.samples.sysmgmt.listaCompras.dac.IListaComprasDAC;
import com.qat.samples.sysmgmt.listaCompras.model.ListaCompras;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * Implementation of the IListaComprasBAC leveraging a BAD, ListaComprasBAD.
 */
public class ListaComprasBACImpl implements IListaComprasBAC
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
	private static final String DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG = "sysmgmt.base.cidadebacimpl.defaultexception";

	/** The cidade dac. */
	private IListaComprasDAC cidadeDAC; // injected by Spring through setter

	/**
	 * Spring Sets the cidade dac.
	 * 
	 * @param cidadeDAC the new cidade dac
	 */
	public void setListaComprasDAC(IListaComprasDAC cidadeDAC)
	{
		this.cidadeDAC = cidadeDAC;
	}

	/**
	 * Gets the cidade dac.
	 * 
	 * @return the cidade dac
	 */
	public IListaComprasDAC getListaComprasDAC()
	{
		return cidadeDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IListaComprasBAC#insertListaCompras(com.qat.samples.sysmgmt.base.model.
	 * ListaCompras)
	 */
	@Override
	public InternalResponse insertListaCompras(ListaCompras cidade)
	{
		// cidade.setPrice(ListaComprasBAD.calculatePrice(INSERT_SEED));
		return getListaComprasDAC().insertListaCompras(cidade);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IListaComprasBAC#updateListaCompras(com.qat.samples.sysmgmt.base.model.
	 * ListaCompras)
	 */
	@Override
	public InternalResponse updateListaCompras(ListaCompras cidade)
	{
		// cidade.setPrice(ListaComprasBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getListaComprasDAC().updateListaCompras(cidade);
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
	 * com.qat.samples.sysmgmt.base.bac.IListaComprasBAC#deleteListaCompras(com.qat.samples.sysmgmt.base.model.
	 * ListaCompras)
	 */
	@Override
	public InternalResponse deleteListaCompras(ListaCompras cidade)
	{
		InternalResponse internalResponse = getListaComprasDAC().deleteListaCompras(cidade);
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
	 * @see com.qat.samples.sysmgmt.base.bac.IListaComprasBAC#refreshListaComprass(int)
	 */
	@Override
	public void refreshListaComprass(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getListaComprasDAC().deleteAllListaComprass();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getListaComprasDAC().insertListaCompras(new ListaCompras(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.IListaComprasBAC#fetchAllListaComprass()
	 */
	@Override
	public InternalResultsResponse<ListaCompras> fetchAllListaComprass()
	{
		InternalResultsResponse<ListaCompras> response = new InternalResultsResponse<ListaCompras>();
		response.getResultsList().addAll(getListaComprasDAC().fetchAllListaComprass());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IListaComprasBAC#fetchListaComprasById(com.qat.samples.sysmgmt.base.model.
	 * ListaCompras
	 * )
	 */
	@Override
	public InternalResultsResponse<ListaCompras> fetchListaComprasById(FetchByIdRequest request)
	{
		InternalResultsResponse<ListaCompras> response = new InternalResultsResponse<ListaCompras>();
		response.getResultsList().add(getListaComprasDAC().fetchListaComprasById(request));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.IListaComprasBAC#fetchListaComprassByRequest(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<ListaCompras> fetchListaComprassByRequest(PagedInquiryRequest request)
	{
		return getListaComprasDAC().fetchListaComprassByRequest(request);
	}

	@Override
	public InternalResponse insertListaComprasItens(ListaCompras procedure)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse updateListaComprasItens(ListaCompras procedure)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResponse deleteListaComprasItens(ListaCompras procedure)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshListaComprasItenss(Integer refreshNumber)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public InternalResultsResponse<ListaCompras> fetchListaComprasItensById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<ListaCompras> fetchAllListaComprasItenss()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<ListaCompras> fetchListaComprasItenssByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
