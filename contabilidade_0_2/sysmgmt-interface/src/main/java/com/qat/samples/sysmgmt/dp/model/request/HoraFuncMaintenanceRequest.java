package com.qat.samples.sysmgmt.dp.model.request;

import com.qat.samples.sysmgmt.dp.model.HorarioFunc;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class HoraFuncMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private HorarioFunc funcionario;

	/**
	 * The Constructor.
	 */
	public HoraFuncMaintenanceRequest()
	{

	}

	/**
	 * Gets the funcionario.
	 *
	 * @return the funcionario
	 */
	public HorarioFunc getHorarioFunc()
	{
		return funcionario;
	}

	/**
	 * Sets the funcionario.
	 *
	 * @param funcionario the funcionario to set
	 */
	public void setHorarioFunc(HorarioFunc funcionario)
	{
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "HorarioFuncMaintenanceRequest [getHorarioFunc()=" + getHorarioFunc() + ", toString()="
				+ super.toString() + "]";
	}

}