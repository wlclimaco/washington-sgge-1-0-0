package com.qat.samples.sysmgmt.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Interface IControleAcessDAC. (Data Access Component - DAC)
 */
public interface IControleAcessDAC
{

	/**
	 * Insert controleAcess.
	 * 
	 * @param controleAcess the controleAcess
	 * @return the internal response
	 */
	public InternalResponse insertControleAcess(ControleAcess controleAcess);

	/**
	 * Fetch all controleAcesss.
	 * 
	 * @param controleAcess the controle acess
	 * @return the list< controleAcess>
	 */
	public List<ControleAcess> fetchAllControleAcesss(ControleAcess controleAcess);

	/**
	 * Fetch county by id.
	 * 
	 * @param controleAcess the controle acess
	 * @return the cached results response
	 */

	public ControleAcess fetchControleAcessById(ControleAcess controleAcess);

	/**
	 * Fetch controle acess by type.
	 * 
	 * @param controleAcess the controle acess
	 * @return the controle acess
	 */
	public ControleAcess fetchControleAcessByType(ControleAcess controleAcess);

}
