package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Cnae;


public class CnaeResponse extends Response
{

    private Integer parentRetry;

    private List<Cnae> cnae ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Cnae> getCnae() {
		return cnae;
	}

	public void setCnae(List<Cnae> cnae) {
		this.cnae = cnae;
	}


}
