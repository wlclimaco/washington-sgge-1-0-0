package com.sensus.mlc.titulares.bcl.impl;

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
 * The Class TitularesBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class TitularesBCLImpl implements ITitularesBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The titulares dac. */ 
	private ITitularesDAC titularesDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_TITULARES_PER_LIGHT. */  
	private static final Integer MAX_TITULARES_PER_LIGHT = 5;

	/** The Constant MAX_TITULARES_FAILURE. */  
	private static final String MAX_TITULARES_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_titularess";

	/** The Constant MAX_TITULARES_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_TITULARES. */ 
	private static final String LIGHT_ALREADY_IN_TITULARES = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_TITULARES_REMOVED. */  
	private static final String AUTO_TITULARES_REMOVED = "sensus.mlc.tagvalidator.autotitulares.removed";

	/** The Constant TITULARES_REMOVED. */      
	private static final String TITULARES_REMOVED = "sensus.mlc.titularesbcfimpl.mlctitularesdeleted";

	/** The Constant LIGHT_NOT_IN_THE_TITULARES. */  
	private static final String LIGHT_NOT_IN_THE_TITULARES = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_titulares";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.titularesbcfimpl.processrunning";

	/** The Constant ALREADY_IN_TITULARES. */   
	private static final String ALREADY_IN_TITULARES = "alreadInTitulares";

	/** The Constant MAX_TITULARES. */  
	private static final String MAX_TITULARES = "maxTitularess";

	/** The Constant NO_LIGHTS_IN_TITULARES. */   
	private static final String NO_LIGHTS_IN_TITULARES = "sensus.mlc.mlc_action.light_status.no.lights_in_titularess";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TITULARES. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TITULARES = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TITULARES_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TITULARES_BY_POLE_ID =  
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
	 * Sets the titulares dac.   
	 *     
	 * @param titularesDACParam the new titulares dac  
	 */   
	public void setTitularesDAC(ITitularesDAC titularesDACParam) 
	{  
		this.titularesDAC = titularesDACParam;
	}  

	/**   
	 * Gets the titulares dac.  
	 *     
	 * @return the titulares dac 
	 */
	public ITitularesDAC getTitularesDAC() 
	{    
		return this.titularesDAC;
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
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#deleteTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)   
	 */ 
	@Override   
	public InternalResponse deleteTitulares(TitularesRequest titularesRequest) 
	{  
		// build a list of titularess based on the user selection - Select All requirement 
		List<Titulares> titularesList = fetchSelectedTitularess(titularesRequest);
		TitularesRequest request = new TitularesRequest(titularesRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Titulares titulares : titularesList)  
		{    
			request.setTitulares(titulares);
			if (!getTitularesDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {titulares.getName()});
				continue;
			}     
			titularesRequest.setTitulares(titulares);

			response = getTitularesDAC().deleteTitulares(titularesRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(TITULARES_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {titulares.getName()});

				removeTagFromTitulares(titularesRequest.getUserContext(), response, titulares.getName());
			}          

			// create a Process for this action titulares      
			InternalResponse titularesProcess = insertProcess(titularesRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(titularesProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#insertTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Titulares> insertTitulares(TitularesRequest titularesRequest) 
	{   
		InternalResultsResponse<Titulares> response = getTitularesDAC().insertTitulares(titularesRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created titulares 
			InternalResponse processResponse = insertProcess(titularesRequest, LCActionTypeEnum.INSERT_TITULARES);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#updateTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)  
	 */    
	@Override  
	public InternalResponse updateTitulares(TitularesRequest titularesRequest)   
	{  
		InternalResponse response = getTitularesDAC().updateTitulares(titularesRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(titularesRequest, LCActionTypeEnum.UPDATE_TITULARES);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.titulares.bcl.ITitularesBCL#fetchAllTitularess(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Titulares> fetchAllTitularess(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getTitularesDAC().fetchAllTitularess(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#fetchTitularesById(com.sensus.mlc.titulares.model.request.TitularesRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Titulares> fetchTitularesById(TitularesRequest titularesRequest) 
	{     
		return getTitularesDAC().fetchTitularesById(titularesRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.titulares.bcl.ITitularesBCL#fetchTitularessByIdList(com.sensus.mlc.titulares.model.request.TitularesRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Titulares> fetchTitularessByIdList(TitularesRequest titularesRequest) 
	{
		return getTitularesDAC().fetchTitularessByIdList(titularesRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(TitularesRequest titularesRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(titularesRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param titularesRequest the titulares request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInTitulares the count already in titulares 
	 * @param lightsWithMaxTitularesAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(TitularesRequest titularesRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInTitulares, List<Light> lightsWithMaxTitularesAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Titulares titulares = titularesRequest.getTitulares();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.TITULARES_ID, String.valueOf(titulares.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.TITULARES_NAME, titulares.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				titulares.getName(),   
				lightAmount,  
				lightsAlreadyInTitulares.size(),   
				lightsWithMaxTitularesAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(titularesRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInTitulares,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_TITULARES));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxTitularesAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_TITULARES_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert titulares process. 
	 *    
	 * @param titulares the titulares 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionTitulares the action titulares 
	 * @param countAlreadyInTitulares the count already in titulares (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertTitularesProcess(Titulares titulares, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionTitulares, Integer countAlreadyInTitulares, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterTitularesId =   
				new LCActionParameter(PropertyEnum.TITULARES_ID, String.valueOf(titulares.getId()));
		LCActionParameter actionParameterTitularesName =     
				new LCActionParameter(PropertyEnum.TITULARES_NAME, titulares.getName());
		LCAction action = new LCAction(actionTitulares);

		actionParameters.add(actionParameterTitularesId);
		actionParameters.add(actionParameterTitularesName);
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

		setProcessDescription(process, titulares.getName(), lightAmount, countAlreadyInTitulares, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param titularesName the titulares name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInTitulares the count already in titulares 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String titularesName,  
			Integer lightAmount,  
			int countAlreadyInTitulares, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				titularesName,  
				lightAmount,    
				countAlreadyInTitulares, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param titularesName the titulares name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInTitulares the count already in titulares 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String titularesName,  
			Integer lightAmount,    
			int countAlreadyInTitulares,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TITULARES, 
							lightAmount,   
							titularesName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInTitulares == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TITULARES_BY_POLE_ID,  
							titularesName).getText());
		} 

		if (countAlreadyInTitulares > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_TITULARES, titularesName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInTitulares + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_TITULARES, titularesName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_TITULARES_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_TITULARES_FAILURE, countMaxPerLight);
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

private List<Titulares> fetchSelectedTitulares(TitularesRequest titularesRequest)
{
	InquiryTitularesRequest inquiryTitularesRequest = new InquiryTitularesRequest();
	inquiryTitularesRequest.setPageSize(0);
	inquiryTitularesRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryTitularesRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryTitularesRequest.setTenant(dominiosRequest.getTenant());

	return getTitularesDAC().fetchAllTitulares(inquiryTitularesRequest).getResultsList();
}
}  
 
 
 
