package com.sensus.mlc.cnae.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.cnae.bcf.ICnaeBCF;
import com.sensus.mlc.cnae.bcl.ICnaeBCL;
import com.sensus.mlc.cnae.model.Cnae;
import com.sensus.mlc.cnae.model.request.CnaeRequest;
import com.sensus.mlc.cnae.model.request.InquiryCnaeRequest;
import com.sensus.mlc.cnae.model.response.CnaeResponse;
import com.sensus.mlc.cnae.model.response.InquiryCnaeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.cnaeBCFImpl;

public class CnaeBCFImpl implements ICnaeBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(CnaeBCFImpl.class); 
 
	private ICnaeBCL cnaeBCL; // injected by Spring through setter
 
	@Override 
	public CnaeResponse insertCnae(CnaeRequest cnaeRequest)
	{
		CnaeResponse response = new CnaeResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, cnaeRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().insertCnae(cnaeRequest); 
				response.setCnae(internalResponse.getResultsList()); 
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
	public CnaeResponse updateCnae(CnaeRequest cnaeRequest)
	{
		CnaeResponse response = new CnaeResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, cnaeRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().updateCnae(cnaeRequest);
				response.setCnae(internalResponse.getResultsList());
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
	public CnaeResponse deleteCnae(CnaeRequest cnaeRequest) 
	{  
		CnaeResponse response = new CnaeResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, cnaeRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getCnaeBCL().deleteCnae(cnaeRequest); 
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
	public InquiryCnaeResponse fetchAllCnae(InquiryCnaeRequest inquirycnaeRequest) 
	{ 
		InquiryCnaeResponse response = new InquiryCnaeResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Cnae> internalResponse =  
						getCnaeBCL().fetchAllCnae(inquirycnaeRequest); 
				response.setCnae(internalResponse.getResultsList());
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
	public CnaeResponse fetchCnaeById(CnaeRequest cnaeRequest) 
	{ 
		CnaeResponse response = new CnaeResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, cnaeRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().fetchCnaeById(cnaeRequest); 
				response.setCnae(internalResponse.getResultsList());
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
	public CnaeResponse fetchAllCnaeFilial(CnaeRequest cnaeRequest) { 
		CnaeResponse response = new CnaeResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryCnaeRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Cnae> internalResponse =  
						getCnaeBCL().fetchAllCnaeFilial(cnaeRequest);  
				response.setCnae(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public ICnaeBCL getCnaeBCL() 
	{       
		return cnaeBCL; 
	}  
    
	public void setCnaeBCL(ICnaeBCL cnaeBCL) 
	{ 
		this.cnaeBCL = cnaeBCL; 
	} 
}  
