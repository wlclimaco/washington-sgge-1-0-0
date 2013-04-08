package com.sensus.mlc.pais.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.pais.bcf.IPaisBCF;
import com.sensus.mlc.pais.bcl.IPaisBCL;
import com.sensus.mlc.pais.model.Pais;
import com.sensus.mlc.pais.model.request.PaisRequest;
import com.sensus.mlc.pais.model.request.InquiryPaisRequest;
import com.sensus.mlc.pais.model.response.PaisResponse;
import com.sensus.mlc.pais.model.response.InquiryPaisResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.paisBCFImpl;

public class PaisBCFImpl implements IPaisBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(PaisBCFImpl.class); 
 
	private IPaisBCL paisBCL; // injected by Spring through setter
 
	@Override 
	public PaisResponse insertPais(PaisRequest paisRequest)
	{
		PaisResponse response = new PaisResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, paisRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Pais> internalResponse = getPaisBCL().insertPais(paisRequest); 
				response.setPais(internalResponse.getResultsList()); 
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
	public PaisResponse updatePais(PaisRequest paisRequest)
	{
		PaisResponse response = new PaisResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, paisRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Pais> internalResponse = getPaisBCL().updatePais(paisRequest);
				response.setPais(internalResponse.getResultsList());
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
	public PaisResponse deletePais(PaisRequest paisRequest) 
	{  
		PaisResponse response = new PaisResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, paisRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getPaisBCL().deletePais(paisRequest); 
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
	public InquiryPaisResponse fetchAllPais(InquiryPaisRequest inquirypaisRequest) 
	{ 
		InquiryPaisResponse response = new InquiryPaisResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Pais> internalResponse =  
						getPaisBCL().fetchAllPais(inquirypaisRequest); 
				response.setPais(internalResponse.getResultsList());
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
	public PaisResponse fetchPaisById(PaisRequest paisRequest) 
	{ 
		PaisResponse response = new PaisResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, paisRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Pais> internalResponse = getPaisBCL().fetchPaisById(paisRequest); 
				response.setPais(internalResponse.getResultsList());
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
	public PaisResponse fetchAllPaisFilial(PaisRequest paisRequest) { 
		PaisResponse response = new PaisResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryPaisRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Pais> internalResponse =  
						getPaisBCL().fetchAllPaisFilial(paisRequest);  
				response.setPais(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IPaisBCL getPaisBCL() 
	{       
		return paisBCL; 
	}  
    
	public void setPaisBCL(IPaisBCL paisBCL) 
	{ 
		this.paisBCL = paisBCL; 
	} 
}  
