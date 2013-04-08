package com.sensus.mlc.auditoria.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryAuditoriaResponse extends InquiryResponse
{
    private List<Auditoria> auditoria ;


    public List<Auditoria> getAuditoria() {
      return auditoria;
    }


    public void setAuditoria(List<Auditoria> auditoria) {
        this.auditoria = auditoria;
}



    public String toString() {
    return "InquiryAuditoriaResponse [auditoria=" + auditoria
      + ", getAuditoria()=" + getAuditoria()
    + "]";
    }

}
