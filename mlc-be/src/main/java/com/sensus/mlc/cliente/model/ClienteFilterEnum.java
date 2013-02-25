package com.sensus.mlc.cliente.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum FilterEnum.
 * 
 * @author QAT Brazil.
 */
public enum ClienteFilterEnum implements IStringEnum
{

	/** The ADDRESS. */
	ADDRESS("ADDRESS"),
	
	 /** The CODEMP. */
	CODEMP("ml.codemp"),

	 /** The CODFILIAL. */
	CODFILIAL("ml.codfilial"),
	
	 /** The CODCLI. */
	CODCLI("ml.codcli"),
	
	 /** The CODEMPCC. */
	CODEMPCC("ml.codempcc"),
	
	 /** The CODFILIALCC. */
	CODFILIALCC("ml.codfilialcc"),
	
	 /** The CODCLASCLI. */
	CODCLASCLI("ml.codclascli"),
	
	 /** The CODEMPVD. */
	CODEMPVD("ml.codempvd"),
	
	 /** The CODFILIALVD. */
	CODFILIALVD("ml.codfilialvd"),
	
	 /** The CODVEND. */
	CODVEND("ml.codvend"),
	
	 /** The CODEMPTC. */
	CODEMPTC("ml.codemptc"),
	
	 /** The CODFILIALTC. */
	CODFILIALTC("ml.codfilialtc"),
	
	 /** The CODTIPOCOB. */
	CODTIPOCOB("ml.codtipocob"),
	
	 /** The CODEMPPG. */
	CODEMPPG("ml.codemppg"),
	
	 /** The CODFILIALPG. */
	CODFILIALPG("ml.codfilialpg"),
	
	 /** The CODPLANOPAG. */
	CODPLANOPAG("ml.codplanopag"),
	
	 /** The CODEMPTN. */
	CODEMPTN("ml.codemptn"),
	
	 /** The CODFILIALTN. */
	CODFILIALTN("ml.codfilialtn"),
	
	 /** The CODTRAN. */
	CODTRAN("ml.codtran"),
	
	 /** The CODEMPBO. */
	CODEMPBO("ml.codempbo"),
	
	 /** The CODFILIALBO. */
	CODFILIALBO("ml.codfilialbo"),
	
	 /** The RAZCLI. */
	RAZCLI("ml.razcli"),
	
	 /** The CODBANCO. */
	CODBANCO("ml.codbanco"),
	
	 /** The CODEMPSR. */
	CODEMPSR("ml.codempsr"),
	
	 /** The CODFILIALSR. */
	CODFILIALSR("ml.codfilialsr"),
	
	 /** The CODSETOR. */
	CODSETOR("ml.codsetor"),
	
	
	 /** The NOMECLI. */
	NOMECLI("ml.nomecli"),
	
	 /** The CODEMPTI. */
	CODEMPTI("ml.codempti"),
	
	 /** The CODFILIALTI. */
	CODFILIALTI("ml.codfilialti"),
	
	
	 /** The CODTIPOCLI. */
	CODTIPOCLI("ml.codtipocli"),
	
	
	/** The DATACLI. */
	DATACLI("ml.datacli"),
	
	
	 /** The PESSOACLI. */
	PESSOACLI("ml.pessoacli"),
	
	 /** The ATIVOCLI. */
	ATIVOCLI("ml.ativocli"),
	
	
	 /** The CNPJCLI. */
	CNPJCLI("ml.cnpjcli"),
	
	 /** The INSCCLI. */
	INSCCLI("ml.insccli"),
	
	 /** The CPFCLI. */
	CPFCLI("ml.cpfcli"),
	
	 /** The RGCLI. */
	RGCLI("ml.rgcli"),
	
	 /** The SSPCLI. */
	SSPCLI("ml.sspcli"),
	
	 /** The ENDCLI. */
	ENDCLI("ml.endcli"),
	
	 /** The NUMCLI. */
	NUMCLI("ml.numcli"),
	
	 /** The COMPLCLI. */
	COMPLCLI("ml.complcli"),
	
	 /** The BAIRCLI. */
	BAIRCLI("ml.baircli"),
	
	 /** The CIDCLI. */
	CIDCLI("ml.cidcli"),
	
	 /** The UFCLI. */
	UFCLI("ml.ufcli"),
	
	 /** The CEPCLI. */
	CEPCLI("ml.cepcli"),
	
	 /** The DDDCLI. */
	DDDCLI("ml.dddcli"),
	
	 /** The FONECLI. */
	FONECLI("ml.fonecli"),
	
	 /** The RAMALCLI. */
	RAMALCLI("ml.ramalcli"),
	
	 /** The DDDFAXCLI. */
	DDDFAXCLI("ml.dddfaxcli"),
	
	 /** The FAXCLI. */
	FAXCLI("ml.faxcli"),
	
	 /** The EMAILCLI. */
	EMAILCLI("ml.emailcli"),
	
	 /** The CONTCLI. */
	CONTCLI("ml.contcli"),
	
	 /** The ENDCOB. */
	ENDCOB("ml.endcob"),
	
	 /** The NUMCOB. */
	NUMCOB("ml.numcob"),
	
	 /** The COMPLCOB. */
	COMPLCOB("ml.complcob"),
	
	 /** The BAIRCOB. */
	BAIRCOB("ml.baircob"),
	
	 /** The CIDCOB. */
	CIDCOB("ml.cidcob"),
	
	 /** The UFCOB. */
	UFCOB("ml.ufcob"),
	
	 /** The CEPCOB. */
	CEPCOB("ml.cepcob"),
	
	 /** The DDDFONECOB. */
	DDDFONECOB("ml.dddfonecob"),
	
	 /** The FONECOB. */
	FONECOB("ml.fonecob"),
	
	 /** The DDDFAXCOB. */
	DDDFAXCOB("ml.dddfaxcob"),
	
	 /** The FAXCOB. */
	FAXCOB("ml.faxcob"),
	
	 /** The ENDENT. */
	ENDENT("ml.endent"),
	
	 /** The NUMENT. */
	NUMENT("ml.nument"),
	
	 /** The COMPLENT. */
	COMPLENT("ml.complent"),
	
	 /** The BAIRENT. */
	BAIRENT("ml.bairent"),
	
	 /** The CIDENT. */
	CIDENT("ml.cident"),
	
	 /** The UFENT. */
	UFENT("ml.ufent"),
	
	 /** The CEPENT. */
	CEPENT("ml.cepent"),
	
	 /** The DDDFONEENT. */
	DDDFONEENT("ml.dddfoneent"),
	
	 /** The FONEENT. */
	FONEENT("ml.foneent"),
	
	 /** The DDDFAXENT. */
	DDDFAXENT("ml.dddfaxent"),
	
	 /** The FAXENT. */
	FAXENT("ml.faxent"),
	
	 /** The OBSCLI. */
	OBSCLI("ml.obscli"),
	
	 /** The AGENCIACLI. */
	AGENCIACLI("ml.agenciacli"),
	
	 /** The CODEMPPQ. */
	CODEMPPQ("ml.codemppq"),
	
	 /** The CODFILIALPQ. */
	CODFILIALPQ("ml.codfilialpq"),
	
	 /** The CODPESQ. */
	CODPESQ("ml.codpesq"),
	
	 /** The INCRACLI. */
	INCRACLI("ml.incracli"),
	
	 /** The CODTPCRED. */
	CODTPCRED("ml.codtpcred"),
	
	 /** The CODFILIALTR. */
	CODFILIALTR("ml.codfilialtr"),
	
	 /** The CODEMPTR. */
	CODEMPTR("ml.codemptr"),
	
	/** The DTINITR. */
	DTINITR("ml.dtinitr"),
	
	/** The DTVENCTOTR. */
	DTVENCTOTR("ml.dtvenctotr"),
	
	 /** The NIRFCLI. */
	NIRFCLI("ml.nirfcli"),
	
	 /** The CODFISCCLI. */
	CODFISCCLI("ml.codfisccli"),
	
	 /** The CODEMPFC. */
	CODEMPFC("ml.codempfc"),
	
	 /** The CODFILIALFC. */
	CODFILIALFC("ml.codfilialfc"),
	
	 /** The NATCLI. */
	NATCLI("ml.natcli"),
	
	 /** The UFNATCLI. */
	UFNATCLI("ml.ufnatcli"),
	
	 /** The TEMPORESCLI. */
	TEMPORESCLI("ml.temporescli"),
	
	 /** The CODPAIS. */
	CODPAIS("ml.codpais"),
	
	 /** The APELIDOCLI. */
	APELIDOCLI("ml.apelidocli"),
	
	 /** The CODEMPEC. */
	CODEMPEC("ml.codempec"),
	
	 /** The CODFILIALEC. */
	CODFILIALEC("ml.codfilialec"),
	
	 /** The CODTBEC. */
	CODTBEC("ml.codtbec"),
	
	 /** The CODITTBEC. */
	CODITTBEC("ml.codittbec"),
	
	 /** The DTINS. */
	DTINS("ml.dtins"),
	
	 /** The IDUSUINS. */
	IDUSUINS("ml.idusuins"),
	
	 /** The DTALT. */
	DTALT("ml.dtalt"),
	
	 /** The IDUSUALT. */
	IDUSUALT("ml.idusualt"),
	
	 /** The HINS. */
	HINS("ml.hins"),
	
	 /** The HALT. */
	HALT("ml.halt"),
	
	 /** The SITECLI. */
	SITECLI("ml.sitecli"),
	
	 /** The CODCONTDEB. */
	CODCONTDEB("ml.codcontdeb"),
	
	 /** The CODCONTCRED. */
	CODCONTCRED("ml.codcontcred"),


	/** The CITY_NAME. */
	CITY_NAME("ml.fldCity"),

	/** The CONNECTIO n_ status. */
	CONNECTION_STATUS("mr.fldconnectionstatus"),

	/** The DEVICE_TYPE. */
	DEVICE_TYPE("vdt.fldDescription"),

	/** The DATE_FORMAT. */
	DATE_FORMAT("DATE_FORMAT"),

	/** The ENCRYPTIO status. */
	ENCRYPTION_STATUS("mr.encryptable"),

	/** The ENCRYPTION supported. */
	ENCRYPTION_SUPPORTED("mr.encrypted"),

	/** The ENGINEERING_VERSION. */
	ENGINEERING_VERSION("ENGINEERING_VERSION"),

	/** The FLEXNET_ID. */
	FLEXNET_ID("ml.fldRepId"),

	/** The GROUPS. */
	GROUPS("GROUP_ID"),

	/** The INSTALL_DATE. */
	INSTALL_DATE("INSTALL_DATE"),

	/** The INSTALL_DATE_START. */
	INSTALL_DATE_START("INSTALL_DATE_START"),

	/** The INSTALL_DATE_END. */
	INSTALL_DATE_END("INSTALL_DATE_END"),

	/** The LAS t_ rea d_ value. */
	LAST_READ_VALUE("LAST_READ_VALUE"),

	/** The LAS t_ rea d_ units. */
	LAST_READ_UNITS("LAST_READ_UNITS"),

	/** The LAS t_ rea d_ time. */
	LAST_READ_TIME("LAST_READ_TIME"),

	/** The LATITUDE. */
	LATITUDE("ml.fldLat"),

	/** The LIFECYCL e_ status. */
	LIFECYCLE_STATUS("LIFECYCLE_STATUS"),

	/** The LIFECYCL e_ state. */
	LIFECYCLE_STATE("ml.fldMeterLifecycleStateCode"),

	/** The LONGITUDE. */
	LONGITUDE("ml.fldLong"),

	/** The METE r_ firmware. */
	METER_FIRMWARE("ml.fldfirmwareflexnet"),

	/** The METER_ID. */
	METER_ID("ml.fldMeterId"),

	/** The MAJOR_VERSION. */
	MAJOR_VERSION("MAJOR_VERSION"),

	/** The MINOR_VERSION. */
	MINOR_VERSION("MINOR_VERSION"),

	/** The PATCH_VERSION. */
	PATCH_VERSION("PATCH_VERSION"),

	/** The PROCESSES. */
	PROCESSES("PROCESS"),

	/** The QUERY. */
	QUERY("QUERY"),

	/** The RADIO_FIRMWARE. */
	RADIO_FIRMWARE("RADIO_FIRMWARE"),

	/** The REMOT e_ disconnect. */
	REMOTE_DISCONNECT("ml.fldRemoteDisconnectAvailable"),

	/** The STREET. */
	STREET("ml.fldAddress"),

	/** The TAG. */
	TAG("TAG"),

	/** The TO u_ status. */
	TOU_STATUS("TOU_STATUS"),

	/** The TO u_ schedule. */
	TOU_SCHEDULE("TOU_SCHEDULE"),

	/** The TO u_ available. */
	TOU_AVAILABLE("TOU_AVAILABLE"),

	/** The ZIP_CODE. */
	ZIP_CODE("ml.fldZip");

	/** The property Id. */
	private String filterValue;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param paramFilterValue the param filter value
	 */
	private ClienteFilterEnum(String paramFilterValue)
	{
		filterValue = paramFilterValue;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return filterValue;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the property enum
	 */
	public static ClienteFilterEnum enumForValue(String value)
	{
		for (ClienteFilterEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		ClienteFilterEnum[] enums = ClienteFilterEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (ClienteFilterEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
