package com.sensus.mlc.empresa.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.empresa.model.Empresa;


/**
 * The Class ActionResponse.
 *
 * @author - QAT Brazil.
 */
public class EmpresaResponse extends Response
{

	/** The actions. */
	private List<Empresa> empresa;

	/**
	 * @return the empresa
	 */
	public List<Empresa> getEmpresa()
	{
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(List<Empresa> empresa)
	{
		this.empresa = empresa;
	}

}
