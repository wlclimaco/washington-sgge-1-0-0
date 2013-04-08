package com.sensus.mlc.classcliente.bcl.impl;

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
 * The Class ClassclienteBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class ClassclienteBCLImpl implements IClassclienteBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The classcliente dac. */ 
	private IClassclienteDAC classclienteDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_CLASSCLIENTE_PER_LIGHT. */  
	private static final Integer MAX_CLASSCLIENTE_PER_LIGHT = 5;

	/** The Constant MAX_CLASSCLIENTE_FAILURE. */  
	private static final String MAX_CLASSCLIENTE_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_classclientes";

	/** The Constant MAX_CLASSCLIENTE_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_CLASSCLIENTE. */ 
	private static final String LIGHT_ALREADY_IN_CLASSCLIENTE = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_CLASSCLIENTE_REMOVED. */  
	private static final String AUTO_CLASSCLIENTE_REMOVED = "sensus.mlc.tagvalidator.autoclasscliente.removed";

	/** The Constant CLASSCLIENTE_REMOVED. */      
	private static final String CLASSCLIENTE_REMOVED = "sensus.mlc.classclientebcfimpl.mlcclassclientedeleted";

	/** The Constant LIGHT_NOT_IN_THE_CLASSCLIENTE. */  
	private static final String LIGHT_NOT_IN_THE_CLASSCLIENTE = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_classcliente";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.classclientebcfimpl.processrunning";

	/** The Constant ALREADY_IN_CLASSCLIENTE. */   
	private static final String ALREADY_IN_CLASSCLIENTE = "alreadInClasscliente";

	/** The Constant MAX_CLASSCLIENTE. */  
	private static final String MAX_CLASSCLIENTE = "maxClassclientes";

	/** The Constant NO_LIGHTS_IN_CLASSCLIENTE. */   
	private static final String NO_LIGHTS_IN_CLASSCLIENTE = "sensus.mlc.mlc_action.light_status.no.lights_in_classclientes";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CLASSCLIENTE. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CLASSCLIENTE = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CLASSCLIENTE_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CLASSCLIENTE_BY_POLE_ID =  
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
	 * Sets the classcliente dac.   
	 *     
	 * @param classclienteDACParam the new classcliente dac  
	 */   
	public void setClassclienteDAC(IClassclienteDAC classclienteDACParam) 
	{  
		this.classclienteDAC = classclienteDACParam;
	}  

	/**   
	 * Gets the classcliente dac.  
	 *     
	 * @return the classcliente dac 
	 */
	public IClassclienteDAC getClassclienteDAC() 
	{    
		return this.classclienteDAC;
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
	 * @see com.sensus.mlc.classcliente.bcl.IClassclienteBCL#deleteClasscliente(com.sensus.mlc.classcliente.model.request.ClassclienteRequest)   
	 */ 
	@Override   
	public InternalResponse deleteClasscliente(ClassclienteRequest classclienteRequest) 
	{  
		// build a list of classclientes based on the user selection - Select All requirement 
		List<Classcliente> classclienteList = fetchSelectedClassclientes(classclienteRequest);
		ClassclienteRequest request = new ClassclienteRequest(classclienteRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Classcliente classcliente : classclienteList)  
		{    
			request.setClasscliente(classcliente);
			if (!getClassclienteDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {classcliente.getName()});
				continue;
			}     
			classclienteRequest.setClasscliente(classcliente);

			response = getClassclienteDAC().deleteClasscliente(classclienteRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(CLASSCLIENTE_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {classcliente.getName()});

				removeTagFromClasscliente(classclienteRequest.getUserContext(), response, classcliente.getName());
			}          

			// create a Process for this action classcliente      
			InternalResponse classclienteProcess = insertProcess(classclienteRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(classclienteProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.classcliente.bcl.IClassclienteBCL#insertClasscliente(com.sensus.mlc.classcliente.model.request.ClassclienteRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Classcliente> insertClasscliente(ClassclienteRequest classclienteRequest) 
	{   
		InternalResultsResponse<Classcliente> response = getClassclienteDAC().insertClasscliente(classclienteRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created classcliente 
			InternalResponse processResponse = insertProcess(classclienteRequest, LCActionTypeEnum.INSERT_CLASSCLIENTE);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.classcliente.bcl.IClassclienteBCL#updateClasscliente(com.sensus.mlc.classcliente.model.request.ClassclienteRequest)  
	 */    
	@Override  
	public InternalResponse updateClasscliente(ClassclienteRequest classclienteRequest)   
	{  
		InternalResponse response = getClassclienteDAC().updateClasscliente(classclienteRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(classclienteRequest, LCActionTypeEnum.UPDATE_CLASSCLIENTE);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.classcliente.bcl.IClassclienteBCL#fetchAllClassclientes(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Classcliente> fetchAllClassclientes(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getClassclienteDAC().fetchAllClassclientes(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.classcliente.bcl.IClassclienteBCL#fetchClassclienteById(com.sensus.mlc.classcliente.model.request.ClassclienteRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Classcliente> fetchClassclienteById(ClassclienteRequest classclienteRequest) 
	{     
		return getClassclienteDAC().fetchClassclienteById(classclienteRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.classcliente.bcl.IClassclienteBCL#fetchClassclientesByIdList(com.sensus.mlc.classcliente.model.request.ClassclienteRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Classcliente> fetchClassclientesByIdList(ClassclienteRequest classclienteRequest) 
	{
		return getClassclienteDAC().fetchClassclientesByIdList(classclienteRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(ClassclienteRequest classclienteRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(classclienteRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param classclienteRequest the classcliente request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInClasscliente the count already in classcliente 
	 * @param lightsWithMaxClassclienteAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(ClassclienteRequest classclienteRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInClasscliente, List<Light> lightsWithMaxClassclienteAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Classcliente classcliente = classclienteRequest.getClasscliente();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.CLASSCLIENTE_ID, String.valueOf(classcliente.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.CLASSCLIENTE_NAME, classcliente.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				classcliente.getName(),   
				lightAmount,  
				lightsAlreadyInClasscliente.size(),   
				lightsWithMaxClassclienteAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(classclienteRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInClasscliente,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_CLASSCLIENTE));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxClassclienteAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_CLASSCLIENTE_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert classcliente process. 
	 *    
	 * @param classcliente the classcliente 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionClasscliente the action classcliente 
	 * @param countAlreadyInClasscliente the count already in classcliente (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertClassclienteProcess(Classcliente classcliente, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionClasscliente, Integer countAlreadyInClasscliente, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterClassclienteId =   
				new LCActionParameter(PropertyEnum.CLASSCLIENTE_ID, String.valueOf(classcliente.getId()));
		LCActionParameter actionParameterClassclienteName =     
				new LCActionParameter(PropertyEnum.CLASSCLIENTE_NAME, classcliente.getName());
		LCAction action = new LCAction(actionClasscliente);

		actionParameters.add(actionParameterClassclienteId);
		actionParameters.add(actionParameterClassclienteName);
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

		setProcessDescription(process, classcliente.getName(), lightAmount, countAlreadyInClasscliente, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param classclienteName the classcliente name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInClasscliente the count already in classcliente 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String classclienteName,  
			Integer lightAmount,  
			int countAlreadyInClasscliente, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				classclienteName,  
				lightAmount,    
				countAlreadyInClasscliente, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param classclienteName the classcliente name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInClasscliente the count already in classcliente 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String classclienteName,  
			Integer lightAmount,    
			int countAlreadyInClasscliente,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CLASSCLIENTE, 
							lightAmount,   
							classclienteName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInClasscliente == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_CLASSCLIENTE_BY_POLE_ID,  
							classclienteName).getText());
		} 

		if (countAlreadyInClasscliente > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_CLASSCLIENTE, classclienteName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInClasscliente + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_CLASSCLIENTE, classclienteName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_CLASSCLIENTE_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_CLASSCLIENTE_FAILURE, countMaxPerLight);
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

private List<Classcliente> fetchSelectedClasscliente(ClassclienteRequest classclienteRequest)
{
	InquiryClassclienteRequest inquiryClassclienteRequest = new InquiryClassclienteRequest();
	inquiryClassclienteRequest.setPageSize(0);
	inquiryClassclienteRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryClassclienteRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryClassclienteRequest.setTenant(dominiosRequest.getTenant());

	return getClassclienteDAC().fetchAllClasscliente(inquiryClassclienteRequest).getResultsList();
}
}  
 
 
 
