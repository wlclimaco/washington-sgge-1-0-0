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
import com.sensus.mlc.gestao.bcl.IMunicipioBCL;
import com.sensus.mlc.gestao.dac.IMunicipioDAC;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.Municipio;
import com.sensus.mlc.gestao.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.gestao.model.request.MunicipioRequest;
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
 * The Class MunicipioBCLImpl.
 */
public class MunicipioBCLImpl implements IMunicipioBCL
{

	/**  The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/**  The municipio dac. */
	private IMunicipioDAC municipioDAC;



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
	 * Spring Sets the municipio dac.
	 *
	 * @param iMunicipioDAC the new municipio dac
	 */
	public void setMunicipioDAC(IMunicipioDAC iMunicipioDAC)
	{
		municipioDAC = iMunicipioDAC;
	}

	/**
	 * Gets the municipio dac.
	 *
	 * @return the municipio dac
	 */
	public IMunicipioDAC getMunicipioDAC()
	{
		return municipioDAC;
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
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#fetchAllMunicipios(InquiryMunicipioRequest)
	 */
	@Override
	public InternalResultsResponse<Municipio> fetchAllMunicipio(InquiryMunicipioRequest inquiryMunicipioRequest)
	{
		return getMunicipioDAC().fetchAllMunicipio(inquiryMunicipioRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#fetchMunicipioById(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */
	@Override
	public InternalResultsResponse<Municipio> fetchMunicipioById(MunicipioRequest municipioRequest)
	{
		return getMunicipioDAC().fetchMunicipioById(municipioRequest);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#insertMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */
	@Override
	public InternalResultsResponse<Municipio> insertMunicipio(MunicipioRequest municipioRequest)
	{

		InternalResultsResponse<Municipio> response = getMunicipioDAC().insertMunicipio(municipioRequest);

		if (!response.isInError())
		{
			Municipio municipio = response.getFirstResult();
			municipioRequest.setMunicipio(municipio);

			SearchParameter municipioParameter = new SearchParameter(PropertyEnum.MUNICIPIO_ID, String.valueOf(municipio.getCodmunic()));
			municipioRequest.getSearchLight().addSearchParameter(municipioParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(municipioRequest, LCActionTypeEnum.INSERT_TAG, null);
			municipioRequest.getSearchLight().getSearchParameters().remove(municipioParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#deleteMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */
	@Override
	public InternalResponse deleteMunicipio(MunicipioRequest municipioRequest)
	{
		InternalResultsResponse<Municipio> municipioResponse = getMunicipioDAC().fetchMunicipioById(municipioRequest);
		InternalResponse response = new InternalResponse();

		if (municipioResponse.isInError())
		{
			response.setStatus(municipioResponse.getStatus());
			response.addMessages(municipioResponse.getMessageInfoList());
			return response;
		}

		response = getMunicipioDAC().deleteMunicipio(municipioRequest);

		if (response.isInError())
		{
			return response;
		}

		Municipio municipio = municipioResponse.getFirstResult();
		municipioRequest.setMunicipio(municipio);

		SearchParameter municipioParameter = new SearchParameter(PropertyEnum.MUNICIPIO_ID, String.valueOf(municipio.getCodmunic()));
		municipioRequest.getSearchLight().addSearchParameter(municipioParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(municipioRequest, LCActionTypeEnum.DELETE_TAG, null);
		municipioRequest.getSearchLight().getSearchParameters().remove(municipioParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcl.IMunicipioBCL#updateMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */
	@Override
	public InternalResultsResponse<Municipio> updateMunicipio(MunicipioRequest municipioRequest)
	{
		InternalResultsResponse<Municipio> response = getMunicipioDAC().updateMunicipio(municipioRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(municipioRequest, LCActionTypeEnum.UPDATE_GROUP, null);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/**
	 * Insert process.
	 *
	 * @param municipioRequest the municipio request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(MunicipioRequest municipioRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(municipioRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param municipioRequest the municipio request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(MunicipioRequest municipioRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(municipioRequest.getMunicipio()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Municipio municipio = municipioRequest.getMunicipio();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, String.valueOf(municipio.getCodmunic())));
   actionParameters.add(new LCActionParameter(PropertyEnum.EMPRESA_ID, municipio.getCodmunic().toString()));
   InquiryLightRequest lightRequest = createInquiryLightRequest(municipioRequest);
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

		ProcessRequest processRequest = createProcessRequest(municipioRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}

	@Override
	public InternalResultsResponse<Municipio> fetchAllMunicipioTypes(
			InquiryMunicipioRequest inquirymunicipioRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalResultsResponse<Municipio> fetchAllMunicipioFilial(
			MunicipioRequest municipioRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}




