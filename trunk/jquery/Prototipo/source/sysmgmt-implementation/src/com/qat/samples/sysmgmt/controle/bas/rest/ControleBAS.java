package com.qat.samples.sysmgmt.controle.bas.rest;

import com.qat.samples.sysmgmt.controle.bai.IControleBAI;
import com.qat.samples.sysmgmt.controle.bas.IControleRESTBAS;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.response.AcessosResponse;
import com.qat.samples.sysmgmt.controle.model.response.ControleResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class ControleBAS implements IControleRESTBAS
{

	/** The controle bai. */
	private IControleBAI controleBAI; // injected by Spring through setter

	/**
	 * Sets the controle bai.
	 * 
	 * @param controleBAI the new controle bai
	 */
	public void setControleBAI(IControleBAI controleBAI)
	{
		this.controleBAI = controleBAI;
	}

	/**
	 * Gets the controle bai.
	 * 
	 * @return the controle bai
	 */
	public IControleBAI getControleBAI()
	{
		return controleBAI;
	}

	@Override
	public ControleResponse fetchControlesByRequest(ControleInquiryRequest request)
	{
		return getControleBAI().fetchControlesByRequest(request);
	}

	@Override
	public ControleResponse fetchAllControles(ControleInquiryRequest request)
	{
		return getControleBAI().fetchAllControles(request);
	}

	@Override
	public ControleResponse fetchControleByPage(ControleInquiryRequest request)
	{
		return getControleBAI().fetchControleByPage(request);
	}

	@Override
	public ControleResponse fetchControleByAction(ControleInquiryRequest request)
	{
		return getControleBAI().fetchControleByAction(request);
	}

	@Override
	public AcessosResponse fetchAllAcessos(AcessosInquiryRequest request)
	{
		return getControleBAI().fetchAllAcessos(request);
	}
}
