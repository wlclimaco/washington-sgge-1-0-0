package com.sensus.dm.common.pessoa.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.pessoa.bcf.IPessoaBCF;
import com.sensus.dm.common.pessoa.bcl.IPessoaBCL;
import com.sensus.dm.common.pessoa.model.Pessoa;
import com.sensus.dm.common.pessoa.model.request.InquiryPessoaRequest;
import com.sensus.dm.common.pessoa.model.request.PessoaRequest;
import com.sensus.dm.common.pessoa.model.response.InquiryPessoaResponse;
import com.sensus.dm.common.pessoa.model.response.PessoaResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * Pessoa BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class PessoaBCFImpl implements IPessoaBCF
{

	/** The Constant DEFAULT_PESSOA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PESSOA_BCF_EXCEPTION_MSG = "sensus.dm.elec.pessoabcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PessoaBCFImpl.class);

	/** The pessoa bcl. */
	private IPessoaBCL pessoaBCL; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The order by validation controller. */
	private ValidationController orderByValidationController; // injected by Spring through setter

	/** The page size validation controller. */
	private ValidationController pageSizeValidationController; // injected by Spring through setter

	/** The base search validation controller. */
	private ValidationController baseSearchValidationController; // injected by Spring through setter

	/** The device search validation controller. */
	private ValidationController deviceSearchValidationController; // injected by Spring through setter

	/** The inquiry device request validation controller. */
	private ValidationController inquiryDeviceRequestValidationController; // injected by Spring through setter

	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	public ValidationController getOrderByValidationController()
	{
		return orderByValidationController;
	}

	public void setOrderByValidationController(ValidationController orderByValidationController)
	{
		this.orderByValidationController = orderByValidationController;
	}

	public ValidationController getPageSizeValidationController()
	{
		return pageSizeValidationController;
	}

	public void setPageSizeValidationController(ValidationController pageSizeValidationController)
	{
		this.pageSizeValidationController = pageSizeValidationController;
	}

	public ValidationController getBaseSearchValidationController()
	{
		return baseSearchValidationController;
	}

	public void setBaseSearchValidationController(ValidationController baseSearchValidationController)
	{
		this.baseSearchValidationController = baseSearchValidationController;
	}

	/**
	 * Gets the device search validation controller.
	 * 
	 * @return the device search validation controller
	 */
	public ValidationController getDeviceSearchValidationController()
	{
		return deviceSearchValidationController;
	}

	/**
	 * Sets the device search validation controller.
	 * 
	 * @param deviceSearchValidationController the new device search validation controller
	 */
	public void setDeviceSearchValidationController(ValidationController deviceSearchValidationController)
	{
		this.deviceSearchValidationController = deviceSearchValidationController;
	}

	/**
	 * Gets the inquiry device request validation controller.
	 * 
	 * @return the inquiry device request validation controller
	 */
	public ValidationController getInquiryDeviceRequestValidationController()
	{
		return inquiryDeviceRequestValidationController;
	}

	/**
	 * Sets the inquiry device request validation controller.
	 * 
	 * @param inquiryDeviceRequestValidationController the new inquiry device request validation controller
	 */
	public void setInquiryDeviceRequestValidationController(
			ValidationController inquiryDeviceRequestValidationController)
	{
		this.inquiryDeviceRequestValidationController = inquiryDeviceRequestValidationController;
	}

	/**
	 * Sets the pessoa bcl.
	 * 
	 * @param paramPessoaBCL the new pessoa bcl
	 */
	public void setPessoaBCL(IPessoaBCL paramPessoaBCL)
	{
		pessoaBCL = paramPessoaBCL;
	}

	/**
	 * Gets the pessoa bcl.
	 * 
	 * @return the pessoa bcl
	 */
	public IPessoaBCL getPessoaBCL()
	{
		return pessoaBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcf.IPessoaBCF#fetchAllPessoas(com.sensus.dm.common.pessoa.model.request.
	 * InquiryPessoaRequest
	 * )
	 */
	@Override
	public InquiryPessoaResponse fetchAllPessoas(InquiryPessoaRequest inquiryPessoaRequest)
	{
		InquiryPessoaResponse response = new InquiryPessoaResponse();

		try
		{
			InternalResultsResponse<Pessoa> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryPessoaRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryPessoaRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryPessoaRequest);
			context.putObjectToBeValidated(BaseSearch.class.getSimpleName(),
					inquiryPessoaRequest.getDeviceSearch());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getPessoaBCL().fetchAllPessoas(inquiryPessoaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PESSOA_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcf.IPessoaBCF#fetchPessoaById(com.sensus.dm.common.pessoa.model.request.PessoaRequest
	 * )
	 */
	@Override
	public PessoaResponse fetchPessoaById(InquiryPessoaRequest request)
	{
		PessoaResponse response = new PessoaResponse();

		try
		{
			InternalResultsResponse<Pessoa> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_ID);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Pessoa.class.getSimpleName(), request.getFirstPessoa());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getPessoaBCL().fetchAllPessoas(request);
				response.setPessoas(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PESSOA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcf.IPessoaBCF#fetchPessoaByName(com.sensus.dm.common.pessoa.model.request.PessoaRequest
	 * )
	 */
	@Override
	public PessoaResponse fetchPessoaByName(InquiryPessoaRequest inquiryPessoaRequest)
	{
		PessoaResponse response = new PessoaResponse();
		try
		{
			InternalResultsResponse<Pessoa> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_NAME);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryPessoaRequest);
			context.putObjectToBeValidated(Pessoa.class.getSimpleName(), inquiryPessoaRequest.getFirstPessoa());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getPessoaBCL().fetchAllPessoas(inquiryPessoaRequest);
				response.setPessoas(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PESSOA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcf.IPessoaBCF#insertPessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public PessoaResponse insertPessoa(PessoaRequest pessoaRequest)
	{
		PessoaResponse response = new PessoaResponse();
		try
		{
			InternalResultsResponse<Pessoa> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					pessoaRequest);
			context.putObjectToBeValidated(Pessoa.class.getSimpleName(),
					pessoaRequest.getFirstPessoa());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getPessoaBCL().insertPessoa(pessoaRequest);
				response.setPessoas(internalResponse.getResultsList());
				response.setProcessId(pessoaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PESSOA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcf.IPessoaBCF#updatePessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public PessoaResponse updatePessoa(PessoaRequest pessoaRequest)
	{
		PessoaResponse response = new PessoaResponse();
		try
		{
			InternalResultsResponse<Pessoa> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					pessoaRequest);
			context.putObjectToBeValidated(Pessoa.class.getSimpleName(),
					pessoaRequest.getFirstPessoa());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getPessoaBCL().updatePessoa(pessoaRequest);
				response.setPessoas(internalResponse.getResultsList());
				response.setProcessId(pessoaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PESSOA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.pessoa.bcf.IPessoaBCF#deletePessoa(com.sensus.dm.common.pessoa.model.request.PessoaRequest)
	 */
	@Override
	public PessoaResponse deletePessoa(PessoaRequest pessoaRequest)
	{
		PessoaResponse response = new PessoaResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					pessoaRequest);
			context.putObjectToBeValidated(Pessoa.class.getSimpleName(),
					pessoaRequest.getFirstPessoa());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getPessoaBCL().deletePessoa(pessoaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PESSOA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public DeviceResponse fetchDevicesByPessoa(PessoaRequest pessoaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
