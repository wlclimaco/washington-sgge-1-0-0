package com.sensus.mlc.chaveprimaria.bcl.impl;

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
 * The Class ChaveprimariaBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class ChaveprimariaBCLImpl implements IChaveprimariaBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The chaveprimaria dac. */ 
	private IChaveprimariaDAC chaveprimariaDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_CHAVEPRIMARIA_PER_LIGHT. */  
	private static final Integer MAX_CHAVEPRIMARIA_PER_LIGHT = 5;

	/** The Constant MAX_CHAVEPRIMARIA_FAILURE. */  
	private static final String MAX_CHAVEPRIMARIA_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_chaveprimarias";

	/** The Constant MAX_CHAVEPRIMARIA_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_CHAVEPRIMARIA. */ 
	private static final String LIGHT_ALREADY_IN_CHAVEPRIMARIA = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_CHAVEPRIMARIA_REMOVED. */  
	private static final String AUTO_CHAVEPRIMARIA_REMOVED = "sensus.mlc.tagvalidator.autochaveprimaria.removed";

	/** The Constant CHAVEPRIMARIA_REMOVED. */      
	private static final String CHAVEPRIMARIA_REMOVED = "sensus.mlc.chaveprimariabcfimpl.mlcchaveprimariadeleted";

	/** The Constant LIGHT_NOT_IN_THE_CHAVEPRIMARIA. */  
	private static final String LIGHT_NOT_IN_THE_CHAVEPRIMARIA = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_chaveprimaria";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.chaveprimariabcfimpl.processrunning";

	/** The Constant ALREADY_IN_CHAVEPRIMARIA. */   
	private static final String ALREADY_IN_CHAVEPRIMARIA = "alreadInChaveprimaria";

	/** The Constant MAX_CHAVEPRIMARIA. */  
	private static final String MAX_CHAVEPRIMARIA = "maxChaveprimarias";

	/** The Constant NO_LIGHTS_IN_CHAVEPRIMARIA. */   
	private static final String NO_LIGHTS_IN_CHAVEPRIMARIA = "sensus.mlc.mlc_action.light_status.no.lights_in_chaveprimarias";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEPRIMARIA. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEPRIMARIA = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEPRIMARIA_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEPRIMARIA_BY_POLE_ID =  
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
	 * Sets the chaveprimaria dac.   
	 *     
	 * @param chaveprimariaDACParam the new chaveprimaria dac  
	 */   
	public void setChaveprimariaDAC(IChaveprimariaDAC chaveprimariaDACParam) 
	{  
		this.chaveprimariaDAC = chaveprimariaDACParam;
	}  

	/**   
	 * Gets the chaveprimaria dac.  
	 *     
	 * @return the chaveprimaria dac 
	 */
	public IChaveprimariaDAC getChaveprimariaDAC() 
	{    
		return this.chaveprimariaDAC;
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
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#deleteChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)   
	 */ 
	@Override   
	public InternalResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest) 
	{  
		// build a list of chaveprimarias based on the user selection - Select All requirement 
		List<Chaveprimaria> chaveprimariaList = fetchSelectedChaveprimarias(chaveprimariaRequest);
		ChaveprimariaRequest request = new ChaveprimariaRequest(chaveprimariaRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Chaveprimaria chaveprimaria : chaveprimariaList)  
		{    
			request.setChaveprimaria(chaveprimaria);
			if (!getChaveprimariaDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {chaveprimaria.getName()});
				continue;
			}     
			chaveprimariaRequest.setChaveprimaria(chaveprimaria);

			response = getChaveprimariaDAC().deleteChaveprimaria(chaveprimariaRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(CHAVEPRIMARIA_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {chaveprimaria.getName()});

				removeTagFromChaveprimaria(chaveprimariaRequest.getUserContext(), response, chaveprimaria.getName());
			}          

			// create a Process for this action chaveprimaria      
			InternalResponse chaveprimariaProcess = insertProcess(chaveprimariaRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(chaveprimariaProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#insertChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Chaveprimaria> insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest) 
	{   
		InternalResultsResponse<Chaveprimaria> response = getChaveprimariaDAC().insertChaveprimaria(chaveprimariaRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created chaveprimaria 
			InternalResponse processResponse = insertProcess(chaveprimariaRequest, LCActionTypeEnum.INSERT_CHAVEPRIMARIA);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#updateChaveprimaria(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)  
	 */    
	@Override  
	public InternalResponse updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)   
	{  
		InternalResponse response = getChaveprimariaDAC().updateChaveprimaria(chaveprimariaRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(chaveprimariaRequest, LCActionTypeEnum.UPDATE_CHAVEPRIMARIA);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#fetchAllChaveprimarias(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Chaveprimaria> fetchAllChaveprimarias(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getChaveprimariaDAC().fetchAllChaveprimarias(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#fetchChaveprimariaById(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Chaveprimaria> fetchChaveprimariaById(ChaveprimariaRequest chaveprimariaRequest) 
	{     
		return getChaveprimariaDAC().fetchChaveprimariaById(chaveprimariaRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL#fetchChaveprimariasByIdList(com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Chaveprimaria> fetchChaveprimariasByIdList(ChaveprimariaRequest chaveprimariaRequest) 
	{
		return getChaveprimariaDAC().fetchChaveprimariasByIdList(chaveprimariaRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(ChaveprimariaRequest chaveprimariaRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(chaveprimariaRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param chaveprimariaRequest the chaveprimaria request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInChaveprimaria the count already in chaveprimaria 
	 * @param lightsWithMaxChaveprimariaAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(ChaveprimariaRequest chaveprimariaRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInChaveprimaria, List<Light> lightsWithMaxChaveprimariaAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Chaveprimaria chaveprimaria = chaveprimariaRequest.getChaveprimaria();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.CHAVEPRIMARIA_ID, String.valueOf(chaveprimaria.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.CHAVEPRIMARIA_NAME, chaveprimaria.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				chaveprimaria.getName(),   
				lightAmount,  
				lightsAlreadyInChaveprimaria.size(),   
				lightsWithMaxChaveprimariaAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(chaveprimariaRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInChaveprimaria,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_CHAVEPRIMARIA));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxChaveprimariaAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_CHAVEPRIMARIA_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert chaveprimaria process. 
	 *    
	 * @param chaveprimaria the chaveprimaria 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionChaveprimaria the action chaveprimaria 
	 * @param countAlreadyInChaveprimaria the count already in chaveprimaria (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertChaveprimariaProcess(Chaveprimaria chaveprimaria, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionChaveprimaria, Integer countAlreadyInChaveprimaria, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterChaveprimariaId =   
				new LCActionParameter(PropertyEnum.CHAVEPRIMARIA_ID, String.valueOf(chaveprimaria.getId()));
		LCActionParameter actionParameterChaveprimariaName =     
				new LCActionParameter(PropertyEnum.CHAVEPRIMARIA_NAME, chaveprimaria.getName());
		LCAction action = new LCAction(actionChaveprimaria);

		actionParameters.add(actionParameterChaveprimariaId);
		actionParameters.add(actionParameterChaveprimariaName);
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

		setProcessDescription(process, chaveprimaria.getName(), lightAmount, countAlreadyInChaveprimaria, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param chaveprimariaName the chaveprimaria name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInChaveprimaria the count already in chaveprimaria 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String chaveprimariaName,  
			Integer lightAmount,  
			int countAlreadyInChaveprimaria, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				chaveprimariaName,  
				lightAmount,    
				countAlreadyInChaveprimaria, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param chaveprimariaName the chaveprimaria name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInChaveprimaria the count already in chaveprimaria 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String chaveprimariaName,  
			Integer lightAmount,    
			int countAlreadyInChaveprimaria,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEPRIMARIA, 
							lightAmount,   
							chaveprimariaName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInChaveprimaria == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEPRIMARIA_BY_POLE_ID,  
							chaveprimariaName).getText());
		} 

		if (countAlreadyInChaveprimaria > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_CHAVEPRIMARIA, chaveprimariaName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInChaveprimaria + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_CHAVEPRIMARIA, chaveprimariaName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_CHAVEPRIMARIA_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_CHAVEPRIMARIA_FAILURE, countMaxPerLight);
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

private List<Chaveprimaria> fetchSelectedChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
{
	InquiryChaveprimariaRequest inquiryChaveprimariaRequest = new InquiryChaveprimariaRequest();
	inquiryChaveprimariaRequest.setPageSize(0);
	inquiryChaveprimariaRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryChaveprimariaRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryChaveprimariaRequest.setTenant(dominiosRequest.getTenant());

	return getChaveprimariaDAC().fetchAllChaveprimaria(inquiryChaveprimariaRequest).getResultsList();
}
}  
 
 
 
