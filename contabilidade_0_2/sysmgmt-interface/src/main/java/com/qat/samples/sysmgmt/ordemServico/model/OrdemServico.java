package com.qat.samples.sysmgmt.ordemServico.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class OrdemServico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The description. */
	private String nome;

	/** The estado. */
	private Long data;

	/** The numero. */
	private String assunto;

	/** The tipo endereco. */
	private List<OrdemServicoItens> ordemStatusList;

	/** The description. */
	private OrdemServicoTypeEnum status;

	public Integer getStatusValue()
	{
		if (status != null)
		{
			return status.getValue();
		}
		return null;
	}

	public void setStatusValue(Integer acaoTypeValue)
	{
		status = OrdemServicoTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Default constructor.
	 */
	public OrdemServico()
	{
		super();
	}

	public OrdemServico(int i, String string) {
		this.id = i;
		this.nome = string;
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * @return the assunto
	 */
	public String getAssunto()
	{
		return assunto;
	}

	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	/**
	 * @return the ordemStatusList
	 */
	public List<OrdemServicoItens> getOrdemStatusList()
	{
		return ordemStatusList;
	}

	/**
	 * @param ordemStatusList the ordemStatusList to set
	 */
	public void setOrdemStatusList(List<OrdemServicoItens> ordemStatusList)
	{
		this.ordemStatusList = ordemStatusList;
	}



	public OrdemServicoTypeEnum getStatus() {
		return status;
	}

	public void setStatus(OrdemServicoTypeEnum status) {
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "OrdemServico [getId()=" + getId() + ", getNome()=" + getNome() + ", getData()=" + getData()
				+ ", getAssunto()=" + getAssunto() + ", getOrdemStatusList()="
				+ getOrdemStatusList() + ", getStatus()=" + getStatus() + ", toString()=" + super.toString() + "]";
	}

}
