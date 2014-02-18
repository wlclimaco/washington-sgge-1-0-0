package com.sensus.lc.exercicio.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.exercicio.bcf.IExercicioBCF;
import com.sensus.lc.exercicio.bcl.IExercicioBCL;
import com.sensus.lc.exercicio.model.Exercicio;
import com.sensus.lc.exercicio.model.request.ExercicioRequest;
import com.sensus.lc.exercicio.model.request.InquiryExercicioRequest;
import com.sensus.lc.exercicio.model.response.ExercicioResponse;
import com.sensus.lc.exercicio.model.response.InquiryExercicioResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * Exercicio BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class ExercicioBCFImpl implements IExercicioBCF
{

	/** The Constant DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG =
			"sensus.dm.elec.exerciciobcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ExercicioBCFImpl.class);

	/** The exercicio bcl. */
	private IExercicioBCL exercicioBCL; // injected by Spring through setter

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
	 * Sets the exercicio bcl.
	 * 
	 * @param paramExercicioBCL the new exercicio bcl
	 */
	public void setExercicioBCL(IExercicioBCL paramExercicioBCL)
	{
		exercicioBCL = paramExercicioBCL;
	}

	/**
	 * Gets the exercicio bcl.
	 * 
	 * @return the exercicio bcl
	 */
	public IExercicioBCL getExercicioBCL()
	{
		return exercicioBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcf.IExercicioBCF#fetchAllExercicios(com.sensus.dm.common.exercicio.model.request.
	 * InquiryExercicioRequest
	 * )
	 */
	@Override
	public InquiryExercicioResponse fetchAllExercicios(InquiryExercicioRequest inquiryExercicioRequest)
	{
		InquiryExercicioResponse response = new InquiryExercicioResponse();

		try
		{
			InternalResultsResponse<Exercicio> internalResponse = null;

			ValidationContext context = new ValidationContext();

			internalResponse = getExercicioBCL().fetchAllExercicios(inquiryExercicioRequest);

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcf.IExercicioBCF#fetchExercicioById(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public ExercicioResponse fetchExercicioById(InquiryExercicioRequest request)
	{
		ExercicioResponse response = new ExercicioResponse();

		try
		{
			InternalResultsResponse<Exercicio> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Exercicio.class.getSimpleName(), request.getFirstExercicio());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getExercicioBCL().fetchAllExercicios(request);
				response.setExercicios(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcf.IExercicioBCF#fetchExercicioByName(com.sensus.dm.common.exercicio.model.request
	 * .
	 * ExercicioRequest)
	 */
	@Override
	public ExercicioResponse fetchExercicioByName(InquiryExercicioRequest inquiryExercicioRequest)
	{
		ExercicioResponse response = new ExercicioResponse();
		try
		{
			InternalResultsResponse<Exercicio> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryExercicioRequest);
			context.putObjectToBeValidated(Exercicio.class.getSimpleName(), inquiryExercicioRequest.getFirstExercicio());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getExercicioBCL().fetchAllExercicios(inquiryExercicioRequest);
				response.setExercicios(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcf.IExercicioBCF#insertExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public ExercicioResponse insertExercicio(ExercicioRequest exercicioRequest)
	{
		ExercicioResponse response = new ExercicioResponse();
		try
		{
			InternalResultsResponse<Exercicio> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					exercicioRequest);
			context.putObjectToBeValidated(Exercicio.class.getSimpleName(),
					exercicioRequest.getFirstExercicio());

			internalResponse = getExercicioBCL().insertExercicio(exercicioRequest);
			response.setExercicios(internalResponse.getResultsList());
			// response.setProcessId(exercicioRequest.getProcessId());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcf.IExercicioBCF#updateExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public ExercicioResponse updateExercicio(ExercicioRequest exercicioRequest)
	{
		ExercicioResponse response = new ExercicioResponse();
		try
		{
			InternalResultsResponse<Exercicio> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					exercicioRequest);
			context.putObjectToBeValidated(Exercicio.class.getSimpleName(),
					exercicioRequest.getExercicios());

			internalResponse = getExercicioBCL().updateExercicio(exercicioRequest);
			response.setExercicios(internalResponse.getResultsList());
			// response.setProcessId(exercicioRequest.getProcessId());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcf.IExercicioBCF#deleteExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public ExercicioResponse deleteExercicio(ExercicioRequest exercicioRequest)
	{
		ExercicioResponse response = new ExercicioResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					exercicioRequest);
			context.putObjectToBeValidated(Exercicio.class.getSimpleName(),
					exercicioRequest.getFirstExercicio());
			//
			// if (getTenantRequestValidationController().validate(context))
			// {
			internalResponse = getExercicioBCL().deleteExercicio(exercicioRequest);
			// }

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public InquiryExercicioResponse fetchAllExercicioByUser(InquiryExercicioRequest inquiryExercicioRequest)
	{
		InquiryExercicioResponse response = new InquiryExercicioResponse();

		try
		{
			InternalResultsResponse<Exercicio> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryExercicioRequest);
			context.putObjectToBeValidated(Exercicio.class.getSimpleName(), inquiryExercicioRequest.getExercicios());

			// if (getTenantRequestValidationController().validate(context))
			// {
			internalResponse = getExercicioBCL().fetchAllExercicioByUser(inquiryExercicioRequest);
			response.setExercicios(internalResponse.getResultsList());
			// }

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXERCICIO_BCF_EXCEPTION_MSG);
		}

		return response;
	}

}
