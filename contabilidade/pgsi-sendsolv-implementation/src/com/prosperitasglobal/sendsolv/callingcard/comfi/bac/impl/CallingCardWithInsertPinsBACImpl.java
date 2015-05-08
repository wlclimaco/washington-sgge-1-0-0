package com.prosperitasglobal.sendsolv.callingcard.comfi.bac.impl;

import org.w3c.dom.Node;

import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * Class used to extend the functionality of CallingCardBACImpl with the ability to insert
 * new pins retrieved into the database by calling the ICallingCardDAC.insertCallingCard.
 * This class should be used in place of CallingCardBACImpl if insertion of new pins retrieved
 * is to be done online (through the web app).
 *
 * @author QATEmployee
 *
 */
public class CallingCardWithInsertPinsBACImpl extends CallingCardBACImpl implements ICallingCardBAC
{
	/**
	 * Calls to the same method in super (CallingCardBACImpl). Then it invokes functionality
	 * to insert the pins retrieved into the database.
	 *
	 */
	@Override
	protected CallingCardInfo processPinInfo(Node rowNode, String lotCode,
			InternalResultsResponse<CallingCardInfo> response)
	{
		CallingCardInfo cardInfo = super.processPinInfo(rowNode, lotCode, response);

		if (response.isInError())
		{
			return cardInfo;
		}

		InternalResultsResponse<CallingCardInfo> insertResponse = insertCallingCard(cardInfo);

		if (insertResponse.isInError())
		{
			response.merge(insertResponse);

			return cardInfo;
		}
		else
		{
			cardInfo = response.getFirstResult();
		}

		return cardInfo;
	}

	private InternalResultsResponse<CallingCardInfo> insertCallingCard(CallingCardInfo callingCardInfo)
	{
		callingCardInfo.setModelAction(PersistanceActionEnum.INSERT);

		CallingCardMaintenanceRequest request = new CallingCardMaintenanceRequest();
		request.setCallingCardInfo(callingCardInfo);

		applyContextParameters(request);

		return getCallingCardDAC().insertCallingCard(request);
	}
}
