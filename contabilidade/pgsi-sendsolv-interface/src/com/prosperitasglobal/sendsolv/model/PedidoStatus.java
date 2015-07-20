package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PedidoStatus extends ModelCosmeDamiao
{

	private Integer id;

	private Integer notaId;

	private Long data;

	private List<NotaTypeEnum> notaTypeEnumList;

}
