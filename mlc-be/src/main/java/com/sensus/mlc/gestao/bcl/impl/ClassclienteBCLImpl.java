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
import com.sensus.mlc.gestao.bcl.IClassclienteBCL;
import com.sensus.mlc.gestao.dac.IClassclienteDAC;
import com.sensus.mlc.gestao.model.Classcliente;
import com.sensus.mlc.gestao.model.request.ClassclienteRequest;
import com.sensus.mlc.gestao.model.request.InquiryClassclienteRequest;
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
 * The Class ClassclienteBCLImpl.
 */
public class ClassclienteBCLImpl implements IClassclienteBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The Classcliente dac. */
	private IClassclienteDAC ClassclienteDAC;



	/**  The process bcl. */
	private IProcessBCL processBCL;

	/**  The lc help. */
	private LCHelp lcHelp;


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
	 * Spring Sets the Classcliente dac.
	 *
	 * @param iClassclienteDAC the new Classcliente dac
	 */
	public void setClassclienteDAC(IClassclienteDAC iClassclienteDAC)
	{
		ClassclienteDAC = iClassclienteDAC;
	}

	/**
	 * Gets the Classcliente dac.
	 *
	 * @return the Classcliente dac
	 */
	public IClassclienteDAC getClassclienteDAC()
	{
		return ClassclienteDAC;
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
	 * @see com.sensus.mlc.Classcliente.bcl.IClassclienteBCL#fetchAllClassclientes(InquiryClassclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Classcliente> fetchAllClasscliente(InquiryClassclienteRequest inquiryClassclienteRequest)
	{
		return getClassclienteDAC().fetchAllClasscliente(inquiryClassclienteRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Classcliente.bcl.IClassclienteBCL#fetchClassclienteById(com.sensus.mlc.Classcliente.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Classcliente> fetchClassclienteById(ClassclienteRequest ClassclienteRequest)
	{
		return getClassclienteDAC().fetchClassclienteById(ClassclienteRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Classcliente.bcl.IClassclienteBCL#insertClasscliente(com.sensus.mlc.Classcliente.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Classcliente> insertClasscliente(ClassclienteRequest ClassclienteRequest)
	{

		InternalResultsResponse<Classcliente> response = getClassclienteDAC().insertClasscliente(ClassclienteRequest);

		if (!response.isInError())
		{
			Classcliente Classcliente = response.getFirstResult();
			ClassclienteRequest.setClasscliente(Classcliente);

			SearchParameter ClassclienteParameter = new SearchParameter(PropertyEnum.CLASSCLIENTE_ID, String.valueOf(Classcliente.getCodclascli()));
			ClassclienteRequest.getSearchLight().addSearchParameter(ClassclienteParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(ClassclienteRequest, LCActionTypeEnum.INSERT_FILIAL, null);
			ClassclienteRequest.getSearchLight().getSearchParameters().remove(ClassclienteParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Classcliente.bcl.IClassclienteBCL#deleteClasscliente(com.sensus.mlc.Classcliente.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResponse deleteClasscliente(ClassclienteRequest ClassclienteRequest)
	{
		InternalResultsResponse<Classcliente> ClassclienteResponse = getClassclienteDAC().fetchClassclienteById(ClassclienteRequest);
		InternalResponse response = new InternalResponse();

		if (ClassclienteResponse.isInError())
		{
			response.setStatus(ClassclienteResponse.getStatus());
			response.addMessages(ClassclienteResponse.getMessageInfoList());
			return response;
		}

		response = getClassclienteDAC().deleteClasscliente(ClassclienteRequest);

		if (response.isInError())
		{
			return response;
		}

		Classcliente Classcliente = ClassclienteResponse.getFirstResult();
		ClassclienteRequest.setClasscliente(Classcliente);

		SearchParameter ClassclienteParameter = new SearchParameter(PropertyEnum.CLASSCLIENTE_ID, String.valueOf(Classcliente.getCodclascli()));
		ClassclienteRequest.getSearchLight().addSearchParameter(ClassclienteParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(ClassclienteRequest, LCActionTypeEnum.DELETE_TAG, null);
		ClassclienteRequest.getSearchLight().getSearchParameters().remove(ClassclienteParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Classcliente.bcl.IClassclienteBCL#updateClasscliente(com.sensus.mlc.Classcliente.model.request.ClassclienteRequest)
	 */
	@Override
	public InternalResultsResponse<Classcliente> updateClasscliente(ClassclienteRequest ClassclienteRequest)
	{
		InternalResultsResponse<Classcliente> response = getClassclienteDAC().updateClasscliente(ClassclienteRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(ClassclienteRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param ClassclienteRequest the Classcliente request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(ClassclienteRequest ClassclienteRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(ClassclienteRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param ClassclienteRequest the Classcliente request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(ClassclienteRequest ClassclienteRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(ClassclienteRequest.getClasscliente()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Classcliente Classcliente = ClassclienteRequest.getClasscliente();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(Classcliente.getCodclascli())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, Classcliente.getCodclascli().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(ClassclienteRequest);
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

		ProcessRequest processRequest = createProcessRequest(ClassclienteRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Classcliente> fetchAllClassclienteTypes(
			InquiryClassclienteRequest inquiryclassclienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Classcliente> fetchAllClassclienteFilial(
			ClassclienteRequest classclienteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}




