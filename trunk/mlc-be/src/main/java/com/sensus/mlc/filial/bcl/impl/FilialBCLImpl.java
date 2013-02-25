package com.sensus.mlc.filial.bcl.impl;

import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.filial.bcl.IFilialBCL;
import com.sensus.mlc.filial.dac.IFilialDAC;
import com.sensus.mlc.filial.model.Filial;
import com.sensus.mlc.filial.model.request.FilialRequest;
import com.sensus.mlc.filial.model.request.InquiryFilialRequest;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;

/**
 * Action BCL implementation class.
 *
 * @author QAT.
 */
public class FilialBCLImpl implements IFilialBCL
{

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(FilialBCLImpl.class);

	/** The filial dac. */
	private IFilialDAC filialDAC; // injected by Spring through setter

	/** The Constant PROCESS_BCL_BEAN. */
	private static final String PROCESS_BCL_BEAN = "processBCLTarget";

	/** The Constant SCHEDULE_BCL_BEAN. */
	private static final String SCHEDULE_BCL_BEAN = "scheduleBCLTarget";

	/** The Constant ACTION_PROVIDER_TYPE. */
	private static final String ACTION_PROVIDER_TYPE = "EPM.TASK";

	/** The Constant ADD_PROPERTY_TO_ACTION_FAILED. */
	private static final String ADD_PROPERTY_TO_ACTION_FAILED =
			"sensus.epm.actionbclimpl.add.property.to.action.failed";

	/** The Constant UPDATE_PROPERTY_TO_ACTION_FAILED. */
	private static final String UPDATE_PROPERTY_TO_ACTION_FAILED =
			"sensus.epm.actionbclimpl.update.property.to.action.failed";

	/** The Constant DELETE_PROPERTY_FROM_ACTION_FAILED. */
	private static final String DELETE_PROPERTY_FROM_ACTION_FAILED =
			"sensus.epm.actionbclimpl.delete.property.from.action.failed";

	/** The Constant PROPERTY_DAC_BEAN. */
	private static final String PROPERTY_DAC_BEAN = "propertyDACTarget";

	/** The Constant GET_ACTION_TO_CLONE_FAILED. */
	private static final String GET_ACTION_TO_CLONE_FAILED = "sensus.epm.actionbclimpl.get.action.to.clone.failed";

	/** The Constant APPLY_ACTION_FAILED. */
	private static final String APPLY_ACTION_FAILED =
			"sensus.epm.actionbclimpl.apply.action.failed";

	/** The Constant DATE_RAND_DIVISION. */
	private static final Integer DATE_RAND_DIVISION = 0xFFFFF;

	/**
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return (IProcessBCL)SensusAppContext.getApplicationContext().getBean(PROCESS_BCL_BEAN);
	}

	/**
	 * Gets the schedule bcl.
	 *
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return (IScheduleBCL)SensusAppContext.getApplicationContext().getBean(SCHEDULE_BCL_BEAN);
	}

	@Override
	public InternalResultsResponse<Filial> insertFilial(FilialRequest filialRequest)
	{
		InternalResultsResponse<Filial> response = getFilialDAC().insertFilial(filialRequest);

		if (!response.isInError())
		{
			Filial filial = response.getFirstResult();
			filialRequest.setFilial(filial);

			InternalResultsResponse<Process> processResponse =
					insertProcess(filialRequest, LCActionTypeEnum.INSERT_FILIAL, null);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	@Override
	public InternalResultsResponse<Filial> updateFilial(FilialRequest filialRequest)
	{
		InternalResponse groupResponse = new InternalResponse();

		InternalResultsResponse<Filial> internalResultsResponse = getFilialDAC().updateFilial(filialRequest);

		if (!ValidationUtil.isNull(groupResponse))
		{
			internalResultsResponse.addMessages(groupResponse.getMessageInfoList());
		}

		return internalResultsResponse;
	}

	@Override
	public InternalResponse deleteFilial(FilialRequest filialRequest)
	{

		InternalResultsResponse<Filial> tagResponse =
				(InternalResultsResponse<Filial>)getFilialDAC().deleteFilial(filialRequest);
		InternalResponse response = new InternalResponse();

		if (tagResponse.isInError())
		{
			return response;
		}

		response = getFilialDAC().deleteFilial(filialRequest);

		if (tagResponse.isInError())
		{
			return response;
		}

		Filial filial = tagResponse.getFirstResult();
		filialRequest.setFilial(filial);

		InternalResultsResponse<Process> processResponse =
				insertProcess(filialRequest, LCActionTypeEnum.DELETE_TAG, null);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Filial> fetchAllFilial(InquiryFilialRequest inquiryfilialRequest)
	{
		return getFilialDAC().fetchAllFilial(inquiryfilialRequest);
	}

	@Override
	public InternalResultsResponse<Filial> fetchFilialById(FilialRequest filialRequest)
	{
		return getFilialDAC().fetchFilialById(filialRequest);
	}

	@Override
	public InternalResultsResponse<Filial> fetchAllFilialTypes(InquiryFilialRequest inquiryfilialRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private InternalResultsResponse<Process> insertProcess(FilialRequest filialRequest,
			LCActionTypeEnum lcActionType,
			String processDescription)
	{
		return insertProcess(filialRequest, lcActionType, processDescription, null);
	}

	/**
	 * Insert process.
	 *
	 * @param tagRequest the tag request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(FilialRequest tagRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		Filial filial = tagRequest.getFilial();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(filial.getCodfilial())));
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, filial.getCnpjfilial()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		action.setDescription("INSERT FILIAL");
		Process process = LCHelp.generateProcess(false, action, 0);
		process.setIsProcessComplete(true);
		process.setIsSubmitted(true);
		process.setDescription("insert Filial");
		process.setRniCorrelationId("");


		process.setEndTime(LCDateUtil.getNewDateUTC());

		if (!ValidationUtil.isNullOrEmpty(processDescription))
		{
			process.setDescription("insert Filial");
		}

		ProcessRequest processRequest = createProcessRequest(tagRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Filial> fetchAllEmpresaFilial(InquiryFilialRequest inquiryfilialRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IFilialDAC getFilialDAC() {
		return filialDAC;
	}

	public void setFilialDAC(IFilialDAC filialDAC) {
		this.filialDAC = filialDAC;
	}



}
