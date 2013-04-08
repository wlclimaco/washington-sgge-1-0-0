package com.sensus.mlc.gestao.bcl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.unimed.bcf.IUnimedBCF;
import com.sensus.mlc.unimed.bcl.IUnimedBCL;
import com.sensus.mlc.unimed.model.Unimed;
import com.sensus.mlc.unimed.model.request.UnimedRequest;
import com.sensus.mlc.unimed.model.request.InquiryUnimedRequest;
import com.sensus.mlc.unimed.model.response.UnimedResponse;
import com.sensus.mlc.unimed.model.response.InquiryUnimedResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.unimedBCFImpl;

public class UnimedBCFImpl implements IUnimedBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(UnimedBCFImpl.class); 
 
	private IUnimedBCL unimedBCL; // injected by Spring through setter
 
	@Override 
	public UnimedResponse insertUnimed(UnimedRequest unimedRequest)
	{
		UnimedResponse response = new UnimedResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, unimedRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Unimed> internalResponse = getUnimedBCL().insertUnimed(unimedRequest); 
				response.setUnimed(internalResponse.getResultsList()); 
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
	public UnimedResponse updateUnimed(UnimedRequest unimedRequest)
	{
		UnimedResponse response = new UnimedResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, unimedRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Unimed> internalResponse = getUnimedBCL().updateUnimed(unimedRequest);
				response.setUnimed(internalResponse.getResultsList());
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
	public UnimedResponse deleteUnimed(UnimedRequest unimedRequest) 
	{  
		UnimedResponse response = new UnimedResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, unimedRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getUnimedBCL().deleteUnimed(unimedRequest); 
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
	public InquiryUnimedResponse fetchAllUnimed(InquiryUnimedRequest inquiryunimedRequest) 
	{ 
		InquiryUnimedResponse response = new InquiryUnimedResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Unimed> internalResponse =  
						getUnimedBCL().fetchAllUnimed(inquiryunimedRequest); 
				response.setUnimed(internalResponse.getResultsList());
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
	public UnimedResponse fetchUnimedById(UnimedRequest unimedRequest) 
	{ 
		UnimedResponse response = new UnimedResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, unimedRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Unimed> internalResponse = getUnimedBCL().fetchUnimedById(unimedRequest); 
				response.setUnimed(internalResponse.getResultsList());
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
	public UnimedResponse fetchAllUnimedFilial(UnimedRequest unimedRequest) { 
		UnimedResponse response = new UnimedResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryUnimedRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Unimed> internalResponse =  
						getUnimedBCL().fetchAllUnimedFilial(unimedRequest);  
				response.setUnimed(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IUnimedBCL getUnimedBCL() 
	{       
		return unimedBCL; 
	}  
    
	public void setUnimedBCL(IUnimedBCL unimedBCL) 
	{ 
		this.unimedBCL = unimedBCL; 
	} 
}  
