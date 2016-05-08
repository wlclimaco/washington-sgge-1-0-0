package com.qat.samples.sysmgmt.contato.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Contato extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The data contato. */
	private Long dataContato;

	/** The nome. */
	private String nome;

	/** The email. */
	private String email;

	/** The telefone. */
	private String telefone;

	/** The motivo. */
	private ContatoTypeEnum motivo;

	private List<ContatoItens> contatoItensList;

	public Contato(Integer id, Long dataContato, String nome, String email, String telefone, ContatoTypeEnum motivo,
			List<ContatoItens> contatoItensList, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.dataContato = dataContato;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.motivo = motivo;
		this.contatoItensList = contatoItensList;
		setModelAction(modelAction);
	}

	/**
	 * Gets the motivo value.
	 *
	 * @return the motivo value
	 */
	public Integer getMotivoValue()
	{
		if (motivo != null)
		{
			return motivo.getValue();
		}
		return null;
	}

	/**
	 * Sets the motivo value.
	 *
	 * @param acaoTypeValue the new motivo value
	 */
	public void setMotivoValue(Integer acaoTypeValue)
	{
		motivo = ContatoTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Gets the motivo.
	 *
	 * @return the motivo
	 */
	public ContatoTypeEnum getMotivo()
	{
		return motivo;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo the motivo to set
	 */
	public void setMotivo(ContatoTypeEnum motivo)
	{
		this.motivo = motivo;
	}

	/**
	 * Default constructor.
	 */
	public Contato()
	{
		super();
	}

	public Contato(int i, String string) {
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the data contato.
	 *
	 * @return the dataContato
	 */
	public Long getDataContato()
	{
		return dataContato;
	}

	/**
	 * Sets the data contato.
	 *
	 * @param dataContato the dataContato to set
	 */
	public void setDataContato(Long dataContato)
	{
		this.dataContato = dataContato;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}

	public List<ContatoItens> getContatoItensList()
	{
		return contatoItensList;
	}

	public void setContatoItensList(List<ContatoItens> contatoItensList)
	{
		this.contatoItensList = contatoItensList;
	}

	@Override
	public String toString()
	{
		return "Contato [getMotivoValue()=" + getMotivoValue() + ", getMotivo()=" + getMotivo() + ", getId()="
				+ getId() + ", getDataContato()=" + getDataContato() + ", getNome()=" + getNome() + ", getEmail()="
				+ getEmail() + ", getTelefone()=" + getTelefone() + ", getContatoItensList()=" + getContatoItensList()
				+ ", toString()=" + super.toString() + "]";
	}

}
