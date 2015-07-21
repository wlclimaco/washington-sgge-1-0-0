package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class HistoricoNF extends ModelCosmeDamiao
{

	private Integer id;

	private Integer notaId;

	private Long data;

	private List<NotaTypeEnum> notaTypeEnumList;

	public HistoricoNF(Long data, List<NotaTypeEnum> notaTypeEnumList)
	{
		super();
		this.data = data;
		this.notaTypeEnumList = notaTypeEnumList;
	}

	public HistoricoNF()
	{
		super();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getNotaId()
	{
		return notaId;
	}

	public void setNotaId(Integer notaId)
	{
		this.notaId = notaId;
	}

	public Long getData()
	{
		return data;
	}

	public void setData(Long data)
	{
		this.data = data;
	}

	public List<NotaTypeEnum> getNotaTypeEnumList()
	{
		return notaTypeEnumList;
	}

	public void setNotaTypeEnumList(List<NotaTypeEnum> notaTypeEnumList)
	{
		this.notaTypeEnumList = notaTypeEnumList;
	}

}
