package com.sensus.mlc.tipcliente.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryTipclienteResponse extends InquiryResponse
{
    private List<Tipcliente> tipcliente ;


    public List<Tipcliente> getTipcliente() {
      return tipcliente;
    }


    public void setTipcliente(List<Tipcliente> tipcliente) {
        this.tipcliente = tipcliente;
}



    public String toString() {
    return "InquiryTipclienteResponse [tipcliente=" + tipcliente
      + ", getTipcliente()=" + getTipcliente()
    + "]";
    }

}
