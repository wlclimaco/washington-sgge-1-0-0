package com.qat.samples.sysmgmt.listaCompras.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.listaCompras.bac.IListaComprasBAC;
import com.qat.samples.sysmgmt.listaCompras.bai.IListaComprasBAI;
import com.qat.samples.sysmgmt.listaCompras.baid.ListaComprasBAID;
import com.qat.samples.sysmgmt.listaCompras.model.request.ListaComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.listaCompras.model.response.ListaComprasResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Class ListasComprasBAIImpl.
 */
public class ListaComprasBAIImpl implements IListaComprasBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.cidadebaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ListaComprasBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ListaComprasBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The cidade bac. */
	private IListaComprasBAC cidadeBAC;

	/**
	 * Gets the validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getValidationController()
	{
		return validationController;
	}

	/**
	 * Sets the validation controller.
	 * 
	 * @param countyValidationController the new validation controller
	 */
	public void setValidationController(ValidationController countyValidationController)
	{
		validationController = countyValidationController;
	}

	/**
	 * Sets the cidade bac.
	 * 
	 * @param cidadeBAC the new cidade bac
	 */
	public void setListasComprasBAC(IListaComprasBAC cidadeBAC)
	{
		this.cidadeBAC = cidadeBAC;
	}

	/**
	 * Gets the cidade bac.
	 * 
	 * @return the cidade bac
	 */
	public IListaComprasBAC getListasComprasBAC()
	{
		return cidadeBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IListasComprasBAI#insertListasCompras(com.qat.samples.sysmgmt.model.request.
	 * ListasComprasMaintenanceRequest
	 * )
	 */
	@Override
	public ListaComprasResponse insertListaCompras(ListaComprasMaintenanceRequest request)
	{
		ListaComprasResponse response = new ListaComprasResponse();
		try
		{
			ListaComprasBAID.maintainListaCompras(getListasComprasBAC(), ValidationContextIndicator.INSERT,
					getValidationController(),
					PersistanceActionEnum.INSERT, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IListasComprasBAI#updateListasCompras(com.qat.samples.sysmgmt.model.request.
	 * ListasComprasMaintenanceRequest
	 * )
	 */
	@Override
	public ListaComprasResponse updateListaCompras(ListaComprasMaintenanceRequest request)
	{
		ListaComprasResponse response = new ListaComprasResponse();
		try
		{
			ListaComprasBAID.maintainListaCompras(getListasComprasBAC(), ValidationContextIndicator.UPDATE,
					getValidationController(),
					PersistanceActionEnum.UPDATE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IListasComprasBAI#deleteListasCompras(com.qat.samples.sysmgmt.model.request.
	 * ListasComprasMaintenanceRequest
	 * )
	 */
	@Override
	public ListaComprasResponse deleteListaCompras(ListaComprasMaintenanceRequest request)
	{
		ListaComprasResponse response = new ListaComprasResponse();
		try
		{
			ListaComprasBAID.maintainListaCompras(getListasComprasBAC(), ValidationContextIndicator.DELETE,
					getValidationController(),
					PersistanceActionEnum.DELETE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	// /*
	// * (non-Javadoc)
	// * @see com.qat.samples.sysmgmt.bai.IListasComprasBAI#refreshListasComprass(com.qat.samples.sysmgmt.model.request.
	// * RefreshRequest)
	// */
	// @Override
	// public ListaComprasResponse refreshListaComprass(RefreshRequest request)
	// {
	// // This method is demo code only. Do not view this as a QAT Standard.
	// ListaComprasResponse response = new ListaComprasResponse();
	// try
	// {
	// ListaComprasBAID.refreshListaComprass(getListasComprasBAC(), request, response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }

	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bai.IListasComprasBAI#fetchAllListasComprass(com.qat.samples.sysmgmt.model.request.
	// * FetchAllRequest)
	// */
	// @Override
	// public ListaComprasResponse fetchAllListaComprass(FetchAllRequest request)
	// {
	// ListaComprasResponse response = new ListaComprasResponse();
	// try
	// {
	// ListaComprasBAID.fetchAllListaComprass(getListasComprasBAC(), response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IListasComprasBAI#fetchListasComprasById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest)
	 */
	// @Override
	// public ListaComprasResponse fetchListaComprasById(FetchByIdRequest request)
	// {
	// ListaComprasResponse response = new ListaComprasResponse();
	// try
	// {
	// ListaComprasBAID.fetchListaComprasById(getListasComprasBAC(), request, response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }
	//
	// @Override
	// public ListaComprasResponse fetchListaComprassByRequest(PagedInquiryRequest request)
	// {
	// ListaComprasResponse response = new ListaComprasResponse();
	// try
	// {
	// ListaComprasBAID.fetchListaComprassPaged(getListasComprasBAC(), request, response);
	// }
	// catch (Exception ex)
	// {
	// QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
	// }
	// return response;
	// }

	@Override
	public ListaComprasResponse insertListaCompraItens(ListaComprasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse updateListaCompraItens(ListaComprasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse deleteListaCompraItens(ListaComprasMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse fetchAllListaCompraItenss(FetchAllRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse refreshListaCompraItenss(RefreshRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse fetchListaCompraItensById(FetchByIdRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaComprasResponse fetchListaCompraItenssByRequest(PagedInquiryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
