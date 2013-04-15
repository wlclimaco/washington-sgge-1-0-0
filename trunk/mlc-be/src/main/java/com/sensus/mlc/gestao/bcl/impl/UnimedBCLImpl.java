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
import com.sensus.mlc.gestao.bcl.IUnimedBCL;
import com.sensus.mlc.gestao.dac.IUnimedDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Unimed;
import com.sensus.mlc.gestao.model.request.InquiryUnimedRequest;
import com.sensus.mlc.gestao.model.request.UnimedRequest;
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
 * The Class UnimedBCLImpl.
 */
public class UnimedBCLImpl implements IUnimedBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The unimed dac. */
	private IUnimedDAC unimedDAC;



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
	 * Spring Sets the unimed dac.
	 *
	 * @param iUnimedDAC the new unimed dac
	 */
	public void setUnimedDAC(IUnimedDAC iUnimedDAC)
	{
		unimedDAC = iUnimedDAC;
	}

	/**
	 * Gets the unimed dac.
	 *
	 * @return the unimed dac
	 */
	public IUnimedDAC getUnimedDAC()
	{
		return unimedDAC;
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
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#fetchAllUnimeds(InquiryUnimedRequest)
	 */
	@Override
	public InternalResultsResponse<Unimed> fetchAllUnimed(InquiryUnimedRequest inquiryUnimedRequest)
	{
		return getUnimedDAC().fetchAllUnimed(inquiryUnimedRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#fetchUnimedById(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */
	@Override
	public InternalResultsResponse<Unimed> fetchUnimedById(UnimedRequest unimedRequest)
	{
		return getUnimedDAC().fetchUnimedById(unimedRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#insertUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */
	@Override
	public InternalResultsResponse<Unimed> insertUnimed(UnimedRequest unimedRequest)
	{

		InternalResultsResponse<Unimed> response = getUnimedDAC().insertUnimed(unimedRequest);

		if (!response.isInError())
		{
			Unimed unimed = response.getFirstResult();
			unimedRequest.setUnimed(unimed);

			SearchParameter unimedParameter = new SearchParameter(PropertyEnum.UNIMED_ID, String.valueOf(unimed.getCdunidad()));
			unimedRequest.getSearchLight().addSearchParameter(unimedParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(unimedRequest, LCActionTypeEnum.INSERT_TAG, null);
			unimedRequest.getSearchLight().getSearchParameters().remove(unimedParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#deleteUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */
	@Override
	public InternalResponse deleteUnimed(UnimedRequest unimedRequest)
	{
		InternalResultsResponse<Unimed> unimedResponse = getUnimedDAC().fetchUnimedById(unimedRequest);
		InternalResponse response = new InternalResponse();

		if (unimedResponse.isInError())
		{
			response.setStatus(unimedResponse.getStatus());
			response.addMessages(unimedResponse.getMessageInfoList());
			return response;
		}

		response = getUnimedDAC().deleteUnimed(unimedRequest);

		if (response.isInError())
		{
			return response;
		}

		Unimed unimed = unimedResponse.getFirstResult();
		unimedRequest.setUnimed(unimed);

		SearchParameter unimedParameter = new SearchParameter(PropertyEnum.UNIMED_ID, String.valueOf(unimed.getCdunidad()));
		unimedRequest.getSearchLight().addSearchParameter(unimedParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(unimedRequest, LCActionTypeEnum.DELETE_TAG, null);
		unimedRequest.getSearchLight().getSearchParameters().remove(unimedParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcl.IUnimedBCL#updateUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */
	@Override
	public InternalResultsResponse<Unimed> updateUnimed(UnimedRequest unimedRequest)
	{
		InternalResultsResponse<Unimed> response = getUnimedDAC().updateUnimed(unimedRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(unimedRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param unimedRequest the unimed request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(UnimedRequest unimedRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(unimedRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param unimedRequest the unimed request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(UnimedRequest unimedRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(unimedRequest.getUnimed()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Unimed unimed = unimedRequest.getUnimed();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(unimed.getCdunidad())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, unimed.getCdunidad().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(unimedRequest);
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

		ProcessRequest processRequest = createProcessRequest(unimedRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Unimed> fetchAllUnimedTypes(
			InquiryUnimedRequest inquiryunimedRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Unimed> fetchAllUnimedFilial(
			UnimedRequest unimedRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




