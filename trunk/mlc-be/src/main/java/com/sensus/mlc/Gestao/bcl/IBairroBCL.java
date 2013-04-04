Memo1
package com.sensus.mlc.CNAE.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.CNAE.model.Cnae; 
import com.sensus.mlc.CNAE.model.request.CnaeRequest; 
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface ICnaeBCL 
{   
    
	/**  
	 * Insert CNAE. 
	 * 
	 * @param CNAERequest the CNAE request  
	 * @return the CNAE response  
	 */ 
	public InternalResultsResponse<Cnae> insertCnae(CnaeRequest CNAERequest);
  
	/** 
	 * Update CNAE. 
	 *  
	 * @param CNAERequest the CNAE request 
	 * @return the CNAE response 
	 */  
	public InternalResultsResponse<Cnae> updateCnae(CnaeRequest CNAERequest); 
   
	/** 
	 * Delete CNAE. 
	 *   
	 * @param CNAERequest the CNAE request 
	 * @return the CNAE response  
	 */ 
	public InternalResponse deleteCnae(CnaeRequest CNAERequest); 
  
	/** 
	 * Fetch all CNAE.
	 *   
	 * @param inquiryCNAERequest the inquiryCNAE request 
	 * @return the inquiry CNAE response 
	 */  
	public InternalResultsResponse<Cnae> fetchAllCnae(InquiryCnaeRequest inquiryCNAERequest);
  
	/** 
	 * Fetch CNAE by id. 
	 * 
	 * @param inquiryCNAERequest the inquiryCNAE request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Cnae> fetchCnaeById(CnaeRequest CNAERequest); 
 
	/** 
	 * Fetch all CNAE types. 
	 * 
	 * @param request the request 
	 * @return the CNAE response 
	 */ 
	public InternalResultsResponse<Cnae> fetchAllCnaeTypes(InquiryCnaeRequest inquiryCNAERequest);  
  
	/** 
	 * Fetch all CNAE filial. 
	 *  
	 * @param request the request 
	 * @return the CNAE response 
	 */ 
	public InternalResultsResponse<Cnae> fetchAllCnaeFilial(CnaeRequest CNAERequest);
 
} 
package com.sensus.mlc.bairro.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.bairro.model.Bairro; 
import com.sensus.mlc.bairro.model.request.BairroRequest; 
import com.sensus.mlc.bairro.model.request.InquiryBairroRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IBairroBCL 
{   
    
	/**  
	 * Insert bairro. 
	 * 
	 * @param bairroRequest the bairro request  
	 * @return the bairro response  
	 */ 
	public InternalResultsResponse<Bairro> insertBairro(BairroRequest bairroRequest);
  
	/** 
	 * Update bairro. 
	 *  
	 * @param bairroRequest the bairro request 
	 * @return the bairro response 
	 */  
	public InternalResultsResponse<Bairro> updateBairro(BairroRequest bairroRequest); 
   
	/** 
	 * Delete bairro. 
	 *   
	 * @param bairroRequest the bairro request 
	 * @return the bairro response  
	 */ 
	public InternalResponse deleteBairro(BairroRequest bairroRequest); 
  
	/** 
	 * Fetch all bairro.
	 *   
	 * @param inquirybairroRequest the inquirybairro request 
	 * @return the inquiry bairro response 
	 */  
	public InternalResultsResponse<Bairro> fetchAllBairro(InquiryBairroRequest inquirybairroRequest);
  
	/** 
	 * Fetch bairro by id. 
	 * 
	 * @param inquirybairroRequest the inquirybairro request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Bairro> fetchBairroById(BairroRequest bairroRequest); 
 
	/** 
	 * Fetch all bairro types. 
	 * 
	 * @param request the request 
	 * @return the bairro response 
	 */ 
	public InternalResultsResponse<Bairro> fetchAllBairroTypes(InquiryBairroRequest inquirybairroRequest);  
  
	/** 
	 * Fetch all bairro filial. 
	 *  
	 * @param request the request 
	 * @return the bairro response 
	 */ 
	public InternalResultsResponse<Bairro> fetchAllBairroFilial(BairroRequest bairroRequest);
 
} 
package com.sensus.mlc.MUNICIPIO.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.MUNICIPIO.model.Municipio; 
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest; 
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IMunicipioBCL 
{   
    
	/**  
	 * Insert MUNICIPIO. 
	 * 
	 * @param MUNICIPIORequest the MUNICIPIO request  
	 * @return the MUNICIPIO response  
	 */ 
	public InternalResultsResponse<Municipio> insertMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/** 
	 * Update MUNICIPIO. 
	 *  
	 * @param MUNICIPIORequest the MUNICIPIO request 
	 * @return the MUNICIPIO response 
	 */  
	public InternalResultsResponse<Municipio> updateMunicipio(MunicipioRequest MUNICIPIORequest); 
   
	/** 
	 * Delete MUNICIPIO. 
	 *   
	 * @param MUNICIPIORequest the MUNICIPIO request 
	 * @return the MUNICIPIO response  
	 */ 
	public InternalResponse deleteMunicipio(MunicipioRequest MUNICIPIORequest); 
  
	/** 
	 * Fetch all MUNICIPIO.
	 *   
	 * @param inquiryMUNICIPIORequest the inquiryMUNICIPIO request 
	 * @return the inquiry MUNICIPIO response 
	 */  
	public InternalResultsResponse<Municipio> fetchAllMunicipio(InquiryMunicipioRequest inquiryMUNICIPIORequest);
  
	/** 
	 * Fetch MUNICIPIO by id. 
	 * 
	 * @param inquiryMUNICIPIORequest the inquiryMUNICIPIO request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Municipio> fetchMunicipioById(MunicipioRequest MUNICIPIORequest); 
 
	/** 
	 * Fetch all MUNICIPIO types. 
	 * 
	 * @param request the request 
	 * @return the MUNICIPIO response 
	 */ 
	public InternalResultsResponse<Municipio> fetchAllMunicipioTypes(InquiryMunicipioRequest inquiryMUNICIPIORequest);  
  
	/** 
	 * Fetch all MUNICIPIO filial. 
	 *  
	 * @param request the request 
	 * @return the MUNICIPIO response 
	 */ 
	public InternalResultsResponse<Municipio> fetchAllMunicipioFilial(MunicipioRequest MUNICIPIORequest);
 
} 
package com.sensus.mlc.MUNICIPIO.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.MUNICIPIO.model.Municipio; 
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest; 
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IMunicipioBCL 
{   
    
	/**  
	 * Insert MUNICIPIO. 
	 * 
	 * @param MUNICIPIORequest the MUNICIPIO request  
	 * @return the MUNICIPIO response  
	 */ 
	public InternalResultsResponse<Municipio> insertMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/** 
	 * Update MUNICIPIO. 
	 *  
	 * @param MUNICIPIORequest the MUNICIPIO request 
	 * @return the MUNICIPIO response 
	 */  
	public InternalResultsResponse<Municipio> updateMunicipio(MunicipioRequest MUNICIPIORequest); 
   
	/** 
	 * Delete MUNICIPIO. 
	 *   
	 * @param MUNICIPIORequest the MUNICIPIO request 
	 * @return the MUNICIPIO response  
	 */ 
	public InternalResponse deleteMunicipio(MunicipioRequest MUNICIPIORequest); 
  
	/** 
	 * Fetch all MUNICIPIO.
	 *   
	 * @param inquiryMUNICIPIORequest the inquiryMUNICIPIO request 
	 * @return the inquiry MUNICIPIO response 
	 */  
	public InternalResultsResponse<Municipio> fetchAllMunicipio(InquiryMunicipioRequest inquiryMUNICIPIORequest);
  
	/** 
	 * Fetch MUNICIPIO by id. 
	 * 
	 * @param inquiryMUNICIPIORequest the inquiryMUNICIPIO request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Municipio> fetchMunicipioById(MunicipioRequest MUNICIPIORequest); 
 
	/** 
	 * Fetch all MUNICIPIO types. 
	 * 
	 * @param request the request 
	 * @return the MUNICIPIO response 
	 */ 
	public InternalResultsResponse<Municipio> fetchAllMunicipioTypes(InquiryMunicipioRequest inquiryMUNICIPIORequest);  
  
	/** 
	 * Fetch all MUNICIPIO filial. 
	 *  
	 * @param request the request 
	 * @return the MUNICIPIO response 
	 */ 
	public InternalResultsResponse<Municipio> fetchAllMunicipioFilial(MunicipioRequest MUNICIPIORequest);
 
} 
package com.sensus.mlc.UF.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.UF.model.Uf; 
import com.sensus.mlc.UF.model.request.UfRequest; 
import com.sensus.mlc.UF.model.request.InquiryUfRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IUfBCL 
{   
    
	/**  
	 * Insert UF. 
	 * 
	 * @param UFRequest the UF request  
	 * @return the UF response  
	 */ 
	public InternalResultsResponse<Uf> insertUf(UfRequest UFRequest);
  
	/** 
	 * Update UF. 
	 *  
	 * @param UFRequest the UF request 
	 * @return the UF response 
	 */  
	public InternalResultsResponse<Uf> updateUf(UfRequest UFRequest); 
   
	/** 
	 * Delete UF. 
	 *   
	 * @param UFRequest the UF request 
	 * @return the UF response  
	 */ 
	public InternalResponse deleteUf(UfRequest UFRequest); 
  
	/** 
	 * Fetch all UF.
	 *   
	 * @param inquiryUFRequest the inquiryUF request 
	 * @return the inquiry UF response 
	 */  
	public InternalResultsResponse<Uf> fetchAllUf(InquiryUfRequest inquiryUFRequest);
  
	/** 
	 * Fetch UF by id. 
	 * 
	 * @param inquiryUFRequest the inquiryUF request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Uf> fetchUfById(UfRequest UFRequest); 
 
	/** 
	 * Fetch all UF types. 
	 * 
	 * @param request the request 
	 * @return the UF response 
	 */ 
	public InternalResultsResponse<Uf> fetchAllUfTypes(InquiryUfRequest inquiryUFRequest);  
  
	/** 
	 * Fetch all UF filial. 
	 *  
	 * @param request the request 
	 * @return the UF response 
	 */ 
	public InternalResultsResponse<Uf> fetchAllUfFilial(UfRequest UFRequest);
 
} 
package com.sensus.mlc.CNAE.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.CNAE.model.Cnae; 
import com.sensus.mlc.CNAE.model.request.CnaeRequest; 
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface ICnaeBCL 
{   
    
	/**  
	 * Insert CNAE. 
	 * 
	 * @param CNAERequest the CNAE request  
	 * @return the CNAE response  
	 */ 
	public InternalResultsResponse<Cnae> insertCnae(CnaeRequest CNAERequest);
  
	/** 
	 * Update CNAE. 
	 *  
	 * @param CNAERequest the CNAE request 
	 * @return the CNAE response 
	 */  
	public InternalResultsResponse<Cnae> updateCnae(CnaeRequest CNAERequest); 
   
	/** 
	 * Delete CNAE. 
	 *   
	 * @param CNAERequest the CNAE request 
	 * @return the CNAE response  
	 */ 
	public InternalResponse deleteCnae(CnaeRequest CNAERequest); 
  
	/** 
	 * Fetch all CNAE.
	 *   
	 * @param inquiryCNAERequest the inquiryCNAE request 
	 * @return the inquiry CNAE response 
	 */  
	public InternalResultsResponse<Cnae> fetchAllCnae(InquiryCnaeRequest inquiryCNAERequest);
  
	/** 
	 * Fetch CNAE by id. 
	 * 
	 * @param inquiryCNAERequest the inquiryCNAE request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Cnae> fetchCnaeById(CnaeRequest CNAERequest); 
 
	/** 
	 * Fetch all CNAE types. 
	 * 
	 * @param request the request 
	 * @return the CNAE response 
	 */ 
	public InternalResultsResponse<Cnae> fetchAllCnaeTypes(InquiryCnaeRequest inquiryCNAERequest);  
  
	/** 
	 * Fetch all CNAE filial. 
	 *  
	 * @param request the request 
	 * @return the CNAE response 
	 */ 
	public InternalResultsResponse<Cnae> fetchAllCnaeFilial(CnaeRequest CNAERequest);
 
} 
package com.sensus.mlc.BAIRRO.bcl;


import com.sensus.common.model.response.InternalResponse; 
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.BAIRRO.model.Bairro; 
import com.sensus.mlc.BAIRRO.model.request.BairroRequest; 
import com.sensus.mlc.BAIRRO.model.request.InquiryBairroRequest; 
 
// TODO: Auto-generated Javadoc 
/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Brazil. 
 * 
 */ 
public interface IBairroBCL 
{   
    
	/**  
	 * Insert BAIRRO. 
	 * 
	 * @param BAIRRORequest the BAIRRO request  
	 * @return the BAIRRO response  
	 */ 
	public InternalResultsResponse<Bairro> insertBairro(BairroRequest BAIRRORequest);
  
	/** 
	 * Update BAIRRO. 
	 *  
	 * @param BAIRRORequest the BAIRRO request 
	 * @return the BAIRRO response 
	 */  
	public InternalResultsResponse<Bairro> updateBairro(BairroRequest BAIRRORequest); 
   
	/** 
	 * Delete BAIRRO. 
	 *   
	 * @param BAIRRORequest the BAIRRO request 
	 * @return the BAIRRO response  
	 */ 
	public InternalResponse deleteBairro(BairroRequest BAIRRORequest); 
  
	/** 
	 * Fetch all BAIRRO.
	 *   
	 * @param inquiryBAIRRORequest the inquiryBAIRRO request 
	 * @return the inquiry BAIRRO response 
	 */  
	public InternalResultsResponse<Bairro> fetchAllBairro(InquiryBairroRequest inquiryBAIRRORequest);
  
	/** 
	 * Fetch BAIRRO by id. 
	 * 
	 * @param inquiryBAIRRORequest the inquiryBAIRRO request
	 * @return the internal results response   
	 */ 
	public InternalResultsResponse<Bairro> fetchBairroById(BairroRequest BAIRRORequest); 
 
	/** 
	 * Fetch all BAIRRO types. 
	 * 
	 * @param request the request 
	 * @return the BAIRRO response 
	 */ 
	public InternalResultsResponse<Bairro> fetchAllBairroTypes(InquiryBairroRequest inquiryBAIRRORequest);  
  
	/** 
	 * Fetch all BAIRRO filial. 
	 *  
	 * @param request the request 
	 * @return the BAIRRO response 
	 */ 
	public InternalResultsResponse<Bairro> fetchAllBairroFilial(BairroRequest BAIRRORequest);
 
} 
