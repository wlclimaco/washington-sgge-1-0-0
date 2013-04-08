package com.sensus.mlc.empresa.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.empresa.model.Empresa;


public class EmpresaResponse extends Response
{
    
    private Integer parentRetry;
    
    private List<Empresa> empresa ;
}
