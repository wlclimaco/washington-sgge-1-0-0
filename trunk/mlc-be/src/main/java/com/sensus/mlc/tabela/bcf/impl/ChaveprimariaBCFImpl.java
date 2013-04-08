package com.sensus.mlc.chaveprimaria.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.chaveprimaria.bcf.IChaveprimariaBCF;
import com.sensus.mlc.chaveprimaria.bcl.IChaveprimariaBCL;
import com.sensus.mlc.chaveprimaria.model.Chaveprimaria;
import com.sensus.mlc.chaveprimaria.model.request.ChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.request.InquiryChaveprimariaRequest;
import com.sensus.mlc.chaveprimaria.model.response.ChaveprimariaResponse;
import com.sensus.mlc.chaveprimaria.model.response.InquiryChaveprimariaResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.chaveprimariaBCFImpl;

public class ChaveprimariaBCFImpl implements IChaveprimariaBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(ChaveprimariaBCFImpl.class); 
 
	private IChaveprimariaBCL chaveprimariaBCL; // injected by Spring through setter
 
	@Override 
	public ChaveprimariaResponse insertChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{
		ChaveprimariaResponse response = new ChaveprimariaResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, chaveprimariaRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Chaveprimaria> internalResponse = getChaveprimariaBCL().insertChaveprimaria(chaveprimariaRequest); 
				response.setChaveprimaria(internalResponse.getResultsList()); 
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
	public ChaveprimariaResponse updateChaveprimaria(ChaveprimariaRequest chaveprimariaRequest)
	{
		ChaveprimariaResponse response = new ChaveprimariaResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, chaveprimariaRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Chaveprimaria> internalResponse = getChaveprimariaBCL().updateChaveprimaria(chaveprimariaRequest);
				response.setChaveprimaria(internalResponse.getResultsList());
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
	public ChaveprimariaResponse deleteChaveprimaria(ChaveprimariaRequest chaveprimariaRequest) 
	{  
		ChaveprimariaResponse response = new ChaveprimariaResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, chaveprimariaRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getChaveprimariaBCL().deleteChaveprimaria(chaveprimariaRequest); 
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
	public InquiryChaveprimariaResponse fetchAllChaveprimaria(InquiryChaveprimariaRequest inquirychaveprimariaRequest) 
	{ 
		InquiryChaveprimariaResponse response = new InquiryChaveprimariaResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Chaveprimaria> internalResponse =  
						getChaveprimariaBCL().fetchAllChaveprimaria(inquirychaveprimariaRequest); 
				response.setChaveprimaria(internalResponse.getResultsList());
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
	public ChaveprimariaResponse fetchChaveprimariaById(ChaveprimariaRequest chaveprimariaRequest) 
	{ 
		ChaveprimariaResponse response = new ChaveprimariaResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, chaveprimariaRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Chaveprimaria> internalResponse = getChaveprimariaBCL().fetchChaveprimariaById(chaveprimariaRequest); 
				response.setChaveprimaria(internalResponse.getResultsList());
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
	public ChaveprimariaResponse fetchAllChaveprimariaFilial(ChaveprimariaRequest chaveprimariaRequest) { 
		ChaveprimariaResponse response = new ChaveprimariaResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryChaveprimariaRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Chaveprimaria> internalResponse =  
						getChaveprimariaBCL().fetchAllChaveprimariaFilial(chaveprimariaRequest);  
				response.setChaveprimaria(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IChaveprimariaBCL getChaveprimariaBCL() 
	{       
		return chaveprimariaBCL; 
	}  
    
	public void setChaveprimariaBCL(IChaveprimariaBCL chaveprimariaBCL) 
	{ 
		this.chaveprimariaBCL = chaveprimariaBCL; 
	} 
}  
