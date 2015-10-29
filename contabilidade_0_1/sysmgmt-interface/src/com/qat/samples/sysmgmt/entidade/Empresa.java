package com.qat.samples.sysmgmt.entidade;

import java.util.List;

import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.pessoa.Socio;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Empresa extends Entidade
{

	private List<Plano> planoList;

	private Integer qntFilial;

	private Integer qntDeposito;

	private List<Socio> socios;

	private List<Filial> filialList;

	private List<Deposito> depositoList;

	public Empresa()
	{
		super();
	}

	public Empresa(Integer id)
	{
		setId(id);
	}

	/**
	 * @return the planoList
	 */
	public List<Plano> getPlanoList()
	{
		return planoList;
	}

	/**
	 * @param planoList the planoList to set
	 */
	public void setPlanoList(List<Plano> planoList)
	{
		this.planoList = planoList;
	}

	/**
	 * @return the qntFilial
	 */
	public Integer getQntFilial()
	{
		return qntFilial;
	}

	/**
	 * @param qntFilial the qntFilial to set
	 */
	public void setQntFilial(Integer qntFilial)
	{
		this.qntFilial = qntFilial;
	}

	/**
	 * @return the qntDeposito
	 */
	public Integer getQntDeposito()
	{
		return qntDeposito;
	}

	/**
	 * @param qntDeposito the qntDeposito to set
	 */
	public void setQntDeposito(Integer qntDeposito)
	{
		this.qntDeposito = qntDeposito;
	}

	/**
	 * @return the socios
	 */
	public List<Socio> getSocios()
	{
		return socios;
	}

	/**
	 * @param socios the socios to set
	 */
	public void setSocios(List<Socio> socios)
	{
		this.socios = socios;
	}

	/**
	 * @return the filialList
	 */
	public List<Filial> getFilialList()
	{
		return filialList;
	}

	/**
	 * @param filialList the filialList to set
	 */
	public void setFilialList(List<Filial> filialList)
	{
		this.filialList = filialList;
	}

	/**
	 * @return the depositoList
	 */
	public List<Deposito> getDepositoList()
	{
		return depositoList;
	}

	/**
	 * @param depositoList the depositoList to set
	 */
	public void setDepositoList(List<Deposito> depositoList)
	{
		this.depositoList = depositoList;
	}

	@Override
	public String toString()
	{
		return "Empresa [getPlanoList()=" + getPlanoList() + ", getQntFilial()=" + getQntFilial()
				+ ", getQntDeposito()=" + getQntDeposito() + ", getSocios()=" + getSocios() + ", getFilialList()="
				+ getFilialList() + ", getDepositoList()=" + getDepositoList() + ", toString()=" + super.toString()
				+ "]";
	}

}
