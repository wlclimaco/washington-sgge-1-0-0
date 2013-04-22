package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfnatoperResponse extends InquiryResponse
{
    private List<Lfnatoper> lfnatoper ;


    public List<Lfnatoper> getLfnatoper() {
      return lfnatoper;
    }


    public void setLfnatoper(List<Lfnatoper> lfnatoper) {
        this.lfnatoper = lfnatoper;
}



    public String toString() {
    return "InquiryLfnatoperResponse [lfnatoper=" + lfnatoper
      + ", getLfnatoper()=" + getLfnatoper()
    + "]";
    }

}
