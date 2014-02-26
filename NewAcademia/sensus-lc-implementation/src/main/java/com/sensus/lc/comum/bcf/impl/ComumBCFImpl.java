package com.sensus.lc.comum.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.comentario.model.Comentario;
import com.sensus.lc.comentario.model.request.ComentarioRequest;
import com.sensus.lc.comentario.model.request.InquiryComentarioRequest;
import com.sensus.lc.comentario.model.response.ComentarioResponse;
import com.sensus.lc.comentario.model.response.InquiryComentarioResponse;
import com.sensus.lc.comum.bcf.IComumBCF;
import com.sensus.lc.comum.bcl.IComumBCL;
import com.sensus.lc.curtir.model.Curtir;
import com.sensus.lc.curtir.model.request.CurtirRequest;
import com.sensus.lc.curtir.model.request.InquiryCurtirRequest;
import com.sensus.lc.curtir.model.response.CurtirResponse;
import com.sensus.lc.curtir.model.response.InquiryCurtirResponse;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;
import com.sensus.lc.foto.model.response.FotoResponse;
import com.sensus.lc.foto.model.response.InquiryFotoResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * Academia BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class ComumBCFImpl implements IComumBCF
{

	/** The Constant DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG = "sensus.dm.elec.academiabcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ComumBCFImpl.class);

	/** The academia bcl. */
	private IComumBCL comumBCL; // injected by Spring through setter

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

	public IComumBCL getComumBCL()
	{
		return comumBCL;
	}

	public void setComumBCL(IComumBCL comumBCL)
	{
		this.comumBCL = comumBCL;
	}

	@Override
	public InquiryComentarioResponse fetchAllComentarios(InquiryComentarioRequest inquiryComentarioRequest)
	{
		InquiryComentarioResponse response = new InquiryComentarioResponse();
		InternalResultsResponse<Comentario> internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();

			internalResponse = getComumBCL().fetchAllComentarios(inquiryComentarioRequest);

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public InquiryComentarioResponse fetchComentarioById(InquiryComentarioRequest inquiryComentarioRequest)
	{
		InquiryComentarioResponse response = new InquiryComentarioResponse();
		InternalResultsResponse<Comentario> internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryComentarioRequest);
			context.putObjectToBeValidated(Comentario.class.getSimpleName(),
					inquiryComentarioRequest.getFirstComentario());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getComumBCL().fetchAllComentarios(inquiryComentarioRequest);
				response.setComentarios(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public ComentarioResponse insertComentario(ComentarioRequest comentarioRequest)
	{
		ComentarioResponse response = new ComentarioResponse();
		InternalResultsResponse<Comentario> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			internalResponse = getComumBCL().insertComentario(comentarioRequest);
			response.setComentarios(internalResponse.getResultsList());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	@Override
	public ComentarioResponse updateComentario(ComentarioRequest comentarioRequest)
	{
		ComentarioResponse response = new ComentarioResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);

			internalResponse = getComumBCL().updateComentario(comentarioRequest);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public ComentarioResponse deleteComentario(ComentarioRequest dietaRequest)
	{
		ComentarioResponse response = new ComentarioResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DELETE);

			internalResponse = getComumBCL().deleteComentario(dietaRequest);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public InquiryCurtirResponse fetchAllCurtirs(InquiryCurtirRequest inquiryCurtirRequest)
	{
		InquiryCurtirResponse response = new InquiryCurtirResponse();
		InternalResultsResponse<Curtir> internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();

			internalResponse = getComumBCL().fetchAllCurtirs(inquiryCurtirRequest);

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public InquiryCurtirResponse fetchCurtirById(InquiryCurtirRequest inquiryCurtirRequest)
	{
		InquiryCurtirResponse response = new InquiryCurtirResponse();
		InternalResultsResponse<Curtir> internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryCurtirRequest);
			context.putObjectToBeValidated(Comentario.class.getSimpleName(),
					inquiryCurtirRequest.getFirstCurtir());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getComumBCL().fetchAllCurtirs(inquiryCurtirRequest);
				response.setCurtir(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public CurtirResponse insertCurtir(CurtirRequest curtirRequest)
	{
		CurtirResponse response = new CurtirResponse();
		InternalResultsResponse<Curtir> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			internalResponse = getComumBCL().insertCurtir(curtirRequest);
			response.setCurtir(internalResponse.getResultsList());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public CurtirResponse updateCurtir(CurtirRequest dietaRequest)
	{
		CurtirResponse response = new CurtirResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);

			internalResponse = getComumBCL().updateCurtir(dietaRequest);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public CurtirResponse deleteCurtir(CurtirRequest dietaRequest)
	{
		CurtirResponse response = new CurtirResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DELETE);

			internalResponse = getComumBCL().deleteCurtir(dietaRequest);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public InquiryFotoResponse fetchAllFotos(InquiryFotoRequest inquiryFotoRequest)
	{
		InquiryFotoResponse response = new InquiryFotoResponse();
		InternalResultsResponse<Foto> internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();

			internalResponse = getComumBCL().fetchAllFotos(inquiryFotoRequest);

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public InquiryFotoResponse fetchFotoById(InquiryFotoRequest inquiryFotoRequest)
	{
		InquiryFotoResponse response = new InquiryFotoResponse();
		InternalResultsResponse<Foto> internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryFotoRequest);
			context.putObjectToBeValidated(Comentario.class.getSimpleName(),
					inquiryFotoRequest.getFirstFoto());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getComumBCL().fetchFotoById(inquiryFotoRequest);
				response.setFotos(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public FotoResponse insertFoto(FotoRequest dietaRequest)
	{
		FotoResponse response = new FotoResponse();
		InternalResultsResponse<Foto> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			internalResponse = getComumBCL().insertFoto(dietaRequest);
			response.setFotos(internalResponse.getResultsList());

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public FotoResponse updateFoto(FotoRequest dietaRequest)
	{
		FotoResponse response = new FotoResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);

			internalResponse = getComumBCL().updateFoto(dietaRequest);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public FotoResponse deleteFoto(FotoRequest dietaRequest)
	{
		FotoResponse response = new FotoResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DELETE);

			internalResponse = getComumBCL().deleteFoto(dietaRequest);

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

}
