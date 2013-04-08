package com.sensus.mlc.uf.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.uf.bcf.IUfBCF;
import com.sensus.mlc.uf.bcl.IUfBCL;
import com.sensus.mlc.uf.model.Uf;
import com.sensus.mlc.uf.model.request.UfRequest;
import com.sensus.mlc.uf.model.request.InquiryUfRequest;
import com.sensus.mlc.uf.model.response.UfResponse;
import com.sensus.mlc.uf.model.response.InquiryUfResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.ufBCFImpl;

public class UfBCFImpl implements IUfBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(UfBCFImpl.class); 
 
	private IUfBCL ufBCL; // injected by Spring through setter
 
	@Override 
	public UfResponse insertUf(UfRequest ufRequest)
	{
		UfResponse response = new UfResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, ufRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Uf> internalResponse = getUfBCL().insertUf(ufRequest); 
				response.setUf(internalResponse.getResultsList()); 
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
	public UfResponse updateUf(UfRequest ufRequest)
	{
		UfResponse response = new UfResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, ufRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Uf> internalResponse = getUfBCL().updateUf(ufRequest);
				response.setUf(internalResponse.getResultsList());
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
	public UfResponse deleteUf(UfRequest ufRequest) 
	{  
		UfResponse response = new UfResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, ufRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getUfBCL().deleteUf(ufRequest); 
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
	public InquiryUfResponse fetchAllUf(InquiryUfRequest inquiryufRequest) 
	{ 
		InquiryUfResponse response = new InquiryUfResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Uf> internalResponse =  
						getUfBCL().fetchAllUf(inquiryufRequest); 
				response.setUf(internalResponse.getResultsList());
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
	public UfResponse fetchUfById(UfRequest ufRequest) 
	{ 
		UfResponse response = new UfResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, ufRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Uf> internalResponse = getUfBCL().fetchUfById(ufRequest); 
				response.setUf(internalResponse.getResultsList());
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
	public UfResponse fetchAllUfFilial(UfRequest ufRequest) { 
		UfResponse response = new UfResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryUfRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Uf> internalResponse =  
						getUfBCL().fetchAllUfFilial(ufRequest);  
				response.setUf(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IUfBCL getUfBCL() 
	{       
		return ufBCL; 
	}  
    
	public void setUfBCL(IUfBCL ufBCL) 
	{ 
		this.ufBCL = ufBCL; 
	} 
}  
