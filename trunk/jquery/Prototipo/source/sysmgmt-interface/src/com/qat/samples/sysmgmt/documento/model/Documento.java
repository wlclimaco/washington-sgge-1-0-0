package com.qat.samples.sysmgmt.documento.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.TableTypeEnum;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Documento", propOrder = {"documentoid", "id", "rgInc", "cpfCnpj", "razao", "tabela", "dateNascimento"})
public class Documento extends Util
{

	/** The id. */

	private Integer documentoid;

	/** The id. */
	private Integer id;

	/** The code. */
	private String rgInc;

	/** The cpf cnpj. */
	private String cpfCnpj;

	/** The razao. */
	private String razao;

	private TableTypeEnum tabela;

	/** The description. */
	private Date dateNascimento;

	/**
	 * Instantiates a new documento.
	 * 
	 * @param id the id
	 * @param rgInc the rg inc
	 * @param cpfCnpj the cpf cnpj
	 * @param nome the nome
	 * @param razao the razao
	 * @param dateNascimento the date nascimento
	 */
	public Documento(Integer id, String rgInc, String cpfCnpj, String nome, String razao, Date dateNascimento)
	{
		super();
		this.id = id;
		this.rgInc = rgInc;
		this.cpfCnpj = cpfCnpj;
		this.razao = razao;
		this.dateNascimento = dateNascimento;
	}

	/**
	 * Gets the razao.
	 * 
	 * @return the razao
	 */
	public String getRazao()
	{
		return razao;
	}

	/**
	 * Sets the razao.
	 * 
	 * @param razao the new razao
	 */
	public void setRazao(String razao)
	{
		this.razao = razao;
	}

	/**
	 * Instantiates a new documento.
	 */
	public Documento()
	{
		super();
	}

	public Integer getDocumentoid()
	{
		return documentoid;
	}

	public void setDocumentoid(Integer documentoid)
	{
		this.documentoid = documentoid;
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
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getRgInc()
	{
		return rgInc;
	}

	/**
	 * Sets the code.
	 * 
	 * @param rgInc the new code
	 */
	public void setRgInc(String rgInc)
	{
		this.rgInc = rgInc;
	}

	/**
	 * Gets the cpf cnpj.
	 * 
	 * @return the cpf cnpj
	 */
	public String getCpfCnpj()
	{
		return cpfCnpj;
	}

	/**
	 * Sets the cpf cnpj.
	 * 
	 * @param cpfCnpj the new cpf cnpj
	 */
	public void setCpfCnpj(String cpfCnpj)
	{
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public Date getDateNascimento()
	{
		return dateNascimento;
	}

	/**
	 * Sets the description.
	 * 
	 * @param dateNascimento the new description
	 */
	public void setDateNascimento(Date dateNascimento)
	{
		this.dateNascimento = dateNascimento;
	}

	/**
	 * Instantiates a new documento.
	 * 
	 * @param id the id
	 * @param rgInc the rg inc
	 * @param cpfCnpj the cpf cnpj
	 * @param dateNascimento the date nascimento
	 */
	public Documento(Integer id, String rgInc, String cpfCnpj, Date dateNascimento)
	{
		super();
		this.id = id;
		this.rgInc = rgInc;
		this.cpfCnpj = cpfCnpj;
		this.dateNascimento = dateNascimento;
	}

	/**
	 * Instantiates a new documento.
	 * 
	 * @param id the id
	 * @param rgInc the rg inc
	 * @param cpfCnpj the cpf cnpj
	 */
	public Documento(Integer id, String rgInc, String cpfCnpj)
	{
		super();
		this.id = id;
		this.rgInc = rgInc;
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getDocumenroid()
	{
		return documentoid;
	}

	/**
	 * Sets the id.
	 * 
	 * @param documenroid the new id
	 */
	public void setDocumenroid(Integer documenroid)
	{
		documentoid = documenroid;
	}

	/**
	 * Instantiates a new documento.
	 * 
	 * @param documenroid the documenroid
	 * @param id the id
	 * @param rgInc the rg inc
	 * @param cpfCnpj the cpf cnpj
	 * @param nome the nome
	 * @param razao the razao
	 * @param dateNascimento the date nascimento
	 */
	public Documento(Integer documenroid, Integer id, String rgInc, String cpfCnpj, String nome, String razao,
			Date dateNascimento)
	{
		super();
		documentoid = documenroid;
		this.id = id;
		this.rgInc = rgInc;
		this.cpfCnpj = cpfCnpj;
		this.razao = razao;
		this.dateNascimento = dateNascimento;
	}

	public TableTypeEnum getTabela()
	{
		return tabela;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 * 
	 * @return the table type value
	 */
	public Integer getTableTypeValue()
	{
		return tabela.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @param cadastroTypeValue the new table type value
	 */
	public void setTableTypeValue(Integer cadastroTypeValue)
	{
		tabela = TableTypeEnum.enumForValue(cadastroTypeValue);
	}

	public void setTabela(TableTypeEnum tabela)
	{
		this.tabela = tabela;
	}

	@Override
	public String toString()
	{
		return "Documento [getRazao()=" + getRazao() + ", getDocumentoid()="
				+ getDocumentoid() + ", getId()=" + getId() + ", getRgInc()=" + getRgInc() + ", getCpfCnpj()="
				+ getCpfCnpj() + ", getDateNascimento()=" + getDateNascimento() + ", getDocumenroid()="
				+ getDocumenroid() + ", getTabela()=" + getTabela() + ", getTableTypeValue()=" + getTableTypeValue()
				+ ", toString()=" + super.toString() + "]";
	}

}
