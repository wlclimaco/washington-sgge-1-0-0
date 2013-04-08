package com.sensus.mlc.gestao.bcf;
import com.sensus.mlc.gestao.model.request.InquiryPaisRequest;
import com.sensus.mlc.gestao.model.request.PaisRequest;
import com.sensus.mlc.gestao.model.response.InquiryPaisResponse;
import com.sensus.mlc.gestao.model.response.PaisResponse;


/**
 * The Interface IPaisBCF.
 *
 * @author Washington.Costa
 */
public interface IPaisBCF
{

	/**
	 * Insert pais.
	 *
	 * @param paisRequest the pais request
	 * @return the pais response
	 */
	public PaisResponse insertPais(PaisRequest paisRequest);

	/**
	 * Update pais.
	 *
	 * @param paisRequest the pais request
	 * @return the pais response
	 */
	public PaisResponse updatePais(PaisRequest paisRequest);

	/**
	 * Delete pais.
	 *
	 * @param paisRequest the pais request
	 * @return the pais response
	 */
	public PaisResponse deletePais(PaisRequest paisRequest);

	/**
	 * Fetch all pais.
	 *
	 * @param inquiryPaisRequest the inquiryPais request
	 * @return the inquiry pais response
	 */
	public InquiryPaisResponse fetchAllPais(InquiryPaisRequest inquiryPaisRequest);

	/**
	 * Fetch pais by id.
	 *
	 * @param PaisRequest the pais request
	 * @return the pais response
	 */
	public PaisResponse fetchPaisById(PaisRequest paisRequest);

}
