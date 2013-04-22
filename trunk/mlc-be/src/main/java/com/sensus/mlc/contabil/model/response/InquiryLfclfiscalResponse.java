package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfclfiscalResponse extends InquiryResponse
{
    private List<Lfclfiscal> lfclfiscal ;


    public List<Lfclfiscal> getLfclfiscal() {
      return lfclfiscal;
    }


    public void setLfclfiscal(List<Lfclfiscal> lfclfiscal) {
        this.lfclfiscal = lfclfiscal;
}



    public String toString() {
    return "InquiryLfclfiscalResponse [lfclfiscal=" + lfclfiscal
      + ", getLfclfiscal()=" + getLfclfiscal()
    + "]";
    }

}
