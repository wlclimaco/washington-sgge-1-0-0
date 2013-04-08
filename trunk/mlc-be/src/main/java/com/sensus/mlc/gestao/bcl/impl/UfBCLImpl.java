package com.sensus.mlc.uf.bcl.impl;

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
 * The Class UfBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class UfBCLImpl implements IUfBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The uf dac. */ 
	private IUfDAC ufDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_UF_PER_LIGHT. */  
	private static final Integer MAX_UF_PER_LIGHT = 5;

	/** The Constant MAX_UF_FAILURE. */  
	private static final String MAX_UF_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_ufs";

	/** The Constant MAX_UF_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_UF. */ 
	private static final String LIGHT_ALREADY_IN_UF = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_UF_REMOVED. */  
	private static final String AUTO_UF_REMOVED = "sensus.mlc.tagvalidator.autouf.removed";

	/** The Constant UF_REMOVED. */      
	private static final String UF_REMOVED = "sensus.mlc.ufbcfimpl.mlcufdeleted";

	/** The Constant LIGHT_NOT_IN_THE_UF. */  
	private static final String LIGHT_NOT_IN_THE_UF = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_uf";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.ufbcfimpl.processrunning";

	/** The Constant ALREADY_IN_UF. */   
	private static final String ALREADY_IN_UF = "alreadInUf";

	/** The Constant MAX_UF. */  
	private static final String MAX_UF = "maxUfs";

	/** The Constant NO_LIGHTS_IN_UF. */   
	private static final String NO_LIGHTS_IN_UF = "sensus.mlc.mlc_action.light_status.no.lights_in_ufs";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UF. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UF = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UF_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UF_BY_POLE_ID =  
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
	 * Sets the uf dac.   
	 *     
	 * @param ufDACParam the new uf dac  
	 */   
	public void setUfDAC(IUfDAC ufDACParam) 
	{  
		this.ufDAC = ufDACParam;
	}  

	/**   
	 * Gets the uf dac.  
	 *     
	 * @return the uf dac 
	 */
	public IUfDAC getUfDAC() 
	{    
		return this.ufDAC;
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
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#deleteUf(com.sensus.mlc.uf.model.request.UfRequest)   
	 */ 
	@Override   
	public InternalResponse deleteUf(UfRequest ufRequest) 
	{  
		// build a list of ufs based on the user selection - Select All requirement 
		List<Uf> ufList = fetchSelectedUfs(ufRequest);
		UfRequest request = new UfRequest(ufRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Uf uf : ufList)  
		{    
			request.setUf(uf);
			if (!getUfDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {uf.getName()});
				continue;
			}     
			ufRequest.setUf(uf);

			response = getUfDAC().deleteUf(ufRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(UF_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {uf.getName()});

				removeTagFromUf(ufRequest.getUserContext(), response, uf.getName());
			}          

			// create a Process for this action uf      
			InternalResponse ufProcess = insertProcess(ufRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(ufProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#insertUf(com.sensus.mlc.uf.model.request.UfRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Uf> insertUf(UfRequest ufRequest) 
	{   
		InternalResultsResponse<Uf> response = getUfDAC().insertUf(ufRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created uf 
			InternalResponse processResponse = insertProcess(ufRequest, LCActionTypeEnum.INSERT_UF);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#updateUf(com.sensus.mlc.uf.model.request.UfRequest)  
	 */    
	@Override  
	public InternalResponse updateUf(UfRequest ufRequest)   
	{  
		InternalResponse response = getUfDAC().updateUf(ufRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(ufRequest, LCActionTypeEnum.UPDATE_UF);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.uf.bcl.IUfBCL#fetchAllUfs(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Uf> fetchAllUfs(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getUfDAC().fetchAllUfs(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#fetchUfById(com.sensus.mlc.uf.model.request.UfRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Uf> fetchUfById(UfRequest ufRequest) 
	{     
		return getUfDAC().fetchUfById(ufRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.uf.bcl.IUfBCL#fetchUfsByIdList(com.sensus.mlc.uf.model.request.UfRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Uf> fetchUfsByIdList(UfRequest ufRequest) 
	{
		return getUfDAC().fetchUfsByIdList(ufRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(UfRequest ufRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(ufRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param ufRequest the uf request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInUf the count already in uf 
	 * @param lightsWithMaxUfAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(UfRequest ufRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInUf, List<Light> lightsWithMaxUfAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Uf uf = ufRequest.getUf();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.UF_ID, String.valueOf(uf.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.UF_NAME, uf.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				uf.getName(),   
				lightAmount,  
				lightsAlreadyInUf.size(),   
				lightsWithMaxUfAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(ufRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInUf,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_UF));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxUfAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_UF_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert uf process. 
	 *    
	 * @param uf the uf 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionUf the action uf 
	 * @param countAlreadyInUf the count already in uf (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertUfProcess(Uf uf, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionUf, Integer countAlreadyInUf, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterUfId =   
				new LCActionParameter(PropertyEnum.UF_ID, String.valueOf(uf.getId()));
		LCActionParameter actionParameterUfName =     
				new LCActionParameter(PropertyEnum.UF_NAME, uf.getName());
		LCAction action = new LCAction(actionUf);

		actionParameters.add(actionParameterUfId);
		actionParameters.add(actionParameterUfName);
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

		setProcessDescription(process, uf.getName(), lightAmount, countAlreadyInUf, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param ufName the uf name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInUf the count already in uf 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String ufName,  
			Integer lightAmount,  
			int countAlreadyInUf, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				ufName,  
				lightAmount,    
				countAlreadyInUf, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param ufName the uf name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInUf the count already in uf 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String ufName,  
			Integer lightAmount,    
			int countAlreadyInUf,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UF, 
							lightAmount,   
							ufName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInUf == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_UF_BY_POLE_ID,  
							ufName).getText());
		} 

		if (countAlreadyInUf > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_UF, ufName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInUf + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_UF, ufName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_UF_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_UF_FAILURE, countMaxPerLight);
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

private List<Uf> fetchSelectedUf(UfRequest ufRequest)
{
	InquiryUfRequest inquiryUfRequest = new InquiryUfRequest();
	inquiryUfRequest.setPageSize(0);
	inquiryUfRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryUfRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryUfRequest.setTenant(dominiosRequest.getTenant());

	return getUfDAC().fetchAllUf(inquiryUfRequest).getResultsList();
}
}  
 
 
 
