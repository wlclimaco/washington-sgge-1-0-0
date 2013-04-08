package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.empresa.model.Empresa;


public class EmpresaRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Empresa  empresa;
}
