package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.gestao.model.Auditoria;


public class AuditoriaResponse extends Response
{

    private Integer parentRetry;

    private List<Auditoria> auditoria ;

	public Integer getParentRetry() {
		return parentRetry;
	}

	public void setParentRetry(Integer parentRetry) {
		this.parentRetry = parentRetry;
	}

	public List<Auditoria> getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(List<Auditoria> auditoria) {
		this.auditoria = auditoria;
	}

	@Override
	public String toString() {
		return "AuditoriaResponse [parentRetry=" + parentRetry + ", auditoria="
				+ auditoria + ", getParentRetry()=" + getParentRetry()
				+ ", getAuditoria()=" + getAuditoria() + "]";
	}

}
