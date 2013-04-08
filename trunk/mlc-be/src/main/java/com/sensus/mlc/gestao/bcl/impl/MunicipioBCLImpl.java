package com.sensus.mlc.municipio.bcl.impl;

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
 * The Class MunicipioBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class MunicipioBCLImpl implements IMunicipioBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The municipio dac. */ 
	private IMunicipioDAC municipioDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_MUNICIPIO_PER_LIGHT. */  
	private static final Integer MAX_MUNICIPIO_PER_LIGHT = 5;

	/** The Constant MAX_MUNICIPIO_FAILURE. */  
	private static final String MAX_MUNICIPIO_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_municipios";

	/** The Constant MAX_MUNICIPIO_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_MUNICIPIO. */ 
	private static final String LIGHT_ALREADY_IN_MUNICIPIO = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_MUNICIPIO_REMOVED. */  
	private static final String AUTO_MUNICIPIO_REMOVED = "sensus.mlc.tagvalidator.automunicipio.removed";

	/** The Constant MUNICIPIO_REMOVED. */      
	private static final String MUNICIPIO_REMOVED = "sensus.mlc.municipiobcfimpl.mlcmunicipiodeleted";

	/** The Constant LIGHT_NOT_IN_THE_MUNICIPIO. */  
	private static final String LIGHT_NOT_IN_THE_MUNICIPIO = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_municipio";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.municipiobcfimpl.processrunning";

	/** The Constant ALREADY_IN_MUNICIPIO. */   
	private static final String ALREADY_IN_MUNICIPIO = "alreadInMunicipio";

	/** The Constant MAX_MUNICIPIO. */  
	private static final String MAX_MUNICIPIO = "maxMunicipios";

	/** The Constant NO_LIGHTS_IN_MUNICIPIO. */   
	private static final String NO_LIGHTS_IN_MUNICIPIO = "sensus.mlc.mlc_action.light_status.no.lights_in_municipios";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_MUNICIPIO. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_MUNICIPIO = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_MUNICIPIO_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_MUNICIPIO_BY_POLE_ID =  
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
	 * Sets the municipio dac.   
	 *     
	 * @param municipioDACParam the new municipio dac  
	 */   
	public void setMunicipioDAC(IMunicipioDAC municipioDACParam) 
	{  
		this.municipioDAC = municipioDACParam;
	}  

	/**   
	 * Gets the municipio dac.  
	 *     
	 * @return the municipio dac 
	 */
	public IMunicipioDAC getMunicipioDAC() 
	{    
		return this.municipioDAC;
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
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#deleteMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)   
	 */ 
	@Override   
	public InternalResponse deleteMunicipio(MunicipioRequest municipioRequest) 
	{  
		// build a list of municipios based on the user selection - Select All requirement 
		List<Municipio> municipioList = fetchSelectedMunicipios(municipioRequest);
		MunicipioRequest request = new MunicipioRequest(municipioRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Municipio municipio : municipioList)  
		{    
			request.setMunicipio(municipio);
			if (!getMunicipioDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {municipio.getName()});
				continue;
			}     
			municipioRequest.setMunicipio(municipio);

			response = getMunicipioDAC().deleteMunicipio(municipioRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(MUNICIPIO_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {municipio.getName()});

				removeTagFromMunicipio(municipioRequest.getUserContext(), response, municipio.getName());
			}          

			// create a Process for this action municipio      
			InternalResponse municipioProcess = insertProcess(municipioRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(municipioProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#insertMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Municipio> insertMunicipio(MunicipioRequest municipioRequest) 
	{   
		InternalResultsResponse<Municipio> response = getMunicipioDAC().insertMunicipio(municipioRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created municipio 
			InternalResponse processResponse = insertProcess(municipioRequest, LCActionTypeEnum.INSERT_MUNICIPIO);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#updateMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)  
	 */    
	@Override  
	public InternalResponse updateMunicipio(MunicipioRequest municipioRequest)   
	{  
		InternalResponse response = getMunicipioDAC().updateMunicipio(municipioRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(municipioRequest, LCActionTypeEnum.UPDATE_MUNICIPIO);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.municipio.bcl.IMunicipioBCL#fetchAllMunicipios(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Municipio> fetchAllMunicipios(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getMunicipioDAC().fetchAllMunicipios(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#fetchMunicipioById(com.sensus.mlc.municipio.model.request.MunicipioRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Municipio> fetchMunicipioById(MunicipioRequest municipioRequest) 
	{     
		return getMunicipioDAC().fetchMunicipioById(municipioRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#fetchMunicipiosByIdList(com.sensus.mlc.municipio.model.request.MunicipioRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Municipio> fetchMunicipiosByIdList(MunicipioRequest municipioRequest) 
	{
		return getMunicipioDAC().fetchMunicipiosByIdList(municipioRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(MunicipioRequest municipioRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(municipioRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param municipioRequest the municipio request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInMunicipio the count already in municipio 
	 * @param lightsWithMaxMunicipioAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(MunicipioRequest municipioRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInMunicipio, List<Light> lightsWithMaxMunicipioAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Municipio municipio = municipioRequest.getMunicipio();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.MUNICIPIO_ID, String.valueOf(municipio.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.MUNICIPIO_NAME, municipio.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				municipio.getName(),   
				lightAmount,  
				lightsAlreadyInMunicipio.size(),   
				lightsWithMaxMunicipioAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(municipioRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInMunicipio,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_MUNICIPIO));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxMunicipioAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_MUNICIPIO_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert municipio process. 
	 *    
	 * @param municipio the municipio 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionMunicipio the action municipio 
	 * @param countAlreadyInMunicipio the count already in municipio (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertMunicipioProcess(Municipio municipio, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionMunicipio, Integer countAlreadyInMunicipio, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterMunicipioId =   
				new LCActionParameter(PropertyEnum.MUNICIPIO_ID, String.valueOf(municipio.getId()));
		LCActionParameter actionParameterMunicipioName =     
				new LCActionParameter(PropertyEnum.MUNICIPIO_NAME, municipio.getName());
		LCAction action = new LCAction(actionMunicipio);

		actionParameters.add(actionParameterMunicipioId);
		actionParameters.add(actionParameterMunicipioName);
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

		setProcessDescription(process, municipio.getName(), lightAmount, countAlreadyInMunicipio, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param municipioName the municipio name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInMunicipio the count already in municipio 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String municipioName,  
			Integer lightAmount,  
			int countAlreadyInMunicipio, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				municipioName,  
				lightAmount,    
				countAlreadyInMunicipio, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param municipioName the municipio name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInMunicipio the count already in municipio 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String municipioName,  
			Integer lightAmount,    
			int countAlreadyInMunicipio,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_MUNICIPIO, 
							lightAmount,   
							municipioName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInMunicipio == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_MUNICIPIO_BY_POLE_ID,  
							municipioName).getText());
		} 

		if (countAlreadyInMunicipio > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_MUNICIPIO, municipioName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInMunicipio + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_MUNICIPIO, municipioName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_MUNICIPIO_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_MUNICIPIO_FAILURE, countMaxPerLight);
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

private List<Municipio> fetchSelectedMunicipio(MunicipioRequest municipioRequest)
{
	InquiryMunicipioRequest inquiryMunicipioRequest = new InquiryMunicipioRequest();
	inquiryMunicipioRequest.setPageSize(0);
	inquiryMunicipioRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryMunicipioRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryMunicipioRequest.setTenant(dominiosRequest.getTenant());

	return getMunicipioDAC().fetchAllMunicipio(inquiryMunicipioRequest).getResultsList();
}
}  
 
 
 
