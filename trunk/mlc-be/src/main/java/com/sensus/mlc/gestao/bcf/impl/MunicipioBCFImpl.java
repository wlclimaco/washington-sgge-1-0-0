package com.sensus.mlc.municipio.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.municipio.bcf.IMunicipioBCF;
import com.sensus.mlc.municipio.bcl.IMunicipioBCL;
import com.sensus.mlc.municipio.model.Municipio;
import com.sensus.mlc.municipio.model.request.MunicipioRequest;
import com.sensus.mlc.municipio.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.municipio.model.response.MunicipioResponse;
import com.sensus.mlc.municipio.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.municipioBCFImpl;

public class MunicipioBCFImpl implements IMunicipioBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(MunicipioBCFImpl.class); 
 
	private IMunicipioBCL municipioBCL; // injected by Spring through setter
 
	@Override 
	public MunicipioResponse insertMunicipio(MunicipioRequest municipioRequest)
	{
		MunicipioResponse response = new MunicipioResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, municipioRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().insertMunicipio(municipioRequest); 
				response.setMunicipio(internalResponse.getResultsList()); 
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
	public MunicipioResponse updateMunicipio(MunicipioRequest municipioRequest)
	{
		MunicipioResponse response = new MunicipioResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, municipioRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().updateMunicipio(municipioRequest);
				response.setMunicipio(internalResponse.getResultsList());
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
	public MunicipioResponse deleteMunicipio(MunicipioRequest municipioRequest) 
	{  
		MunicipioResponse response = new MunicipioResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, municipioRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getMunicipioBCL().deleteMunicipio(municipioRequest); 
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
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquirymunicipioRequest) 
	{ 
		InquiryMunicipioResponse response = new InquiryMunicipioResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Municipio> internalResponse =  
						getMunicipioBCL().fetchAllMunicipio(inquirymunicipioRequest); 
				response.setMunicipio(internalResponse.getResultsList());
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
	public MunicipioResponse fetchMunicipioById(MunicipioRequest municipioRequest) 
	{ 
		MunicipioResponse response = new MunicipioResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, municipioRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().fetchMunicipioById(municipioRequest); 
				response.setMunicipio(internalResponse.getResultsList());
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
	public MunicipioResponse fetchAllMunicipioFilial(MunicipioRequest municipioRequest) { 
		MunicipioResponse response = new MunicipioResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryMunicipioRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Municipio> internalResponse =  
						getMunicipioBCL().fetchAllMunicipioFilial(municipioRequest);  
				response.setMunicipio(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IMunicipioBCL getMunicipioBCL() 
	{       
		return municipioBCL; 
	}  
    
	public void setMunicipioBCL(IMunicipioBCL municipioBCL) 
	{ 
		this.municipioBCL = municipioBCL; 
	} 
}  
