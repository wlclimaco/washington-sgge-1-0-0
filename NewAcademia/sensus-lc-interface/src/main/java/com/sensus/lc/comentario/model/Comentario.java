package com.sensus.lc.comentario.model;

import com.sensus.lc.base.model.PrincipalClass;
import com.sensus.lc.base.model.TabelaEnum;

/**
 * The Class Comentario.
 */
@SuppressWarnings("serial")
public class Comentario extends PrincipalClass
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The cdcomentario. */
	private Integer cdcomentario;

	/** The coment. */
	private String coment;

	/** The id. */
	private Integer id;

	/** The acao comentario enum. */
	private TabelaEnum acaoComentarioEnum;

	/**
	 * Instantiates a new comentario.
	 * 
	 * @param cdcomentario the cdcomentario
	 * @param coment the coment
	 * @param id the id
	 * @param acaoComentarioEnum the acao comentario enum
	 */
	public Comentario(Integer cdcomentario, String coment, Integer id, TabelaEnum acaoComentarioEnum)
	{
		super();
		this.cdcomentario = cdcomentario;
		this.coment = coment;
		this.id = id;
		this.acaoComentarioEnum = acaoComentarioEnum;
	}

	/**
	 * Instantiates a new comentario.
	 */
	public Comentario()
	{
		super();
	}

	/**
	 * Gets the cdcomentario.
	 * 
	 * @return the cdcomentario
	 */
	public Integer getCdcomentario()
	{
		return cdcomentario;
	}

	/**
	 * Sets the cdcomentario.
	 * 
	 * @param cdcomentario the new cdcomentario
	 */
	public void setCdcomentario(Integer cdcomentario)
	{
		this.cdcomentario = cdcomentario;
	}

	/**
	 * Gets the acao comentario enum.
	 * 
	 * @return the acao comentario enum
	 */
	public TabelaEnum getAcaoComentarioEnum()
	{
		return acaoComentarioEnum;
	}

	/**
	 * Sets the acao comentario enum.
	 * 
	 * @param acaoComentarioEnum the new acao comentario enum
	 */
	public void setAcaoComentarioEnum(TabelaEnum acaoComentarioEnum)
	{
		this.acaoComentarioEnum = acaoComentarioEnum;
	}

	/**
	 * Gets the cdfoto.
	 * 
	 * @return the cdfoto
	 */
	public Integer getCdfoto()
	{
		return cdcomentario;
	}

	/**
	 * Sets the cdfoto.
	 * 
	 * @param cdfoto the new cdfoto
	 */
	public void setCdfoto(Integer cdfoto)
	{
		cdcomentario = cdfoto;
	}

	/**
	 * Gets the coment.
	 * 
	 * @return the coment
	 */
	public String getComent()
	{
		return coment;
	}

	/**
	 * Sets the coment.
	 * 
	 * @param coment the new coment
	 */
	public void setComent(String coment)
	{
		this.coment = coment;
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

	@Override
	public String toString()
	{
		return "Comentario [getCdcomentario()=" + getCdcomentario() + ", getAcaoComentarioEnum()="
				+ getAcaoComentarioEnum() + ", getCdfoto()=" + getCdfoto() + ", getComent()=" + getComent()
				+ ", getId()=" + getId() + ", toString()=" + super.toString() + "]";
	}
}
