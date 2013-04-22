package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfitregrafiscalResponse extends InquiryResponse
{
    private List<Lfitregrafiscal> lfitregrafiscal ;


    public List<Lfitregrafiscal> getLfitregrafiscal() {
      return lfitregrafiscal;
    }


    public void setLfitregrafiscal(List<Lfitregrafiscal> lfitregrafiscal) {
        this.lfitregrafiscal = lfitregrafiscal;
}



    public String toString() {
    return "InquiryLfitregrafiscalResponse [lfitregrafiscal=" + lfitregrafiscal
      + ", getLfitregrafiscal()=" + getLfitregrafiscal()
    + "]";
    }

}
