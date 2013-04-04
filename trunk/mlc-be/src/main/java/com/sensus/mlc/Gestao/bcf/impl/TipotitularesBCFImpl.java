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

package com.sensus.mlc.ClassTitulares.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.ClassTitulares.bcf.IClasstitularesBCF;
import com.sensus.mlc.ClassTitulares.bcl.IClasstitularesBCL;
import com.sensus.mlc.ClassTitulares.model.Classtitulares;
import com.sensus.mlc.ClassTitulares.model.request.ClasstitularesRequest;
import com.sensus.mlc.ClassTitulares.model.request.InquiryClasstitularesRequest;
import com.sensus.mlc.ClassTitulares.model.response.ClasstitularesResponse;
import com.sensus.mlc.ClassTitulares.model.response.InquiryClasstitularesResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.ClassTitularesBCFImpl;

public class ClasstitularesBCFImpl implements IClasstitularesBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(ClassTitularesBCFImpl.class); 
 
	private IClasstitularesBCL ClassTitularesBCL; // injected by Spring through setter
 
	@Override 
	public ClasstitularesResponse insertClasstitulares(ClasstitularesRequest ClassTitularesRequest)
	{
		ClasstitularesResponse response = new ClasstitularesResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, ClassTitularesRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Classtitulares> internalResponse = getClasstitularesBCL().insertClasstitulares(ClassTitularesRequest); 
				response.setClasstitulares(internalResponse.getResultsList()); 
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
	public ClasstitularesResponse updateClasstitulares(ClasstitularesRequest ClassTitularesRequest)
	{
		ClasstitularesResponse response = new ClasstitularesResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, ClassTitularesRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Classtitulares> internalResponse = getClasstitularesBCL().updateClasstitulares(ClassTitularesRequest);
				response.setClasstitulares(internalResponse.getResultsList());
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
	public ClasstitularesResponse deleteClasstitulares(ClasstitularesRequest ClassTitularesRequest) 
	{  
		ClasstitularesResponse response = new ClasstitularesResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, ClassTitularesRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getClasstitularesBCL().deleteClasstitulares(ClassTitularesRequest); 
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
	public InquiryClasstitularesResponse fetchAllClasstitulares(InquiryClasstitularesRequest inquiryClassTitularesRequest) 
	{ 
		InquiryClasstitularesResponse response = new InquiryClasstitularesResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Classtitulares> internalResponse =  
						getClasstitularesBCL().fetchAllClasstitulares(inquiryClassTitularesRequest); 
				response.setClasstitulares(internalResponse.getResultsList());
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
	public ClasstitularesResponse fetchClasstitularesById(ClasstitularesRequest ClassTitularesRequest) 
	{ 
		ClasstitularesResponse response = new ClasstitularesResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, ClassTitularesRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Classtitulares> internalResponse = getClasstitularesBCL().fetchClasstitularesById(ClassTitularesRequest); 
				response.setClasstitulares(internalResponse.getResultsList());
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
	public ClasstitularesResponse fetchAllClasstitularesFilial(ClasstitularesRequest ClassTitularesRequest) { 
		ClasstitularesResponse response = new ClasstitularesResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryClasstitularesRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Classtitulares> internalResponse =  
						getClasstitularesBCL().fetchAllClasstitularesFilial(ClassTitularesRequest);  
				response.setClasstitulares(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public IClasstitularesBCL getClasstitularesBCL() 
	{       
		return ClassTitularesBCL; 
	}  
    
	public void setClasstitularesBCL(IClasstitularesBCL ClassTitularesBCL) 
	{ 
		this.ClassTitularesBCL = ClassTitularesBCL; 
	} 
}  

package com.sensus.mlc.TipoTitulares.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.request.Request;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.TipoTitulares.bcf.ITipotitularesBCF;
import com.sensus.mlc.TipoTitulares.bcl.ITipotitularesBCL;
import com.sensus.mlc.TipoTitulares.model.Tipotitulares;
import com.sensus.mlc.TipoTitulares.model.request.TipotitularesRequest;
import com.sensus.mlc.TipoTitulares.model.request.InquiryTipotitularesRequest;
import com.sensus.mlc.TipoTitulares.model.response.TipotitularesResponse;
import com.sensus.mlc.TipoTitulares.model.response.InquiryTipotitularesResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.tag.bcf.impl.TipoTitularesBCFImpl;

public class TipotitularesBCFImpl implements ITipotitularesBCF
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
	private static final Logger LOG = LoggerFactory.getLogger(TipoTitularesBCFImpl.class); 
 
	private ITipotitularesBCL TipoTitularesBCL; // injected by Spring through setter
 
	@Override 
	public TipotitularesResponse insertTipotitulares(TipotitularesRequest TipoTitularesRequest)
	{
		TipotitularesResponse response = new TipotitularesResponse();
 
		try 
		{ 
     
			if (LCHelp.checkValidation(response, TipoTitularesRequest, new Object[] {MLCPersistanceActionEnum.INSERT}))
			{
				InternalResultsResponse<Tipotitulares> internalResponse = getTipotitularesBCL().insertTipotitulares(TipoTitularesRequest); 
				response.setTipotitulares(internalResponse.getResultsList()); 
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
	public TipotitularesResponse updateTipotitulares(TipotitularesRequest TipoTitularesRequest)
	{
		TipotitularesResponse response = new TipotitularesResponse();
		try 
		{ 
			if (LCHelp.checkValidation(response, TipoTitularesRequest, MLCPersistanceActionEnum.UPDATE)) 
			{  
				InternalResultsResponse<Tipotitulares> internalResponse = getTipotitularesBCL().updateTipotitulares(TipoTitularesRequest);
				response.setTipotitulares(internalResponse.getResultsList());
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
	public TipotitularesResponse deleteTipotitulares(TipotitularesRequest TipoTitularesRequest) 
	{  
		TipotitularesResponse response = new TipotitularesResponse();
		try  
		{  
			if (LCHelp.checkValidation(response, TipoTitularesRequest, MLCPersistanceActionEnum.DELETE)) 
			{  
				InternalResponse internalResponse = getTipotitularesBCL().deleteTipotitulares(TipoTitularesRequest); 
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
	public InquiryTipotitularesResponse fetchAllTipotitulares(InquiryTipotitularesRequest inquiryTipoTitularesRequest) 
	{ 
		InquiryTipotitularesResponse response = new InquiryTipotitularesResponse(); 
		try 
		{ 
    
				InternalResultsResponse<Tipotitulares> internalResponse =  
						getTipotitularesBCL().fetchAllTipotitulares(inquiryTipoTitularesRequest); 
				response.setTipotitulares(internalResponse.getResultsList());
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
	public TipotitularesResponse fetchTipotitularesById(TipotitularesRequest TipoTitularesRequest) 
	{ 
		TipotitularesResponse response = new TipotitularesResponse(); 
		try 
		{  
			if (LCHelp.checkValidation(response, TipoTitularesRequest, MLCPersistanceActionEnum.FETCH_BY_ID)) 
			{ 
				InternalResultsResponse<Tipotitulares> internalResponse = getTipotitularesBCL().fetchTipotitularesById(TipoTitularesRequest); 
				response.setTipotitulares(internalResponse.getResultsList());
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
	public TipotitularesResponse fetchAllTipotitularesFilial(TipotitularesRequest TipoTitularesRequest) { 
		TipotitularesResponse response = new TipotitularesResponse(); 
		try 
		{    
			if (LCHelp.checkValidation(response, inquiryTipotitularesRequest, MLCPersistanceActionEnum.FETCH)) 
			{ 
				InternalResultsResponse<Tipotitulares> internalResponse =  
						getTipotitularesBCL().fetchAllTipotitularesFilial(TipoTitularesRequest);  
				response.setTipotitulares(internalResponse.getResultsList());   
				LCHelp.treatReturnFromBCL(response, internalResponse, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
			}  
		} 
		catch (Exception ex) 
		{  
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG); 
		} 
		return response; 
	} 
   
   
	public ITipotitularesBCL getTipotitularesBCL() 
	{       
		return TipoTitularesBCL; 
	}  
    
	public void setTipotitularesBCL(ITipotitularesBCL TipoTitularesBCL) 
	{ 
		this.TipoTitularesBCL = TipoTitularesBCL; 
	} 
}  
