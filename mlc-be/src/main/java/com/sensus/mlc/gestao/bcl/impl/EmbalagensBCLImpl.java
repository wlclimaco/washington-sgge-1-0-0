package com.sensus.mlc.embalagens.bcl.impl;

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
 * The Class EmbalagensBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class EmbalagensBCLImpl implements IEmbalagensBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The embalagens dac. */ 
	private IEmbalagensDAC embalagensDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_EMBALAGENS_PER_LIGHT. */  
	private static final Integer MAX_EMBALAGENS_PER_LIGHT = 5;

	/** The Constant MAX_EMBALAGENS_FAILURE. */  
	private static final String MAX_EMBALAGENS_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_embalagenss";

	/** The Constant MAX_EMBALAGENS_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_EMBALAGENS. */ 
	private static final String LIGHT_ALREADY_IN_EMBALAGENS = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_EMBALAGENS_REMOVED. */  
	private static final String AUTO_EMBALAGENS_REMOVED = "sensus.mlc.tagvalidator.autoembalagens.removed";

	/** The Constant EMBALAGENS_REMOVED. */      
	private static final String EMBALAGENS_REMOVED = "sensus.mlc.embalagensbcfimpl.mlcembalagensdeleted";

	/** The Constant LIGHT_NOT_IN_THE_EMBALAGENS. */  
	private static final String LIGHT_NOT_IN_THE_EMBALAGENS = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_embalagens";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.embalagensbcfimpl.processrunning";

	/** The Constant ALREADY_IN_EMBALAGENS. */   
	private static final String ALREADY_IN_EMBALAGENS = "alreadInEmbalagens";

	/** The Constant MAX_EMBALAGENS. */  
	private static final String MAX_EMBALAGENS = "maxEmbalagenss";

	/** The Constant NO_LIGHTS_IN_EMBALAGENS. */   
	private static final String NO_LIGHTS_IN_EMBALAGENS = "sensus.mlc.mlc_action.light_status.no.lights_in_embalagenss";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMBALAGENS. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMBALAGENS = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMBALAGENS_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMBALAGENS_BY_POLE_ID =  
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
	 * Sets the embalagens dac.   
	 *     
	 * @param embalagensDACParam the new embalagens dac  
	 */   
	public void setEmbalagensDAC(IEmbalagensDAC embalagensDACParam) 
	{  
		this.embalagensDAC = embalagensDACParam;
	}  

	/**   
	 * Gets the embalagens dac.  
	 *     
	 * @return the embalagens dac 
	 */
	public IEmbalagensDAC getEmbalagensDAC() 
	{    
		return this.embalagensDAC;
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
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#deleteEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)   
	 */ 
	@Override   
	public InternalResponse deleteEmbalagens(EmbalagensRequest embalagensRequest) 
	{  
		// build a list of embalagenss based on the user selection - Select All requirement 
		List<Embalagens> embalagensList = fetchSelectedEmbalagenss(embalagensRequest);
		EmbalagensRequest request = new EmbalagensRequest(embalagensRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Embalagens embalagens : embalagensList)  
		{    
			request.setEmbalagens(embalagens);
			if (!getEmbalagensDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {embalagens.getName()});
				continue;
			}     
			embalagensRequest.setEmbalagens(embalagens);

			response = getEmbalagensDAC().deleteEmbalagens(embalagensRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(EMBALAGENS_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {embalagens.getName()});

				removeTagFromEmbalagens(embalagensRequest.getUserContext(), response, embalagens.getName());
			}          

			// create a Process for this action embalagens      
			InternalResponse embalagensProcess = insertProcess(embalagensRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(embalagensProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#insertEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Embalagens> insertEmbalagens(EmbalagensRequest embalagensRequest) 
	{   
		InternalResultsResponse<Embalagens> response = getEmbalagensDAC().insertEmbalagens(embalagensRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created embalagens 
			InternalResponse processResponse = insertProcess(embalagensRequest, LCActionTypeEnum.INSERT_EMBALAGENS);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#updateEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)  
	 */    
	@Override  
	public InternalResponse updateEmbalagens(EmbalagensRequest embalagensRequest)   
	{  
		InternalResponse response = getEmbalagensDAC().updateEmbalagens(embalagensRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(embalagensRequest, LCActionTypeEnum.UPDATE_EMBALAGENS);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#fetchAllEmbalagenss(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Embalagens> fetchAllEmbalagenss(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getEmbalagensDAC().fetchAllEmbalagenss(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#fetchEmbalagensById(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Embalagens> fetchEmbalagensById(EmbalagensRequest embalagensRequest) 
	{     
		return getEmbalagensDAC().fetchEmbalagensById(embalagensRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.embalagens.bcl.IEmbalagensBCL#fetchEmbalagenssByIdList(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Embalagens> fetchEmbalagenssByIdList(EmbalagensRequest embalagensRequest) 
	{
		return getEmbalagensDAC().fetchEmbalagenssByIdList(embalagensRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(EmbalagensRequest embalagensRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(embalagensRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param embalagensRequest the embalagens request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInEmbalagens the count already in embalagens 
	 * @param lightsWithMaxEmbalagensAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(EmbalagensRequest embalagensRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInEmbalagens, List<Light> lightsWithMaxEmbalagensAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Embalagens embalagens = embalagensRequest.getEmbalagens();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMBALAGENS_ID, String.valueOf(embalagens.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.EMBALAGENS_NAME, embalagens.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				embalagens.getName(),   
				lightAmount,  
				lightsAlreadyInEmbalagens.size(),   
				lightsWithMaxEmbalagensAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(embalagensRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInEmbalagens,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_EMBALAGENS));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxEmbalagensAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_EMBALAGENS_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert embalagens process. 
	 *    
	 * @param embalagens the embalagens 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionEmbalagens the action embalagens 
	 * @param countAlreadyInEmbalagens the count already in embalagens (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertEmbalagensProcess(Embalagens embalagens, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionEmbalagens, Integer countAlreadyInEmbalagens, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterEmbalagensId =   
				new LCActionParameter(PropertyEnum.EMBALAGENS_ID, String.valueOf(embalagens.getId()));
		LCActionParameter actionParameterEmbalagensName =     
				new LCActionParameter(PropertyEnum.EMBALAGENS_NAME, embalagens.getName());
		LCAction action = new LCAction(actionEmbalagens);

		actionParameters.add(actionParameterEmbalagensId);
		actionParameters.add(actionParameterEmbalagensName);
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

		setProcessDescription(process, embalagens.getName(), lightAmount, countAlreadyInEmbalagens, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param embalagensName the embalagens name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEmbalagens the count already in embalagens 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String embalagensName,  
			Integer lightAmount,  
			int countAlreadyInEmbalagens, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				embalagensName,  
				lightAmount,    
				countAlreadyInEmbalagens, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param embalagensName the embalagens name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEmbalagens the count already in embalagens 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String embalagensName,  
			Integer lightAmount,    
			int countAlreadyInEmbalagens,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMBALAGENS, 
							lightAmount,   
							embalagensName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInEmbalagens == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMBALAGENS_BY_POLE_ID,  
							embalagensName).getText());
		} 

		if (countAlreadyInEmbalagens > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_EMBALAGENS, embalagensName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInEmbalagens + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_EMBALAGENS, embalagensName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_EMBALAGENS_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_EMBALAGENS_FAILURE, countMaxPerLight);
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

private List<Embalagens> fetchSelectedEmbalagens(EmbalagensRequest embalagensRequest)
{
	InquiryEmbalagensRequest inquiryEmbalagensRequest = new InquiryEmbalagensRequest();
	inquiryEmbalagensRequest.setPageSize(0);
	inquiryEmbalagensRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryEmbalagensRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryEmbalagensRequest.setTenant(dominiosRequest.getTenant());

	return getEmbalagensDAC().fetchAllEmbalagens(inquiryEmbalagensRequest).getResultsList();
}
}  
 
 
 
