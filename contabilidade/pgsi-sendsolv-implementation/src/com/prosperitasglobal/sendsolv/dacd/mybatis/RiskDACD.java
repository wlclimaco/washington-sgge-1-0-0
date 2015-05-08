package com.prosperitasglobal.sendsolv.dacd.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY static methods so everything must be
 * passed into the methods. Nothing injected.
 */
public final class RiskDACD extends SqlSessionDaoSupport
{

	/** The Constant ORGANIZATION_NAMESPACE. */
	private static final String ORGANIZATION_NAMESPACE = "OrganizationMap.";

	/** The Constant RISK_STMT_VERSION. */
	private static final String RISK_STMT_VERSION = ORGANIZATION_NAMESPACE + "fetchVersionNumberRisk";

	/** The Constant ORGANIZATION_STMT_UPDATE_RISK. */
	private static final String ORGANIZATION_STMT_UPDATE_RISK = ORGANIZATION_NAMESPACE + "updateRisk";

	/**
	 * Update risk.
	 *
	 * @param sqlSession the sql session
	 * @param request the request
	 * @return the internal results response< risk>
	 */
	public static InternalResultsResponse<Risk> updateRisk(SqlSession sqlSession, RiskMaintenanceRequest request)
	{
		InternalResultsResponse<Risk> response = new InternalResultsResponse<Risk>();

		QATMyBatisDacHelper.doUpdateOL(sqlSession, ORGANIZATION_STMT_UPDATE_RISK, request.getRisk(),
				RISK_STMT_VERSION, response);

		return response;
	}
}
