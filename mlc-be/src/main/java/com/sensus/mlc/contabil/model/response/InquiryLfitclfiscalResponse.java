package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfitclfiscalResponse extends InquiryResponse
{
    private List<Lfitclfiscal> lfitclfiscal ;


    public List<Lfitclfiscal> getLfitclfiscal() {
      return lfitclfiscal;
    }


    public void setLfitclfiscal(List<Lfitclfiscal> lfitclfiscal) {
        this.lfitclfiscal = lfitclfiscal;
}



    public String toString() {
    return "InquiryLfitclfiscalResponse [lfitclfiscal=" + lfitclfiscal
      + ", getLfitclfiscal()=" + getLfitclfiscal()
    + "]";
    }

}
