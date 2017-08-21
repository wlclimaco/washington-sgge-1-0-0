/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

import com.qat.samples.sysmgmt.nf.model.NotaTypeEnum;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFHistorico extends ModelCosmeDamiao {

	private Integer id;
	/** The econtabil cnpj for the NFPessoaAutorizadaDownloadNFe. */
	private Long dateAlter;

	/** The econtabil cpf for the NFPessoaAutorizadaDownloadNFe. */
	private String user;

	/** The tipo NF enum. */
	private NotaTypeEnum tipoNFEnum;

	/**
	 * Gets the nota type enum value.
	 *
	 * @return the nota type enum value
	 */
	public Integer getTipoNFEnumValue() {
		if (tipoNFEnum != null) {
			return tipoNFEnum.getValue();
		}
		return null;
	}

	/**
	 * Sets the nota type enum value.
	 *
	 * @param acaoTypeValue the new nota type enum value
	 */
	public void setTipoNFEnumValue(Integer acaoTypeValue) {
		tipoNFEnum = NotaTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Default constructor.
	 */
	public NFHistorico() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDateAlter() {
		return dateAlter;
	}

	public void setDateAlter(Long dateAlter) {
		this.dateAlter = dateAlter;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the tipo NF enum.
	 *
	 * @return the tipo NF enum
	 */
	public NotaTypeEnum getTipoNFEnum() {
		return tipoNFEnum;
	}

	/**
	 * Sets the tipo NF enum.
	 *
	 * @param tipoNFEnum the new tipo NF enum
	 */
	public void setTipoNFEnum(NotaTypeEnum tipoNFEnum) {
		this.tipoNFEnum = tipoNFEnum;
	}

	@Override
	public String toString() {
		return "NFHistorico [getNotaTypeEnumValue()=" + getTipoNFEnumValue() + ", getId()=" + getId()
				+ ", getDateAlter()=" + getDateAlter() + ", getUser()=" + getUser() + ", getTipoNFEnum()="
				+ getTipoNFEnum() + ", toString()=" + super.toString() + "]";
	}



}
