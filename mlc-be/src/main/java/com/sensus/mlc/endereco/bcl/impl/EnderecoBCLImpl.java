package com.sensus.mlc.endereco.bcl.impl;

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
import com.sensus.mlc.endereco.bcl.IEnderecoBCL;
import com.sensus.mlc.endereco.dac.IEnderecoDAC;
import com.sensus.mlc.endereco.model.Endereco;
import com.sensus.mlc.endereco.model.request.EnderecoRequest;
import com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest;
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
 * Action BCL implementation class.
 *
 * @author QAT.
 */
public class EnderecoBCLImpl implements IEnderecoBCL
{

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(EnderecoBCLImpl.class);

	/** The endereco dac. */
	private IEnderecoDAC enderecoDAC; // injected by Spring through setter

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
	public InternalResultsResponse<Endereco> insertEndereco(EnderecoRequest enderecoRequest)
	{
		InternalResultsResponse<Endereco> response = getEnderecoDAC().insertEndereco(enderecoRequest);

		if (!response.isInError())
		{
			Endereco endereco = response.getFirstResult();
			enderecoRequest.setEndereco(endereco);

			SearchParameter enderecoParameter =
					new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(endereco.getCodend()));
			enderecoRequest.getSearchLight().addSearchParameter(enderecoParameter);
			InternalResultsResponse<Process> processResponse =
					insertProcess(enderecoRequest, LCActionTypeEnum.INSERT_TAG, null);
			enderecoRequest.getSearchLight().getSearchParameters().remove(enderecoParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	@Override
	public InternalResultsResponse<Endereco> updateEndereco(EnderecoRequest enderecoRequest)
	{
		InternalResponse groupResponse = new InternalResponse();

		InternalResultsResponse<Endereco> internalResultsResponse = getEnderecoDAC().updateEndereco(enderecoRequest);

		if (!ValidationUtil.isNull(groupResponse))
		{
			internalResultsResponse.addMessages(groupResponse.getMessageInfoList());
		}

		return internalResultsResponse;
	}

	@Override
	public InternalResponse deleteEndereco(EnderecoRequest enderecoRequest)
	{

		InternalResultsResponse<Endereco> tagResponse =
				(InternalResultsResponse<Endereco>)getEnderecoDAC().deleteEndereco(enderecoRequest);
		InternalResponse response = new InternalResponse();

		if (tagResponse.isInError())
		{
			return response;
		}

		response = getEnderecoDAC().deleteEndereco(enderecoRequest);

		if (tagResponse.isInError())
		{
			return response;
		}

		Endereco endereco = tagResponse.getFirstResult();
		enderecoRequest.setEndereco(endereco);

		SearchParameter tagParameter =
				new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(endereco.getCodend()));
		enderecoRequest.getSearchLight().addSearchParameter(tagParameter);

		InternalResultsResponse<Process> processResponse =
				insertProcess(enderecoRequest, LCActionTypeEnum.DELETE_TAG, null);
		enderecoRequest.getSearchLight().getSearchParameters().remove(tagParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Endereco> fetchAllEndereco(InquiryEnderecoRequest inquiryenderecoRequest)
	{
		return getEnderecoDAC().fetchAllEndereco(inquiryenderecoRequest);
	}

	@Override
	public InternalResultsResponse<Endereco> fetchEnderecoById(EnderecoRequest enderecoRequest)
	{
		return getEnderecoDAC().fetchEnderecoById(enderecoRequest);
	}

	public IEnderecoDAC getEnderecoDAC()
	{
		return enderecoDAC;
	}

	public void setEnderecoDAC(IEnderecoDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	@Override
	public InternalResultsResponse<Endereco> fetchAllEnderecoTypes(InquiryEnderecoRequest inquiryenderecoRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private InternalResultsResponse<Process> insertProcess(EnderecoRequest enderecoRequest,
			LCActionTypeEnum lcActionType,
			String processDescription)
	{
		return insertProcess(enderecoRequest, lcActionType, processDescription, null);
	}

	/**
	 * Insert process.
	 *
	 * @param tagRequest the tag request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EnderecoRequest tagRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		Endereco endereco = tagRequest.getEndereco();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(endereco.getCodend())));
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, endereco.getEndereco()));

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

	@Override
	public InternalResultsResponse<Endereco> fetchAllEnderecoFilial(InquiryEnderecoRequest inquiryenderecoRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
