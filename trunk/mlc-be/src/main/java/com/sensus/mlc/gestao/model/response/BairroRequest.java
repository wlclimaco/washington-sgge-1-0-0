package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.bairro.model.Bairro;


public class BairroRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Bairro  bairro;
}
