Memo1

package com.sensus.mlc.CNAE.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.CNAE.bcf.ICnaeBCF;
import com.sensus.mlc.CNAE.bcl.ICnaeBCL;
import com.sensus.mlc.CNAE.model.Cnae;
import com.sensus.mlc.CNAE.model.request.CnaeRequest;
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest;
import com.sensus.mlc.CNAE.model.response.CnaeResponse;
import com.sensus.mlc.CNAE.model.response.InquiryCnaeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.CNAEBCFImpl;

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
	private static final Logger LOG = LoggerFactory.getLogger(CNAEBCFImpl.class); 
 
	private ICnaeBCL CNAEBCL; // injected by Spring through setter
 
	@Override 
	public CnaeResponse insertCnae(CnaeRequest CNAERequest)
	{
		CnaeResponse response = new CnaeResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, CNAERequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().insertCnae(CNAERequest); 
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
	public CnaeResponse updateCnae(CnaeRequest CNAERequest)
	{
		CnaeResponse response = new CnaeResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, CNAERequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().updateCnae(CNAERequest);
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
	public CnaeResponse deleteCnae(CnaeRequest CNAERequest) 
	{  
		CnaeResponse response = new CnaeResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, CNAERequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getCnaeBCL().deleteCnae(CNAERequest); 
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
	public InquiryCnaeResponse fetchAllCnae(InquiryCnaeRequest inquiryCNAERequest) 
	{ 
		InquiryCnaeResponse response = new InquiryCnaeResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Cnae> internalResponse =  
						getCnaeBCL().fetchAllCnae(inquiryCNAERequest); 
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
	public CnaeResponse fetchCnaeById(CnaeRequest CNAERequest) 
	{ 
		CnaeResponse response = new CnaeResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, CNAERequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().fetchCnaeById(CNAERequest); 
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
	public CnaeResponse fetchAllCnaeFilial(CnaeRequest CNAERequest) { 
		CnaeResponse response = new CnaeResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryCnaeRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Cnae> internalResponse =  
						getCnaeBCL().fetchAllCnaeFilial(CNAERequest);  
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
		return CNAEBCL; 
	}  
    
	public void setCnaeBCL(ICnaeBCL CNAEBCL) 
	{ 
		this.CNAEBCL = CNAEBCL; 
	} 
}  

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
	private static final Logger LOG = LoggerFactory.getLogger(bairroBCFImpl.class); 
 
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

package com.sensus.mlc.MUNICIPIO.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.MUNICIPIO.bcf.IMunicipioBCF;
import com.sensus.mlc.MUNICIPIO.bcl.IMunicipioBCL;
import com.sensus.mlc.MUNICIPIO.model.Municipio;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.MUNICIPIOBCFImpl;

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
	private static final Logger LOG = LoggerFactory.getLogger(MUNICIPIOBCFImpl.class); 
 
	private IMunicipioBCL MUNICIPIOBCL; // injected by Spring through setter
 
	@Override 
	public MunicipioResponse insertMunicipio(MunicipioRequest MUNICIPIORequest)
	{
		MunicipioResponse response = new MunicipioResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, MUNICIPIORequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().insertMunicipio(MUNICIPIORequest); 
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
	public MunicipioResponse updateMunicipio(MunicipioRequest MUNICIPIORequest)
	{
		MunicipioResponse response = new MunicipioResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, MUNICIPIORequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().updateMunicipio(MUNICIPIORequest);
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
	public MunicipioResponse deleteMunicipio(MunicipioRequest MUNICIPIORequest) 
	{  
		MunicipioResponse response = new MunicipioResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, MUNICIPIORequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getMunicipioBCL().deleteMunicipio(MUNICIPIORequest); 
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
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquiryMUNICIPIORequest) 
	{ 
		InquiryMunicipioResponse response = new InquiryMunicipioResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Municipio> internalResponse =  
						getMunicipioBCL().fetchAllMunicipio(inquiryMUNICIPIORequest); 
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
	public MunicipioResponse fetchMunicipioById(MunicipioRequest MUNICIPIORequest) 
	{ 
		MunicipioResponse response = new MunicipioResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, MUNICIPIORequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().fetchMunicipioById(MUNICIPIORequest); 
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
	public MunicipioResponse fetchAllMunicipioFilial(MunicipioRequest MUNICIPIORequest) { 
		MunicipioResponse response = new MunicipioResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryMunicipioRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Municipio> internalResponse =  
						getMunicipioBCL().fetchAllMunicipioFilial(MUNICIPIORequest);  
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
		return MUNICIPIOBCL; 
	}  
    
	public void setMunicipioBCL(IMunicipioBCL MUNICIPIOBCL) 
	{ 
		this.MUNICIPIOBCL = MUNICIPIOBCL; 
	} 
}  

package com.sensus.mlc.MUNICIPIO.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.MUNICIPIO.bcf.IMunicipioBCF;
import com.sensus.mlc.MUNICIPIO.bcl.IMunicipioBCL;
import com.sensus.mlc.MUNICIPIO.model.Municipio;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.MUNICIPIOBCFImpl;

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
	private static final Logger LOG = LoggerFactory.getLogger(MUNICIPIOBCFImpl.class); 
 
	private IMunicipioBCL MUNICIPIOBCL; // injected by Spring through setter
 
	@Override 
	public MunicipioResponse insertMunicipio(MunicipioRequest MUNICIPIORequest)
	{
		MunicipioResponse response = new MunicipioResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, MUNICIPIORequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().insertMunicipio(MUNICIPIORequest); 
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
	public MunicipioResponse updateMunicipio(MunicipioRequest MUNICIPIORequest)
	{
		MunicipioResponse response = new MunicipioResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, MUNICIPIORequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().updateMunicipio(MUNICIPIORequest);
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
	public MunicipioResponse deleteMunicipio(MunicipioRequest MUNICIPIORequest) 
	{  
		MunicipioResponse response = new MunicipioResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, MUNICIPIORequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getMunicipioBCL().deleteMunicipio(MUNICIPIORequest); 
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
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquiryMUNICIPIORequest) 
	{ 
		InquiryMunicipioResponse response = new InquiryMunicipioResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Municipio> internalResponse =  
						getMunicipioBCL().fetchAllMunicipio(inquiryMUNICIPIORequest); 
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
	public MunicipioResponse fetchMunicipioById(MunicipioRequest MUNICIPIORequest) 
	{ 
		MunicipioResponse response = new MunicipioResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, MUNICIPIORequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Municipio> internalResponse = getMunicipioBCL().fetchMunicipioById(MUNICIPIORequest); 
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
	public MunicipioResponse fetchAllMunicipioFilial(MunicipioRequest MUNICIPIORequest) { 
		MunicipioResponse response = new MunicipioResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryMunicipioRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Municipio> internalResponse =  
						getMunicipioBCL().fetchAllMunicipioFilial(MUNICIPIORequest);  
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
		return MUNICIPIOBCL; 
	}  
    
	public void setMunicipioBCL(IMunicipioBCL MUNICIPIOBCL) 
	{ 
		this.MUNICIPIOBCL = MUNICIPIOBCL; 
	} 
}  

package com.sensus.mlc.UF.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.UF.bcf.IUfBCF;
import com.sensus.mlc.UF.bcl.IUfBCL;
import com.sensus.mlc.UF.model.Uf;
import com.sensus.mlc.UF.model.request.UfRequest;
import com.sensus.mlc.UF.model.request.InquiryUfRequest;
import com.sensus.mlc.UF.model.response.UfResponse;
import com.sensus.mlc.UF.model.response.InquiryUfResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.UFBCFImpl;

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
	private static final Logger LOG = LoggerFactory.getLogger(UFBCFImpl.class); 
 
	private IUfBCL UFBCL; // injected by Spring through setter
 
	@Override 
	public UfResponse insertUf(UfRequest UFRequest)
	{
		UfResponse response = new UfResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, UFRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Uf> internalResponse = getUfBCL().insertUf(UFRequest); 
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
	public UfResponse updateUf(UfRequest UFRequest)
	{
		UfResponse response = new UfResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, UFRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Uf> internalResponse = getUfBCL().updateUf(UFRequest);
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
	public UfResponse deleteUf(UfRequest UFRequest) 
	{  
		UfResponse response = new UfResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, UFRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getUfBCL().deleteUf(UFRequest); 
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
	public InquiryUfResponse fetchAllUf(InquiryUfRequest inquiryUFRequest) 
	{ 
		InquiryUfResponse response = new InquiryUfResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Uf> internalResponse =  
						getUfBCL().fetchAllUf(inquiryUFRequest); 
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
	public UfResponse fetchUfById(UfRequest UFRequest) 
	{ 
		UfResponse response = new UfResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, UFRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Uf> internalResponse = getUfBCL().fetchUfById(UFRequest); 
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
	public UfResponse fetchAllUfFilial(UfRequest UFRequest) { 
		UfResponse response = new UfResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryUfRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Uf> internalResponse =  
						getUfBCL().fetchAllUfFilial(UFRequest);  
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
		return UFBCL; 
	}  
    
	public void setUfBCL(IUfBCL UFBCL) 
	{ 
		this.UFBCL = UFBCL; 
	} 
}  

package com.sensus.mlc.CNAE.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.CNAE.bcf.ICnaeBCF;
import com.sensus.mlc.CNAE.bcl.ICnaeBCL;
import com.sensus.mlc.CNAE.model.Cnae;
import com.sensus.mlc.CNAE.model.request.CnaeRequest;
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest;
import com.sensus.mlc.CNAE.model.response.CnaeResponse;
import com.sensus.mlc.CNAE.model.response.InquiryCnaeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.CNAEBCFImpl;

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
	private static final Logger LOG = LoggerFactory.getLogger(CNAEBCFImpl.class); 
 
	private ICnaeBCL CNAEBCL; // injected by Spring through setter
 
	@Override 
	public CnaeResponse insertCnae(CnaeRequest CNAERequest)
	{
		CnaeResponse response = new CnaeResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, CNAERequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().insertCnae(CNAERequest); 
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
	public CnaeResponse updateCnae(CnaeRequest CNAERequest)
	{
		CnaeResponse response = new CnaeResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, CNAERequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().updateCnae(CNAERequest);
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
	public CnaeResponse deleteCnae(CnaeRequest CNAERequest) 
	{  
		CnaeResponse response = new CnaeResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, CNAERequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getCnaeBCL().deleteCnae(CNAERequest); 
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
	public InquiryCnaeResponse fetchAllCnae(InquiryCnaeRequest inquiryCNAERequest) 
	{ 
		InquiryCnaeResponse response = new InquiryCnaeResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Cnae> internalResponse =  
						getCnaeBCL().fetchAllCnae(inquiryCNAERequest); 
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
	public CnaeResponse fetchCnaeById(CnaeRequest CNAERequest) 
	{ 
		CnaeResponse response = new CnaeResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, CNAERequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Cnae> internalResponse = getCnaeBCL().fetchCnaeById(CNAERequest); 
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
	public CnaeResponse fetchAllCnaeFilial(CnaeRequest CNAERequest) { 
		CnaeResponse response = new CnaeResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryCnaeRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Cnae> internalResponse =  
						getCnaeBCL().fetchAllCnaeFilial(CNAERequest);  
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
		return CNAEBCL; 
	}  
    
	public void setCnaeBCL(ICnaeBCL CNAEBCL) 
	{ 
		this.CNAEBCL = CNAEBCL; 
	} 
}  

package com.sensus.mlc.BAIRRO.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.BAIRRO.bcf.IBairroBCF;
import com.sensus.mlc.BAIRRO.bcl.IBairroBCL;
import com.sensus.mlc.BAIRRO.model.Bairro;
import com.sensus.mlc.BAIRRO.model.request.BairroRequest;
import com.sensus.mlc.BAIRRO.model.request.InquiryBairroRequest;
import com.sensus.mlc.BAIRRO.model.response.BairroResponse;
import com.sensus.mlc.BAIRRO.model.response.InquiryBairroResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.BAIRROBCFImpl;

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
	private static final Logger LOG = LoggerFactory.getLogger(BAIRROBCFImpl.class); 
 
	private IBairroBCL BAIRROBCL; // injected by Spring through setter
 
	@Override 
	public BairroResponse insertBairro(BairroRequest BAIRRORequest)
	{
		BairroResponse response = new BairroResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, BAIRRORequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Bairro> internalResponse = getBairroBCL().insertBairro(BAIRRORequest); 
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
	public BairroResponse updateBairro(BairroRequest BAIRRORequest)
	{
		BairroResponse response = new BairroResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, BAIRRORequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Bairro> internalResponse = getBairroBCL().updateBairro(BAIRRORequest);
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
	public BairroResponse deleteBairro(BairroRequest BAIRRORequest) 
	{  
		BairroResponse response = new BairroResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, BAIRRORequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getBairroBCL().deleteBairro(BAIRRORequest); 
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
	public InquiryBairroResponse fetchAllBairro(InquiryBairroRequest inquiryBAIRRORequest) 
	{ 
		InquiryBairroResponse response = new InquiryBairroResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Bairro> internalResponse =  
						getBairroBCL().fetchAllBairro(inquiryBAIRRORequest); 
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
	public BairroResponse fetchBairroById(BairroRequest BAIRRORequest) 
	{ 
		BairroResponse response = new BairroResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, BAIRRORequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Bairro> internalResponse = getBairroBCL().fetchBairroById(BAIRRORequest); 
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
	public BairroResponse fetchAllBairroFilial(BairroRequest BAIRRORequest) { 
		BairroResponse response = new BairroResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryBairroRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Bairro> internalResponse =  
						getBairroBCL().fetchAllBairroFilial(BAIRRORequest);  
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
		return BAIRROBCL; 
	}  
    
	public void setBairroBCL(IBairroBCL BAIRROBCL) 
	{ 
		this.BAIRROBCL = BAIRROBCL; 
	} 
}  
