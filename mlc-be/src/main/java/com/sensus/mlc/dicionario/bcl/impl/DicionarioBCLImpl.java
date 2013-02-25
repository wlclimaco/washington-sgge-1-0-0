package com.sensus.mlc.dicionario.bcl.impl;

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
import com.sensus.mlc.dicionario.bcl.IDicionarioBCL;
import com.sensus.mlc.dicionario.dac.IDicionarioDAC;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
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
import com.sensus.mlc.smartpoint.model.SearchParameter;

/**
 * The Class GroupBCLImpl.
 *
 * @author Gustavo Aragao - QAT.
 *
 */
public class DicionarioBCLImpl implements IDicionarioBCL
{
	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(DicionarioBCLImpl.class);

	/** The dicionario dac. */
	private IDicionarioDAC dicionarioDAC; // injected by Spring through setter

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
	public InternalResultsResponse<Tela> insertTela(TelaRequest dicionarioRequest)
	{
		InternalResultsResponse<Tela> response = getDicionarioDAC().insertTela(dicionarioRequest);

		if (!response.isInError())
		{
			Tela dicionario = response.getFirstResult();
			dicionarioRequest.setTela((List<Tela>) dicionario);

			SearchParameter dicionarioParameter =
					new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(dicionario.getCodTela()));
			dicionarioRequest.getSearchLight().addSearchParameter(dicionarioParameter);
			InternalResultsResponse<Process> processResponse =
					insertProcess(dicionarioRequest, LCActionTypeEnum.INSERT_TAG, null);
			dicionarioRequest.getSearchLight().getSearchParameters().remove(dicionarioParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	@Override
	public InternalResultsResponse<Tela> updateTela(TelaRequest dicionarioRequest)
	{
		InternalResponse groupResponse = new InternalResponse();

		InternalResultsResponse<Tela> internalResultsResponse = getDicionarioDAC().updateTela(dicionarioRequest);

		if (!ValidationUtil.isNull(groupResponse))
		{
			internalResultsResponse.addMessages(groupResponse.getMessageInfoList());
		}

		return internalResultsResponse;
	}

	@Override
	public InternalResponse deleteTela(TelaRequest dicionarioRequest)
	{

		InternalResultsResponse<Tela> tagResponse =
				(InternalResultsResponse<Tela>)getDicionarioDAC().deleteTela(dicionarioRequest);
		InternalResponse response = new InternalResponse();

		if (tagResponse.isInError())
		{
			return response;
		}

		response = getDicionarioDAC().deleteTela(dicionarioRequest);

		if (tagResponse.isInError())
		{
			return response;
		}

		Tela dicionario = tagResponse.getFirstResult();
		dicionarioRequest.setTela((List<Tela>) dicionario);

		SearchParameter tagParameter =
				new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(dicionario.getCodTela()));
		dicionarioRequest.getSearchLight().addSearchParameter(tagParameter);

		InternalResultsResponse<Process> processResponse =
				insertProcess(dicionarioRequest, LCActionTypeEnum.DELETE_TAG, null);
		dicionarioRequest.getSearchLight().getSearchParameters().remove(tagParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Tela> fetchAllTelas(InquiryTelaRequest inquirydicionarioRequest)
	{
		return getDicionarioDAC().fetchAllTela(inquirydicionarioRequest);
	}

	@Override
	public InternalResultsResponse<Tela> fetchTelaById(TelaRequest dicionarioRequest)
	{
		return getDicionarioDAC().fetchTelaById(dicionarioRequest);
	}

	public IDicionarioDAC getDicionarioDAC()
	{
		return dicionarioDAC;
	}

	public void setDicionarioDAC(IDicionarioDAC dicionarioDAC)
	{
		this.dicionarioDAC = dicionarioDAC;
	}



	private InternalResultsResponse<Process> insertProcess(TelaRequest dicionarioRequest,
			LCActionTypeEnum lcActionType,
			String processDescription)
	{
		return insertProcess(dicionarioRequest, lcActionType, processDescription, null);
	}

	/**
	 * Insert process.
	 *
	 * @param tagRequest the tag request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TelaRequest tagRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		List<Tela> dicionario = tagRequest.getTela();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(dicionario.get(0).getCodTela())));
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, dicionario.get(0).getNmTela()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = LCHelp.generateProcess(false, action, 0);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		if (!ValidationUtil.isNullOrEmpty(processDescription))
		{
			process.setDescription(processDescription);
		}

		ProcessRequest processRequest = createProcessRequest(tagRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

}
