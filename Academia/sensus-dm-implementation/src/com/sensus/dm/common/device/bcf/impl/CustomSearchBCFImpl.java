package com.sensus.dm.common.device.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.bcf.ICustomSearchBCF;
import com.sensus.dm.common.device.bcl.ICustomSearchBCL;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.device.model.response.CustomSearchResponse;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class CustomSearchBCFImpl.
 * 
 * @author QAT Brazil.
 * 
 */
public class CustomSearchBCFImpl implements ICustomSearchBCF
{

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CustomSearchBCFImpl.class);

	/** The device bcl. */
	private ICustomSearchBCL customSearchBCL; // injected by Spring through setter

	/** The user tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The custom search validation controller. */
	private ValidationController customSearchValidationController; // injected by Spring through setter

	/** The order by validation controller. */
	private ValidationController orderByValidationController; // injected by Spring through setter

	/** The page size validation controller. */
	private ValidationController pageSizeValidationController; // injected by Spring through setter

	/** The column filter request validation controller. */
	private ValidationController columnFilterRequestValidationController; // injected by Spring through setter

	/**
	 * Gets the column filter request validation controller.
	 * 
	 * @return the column filter request validation controller
	 */
	public ValidationController getColumnFilterRequestValidationController()
	{
		return columnFilterRequestValidationController;
	}

	/**
	 * Sets the column filter request validation controller.
	 * 
	 * @param columnFilterRequestValidationController the new column filter request validation controller
	 */
	public void setColumnFilterRequestValidationController(ValidationController columnFilterRequestValidationController)
	{
		this.columnFilterRequestValidationController = columnFilterRequestValidationController;
	}

	/**
	 * Gets the order by validation controller.
	 * 
	 * @return the order by validation controller
	 */
	public ValidationController getOrderByValidationController()
	{
		return orderByValidationController;
	}

	/**
	 * Sets the order by validation controller.
	 * 
	 * @param orderByValidationController the new order by validation controller
	 */
	public void setOrderByValidationController(ValidationController orderByValidationController)
	{
		this.orderByValidationController = orderByValidationController;
	}

	/**
	 * Gets the page size validation controller.
	 * 
	 * @return the page size validation controller
	 */
	public ValidationController getPageSizeValidationController()
	{
		return pageSizeValidationController;
	}

	/**
	 * Sets the page size validation controller.
	 * 
	 * @param pageSizeValidationController the new page size validation controller
	 */
	public void setPageSizeValidationController(ValidationController pageSizeValidationController)
	{
		this.pageSizeValidationController = pageSizeValidationController;
	}

	/**
	 * Gets the custom search validation controller.
	 * 
	 * @return the custom search validation controller
	 */
	public ValidationController getCustomSearchValidationController()
	{
		return customSearchValidationController;
	}

	/**
	 * Sets the custom search validation controller.
	 * 
	 * @param customSearchValidationController the new custom search validation controller
	 */
	public void setCustomSearchValidationController(ValidationController customSearchValidationController)
	{
		this.customSearchValidationController = customSearchValidationController;
	}

	/**
	 * Gets the tenant request validation controller.
	 * 
	 * @return the tenant request validation controller
	 */
	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	/**
	 * Sets the tenant request validation controller.
	 * 
	 * @param tenantRequestValidationController the new tenant request validation controller
	 */
	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	/**
	 * Gets the custom search bcl.
	 * 
	 * @return the custom search bcl
	 */
	public ICustomSearchBCL getCustomSearchBCL()
	{
		return customSearchBCL;
	}

	/**
	 * Sets the custom search bcl.
	 * 
	 * @param customSearchBCL the new custom search bcl
	 */
	public void setCustomSearchBCL(ICustomSearchBCL customSearchBCL)
	{
		this.customSearchBCL = customSearchBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#insertCustomSearch(com.sensus.dm.elec..model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest customSearchRequest)
	{
		CustomSearchResponse response = new CustomSearchResponse();
		try
		{
			InternalResultsResponse<CustomSearch> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_CUSTOM_SEARCH);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), customSearchRequest);
			context.putObjectToBeValidated(CustomSearch.class.getSimpleName(), customSearchRequest.getCustomSearch());

			if (getTenantRequestValidationController().validate(context)
					&& getCustomSearchValidationController().validate(context))
			{
				internalResponse = getCustomSearchBCL().insertCustomSearch(customSearchRequest);
				response.setCustomSearches(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#deleteCustomSearch(com.sensus.dm.elec.radio.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest customSearchRequest)
	{
		CustomSearchResponse response = new CustomSearchResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE_CUSTOM_SEARCH);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), customSearchRequest);
			context.putObjectToBeValidated(CustomSearch.class.getSimpleName(), customSearchRequest.getCustomSearch());

			if (getTenantRequestValidationController().validate(context)
					&& getCustomSearchValidationController().validate(context))
			{
				internalResponse = getCustomSearchBCL().deleteCustomSearch(customSearchRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllCustomSearch(com.sensus.dm.elec.device.model.request.
	 * InquiryCustomSearchRequest)
	 */
	@Override
	public InquiryCustomSearchResponse fetchAllCustomSearch(InquiryCustomSearchRequest inquiryCustomSearchRequest)
	{
		InquiryCustomSearchResponse response = new InquiryCustomSearchResponse();
		try
		{
			InternalResultsResponse<CustomSearch> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_CUSTOM_SEARCH);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryCustomSearchRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryCustomSearchRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryCustomSearchRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context))
			{
				internalResponse = getCustomSearchBCL().fetchAllCustomSearch(inquiryCustomSearchRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllColumnFilters(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();
		try
		{
			InternalResultsResponse<ColumnFilterResponse> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_COLUMN_FILTERS);
			context.putObjectToBeValidated(ColumnFilterRequest.class.getSimpleName(), columnFilterRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), columnFilterRequest);

			if (getColumnFilterRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getCustomSearchBCL().fetchAllColumnFilters(columnFilterRequest);
				if (!ValidationUtil.isNullOrEmpty(internalResponse.getResultsList()))
				{
					response = internalResponse.getFirstResult();
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.IElectricMeterBCF#updateColumnFilters(com.sensus.dm.elec.device.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse updateColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE_COLUMN_FILTERS);
			context.putObjectToBeValidated(ColumnFilterRequest.class.getSimpleName(), columnFilterRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), columnFilterRequest);

			if (getColumnFilterRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getCustomSearchBCL().updateColumnFilters(columnFilterRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}
