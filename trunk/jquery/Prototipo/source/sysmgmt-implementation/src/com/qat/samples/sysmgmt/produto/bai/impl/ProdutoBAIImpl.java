package com.qat.samples.sysmgmt.produto.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.framework.validation.ValidationContextIndicator;
import com.qat.framework.validation.ValidationController;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.bai.IProdutoBAI;
import com.qat.samples.sysmgmt.produto.baid.ProdutoBAID;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.CadastroResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

/**
 * The Class ProdutoBAIImpl.
 */
public class ProdutoBAIImpl implements IProdutoBAI
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sysmgmt.base.produtobaiimpl.defaultexception";

	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = ProdutoBAIImpl.class.getName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoBAIImpl.class);

	/** The validation controller. */
	private ValidationController validationController;

	/** The produto bac. */
	private IProdutoBAC produtoBAC;

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
	 * Sets the produto bac.
	 * 
	 * @param produtoBAC the new produto bac
	 */
	public void setProdutoBAC(IProdutoBAC produtoBAC)
	{
		this.produtoBAC = produtoBAC;
	}

	/**
	 * Gets the produto bac.
	 * 
	 * @return the produto bac
	 */
	public IProdutoBAC getProdutoBAC()
	{
		return produtoBAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bai.IProdutoBAI#insertProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
	 * )
	 */
	@Override
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			ProdutoBAID.maintainProduto(getProdutoBAC(), ValidationContextIndicator.INSERT, getValidationController(),
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
	 * com.qat.samples.sysmgmt.bai.IProdutoBAI#updateProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
	 * )
	 */
	@Override
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			ProdutoBAID.maintainProduto(getProdutoBAC(), ValidationContextIndicator.UPDATE, getValidationController(),
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
	 * com.qat.samples.sysmgmt.bai.IProdutoBAI#deleteProduto(com.qat.samples.sysmgmt.model.request.ProdutoMaintenanceRequest
	 * )
	 */
	@Override
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			ProdutoBAID.maintainProduto(getProdutoBAC(), ValidationContextIndicator.DELETE, getValidationController(),
					PersistanceActionEnum.DELETE, request, response);
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
	 * com.qat.samples.sysmgmt.bai.IProdutoBAI#refreshProdutos(com.qat.samples.sysmgmt.model.request.RefreshRequest)
	 */
	@Override
	public ProdutoResponse refreshProdutos(RefreshRequest request)
	{
		// This method is demo code only. Do not view this as a QAT Standard.
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			ProdutoBAID.refreshProdutos(getProdutoBAC(), request, response);
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
	 * com.qat.samples.sysmgmt.bai.IProdutoBAI#fetchAllProdutos(com.qat.samples.sysmgmt.model.request.FetchAllRequest)
	 */
	@Override
	public ProdutoResponse fetchAllProdutos(ProdutoInquiryRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			ProdutoBAID.fetchAllProdutos(getProdutoBAC(), response, request);
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
	 * com.qat.samples.sysmgmt.bai.IProdutoBAI#fetchProdutoById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)
	 */
	@Override
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			ProdutoBAID.fetchProdutoById(getProdutoBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public ProdutoResponse fetchProdutosByRequest(PagedInquiryRequest request)
	{
		ProdutoResponse response = new ProdutoResponse();
		try
		{
			ProdutoBAID.fetchProdutosPaged(getProdutoBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CadastroResponse insertCadastro(CadastroMaintenanceRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			ProdutoBAID.maintainCadastro(getProdutoBAC(), ValidationContextIndicator.INSERT, getValidationController(),
					PersistanceActionEnum.INSERT, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CadastroResponse updateCadastro(CadastroMaintenanceRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			ProdutoBAID.maintainCadastro(getProdutoBAC(), ValidationContextIndicator.UPDATE, getValidationController(),
					PersistanceActionEnum.UPDATE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CadastroResponse deleteCadastro(CadastroMaintenanceRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			ProdutoBAID.maintainCadastro(getProdutoBAC(), ValidationContextIndicator.DELETE, getValidationController(),
					PersistanceActionEnum.DELETE, request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CadastroResponse fetchAllCadastros(CadastroInquiryRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			ProdutoBAID.fetchAllCadastros(getProdutoBAC(), response, request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CadastroResponse refreshCadastros(RefreshRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CadastroResponse fetchCadastroById(FetchByIdRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			ProdutoBAID.fetchCadastroById(getProdutoBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}

	@Override
	public CadastroResponse fetchCadastrosByRequest(CadastroInquiryRequest request)
	{
		CadastroResponse response = new CadastroResponse();
		try
		{
			ProdutoBAID.fetchCadastrosPaged(getProdutoBAC(), request, response);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG, new Object[] {CLASS_NAME});
		}
		return response;
	}
}
