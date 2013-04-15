package com.sensus.mlc.gestao.bcl.impl;

import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.gestao.bcl.IEventosBCL;
import com.sensus.mlc.gestao.dac.IEventosDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Eventos;
import com.sensus.mlc.gestao.model.request.EventosRequest;
import com.sensus.mlc.gestao.model.request.InquiryEventosRequest;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

/**
 * The Class EventosBCLImpl.
 */
public class EventosBCLImpl implements IEventosBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The eventos dac. */
	private IEventosDAC eventosDAC;



	/**  The process bcl. */
	private IProcessBCL processBCL;

	/**  The lc help. */
	private LCHelp lcHelp;


	/**
	 * Gets the lc help.
	 *
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 *
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Spring Sets the eventos dac.
	 *
	 * @param iEventosDAC the new eventos dac
	 */
	public void setEventosDAC(IEventosDAC iEventosDAC)
	{
		eventosDAC = iEventosDAC;
	}

	/**
	 * Gets the eventos dac.
	 *
	 * @return the eventos dac
	 */
	public IEventosDAC getEventosDAC()
	{
		return eventosDAC;
	}

	/**
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 *
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#fetchAllEventoss(InquiryEventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> fetchAllEventos(InquiryEventosRequest inquiryEventosRequest)
	{
		return getEventosDAC().fetchAllEventos(inquiryEventosRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#fetchEventosById(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> fetchEventosById(EventosRequest eventosRequest)
	{
		return getEventosDAC().fetchEventosById(eventosRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#insertEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> insertEventos(EventosRequest eventosRequest)
	{

		InternalResultsResponse<Eventos> response = getEventosDAC().insertEventos(eventosRequest);

		if (!response.isInError())
		{
			Eventos eventos = response.getFirstResult();
			eventosRequest.setEventos(eventos);

			SearchParameter eventosParameter = new SearchParameter(PropertyEnum.EVENTO_ID, String.valueOf(eventos.getCdevento()));
			eventosRequest.getSearchLight().addSearchParameter(eventosParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(eventosRequest, LCActionTypeEnum.INSERT_EMPRESA, null);
			eventosRequest.getSearchLight().getSearchParameters().remove(eventosParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#deleteEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResponse deleteEventos(EventosRequest eventosRequest)
	{
		InternalResultsResponse<Eventos> eventosResponse = getEventosDAC().fetchEventosById(eventosRequest);
		InternalResponse response = new InternalResponse();

		if (eventosResponse.isInError())
		{
			response.setStatus(eventosResponse.getStatus());
			response.addMessages(eventosResponse.getMessageInfoList());
			return response;
		}

		response = getEventosDAC().deleteEventos(eventosRequest);

		if (response.isInError())
		{
			return response;
		}

		Eventos eventos = eventosResponse.getFirstResult();
		eventosRequest.setEventos(eventos);

		SearchParameter eventosParameter = new SearchParameter(PropertyEnum.EVENTO_ID, String.valueOf(eventos.getCdevento()));
		eventosRequest.getSearchLight().addSearchParameter(eventosParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(eventosRequest, LCActionTypeEnum.DELETE_TAG, null);
		eventosRequest.getSearchLight().getSearchParameters().remove(eventosParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#updateEventos(com.sensus.mlc.eventos.model.request.EventosRequest)
	 */
	@Override
	public InternalResultsResponse<Eventos> updateEventos(EventosRequest eventosRequest)
	{
		InternalResultsResponse<Eventos> response = getEventosDAC().updateEventos(eventosRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(eventosRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param eventosRequest the eventos request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EventosRequest eventosRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(eventosRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param eventosRequest the eventos request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EventosRequest eventosRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(eventosRequest.getEventos()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Eventos eventos = eventosRequest.getEventos();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(eventos.getCdevento())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, eventos.getCdevento().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(eventosRequest);
   Integer lightAmount = 1;

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = LCHelp.generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		if (!ValidationUtil.isNullOrEmpty(processDescription))
		{
			process.setDescription(processDescription);
		}

		ProcessRequest processRequest = createProcessRequest(eventosRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Eventos> fetchAllEventosTypes(
			InquiryEventosRequest inquiryeventosRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Eventos> fetchAllEventosFilial(
			EventosRequest eventosRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




