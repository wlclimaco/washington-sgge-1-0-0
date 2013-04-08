package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Unimed;


public class UnimedRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Unimed  unimed;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Unimed getUnimed() {
		return unimed;
	}

	public void setUnimed(Unimed unimed) {
		this.unimed = unimed;
	}

	@Override
	public String toString() {
		return "UnimedRequest [parentRetry=" + parentRetry + ", unimed="
				+ unimed + ", getParentRetry()=" + getParentRetry()
				+ ", getUnimed()=" + getUnimed() + "]";
	}


}
