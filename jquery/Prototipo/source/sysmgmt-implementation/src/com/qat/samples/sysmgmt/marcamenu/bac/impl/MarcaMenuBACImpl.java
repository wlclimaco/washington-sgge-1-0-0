package com.qat.samples.sysmgmt.marcamenu.bac.impl;

import com.qat.framework.model.Message;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cidade.bac.ICidadeBAC;
import com.qat.samples.sysmgmt.cidade.dac.ICidadeDAC;
import com.qat.samples.sysmgmt.cidade.model.Cidade;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * Implementation of the ICidadeBAC leveraging a BAD, CidadeBAD.
 */
public class MarcaMenuBACImpl implements ICidadeBAC
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
	private ICidadeDAC cidadeDAC; // injected by Spring through setter

	/**
	 * Spring Sets the cidade dac.
	 * 
	 * @param cidadeDAC the new cidade dac
	 */
	public void setCidadeDAC(ICidadeDAC cidadeDAC)
	{
		this.cidadeDAC = cidadeDAC;
	}

	/**
	 * Gets the cidade dac.
	 * 
	 * @return the cidade dac
	 */
	public ICidadeDAC getCidadeDAC()
	{
		return cidadeDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ICidadeBAC#insertCidade(com.qat.samples.sysmgmt.base.model.Cidade)
	 */
	@Override
	public InternalResponse insertCidade(Cidade cidade)
	{
		// cidade.setPrice(CidadeBAD.calculatePrice(INSERT_SEED));
		return getCidadeDAC().insertCidade(cidade);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ICidadeBAC#updateCidade(com.qat.samples.sysmgmt.base.model.Cidade)
	 */
	@Override
	public InternalResponse updateCidade(Cidade cidade)
	{
		// cidade.setPrice(CidadeBAD.calculatePrice(UPDATE_SEED));
		InternalResponse internalResponse = getCidadeDAC().updateCidade(cidade);
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
	 * com.qat.samples.sysmgmt.base.bac.ICidadeBAC#deleteCidade(com.qat.samples.sysmgmt.base.model.Cidade)
	 */
	@Override
	public InternalResponse deleteCidade(Cidade cidade)
	{
		InternalResponse internalResponse = getCidadeDAC().deleteCidade(cidade);
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
	 * @see com.qat.samples.sysmgmt.base.bac.ICidadeBAC#refreshCidades(int)
	 */
	@Override
	public void refreshCidades(Integer refreshNumber)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		getCidadeDAC().deleteAllCidades();
		refreshNumber = (refreshNumber < 1) ? MINIMUM_ENTRIES : refreshNumber;

		for (int i = 1; i <= refreshNumber; i++)
		{
			getCidadeDAC().insertCidade(new Cidade(i, "CidadeDesc", "REFRESH_SEED"));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.base.bac.ICidadeBAC#fetchAllCidades()
	 */
	@Override
	public InternalResultsResponse<Cidade> fetchAllCidades()
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
		response.getResultsList().addAll(getCidadeDAC().fetchAllCidades());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.ICidadeBAC#fetchCidadeById(com.qat.samples.sysmgmt.base.model.Cidade
	 * )
	 */
	@Override
	public InternalResultsResponse<Cidade> fetchCidadeById(FetchByIdRequest request)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();
		response.getResultsList().add(getCidadeDAC().fetchCidadeById(request));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bac.ICidadeBAC#fetchCidadesByRequest(com.qat.samples.sysmgmt.model.request.
	 * CidadeInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Cidade> fetchCidadesByRequest(PagedInquiryRequest request)
	{
		return getCidadeDAC().fetchCidadesByRequest(request);
	}

}
