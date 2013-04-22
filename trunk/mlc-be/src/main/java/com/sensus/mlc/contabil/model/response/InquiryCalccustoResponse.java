package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryCalccustoResponse extends InquiryResponse
{
    private List<Calccusto> calccusto ;


    public List<Calccusto> getCalccusto() {
      return calccusto;
    }


    public void setCalccusto(List<Calccusto> calccusto) {
        this.calccusto = calccusto;
}



    public String toString() {
    return "InquiryCalccustoResponse [calccusto=" + calccusto
      + ", getCalccusto()=" + getCalccusto()
    + "]";
    }

}
