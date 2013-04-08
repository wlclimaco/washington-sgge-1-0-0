package com.sensus.mlc.bairro.bcl.impl;

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
 * The Class BairroBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class BairroBCLImpl implements IBairroBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The bairro dac. */ 
	private IBairroDAC bairroDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_BAIRRO_PER_LIGHT. */  
	private static final Integer MAX_BAIRRO_PER_LIGHT = 5;

	/** The Constant MAX_BAIRRO_FAILURE. */  
	private static final String MAX_BAIRRO_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_bairros";

	/** The Constant MAX_BAIRRO_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_BAIRRO. */ 
	private static final String LIGHT_ALREADY_IN_BAIRRO = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_BAIRRO_REMOVED. */  
	private static final String AUTO_BAIRRO_REMOVED = "sensus.mlc.tagvalidator.autobairro.removed";

	/** The Constant BAIRRO_REMOVED. */      
	private static final String BAIRRO_REMOVED = "sensus.mlc.bairrobcfimpl.mlcbairrodeleted";

	/** The Constant LIGHT_NOT_IN_THE_BAIRRO. */  
	private static final String LIGHT_NOT_IN_THE_BAIRRO = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_bairro";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.bairrobcfimpl.processrunning";

	/** The Constant ALREADY_IN_BAIRRO. */   
	private static final String ALREADY_IN_BAIRRO = "alreadInBairro";

	/** The Constant MAX_BAIRRO. */  
	private static final String MAX_BAIRRO = "maxBairros";

	/** The Constant NO_LIGHTS_IN_BAIRRO. */   
	private static final String NO_LIGHTS_IN_BAIRRO = "sensus.mlc.mlc_action.light_status.no.lights_in_bairros";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_BAIRRO. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_BAIRRO = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_BAIRRO_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_BAIRRO_BY_POLE_ID =  
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
	 * Sets the bairro dac.   
	 *     
	 * @param bairroDACParam the new bairro dac  
	 */   
	public void setBairroDAC(IBairroDAC bairroDACParam) 
	{  
		this.bairroDAC = bairroDACParam;
	}  

	/**   
	 * Gets the bairro dac.  
	 *     
	 * @return the bairro dac 
	 */
	public IBairroDAC getBairroDAC() 
	{    
		return this.bairroDAC;
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
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#deleteBairro(com.sensus.mlc.bairro.model.request.BairroRequest)   
	 */ 
	@Override   
	public InternalResponse deleteBairro(BairroRequest bairroRequest) 
	{  
		// build a list of bairros based on the user selection - Select All requirement 
		List<Bairro> bairroList = fetchSelectedBairros(bairroRequest);
		BairroRequest request = new BairroRequest(bairroRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Bairro bairro : bairroList)  
		{    
			request.setBairro(bairro);
			if (!getBairroDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {bairro.getName()});
				continue;
			}     
			bairroRequest.setBairro(bairro);

			response = getBairroDAC().deleteBairro(bairroRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(BAIRRO_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {bairro.getName()});

				removeTagFromBairro(bairroRequest.getUserContext(), response, bairro.getName());
			}          

			// create a Process for this action bairro      
			InternalResponse bairroProcess = insertProcess(bairroRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(bairroProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#insertBairro(com.sensus.mlc.bairro.model.request.BairroRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Bairro> insertBairro(BairroRequest bairroRequest) 
	{   
		InternalResultsResponse<Bairro> response = getBairroDAC().insertBairro(bairroRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created bairro 
			InternalResponse processResponse = insertProcess(bairroRequest, LCActionTypeEnum.INSERT_BAIRRO);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#updateBairro(com.sensus.mlc.bairro.model.request.BairroRequest)  
	 */    
	@Override  
	public InternalResponse updateBairro(BairroRequest bairroRequest)   
	{  
		InternalResponse response = getBairroDAC().updateBairro(bairroRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(bairroRequest, LCActionTypeEnum.UPDATE_BAIRRO);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.bairro.bcl.IBairroBCL#fetchAllBairros(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Bairro> fetchAllBairros(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getBairroDAC().fetchAllBairros(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#fetchBairroById(com.sensus.mlc.bairro.model.request.BairroRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Bairro> fetchBairroById(BairroRequest bairroRequest) 
	{     
		return getBairroDAC().fetchBairroById(bairroRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#fetchBairrosByIdList(com.sensus.mlc.bairro.model.request.BairroRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Bairro> fetchBairrosByIdList(BairroRequest bairroRequest) 
	{
		return getBairroDAC().fetchBairrosByIdList(bairroRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(BairroRequest bairroRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(bairroRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param bairroRequest the bairro request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInBairro the count already in bairro 
	 * @param lightsWithMaxBairroAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(BairroRequest bairroRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInBairro, List<Light> lightsWithMaxBairroAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Bairro bairro = bairroRequest.getBairro();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.BAIRRO_ID, String.valueOf(bairro.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.BAIRRO_NAME, bairro.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				bairro.getName(),   
				lightAmount,  
				lightsAlreadyInBairro.size(),   
				lightsWithMaxBairroAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(bairroRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInBairro,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_BAIRRO));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxBairroAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_BAIRRO_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert bairro process. 
	 *    
	 * @param bairro the bairro 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionBairro the action bairro 
	 * @param countAlreadyInBairro the count already in bairro (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertBairroProcess(Bairro bairro, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionBairro, Integer countAlreadyInBairro, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterBairroId =   
				new LCActionParameter(PropertyEnum.BAIRRO_ID, String.valueOf(bairro.getId()));
		LCActionParameter actionParameterBairroName =     
				new LCActionParameter(PropertyEnum.BAIRRO_NAME, bairro.getName());
		LCAction action = new LCAction(actionBairro);

		actionParameters.add(actionParameterBairroId);
		actionParameters.add(actionParameterBairroName);
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

		setProcessDescription(process, bairro.getName(), lightAmount, countAlreadyInBairro, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param bairroName the bairro name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInBairro the count already in bairro 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String bairroName,  
			Integer lightAmount,  
			int countAlreadyInBairro, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				bairroName,  
				lightAmount,    
				countAlreadyInBairro, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param bairroName the bairro name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInBairro the count already in bairro 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String bairroName,  
			Integer lightAmount,    
			int countAlreadyInBairro,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_BAIRRO, 
							lightAmount,   
							bairroName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInBairro == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_BAIRRO_BY_POLE_ID,  
							bairroName).getText());
		} 

		if (countAlreadyInBairro > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_BAIRRO, bairroName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInBairro + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_BAIRRO, bairroName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_BAIRRO_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_BAIRRO_FAILURE, countMaxPerLight);
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

private List<Bairro> fetchSelectedBairro(BairroRequest bairroRequest)
{
	InquiryBairroRequest inquiryBairroRequest = new InquiryBairroRequest();
	inquiryBairroRequest.setPageSize(0);
	inquiryBairroRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryBairroRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryBairroRequest.setTenant(dominiosRequest.getTenant());

	return getBairroDAC().fetchAllBairro(inquiryBairroRequest).getResultsList();
}
}  
 
 
 
