package com.sensus.mlc.embalagens.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.embalagens.bcf.IEmbalagensBCF;
import com.sensus.mlc.embalagens.bcl.IEmbalagensBCL;
import com.sensus.mlc.embalagens.model.Embalagens;
import com.sensus.mlc.embalagens.model.request.EmbalagensRequest;
import com.sensus.mlc.embalagens.model.request.InquiryEmbalagensRequest;
import com.sensus.mlc.embalagens.model.response.EmbalagensResponse;
import com.sensus.mlc.embalagens.model.response.InquiryEmbalagensResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.embalagensBCFImpl;

public class EmbalagensBCFImpl implements IEmbalagensBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(EmbalagensBCFImpl.class); 
 
	private IEmbalagensBCL embalagensBCL; // injected by Spring through setter
 
	@Override 
	public EmbalagensResponse insertEmbalagens(EmbalagensRequest embalagensRequest)
	{
		EmbalagensResponse response = new EmbalagensResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, embalagensRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Embalagens> internalResponse = getEmbalagensBCL().insertEmbalagens(embalagensRequest); 
				response.setEmbalagens(internalResponse.getResultsList()); 
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
	public EmbalagensResponse updateEmbalagens(EmbalagensRequest embalagensRequest)
	{
		EmbalagensResponse response = new EmbalagensResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, embalagensRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Embalagens> internalResponse = getEmbalagensBCL().updateEmbalagens(embalagensRequest);
				response.setEmbalagens(internalResponse.getResultsList());
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
	public EmbalagensResponse deleteEmbalagens(EmbalagensRequest embalagensRequest) 
	{  
		EmbalagensResponse response = new EmbalagensResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, embalagensRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getEmbalagensBCL().deleteEmbalagens(embalagensRequest); 
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
	public InquiryEmbalagensResponse fetchAllEmbalagens(InquiryEmbalagensRequest inquiryembalagensRequest) 
	{ 
		InquiryEmbalagensResponse response = new InquiryEmbalagensResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Embalagens> internalResponse =  
						getEmbalagensBCL().fetchAllEmbalagens(inquiryembalagensRequest); 
				response.setEmbalagens(internalResponse.getResultsList());
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
	public EmbalagensResponse fetchEmbalagensById(EmbalagensRequest embalagensRequest) 
	{ 
		EmbalagensResponse response = new EmbalagensResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, embalagensRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Embalagens> internalResponse = getEmbalagensBCL().fetchEmbalagensById(embalagensRequest); 
				response.setEmbalagens(internalResponse.getResultsList());
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
	public EmbalagensResponse fetchAllEmbalagensFilial(EmbalagensRequest embalagensRequest) { 
		EmbalagensResponse response = new EmbalagensResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryEmbalagensRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Embalagens> internalResponse =  
						getEmbalagensBCL().fetchAllEmbalagensFilial(embalagensRequest);  
				response.setEmbalagens(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IEmbalagensBCL getEmbalagensBCL() 
	{       
		return embalagensBCL; 
	}  
    
	public void setEmbalagensBCL(IEmbalagensBCL embalagensBCL) 
	{ 
		this.embalagensBCL = embalagensBCL; 
	} 
}  
