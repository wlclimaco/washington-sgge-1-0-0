package com.qat.samples.sysmgmt.ordemServico.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

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
	private List<OrdemServicoItens> ordemServicoItensList;

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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<OrdemServicoItens> getOrdemServicoItensList() {
		return ordemServicoItensList;
	}

	public void setOrdemServicoItensList(List<OrdemServicoItens> ordemServicoItensList) {
		this.ordemServicoItensList = ordemServicoItensList;
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
				+ getOrdemServicoItensList() + ", getStatus()=" + getStatus() + ", toString()=" + super.toString() + "]";
	}

}
