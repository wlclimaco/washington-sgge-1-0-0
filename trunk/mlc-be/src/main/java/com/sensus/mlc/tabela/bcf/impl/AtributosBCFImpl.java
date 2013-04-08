package com.sensus.mlc.tabela.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.tabela.bcf.IAtributosBCF;
import com.sensus.mlc.tabela.bcl.IAtributosBCL;
import com.sensus.mlc.tabela.model.Atributos;
import com.sensus.mlc.tabela.model.request.AtributosRequest;
import com.sensus.mlc.tabela.model.request.InquiryAtributosRequest;
import com.sensus.mlc.tabela.model.response.AtributosResponse;
import com.sensus.mlc.tabela.model.response.InquiryAtributosResponse;

public class AtributosBCFImpl implements IAtributosBCF
{

	/** The Constant NAME_LENGTH. */
	private static final Integer NAME_LENGTH = 255;

	/** The Constant SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID. */
	private static final String SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID =
			"sensus.epm.actionvalidator.name.invalid";

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception";

	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BCL_EXCEPTION_MSG = "sensus.mlc.groupbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AtributosBCFImpl.class);

	private IAtributosBCL atributosBCL; // injected by Spring through setter

	@Override
	public AtributosResponse insertAtributos(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();

		try
		{

			if (LCHelp.checkValidation(response, atributosRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Atributos> internalResponse = getAtributosBCL().insertAtributos(atributosRequest);
				response.setAtributos(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public AtributosResponse updateAtributos(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();
		try
		{
			if (LCHelp.checkValidation(response, atributosRequest, MLCPersistanceActionEnum.UPDATE))
			{
				InternalResultsResponse<Atributos> internalResponse = getAtributosBCL().updateAtributos(atributosRequest);
				response.setAtributos(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public AtributosResponse deleteAtributos(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();
		try
		{
			if (LCHelp.checkValidation(response, atributosRequest, MLCPersistanceActionEnum.DELETE))
			{
				InternalResponse internalResponse = getAtributosBCL().deleteAtributos(atributosRequest);
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public InquiryAtributosResponse fetchAllAtributos(InquiryAtributosRequest inquiryatributosRequest)
	{
		InquiryAtributosResponse response = new InquiryAtributosResponse();
		try
		{

				InternalResultsResponse<Atributos> internalResponse =
						getAtributosBCL().fetchAllAtributos(inquiryatributosRequest);
				response.setAtributos(internalResponse.getResultsList());
				response.setResultsSetInfo(internalResponse.getResultsSetInfo());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public AtributosResponse fetchAtributosById(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();
		try
		{
			if (LCHelp.checkValidation(response, atributosRequest, MLCPersistanceActionEnum.FETCH_BY_ID))
			{
				InternalResultsResponse<Atributos> internalResponse = getAtributosBCL().fetchAtributosById(atributosRequest);
				response.setAtributos(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public AtributosResponse fetchAllAtributosFilial(AtributosRequest atributosRequest) {
		AtributosResponse response = new AtributosResponse();
		try
		{
			if (LCHelp.checkValidation(response, inquiryAtributosRequest, MLCPersistanceActionEnum.FETCH))
			{
				InternalResultsResponse<Atributos> internalResponse =
						getAtributosBCL().fetchAllAtributosFilial(atributosRequest);
				response.setAtributos(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}


	public IAtributosBCL getAtributosBCL()
	{
		return atributosBCL;
	}

	public void setAtributosBCL(IAtributosBCL atributosBCL)
	{
		this.atributosBCL = atributosBCL;
	}
}
