package com.prosperitasglobal.sendsolv.sdn.unittest.util;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnFieldEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchField;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;

/**
 * The Class CommonTestRoutines.
 */
public final class CommonSdnTestRoutines
{
	/**
	 * The Constructor.
	 */
	private CommonSdnTestRoutines()
	{

	}

	public static SdnStatusHistory mockHistory(SDNStatusEnum sdnStatus)
	{
		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setSdnStatus(sdnStatus);

		sdnStatusHistory.setSdnMatchRecordList(new ArrayList<SdnMatchRecord>());

		SdnMatchRecord sdnMatchRecord = new SdnMatchRecord();
		sdnMatchRecord.setXmlRecord("XML1");

		List<SdnMatchField> fields = new ArrayList<SdnMatchField>();
		fields.add(new SdnMatchField(SdnFieldEnum.FIRST_NAME, 0.95, "Bill", "pgsiBill", 1));
		fields.add(new SdnMatchField(SdnFieldEnum.LAST_NAME, 0.97, "Gates", "pgsiGates", 2));
		sdnMatchRecord.setSdnMatchFieldList(fields);

		SdnMatchRecord sdnMatchRecord2 = new SdnMatchRecord();
		sdnMatchRecord2.setXmlRecord("XML2");

		List<SdnMatchField> fields2 = new ArrayList<SdnMatchField>();
		fields2.add(new SdnMatchField(SdnFieldEnum.FIRST_NAME, 0.95, "Warren", "pgsiWarren", 3));
		fields2.add(new SdnMatchField(SdnFieldEnum.LAST_NAME, 0.97, "Buffet", "pgsiBuffet", 4));
		sdnMatchRecord2.setSdnMatchFieldList(fields2);

		sdnStatusHistory.getSdnMatchRecordList().add(sdnMatchRecord);
		sdnStatusHistory.getSdnMatchRecordList().add(sdnMatchRecord2);

		return sdnStatusHistory;
	}

	/**
	 * Create a dummy sdn status history.
	 * 
	 * @param status
	 * @return
	 */
	public static SdnStatusHistory createDummySdnStatusHistory(SDNStatusEnum status)
	{
		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setSdnStatus(status);

		return sdnStatusHistory;
	}
}
