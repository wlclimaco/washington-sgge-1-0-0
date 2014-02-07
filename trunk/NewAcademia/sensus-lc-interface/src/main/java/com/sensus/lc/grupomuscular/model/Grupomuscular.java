package com.sensus.lc.grupomuscular.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class Grupomuscular.
 */
@SuppressWarnings("serial")
public class Grupomuscular extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The cdgrmusc. */
	private Integer cdgrmusc;

	/** The musculo. */
	private String musculo;

	/** The dsgrmusc. */
	private String dsgrmusc;

	/** The createdate. */
	private Date createdate;

	/** The createuser. */
	private String createuser;

	/** The tenantid. */
	private Integer tenantid;

	/** The tenantid. */
	private Integer userid;

	/**
	 * Instantiates a new grupomuscular.
	 */
	public Grupomuscular()
	{
		super();
	}

	/**
	 * Instantiates a new grupomuscular.
	 * 
	 * @param cdgrmusc the cdgrmusc
	 * @param musculo the musculo
	 * @param dsgrmusc the dsgrmusc
	 */
	public Grupomuscular(Integer cdgrmusc, String musculo, String dsgrmusc)
	{
		super();
		this.cdgrmusc = cdgrmusc;
		this.musculo = musculo;
		this.dsgrmusc = dsgrmusc;
	}

	public Grupomuscular(Integer cdgrmusc, Integer tenantid, Integer userid)
	{
		super();
		this.cdgrmusc = cdgrmusc;
		this.tenantid = tenantid;
		this.userid = userid;
	}

	public Grupomuscular(Integer cdgrmusc, Integer userid)
	{
		super();
		this.cdgrmusc = cdgrmusc;
		this.userid = userid;
	}

	public Grupomuscular(Integer cdgrmusc, String musculo, String dsgrmusc, Date createdate, String createuser,
			Integer tenantid, Integer userid)
	{
		super();
		this.cdgrmusc = cdgrmusc;
		this.musculo = musculo;
		this.dsgrmusc = dsgrmusc;
		this.createdate = createdate;
		this.createuser = createuser;
		this.tenantid = tenantid;
		this.userid = userid;
	}

	/**
	 * Instantiates a new grupomuscular.
	 * 
	 * @param cdgrmusc the cdgrmusc
	 */
	public Grupomuscular(Integer cdgrmusc)
	{
		super();
		this.cdgrmusc = cdgrmusc;
	}

	/**
	 * Gets the cdgrmusc.
	 * 
	 * @return the cdgrmusc
	 */
	public Integer getCdgrmusc()
	{
		return cdgrmusc;
	}

	/**
	 * Sets the cdgrmusc.
	 * 
	 * @param cdgrmusc the new cdgrmusc
	 */
	public void setCdgrmusc(Integer cdgrmusc)
	{
		this.cdgrmusc = cdgrmusc;
	}

	/**
	 * Gets the musculo.
	 * 
	 * @return the musculo
	 */
	public String getMusculo()
	{
		return musculo;
	}

	/**
	 * Sets the musculo.
	 * 
	 * @param musculo the new musculo
	 */
	public void setMusculo(String musculo)
	{
		this.musculo = musculo;
	}

	/**
	 * Gets the dsgrmusc.
	 * 
	 * @return the dsgrmusc
	 */
	public String getDsgrmusc()
	{
		return dsgrmusc;
	}

	/**
	 * Sets the dsgrmusc.
	 * 
	 * @param dsgrmusc the new dsgrmusc
	 */
	public void setDsgrmusc(String dsgrmusc)
	{
		this.dsgrmusc = dsgrmusc;
	}

	/**
	 * Gets the createdate.
	 * 
	 * @return the createdate
	 */
	public Date getCreatedate()
	{
		return createdate;
	}

	/**
	 * Sets the createdate.
	 * 
	 * @param createdate the new createdate
	 */
	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
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
		return "Grupomuscular [getCdgrmusc()=" + getCdgrmusc() + ", getMusculo()=" + getMusculo() + ", getDsgrmusc()="
				+ getDsgrmusc() + ", getCreatedate()=" + getCreatedate() + ", getCreateuser()=" + getCreateuser()
				+ ", getTenantid()=" + getTenantid() + ", getUserid()=" + getUserid() + ", toString()="
				+ super.toString() + "]";
	}

}
