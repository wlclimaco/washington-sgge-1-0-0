package com.qat.samples.sysmgmt.controle.bac.impl;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.controle.bac.IControleBAC;
import com.qat.samples.sysmgmt.controle.dac.IControleDAC;
import com.qat.samples.sysmgmt.controle.model.Controle;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * Implementation of the ICidadeBAC leveraging a BAD, CidadeBAD.
 */
public class ControleBACImpl implements IControleBAC
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
	private IControleDAC controleDAC; // injected by Spring through setter

	/**
	 * Spring Sets the controle dac.
	 * 
	 * @param controleDAC the new controle dac
	 */
	public void setControleDAC(IControleDAC controleDAC)
	{
		this.controleDAC = controleDAC;
	}

	/**
	 * Gets the controle dac.
	 * 
	 * @return the controle dac
	 */
	public IControleDAC getControleDAC()
	{
		return controleDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.base.bac.IControleBAC#insertControle(com.qat.samples.sysmgmt.base.model.Controle)
	 */

	@Override
	public InternalResultsResponse<Controle> fetchAllControles(ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> response = new InternalResultsResponse<Controle>();
		response.getResultsList().addAll(getControleDAC().fetchAllControles(request));
		return response;
	}

	@Override
	public InternalResultsResponse<ControleAcess> fetchAllAcessos(AcessosInquiryRequest request)
	{
		InternalResultsResponse<ControleAcess> response = new InternalResultsResponse<ControleAcess>();
		response.getResultsList().addAll(getControleDAC().fetchAllAcessos(request));
		return response;
	}

	@Override
	public InternalResultsResponse<Controle> fetchControleByPage(ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> response = new InternalResultsResponse<Controle>();
		response.getResultsList().addAll(getControleDAC().fetchControleByPage(request));
		return response;
	}

	@Override
	public InternalResultsResponse<Controle> fetchControlesByRequest(ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> response = new InternalResultsResponse<Controle>();
		response.getResultsList().addAll(getControleDAC().fetchControlesByRequest(request));
		return response;
	}

	@Override
	public InternalResultsResponse<Controle> fetchControleByAction(ControleInquiryRequest request)
	{
		InternalResultsResponse<Controle> response = new InternalResultsResponse<Controle>();
		response.getResultsList().addAll(getControleDAC().fetchControleByAction(request));
		return response;
	}

}
