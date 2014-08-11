package com.qat.samples.sysmgmt.util;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Filter", propOrder = {"listId", "tableEnum", "nome"})
public class Filter extends Util
{

	private String nome;
	/** The nome. */
	private List<Integer> listId;

	private TableTypeEnum tableEnum;

	/**
	 * Instantiates a new imagem.
	 */
	public Filter()
	{

	}

	public List<Integer> getListId()
	{
		return listId;
	}

	public void setListId(List<Integer> listId)
	{
		this.listId = listId;
	}

	/**
	 * Gets the table enum.
	 * 
	 * @return the table enum
	 */
	public TableTypeEnum getTableEnum()
	{
		return tableEnum;
	}

	/**
	 * Sets the table enum.
	 * 
	 * @param tableEnum the new table enum
	 */
	public void setTableEnum(TableTypeEnum tableEnum)
	{
		this.tableEnum = tableEnum;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 * 
	 * @return the table type value
	 */
	public Integer getTableTypeValue()
	{
		return tableEnum.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @param cadastroTypeValue the new table type value
	 */
	public void setTableTypeValue(Integer cadastroTypeValue)
	{
		tableEnum = TableTypeEnum.enumForValue(cadastroTypeValue);
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Filter(List<Integer> listId, TableTypeEnum tableEnum)
	{
		super();
		this.listId = listId;
		this.tableEnum = tableEnum;
	}

	@Override
	public String toString()
	{
		return "Filter [getListId()=" + getListId() + ", getTableEnum()=" + getTableEnum() + ", getTableTypeValue()="
				+ getTableTypeValue() + ", getNome()=" + getNome() + "]";
	}

}
