package com.sensus.mlc.gestao.bcl.impl;

import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.gestao.bcl.IEmpresaBCL;
import com.sensus.mlc.gestao.dac.IEmpresaDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.request.EmpresaRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

/**
 * The Class EmpresaBCLImpl.
 */
public class EmpresaBCLImpl implements IEmpresaBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The empresa dac. */
	private IEmpresaDAC empresaDAC;



	/**  The process bcl. */
	private IProcessBCL processBCL;

	/**  The lc help. */
	private LCHelp lcHelp;

	/**  The Constant LIGH_ALREADY_IN_THE_EMPRESA. */
	private static final String LIGHT_ALREADY_IN_EMPRESA = "sensus.mlc.empresavalidator.light.already.exist";

	/**  The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA = "sensus.mlc.mlc_action.add_smp_to_empresa";

	/**  The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_EMPRESA_BY_POLE_ID =
			"sensus.mlc.mlc_action.add_smp_to_empresa_by_poleid";

	/**  The Constant SMARTPOINT_BCL_BEAN. */
	private static final String SMARTPOINT_ACCESSOR_BCL_BEAN = "smartPointAccessorBCLTarget";

	/**  The Constant EMPRESA_BCL_BEAN. */
	private static final String EMPRESA_BCL_BEAN = "empresaBCLTarget";

	/**
	 * Gets the lc help.
	 *
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
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
	 * Spring Sets the empresa dac.
	 *
	 * @param iEmpresaDAC the new empresa dac
	 */
	public void setEmpresaDAC(IEmpresaDAC iEmpresaDAC)
	{
		empresaDAC = iEmpresaDAC;
	}

	/**
	 * Gets the empresa dac.
	 *
	 * @return the empresa dac
	 */
	public IEmpresaDAC getEmpresaDAC()
	{
		return empresaDAC;
	}

	/**
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
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
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#fetchAllEmpresas(InquiryEmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest)
	{
		return getEmpresaDAC().fetchAllEmpresa(inquiryEmpresaRequest);
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
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#insertEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaRequest empresaRequest)
	{

		InternalResultsResponse<Empresa> response = getEmpresaDAC().insertEmpresa(empresaRequest);

		if (!response.isInError())
		{
			Empresa empresa = response.getFirstResult();
			empresaRequest.setEmpresa(empresa);

			SearchParameter empresaParameter = new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getCodemp()));
			empresaRequest.getSearchLight().addSearchParameter(empresaParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(empresaRequest, LCActionTypeEnum.INSERT_EMPRESA, null);
			empresaRequest.getSearchLight().getSearchParameters().remove(empresaParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#deleteEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public InternalResponse deleteEmpresa(EmpresaRequest empresaRequest)
	{
		InternalResultsResponse<Empresa> empresaResponse = getEmpresaDAC().fetchEmpresaById(empresaRequest);
		InternalResponse response = new InternalResponse();

		if (empresaResponse.isInError())
		{
			response.setStatus(empresaResponse.getStatus());
			response.addMessages(empresaResponse.getMessageInfoList());
			return response;
		}

		response = getEmpresaDAC().deleteEmpresa(empresaRequest);

		if (response.isInError())
		{
			return response;
		}

		Empresa empresa = empresaResponse.getFirstResult();
		empresaRequest.setEmpresa(empresa);

		SearchParameter empresaParameter = new SearchParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getCodemp()));
		empresaRequest.getSearchLight().addSearchParameter(empresaParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(empresaRequest, LCActionTypeEnum.DELETE_TAG, null);
		empresaRequest.getSearchLight().getSearchParameters().remove(empresaParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.bcl.IEmpresaBCL#updateEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaRequest empresaRequest)
	{
		InternalResultsResponse<Empresa> response = getEmpresaDAC().updateEmpresa(empresaRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(empresaRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param empresaRequest the empresa request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EmpresaRequest empresaRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(empresaRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param empresaRequest the empresa request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(EmpresaRequest empresaRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(empresaRequest.getEmpresa()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Empresa empresa = empresaRequest.getEmpresa();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(empresa.getCodemp())));
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, empresa.getCodemp().toString()));

		InquiryLightRequest lightRequest = createInquiryLightRequest(empresaRequest);
		Integer lightAmount = 1;

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = LCHelp.generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		if (!ValidationUtil.isNullOrEmpty(processDescription))
		{
			process.setDescription(processDescription);
		}

		ProcessRequest processRequest = createProcessRequest(empresaRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresaTypes(
			InquiryEmpresaRequest inquiryempresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Empresa> fetchAllEmpresaFilial(
			EmpresaRequest empresaRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




