package com.sensus.mlc.chaveestrangeira.bcl.impl;

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
 * The Class ChaveestrangeiraBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class ChaveestrangeiraBCLImpl implements IChaveestrangeiraBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The chaveestrangeira dac. */ 
	private IChaveestrangeiraDAC chaveestrangeiraDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_CHAVEESTRANGEIRA_PER_LIGHT. */  
	private static final Integer MAX_CHAVEESTRANGEIRA_PER_LIGHT = 5;

	/** The Constant MAX_CHAVEESTRANGEIRA_FAILURE. */  
	private static final String MAX_CHAVEESTRANGEIRA_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_chaveestrangeiras";

	/** The Constant MAX_CHAVEESTRANGEIRA_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_CHAVEESTRANGEIRA. */ 
	private static final String LIGHT_ALREADY_IN_CHAVEESTRANGEIRA = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_CHAVEESTRANGEIRA_REMOVED. */  
	private static final String AUTO_CHAVEESTRANGEIRA_REMOVED = "sensus.mlc.tagvalidator.autochaveestrangeira.removed";

	/** The Constant CHAVEESTRANGEIRA_REMOVED. */      
	private static final String CHAVEESTRANGEIRA_REMOVED = "sensus.mlc.chaveestrangeirabcfimpl.mlcchaveestrangeiradeleted";

	/** The Constant LIGHT_NOT_IN_THE_CHAVEESTRANGEIRA. */  
	private static final String LIGHT_NOT_IN_THE_CHAVEESTRANGEIRA = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_chaveestrangeira";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.chaveestrangeirabcfimpl.processrunning";

	/** The Constant ALREADY_IN_CHAVEESTRANGEIRA. */   
	private static final String ALREADY_IN_CHAVEESTRANGEIRA = "alreadInChaveestrangeira";

	/** The Constant MAX_CHAVEESTRANGEIRA. */  
	private static final String MAX_CHAVEESTRANGEIRA = "maxChaveestrangeiras";

	/** The Constant NO_LIGHTS_IN_CHAVEESTRANGEIRA. */   
	private static final String NO_LIGHTS_IN_CHAVEESTRANGEIRA = "sensus.mlc.mlc_action.light_status.no.lights_in_chaveestrangeiras";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEESTRANGEIRA. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEESTRANGEIRA = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEESTRANGEIRA_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEESTRANGEIRA_BY_POLE_ID =  
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
	 * Sets the chaveestrangeira dac.   
	 *     
	 * @param chaveestrangeiraDACParam the new chaveestrangeira dac  
	 */   
	public void setChaveestrangeiraDAC(IChaveestrangeiraDAC chaveestrangeiraDACParam) 
	{  
		this.chaveestrangeiraDAC = chaveestrangeiraDACParam;
	}  

	/**   
	 * Gets the chaveestrangeira dac.  
	 *     
	 * @return the chaveestrangeira dac 
	 */
	public IChaveestrangeiraDAC getChaveestrangeiraDAC() 
	{    
		return this.chaveestrangeiraDAC;
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
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#deleteChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)   
	 */ 
	@Override   
	public InternalResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{  
		// build a list of chaveestrangeiras based on the user selection - Select All requirement 
		List<Chaveestrangeira> chaveestrangeiraList = fetchSelectedChaveestrangeiras(chaveestrangeiraRequest);
		ChaveestrangeiraRequest request = new ChaveestrangeiraRequest(chaveestrangeiraRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Chaveestrangeira chaveestrangeira : chaveestrangeiraList)  
		{    
			request.setChaveestrangeira(chaveestrangeira);
			if (!getChaveestrangeiraDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {chaveestrangeira.getName()});
				continue;
			}     
			chaveestrangeiraRequest.setChaveestrangeira(chaveestrangeira);

			response = getChaveestrangeiraDAC().deleteChaveestrangeira(chaveestrangeiraRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(CHAVEESTRANGEIRA_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {chaveestrangeira.getName()});

				removeTagFromChaveestrangeira(chaveestrangeiraRequest.getUserContext(), response, chaveestrangeira.getName());
			}          

			// create a Process for this action chaveestrangeira      
			InternalResponse chaveestrangeiraProcess = insertProcess(chaveestrangeiraRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(chaveestrangeiraProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#insertChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Chaveestrangeira> insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{   
		InternalResultsResponse<Chaveestrangeira> response = getChaveestrangeiraDAC().insertChaveestrangeira(chaveestrangeiraRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created chaveestrangeira 
			InternalResponse processResponse = insertProcess(chaveestrangeiraRequest, LCActionTypeEnum.INSERT_CHAVEESTRANGEIRA);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#updateChaveestrangeira(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)  
	 */    
	@Override  
	public InternalResponse updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)   
	{  
		InternalResponse response = getChaveestrangeiraDAC().updateChaveestrangeira(chaveestrangeiraRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(chaveestrangeiraRequest, LCActionTypeEnum.UPDATE_CHAVEESTRANGEIRA);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#fetchAllChaveestrangeiras(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Chaveestrangeira> fetchAllChaveestrangeiras(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getChaveestrangeiraDAC().fetchAllChaveestrangeiras(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#fetchChaveestrangeiraById(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{     
		return getChaveestrangeiraDAC().fetchChaveestrangeiraById(chaveestrangeiraRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see 
com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL#fetchChaveestrangeirasByIdList(com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Chaveestrangeira> fetchChaveestrangeirasByIdList(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{
		return getChaveestrangeiraDAC().fetchChaveestrangeirasByIdList(chaveestrangeiraRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(ChaveestrangeiraRequest chaveestrangeiraRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(chaveestrangeiraRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param chaveestrangeiraRequest the chaveestrangeira request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInChaveestrangeira the count already in chaveestrangeira 
	 * @param lightsWithMaxChaveestrangeiraAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(ChaveestrangeiraRequest chaveestrangeiraRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInChaveestrangeira, List<Light> lightsWithMaxChaveestrangeiraAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Chaveestrangeira chaveestrangeira = chaveestrangeiraRequest.getChaveestrangeira();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.CHAVEESTRANGEIRA_ID, String.valueOf(chaveestrangeira.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.CHAVEESTRANGEIRA_NAME, chaveestrangeira.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				chaveestrangeira.getName(),   
				lightAmount,  
				lightsAlreadyInChaveestrangeira.size(),   
				lightsWithMaxChaveestrangeiraAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(chaveestrangeiraRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInChaveestrangeira,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_CHAVEESTRANGEIRA));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxChaveestrangeiraAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_CHAVEESTRANGEIRA_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert chaveestrangeira process. 
	 *    
	 * @param chaveestrangeira the chaveestrangeira 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionChaveestrangeira the action chaveestrangeira 
	 * @param countAlreadyInChaveestrangeira the count already in chaveestrangeira (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertChaveestrangeiraProcess(Chaveestrangeira chaveestrangeira, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionChaveestrangeira, Integer countAlreadyInChaveestrangeira, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterChaveestrangeiraId =   
				new LCActionParameter(PropertyEnum.CHAVEESTRANGEIRA_ID, String.valueOf(chaveestrangeira.getId()));
		LCActionParameter actionParameterChaveestrangeiraName =     
				new LCActionParameter(PropertyEnum.CHAVEESTRANGEIRA_NAME, chaveestrangeira.getName());
		LCAction action = new LCAction(actionChaveestrangeira);

		actionParameters.add(actionParameterChaveestrangeiraId);
		actionParameters.add(actionParameterChaveestrangeiraName);
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

		setProcessDescription(process, chaveestrangeira.getName(), lightAmount, countAlreadyInChaveestrangeira, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param chaveestrangeiraName the chaveestrangeira name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInChaveestrangeira the count already in chaveestrangeira 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String chaveestrangeiraName,  
			Integer lightAmount,  
			int countAlreadyInChaveestrangeira, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				chaveestrangeiraName,  
				lightAmount,    
				countAlreadyInChaveestrangeira, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param chaveestrangeiraName the chaveestrangeira name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInChaveestrangeira the count already in chaveestrangeira 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String chaveestrangeiraName,  
			Integer lightAmount,    
			int countAlreadyInChaveestrangeira,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEESTRANGEIRA, 
							lightAmount,   
							chaveestrangeiraName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInChaveestrangeira == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CHAVEESTRANGEIRA_BY_POLE_ID,  
							chaveestrangeiraName).getText());
		} 

		if (countAlreadyInChaveestrangeira > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_CHAVEESTRANGEIRA, chaveestrangeiraName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInChaveestrangeira + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_CHAVEESTRANGEIRA, chaveestrangeiraName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_CHAVEESTRANGEIRA_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_CHAVEESTRANGEIRA_FAILURE, countMaxPerLight);
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

private List<Chaveestrangeira> fetchSelectedChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
{
	InquiryChaveestrangeiraRequest inquiryChaveestrangeiraRequest = new InquiryChaveestrangeiraRequest();
	inquiryChaveestrangeiraRequest.setPageSize(0);
	inquiryChaveestrangeiraRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryChaveestrangeiraRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryChaveestrangeiraRequest.setTenant(dominiosRequest.getTenant());

	return getChaveestrangeiraDAC().fetchAllChaveestrangeira(inquiryChaveestrangeiraRequest).getResultsList();
}
}  
 
 
 
