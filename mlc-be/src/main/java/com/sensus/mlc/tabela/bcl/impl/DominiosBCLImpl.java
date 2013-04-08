package com.sensus.mlc.tabela.bcl.impl;

import static com.sensus.mlc.base.util.LCActionUtil.createMessageInfoNone;
import static com.sensus.mlc.base.util.LCActionUtil.createMessageWarningOther;
import static com.sensus.mlc.base.util.LCActionUtil.generateDescription;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;
import static com.sensus.mlc.base.util.LCHelp.generateProcess;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.Tenant;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.mlcserver.client.MlcServerClient;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.tabela.bcl.IDominiosBCL;
import com.sensus.mlc.tabela.dac.IDominiosDAC;
import com.sensus.mlc.tabela.model.Dominios;
import com.sensus.mlc.tabela.model.request.DominiosRequest;
import com.sensus.mlc.tabela.model.request.InquiryDominiosRequest;
import com.sensus.mlc.tag.bcl.ITagBCL;



/**
 * The Class DominiosBCLImpl.
 *
 * @author Washington
 *
 */
public class DominiosBCLImpl implements IDominiosBCL
{

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The dominios dac. */
	private IDominiosDAC dominiosDAC;

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL;  // injected by Spring through setter

	/** The Constant MAX_DOMINIOS_PER_LIGHT. */
	private static final Integer MAX_DOMINIOS_PER_LIGHT = 5;

	/** The Constant MAX_DOMINIOS_FAILURE. */
	private static final String MAX_DOMINIOS_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_dominioss";

	/** The Constant MAX_DOMINIOS_FAILURE. */
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_DOMINIOS. */
	private static final String LIGHT_ALREADY_IN_DOMINIOS = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_DOMINIOS_REMOVED. */
	private static final String AUTO_DOMINIOS_REMOVED = "sensus.mlc.tagvalidator.autodominios.removed";

	/** The Constant DOMINIOS_REMOVED. */
	private static final String DOMINIOS_REMOVED = "sensus.mlc.dominiosbcfimpl.mlcdominiosdeleted";

	/** The Constant LIGHT_NOT_IN_THE_DOMINIOS. */
	private static final String LIGHT_NOT_IN_THE_DOMINIOS = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_dominios";

	/** The Constant PROCESS_RUNNING. */
	private static final String PROCESS_RUNNING = "sensus.mlc.dominiosbcfimpl.processrunning";

	/** The Constant ALREADY_IN_DOMINIOS. */
	private static final String ALREADY_IN_DOMINIOS = "alreadInDominios";

	/** The Constant MAX_DOMINIOS. */
	private static final String MAX_DOMINIOS = "maxDominioss";

	/** The Constant NO_LIGHTS_IN_DOMINIOS. */
	private static final String NO_LIGHTS_IN_DOMINIOS = "sensus.mlc.mlc_action.light_status.no.lights_in_dominioss";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_DOMINIOS. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_DOMINIOS = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_DOMINIOS_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_DOMINIOS_BY_POLE_ID =
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
	 * Sets the dominios dac.
	 *
	 * @param dominiosDACParam the new dominios dac
	 */
	public void setDominiosDAC(IDominiosDAC dominiosDACParam)
	{
		this.dominiosDAC = dominiosDACParam;
	}

	/**
	 * Gets the dominios dac.
	 *
	 * @return the dominios dac
	 */
	public IDominiosDAC getDominiosDAC()
	{
		return this.dominiosDAC;
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
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#deleteDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */
	@Override
	public InternalResponse deleteDominios(DominiosRequest dominiosRequest)
	{
		// build a list of dominioss based on the user selection - Select All requirement
		List<Dominios> dominiosList = fetchSelectedDominios(dominiosRequest);
		DominiosRequest request = new DominiosRequest();
		InternalResponse response = new InternalResponse();

		for (Dominios dominios : dominiosList)
		{
			request.setDominios(dominios);
			dominiosRequest.setDominios(dominios);

			response = getDominiosDAC().deleteDominios(dominiosRequest);
			if (response.getStatus().equals(Status.OperationSuccess))
			{
				response.addMessage(DOMINIOS_REMOVED, MessageSeverity.Info, MessageLevel.None,
						new Object[] {dominios.getDsdomini()});

			}

			// create a Process for this action dominios
			InternalResponse dominiosProcess = insertProcess(dominiosRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(dominiosProcess.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#insertDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */
	@Override
	public InternalResultsResponse<Dominios> insertDominios(DominiosRequest dominiosRequest)
	{
		InternalResultsResponse<Dominios> response = getDominiosDAC().insertDominios(dominiosRequest);

		if (!response.isInError())
		{
			// create a Process for the created dominios
			InternalResponse processResponse = insertProcess(dominiosRequest, LCActionTypeEnum.INSERT_DOMINIOS);
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#updateDominios(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */
	@Override
	public InternalResponse updateDominios(DominiosRequest dominiosRequest)
	{
		InternalResponse response = getDominiosDAC().updateDominios(dominiosRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(dominiosRequest, LCActionTypeEnum.UPDATE_DOMINIOS);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.dominios.bcl.IDominiosBCL#fetchAllDominioss(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<Dominios> fetchAllDominios(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return getDominiosDAC().fetchAllDominios(inquiryPaginationRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#fetchDominiosById(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */
	@Override
	public InternalResultsResponse<Dominios> fetchDominiosById(DominiosRequest dominiosRequest)
	{
		return getDominiosDAC().fetchDominiosById(dominiosRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.dominios.bcl.IDominiosBCL#fetchDominiossByIdList(com.sensus.mlc.dominios.model.request.DominiosRequest)
	 */
	@Override
	public InternalResultsResponse<Dominios> fetchDominiossByIdList(DominiosRequest dominiosRequest)
	{
		return getDominiosDAC().fetchDominiossByIdList(dominiosRequest);
	}

	private InternalResultsResponse<Process> insertProcess(DominiosRequest dominiosRequest, LCActionTypeEnum lcActionType)
	{
		return insertProcess(dominiosRequest,
				lcActionType,
				NumberUtils.INTEGER_ZERO,
				new ArrayList<Light>(),
				new ArrayList<Light>(),
				new ArrayList<Light>());
	}

	/**
	 * Insert process.
	 *
	 * @param dominiosRequest the dominios request
	 * @param lcActionType the lc action type
	 * @param lightAmount the light amount
	 * @param lightsAlreadyInDominios the count already in dominios
	 * @param lightsWithMaxDominiosAllowed the count max per light
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(DominiosRequest dominiosRequest, LCActionTypeEnum lcActionType,
			Integer lightAmount, List<Light> lightsAlreadyInDominios, List<Light> lightsWithMaxDominiosAllowed,
			List<Light> deactivatedLights)
	{
		Dominios dominios = dominiosRequest.getDominios();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.DOMINIOS_ID, String.valueOf(dominios.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.DOMINIOS_NAME, dominios.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(
				process,
				dominios.getName(),
				lightAmount,
				lightsAlreadyInDominios.size(),
				lightsWithMaxDominiosAllowed.size(),
				deactivatedLights.size(),
				processResponse);

		ProcessRequest processRequest = createProcessRequest(dominiosRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(
				createProcessItemWithFailure(
						lightsAlreadyInDominios,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_BELONG_DOMINIOS));

		processRequest.getProcessItemFailureList().addAll(
				createProcessItemWithFailure(
						lightsWithMaxDominiosAllowed,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_DOMINIOS_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}


	/**
	 * Insert dominios process.
	 *
	 * @param dominios the dominios
	 * @param userContext the user context
	 * @param tenant the tenant
	 * @param actionDominios the action dominios
	 * @param countAlreadyInDominios the count already in dominios (in order to give a specific error message according
	 *            Business Requirement)
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business
	 *            Requirement)
	 * @param lightList the light list
	 * @return the internal response
	 */
	private InternalResponse insertDominiosProcess(Dominios dominios, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionDominios, Integer countAlreadyInDominios, Integer countMaxPerLight, List<Light> lightList)
	{
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterDominiosId =
				new LCActionParameter(PropertyEnum.DOMINIOS_ID, String.valueOf(dominios.getId()));
		LCActionParameter actionParameterDominiosName =
				new LCActionParameter(PropertyEnum.DOMINIOS_NAME, dominios.getName());
		LCAction action = new LCAction(actionDominios);

		actionParameters.add(actionParameterDominiosId);
		actionParameters.add(actionParameterDominiosName);
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

		setProcessDescription(process, dominios.getName(), lightAmount, countAlreadyInDominios, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.
	 *
	 * @param process the process
	 * @param dominiosName the dominios name
	 * @param lightAmount the light amount
	 * @param countAlreadyInDominios the count already in dominios
	 * @param countMaxPerLight the count max per light
	 */
	private void setProcessDescription(
			Process process,
			String dominiosName,
			Integer lightAmount,
			int countAlreadyInDominios,
			int countMaxPerLight)
	{
		setProcessDescription(
				process,
				dominiosName,
				lightAmount,
				countAlreadyInDominios,
				countMaxPerLight,
				NumberUtils.INTEGER_ZERO,
				new InternalResultsResponse<Process>());
	}

	/**
	 * Sets the process description.
	 *
	 * @param process the process
	 * @param dominiosName the dominios name
	 * @param lightAmount the light amount
	 * @param countAlreadyInDominios the count already in dominios
	 * @param countMaxPerLight the count max per light
	 * @param processResponse the process response
	 */
	private void setProcessDescription(
			Process process,
			String dominiosName,
			Integer lightAmount,
			int countAlreadyInDominios,
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
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_DOMINIOS,
							lightAmount,
							dominiosName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInDominios == 0) && (countMaxPerLight == 0) && (countDeactivated == 0))
		{
			process.setDescription(
					createMessageWarningOther(
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_DOMINIOS_BY_POLE_ID,
							dominiosName).getText());
		}

		if (countAlreadyInDominios > 0)
		{
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_DOMINIOS, dominiosName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInDominios + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_DOMINIOS, dominiosName);
		}

		if (countMaxPerLight > 0)
		{
			Message message = createMessageInfoNone(MAX_DOMINIOS_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_DOMINIOS_FAILURE, countMaxPerLight);
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

	@Override
	public InternalResultsResponse<Dominios> fetchAllDominios(
			InquiryDominiosRequest inquirydominiosRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Dominios> fetchAllDominiosTypes(
			InquiryDominiosRequest inquirydominiosRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Dominios> fetchAllDominiosFilial(
			DominiosRequest dominiosRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	private List<Dominios> fetchSelectedDominios(DominiosRequest dominiosRequest)
	{
		InquiryDominiosRequest inquiryDominiosRequest = new InquiryDominiosRequest();
		inquiryDominiosRequest.setPageSize(0);
		inquiryDominiosRequest.setSelectionPaginationIds(dominiosRequest.getSelectionPaginationIds());
		inquiryDominiosRequest.setPaginationAllSelected(dominiosRequest.getPaginationAllSelected());
		inquiryDominiosRequest.setTenant(dominiosRequest.getTenant());

		return getDominiosDAC().fetchAllDominios(inquiryDominiosRequest).getResultsList();
	}
}



