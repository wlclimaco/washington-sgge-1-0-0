package com.sensus.lc.academia.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Academia.
 */
@SuppressWarnings("serial")
public class Academia extends SensusModel
{

	/** The cdacad. */
	private Integer cdacad;

	/** The academ. */
	private String academ;

	/** The lograd. */
	private String lograd;

	/** The numen. */
	private String numen;

	/** The bairr. */
	private String bairr;

	/** The cidade. */
	private String cidade;

	/** The cep. */
	private String cep;

	/** The telef. */
	private String telef;

	/** The create date. */
	private Date dataini;

	/** The modify date. */
	private Date datafin;

	/** The createdate. */
	private Date createdate;

	/** The createuser. */
	private String createuser;

	/** The tenantid. */
	private Integer tenantid;

	/** The tenantid. */
	private Integer userid;

	/** The latitude. */
	private Double latitude;

	/** The longitude. */
	private Double longitude;

	/** The atual. */
	private Boolean atual;

	/**
	 * Instantiates a new academia.
	 * 
	 * @param cdacad the cdacad
	 */
	public Academia(Integer cdacad)
	{
		super();
		this.cdacad = cdacad;
	}

	/**
	 * Instantiates a new academia.
	 */
	public Academia()
	{

	}

	/**
	 * Gets the cdacad.
	 * 
	 * @return the cdacad
	 */
	public Integer getCdacad()
	{
		return cdacad;
	}

	/**
	 * Gets the createuser.
	 * 
	 * @return the createuser
	 */
	public String getCreateuser()
	{
		return createuser;
	}

	/**
	 * Sets the createuser.
	 * 
	 * @param createuser the new createuser
	 */
	public void setCreateuser(String createuser)
	{
		this.createuser = createuser;
	}

	/**
	 * Gets the tenantid.
	 * 
	 * @return the tenantid
	 */
	public Integer getTenantid()
	{
		return tenantid;
	}

	/**
	 * Sets the tenantid.
	 * 
	 * @param tenantid the new tenantid
	 */
	public void setTenantid(Integer tenantid)
	{
		this.tenantid = tenantid;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public Double getLatitude()
	{
		return latitude;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude the new latitude
	 */
	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public Double getLongitude()
	{
		return longitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the new longitude
	 */
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * Sets the cdacad.
	 * 
	 * @param cdacad the new cdacad
	 */
	public void setCdacad(Integer cdacad)
	{
		this.cdacad = cdacad;
	}

	/**
	 * Gets the academ.
	 * 
	 * @return the academ
	 */
	public String getAcadem()
	{
		return academ;
	}

	/**
	 * Sets the academ.
	 * 
	 * @param academ the new academ
	 */
	public void setAcadem(String academ)
	{
		this.academ = academ;
	}

	/**
	 * Gets the lograd.
	 * 
	 * @return the lograd
	 */
	public String getLograd()
	{
		return lograd;
	}

	/**
	 * Sets the lograd.
	 * 
	 * @param lograd the new lograd
	 */
	public void setLograd(String lograd)
	{
		this.lograd = lograd;
	}

	/**
	 * Gets the numen.
	 * 
	 * @return the numen
	 */
	public String getNumen()
	{
		return numen;
	}

	/**
	 * Sets the numen.
	 * 
	 * @param numen the new numen
	 */
	public void setNumen(String numen)
	{
		this.numen = numen;
	}

	/**
	 * Gets the bairr.
	 * 
	 * @return the bairr
	 */
	public String getBairr()
	{
		return bairr;
	}

	/**
	 * Sets the bairr.
	 * 
	 * @param bairr the new bairr
	 */
	public void setBairr(String bairr)
	{
		this.bairr = bairr;
	}

	/**
	 * Gets the cidade.
	 * 
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * Sets the cidade.
	 * 
	 * @param cidade the new cidade
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * Gets the cep.
	 * 
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * Sets the cep.
	 * 
	 * @param cep the new cep
	 */
	public void setCep(String cep)
	{
		this.cep = cep;
	}

	/**
	 * Gets the telef.
	 * 
	 * @return the telef
	 */
	public String getTelef()
	{
		return telef;
	}

	/**
	 * Sets the telef.
	 * 
	 * @param telef the new telef
	 */
	public void setTelef(String telef)
	{
		this.telef = telef;
	}

	/**
	 * Gets the atual.
	 * 
	 * @return the atual
	 */
	public Boolean getAtual()
	{
		return atual;
	}

	/**
	 * Sets the atual.
	 * 
	 * @param atual the new atual
	 */
	public void setAtual(Boolean atual)
	{
		this.atual = atual;
	}

	public Date getDataini()
	{
		return dataini;
	}

	public void setDataini(Date dataini)
	{
		this.dataini = dataini;
	}

	public Date getDatafin()
	{
		return datafin;
	}

	public void setDatafin(Date datafin)
	{
		this.datafin = datafin;
	}

	public Date getCreatedate()
	{
		return createdate;
	}

	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}

	/**
	 * Gets the userid.
	 * 
	 * @return the userid
	 */
	public Integer getUserid()
	{
		return userid;
	}

	/**
	 * Sets the userid.
	 * 
	 * @param userid the new userid
	 */
	public void setUserid(Integer userid)
	{
		this.userid = userid;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Academia [getCdacad()=" + getCdacad() + ", getCreateuser()=" + getCreateuser() + ", getTenantid()="
				+ getTenantid() + ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude()
				+ ", getAcadem()=" + getAcadem() + ", getLograd()=" + getLograd() + ", getNumen()=" + getNumen()
				+ ", getBairr()=" + getBairr() + ", getCidade()=" + getCidade() + ", getCep()=" + getCep()
				+ ", getTelef()=" + getTelef() + ", getAtual()=" + getAtual() + ", getDataini()=" + getDataini()
				+ ", getDatafin()=" + getDatafin() + ", getCreatedate()=" + getCreatedate() + ", getUserid()="
				+ getUserid() + "]";
	}

}
