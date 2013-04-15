package com.sensus.mlc.gestao.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.gestao.bcf.IEventosBCF;
import com.sensus.mlc.gestao.bcl.IEventosBCL;
import com.sensus.mlc.gestao.model.Eventos;
import com.sensus.mlc.gestao.model.request.EventosRequest;
import com.sensus.mlc.gestao.model.request.InquiryEventosRequest;
import com.sensus.mlc.gestao.model.response.EventosResponse;
import com.sensus.mlc.gestao.model.response.InquiryEventosResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class EventosBCFImpl.
 *
 * @author - Washington
 */
public class EventosBCFImpl extends AbstractBaseBCF implements IEventosBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_EVENTOS_REQUEST_NAME. */
	private static final String INQUIRY_EVENTOS_REQUEST_NAME = InquiryEventosRequest.class.getSimpleName();

	/** The Constant EVENTOS_REQUEST_NAME. */
	private static final String EVENTOS_REQUEST_NAME = EventosRequest.class.getSimpleName();

	/** The Constant EVENTOS_NAME. */
	private static final String EVENTOS_NAME = Eventos.class.getSimpleName();

	/** The Constant DEFAULT_EVENTOS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EVENTOS_BCF_EXCEPTION_MSG = "sensus.mlc.eventosbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EventosBCFImpl.class);

	/** The eventos bcl. */
	private IEventosBCL eventosBCL;

	/** The eventos validation controller. */
	private ValidationController eventosValidationController;

	/** The eventos list validation controller. */
	private ValidationController eventosListValidationController;

	/** The eventos request validation controller. */
	private ValidationController eventosRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the eventos bcl.
	 *
	 * @return the eventos bcl
	 */
	public IEventosBCL getEventosBCL()
	{
		return this.eventosBCL;
	}

	/**
	 * Sets the eventos bcl.
	 *
	 * @param eventosBCLObject the new eventos bcl
	 */
	public void setEventosBCL(IEventosBCL eventosBCLObject)
	{
		this.eventosBCL = eventosBCLObject;
	}

	/**
	 * Gets the eventos validation controller.
	 *
	 * @return the eventos validation controller
	 */
	public ValidationController getEventosValidationController()
	{
		return this.eventosValidationController;
	}

	/**
	 * Sets the eventos validation controller.
	 *
	 * @param eventosValidationController the new eventos validation controller
	 */
	public void setEventosValidationController(ValidationController eventosValidationController)
	{
		this.eventosValidationController = eventosValidationController;
	}

	/**
	 * Gets the eventos list validation controller.
	 *
	 * @return the eventos list validation controller
	 */
	public ValidationController getEventosListValidationController()
	{
		return this.eventosListValidationController;
	}

	/**
	 * Sets the eventos list validation controller.
	 *
	 * @param eventosListValidationController the new eventos list validation controller
	 */
	public void setEventosListValidationController(ValidationController eventosListValidationController)
	{
		this.eventosListValidationController = eventosListValidationController;
	}

	/**
	 * Gets the eventos request validation controller.
	 *
	 * @return the eventos request validation controller
	 */
	public ValidationController getEventosRequestValidationController()
	{
		return this.eventosRequestValidationController;
	}

	/**
	 * Sets the eventos request validation controller.
	 *
	 * @param eventosRequestValidationController the new eventos request validation controller
	 */
	public void setEventosRequestValidationController(ValidationController eventosRequestValidationController)
	{
		this.eventosRequestValidationController = eventosRequestValidationController;
	}

	/**
	 * Gets the light validation controller.
	 *
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return this.lightValidationController;
	}

	/**
	 * Sets the light validation controller.
	 *
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
	}

	/**
	 * Gets the light list validation controller.
	 *
	 * @return the light list validation controller
	 */
	public ValidationController getLightListValidationController()
	{
		return this.lightListValidationController;
	}

	/**
	 * Sets the light list validation controller.
	 *
	 * @param lightListValidationController the new light list validation controller
	 */
	public void setLightListValidationController(ValidationController lightListValidationController)
	{
		this.lightListValidationController = lightListValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcf.IEventosBCF#fetchAllEventoss(com.sensus.mlc.eventos.model.request.InquiryEventosRequest
	 */
	@Override
	public InquiryEventosResponse fetchAllEventos(InquiryEventosRequest inquiryEventosRequest)
	{
		InquiryEventosResponse response = new InquiryEventosResponse();
		InternalResultsResponse<Eventos> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_EVENTOS_REQUEST_NAME, inquiryEventosRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getEventosBCL().fetchAllEventos(inquiryEventosRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EVENTOS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcf.IEventosBCF#fetchEventosByName(com.sensus.mlc.eventos.model.request.EventosRequest
	 */
	@Override
	public EventosResponse fetchEventosById(EventosRequest eventosRequest)
	{
		EventosResponse response = new EventosResponse();
		InternalResultsResponse<Eventos> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(EVENTOS_REQUEST_NAME, eventosRequest);
			context.putObjectToBeValidated(EVENTOS_NAME, eventosRequest.getEventos());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEventosValidationController().validate(context))
			{
				internalResponse = getEventosBCL().fetchEventosById(eventosRequest);
				response.setEventos(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EVENTOS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcf.IEventosBCF#insertEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public EventosResponse insertEventos(EventosRequest eventosRequest)
	{
		EventosResponse response = new EventosResponse();
		InternalResultsResponse<Eventos> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(EVENTOS_REQUEST_NAME, eventosRequest);
			context.putObjectToBeValidated(EVENTOS_NAME, eventosRequest.getEventos());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEventosValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getEventosBCL().insertEventos(eventosRequest);
					response.setEventos(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EVENTOS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcf.IEventosBCF#deleteEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public EventosResponse deleteEventos(EventosRequest eventosRequest)
	{
		EventosResponse response = new EventosResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(EVENTOS_REQUEST_NAME, eventosRequest);
			context.putObjectToBeValidated(EVENTOS_NAME, eventosRequest.getEventos());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEventosValidationController().validate(context))
			{
				internalResponse = getEventosBCL().deleteEventos(eventosRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EVENTOS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcf.IEventosBCF#updateEventos(com.sensus.mlc.eventos.model.request.EventosRequest
	 */
	@Override
	public EventosResponse updateEventos(EventosRequest eventosRequest)
	{
		EventosResponse response = new EventosResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(EVENTOS_REQUEST_NAME, eventosRequest);
			context.putObjectToBeValidated(EVENTOS_NAME, eventosRequest.getEventos());

			if (getLightingControlRequestValidationController().validate(context) &&
					getEventosRequestValidationController().validate(context) &&
					getEventosValidationController().validate(context))
			{
				internalResponse = getEventosBCL().updateEventos(eventosRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EVENTOS_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
