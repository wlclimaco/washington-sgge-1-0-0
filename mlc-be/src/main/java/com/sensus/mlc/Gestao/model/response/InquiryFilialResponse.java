Memo1
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryFilialResponse extends InquiryResponse

    private List<Filial> filial ;


    public List<Filial> getFilial() {
      return Filial;
    }


    public void setFilial(List<Filial> Filial) {
        this.Filial = Filial;
}



    public String toString() {
    return "InquiryFilialResponse [Filial=" + Filial
      + ", getFilial()=" + getFilial()
    + "]";


}
