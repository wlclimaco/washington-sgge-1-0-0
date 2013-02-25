package com.sensus.mlc.empresa.bcl.impl;

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
import com.sensus.mlc.empresa.bcl.IEmpresaBCL;
import com.sensus.mlc.empresa.dac.IEmpresaDAC;
import com.sensus.mlc.empresa.model.Empresa;
import com.sensus.mlc.empresa.model.request.EmpresaRequest;
import com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest;
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
public class EmpresaBCLImpl implements IEmpresaBCL
{

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(EmpresaBCLImpl.class);

	/** The empresa dac. */
	private IEmpresaDAC empresaDAC; // injected by Spring through setter

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
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest empresaRequest)
	{
		InternalResultsResponse<Empresa> response = getEmpresaDAC().insertEmpresa(empresaRequest);

		if (!response.isInError())
		{
			Empresa empresa = response.getFirstResult();
			empresaRequest.setEmpresa(empresa);

			SearchParameter empresaParameter =
					new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getCodemp()));
			empresaRequest.getSearchLight().addSearchParameter(empresaParameter);
			InternalResultsResponse<Process> processResponse =
					insertProcess(empresaRequest, LCActionTypeEnum.INSERT_EMPRESA, null);
			empresaRequest.getSearchLight().getSearchParameters().remove(empresaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest empresaRequest)
	{
		InternalResponse groupResponse = new InternalResponse();

		InternalResultsResponse<Empresa> internalResultsResponse = getEmpresaDAC().updateEmpresa(empresaRequest);

		if (!ValidationUtil.isNull(groupResponse))
		{
			internalResultsResponse.addMessages(groupResponse.getMessageInfoList());
		}

		return internalResultsResponse;
	}

	@Override
	public InternalResponse deleteEmpresa(EmpresaRequest empresaRequest)
	{

		InternalResultsResponse<Empresa> tagResponse =
				(InternalResultsResponse<Empresa>)getEmpresaDAC().deleteEmpresa(empresaRequest);
		InternalResponse response = new InternalResponse();

		if (tagResponse.isInError())
		{
			return response;
		}

		response = getEmpresaDAC().deleteEmpresa(empresaRequest);

		if (tagResponse.isInError())
		{
			return response;
		}

		Empresa empresa = tagResponse.getFirstResult();
		empresaRequest.setEmpresa(empresa);

		SearchParameter tagParameter =
				new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getCodemp()));
		empresaRequest.getSearchLight().addSearchParameter(tagParameter);

		InternalResultsResponse<Process> processResponse =
				insertProcess(empresaRequest, LCActionTypeEnum.DELETE_TAG, null);
		empresaRequest.getSearchLight().getSearchParameters().remove(tagParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryempresaRequest)
	{
		return getEmpresaDAC().fetchAllEmpresa(inquiryempresaRequest);
	}

	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaById(EmpresaRequest empresaRequest)
	{
		return getEmpresaDAC().fetchEmpresaById(empresaRequest);
	}

	public IEmpresaDAC getEmpresaDAC()
	{
		return empresaDAC;
	}

	public void setEmpresaDAC(IEmpresaDAC empresaDAC)
	{
		this.empresaDAC = empresaDAC;
	}

	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresaTypes(InquiryEmpresaRequest inquiryempresaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private InternalResultsResponse<Process> insertProcess(EmpresaRequest empresaRequest,
			LCActionTypeEnum lcActionType,
			String processDescription)
	{
		return insertProcess(empresaRequest, lcActionType, processDescription, null);
	}

	/**
	 * Insert process.
	 *
	 * @param tagRequest the tag request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EmpresaRequest tagRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		Empresa empresa = tagRequest.getEmpresa();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(empresa.getCodemp())));
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, empresa.getCnpjemp()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		action.setDescription("INSERT EMPRESA");
		Process process = LCHelp.generateProcess(false, action, 0);
		process.setIsProcessComplete(true);
		process.setIsSubmitted(true);
		process.setDescription("insert Empresa");
		process.setRniCorrelationId("");


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
	public InternalResultsResponse<Empresa> fetchAllEmpresaFilial(EmpresaRequest empresaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
