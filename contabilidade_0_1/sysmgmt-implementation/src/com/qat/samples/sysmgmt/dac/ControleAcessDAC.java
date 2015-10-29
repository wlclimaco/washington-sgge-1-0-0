package com.qat.samples.sysmgmt.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Interface IControleAcessDAC. (Data Access Component - DAC)
 */
public interface ControleAcessDAC
{

	/**
	 * Insert controleAcess.
	 * 
	 * @param controleAcess the controleAcess
	 * @return the internal response
	 */
	public InternalResponse insertControleAcess(ControleAcess controleAcess);

	/**
	 * Update controleAcess.
	 * 
	 * @param controleAcess the controleAcess
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateControleAcess(ControleAcess controleAcess);

	/**
	 * Delete controleAcess.
	 * 
	 * @param controleAcess the controleAcess
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteControleAcess(ControleAcess controleAcess);

	/**
	 * Fetch all controleAcesss.
	 * 
	 * @return the list< controleAcess>
	 */
	public List<ControleAcess> fetchAllControleAcesssType(ControleAcess controleAcess);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public ControleAcess fetchControleAcessById(ControleAcess request);

}
