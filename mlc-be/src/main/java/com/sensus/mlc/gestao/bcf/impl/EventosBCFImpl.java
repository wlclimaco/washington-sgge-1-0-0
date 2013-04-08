package com.sensus.mlc.eventos.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.eventos.bcf.IEventosBCF;
import com.sensus.mlc.eventos.bcl.IEventosBCL;
import com.sensus.mlc.eventos.model.Eventos;
import com.sensus.mlc.eventos.model.request.EventosRequest;
import com.sensus.mlc.eventos.model.request.InquiryEventosRequest;
import com.sensus.mlc.eventos.model.response.EventosResponse;
import com.sensus.mlc.eventos.model.response.InquiryEventosResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.eventosBCFImpl;

public class EventosBCFImpl implements IEventosBCF
{

	/** The Constant NAME_LENGTH. */
	private static final Integer NAME_LENGTH = 255;
 
	/** The Constant SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID. */ 
	private static final String SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID = 
			"sensus.epm.actionvalidator.name.invalid"; 

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */ 
	private static final String DEFAULT_EMPRESA_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception"; 
 
	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */ 
	private static final String DEFAULT_EMPRESA_BCL_EXCEPTION_MSG = "sensus.mlc.groupbclimpl.defaultexception";
 
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EventosBCFImpl.class); 
 
	private IEventosBCL eventosBCL; // injected by Spring through setter
 
	@Override 
	public EventosResponse insertEventos(EventosRequest eventosRequest)
	{
		EventosResponse response = new EventosResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, eventosRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Eventos> internalResponse = getEventosBCL().insertEventos(eventosRequest); 
				response.setEventos(internalResponse.getResultsList()); 
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}
		} 
		catch (Exception ex) 
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public EventosResponse updateEventos(EventosRequest eventosRequest)
	{
		EventosResponse response = new EventosResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, eventosRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Eventos> internalResponse = getEventosBCL().updateEventos(eventosRequest);
				response.setEventos(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);
			}
		} 
		catch (Exception ex) 
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		} 
		return response;  
	} 
   
	@Override  
	public EventosResponse deleteEventos(EventosRequest eventosRequest) 
	{  
		EventosResponse response = new EventosResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, eventosRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getEventosBCL().deleteEventos(eventosRequest); 
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		}  
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
  
	@Override 
	public InquiryEventosResponse fetchAllEventos(InquiryEventosRequest inquiryeventosRequest) 
	{ 
		InquiryEventosResponse response = new InquiryEventosResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Eventos> internalResponse =  
						getEventosBCL().fetchAllEventos(inquiryeventosRequest); 
				response.setEventos(internalResponse.getResultsList());
				response.setResultsSetInfo(internalResponse.getResultsSetInfo());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
   
		}
		catch (Exception ex)
		{ 
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		}  
		return response;
	} 
   
	@Override  
	public EventosResponse fetchEventosById(EventosRequest eventosRequest) 
	{ 
		EventosResponse response = new EventosResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, eventosRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Eventos> internalResponse = getEventosBCL().fetchEventosById(eventosRequest); 
				response.setEventos(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			} 
		}  
		catch (Exception ex)  
		{      
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);  
		}
		return response;  
	}
  
	@Override 
	public EventosResponse fetchAllEventosFilial(EventosRequest eventosRequest) { 
		EventosResponse response = new EventosResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryEventosRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Eventos> internalResponse =  
						getEventosBCL().fetchAllEventosFilial(eventosRequest);  
				response.setEventos(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IEventosBCL getEventosBCL() 
	{       
		return eventosBCL; 
	}  
    
	public void setEventosBCL(IEventosBCL eventosBCL) 
	{ 
		this.eventosBCL = eventosBCL; 
	} 
}  
