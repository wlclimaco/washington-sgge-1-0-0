package com.sensus.mlc.chaveestrangeira.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.chaveestrangeira.bcf.IChaveestrangeiraBCF;
import com.sensus.mlc.chaveestrangeira.bcl.IChaveestrangeiraBCL;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira;
import com.sensus.mlc.chaveestrangeira.model.request.ChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.request.InquiryChaveestrangeiraRequest;
import com.sensus.mlc.chaveestrangeira.model.response.ChaveestrangeiraResponse;
import com.sensus.mlc.chaveestrangeira.model.response.InquiryChaveestrangeiraResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.chaveestrangeiraBCFImpl;

public class ChaveestrangeiraBCFImpl implements IChaveestrangeiraBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(ChaveestrangeiraBCFImpl.class); 
 
	private IChaveestrangeiraBCL chaveestrangeiraBCL; // injected by Spring through setter
 
	@Override 
	public ChaveestrangeiraResponse insertChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, chaveestrangeiraRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Chaveestrangeira> internalResponse = getChaveestrangeiraBCL().insertChaveestrangeira(chaveestrangeiraRequest); 
				response.setChaveestrangeira(internalResponse.getResultsList()); 
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
	public ChaveestrangeiraResponse updateChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest)
	{
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, chaveestrangeiraRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Chaveestrangeira> internalResponse = getChaveestrangeiraBCL().updateChaveestrangeira(chaveestrangeiraRequest);
				response.setChaveestrangeira(internalResponse.getResultsList());
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
	public ChaveestrangeiraResponse deleteChaveestrangeira(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{  
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, chaveestrangeiraRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getChaveestrangeiraBCL().deleteChaveestrangeira(chaveestrangeiraRequest); 
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
	public InquiryChaveestrangeiraResponse fetchAllChaveestrangeira(InquiryChaveestrangeiraRequest inquirychaveestrangeiraRequest) 
	{ 
		InquiryChaveestrangeiraResponse response = new InquiryChaveestrangeiraResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Chaveestrangeira> internalResponse =  
						getChaveestrangeiraBCL().fetchAllChaveestrangeira(inquirychaveestrangeiraRequest); 
				response.setChaveestrangeira(internalResponse.getResultsList());
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
	public ChaveestrangeiraResponse fetchChaveestrangeiraById(ChaveestrangeiraRequest chaveestrangeiraRequest) 
	{ 
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, chaveestrangeiraRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Chaveestrangeira> internalResponse = 
getChaveestrangeiraBCL().fetchChaveestrangeiraById(chaveestrangeiraRequest); 
				response.setChaveestrangeira(internalResponse.getResultsList());
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
	public ChaveestrangeiraResponse fetchAllChaveestrangeiraFilial(ChaveestrangeiraRequest chaveestrangeiraRequest) { 
		ChaveestrangeiraResponse response = new ChaveestrangeiraResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryChaveestrangeiraRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Chaveestrangeira> internalResponse =  
						getChaveestrangeiraBCL().fetchAllChaveestrangeiraFilial(chaveestrangeiraRequest);  
				response.setChaveestrangeira(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IChaveestrangeiraBCL getChaveestrangeiraBCL() 
	{       
		return chaveestrangeiraBCL; 
	}  
    
	public void setChaveestrangeiraBCL(IChaveestrangeiraBCL chaveestrangeiraBCL) 
	{ 
		this.chaveestrangeiraBCL = chaveestrangeiraBCL; 
	} 
}  
