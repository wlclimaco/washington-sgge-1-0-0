package com.sensus.mlc.dicionario.bcf.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.dicionario.bcf.IDicionarioBCF;
import com.sensus.mlc.dicionario.bcl.IDicionarioBCL;
import com.sensus.mlc.dicionario.model.Tela;
import com.sensus.mlc.dicionario.model.request.InquiryTelaRequest;
import com.sensus.mlc.dicionario.model.request.TelaRequest;
import com.sensus.mlc.dicionario.model.response.InquiryTelaResponse;
import com.sensus.mlc.dicionario.model.response.TelaResponse;
import com.sensus.mlc.tag.bcf.impl.TagBCFImpl;



public class DicionarioBCFImpl implements IDicionarioBCF
{

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_GROUP_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception";

	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_GROUP_BCL_EXCEPTION_MSG = "sensus.mlc.groupbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TagBCFImpl.class);

	/** The group bcl. */
	private IDicionarioBCL dicionarioBCL; // injected by Spring through setter


	/** The Constant NAME_LENGTH. */
	private static final Integer NAME_LENGTH = 255;

	/** The Constant SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID. */
	private static final String SENSUS_EPM_ACTIONVALIDATOR_NAME_INVALID =
			"sensus.epm.actionvalidator.name.invalid";

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception";

	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BCL_EXCEPTION_MSG = "sensus.mlc.groupbclimpl.defaultexception";


	private IDicionarioBCL telaBCL; // injected by Spring through setter

	@Override
	public TelaResponse insertTela(TelaRequest telaRequest)
	{
		TelaResponse response = new TelaResponse();

		try
		{
		//	if (LCHelp.checkValidation(response, telaRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
		//	{
				InternalResultsResponse<Tela> internalResponse = getDicionarioBCL().insertTela(telaRequest);
				response.setTela((List<Tela>) internalResponse.getFirstResult());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);
		//	}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCL_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public TelaResponse updateTela(TelaRequest telaRequest)
	{
		TelaResponse response = new TelaResponse();
		try
		{
		//	if (LCHelp.checkValidation(response, telaRequest, MLCPersistanceActionEnum.UPDATE))
			{
				InternalResultsResponse<Tela> internalResponse = getDicionarioBCL().updateTela(telaRequest);
				response.setTela((List<Tela>) internalResponse.getFirstResult());
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
	public TelaResponse deleteTela(TelaRequest telaRequest)
	{
		TelaResponse response = new TelaResponse();
		try
		{

				InternalResponse internalResponse = getDicionarioBCL().deleteTela(telaRequest);
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public InquiryTelaResponse fetchAllTelas(InquiryTelaRequest inquirytelaRequest)
	{
		InquiryTelaResponse response = new InquiryTelaResponse();
		try
		{

				InternalResultsResponse<Tela> internalResponse =
						getDicionarioBCL().fetchAllTelas(inquirytelaRequest);
				response.setTela(internalResponse.getResultsList());
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
	public TelaResponse fetchTelaById(TelaRequest telaRequest)
	{
		TelaResponse response = new TelaResponse();
		try
		{
		//	if (LCHelp.checkValidation(response, telaRequest, MLCPersistanceActionEnum.FETCH_BY_ID))
		//	{
				InternalResultsResponse<Tela> internalResponse = getDicionarioBCL().fetchTelaById(telaRequest);
				response.setTela((List<Tela>) internalResponse.getFirstResult());
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		//	}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}



	public IDicionarioBCL getDicionarioBCL()
	{
		return telaBCL;
	}

	public void setDicionarioBCL(IDicionarioBCL telaBCL)
	{
		this.telaBCL = telaBCL;
	}

}
