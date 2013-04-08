package com.sensus.mlc.tipcliente.bcl.impl;

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
 * The Class TipclienteBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class TipclienteBCLImpl implements ITipclienteBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The tipcliente dac. */ 
	private ITipclienteDAC tipclienteDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_TIPCLIENTE_PER_LIGHT. */  
	private static final Integer MAX_TIPCLIENTE_PER_LIGHT = 5;

	/** The Constant MAX_TIPCLIENTE_FAILURE. */  
	private static final String MAX_TIPCLIENTE_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_tipclientes";

	/** The Constant MAX_TIPCLIENTE_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_TIPCLIENTE. */ 
	private static final String LIGHT_ALREADY_IN_TIPCLIENTE = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_TIPCLIENTE_REMOVED. */  
	private static final String AUTO_TIPCLIENTE_REMOVED = "sensus.mlc.tagvalidator.autotipcliente.removed";

	/** The Constant TIPCLIENTE_REMOVED. */      
	private static final String TIPCLIENTE_REMOVED = "sensus.mlc.tipclientebcfimpl.mlctipclientedeleted";

	/** The Constant LIGHT_NOT_IN_THE_TIPCLIENTE. */  
	private static final String LIGHT_NOT_IN_THE_TIPCLIENTE = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_tipcliente";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.tipclientebcfimpl.processrunning";

	/** The Constant ALREADY_IN_TIPCLIENTE. */   
	private static final String ALREADY_IN_TIPCLIENTE = "alreadInTipcliente";

	/** The Constant MAX_TIPCLIENTE. */  
	private static final String MAX_TIPCLIENTE = "maxTipclientes";

	/** The Constant NO_LIGHTS_IN_TIPCLIENTE. */   
	private static final String NO_LIGHTS_IN_TIPCLIENTE = "sensus.mlc.mlc_action.light_status.no.lights_in_tipclientes";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TIPCLIENTE. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TIPCLIENTE = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TIPCLIENTE_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TIPCLIENTE_BY_POLE_ID =  
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
	 * Sets the tipcliente dac.   
	 *     
	 * @param tipclienteDACParam the new tipcliente dac  
	 */   
	public void setTipclienteDAC(ITipclienteDAC tipclienteDACParam) 
	{  
		this.tipclienteDAC = tipclienteDACParam;
	}  

	/**   
	 * Gets the tipcliente dac.  
	 *     
	 * @return the tipcliente dac 
	 */
	public ITipclienteDAC getTipclienteDAC() 
	{    
		return this.tipclienteDAC;
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
	 * @see com.sensus.mlc.tipcliente.bcl.ITipclienteBCL#deleteTipcliente(com.sensus.mlc.tipcliente.model.request.TipclienteRequest)   
	 */ 
	@Override   
	public InternalResponse deleteTipcliente(TipclienteRequest tipclienteRequest) 
	{  
		// build a list of tipclientes based on the user selection - Select All requirement 
		List<Tipcliente> tipclienteList = fetchSelectedTipclientes(tipclienteRequest);
		TipclienteRequest request = new TipclienteRequest(tipclienteRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Tipcliente tipcliente : tipclienteList)  
		{    
			request.setTipcliente(tipcliente);
			if (!getTipclienteDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {tipcliente.getName()});
				continue;
			}     
			tipclienteRequest.setTipcliente(tipcliente);

			response = getTipclienteDAC().deleteTipcliente(tipclienteRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(TIPCLIENTE_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {tipcliente.getName()});

				removeTagFromTipcliente(tipclienteRequest.getUserContext(), response, tipcliente.getName());
			}          

			// create a Process for this action tipcliente      
			InternalResponse tipclienteProcess = insertProcess(tipclienteRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(tipclienteProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.tipcliente.bcl.ITipclienteBCL#insertTipcliente(com.sensus.mlc.tipcliente.model.request.TipclienteRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Tipcliente> insertTipcliente(TipclienteRequest tipclienteRequest) 
	{   
		InternalResultsResponse<Tipcliente> response = getTipclienteDAC().insertTipcliente(tipclienteRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created tipcliente 
			InternalResponse processResponse = insertProcess(tipclienteRequest, LCActionTypeEnum.INSERT_TIPCLIENTE);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.tipcliente.bcl.ITipclienteBCL#updateTipcliente(com.sensus.mlc.tipcliente.model.request.TipclienteRequest)  
	 */    
	@Override  
	public InternalResponse updateTipcliente(TipclienteRequest tipclienteRequest)   
	{  
		InternalResponse response = getTipclienteDAC().updateTipcliente(tipclienteRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(tipclienteRequest, LCActionTypeEnum.UPDATE_TIPCLIENTE);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.tipcliente.bcl.ITipclienteBCL#fetchAllTipclientes(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Tipcliente> fetchAllTipclientes(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getTipclienteDAC().fetchAllTipclientes(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.tipcliente.bcl.ITipclienteBCL#fetchTipclienteById(com.sensus.mlc.tipcliente.model.request.TipclienteRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Tipcliente> fetchTipclienteById(TipclienteRequest tipclienteRequest) 
	{     
		return getTipclienteDAC().fetchTipclienteById(tipclienteRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.tipcliente.bcl.ITipclienteBCL#fetchTipclientesByIdList(com.sensus.mlc.tipcliente.model.request.TipclienteRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Tipcliente> fetchTipclientesByIdList(TipclienteRequest tipclienteRequest) 
	{
		return getTipclienteDAC().fetchTipclientesByIdList(tipclienteRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(TipclienteRequest tipclienteRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(tipclienteRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param tipclienteRequest the tipcliente request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInTipcliente the count already in tipcliente 
	 * @param lightsWithMaxTipclienteAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(TipclienteRequest tipclienteRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInTipcliente, List<Light> lightsWithMaxTipclienteAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Tipcliente tipcliente = tipclienteRequest.getTipcliente();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.TIPCLIENTE_ID, String.valueOf(tipcliente.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.TIPCLIENTE_NAME, tipcliente.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				tipcliente.getName(),   
				lightAmount,  
				lightsAlreadyInTipcliente.size(),   
				lightsWithMaxTipclienteAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(tipclienteRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInTipcliente,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_TIPCLIENTE));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxTipclienteAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_TIPCLIENTE_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert tipcliente process. 
	 *    
	 * @param tipcliente the tipcliente 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionTipcliente the action tipcliente 
	 * @param countAlreadyInTipcliente the count already in tipcliente (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertTipclienteProcess(Tipcliente tipcliente, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionTipcliente, Integer countAlreadyInTipcliente, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterTipclienteId =   
				new LCActionParameter(PropertyEnum.TIPCLIENTE_ID, String.valueOf(tipcliente.getId()));
		LCActionParameter actionParameterTipclienteName =     
				new LCActionParameter(PropertyEnum.TIPCLIENTE_NAME, tipcliente.getName());
		LCAction action = new LCAction(actionTipcliente);

		actionParameters.add(actionParameterTipclienteId);
		actionParameters.add(actionParameterTipclienteName);
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

		setProcessDescription(process, tipcliente.getName(), lightAmount, countAlreadyInTipcliente, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param tipclienteName the tipcliente name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInTipcliente the count already in tipcliente 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String tipclienteName,  
			Integer lightAmount,  
			int countAlreadyInTipcliente, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				tipclienteName,  
				lightAmount,    
				countAlreadyInTipcliente, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param tipclienteName the tipcliente name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInTipcliente the count already in tipcliente 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String tipclienteName,  
			Integer lightAmount,    
			int countAlreadyInTipcliente,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TIPCLIENTE, 
							lightAmount,   
							tipclienteName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInTipcliente == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TIPCLIENTE_BY_POLE_ID,  
							tipclienteName).getText());
		} 

		if (countAlreadyInTipcliente > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_TIPCLIENTE, tipclienteName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInTipcliente + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_TIPCLIENTE, tipclienteName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_TIPCLIENTE_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_TIPCLIENTE_FAILURE, countMaxPerLight);
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

private List<Tipcliente> fetchSelectedTipcliente(TipclienteRequest tipclienteRequest)
{
	InquiryTipclienteRequest inquiryTipclienteRequest = new InquiryTipclienteRequest();
	inquiryTipclienteRequest.setPageSize(0);
	inquiryTipclienteRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryTipclienteRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryTipclienteRequest.setTenant(dominiosRequest.getTenant());

	return getTipclienteDAC().fetchAllTipcliente(inquiryTipclienteRequest).getResultsList();
}
}  
 
 
 
