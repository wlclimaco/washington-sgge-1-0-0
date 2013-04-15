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
import com.sensus.mlc.gestao.bcl.IBairroBCL;
import com.sensus.mlc.gestao.dac.IBairroDAC;
import com.sensus.mlc.gestao.model.Bairro;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.request.BairroRequest;
import com.sensus.mlc.gestao.model.request.InquiryBairroRequest;
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
 * The Class BairroBCLImpl.
 */
public class BairroBCLImpl implements IBairroBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The bairro dac. */
	private IBairroDAC bairroDAC;



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
	 * Spring Sets the bairro dac.
	 *
	 * @param iBairroDAC the new bairro dac
	 */
	public void setBairroDAC(IBairroDAC iBairroDAC)
	{
		bairroDAC = iBairroDAC;
	}

	/**
	 * Gets the bairro dac.
	 *
	 * @return the bairro dac
	 */
	public IBairroDAC getBairroDAC()
	{
		return bairroDAC;
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
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#fetchAllBairros(InquiryBairroRequest)
	 */
	@Override
	public InternalResultsResponse<Bairro> fetchAllBairro(InquiryBairroRequest inquiryBairroRequest)
	{
		return getBairroDAC().fetchAllBairro(inquiryBairroRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#fetchBairroById(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResultsResponse<Bairro> fetchBairroById(BairroRequest bairroRequest)
	{
		return getBairroDAC().fetchBairroById(bairroRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#insertBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResultsResponse<Bairro> insertBairro(BairroRequest bairroRequest)
	{

		InternalResultsResponse<Bairro> response = getBairroDAC().insertBairro(bairroRequest);

		if (!response.isInError())
		{
			Bairro bairro = response.getFirstResult();
			bairroRequest.setBairro(bairro);

			SearchParameter bairroParameter = new SearchParameter(PropertyEnum.BAIRRO_ID, String.valueOf(bairro.getCodbairro()));
			bairroRequest.getSearchLight().addSearchParameter(bairroParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(bairroRequest, LCActionTypeEnum.INSERT_TAG, null);
			bairroRequest.getSearchLight().getSearchParameters().remove(bairroParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#deleteBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResponse deleteBairro(BairroRequest bairroRequest)
	{
		InternalResultsResponse<Bairro> bairroResponse = getBairroDAC().fetchBairroById(bairroRequest);
		InternalResponse response = new InternalResponse();

		if (bairroResponse.isInError())
		{
			response.setStatus(bairroResponse.getStatus());
			response.addMessages(bairroResponse.getMessageInfoList());
			return response;
		}

		response = getBairroDAC().deleteBairro(bairroRequest);

		if (response.isInError())
		{
			return response;
		}

		Bairro bairro = bairroResponse.getFirstResult();
		bairroRequest.setBairro(bairro);

		SearchParameter bairroParameter = new SearchParameter(PropertyEnum.BAIRRO_ID, String.valueOf(bairro.getCodbairro()));
		bairroRequest.getSearchLight().addSearchParameter(bairroParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(bairroRequest, LCActionTypeEnum.DELETE_TAG, null);
		bairroRequest.getSearchLight().getSearchParameters().remove(bairroParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcl.IBairroBCL#updateBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public InternalResultsResponse<Bairro> updateBairro(BairroRequest bairroRequest)
	{
		InternalResultsResponse<Bairro> response = getBairroDAC().updateBairro(bairroRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(bairroRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param bairroRequest the bairro request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(BairroRequest bairroRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(bairroRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param bairroRequest the bairro request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(BairroRequest bairroRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(bairroRequest.getBairro()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Bairro bairro = bairroRequest.getBairro();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(bairro.getCodbairro())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, bairro.getCodbairro().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(bairroRequest);
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

		ProcessRequest processRequest = createProcessRequest(bairroRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Bairro> fetchAllBairroTypes(
			InquiryBairroRequest inquirybairroRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Bairro> fetchAllBairroFilial(
			BairroRequest bairroRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




