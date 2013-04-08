package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.endereco.model.Endereco;


public class EnderecoRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Endereco  endereco;
}
