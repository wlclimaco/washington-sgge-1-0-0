package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfitcalccustoResponse extends InquiryResponse
{
    private List<Lfitcalccusto> lfitcalccusto ;


    public List<Lfitcalccusto> getLfitcalccusto() {
      return lfitcalccusto;
    }


    public void setLfitcalccusto(List<Lfitcalccusto> lfitcalccusto) {
        this.lfitcalccusto = lfitcalccusto;
}



    public String toString() {
    return "InquiryLfitcalccustoResponse [lfitcalccusto=" + lfitcalccusto
      + ", getLfitcalccusto()=" + getLfitcalccusto()
    + "]";
    }

}
