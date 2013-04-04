Memo1
package com.sensus.mlc.CNAE.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.CNAE.model.request.CnaeRequest;
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest;
import com.sensus.mlc.CNAE.model.response.CnaeResponse;
import com.sensus.mlc.CNAE.model.response.InquiryCnaeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ICnaeBCF.
 *
 * @author Washington.Costa
 */
public interface ICnaeBCF 
{

	/** 
	 * Insert CNAE.
	 *
	 * @param CNAERequest the CNAE request
	 * @return the CNAE response 
	 */
	public CnaeResponse insertCnae(CnaeRequest CNAERequest);
  
	/**
	 * Update CNAE.
	 *
	 * @param CNAERequest the CNAE request
	 * @return the CNAE response
	 */
	public CnaeResponse updateCnae(CnaeRequest CNAERequest);
  
	/**  
	 * Delete CNAE.  
	 *     
	 * @param CNAERequest the CNAE request 
	 * @return the CNAE response
	 */
	public CnaeResponse deleteCnae(CnaeRequest CNAERequest); 
   
	/**  
	 * Fetch all CNAE. 
	 *  
	 * @param inquiryCnaeRequest the inquiryCnae request   
	 * @return the inquiry CNAE response 
	 */ 
	public InquiryCnaeResponse fetchAllCnae(InquiryCnaeRequest inquiryCnaeRequest); 
   
	/** 
	 * Fetch CNAE by id. 
	 *   
	 * @param CnaeRequest the CNAE request 
	 * @return the CNAE response  
	 */ 
	public CnaeResponse fetchCnaeById(CnaeRequest CNAERequest); 
 
} 
package com.sensus.mlc.bairro.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.bairro.model.request.BairroRequest;
import com.sensus.mlc.bairro.model.request.InquiryBairroRequest;
import com.sensus.mlc.bairro.model.response.BairroResponse;
import com.sensus.mlc.bairro.model.response.InquiryBairroResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IBairroBCF.
 *
 * @author Washington.Costa
 */
public interface IBairroBCF 
{

	/** 
	 * Insert bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response 
	 */
	public BairroResponse insertBairro(BairroRequest bairroRequest);
  
	/**
	 * Update bairro.
	 *
	 * @param bairroRequest the bairro request
	 * @return the bairro response
	 */
	public BairroResponse updateBairro(BairroRequest bairroRequest);
  
	/**  
	 * Delete bairro.  
	 *     
	 * @param bairroRequest the bairro request 
	 * @return the bairro response
	 */
	public BairroResponse deleteBairro(BairroRequest bairroRequest); 
   
	/**  
	 * Fetch all bairro. 
	 *  
	 * @param inquiryBairroRequest the inquiryBairro request   
	 * @return the inquiry bairro response 
	 */ 
	public InquiryBairroResponse fetchAllBairro(InquiryBairroRequest inquiryBairroRequest); 
   
	/** 
	 * Fetch bairro by id. 
	 *   
	 * @param BairroRequest the bairro request 
	 * @return the bairro response  
	 */ 
	public BairroResponse fetchBairroById(BairroRequest bairroRequest); 
 
} 
package com.sensus.mlc.MUNICIPIO.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IMunicipioBCF.
 *
 * @author Washington.Costa
 */
public interface IMunicipioBCF 
{

	/** 
	 * Insert MUNICIPIO.
	 *
	 * @param MUNICIPIORequest the MUNICIPIO request
	 * @return the MUNICIPIO response 
	 */
	public MunicipioResponse insertMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/**
	 * Update MUNICIPIO.
	 *
	 * @param MUNICIPIORequest the MUNICIPIO request
	 * @return the MUNICIPIO response
	 */
	public MunicipioResponse updateMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/**  
	 * Delete MUNICIPIO.  
	 *     
	 * @param MUNICIPIORequest the MUNICIPIO request 
	 * @return the MUNICIPIO response
	 */
	public MunicipioResponse deleteMunicipio(MunicipioRequest MUNICIPIORequest); 
   
	/**  
	 * Fetch all MUNICIPIO. 
	 *  
	 * @param inquiryMunicipioRequest the inquiryMunicipio request   
	 * @return the inquiry MUNICIPIO response 
	 */ 
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquiryMunicipioRequest); 
   
	/** 
	 * Fetch MUNICIPIO by id. 
	 *   
	 * @param MunicipioRequest the MUNICIPIO request 
	 * @return the MUNICIPIO response  
	 */ 
	public MunicipioResponse fetchMunicipioById(MunicipioRequest MUNICIPIORequest); 
 
} 
package com.sensus.mlc.MUNICIPIO.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IMunicipioBCF.
 *
 * @author Washington.Costa
 */
public interface IMunicipioBCF 
{

	/** 
	 * Insert MUNICIPIO.
	 *
	 * @param MUNICIPIORequest the MUNICIPIO request
	 * @return the MUNICIPIO response 
	 */
	public MunicipioResponse insertMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/**
	 * Update MUNICIPIO.
	 *
	 * @param MUNICIPIORequest the MUNICIPIO request
	 * @return the MUNICIPIO response
	 */
	public MunicipioResponse updateMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/**  
	 * Delete MUNICIPIO.  
	 *     
	 * @param MUNICIPIORequest the MUNICIPIO request 
	 * @return the MUNICIPIO response
	 */
	public MunicipioResponse deleteMunicipio(MunicipioRequest MUNICIPIORequest); 
   
	/**  
	 * Fetch all MUNICIPIO. 
	 *  
	 * @param inquiryMunicipioRequest the inquiryMunicipio request   
	 * @return the inquiry MUNICIPIO response 
	 */ 
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquiryMunicipioRequest); 
   
	/** 
	 * Fetch MUNICIPIO by id. 
	 *   
	 * @param MunicipioRequest the MUNICIPIO request 
	 * @return the MUNICIPIO response  
	 */ 
	public MunicipioResponse fetchMunicipioById(MunicipioRequest MUNICIPIORequest); 
 
} 
package com.sensus.mlc.UF.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.UF.model.request.UfRequest;
import com.sensus.mlc.UF.model.request.InquiryUfRequest;
import com.sensus.mlc.UF.model.response.UfResponse;
import com.sensus.mlc.UF.model.response.InquiryUfResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IUfBCF.
 *
 * @author Washington.Costa
 */
public interface IUfBCF 
{

	/** 
	 * Insert UF.
	 *
	 * @param UFRequest the UF request
	 * @return the UF response 
	 */
	public UfResponse insertUf(UfRequest UFRequest);
  
	/**
	 * Update UF.
	 *
	 * @param UFRequest the UF request
	 * @return the UF response
	 */
	public UfResponse updateUf(UfRequest UFRequest);
  
	/**  
	 * Delete UF.  
	 *     
	 * @param UFRequest the UF request 
	 * @return the UF response
	 */
	public UfResponse deleteUf(UfRequest UFRequest); 
   
	/**  
	 * Fetch all UF. 
	 *  
	 * @param inquiryUfRequest the inquiryUf request   
	 * @return the inquiry UF response 
	 */ 
	public InquiryUfResponse fetchAllUf(InquiryUfRequest inquiryUfRequest); 
   
	/** 
	 * Fetch UF by id. 
	 *   
	 * @param UfRequest the UF request 
	 * @return the UF response  
	 */ 
	public UfResponse fetchUfById(UfRequest UFRequest); 
 
} 
package com.sensus.mlc.CNAE.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.CNAE.model.request.CnaeRequest;
import com.sensus.mlc.CNAE.model.request.InquiryCnaeRequest;
import com.sensus.mlc.CNAE.model.response.CnaeResponse;
import com.sensus.mlc.CNAE.model.response.InquiryCnaeResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface ICnaeBCF.
 *
 * @author Washington.Costa
 */
public interface ICnaeBCF 
{

	/** 
	 * Insert CNAE.
	 *
	 * @param CNAERequest the CNAE request
	 * @return the CNAE response 
	 */
	public CnaeResponse insertCnae(CnaeRequest CNAERequest);
  
	/**
	 * Update CNAE.
	 *
	 * @param CNAERequest the CNAE request
	 * @return the CNAE response
	 */
	public CnaeResponse updateCnae(CnaeRequest CNAERequest);
  
	/**  
	 * Delete CNAE.  
	 *     
	 * @param CNAERequest the CNAE request 
	 * @return the CNAE response
	 */
	public CnaeResponse deleteCnae(CnaeRequest CNAERequest); 
   
	/**  
	 * Fetch all CNAE. 
	 *  
	 * @param inquiryCnaeRequest the inquiryCnae request   
	 * @return the inquiry CNAE response 
	 */ 
	public InquiryCnaeResponse fetchAllCnae(InquiryCnaeRequest inquiryCnaeRequest); 
   
	/** 
	 * Fetch CNAE by id. 
	 *   
	 * @param CnaeRequest the CNAE request 
	 * @return the CNAE response  
	 */ 
	public CnaeResponse fetchCnaeById(CnaeRequest CNAERequest); 
 
} 
package com.sensus.mlc.BAIRRO.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.BAIRRO.model.request.BairroRequest;
import com.sensus.mlc.BAIRRO.model.request.InquiryBairroRequest;
import com.sensus.mlc.BAIRRO.model.response.BairroResponse;
import com.sensus.mlc.BAIRRO.model.response.InquiryBairroResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IBairroBCF.
 *
 * @author Washington.Costa
 */
public interface IBairroBCF 
{

	/** 
	 * Insert BAIRRO.
	 *
	 * @param BAIRRORequest the BAIRRO request
	 * @return the BAIRRO response 
	 */
	public BairroResponse insertBairro(BairroRequest BAIRRORequest);
  
	/**
	 * Update BAIRRO.
	 *
	 * @param BAIRRORequest the BAIRRO request
	 * @return the BAIRRO response
	 */
	public BairroResponse updateBairro(BairroRequest BAIRRORequest);
  
	/**  
	 * Delete BAIRRO.  
	 *     
	 * @param BAIRRORequest the BAIRRO request 
	 * @return the BAIRRO response
	 */
	public BairroResponse deleteBairro(BairroRequest BAIRRORequest); 
   
	/**  
	 * Fetch all BAIRRO. 
	 *  
	 * @param inquiryBairroRequest the inquiryBairro request   
	 * @return the inquiry BAIRRO response 
	 */ 
	public InquiryBairroResponse fetchAllBairro(InquiryBairroRequest inquiryBairroRequest); 
   
	/** 
	 * Fetch BAIRRO by id. 
	 *   
	 * @param BairroRequest the BAIRRO request 
	 * @return the BAIRRO response  
	 */ 
	public BairroResponse fetchBairroById(BairroRequest BAIRRORequest); 
 
} 
package com.sensus.mlc.MUNICIPIO.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.MUNICIPIO.model.request.MunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.MUNICIPIO.model.response.MunicipioResponse;
import com.sensus.mlc.MUNICIPIO.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IMunicipioBCF.
 *
 * @author Washington.Costa
 */
public interface IMunicipioBCF 
{

	/** 
	 * Insert MUNICIPIO.
	 *
	 * @param MUNICIPIORequest the MUNICIPIO request
	 * @return the MUNICIPIO response 
	 */
	public MunicipioResponse insertMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/**
	 * Update MUNICIPIO.
	 *
	 * @param MUNICIPIORequest the MUNICIPIO request
	 * @return the MUNICIPIO response
	 */
	public MunicipioResponse updateMunicipio(MunicipioRequest MUNICIPIORequest);
  
	/**  
	 * Delete MUNICIPIO.  
	 *     
	 * @param MUNICIPIORequest the MUNICIPIO request 
	 * @return the MUNICIPIO response
	 */
	public MunicipioResponse deleteMunicipio(MunicipioRequest MUNICIPIORequest); 
   
	/**  
	 * Fetch all MUNICIPIO. 
	 *  
	 * @param inquiryMunicipioRequest the inquiryMunicipio request   
	 * @return the inquiry MUNICIPIO response 
	 */ 
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquiryMunicipioRequest); 
   
	/** 
	 * Fetch MUNICIPIO by id. 
	 *   
	 * @param MunicipioRequest the MUNICIPIO request 
	 * @return the MUNICIPIO response  
	 */ 
	public MunicipioResponse fetchMunicipioById(MunicipioRequest MUNICIPIORequest); 
 
} 
package com.sensus.mlc.UF.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.UF.model.request.UfRequest;
import com.sensus.mlc.UF.model.request.InquiryUfRequest;
import com.sensus.mlc.UF.model.response.UfResponse;
import com.sensus.mlc.UF.model.response.InquiryUfResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IUfBCF.
 *
 * @author Washington.Costa
 */
public interface IUfBCF 
{

	/** 
	 * Insert UF.
	 *
	 * @param UFRequest the UF request
	 * @return the UF response 
	 */
	public UfResponse insertUf(UfRequest UFRequest);
  
	/**
	 * Update UF.
	 *
	 * @param UFRequest the UF request
	 * @return the UF response
	 */
	public UfResponse updateUf(UfRequest UFRequest);
  
	/**  
	 * Delete UF.  
	 *     
	 * @param UFRequest the UF request 
	 * @return the UF response
	 */
	public UfResponse deleteUf(UfRequest UFRequest); 
   
	/**  
	 * Fetch all UF. 
	 *  
	 * @param inquiryUfRequest the inquiryUf request   
	 * @return the inquiry UF response 
	 */ 
	public InquiryUfResponse fetchAllUf(InquiryUfRequest inquiryUfRequest); 
   
	/** 
	 * Fetch UF by id. 
	 *   
	 * @param UfRequest the UF request 
	 * @return the UF response  
	 */ 
	public UfResponse fetchUfById(UfRequest UFRequest); 
 
} 
package com.sensus.mlc.PAIS.bcf;

import com.sensus.common.model.request.Request;
import com.sensus.mlc.PAIS.model.request.PaisRequest;
import com.sensus.mlc.PAIS.model.request.InquiryPaisRequest;
import com.sensus.mlc.PAIS.model.response.PaisResponse;
import com.sensus.mlc.PAIS.model.response.InquiryPaisResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;


/** 
 * The Interface IPaisBCF.
 *
 * @author Washington.Costa
 */
public interface IPaisBCF 
{

	/** 
	 * Insert PAIS.
	 *
	 * @param PAISRequest the PAIS request
	 * @return the PAIS response 
	 */
	public PaisResponse insertPais(PaisRequest PAISRequest);
  
	/**
	 * Update PAIS.
	 *
	 * @param PAISRequest the PAIS request
	 * @return the PAIS response
	 */
	public PaisResponse updatePais(PaisRequest PAISRequest);
  
	/**  
	 * Delete PAIS.  
	 *     
	 * @param PAISRequest the PAIS request 
	 * @return the PAIS response
	 */
	public PaisResponse deletePais(PaisRequest PAISRequest); 
   
	/**  
	 * Fetch all PAIS. 
	 *  
	 * @param inquiryPaisRequest the inquiryPais request   
	 * @return the inquiry PAIS response 
	 */ 
	public InquiryPaisResponse fetchAllPais(InquiryPaisRequest inquiryPaisRequest); 
   
	/** 
	 * Fetch PAIS by id. 
	 *   
	 * @param PaisRequest the PAIS request 
	 * @return the PAIS response  
	 */ 
	public PaisResponse fetchPaisById(PaisRequest PAISRequest); 
 
} 
