Memo1

package com.sensus.mlc.UnidMed.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.UnidMed.bcf.IUnidmedBCF;
import com.sensus.mlc.UnidMed.bcl.IUnidmedBCL;
import com.sensus.mlc.UnidMed.model.Unidmed;
import com.sensus.mlc.UnidMed.model.request.UnidmedRequest;
import com.sensus.mlc.UnidMed.model.request.InquiryUnidmedRequest;
import com.sensus.mlc.UnidMed.model.response.UnidmedResponse;
import com.sensus.mlc.UnidMed.model.response.InquiryUnidmedResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.UnidMedBCFImpl;

public class UnidmedBCFImpl implements IUnidmedBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(UnidMedBCFImpl.class); 
 
	private IUnidmedBCL UnidMedBCL; // injected by Spring through setter
 
	@Override 
	public UnidmedResponse insertUnidmed(UnidmedRequest UnidMedRequest)
	{
		UnidmedResponse response = new UnidmedResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, UnidMedRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Unidmed> internalResponse = getUnidmedBCL().insertUnidmed(UnidMedRequest); 
				response.setUnidmed(internalResponse.getResultsList()); 
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
	public UnidmedResponse updateUnidmed(UnidmedRequest UnidMedRequest)
	{
		UnidmedResponse response = new UnidmedResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, UnidMedRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Unidmed> internalResponse = getUnidmedBCL().updateUnidmed(UnidMedRequest);
				response.setUnidmed(internalResponse.getResultsList());
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
	public UnidmedResponse deleteUnidmed(UnidmedRequest UnidMedRequest) 
	{  
		UnidmedResponse response = new UnidmedResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, UnidMedRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getUnidmedBCL().deleteUnidmed(UnidMedRequest); 
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
	public InquiryUnidmedResponse fetchAllUnidmed(InquiryUnidmedRequest inquiryUnidMedRequest) 
	{ 
		InquiryUnidmedResponse response = new InquiryUnidmedResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Unidmed> internalResponse =  
						getUnidmedBCL().fetchAllUnidmed(inquiryUnidMedRequest); 
				response.setUnidmed(internalResponse.getResultsList());
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
	public UnidmedResponse fetchUnidmedById(UnidmedRequest UnidMedRequest) 
	{ 
		UnidmedResponse response = new UnidmedResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, UnidMedRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Unidmed> internalResponse = getUnidmedBCL().fetchUnidmedById(UnidMedRequest); 
				response.setUnidmed(internalResponse.getResultsList());
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
	public UnidmedResponse fetchAllUnidmedFilial(UnidmedRequest UnidMedRequest) { 
		UnidmedResponse response = new UnidmedResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryUnidmedRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Unidmed> internalResponse =  
						getUnidmedBCL().fetchAllUnidmedFilial(UnidMedRequest);  
				response.setUnidmed(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IUnidmedBCL getUnidmedBCL() 
	{       
		return UnidMedBCL; 
	}  
    
	public void setUnidmedBCL(IUnidmedBCL UnidMedBCL) 
	{ 
		this.UnidMedBCL = UnidMedBCL; 
	} 
}  
