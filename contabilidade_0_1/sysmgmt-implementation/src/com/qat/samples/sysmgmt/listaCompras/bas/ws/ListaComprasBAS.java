package com.qat.samples.sysmgmt.listaCompras.bas.ws;

import javax.jws.WebService;

import com.qat.samples.sysmgmt.listaCompras.bai.IListaComprasBAI;
import com.qat.samples.sysmgmt.listaCompras.bas.IListaComprasBAS;
import com.qat.samples.sysmgmt.listaCompras.model.request.ListaComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.listaCompras.model.response.ListaComprasResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
@WebService(targetNamespace = "http://qat.com/sysmgmt")
public class ListaComprasBAS implements IListaComprasBAS
{

	/** The listaCompras bai. */
	private IListaComprasBAI listaComprasBAI; // injected by Spring through setter

	/**
	 * Spring Sets the listaCompras bai.
	 * 
	 * @param listaComprasBAI the new listaCompras bai
	 */
	public void setListaComprasBAI(IListaComprasBAI listaComprasBAI)
	{
		this.listaComprasBAI = listaComprasBAI;
	}

	/**
	 * Gets the listaCompras bac.
	 * 
	 * @return the listaCompras bac
	 */
	public IListaComprasBAI getListaComprasBAI()
	{
		return listaComprasBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IListaComprasBAS#insertListaCompras(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasMaintenanceRequest
	 * )
	 */
	@Override
	public ListaComprasResponse insertListaCompras(ListaComprasMaintenanceRequest request)
	{
		return getListaComprasBAI().insertListaCompras(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IListaComprasBAS#updateListaCompras(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasMaintenanceRequest
	 * )
	 */
	@Override
	public ListaComprasResponse updateListaCompras(ListaComprasMaintenanceRequest request)
	{
		return getListaComprasBAI().updateListaCompras(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.IListaComprasBAS#deleteListaCompras(com.qat.samples.sysmgmt.model.request.
	 * ListaComprasMaintenanceRequest
	 * )
	 */
	@Override
	public ListaComprasResponse deleteListaCompras(ListaComprasMaintenanceRequest request)
	{
		return getListaComprasBAI().deleteListaCompras(request);
	}

	@Override
	public ListaComprasResponse fetchAllListaCompras(FetchAllRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse fetchListaComprasById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse refreshListaCompras(RefreshRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse fetchListaComprasByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// /*
	// * (non-Javadoc)
	// * @see com.qat.samples.sysmgmt.bas.IListaComprasBAS#refreshListaComprass(com.qat.samples.sysmgmt.model.request.
	// * RefreshRequest)
	// */
	// @Override
	// public ListaComprasResponse refreshListaCompras(RefreshRequest request)
	// {
	// // This method is demo code only. Do not view this as a QAT Global Standard.
	// return getListaComprasBAI().refreshListaComprass(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bas.IListaComprasBAS#fetchAllListaComprass(com.qat.samples.sysmgmt.model.request.
	// * FetchAllRequest)
	// */
	// @Override
	// public ListaComprasResponse fetchAllListaCompras(FetchAllRequest request)
	// {
	// return getListaComprasBAI().fetchAllListaComprass(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bas.IListaComprasBAS#fetchListaComprasById(com.qat.samples.sysmgmt.model.request.
	// * FetchByIdRequest)
	// */
	// @Override
	// public ListaComprasResponse fetchListaComprasById(FetchByIdRequest request)
	// {
	// return getListaComprasBAI().fetchListaComprasById(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bas.IListaComprasBAS#fetchListaComprassByRequest(com.qat.samples.sysmgmt.model.request.
	// * PagedInquiryRequest)
	// */
	// @Override
	// public ListaComprasResponse fetchListaComprasByRequest(PagedInquiryRequest request)
	// {
	// return getListaComprasBAI().fetchListaComprassByRequest(request);
	// }

}
