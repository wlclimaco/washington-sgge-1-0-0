package com.sensus.mlc.tabela.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.tabela.bcf.IDominiosBCF;
import com.sensus.mlc.tabela.bcl.IDominiosBCL;
import com.sensus.mlc.tabela.model.Dominios;
import com.sensus.mlc.tabela.model.request.DominiosRequest;
import com.sensus.mlc.tabela.model.request.InquiryDominiosRequest;
import com.sensus.mlc.tabela.model.response.DominiosResponse;
import com.sensus.mlc.tabela.model.response.InquiryDominiosResponse;

public class DominiosBCFImpl implements IDominiosBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(DominiosBCFImpl.class);

	private IDominiosBCL dominiosBCL; // injected by Spring through setter

	@Override
	public DominiosResponse insertDominios(DominiosRequest dominiosRequest)
	{
		DominiosResponse response = new DominiosResponse();

		try
		{

			if (LCHelp.checkValidation(response, dominiosRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Dominios> internalResponse = getDominiosBCL().insertDominios(dominiosRequest);
				response.setDominioss(internalResponse.getResultsList());
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
	public DominiosResponse updateDominios(DominiosRequest dominiosRequest)
	{
		DominiosResponse response = new DominiosResponse();
		try
		{
			if (LCHelp.checkValidation(response, dominiosRequest, MLCPersistanceActionEnum.UPDATE))
			{
				InternalResultsResponse<Dominios> internalResponse = getDominiosBCL().updateDominios(dominiosRequest);
				response.setDominioss(internalResponse.getResultsList());
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
	public DominiosResponse deleteDominios(DominiosRequest dominiosRequest)
	{
		DominiosResponse response = new DominiosResponse();
		try
		{
			if (LCHelp.checkValidation(response, dominiosRequest, MLCPersistanceActionEnum.DELETE))
			{
				InternalResponse internalResponse = getDominiosBCL().deleteDominios(dominiosRequest);
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
	public InquiryDominiosResponse fetchAllDominios(InquiryDominiosRequest inquirydominiosRequest)
	{
		InquiryDominiosResponse response = new InquiryDominiosResponse();
		try
		{

				InternalResultsResponse<Dominios> internalResponse =
						getDominiosBCL().fetchAllDominios(inquirydominiosRequest);
				response.setDominios(internalResponse.getResultsList());
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
	public DominiosResponse fetchDominiosById(DominiosRequest dominiosRequest)
	{
		DominiosResponse response = new DominiosResponse();
		try
		{
			if (LCHelp.checkValidation(response, dominiosRequest, MLCPersistanceActionEnum.FETCH_BY_ID))
			{
				InternalResultsResponse<Dominios> internalResponse = getDominiosBCL().fetchDominiosById(dominiosRequest);
				response.setDominioss(internalResponse.getResultsList());
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
	public DominiosResponse fetchAllDominiosFilial(DominiosRequest dominiosRequest) {
		DominiosResponse response = new DominiosResponse();
		try
		{
			if (LCHelp.checkValidation(response, inquiryDominiosRequest, MLCPersistanceActionEnum.FETCH))
			{
				InternalResultsResponse<Dominios> internalResponse =
						getDominiosBCL().fetchAllDominiosFilial(dominiosRequest);
				response.setDominioss(internalResponse.getResultsList());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}


	public IDominiosBCL getDominiosBCL()
	{
		return dominiosBCL;
	}

	public void setDominiosBCL(IDominiosBCL dominiosBCL)
	{
		this.dominiosBCL = dominiosBCL;
	}
}
