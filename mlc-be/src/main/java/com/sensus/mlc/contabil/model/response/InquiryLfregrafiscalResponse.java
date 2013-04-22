package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfregrafiscalResponse extends InquiryResponse
{
    private List<Lfregrafiscal> lfregrafiscal ;


    public List<Lfregrafiscal> getLfregrafiscal() {
      return lfregrafiscal;
    }


    public void setLfregrafiscal(List<Lfregrafiscal> lfregrafiscal) {
        this.lfregrafiscal = lfregrafiscal;
}



    public String toString() {
    return "InquiryLfregrafiscalResponse [lfregrafiscal=" + lfregrafiscal
      + ", getLfregrafiscal()=" + getLfregrafiscal()
    + "]";
    }

}
