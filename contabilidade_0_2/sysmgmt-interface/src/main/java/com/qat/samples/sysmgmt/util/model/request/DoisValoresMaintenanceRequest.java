package com.qat.samples.sysmgmt.util.model.request;

import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * The Class DoisValoresMaintenanceRequest.
 */
public class DoisValoresMaintenanceRequest extends UtilMaintenanceRequest
{
	/** The doisValor. */
	private DoisValores doisValor;



	/**
	 * Instantiates a new doisValor maintenance request.
	 *
	 * @param doisValor the doisValor
	 * @param returnList the return list
	 * @param returnListPaged the return list paged
	 */
	public DoisValoresMaintenanceRequest(DoisValores doisValor, Boolean returnList, Boolean returnListPaged)
	{
		this.doisValor = doisValor;
	}

	/**
	 * Instantiates a new doisValor maintenance request.
	 */
	public DoisValoresMaintenanceRequest()
	{
	}




	public DoisValores getDoisValor() {
		return doisValor;
	}

	public void setDoisValor(DoisValores doisValor) {
		this.doisValor = doisValor;
	}

	@Override
	public String toString() {
		return "DoisValoresMaintenanceRequest [getDoisValor()=" + getDoisValor() + ", toString()=" + super.toString()
				+ "]";
	}


}
