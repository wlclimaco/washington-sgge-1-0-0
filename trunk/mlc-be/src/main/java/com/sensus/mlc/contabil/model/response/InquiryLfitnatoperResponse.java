package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfitnatoperResponse extends InquiryResponse
{
    private List<Lfitnatoper> lfitnatoper ;


    public List<Lfitnatoper> getLfitnatoper() {
      return lfitnatoper;
    }


    public void setLfitnatoper(List<Lfitnatoper> lfitnatoper) {
        this.lfitnatoper = lfitnatoper;
}



    public String toString() {
    return "InquiryLfitnatoperResponse [lfitnatoper=" + lfitnatoper
      + ", getLfitnatoper()=" + getLfitnatoper()
    + "]";
    }

}
