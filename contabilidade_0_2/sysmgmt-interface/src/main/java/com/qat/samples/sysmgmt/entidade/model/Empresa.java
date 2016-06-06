package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.pessoa.model.Socio;

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

	private List<Filial> filialList;

	private List<Deposito> depositoList;

	private List<ContaCorrente> contaCorrenteList;

	private List<TarefaEnt> tarefaList;

	public Empresa()
	{
		super();
	}

	public Empresa(Integer id)
	{
		setId(id);
	}

	public Empresa(Integer id,String nome) {
		super();
		setId(id);
		setNome(nome);
		setEntidadeEnum(EntidadeTypeEnum.ADVOCACIA);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
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

	public List<ContaCorrente> getContaCorrenteList()
	{
		return contaCorrenteList;
	}

	public void setContaCorrenteList(List<ContaCorrente> contaCorrenteList)
	{
		this.contaCorrenteList = contaCorrenteList;
	}

	public List<TarefaEnt> getTarefaList() {
		return tarefaList;
	}

	public void setTarefaList(List<TarefaEnt> tarefaList) {
		this.tarefaList = tarefaList;
	}

	@Override
	public String toString() {
		return "Empresa [getPlanoList()=" + getPlanoList() + ", getQntFilial()=" + getQntFilial()
				+ ", getQntDeposito()=" + getQntDeposito() + ", getFilialList()=" + getFilialList()
				+ ", getDepositoList()=" + getDepositoList() + ", getContaCorrenteList()=" + getContaCorrenteList()
				+ ", getTarefaList()=" + getTarefaList() + ", toString()=" + super.toString() + "]";
	}

}
