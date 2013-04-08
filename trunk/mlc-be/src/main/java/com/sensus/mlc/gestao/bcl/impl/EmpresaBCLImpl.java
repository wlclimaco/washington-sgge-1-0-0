package com.sensus.mlc.empresa.bcl.impl;

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
 * The Class EmpresaBCLImpl.
 *
 * @author Washington  
 *  
 */ 
public class EmpresaBCLImpl implements IEmpresaBCL    
{   

	/** The lc help. */ 
	private LCHelp lcHelp; // injected by Spring through setter 

	/** The empresa dac. */ 
	private IEmpresaDAC empresaDAC;

	/** The tag bcl. */    
	private ITagBCL tagBCL; // injected by Spring through setter  

	/** The smart point accessor bcl. */    
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter  

	/** The smart point updater bcl. */    
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter   

	/** The process bcl. */  
	private IProcessBCL processBCL;  // injected by Spring through setter 

	/** The Constant MAX_EMPRESA_PER_LIGHT. */  
	private static final Integer MAX_EMPRESA_PER_LIGHT = 5;

	/** The Constant MAX_EMPRESA_FAILURE. */  
	private static final String MAX_EMPRESA_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_empresas";

	/** The Constant MAX_EMPRESA_FAILURE. */  
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_EMPRESA. */ 
	private static final String LIGHT_ALREADY_IN_EMPRESA = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_EMPRESA_REMOVED. */  
	private static final String AUTO_EMPRESA_REMOVED = "sensus.mlc.tagvalidator.autoempresa.removed";

	/** The Constant EMPRESA_REMOVED. */      
	private static final String EMPRESA_REMOVED = "sensus.mlc.empresabcfimpl.mlcempresadeleted";

	/** The Constant LIGHT_NOT_IN_THE_EMPRESA. */  
	private static final String LIGHT_NOT_IN_THE_EMPRESA = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_empresa";

	/** The Constant PROCESS_RUNNING. */ 
	private static final String PROCESS_RUNNING = "sensus.mlc.empresabcfimpl.processrunning";

	/** The Constant ALREADY_IN_EMPRESA. */   
	private static final String ALREADY_IN_EMPRESA = "alreadInEmpresa";

	/** The Constant MAX_EMPRESA. */  
	private static final String MAX_EMPRESA = "maxEmpresas";

	/** The Constant NO_LIGHTS_IN_EMPRESA. */   
	private static final String NO_LIGHTS_IN_EMPRESA = "sensus.mlc.mlc_action.light_status.no.lights_in_empresas";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA. */   
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA_BY_POLE_ID. */ 
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA_BY_POLE_ID =  
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
	 * Sets the empresa dac.   
	 *     
	 * @param empresaDACParam the new empresa dac  
	 */   
	public void setEmpresaDAC(IEmpresaDAC empresaDACParam) 
	{  
		this.empresaDAC = empresaDACParam;
	}  

	/**   
	 * Gets the empresa dac.  
	 *     
	 * @return the empresa dac 
	 */
	public IEmpresaDAC getEmpresaDAC() 
	{    
		return this.empresaDAC;
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
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#deleteEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)   
	 */ 
	@Override   
	public InternalResponse deleteEmpresa(EmpresaRequest empresaRequest) 
	{  
		// build a list of empresas based on the user selection - Select All requirement 
		List<Empresa> empresaList = fetchSelectedEmpresas(empresaRequest);
		EmpresaRequest request = new EmpresaRequest(empresaRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Empresa empresa : empresaList)  
		{    
			request.setEmpresa(empresa);
			if (!getEmpresaDAC().fetchCanDelete(request))
			{  
				// There are Processes running, return with an error   
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None, 
						new Object[] {empresa.getName()});
				continue;
			}     
			empresaRequest.setEmpresa(empresa);

			response = getEmpresaDAC().deleteEmpresa(empresaRequest);
			if (response.getStatus().equals(Status.OperationSuccess))    
			{            
				response.addMessage(EMPRESA_REMOVED, MessageSeverity.Info, MessageLevel.None,  
						new Object[] {empresa.getName()});

				removeTagFromEmpresa(empresaRequest.getUserContext(), response, empresa.getName());
			}          

			// create a Process for this action empresa      
			InternalResponse empresaProcess = insertProcess(empresaRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(empresaProcess.getMessageInfoList());
		} 

		return response;
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#insertEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)    
	 */   
	@Override    
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest empresaRequest) 
	{   
		InternalResultsResponse<Empresa> response = getEmpresaDAC().insertEmpresa(empresaRequest);

		if (!response.isInError())  
		{ 
			// create a Process for the created empresa 
			InternalResponse processResponse = insertProcess(empresaRequest, LCActionTypeEnum.INSERT_EMPRESA);
			response.addMessages(processResponse.getMessageInfoList());
		} 
		return response;
	}   

	/*    
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#updateEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)  
	 */    
	@Override  
	public InternalResponse updateEmpresa(EmpresaRequest empresaRequest)   
	{  
		InternalResponse response = getEmpresaDAC().updateEmpresa(empresaRequest);

		if (!response.isInError()) 
		{    
			InternalResponse processResponse = insertProcess(empresaRequest, LCActionTypeEnum.UPDATE_EMPRESA);

			response.addMessages(processResponse.getMessageInfoList());
		}     
		return response;

	}   

	/*  
	 * (non-Javadoc)   
	 * @see    
	 * com.sensus.mlc.empresa.bcl.IEmpresaBCL#fetchAllEmpresas(com.sensus.mlc.base.model.request.InquiryPaginationRequest)  
	 */  
	@Override  
	public InternalResultsResponse<Empresa> fetchAllEmpresas(InquiryPaginationRequest inquiryPaginationRequest)  
	{ 
		return getEmpresaDAC().fetchAllEmpresas(inquiryPaginationRequest);
	}  

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#fetchEmpresaById(com.sensus.mlc.empresa.model.request.EmpresaRequest)     
	 */   
	@Override  
	public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest empresaRequest) 
	{     
		return getEmpresaDAC().fetchEmpresaById(empresaRequest);
	} 

	/*  
	 * (non-Javadoc)  
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#fetchEmpresasByIdList(com.sensus.mlc.empresa.model.request.EmpresaRequest)  
	 */   
	@Override   
	public InternalResultsResponse<Empresa> fetchEmpresasByIdList(EmpresaRequest empresaRequest) 
	{
		return getEmpresaDAC().fetchEmpresasByIdList(empresaRequest);
	}   

	private InternalResultsResponse<Process> insertProcess(EmpresaRequest empresaRequest, LCActionTypeEnum lcActionType)   
	{ 
		return insertProcess(empresaRequest,   
				lcActionType,  
				NumberUtils.INTEGER_ZERO,  
				new ArrayList<Light>(),   
				new ArrayList<Light>(),    
				new ArrayList<Light>());
	}    

	/**  
	 * Insert process.   
	 *    
	 * @param empresaRequest the empresa request   
	 * @param lcActionType the lc action type  
	 * @param lightAmount the light amount 
	 * @param lightsAlreadyInEmpresa the count already in empresa 
	 * @param lightsWithMaxEmpresaAllowed the count max per light 
	 * @return the internal results response  
	 */ 
	private InternalResultsResponse<Process> insertProcess(EmpresaRequest empresaRequest, LCActionTypeEnum lcActionType,  
			Integer lightAmount, List<Light> lightsAlreadyInEmpresa, List<Light> lightsWithMaxEmpresaAllowed,  
			List<Light> deactivatedLights)  
	{ 
		Empresa empresa = empresaRequest.getEmpresa();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_NAME, empresa.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(    
				process,   
				empresa.getName(),   
				lightAmount,  
				lightsAlreadyInEmpresa.size(),   
				lightsWithMaxEmpresaAllowed.size(),  
				deactivatedLights.size(),  
				processResponse);

		ProcessRequest processRequest = createProcessRequest(empresaRequest, process);
		processRequest.setProcessItemFailureList( 
				createProcessItemWithFailure(   
						deactivatedLights,        
						ProcessItemStatusEnum.MLCFAILURE,    
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(       
						lightsAlreadyInEmpresa,   
						ProcessItemStatusEnum.MLCFAILURE, 
						ProcessStatusReasonEnum.LIGHT_BELONG_EMPRESA));

		processRequest.getProcessItemFailureList().addAll(    
				createProcessItemWithFailure(      
						lightsWithMaxEmpresaAllowed, 
						ProcessItemStatusEnum.MLCFAILURE,          
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_EMPRESA_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}      


	/**   
	 * Insert empresa process. 
	 *    
	 * @param empresa the empresa 
	 * @param userContext the user context   
	 * @param tenant the tenant  
	 * @param actionEmpresa the action empresa 
	 * @param countAlreadyInEmpresa the count already in empresa (in order to give a specific error message according  
	 *            Business Requirement)    
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business   
	 *            Requirement)    
	 * @param lightList the light list 
	 * @return the internal response  
	 */   
	private InternalResponse insertEmpresaProcess(Empresa empresa, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionEmpresa, Integer countAlreadyInEmpresa, Integer countMaxPerLight, List<Light> lightList)  
	{ 
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterEmpresaId =   
				new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getId()));
		LCActionParameter actionParameterEmpresaName =     
				new LCActionParameter(PropertyEnum.EMPRESA_NAME, empresa.getName());
		LCAction action = new LCAction(actionEmpresa);

		actionParameters.add(actionParameterEmpresaId);
		actionParameters.add(actionParameterEmpresaName);
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

		setProcessDescription(process, empresa.getName(), lightAmount, countAlreadyInEmpresa, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.   
	 *      
	 * @param process the process  
	 * @param empresaName the empresa name   
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEmpresa the count already in empresa 
	 * @param countMaxPerLight the count max per light 
	 */  
	private void setProcessDescription(    
			Process process,   
			String empresaName,  
			Integer lightAmount,  
			int countAlreadyInEmpresa, 
			int countMaxPerLight)  
	{    
		setProcessDescription(     
				process,      
				empresaName,  
				lightAmount,    
				countAlreadyInEmpresa, 
				countMaxPerLight,    
				NumberUtils.INTEGER_ZERO,    
				new InternalResultsResponse<Process>());
	}   

	/**  
	 * Sets the process description.  
	 *  
	 * @param process the process 
	 * @param empresaName the empresa name 
	 * @param lightAmount the light amount   
	 * @param countAlreadyInEmpresa the count already in empresa 
	 * @param countMaxPerLight the count max per light    
	 * @param processResponse the process response     
	 */  
	private void setProcessDescription(  
			Process process,
			String empresaName,  
			Integer lightAmount,    
			int countAlreadyInEmpresa,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA, 
							lightAmount,   
							empresaName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInEmpresa == 0) && (countMaxPerLight == 0) && (countDeactivated == 0)) 
		{
			process.setDescription(    
					createMessageWarningOther( 
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA_BY_POLE_ID,  
							empresaName).getText());
		} 

		if (countAlreadyInEmpresa > 0)  
		{ 
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_EMPRESA, empresaName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInEmpresa + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_EMPRESA, empresaName);
		} 

		if (countMaxPerLight > 0)  
		{                
			Message message = createMessageInfoNone(MAX_EMPRESA_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_EMPRESA_FAILURE, countMaxPerLight);
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

private List<Empresa> fetchSelectedEmpresa(EmpresaRequest empresaRequest)
{
	InquiryEmpresaRequest inquiryEmpresaRequest = new InquiryEmpresaRequest();
	inquiryEmpresaRequest.setPageSize(0);
	inquiryEmpresaRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
	inquiryEmpresaRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
	inquiryEmpresaRequest.setTenant(dominiosRequest.getTenant());

	return getEmpresaDAC().fetchAllEmpresa(inquiryEmpresaRequest).getResultsList();
}
}  
 
 
 
