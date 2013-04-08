package com.sensus.mlc.filial.bcl.impl;

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
 * The Class FilialBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class FilialBCLImpl implements IFilialBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The filial dac. */ 
	private IFilialDAC filialDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_FILIAL_PER_LIGHT. */  
	private static final Integer MAX_FILIAL_PER_LIGHT = 5;

	/** The Constant MAX_FILIAL_FAILURE. */  
	private static final String MAX_FILIAL_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_filials";

	/** The Constant MAX_FILIAL_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_FILIAL. */ 
	private static final String LIGHT_ALREADY_IN_FILIAL = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_FILIAL_REMOVED. */  
	private static final String AUTO_FILIAL_REMOVED = "sensus.mlc.tagvalidator.autofilial.removed";

	/** The Constant FILIAL_REMOVED. */      
	private static final String FILIAL_REMOVED = "sensus.mlc.filialbcfimpl.mlcfilialdeleted";

	/** The Constant LIGHT_NOT_IN_THE_FILIAL. */  
	private static final String LIGHT_NOT_IN_THE_FILIAL = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_filial";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.filialbcfimpl.processrunning";

	/** The Constant ALREADY_IN_FILIAL. */   
	private static final String ALREADY_IN_FILIAL = "alreadInFilial";

	/** The Constant MAX_FILIAL. */  
	private static final String MAX_FILIAL = "maxFilials";

	/** The Constant NO_LIGHTS_IN_FILIAL. */   
	private static final String NO_LIGHTS_IN_FILIAL = "sensus.mlc.mlc_action.light_status.no.lights_in_filials";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_FILIAL. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_FILIAL = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_FILIAL_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_FILIAL_BY_POLE_ID =  
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
	 * Sets the filial dac.   
	 *     
	 * @param filialDACParam the new filial dac  
	 */   
	public void setFilialDAC(IFilialDAC filialDACParam) 
	{  
		this.filialDAC = filialDACParam;
	}  

	/**   
	 * Gets the filial dac.  
	 *     
	 * @return the filial dac 
	 */
	public IFilialDAC getFilialDAC() 
	{    
		return this.filialDAC;
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
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#deleteFilial(com.sensus.mlc.filial.model.request.FilialRequest)   
	 */ 
	@Override   
	public InternalResponse deleteFilial(FilialRequest filialRequest) 
	{  
		// build a list of filials based on the user selection - Select All requirement 
		List<Filial> filialList = fetchSelectedFilials(filialRequest);
		FilialRequest request = new FilialRequest(filialRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Filial filial : filialList)  
		{    
			request.setFilial(filial);
			if (!getFilialDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {filial.getName()});
				continue;
			}     
			filialRequest.setFilial(filial);

			response = getFilialDAC().deleteFilial(filialRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(FILIAL_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {filial.getName()});

				removeTagFromFilial(filialRequest.getUserContext(), response, filial.getName());
			}          

			// create a Process for this action filial      
			InternalResponse filialProcess = insertProcess(filialRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(filialProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#insertFilial(com.sensus.mlc.filial.model.request.FilialRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Filial> insertFilial(FilialRequest filialRequest) 
	{   
		InternalResultsResponse<Filial> response = getFilialDAC().insertFilial(filialRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created filial 
			InternalResponse processResponse = insertProcess(filialRequest, LCActionTypeEnum.INSERT_FILIAL);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#updateFilial(com.sensus.mlc.filial.model.request.FilialRequest)  
	 */    
	@Override  
	public InternalResponse updateFilial(FilialRequest filialRequest)   
	{  
		InternalResponse response = getFilialDAC().updateFilial(filialRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(filialRequest, LCActionTypeEnum.UPDATE_FILIAL);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.filial.bcl.IFilialBCL#fetchAllFilials(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Filial> fetchAllFilials(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getFilialDAC().fetchAllFilials(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#fetchFilialById(com.sensus.mlc.filial.model.request.FilialRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Filial> fetchFilialById(FilialRequest filialRequest) 
	{     
		return getFilialDAC().fetchFilialById(filialRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.filial.bcl.IFilialBCL#fetchFilialsByIdList(com.sensus.mlc.filial.model.request.FilialRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Filial> fetchFilialsByIdList(FilialRequest filialRequest) 
	{
		return getFilialDAC().fetchFilialsByIdList(filialRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(FilialRequest filialRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(filialRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param filialRequest the filial request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInFilial the count already in filial 
	 * @param lightsWithMaxFilialAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(FilialRequest filialRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInFilial, List<Light> lightsWithMaxFilialAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Filial filial = filialRequest.getFilial();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.FILIAL_ID, String.valueOf(filial.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.FILIAL_NAME, filial.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				filial.getName(),   
				lightAmount,  
				lightsAlreadyInFilial.size(),   
				lightsWithMaxFilialAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(filialRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInFilial,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_FILIAL));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxFilialAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_FILIAL_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert filial process. 
	 *    
	 * @param filial the filial 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionFilial the action filial 
	 * @param countAlreadyInFilial the count already in filial (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertFilialProcess(Filial filial, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionFilial, Integer countAlreadyInFilial, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterFilialId =   
				new LCActionParameter(PropertyEnum.FILIAL_ID, String.valueOf(filial.getId()));
		LCActionParameter actionParameterFilialName =     
				new LCActionParameter(PropertyEnum.FILIAL_NAME, filial.getName());
		LCAction action = new LCAction(actionFilial);

		actionParameters.add(actionParameterFilialId);
		actionParameters.add(actionParameterFilialName);
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

		setProcessDescription(process, filial.getName(), lightAmount, countAlreadyInFilial, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param filialName the filial name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInFilial the count already in filial 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String filialName,  
			Integer lightAmount,  
			int countAlreadyInFilial, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				filialName,  
				lightAmount,    
				countAlreadyInFilial, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param filialName the filial name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInFilial the count already in filial 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String filialName,  
			Integer lightAmount,    
			int countAlreadyInFilial,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_FILIAL, 
							lightAmount,   
							filialName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInFilial == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_FILIAL_BY_POLE_ID,  
							filialName).getText());
		} 

		if (countAlreadyInFilial > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_FILIAL, filialName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInFilial + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_FILIAL, filialName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_FILIAL_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_FILIAL_FAILURE, countMaxPerLight);
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

private List<Filial> fetchSelectedFilial(FilialRequest filialRequest)
{
	InquiryFilialRequest inquiryFilialRequest = new InquiryFilialRequest();
	inquiryFilialRequest.setPageSize(0);
	inquiryFilialRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryFilialRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryFilialRequest.setTenant(dominiosRequest.getTenant());

	return getFilialDAC().fetchAllFilial(inquiryFilialRequest).getResultsList();
}
}  
 
 
 
