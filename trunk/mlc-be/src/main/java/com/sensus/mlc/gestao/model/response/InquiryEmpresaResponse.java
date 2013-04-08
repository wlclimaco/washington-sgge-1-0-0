package com.sensus.mlc.empresa.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryEmpresaResponse extends InquiryResponse
{
    private List<Empresa> empresa ;


    public List<Empresa> getEmpresa() {
      return empresa;
    }


    public void setEmpresa(List<Empresa> empresa) {
        this.empresa = empresa;
}



    public String toString() {
    return "InquiryEmpresaResponse [empresa=" + empresa
      + ", getEmpresa()=" + getEmpresa()
    + "]";
    }

}
