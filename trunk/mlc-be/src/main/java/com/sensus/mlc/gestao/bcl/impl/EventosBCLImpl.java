package com.sensus.mlc.eventos.bcl.impl;

import static com.sensus.mlc.base.util.LCActionUtil.createMessageInfoNone;
import static com.sensus.mlc.base.util.LCActionUtil.createMessageWarningOther;
import static com.sensus.mlc.base.util.LCActionUtil.generateDescription;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;
import static com.sensus.mlc.base.util.LCHelp.generateProcess;
import static com.sensus.mlc.base.util.LCPropertiesExtractorUtil.extractLightId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;



/** 
 * The Class EventosBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class EventosBCLImpl implements IEventosBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The eventos dac. */ 
	private IEventosDAC eventosDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_EVENTOS_PER_LIGHT. */  
	private static final Integer MAX_EVENTOS_PER_LIGHT = 5;

	/** The Constant MAX_EVENTOS_FAILURE. */  
	private static final String MAX_EVENTOS_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_eventoss";

	/** The Constant MAX_EVENTOS_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_EVENTOS. */ 
	private static final String LIGHT_ALREADY_IN_EVENTOS = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_EVENTOS_REMOVED. */  
	private static final String AUTO_EVENTOS_REMOVED = "sensus.mlc.tagvalidator.autoeventos.removed";

	/** The Constant EVENTOS_REMOVED. */      
	private static final String EVENTOS_REMOVED = "sensus.mlc.eventosbcfimpl.mlceventosdeleted";

	/** The Constant LIGHT_NOT_IN_THE_EVENTOS. */  
	private static final String LIGHT_NOT_IN_THE_EVENTOS = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_eventos";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.eventosbcfimpl.processrunning";

	/** The Constant ALREADY_IN_EVENTOS. */   
	private static final String ALREADY_IN_EVENTOS = "alreadInEventos";

	/** The Constant MAX_EVENTOS. */  
	private static final String MAX_EVENTOS = "maxEventoss";

	/** The Constant NO_LIGHTS_IN_EVENTOS. */   
	private static final String NO_LIGHTS_IN_EVENTOS = "sensus.mlc.mlc_action.light_status.no.lights_in_eventoss";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EVENTOS. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EVENTOS = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EVENTOS_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EVENTOS_BY_POLE_ID =  
			"sensus.mlc.mlc_action.add_smp_to_grp_by_poleid";

	/** The mlc gateway ws. */  
	private MlcServerClient mlcGatewayWs;

	/** The Constant SMARTPOINT_BCL_BEAN. */  
	private static final String SMARTPOINT_ACCESSOR_BCL_BEAN = "smartPointAccessorBCLTarget";

	/**   
	 * Gets the lc help. 
	 *             
	 * @return the lc help  
	 */                    
	public LCHelp getLcHelp()  
	{                         
		return this.lcHelp;
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
	 * Gets the tag bcl.       
	 *                        
	 * @return the tag bcl     
	 */                        
	public ITagBCL getTagBCL()     
	{                  
		return this.tagBCL;
	} 

	/**  
	 * Sets the tag bcl.
	 *     
	 * @param tagBCL the new tag bcl  
	 */  
	public void setTagBCL(ITagBCL tagBCL) 
	{    
		this.tagBCL = tagBCL;
	} 

	/** 
	 * Sets the eventos dac.   
	 *     
	 * @param eventosDACParam the new eventos dac  
	 */   
	public void setEventosDAC(IEventosDAC eventosDACParam) 
	{  
		this.eventosDAC = eventosDACParam;
	}  

	/**   
	 * Gets the eventos dac.  
	 *     
	 * @return the eventos dac 
	 */
	public IEventosDAC getEventosDAC() 
	{    
		return this.eventosDAC;
	} 

	/**    
	 * Gets the smart point accessor bcl. 
	 *          
	 * @return the smart point accessor bcl  
	 */    
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()  
	{         
		if (this.smartPointAccessorBCL == null)    
		{                                 
			this.smartPointAccessorBCL =    
					(ISmartPointAccessorBCL)SensusAppContext.getApplicationContext().getBean(  
							SMARTPOINT_ACCESSOR_BCL_BEAN);
		}  

		return this.smartPointAccessorBCL;
	} 

	/**   
	 * Sets the smart point accessor bcl. 
	 *                            
	 * @param smartPointAccessorBCL the new smart point accessor bcl 
	 */                                                   
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL) 
	{                                                          
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}                                                     

	/**     
	 * Gets the smart point updater bcl.   
	 *                                      
	 * @return the smart point updater bcl   
	 */                                      
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()  
	{                              
		return this.smartPointUpdaterBCL;
	}     

	/**    
	 * Sets the smart point updater bcl.                              
	 *                                                                
	 * @param smartPointUpdaterBCL the new smart point updater bcl    
	 */                                                               
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)  
	{   
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
	}  

	/** 
	 * Gets the process bcl.  
	 *                      
	 * @return the process bcl 
	 */                        
	public IProcessBCL getProcessBCL() 
	{                             
		return this.processBCL;
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

	/**   
	 * Sets the mlc gateway ws.     
	 *       
	 * @param mlcGatewayWs the new mlc gateway ws     
	 */                                     
	public void setMlcGatewayWs(MlcServerClient mlcGatewayWs)    
	{                          
		this.mlcGatewayWs = mlcGatewayWs;
	}                 

	/**      
	 * Gets the mlc gateway ws.   
	 *     
	 * @return the mlc gateway ws   
	 */  
	public MlcServerClient getMlcGatewayWs() 
	{                                
		return this.mlcGatewayWs;
	}  

	/* 
	 * (non-Javadoc)    
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#deleteEventos(com.sensus.mlc.eventos.model.request.EventosRequest)   
	 */ 
	@Override   
	public InternalResponse deleteEventos(EventosRequest eventosRequest) 
	{  
		// build a list of eventoss based on the user selection - Select All requirement 
		List<Eventos> eventosList = fetchSelectedEventoss(eventosRequest);
		EventosRequest request = new EventosRequest(eventosRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Eventos eventos : eventosList)  
		{    
			request.setEventos(eventos);
			if (!getEventosDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {eventos.getName()});
				continue;
			}     
			eventosRequest.setEventos(eventos);

			response = getEventosDAC().deleteEventos(eventosRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(EVENTOS_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {eventos.getName()});

				removeTagFromEventos(eventosRequest.getUserContext(), response, eventos.getName());
			}          

			// create a Process for this action eventos      
			InternalResponse eventosProcess = insertProcess(eventosRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(eventosProcess.getMessageInfoList());
		} 

		return response;
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
			// create a Process for the created eventos 
			InternalResponse processResponse = insertProcess(eventosRequest, LCActionTypeEnum.INSERT_EVENTOS);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#updateEventos(com.sensus.mlc.eventos.model.request.EventosRequest)  
	 */    
	@Override  
	public InternalResponse updateEventos(EventosRequest eventosRequest)   
	{  
		InternalResponse response = getEventosDAC().updateEventos(eventosRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(eventosRequest, LCActionTypeEnum.UPDATE_EVENTOS);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.eventos.bcl.IEventosBCL#fetchAllEventoss(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Eventos> fetchAllEventoss(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getEventosDAC().fetchAllEventoss(inquiryPaginationRequest);
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
	 * @see com.sensus.mlc.eventos.bcl.IEventosBCL#fetchEventossByIdList(com.sensus.mlc.eventos.model.request.EventosRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Eventos> fetchEventossByIdList(EventosRequest eventosRequest) 
	{
		return getEventosDAC().fetchEventossByIdList(eventosRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(EventosRequest eventosRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(eventosRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param eventosRequest the eventos request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInEventos the count already in eventos 
	 * @param lightsWithMaxEventosAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(EventosRequest eventosRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInEventos, List<Light> lightsWithMaxEventosAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Eventos eventos = eventosRequest.getEventos();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EVENTOS_ID, String.valueOf(eventos.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.EVENTOS_NAME, eventos.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				eventos.getName(),   
				lightAmount,  
				lightsAlreadyInEventos.size(),   
				lightsWithMaxEventosAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(eventosRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInEventos,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_EVENTOS));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxEventosAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_EVENTOS_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert eventos process. 
	 *    
	 * @param eventos the eventos 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionEventos the action eventos 
	 * @param countAlreadyInEventos the count already in eventos (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertEventosProcess(Eventos eventos, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionEventos, Integer countAlreadyInEventos, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterEventosId =   
				new LCActionParameter(PropertyEnum.EVENTOS_ID, String.valueOf(eventos.getId()));
		LCActionParameter actionParameterEventosName =     
				new LCActionParameter(PropertyEnum.EVENTOS_NAME, eventos.getName());
		LCAction action = new LCAction(actionEventos);

		actionParameters.add(actionParameterEventosId);
		actionParameters.add(actionParameterEventosName);
		action.setActionParameters(actionParameters);

		Process process = getLcHelp().generateProcess(null, action, lightList);
		process.setIsProcessComplete(true);
		process.setIsMonitoredInstance(false);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		ProcessRequest processRequest = new ProcessRequest(userContext);
		processRequest.setProcess(process);
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);

		Integer lightAmount = 0;
		if (!ValidationUtil.isNullOrEmpty(lightList))   
		{  
			lightAmount = lightList.size();
		}  

		setProcessDescription(process, eventos.getName(), lightAmount, countAlreadyInEventos, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param eventosName the eventos name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEventos the count already in eventos 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String eventosName,  
			Integer lightAmount,  
			int countAlreadyInEventos, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				eventosName,  
				lightAmount,    
				countAlreadyInEventos, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param eventosName the eventos name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEventos the count already in eventos 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String eventosName,  
			Integer lightAmount,    
			int countAlreadyInEventos,
			int countMaxPerLight,
			int countDeactivated,
			InternalResultsResponse<Process> processResponse)
	{ 

		if (ValidationUtil.isNull(process)) 
		{  
			return;
		} 

		if (process.getLcAction().getActionType() != LCActionTypeEnum.ADD_SMP_TO_GRP)
		{ 
			process.setDescription(generateDescription(process, lightAmount));
			return;
		} 

		// create process description based on the max count per light 
		if (lightAmount > 1)  
		{                
			process.setDescription( 
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EVENTOS, 
							lightAmount,   
							eventosName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInEventos == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EVENTOS_BY_POLE_ID,  
							eventosName).getText());
		} 

		if (countAlreadyInEventos > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_EVENTOS, eventosName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInEventos + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_EVENTOS, eventosName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_EVENTOS_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_EVENTOS_FAILURE, countMaxPerLight);
		} 

		if (countDeactivated > 0) 
		{   
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_DEACTIVATED);
		}  
	} 

	/**  
	 * Adds the message info into process response. 
	 *     
	 * @param lightAmount the light amount  
	 * @param processResponse the process response 
	 * @param message the message        
	 * @param args the args  
	 */    
	public void addMessageInfoIntoProcessResponse( 
			Integer lightAmount, 
			InternalResultsResponse<Process> processResponse,  
			String message, 
			Object... args)  
	{                
		if (ValidationUtil.isNullOrZero(lightAmount) || (lightAmount > 1)) 
		{       
			return;
		}    

		processResponse.addMessage(message, MessageSeverity.None, MessageLevel.None, args);
		processResponse.setStatus(Status.ExceptionError);
	}  

private List<Eventos> fetchSelectedEventos(EventosRequest eventosRequest)
{
	InquiryEventosRequest inquiryEventosRequest = new InquiryEventosRequest();
	inquiryEventosRequest.setPageSize(0);
	inquiryEventosRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryEventosRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryEventosRequest.setTenant(dominiosRequest.getTenant());

	return getEventosDAC().fetchAllEventos(inquiryEventosRequest).getResultsList();
}
}  
 
 
 
