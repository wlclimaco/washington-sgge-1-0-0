package com.sensus.mlc.unimed.bcl.impl;

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
 * The Class UnimedBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class UnimedBCLImpl implements IUnimedBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The unimed dac. */ 
	private IUnimedDAC unimedDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_UNIMED_PER_LIGHT. */  
	private static final Integer MAX_UNIMED_PER_LIGHT = 5;

	/** The Constant MAX_UNIMED_FAILURE. */  
	private static final String MAX_UNIMED_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_unimeds";

	/** The Constant MAX_UNIMED_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_UNIMED. */ 
	private static final String LIGHT_ALREADY_IN_UNIMED = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_UNIMED_REMOVED. */  
	private static final String AUTO_UNIMED_REMOVED = "sensus.mlc.tagvalidator.autounimed.removed";

	/** The Constant UNIMED_REMOVED. */      
	private static final String UNIMED_REMOVED = "sensus.mlc.unimedbcfimpl.mlcunimeddeleted";

	/** The Constant LIGHT_NOT_IN_THE_UNIMED. */  
	private static final String LIGHT_NOT_IN_THE_UNIMED = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_unimed";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.unimedbcfimpl.processrunning";

	/** The Constant ALREADY_IN_UNIMED. */   
	private static final String ALREADY_IN_UNIMED = "alreadInUnimed";

	/** The Constant MAX_UNIMED. */  
	private static final String MAX_UNIMED = "maxUnimeds";

	/** The Constant NO_LIGHTS_IN_UNIMED. */   
	private static final String NO_LIGHTS_IN_UNIMED = "sensus.mlc.mlc_action.light_status.no.lights_in_unimeds";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UNIMED. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UNIMED = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UNIMED_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UNIMED_BY_POLE_ID =  
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
	 * Sets the unimed dac.   
	 *     
	 * @param unimedDACParam the new unimed dac  
	 */   
	public void setUnimedDAC(IUnimedDAC unimedDACParam) 
	{  
		this.unimedDAC = unimedDACParam;
	}  

	/**   
	 * Gets the unimed dac.  
	 *     
	 * @return the unimed dac 
	 */
	public IUnimedDAC getUnimedDAC() 
	{    
		return this.unimedDAC;
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
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#deleteUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)   
	 */ 
	@Override   
	public InternalResponse deleteUnimed(UnimedRequest unimedRequest) 
	{  
		// build a list of unimeds based on the user selection - Select All requirement 
		List<Unimed> unimedList = fetchSelectedUnimeds(unimedRequest);
		UnimedRequest request = new UnimedRequest(unimedRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Unimed unimed : unimedList)  
		{    
			request.setUnimed(unimed);
			if (!getUnimedDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {unimed.getName()});
				continue;
			}     
			unimedRequest.setUnimed(unimed);

			response = getUnimedDAC().deleteUnimed(unimedRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(UNIMED_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {unimed.getName()});

				removeTagFromUnimed(unimedRequest.getUserContext(), response, unimed.getName());
			}          

			// create a Process for this action unimed      
			InternalResponse unimedProcess = insertProcess(unimedRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(unimedProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#insertUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Unimed> insertUnimed(UnimedRequest unimedRequest) 
	{   
		InternalResultsResponse<Unimed> response = getUnimedDAC().insertUnimed(unimedRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created unimed 
			InternalResponse processResponse = insertProcess(unimedRequest, LCActionTypeEnum.INSERT_UNIMED);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#updateUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)  
	 */    
	@Override  
	public InternalResponse updateUnimed(UnimedRequest unimedRequest)   
	{  
		InternalResponse response = getUnimedDAC().updateUnimed(unimedRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(unimedRequest, LCActionTypeEnum.UPDATE_UNIMED);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.unimed.bcl.IUnimedBCL#fetchAllUnimeds(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Unimed> fetchAllUnimeds(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getUnimedDAC().fetchAllUnimeds(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#fetchUnimedById(com.sensus.mlc.unimed.model.request.UnimedRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Unimed> fetchUnimedById(UnimedRequest unimedRequest) 
	{     
		return getUnimedDAC().fetchUnimedById(unimedRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#fetchUnimedsByIdList(com.sensus.mlc.unimed.model.request.UnimedRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Unimed> fetchUnimedsByIdList(UnimedRequest unimedRequest) 
	{
		return getUnimedDAC().fetchUnimedsByIdList(unimedRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(UnimedRequest unimedRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(unimedRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param unimedRequest the unimed request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInUnimed the count already in unimed 
	 * @param lightsWithMaxUnimedAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(UnimedRequest unimedRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInUnimed, List<Light> lightsWithMaxUnimedAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Unimed unimed = unimedRequest.getUnimed();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.UNIMED_ID, String.valueOf(unimed.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.UNIMED_NAME, unimed.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				unimed.getName(),   
				lightAmount,  
				lightsAlreadyInUnimed.size(),   
				lightsWithMaxUnimedAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(unimedRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInUnimed,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_UNIMED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxUnimedAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_UNIMED_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert unimed process. 
	 *    
	 * @param unimed the unimed 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionUnimed the action unimed 
	 * @param countAlreadyInUnimed the count already in unimed (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertUnimedProcess(Unimed unimed, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionUnimed, Integer countAlreadyInUnimed, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterUnimedId =   
				new LCActionParameter(PropertyEnum.UNIMED_ID, String.valueOf(unimed.getId()));
		LCActionParameter actionParameterUnimedName =     
				new LCActionParameter(PropertyEnum.UNIMED_NAME, unimed.getName());
		LCAction action = new LCAction(actionUnimed);

		actionParameters.add(actionParameterUnimedId);
		actionParameters.add(actionParameterUnimedName);
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

		setProcessDescription(process, unimed.getName(), lightAmount, countAlreadyInUnimed, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param unimedName the unimed name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInUnimed the count already in unimed 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String unimedName,  
			Integer lightAmount,  
			int countAlreadyInUnimed, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				unimedName,  
				lightAmount,    
				countAlreadyInUnimed, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param unimedName the unimed name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInUnimed the count already in unimed 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String unimedName,  
			Integer lightAmount,    
			int countAlreadyInUnimed,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UNIMED, 
							lightAmount,   
							unimedName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInUnimed == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UNIMED_BY_POLE_ID,  
							unimedName).getText());
		} 

		if (countAlreadyInUnimed > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_UNIMED, unimedName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInUnimed + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_UNIMED, unimedName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_UNIMED_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_UNIMED_FAILURE, countMaxPerLight);
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

private List<Unimed> fetchSelectedUnimed(UnimedRequest unimedRequest)
{
	InquiryUnimedRequest inquiryUnimedRequest = new InquiryUnimedRequest();
	inquiryUnimedRequest.setPageSize(0);
	inquiryUnimedRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryUnimedRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryUnimedRequest.setTenant(dominiosRequest.getTenant());

	return getUnimedDAC().fetchAllUnimed(inquiryUnimedRequest).getResultsList();
}
}  
 
 
 
