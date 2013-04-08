package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.municipio.model.Municipio;


public class MunicipioRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Municipio  municipio;
}
