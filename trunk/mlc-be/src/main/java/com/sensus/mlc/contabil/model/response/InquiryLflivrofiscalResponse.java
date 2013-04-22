package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLflivrofiscalResponse extends InquiryResponse
{
    private List<Lflivrofiscal> lflivrofiscal ;


    public List<Lflivrofiscal> getLflivrofiscal() {
      return lflivrofiscal;
    }


    public void setLflivrofiscal(List<Lflivrofiscal> lflivrofiscal) {
        this.lflivrofiscal = lflivrofiscal;
}



    public String toString() {
    return "InquiryLflivrofiscalResponse [lflivrofiscal=" + lflivrofiscal
      + ", getLflivrofiscal()=" + getLflivrofiscal()
    + "]";
    }

}
