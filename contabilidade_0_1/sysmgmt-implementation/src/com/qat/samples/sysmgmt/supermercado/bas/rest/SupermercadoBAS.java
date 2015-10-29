package com.qat.samples.sysmgmt.supermercado.bas.rest;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.bai.ISupermercadoBAI;
import com.qat.samples.sysmgmt.supermercado.bas.ISupermercadoRESTBAS;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class SupermercadoBAS implements ISupermercadoRESTBAS
{

	/** The supermercado bai. */
	private ISupermercadoBAI supermercadoBAI; // injected by Spring through setter

	/**
	 * Sets the supermercado bai.
	 * 
	 * @param supermercadoBAI the new supermercado bai
	 */
	public void setSupermercadoBAI(ISupermercadoBAI supermercadoBAI)
	{
		this.supermercadoBAI = supermercadoBAI;
	}

	/**
	 * Gets the supermercado bai.
	 * 
	 * @return the supermercado bai
	 */
	public ISupermercadoBAI getSupermercadoBAI()
	{
		return supermercadoBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISupermercadoRESTBAS#insertSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest)
	 */
	@Override
	public SupermercadoResponse insertSupermercado(SupermercadoMaintenanceRequest request)
	{
		return getSupermercadoBAI().insertSupermercado(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISupermercadoRESTBAS#updateSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest)
	 */
	@Override
	public SupermercadoResponse updateSupermercado(SupermercadoMaintenanceRequest request)
	{
		return getSupermercadoBAI().updateSupermercado(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISupermercadoRESTBAS#deleteSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest)
	 */
	@Override
	public SupermercadoResponse deleteSupermercado(SupermercadoMaintenanceRequest request)
	{
		return getSupermercadoBAI().deleteSupermercado(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoRESTBAS#refreshSupermercados(com.qat.samples.sysmgmt.model.request.
	 * RefreshRequest)
	 */
	@Override
	public SupermercadoResponse refreshSupermercados(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Global Standard.
		return getSupermercadoBAI().refreshSupermercados(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoRESTBAS#fetchAllSupermercados(com.qat.samples.sysmgmt.model.request.
	 * FetchAllRequest)
	 */
	@Override
	public SupermercadoResponse fetchAllSupermercados(FetchAllRequest request)
	{
		return getSupermercadoBAI().fetchAllSupermercados(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoRESTBAS#fetchSupermercadoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest
	 * )
	 */
	@Override
	public SupermercadoResponse fetchSupermercadoById(FetchByIdRequest request)
	{
		return getSupermercadoBAI().fetchSupermercadoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoRESTBAS#fetchSupermercadosByRequest(com.qat.samples.sysmgmt.model.request
	 * .
	 * PagedInquiryRequest)
	 */
	@Override
	public SupermercadoResponse fetchSupermercadosByRequest(PagedInquiryRequest request)
	{
		return getSupermercadoBAI().fetchSupermercadosByRequest(request);
	}
}
