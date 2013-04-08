package com.sensus.mlc.auditoria.bcl.impl;

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
 * The Class AuditoriaBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class AuditoriaBCLImpl implements IAuditoriaBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The auditoria dac. */ 
	private IAuditoriaDAC auditoriaDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_AUDITORIA_PER_LIGHT. */  
	private static final Integer MAX_AUDITORIA_PER_LIGHT = 5;

	/** The Constant MAX_AUDITORIA_FAILURE. */  
	private static final String MAX_AUDITORIA_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_auditorias";

	/** The Constant MAX_AUDITORIA_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_AUDITORIA. */ 
	private static final String LIGHT_ALREADY_IN_AUDITORIA = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_AUDITORIA_REMOVED. */  
	private static final String AUTO_AUDITORIA_REMOVED = "sensus.mlc.tagvalidator.autoauditoria.removed";

	/** The Constant AUDITORIA_REMOVED. */      
	private static final String AUDITORIA_REMOVED = "sensus.mlc.auditoriabcfimpl.mlcauditoriadeleted";

	/** The Constant LIGHT_NOT_IN_THE_AUDITORIA. */  
	private static final String LIGHT_NOT_IN_THE_AUDITORIA = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_auditoria";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.auditoriabcfimpl.processrunning";

	/** The Constant ALREADY_IN_AUDITORIA. */   
	private static final String ALREADY_IN_AUDITORIA = "alreadInAuditoria";

	/** The Constant MAX_AUDITORIA. */  
	private static final String MAX_AUDITORIA = "maxAuditorias";

	/** The Constant NO_LIGHTS_IN_AUDITORIA. */   
	private static final String NO_LIGHTS_IN_AUDITORIA = "sensus.mlc.mlc_action.light_status.no.lights_in_auditorias";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_AUDITORIA. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_AUDITORIA = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_AUDITORIA_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_AUDITORIA_BY_POLE_ID =  
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
	 * Sets the auditoria dac.   
	 *     
	 * @param auditoriaDACParam the new auditoria dac  
	 */   
	public void setAuditoriaDAC(IAuditoriaDAC auditoriaDACParam) 
	{  
		this.auditoriaDAC = auditoriaDACParam;
	}  

	/**   
	 * Gets the auditoria dac.  
	 *     
	 * @return the auditoria dac 
	 */
	public IAuditoriaDAC getAuditoriaDAC() 
	{    
		return this.auditoriaDAC;
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
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#deleteAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)   
	 */ 
	@Override   
	public InternalResponse deleteAuditoria(AuditoriaRequest auditoriaRequest) 
	{  
		// build a list of auditorias based on the user selection - Select All requirement 
		List<Auditoria> auditoriaList = fetchSelectedAuditorias(auditoriaRequest);
		AuditoriaRequest request = new AuditoriaRequest(auditoriaRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Auditoria auditoria : auditoriaList)  
		{    
			request.setAuditoria(auditoria);
			if (!getAuditoriaDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {auditoria.getName()});
				continue;
			}     
			auditoriaRequest.setAuditoria(auditoria);

			response = getAuditoriaDAC().deleteAuditoria(auditoriaRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(AUDITORIA_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {auditoria.getName()});

				removeTagFromAuditoria(auditoriaRequest.getUserContext(), response, auditoria.getName());
			}          

			// create a Process for this action auditoria      
			InternalResponse auditoriaProcess = insertProcess(auditoriaRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(auditoriaProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#insertAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Auditoria> insertAuditoria(AuditoriaRequest auditoriaRequest) 
	{   
		InternalResultsResponse<Auditoria> response = getAuditoriaDAC().insertAuditoria(auditoriaRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created auditoria 
			InternalResponse processResponse = insertProcess(auditoriaRequest, LCActionTypeEnum.INSERT_AUDITORIA);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#updateAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)  
	 */    
	@Override  
	public InternalResponse updateAuditoria(AuditoriaRequest auditoriaRequest)   
	{  
		InternalResponse response = getAuditoriaDAC().updateAuditoria(auditoriaRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(auditoriaRequest, LCActionTypeEnum.UPDATE_AUDITORIA);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#fetchAllAuditorias(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Auditoria> fetchAllAuditorias(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getAuditoriaDAC().fetchAllAuditorias(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#fetchAuditoriaById(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Auditoria> fetchAuditoriaById(AuditoriaRequest auditoriaRequest) 
	{     
		return getAuditoriaDAC().fetchAuditoriaById(auditoriaRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.auditoria.bcl.IAuditoriaBCL#fetchAuditoriasByIdList(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Auditoria> fetchAuditoriasByIdList(AuditoriaRequest auditoriaRequest) 
	{
		return getAuditoriaDAC().fetchAuditoriasByIdList(auditoriaRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(AuditoriaRequest auditoriaRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(auditoriaRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param auditoriaRequest the auditoria request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInAuditoria the count already in auditoria 
	 * @param lightsWithMaxAuditoriaAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(AuditoriaRequest auditoriaRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInAuditoria, List<Light> lightsWithMaxAuditoriaAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Auditoria auditoria = auditoriaRequest.getAuditoria();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.AUDITORIA_ID, String.valueOf(auditoria.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.AUDITORIA_NAME, auditoria.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				auditoria.getName(),   
				lightAmount,  
				lightsAlreadyInAuditoria.size(),   
				lightsWithMaxAuditoriaAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(auditoriaRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInAuditoria,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_AUDITORIA));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxAuditoriaAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_AUDITORIA_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert auditoria process. 
	 *    
	 * @param auditoria the auditoria 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionAuditoria the action auditoria 
	 * @param countAlreadyInAuditoria the count already in auditoria (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertAuditoriaProcess(Auditoria auditoria, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionAuditoria, Integer countAlreadyInAuditoria, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterAuditoriaId =   
				new LCActionParameter(PropertyEnum.AUDITORIA_ID, String.valueOf(auditoria.getId()));
		LCActionParameter actionParameterAuditoriaName =     
				new LCActionParameter(PropertyEnum.AUDITORIA_NAME, auditoria.getName());
		LCAction action = new LCAction(actionAuditoria);

		actionParameters.add(actionParameterAuditoriaId);
		actionParameters.add(actionParameterAuditoriaName);
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

		setProcessDescription(process, auditoria.getName(), lightAmount, countAlreadyInAuditoria, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param auditoriaName the auditoria name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInAuditoria the count already in auditoria 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String auditoriaName,  
			Integer lightAmount,  
			int countAlreadyInAuditoria, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				auditoriaName,  
				lightAmount,    
				countAlreadyInAuditoria, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param auditoriaName the auditoria name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInAuditoria the count already in auditoria 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String auditoriaName,  
			Integer lightAmount,    
			int countAlreadyInAuditoria,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_AUDITORIA, 
							lightAmount,   
							auditoriaName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInAuditoria == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_AUDITORIA_BY_POLE_ID,  
							auditoriaName).getText());
		} 

		if (countAlreadyInAuditoria > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_AUDITORIA, auditoriaName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInAuditoria + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_AUDITORIA, auditoriaName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_AUDITORIA_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_AUDITORIA_FAILURE, countMaxPerLight);
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

private List<Auditoria> fetchSelectedAuditoria(AuditoriaRequest auditoriaRequest)
{
	InquiryAuditoriaRequest inquiryAuditoriaRequest = new InquiryAuditoriaRequest();
	inquiryAuditoriaRequest.setPageSize(0);
	inquiryAuditoriaRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryAuditoriaRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryAuditoriaRequest.setTenant(dominiosRequest.getTenant());

	return getAuditoriaDAC().fetchAllAuditoria(inquiryAuditoriaRequest).getResultsList();
}
}  
 
 
 
