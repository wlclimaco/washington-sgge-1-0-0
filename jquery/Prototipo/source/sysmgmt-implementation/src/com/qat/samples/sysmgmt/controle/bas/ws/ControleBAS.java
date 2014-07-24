package com.qat.samples.sysmgmt.controle.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.controle.bai.IControleBAI;
import com.qat.samples.sysmgmt.controle.bas.IControleBAS;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.response.AcessosResponse;
import com.qat.samples.sysmgmt.controle.model.response.ControleResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt")
public class ControleBAS implements IControleBAS
{

	/** The cidade bai. */
	private IControleBAI cidadeBAI; // injected by Spring through setter

	/**
	 * Spring Sets the cidade bai.
	 * 
	 * @param cidadeBAI the new cidade bai
	 */
	public void setControleBAI(IControleBAI cidadeBAI)
	{
		this.cidadeBAI = cidadeBAI;
	}

	/**
	 * Gets the cidade bac.
	 * 
	 * @return the cidade bac
	 */
	public IControleBAI getControleBAI()
	{
		return cidadeBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.IControleBAS#fetchControlesByRequest(com.qat.samples.sysmgmt.model.request.
	 * PagedInquiryRequest)
	 */
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
