package com.qat.samples.complexmo.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.samples.complexmo.dac.IStateDAC;
import com.qat.samples.complexmo.model.State;

/**
 * The Class StateDACImpl.
 */
public class StateDACImpl extends SqlSessionDaoSupport implements IStateDAC
{
	private static final String NAMESPACE = "StateMap.";
	private static final String STMT_FETCH = NAMESPACE + "fetchStateByCode";

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.IStateDAC#fetchStateByCode(java.lang.String)
	 */
	@Override
	public State fetchStateByCode(String code)
	{
		return (State)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), STMT_FETCH, code);
	}

}
