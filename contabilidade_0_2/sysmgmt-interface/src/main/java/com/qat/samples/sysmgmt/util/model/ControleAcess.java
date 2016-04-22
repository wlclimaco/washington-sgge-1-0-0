package com.qat.samples.sysmgmt.util.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "controleAcess", propOrder = {"controleid", "local", "data", "tenantId", "userId"
		, "tableEnum", "acaoId", "acao"})
public class ControleAcess extends ModelCosmeDamiao
{

	/** The id. */
	private Integer controleid;

	/** The description. */
	private String local;

	/** The data. */
	private Date data;

	/** The tenant id. */
	private Integer tenantId;

	/** The user id. */
	private String userId;

	/** The table enum. */
	private TableTypeEnum tableEnum;

	/** The acao id. */
	private Integer acaoId;

	/** The acao. */
	private AcaoTypeEnum acao;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
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
	 * Gets the acao id.
	 *
	 * @return the acao id
	 */
	public Integer getAcaoId()
	{
		return acaoId;
	}

	/**
	 * Sets the acao id.
	 *
	 * @param acaoId the new acao id
	 */
	public void setAcaoId(Integer acaoId)
	{
		this.acaoId = acaoId;
	}

	/**
	 * Instantiates a new bundle.
	 */
	public ControleAcess()
	{

	}

	/**
	 * Instantiates a new controle acess.
	 *
	 * @param controleid the controleid
	 * @param user the user
	 * @param tenantId the tenant id
	 */
	public ControleAcess(Integer controleid, Integer tenantId)
	{
		super();
		this.controleid = controleid;
		this.tenantId = tenantId;
	}

	/**
	 * Instantiates a new controle acess.
	 *
	 * @param controleid the controleid
	 * @param user the user
	 * @param local the local
	 * @param data the data
	 * @param tenantId the tenant id
	 * @param acao the acao
	 */
	public ControleAcess(Integer controleid, String local, Date data, Integer tenantId, AcaoTypeEnum acao)
	{
		super();
		this.controleid = controleid;
		this.local = local;
		this.data = data;
		this.tenantId = tenantId;
		this.acao = acao;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getControleid()
	{
		return controleid;
	}

	/**
	 * Sets the id.
	 *
	 * @param controleid the new id
	 */
	public void setControleid(Integer controleid)
	{
		this.controleid = controleid;
	}

	/**
	 * Instantiates a new controle acess.
	 *
	 * @param controleid the controleid
	 * @param data the data
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @param tableEnum the table enum
	 * @param acaoId the acao id
	 * @param acao the acao
	 */
	public ControleAcess(Integer controleid, Date data, Integer tenantId, String userId, TableTypeEnum tableEnum,
			Integer acaoId, AcaoTypeEnum acao)
	{
		super();
		this.controleid = controleid;
		this.data = data;
		this.tenantId = tenantId;
		this.userId = userId;
		this.tableEnum = tableEnum;
		this.acaoId = acaoId;
		this.acao = acao;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getLocal()
	{
		return local;
	}

	/**
	 * Sets the description.
	 *
	 * @param local the new description
	 */
	public void setLocal(String local)
	{
		this.local = local;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Date getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Date data)
	{
		this.data = data;
	}

	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

	/**
	 * Gets the acao.
	 *
	 * @return the acao
	 */
	public AcaoTypeEnum getAcao()
	{
		return acao;
	}

	/**
	 * Sets the acao.
	 *
	 * @param acao the new acao
	 */
	public void setAcao(AcaoTypeEnum acao)
	{
		this.acao = acao;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 *
	 * @return the acao type value
	 */
	public Integer getAcaoTypeValue()
	{
		return acao.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param cadastroTypeValue the new acao type value
	 */
	public void setAcaoTypeValue(Integer cadastroTypeValue)
	{
		acao = AcaoTypeEnum.enumForValue(cadastroTypeValue);
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

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.QATModel#toString()
	 */
	@Override
	public String toString()
	{
		return "ControleAcess [getUserId()=" + getUserId() + ", getTableEnum()=" + getTableEnum() + ", getAcaoId()="
				+ getAcaoId() + ", getControleid()=" + getControleid() + ", getLocal()="
				+ getLocal() + ", getData()=" + getData() + ", getTenantId()=" + getTenantId() + ", getAcao()="
				+ getAcao() + ", getAcaoTypeValue()=" + getAcaoTypeValue() + ", getTableTypeValue()="
				+ getTableTypeValue() + ", toString()=" + super.toString() + "]";
	}

}
