package com.sensus.lc.foto.model;

import com.sensus.lc.base.model.PrincipalClass;
import com.sensus.lc.base.model.TabelaEnum;

/**
 * The Class Foto.
 */
@SuppressWarnings("serial")
public class Foto extends PrincipalClass
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The cdfoto. */
	private Integer cdfoto;

	/** The id. */
	private Integer id;

	/** The coment. */
	private String nmfoto;

	/** The lcfoto. */
	private String lcfoto;

	/** The ttfoto. */
	private String ttfoto;

	private String comentario;

	/** The acao comentario enum. */
	private TabelaEnum acaoComentarioEnum;

	public Foto(String nmfoto, String lcfoto, String ttfoto, TabelaEnum acaoComentarioEnum)
	{
		super();
		this.nmfoto = nmfoto;
		this.lcfoto = lcfoto;
		this.ttfoto = ttfoto;
		this.acaoComentarioEnum = acaoComentarioEnum;
	}

	/**
	 * Gets the cdfoto.
	 * 
	 * @return the cdfoto
	 */
	public Integer getCdfoto()
	{
		return cdfoto;
	}

	public Foto()
	{
		super();
	}

	public Foto(Integer cdfoto, Integer id, String nmfoto, String lcfoto, String ttfoto, String comentario)
	{
		super();
		this.cdfoto = cdfoto;
		this.id = id;
		this.nmfoto = nmfoto;
		this.lcfoto = lcfoto;
		this.ttfoto = ttfoto;
		this.comentario = comentario;
	}

	/**
	 * Sets the cdfoto.
	 * 
	 * @param cdfoto the new cdfoto
	 */
	public void setCdfoto(Integer cdfoto)
	{
		this.cdfoto = cdfoto;
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
	 * Gets the nmfoto.
	 * 
	 * @return the nmfoto
	 */
	public String getNmfoto()
	{
		return nmfoto;
	}

	/**
	 * Sets the nmfoto.
	 * 
	 * @param nmfoto the new nmfoto
	 */
	public void setNmfoto(String nmfoto)
	{
		this.nmfoto = nmfoto;
	}

	/**
	 * Gets the lcfoto.
	 * 
	 * @return the lcfoto
	 */
	public String getLcfoto()
	{
		return lcfoto;
	}

	/**
	 * Sets the lcfoto.
	 * 
	 * @param lcfoto the new lcfoto
	 */
	public void setLcfoto(String lcfoto)
	{
		this.lcfoto = lcfoto;
	}

	/**
	 * Gets the ttfoto.
	 * 
	 * @return the ttfoto
	 */
	public String getTtfoto()
	{
		return ttfoto;
	}

	/**
	 * Sets the ttfoto.
	 * 
	 * @param ttfoto the new ttfoto
	 */
	public void setTtfoto(String ttfoto)
	{
		this.ttfoto = ttfoto;
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

	public String getComentario()
	{
		return comentario;
	}

	public void setComentario(String comentario)
	{
		this.comentario = comentario;
	}

	@Override
	public String toString()
	{
		return "Foto [getCdfoto()=" + getCdfoto() + ", getId()=" + getId() + ", getNmfoto()=" + getNmfoto()
				+ ", getLcfoto()=" + getLcfoto() + ", getTtfoto()=" + getTtfoto() + ", getAcaoComentarioEnum()="
				+ getAcaoComentarioEnum() + ", getComentario()=" + getComentario() + ", toString()=" + super.toString()
				+ "]";
	}
}
