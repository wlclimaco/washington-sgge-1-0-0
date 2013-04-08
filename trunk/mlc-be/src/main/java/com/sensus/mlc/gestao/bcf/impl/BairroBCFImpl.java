package com.sensus.mlc.bairro.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.bairro.bcf.IBairroBCF;
import com.sensus.mlc.bairro.bcl.IBairroBCL;
import com.sensus.mlc.bairro.model.Bairro;
import com.sensus.mlc.bairro.model.request.BairroRequest;
import com.sensus.mlc.bairro.model.request.InquiryBairroRequest;
import com.sensus.mlc.bairro.model.response.BairroResponse;
import com.sensus.mlc.bairro.model.response.InquiryBairroResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.bairroBCFImpl;

public class BairroBCFImpl implements IBairroBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(BairroBCFImpl.class); 
 
	private IBairroBCL bairroBCL; // injected by Spring through setter
 
	@Override 
	public BairroResponse insertBairro(BairroRequest bairroRequest)
	{
		BairroResponse response = new BairroResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, bairroRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Bairro> internalResponse = getBairroBCL().insertBairro(bairroRequest); 
				response.setBairro(internalResponse.getResultsList()); 
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
	public BairroResponse updateBairro(BairroRequest bairroRequest)
	{
		BairroResponse response = new BairroResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, bairroRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Bairro> internalResponse = getBairroBCL().updateBairro(bairroRequest);
				response.setBairro(internalResponse.getResultsList());
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
	public BairroResponse deleteBairro(BairroRequest bairroRequest) 
	{  
		BairroResponse response = new BairroResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, bairroRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getBairroBCL().deleteBairro(bairroRequest); 
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
	public InquiryBairroResponse fetchAllBairro(InquiryBairroRequest inquirybairroRequest) 
	{ 
		InquiryBairroResponse response = new InquiryBairroResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Bairro> internalResponse =  
						getBairroBCL().fetchAllBairro(inquirybairroRequest); 
				response.setBairro(internalResponse.getResultsList());
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
	public BairroResponse fetchBairroById(BairroRequest bairroRequest) 
	{ 
		BairroResponse response = new BairroResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, bairroRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Bairro> internalResponse = getBairroBCL().fetchBairroById(bairroRequest); 
				response.setBairro(internalResponse.getResultsList());
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
	public BairroResponse fetchAllBairroFilial(BairroRequest bairroRequest) { 
		BairroResponse response = new BairroResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryBairroRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Bairro> internalResponse =  
						getBairroBCL().fetchAllBairroFilial(bairroRequest);  
				response.setBairro(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IBairroBCL getBairroBCL() 
	{       
		return bairroBCL; 
	}  
    
	public void setBairroBCL(IBairroBCL bairroBCL) 
	{ 
		this.bairroBCL = bairroBCL; 
	} 
}  
