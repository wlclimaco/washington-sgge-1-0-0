package com.prosperitasglobal.sendsolv.sdn.dac;

import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchField;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ILocationDAC.
 */
public interface ISdnCheckerDAC
{

	public Integer insertSdnMatchRecord(SdnMatchRecord sdnMatchRecord, InternalResponse response);

	public Integer insertSdnMatchField(SdnMatchField sdnMatchField, InternalResponse response);

	public InternalResponse insertSdnStatusHistory(SdnStatusHistory sdnStatusHistory, SdnMatch request);

	public InternalResultsResponse<SdnStatusHistory> fetchCurrentSdnStatusHistory(SdnMatchTypeEnum sdnMatchTypeEnum,
			Integer parentKey);

	public InternalResultsResponse<SdnStatusHistory> fetchFullSdnStatusHistory(SdnMatchTypeEnum sdnMatchTypeEnum,
			Integer parentKey);

	public InternalResponse updateSdnHistory(SdnStatusHistory sdnStatusHistory);

	public InternalResultsResponse<SdnHistory> fetchSdnStatusHistoryByRequest(
			SdnStatusHistoryInquiryRequest request);

}
