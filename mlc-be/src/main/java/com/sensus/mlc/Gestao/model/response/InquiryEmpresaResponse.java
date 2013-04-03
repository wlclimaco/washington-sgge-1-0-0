Memo1
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryEmpresaResponse extends InquiryResponse

    private List<Empresa> empresa ;


    public List<Empresa> getEmpresa() {
      return Empresa;
    }


    public void setEmpresa(List<Empresa> Empresa) {
        this.Empresa = Empresa;
}



    public String toString() {
    return "InquiryEmpresaResponse [Empresa=" + Empresa
      + ", getEmpresa()=" + getEmpresa()
    + "]";


}
