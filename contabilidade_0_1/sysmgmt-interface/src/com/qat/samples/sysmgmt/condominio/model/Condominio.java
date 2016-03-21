package com.qat.samples.sysmgmt.entidade;

import java.util.List;

import com.qat.samples.sysmgmt.conta.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.pessoa.Socio;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Condominio extends Entidade
{

	private List<ContaCorrente> contaCorrenteList;
	
	private List<TarefaEnt> tarefaList;
	
	private List<Sindico> sindicoList;

	public Condominio()
	{
		super();
	}

	public Condominio(Integer id)
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

	public Integer getQntSocios()
	{
		return qntSocios;
	}

	public void setQntSocios(Integer qntSocios)
	{
		this.qntSocios = qntSocios;
	}

	public List<Usuario> getUsuarioList()
	{
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList)
	{
		this.usuarioList = usuarioList;
	}

	public List<ContaCorrente> getContaCorrenteList()
	{
		return contaCorrenteList;
	}

	public void setContaCorrenteList(List<ContaCorrente> contaCorrenteList)
	{
		this.contaCorrenteList = contaCorrenteList;
	}

	@Override
	public String toString()
	{
		return "Condominio [getPlanoList()=" + getPlanoList() + ", getQntFilial()=" + getQntFilial()
				+ ", getQntDeposito()=" + getQntDeposito() + ", getSocios()=" + getSocios() + ", getFilialList()="
				+ getFilialList() + ", getDepositoList()=" + getDepositoList() + ", getQntSocios()=" + getQntSocios()
				+ ", getUsuarioList()=" + getUsuarioList() + ", getContaCorrenteList()=" + getContaCorrenteList()
				+ ", toString()=" + super.toString() + "]";
	}

}
