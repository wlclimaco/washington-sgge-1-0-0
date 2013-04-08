package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Embalagens;


public class EmbalagensRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Embalagens  embalagens;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Embalagens getEmbalagens() {
		return embalagens;
	}

	public void setEmbalagens(Embalagens embalagens) {
		this.embalagens = embalagens;
	}

	@Override
	public String toString() {
		return "EmbalagensRequest [parentRetry=" + parentRetry
				+ ", embalagens=" + embalagens + ", getParentRetry()="
				+ getParentRetry() + ", getEmbalagens()=" + getEmbalagens()
				+ "]";
	}


}
