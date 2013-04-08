package com.sensus.mlc.endereco.bcl.impl;

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
 * The Class EnderecoBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class EnderecoBCLImpl implements IEnderecoBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The endereco dac. */ 
	private IEnderecoDAC enderecoDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_ENDERECO_PER_LIGHT. */  
	private static final Integer MAX_ENDERECO_PER_LIGHT = 5;

	/** The Constant MAX_ENDERECO_FAILURE. */  
	private static final String MAX_ENDERECO_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_enderecos";

	/** The Constant MAX_ENDERECO_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_ENDERECO. */ 
	private static final String LIGHT_ALREADY_IN_ENDERECO = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_ENDERECO_REMOVED. */  
	private static final String AUTO_ENDERECO_REMOVED = "sensus.mlc.tagvalidator.autoendereco.removed";

	/** The Constant ENDERECO_REMOVED. */      
	private static final String ENDERECO_REMOVED = "sensus.mlc.enderecobcfimpl.mlcenderecodeleted";

	/** The Constant LIGHT_NOT_IN_THE_ENDERECO. */  
	private static final String LIGHT_NOT_IN_THE_ENDERECO = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_endereco";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.enderecobcfimpl.processrunning";

	/** The Constant ALREADY_IN_ENDERECO. */   
	private static final String ALREADY_IN_ENDERECO = "alreadInEndereco";

	/** The Constant MAX_ENDERECO. */  
	private static final String MAX_ENDERECO = "maxEnderecos";

	/** The Constant NO_LIGHTS_IN_ENDERECO. */   
	private static final String NO_LIGHTS_IN_ENDERECO = "sensus.mlc.mlc_action.light_status.no.lights_in_enderecos";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_ENDERECO. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_ENDERECO = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_ENDERECO_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_ENDERECO_BY_POLE_ID =  
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
	 * Sets the endereco dac.   
	 *     
	 * @param enderecoDACParam the new endereco dac  
	 */   
	public void setEnderecoDAC(IEnderecoDAC enderecoDACParam) 
	{  
		this.enderecoDAC = enderecoDACParam;
	}  

	/**   
	 * Gets the endereco dac.  
	 *     
	 * @return the endereco dac 
	 */
	public IEnderecoDAC getEnderecoDAC() 
	{    
		return this.enderecoDAC;
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
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#deleteEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)   
	 */ 
	@Override   
	public InternalResponse deleteEndereco(EnderecoRequest enderecoRequest) 
	{  
		// build a list of enderecos based on the user selection - Select All requirement 
		List<Endereco> enderecoList = fetchSelectedEnderecos(enderecoRequest);
		EnderecoRequest request = new EnderecoRequest(enderecoRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Endereco endereco : enderecoList)  
		{    
			request.setEndereco(endereco);
			if (!getEnderecoDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {endereco.getName()});
				continue;
			}     
			enderecoRequest.setEndereco(endereco);

			response = getEnderecoDAC().deleteEndereco(enderecoRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(ENDERECO_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {endereco.getName()});

				removeTagFromEndereco(enderecoRequest.getUserContext(), response, endereco.getName());
			}          

			// create a Process for this action endereco      
			InternalResponse enderecoProcess = insertProcess(enderecoRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(enderecoProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#insertEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest) 
	{   
		InternalResultsResponse<Endereco> response = getEnderecoDAC().insertEndereco(enderecoRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created endereco 
			InternalResponse processResponse = insertProcess(enderecoRequest, LCActionTypeEnum.INSERT_ENDERECO);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#updateEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)  
	 */    
	@Override  
	public InternalResponse updateEndereco(EnderecoRequest enderecoRequest)   
	{  
		InternalResponse response = getEnderecoDAC().updateEndereco(enderecoRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(enderecoRequest, LCActionTypeEnum.UPDATE_ENDERECO);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.endereco.bcl.IEnderecoBCL#fetchAllEnderecos(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Endereco> fetchAllEnderecos(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getEnderecoDAC().fetchAllEnderecos(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#fetchEnderecoById(com.sensus.mlc.endereco.model.request.EnderecoRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Endereco> fetchEnderecoById(EnderecoRequest enderecoRequest) 
	{     
		return getEnderecoDAC().fetchEnderecoById(enderecoRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.endereco.bcl.IEnderecoBCL#fetchEnderecosByIdList(com.sensus.mlc.endereco.model.request.EnderecoRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Endereco> fetchEnderecosByIdList(EnderecoRequest enderecoRequest) 
	{
		return getEnderecoDAC().fetchEnderecosByIdList(enderecoRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(EnderecoRequest enderecoRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(enderecoRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param enderecoRequest the endereco request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInEndereco the count already in endereco 
	 * @param lightsWithMaxEnderecoAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(EnderecoRequest enderecoRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInEndereco, List<Light> lightsWithMaxEnderecoAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Endereco endereco = enderecoRequest.getEndereco();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.ENDERECO_ID, String.valueOf(endereco.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.ENDERECO_NAME, endereco.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				endereco.getName(),   
				lightAmount,  
				lightsAlreadyInEndereco.size(),   
				lightsWithMaxEnderecoAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(enderecoRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInEndereco,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_ENDERECO));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxEnderecoAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_ENDERECO_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert endereco process. 
	 *    
	 * @param endereco the endereco 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionEndereco the action endereco 
	 * @param countAlreadyInEndereco the count already in endereco (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertEnderecoProcess(Endereco endereco, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionEndereco, Integer countAlreadyInEndereco, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterEnderecoId =   
				new LCActionParameter(PropertyEnum.ENDERECO_ID, String.valueOf(endereco.getId()));
		LCActionParameter actionParameterEnderecoName =     
				new LCActionParameter(PropertyEnum.ENDERECO_NAME, endereco.getName());
		LCAction action = new LCAction(actionEndereco);

		actionParameters.add(actionParameterEnderecoId);
		actionParameters.add(actionParameterEnderecoName);
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

		setProcessDescription(process, endereco.getName(), lightAmount, countAlreadyInEndereco, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param enderecoName the endereco name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEndereco the count already in endereco 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String enderecoName,  
			Integer lightAmount,  
			int countAlreadyInEndereco, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				enderecoName,  
				lightAmount,    
				countAlreadyInEndereco, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param enderecoName the endereco name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEndereco the count already in endereco 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String enderecoName,  
			Integer lightAmount,    
			int countAlreadyInEndereco,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_ENDERECO, 
							lightAmount,   
							enderecoName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInEndereco == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_ENDERECO_BY_POLE_ID,  
							enderecoName).getText());
		} 

		if (countAlreadyInEndereco > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_ENDERECO, enderecoName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInEndereco + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_ENDERECO, enderecoName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_ENDERECO_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_ENDERECO_FAILURE, countMaxPerLight);
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

private List<Endereco> fetchSelectedEndereco(EnderecoRequest enderecoRequest)
{
	InquiryEnderecoRequest inquiryEnderecoRequest = new InquiryEnderecoRequest();
	inquiryEnderecoRequest.setPageSize(0);
	inquiryEnderecoRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryEnderecoRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryEnderecoRequest.setTenant(dominiosRequest.getTenant());

	return getEnderecoDAC().fetchAllEndereco(inquiryEnderecoRequest).getResultsList();
}
}  
 
 
 
