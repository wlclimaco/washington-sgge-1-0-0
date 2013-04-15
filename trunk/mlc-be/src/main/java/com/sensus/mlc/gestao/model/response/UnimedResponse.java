package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Unimed;


public class UnimedResponse extends Response
{

    private Integer parentRetry;

    private List<Unimed> unimed ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Unimed> getUnimed() {
		return unimed;
	}

	public void setUnimed(List<Unimed> unimed) {
		this.unimed = unimed;
	}

	@Override
	public String toString() {
		return "UnimedResponse [parentRetry=" + parentRetry + ", unimed="
				+ unimed + ", getParentRetry()=" + getParentRetry()
				+ ", getUnimed()=" + getUnimed() + "]";
	}


}
