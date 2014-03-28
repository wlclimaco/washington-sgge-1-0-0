package com.sensus.lc.video.model;

import com.sensus.lc.base.model.PrincipalClass;
import com.sensus.lc.base.model.TabelaEnum;

/**
 * The Class Video.
 */
@SuppressWarnings("serial")
public class Video extends PrincipalClass
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The cdVideo. */
	private Integer cdVideo;

	/** The id. */
	private Integer id;

	/** The coment. */
	private String nmVideo;

	/** The lcVideo. */
	private String lcVideo;

	/** The ttVideo. */
	private String ttVideo;

	private String comentario;

	/** The acao comentario enum. */
	private TabelaEnum acaoComentarioEnum;

	public Video(String nmVideo, String lcVideo, String ttVideo, TabelaEnum acaoComentarioEnum)
	{
		super();
		this.nmVideo = nmVideo;
		this.lcVideo = lcVideo;
		this.ttVideo = ttVideo;
		this.acaoComentarioEnum = acaoComentarioEnum;
	}

	/**
	 * Gets the cdVideo.
	 * 
	 * @return the cdVideo
	 */
	public Integer getCdVideo()
	{
		return cdVideo;
	}

	public Video()
	{
		super();
	}

	public Video(Integer cdVideo, Integer id, String nmVideo, String lcVideo, String ttVideo, String comentario)
	{
		super();
		this.cdVideo = cdVideo;
		this.id = id;
		this.nmVideo = nmVideo;
		this.lcVideo = lcVideo;
		this.ttVideo = ttVideo;
		this.comentario = comentario;
	}

	/**
	 * Sets the cdVideo.
	 * 
	 * @param cdVideo the new cdVideo
	 */
	public void setCdVideo(Integer cdVideo)
	{
		this.cdVideo = cdVideo;
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
	 * Gets the nmVideo.
	 * 
	 * @return the nmVideo
	 */
	public String getNmVideo()
	{
		return nmVideo;
	}

	/**
	 * Sets the nmVideo.
	 * 
	 * @param nmVideo the new nmVideo
	 */
	public void setNmVideo(String nmVideo)
	{
		this.nmVideo = nmVideo;
	}

	/**
	 * Gets the lcVideo.
	 * 
	 * @return the lcVideo
	 */
	public String getLcVideo()
	{
		return lcVideo;
	}

	/**
	 * Sets the lcVideo.
	 * 
	 * @param lcVideo the new lcVideo
	 */
	public void setLcVideo(String lcVideo)
	{
		this.lcVideo = lcVideo;
	}

	/**
	 * Gets the ttVideo.
	 * 
	 * @return the ttVideo
	 */
	public String getTtVideo()
	{
		return ttVideo;
	}

	/**
	 * Sets the ttVideo.
	 * 
	 * @param ttVideo the new ttVideo
	 */
	public void setTtVideo(String ttVideo)
	{
		this.ttVideo = ttVideo;
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
		return "Video [getCdVideo()=" + getCdVideo() + ", getId()=" + getId() + ", getNmVideo()=" + getNmVideo()
				+ ", getLcVideo()=" + getLcVideo() + ", getTtVideo()=" + getTtVideo() + ", getAcaoComentarioEnum()="
				+ getAcaoComentarioEnum() + ", getComentario()=" + getComentario() + ", toString()=" + super.toString()
				+ "]";
	}
}
