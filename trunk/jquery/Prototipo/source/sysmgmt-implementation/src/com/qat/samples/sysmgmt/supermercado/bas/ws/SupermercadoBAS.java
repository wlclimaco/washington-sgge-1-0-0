package com.qat.samples.sysmgmt.supermercado.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.bai.ISupermercadoBAI;
import com.qat.samples.sysmgmt.supermercado.bas.ISupermercadoBAS;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http:// www.supermercado.kinghost.net/sysmgmt")
public class SupermercadoBAS implements ISupermercadoBAS
{

	/** The supermercado bai. */
	private ISupermercadoBAI supermercadoBAI; // injected by Spring through setter

	/**
	 * Spring Sets the supermercado bai.
	 * 
	 * @param supermercadoBAI the new supermercado bai
	 */
	public void setSupermercadoBAI(ISupermercadoBAI supermercadoBAI)
	{
		this.supermercadoBAI = supermercadoBAI;
	}

	/**
	 * Gets the supermercado bac.
	 * 
	 * @return the supermercado bac
	 */
	public ISupermercadoBAI getSupermercadoBAI()
	{
		return supermercadoBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoBAS#insertSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest
	 * )
	 */
	@Override
	public SupermercadoResponse insertSupermercado(SupermercadoMaintenanceRequest request)
	{
		return getSupermercadoBAI().insertSupermercado(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoBAS#updateSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest
	 * )
	 */
	@Override
	public SupermercadoResponse updateSupermercado(SupermercadoMaintenanceRequest request)
	{
		return getSupermercadoBAI().updateSupermercado(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoBAS#deleteSupermercado(com.qat.samples.sysmgmt.model.request.
	 * SupermercadoMaintenanceRequest
	 * )
	 */
	@Override
	public SupermercadoResponse deleteSupermercado(SupermercadoMaintenanceRequest request)
	{
		return getSupermercadoBAI().deleteSupermercado(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISupermercadoBAS#refreshSupermercados(com.qat.samples.sysmgmt.model.request.
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
	 * com.qat.samples.sysmgmt.bas.ISupermercadoBAS#fetchAllSupermercados(com.qat.samples.sysmgmt.model.request.
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
	 * com.qat.samples.sysmgmt.bas.ISupermercadoBAS#fetchSupermercadoById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	@Override
	public SupermercadoResponse fetchSupermercadoById(FetchByIdRequest request)
	{
		return getSupermercadoBAI().fetchSupermercadoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISupermercadoBAS#fetchSupermercadosByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
	@Override
	public SupermercadoResponse fetchSupermercadosByRequest(PagedInquiryRequest request)
	{
		return getSupermercadoBAI().fetchSupermercadosByRequest(request);
	}

}
