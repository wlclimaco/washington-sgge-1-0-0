package com.sensus.mlc.pais.bcl.impl;

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
 * The Class PaisBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class PaisBCLImpl implements IPaisBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The pais dac. */ 
	private IPaisDAC paisDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_PAIS_PER_LIGHT. */  
	private static final Integer MAX_PAIS_PER_LIGHT = 5;

	/** The Constant MAX_PAIS_FAILURE. */  
	private static final String MAX_PAIS_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_paiss";

	/** The Constant MAX_PAIS_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_PAIS. */ 
	private static final String LIGHT_ALREADY_IN_PAIS = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_PAIS_REMOVED. */  
	private static final String AUTO_PAIS_REMOVED = "sensus.mlc.tagvalidator.autopais.removed";

	/** The Constant PAIS_REMOVED. */      
	private static final String PAIS_REMOVED = "sensus.mlc.paisbcfimpl.mlcpaisdeleted";

	/** The Constant LIGHT_NOT_IN_THE_PAIS. */  
	private static final String LIGHT_NOT_IN_THE_PAIS = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_pais";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.paisbcfimpl.processrunning";

	/** The Constant ALREADY_IN_PAIS. */   
	private static final String ALREADY_IN_PAIS = "alreadInPais";

	/** The Constant MAX_PAIS. */  
	private static final String MAX_PAIS = "maxPaiss";

	/** The Constant NO_LIGHTS_IN_PAIS. */   
	private static final String NO_LIGHTS_IN_PAIS = "sensus.mlc.mlc_action.light_status.no.lights_in_paiss";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_PAIS. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_PAIS = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_PAIS_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_PAIS_BY_POLE_ID =  
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
	 * Sets the pais dac.   
	 *     
	 * @param paisDACParam the new pais dac  
	 */   
	public void setPaisDAC(IPaisDAC paisDACParam) 
	{  
		this.paisDAC = paisDACParam;
	}  

	/**   
	 * Gets the pais dac.  
	 *     
	 * @return the pais dac 
	 */
	public IPaisDAC getPaisDAC() 
	{    
		return this.paisDAC;
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
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#deletePais(com.sensus.mlc.pais.model.request.PaisRequest)   
	 */ 
	@Override   
	public InternalResponse deletePais(PaisRequest paisRequest) 
	{  
		// build a list of paiss based on the user selection - Select All requirement 
		List<Pais> paisList = fetchSelectedPaiss(paisRequest);
		PaisRequest request = new PaisRequest(paisRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Pais pais : paisList)  
		{    
			request.setPais(pais);
			if (!getPaisDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {pais.getName()});
				continue;
			}     
			paisRequest.setPais(pais);

			response = getPaisDAC().deletePais(paisRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(PAIS_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {pais.getName()});

				removeTagFromPais(paisRequest.getUserContext(), response, pais.getName());
			}          

			// create a Process for this action pais      
			InternalResponse paisProcess = insertProcess(paisRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(paisProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#insertPais(com.sensus.mlc.pais.model.request.PaisRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Pais> insertPais(PaisRequest paisRequest) 
	{   
		InternalResultsResponse<Pais> response = getPaisDAC().insertPais(paisRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created pais 
			InternalResponse processResponse = insertProcess(paisRequest, LCActionTypeEnum.INSERT_PAIS);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#updatePais(com.sensus.mlc.pais.model.request.PaisRequest)  
	 */    
	@Override  
	public InternalResponse updatePais(PaisRequest paisRequest)   
	{  
		InternalResponse response = getPaisDAC().updatePais(paisRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(paisRequest, LCActionTypeEnum.UPDATE_PAIS);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.pais.bcl.IPaisBCL#fetchAllPaiss(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Pais> fetchAllPaiss(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getPaisDAC().fetchAllPaiss(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#fetchPaisById(com.sensus.mlc.pais.model.request.PaisRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Pais> fetchPaisById(PaisRequest paisRequest) 
	{     
		return getPaisDAC().fetchPaisById(paisRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.pais.bcl.IPaisBCL#fetchPaissByIdList(com.sensus.mlc.pais.model.request.PaisRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Pais> fetchPaissByIdList(PaisRequest paisRequest) 
	{
		return getPaisDAC().fetchPaissByIdList(paisRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(PaisRequest paisRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(paisRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param paisRequest the pais request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInPais the count already in pais 
	 * @param lightsWithMaxPaisAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(PaisRequest paisRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInPais, List<Light> lightsWithMaxPaisAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Pais pais = paisRequest.getPais();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.PAIS_ID, String.valueOf(pais.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.PAIS_NAME, pais.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				pais.getName(),   
				lightAmount,  
				lightsAlreadyInPais.size(),   
				lightsWithMaxPaisAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(paisRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInPais,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_PAIS));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxPaisAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_PAIS_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert pais process. 
	 *    
	 * @param pais the pais 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionPais the action pais 
	 * @param countAlreadyInPais the count already in pais (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertPaisProcess(Pais pais, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionPais, Integer countAlreadyInPais, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterPaisId =   
				new LCActionParameter(PropertyEnum.PAIS_ID, String.valueOf(pais.getId()));
		LCActionParameter actionParameterPaisName =     
				new LCActionParameter(PropertyEnum.PAIS_NAME, pais.getName());
		LCAction action = new LCAction(actionPais);

		actionParameters.add(actionParameterPaisId);
		actionParameters.add(actionParameterPaisName);
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

		setProcessDescription(process, pais.getName(), lightAmount, countAlreadyInPais, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param paisName the pais name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInPais the count already in pais 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String paisName,  
			Integer lightAmount,  
			int countAlreadyInPais, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				paisName,  
				lightAmount,    
				countAlreadyInPais, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param paisName the pais name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInPais the count already in pais 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String paisName,  
			Integer lightAmount,    
			int countAlreadyInPais,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_PAIS, 
							lightAmount,   
							paisName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInPais == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_PAIS_BY_POLE_ID,  
							paisName).getText());
		} 

		if (countAlreadyInPais > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_PAIS, paisName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInPais + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_PAIS, paisName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_PAIS_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_PAIS_FAILURE, countMaxPerLight);
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

private List<Pais> fetchSelectedPais(PaisRequest paisRequest)
{
	InquiryPaisRequest inquiryPaisRequest = new InquiryPaisRequest();
	inquiryPaisRequest.setPageSize(0);
	inquiryPaisRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryPaisRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryPaisRequest.setTenant(dominiosRequest.getTenant());

	return getPaisDAC().fetchAllPais(inquiryPaisRequest).getResultsList();
}
}  
 
 
 
