package com.qat.samples.sysmgmt.supermercado.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dac.IDocumentoDAC;
import com.qat.samples.sysmgmt.dac.IEnderecoDAC;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.supermercado.bac.ISupermercadoBAC;
import com.qat.samples.sysmgmt.supermercado.dac.ISupermercadoDAC;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;

/**
 * Implementation of the ISupermercadoBAC leveraging a BAD, SupermercadoBAD.
 */
public class SupermercadoBACImpl implements ISupermercadoBAC
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
	private static final String DEFAULT_PROCEDURE_BAC_EXCEPTION_MSG =
			"sysmgmt.base.supermercadobacimpl.defaultexception";

	/** The supermercado dac. */
	private ISupermercadoDAC supermercadoDAC; // injected by Spring through setter

	private IEnderecoDAC enderecoDAC; // injected by Spring through setter

	private IDocumentoDAC documentoDAC; // injected by Spring through setter

	/**
	 * Spring Sets the supermercado dac.
	 * 
	 * @param supermercadoDAC the new supermercado dac
	 */
	public void setSupermercadoDAC(ISupermercadoDAC supermercadoDAC)
	{
		this.supermercadoDAC = supermercadoDAC;
	}

	/**
	 * Gets the supermercado dac.
	 * 
	 * @return the supermercado dac
	 */
	public ISupermercadoDAC getSupermercadoDAC()
	{
		return supermercadoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ISupermercadoBAC#insertSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	public IDocumentoDAC getDocumentoDAC()
	{
		return documentoDAC;
	}

	public void setDocumentoDAC(IDocumentoDAC documentoDAC)
	{
		this.documentoDAC = documentoDAC;
	}

	@Override
	public InternalResponse insertSupermercado(Supermercado supermercado)
	{
		// supermercado.setPrice(SupermercadoBAD.calculatePrice(INSERT_SEED));
		// InternalResponseLocal responseEndereco = new InternalResponseLocal();
		// responseEndereco = getEnderecoDAC().insertEndereco(supermercado.getEnderecos().get(0));
		// supermercado.getEnderecos().get(0).setId(responseEndereco.getId());
		return getSupermercadoDAC().insertSupermercado(supermercado);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ISupermercadoBAC#updateSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse updateSupermercado(Supermercado supermercado)
	{
		// supermercado.setPrice(SupermercadoBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getSupermercadoDAC().updateSupermercado(supermercado);
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
	 * com.qat.samples.sysmgmt.base.bac.ISupermercadoBAC#deleteSupermercado(com.qat.samples.sysmgmt.base.model.Supermercado
	 * )
	 */
	@Override
	public InternalResponse deleteSupermercado(Supermercado supermercado)
	{
		InternalResponse internalResponse = getSupermercadoDAC().deleteSupermercado(supermercado);
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
	 * @see com.qat.samples.sysmgmt.base.bac.ISupermercadoBAC#refreshSupermercados(int)
	 */
	@Override
	public void refreshSupermercados(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getSupermercadoDAC().deleteAllSupermercados();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getSupermercadoDAC().insertSupermercado(new Supermercado(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.ISupermercadoBAC#fetchAllSupermercados()
	 */
	@Override
	public InternalResultsResponse<Supermercado> fetchAllSupermercados()
	{
		InternalResultsResponse<Supermercado> response = new InternalResultsResponse<Supermercado>();
		response.getResultsList().addAll(getSupermercadoDAC().fetchAllSupermercados());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ISupermercadoBAC#fetchSupermercadoById(com.qat.samples.sysmgmt.base.model.
	 * Supermercado
	 * )
	 */
	@Override
	public InternalResultsResponse<Supermercado> fetchSupermercadoById(FetchByIdRequest request)
	{
		InternalResultsResponse<Supermercado> response = new InternalResultsResponse<Supermercado>();
		response.getResultsList().add(getSupermercadoDAC().fetchSupermercadoById(request));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bac.ISupermercadoBAC#fetchSupermercadosByRequest(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Supermercado> fetchSupermercadosByRequest(PagedInquiryRequest request)
	{
		return getSupermercadoDAC().fetchSupermercadosByRequest(request);
	}

}
