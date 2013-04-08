package com.sensus.mlc.gestao.model.request;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.gestao.model.Auditoria;


public class AuditoriaRequest extends LightSelectionRequest
{

    private Integer parentRetry;

    private Auditoria  auditoria;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	@Override
	public String toString() {
		return "AuditoriaRequest [parentRetry=" + parentRetry + ", auditoria="
				+ auditoria + ", getParentRetry()=" + getParentRetry()
				+ ", getAuditoria()=" + getAuditoria() + "]";
	}


}
